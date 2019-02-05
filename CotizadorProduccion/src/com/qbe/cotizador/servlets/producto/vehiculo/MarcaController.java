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

import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.transaction.producto.vehiculo.MarcaTransaction;

/**
 * Servlet implementation class Color
 */
@WebServlet("/MarcaController")
public class MarcaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcaController() {
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
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject marcaJSONObject = new JSONObject();
			JSONArray marcaJSONArray = new JSONArray();
			
			Marca marca = new Marca();
			MarcaDAO marcaDAO = new MarcaDAO();
			
			MarcaTransaction marcaTransaction = new MarcaTransaction();
			
			if(!codigoEnsurance.equals(""))
				marca.setMarEnsurance(codigoEnsurance);
			
			if(!codigo.equals(""))
				marca.setId(codigo);;

			if(!nombre.equals(""))
				marca.setNombre(nombre);
			
			if(activo.equals("1"))
				marca.setActivo(true);
			else
				marca.setActivo(false);
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Marca> results = marcaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					marca = results.get(i);
					marcaJSONObject.put("codigo", marca.getId());
					marcaJSONObject.put("codigoEnsurance", marca.getMarEnsurance());
					marcaJSONObject.put("nombre", marca.getNombre());
					marcaJSONObject.put("activo", marca.getActivo());
					marcaJSONArray.add(marcaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoMarca", marcaJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				marcaTransaction.crear(marca);

			if(tipoConsulta.equals("actualizar"))
				marcaTransaction.editar(marca);

			if(tipoConsulta.equals("eliminar"))
				marcaTransaction.eliminar(marca);			

			if(tipoConsulta.equals("autocomplete")){
				List<Marca> results = marcaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					marca = results.get(i);
					//marcaJSONObject.put("codigo", marca.getId());
					marcaJSONObject.put("codigo", marca.getId());
					marcaJSONObject.put("nombre", marca.getNombre());
					marcaJSONArray.add(marcaJSONObject);
				}
				result.put("listadoMarca", marcaJSONArray);				
			}
			
			if(tipoConsulta.equals("cargaSelect2")){
				List<Marca> results = marcaDAO.buscarActivos();
				int i=0;
				//marcaJSONObject.put("id", "-1");
				//marcaJSONObject.put("text", "Escoja una marca");
				//marcaJSONArray.add(marcaJSONObject);
				for(i=0; i<results.size(); i++){
					marca = results.get(i);
					//marcaJSONObject.put("codigo", marca.getId());
					marcaJSONObject.put("id", marca.getId());
					marcaJSONObject.put("text", marca.getNombre());
					marcaJSONArray.add(marcaJSONObject);
				}
				result.put("listadoMarca", marcaJSONArray);				
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
