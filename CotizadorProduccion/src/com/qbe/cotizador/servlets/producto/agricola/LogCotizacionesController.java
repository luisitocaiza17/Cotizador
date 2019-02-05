package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreAuditoriaDAO;
import com.qbe.cotizador.model.AgriSucreAuditoria;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class LogCotizacionesController
 */
@WebServlet("/LogCotizacionesController")
public class LogCotizacionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogCotizacionesController() {
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
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			
			if(tipoConsulta.equals("encontrarPorParametros") ){
				String tramite = request.getParameter("nro_tramite") == null ? "": request.getParameter("nro_tramite").trim();
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));


				AgriSucreAuditoriaDAO agriSucreAuditoriaDAO=new AgriSucreAuditoriaDAO();
				List<AgriSucreAuditoria> data=agriSucreAuditoriaDAO.buscarLog(fInicio, fFinal, tramite, skip, take);
				long total=agriSucreAuditoriaDAO.buscarLogNum(fInicio, fFinal, tramite, skip, take);
				
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)total);
				pg.setData(data);
				
				Gson gson = new Gson();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
			}
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}
