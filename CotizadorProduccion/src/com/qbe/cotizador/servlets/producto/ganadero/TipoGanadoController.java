package com.qbe.cotizador.servlets.producto.ganadero;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.ganadero.TipoGanadoDAO;
import com.qbe.cotizador.model.TipoGanado;

/**
 * Servlet implementation class TipoGanadoController
 */
@WebServlet("/TipoGanadoController")
public class TipoGanadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoGanadoController() {
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
			JSONObject formaPagoJSONObject = new JSONObject();
			JSONArray formaPagoJSONArray = new JSONArray();

			TipoGanadoDAO tipoGanadoDAO = new TipoGanadoDAO();
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoGanado> results = tipoGanadoDAO.buscarTodos();
				
				for(TipoGanado tipoGanado:results){
					formaPagoJSONObject.put("id", tipoGanado.getId());
					formaPagoJSONObject.put("nombre", tipoGanado.getNombre());
					formaPagoJSONArray.add(formaPagoJSONObject);
				}
				result.put("listadoTiposGanado", formaPagoJSONArray);
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
