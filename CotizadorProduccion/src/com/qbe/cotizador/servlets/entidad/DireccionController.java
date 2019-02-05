package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DireccionController
 */
@WebServlet("/DireccionController")
public class DireccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DireccionController() {
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
		String tipoConsulta 	= request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
		JSONObject result 		= new JSONObject();
		
		try{		
			
			if(tipoConsulta.equals("guardarDatosFacturaSolicitante")){
				result.put("resultado", guardarDatosFacturaSolicitante(request));
			}
			
			if(tipoConsulta.equals("guardarDatosFacturaSolicitanteBeneficiario")){
				result.put("resultado", guardarDatosFacturaSolicitanteBeneficiario(request));
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
	
	public JSONObject guardarDatosFacturaSolicitanteBeneficiario(HttpServletRequest request){
		JSONObject retorno = new JSONObject();
		
		String zonaSolicitante= request.getParameter("zonaSolicitante") == null ? "" : request.getParameter("zonaSolicitante");
		String parroquiaSolicitante= request.getParameter("parroquiaSolicitante") == null ? "" : request.getParameter("parroquiaSolicitante");
		String ciudadSolicitante= request.getParameter("ciudadSolicitante") == null ? "" : request.getParameter("ciudadSolicitante");
		String principalSolicitante= request.getParameter("principalSolicitante") == null ? "" : request.getParameter("principalSolicitante");
		String numeroSolicitante= request.getParameter("numeroSolicitante") == null ? "" : request.getParameter("numeroSolicitante");
		String secundariaSolicitante= request.getParameter("secundariaSolicitante") == null ? "" : request.getParameter("secundariaSolicitante");
		String referenciaSolicitante= request.getParameter("referenciaSolicitante") == null ? "" : request.getParameter("referenciaSolicitante");
		String telefonoSolicitante= request.getParameter("telefonoSolicitante") == null ? "" : request.getParameter("telefonoSolicitante");
		String celularSolicitante= request.getParameter("celularSolicitante") == null ? "" : request.getParameter("celularSolicitante");
		String emailSolicitante= request.getParameter("emailSolicitante") == null ? "" : request.getParameter("emailSolicitante");
		String identificacionSolicitante= request.getParameter("identificacionSolicitante") == null ? "" : request.getParameter("identificacionSolicitante");
		String zonaAsegurado= request.getParameter("zonaAsegurado") == null ? "" : request.getParameter("zonaAsegurado");
		String parroquiaAsegurado= request.getParameter("parroquiaAsegurado") == null ? "" : request.getParameter("parroquiaAsegurado");
		String ciudadAsegurado= request.getParameter("ciudadAsegurado") == null ? "" : request.getParameter("ciudadAsegurado");
		String principalAsegurado= request.getParameter("principalAsegurado") == null ? "" : request.getParameter("principalAsegurado");
		String numeroAsegurado= request.getParameter("numeroAsegurado") == null ? "" : request.getParameter("numeroAsegurado");
		String secundariaAsegurado= request.getParameter("secundariaAsegurado") == null ? "" : request.getParameter("secundariaAsegurado");
		String referenciaAsegurado= request.getParameter("referenciaAsegurado") == null ? "" : request.getParameter("referenciaAsegurado");
		String telefonoAsegurado= request.getParameter("telefonoAsegurado") == null ? "" : request.getParameter("telefonoAsegurado");
		String celularAsegurado= request.getParameter("celularAsegurado") == null ? "" : request.getParameter("celularAsegurado");
		String emailAsegurado= request.getParameter("emailAsegurado") == null ? "" : request.getParameter("emailAsegurado");
		String identificacionAsegurado= request.getParameter("identificacionAsegurado") == null ? "" : request.getParameter("identificacionAsegurado");
		String cotizacionId= request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");

		DireccionTransaction direccionTransaction = new DireccionTransaction();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		EntidadTransaction entidadTransaction = new EntidadTransaction();
		
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		Cotizacion cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
		if(cotizacion!=null&&cotizacion.getId()!=null){
			cotizacion.setEtapaWizard(4);
			cotizacion=cotizacionTransaction.editar(cotizacion);
		}
		
		EntidadDAO entidadDAO=new EntidadDAO();
		Entidad solicitante=entidadDAO.buscarEntidadPorIdentificacion(identificacionSolicitante);
		
		solicitante.setCelular(celularSolicitante);
		solicitante.setTelefono(telefonoSolicitante);
		solicitante.setMail(emailSolicitante);
		solicitante=entidadTransaction.editar(solicitante);
		
		DireccionDAO direccionDAO=new DireccionDAO(); 
		Direccion direccionSolicitante =new Direccion();
		direccionSolicitante.setEntidad(solicitante);
		if(direccionDAO.buscarPorEntidadId(solicitante).size()>0)
			direccionSolicitante=direccionDAO.buscarPorEntidadId(solicitante).get(0);
		direccionSolicitante.setCallePrincipal(principalSolicitante);
		direccionSolicitante.setCalleSecundaria(secundariaSolicitante);
		direccionSolicitante.setNumero(numeroSolicitante);
		direccionSolicitante.setDatosDeReferencia(referenciaSolicitante);
		
		CiudadDAO ciudadDAO = new CiudadDAO();
		Ciudad ciudad=ciudadDAO.buscarPorId(ciudadSolicitante);
		
		if(ciudad!=null&&ciudad.getId()!=null)
			direccionSolicitante.setCiudad(ciudad);
		
		ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
		Parroquia parroquia=parroquiaDAO.buscarPorId(parroquiaSolicitante);
		
		if(parroquia!=null&&parroquia.getId()!=null)
			direccionSolicitante.setParroquia(parroquia);
		
		if(zonaSolicitante.equals("U")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionSolicitante.setZona(zDAO.buscarPorId("1"));
		}
		if(zonaSolicitante.equals("R")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionSolicitante.setZona(zDAO.buscarPorId("2"));
		}		
		
		TipoDireccionDAO tdDAO=new TipoDireccionDAO();
		direccionSolicitante.setTipoDireccion(tdDAO.buscarPorId("3"));//direccion de cobro
		
		if(direccionSolicitante.getId()==null)
			direccionSolicitante=direccionTransaction.crear(direccionSolicitante);
		else
			direccionSolicitante=direccionTransaction.editar(direccionSolicitante);
		
		retorno.put("direccionSolicitanteId", direccionSolicitante.getId());
		
		//asegurado
		entidadDAO=new EntidadDAO();
		Entidad asegurado=entidadDAO.buscarEntidadPorIdentificacion(identificacionAsegurado);
		
		asegurado.setCelular(celularAsegurado);
		asegurado.setTelefono(telefonoAsegurado);
		asegurado.setMail(emailAsegurado);
		asegurado=entidadTransaction.editar(asegurado);
		
		direccionDAO=new DireccionDAO(); 
		Direccion direccionAsegurado =new Direccion();
		direccionAsegurado.setEntidad(asegurado);
		if(direccionDAO.buscarPorEntidadId(asegurado).size()>0)
			direccionAsegurado=direccionDAO.buscarPorEntidadId(asegurado).get(0);
		direccionAsegurado.setCallePrincipal(principalAsegurado);
		direccionAsegurado.setCalleSecundaria(secundariaAsegurado);
		direccionAsegurado.setNumero(numeroAsegurado);
		direccionAsegurado.setDatosDeReferencia(referenciaAsegurado);
		
		ciudadDAO = new CiudadDAO();
		ciudad=ciudadDAO.buscarPorId(ciudadAsegurado);
		
		if(ciudad!=null&&ciudad.getId()!=null)
			direccionAsegurado.setCiudad(ciudad);
		
		parroquiaDAO = new ParroquiaDAO();
		parroquia=parroquiaDAO.buscarPorId(parroquiaAsegurado);
		
		if(parroquia!=null&&parroquia.getId()!=null)
			direccionAsegurado.setParroquia(parroquia);
		
		if(zonaAsegurado.equals("U")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionAsegurado.setZona(zDAO.buscarPorId("1"));
		}
		if(zonaAsegurado.equals("R")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionAsegurado.setZona(zDAO.buscarPorId("2"));
		}		
		
		tdDAO=new TipoDireccionDAO();
		direccionAsegurado.setTipoDireccion(tdDAO.buscarPorId("3"));//direccion de cobro
		
		if(direccionAsegurado.getId()==null)
			direccionAsegurado=direccionTransaction.crear(direccionAsegurado);
		else
			direccionAsegurado=direccionTransaction.editar(direccionAsegurado);
		
		retorno.put("direccionAseguradoId", direccionAsegurado.getId());
		
		return retorno;
	}

	public JSONObject guardarDatosFacturaSolicitante(HttpServletRequest request){
		JSONObject retorno = new JSONObject();
		
		String zonaSolicitante= request.getParameter("zonaSolicitante") == null ? "" : request.getParameter("zonaSolicitante");
		String parroquiaSolicitante= request.getParameter("parroquiaSolicitante") == null ? "" : request.getParameter("parroquiaSolicitante");
		String ciudadSolicitante= request.getParameter("ciudadSolicitante") == null ? "" : request.getParameter("ciudadSolicitante");
		String principalSolicitante= request.getParameter("principalSolicitante") == null ? "" : request.getParameter("principalSolicitante");
		String numeroSolicitante= request.getParameter("numeroSolicitante") == null ? "" : request.getParameter("numeroSolicitante");
		String secundariaSolicitante= request.getParameter("secundariaSolicitante") == null ? "" : request.getParameter("secundariaSolicitante");
		String referenciaSolicitante= request.getParameter("referenciaSolicitante") == null ? "" : request.getParameter("referenciaSolicitante");
		String telefonoSolicitante= request.getParameter("telefonoSolicitante") == null ? "" : request.getParameter("telefonoSolicitante");
		String celularSolicitante= request.getParameter("celularSolicitante") == null ? "" : request.getParameter("celularSolicitante");
		String emailSolicitante= request.getParameter("emailSolicitante") == null ? "" : request.getParameter("emailSolicitante");
		String identificacionSolicitante= request.getParameter("identificacionSolicitante") == null ? "" : request.getParameter("identificacionSolicitante");
		String cotizacionId= request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
		
		DireccionTransaction direccionTransaction = new DireccionTransaction();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		EntidadTransaction entidadTransaction = new EntidadTransaction();
		
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		Cotizacion cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
		if(cotizacion!=null&&cotizacion.getId()!=null){
			cotizacion.setEtapaWizard(4);
			cotizacion=cotizacionTransaction.editar(cotizacion);
		}
		
		EntidadDAO entidadDAO=new EntidadDAO();
		Entidad solicitante=entidadDAO.buscarEntidadPorIdentificacion(identificacionSolicitante);
		
		solicitante.setCelular(celularSolicitante);
		solicitante.setTelefono(telefonoSolicitante);
		solicitante.setMail(emailSolicitante);
		solicitante=entidadTransaction.editar(solicitante);
		
		DireccionDAO direccionDAO=new DireccionDAO(); 
		Direccion direccionSolicitante =new Direccion();
		if(direccionDAO.buscarPorEntidadId(solicitante).size()>0)
			direccionSolicitante=direccionDAO.buscarPorEntidadId(solicitante).get(0);
		direccionSolicitante.setEntidad(solicitante);
		direccionSolicitante.setCallePrincipal(principalSolicitante);
		direccionSolicitante.setCalleSecundaria(secundariaSolicitante);
		direccionSolicitante.setNumero(numeroSolicitante);
		direccionSolicitante.setDatosDeReferencia(referenciaSolicitante);
		
		CiudadDAO ciudadDAO = new CiudadDAO();
		Ciudad ciudad=ciudadDAO.buscarPorId(ciudadSolicitante);
		
		if(ciudad!=null&&ciudad.getId()!=null)
			direccionSolicitante.setCiudad(ciudad);
		
		ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
		Parroquia parroquia=parroquiaDAO.buscarPorId(parroquiaSolicitante);
		
		if(parroquia!=null&&parroquia.getId()!=null)
			direccionSolicitante.setParroquia(parroquia);
		
		if(zonaSolicitante.equals("U")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionSolicitante.setZona(zDAO.buscarPorId("1"));
		}
		if(zonaSolicitante.equals("R")){
			ZonaDAO zDAO=new ZonaDAO();
			direccionSolicitante.setZona(zDAO.buscarPorId("2"));
		}		
		
		TipoDireccionDAO tdDAO=new TipoDireccionDAO();
		direccionSolicitante.setTipoDireccion(tdDAO.buscarPorId("3"));//direccion de cobro
		
		if(direccionSolicitante.getId()==null)
			direccionSolicitante=direccionTransaction.crear(direccionSolicitante);
		else
			direccionSolicitante=direccionTransaction.editar(direccionSolicitante);
		
		retorno.put("direccionSolicitanteId", direccionSolicitante.getId());
		
		return retorno;
	}

}
