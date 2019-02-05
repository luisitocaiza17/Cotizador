package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEndosoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMovimientoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriIndemnizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriIndemnizacionViewDAO;
import com.qbe.cotizador.model.AgriCotizacionMovimiento;
import com.qbe.cotizador.model.AgriIndemnizacionView;

/**
 * Servlet implementation class AgriIndemnizacionesController
 */
@WebServlet("/AgriIndemnizacionesController")
public class AgriIndemnizacionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriIndemnizacionesController() {
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
		JSONObject result = new JSONObject ();
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
		
		try 
		{
			if(tipoConsulta.equals("cargarIndemnizaciones")){
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? "": request.getParameter("puntoVenta");
				String NroTramite = request.getParameter("NroTramite") == null ? "": request.getParameter("NroTramite");
				String identificacion = request.getParameter("identificacion") == null ? "": request.getParameter("identificacion");
				String canalId = request.getParameter("CanalId") == null ? "": request.getParameter("CanalId");
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				//primeto tomamos los id de las cotizaciones que se encuentren dentro de los parametros de busquedas
				AgriIndemnizacionViewDAO agriIndemnizacionViewDAO = new AgriIndemnizacionViewDAO();
				List<AgriIndemnizacionView> data =agriIndemnizacionViewDAO.buscarPorParametros(numeroCotizacion, NroTramite, take, skip, fInicio, fFinal, canalId, puntoVentaId, identificacion);
				
				long  tamaño=agriIndemnizacionViewDAO.buscarPorParametrosNum(numeroCotizacion, NroTramite, take, skip, fInicio, fFinal, canalId, puntoVentaId, identificacion);
						
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)tamaño);
				pg.setData(data);
				
				//Gson gson = new Gson();
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;				
			}
				
				
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		
		}catch(Exception e)
		{
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}
