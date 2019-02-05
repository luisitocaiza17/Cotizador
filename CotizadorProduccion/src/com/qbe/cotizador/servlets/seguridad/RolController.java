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

import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.transaction.seguridad.RolTransaction;

/**
 * Servlet implementation class Rol
 */
@WebServlet("/RolController")
public class RolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RolController() {
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

		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject RolJSONObject = new JSONObject();
			JSONArray RolJSONArray = new JSONArray();

			RolTransaction rolTransaction = new RolTransaction();
			
			Rol rol = new Rol();
			RolDAO rolDAO = new RolDAO();

			if (!codigo.equals(""))
				rol.setId(codigo);

			if (!nombre.equals(""))
				rol.setNombre(nombre);
			if (!descripcion.equals(""))
				rol.setDescripcion(descripcion);

			if (activo.equals("1"))
				rol.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				rol.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Rol> results = rolDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					rol = results.get(i);
					RolJSONObject.put("codigo", rol.getId());
					RolJSONObject.put("nombre", rol.getNombre());
					RolJSONObject.put("descripcion", rol.getDescripcion());
					
					if(rol.getActivo())
						RolJSONObject.put("activo", "Si");
					else
						RolJSONObject.put("activo", "No");

					RolJSONArray.add(RolJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoRol", RolJSONArray);
			}
			
			
			if(tipoConsulta.equals("encontrarRolesActivas")){
				List<Rol> listado = rolDAO.buscarTodosActivos();
				if(listado.size() > 0) {
					JSONObject RolesJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						rol = (Rol) listado.get(i);					
						RolesJSON.put("id", rol.getId());
						RolesJSON.put("nombre", rol.getNombre());
						RolesJSON.put("descripcion", rol.getDescripcion());
						RolJSONArray.add(RolesJSON);
					}
				}
				result.put("Roles", RolJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				rolTransaction.crear(rol);

			if(tipoConsulta.equals("actualizar"))
				rolTransaction.editar(rol);

			if(tipoConsulta.equals("eliminar"))
				rolTransaction.eliminar(rol);


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
