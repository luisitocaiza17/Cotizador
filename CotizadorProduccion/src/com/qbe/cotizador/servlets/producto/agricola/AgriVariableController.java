package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreAuditoriaDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.seguridad.VariableSistemaTransaction;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriVariableController
 */
@WebServlet("/AgriVariableController")
public class AgriVariableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriVariableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
		
		
		
		if(tipoConsulta.equalsIgnoreCase("editar")){
			String id = request.getParameter("variableId") == null ? "": request.getParameter("variableId");
			String nombreV = request.getParameter("nombreV") == null ? "": request.getParameter("nombreV");
			String valor = request.getParameter("valor") == null ? "": request.getParameter("valor");
			
			VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
			VariableSistema variableSistemaOriginal= variableSistemaDAO.buscarPorId2(Integer.parseInt(id));
			
			
			VariableSistema variableSistema = new VariableSistema();
			variableSistema.setId(Integer.parseInt(id));
			variableSistema.setNombre(nombreV);
			variableSistema.setValor(valor);
			variableSistema.setActivo(variableSistemaOriginal.getActivo());
			variableSistema.setTipoVariable(variableSistemaOriginal.getTipoVariable());
			variableSistema.setModulo(variableSistemaOriginal.getModulo());
			VariableSistemaTransaction  variableProceso = new VariableSistemaTransaction();
			
			variableProceso.editar(variableSistema);
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());	
		}
		
		if(tipoConsulta.equalsIgnoreCase("obtenerPorId")){
			String id = request.getParameter("id") == null ? "": request.getParameter("id");
			
			VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
			VariableSistema variableSistema= variableSistemaDAO.buscarPorId2(Integer.parseInt(id));
			
			result.put("id", variableSistema.getId());
			result.put("nombre", variableSistema.getNombre());
			result.put("valor", variableSistema.getValor());
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());		
			
		}
		
		if(tipoConsulta.equalsIgnoreCase("encontrarPorParametros")){
			String nombre = request.getParameter("nombre") == null ? "": request.getParameter("nombre").trim();
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

			VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
			List<VariableSistema> data=variableSistemaDAO.buscarVariableK(nombre, skip, take);
			long total=variableSistemaDAO.buscarVarNum(nombre, skip, take);
			
			List<AgriVariable> agriVariable = new ArrayList<AgriVariable>();
			
			for(VariableSistema rs:data){
				AgriVariable nueva= new AgriVariable();
				nueva.setId(rs.getId());
				nueva.setNombre(rs.getNombre());
				nueva.setTipo(rs.getTipoVariable().getNombre());
				nueva.setValor(rs.getValor());
				agriVariable.add(nueva);
			}
			
			
			
			
			
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(agriVariable);
			
			Gson gson = new Gson();
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;
			
			
		}
		
	}

}
