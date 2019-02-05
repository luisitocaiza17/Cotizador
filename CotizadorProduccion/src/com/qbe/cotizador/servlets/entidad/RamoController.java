package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.model.Ramo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Ramo
 */
@WebServlet("/RamoController")
public class RamoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RamoController() {
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
			JSONObject ramoJSONObject = new JSONObject();
			JSONArray ramoJSONArray = new JSONArray();
			RamoDAO ramoDAO=new RamoDAO();
			Ramo ramo=new Ramo();

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Ramo> results = ramoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					ramo = results.get(i);
					ramoJSONObject.put("codigo", ramo.getId());
					ramoJSONObject.put("nombre", ramo.getNombre());
					ramoJSONArray.add(ramoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoRamo", ramoJSONArray);
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
