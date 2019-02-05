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

import com.qbe.cotizador.dao.cotizacion.MotivoDescuentoDAO;
import com.qbe.cotizador.model.MotivoDescuento;
import com.qbe.cotizador.transaction.cotizacion.MotivoDescuentoTransaction;

/**
 * Servlet implementation class MotivoDescuentoController
 */
@WebServlet("/MotivoDescuentoController")
public class MotivoDescuentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotivoDescuentoController() {
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
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String activo = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject motivoDescuentoJSONObject = new JSONObject();
			JSONArray motivoDescuentoJSONArray = new JSONArray();

			MotivoDescuento motivoDescuento = new MotivoDescuento();
			MotivoDescuentoDAO motivoDescuentoDAO = new MotivoDescuentoDAO();
			
			MotivoDescuentoTransaction motivoDescuentoTransaction = new MotivoDescuentoTransaction();

			if (!codigo.equals(""))
				motivoDescuento.setId(codigo);

			if (!nombre.equals(""))
				motivoDescuento.setNombre(nombre);

			if (activo.equals("1"))
				motivoDescuento.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				motivoDescuento.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<MotivoDescuento> results = motivoDescuentoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					motivoDescuento = results.get(i);
					motivoDescuentoJSONObject.put("codigo", motivoDescuento.getId());
					motivoDescuentoJSONObject.put("nombre", motivoDescuento.getNombre());
					if(motivoDescuento.getActivo())
						motivoDescuentoJSONObject.put("activo", "Si");
					else
						motivoDescuentoJSONObject.put("activo", "No");

					motivoDescuentoJSONArray.add(motivoDescuentoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoMotivoDescuento", motivoDescuentoJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarTodosActivos")){ 
				List<MotivoDescuento> results = motivoDescuentoDAO.buscarActivos();
				int i=0;
				for(i=0; i<results.size(); i++){
					motivoDescuento = results.get(i);
					motivoDescuentoJSONObject.put("codigo", motivoDescuento.getId());
					motivoDescuentoJSONObject.put("nombre", motivoDescuento.getNombre());
					if(motivoDescuento.getActivo())
						motivoDescuentoJSONObject.put("activo", "Si");
					else
						motivoDescuentoJSONObject.put("activo", "No");

					motivoDescuentoJSONArray.add(motivoDescuentoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoMotivoDescuento", motivoDescuentoJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				motivoDescuentoTransaction.crear(motivoDescuento);

			if(tipoConsulta.equals("actualizar"))
				motivoDescuentoTransaction.editar(motivoDescuento);

			if(tipoConsulta.equals("eliminar"))
				motivoDescuentoTransaction.eliminar(motivoDescuento);


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
