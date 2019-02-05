package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaVtaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriReglaVta;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Provincia;

/**
 * Servlet implementation class AgriReglaController
 */
@WebServlet("/AgriReglaVtaController")
public class AgriReglaVtaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriReglaVtaController() {
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
		JSONObject result = new JSONObject ();
		try 
		{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String reglaId = request.getParameter("reglaId")==null?"":request.getParameter("reglaId");
			String tipoCultivoId = request.getParameter("tipoCultivoId")==null?"":request.getParameter("tipoCultivoId");
			String cicloId = request.getParameter("cicloId")==null?"":request.getParameter("cicloId");
			String tipoCalculoId = request.getParameter("tipoCalculoId")==null?"":request.getParameter("tipoCalculoId");
			String provinciaId = request.getParameter("provinciaId")==null?"":request.getParameter("provinciaId");
			String cantonId = request.getParameter("cantonId")==null?"":request.getParameter("cantonId");
			String costoProduccion = request.getParameter("costoProduccion")==null?"":request.getParameter("costoProduccion");
			String tasa = request.getParameter("tasa")==null?"":request.getParameter("tasa");
			String costoMantenimiento = request.getParameter("costoMantenimiento")==null?"":request.getParameter("costoMantenimiento");
			String aceptabilidadDesde = request.getParameter("aceptabilidadDesde")==null?"":request.getParameter("aceptabilidadDesde");
			String aceptabilidadHasta = request.getParameter("aceptabilidadHasta")==null?"":request.getParameter("aceptabilidadHasta");
			String observaciones = request.getParameter("observaciones")==null?"":request.getParameter("observaciones");
			
			if (tipoConsulta.equals("cargarCombos"))
			{	
				JSONObject cultivoJSONObject = new JSONObject();			
				JSONArray cultivoJSONArray = new JSONArray();
				
				AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
				List<AgriTipoCultivo> tipoCultivoList = tipoCultivoDAO.BuscarTodos();
				for(AgriTipoCultivo r : tipoCultivoList) {
					cultivoJSONObject.put("id", r.getTipoCultivoId());
					cultivoJSONObject.put("cultivoNombre", r.getNombre());					
					cultivoJSONArray.add(cultivoJSONObject);					
				}
				result.put("cultivoArr", cultivoJSONArray);
				
				JSONObject calculoJSONObject = new JSONObject();			
				JSONArray calculoJSONArray = new JSONArray();
				
				AgriTipoCalculoDAO tipoCalculoDAO = new AgriTipoCalculoDAO();
				List<AgriTipoCalculo> tipoCalculoList = tipoCalculoDAO.BuscarTodos();
				for(AgriTipoCalculo r : tipoCalculoList) {
					calculoJSONObject.put("id", r.getTipoCalculoId());
					calculoJSONObject.put("calculoNombre", r.getNombre());					
					calculoJSONArray.add(calculoJSONObject);					
				}
				result.put("calculoArr", calculoJSONArray);
				
				
				JSONObject provinciaJSONObject = new JSONObject();			
				JSONArray provinciaJSONArray = new JSONArray();
				
				ProvinciaDAO provinciaDAO = new ProvinciaDAO();
				List<Provincia> provinciaList = provinciaDAO.buscarTodos();
				for(Provincia r : provinciaList) {
					provinciaJSONObject.put("id", r.getId());
					provinciaJSONObject.put("provinciaNombre", r.getNombre());					
					provinciaJSONArray.add(calculoJSONObject);					
				}
				result.put("provinciaArr", provinciaJSONArray);
			}
			
			
			
			if (tipoConsulta.equals("encontrarPorParametros"))
			{								
				result.put("ReglaJSONArray", buscarPorParametros(reglaId,tipoCultivoId,tipoCalculoId,provinciaId,cantonId));
			}			
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}	
		
		catch(Exception e)
		{
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}
	
	public JSONArray buscarPorParametros(String reglaId, String tipoCultivoId,String tipoCalculoId,String provinciaId,String cantonId) {
		JSONObject retornoJSONObject = new JSONObject();
		JSONArray retornoJSONArray = new JSONArray();
		
		AgriReglaVtaDAO agriReglaVtaDAO = new AgriReglaVtaDAO();
		
		List<AgriReglaVta> agriReglaVtaList = agriReglaVtaDAO.BuscarPorParametros(reglaId,tipoCultivoId,tipoCalculoId,provinciaId,cantonId);
		for(AgriReglaVta r : agriReglaVtaList) {
			retornoJSONObject.put("reglaId",r.getReglaId().toString());
			retornoJSONObject.put("cultivoId",r.getTipoCultivoId());
			retornoJSONObject.put("cultivoNombre",r.getCultivoNombre());
			retornoJSONObject.put("cicloId",r.getCicloId());
			retornoJSONObject.put("cicloNombre",r.getCicloNombre());
			retornoJSONObject.put("calculoId",r.getTipoCalculoId());
			retornoJSONObject.put("calculoNombre",r.getNombreCalculo());
			retornoJSONObject.put("proviciaId",r.getProvinciaId());
			retornoJSONObject.put("proviciaNombre",r.getProvinciaNombre());
			retornoJSONObject.put("cantonId",r.getCantonId());
			retornoJSONObject.put("cantonNombre",r.getCantonNombre());
			retornoJSONObject.put("costoProduccion",r.getCostoProduccion());
			retornoJSONObject.put("tasa",r.getTasa());
			retornoJSONObject.put("costoMantenimiento",r.getCostoMantenimiento());
			retornoJSONObject.put("aceptabilidadDesde",r.getAceptabilidadDesde());
			retornoJSONObject.put("aceptabilidadHasta",r.getAceptabilidadHasta());
			retornoJSONObject.put("observaciones",r.getObservaciones());
			if(r.getEstado() == 1)
				retornoJSONObject.put("estado","Activo");
			else
				retornoJSONObject.put("estado","Inactivo");				
			retornoJSONArray.add(retornoJSONObject);
		}
		
		return retornoJSONArray;
	}	

}
