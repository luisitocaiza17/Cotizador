package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.model.VigenciaPoliza;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class VigenciaPolizaController
 */
@WebServlet("/VigenciaPolizaController")
public class VigenciaPolizaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VigenciaPolizaController() {
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
			
			VigenciaPoliza vigenciaPoliza = new VigenciaPoliza();
			VigenciaPolizaDAO vigenciaPolizaDAO  = new VigenciaPolizaDAO();
			
			JSONObject vigenciaPolizaJSONObject = new JSONObject();
			JSONArray vigenciaPolizaJSONArray = new JSONArray();
			
			
			if(tipoConsulta.equals("encontrarTodosActivos")){ 
				List<VigenciaPoliza> results = vigenciaPolizaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					vigenciaPoliza = results.get(i);
					vigenciaPolizaJSONObject.put("id", vigenciaPoliza.getId());
					vigenciaPolizaJSONObject.put("nombre", vigenciaPoliza.getNombre());
					vigenciaPolizaJSONArray.add(vigenciaPolizaJSONObject);
				}			
				result.put("vigencias_poliza", vigenciaPolizaJSONArray);
			}
			
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<VigenciaPoliza> results = vigenciaPolizaDAO.buscarTodos();
				for(VigenciaPoliza vigencia : results){
					
					vigenciaPolizaJSONObject.put("codigo", vigencia.getId());					
					vigenciaPolizaJSONObject.put("nombre", vigencia.getNombre());
					
					vigenciaPolizaJSONArray.add(vigenciaPolizaJSONObject);
				}
				
				result.put("listadoVigenciaPoliza",vigenciaPolizaJSONArray);
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
