package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.transaction.entidad.UnidadNegocioTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UnidadNegocioController
 */
@WebServlet("/UnidadNegocioController")
public class UnidadNegocioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnidadNegocioController() {
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

		UnidadNegocioTransaction unidadNegocioTransaction = new UnidadNegocioTransaction();
		
		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String un_ensurance = request.getParameter("un_ensurance") == null ? "" : request.getParameter("un_ensurance");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			UnidadNegocio un 	= new UnidadNegocio();
			UnidadNegocioDAO unDAO 	= new UnidadNegocioDAO();
			JSONObject unJSONObject = new JSONObject();
			JSONArray unJSONArray = new JSONArray();


			if (!codigo.equals(""))
				un.setId(codigo);

			if (!nombre.equals(""))
				un.setNombre(nombre);
			
			if (!un_ensurance.equals(""))
				un.setUnEnsurance(un_ensurance);

			if (activo.equals("1"))
				un.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				un.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<UnidadNegocio> results = unDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					un = results.get(i);
					unJSONObject.put("codigo", un.getId());
					unJSONObject.put("nombre", un.getNombre());
					unJSONObject.put("un_ensurance", un.getUnEnsurance() );
					
					if(un.getActivo())
						unJSONObject.put("activo", "Si");
					else
						unJSONObject.put("activo", "No");

					unJSONArray.add(unJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoUN", unJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				unidadNegocioTransaction.crear(un);

			if(tipoConsulta.equals("actualizar"))
				unidadNegocioTransaction.editar(un);

			if(tipoConsulta.equals("eliminar"))
			{
				un = unDAO.buscarPorId(un.getId());
				unidadNegocioTransaction.eliminar(un);
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
