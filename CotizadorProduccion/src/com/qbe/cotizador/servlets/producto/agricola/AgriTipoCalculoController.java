package com.qbe.cotizador.servlets.producto.agricola;

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

import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.transaction.producto.agricola.AgriTipoCalculoTransaction;

/**
 * Servlet implementation class AgriTipoCalculoController
 */
@WebServlet("/AgriTipoCalculoController")
public class AgriTipoCalculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriTipoCalculoController() {
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
			String TipoCalculoId = request.getParameter("TipoCalculoId")==null?"":request.getParameter("TipoCalculoId");
			String Nombre = request.getParameter("Nombre")==null?"":request.getParameter("Nombre");
			
			JSONObject TipoCalculoJSONObjetc = new JSONObject();
			JSONArray TipoCalculoJSONArray = new JSONArray();
			
			AgriTipoCalculo agriTipoCalculo = new AgriTipoCalculo();
			AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
			AgriTipoCalculoTransaction agriTipoCalculoTransaction = new AgriTipoCalculoTransaction();

			if (!TipoCalculoId.equals(""))
				agriTipoCalculo.setTipoCalculoId(new BigInteger(TipoCalculoId) );
			if (!Nombre.equals(""))
				agriTipoCalculo.setNombre(Nombre);
			
			if (tipoConsulta.equals("encontrarTodos"))
			{
				List<AgriTipoCalculo> results = agriTipoCalculoDAO.BuscarTodos();
				for (AgriTipoCalculo TipoCalculo: results)
				{
					TipoCalculoJSONObjetc.put("TipoCalculoId", TipoCalculo.getTipoCalculoId());
					TipoCalculoJSONObjetc.put("Nombre", TipoCalculo.getNombre());
					TipoCalculoJSONArray.add(TipoCalculoJSONObjetc);
				}
				result.put("TipoCalculoJSONArray", TipoCalculoJSONArray);
			}
			if (tipoConsulta.equals("obtenerPorId"))
			{
				AgriTipoCalculo results =agriTipoCalculoDAO.BuscarPorId(new BigInteger(TipoCalculoId));
				result.put("TipoCalculoId", results.getTipoCalculoId());
				result.put("Nombre", results.getNombre());
			}
			if (tipoConsulta.equals("crear"))
				agriTipoCalculoTransaction.crear(agriTipoCalculo);
			
			if (tipoConsulta.equals("editar"))
				agriTipoCalculoTransaction.editar(agriTipoCalculo);
			
			if (tipoConsulta.equals("eliminar"))
				agriTipoCalculoTransaction.eliminar(agriTipoCalculo);
			
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

}
