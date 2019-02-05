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
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.dao.seguridad.TipoRolModuloDAO;
import com.qbe.cotizador.model.Modulo;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoRolModulo;
import com.qbe.cotizador.transaction.seguridad.TipoRolModuloTransaction;


/**
 * Servlet implementation class TipoRolModuloController
 */
@WebServlet("/TipoRolModuloController")
public class TipoRolModuloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoRolModuloController() {
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
			String moduloId = request.getParameter("moduloId") == null ? "" : request.getParameter("moduloId");
			String rolId = request.getParameter("rolId") == null ? "" : request.getParameter("rolId");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject tipoRolModuloJSONObject = new JSONObject();
			JSONArray tipoRolModuloJSONArray = new JSONArray();

			TipoRolModulo tipoRolModulo = new TipoRolModulo();
			TipoRolModuloDAO tipoRolModuloDAO = new TipoRolModuloDAO();

			Modulo modulo = new Modulo();
			ModuloDAO moduloDAO = new ModuloDAO();

			Rol rol = new Rol();
			RolDAO rolDAO = new RolDAO();
			
			TipoRolModuloTransaction tipoRolModuloTransaction = new TipoRolModuloTransaction();
			
			if (!codigo.equals(""))
				tipoRolModulo.setId(codigo);

			if (!moduloId.equals("")){
				modulo = moduloDAO.buscarPorId(moduloId);				
				tipoRolModulo.setModulo(modulo);        
			}
			
			if (!rolId.equals("")){
				rol =  rolDAO.buscarPorId(rolId);
				tipoRolModulo.setRol(rol);
			}

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoRolModulo> results = tipoRolModuloDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoRolModulo = results.get(i);
					tipoRolModuloJSONObject.put("codigo", tipoRolModulo.getId());
					tipoRolModuloJSONObject.put("moduloId", tipoRolModulo.getModulo().getNombre());
					tipoRolModuloJSONObject.put("rolId", tipoRolModulo.getRol().getNombre());

					tipoRolModuloJSONArray.add(tipoRolModuloJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoRolModulo", tipoRolModuloJSONArray);
				
				// Se consultan todos los usuarios activos para mostrar en los combos de la pantalla de parametrizacion
				JSONObject moduloJSONObject = new JSONObject();
				JSONArray moduloJSONArray = new JSONArray();
				List <Modulo> listaModulo =  moduloDAO.buscarActivos();
				for (i=0; i<listaModulo.size(); i++){
					modulo = listaModulo.get(i);
					moduloJSONObject.put("codigo", modulo.getId());
					moduloJSONObject.put("nombre", modulo.getNombre());
					moduloJSONArray.add(moduloJSONObject);
				}
				result.put("listaModulo", moduloJSONArray);
				
				// Se consultan todos los puntos de venta activos para mostrar en los combos de la pantalla de parametrizacion
				JSONObject rolJSONObject = new JSONObject();
				JSONArray rolJSONArray = new JSONArray();
				List <Rol> listaRol =  rolDAO.buscarTodosActivos();
				for (i=0; i<listaRol.size(); i++){
					rol = listaRol.get(i);
					rolJSONObject.put("codigo", rol.getId());
					rolJSONObject.put("nombre", rol.getNombre());
					rolJSONArray.add(rolJSONObject);
				}
				result.put("listaRol", rolJSONArray);
				
			}
			
			
			if(tipoConsulta.equals("crear"))
				tipoRolModuloTransaction.crear(tipoRolModulo);

			if(tipoConsulta.equals("actualizar"))
				tipoRolModuloTransaction.editar(tipoRolModulo);

			if(tipoConsulta.equals("eliminar"))
				tipoRolModuloTransaction.eliminar(tipoRolModulo);


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
