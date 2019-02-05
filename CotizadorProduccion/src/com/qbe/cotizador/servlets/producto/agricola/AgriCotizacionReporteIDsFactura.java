package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.agricola.AgriArchivosCotizacionDAO;
import com.qbe.cotizador.model.AgriArchivosCotizacion;

/**
 * Servlet implementation class AgriCotizacionReporteIDsFactura
 */
@WebServlet("/AgriCotizacionReporteIDsFactura")
public class AgriCotizacionReporteIDsFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCotizacionReporteIDsFactura() {
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
			String idFile = request.getParameter("idFile") == null ? "" : request.getParameter("idFile");
			AgriArchivosCotizacionDAO fileCotizacionDAO = new AgriArchivosCotizacionDAO();
			AgriArchivosCotizacion fileCotizacion = fileCotizacionDAO.BuscarPorId(new BigInteger(idFile));
			byte[] data =fileCotizacion.getFile();
			if (data!=null){
				response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + fileCotizacion.getNombreArchivo());//fileName);
				//result.write(response.getWriter());
				ServletOutputStream  o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 
			}
			return;
	}

}
