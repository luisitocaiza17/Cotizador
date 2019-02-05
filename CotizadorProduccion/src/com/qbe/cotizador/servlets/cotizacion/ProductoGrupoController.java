package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.ProductoGrupoDAO;
import com.qbe.cotizador.model.ProductoGrupo;
import com.qbe.cotizador.transaction.cotizacion.ProductoGrupoTransaction;

/**
 * Servlet implementation class ProductoGrupoController
 */
@WebServlet("/ProductoGrupoController")
public class ProductoGrupoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoGrupoController() {
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
			JSONObject cargoJSONObject = new JSONObject();
			JSONArray cargoJSONArray = new JSONArray();

			ProductoGrupo pg = new ProductoGrupo();
			ProductoGrupoDAO pgDAO = new ProductoGrupoDAO();
			
			ProductoGrupoTransaction productoGrupoTransaction = new ProductoGrupoTransaction();

			if (!codigo.equals(""))
				pg.setId(codigo);

			if (!nombre.equals(""))
				pg.setNombre(nombre);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<ProductoGrupo> results = pgDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					pg = results.get(i);
					cargoJSONObject.put("codigo", pg.getId());
					cargoJSONObject.put("nombre", pg.getNombre());
					
					cargoJSONArray.add(cargoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoProductoGrupo", cargoJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				productoGrupoTransaction.crear(pg);

			if(tipoConsulta.equals("actualizar"))
				productoGrupoTransaction.editar(pg);

			if(tipoConsulta.equals("eliminar"))
			{
				ProductoGrupo productoG = pgDAO.buscarPorId(pg.getId());
				productoGrupoTransaction.eliminar(productoG);
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
