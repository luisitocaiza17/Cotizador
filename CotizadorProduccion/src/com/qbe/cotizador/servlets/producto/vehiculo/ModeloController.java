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
import com.qbe.cotizador.dao.producto.vehiculo.ClaseVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloGenericoDAO;
import com.qbe.cotizador.model.ClaseVehiculo;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ModeloGenerico;
import com.qbe.cotizador.transaction.producto.vehiculo.ModeloTransaction;

/**
 * Servlet implementation class Color
 */
@WebServlet("/ModeloController")
public class ModeloController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModeloController() {
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
			String codigoMarca= request.getParameter("marca") == null ? "" : request.getParameter("marca");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String indiceDanoParcial = request.getParameter("indiceDanoParcial") == null ? "" : request.getParameter("indiceDanoParcial");
			String tonelaje = request.getParameter("tonelaje") == null ? "" : request.getParameter("tonelaje");
			String modeloGenericoId = request.getParameter("modeloGenerico") == null ? "" : request.getParameter("modeloGenerico");
			String claseVehiculoId = request.getParameter("claseVehiculo") == null ? "" : request.getParameter("claseVehiculo");
			//String query = request.getParameter("query") == null ? "" : request.getParameter("query");
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			JSONObject modeloJSONObject = new JSONObject();
			JSONArray modeloJSONArray = new JSONArray();
			
			Modelo modelo = new Modelo();
			ModeloDAO modeloDAO = new ModeloDAO();
			
			//Marca marca = new Marca();
			MarcaDAO marcaDAO = new MarcaDAO();
			
			ModeloTransaction modeloTransaction = new ModeloTransaction();
			
			if(!codigoEnsurance.equals(""))
				modelo.setModEnsurance(codigoEnsurance);
			
			if(!codigo.equals(""))
				modelo.setId(codigo);;

			if(!codigoMarca.equals(""))
					modelo.setMarca(marcaDAO.buscarPorId(codigoMarca));
					
				
			if(!nombre.equals(""))
				modelo.setNombre(nombre);
			
			if(!indiceDanoParcial.equals(""))
				modelo.setIndiceDanoParcialFrecuencia(indiceDanoParcial);
			
			if(!tonelaje.equals(""))
				modelo.setTonelaje(new Double(tonelaje));
			
			if(!modeloGenericoId.equals("")){
				ModeloGenericoDAO mgDAO=new ModeloGenericoDAO();
				modelo.setModeloGenerico(mgDAO.buscarPorId(modeloGenericoId));
				}
			
			if(!claseVehiculoId.equals("")){
				ClaseVehiculoDAO cvDAO=new ClaseVehiculoDAO();
				modelo.setClaseVehiculo(cvDAO.buscarPorId(claseVehiculoId));
				}

			if (activa.equals("1"))
				modelo.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				modelo.setActivo(false);
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Modelo> results = modeloDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					modelo = results.get(i);
					modeloJSONObject.put("codigo", modelo.getId());
					modeloJSONObject.put("codigoEnsurance", modelo.getModEnsurance());
					modeloJSONObject.put("marca", modelo.getMarca().getNombre());
					modeloJSONObject.put("nombre", modelo.getNombre());
					
					if(modelo.getActivo())
						modeloJSONObject.put("activo", "Si");
					else
						modeloJSONObject.put("activo", "No");
					
					modeloJSONArray.add(modeloJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoModelo", modeloJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				modeloTransaction.crear(modelo);

			if(tipoConsulta.equals("actualizar"))
				modeloTransaction.editar(modelo);

			if(tipoConsulta.equals("eliminar"))
				modeloTransaction.eliminar(modelo);			

			if(tipoConsulta.equals("autocomplete")){
				List<Modelo> results = modeloDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					modelo = results.get(i);
					//modeloJSONObject.put("codigo", modelo.getId());
					modeloJSONObject.put("codigo", modelo.getId());
					modeloJSONObject.put("nombre", modelo.getNombre());
					modeloJSONArray.add(modeloJSONObject);
				}
				result.put("listadoModelo", modeloJSONArray);				
			}
			
			if(tipoConsulta.equals("cargaPorMarcaSelect2")){
				List<Modelo> results = modeloDAO.buscarPorMarca(codigoMarca) ;
				int i=0;
				modeloJSONObject.put("id", "-1");
				modeloJSONObject.put("text", "Escoja una marca");
				if(results!=null)
				for(i=0; i<results.size(); i++){
					modelo = results.get(i);
					// Validacion cuando es modelos publicos (pesados y livianos)
					/*
					if(claseVehiculo.equalsIgnoreCase("PUBLICOS")){
						if(modelo.getClaseVehiculo().getNombre().equals("LIVIANOS")|| modelo.getClaseVehiculo().getNombre().equals("PESADOS")){
							modeloJSONObject.put("id", modelo.getId());
							modeloJSONObject.put("text", modelo.getNombre());
							modeloJSONArray.add(modeloJSONObject);
						}
					}else{
					*/
						//if(modelo.getClaseVehiculo().getNombre().equals(claseVehiculo)){
							modeloJSONObject.put("id", modelo.getId());
							modeloJSONObject.put("text", modelo.getNombre());
							modeloJSONArray.add(modeloJSONObject);
						//}
					//}
				}
				result.put("listadoModelo", modeloJSONArray);				
			}
			
