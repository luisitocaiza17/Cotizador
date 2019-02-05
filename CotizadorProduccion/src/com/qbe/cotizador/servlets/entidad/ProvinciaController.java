package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.transaction.entidad.ProvinciaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class provincia
 */
@WebServlet("/ProvinciaController")
public class ProvinciaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProvinciaController() {
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
			JSONObject provinciaJSONObject = new JSONObject();
			JSONArray provinciaJSONArray = new JSONArray();
			ProvinciaDAO provinciaDAO=new ProvinciaDAO();
			Provincia provincia=new Provincia();

			ProvinciaTransaction provinciaTransaction = new ProvinciaTransaction();
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Provincia> results = provinciaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					provincia = results.get(i);
					provinciaJSONObject.put("codigo", provincia.getId());
					provinciaJSONObject.put("nombre", provincia.getNombre());
					provinciaJSONArray.add(provinciaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoProvincia", provinciaJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				provinciaTransaction.crear(provincia);

			if(tipoConsulta.equals("actualizar"))
				provinciaTransaction.editar(provincia);

			if(tipoConsulta.equals("eliminar"))
				provinciaTransaction.eliminar(provincia);


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
