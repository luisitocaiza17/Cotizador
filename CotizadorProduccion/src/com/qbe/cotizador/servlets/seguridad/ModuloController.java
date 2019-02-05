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
import com.qbe.cotizador.model.Modulo;
import com.qbe.cotizador.transaction.seguridad.ModuloTransaction;

/**
 * Servlet implementation class Color
 */
@WebServlet("/ModuloController")
public class ModuloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModuloController() {
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
			String descripcion= request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			JSONObject moduloJSONObject = new JSONObject();
			JSONArray moduloJSONArray = new JSONArray();
			
			Modulo modulo = new Modulo();
			ModuloDAO moduloDAO = new ModuloDAO();
			
			ModuloTransaction moduloTransaction = new ModuloTransaction();
			
			if(!codigo.equals(""))
				modulo.setId(codigo);

			if(!descripcion.equals(""))
					modulo.setDescripcion(descripcion);
				
			if(!nombre.equals(""))
				modulo.setNombre(nombre);

			if (activo.equals("1"))
				modulo.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				modulo.setActivo(false);
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Modulo> results = moduloDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					modulo = results.get(i);
					moduloJSONObject.put("codigo", modulo.getId());
					moduloJSONObject.put("descripcion", modulo.getDescripcion());
					moduloJSONObject.put("nombre", modulo.getNombre());
					
					if(modulo.getActivo())
						moduloJSONObject.put("activo", "Si");
					else
						moduloJSONObject.put("activo", "No");
					
					moduloJSONArray.add(moduloJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoModulo", moduloJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				moduloTransaction.crear(modulo);

			if(tipoConsulta.equals("actualizar"))
				moduloTransaction.editar(modulo);

			if(tipoConsulta.equals("eliminar"))
				moduloTransaction.eliminar(modulo);
			
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
