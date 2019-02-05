package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEndosoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriCotizacionEndoso;
import com.qbe.cotizador.model.Estado;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriCotizacionEndosoController
 */
@WebServlet("/AgriCotizacionEndosoController")
public class AgriCotizacionEndosoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCotizacionEndosoController() {
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
		try{
			
			if(tipoConsulta.equalsIgnoreCase("cargarEndoso")){
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVenta = request.getParameter("puntoVenta") == null ? "": request.getParameter("puntoVenta");
				String NroTramite = request.getParameter("NroTramite") == null ? "": request.getParameter("NroTramite");
				String identificacion = request.getParameter("identificacion") == null ? "": request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? "": request.getParameter("CanalId");
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
				
				List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();				
				long contador = 0;	
				Estado estado= new Estado();
				cotizacionList = cDAO.consultarPorEstado("", "", numeroCotizacion, puntoVenta, "", identificacion, "", "", NroTramite, "",CanalId,estado,(int)skip,(int)take,"",true,false,fInicio, fFinal);
				contador = cDAO.consultarPorEstadoContador("", "", numeroCotizacion, puntoVenta, "", identificacion, "", "", NroTramite, "",CanalId,estado,(int)skip,(int)take,"",true,false,fInicio, fFinal);
					
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)contador);
				pg.setData(cotizacionList);
				
				//Gson gson = new Gson();
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
				/*String cotizacionId = request.getParameter("id") == null ? "": request.getParameter("id");
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				AgriCotizacionEndosoDAO agriCotizacionEndosoDAO = new AgriCotizacionEndosoDAO();
				List<AgriCotizacionEndoso> endoso = agriCotizacionEndosoDAO.buscarPorCotizacionId(new BigInteger(cotizacionId),take,skip,"","");
				long  tamaño=agriCotizacionEndosoDAO.buscarPorCotizacionIdNum(new BigInteger(cotizacionId),take,skip,"","");
						
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)tamaño);
				pg.setData(endoso);
				
				//Gson gson = new Gson();
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
				*/
			}
			if(tipoConsulta.equalsIgnoreCase("cargarEndosoTodos")){
				/*String fechaInicio = request.getParameter("fechaInicio") == null ? "": request.getParameter("fechaInicio");
				String fechaFin = request.getParameter("fechaFin") == null ? "": request.getParameter("fechaFin");
				// get the take and skip parameters
				 int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				 int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				
				AgriCotizacionEndosoDAO agriCotizacionEndosoDAO = new AgriCotizacionEndosoDAO();
				List<AgriCotizacionEndoso> endoso = agriCotizacionEndosoDAO.buscarPorCotizacionId("","","","","","","");
				long tamaño=agriCotizacionEndosoDAO.buscarPorCotizacionIdNum(null,take,skip,fechaInicio,fechaFin);
						
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)tamaño);
				pg.setData(endoso);
				
				//Gson gson = new Gson();
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
				*/
			}
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());	
			
		}catch(Exception e){
			e.printStackTrace();
			result.put("success", Boolean.FALSE);
			result.put("error", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());	
		}
		
		
	}

}
