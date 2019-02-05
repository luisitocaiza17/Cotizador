package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeEndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeEndosoBeneficiario;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeEndosoBeneficiarioTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;

/**
 * Servlet implementation class EndosoBeneficiarioController
 */
@WebServlet("/PymeEndosoBeneficiarioController")
public class PymeEndosoBeneficiarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeEndosoBeneficiarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject endosoBeneficiarioJSONObject = new JSONObject();
			JSONArray endosoBeneficiarioJSONArray = new JSONArray();
			
			PymeEndosoBeneficiario endosoBeneficiario = new PymeEndosoBeneficiario();
			PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO = new PymeEndosoBeneficiarioDAO();
			
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
			String beneficiarioId = request.getParameter("beneficiarioId") == null ? "" : request.getParameter("beneficiarioId");
			String monto = request.getParameter("monto") == null ? "" : request.getParameter("monto");
			String rubro = request.getParameter("beneficiario_rubro") == null ? "" : request.getParameter("beneficiario_rubro");
			String guardarAsegurado= request.getParameter("guardarAsegurado") == null ? "" : request.getParameter("guardarAsegurado");
			
			EntidadTransaction entidadTransaction= new EntidadTransaction();
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			PymeEndosoBeneficiarioTransaction endosoBeneficiarioTransaction = new PymeEndosoBeneficiarioTransaction();
			
			if(codigo!=null&&!codigo.equals(""))
				endosoBeneficiario=pymeEndosoBeneficiarioDAO.buscarPorId(new BigInteger(codigo));
			
				if(cotizacionId!=null&&!cotizacionId.equals("")&&!guardarAsegurado.equals("1")){
					CotizacionDAO cotizacionDAO = new CotizacionDAO();
					Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
					//endosoBeneficiario=endosoBeneficiarioDAO.buscarPorCotizacion(cotizacion);
					if(cotizacion.getId()!=null)
						endosoBeneficiario.setCotizacionId(new BigInteger(cotizacion.getId()));
				}
			
			if(rubro!=null&&!rubro.equals("")){
				endosoBeneficiario.setRubro(Integer.parseInt(rubro));
			}
					
			if(beneficiarioId!=null&&!beneficiarioId.equals("")&&!guardarAsegurado.equals("1")){
				BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
				Beneficiario beneficiario=beneficiarioDAO.buscarPorId(beneficiarioId);
				if(beneficiario.getId()!=null)
					endosoBeneficiario.setBeneficiarioId(new BigInteger(beneficiario.getId()));
			}
			
			if(!monto.equals("")){
				endosoBeneficiario.setMonto(Double.parseDouble(monto));
			}
			else{
				endosoBeneficiario.setMonto(0);
			}
			
			if(tipoConsulta.equals("crear")&&!guardarAsegurado.equals("1")){
				
				//Valido el monto
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				List<CotizacionDetalle> detalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				double valorEstructuras=0;
				for(CotizacionDetalle detalleActual:detalles){
					PymeObjetoCotizacion pymeObjetoCotizacion = pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(rubro.equals("1")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorEstructuras();
					}
					if(rubro.equals("2")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMueblesEnseres();
					}
					if(rubro.equals("3")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMaquinaria();
					}
					if(rubro.equals("4")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMercaderia();
					}
					if(rubro.equals("5")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorInsumosNoelectronicos();
					}
					if(rubro.equals("6")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorEquipoHerramienta();
					}
				}
				if(endosoBeneficiario.getMonto()>valorEstructuras){
					throw new Exception("El monto del endoso no puede ser mayor al valor asegurado.");
				}
				
				endosoBeneficiario=endosoBeneficiarioTransaction.crear(endosoBeneficiario);
				
				cotizacion.setEtapaWizard(4);
				cotizacionTransaction.editar(cotizacion);
				result.put("endosoBeneficiarioId", endosoBeneficiario.getId());
			}
			
			if(tipoConsulta.equals("actualizar")&&!guardarAsegurado.equals("1")){
				//Valido el monto
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				List<CotizacionDetalle> detalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				double valorEstructuras=0;
				for(CotizacionDetalle detalleActual:detalles){
					PymeObjetoCotizacion pymeObjetoCotizacion = pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(rubro.equals("1")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorEstructuras();
					}
					if(rubro.equals("2")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMueblesEnseres();
					}
					if(rubro.equals("3")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMaquinaria();
					}
					if(rubro.equals("4")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorMercaderia();
					}
					if(rubro.equals("5")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorInsumosNoelectronicos();
					}
					if(rubro.equals("6")){
						valorEstructuras = valorEstructuras + pymeObjetoCotizacion.getValorEquipoHerramienta();
					}
				}
				if(endosoBeneficiario.getMonto()>valorEstructuras){
					throw new Exception("El monto del endoso no puede ser mayor al valor asegurado.");
				}
				
				endosoBeneficiario=endosoBeneficiarioTransaction.editar(endosoBeneficiario);
				result.put("endosoBeneficiarioId", endosoBeneficiario.getId());
			}
			
			if(tipoConsulta.equals("eliminar")&&!guardarAsegurado.equals("1")){
				endosoBeneficiario=pymeEndosoBeneficiarioDAO.buscarPorId(new BigInteger(codigo));
				endosoBeneficiarioTransaction.eliminar(endosoBeneficiario.getId());
			}
			
			if(tipoConsulta.equals("encontrarTodos")){
				
				List<PymeEndosoBeneficiario> results= pymeEndosoBeneficiarioDAO.buscarTodos();
				
				int i=0;
				for(PymeEndosoBeneficiario endosoBeneficiarioActual:results){
					endosoBeneficiarioJSONObject.put("codigo", endosoBeneficiarioActual.getId());
					endosoBeneficiarioJSONObject.put("beneficiario", endosoBeneficiarioActual.getBeneficiarioId());
					endosoBeneficiarioJSONObject.put("cotizacion", endosoBeneficiarioActual.getCotizacionId());
					endosoBeneficiarioJSONObject.put("rubro", endosoBeneficiarioActual.getRubro());
					endosoBeneficiarioJSONObject.put("monto", endosoBeneficiarioActual.getMonto());
					endosoBeneficiarioJSONArray.add(endosoBeneficiarioJSONObject);
				}
				
				
				result.put("totalEndosoBeneficiarios", i);
				result.put("listadoEndosoBeneficiario", endosoBeneficiarioJSONArray);
				
			}
			
			if(guardarAsegurado.equals("1")){
				String tipoIdentificacionId= request.getParameter("tipoIdentificacionAsegurado") == null ? "" : request.getParameter("tipoIdentificacionAsegurado");
				String identificacion= request.getParameter("identificacionAsegurado") == null ? "" : request.getParameter("identificacionAsegurado");
				String nombre= request.getParameter("nombreAsegurado") == null ? "" : request.getParameter("nombreAsegurado");
				String apellido= request.getParameter("apellidoAsegurado") == null ? "" : request.getParameter("apellidoAsegurado");
				String nombreCompleto= request.getParameter("nombreCompletoAsegurado") == null ? "" : request.getParameter("nombreCompletoAsegurado");
				
				EntidadDAO entidadDAO=new EntidadDAO();
				Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				
				TipoIdentificacionDAO tipoIdentificacionDAO=new TipoIdentificacionDAO();
				TipoIdentificacion tipoIdentificacion=tipoIdentificacionDAO.buscarPorId(tipoIdentificacionId) ;
								
				entidad.setTipoIdentificacion(tipoIdentificacion);
				entidad.setApellidos(apellido);
				entidad.setNombres(nombre);
				entidad.setNombreCompleto(nombreCompleto);
				entidad.setIdentificacion(identificacion);
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				
				if(tipoIdentificacionId.equalsIgnoreCase("4"))
					entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));
				else					
					entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
				
				if(entidad.getId()==null)
					entidad=entidadTransaction.crear(entidad);
				else
					entidad=entidadTransaction.editar(entidad);
						
				if(cotizacionId!=null&&!cotizacionId.equals("")){
					CotizacionDAO cotizacionDAO = new CotizacionDAO();
					Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
					cotizacion.setEtapaWizard(4);
					cotizacion.setAsegurado(entidad);
					cotizacionTransaction.editar(cotizacion);
				}		
				result.put("aseguradoId", entidad.getId());				
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			/***TRATAMIENTO DE ERROR***/
			Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
			String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
			/***AUDITORIA auditamos el error para el seguimiento***/
			PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
			PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral.setTramite(CodError);
			pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
			pymeAuditoriaGeneral.setProceso("PYMES");
			pymeAuditoriaGeneral.setObjeto(e.getMessage()+"||"+e.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}	
	}

}
