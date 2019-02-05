package com.qbe.cotizador.servlets.producto.agricola;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.lf5.util.StreamUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Blob;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriParametroXCanal;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.transaction.producto.agricola.AgriParametroXCanalTransaction;


/**
 * Servlet implementation class AgriParametroXCanalController
 */
@WebServlet("/AgriParametroXCanalController")
public class AgriParametroXCanalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriParametroXCanalController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String id = request.getParameter("id") == null ? "" : request.getParameter("id");
			String sucursalEmisionId = request.getParameter("sucursalEmisionId") == null ? "" : request.getParameter("sucursalEmisionId");
			String canalId = request.getParameter("canalId") == null ? "" : request.getParameter("canalId");
			String tipoCultivoId = request.getParameter("tipoCultivoId")== null ? "" : request.getParameter("tipoCultivoId");						
			String plantillaEnsuranceId = request.getParameter("plantillaEnsuranceId")== null ? "" : request.getParameter("plantillaEnsuranceId");
			String contenedorEnsuranceId = request.getParameter("contenedorEnsuranceId")== null ? "" : request.getParameter("contenedorEnsuranceId");
			String plantillaNombre = request.getParameter("plantillaNombre")== null ? "" : request.getParameter("plantillaNombre");
			//noobmre del archivo subido
			String FileName = request.getParameter("FileName")== null ? "" : request.getParameter("FileName");
			String texto = request.getParameter("Texto") == null ? "" : request.getParameter("Texto");
			
			AgriParametroXCanal parametroXCanal = new AgriParametroXCanal();
			AgriParametroXCanalDAO parametroXCanalDAO = new AgriParametroXCanalDAO();
			AgriParametroXCanalTransaction parametroXCanalTransaction = new AgriParametroXCanalTransaction();
									
			com.qbe.cotizador.model.PuntoVenta canal = new com.qbe.cotizador.model.PuntoVenta();
			PuntoVentaDAO canalDAO = new PuntoVentaDAO();
			
			AgriTipoCultivo tipoCultivo = new AgriTipoCultivo();
			AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
			
			JSONObject parametroJSONbject = new JSONObject();
			JSONArray parametroJSONArray = new JSONArray();
						
			if(!id.equals(""))
				parametroXCanal.setParametroCanalId(new BigInteger(id));
			if(!canalId.equals(""))
				parametroXCanal.setPuntoVentaId(new BigInteger(canalId));
			if(!tipoCultivoId.equals(""))
				parametroXCanal.setTipoCultivoId(new BigInteger(tipoCultivoId));
			if(!plantillaEnsuranceId.equals(""))
				parametroXCanal.setPlantillaId(plantillaEnsuranceId);
			if(!contenedorEnsuranceId.equals(""))
				parametroXCanal.setContenedorEnsuranceId(contenedorEnsuranceId);
			if(!sucursalEmisionId.equals(""))
				parametroXCanal.setContenedorNumero(sucursalEmisionId);
			if(!plantillaNombre.equals(""))
				parametroXCanal.setPlantillaNombre(plantillaNombre);
			if(!FileName.equals("")){
				
				String rutaPlantilla = this.getServletContext().getRealPath("")+File.separator +"static"+File.separator +"plantillas"+File.separator +"Agricola"+File.separator +"PlantillasCultivos"+File.separator + FileName;
				System.out.println("Entra el html "+rutaPlantilla); 
				File f1=new File(rutaPlantilla);
				 FileInputStream f_in=new FileInputStream(f1);
				 parametroXCanal.setPlantillaHtml(IOUtils.toByteArray(f_in));
			}
			
			if(!texto.equals("")&& FileName.equals(""))
				parametroXCanal.setPlantillaHtml(texto.getBytes("UTF-8"));	
			
			
			if (tipoConsulta.equals("")){
				// /Hacer una copia del archivo cargado para el archivo de emision
				// masiva
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

							//tomamos la ruta relativa de la clase para referenciar la plantilla
							String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
									.getLocation().getPath();
							rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
							String rutaRelativaReporte=".."+File.separator +".."+File.separator +".."+File.separator +".."+File.separator +".."+File.separator +".."+File.separator +".."+File.separator +".."+File.separator +"static"+File.separator +"plantillas"+File.separator +"Agricola"+File.separator +"PlantillasCultivos"+File.separator;
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
							System.out.println("Ruta!:"+rutaPlantilla);
							String fileName = item.getName();
							try {
								File saveFile = new File(
										rutaPlantilla,
										fileName);
								saveFile.createNewFile();
								item.write(saveFile);
								System.out.println("Current folder: "
										+ saveFile.getCanonicalPath());
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
			
			if(tipoConsulta.equals("buscarTodos")){
				List<AgriParametroXCanal> listParametro = parametroXCanalDAO.BuscarTodos();
				for(AgriParametroXCanal parametro: listParametro) {
					parametroJSONbject.put("id", parametro.getParametroCanalId());
					
					canal = canalDAO.buscarPorId(parametro.getPuntoVentaId().toString());
					parametroJSONbject.put("canalNombre", canal.getNombre());						
										
					tipoCultivo = tipoCultivoDAO.BuscarPorId(parametro.getTipoCultivoId());
					parametroJSONbject.put("tipoCultivo", tipoCultivo.getNombre());
					
					parametroJSONbject.put("plantillaId", parametro.getPlantillaId());
					parametroJSONbject.put("contenedorId", parametro.getContenedorEnsuranceId());
					parametroJSONbject.put("plantillaNombre", parametro.getPlantillaNombre());

					
					
					parametroJSONArray.add(parametroJSONbject);
				}
				result.put("parametroArr", parametroJSONArray);				
				
			}
					
			if(tipoConsulta.equals("buscarPorId")){
				parametroXCanal = parametroXCanalDAO.BuscarPorId(new BigInteger(id));			 
				result.put("id", parametroXCanal.getParametroCanalId());
				result.put("canalId", parametroXCanal.getPuntoVentaId());
				result.put("tipoCultivoId", parametroXCanal.getTipoCultivoId());				
				result.put("plantillaId", parametroXCanal.getPlantillaId());
				result.put("sucursalEmisionId", parametroXCanal.getContenedorNumero());
				result.put("contenedorId", parametroXCanal.getContenedorEnsuranceId());
				result.put("plantillaNombre", parametroXCanal.getPlantillaNombre());
				if(parametroXCanal.getPlantillaHtml()!=null)
					result.put("texto", new String(parametroXCanal.getPlantillaHtml(), "UTF-8"));	
			}
			
			if(tipoConsulta.equals("buscarPorCanalCultivo")){
				parametroXCanal = parametroXCanalDAO.obtenerPorCanalTipoCultivo(new BigInteger(canalId), new BigInteger(tipoCultivoId));			 
				result.put("id", parametroXCanal.getParametroCanalId());
				result.put("canalId", parametroXCanal.getPuntoVentaId());
				result.put("tipoCultivoId", parametroXCanal.getTipoCultivoId());				
				result.put("plantillaId", parametroXCanal.getPlantillaId());
				result.put("contenedorId", parametroXCanal.getContenedorEnsuranceId());
				result.put("sucursalEmisionId", parametroXCanal.getContenedorNumero());
				result.put("plantillaNombre", parametroXCanal.getPlantillaNombre());
			}
			
			if(tipoConsulta.equals("crear")){				
				parametroXCanalTransaction.crear(parametroXCanal);
			}
			
			
			if(tipoConsulta.equals("editar")){
				parametroXCanalTransaction.editar(parametroXCanal);
			}		
			
			if(tipoConsulta.equals("eliminar")){
				parametroXCanalTransaction.eliminar(parametroXCanal);
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter()); 			
			
		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}
