package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.TipoTasaDAO;
import com.qbe.cotizador.model.TipoTasa;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TipoTasaController
 */
@WebServlet("/TipoTasaController")
public class TipoTasaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoTasaController() {
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
			
			TipoTasa tipoTasa = new TipoTasa();
			TipoTasaDAO tipoTasaDAO  = new TipoTasaDAO();
			
			JSONObject tipoTasaJSONObject = new JSONObject();
			JSONArray tipoTasaJSONArray = new JSONArray();
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoTasa> results = tipoTasaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoTasa = results.get(i);
					tipoTasaJSONObject.put("id", tipoTasa.getId());
					tipoTasaJSONObject.put("nombre", tipoTasa.getNombre());
					tipoTasaJSONArray.add(tipoTasaJSONObject);
				}			
				result.put("listadoTipoTasa", tipoTasaJSONArray);
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