			if(tipoConsulta.equals("cargaTodosPorMarcaSelect2")){
				List<Modelo> results = modeloDAO.buscarPorMarca(codigoMarca) ;
				int i=0;
				modeloJSONObject.put("id", "-1");
				modeloJSONObject.put("text", "Escoja una marca");
				if(results!=null)
				for(i=0; i<results.size(); i++){
					modelo = results.get(i);
							modeloJSONObject.put("id", modelo.getId());
							modeloJSONObject.put("text", modelo.getNombre());
							modeloJSONArray.add(modeloJSONObject);
					
				}
				result.put("listadoModelo", modeloJSONArray);				
			}
			
			
			if(tipoConsulta.equals("cargaTodosPorMarca")){
				List<Modelo> results = modeloDAO.buscarPorMarca(codigoMarca) ;
				int i=0;
				if(results!=null)
				for(i=0; i<results.size(); i++){
						modelo = results.get(i);
						modeloJSONObject.put("codigo", modelo.getId());
						modeloJSONObject.put("codigoEnsurance",modelo.getModEnsurance());
						modeloJSONObject.put("marca", modelo.getMarca().getNombre());
						modeloJSONObject.put("nombre", modelo.getNombre());
						modeloJSONObject.put("indiceDanoParcialFrecuencia", modelo.getIndiceDanoParcialFrecuencia());
						modeloJSONObject.put("modeloGenerico", modelo.getModeloGenerico().getNombre());
						modeloJSONObject.put("tonelaje", modelo.getTonelaje());
						modeloJSONObject.put("claseVehiculo", modelo.getClaseVehiculo().getNombre());

						if (modelo.getActivo())
							modeloJSONObject.put("activo", "Si");
						else
							modeloJSONObject.put("activo", "No");

						modeloJSONArray.add(modeloJSONObject);
					
				}
				result.put("listadoModelo", modeloJSONArray);				
			}
			
			if(tipoConsulta.equals("cargaMantenimientoModelo")){
				ModeloGenericoDAO modeloGenericoDAO=new ModeloGenericoDAO();
				ModeloGenerico modeloGenerico=new ModeloGenerico(); 
				List<ModeloGenerico> results = modeloGenericoDAO.buscarActivos();
				JSONObject modeloGenericoJSONObject=new JSONObject();
				JSONArray modeloGenericoJSONArray=new JSONArray();
				
				int i=0;
				if(results!=null)
				for(i=0; i<results.size(); i++){
					modeloGenericoJSONObject=new JSONObject();
					modeloGenerico = results.get(i);
					modeloGenericoJSONObject.put("codigo", modeloGenerico.getId());
					modeloGenericoJSONObject.put("nombre", modeloGenerico.getNombre());

						if (modeloGenerico.getActivo())
							modeloGenericoJSONObject.put("activo", "Si");
						else
							modeloGenericoJSONObject.put("activo", "No");

						modeloGenericoJSONArray.add(modeloGenericoJSONObject);
					
				}
				result.put("listadoModeloGenerico", modeloGenericoJSONArray);		
				
				ClaseVehiculoDAO claseVehiculoDAO=new ClaseVehiculoDAO();
				ClaseVehiculo claseVehiculo=new ClaseVehiculo(); 
				List<ClaseVehiculo> resultsCV = claseVehiculoDAO.buscarActivos();
				JSONObject claseVehiculoJSONObject=new JSONObject();
				JSONArray claseVehiculoJSONArray=new JSONArray();
				
				if(resultsCV!=null)
				for(i=0; i<resultsCV.size(); i++){
					claseVehiculoJSONObject=new JSONObject();
					claseVehiculo = resultsCV.get(i);
					claseVehiculoJSONObject.put("codigo", claseVehiculo.getId());
					claseVehiculoJSONObject.put("nombre", claseVehiculo.getNombre());

						if (claseVehiculo.getActivo())
							claseVehiculoJSONObject.put("activo", "Si");
						else
							claseVehiculoJSONObject.put("activo", "No");

						claseVehiculoJSONArray.add(claseVehiculoJSONObject);
					
				}
				result.put("listadoClaseVehiculo", claseVehiculoJSONArray);		
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
