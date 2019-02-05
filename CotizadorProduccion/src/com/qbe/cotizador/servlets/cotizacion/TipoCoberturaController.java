package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.TipoCoberturaDAO;
import com.qbe.cotizador.model.TipoCobertura;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TipoCoberturaController
 */
@WebServlet("/TipoCoberturaController")
public class TipoCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoCoberturaController() {
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
			
			TipoCobertura tipoCobertura = new TipoCobertura();
			TipoCoberturaDAO tipoCoberturaDAO  = new TipoCoberturaDAO();
			
			JSONObject tipoCoberturaJSONObject = new JSONObject();
			JSONArray tipoCoberturaJSONArray = new JSONArray();
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoCobertura> results = tipoCoberturaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoCobertura = results.get(i);
					tipoCoberturaJSONObject.put("id", tipoCobertura.getId());
					tipoCoberturaJSONObject.put("nombre", tipoCobertura.getNombre());
					tipoCoberturaJSONArray.add(tipoCoberturaJSONObject);
				}			
				result.put("listadoTipoCobertura", tipoCoberturaJSONArray);
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