package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaArchivoPlanoDAO;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriCargaPreviaArchivoPlanoTransaction;

/**
 * Servlet implementation class AgriCargaPreviaArchivoPlanoController
 */
@WebServlet("/AgriCargaPreviaArchivoPlanoController")
public class AgriCargaPreviaArchivoPlanoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCargaPreviaArchivoPlanoController() {
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
		

		JSONObject result = new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
			String nombreArchivo = request.getParameter("nombreArchivo") == null ? "":request.getParameter("nombreArchivo");
			String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
			String id = request.getParameter("id") == null ? "":request.getParameter("id");
			String previaId=request.getParameter("previaId") == null ? "":request.getParameter("previaId");
			String hasAseguradas = request.getParameter("hasAseguradas") == null ? "":request.getParameter("hasAseguradas");
			
			AgriCargaPreviaArchivoPlano cargaPreviaArchivo = new AgriCargaPreviaArchivoPlano();
			AgriCargaPreviaArchivoPlanoDAO cargaPreviaArchivoDAO = new AgriCargaPreviaArchivoPlanoDAO();
			AgriCargaPreviaArchivoPlanoTransaction cargaPreviaArchivoTransaction = new AgriCargaPreviaArchivoPlanoTransaction();
			
			JSONObject cotizacionPreviaJsonObject = new JSONObject();
			JSONArray cotizacionPreviaJsonArray = new JSONArray();	

