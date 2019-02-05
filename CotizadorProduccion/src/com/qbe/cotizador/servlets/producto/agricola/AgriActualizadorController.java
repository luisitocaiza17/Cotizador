package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.*;

/**
 * Servlet implementation class AgriActualizadorController
 */
@WebServlet("/AgriActualizadorController")
public class AgriActualizadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriActualizadorController() {
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
		String Cotizador = request.getParameter("cotizador") == null ? "" : request.getParameter("cotizador");
		
		//JSONObject retorno=new JSONObject();
		String result=generarData(Cotizador);

//		response.setContentType("text/plain; charset=ISO-8859-1"); 
//		response.getWriter().write(result);
		
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(result.getBytes("UTF-8").length);
		response.setHeader("Content-Encoding", "none");
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=" + "DataCotizadorOffline.config");//fileName);

		
		ServletOutputStream  o = response.getOutputStream();
		o.write(result.getBytes("UTF-8")); 
		o.flush(); 
		o.close(); 
		return;
		
		
	}
	
	public String generarData(String Cotizador){
		
		return "";
	}

}
