package com.qbe.cotizador.servlets.cotizacion.vehiculos_dinamicos.tarificador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.VhTari1ZonaDAO;
import com.qbe.cotizador.model.VhTari1Zona;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TipoTasaController
 */
@WebServlet("/TipoTasaController")
public class TarificadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TarificadorController() {
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
			
			VhTari1ZonaDAO vhTari1ZonaDAO  = new VhTari1ZonaDAO();
			VhTari1Zona vhTari1Zona = new VhTari1Zona();
						
			JSONObject vhTari1ZonaJSONObject = new JSONObject();
			JSONArray vhTari1ZonaJSONArray = new JSONArray();
						
			if(tipoConsulta.equals("encontrarTodosVhTari1Zona")){ 
				List<VhTari1Zona> results = vhTari1ZonaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					vhTari1Zona = results.get(i);
					vhTari1ZonaJSONObject.put("id", vhTari1Zona.getId());
					vhTari1ZonaJSONObject.put("nombre", vhTari1Zona.getNombre());
					vhTari1ZonaJSONArray.add(vhTari1ZonaJSONObject);
				}			
				result.put("listadoVhTari1Zona", vhTari1ZonaJSONArray);
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