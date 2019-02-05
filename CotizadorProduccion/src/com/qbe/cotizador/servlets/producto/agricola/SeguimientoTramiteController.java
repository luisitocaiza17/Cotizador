package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoDetalleDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoDetalle;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.seguridad.VariableSistemaTransaction;

/**
 * Servlet implementation class SeguimientoTramiteController
 */
@WebServlet("/SeguimientoTramiteController")
public class SeguimientoTramiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguimientoTramiteController() {
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
		
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
		
		if(tipoConsulta.equalsIgnoreCase("obtenerPorId")){
			String id = request.getParameter("id") == null ? "": request.getParameter("id");
			
			VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
			VariableSistema variableSistema= variableSistemaDAO.buscarPorId2(Integer.parseInt(id));
			
			result.put("id", variableSistema.getId());
			result.put("nombre", variableSistema.getNombre());
			result.put("valor", variableSistema.getValor());
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());		
			
		}
		
		if(tipoConsulta.equalsIgnoreCase("encontrarCotizacion")){
			String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
			String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
			String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
			String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
			String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
			String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
			String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
			 // get the take and skip parameters
			  int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			  int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));


			JSONArray listaCotizacionesJSONArray = new JSONArray();
			listaCotizacionesJSONArray = new JSONArray();
			
			AgriObjetoDetalleDAO agriObjetoDetalleDAO = new AgriObjetoDetalleDAO();
			String clienteId="";
			
			
			List<BigInteger> data = agriObjetoDetalleDAO.buscarPorCotizacionesIds(fInicio, fFinal, numeroCotizacion, puntoVentaId, NroTramite, Identificacion,CanalId,skip, take);
			
			List<BigInteger> ids= new ArrayList<BigInteger>();
			for(BigInteger rs :data){
				ids.add(rs);			
			}
			CotizacionAprobacionDAO cotizacionAprobacionDAO = new CotizacionAprobacionDAO();
			List<AgriCotizacionAprobacion> data2=cotizacionAprobacionDAO.consultarCotizacionIdsIN(ids, skip, take);
			
			long total = cotizacionAprobacionDAO.consultarCotizacionIdsINumero(ids, skip, take);
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(data2);
			
			//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;
			
			
		}
		
		
		if(tipoConsulta.equalsIgnoreCase("encontrar")){
			String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
			String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
			String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
			String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
			String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
			String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
			String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
			 // get the take and skip parameters
			  int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			  int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));


			JSONArray listaCotizacionesJSONArray = new JSONArray();
			listaCotizacionesJSONArray = new JSONArray();
			
			AgriObjetoDetalleDAO agriObjetoDetalleDAO = new AgriObjetoDetalleDAO();
			String clienteId="";
			
			
			List<AgriObjetoDetalle> data = agriObjetoDetalleDAO.buscarPorCotizaciones(fInicio, fFinal, numeroCotizacion, puntoVentaId, NroTramite, Identificacion,CanalId,skip, take);
			
			List<SeguimientoTramitesObjeto> Listobjetos= new ArrayList<SeguimientoTramitesObjeto>();
			for(AgriObjetoDetalle rs :data){
				SeguimientoTramitesObjeto objeto= new SeguimientoTramitesObjeto();
				objeto.setCotizacionId(rs.getCotizacionId());
				objeto.setId2(""+rs.getAgricolaId());
				//hallamos el ciclo
				AgriCicloDAO agriCicloDAO = new AgriCicloDAO();
				AgriCiclo agriCiclo = agriCicloDAO.BuscarPorId(rs.getPeriodoId());
				objeto.setPeriodo(agriCiclo.getNombre());
				
				//hallamos el cliente;
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente =clienteDAO.buscarPorId(""+rs.getClienteId());
				objeto.setCedulaCliente(cliente.getEntidad().getIdentificacion());
				objeto.setCliente(cliente.getEntidad().getNombreCompleto());
				
				//hallamos el canal
				AgriCanalDAO agriCanalDAO = new AgriCanalDAO();
				AgriCanal agriCanal =  agriCanalDAO.BuscarPorId(rs.getCanalId());
				objeto.setCanal(agriCanal.getNombre());
				
				//hallamos el punto de venta
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				com.qbe.cotizador.model.PuntoVenta puntoVenta = puntoVentaDAO.buscarPorId(""+rs.getPuntoVentaId());
				objeto.setPuntoVenta(puntoVenta.getNombre());
				
				objeto.setNumeroTramite(rs.getNumeroTramite());
				objeto.setVigenciaDesde(rs.getVigenciaDesde());
				objeto.setVigenciaHasta(rs.getVigenciaHasta());
				
				//hallamos el cultivo
				AgriTipoCultivoDAO agriTipoCultivoDAO =new AgriTipoCultivoDAO();
				AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorId(rs.getCultivoId());
				objeto.setCultivo(agriTipoCultivo.getNombre());
				
				//hallamos la provincia
				ProvinciaDAO provinciaDAO = new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia = provinciaDAO.buscarPorId(""+rs.getProvinciaId());
				objeto.setProvincia(provincia.getNombre());
				
				//hallo el canton
				CantonDAO cantonDAO = new CantonDAO();
				com.qbe.cotizador.model.Canton canton = cantonDAO.buscarPorId(""+rs.getCantonId());
				objeto.setCanton(canton.getNombre());
				
				//hallo la parroquia
				try{
					AgriParroquiaDAO agriParroquiaDAO = new AgriParroquiaDAO();
					AgriParroquia agriParroquia = agriParroquiaDAO.BuscarPorId(Integer.parseInt(""+ rs.getParroquiaId()));
					if(agriParroquia.getParroquiaNombre()!=null){
						objeto.setParroquia(agriParroquia.getParroquiaNombre());
					}else{
						ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
						Parroquia parroquia =parroquiaDAO.buscarPorId(""+ rs.getParroquiaId());
						objeto.setParroquia(parroquia.getNombre());
					}
				}catch(Exception e){
					
				}
				objeto.setFechaCreacion(rs.getFechaElaboracion());
				objeto.setSitioInvesion(rs.getSitioInversion());
				objeto.setTelefono(rs.getTelefono());
				objeto.setHectareasAseguradas(""+rs.getHectareasAseguradas());
				objeto.setHectareasLote(""+rs.getHectareasLote());
				objeto.setFechaSiembra(rs.getFechaSiembra());
				objeto.setMontoAsegurado(rs.getMontoAsegurado());
				objeto.setCostroProduccion(rs.getCostoProduccion());
				objeto.setTasa(rs.getTasa());
				objeto.setSuperBancos(rs.getSuperBancos());
				objeto.setDerechoEmision(rs.getDerechoEmision());
				objeto.setIva(rs.getIva());
				objeto.setPrimaNeta(rs.getPrimaNeta());
				objeto.setPrimaTotal(rs.getPrimaTotal());
				objeto.setLatitud(Double.parseDouble(rs.getLatitud()));
				objeto.setLongitud(Double.parseDouble(rs.getLongitud()));
				
				if(rs.isDisponeRiego())
					objeto.setDisponeRiego("SI");
				else
					objeto.setDisponeRiego("NO");
				
				if(rs.isAgricultorTecnificado())
					objeto.setAgricultoTecnificado("SI");
				else
					objeto.setAgricultoTecnificado("NO");
				if(rs.isDisponeAsistencia())
					objeto.setDisponeAsistencia("SI");
				else
					objeto.setDisponeAsistencia("NO");
				objeto.setObservacion(rs.getObservacion());
				objeto.setObservacionQBE(rs.getObservacionQbe());
				Listobjetos.add(objeto);				
			}
			
			
			long total = agriObjetoDetalleDAO.buscarPorCotizacionesNum(fInicio, fFinal, numeroCotizacion, puntoVentaId, NroTramite, Identificacion,CanalId,skip, take);
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(Listobjetos);
			
			//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;
			
			
		}
		
	}
}
