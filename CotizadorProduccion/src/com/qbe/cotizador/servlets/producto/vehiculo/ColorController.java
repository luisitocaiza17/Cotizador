package com.qbe.cotizador.servlets.producto.vehiculo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.transaction.producto.vehiculo.ColorTransaction;

/**
 * Servlet implementation class ColorController
 */
@WebServlet("/ColorController")
public class ColorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColorController() {
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
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject colorJSONObject = new JSONObject();
			JSONArray colorJSONArray = new JSONArray();
			
			ColorTransaction colorTransaction = new ColorTransaction();
			
			Color color = new Color();
			ColorDAO colorDAO = new ColorDAO();
			
			if(!codigoEnsurance.equals(""))
				color.setColEnsurance(codigoEnsurance);
			
			if(!codigo.equals(""))
				color.setId(codigo);;

			if(!nombre.equals(""))
				color.setNombre(nombre);
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Color> results = colorDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					color = results.get(i);
					colorJSONObject.put("codigo", color.getId());
					colorJSONObject.put("codigoEnsurance", color.getColEnsurance());
					colorJSONObject.put("nombre", color.getNombre());
					colorJSONObject.put("activo", color.getActivo());
					colorJSONArray.add(colorJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoColor", colorJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				colorTransaction.crear(color);

			if(tipoConsulta.equals("actualizar"))
				colorTransaction.editar(color);

			if(tipoConsulta.equals("eliminar"))
				colorTransaction.eliminar(color);			

			if(tipoConsulta.equals("cargaSelect2")){
				List<Color> results = colorDAO.buscarActivos();
				int i=0;
				for(i=0; i<results.size(); i++){
					color = results.get(i);
					//marcaJSONObject.put("codigo", marca.getId());
					colorJSONObject.put("id", color.getId());
					colorJSONObject.put("text", color.getNombre());
					colorJSONArray.add(colorJSONObject);
				}
				result.put("listadoColor", colorJSONArray);				
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
