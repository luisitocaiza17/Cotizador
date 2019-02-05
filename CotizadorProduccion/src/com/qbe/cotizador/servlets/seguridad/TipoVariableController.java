package com.qbe.cotizador.servlets.seguridad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.transaction.seguridad.TipoVariableTransaction;

/**
 * Servlet implementation class TipoVariable
 */
@WebServlet("/TipoVariableController")
public class TipoVariableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoVariableController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
			
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject tipoVariableJSONObject = new JSONObject();
			JSONArray tipoVariableJSONArray = new JSONArray();

			TipoVariable tipoVariable = new TipoVariable();
			TipoVariableDAO tipoVariableDAO = new TipoVariableDAO();
			
			TipoVariableTransaction tipoVariableTransaction = new TipoVariableTransaction(); 

			if (!codigo.equals(""))
				tipoVariable.setId(codigo);

			if (!nombre.equals(""))
				tipoVariable.setNombre(nombre);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoVariable> results = tipoVariableDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVariable = results.get(i);
					tipoVariableJSONObject.put("codigo", tipoVariable.getId());
					tipoVariableJSONObject.put("nombre", tipoVariable.getNombre());
					
					tipoVariableJSONArray.add(tipoVariableJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVariable", tipoVariableJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				tipoVariableTransaction.crear(tipoVariable);

			if(tipoConsulta.equals("actualizar"))
				tipoVariableTransaction.editar(tipoVariable);

			if(tipoConsulta.equals("eliminar"))
				tipoVariableTransaction.eliminar(tipoVariable);


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
