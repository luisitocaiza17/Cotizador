package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.EntidadJrDAO;
import com.qbe.cotizador.model.EntidadJr;
import com.qbe.cotizador.transaction.entidad.EntidadJrTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class entidadJr
 */
@WebServlet("/EntidadJrController")
public class EntidadJrController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntidadJrController() {
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
			//String id = request.getParameter("id") == null ? "" : request.getParameter("id");
			String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
			JSONObject entidadJrJSONObject = new JSONObject();
			JSONArray entidadJrJSONArray = new JSONArray();
			EntidadJrDAO entidadJrDAO=new EntidadJrDAO();
			EntidadJr entidadJr=new EntidadJr();
		
			EntidadJrTransaction entidadJrTransaction = new EntidadJrTransaction();

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<EntidadJr> results = entidadJrDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					entidadJr = results.get(i);
					entidadJrJSONObject.put("codigo", entidadJr.getId());
					entidadJrJSONObject.put("nombre", entidadJr.getNombre());
					entidadJrJSONArray.add(entidadJrJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoEntidadJr", entidadJrJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorIdentificacion")){
				entidadJr =  entidadJrDAO.buscarPorIdentificacion(identificacion);

				if(entidadJr!=null&&entidadJr.getIdentificacion()!=null)
				{
					result.put("esRiesgosa", true);
					result.put("id", entidadJr.getId());
					result.put("identificacion", entidadJr.getIdentificacion());
					result.put("nombre", entidadJr.getNombre());
					result.put("apellido", entidadJr.getApellido());
					result.put("nombreCompleto", entidadJr.getNombreCompleto());
				}
				else{
					result.put("esRiesgosa", false);
				}
			}

			if(tipoConsulta.equals("crear"))
				entidadJrTransaction.crear(entidadJr);

			if(tipoConsulta.equals("actualizar"))
				entidadJrTransaction.editar(entidadJr);

			if(tipoConsulta.equals("eliminar"))
				entidadJrTransaction.eliminar(entidadJr);


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
