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

import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.model.GrupoCobertura;

/**
 * Servlet implementation class GrupoCoberturaController
 */
@WebServlet("/GrupoCoberturaController")
public class GrupoCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrupoCoberturaController() {
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

			if(tipoConsulta.equals("encontrarTodosSelect2")){
								
				JSONObject grupoCoberturaJSONObject = new JSONObject();
				JSONArray grupoCoberturaJSONArray = new JSONArray();
				
				GrupoCoberturaDAO grupoCoberturaDAO = new GrupoCoberturaDAO();
				List<GrupoCobertura> gruposCobertura= grupoCoberturaDAO.buscarTodos();
				
				for(int j=0;j<gruposCobertura.size();j++){
					GrupoCobertura grupoCobertura=gruposCobertura.get(j);
					grupoCoberturaJSONObject.put("id", grupoCobertura.getId() );
					grupoCoberturaJSONObject.put("text", grupoCobertura.getNombre() );
					grupoCoberturaJSONArray.add(grupoCoberturaJSONObject);
				}
				
				result.put("listadoGrupoCobertura", grupoCoberturaJSONArray);
				
				
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
