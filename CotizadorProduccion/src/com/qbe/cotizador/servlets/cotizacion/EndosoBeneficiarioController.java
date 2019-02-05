package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.EndosoBeneficiarioTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.util.Utilitarios;

/**
 * Servlet implementation class EndosoBeneficiarioController
 */
@WebServlet("/EndosoBeneficiarioController")
public class EndosoBeneficiarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EndosoBeneficiarioController() {
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
			
			EndosoBeneficiario endosoBeneficiario = new EndosoBeneficiario();
			EndosoBeneficiarioDAO endosoBeneficiarioDAO = new EndosoBeneficiarioDAO();
			
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
			String beneficiarioId = request.getParameter("beneficiarioId") == null ? "" : request.getParameter("beneficiarioId");
			String monto = request.getParameter("monto") == null ? "" : request.getParameter("monto");
			String guardarAsegurado= request.getParameter("guardarAsegurado") == null ? "" : request.getParameter("guardarAsegurado");
			
			EntidadTransaction entidadTransaction= new EntidadTransaction();
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			EndosoBeneficiarioTransaction endosoBeneficiarioTransaction = new EndosoBeneficiarioTransaction();
			
			if(codigo!=null&&!codigo.equals(""))
				endosoBeneficiario=endosoBeneficiarioDAO.buscarPorId(codigo);
			
				if(cotizacionId!=null&&!cotizacionId.equals("")&&!guardarAsegurado.equals("1")){
					CotizacionDAO cotizacionDAO = new CotizacionDAO();
					Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
					//endosoBeneficiario=endosoBeneficiarioDAO.buscarPorCotizacion(cotizacion);
					if(cotizacion.getId()!=null)
						endosoBeneficiario.setCotizacion(cotizacion);
				}
			
			if(beneficiarioId!=null&&!beneficiarioId.equals("")&&!guardarAsegurado.equals("1")){
				BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
				Beneficiario beneficiario=beneficiarioDAO.buscarPorId(beneficiarioId);
				if(beneficiario.getId()!=null)
					endosoBeneficiario.setBeneficiario(beneficiario);
			}
			
			if(!monto.equals("")){
				endosoBeneficiario.setMonto(Double.parseDouble(monto));
			}
			else{
				endosoBeneficiario.setMonto(0);
			}
			
			if(tipoConsulta.equals("crear")&&!guardarAsegurado.equals("1")){
				endosoBeneficiario=endosoBeneficiarioTransaction.crear(endosoBeneficiario);
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				cotizacion.setEtapaWizard(3);
				cotizacionTransaction.editar(cotizacion);
				result.put("endosoBeneficiarioId", endosoBeneficiario.getId());
			}
			
			if(tipoConsulta.equals("actualizar")&&!guardarAsegurado.equals("1")){
				endosoBeneficiario=endosoBeneficiarioTransaction.editar(endosoBeneficiario);
				result.put("endosoBeneficiarioId", endosoBeneficiario.getId());
			}
			
			if(tipoConsulta.equals("eliminar")&&!guardarAsegurado.equals("1")){
				endosoBeneficiarioTransaction.eliminar(endosoBeneficiario);
			}
			
			if(tipoConsulta.equals("encontrarTodos")){
				
				List<EndosoBeneficiario> results= endosoBeneficiarioDAO.buscarTodos();
				
				int i=0;
				for(i=0; i<results.size(); i++){
					endosoBeneficiario = results.get(i);
					endosoBeneficiarioJSONObject.put("codigo", endosoBeneficiario.getId());
					endosoBeneficiarioJSONObject.put("beneficiario", endosoBeneficiario.getBeneficiario().getNombre());
					endosoBeneficiarioJSONObject.put("cotizacion", endosoBeneficiario.getCotizacion().getId());
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
								
				if(!validarIdentificacion(identificacion, tipoIdentificacion))
					throw new Exception("Identificacion no valida! No se pudo guardar la entidad");
				
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
					cotizacion.setEtapaWizard(3);
					cotizacion.setAsegurado(entidad);
					cotizacionTransaction.editar(cotizacion);
				}		
				result.put("aseguradoId", entidad.getId());				
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}	
	}
	public boolean validarIdentificacion(String identificacion, TipoIdentificacion ti){
		boolean retorno=true;
		
		if(ti.getNombre().toUpperCase().equals("CEDULA")||ti.getNombre().toUpperCase().equals("CÉDULA")){
			retorno=Utilitarios.validarCedula(identificacion);
			return retorno;
		}
		if(ti.getNombre().toUpperCase().contains("RUC")){
			retorno=Utilitarios.validaRUC(identificacion);
			return retorno;
		}
		if(ti.getNombre().toUpperCase().equals("PASAPORTE")){
			retorno=!identificacion.toUpperCase().contains("CONFIRMAR");
			return retorno;
		}
		
		return retorno;
	}
}
