package com.qbe.cotizador.servlets.producto.vehiculo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.model.TipoVehiculo;
import com.qbe.cotizador.transaction.producto.vehiculo.TipoVehiculoTransaction;

/**
 * Servlet implementation class TipoVehiculoController
 */
@WebServlet("/TipoVehiculoController")
public class TipoVehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoVehiculoController() {
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
		// TODO Auto-generated method stub|
		JSONObject result = new JSONObject();
		try{
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject tipoVehiculoJSONObject = new JSONObject();
			JSONArray tipoVehiculoJSONArray = new JSONArray();

			TipoVehiculo tipoVehiculo = new TipoVehiculo();
			TipoVehiculoDAO tipoVehiculoDAO = new TipoVehiculoDAO();
			
			TipoVehiculoTransaction tipoVehiculoTransaction = new TipoVehiculoTransaction();

			if (!codigo.equals(""))
				tipoVehiculo.setId(codigo);

			if (!codigoEnsurance.equals(""))
				tipoVehiculo.setTipVhEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				tipoVehiculo.setNombre(nombre);

			if (activa.equals("1"))
				tipoVehiculo.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				tipoVehiculo.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoVehiculo> results = tipoVehiculoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}
			
			if(tipoConsulta.equals("VHDinamico")){ 
				List<String> ids = new ArrayList();
				ids.add("2");
				ids.add("3");
				ids.add("6");
				ids.add("5");
				ids.add("11");
				ids.add("12");
				ids.add("21");				
				List<TipoVehiculo> results = tipoVehiculoDAO.buscarPorIds(ids);
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}
			
			
			if(tipoConsulta.equals("Livianos")){ 
				
				List<TipoVehiculo> results = tipoVehiculoDAO.buscarPorGrupo("LIVIANOS");
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}
			
			if(tipoConsulta.equals("Motos")){ 
				
				List<TipoVehiculo> results = tipoVehiculoDAO.buscarPorGrupo("MOTO");
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}

			if(tipoConsulta.equals("Pesados")){ 
				List<TipoVehiculo> results = new ArrayList<TipoVehiculo>();
				results = tipoVehiculoDAO.buscarPorGrupo("PESADOS");
								
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}
			
			if(tipoConsulta.equals("Publicos")){ 
				List<String> valores = new ArrayList<String>();
				valores.add("LIVIANOS");
				valores.add("PESADOS");
				List<TipoVehiculo> results = tipoVehiculoDAO.buscarPorNombres(valores);
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoVehiculo = results.get(i);
					tipoVehiculoJSONObject.put("codigo", tipoVehiculo.getId());
					tipoVehiculoJSONObject.put("codigoEnsurance", tipoVehiculo.getTipVhEnsurance());
					tipoVehiculoJSONObject.put("nombre", tipoVehiculo.getNombre());

					if(tipoVehiculo.getActivo())
						tipoVehiculoJSONObject.put("activo", "Si");
					else
						tipoVehiculoJSONObject.put("activo", "No");

					tipoVehiculoJSONArray.add(tipoVehiculoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoVehiculo", tipoVehiculoJSONArray);
			}
			if(tipoConsulta.equals("crear"))
				tipoVehiculoTransaction.crear(tipoVehiculo);

			if(tipoConsulta.equals("actualizar"))
				tipoVehiculoTransaction.editar(tipoVehiculo);

			if(tipoConsulta.equals("eliminar"))
				tipoVehiculoTransaction.eliminar(tipoVehiculo);


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
