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

import com.qbe.cotizador.dao.seguridad.NivelMenuDAO;
import com.qbe.cotizador.model.NivelMenu;
import com.qbe.cotizador.transaction.seguridad.NivelMenuTransaction;

/**
 * Servlet implementation class NivelMenu
 */
@WebServlet("/NivelMenuController")
public class NivelMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NivelMenuController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject NivelMenuJSONObject = new JSONObject();
			JSONArray NivelMenuJSONArray = new JSONArray();

			NivelMenu nivelMenu = new NivelMenu();
			NivelMenuDAO nivelMenuDAO = new NivelMenuDAO();
			
			NivelMenuTransaction nivelMenuTransaction = new NivelMenuTransaction();

			if (!codigo.equals(""))
				nivelMenu.setId(codigo);

			if (!nombre.equals(""))
				nivelMenu.setNombre(nombre);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<NivelMenu> results = nivelMenuDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					nivelMenu = results.get(i);
					NivelMenuJSONObject.put("codigo", nivelMenu.getId());
					NivelMenuJSONObject.put("nombre", nivelMenu.getNombre());

					NivelMenuJSONArray.add(NivelMenuJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoNivelMenu", NivelMenuJSONArray);
			}
			
			
//			if(tipoConsulta.equals("encontrarNivelMenuesActivas")){
//				List<NivelMenu> listado = nivelMenuDAO.buscarActivos();
//				if(listado.size() > 0) {
//					JSONObject NivelMenuesJSON = new JSONObject();
//					for(int i=0; i<listado.size(); i++) {
//						nivelMenu = (NivelMenu) listado.get(i);					
//						NivelMenuesJSON.put("id", nivelMenu.getId());
//						NivelMenuesJSON.put("nombre", nivelMenu.getNombre());
//						NivelMenuJSONArray.add(NivelMenuesJSON);
//					}
//				}
//				result.put("NivelMenues", NivelMenuJSONArray);
//			}

			if(tipoConsulta.equals("crear"))
				nivelMenuTransaction.crear(nivelMenu);

			if(tipoConsulta.equals("actualizar"))
				nivelMenuTransaction.editar(nivelMenu);

			if(tipoConsulta.equals("eliminar"))
				nivelMenuTransaction.eliminar(nivelMenu);


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
