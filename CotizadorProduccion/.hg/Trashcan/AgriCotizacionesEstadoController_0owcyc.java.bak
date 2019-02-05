package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import SucreAgro7.PQBE_wsACTUALIZAESTADOResponse;
import SucreAgro7.SdtEstado;
import SucreAgro7.SdtResponseQBEErroresItem;

import com.google.gson.JsonArray;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEM;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriCotizacionesEstadoController
 */
@WebServlet("/AgriCotizacionesEstadoController")
public class AgriCotizacionesEstadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String IdFileIds="";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCotizacionesEstadoController() {
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
			String cotizacionId = request.getParameter("cotizacionId") == null ? "": request.getParameter("cotizacionId").trim();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			JSONArray listaEstados = new JSONArray(); 
			JSONObject estados= new JSONObject();
			
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
							String rutaRelativaReporte="../../../../../../../../static/CotizacionesAprobarMasivo/";
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
							
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
			
			if (tipoConsulta.equals("importar")) {
				
				String FileName = request.getParameter("FileName") == null ? "": request.getParameter("FileName");
				String actividad = request.getParameter("actividad") == null ? "": request.getParameter("actividad");

				String rutaPlantilla = this.getServletContext().getRealPath("")+ "/static/CotizacionesAprobarMasivo/" + FileName;
				
				List<AgriCotizacionEM> cotizacionList = new ArrayList<AgriCotizacionEM>();

				if (FileName.endsWith(".xlsx")) {cotizacionList = ReadExelFile.readXLSXFileEstadoMasiva(rutaPlantilla);
				} else if (FileName.endsWith(".xls")) {cotizacionList = ReadExelFile.readXLSXFileEstadoMasiva(rutaPlantilla);
				}
				JSONArray listDetalle = new JSONArray();
				listDetalle = procesarCambios(cotizacionList);
				
				
				if (listDetalle.size() > 0) {
					String IdFile = IdFileIds;
					IdFileIds="";
					result.put("mensaje", "Archivo procesado.");
					result.put("listDetalle", listDetalle);
					result.put("IdFile", IdFile);
					
				} else {
					result.put("mensaje",
							"No se ha podido procesar el archivo.");
				}
			}
			
			
			if(tipoConsulta.equals("cargarEstados")){
				EstadoDAO estadoDAO = new EstadoDAO();
				List<Estado> estado=estadoDAO.buscarTodos();
				for(Estado rs : estado ){
					if(rs.getNombre().equals("Borrador")||rs.getNombre().equals("Emitido")||rs.getNombre().equals("Anulado")
							||rs.getNombre().equals("Pendiente de Emitir")
							||rs.getNombre().equals("Pendiente Aprobar")
							||rs.getNombre().equals("Revocado")
							||rs.getNombre().equals("RevocadoCanal")
							||rs.getNombre().equals("Rechazado")){
						estados.put("nombre", rs.getNombre());
						estados.put("codigo", rs.getId());
						listaEstados.add(estados);						
					}
					
				}
				estados.put("nombre", "Borrar Num.Tramite");
				estados.put("codigo", "T");
				listaEstados.add(estados);
				result.put("listaEstados", listaEstados);
			}
			
