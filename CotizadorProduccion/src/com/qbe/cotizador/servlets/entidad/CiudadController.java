package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.transaction.entidad.CiudadTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ciudad
 */
@WebServlet("/CiudadController")
public class CiudadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CiudadController() {
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
			JSONObject ciudadJSONObject = new JSONObject();
			JSONArray ciudadJSONArray = new JSONArray();
			CiudadDAO ciudadDAO=new CiudadDAO();
			Ciudad ciudad=new Ciudad();

			CiudadTransaction ciudadTransaction = new CiudadTransaction(); 
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Ciudad> results = ciudadDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					ciudad = results.get(i);
					ciudadJSONObject.put("codigo", ciudad.getId());
					ciudadJSONObject.put("nombre", ciudad.getNombre());
					ciudadJSONArray.add(ciudadJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoCiudad", ciudadJSONArray);
			}
			
			if(tipoConsulta.equals("busquedaGeneral")){ 
				List<Ciudad> results = ciudadDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					ciudad = results.get(i);
					ciudadJSONObject.put("id", ciudad.getId());
					ciudadJSONObject.put("nombre", ciudad.getNombre());
					ciudadJSONArray.add(ciudadJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoCiudad", ciudadJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorProvincia")){
				String provincia = request.getParameter("provincia") == null ? "" : request.getParameter("provincia");
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				List<Ciudad> listado =  ciudadDAO.buscarPorProvincia(provinciaDAO.buscarPorId((provincia)));
				if(listado.size() > 0) {
					JSONObject ciudadesJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						ciudad = (Ciudad) listado.get(i);					
						ciudadesJSON.put("id", ciudad.getId());
						ciudadesJSON.put("nombre", ciudad.getNombre());
						ciudadJSONArray.add(ciudadesJSON);
					}
				}
				result.put("ciudades", ciudadJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				ciudadTransaction.crear(ciudad);

			if(tipoConsulta.equals("actualizar"))
				ciudadTransaction.editar(ciudad);

			if(tipoConsulta.equals("eliminar"))
				ciudadTransaction.eliminar(ciudad);


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
