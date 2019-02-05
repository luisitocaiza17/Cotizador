package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.TipoPredioDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.TipoPredio;
import com.qbe.cotizador.transaction.entidad.TipoPredioTransaction;

/**
 * Servlet implementation class TipoIdentificacionController
 */
@WebServlet("/TipoPredioController")
public class TipoPredioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoPredioController() {
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
	JSONObject result= new JSONObject();
		try{
		String tipoConsulta= request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
		String actividadEconomicaId = request.getParameter("actividadEconomicaId") == null ? "" : request.getParameter("actividadEconomicaId").trim();
		String tipoprediogeneralid = request.getParameter("tipoprediogeneralid") == null ? "" : request.getParameter("tipoprediogeneralid").trim();
		String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre").trim();
		String nemotecnico = request.getParameter("nemotecnico") == null ? "" : request.getParameter("nemotecnico").trim();
		String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
		JSONArray jsonArray= new JSONArray();		
		JSONObject tipoIdentificacionJSON = new JSONObject();
		JSONArray jsonActividadArray= new JSONArray();		
		JSONObject resultActividad= new JSONObject();
		TipoPredio tipo= new TipoPredio();
		TipoPredioDAO tipoPredioDAO= new TipoPredioDAO();
		TipoPredioTransaction tipoPredioTransaction = new TipoPredioTransaction();
		ActividadEconomica actividadEconomica = new ActividadEconomica();
		ActividadEconomicaDAO actividadEconomicaDAO = new ActividadEconomicaDAO();
		
		if(!codigo.equals(""))
			tipo.setId(codigo);	
		if(!actividadEconomicaId.equals(""))
			tipo.setActividadEconomicaId(new BigInteger(actividadEconomicaId));	
		if(!nemotecnico.equals(""))
			tipo.setNemotecnico(nemotecnico);
		if(!nombre.equals(""))
			tipo.setNombre(nombre);
		if(!tipoprediogeneralid.equals(""))
			tipo.setTipoprediogeneralid(tipoprediogeneralid);
		if(tipoConsulta.equalsIgnoreCase("encontrarTodos"))
		{						
			List<TipoPredio> listado = tipoPredioDAO.buscarTodos();
			
			for(TipoPredio tipoPredio:listado) {				
				tipoIdentificacionJSON.put("codigo", tipoPredio.getId());
				tipoIdentificacionJSON.put("nombre", tipoPredio.getNombre());
				tipoIdentificacionJSON.put("nemotecnico", tipoPredio.getNemotecnico());
				tipoIdentificacionJSON.put("tipoprediogeneralid", tipoPredio.getTipoprediogeneralid());
				if(tipoPredio.getActividadEconomicaId() != null){
					actividadEconomica = actividadEconomicaDAO.buscarPorId(tipoPredio.getActividadEconomicaId().toString());
					tipoIdentificacionJSON.put("nombreActividadEconomica",actividadEconomica.getNombre());
				}else{
					tipoIdentificacionJSON.put("nombreActividadEconomica","NO TIENE");	
				}
				jsonArray.add(tipoIdentificacionJSON);
			}	
				
				result.put("tiposPredio", jsonArray);
				
				List<ActividadEconomica> resultR = actividadEconomicaDAO.buscarTodos();
				for(ActividadEconomica r: resultR){
					resultActividad.put("actividadId", r.getId());
					resultActividad.put("nombreActividad", r.getNombre());
					jsonActividadArray.add(resultActividad);
				}
				result.put("actividadArr", jsonActividadArray);
		}
		if(tipoConsulta.equals("encontrarPorActividad")){
			if(actividadEconomicaId.equals("")){
				actividadEconomicaId = "0";
			}
			List<TipoPredio> listado =  tipoPredioDAO.buscarPorActividad(new BigInteger(actividadEconomicaId));
			for(TipoPredio tipoPredio:listado) {
				tipoIdentificacionJSON.put("codigo", tipoPredio.getId());
				tipoIdentificacionJSON.put("nombre", tipoPredio.getNombre());
				jsonArray.add(tipoIdentificacionJSON);
			}
			result.put("tiposPredio", jsonArray);
		}
		if(tipoConsulta.equals("obtenerPorId")){
				TipoPredio results = tipoPredioDAO.buscarPorId(codigo);	
				result.put("actividadEconomicaId", results.getActividadEconomicaId());
				result.put("codigo", results.getId());
				result.put("nombre", results.getNombre());
				result.put("nemotecnico", results.getNemotecnico());
				result.put("tipoprediogeneralid", results.getTipoprediogeneralid());
		}
		if(tipoConsulta.equals("crear")){
			    TipoPredio tipoX = null;
				if(tipo.getId() != null){
				    tipoX = tipoPredioDAO.buscarPorId(tipo.getId().toString());
				}   
				if(tipoX != null)
					tipoPredioTransaction.editar(tipo);					
				else
					tipoPredioTransaction.crear(tipo);
					
					
		}
		if(tipoConsulta.equals("eliminar")){
				tipoPredioTransaction.eliminar(tipo);

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
