package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import SucreAgro7.PQBE_wsACTUALIZAESTADOResponse;
import SucreAgro7.SdtEstado;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import SucreAgro7.SdtResponseQBEErroresItem;

import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCanal_Punto_VentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaArchivoPlanoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriArchivosCotizacion;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.AgriCanal_Punto_Venta;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servicios.QBE.archivoPlano.ImportarCotizaciones;
import com.qbe.cotizador.servicios.QBE.archivoPlano.ObjetosRespuesta;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriArchivosCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriAuditoriaCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriCargaPreviaArchivoPlanoTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.qbe.cotizador.util.ResultAdjuntos;
import com.qbe.cotizador.util.Utilitarios;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriCotizacionAprobacionController
 */
@WebServlet("/AgriCotizacionAprobacionController")
public class AgriCotizacionAprobacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String IdFileIds="";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriCotizacionAprobacionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		JSONObject result = new JSONObject();
		try {
			String cotizacionId = request.getParameter("cotizacionId") == null ? "": request.getParameter("cotizacionId").trim();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			//String producto = request.getParameter("producto") == null ? "": request.getParameter("producto");

			//JSONObject cotizacionJSONObject = new JSONObject();
			//JSONArray cotizacionJSONArray = new JSONArray();
			//AgriCotizacionAprobacion cotizacion = new AgriCotizacionAprobacion();
			//CotizacionAprobacionDAO cotizacionDAO = new CotizacionAprobacionDAO();

			// /TODO: obtener usuario
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
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
			
			if (tipoConsulta.equalsIgnoreCase("cargarCombos")) {
				String canalId = request.getParameter("canalId") == null ? "": request.getParameter("canalId").trim();
				JSONObject puntoVentaJsonObject = new JSONObject();
				JSONArray puntoVentaJsonArray = new JSONArray();
				//PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				AgriCanal_Punto_VentaDAO puntoVentaDAO = new AgriCanal_Punto_VentaDAO();
				List<AgriCanal_Punto_Venta> puntoVentaList = puntoVentaDAO.BuscarPorCanal(canalId);
				for (AgriCanal_Punto_Venta r : puntoVentaList) {
					puntoVentaJsonObject.put("puntoVentaId", r.getPuntoVentaId());
					puntoVentaJsonObject.put("nombre", r.getNombrePuntoVenta());
					puntoVentaJsonArray.add(puntoVentaJsonObject);
				}
				result.put("listPuntoVenta", puntoVentaJsonArray);

			}
			if (tipoConsulta.equalsIgnoreCase("cargarCombosPuntoVenta")) {
				String canalId = request.getParameter("canalId") == null ? "": request.getParameter("canalId").trim();
				JSONObject puntoVentaJsonObject = new JSONObject();
				JSONArray puntoVentaJsonArray = new JSONArray();
				//PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				AgriCanal_Punto_VentaDAO puntoVentaDAO = new AgriCanal_Punto_VentaDAO();
				List<AgriCanal_Punto_Venta> puntoVentaList = puntoVentaDAO.BuscarPorCanal(canalId);
				for (AgriCanal_Punto_Venta r : puntoVentaList) {
					puntoVentaJsonObject.put("codigo", r.getPuntoVentaId());
					puntoVentaJsonObject.put("nombre", r.getNombrePuntoVenta());
					puntoVentaJsonArray.add(puntoVentaJsonObject);
				}
				result.put("listPuntoVenta", puntoVentaJsonArray);

			}
			if (tipoConsulta.equals("CargarCanal"))
			{
				JSONObject canalJsonObject = new JSONObject();
				JSONArray canalJsonArray = new JSONArray();
				AgriCanalDAO canalDAO = new AgriCanalDAO();
				
				List<AgriCanal> results = canalDAO.BuscarTodos();
				for (AgriCanal agriCanal: results)
				{
					canalJsonObject.put("CanalId", agriCanal.getCanalId());
					canalJsonObject.put("CanalNombre", agriCanal.getNombre());
					canalJsonArray.add(canalJsonObject);
				}
				result.put("ListadoCanal", canalJsonArray);
			}
			
			if (tipoConsulta.equalsIgnoreCase("encontrarPendientesAprobacion")) {
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
				String AgenteId = request.getParameter("agente") == null ? ""	: request.getParameter("agente");
				String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
				String ApellidosCliente = request.getParameter("ApellidosCliente") == null ? ""	: request.getParameter("ApellidosCliente");
				String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
				 // get the take and skip parameters
				  int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				  int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));


				// String identificacion= request.getParameter("identificacion")
				// == null ? "" : request.getParameter("identificacion");
				// String misCotizaciones=
				// request.getParameter("misCotizaciones") == null ? "" :
				// request.getParameter("misCotizaciones");
				String estadoConsulta = request.getParameter("estadoConsulta") == null ? ""
						: request.getParameter("estadoConsulta");
				// String usuarioId="";

				// if(misCotizaciones.equalsIgnoreCase("true"))
				// usuarioId = usuario.getId();
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				listaCotizacionesJSONArray = new JSONArray();
				// listaCotizacionesJSONArray.add(consultarPorTipoObjetoPuntoVentaYEstado(fInicio,
				// fFinal, tipoObjetoEncontrar, puntoVentaId,numeroCotizacion,
				// estadoConsulta));
				//listaCotizacionesJSONArray = consultarPorTipoObjetoPuntoVentaYEstado(fInicio, fFinal, tipoObjetoEncontrar, puntoVentaId, numeroCotizacion, AgenteId, NroTramite, Identificacion, ApellidosCliente,CanalId, estadoConsulta, skip, take);
				
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
				
				List<AgriCotizacionAprobacion> data = cDAO.buscarPorNoAprobadasNoEmitidasxFecha(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, NroTramite, Identificacion, ApellidosCliente,CanalId, estadoConsulta, skip, take);
				
				long total = cDAO.buscarContadorPorNoAprobadasNoEmitidasxFecha(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, NroTramite, Identificacion, ApellidosCliente,CanalId, estadoConsulta, skip, take);
				//result.put("listadoCotizacion", listaCotizacionesJSONArray);
				//buscarPorNoAprobadasNoEmitidasxFecha(fInicio, tipoObjetoEncontrar, fFinal, puntoVentaId, numeroCotizacion, AgenteId, NroTramite, Identificacion, ApellidosCliente,CanalId, estadoConsulta, skip, take);
				
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)total);
				pg.setData(data);
				
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
			}
			if (tipoConsulta.equals("Aprobacion")) {
				String AprobacionStatus = request.getParameter("AprobacionStatus") == null ? "": request.getParameter("AprobacionStatus");
				String ValorAsegurado = request.getParameter("ValorAsegurado") == null ? "": request.getParameter("ValorAsegurado");
				String Prima = request.getParameter("Prima") == null ? "": request.getParameter("Prima");
				String Iva = request.getParameter("Iva") == null ? "" : request.getParameter("Iva");
				String TotalFactura = request.getParameter("TotalFactura") == null ? "": request.getParameter("TotalFactura");
				String DerechoEmsion = request.getParameter("DerechoEmsion") == null ? "": request.getParameter("DerechoEmsion");
				String SeguroCampesino = request.getParameter("SeguroCampesino") == null ? "" : request.getParameter("SeguroCampesino");
				String SuperBancos = request.getParameter("SuperBancos") == null ? "": request.getParameter("SuperBancos");
				String Tasa = request.getParameter("Tasa") == null ? "": request.getParameter("Tasa");
				String Comentario = request.getParameter("Comentario") == null ? "": request.getParameter("Comentario");
				String IsRecalculo = request.getParameter("IsRecalculo") == null ? "": request.getParameter("IsRecalculo");

				Cotizacion cotizacionC = new Cotizacion();
				CotizacionDAO cotizacionCDAO = new CotizacionDAO();
				//AgriObjetoCotizacion objetoCotizacion = new AgriObjetoCotizacion();
				//AgriObjetoCotizacionDAO objetoDAO = new AgriObjetoCotizacionDAO();

				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				if (cotizacionId != null && !cotizacionId.equals(""))
					cotizacionC = cotizacionCDAO.buscarPorId(cotizacionId);
				
				//Auditoria Inicia el proceso de cotizacion:
				//para saber a que hora se hizo la notificacion
				Date fechaActual= new Date();
				AgriSucreAuditoria agriSucreAuditoria2 = new AgriSucreAuditoria();
				AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction2 = new AgriSucreAuditoriaTransaction();
				agriSucreAuditoria2.setCanal(cotizacionC.getPuntoVenta().getNombre());
				agriSucreAuditoria2.setEstado("INI_NOTIF");
				agriSucreAuditoria2.setFecha(fechaActual);
				agriSucreAuditoria2.setObjeto("Ingresa al proceso de cambio de estado");
				agriSucreAuditoria2.setTramite(cotizacionC.getNumeroTramite());
				agriSucreAuditoriaTransaction2.crear(agriSucreAuditoria2);
				//fin de auditoria
				
				
				// Cambiar estados
				EstadoDAO estadoDAO = new EstadoDAO();
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
				AgriCotizacionAprobacion cotAct = cDAO.buscarPorId(cotizacionC.getId());
				///Para el detalle de la cotizacion
				CotizacionDetalleTransaction cotizacionDetTransaction = new CotizacionDetalleTransaction();
				CotizacionDetalle cotizacionDet = new CotizacionDetalle();
				AgriObjetoCotizacionTransaction objetoTransaccion = new AgriObjetoCotizacionTransaction();
				AgriObjetoCotizacion objetoCotizacion = new AgriObjetoCotizacion();
				String mensajeEstado ="";
				Date VigenciaDesde = new Date();
				java.sql.Timestamp FechaEmision = new java.sql.Timestamp(VigenciaDesde.getTime());
				//TODO: llamar a las tablas hijas para poder grabar las observaciones de rechazo / aprobacion
				List<CotizacionDetalle> cotizacionesD = new ArrayList<CotizacionDetalle>();
				CotizacionDetalleDAO cotizacionDDAO = new CotizacionDetalleDAO();
				if (cotizacionId != null && !cotizacionId.equals(""))
					cotizacionesD = cotizacionDDAO.buscarCotizacionDetallePorCotizacion(cotizacionC);
				if (cotizacionesD.size() > 0){
						for (CotizacionDetalle cotDet : cotizacionesD) {
							TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
							TipoObjeto tipoObjeto  = tipoObjetoDAO.buscarPorNombre("Agricola");
							if (cotDet.getTipoObjetoId().equals(tipoObjeto.getId())){
								cotizacionDet = cotDet;
							}
						}
				}
				//TODO: Cargar el log de respuesta de sucre para probacion o rechazo
				AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
				AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
				String DatosRecibidos = "SERVICIO WEB-AGRICOLA Respuesta SUCRE: ";
				
				if (AprobacionStatus.equals("AP")) {
					Estado estadoAprobar = estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion");
					if (cotizacionC.getEstado().getId().equals(estadoAprobar.getId()))
						mensajeEstado = mensajeEstado+ "La cotizaci�n ya fue aprobada anteriormente. ";
					estadoAprobar = estadoDAO.buscarPorNombre("Pendiente Aprobar", "Cotizacion");
					if (!cotizacionC.getEstado().getId().equals(estadoAprobar.getId())){
						estadoAprobar = estadoDAO.buscarPorId(cotizacionC.getId());
						mensajeEstado = mensajeEstado+"La cotizaci�n esta en estado "+estadoAprobar.getNombre();
					}
					if (IsRecalculo.equals("true")) {

						// Cambio de valores cotizacion
						cotizacionC.setImpDerechoEmision(new Float(DerechoEmsion));
						cotizacionC.setImpSeguroCampesino(new Float(SeguroCampesino));
						cotizacionC.setImpSuperBancos(new Float(SuperBancos));
						cotizacionC.setSumaAseguradaTotal(new Float(ValorAsegurado));
						cotizacionC.setPrimaNetaTotal(Prima);
						cotizacionC.setImpIva(new Float(Iva));
						cotizacionC.setTotalFactura(new Float(TotalFactura));
						cotizacionC.setTasaProductoValor(Double.parseDouble(Tasa));
						
						if (cotizacionDet.getId() != null) {
							cotizacionDet.setPrimaNetaItem(new Float(Prima));
							cotizacionDet.setSumaAseguradaItem(new Float(ValorAsegurado));
							cotizacionDet.setTasa(new Float(Tasa));

						}
						//recalculamos Costos de produccion
						
						
						AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
						AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
						AgriObjetoCotizacion ObjectRecal=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDet.getObjetoId()));
						
						Double SumaA=Double.parseDouble(ValorAsegurado);
						Double hectareas=Double.parseDouble(""+ObjectRecal.getHectareasAsegurables());
						Double costoP=SumaA/hectareas;
						
						
						ObjectRecal.setCostoProduccion(Float.parseFloat(costoP.toString()));
						ObjectRecal.setCostoProduccionQBE(Float.parseFloat(costoP.toString()));
						agriObjetoCotizacionTransaction.editar(ObjectRecal);
						
					}
					
					if (cotizacionC.getSumaAseguradaTotal()==0)
						mensajeEstado = mensajeEstado+"La cotizaci�n no tiene valor Suma Asegurada. ";
					if (cotizacionC.getPrimaNetaTotal().equals("0"))
						mensajeEstado = mensajeEstado+"La cotizaci�n no tiene valor Prima. ";
					if (cotizacionC.getTotalFactura()==0)
						mensajeEstado = mensajeEstado+"La cotizaci�n no tiene valor Total de Factura. ";
					
					if (mensajeEstado.equals("")){
					
					
					// /TODO:Si es canal sucre consumir el servicio web
					// /Integracion con Canal sucre
					AgriParametroXPuntoVentaDAO canalNotificacionDAO = new AgriParametroXPuntoVentaDAO();
					AgriParametroXPuntoVenta canalNotificacion = canalNotificacionDAO.buscarPorPuntoVentaId(new BigInteger (cotizacionC.getPuntoVenta().getId()));
					//TODO:Guardar observacion de aprobacion / rechazo 
					AgriObjetoCotizacionDAO objetoDAO = new AgriObjetoCotizacionDAO();
					objetoCotizacion = objetoDAO.buscarPorId(new BigInteger(cotizacionDet.getObjetoId()));
					objetoCotizacion.setObservacionCotizacion(Comentario);
					objetoCotizacion = objetoTransaccion.editar(objetoCotizacion);
					///Forma de notificacion canal 
					/// 1) Ninguno, 2 )Web Service(Sucre) 3) mail (offline)
					
					//Para saber a que hora se hizo la notificacion
					AgriSucreAuditoria agriSucreAuditoria3 = new AgriSucreAuditoria();
					AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction3 = new AgriSucreAuditoriaTransaction();
					agriSucreAuditoria3.setCanal("SUCRE");
					agriSucreAuditoria3.setEstado("INI_NOTIF");
					agriSucreAuditoria3.setFecha(fechaActual);
					agriSucreAuditoria3.setObjeto("Ingresa al proceso de notificacion");
					agriSucreAuditoria3.setTramite(cotizacionC.getNumeroTramite());
					agriSucreAuditoriaTransaction3.crear(agriSucreAuditoria3);
					//fin de auditoria
					
						if (canalNotificacion.getFormaNotificacion() == 2) {
							if (cotizacionC.getNumeroTramite() != null) {
								AgriSucreRespuesta sucre = new AgriSucreRespuesta();
								//TODO: Se envia la  suma asegurada con recalculo de valores
								SdtEstado datos = ObjetoSucre(cotAct,cotizacionC.getSumaAseguradaTotal(),"APROBADO");
								
								PQBE_wsACTUALIZAESTADOResponse resultado = sucre.RespuestaSucre(cotizacionC.getNumeroTramite(),datos);
								if (resultado != null) {
									if (resultado.getRespuesta().getObservaciones().toUpperCase().equals("OK")) {// && resultado.getRespuesta().getObservaciones().toUpperCase().equals("OK")
										DatosRecibidos = DatosRecibidos +"Aprobaci�n "+resultado.getRespuesta().getObservaciones().toUpperCase();
										// Actualiza tabla detalle cotizacion si es un recalculo
										if (IsRecalculo.equals("true"))
											cotizacionDet = cotizacionDetTransaction.editar(cotizacionDet);
										// TODO:Actualiza estado tablacotizacion
										cotizacionC.setEstado(estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion"));
										//TODO: se cargar la vigencia con la fecha actual
										cotizacionC.setVigenciaDesde(VigenciaDesde);
										cotizacionC.setFechaEmision(FechaEmision);
										cotizacionC = cotizacionTransaction.editar(cotizacionC);
										// /TODO: Auditoria de aprobaciones y rechazos										
										try{
											String IdUsuario="0";
											if(usuario!=null)
												IdUsuario=usuario.getId();												
											RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"APROBADO REVISION");											
										}catch(Exception e){
											e.printStackTrace();
										}
										try{
											//registro el detalle de la cotizacion
											AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
											agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
										}catch(Exception e){
											e.printStackTrace();
										}
										mensajeEstado = "La Cotizaci�n ha sido aprobada correctamente";										
										result.put("mensajeEstado",mensajeEstado);
									} else {
										mensajeEstado = resultado.getRespuesta().getObservaciones();
										String erroresCadena="";
										SdtResponseQBEErroresItem[] error = resultado.getRespuesta().getErrores();
										for (SdtResponseQBEErroresItem item : error){
										String[] erroresU= item.getError().getErrorB();
											for (String er : erroresU)
											erroresCadena = erroresCadena+ er+" ";
										}
										DatosRecibidos = DatosRecibidos+ mensajeEstado+" "+erroresCadena;
										result.put("mensajeEstado", "No se ha podido aprobar. " +mensajeEstado+". "+erroresCadena);
									}
								} else {
									String ms = "No se obtuvo respuesta de confirmaci�n de Sucre.";
									DatosRecibidos = DatosRecibidos+ ms;
									result.put("mensajeEstado","No se ha podido aprobar la cotizaci�n. "+ms+" Por favor vuelva a intentar m�s tarde.");
								}
								//TODO: Cargar el log de respuesta de sucre para probacion o rechazo
								auditoria.setTramite(cotizacionC.getNumeroTramite());
								auditoria.setObjeto(DatosRecibidos);
								auditoria.setFecha(VigenciaDesde);
								auditoria.setCanal("SUCRE");
								auditoria.setEstado("APROBACION");
								procesoAuditoria.crear(auditoria);
							} else {
								result.put("mensajeEstado","No se ha podido aprobar. La cotizaci�n no tiene n�mero de tramite. ");
							}
					} else {
						// Actualiza tabla detalle cotizacion si es un recalculo
						if (IsRecalculo.equals("true"))
							cotizacionDet = cotizacionDetTransaction.editar(cotizacionDet);
						// TODO:Actualiza estado tabla cotizacion
						cotizacionC.setEstado(estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion"));
						//TODO: se cargar la vigencia con la fecha actual
						cotizacionC.setVigenciaDesde(VigenciaDesde);
						cotizacionC = cotizacionTransaction.editar(cotizacionC);
						try{
							//registro el detalle de la cotizacion
							AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
							// /TODO: Auditoria de aprobaciones y rechazos
						}catch(Exception e){
							e.printStackTrace();
						}
						
						try{
							String IdUsuario="0";
							if(usuario!=null)
								IdUsuario=usuario.getId();							
							RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"APROBADO REVISION");
						}catch(Exception e){
							e.printStackTrace();
						}
						mensajeEstado = "La Cotizaci�n ha sido aprobada correctamente";
						result.put("mensajeEstado", mensajeEstado);
					}
					// /TODO: Email pre-poliza si es offline
						if (cotAct.getTipoOrigen()!=null && cotAct.getTipoOrigen().equals("COTIZADOR_OFFLINE")) {
							if(cotizacionC.getPuntoVenta().getNombre().trim().equals("CREDIFE")){
								//registro el detalle de la cotizacion
								try{
									AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
									agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
								}catch(Exception e){
									e.printStackTrace();
								}
								EnviarMailCambioEstadoBlob(cotizacionC, cotAct, AprobacionStatus);
							}
							else{
								//registro el detalle de la cotizacion
								try{
									AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
									agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
								}catch(Exception e){
									e.printStackTrace();
								}
								AgriCorreoCoprogreso agriCorreoCoprogreso = new AgriCorreoCoprogreso();
								agriCorreoCoprogreso.envioEmail(cotAct.getUsuarioOffline(),cotizacionC, cotAct);
							}
						}
					}
					else {
						result.put("mensajeEstado", "No se ha podido aprobar. "+mensajeEstado);
					}
				} else if (AprobacionStatus.equals("RE")) {
					String MensajeRechazo = "";
					Estado estadoRechazado = estadoDAO.buscarPorNombre("Rechazado", "Cotizacion");
					if (cotizacionC.getEstado().getId().equals(estadoRechazado.getId()))
						MensajeRechazo = "La cotizaci�n ya fue rechazada anteriormente";
					estadoRechazado = estadoDAO.buscarPorNombre("Rechazado", "Cotizacion");
					if (MensajeRechazo.equals("")){
						cotizacionC.setEstado(estadoDAO.buscarPorNombre("Rechazado", "Cotizacion"));
						// /TODO:Si es canal sucre consumir el servicio web
						// /Integracion con Canal sucre
						AgriParametroXPuntoVentaDAO canalNotificacionDAO = new AgriParametroXPuntoVentaDAO();
						AgriParametroXPuntoVenta canalNotificacion = canalNotificacionDAO.buscarPorPuntoVentaId(new BigInteger (cotizacionC.getPuntoVenta().getId()));
						//TODO:Guardar observacion de aprobacion / rechazo 
						AgriObjetoCotizacionDAO objetoDAO = new AgriObjetoCotizacionDAO();
						objetoCotizacion = objetoDAO.buscarPorId(new BigInteger(cotizacionDet.getObjetoId()));
						objetoCotizacion.setObservacionCotizacion(Comentario);
						objetoCotizacion = objetoTransaccion.editar(objetoCotizacion);
						///Forma de notificacion canal 
						/// 1) Ninguno, 2 )Web Service(Sucre) 3) mail (offline)
							if (canalNotificacion.getFormaNotificacion() == 2) {
								if (cotizacionC.getNumeroTramite() != null) {
									AgriSucreRespuesta sucre = new AgriSucreRespuesta();
									//TODO: Se envia la misma suma asegurada al no haber recalculo de valores 
									SdtEstado datos = ObjetoSucre(cotAct,cotAct.getSumaAseguradaTotal(),"RECHAZADO");
									PQBE_wsACTUALIZAESTADOResponse resultado = sucre.RespuestaSucre(cotizacionC.getNumeroTramite(),datos);
									if (resultado != null) {
										if (resultado.getRespuesta().getObservaciones().toUpperCase().equals("OK")) {// && resultado.getRespuesta().getObservaciones().toUpperCase().equals("OK")
											DatosRecibidos = DatosRecibidos+ "Rechazar "+resultado.getRespuesta().getObservaciones().toUpperCase();
											// TODO:Actualiza estado tablacotizacion
											cotizacionC = cotizacionTransaction.editar(cotizacionC);
											// /TODO: Auditoria de aprobaciones y rechazos
											try{
												String IdUsuario="0";	
												if(usuario!=null)
													IdUsuario=usuario.getId();
												RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"RECHAZADO REVISION");												
											}catch(Exception e){
												e.printStackTrace();
											}
											try{
												//registro el detalle de la cotizacion
												AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
												agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Rechazo");
											}catch(Exception e){
												e.printStackTrace();
											}
											mensajeEstado = "La Cotizaci�n ha sido rechazada correctamente";
											result.put("mensajeEstado",mensajeEstado);
										} else {
											mensajeEstado = resultado.getRespuesta().getObservaciones();
											String erroresCadena="";
											SdtResponseQBEErroresItem[] error = resultado.getRespuesta().getErrores();
											for (SdtResponseQBEErroresItem item : error){
												String[] erroresU= item.getError().getErrorB();
												for (String er : erroresU)
												erroresCadena = erroresCadena+ er+" ";
											}
											DatosRecibidos = DatosRecibidos+ mensajeEstado+" "+erroresCadena;
											result.put("mensajeEstado", "No se ha podido rechazar. " +mensajeEstado+" "+erroresCadena);
										}
									} else {
										String ms = "No se obtuvo respuesta de confirmaci�n de Sucre.";
										DatosRecibidos = DatosRecibidos+ ms;
										result.put("mensajeEstado","No se ha podido rechazar la cotizaci�n. "+ms+" Por favor vuelva a intentar m�s tarde.");
									}
									//TODO: Cargar el log de respuesta de sucre para probacion o rechazo
									auditoria.setTramite(cotizacionC.getNumeroTramite());
									auditoria.setObjeto(DatosRecibidos);
									auditoria.setFecha(VigenciaDesde);
									auditoria.setCanal("SUCRE");
									auditoria.setEstado("RECHAZADO");
									procesoAuditoria.crear(auditoria);
								} else {
									result.put("mensajeEstado","No se ha podido rechazar. La cotizaci�n no tiene n�mero de tramite. ");
								}
						} else {
						// Actualiza estado tabla cotizacion
						cotizacionC = cotizacionTransaction.editar(cotizacionC);
						// /TODO:Auditoria de aprobaciones y rechazos
						try{
							String IdUsuario="0";
							if(usuario!=null)
								IdUsuario=usuario.getId();
							RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"RECHAZADO REVISION");
						}catch(Exception e){
							e.printStackTrace();
						}
						if (cotAct.getTipoOrigen()!=null && cotAct.getTipoOrigen().equals("COTIZADOR_OFFLINE")) {
							// Email rechazo cotizacion
							//EnviarMailCambioEstadoBlob(cotizacionC, null,AprobacionStatus);
							if (cotAct.getTipoOrigen()!=null && cotAct.getTipoOrigen().equals("COTIZADOR_OFFLINE")) {
								if(cotizacionC.getPuntoVenta().getNombre().trim().equals("CREDIFE")){
									//registro el detalle de la cotizacion
									try{
										AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
										agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Rechazo");
									}catch(Exception e){
										e.printStackTrace();
									}
									EnviarMailCambioEstadoBlob(cotizacionC, cotAct, AprobacionStatus);
								}
								else{
									try{
										//registro el detalle de la cotizacion
										AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
										agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Rechazo");
									}catch(Exception e){
										e.printStackTrace();
									}
									AgriCorreoCoprogreso agriCorreoCoprogreso = new AgriCorreoCoprogreso();
									agriCorreoCoprogreso.envioEmail(cotAct.getUsuarioOffline(),cotizacionC, cotAct);
								}
							}
						}else{
							/*Pronaca Proceso de suma de hectareas en cotizaciones previas*/
							if(cotizacionC.getPuntoVenta().getNombre().equals("PRONACA")){
								CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
								CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacionC).get(0);
								AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
								AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
								
								AgriCargaPreviaArchivoPlanoDAO agriCargaPreviaArchivoPlanoDAO = new AgriCargaPreviaArchivoPlanoDAO();
								AgriCargaPreviaArchivoPlano agriCargaPreviaArchivoPlano = agriCargaPreviaArchivoPlanoDAO.buscarPorId(new BigInteger(agriObjetoCotizacion.getObjetoOfflineId()));
								double valorActual=agriCargaPreviaArchivoPlano.getNumerHasAseguradas();
								double ValorRechazado=Double.parseDouble(""+objetoCotizacion.getHectareasAsegurables());
								double valorTotal=valorActual+ValorRechazado;
								agriCargaPreviaArchivoPlano.setNumerHasAseguradas(valorTotal);
								agriCargaPreviaArchivoPlano.setEstado(1);
								AgriCargaPreviaArchivoPlanoTransaction agriCargaPreviaArchivoPlanoTransaction= new AgriCargaPreviaArchivoPlanoTransaction();
								agriCargaPreviaArchivoPlanoTransaction.editar(agriCargaPreviaArchivoPlano);
							}else{
								//registro el detalle de la cotizacion
								try{
									AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
									agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Rechazo");
								}catch(Exception e){
									e.printStackTrace();
								}									
							}
						}
						
						
						/*Fin de Proceso*/
						
						mensajeEstado = "La Cotizaci�n ha sido rechazada correctamente";
						result.put("mensajeEstado", mensajeEstado);
						}
					}
					else {
						result.put("mensajeEstado", "No se ha podido rechazar. "+MensajeRechazo);
					}
				}

			}
			if (tipoConsulta.equals("buscarCotizacionId")) {
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
				AgriCotizacionAprobacion cotAct = cDAO
						.buscarPorId(cotizacionId);
				result.put("NumeroTramite", cotAct.getNumeroTramite());
				result.put("CanalNombre", cotAct.getCanalNombre());
				result.put("PuntoVenta", cotAct.getPuntoVenta());
				result.put("EstadoCotizacion", cotAct.getEstadoCotizacion());
				result.put("confirmacion", cotAct.getConfirmacion());
				result.put("codigo", cotAct.getId());
//				result.put("objeto_cotizacion_id",
//						cotAct.getObjetoCotizacionId());
				result.put("punto_venta", cotAct.getPuntoVenta());
				result.put("tipo_cultivo_nombre", cotAct.getTipoCultivoNombre());
				result.put("provincia_nombre", cotAct.getProvinciaNombre());
				result.put("canton_nombre", cotAct.getCantonNombre());
				result.put("hectareas_lote", cotAct.getHectareasLote());
				result.put("hectareas_asegurables",
						cotAct.getHectareasAsegurables());
				SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
				result.put("fecha_siembra",sm.format(cotAct.getFechaSiembra()));
				result.put("suma_asegurada_total",
						cotAct.getSumaAseguradaTotal());
				result.put("prima_neta_total", cotAct.getPrimaNetaTotal());
				result.put("monto_credito", cotAct.getMontoCredito());
				result.put("costo_produccion", cotAct.getCostoProduccion());
				result.put("costo_produccionQBE", cotAct.getCostoProduccionQBE());
				result.put("iva",cotAct.getIva());
				result.put("totalImpuestos", cotAct.getIva()+cotAct.getSeguroBancos()+cotAct.getSeguroCampesino()+cotAct.getDerechoEmision());
				result.put("totalFactura", cotAct.getTotalFactura());
//				result.put("tipo_seguro", cotAct.getTipoSeguro());
				result.put("anios_cultivo", cotAct.getAniosCultivo());
				result.put("tasa", cotAct.getTasa());
				result.put("parroquia_nombre", cotAct.getParroquiaNombre()==null?"":cotAct.getParroquiaNombre());
				result.put("sitio", cotAct.getDireccionSiembra());
				result.put("nombres_cliente", cotAct.getnombresCliente()==null?"":cotAct.getnombresCliente());
				result.put("nombresAgente", (cotAct.getNombresAgente()== null ?"": cotAct.getNombresAgente()));
				result.put("nombresUsuario", (cotAct.getNombresUsuario()== null ?"": cotAct.getNombresUsuario()));
				result.put("variedad",cotAct.getVariedad()==null?"":cotAct.getVariedad());
				result.put("observacion",cotAct.getObservacionRegla());
				result.put("latitud", cotAct.getLatitud());
				result.put("longitud", cotAct.getLongitud());
				result.put("ClienteIdentificacion", cotAct.getIdentificacionCliente());
				result.put("DisponeRiego", (cotAct.getDisponeRiego()==true ? "SI":"NO"));
				result.put("DisponeAsistencia", (cotAct.getDisponeAsistencia()==true ? "SI":"NO"));
				result.put("AgricultorTecnificado", (cotAct.getAgricultorTecnificado()==true ? "SI":"NO"));
				//cambios en sitios de siembra y lugares de siembra.
				result.put("tieneCambios", cotAct.getTieneModificacion());
				result.put("detalleCambios", cotAct.getDetalleModificacion());
				
				// Regla
				if (cotAct.getTasa() == 0) {
					AgriReglaDAO agriReglaDAO = new AgriReglaDAO();
					JSONObject ReglaObjetc = null;
					//JSONArray listaReglas = new JSONArray();

					Date dateSiembra = null;

					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					if (cotAct.getFechaSiembra().toString() != "") {
						dateSiembra = formatter.parse(cotAct.getFechaSiembra()
								.toString());
					}

					if (cotAct.getProvinciaId() != null
							&& cotAct.getCantonId() != null
							&& cotAct.getTipoCultivoId() != null) {
						//Obtengo el id del tipo de calculo en base al parametro por punto de venta
						AgriParametroXPuntoVentaDAO parametroDAO = new AgriParametroXPuntoVentaDAO();
						AgriParametroXPuntoVenta parametro = parametroDAO.buscarPorId(cotAct.getPuntoVentaId());
						AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
						AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
						agriTipoCalculo=agriTipoCalculoDAO.BuscarPorId(parametro.getTipoCalculoId());
						//Se buscca la regla
						List<AgriRegla> reglas = agriReglaDAO
								.BuscarPorParametros(cotAct.getProvinciaId(),
										cotAct.getCantonId(),
										cotAct.getTipoCultivoId(),agriTipoCalculo.getTipoCalculoId());
						if (reglas.size() != 0) {
							ReglaObjetc = new JSONObject();
							AgriRegla regla = reglas.get(0);
							// for(AgriRegla regla:reglas){
							if (regla.getClicloId() != null) {
								AgriCicloDAO cicloDAO = new AgriCicloDAO();
								AgriCiclo ciclo = cicloDAO.BuscarPorId(regla
										.getClicloId());
								Date fechafin = ciclo.getFechaFin();
								Date fechaInicio = ciclo.getFechaInicio();
								if (dateSiembra.after(fechaInicio)
										&& dateSiembra.before(fechafin)) {
									ReglaObjetc.put("CostoProduccion",
											regla.getCostoProduccion());
									ReglaObjetc.put("Tasa", regla.getTasa());
								}
							} else {
									ReglaObjetc.put("CostoProduccion","0.00");
									ReglaObjetc.put("Tasa", "0.00");
							}

						}
						// result.put("Reglas", listaReglas);
						result.put("Regla", ReglaObjetc);
					}
				}
				// Variables Calculo
				VariableSistemaDAO variableDAO = new VariableSistemaDAO();
				TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
				TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
				List<VariableSistema> variablesistema = variableDAO
						.buscarTipoVariable(tipoVariable);
				double valorDerechosEmision = 0;
				double valorSeguroCampesino = 0;
				double valorSuperBancos = 0;
				double valorIva = 0;
				JSONObject VariablesObjetc = null;
				if (variablesistema.size() > 0) {
					VariablesObjetc = new JSONObject();
					for (VariableSistema variable : variablesistema) {
						if (variable.getNombre().equals("DERECHOS_EMISION_AGRICOLA")) {
							valorDerechosEmision = Double.parseDouble(variable
									.getValor());
							VariablesObjetc.put("DerechosEmision",
									valorDerechosEmision);

						} else if (variable.getNombre().equals(
								"SEGURO_CAMPESINO")) {
							valorSeguroCampesino = Double.parseDouble(variable
									.getValor());
							VariablesObjetc.put("SeguroCampesino",
									valorSeguroCampesino);
						} else if (variable.getNombre().equals(
								"SUPER_DE_BANCOS")) {
							valorSuperBancos = Double.parseDouble(variable
									.getValor());
							VariablesObjetc
									.put("SuperBancos", valorSuperBancos);

						} else if (variable.getNombre().equals("IVA")) {
							valorIva = Double.parseDouble(variable.getValor());
							VariablesObjetc.put("Iva", valorIva);
						}

					}
					result.put("VariablesCalculo", VariablesObjetc);
				}
			}
			///TODO:Aprobacion/Emision Masiva
			if (tipoConsulta.equals("importar")) {
				
				//verificamos que el usuario este en secion;
				HttpSession sessionUsuario = request.getSession(true);
				Usuario usuarioSession = (Usuario) session.getAttribute("usuario");
				if(usuarioSession==null)
					throw new Exception("El usuario no esta en seccion, debe refrescar la p�gina");
				
				String FileName = request.getParameter("FileName") == null ? "": request.getParameter("FileName");
				String actividad = request.getParameter("actividad") == null ? "": request.getParameter("actividad");
				String subproceso = request.getParameter("subproceso") == null ? "": request.getParameter("subproceso");

				String rutaPlantilla = this.getServletContext().getRealPath("")+ "/static/CotizacionesAprobarMasivo/" + FileName;
				
				List<Cotizacion> cotizacionList = new ArrayList<Cotizacion>();

				if (FileName.endsWith(".xlsx")) {cotizacionList = ReadExelFile.readXLSXFileAprobacionMasiva(rutaPlantilla);
				} else if (FileName.endsWith(".xls")) {cotizacionList = ReadExelFile.readXLSFileAprovacionMasiva(rutaPlantilla);
				}
				JSONArray listDetalle = new JSONArray();
				if (actividad.equals("aprobar")) {
					listDetalle = procesarAprobacionMasiva(cotizacionList,
							usuarioSession,subproceso);
				} else if (actividad.equals("emitir")){
					listDetalle = procesarEmision(cotizacionList,usuarioSession);
					//det = procesarEmision(cotizacionList,usuario);
				}
				
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
			///TODO:EMISION INDIVIDUAL
			if (tipoConsulta.equals("Emitir")) {
				String lstCotizacion = request.getParameter("Cotizaciones") == null ? "" : request.getParameter("Cotizaciones");
				List<Cotizacion> cotizacionList = new ArrayList<Cotizacion>();
				if (!lstCotizacion.equals("")){
					List<String> list = new ArrayList<String>(Arrays.asList(lstCotizacion.split(",")));
					for (String id : list){
						Cotizacion cotizacionC = new Cotizacion();
						CotizacionDAO cotizacionCDAO = new CotizacionDAO();
						cotizacionC = cotizacionCDAO.buscarPorId(id);
						cotizacionList.add(cotizacionC);
					}
				}
				JSONArray listDetalle = new JSONArray();
				//List<JSONArray> det = new ArrayList<JSONArray>();
				listDetalle = procesarEmision(cotizacionList,usuario);
				if (listDetalle.size()>0){
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
			///TODO: REVOCAR VARIOS
			if (tipoConsulta.equals("Revocar")) {
				String lstCotizacion = request.getParameter("Cotizaciones") == null ? "" : request.getParameter("Cotizaciones");
				List<Cotizacion> cotizacionList = new ArrayList<Cotizacion>();
				if (!lstCotizacion.equals("")){
					List<String> list = new ArrayList<String>(Arrays.asList(lstCotizacion.split(",")));
					for (String id : list){
						Cotizacion cotizacionC = new Cotizacion();
						CotizacionDAO cotizacionCDAO = new CotizacionDAO();
						cotizacionC = cotizacionCDAO.buscarPorId(id);
						cotizacionList.add(cotizacionC);
					}
				}
				JSONArray listDetalle = new JSONArray();
				//List<JSONArray> det = new ArrayList<JSONArray>();
				listDetalle = procesarRevocacion(cotizacionList,usuario);
				if (listDetalle.size()>0){
					result.put("mensaje", "Archivo procesado.");
					result.put("listDetalle", listDetalle);
				} else {
					result.put("mensaje",
							"No se ha podido procesar el archivo.");
				}
			}
			
			
			//Cotizaciones archivadas agricola
			if (tipoConsulta.equalsIgnoreCase("encontrarArchivadas")) {
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String fInicioE = request.getParameter("fInicioE") == null ? "": request.getParameter("fInicioE");
				String fFinalE = request.getParameter("fFinalE") == null ? "": request.getParameter("fFinalE");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
				String AgenteId = request.getParameter("agente") == null ? ""	: request.getParameter("agente");
				String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
				String ApellidosCliente = request.getParameter("ApellidosCliente") == null ? ""	: request.getParameter("ApellidosCliente");
				String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
				String misCotizaciones = request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String confirmadas = request.getParameter("confirmadas") .equals("") ? null : request.getParameter("confirmadas");
				String movimientos = request.getParameter("movimientos") .equals("") ? null : request.getParameter("movimientos");
				String tieneObservaciones = request.getParameter("tieneObservaciones") == null ? "" : request.getParameter("tieneObservaciones");
				String usuarioId = "";
				
				 // get the take and skip parameters
				 int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				 int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				
				String estadoConsulta = request.getParameter("estadoConsulta") == null ? ""
						: request.getParameter("estadoConsulta");
				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();
				if(!tieneObservaciones.equalsIgnoreCase("true"))
					tieneObservaciones = "";									

				Rol rol=new Rol();
				
				List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();				
				long contador = 0;	
				Estado estado= new Estado();
				boolean estaConfirmadoPorCanal=false;
				//verifico si estan confirmadas
				if(confirmadas!=null){
					estaConfirmadoPorCanal=true;
				}
				
				boolean movimientosHechos=false;
				if(movimientos!=null){
					movimientosHechos=true;
				}
				JSONArray cotizacionJsonArray = new JSONArray();
				cotizacionList = cDAO.consultarPorEstado(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,(int)skip,(int)take,"",estaConfirmadoPorCanal,movimientosHechos,fInicioE,fFinalE);
				contador = cDAO.consultarPorEstadoContador(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,(int)skip,(int)take,"",estaConfirmadoPorCanal,movimientosHechos,fInicioE,fFinalE);
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)contador);
				pg.setData(cotizacionList);
				
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;	
			}
			
			//Cotizaciones archivadas agricola
			if (tipoConsulta.equalsIgnoreCase("encontrarParaImprimir")) {
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
				String AgenteId = request.getParameter("agente") == null ? ""	: request.getParameter("agente");
				String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
				String ApellidosCliente = request.getParameter("ApellidosCliente") == null ? ""	: request.getParameter("ApellidosCliente");
				String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
				String misCotizaciones = request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String esImpreso = request.getParameter("esImpreso") == null ? "" : request.getParameter("esImpreso");
				String tieneObservaciones = request.getParameter("tieneObservaciones") == null ? "" : request.getParameter("tieneObservaciones");		
				String usuarioId = "";
				// get the take and skip parameters
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
				
				String estadoConsulta = request.getParameter("estadoConsulta") == null ? ""
						: request.getParameter("estadoConsulta");
				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();
				
				if(!tieneObservaciones.equalsIgnoreCase("true"))
					tieneObservaciones = "";
				
				if(esImpreso.equalsIgnoreCase("true"))
					esImpreso = "0";
				else
					esImpreso = "1";	
				
				Rol rol=new Rol();
				
				List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();				
				long contador = 0;	
				
				/*if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();*/
				//if(rol.getId().equals("1")||rol.getId().equals("2")||rol.getId().equals("7")||rol.getId().equals("23")){//usuarios de qbe
				cotizacionList =  cDAO.consultarCotizacion(fInicio, fFinal, numeroCotizacion,puntoVentaId,AgenteId,Identificacion,usuarioId,/*esImpreso,*/ NroTramite,ApellidosCliente,CanalId,estadoConsulta,tieneObservaciones,(int)skip,(int)take);
				contador = cDAO.consultarCotizacionContador(fInicio, fFinal, numeroCotizacion,puntoVentaId,AgenteId,Identificacion,usuarioId,/*esImpreso,*/NroTramite,ApellidosCliente,CanalId,estadoConsulta,tieneObservaciones,(int)skip,(int)take);										
				/*}else{
					PuntoVenta puntoVenta=new PuntoVenta();					
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						cotizacionList =  cDAO.consultarCotizacion(fInicio, fFinal, numeroCotizacion,puntoVenta.getId(),AgenteId,Identificacion,usuarioId,esImpreso,NroTramite,ApellidosCliente,CanalId,estadoConsulta,tieneObservaciones,(int)skip,(int)take);
						contador = cDAO.consultarCotizacionContador(fInicio, fFinal, numeroCotizacion,puntoVentaId,AgenteId,Identificacion,usuarioId,esImpreso,NroTramite,ApellidosCliente,CanalId,estadoConsulta,tieneObservaciones,(int)skip,(int)take);						
					}
				}*/
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)contador);
				pg.setData(cotizacionList);
				
				
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;			
			}			
			
			//Cotizaciones por estado
			if (tipoConsulta.equalsIgnoreCase("encontrarPorEstado")) {
				String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
				String numeroCotizacion = request.getParameter("numeroCotizacion") == null ? "": request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? ""	: request.getParameter("puntoVenta");
				String AgenteId = request.getParameter("agente") == null ? ""	: request.getParameter("agente");
				String NroTramite = request.getParameter("NroTramite") == null ? ""	: request.getParameter("NroTramite");
				String ApellidosCliente = request.getParameter("ApellidosCliente") == null ? ""	: request.getParameter("ApellidosCliente");
				String Identificacion = request.getParameter("identificacion") == null ? ""	: request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? ""	: request.getParameter("CanalId");
				String misCotizaciones = request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String facturacionId = request.getParameter("facturaId") == null ? "" : request.getParameter("facturaId");
								//String esImpreso = request.getParameter("esImpreso") == null ? "" : request.getParameter("esImpreso");
				String usuarioId = "";
				
				// get the take and skip parameters
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
				
				String estadoConsulta = request.getParameter("estadoConsulta") == null ? "": request.getParameter("estadoConsulta");
								
				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();			
								
				Estado estado = new Estado();
				EstadoDAO estadoDAO = new EstadoDAO();
				estado = estadoDAO.buscarPorId(estadoConsulta);

				Rol rol=new Rol();
				List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();				
				long contador = 0;	
				contador = cDAO.consultarPorEstadoContador(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,(int)skip,(int)take,facturacionId,false,false,"","");
							
				cotizacionList = cDAO.consultarPorEstado(fInicio, fFinal, numeroCotizacion, puntoVentaId, AgenteId, Identificacion, usuarioId, "", NroTramite, ApellidosCliente,CanalId,estado,(int)skip,(int)take,facturacionId,false,false,"","");
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)contador);
				pg.setData(cotizacionList);
				//Gson gson = new Gson();
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());

		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}
	
	
	public JSONArray procesarAprobacionMasiva(List<Cotizacion> listado,	Usuario usuario, String subproceso) {
		JSONObject detalleProceso = new JSONObject();
		JSONArray detalleProcesoList = new JSONArray();
		
		String proceso="";
		String estado="";
		//estado dependiendo del subproceso
		if(subproceso.equals("aprobacion")){
			proceso="APROBADO";
			estado="Pendiente de Emitir";
		}else{
			proceso="RECHAZADO";
			estado="Rechazado";
		}
		
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		Cotizacion cotizacionC = new Cotizacion();
		CotizacionDAO cotizacionCDAO = new CotizacionDAO();
		String mensaje = "";
		Date fechaActual= new Date();
		try {
			for (Cotizacion nuevaCotizacion : listado) {//recorremos el listado de cotizaciones que viende desde excel				
				mensaje = "";
				EstadoDAO estadoDAO = new EstadoDAO();
				Date VigenciaDesde = new Date();
				java.sql.Timestamp FechaEmision = new java.sql.Timestamp(VigenciaDesde.getTime());
				//TODO: Cargar el log de respuesta de sucre para probacion o rechazo
				AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
				AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
				String DatosRecibidos = "SERVICIO WEB-AGRICOLA Respuesta SUCRE: ";				
				try {
					if (nuevaCotizacion.getId() != null && !nuevaCotizacion.getId().equals(""))//verificamos si tiene el ID
						cotizacionC = cotizacionCDAO.buscarPorId(nuevaCotizacion.getId());
					try{
						//Auditoria Inicia el proceso de cotizacion:
						//para saber a que hora se hizo la notificacion
						AgriSucreAuditoria agriSucreAuditoria2 = new AgriSucreAuditoria();
						AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction2 = new AgriSucreAuditoriaTransaction();
						agriSucreAuditoria2.setCanal(cotizacionC.getPuntoVenta().getNombre());
						agriSucreAuditoria2.setEstado("INI_NOTIF");
						agriSucreAuditoria2.setFecha(fechaActual);
						agriSucreAuditoria2.setObjeto("Ingresa al proceso cambio de estado");
						agriSucreAuditoria2.setTramite(cotizacionC.getNumeroTramite());
						agriSucreAuditoriaTransaction2.crear(agriSucreAuditoria2);
						//fin de auditoria
					}catch(Exception e){
						e.printStackTrace();
					}
					if (cotizacionC.getId() != null) {//verificamos si esta registrado en base
						CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
						Estado estadoPendiente = estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion");
						Estado estadoRechazado = estadoDAO.buscarPorNombre("Rechazado", "Cotizacion");
						AgriCotizacionAprobacion cotAct = cDAO.buscarPorId(cotizacionC.getId());
						/***VALIDACIONES GLOBALES***/
						if (cotizacionC.getEstado().getId().equals(estadoPendiente.getId()))
							mensaje = mensaje + " la cotizaci�n ya fue aprobada anteriormente,";
						if (cotizacionC.getEstado().getId().equals(estadoRechazado.getId()))
							mensaje = mensaje + " la cotizaci�n ya fue rechazada anteriormente,";
						
						
						estadoPendiente = estadoDAO.buscarPorNombre("Pendiente Aprobar", "Cotizacion");
						if (!cotizacionC.getEstado().getId().equals(estadoPendiente.getId())){
							estadoPendiente = estadoDAO.buscarPorId(cotizacionC.getEstado().getId());
							mensaje = mensaje + " la cotizaci�n se encuentra en estado "+estadoPendiente.getNombre()+",";
							}
						if (cotAct.getTipoCultivoId()==null)
							mensaje = mensaje + " no existe tipo de cultivo,";
						if (cotAct.getSumaAseguradaTotal()==0 || (Float)cotAct.getSumaAseguradaTotal()==null)
							mensaje = mensaje + " la suma asegurada no puede ser (0),";
						if (cotAct.getPrimaNetaTotal()==0 || (Float)cotAct.getPrimaNetaTotal()==null)
							mensaje = mensaje + " la prima neta total no puede ser (0),";
						if (cotAct.getTasa()==0 || (Float)cotAct.getTasa()== null)
							mensaje = mensaje + " la tasa no puede ser (0),";
						if (cotAct.getSeguroBancos()==0 || (Float)cotAct.getSeguroBancos()==null)
							mensaje = mensaje + " el impuesto super bancos no puede ser (0),";
						if (cotAct.getSeguroCampesino()==0 || (Float)cotAct.getSeguroCampesino()==null)
							mensaje = mensaje + " el impuesto seguro campesino no puede ser (0),";
						if (cotAct.getTotalFactura()==0 || (Float)cotAct.getTotalFactura()==null)
							mensaje = mensaje + " el total factura no puede ser (0),";
						
						if (mensaje.equals("")) {//Si todo esta correcto
							/**BUSCAMOS EN TABLA DE CONFIGURACION EL TIPO DE NOTIFICACION**/
							AgriParametroXPuntoVentaDAO canalNotificacionDAO = new AgriParametroXPuntoVentaDAO();
							AgriParametroXPuntoVenta canalNotificacion = canalNotificacionDAO.buscarPorPuntoVentaId(new BigInteger (cotizacionC.getPuntoVenta().getId()));
							///Forma de notificacion canal
							try{
								//Auditoria Inicia el proceso de cotizacion:
								//para saber a que hora se hizo la notificacion
								AgriSucreAuditoria agriSucreAuditoria3 = new AgriSucreAuditoria();
								AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction3 = new AgriSucreAuditoriaTransaction();
								agriSucreAuditoria3.setCanal(cotizacionC.getPuntoVenta().getNombre());
								agriSucreAuditoria3.setEstado("INI_NOTIF");
								agriSucreAuditoria3.setFecha(fechaActual);
								agriSucreAuditoria3.setObjeto("Ingresa al proceso de notificacion");
								agriSucreAuditoria3.setTramite(cotizacionC.getNumeroTramite());
								agriSucreAuditoriaTransaction3.crear(agriSucreAuditoria3);
							}catch(Exception e){
								e.printStackTrace();
							}//fin de auditoria
							
							/****NOTIFICACIONES AL CANAL****/
							/// 1) Ninguno, 2 )Web Service(Sucre) 3) mail (offline)
							if (canalNotificacion.getFormaNotificacion() == 2) {
								if (cotizacionC.getNumeroTramite() != null) {//si tiene un numero de tramite
									AgriSucreRespuesta sucre = new AgriSucreRespuesta();
									String suma=""+cotAct.getSumaAseguradaTotal();
									double sumaA=Double.parseDouble(suma);
									SdtEstado datos = ObjetoSucre(cotAct,sumaA,proceso);
									PQBE_wsACTUALIZAESTADOResponse resultado = sucre.RespuestaSucre(cotizacionC.getNumeroTramite(), datos);
									if (resultado != null) {
										try{
											//TODO: Cargar el log de respuesta de sucre una vez que recibo el resultado
											auditoria = new AgriSucreAuditoria();
											auditoria.setTramite(cotizacionC.getNumeroTramite());
											String h=""+resultado.getRespuesta().isHuboErrores();
											DatosRecibidos= "Recibido - Observaciones"+resultado.getRespuesta().getObservaciones()+h;
											auditoria.setObjeto(DatosRecibidos);
											auditoria.setFecha(VigenciaDesde);
											auditoria.setCanal("SUCRE");
											auditoria.setEstado(proceso);
											procesoAuditoria.crear(auditoria);
										}catch(Exception e){
											e.printStackTrace();
										}
										/**SI LA RESPUESTA DEL SERVICIO ES OK TODO ESTA CORRECTO**/
										if (resultado.getRespuesta().getObservaciones().toUpperCase().equals("OK")) {
											DatosRecibidos = DatosRecibidos+ " "+resultado.getRespuesta().getObservaciones().toUpperCase();
											
											if(proceso.equals("APROBADO")){
												cotizacionC.setEstado(estadoDAO.buscarPorNombre("Pendiente de Emitir","Cotizacion"));
											}else{
												cotizacionC.setEstado(estadoDAO.buscarPorNombre("Rechazado","Cotizacion"));
											}
											
											cotizacionC = cotizacionTransaction.editar(cotizacionC);
											// /TODO: Auditoria de aprobaciones y rechazos											
											try{
												String IdUsuario="0";
												if(usuario!=null)
													IdUsuario=usuario.getId();
												
												if(proceso.equals("APROBADO")){
													RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"APROBADO");
													//registro el detalle de la cotizacion
													try{
														AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
														agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
													}catch(Exception e){
														e.printStackTrace();
													}
												}else{
													RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"RECHAZADO");
													//registro el detalle de la cotizacion
													try{
														AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
														agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"RECHAZADO");
													}catch(Exception e){
														e.printStackTrace();
													}
												}
												
											}catch(Exception e){
												e.printStackTrace();
											}
											mensaje = "La Cotizaci�n ha sido "+proceso+" correctamente";
										} else {
											String mensajeEstado = resultado.getRespuesta().getObservaciones();
											String erroresCadena="";
											SdtResponseQBEErroresItem[] error = resultado.getRespuesta().getErrores();
											for (SdtResponseQBEErroresItem item : error){
												String[] erroresU= item.getError().getErrorB();
												for (String er : erroresU)
												erroresCadena = erroresCadena+ er+" ";
											}
											DatosRecibidos = DatosRecibidos+ mensajeEstado+" "+erroresCadena;
											mensaje = "No se ha podido "+proceso+" " + mensajeEstado+" "+erroresCadena+ " ";
										}
									} else {
										 String ms = "No se obtuvo respuesta de confirmaci�n de Sucre.";
										 DatosRecibidos = DatosRecibidos+ ms;
										 mensaje = ms+" Por favor vuelva a intentar m�s tarde.";
									}
									try{
										//TODO: Cargar el log de respuesta de sucre para probacion o rechazo
										auditoria = new AgriSucreAuditoria();
										auditoria.setTramite(cotizacionC.getNumeroTramite());
										auditoria.setObjeto(DatosRecibidos);
										auditoria.setFecha(VigenciaDesde);
										auditoria.setCanal("SUCRE");
										auditoria.setEstado(proceso);
										procesoAuditoria.crear(auditoria);
									}catch(Exception e){
										e.printStackTrace();
									}
								} else {
									mensaje = "No se ha podido "+proceso+" La cotizaci�n no tiene n�mero de tramite. ";
								}
							}else {
								///Actualiza estado tabla cotizacion
								Estado nuevoAprodo = new Estado();
								EstadoDAO estadoDAo = new EstadoDAO();
								
								nuevoAprodo=estadoDAo.buscarPorNombre(estado,"Cotizacion");
								cotizacionC.setEstado(nuevoAprodo);
								cotizacionC = cotizacionTransaction.editar(cotizacionC);
								// /TODO: Auditoria de aprobaciones y rechazos
								
								try{
									String IdUsuario="0";
									if(usuario!=null)
										IdUsuario=usuario.getId();
									RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()), new BigInteger(IdUsuario), proceso);									
								}catch(Exception e){
									e.printStackTrace();
								}
								try{
									//registro el detalle de la cotizacion
									AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
									agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,proceso);
								}catch(Exception e){
									e.printStackTrace();
								}
								mensaje = "La cotizaci�n ha sido "+proceso+" correctamente.";
							}
						}
						else 
							mensaje = "La cotizaci�n no se ha podido "+proceso+": "+mensaje.substring(0,mensaje.length()-1);
						
						detalleProceso.put("cotizacion", cotizacionC.getId());
						detalleProceso.put("detalle", mensaje);
						detalleProcesoList.add(detalleProceso);
						/**SOLO OFFLINE***/
						// Enviar mail aprobacion pre- poliza si viene del offline
						if (cotAct.getTipoOrigen()!=null && cotAct.getTipoOrigen().equals("COTIZADOR_OFFLINE")) {							
							try{
								EnviarMailCambioEstadoBlob(cotizacionC, cotAct,"AP");	
							}catch(Exception e){
								e.printStackTrace();
								throw new Exception("Cotizacion "+proceso+" pero no se puedo enviar Mail");								
							}
						}
					} else {
						mensaje = ("No se pudo "+proceso+" la cotizaci�n, la cotizaci�n no se encuentra registrada.");						
					}
				} catch (Exception e) {
					detalleProceso.put("cotizacion", nuevaCotizacion.getId());
					detalleProceso.put("detalle", mensaje+"Se ha producido un error: "+e.getMessage());
					detalleProcesoList.add(detalleProceso);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return detalleProcesoList;
		}
	}

	public JSONArray procesarEmision(List<Cotizacion> listado,Usuario usuario) {
		JSONObject detalleProceso = new JSONObject();
		JSONArray detalleProcesoList = new JSONArray();
		String mensaje = "";	String estado="";
		String cadenaArchivo = "";
		Date fecha = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat fHora = new SimpleDateFormat("HH:mm:ss");
		String nombreArchivo="ArchivoIDsFacturas_"+sm.format(fecha).replace("/", "")+
				fHora.format(fecha).replace(":", "")+".csv";
		
		try {
			for (Cotizacion nuevaCotizacion : listado) {
				Cotizacion cotizacionC = new Cotizacion();
				CotizacionDAO cotizacionCDAO = new CotizacionDAO();
				mensaje = "";
				try {
					if (nuevaCotizacion.getId() != null&& !nuevaCotizacion.getId().equals(""))
						cotizacionC = cotizacionCDAO.buscarPorId(nuevaCotizacion.getId());
					if (cotizacionC.getId() != null) {
						EstadoDAO estadoDAO = new EstadoDAO();
						Estado estadoPendiente = estadoDAO.buscarPorNombre("Emitido", "Cotizacion");
						CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
						AgriCotizacionAprobacion cotAct = cDAO.buscarPorId(cotizacionC.getId());
						if (cotAct.getEstadoId().equals(estadoPendiente.getId()))
							mensaje = mensaje + " la cotizaci�n ya fue emitida anteriormente,";
						estadoPendiente = estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion");
						if (!cotizacionC.getEstado().getId().equals(estadoPendiente.getId())){
							estadoPendiente = estadoDAO.buscarPorId(cotizacionC.getEstado().getId());
							mensaje = mensaje + " la cotizaci�n se encuentra en estado "+estadoPendiente.getNombre()+",";
						}
						if (cotizacionC.getAsegurado()==null)
							mensaje = mensaje + " la cotizacion no tiene  asegurado,";
						///Validar direcciones de la entidad cliente 
						ClienteDAO cliDAO = new ClienteDAO();
						Cliente cli = cliDAO.buscarPorId(cotizacionC.getClienteId().toString());
						EntidadDAO entyDAO = new EntidadDAO();
						Entidad enty = entyDAO.buscarPorId(cli.getEntidad().getId());
						if (cotAct.getDireccionSiembra()==null || cotAct.getDireccionSiembra().equals(""))
							mensaje = mensaje + " la cotizacion no tiene  direcci�n,";
						if ((enty.getTelefono()==null || enty.getTelefono().equals("")))
							if (enty.getCelular()==null || enty.getCelular().equals(""))
								mensaje = mensaje + " la cotizacion no tiene  tel�fono o celular,";					
						if (mensaje.equals("")) {
							// TODO:LLamar al metodo para emision
							AgriResultadoEmision resultado = AgriEmisionPoliza.emitirPoliza(cotizacionC.getId());

							if (resultado.isEmitidoCorrectamente()) {
								cotizacionC = cotizacionCDAO.buscarPorId(cotizacionC.getId());
								String row = cotizacionC.getNumeroFactura()+ " , " + cotizacionC.getTotalFactura()+ "\r\n";
								cadenaArchivo = cadenaArchivo + row;
								try{
									//registro el detalle de la cotizacion
									AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
									agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
								}catch(Exception e){
									e.printStackTrace();
								}
								mensaje = "La cotizaci�n ha sido emitida correctamente. ";
								estado="OK";
							} else {
								mensaje = ("La cotizaci�n no se ha podido emitir. " + resultado.getMensaje());
								estado="Error";
							}
						} else{
							mensaje = "La cotizaci�n no se ha podido emitir: "+ mensaje.substring(0, mensaje.length() - 1);
							estado="Error";
						}
						detalleProceso.put("cotizacion", cotizacionC.getId());
						detalleProceso.put("detalle", mensaje);
						detalleProceso.put("estado", estado);
						detalleProcesoList.add(detalleProceso);
					} else {
						mensaje = ("No se pudo emitir la cotizaci�n, la cotizaci�n no se encuentra registrada.");
						estado="Error";
						throw new Exception();
					}
				} catch (Exception e) {
					mensaje = "No se pudo emitir la cotizaci�n, se ha producido un error. "+e.getMessage();
					estado="Error";
					detalleProceso.put("cotizacion", nuevaCotizacion.getId());
					detalleProceso.put("detalle", mensaje);
					detalleProceso.put("estado", estado);
					detalleProcesoList.add(detalleProceso);
				}
			}
			
			//JSONObject detalleFile = new JSONObject();
			if (cadenaArchivo.length() > 0) {
				
				// Grabar en base el archivo de ids generado
				AgriArchivosCotizacion fileCotizacion = new AgriArchivosCotizacion();
				AgriArchivosCotizacionTransaction fileCotizacionTransaction = new AgriArchivosCotizacionTransaction();
				fileCotizacion.setFechaGeneracion(fecha);
				fileCotizacion.setFile(cadenaArchivo.getBytes("UTF-8"));
				fileCotizacion.setNombreArchivo(nombreArchivo);
				String IdUsuario="0";
				if(usuario!=null)
					IdUsuario=usuario.getId();
				fileCotizacion.setUsuarioId(new BigInteger(IdUsuario));
				
				fileCotizacion = fileCotizacionTransaction.crear(fileCotizacion);
				if (fileCotizacion.getId()!=null)
					IdFileIds = fileCotizacion.getId().toString();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return detalleProcesoList;
		}
	}
	
	public JSONArray procesarRevocacion(List<Cotizacion> listado,Usuario usuario) {
		JSONObject detalleProceso = new JSONObject();
		JSONArray detalleProcesoList = new JSONArray();
		String mensaje = "";
		String estado="";
		
		try {
			for (Cotizacion nuevaCotizacion : listado) {
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				Cotizacion cotizacionC = new Cotizacion();
				CotizacionDAO cotizacionCDAO = new CotizacionDAO();
				mensaje = "";
				try {
					if (nuevaCotizacion.getId() != null&& !nuevaCotizacion.getId().equals(""))
						cotizacionC = cotizacionCDAO.buscarPorId(nuevaCotizacion.getId());
					if (cotizacionC.getId() != null) {
						cotizacionC = cotizacionCDAO.buscarPorId(cotizacionC.getId());
						// Cambiar estados
						EstadoDAO estadoDAO = new EstadoDAO();
						cotizacionC.setEstado(estadoDAO.buscarPorNombre("Revocado","Cotizacion"));
						cotizacionC.setNumeroTramite(null);
						// Actualiza estado tabla cotizacion
						cotizacionC = cotizacionTransaction.editar(cotizacionC);
						// /TODO: Auditoria de aprobaciones y rechazos
						
						try{
							String IdUsuario="0";
							if(usuario!=null)
								IdUsuario=usuario.getId();
							RegistrarAuditoriaCotizacion(new BigInteger(cotizacionC.getId()),new BigInteger(IdUsuario),"REVOCADO REVISION");
						}catch(Exception e){
							e.printStackTrace();
						}
						try{
							//registro el detalle de la cotizacion
							AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacionC,"Aprobacion");
						}catch(Exception e){
							e.printStackTrace();
						}
						mensaje = "La cotizaci�n ha sido revocada. ";
						estado="OK";
						
						detalleProceso.put("cotizacion", cotizacionC.getId());
						detalleProceso.put("detalle", mensaje);
						detalleProceso.put("estado", estado);
						detalleProcesoList.add(detalleProceso);
					} else {
						mensaje = ("No se pudo revocar la cotizaci�n, la cotizaci�n no se encuentra registrada.");
						estado="Error";
						throw new Exception();
					}
				} catch (Exception e) {
					mensaje = "No se pudo revocar la cotizaci�n, se ha producido un error. "+e.getMessage();
					estado="Error";
					detalleProceso.put("cotizacion", nuevaCotizacion.getId());
					detalleProceso.put("detalle", mensaje);
					detalleProceso.put("estado", estado);
					detalleProcesoList.add(detalleProceso);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return detalleProcesoList;
		}
	}

	private void EnviarMailCambioEstado(Cotizacion cotizacion,
			AgriCotizacionAprobacion detalleCotizacion, String status) {
		String cadenaCotizaciones = "";
		byte[] data = null;
		cadenaCotizaciones = "[" + cotizacion.getId() + "]";
		AgriParametroXPuntoVentaDAO puntoVentaDAO = new AgriParametroXPuntoVentaDAO();
		AgriParametroXPuntoVenta puntoVenta = puntoVentaDAO
				.buscarPorPuntoVentaId(new BigInteger(cotizacion
						.getPuntoVenta().getId()));
		String correo = "";
		String asunto = "QBE Seguros Colonial: Detalle de Aprobaci�n Cotizaci�n";
		String detalle = "";
		String estado = "";
		String Usuario_ = "";
		if (puntoVenta.getEmailPuntoVenta() != null) {
			correo = puntoVenta.getEmailPuntoVenta();
			if (detalleCotizacion != null) {
				data = GenerarReporte(cotizacion, detalleCotizacion);
				Usuario_ = detalleCotizacion.getPuntoVenta();
			} else {
				PuntoVentaDAO uDAO = new PuntoVentaDAO();
				EntidadDAO eDAO = new EntidadDAO();
				PuntoVenta canal = uDAO.buscarPorId(cotizacion.getPuntoVenta().getId().toString());
				//Entidad entidad = eDAO.buscarPorId(canal.getNombre());
				Usuario_ = canal.getNombre();
			}
		}
		if (status.equals("AP")) {
			estado = "aprobadas";
			detalle = "A continuaci�n se adjunta el documento de pre-poliza.";
		} else {
			estado = "rechazadas";
			detalle = "";
		}
		if (data != null) {
			ResultAdjuntos adjunto = new ResultAdjuntos();
			adjunto.setDataAdjunto(data);
			adjunto.setNombreAdjunto("CotizacionAgricola_"
					+ detalleCotizacion.getId());
			adjunto.setCotizacionID_(detalleCotizacion.getId().toString());
			EnvioMailPrePoliza(correo, adjunto, asunto, Usuario_,
					cadenaCotizaciones, estado, detalle);
		} else
			EnvioMailPrePoliza(correo, null, asunto, Usuario_,
					cadenaCotizaciones, estado, detalle);

	}

	private byte[] GenerarReporte(Cotizacion cotizacion,
			AgriCotizacionAprobacion detalleCotizacion) {
		AgriCotizacionReporte cotizacionReporte = new AgriCotizacionReporte();
		String html = cotizacionReporte.generarHtmlCondicionesParticulares(
				detalleCotizacion);
		byte[] data = cotizacionReporte.GenerarPDFCotizacion(html);
		return data;
	}


	private String GenerarContenido(String html,
			java.util.Hashtable<String, String> ParamValues) {
		List<String> detectedParams = new ArrayList<String>();
		// Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\._]*\\]");
		Matcher mat = params.matcher(html);
		while (mat.find()) {
			detectedParams.add(mat.group());
		}

		for (String detectedParam : detectedParams) {
			String valor = ParamValues.get(detectedParam.replace("[", "")
					.replace("]", ""));
			html = html.replace(detectedParam, valor);
		}
		return html;
	}

	public void EnvioMailPrePoliza(String correos, ResultAdjuntos adjunto,
			String asunto, String Usuario, String cadenaCotizaciones,
			String estado, String detalle) {
		// String[] correosArr = correos.split(";");
		// String rutaPlantilla = request.getServletContext().getRealPath("")+
		// plantillaRuta;
		String html = "";
		try {
			String rutaPlantilla = this.getServletContext().getRealPath("")
					+ "/static/plantillas/mailAprobacionCotizacion.html";
			//html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			File CurrentStructureFile = new File(rutaPlantilla);
			Path CurrentStructurePath = FileSystems.getDefault().getPath(CurrentStructureFile.getPath());
			html = new String(Files.readAllBytes(CurrentStructurePath));
		} catch (IOException ex) {
		}
		
		// parametros html
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		parametersHeader.put("USUARIO", Usuario);
		parametersHeader.put("COTIZACIONES", cadenaCotizaciones);
		parametersHeader.put("ESTADO", estado);
		parametersHeader.put("DETALLE", detalle);
		String cuerpoMail = "";
		cuerpoMail = GenerarContenido(html, parametersHeader);
		if (adjunto != null)
			Utilitarios.envioMailPDFVariosAdjunto(correos, asunto, cuerpoMail,
					adjunto);
		else
			Utilitarios.envioMailPDFVariosAdjunto(correos, asunto, cuerpoMail,
					null);
	}

	public AgriAuditoriaCotizacion RegistrarAuditoriaCotizacion(
			BigInteger cotizacionId, BigInteger usuario, String actividad) {
		// /Grabar en la tabla auditoria
		Date fecha = new Date();
		AgriAuditoriaCotizacion agriAuditoria = new AgriAuditoriaCotizacion();
		agriAuditoria.setCotizacionId(cotizacionId);
		agriAuditoria.setFecha(fecha);
		agriAuditoria.setUsuarioId(usuario);
		agriAuditoria.setTipoActividad(actividad);
		AgriAuditoriaCotizacionTransaction agriAuditoriaTranasaction = new AgriAuditoriaCotizacionTransaction();
		// Grabar en auditoria
		agriAuditoria = agriAuditoriaTranasaction.crear(agriAuditoria);
		return agriAuditoria;
	}
	
	public void SaveFileFolderLocal (HttpServletRequest request){
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
					try {
						//cambiar 
						File saveFile = new File("../eclipseApps/Cotizador/static/CotizacionesOffline",fileName);
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
	
	public SdtEstado ObjetoSucre(AgriCotizacionAprobacion cotAct,double SumaAseguradaTotal,String estado){
		Calendar fechaAprobacion = Calendar.getInstance();
		SdtEstado datos = new SdtEstado();
		datos.setAutorizacion(Integer.parseInt(cotAct.getId().toString()));
		//TODO: Si existe diferencia de valores en los montos
		datos.setDiferenciaMonto( "NO");
		
		//generamos la consulta para enviar el comentario de la aprobacion o rechazo
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		
		AgriObjetoCotizacion agriObjetoCotizacion = new AgriObjetoCotizacion();
		AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
		
		cotizacion= cotizacionDAO.buscarPorNumeroTramite(cotAct.getNumeroTramite().trim());
		cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
		agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
		
		datos.setEstado(estado);
		//TODO: se envia el valor de la suma asegurada con calculo de valores si existen
		// y el monto sin variar 
		datos.setMontoAprobadoQBE(SumaAseguradaTotal);
		datos.setMontoRecomendadoQBE(SumaAseguradaTotal);
		datos.setNumeroCotizacion(cotAct.getId().toString());
		
		if(agriObjetoCotizacion.getObservacionCotizacion()==null||agriObjetoCotizacion.getObservacionCotizacion().trim().equals("")){
			if(estado.equals("APROBADO"))
				datos.setObservacion("OK");
			else
				datos.setObservacion("RECHAZADO por no cumplir con las condiciones optimas para la poliza.");
			
		}else{
			datos.setObservacion(agriObjetoCotizacion.getObservacionCotizacion());
		}
		
		datos.setFechaAprobacion(fechaAprobacion);
		String Prima=""+cotAct.getPrimaNetaTotal();
		datos.setPrima(Double.parseDouble(Prima));
		return datos;
	}
		
	private void EnviarMailCambioEstadoBlob(Cotizacion cotizacion,
			AgriCotizacionAprobacion detalleCotizacion, String status) {
		
		ObjetosRespuesta objetosRespuesta = new  ObjetosRespuesta();
		if(cotizacion.getPuntoVenta().getNombre().equals("CREDIFE"))
			objetosRespuesta.setBeneficiario("BANCO DEL PICHINCHA");
		else
			objetosRespuesta.setBeneficiario("COOPROGRESO");
		objetosRespuesta.setComentario("Correcto");
		objetosRespuesta.setCotizacionID(""+cotizacion.getId());
		objetosRespuesta.setEstado(Integer.parseInt(cotizacion.getEstado().getId()));
		
		ObjetosRespuesta[] objetoRespuesta1 = new ObjetosRespuesta[1];
		objetoRespuesta1[0]=objetosRespuesta;
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
		//tomamos y  guardamos los correos en el campo correos de agriObjetoCotizacion
		AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
		AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
		String Correos= agriObjetoCotizacion.getCorreos();
		String[] ArregloCorreos=Correos.split(",");
		String AReemplazar=ArregloCorreos[0]+",";
		Correos=Correos.replace(AReemplazar+",", " ");
		ImportarCotizaciones importarCotizaciones = new ImportarCotizaciones();
		importarCotizaciones.enviarMailCambioEstado(objetoRespuesta1,ArregloCorreos[0], Correos);
		
	}

}