package com.qbe.cotizador.servlets.producto.ganadero;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.ganadero.DeducibleGanaderoDAO;
import com.qbe.cotizador.model.DeducibleGanadero;
import com.qbe.cotizador.transaction.producto.ganadero.DeducibleGanaderoTransaction;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeducibleGanaderoController
 */
@WebServlet("/DeducibleGanaderoController")
public class DeducibleGanaderoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeducibleGanaderoController() {
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
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String categoria = request.getParameter("categoria") == null ? "" : request.getParameter("categoria");
			String deducible = request.getParameter("deducible") == null ? "" : request.getParameter("deducible");
			String numeroSiniestro = request.getParameter("numeroSiniestro") == null ? "" : request.getParameter("numeroSiniestro");
			String rangoFinal = request.getParameter("rangoFinal") == null ? "" : request.getParameter("rangoFinal");
			String rangoInicial = request.getParameter("rangoInicial") == null ? "" : request.getParameter("rangoInicial");
			String tipoProduccion = request.getParameter("tipoProduccion") == null ? "" : request.getParameter("tipoProduccion");
			String valorPrima = request.getParameter("valorPrima") == null ? "" : request.getParameter("valorPrima");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject deducibleGanaderoJSONObject = new JSONObject();
			JSONArray deducibleGanaderoJSONArray = new JSONArray();
			
			DeducibleGanadero deducibleGanadero = new DeducibleGanadero();
			DeducibleGanaderoDAO deducibleGanaderoDAO = new DeducibleGanaderoDAO();
			
			DeducibleGanaderoTransaction deducibleGanaderoTransaction = new DeducibleGanaderoTransaction();
			
			if(!codigo.equals(""))
				deducibleGanadero.setId(codigo);
			
			if(!categoria.equals(""))
				deducibleGanadero.setCategoria(categoria);
			
			if(!deducible.equals(""))
				deducibleGanadero.setDeducible(Integer.parseInt(deducible));
			
			if(!numeroSiniestro.equals(""))
				deducibleGanadero.setNumeroSiniestro(numeroSiniestro);
			
			if(!rangoFinal.equals(""))
				deducibleGanadero.setRangoFinal(Integer.parseInt(rangoFinal));
			
			if(!rangoInicial.equals(""))
				deducibleGanadero.setRangoInicial(Integer.parseInt(rangoInicial));
			
			if(!tipoProduccion.equals(""))
				deducibleGanadero.setTipoProduccion(tipoProduccion);
			
			if(!valorPrima.equals(""))
				deducibleGanadero.setValorPrima(Double.parseDouble(valorPrima));
			
			
			if(tipoConsulta.equals("obtenerPorID")){
				deducibleGanadero = deducibleGanaderoDAO.buscarPorId(codigo);
				result.put("codigo", deducibleGanadero.getId());
				result.put("categoria", deducibleGanadero.getCategoria());
				result.put("deducible", deducibleGanadero.getDeducible());
				result.put("numeroSiniestro", deducibleGanadero.getNumeroSiniestro());
				result.put("rangoFinal", deducibleGanadero.getRangoFinal());
				result.put("rangoInicial", deducibleGanadero.getRangoInicial());
				result.put("tipoProduccion", deducibleGanadero.getTipoProduccion());
				result.put("valorPrima", deducibleGanadero.getValorPrima());
			}
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<DeducibleGanadero> results = deducibleGanaderoDAO.buscarTodos();
				int i = 0;
				for(i=0; i< results.size(); i++){
					deducibleGanadero = results.get(i);
					deducibleGanaderoJSONObject.put("codigo", deducibleGanadero.getId());
					deducibleGanaderoJSONObject.put("categoria", deducibleGanadero.getCategoria());
					deducibleGanaderoJSONObject.put("deducible", deducibleGanadero.getDeducible());
					deducibleGanaderoJSONObject.put("numeroSiniestro", deducibleGanadero.getNumeroSiniestro());
					deducibleGanaderoJSONObject.put("rangoFinal", deducibleGanadero.getRangoFinal());
					deducibleGanaderoJSONObject.put("rangoInicial", deducibleGanadero.getRangoInicial());
					deducibleGanaderoJSONObject.put("tipoProduccion", deducibleGanadero.getTipoProduccion());
					deducibleGanaderoJSONObject.put("valorPrima", deducibleGanadero.getValorPrima());
					
					deducibleGanaderoJSONArray.add(deducibleGanaderoJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoDeducibleGanadero", deducibleGanaderoJSONArray);				
			}
			
			if(tipoConsulta.equals("crear"))
			{				
				deducibleGanaderoTransaction.crear(deducibleGanadero);
			}
			
			if(tipoConsulta.equals("actualizar"))
				deducibleGanaderoTransaction.editar(deducibleGanadero);
			
			if(tipoConsulta.equals("eliminar"))
				deducibleGanaderoTransaction.eliminar(deducibleGanadero);
				
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			
			//DeducibleGanadero 
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();

		}
	}

}
