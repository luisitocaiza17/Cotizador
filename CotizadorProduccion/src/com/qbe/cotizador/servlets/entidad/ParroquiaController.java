package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.transaction.entidad.ParroquiaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class parroquia
 */
@WebServlet("/ParroquiaController")
public class ParroquiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ParroquiaController() {
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
			JSONObject parroquiaJSONObject = new JSONObject();
			JSONArray parroquiaJSONArray = new JSONArray();
			ParroquiaDAO parroquiaDAO=new ParroquiaDAO();
			Parroquia parroquia=new Parroquia();

			ParroquiaTransaction parroquiaTransaction = new ParroquiaTransaction();
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Parroquia> results = parroquiaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					parroquia = results.get(i);
					parroquiaJSONObject.put("codigo", parroquia.getId());
					parroquiaJSONObject.put("nombre", parroquia.getNombre());
					parroquiaJSONArray.add(parroquiaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoParroquia", parroquiaJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorCanton")){
				String canton = request.getParameter("canton") == null ? "" : request.getParameter("canton");
				CantonDAO cantonDAO= new CantonDAO();
				List<Parroquia> listado =  parroquiaDAO.buscarPorCanton(cantonDAO.buscarPorId((canton)));
				if(listado.size() > 0) {
					JSONObject parroquiasJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						parroquia = (Parroquia) listado.get(i);					
						parroquiasJSON.put("id", parroquia.getId());
						parroquiasJSON.put("nombre", parroquia.getNombre());
						parroquiaJSONArray.add(parroquiasJSON);
					}
				}
				result.put("listadoParroquia", parroquiaJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				parroquiaTransaction.crear(parroquia);

			if(tipoConsulta.equals("actualizar"))
				parroquiaTransaction.editar(parroquia);

			if(tipoConsulta.equals("eliminar"))
				parroquiaTransaction.eliminar(parroquia);


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
