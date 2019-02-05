package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.model.TipoIdentificacion;

/**
 * Servlet implementation class TipoIdentificacionController
 */
@WebServlet("/TipoIdentificacionController")
public class TipoIdentificacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoIdentificacionController() {
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
		String tipoConsulta 							= request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");;
		JSONArray jsonArray 							= new JSONArray();		
		JSONObject result 								= new JSONObject();
		TipoIdentificacion tipoIdentificacion 			= new TipoIdentificacion();
		TipoIdentificacionDAO tipoIdentificacionDAO 	= new TipoIdentificacionDAO();
				
		// R: Consulta todos los tipos de identificacion ayanez
		if(tipoConsulta.equalsIgnoreCase("ObtenerTodos"))
		{						
				List<TipoIdentificacion> listado = tipoIdentificacionDAO.buscarTodos();
					if(listado.size() > 0) {
						JSONObject tipoIdentificacionJSON = new JSONObject();
						for(int i=0; i<listado.size(); i++) {
							tipoIdentificacion = (TipoIdentificacion) listado.get(i);					
							tipoIdentificacionJSON.put("id", tipoIdentificacion.getId());
							tipoIdentificacionJSON.put("nombre", tipoIdentificacion.getNombre());
							jsonArray.add(tipoIdentificacionJSON);
						}
					}
		result.put("tipoIdentificacion", jsonArray);
		result.put("success", Boolean.TRUE);
		response.setContentType("application/json; charset=ISO-8859-1"); 
		result.write(response.getWriter());
		}
	}

}
