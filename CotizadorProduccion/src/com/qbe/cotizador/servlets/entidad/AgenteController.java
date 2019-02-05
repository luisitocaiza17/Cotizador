package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgenteAcuerdo;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.Usuario;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgenteController
 */
@WebServlet("/AgenteController")
public class AgenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgenteController() {
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
		String tipoConsulta 	= request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");;
		JSONArray jsonArray 	= new JSONArray();		
		JSONObject result 		= new JSONObject();
	
		Agente agente 			= new Agente();
		AgenteDAO agenteDAO 	= new AgenteDAO();
		try{		
		// Consulta todos los agentes activos
		if(tipoConsulta.equalsIgnoreCase("consultarAgentes"))
		{						
				List<Agente> listado 		= agenteDAO.buscarActivos();
					if(listado.size() > 0) {
						JSONObject agentesJSON = new JSONObject();
						for(int i=0; i<listado.size(); i++) {
							agente = (Agente) listado.get(i);					
							agentesJSON.put("id", agente.getId());
							agentesJSON.put("nombre", agente.getEntidad().getNombreCompleto());
							jsonArray.add(agentesJSON);
						}
					}
					result.put("agentes", jsonArray);
		}
		
	    // RID: Consulta del agente por medio del ID
		if(tipoConsulta.equalsIgnoreCase("ObtenerAgenteId"))
		{						
			String agenteId = request.getParameter("agenteId");
			if(agenteId.length() >0){
			agente	= agenteDAO.buscarPorId(agenteId);							
			result.put("comision", agente.getComisionVh());	
			result.put("comisionVariable", agente.getComisionVariable());
			result.put("comision1", agente.getComision1());
			result.put("comision2", agente.getComision2());
			result.put("comision3", agente.getComision3());
			Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
			Rol rolUsuario = usuario.getUsuarioRols().get(0).getRol();
			result.put("rol", rolUsuario.getId());
			}
		}
		
		// Obtener los acuerdos del cliente
		if(tipoConsulta.equalsIgnoreCase("ObtenerAgenteAcuerdo"))
		{
			String agenteId = request.getParameter("agenteId");
			if(agenteId.length() >0){
				List<AgenteAcuerdo> agenteAcuerdo = new ArrayList<AgenteAcuerdo>();
				agenteAcuerdo = agenteDAO.buscarAgentesAcuerdo(agenteId);
				if(agenteAcuerdo.size() > 0) {
					JSONObject agentesAcuerdoJSON = new JSONObject();
					for(int i=0; i<agenteAcuerdo.size(); i++) {
						AgenteAcuerdo agenteAcuerdoObjeto = (AgenteAcuerdo) agenteAcuerdo.get(i);					
						agentesAcuerdoJSON.put("id", agenteAcuerdoObjeto.getId());
						agentesAcuerdoJSON.put("nombre", agenteAcuerdoObjeto.getNombre());
						jsonArray.add(agentesAcuerdoJSON);
					}
				}
				result.put("acuerdoAgentes", jsonArray);
				result.put("numAcuerdoAgentes", jsonArray.size());
			}
		}
		
		if(tipoConsulta.equals("cargaSelect2")){
			List<Agente> results = agenteDAO.buscarActivos();
			int i=0;
			JSONArray agentes=new JSONArray();
			JSONObject agenteJSON=new JSONObject();
			for(i=0; i<results.size(); i++){
				agente = results.get(i);
				//marcaJSONObject.put("codigo", marca.getId());
				agenteJSON.put("id", agente.getId());
				agenteJSON.put("text", agente.getEntidad().getNombreCompleto());
				agentes.add(agenteJSON);
			}
			result.put("listadoAgentes", agentes);				
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

}
