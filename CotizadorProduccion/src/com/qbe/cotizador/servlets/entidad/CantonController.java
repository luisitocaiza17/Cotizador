package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.transaction.entidad.CantonTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class canton
 */
@WebServlet("/CantonController")
public class CantonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CantonController() {
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
			JSONObject cantonJSONObject = new JSONObject();
			JSONArray cantonJSONArray = new JSONArray();
			CantonDAO cantonDAO=new CantonDAO();
			Canton canton=new Canton();

			CantonTransaction cantonTransaction = new CantonTransaction(); 
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Canton> results = cantonDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					canton = results.get(i);
					cantonJSONObject.put("codigo", canton.getId());
					cantonJSONObject.put("nombre", canton.getNombre());
					cantonJSONArray.add(cantonJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoCanton", cantonJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarTodosCombos")){ 
				List<Canton> results = cantonDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					canton = results.get(i);
					cantonJSONObject.put("id", canton.getId());
					cantonJSONObject.put("nombre", canton.getNombre());
					cantonJSONArray.add(cantonJSONObject);
				}
				result.put("listadoCanton", cantonJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorProvincia")){
				String provincia = request.getParameter("provincia") == null ? "" : request.getParameter("provincia");
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				List<Canton> listado =  cantonDAO.buscarPorProvincia(provinciaDAO.buscarPorId((provincia)));
				if(listado.size() > 0) {
					JSONObject cantonesJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						canton = (Canton) listado.get(i);					
						cantonesJSON.put("id", canton.getId());
						cantonesJSON.put("nombre", canton.getNombre());
						cantonJSONArray.add(cantonesJSON);
					}
				}
				result.put("cantones", cantonJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				cantonTransaction.crear(canton);

			if(tipoConsulta.equals("actualizar"))
				cantonTransaction.editar(canton);

			if(tipoConsulta.equals("eliminar"))
				cantonTransaction.eliminar(canton);


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
