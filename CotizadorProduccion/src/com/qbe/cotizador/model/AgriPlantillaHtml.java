package com.qbe.cotizador.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoXTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionAprobacionController;
import com.qbe.cotizador.transaction.producto.agricola.AgriParametroXCanalTransaction;

/**
 * Servlet implementation class AgriPlantillaHtml
 */
@WebServlet("/AgriPlantillaHtml")
public class AgriPlantillaHtml extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriPlantillaHtml() {
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
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		
		AgriParametroXCanal parametroXCanal = new AgriParametroXCanal();
		AgriParametroXCanalDAO parametroXCanalDAO = new AgriParametroXCanalDAO();
		AgriParametroXCanalTransaction parametroXCanalTransaction = new AgriParametroXCanalTransaction();
		
		if(tipoConsulta.equals("descargar")){
			
			parametroXCanal = parametroXCanalDAO.BuscarPorId(new BigInteger(id));	
			String fileName=parametroXCanal.getPlantillaNombre();
			byte[] fileBytes = new byte[5000000];
			
			
			fileBytes=parametroXCanal.getPlantillaHtml();
			response.setHeader("Content-Transfer-Encoding", "binary"); 
			response.setContentLength(fileBytes.length);
			response.setHeader("Content-Encoding", "none");
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition","attachment; filename=" + "Cotizacion_" + fileName + ".html");//fileName);
			//result.write(response.getWriter());
			ServletOutputStream  o = response.getOutputStream();
			o.write(fileBytes); 
			o.flush(); 
			o.close(); 
			
		}
		
		
		return;
	}
	
}
