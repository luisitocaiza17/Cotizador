package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.TipoDeducibleDAO;
import com.qbe.cotizador.model.TipoDeducible;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TipoCoberturaController
 */
@WebServlet("/TipoDeducibleController")
public class TipoDeducibleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoDeducibleController() {
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
			
			TipoDeducibleDAO tipoDeducibleDAO  = new TipoDeducibleDAO();
			
			JSONObject tipoDeducibleJSONObject = new JSONObject();
			JSONArray tipoDeducibleJSONArray = new JSONArray();
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoDeducible> results = tipoDeducibleDAO.buscarTodos();
				for(TipoDeducible tipoDeducibleActual:results){
					tipoDeducibleJSONObject.put("id", tipoDeducibleActual.getId());
					tipoDeducibleJSONObject.put("nombre", tipoDeducibleActual.getNombre());
					tipoDeducibleJSONObject.put("texto_descripcion", tipoDeducibleActual.getTextoDescripcion());
					tipoDeducibleJSONArray.add(tipoDeducibleJSONObject);
				}			
				result.put("listadoTipoDeducible", tipoDeducibleJSONArray);
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