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

import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.model.TipoExtra;
import com.qbe.cotizador.transaction.producto.vehiculo.TipoExtraTransaction;

/**
 * Servlet implementation class TipoExtra
 */
@WebServlet("/TipoExtraController")
public class TipoExtraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoExtraController() {
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
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject tipoExtraJSONObject = new JSONObject();
			JSONArray tipoExtraJSONArray = new JSONArray();

			TipoExtra tipoExtra = new TipoExtra();
			TipoExtraDAO tipoExtraDAO = new TipoExtraDAO();

			TipoExtraTransaction tipoExtraTransaction = new TipoExtraTransaction();
			
			if (!codigo.equals(""))
				tipoExtra.setId(codigo);

			if (!codigoEnsurance.equals(""))
				tipoExtra.setTipExtEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				tipoExtra.setNombre(nombre);

			if (activa.equals("1"))
				tipoExtra.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				tipoExtra.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoExtra> results = tipoExtraDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoExtra = results.get(i);
					tipoExtraJSONObject.put("codigo", tipoExtra.getId());
					tipoExtraJSONObject.put("codigoEnsurance", tipoExtra.getTipExtEnsurance());
					tipoExtraJSONObject.put("nombre", tipoExtra.getNombre());

					if(tipoExtra.getActivo())
						tipoExtraJSONObject.put("activo", "Si");
					else
						tipoExtraJSONObject.put("activo", "No");

					tipoExtraJSONArray.add(tipoExtraJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoExtra", tipoExtraJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				tipoExtraTransaction.crear(tipoExtra);

			if(tipoConsulta.equals("actualizar"))
				tipoExtraTransaction.editar(tipoExtra);

			if(tipoConsulta.equals("eliminar"))
				tipoExtraTransaction.eliminar(tipoExtra);


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

	public static JSONArray getActivos(){
		TipoExtraDAO teDAO= new TipoExtraDAO();
		List<TipoExtra> tiposExtra=teDAO.buscarActivos();
		JSONArray extrasJSON=new JSONArray();
		for(TipoExtra tipoExtra:tiposExtra){
			extrasJSON.add(tipoExtra.getJSON());
		}
		return extrasJSON;
	}
	
}
