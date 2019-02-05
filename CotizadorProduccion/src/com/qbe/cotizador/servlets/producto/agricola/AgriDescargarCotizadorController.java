package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.agricola.AgriCotizadorOfflineDAO;
import com.qbe.cotizador.model.AgriCotizadorOffline;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriDescargarCotizadorController
 */
@WebServlet("/AgriDescargarCotizadorController")
public class AgriDescargarCotizadorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriDescargarCotizadorController() {
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
		JSONObject result = new JSONObject ();
		AgriCotizadorOfflineDAO agriCotizadorOfflineDAO = new AgriCotizadorOfflineDAO();
		AgriCotizadorOffline agriCotizadorOffline=agriCotizadorOfflineDAO.BuscarPorFecha();
		
		byte[] fileBytes = new byte[5000000];
		
		
		fileBytes=agriCotizadorOffline.getOffline();
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(fileBytes.length);
		response.setHeader("Content-Encoding", "none");
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=" + "CotizadorOffline_" + agriCotizadorOffline.getID() + ".zip");//fileName);
		//result.write(response.getWriter());
		ServletOutputStream  o = response.getOutputStream();
		o.write(fileBytes); 
		o.flush(); 
		o.close(); 
		
		System.out.println("Exito");
		
	}

}