			if(tipoConsulta.equals("")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (!item.isFormField()) {							
							String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
									.getLocation().getPath();
							rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
							String rutaRelativaReporte=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano";
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
							
							String fileName = item.getName();
							try {
								File saveFile = new File(rutaPlantilla,fileName);
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


			/*	
			if(tipoConsulta.equals("importar")){				

				String rutaPlantilla = this.getServletContext().getRealPath("") + "/static/CotizacionesArchivoPlano/"+nombreArchivo;
				FileInputStream input = new FileInputStream(rutaPlantilla);
				List<CotizacionArchivoPlano> cotizacionList = new ArrayList<CotizacionArchivoPlano>();
				
				if(nombreArchivo.endsWith(".xlsx")){
					cotizacionList = ReadExelFile.readXLSXFile(rutaPlantilla);		
				}else if(nombreArchivo.endsWith(".xls")){
					cotizacionList = ReadExelFile.readXLSFile(rutaPlantilla);					 
				}
				
				HttpSession session = request.getSession(true);
				
				JSONArray listCotError = AgriProcesarCotizacionAP.procesarCotizacionesCargaPrevia(cotizacionList,session);
				if(listCotError.size() > 0){
					result.put("mensaje", "Archivo con errores.");
					result.put("listCotError", listCotError);
				}else{
					result.put("mensaje", "Archivo procesado correcatmente.");
				}
			}*/
			
			
			if(tipoConsulta.equals("importarKendo")){
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				String rutaPlantilla = this.getServletContext().getRealPath("") + File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano"+File.separator+nombreArchivo;
				List<CotizacionArchivoPlano> cotizacionList = new ArrayList<CotizacionArchivoPlano>();
				
				
				if(nombreArchivo.endsWith(".xlsx")){
					cotizacionList = ReadExelFile.readXLSXFile(rutaPlantilla);		
				}else if(nombreArchivo.endsWith(".xls")){
					cotizacionList = ReadExelFile.readXLSFile(rutaPlantilla);					 
				}
				
				HttpSession session = request.getSession(true);
				JSONArray listCotError = AgriProcesarCotizacionAP.procesarCotizacionesCargaPrevia(cotizacionList,session);
				
				long total =listCotError.size();
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)total);
				pg.setData(listCotError);
				
				Gson gson = new Gson();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
			}
			
			if(tipoConsulta.equalsIgnoreCase("buscarPorIdentificacion")){
				List<AgriCargaPreviaArchivoPlano> archivoList = cargaPreviaArchivoDAO.buscarPorIdentificacion(identificacion,1);
				for(AgriCargaPreviaArchivoPlano r : archivoList){
					cotizacionPreviaJsonObject.put("id",r.getId());
					cotizacionPreviaJsonObject.put("canal",r.getCanalNombre());
					cotizacionPreviaJsonObject.put("identificacion",r.getClienteIdentificacion());
					cotizacionPreviaJsonObject.put("cliente",r.getClienteNombre());
					cotizacionPreviaJsonObject.put("montoAsegurado",r.getMontoAsegurado());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try{
						String fechaCredito = df.format(r.getSolicitudFecha());
						cotizacionPreviaJsonObject.put("fechaSolicitud", fechaCredito);
					}catch(Exception e){
						System.out.println("sin fecha credito");
					}
					try{
						String fechaSiembra = df.format(r.getSiembraFecha());
						cotizacionPreviaJsonObject.put("fechaSiembra", fechaSiembra);
					}catch(Exception e){
						System.out.println("sin fecha credito");
					}
					/*cotizacionPreviaJsonObject.put("fechaSolicitud",r.getSolicitudFecha().toString());
					cotizacionPreviaJsonObject.put("fechaSiembra",r.getSiembraFecha().toString());*/
					cotizacionPreviaJsonObject.put("tipoCultivoId",r.getTipoCultivoId());
					cotizacionPreviaJsonObject.put("tipoCultivo",r.getTipoCultivoNombre());
					cotizacionPreviaJsonObject.put("hasAseguradas",r.getNumerHasAseguradas());
					cotizacionPreviaJsonObject.put("hasLote",r.getNumeroHasLote());
					if(r.getEsTecnificado())
						cotizacionPreviaJsonObject.put("esTecnificado","SI");
					else
						cotizacionPreviaJsonObject.put("esTecnificado","NO");
					
					cotizacionPreviaJsonObject.put("provinciaId",r.getProvinciaId());
					cotizacionPreviaJsonObject.put("provincia",r.getProvinciaNombre());
					cotizacionPreviaJsonObject.put("cantonId",r.getCantonId());
					cotizacionPreviaJsonObject.put("canton",r.getCantonNombre());					
					cotizacionPreviaJsonObject.put("parroquiaId",r.getParroquiaId());
					cotizacionPreviaJsonObject.put("parroquia",r.getParroquiaNombre());
					cotizacionPreviaJsonObject.put("ubicacion",r.getUbicacionCultivo());
					cotizacionPreviaJsonObject.put("telefono",r.getTelefono());
					cotizacionPreviaJsonObject.put("gpsX",r.getGpsX());
					cotizacionPreviaJsonObject.put("gpsY",r.getGpsY());
					cotizacionPreviaJsonObject.put("clienteId",r.getClienteId());
					cotizacionPreviaJsonArray.add(cotizacionPreviaJsonObject);
				}
				result.put("listCotizacion", cotizacionPreviaJsonArray);
				
			}
			String mensaje="";
			if(tipoConsulta.equalsIgnoreCase("cambiarEstado")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "":request.getParameter("cotizacionId");
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				Double hasAseguradasRestantes = 0.0;
				cargaPreviaArchivo = cargaPreviaArchivoDAO.buscarPorId(new BigInteger(id));
				hasAseguradasRestantes = cargaPreviaArchivo.getNumerHasAseguradas() - Double.parseDouble(hasAseguradas);
				
				if(hasAseguradasRestantes <= 0){
					cargaPreviaArchivo.setEstado(0);
					cargaPreviaArchivo.setNumerHasAseguradas(hasAseguradasRestantes);
				}else{
					cargaPreviaArchivo.setNumerHasAseguradas(hasAseguradasRestantes);
				}
				cargaPreviaArchivoTransaction.editar(cargaPreviaArchivo);
				mensaje="La Cotizacion N.-"+cotizacion.getId()+" se encuentra en Estado: "+cotizacion.getEstado().getNombre() +" y a sido procesada correctamente.";
				
				//Cambio de estado de la cotizacion en el final de los pasos todos quedan como pendiente de emitir
				Estado estado = new Estado();
				EstadoDAO estadoDAO = new EstadoDAO();
				estado=estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion");
				cotizacion.setEstado(estado);
				CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
				cotizacionTransaction.editar(cotizacion);				
			}
			
			if(tipoConsulta.equalsIgnoreCase("cargar")){
				if(!previaId.equals(""))
					id=previaId;
				cargaPreviaArchivo = cargaPreviaArchivoDAO.buscarPorId(new BigInteger(id));
				result.put("id",cargaPreviaArchivo.getId());
				result.put("previaId",cargaPreviaArchivo.getId());
				result.put("canal",cargaPreviaArchivo.getCanalNombre());
				result.put("identificacion",cargaPreviaArchivo.getClienteIdentificacion());
				result.put("cliente",cargaPreviaArchivo.getClienteNombre());
				result.put("apellidos",cargaPreviaArchivo.getClienteApellido());
				result.put("montoAsegurado",cargaPreviaArchivo.getMontoAsegurado());
				result.put("tipoIdentificacion",cargaPreviaArchivo.getMontoAsegurado());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
					String fechaCredito = df.format(cargaPreviaArchivo.getSolicitudFecha());
					result.put("fechaSolicitud", fechaCredito);
				}catch(Exception e){
					System.out.println("sin fecha credito");
				}
				try{
					String fechaSiembra = df.format(cargaPreviaArchivo.getSiembraFecha());
					result.put("fechaSiembra", fechaSiembra);
				}catch(Exception e){
					System.out.println("sin fecha credito");
				}
				//result.put("",.toString());
				//result.put("",cargaPreviaArchivo.getSiembraFecha().toString());
				result.put("tipoCultivoId",cargaPreviaArchivo.getTipoCultivoId());
				result.put("tipoCultivo",cargaPreviaArchivo.getTipoCultivoNombre());
				result.put("hasAseguradas",cargaPreviaArchivo.getNumerHasAseguradas());
				result.put("hasLote",cargaPreviaArchivo.getNumeroHasLote());				
				result.put("esTecnificado",cargaPreviaArchivo.getEsTecnificado());
				result.put("provinciaId",cargaPreviaArchivo.getProvinciaId());
				result.put("provincia",cargaPreviaArchivo.getProvinciaNombre());
				result.put("cantonId",cargaPreviaArchivo.getCantonId());
				result.put("canton",cargaPreviaArchivo.getCantonNombre());					
				result.put("parroquiaId",cargaPreviaArchivo.getParroquiaId());
				result.put("parroquia",cargaPreviaArchivo.getParroquiaNombre());
				result.put("ubicacion",cargaPreviaArchivo.getUbicacionCultivo());
				result.put("telefono",cargaPreviaArchivo.getTelefono());
				result.put("gpsX",cargaPreviaArchivo.getGpsX());
				result.put("gpsY",cargaPreviaArchivo.getGpsY());
				result.put("clienteId",cargaPreviaArchivo.getClienteId());		
			}
			
			result.put("mensaje",mensaje);
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
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
