package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.util.AES_Helper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class ImportarCAPController
 */
@WebServlet("/ImportarCAPController")
public class ImportarCAPController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportarCAPController() {
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

		JSONObject result = new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
			String nombreArchivo = request.getParameter("nombreArchivo") == null ? "":request.getParameter("nombreArchivo");
			

			if(tipoConsulta.equals("")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (!item.isFormField()) {							

							String fileName = item.getName();
							try {
								File saveFile = new File("../eclipseApps/Cotizador/static/CotizacionesArchivoPlano",fileName);
								saveFile.createNewFile();
								item.write(saveFile);								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (FileUploadException e) {
					try {
						throw new ServletException("Cannot parse multipart request.", e);
					} catch (ServletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}   

			}



			if(tipoConsulta.equals("importar")){				

				String rutaPlantilla = this.getServletContext().getRealPath("") + "/static/CotizacionesArchivoPlano/"+nombreArchivo;
				FileInputStream input = new FileInputStream(rutaPlantilla);
				List<CotizacionArchivoPlano> cotizacionList = new ArrayList<CotizacionArchivoPlano>();
				
				if(nombreArchivo.endsWith(".xlsx")){
					cotizacionList = ReadExelFile.readXLSXFile(rutaPlantilla);		
				}else if(nombreArchivo.endsWith(".xls")){
					cotizacionList = ReadExelFile.readXLSFile(rutaPlantilla);					 
				}
				
				JSONArray listCotError = AgriProcesarCotizacionAP.procesarCotizaciones(cotizacionList);
				if(listCotError.size() > 0){
					result.put("mensaje", "Archivo con errores.");
					result.put("listCotError", listCotError);
				}else{
					result.put("mensaje", "Archivo procesado correcatmente.");
				}
			}

			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			//result.put("mensaje", "El archivo a sido procesado con exito.");
			result.write(response.getWriter());


		}catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("mensaje", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}    
	}

}
