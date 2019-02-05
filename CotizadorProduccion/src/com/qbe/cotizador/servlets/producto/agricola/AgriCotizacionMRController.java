package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMaxDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.AgriCotizacionMax;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriCotizacionMRController
 */
@WebServlet("/AgriCotizacionMRController")
public class AgriCotizacionMRController extends HttpServlet {
	private int ContadorGeneral;
	private int ContadorTotal;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCotizacionMRController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String puntoVenta = request.getParameter("puntoVenta") == null ? "": request.getParameter("puntoVenta").trim();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta").trim();
			
			//Proceso
			boolean correcto=false;
			
			if(tipoConsulta.equals("EliminarDuplicadosTramites")){
				ContadorGeneral=0;
				ContadorTotal=0;
				/**Consultamos todas las cotizaciones con duplicidada dependiendo del punto de venta**/
				if(puntoVenta!=null&&!puntoVenta.equals("")){
					correcto=procesoNumeroTramite(puntoVenta);
				}else{
					//Todas las cotizaciones de un canal
					String canal = request.getParameter("canalId") == null ? "": request.getParameter("canalId").trim();
					AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO= new AgriParametroXPuntoVentaDAO();
					List<AgriParametroXPuntoVenta> agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarTodosCanal(new BigInteger(canal));
					for(AgriParametroXPuntoVenta rs:agriParametroXPuntoVenta){
						correcto=procesoNumeroTramite(rs.getPuntoVentaId().toString());
					}
				}
				if(!correcto)
					throw new Exception("No se pudo completar el proceso de tramites duplicados");
				
			}
			
			if(tipoConsulta.equals("EliminarDuplicadosPolizas")){
				ContadorGeneral=0;
				ContadorTotal=0;
				correcto=procesNumeroFactura();
				if(!correcto)
					throw new Exception("No se pudo completar el proceso de ids duplicados");
			}
			result.put("total", ContadorTotal);
			result.put("actual", ContadorGeneral);
			result.put("faltante", ContadorTotal-ContadorGeneral);
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}
	
	private boolean procesNumeroFactura(){
		/***Reasignacion de ids de polizas a cotizaciones SUCRE***/
		try{
			int contadorSobreCarga=0;
			AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
			AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
			auditoria.setTramite("Eliminacion Duplicados poliza");
			String DatosRecibidos = " ";
			
			auditoria.setObjeto(DatosRecibidos);
			java.util.Date date2 = new java.util.Date();
			java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
			auditoria.setFecha(sq2);
			auditoria.setCanal("TODOS");
			auditoria.setEstado("DUPLICADOS");
			procesoAuditoria.crear(auditoria);
			
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
			//obtengo las cotizaciones con polizas repetidas
			List<BigInteger> listaCotizaciones =agriObjetoCotizacionDAO.buscaPolizasRepetidas(); 
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
			
			general:for(BigInteger rs:listaCotizaciones){
				if(contadorSobreCarga++>100)
					break general;
				//buscamos las cotizacion con el mismo numero de poliza
				List<AgriObjetoCotizacion> listaCotizacionesReasignacion =agriObjetoCotizacionDAO.buscarPorIdPoliza(rs); 
				//buscamos las cotizaciones que estan correctas
				BigInteger cotizacionCorrecta=agriObjetoCotizacionDAO.buscarMaxIdTramites(rs);
				
				for(AgriObjetoCotizacion rs2:listaCotizacionesReasignacion){
					
					if(!rs2.getObjetoCotizacionId().toString().equals(cotizacionCorrecta.toString())){
						//AUDITAMOS
						DatosRecibidos=DatosRecibidos+""+rs2.getIdCotizacion2();						
						//BUSCAMOS EL NUMERO DE POLIZA
						AgriCotizacionMaxDAO busquedaMax = new AgriCotizacionMaxDAO();
						AgriCotizacionMax numMaximo=busquedaMax.buscarTodos();
						int numeroActual=numMaximo.getMaximo().intValue();
						rs2.setIdCotizacion2(new BigInteger(""+(numeroActual+1)));
						
						//ACTUALIZAMOS
						agriObjetoCotizacionTransaction.editar(rs2);
						//AUDITAMOS
						auditoria.setObjeto(DatosRecibidos);
						DatosRecibidos=DatosRecibidos+":"+rs2.getIdCotizacion2()+",";						
						procesoAuditoria.editar(auditoria);
					}
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean procesoNumeroTramite(String puntoVentaId){
		PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
		PuntoVenta puntoVenta = puntoVentaDAO.buscarPorId(puntoVentaId);
		TipoObjetoDAO tipoObjetoDAO = new  TipoObjetoDAO();
		TipoObjeto tipoObjeto=tipoObjetoDAO.buscarPorId("8");
		CotizacionDAO cotizacionDAO= new CotizacionDAO();
		try{
			
			AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
			AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
			auditoria.setTramite("Eliminacion Duplicados Tramites");
			String DatosRecibidos = " ";
			
			auditoria.setObjeto(DatosRecibidos);
			java.util.Date date2 = new java.util.Date();
			java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
			auditoria.setFecha(sq2);
			auditoria.setCanal("TODOS");
			auditoria.setEstado("DUPLICADOS");
			procesoAuditoria.crear(auditoria);
			
			/**Listado de cotizaciones con Duplicados**/
			List<String> listaCotizaciones= cotizacionDAO.buscarRepetidos(tipoObjeto, puntoVenta);
			ContadorTotal=ContadorTotal+listaCotizaciones.size();
			
			/*
			 * 1.- ponemos a las cotizaciones como revocadas
			 * 2.- ponemos a un sola cotizacion como repetida por cotizaciones repetidas.
			 * 3.- borramos el numero de tramite de cotizaciones repetidas.
			  */
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			
			int contadorSobrecarga=0;
			
			/**1.- recorremos el listado para encontrar todas las cotizaciones con numero de tramites repetidos**/
			procesoGeneral:for(String cot:listaCotizaciones){
				ContadorGeneral++;
				if(contadorSobrecarga++>100)
					break procesoGeneral;
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado= estadoDAO.buscarPorNombre("Revocado", "Cotizacion");
				// tomamos las cotizaciones repetidas que no coincidan con las que se van a 
				//quedar y se las pone como revocadas,
				//y se actualiza el numero de tramite como B-(Borrador)
				List<Cotizacion> listaCotizacionesTotales= cotizacionDAO.buscarPorNumeroTramiteTodos(cot);
				//buscamos el numero de cotizacion que no se va a cambiar
				String CotizacionCorrecta= cotizacionDAO.buscarMaxIdTramites(cot);
				
				int i=0;
				for(Cotizacion cot2: listaCotizacionesTotales){
					if(!cot2.getId().equals(CotizacionCorrecta)){
						//AUDITAMOS
						DatosRecibidos=DatosRecibidos+""+cot2.getNumeroTramite();
						
						i=i+1;
						cot2.setEstado(estado);
						cot2.setNumeroTramite("B-"+i+"-"+cot2.getNumeroTramite());
						cotizacionTransaction.editar(cot2);
						
						//AUDITAMOS
						auditoria.setObjeto(DatosRecibidos);
						DatosRecibidos=DatosRecibidos+":"+cot2.getNumeroTramite()+",";						
						procesoAuditoria.editar(auditoria);
					}
				}
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
