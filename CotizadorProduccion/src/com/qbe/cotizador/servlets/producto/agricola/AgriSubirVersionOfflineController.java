package com.qbe.cotizador.servlets.producto.agricola;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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
import org.apache.commons.io.IOUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizadorOfflineDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.model.AgriCotizadorOffline;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.transaction.producto.agricola.AgriCotizadorOfflineTransaction;
import com.qbe.cotizador.util.AES_Helper;

/**
 * Servlet implementation class AgriSubirVersionOfflineController
 */
@WebServlet("/AgriSubirVersionOfflineController")
public class AgriSubirVersionOfflineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriSubirVersionOfflineController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
			String nombreArchivo = request.getParameter("nombreArchivo") == null ? "":request.getParameter("nombreArchivo");

			if(tipoConsulta.equals("")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (!item.isFormField()) {
							// Process form file field (input type="file").
							System.out.println("Field name: " + item.getFieldName());
							System.out.println("File name: " + item.getName());
							System.out.println("File size: " + item.getSize());
							System.out.println("File type: " + item.getContentType());

							String fileName = item.getName();
							
							//tomamos la ruta relativa de la clase para referenciar la plantilla
							String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
									.getLocation().getPath();
							rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
							String rutaRelativaReporte="../../../../../../../../static/CotizacionesOffline/InstaladorOffline/";
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
							
							try {
								File saveFile = new File(rutaPlantilla,fileName);
								saveFile.createNewFile();
								item.write(saveFile);
								System.out.println("Current folder: " + saveFile.getCanonicalPath());
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
				//Leo el contenido del archivo que viene en la ruta seleccionada en pantalla
				AgriCotizadorOffline agriCotizadorOffline = new AgriCotizadorOffline();
				Date fechaActual = new Date();
				
				
				String textoJson="";
				try{
					String rutaPlantilla = this.getServletContext().getRealPath("") + "/static/CotizacionesOffline/InstaladorOffline/"+nombreArchivo;
					File f1=new File(rutaPlantilla);
					FileInputStream f_in=new FileInputStream(f1);
					
					agriCotizadorOffline.setFecha(fechaActual);
					agriCotizadorOffline.setOffline(IOUtils.toByteArray(f_in));
					AgriCotizadorOfflineTransaction agriCotizadorOfflineTransaction = new AgriCotizadorOfflineTransaction();
					agriCotizadorOfflineTransaction.crear(agriCotizadorOffline);
					result.put("mensaje", "El archivo se guardo con éxito.");
					result.put("estado", Boolean.FALSE);
				}
				catch(IOException ex){
					result.put("mensaje", "El archivo no pudo ser guardado.");
					result.put("estado", Boolean.TRUE);
				}
			}
			
			response.setContentType("application/json; charset=ISO-8859-1"); 
			//result.put("mensaje", "El archivo a sido procesado con exito.");
			result.write(response.getWriter());


		}catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("mensaje", ""+e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}
		
		
	}

	

	


}
