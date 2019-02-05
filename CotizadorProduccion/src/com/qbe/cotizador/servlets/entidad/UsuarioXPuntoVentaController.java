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

import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.UsuarioXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioXPuntoVenta;
import com.qbe.cotizador.transaction.entidad.UsuarioXPuntoVentaTransaction;

/**
 * Servlet implementation class UsuarioXPuntoVentaController
 */
@WebServlet("/UsuarioXPuntoVentaController")
public class UsuarioXPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioXPuntoVentaController() {
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
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String usuarioId = request.getParameter("usuario") == null ? "" : request.getParameter("usuario");
			String puntoVentaId = request.getParameter("punto_venta") == null ? "" : request.getParameter("punto_venta");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject usuarioPuntoVentaJSONObject = new JSONObject();
			JSONArray usuarioPuntoVentaJSONArray = new JSONArray();

			UsuarioXPuntoVenta usuarioPuntoVenta = new UsuarioXPuntoVenta();
			UsuarioXPuntoVentaDAO usuarioPuntoVentaDAO = new UsuarioXPuntoVentaDAO();
			
			UsuarioXPuntoVentaTransaction usuarioXPuntoVentaTransaction = new UsuarioXPuntoVentaTransaction();
			
			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			PuntoVenta puntoVenta = new PuntoVenta();
			PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
			
			if (!codigo.equals(""))
				usuarioPuntoVenta.setId(codigo);

			if (!usuarioId.equals("")){
				usuario = usuarioDAO.buscarPorId(usuarioId);
				usuarioPuntoVenta.setUsuario(usuario);        
			}
			
			if (!puntoVentaId.equals("")){
				puntoVenta =  puntoVentaDAO.buscarPorId(puntoVentaId);
				usuarioPuntoVenta.setPuntoVenta(puntoVenta);
			}

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<UsuarioXPuntoVenta> results = usuarioPuntoVentaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					usuarioPuntoVenta = results.get(i);
					usuarioPuntoVentaJSONObject.put("codigo", usuarioPuntoVenta.getId());
					usuarioPuntoVentaJSONObject.put("usuario", usuarioPuntoVenta.getUsuario().getEntidad().getNombreCompleto());
					usuarioPuntoVentaJSONObject.put("puntoVenta", usuarioPuntoVenta.getPuntoVenta().getNombre());
					
					usuarioPuntoVentaJSONArray.add(usuarioPuntoVentaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listaUsuarioPuntoVenta", usuarioPuntoVentaJSONArray);

				// Se consultan todos los usuarios activos para mostrar en los combos de la pantalla de parametrizacion
				JSONObject usuarioJSONObject = new JSONObject();
				JSONArray usuarioJSONArray = new JSONArray();
				List <Usuario> listaUsuario =  usuarioDAO.buscarActivos();
				for (i=0; i<listaUsuario.size(); i++){
					usuario = listaUsuario.get(i);
					usuarioJSONObject.put("codigo", usuario.getId());
					usuarioJSONObject.put("nombre", usuario.getEntidad().getNombreCompleto());
					usuarioJSONArray.add(usuarioJSONObject);
				}
				result.put("listaUsuario", usuarioJSONArray);
				
				// Se consultan todos los puntos de venta activos para mostrar en los combos de la pantalla de parametrizacion
				JSONObject puntoVentaJSONObject = new JSONObject();
				JSONArray puntoVentaJSONArray = new JSONArray();
				List <PuntoVenta> listaPuntoVenta =  puntoVentaDAO.buscarActivos();
				for (i=0; i<listaPuntoVenta.size(); i++){
					puntoVenta = listaPuntoVenta.get(i);
					puntoVentaJSONObject.put("codigo", puntoVenta.getId());
					puntoVentaJSONObject.put("nombre", puntoVenta.getNombre());
					puntoVentaJSONArray.add(puntoVentaJSONObject);
				}
				result.put("listaPuntoVenta", puntoVentaJSONArray);
			}
				
			if(tipoConsulta.equals("crear"))
				usuarioXPuntoVentaTransaction.crear(usuarioPuntoVenta);

			if(tipoConsulta.equals("actualizar"))
				usuarioXPuntoVentaTransaction.editar(usuarioPuntoVenta);

			if(tipoConsulta.equals("eliminar"))
				usuarioXPuntoVentaTransaction.eliminar(usuarioPuntoVenta);

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
