package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.transaction.producto.agricola.AgriCanalTransaction;

/**
 * Servlet implementation class AgriCanalController
 */
@WebServlet("/AgriCanalController")
public class AgriCanalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCanalController() {
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
			String canalId = request.getParameter("canalId") == null ? "" : request.getParameter("canalId");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");		
			
			AgriCanal agriCanal=new AgriCanal();
			AgriCanalDAO agriCanalDAO=new AgriCanalDAO();
			AgriCanalTransaction agriCanalTransaction=new AgriCanalTransaction();
			JSONObject canalJSONObjetc = new JSONObject();
			JSONArray canalesJSONArray = new JSONArray();
			
			if(canalId.length() > 0)
				agriCanal.setCanalId(new BigInteger(canalId));
			
			if(nombre.length() > 0)
				agriCanal.setNombre(nombre);			
			
			if (tipoConsulta.equals("encontrarTodos"))
			{
				List<AgriCanal> results = agriCanalDAO.BuscarTodos();
				for (AgriCanal canal: results)
				{
					canalJSONObjetc.put("id", canal.getCanalId());
					canalJSONObjetc.put("nombre", canal.getNombre());
					canalesJSONArray.add(canalJSONObjetc);
				}
				result.put("canalesJSONArray", canalesJSONArray);
			}
			
			if (tipoConsulta.equals("encontrarPorCanalId"))
			{
				List<AgriCanal> results = agriCanalDAO.BuscarTodos();
				for (AgriCanal canal: results)
				{
					if(canalId.equals("0")){
						canalJSONObjetc.put("id", canal.getCanalId());
						canalJSONObjetc.put("nombre", canal.getNombre());
						canalesJSONArray.add(canalJSONObjetc);
					}
					else if(canalId.equals(canal.getCanalId().toString())){
						canalJSONObjetc.put("id", canal.getCanalId());
						canalJSONObjetc.put("nombre", canal.getNombre());
						canalesJSONArray.add(canalJSONObjetc);
					}
				}
				result.put("canalesJSONArray", canalesJSONArray);
			}
			
			if (tipoConsulta.equals("encontrarPorNombre")){
				List<AgriCanal> results = agriCanalDAO.BuscarTodos();
				for(AgriCanal rs:results){
					canalJSONObjetc.put("id", rs.getCanalId());
					canalJSONObjetc.put("nombre", rs.getNombre());
					canalesJSONArray.add(canalJSONObjetc);
				}
				result.put("canalesJSONArray", canalesJSONArray);
			}			
			if (tipoConsulta.equals("encontrarPorId")){
				AgriCanal results = agriCanalDAO.BuscarPorId(new BigInteger(canalId));
				result.put("id", results.getCanalId());
				result.put("nombre", results.getNombre());	
				
			}
			if (tipoConsulta.equals("crear")){
				agriCanalTransaction.crear(agriCanal);
			}
			if (tipoConsulta.equals("editar")){
				agriCanalTransaction.editar(agriCanal);
			}
			if (tipoConsulta.equals("eliminar")){
				agriCanalTransaction.eliminar(agriCanal);
			}			
			
			/*if (tipoConsulta.equals("encontrarPronaca")){
				AgriCanal results = agriCanalDAO.buscarPorNombre("PRONACA");
				result.put("id", results.getCanalId());
				result.put("nombre", results.getNombre());
			}
			
			if (tipoConsulta.equals("encontrarSucre")){				
				AgriCanal results = agriCanalDAO.buscarPorNombre("SUCRE");	
				canalJSONObjetc.put("id", results.getCanalId());
				canalJSONObjetc.put("nombre", results.getNombre());
				canalesJSONArray.add(canalJSONObjetc);
				result.put("canalesJSONArray", canalesJSONArray);
			}*/
			
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
