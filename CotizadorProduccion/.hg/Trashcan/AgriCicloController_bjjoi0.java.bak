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

import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.transaction.producto.agricola.AgriCicloTransaction;

/**
 * Servlet implementation class AgriCicloController
 */
@WebServlet("/AgriCicloController")
public class AgriCicloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCicloController() {
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
			String CicloId = request.getParameter("CicloId")==null?"":request.getParameter("CicloId");
			String Nombre = request.getParameter("Nombre")==null?"":request.getParameter("Nombre");
			String FechaInicio = request.getParameter("FechaInicio")==null?"":request.getParameter("FechaInicio");
			String FechaFin = request.getParameter("FechaFin")==null?"":request.getParameter("FechaFin");
			//String Estado = request.getParameter("Estado")==null?"":request.getParameter("Estado");
			JSONObject CicloJSONObjetc = new JSONObject();
			JSONArray CicloJSONArray = new JSONArray();
			
			AgriCiclo agriCiclo = new AgriCiclo();
			AgriCicloDAO agriCicloDAO = new AgriCicloDAO();
			AgriCicloTransaction agriCicloTransaction = new AgriCicloTransaction();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (!CicloId.equals(""))
				agriCiclo.setClicloId(new BigInteger(CicloId) );
			if (!Nombre.equals(""))
				agriCiclo.setNombre(Nombre);
			if (!FechaInicio.equals("")){
				Date fechaI = df.parse(FechaInicio);
				agriCiclo.setFechaInicio(fechaI);
				}
			if (!FechaFin.equals("")){
				Date fechaF = df.parse(FechaFin);
				agriCiclo.setFechaFin(fechaF);
				}
			//if (!Estado.equals(""))
				//agriCiclo.setEstado(new Integer(Estado));
			if (tipoConsulta.equals("encontrarTodos"))
			{
				JSONObject ActivoObject = new JSONObject();
				JSONArray ActivoArrays = new JSONArray();
				
				List<AgriCiclo> results = agriCicloDAO.BuscarTodos();
				for (AgriCiclo Ciclo: results)
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					CicloJSONObjetc.put("CicloId", Ciclo.getClicloId());
					CicloJSONObjetc.put("Nombre", Ciclo.getNombre());
					CicloJSONObjetc.put("FechaInicio", formatter.format(Ciclo.getFechaInicio()));
					CicloJSONObjetc.put("FechaFin", formatter.format(Ciclo.getFechaFin()));
					if (Ciclo.getEstado()==1)
						CicloJSONObjetc.put("Estado", "Activo");
					else 
						CicloJSONObjetc.put("Estado", "Inactivo");
					CicloJSONArray.add(CicloJSONObjetc);
				}
				ActivoObject.put("id", "1");
				ActivoObject.put("valor", "Si");
				ActivoArrays.add(ActivoObject);
				ActivoObject.put("id", "0");
				ActivoObject.put("valor", "No");
				ActivoArrays.add(ActivoObject);
				
				result.put("CicloJSONArray", CicloJSONArray);
				result.put("ActivoJSONArray", ActivoArrays);
			}
			if (tipoConsulta.equals("obtenerPorId"))
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				AgriCiclo results =agriCicloDAO.BuscarPorId(new BigInteger(CicloId));
				result.put("CicloId", results.getClicloId());
				result.put("Nombre", results.getNombre());
				result.put("FechaInicio", formatter.format(results.getFechaInicio()));
				result.put("FechaFin", formatter.format(results.getFechaFin()));
				result.put("Estado", results.getEstado());
			}
			if (tipoConsulta.equals("crear")){
				agriCiclo.setEstado(1);
				agriCicloTransaction.crear(agriCiclo);
				}
			
			if (tipoConsulta.equals("editar")){
				agriCiclo.setEstado(1);
				agriCicloTransaction.editar(agriCiclo);
				}
			
			if (tipoConsulta.equals("eliminar"))
				agriCicloTransaction.eliminar(agriCiclo);
			
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
