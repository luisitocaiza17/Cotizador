package com.qbe.cotizador.servlets.entidad;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.PymeExtRamo;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.transaction.entidad.ActividadEconomicaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class actividadEconomica
 */
@WebServlet("/ActividadEconomicaController")
public class ActividadEconomicaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActividadEconomicaController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String grupoProductoId = request.getParameter("grupoProductoId") == null ? "" : request.getParameter("grupoProductoId").trim();
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance").trim();
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre").trim();
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			JSONObject actividadEconomicaJSONObject = new JSONObject();
			JSONArray actividadesEconomicasJSONArray = new JSONArray();
			JSONObject grupoProductoJSONObject = new JSONObject();
			JSONArray grupoProductoJSONObjectJSONArray = new JSONArray();
			ActividadEconomicaDAO actividadEconomicaDAO=new ActividadEconomicaDAO();
			ActividadEconomica actividadEconomica=new ActividadEconomica();
			ActividadEconomicaTransaction actividadEconomicaTransaccion =new ActividadEconomicaTransaction();
			GrupoPorProducto grupoProducto = new GrupoPorProducto();
			GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
			if(!codigo.equals(""))
				actividadEconomica.setId(codigo);	
			if(!grupoProductoId.equals(""))
				actividadEconomica.setGrupoProductoId(new BigInteger(grupoProductoId));	
			if(!codigoEnsurance.equals(""))
				actividadEconomica.setActEnsurance(codigoEnsurance);
			if(!nombre.equals(""))
				actividadEconomica.setNombre(nombre);
			
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<ActividadEconomica> results = actividadEconomicaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					actividadEconomica = results.get(i);
					actividadEconomicaJSONObject.put("codigo", actividadEconomica.getId());
					actividadEconomicaJSONObject.put("nombre", actividadEconomica.getNombre());
					actividadEconomicaJSONObject.put("codigoEnsurance", actividadEconomica.getActEnsurance());
					if(actividadEconomica.getGrupoProductoId() != null){
						grupoProducto = grupoPorProductoDAO.buscarPorId(actividadEconomica.getGrupoProductoId().toString());
						actividadEconomicaJSONObject.put("nombreGrupo",grupoProducto.getNombreComercialProducto());
					}else{
						actividadEconomicaJSONObject.put("nombreGrupo","NO TIENE");	
					}
					actividadesEconomicasJSONArray.add(actividadEconomicaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoActividadesEconomicas", actividadesEconomicasJSONArray);
				List<GrupoPorProducto> resultR = grupoPorProductoDAO.buscarTodos();
				for(GrupoPorProducto r: resultR){
					grupoProductoJSONObject.put("grupoProductoId", r.getId());
					grupoProductoJSONObject.put("nombreGrupoCobertura", r.getNombreComercialProducto());
					grupoProductoJSONObjectJSONArray.add(grupoProductoJSONObject);
				}
				result.put("grupoProductoArr", grupoProductoJSONObjectJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorGrupoProductoId")){ 
				GrupoPorProductoDAO grupoPorProductoDAO1=new GrupoPorProductoDAO();
				GrupoPorProducto grupoProducto1 =grupoPorProductoDAO1.buscarPorId(grupoProductoId);
				List<ActividadEconomica> results=null;
				
				if(grupoProducto1.getNombreComercialProducto().equals("COPROPIEDADES"))
					results = actividadEconomicaDAO.buscarTodos();
				else
					results = actividadEconomicaDAO.buscarPorGrupoProductoId(new BigInteger(grupoProductoId));
				int i=0;
				for(i=0; i<results.size(); i++){
					actividadEconomica = results.get(i);
					actividadEconomicaJSONObject.put("codigo", actividadEconomica.getId());
					actividadEconomicaJSONObject.put("nombre", actividadEconomica.getNombre());
					actividadesEconomicasJSONArray.add(actividadEconomicaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoActividadesEconomicas", actividadesEconomicasJSONArray);
			}
			
			if(tipoConsulta.equals("cargarSelect2")){ 
				List<ActividadEconomica> results = actividadEconomicaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					actividadEconomica = results.get(i);
					actividadEconomicaJSONObject.put("id", actividadEconomica.getId());
					actividadEconomicaJSONObject.put("text", actividadEconomica.getNombre());
					actividadesEconomicasJSONArray.add(actividadEconomicaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoActividadesEconomicas", actividadesEconomicasJSONArray);
			}
			if(tipoConsulta.equals("obtenerPorId")){
				ActividadEconomica results = actividadEconomicaDAO.buscarPorId(codigo);	
				result.put("grupoProductoId", results.getGrupoProductoId());
				result.put("codigo", results.getId());
				result.put("nombre", results.getNombre());
				result.put("codigoEnsurance", results.getActEnsurance());				
			}
			if(tipoConsulta.equals("crear")){
				ActividadEconomica actividadEconomicaX = actividadEconomicaDAO.buscarPorId(actividadEconomica.getId());
				if(actividadEconomicaX != null)
					actividadEconomicaTransaccion.editar(actividadEconomica);					
				else
					actividadEconomicaTransaccion.crear(actividadEconomica);
					
					
			}
			if(tipoConsulta.equals("eliminar")){
				actividadEconomicaTransaccion.eliminar(actividadEconomica);

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