			if(tipoConsulta.equals("actualizar")){
				String id = request.getParameter("cotizacionId") == null ? "": request.getParameter("cotizacionId").trim();
				String estadoId = request.getParameter("estadoId") == null ? "": request.getParameter("estadoId");
				
				Cotizacion cotizacion = new Cotizacion();
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				cotizacion=cotizacionDAO.buscarPorId(id);				
				
				if(estadoId.equals("T")){
					cotizacion.setNumeroTramite(null);
				}else{
					String estadoActual=cotizacion.getEstado().getId();
					if(estadoActual.equals(estadoId))
						throw new Exception("LA COTIZACION YA SE ENCUENTRA EN EL ESTADO AL QUE SE QUIERE ACTUALIZAR");					
					Estado estado = new Estado();
					EstadoDAO estadoDAO = new EstadoDAO();
					estado=estadoDAO.buscarPorId(estadoId);
					cotizacion.setEstado(estado);
				}
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				cotizacionTransaction.editar(cotizacion);				
			}
		
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}
	
	public JSONArray procesarCambios(List<AgriCotizacionEM> listado) {
		JSONObject detalleProceso = new JSONObject();
		JSONArray detalleProcesoList = new JSONArray();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		Cotizacion cotizacionC = new Cotizacion();
		CotizacionDAO cotizacionCDAO = new CotizacionDAO();
		String mensaje = "";
		Date fechaActual= new Date();
		try {
			for (AgriCotizacionEM nuevaCotizacion : listado) {//recorremos el listado de cotizaciones que viende desde excel				
				EstadoDAO estadoDAO = new EstadoDAO();
				AgriSucreAuditoria agriSucreAuditoria2 = new AgriSucreAuditoria();
				AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction2 = new AgriSucreAuditoriaTransaction();
				
				try {
					if (nuevaCotizacion.getCotizacionId() != null && !nuevaCotizacion.getCotizacionId().equals(""))//verificamos si tiene el ID
						cotizacionC = cotizacionCDAO.buscarPorId(nuevaCotizacion.getCotizacionId());
					try{
						//Auditoria Inicia el proceso de cotizacion:
						//para saber a que hora se hizo la notificacion
						agriSucreAuditoria2.setCanal("CAMBIO DE ESTADO");
						agriSucreAuditoria2.setEstado("MASIVO");
						agriSucreAuditoria2.setFecha(fechaActual);
						agriSucreAuditoria2.setObjeto("Ingresa al proceso cambio de estado Cot:"+nuevaCotizacion.getCotizacionId());
						agriSucreAuditoria2.setTramite(cotizacionC.getNumeroTramite());
						agriSucreAuditoriaTransaction2.crear(agriSucreAuditoria2);
						//fin de auditoria
					}catch(Exception e){
						e.printStackTrace();
					}
					String Objeto=agriSucreAuditoria2.getObjeto();
					String EstadoAnterior="";
					if (cotizacionC.getId() != null) {//verificamos si esta registrado en base
						Estado estado = new Estado();
						if(nuevaCotizacion.getEstado().equalsIgnoreCase("BAJA")){
							EstadoAnterior=cotizacionC.getEstado().getNombre();
							estado = estadoDAO.buscarPorNombre("Revocado", "Cotizacion");
							if(estado.getNombre()==null)
								throw new Exception("No se encuentra el estado "+nuevaCotizacion.getEstado()+" para poder actualizar la cotizacion");
							if(estado.getNombre().equals(cotizacionC.getEstado().getNombre()))
								throw new Exception("La cotizacion ya se encuentra en estado "+estado.getNombre());
							cotizacionC.setEstado(estado);
							//BORRAMOS EL NUMERO DE TRAMITE;
							cotizacionC.setNumeroTramite(null);
							//Auditoria
							Objeto=Objeto+" Cotizacion: "+cotizacionC.getId()+" estado anterior "+EstadoAnterior+ " estado Actual "+estado.getNombre()+" Se borro el num tramite";
							
						}else{
							EstadoAnterior=cotizacionC.getEstado().getNombre();
							
							estado = estadoDAO.buscarPorNombre(nuevaCotizacion.getEstado(), "Cotizacion");
							if(estado.getNombre()==null)
								throw new Exception("No se encuentra el estado "+nuevaCotizacion.getEstado()+" para poder actualizar la cotizacion");
							if(estado.getNombre().equals(cotizacionC.getEstado().getNombre()))
								throw new Exception("La cotizacion ya se encuentra en estado "+estado.getNombre());
							cotizacionC.setEstado(estado);	
							
							Objeto=Objeto+" Cotizacion: "+cotizacionC.getId()+" estado anterior "+EstadoAnterior+ " estado Actual "+estado.getNombre();
						}
						cotizacionTransaction.editar(cotizacionC);		
						agriSucreAuditoria2.setObjeto(Objeto);
						agriSucreAuditoriaTransaction2.editar(agriSucreAuditoria2);						
						detalleProceso.put("cotizacion", cotizacionC.getId());
						detalleProceso.put("detalle", "Estado Correcto: "+estado.getNombre());
						detalleProcesoList.add(detalleProceso);
					}else{
						throw new Exception("La cotizacion no existe");
					}
						
				} catch (Exception e) {
					detalleProceso.put("cotizacion", nuevaCotizacion.getCotizacionId());
					detalleProceso.put("detalle", mensaje+"Se ha producido un error: "+e.getMessage());
					detalleProcesoList.add(detalleProceso);
					cotizacionTransaction.editar(cotizacionC);		
					agriSucreAuditoria2.setObjeto(e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return detalleProcesoList;
		}
	}
}
