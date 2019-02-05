package com.qbe.cotizador.servlets.producto.vehiculocerrado;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TipoGrupoDAO;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.TipoGrupo;
import com.qbe.cotizador.transaction.producto.GrupoProductoTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GrupoProductoController
 */
@WebServlet("/GrupoProductoController")
public class GrupoProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrupoProductoController() {
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
		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject grupoJSONObject = new JSONObject();
			JSONArray grupoJSONArray = new JSONArray();
			
			GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
			GrupoProducto grupoProducto = new GrupoProducto();
			
			GrupoProductoTransaction grupoProductoTransaction = new GrupoProductoTransaction();
			
			if (!codigo.equals(""))
				grupoProducto.setId(codigo);

			if (!nombre.equals(""))
				grupoProducto.setNombre(nombre);
			
			if (!descripcion.equals(""))
				grupoProducto.setDescripcion(descripcion);
			
			if (activo.equals("1")) {
				grupoProducto.setActivo(true);
			}else if(activo.equals("0")) {
				grupoProducto.setActivo(false);
			}
			
			 if(tipoConsulta.equals("encontrarTodos")){ 
               List<GrupoProducto> results = grupoProductoDAO.buscarTodos();
               int i=0;
               for(i=0; i<results.size(); i++){
                   grupoProducto = results.get(i);
                   grupoJSONObject.put("codigo", grupoProducto.getId());
                   grupoJSONObject.put("nombre", grupoProducto.getNombre());
                   grupoJSONObject.put("descripcion", grupoProducto.getDescripcion() == null ? "" : grupoProducto.getDescripcion());
                   if(grupoProducto.getActivo()){
                       grupoJSONObject.put("activo", "Si");
                   }else{
                       grupoJSONObject.put("activo", "No");
                   }
                   grupoJSONArray.add(grupoJSONObject);
               }               
               result.put("numRegistros",i);
               result.put("listadoGrupoProducto", grupoJSONArray);
           }
			 
		    
		    if(tipoConsulta.equals("crear")){
		    	grupoProductoTransaction.crear(grupoProducto);
		    }

			if(tipoConsulta.equals("actualizar")){
				grupoProductoTransaction.editar(grupoProducto);
			}
			
			if(tipoConsulta.equals("eliminar")){
				grupoProductoTransaction.eliminar(grupoProducto);
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
