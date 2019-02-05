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

import com.qbe.cotizador.dao.seguridad.ModuloDAO;
import com.qbe.cotizador.dao.seguridad.OpcionPantallaDAO;
import com.qbe.cotizador.model.Modulo;
import com.qbe.cotizador.model.OpcionPantallaUsuario;
import com.qbe.cotizador.model.Usuario;

/**
 * Servlet implementation class Color
 */
@WebServlet("/OpcionPantallaController")
public class OpcionPantallaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpcionPantallaController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject opcionPantallaJSONObject = new JSONObject();			
			

			if(tipoConsulta.equals("activarOpcionesPantallaVH")){
				OpcionPantallaDAO opcionPantallaDAO = new OpcionPantallaDAO();
				// Obtenemos los datos del usuario
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				if(usuario==null||usuario.getId()==null)
					throw new Exception("Usuario sin sesión");
				
				List<OpcionPantallaUsuario>opcionPantallaUsuarioListado = opcionPantallaDAO.buscarOpcionesPantallaUsuario(usuario);
				
				String opciones = "";
				for(int i=0; i<opcionPantallaUsuarioListado.size(); i++){
					opciones+=opcionPantallaUsuarioListado.get(i).getOpcionPantalla().getIdentificador();
				}															
				result.put("Opciones", opciones);
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
