package com.qbe.cotizador.servlets.producto.pymes;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity;
import org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA;
import org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket;
import org.tempuri.EngineProxy;

import java.io.ByteArrayOutputStream;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionRespuestaDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.FirmasSucursalDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.TipoInspectorDAO;
import com.qbe.cotizador.dao.entidad.TipoPredioDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.pagos.PagoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDeducibleCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDistribucionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeEndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeExtRamoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeFechaCierreDAO;
import com.qbe.cotizador.dao.producto.pymes.NotificacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeRamoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTextoCoberturaCotizacionDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.CotizacionRespuesta;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.FirmasSucursal;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Pago;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCoberturaCotizacionValor;
import com.qbe.cotizador.model.PymeDeducibleCotizacionValor;
import com.qbe.cotizador.model.PymeDistribucionCobertura;
import com.qbe.cotizador.model.PymeEndosoBeneficiario;
import com.qbe.cotizador.model.PymeExtRamo;
import com.qbe.cotizador.model.PymeFechaCierre;
import com.qbe.cotizador.model.Notificacion;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacionCobertura;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;
import com.qbe.cotizador.model.PymeRamoCotizacion;
import com.qbe.cotizador.model.PymeTextoGrupoCoberturaCotizacion;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.SolicitudEmision;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoInspector;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoPredio;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servicios.QBE.emisionPymesWS.ServicioIntegracionProxy;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionRespuestaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.SolicitudEmisionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeObjetoCotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeObjetoCotizacionTransaction;
import com.qbe.cotizador.util.Reportes;
import com.qbe.cotizador.util.Utilitarios;
import com.tandi.servicios.DTOs.BlanketDTO;
import com.tandi.servicios.DTOs.ClienteDTO;
import com.tandi.servicios.DTOs.CoberturaDTO;
import com.tandi.servicios.DTOs.ConfiguracionPagoDTO;
import com.tandi.servicios.DTOs.DeducibleDTO;
import com.tandi.servicios.DTOs.DireccionDTO;
import com.tandi.servicios.DTOs.EndosoDTO;
import com.tandi.servicios.DTOs.FacturaDTO;
import com.tandi.servicios.DTOs.ItemDTO;
import com.tandi.servicios.DTOs.PagoDTO;
import com.tandi.servicios.DTOs.PolizaDTO;
import com.tandi.servicios.DTOs.PredioDTO;
import com.tandi.servicios.DTOs.ProductoDTO;
import com.tandi.servicios.DTOs.RequestDTO;
import com.tandi.servicios.DTOs.RespuestaDTO;

/**
 * Servlet implementation class CotizacionController
 */
@WebServlet("/PymeCotizacionController")
public class PymeCotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymeCotizacionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**o
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");


			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();

			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction(); 
			EntidadTransaction entidadTransaction = new EntidadTransaction();
			ClienteTransaction clienteTransaction = new ClienteTransaction();

		//  Obtenemos el IVA segun el Punto de Venta
			if(tipoConsulta.equalsIgnoreCase("ivaSegunPuntoVenta")){				
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();									
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				double respuesta [] = {0,0};
				double valorIVA = 0.0;
				double compensacion =2.0;
				double valorCompensacion =0.0;
				double valorIVATotal = 0.0;
				
				if(cotizacion.getId() != null){
					respuesta = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);				
					double valorSubtotal= Double.parseDouble(cotizacion.getPrimaNetaTotal())+cotizacion.getImpSuperBancos()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpRecargoSeguroCampesino()+cotizacion.getImpDerechoEmision();
					DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				    Date fechaNuevoIVA = (Date) formatoFecha.parse("01/06/2016");				   					
					if(cotizacion.getFechaElaboracion().before(new Timestamp(fechaNuevoIVA.getTime()))){
						valorIVATotal = cotizacion.getImpIva();
					}else{
						if(respuesta[0] == 12.00){						
							valorCompensacion = (valorSubtotal*compensacion)/100;
							valorIVATotal = (cotizacion.getImpIva()*12)/14; 
						}else{
							valorIVATotal = cotizacion.getImpIva();
						}	
					}					
				}								
				      
				valorCompensacion = Math.round(valorCompensacion*100.0)/100.0;
				valorIVATotal = Math.round(valorIVATotal*100.0)/100.0;
				
				result.put("iva", respuesta[0]);
				result.put("compensacion", compensacion);
				result.put("valor_compensacion", valorCompensacion);
				result.put("valor_iva", valorIVATotal);
			}	
			
			//Modifico el estado de la cotización del producto ganadero.
			if(tipoConsulta.equalsIgnoreCase("cambiarEstado"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String estadoNombre = request.getParameter("estadoNombre") == null ? "" : request.getParameter("estadoNombre").trim();

				if(cotizacionId!=null&&!cotizacionId.equals(""))
					cotizacion=cotizacionDAO.buscarPorId(cotizacionId);

				EstadoDAO estadoDAO=new EstadoDAO();

				cotizacion.setEstado(estadoDAO.buscarPorNombre(estadoNombre,"Cotizacion"));
				/*if(estadoNombre.equals("Revision Aprobada"))
				{
					//Obtengo los parametros en base al canal
					ParametroXPuntoVentaDAO parametroDAO=new ParametroXPuntoVentaDAO();
					ParametroXPuntoVenta parametroCanal=parametroDAO.obtenerPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
					if(parametroCanal.getTipoCanal().equals("REASEGURADOR"))
					{
						if(parametroCanal.getEmisionDirecta()==1)
						{
							//Llamo a la emisión de reaseguros
							cotizacion.setEstado(estadoDAO.buscarPorNombre("Emitido","Cotizacion"));
						}
					}
				}*/
				cotizacion.setEtapaWizard(3);
				cotizacion = cotizacionTransaction.editar(cotizacion);
				
				if(estadoNombre.equals("Pendiente Asignar Inspector")){
					VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
					VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");

					ClienteDAO clienteProcesos = new ClienteDAO();
					Cliente cliente = clienteProcesos.buscarPorId(""+cotizacion.getClienteId());
					Entidad usuarioActual=cotizacion.getUsuario().getEntidad();
					
					CotizacionDetalle nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacion.getCotizacionDetalles().get(0).getId());
					
					int pymes=Integer.parseInt(nuevoCotizacionDetalle.getObjetoId());
					BigInteger pymesId = new BigInteger(Integer.toString(pymes));
					PymeObjetoCotizacionDAO pymesProcesos= new PymeObjetoCotizacionDAO();
					PymeObjetoCotizacion pymesCotizacion = pymesProcesos.buscarPorId(pymesId);
					
					CantonDAO cantonDAO= new CantonDAO();
					String cantonID=""+pymesCotizacion.getCiudadId();
					Canton canton=cantonDAO.buscarPorId(cantonID);
					String CPrincipal= pymesCotizacion.getCallePrincipal();
					String CSecundaria= pymesCotizacion.getCalleSecundaria();
					String Ubicacion=CPrincipal + " y " + CSecundaria;
					String nombreCliente=cliente.getEntidad().getNombreCompleto();
					
					java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
					parametersHeader.put("urlImagenes", variableSistema.getValor());
					parametersHeader.put("CotizacionId", cotizacion.getId());
					//parametersHeader.put("DireccionUbicacion", canton.getNombre());
					//parametersHeader.put("Direccion", Ubicacion);
					parametersHeader.put("ClienteNombre", nombreCliente);
					//parametersHeader.put("ClienteIdentificacion", cliente.getEntidad().getIdentificacion());
					//parametersHeader.put("ClienteTelefono", cliente.getEntidad().getTelefono());
					//parametersHeader.put("ClienteEmail", cliente.getEntidad().getMail());
					parametersHeader.put("UsuarioNombre", usuarioActual.getNombreCompleto());
					parametersHeader.put("UsuarioEmail", usuarioActual.getMail());
					
					PymeObjetoCotizacionDAO objetoPymesDAO=new PymeObjetoCotizacionDAO();
					ProvinciaDAO provinciaDAO=new ProvinciaDAO();
					String datosBien="";
					int contador=1;
					for(CotizacionDetalle detalle:cotizacion.getCotizacionDetalles()){
						PymeObjetoCotizacion detalleObjeto=objetoPymesDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
						if(detalleObjeto!=null){
							Provincia provincia=provinciaDAO.buscarPorId(""+detalleObjeto.getProvinciaId());
							datosBien+="<tr><td>"+contador+"</td><td>"+provincia.getNombre()+"</td><td>"+detalleObjeto.getCallePrincipal()+" y " +detalleObjeto.getCalleSecundaria()+"</td></tr>";
							contador++;
						}
					}
					parametersHeader.put("DatosBienes", datosBien);
					
					//Obtengo las configuraciones de las notificaciones
					NotificacionDAO notificacionDAO=new NotificacionDAO();
					Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_COTIZACION_ACEPTADA");
					if(notificacion.getNotificacionId()!=null){
						
						StringBuilder mailReceptor = new StringBuilder();
						String mailEjecutivo="";
						if(notificacion.isNotificacionCliente())
							if(!cliente.getEntidad().getMail().equals(""))
							{
								mailReceptor.append(cliente.getEntidad().getMail());
								mailReceptor.append(",");
							}
						if(notificacion.isNotificacionUsuario())
							if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
							{
								mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
								mailReceptor.append(",");
							}
						if(notificacion.isNotificacionEjecutivo())
						{
							//proceso para obtener de la nueva tabla
							PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
							EntidadDAO entidadDA=new EntidadDAO();
							PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
							if(pppv.getParametroPuntoventaId()!=null){
								Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
								if(!entidad.getMail().equals(""))
								{
									mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
									mailReceptor.append(",");
								}
							}
						}
						if(!notificacion.getEmailInspector().equals("")){
							mailEjecutivo = notificacion.getEmailInspector();
						}

						
						if(!mailReceptor.toString().equals("")){
							MailGenericoPlantillas.EnvioPlantillaGenerica(mailReceptor.substring(0,mailReceptor.length()-1), parametersHeader, 
														"/static/plantillas/pymes/"+notificacion.getPlantillaNombre(), request, mailEjecutivo);
						}
					}
				}

				result.put("cotizacionId",cotizacion.getId());
			}
			
			if(tipoConsulta.equalsIgnoreCase("procesarCambioEstado"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String estadoFinalId = request.getParameter("estadoFinalId") == null ? "" : request.getParameter("estadoFinalId").trim();

				if(cotizacionId!=null&&!cotizacionId.equals(""))
					cotizacion=cotizacionDAO.buscarPorId(cotizacionId);

				if(cotizacion.getId()!=null){
					EstadoDAO estadoDAO=new EstadoDAO();
					Estado estado=estadoDAO.buscarPorId(estadoFinalId);
	
					if(cotizacion.getEstado().getNombre().equals("Emitido")){
							
						/***TRATAMIENTO DE ERROR***/
						Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
						String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
						/***AUDITORIA auditamos el error para el seguimiento***/
						PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
						PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
						pymeAuditoriaGeneral.setTramite(CodError);
						pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
						pymeAuditoriaGeneral.setProceso("PYMES");
						pymeAuditoriaGeneral.setObjeto("La cotización ya fue emitida y ya no se puede cambiar de estado.");
						try {
							pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
						} catch (Exception e1) {				
							e1.printStackTrace();
						}
						/***RESPUESTA A INTERFAZ***/
						result.put("success", Boolean.FALSE);
						result.put("error", "La cotización ya fue emitida y ya no se puede cambiar de estado.");
						result.put("ex", "La cotización ya fue emitida y ya no se puede cambiar de estado.");
						response.setContentType("application/json; charset=ISO-8859-1"); 
						result.write(response.getWriter());						
						return;
					}
					if(estado.getNombre().equals("Emitido")){
						/***TRATAMIENTO DE ERROR***/
						Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
						String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
						/***AUDITORIA auditamos el error para el seguimiento***/
						PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
						PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
						pymeAuditoriaGeneral.setTramite(CodError);
						pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
						pymeAuditoriaGeneral.setProceso("PYMES");
						pymeAuditoriaGeneral.setObjeto("No se puede cambiar ha estado emitido.");
						try {
							pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
						} catch (Exception e1) {				
							e1.printStackTrace();
						}
						/***RESPUESTA A INTERFAZ***/
						result.put("success", Boolean.FALSE);
						result.put("error", "No se puede cambiar ha estado emitido.");
						result.put("ex", "No se puede cambiar ha estado emitido.");
						response.setContentType("application/json; charset=ISO-8859-1"); 
						result.write(response.getWriter());
						
						return;
					}
					cotizacion.setEstado(estado);
					cotizacion = cotizacionTransaction.editar(cotizacion);
					cotizacion.setEstado(estado);
 					cotizacion = cotizacionTransaction.editar(cotizacion);
					CotizacionDetalleDAO detalleDAO = new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle =detalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
					PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO = new PymeObjetoCotizacionDAO();
					PymeObjetoCotizacion pymeObjetoCotizacion = pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					pymeObjetoCotizacion.setEstadoInspeccion(0);
					PymeObjetoCotizacionTransaction pymeObjetoCotizacionTransaction = new PymeObjetoCotizacionTransaction();
					pymeObjetoCotizacionTransaction.editar(pymeObjetoCotizacion);
				}
				else{
					/***TRATAMIENTO DE ERROR***/
					Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
					String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
					/***AUDITORIA auditamos el error para el seguimiento***/
					PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
					PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
					pymeAuditoriaGeneral.setTramite(CodError);
					pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
					pymeAuditoriaGeneral.setProceso("PYMES");
					pymeAuditoriaGeneral.setObjeto("No existe una cotización con ese número.");
					try {
						pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
					} catch (Exception e1) {				
						e1.printStackTrace();
					}
					/***RESPUESTA A INTERFAZ***/
					result.put("success", Boolean.FALSE);
					result.put("error", "No existe una cotización con ese número.");
					result.put("ex", "No existe una cotización con ese número.");
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());					
					return;
				}
			}

			if(tipoConsulta.equalsIgnoreCase("encontrarLogCotizacion"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				Cotizacion cotizacionActual=cotizacionDAO.buscarPorId(cotizacionId);
				PymeCoberturaCotizacionValorDAO pymeCoberturaCotizacionValorDAO=new PymeCoberturaCotizacionValorDAO();
				PymeRamoCotizacionDAO pymeRamoCotizacionDAO=new PymeRamoCotizacionDAO();
				PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				
				List<PymeCoberturaCotizacionValor> lstPymeCoberturaCotizacionValor=pymeCoberturaCotizacionValorDAO.buscarPorCotizacionId(new BigInteger(cotizacionId));
				JSONObject coberturaValorJSONObject = new JSONObject();
				JSONArray cotizacionCoberturaValorJSONArray = new JSONArray();
				for(PymeCoberturaCotizacionValor coberturaActual:lstPymeCoberturaCotizacionValor){
					coberturaValorJSONObject.put("GrupoProductoId", coberturaActual.getGrupoProductoId());
					coberturaValorJSONObject.put("RamoId", coberturaActual.getRamoId());
					coberturaValorJSONObject.put("CoberturaEnsMultiId", coberturaActual.getCoberturaEnsMultiId());
					coberturaValorJSONObject.put("CoberturaEnsProgrId", coberturaActual.getCoberturaEnsProgrId());
					coberturaValorJSONObject.put("Nombre", coberturaActual.getNombre());
					coberturaValorJSONObject.put("TasaSugerida", coberturaActual.getTasaSugerida());
					coberturaValorJSONObject.put("TasaIngresada", coberturaActual.getTasaIngresada());
					coberturaValorJSONObject.put("ValorIngresado", coberturaActual.getValorIngresado());
					coberturaValorJSONObject.put("PrimaCalculada", coberturaActual.getPrimaCalculada());
					coberturaValorJSONObject.put("CotizacionId", coberturaActual.getCotizacionId());
					coberturaValorJSONObject.put("ObjetoPymesCoberturaId", coberturaActual.getObjetoPymesCoberturaId());
					coberturaValorJSONObject.put("CotizacionDetalleId", coberturaActual.getCotizacionDetalleId());
					coberturaValorJSONObject.put("CoberturaPymesId", coberturaActual.getCoberturaPymesId());
					coberturaValorJSONObject.put("TipoOrigen", coberturaActual.getTipoOrigen());
					coberturaValorJSONObject.put("ValorMaximoLimiteAsegurado", coberturaActual.getValorMaximoLimiteAsegurado());
					coberturaValorJSONObject.put("PorcentajeLimiteAsegurado", coberturaActual.getPorcentajeLimiteAsegurado());
					coberturaValorJSONObject.put("TipoCoberturaId", coberturaActual.getTipoCoberturaId());
					cotizacionCoberturaValorJSONArray.add(coberturaValorJSONObject);
				}
				result.put("listadoCotizacionCoberturaValor",  cotizacionCoberturaValorJSONArray);
				
				List<PymeRamoCotizacion> lstPymeRamoCotizacion=pymeRamoCotizacionDAO.buscarPorCotizacionId(new BigInteger(cotizacionId));
				JSONObject ramoCotizacionJSONObject = new JSONObject();
				JSONArray ramoCotizacionJSONArray = new JSONArray();
				for(PymeRamoCotizacion coberturaActual:lstPymeRamoCotizacion){
					ramoCotizacionJSONObject.put("RamoId", coberturaActual.getRamoId());
					ramoCotizacionJSONObject.put("CotizacionId", coberturaActual.getCotizacionId());
					ramoCotizacionJSONObject.put("PrimaCalculada", coberturaActual.getPrimaCalculada());
					ramoCotizacionJSONObject.put("SumaAsegurada", coberturaActual.getSumaAsegurada());
					ramoCotizacionJSONObject.put("NombreRamo", coberturaActual.getNombreRamo());
					ramoCotizacionJSONArray.add(ramoCotizacionJSONObject);
				}
				result.put("listadoRamosCotizacion",  ramoCotizacionJSONArray);
				
				List<CotizacionDetalle> detalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacionActual);
				PymeObjetoCotizacion pymeObjetoCotizacion=null;
				JSONArray cotizacionDetalleJSONArray = new JSONArray();
				for(CotizacionDetalle detalle: detalles){
					pymeObjetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
					JSONObject cotizacionDetalleJSONObject = new JSONObject();
					cotizacionDetalleJSONObject.put("ObjetoPymesId", pymeObjetoCotizacion.getObjetoPymesId());
					cotizacionDetalleJSONObject.put("CallePrincipal", pymeObjetoCotizacion.getCallePrincipal());
					cotizacionDetalleJSONObject.put("NumeroDireccion", pymeObjetoCotizacion.getNumeroDireccion());
					cotizacionDetalleJSONObject.put("CalleSecundaria", pymeObjetoCotizacion.getCalleSecundaria());
					cotizacionDetalleJSONObject.put("NombreEdificio", pymeObjetoCotizacion.getNombreEdificio());
					cotizacionDetalleJSONObject.put("NumeroPiso", pymeObjetoCotizacion.getNumeroPiso());
					cotizacionDetalleJSONObject.put("NumeroOficina", pymeObjetoCotizacion.getNumeroOficina());
					cotizacionDetalleJSONObject.put("Sector", pymeObjetoCotizacion.getSector());
					cotizacionDetalleJSONObject.put("ProvinciaId", pymeObjetoCotizacion.getProvinciaId());
					cotizacionDetalleJSONObject.put("CiudadId", pymeObjetoCotizacion.getCiudadId());
					cotizacionDetalleJSONObject.put("ActividadEconomicaId", pymeObjetoCotizacion.getActividadEconomicaId());
					cotizacionDetalleJSONObject.put("ContabilidadFormal", pymeObjetoCotizacion.getContabilidadFormal());
					cotizacionDetalleJSONObject.put("TieneMasDosAnio", pymeObjetoCotizacion.getTieneMasDosAnio());
					cotizacionDetalleJSONObject.put("TipoConstruccionId", pymeObjetoCotizacion.getTipoConstruccionId());
					cotizacionDetalleJSONObject.put("TipoOcupacionId", pymeObjetoCotizacion.getTipoOcupacionId());
					cotizacionDetalleJSONObject.put("AnioConstruccion", pymeObjetoCotizacion.getAnioConstruccion());
					cotizacionDetalleJSONObject.put("NumeroTotalPisos", pymeObjetoCotizacion.getNumeroTotalPisos());
					cotizacionDetalleJSONObject.put("Extintores", pymeObjetoCotizacion.getExtintores());
					cotizacionDetalleJSONObject.put("DetectorHumo", pymeObjetoCotizacion.getDetectorHumo());
					cotizacionDetalleJSONObject.put("Sprinklers", pymeObjetoCotizacion.getSprinklers());
					cotizacionDetalleJSONObject.put("AlarmaMonitoreada", pymeObjetoCotizacion.getAlarmaMonitoreada());
					cotizacionDetalleJSONObject.put("Guardiania", pymeObjetoCotizacion.getGuardiania());
					cotizacionDetalleJSONObject.put("Otros", pymeObjetoCotizacion.getOtros());
					cotizacionDetalleJSONObject.put("ValorEstructuras", pymeObjetoCotizacion.getValorEstructuras());
					cotizacionDetalleJSONObject.put("ValorMueblesEnseres", pymeObjetoCotizacion.getValorMueblesEnseres());
					cotizacionDetalleJSONObject.put("ValorMaquinaria", pymeObjetoCotizacion.getValorMaquinaria());
					cotizacionDetalleJSONObject.put("ValorMercaderia", pymeObjetoCotizacion.getValorMercaderia());
					cotizacionDetalleJSONObject.put("ValorInsumosNoelectronicos", pymeObjetoCotizacion.getValorInsumosNoelectronicos());
					cotizacionDetalleJSONObject.put("ValorEquipoHerramienta", pymeObjetoCotizacion.getValorEquipoHerramienta());
					cotizacionDetalleJSONObject.put("Latitud", pymeObjetoCotizacion.getLatitud());
					cotizacionDetalleJSONObject.put("Longuitud", pymeObjetoCotizacion.getLonguitud());
					cotizacionDetalleJSONObject.put("Informe", pymeObjetoCotizacion.getInforme());
					cotizacionDetalleJSONObject.put("EstadoInspeccion", pymeObjetoCotizacion.getEstadoInspeccion());
					cotizacionDetalleJSONObject.put("RequiereInspeccion", pymeObjetoCotizacion.getRequiereInspeccion());
					cotizacionDetalleJSONObject.put("TipoPredioId", pymeObjetoCotizacion.getTipoPredioId());
					cotizacionDetalleJSONObject.put("InspectorId", pymeObjetoCotizacion.getInspectorId());
					cotizacionDetalleJSONObject.put("PlanContenidosId", pymeObjetoCotizacion.getPlanContenidosId());
					cotizacionDetalleJSONObject.put("PlanEstructuraId", pymeObjetoCotizacion.getPlanEstructuraId());
					cotizacionDetalleJSONObject.put("ValorContenidos", pymeObjetoCotizacion.getValorContenidos());
					cotizacionDetalleJSONObject.put("SeguridadAdecuada", pymeObjetoCotizacion.getSeguridadAdecuada());
					cotizacionDetalleJSONObject.put("FechaInspeccion", pymeObjetoCotizacion.getFechaInspeccion());
					cotizacionDetalleJSONObject.put("FechaSolicitud", pymeObjetoCotizacion.getFechaSolicitud());
					cotizacionDetalleJSONArray.add(cotizacionDetalleJSONObject);
				}
				result.put("listadoCotizacionDetalle",  cotizacionDetalleJSONArray);
			}
			//Modifico el estado de la cotización del producto ganadero.
			if(tipoConsulta.equalsIgnoreCase("cambiarEtapa"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String etapaNumero = request.getParameter("etapaNumero") == null ? "" : request.getParameter("etapaNumero").trim();

				if(cotizacionId!=null&&!cotizacionId.equals(""))
					cotizacion=cotizacionDAO.buscarPorId(cotizacionId);

				cotizacion.setEtapaWizard(Integer.parseInt(etapaNumero));
				cotizacion = cotizacionTransaction.editar(cotizacion);

				result.put("cotizacionId",cotizacion.getId());
			}

			if(tipoConsulta.equalsIgnoreCase("descargarInformeInspeccion"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String objetoCotizacionId = request.getParameter("cotizacionDetalleId") == null ? "" : request.getParameter("cotizacionDetalleId").trim();

				CotizacionDetalle detalle=cotizacionDetalleDAO.buscarPorId(objetoCotizacionId);
				
				PymeObjetoCotizacionDAO agriCotizadorOfflineDAO = new PymeObjetoCotizacionDAO();
				PymeObjetoCotizacion agriCotizadorOffline=agriCotizadorOfflineDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
				
				byte[] fileBytes = new byte[5000000];
				
				if(agriCotizadorOffline.getInforme()!=null){
					fileBytes=agriCotizadorOffline.getInforme();
					response.setHeader("Content-Transfer-Encoding", "binary"); 
					response.setContentLength(fileBytes.length);
					response.setHeader("Content-Encoding", "none");
					response.setContentType("application/force-download");
					response.setHeader("Content-Disposition","attachment; filename=" + "InformeInspeccionCotizacion_" + cotizacionId + ".pdf");//fileName);
					//result.write(response.getWriter());
					ServletOutputStream  o = response.getOutputStream();
					o.write(fileBytes); 
					o.flush(); 
					o.close();  
				}

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("generarReporte"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				/*Tomamos el punto de venta de la cotizacion l.caiza*/
				CotizacionDAO cotizacionDAO2= new CotizacionDAO();	
				Cotizacion cotizacion2 = cotizacionDAO2.buscarPorId(cotizacionId);
				PymeParametroPuntoVentaDAO pymeParametroPuntoVentaDAO=new PymeParametroPuntoVentaDAO(); 
				PymeParametroPuntoVenta  pymeParametroPuntoVenta=pymeParametroPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion2.getPuntoVenta().getId()));
					
				/*fin*/
				String html= generarHtmlCotizacion(cotizacionId, ""+File.separator+"static"+File.separator+"plantillas"+File.separator+"pymes"+File.separator+"solicitudesCotizacion"+File.separator+""+pymeParametroPuntoVenta.getPlantillaNombre());
				System.out.println(html);
				byte[] data = GenerarPDF(html, cotizacionId);
				response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "Cotizacion_" + cotizacionId + ".pdf");//fileName);

				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("imprimirPoliza"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();

				String html= generarHtml(cotizacionId, "/static/plantillas/pymes/poliza.htm", false);

				byte[] data = GenerarPDF(html, cotizacionId);
				response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "PolizaCotizacion_" + cotizacionId + ".pdf");//fileName);


				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("imprimirEndosoBeneficiario"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				Cotizacion cotizacionActual=cotizacionDAO.buscarPorId(cotizacionId);
				
				PymeParametroXPuntoVentaDAO parametroPVDAO=new PymeParametroXPuntoVentaDAO();
				PymeParametroXPuntoVenta parametroPVActual=parametroPVDAO.obtenerPorAgenteId(cotizacion.getAgenteId());
				
				String html="";
				if(parametroPVActual.getParametroPuntoVentaId()!=null)
				{
					if(parametroPVActual.getTieneMultiriesgo()){
						//COMO EL AGENTE TIENE APROBADO MIC SOLO RECORRO LOS ENDOSOS Y GENERO UN HTML POR ENDOSO
						PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO=new PymeEndosoBeneficiarioDAO();
						List<PymeEndosoBeneficiario> listadoEndosos=pymeEndosoBeneficiarioDAO.buscarPorCotizacion(new BigInteger(cotizacionId));
						for(PymeEndosoBeneficiario endosoActual:listadoEndosos){
							html+=generarHtmlEndosoBeneficiario(cotizacionActual, endosoActual, "/static/plantillas/pymes/endosobeneficiario.htm", "MULTIRIESGO INDUSTRIAL COMERCIAL");
						}
					}
					else{
						//Recupero los ramos de la cotización para hacer un endoso por ramo
						PymeRamoCotizacionDAO ramoCotizacionDAO=new PymeRamoCotizacionDAO();
						List<PymeRamoCotizacion> listadoRamosCotizacion=ramoCotizacionDAO.buscarPorCotizacionId(new BigInteger(cotizacionActual.getId()));
						//Recupero los endosos
						PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO=new PymeEndosoBeneficiarioDAO();
						List<PymeEndosoBeneficiario> listadoEndosos=pymeEndosoBeneficiarioDAO.buscarPorCotizacion(new BigInteger(cotizacionId));
						for(PymeRamoCotizacion ramoCotizacionActual:listadoRamosCotizacion){
							for(PymeEndosoBeneficiario endosoActual:listadoEndosos){
								html+=generarHtmlEndosoBeneficiario(cotizacionActual, endosoActual, "/static/plantillas/pymes/endosobeneficiario.htm", ramoCotizacionActual.getNombreRamo());
							}
						}
					}
				}
				else{
					//Recupero los ramos de la cotización para hacer un endoso por ramo
					PymeRamoCotizacionDAO ramoCotizacionDAO=new PymeRamoCotizacionDAO();
					List<PymeRamoCotizacion> listadoRamosCotizacion=ramoCotizacionDAO.buscarPorCotizacionId(new BigInteger(cotizacionActual.getId()));
					//Recupero los endosos
					PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO=new PymeEndosoBeneficiarioDAO();
					List<PymeEndosoBeneficiario> listadoEndosos=pymeEndosoBeneficiarioDAO.buscarPorCotizacion(new BigInteger(cotizacionId));
					for(PymeRamoCotizacion ramoCotizacionActual:listadoRamosCotizacion){
						for(PymeEndosoBeneficiario endosoActual:listadoEndosos){
							html+=generarHtmlEndosoBeneficiario(cotizacionActual, endosoActual, "/static/plantillas/pymes/endosobeneficiario.htm", ramoCotizacionActual.getNombreRamo());
						}
					}
				}
				
				byte[] data = GenerarPDF(html, cotizacionId);
				response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "EndosoBeneficiarioCotizacion_" + cotizacionId + ".pdf");//fileName);


				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("imprimirPolizaBorrador"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();

				String html= generarHtml(cotizacionId, "/static/plantillas/pymes/polizaBorrador.htm", true);

				byte[] data = GenerarPDF(html, cotizacionId);
				response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "PolizaBorradorCotizacion_" + cotizacionId + ".pdf");//fileName);
				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("enviarReporte"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);

				ClienteDAO clienteDAO=new ClienteDAO();
				
				Cliente cliente=clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
				
				Entidad entidadUsuario=cotizacion.getUsuario().getEntidad();
				
				String html= generarHtmlCotizacion(cotizacionId, "/static/plantillas/pymes/solicitudCotizacion.htm");

				byte[] data = GenerarPDF(html, cotizacionId);
				
				
				//Obtengo las configuraciones de las notificaciones
				NotificacionDAO notificacionDAO=new NotificacionDAO();
				Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_COTIZACION");
				if(notificacion.getNotificacionId()!=null){
					
					VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
					VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
					
					java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
					parametersHeader.put("urlImagenes", variableSistema.getValor());
					parametersHeader.put("nombreCliente", cliente.getEntidad().getNombreCompleto());
					
					parametersHeader.put("CotizacionId", cotizacion.getId());
					parametersHeader.put("UsuarioNombre", entidadUsuario.getNombreCompleto());
					if(entidadUsuario.getTelefono()==null)
						parametersHeader.put("UsuarioTelefono", " ");
					else
						parametersHeader.put("UsuarioTelefono", entidadUsuario.getTelefono());
					parametersHeader.put("UsuarioExtension", " ");
					parametersHeader.put("UsuarioSucursal", " ");
					
					
					PymeObjetoCotizacionDAO objetoPymesDAO=new PymeObjetoCotizacionDAO();
					ProvinciaDAO provinciaDAO=new ProvinciaDAO();
					String datosBien="<table width='100%'>";
					int contador=1;
					for(CotizacionDetalle detalle:cotizacion.getCotizacionDetalles()){
						PymeObjetoCotizacion detalleObjeto=objetoPymesDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
						if(detalleObjeto!=null){
							Provincia provincia=provinciaDAO.buscarPorId(""+detalleObjeto.getProvinciaId());
							datosBien+="<tr><td>"+contador+"</td><td>"+provincia.getNombre()+"</td><td>"+detalleObjeto.getCallePrincipal()+" y " +detalleObjeto.getCalleSecundaria()+"</td></tr>";
							contador++;
						}
					}
					datosBien+="</table>";
					parametersHeader.put("DatosBienes", datosBien);
					
					StringBuilder mailReceptor = new StringBuilder();
					String mailEjecutivo="";
					if(notificacion.isNotificacionCliente())
						if(!cliente.getEntidad().getMail().equals(""))
						{
							mailReceptor.append(cliente.getEntidad().getMail());
							mailReceptor.append(",");
						}
					if(notificacion.isNotificacionUsuario())
						if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
						{
							mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
							mailReceptor.append(",");
						}
					if(notificacion.isNotificacionEjecutivo())
					{
						//proceso para obtener de la nueva tabla
						PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
						EntidadDAO entidadDA=new EntidadDAO();
						PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
						if(pppv.getParametroPuntoventaId()!=null){
							Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
							if(!entidad.getMail().equals(""))
							{
								mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
								mailReceptor.append(",");
							}
						}
					}
					if(!notificacion.getEmailInspector().equals("")){
						mailEjecutivo = notificacion.getEmailInspector();
					}
					
					if(!mailReceptor.equals("")){
						MailGenericoPlantillas.EnvioPlantillaGenerica(cliente.getEntidad().getMail(), parametersHeader, "/static/plantillas/pymes/" + notificacion.getPlantillaNombre(), 
													request, data, mailEjecutivo);
					}
				}
			}

			if(tipoConsulta.equalsIgnoreCase("enviarPoliza"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);

				ClienteDAO clienteDAO=new ClienteDAO();
				
				Cliente cliente=clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
				
				String html= generarHtml(cotizacionId, "/static/plantillas/pymes/poliza.htm", false);

				byte[] data = GenerarPDF(html, cotizacionId);
				
				
				//Obtengo las configuraciones de las notificaciones
				NotificacionDAO notificacionDAO=new NotificacionDAO();
				Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_EMISION_POLIZA");
				if(notificacion.getNotificacionId()!=null){
					
					VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
					VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
					
					java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
					parametersHeader.put("urlImagenes", variableSistema.getValor());
					parametersHeader.put("nombreCliente", cliente.getEntidad().getNombreCompleto());
					
					StringBuilder mailReceptor = new StringBuilder();
					String mailEjecutivo="";
					if(notificacion.isNotificacionCliente())
						if(!cliente.getEntidad().getMail().equals(""))
						{
							mailReceptor.append(cliente.getEntidad().getMail());
							mailReceptor.append(",");
						}
					if(notificacion.isNotificacionUsuario())
						if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
						{
							mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
							mailReceptor.append(",");
						}
					if(notificacion.isNotificacionEjecutivo())
					{
						//proceso para obtener de la nueva tabla
						PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
						EntidadDAO entidadDA=new EntidadDAO();
						PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
						if(pppv.getParametroPuntoventaId()!=null){
							Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
							if(!entidad.getMail().equals(""))
							{
								mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
								mailReceptor.append(",");
							}
						}
					}
					if(!notificacion.getEmailInspector().equals("")){
						mailEjecutivo = notificacion.getEmailInspector();
					}
					
					if(!mailReceptor.equals("")){
						MailGenericoPlantillas.EnvioPlantillaGenerica(cliente.getEntidad().getMail(), parametersHeader, "/static/plantillas/pymes/" + notificacion.getPlantillaNombre(), 
													request, data, mailEjecutivo);
					}
				}
			}
			
			//Crear la cotización de Pymes fzurita
			if(tipoConsulta.equalsIgnoreCase("crear") && producto.equalsIgnoreCase("PYMES"))
			{			
				String codigoEntidadEnsurance = request.getParameter("codigoEntidadEnsurance") == null ? "" : request.getParameter("codigoEntidadEnsurance").trim();
				String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId").trim();
				String vigenciaPoliza = request.getParameter("vigenciaPoliza") == null ? "" : request.getParameter("vigenciaPoliza").trim();
				String tipoIdentificacion = request.getParameter("tipoIdentificacion") == null ? "" : request.getParameter("tipoIdentificacion").trim();
				String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion").trim();
				String nombres = request.getParameter("nombres") == null ? "" : request.getParameter("nombres").trim();
				String apellidos = request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos").trim();
				String nombreCompleto = request.getParameter("nombreCompleto") == null ? "" : request.getParameter("nombreCompleto").trim();
				String agenteId = request.getParameter("agenteId") == null ? "" : request.getParameter("agenteId").trim();
				String mail = request.getParameter("email") == null ? "" : request.getParameter("email").trim();
				String telefono = request.getParameter("telefono") == null ? "" : request.getParameter("telefono").trim();
				String celular = request.getParameter("celular") == null ? "" : request.getParameter("celular").trim();
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String pxpv = request.getParameter("productoXPuntoDeVenta") == null ? "" : request.getParameter("productoXPuntoDeVenta").trim();
				String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
				String esContribuyente = request.getParameter("esContribuyente") == null ? "" : request.getParameter("esContribuyente");

				EntidadDAO entidadDAO = new EntidadDAO();
				TipoIdentificacionDAO tipoIdentificacionDAO = new TipoIdentificacionDAO();
				ClienteDAO clienteDAO = new ClienteDAO();
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				
				Entidad entidad = new Entidad();
				Cliente cliente = new Cliente();

				entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);

				if(!identificacion.equals(""))
					entidad.setIdentificacion(identificacion);

				if(!telefono.equals(""))
					entidad.setTelefono(telefono);

				if(!celular.equals(""))
					entidad.setCelular(celular);

				if(!codigoEntidadEnsurance.equals(""))
					entidad.setEntEnsurance(codigoEntidadEnsurance);

				if(!tipoIdentificacion.equals(""))
					entidad.setTipoIdentificacion(tipoIdentificacionDAO.buscarPorId(tipoIdentificacion));

				entidad.setMail(mail);
				
				

				if(tipoIdentificacionDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")){
					entidad.setNombres("");
					entidad.setApellidos("");
					entidad.setNombreCompleto(nombreCompleto.toUpperCase());
					if(esContribuyente.equalsIgnoreCase("1"))
						entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("3"));
					else
						entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));

				}else{
					entidad.setNombres(nombres.toUpperCase());
					entidad.setApellidos(apellidos.toUpperCase());
					entidad.setNombreCompleto(nombres.toUpperCase() + " " + apellidos.toUpperCase());
					entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
				}

				if(entidad.getId()==null)
					entidad=entidadTransaction.crear(entidad);
				else
					entidad=entidadTransaction.editar(entidad);

				cliente = clienteDAO.buscarPorEntidadId(entidad);

				if(cliente.getId() == null){
					ActividadEconomica actividad = new ActividadEconomica();
					ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
					actividad = actividadDAO.buscarPorNombre("Ninguno");

					Cliente clienteNuevo = new Cliente();

					clienteNuevo.setEntidad(entidad);
					clienteNuevo.setActividadEconomica(actividad);
					clienteNuevo.setActivo(true);
					cliente=clienteTransaction.crear(clienteNuevo);
					//falta en codigo del ensurance
				}

				EstadoDAO estadoDAO = new EstadoDAO();

				PuntoVentaDAO pvDAO = new PuntoVentaDAO();

				VigenciaPolizaDAO vpDAO= new  VigenciaPolizaDAO();

				if(cotizacionId!=null&&!cotizacionId.equals(""))
					cotizacion=cotizacionDAO.buscarPorId(cotizacionId);

				if(!puntoVentaId.equals(""))
					cotizacion.setPuntoVenta(pvDAO.buscarPorId(puntoVentaId));

				if(!pxpv.equals(""))
					cotizacion.setProductoXPuntoVentaId(BigInteger.valueOf(Long.parseLong(pxpv)));

				if(!vigenciaPoliza.equals(""))
					cotizacion.setVigenciaPoliza(vpDAO.buscarPorId(vigenciaPoliza));										

				if(!agenteId.equals("")){
					cotizacion.setAgenteId(BigInteger.valueOf(Long.valueOf(agenteId)));				
				}

				cotizacion.setClienteId(BigInteger.valueOf(Long.valueOf(cliente.getId())));

				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();

				GrupoPorProducto grupoPorProducto = new GrupoPorProducto();

				grupoPorProducto =  grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
				cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
				cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
				cotizacion.setProducto(grupoPorProducto.getProducto());

				// Agregamos el tipo de  objeto a la cotización
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();	
				cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("PYMES"));
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));

				if(cotizacion.getId()==null)
					cotizacion.setUsuario(usuario);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(!fechaActual.equals(""))
					cotizacion.setFechaElaboracion(fechaActual);

				if(cotizacion.getEtapaWizard()<1){
					cotizacion.setEtapaWizard(1);
				}

				if(cotizacion.getId()!=null){
					cotizacion = cotizacionTransaction.editar(cotizacion);
					//Elimino los detalles que aun no estan grabados en el paso 2
					if(cotizacion.getEtapaWizard()==1){
						CotizacionDetalleTransaction cotizacionDetalleTransaction=new CotizacionDetalleTransaction();
						PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
						PymeObjetoCotizacionCoberturaDAO pymeObjetoCotizacionCoberturaDAO=new PymeObjetoCotizacionCoberturaDAO();
						PymeObjetoCotizacionTransaction pymeObjetoCotizacionTransaction=new PymeObjetoCotizacionTransaction();
						PymeObjetoCotizacionCoberturaTransaction pymeObjetoCotizacionCoberturaTransaction=new PymeObjetoCotizacionCoberturaTransaction();
						List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
						for(CotizacionDetalle detalleActual: listadoDetalles){
							PymeObjetoCotizacion pymeObjetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
							List<PymeObjetoCotizacionCobertura> listadoCoberturas=pymeObjetoCotizacionCoberturaDAO.buscarPorObjetoPymeId(pymeObjetoCotizacion.getObjetoPymesId());
							for(PymeObjetoCotizacionCobertura coberturaActual:listadoCoberturas){
								pymeObjetoCotizacionCoberturaTransaction.eliminar(coberturaActual);
							}
							pymeObjetoCotizacionTransaction.eliminar(pymeObjetoCotizacion);
							cotizacionDetalleTransaction.eliminar(detalleActual);
						}
					}
				}
				else
					cotizacion = cotizacionTransaction.crear(cotizacion);
				
				grupoPorProducto = new GrupoPorProducto();
				grupoPorProductoDAO = new GrupoPorProductoDAO();
				grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());

				ProductoXPuntoVenta productoXPuntoVenta =  new ProductoXPuntoVenta();
				ProductoXPuntoVentaDAO productoXPuntoVentaDAO =  new ProductoXPuntoVentaDAO();
				productoXPuntoVenta = (ProductoXPuntoVenta) productoXPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, cotizacion.getPuntoVenta());
				result.put("unidadNegocioId", productoXPuntoVenta.getUnidadNegocio().getId());
				result.put("cotizacionId",cotizacion.getId());
			}
			//Fin de la creación de la cotización

			if(tipoConsulta.equalsIgnoreCase("eliminar"))
			{		
				String cotizacion_id = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
				cotizacion = cotizacionDAO.buscarPorId(cotizacion_id);

				if(cotizacion.getId()!=null)
					cotizacionTransaction.eliminar(cotizacion);
			}

			if(tipoConsulta.equalsIgnoreCase("eliminarObjetoDetalle"))
			{
				String detalleId = request.getParameter("detalleId") == null ? "" : request.getParameter("detalleId");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				
				PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				PymeObjetoCotizacionCoberturaDAO pymeObjetoCotizacionCoberturaDAO=new PymeObjetoCotizacionCoberturaDAO();
				PymeObjetoCotizacionCoberturaTransaction pymeObjetoCotizacionCoberturaTransaction=new PymeObjetoCotizacionCoberturaTransaction();
				PymeObjetoCotizacionTransaction pymeObjetoCotizacionTransaction=new PymeObjetoCotizacionTransaction();
				CotizacionDetalleTransaction cotizacionDetalleTransaction=new CotizacionDetalleTransaction();
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalle detalle=cotizacionDetalleDAO.buscarPorId(detalleId);
				PymeObjetoCotizacion pymeObjetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
				List<PymeObjetoCotizacionCobertura> listadoCoberturas=pymeObjetoCotizacionCoberturaDAO.buscarPorObjetoPymeId(pymeObjetoCotizacion.getObjetoPymesId());
				for(PymeObjetoCotizacionCobertura coberturaActual:listadoCoberturas){
					pymeObjetoCotizacionCoberturaTransaction.eliminar(coberturaActual);
				}
				pymeObjetoCotizacionTransaction.eliminar(pymeObjetoCotizacion);
				cotizacionDetalleTransaction.eliminar(detalle);

			}


			if(tipoConsulta.equalsIgnoreCase("encontrarTodos"))
			{

				result.put("listadoCotizacion",  consultarTodos());

			}

			if(tipoConsulta.equalsIgnoreCase("encontrarTipoObjeto"))
			{
				String codigoTipoObjeto = request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String numeroCotizacion= request.getParameter("numeroCotizacion") == null ? "" : request.getParameter("numeroCotizacion");
				String puntoVentaId= request.getParameter("puntoVenta") == null ? "" : request.getParameter("puntoVenta");
				String agenteId= request.getParameter("agente") == null ? "" : request.getParameter("agente");
				String identificacion= request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
				String misCotizaciones= request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String estadoCotizacion= request.getParameter("estadoCotizacion") == null ? "" : request.getParameter("estadoCotizacion");

				String usuarioId="";

				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEncontrar = new TipoObjeto();


				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				if(!codigoTipoObjeto.equalsIgnoreCase("0"))
					tipoObjetoEncontrar = tipoObjetoDAO.buscarPorId(codigoTipoObjeto);	
				else
					tipoObjetoEncontrar = null;			

				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();

				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol					
					listaCotizacionesJSONArray.add(i, consultarPorTipoObjeto(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId, estadoCotizacion));
					result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));

					result.put("numeroListas", i);
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();
					i=0;
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						listaCotizacionesJSONArray = new JSONArray();															
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoPuntoVenta(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion));
						result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));							
					}
					result.put("numeroListas", i);	
				}

			}

			//Pjacome
			if(tipoConsulta.equalsIgnoreCase("encontrarTipoObjetoEmitido"))
			{
				String codigoTipoObjeto = request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String[] arrCodigoTipoObjeto = codigoTipoObjeto.split(",");
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEmitido = new TipoObjeto();

				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol				
					for(i=0; i<arrCodigoTipoObjeto.length; i++){
						tipoObjetoEmitido = tipoObjetoDAO.buscarPorId(arrCodigoTipoObjeto[i]);
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoEmitidos(fInicio, fFinal, tipoObjetoEmitido));
						result.put("listadoCotizacion" + i, listaCotizacionesJSONArray.get(i));
					}
					result.put("numeroListas", i);
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();

					if(usuario.getUsuarioXPuntoVentas().size()>0)
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();

					result.put("listadoCotizacion",  consultarPorTipoObjetoPuntoDeVenta(tipoObjetoEmitido, puntoVenta));
				}

			}

			if(tipoConsulta.equalsIgnoreCase("encontrarPorEstado"))
			{
				String estadoNombre = request.getParameter("estadoId") == null ? "" : request.getParameter("estadoId");
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorNombre(estadoNombre, "cotizacion");

				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				Rol rol=new Rol();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();

				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol				
					result.put("listadoCotizaciones", consultarPorEstado(estado));
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();

					if(usuario.getUsuarioXPuntoVentas().size()>0)
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();

					result.put("listadoCotizaciones", consultarPorEstadoPuntoVenta(estado,puntoVenta));
				}

			}


			//Metodo buscar por estado y fecha para ganadero
			if(tipoConsulta.equalsIgnoreCase("encontrarTipoObjetoPorEstado"))
			{
				String codigoTipoObjeto = request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String estadoConsulta= request.getParameter("estadoConsulta") == null ? "" : request.getParameter("estadoConsulta");
				String[] arrCodigoTipoObjeto = codigoTipoObjeto.split(",");
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEncontrar = new TipoObjeto();


				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol								
					for(i=0; i<arrCodigoTipoObjeto.length; i++){
						tipoObjetoEncontrar = tipoObjetoDAO.buscarPorId(arrCodigoTipoObjeto[i]);
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoPorEstado(fInicio, fFinal, tipoObjetoEncontrar, estadoConsulta));
						result.put("listadoCotizacion" + i, listaCotizacionesJSONArray.get(i));
					}
					result.put("numeroListas", i);
				}
			}

			//Metodo buscar por estado y por parametros para PYMES
			if(tipoConsulta.equalsIgnoreCase("encontrarAprobadoPorCanalPymes"))
			{
				String codigoTipoObjeto = request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String numeroCotizacion= request.getParameter("numeroCotizacion") == null ? "" : request.getParameter("numeroCotizacion");
				String puntoVentaId= request.getParameter("puntoVenta") == null ? "" : request.getParameter("puntoVenta");
				String agenteId= request.getParameter("agente") == null ? "" : request.getParameter("agente");
				String identificacion= request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
				String misCotizaciones= request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String estadoConsulta= request.getParameter("estadoConsulta") == null ? "" : request.getParameter("estadoConsulta");
				String usuarioId="";

				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEncontrar = new TipoObjeto();


				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				if(!codigoTipoObjeto.equalsIgnoreCase("0"))
					tipoObjetoEncontrar = tipoObjetoDAO.buscarPorId(codigoTipoObjeto);	
				else
					tipoObjetoEncontrar = null;			

				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();

				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol					
					listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoParaPymes(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId,estadoConsulta));
					result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));

					result.put("numeroListas", i);
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();
					i=0;
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						listaCotizacionesJSONArray = new JSONArray();															
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoPuntoVentaYEstado(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId, estadoConsulta));
						result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));							
					}
					result.put("numeroListas", i);	
				}

			}
			
			if(tipoConsulta.equalsIgnoreCase("imprimirAutorizacionDebito")){
				
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("COTIZACION", cotizacionId);
				
				String pathReporte = "/static/reportes/CertificadosVehiculos/AutorizacionDebito/autorizacionDebito.jasper";
				File reportFile = new File(getServletConfig().getServletContext().getRealPath(pathReporte));
				
				Reportes reporte=new Reportes();
	    		byte[] data = JasperRunManager.runReportToPdf(reportFile.getPath(), toMap(jsonObject), reporte.getConnection());
	    		
	    		response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "AutorizacionDebitoCotizacion_" + cotizacionId + ".pdf");//fileName);


				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("imprimirVinculacionCliente")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				Cotizacion cotizacionActual=cotizacionDAO.buscarPorId(cotizacionId);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("COTIZACION_ID", cotizacionId);
				
				String pathReporte = "/static/reportes/CertificadosVehiculos/UPLA/conozcaASuClienteNaturalVacio.jasper";

				if (cotizacionActual.getAsegurado().getTipoIdentificacion().getId().equals('4'))
					pathReporte = "/static/reportes/CertificadosVehiculos/UPLA/conozcaASuClienteJuridicaVacio.jasper";
				
				File reportFile = new File(getServletConfig().getServletContext().getRealPath(pathReporte));
				
				Reportes reporte=new Reportes();
	    		byte[] data = JasperRunManager.runReportToPdf(reportFile.getPath(), toMap(jsonObject), reporte.getConnection());
	    		
	    		response.setHeader("Content-Transfer-Encoding", "binary"); 
				response.setContentLength(data.length);
				response.setHeader("Content-Encoding", "none");
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition","attachment; filename=" + "ConozcaSuClienteCotizacion_" + cotizacionId + ".pdf");//fileName);


				//result.write(response.getWriter());
				OutputStream o = response.getOutputStream();
				o.write(data); 
				o.flush(); 
				o.close(); 

				return;
			}

			//Metodo buscar por estado y por parametros para ganadero
			if(tipoConsulta.equalsIgnoreCase("encontrarTipoObjetoPorAprobarCanal"))
			{
				String codigoTipoObjeto = request.getParameter("codigoTipoObjeto") == null ? "" : request.getParameter("codigoTipoObjeto");
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				String numeroCotizacion= request.getParameter("numeroCotizacion") == null ? "" : request.getParameter("numeroCotizacion");
				String puntoVentaId= request.getParameter("puntoVenta") == null ? "" : request.getParameter("puntoVenta");
				String agenteId= request.getParameter("agente") == null ? "" : request.getParameter("agente");
				String identificacion= request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
				String misCotizaciones= request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
				String estadoConsulta= request.getParameter("estadoConsulta") == null ? "" : request.getParameter("estadoConsulta");
				String usuarioId="";

				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEncontrar = new TipoObjeto();


				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");

				if(!codigoTipoObjeto.equalsIgnoreCase("0"))
					tipoObjetoEncontrar = tipoObjetoDAO.buscarPorId(codigoTipoObjeto);	
				else
					tipoObjetoEncontrar = null;			

				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();

				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol					
					listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoParaPymes(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId,estadoConsulta));
					result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));

					result.put("numeroListas", i);
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();
					i=0;
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						listaCotizacionesJSONArray = new JSONArray();															
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoPuntoVentaYEstado(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId, estadoConsulta));
						result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));							
					}
					result.put("numeroListas", i);	
				}

			}

			if(tipoConsulta.equalsIgnoreCase("encontrarPorId"))
			{
				String cotizacionId = request.getParameter("id") == null ? "" : request.getParameter("id");									
				usuario = new Usuario();

				if(request.getSession().getAttribute("usuario")!=null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");
				Rol rol=new Rol();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol)))// Validacion para administradores segun el rol				
					result.put("datosCotizacion",  encontrarPorId(cotizacionId));
				else {
					cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
					if (usuario.getUsuarioXPuntoVentas().size() > 0) {
						PuntoVenta puntoVenta = usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						if (cotizacion.getPuntoVenta().getId().equals(puntoVenta.getId()))
							result.put("datosCotizacion",encontrarPorId(cotizacionId));
						else
							throw new Exception("El usuario no puede ver esta cotizacion");
					} else
						throw new Exception("El usuario no tiene punto de venta");
				}				
			}

			if(tipoConsulta.equalsIgnoreCase("enviarCertificado"))
			{
				String correos = request.getParameter("correos") == null ? "" : request.getParameter("correos");		
				String id = request.getParameter("id") == null ? "" : request.getParameter("id");		
				String casoEspecial = request.getParameter("casoEspecial") == null ? "" : request.getParameter("casoEspecial");		

				enviarCertificado(id, correos, casoEspecial,request);

			}

			if(tipoConsulta.equalsIgnoreCase("verificarEstadoPago")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String codigoPagoRegistrado = request.getParameter("codigoPagoRegistrado") == null ? "" : request.getParameter("codigoPagoRegistrado");
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				if(cotizacion.getAsegurado()==null){
					throw new Exception("Debe grabar los datos del asegurado");
				}
				PagoDAO pagoDAO=new PagoDAO();
				Pago pago=pagoDAO.buscarPorId(codigoPagoRegistrado);
				if(pago.getValorTotal()!=(float)cotizacion.getTotalFactura())
					throw new Exception("El valor total de la cotización no corresponde al valor total del pago.");
			}
			
			if(tipoConsulta.equalsIgnoreCase("cargarInspectoresInternos"))
			{
				result.put("listadoInspectoresInternos", cargarInspectoresInternos());

			}

			if(tipoConsulta.equalsIgnoreCase("solicitarAutorizacion")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				EstadoDAO estadoDAO=new EstadoDAO();
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente de Autorizar","Cotizacion"));
				cotizacionTransaction.editar(cotizacion);
				result.put("success", Boolean.TRUE);
				response.setContentType("application/json; charset=ISO-8859-1"); 
				result.write(response.getWriter());
				
				//Envió correo de notificación
				Entidad usuarioCotiza=cotizacion.getUsuario().getEntidad();
				VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
				VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
				
				java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
				parametersHeader.put("urlImagenes", variableSistema.getValor());
				parametersHeader.put("CotizacionId", cotizacion.getId());
				parametersHeader.put("UsuarioNombre", usuarioCotiza.getNombreCompleto());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("EMAILS_EJECUTIVOS_PYMES");
				
				if(!variableSistema.getValor().equals("")){
					MailGenericoPlantillas.EnvioPlantillaGenerica(variableSistema.getValor(), parametersHeader, "/static/plantillas/pymes/solicitudAutorizacion.html", request, "");
				}
				
				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("autorizarCotizacion")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				EstadoDAO estadoDAO=new EstadoDAO();
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Autorizada","Cotizacion"));
				cotizacionTransaction.editar(cotizacion);
				result.put("success", Boolean.TRUE);
				response.setContentType("application/json; charset=ISO-8859-1"); 
				result.write(response.getWriter());
				return;
			}
			
			if(tipoConsulta.equalsIgnoreCase("emitirPoliza")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String fechaInicioVigencia = request.getParameter("fechaInicioVigencia") == null ? "" : request.getParameter("fechaInicioVigencia");
				
				EstadoDAO estadoDAO=new EstadoDAO();
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				
				Estado estado=estadoDAO.buscarPorNombre("Emitido","Cotizacion");
				
				if(cotizacion.getEstado().getId()==estado.getId()){
					/***TRATAMIENTO DE ERROR***/
					Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
					String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
					/***AUDITORIA auditamos el error para el seguimiento***/
					PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
					PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
					pymeAuditoriaGeneral.setTramite(CodError);
					pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
					pymeAuditoriaGeneral.setProceso("PYMES");
					pymeAuditoriaGeneral.setObjeto("La cotización ya fue emitida anteriormente.");
					try {
						pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
					} catch (Exception e1) {				
						e1.printStackTrace();
					}
					/***RESPUESTA A INTERFAZ***/
					result.put("success", Boolean.FALSE);
					result.put("autorizacion", Boolean.FALSE);
					result.put("errorMesaje", "La cotización ya fue emitida anteriormente.");
					result.put("error", "Error generado, refiérase para soporte con el siguiente código: "+CodError);
					result.put("ex", "La cotización ya fue emitida anteriormente.");
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());	
					return;
				}
				//Transforma la fecha de vigencia dede
				Date fechaInicioVigenciaValor = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				fechaInicioVigenciaValor=formatter.parse(fechaInicioVigencia);
				
				//Asigno la fecha de inicio de vigencia ingresada
				cotizacion.setVigenciaDesde(fechaInicioVigenciaValor);
				
				//Valido que la emisión no tenga fecha menor a la de la inspección.
				estado=estadoDAO.buscarPorNombre("Pendiente","Cotizacion");
				if(cotizacion.getEstado().getId().toString().equals(estado.getId().toString())){
					PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO(); 
					List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
					int contador=0;
					for(CotizacionDetalle detalle:listadoDetalles){
						PymeObjetoCotizacion objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
						if(objetoCotizacion.getFechaInspeccion()!=null){
							if(fechaInicioVigenciaValor.before(objetoCotizacion.getFechaInspeccion())){
								contador++;
								break;
							}
						}
					}
					
					if(contador>=1)
					{
						/***TRATAMIENTO DE ERROR***/
						Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
						String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
						/***AUDITORIA auditamos el error para el seguimiento***/
						PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
						PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
						pymeAuditoriaGeneral.setTramite(CodError);
						pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
						pymeAuditoriaGeneral.setProceso("PYMES");
						pymeAuditoriaGeneral.setObjeto("La cotización no puede ser emitida porque la fecha de emisión debe ser mayor a la fecha de inspección.");
						try {
							pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
						} catch (Exception e1) {				
							e1.printStackTrace();
						}
						/***RESPUESTA A INTERFAZ***/
						result.put("success", Boolean.FALSE);
						result.put("autorizacion", Boolean.TRUE);
						result.put("errorMesaje", "La cotización no puede ser emitida porque la fecha de emisión debe ser mayor a la fecha de inspección.");
						result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
						result.put("ex", "La cotización no puede ser emitida porque la fecha de emisión debe ser mayor a la fecha de inspección.");
						response.setContentType("application/json; charset=ISO-8859-1"); 
						result.write(response.getWriter());
						return;
					}
				}
				
				//Determino la fecha en base a las tablas
				PymeFechaCierreDAO fechaCierreDAO=new PymeFechaCierreDAO();
				List<PymeFechaCierre> listadoFechas= fechaCierreDAO.buscarTodos();
				Date fechaActual=new Date();
				Calendar cal = Calendar.getInstance();
			    cal.setTime(fechaActual);
			    int diaActual = cal.get(Calendar.DAY_OF_MONTH);
			    int mesActual = cal.get(Calendar.MONTH);
			    int anioActual = cal.get(Calendar.YEAR);
			    int diaDeterminado=0;
			    
				for(PymeFechaCierre fechaCierreActual:listadoFechas){
					Calendar calF = Calendar.getInstance();
					calF.setTime(fechaCierreActual.getFechaCierre());
					int mesTabla=calF.get(Calendar.MONTH);
					int anioTable=calF.get(Calendar.YEAR);
					if(mesTabla==mesActual && anioActual==anioTable){
						diaDeterminado = calF.get(Calendar.DAY_OF_MONTH);
						break;
					}
				}
				
				if(diaDeterminado!=0){
					if(diaActual>diaDeterminado){
						cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente de Emitir","Cotizacion"));
						cotizacion.setVigenciaDesde(fechaInicioVigenciaValor);
						cotizacionTransaction.editar(cotizacion);
					}
					else{
						//Antes de emitir, primero actualizo los datos de la entidad
						EntidadDAO entidadDAO=new EntidadDAO();
						DireccionDAO direccionDAO=new DireccionDAO();
						UplaDAO uplaDAO=new UplaDAO();
						
						Entidad aseguradoCotizacion=entidadDAO.buscarPorId(cotizacion.getAsegurado().getId());
						List<Direccion> aseguradoDirecciones=direccionDAO.buscarCobroPorEntidadId(aseguradoCotizacion);
						Direccion aseguradoDireccion=null;
						if(aseguradoDirecciones.size()!=0){
							aseguradoDireccion=aseguradoDirecciones.get(0);
						}
							
						
						EnsuranceEntity entity=new EnsuranceEntity();
						EngineProxy proxy=new EngineProxy();
						ENTIDAD entidadActualizada=new ENTIDAD();
						PERSONA personaActualizada=new PERSONA();
						ENTIDADDIRECCION entidadDireccionActualizada = new ENTIDADDIRECCION();
						DIRECCION direccionActualizada=new DIRECCION();
						EMPRESA empresaActualizada = new EMPRESA();
						
						if(aseguradoCotizacion.getEntEnsurance()!=null){
							if(aseguradoCotizacion.getEntEnsurance().equals("")){
								ENTIDAD[] listado=proxy.searchData(aseguradoCotizacion.getIdentificacion(), 1, "");
								if(listado.length!=0){
									entidadActualizada.setID(listado[0].getID());
									entity.setEsActualizacion(true);
									entity.setEsNuevo(false);
								}
								else{
									entity.setEsActualizacion(false);
									entity.setEsNuevo(true);
								}
							}
							else{
								entidadActualizada.setID(aseguradoCotizacion.getEntEnsurance());
								entity.setEsActualizacion(true);
								entity.setEsNuevo(false);
							}
						}
						else{
							ENTIDAD[] listado=proxy.searchData(aseguradoCotizacion.getIdentificacion(), 1, "");
							if(listado.length!=0)
							{
								entidadActualizada.setID(listado[0].getID());
								entity.setEsActualizacion(true);
								entity.setEsNuevo(false);
							}
							else{
								entity.setEsActualizacion(false);
								entity.setEsNuevo(true);
								entidadActualizada.setID("");
							}
						}
						
						entidadActualizada.setTELEFONOCELULAR1(aseguradoCotizacion.getTelefono());
						entidadActualizada.setIDENTIFICACION(aseguradoCotizacion.getIdentificacion());
						entidadActualizada.setTELEFONOCELULAR2("");
						entidadActualizada.setTELEFONOCELULAR3(aseguradoCotizacion.getCelular());
						entidadActualizada.setEMAILPRINCIPAL(aseguradoCotizacion.getMail());
						entidadActualizada.setTIPOENTIDADID(aseguradoCotizacion.getTipoEntidad().getId());
						entidadActualizada.setTIPOID(aseguradoCotizacion.getTipoIdentificacion().getId());
						entidadActualizada.setTIPOOBJETO("Cliente");
						entidadActualizada.setNACIONALIDADID(null);
						entidadActualizada.setFECHAACTUALIZA(Calendar.getInstance());
						entidadActualizada.setPEPS("0");
						entidadActualizada.setOFAC("0");
						entidadActualizada.setCONSUEP("0");
						entidadActualizada.setESTADO("1");
						entidadActualizada.setNUMEROINTENTOS(Short.valueOf("0"));
						entidadActualizada.setBLOQUEADA("0");
						entidadActualizada.setUSUARIOACTUALIZA("005338");
						entidadActualizada.setTIPOEMPLEADOID("3");
						entidadActualizada.setTIPOEMPRESAID("-1");
						entidadActualizada.setPARENTESCOID("11");
						entidadActualizada.setGRUPOECONOMICOID("-1");
	
						entidadActualizada.setGRUPOECONOMICOID("-1");
						if(Integer.parseInt(aseguradoCotizacion.getTipoEntidad().getId())==1){
							entidadActualizada.setAPELLIDO(aseguradoCotizacion.getApellidos());
							entidadActualizada.setNOMBRE(aseguradoCotizacion.getNombres());
							entidadActualizada.setNOMBRECOMPLETO(aseguradoCotizacion.getNombreCompleto());
							if(aseguradoCotizacion.getClientes().size()!=0){
								Upla uplaCotizacion=uplaDAO.buscarPorCliente(aseguradoCotizacion.getClientes().get(0));
								Calendar cal2 = Calendar.getInstance();
							    cal2.setTime(uplaCotizacion.getFechaNacimientoNatural());
								personaActualizada.setFECHANACIMIENTO(cal2);
								personaActualizada.setNACIONALIDAD(null);
								personaActualizada.setGENERO(uplaCotizacion.getGeneroNatural());
								personaActualizada.setGENERO("M");
								personaActualizada.setID("");
								personaActualizada.setUSUARIOACTUALIZA("005338");
								entidadActualizada.setPERSONA(personaActualizada);
							}
						}
						else{
							entidadActualizada.setNOMBRE(aseguradoCotizacion.getNombreCompleto());
							entidadActualizada.setNOMBRECOMPLETO(aseguradoCotizacion.getNombreCompleto());
							if(aseguradoCotizacion.getClientes().size()!=0){
								Upla uplaCotizacion=uplaDAO.buscarPorCliente(aseguradoCotizacion.getClientes().get(0));
							    empresaActualizada.setID("");
							    empresaActualizada.setSECTORID("8847360");
							    empresaActualizada.setUSUARIOACTUALIZA("005338");
							    empresaActualizada.setAPELLIDOSREPRESENTANTE(uplaCotizacion.getApellidosRepresentanteLegal());
							    empresaActualizada.setNOMBRESREPRESENTANTE(uplaCotizacion.getNombresRepresentanteLegal());
							    empresaActualizada.setFECHACONSTITUCION(null);
							    entidadActualizada.setEMPRESA(empresaActualizada);	
							}
						}
						entity.setE(entidadActualizada);
						
						//Lleno el objeto Direccion
						direccionActualizada.setNOMBRE(null);
						direccionActualizada.setTELEFONO1(aseguradoCotizacion.getTelefono());
						direccionActualizada.setTELEFONO2("");
						direccionActualizada.setTELEFONO3(aseguradoCotizacion.getCelular());
						direccionActualizada.setPAISID("6815744");
						if(aseguradoDireccion.getCiudad()!=null){
							if(aseguradoDireccion.getZona().getId().equals("1"))
								direccionActualizada.setCIUDADID(aseguradoDireccion.getCiudad().getId());
							else
								direccionActualizada.setCANTONID(aseguradoDireccion.getCiudad().getId());
						
							direccionActualizada.setPROVINCIAID(aseguradoDireccion.getCiudad().getProvincia().getCodigoSbs());
								
						}
						
						direccionActualizada.setNOMBREPRINCIPAL(aseguradoDireccion.getCallePrincipal());
						direccionActualizada.setNOMBRESECUNDARIA(aseguradoDireccion.getCalleSecundaria());
						direccionActualizada.setNUMERO(aseguradoDireccion.getNumero());
						direccionActualizada.setZONA(aseguradoDireccion.getZona().getId());
						direccionActualizada.setNOMBRE(aseguradoDireccion.getCallePrincipal()+" "+aseguradoDireccion.getNumero()+" "+aseguradoDireccion.getCalleSecundaria());
						direccionActualizada.setNOMBREOPCIONAL(aseguradoDireccion.getDatosDeReferencia());
						direccionActualizada.setESTADOINFORMACION("A");
						direccionActualizada.setUSUARIOACTUALIZA("005338");
						entity.setD(direccionActualizada);
						
						//Lleno el objeto Direccion por Entidad
						entidadDireccionActualizada.setTIPODIRECCIONID(aseguradoDireccion.getTipoDireccion().getId());
						
						entity.setEd(entidadDireccionActualizada);
						
						Calendar date = Calendar.getInstance();
						
						InetAddress ip=InetAddress.getLocalHost();
						RequestTicket req=new RequestTicket();
						req.setApplicationID("1C77E350-6874-447B-8D76-7ABA7A7CCFDF");
						req.setIP(ip.getHostAddress());
						req.setLoginDate(date);
						req.setSessionToken(java.util.UUID.randomUUID().toString());
						req.setUserID(java.util.UUID.randomUUID().toString());
						//String resultado = "";
						String resultado = proxy.saveData(java.util.UUID.randomUUID().toString(), req, entity);
						if(!resultado.equals("")){
							String[] ids=resultado.split("-");
							if(ids.length!=0){
								aseguradoCotizacion.setEntEnsurance(ids[0].trim());
								entidadTransaction.editar(aseguradoCotizacion);
							}
						}
						
						
						//Obtengo la unidad de producción
						ProductoXPuntoVentaDAO ppvDAO=new ProductoXPuntoVentaDAO();
						GrupoPorProductoDAO gpDAO=new GrupoPorProductoDAO();
						ProductoXPuntoVenta ppv=ppvDAO.buscarPorGrupoPuntoVenta(gpDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString()), cotizacion.getPuntoVenta());
						String unidadProduccionId="";
						if(ppv.getUnidadProduccion()!=null){
							unidadProduccionId=ppv.getUnidadProduccion().getUpEnsurance();
						}
					
						//Empiezo la creación de objetos para enviar a emitir
						ClienteDAO clienteDAO = new ClienteDAO();
						PymeParametroXPuntoVentaDAO parametroPVDAO=new PymeParametroXPuntoVentaDAO();
	
						Cliente cliente= clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
	
						//Formo el objeto ClienteDTO para enviar como cliente
						ClienteDTO clienteDTO=new ClienteDTO();
						if(cliente.getEntidad().getIdentificacion().equals(cotizacion.getAsegurado().getIdentificacion()))
							clienteDTO=generarObjetoAseguradoDTO(cotizacion.getAsegurado());
						else
							clienteDTO=generarObjetoClienteDTO(cliente.getEntidad());
	
	
						//Formo el objeto ClienteDTO para enviar como asegurado
						ClienteDTO aseguradoDTO=generarObjetoAseguradoDTO(cotizacion.getAsegurado());
	
						//Arreglo de beneficiarios
						ClienteDTO[] beneficiariosDTO=new ClienteDTO[1];
						beneficiariosDTO[0]=aseguradoDTO;
	
						//Forma el objeto del Pago
						ConfiguracionPagoDTO configuracionPagoDTO=generarObjetoPagoDTO(cotizacion.getPago());
	
						PymeParametroXPuntoVenta parametroPVActual=parametroPVDAO.obtenerPorAgenteId(cotizacion.getAgenteId());
	
						PolizaDTO polizaDTO=new PolizaDTO();
						HashMap<String, EndosoDTO> endosos=new HashMap<String, EndosoDTO>();
						if(parametroPVActual!=null)
						{
							if(parametroPVActual.getParametroPuntoVentaId()!=null)
							{
								if(parametroPVActual.getTieneMultiriesgo()){
									polizaDTO=generarPolizaMultiriesgo(cotizacion.getAgenteId().toString(), null, cotizacion.getId(), unidadProduccionId);
									endosos=generarEndosoMultiriesgo(cotizacion, null);
								}
								else{
									polizaDTO=generarPolizaProgramaSeguros(cotizacion.getAgenteId().toString(), null, cotizacion.getId(), unidadProduccionId);
									endosos=generarEndosoProgramaSeguros(cotizacion, null);
								}
							}
							else{
								polizaDTO=generarPolizaProgramaSeguros(cotizacion.getAgenteId().toString(), null, cotizacion.getId(), unidadProduccionId);
								endosos=generarEndosoProgramaSeguros(cotizacion, null);
							}
						}
						else{
							polizaDTO=generarPolizaProgramaSeguros(cotizacion.getAgenteId().toString(), null, cotizacion.getId(), unidadProduccionId);
							endosos=generarEndosoProgramaSeguros(cotizacion, null);
						}
	
						FacturaDTO nuevaFactura=new FacturaDTO();
						nuevaFactura.setTipoDocumentoFac("1");
						nuevaFactura.setValorFactura(new BigDecimal(cotizacion.getTotalFactura()));
	
						HashMap<String, Object> properties=new HashMap<String, Object>();
						properties.put("poliza", polizaDTO);
						properties.put("factura", nuevaFactura); 
						properties.put("endosos", endosos);
						properties.put("asegurado", aseguradoDTO);
						properties.put("beneficiarios", beneficiariosDTO);
						properties.put("configPago", configuracionPagoDTO);
						properties.put("cliente", clienteDTO);
	
						//Ensamblo el objeto RequestDTO para enviar al servicio
						RequestDTO requestSW=new RequestDTO();
						requestSW.setProperties(properties);
						VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
						VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("USUARIO_EMISION_PYMES");
						requestSW.setUsername(variableSistema.getValor());
						variableSistema=variableSistemaDAO.buscarPorNombre("USUARIO_PASSWORD_EMISION_PYMES");
						requestSW.setPassword(variableSistema.getValor());
						variableSistema=variableSistemaDAO.buscarPorNombre("PUERTO_EMISION_PYMES");
						requestSW.setIntegrationName("PYMES");
						requestSW.setPuerto(Integer.parseInt(variableSistema.getValor()));
						
						SolicitudEmision nuevaSolucitud=new SolicitudEmision();
						SolicitudEmisionTransaction solicitudEmisionTransaction=new SolicitudEmisionTransaction();
						nuevaSolucitud.setCotizacionId(cotizacion.getId());
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(baos));
						xmlEncoder.writeObject(requestSW);
						xmlEncoder.close();
						
						nuevaSolucitud.setXml(baos.toString());
						
						try{
							ServicioIntegracionProxy client=new ServicioIntegracionProxy();
							RespuestaDTO respuesta= client.integrar(requestSW);
							if(respuesta!=null){
								//Serializo el objeto de respuesta
								ByteArrayOutputStream baosR = new ByteArrayOutputStream();
								XMLEncoder xmlEncoderR = new XMLEncoder(new BufferedOutputStream(baosR));
								xmlEncoderR.writeObject(respuesta);
								xmlEncoderR.close();
								//Grabo la solicitud de emision
								nuevaSolucitud.setRespuesta(baosR.toString());
								solicitudEmisionTransaction.crear(nuevaSolucitud);
								if(respuesta.isExito()){

									//Creo un objeto de respuesta de la cotización
									CotizacionRespuestaTransaction cotizacionRTrans=new CotizacionRespuestaTransaction();
									CotizacionRespuesta cotizacionRespuesta=new CotizacionRespuesta();
									cotizacionRespuesta.setCotizacionId(new BigInteger(cotizacion.getId()));
									cotizacionRespuesta.setFacturaNumero(respuesta.getClaseId());
									//cotizacionRespuesta.setFacturaId(facturaId);
									if(respuesta.getPoliza()!=null)
									{
										cotizacionRespuesta.setPolizaId(respuesta.getPoliza().getPolizaid());
										cotizacionRespuesta.setPolizaNumero(respuesta.getPoliza().getNumeroPoliza());
									}
									Date fechaEmision=new Date();
									cotizacionRespuesta.setFechaEmision(fechaEmision.toString());
									cotizacionRTrans.crear(cotizacionRespuesta);
									
									//Actualizo los campos de la cotizacion
									cotizacion.setEstado(estadoDAO.buscarPorNombre("Emitido","Cotizacion"));
									//cotizacion.setNumeroFactura(respuesta.getPoliza().getNumeroPoliza());
									cotizacion=cotizacionTransaction.editar(cotizacion);
								}
								else{
									
									/***TRATAMIENTO DE ERROR***/
									Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
									String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
									/***AUDITORIA auditamos el error para el seguimiento***/
									PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
									PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
									pymeAuditoriaGeneral.setTramite(CodError);
									pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
									pymeAuditoriaGeneral.setProceso("PYMES");
									if(respuesta.getErrorDTO()!=null)
										pymeAuditoriaGeneral.setObjeto("El sistema Ensurance detecto el siguiente problema: " + respuesta.getErrorDTO().getTexto());
									else
										pymeAuditoriaGeneral.setObjeto("El sistema Ensurance detecto un problema no identificado.");								
									
									try {
										pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
									} catch (Exception e1) {				
										e1.printStackTrace();
									}
									/***RESPUESTA A INTERFAZ***/
									result.put("success", Boolean.FALSE);
									result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
									result.put("autorizacion", Boolean.FALSE);
									if(respuesta.getErrorDTO()!=null)
										result.put("errorMesaje", "El sistema Ensurance detecto el siguiente problema: " + respuesta.getErrorDTO().getTexto());
									else
										result.put("errorMesaje", "El sistema Ensurance detecto un problema no identificado.");									
									result.put("ex","El sistema Ensurance detecto un problema");
									response.setContentType("application/json; charset=ISO-8859-1"); 
									result.write(response.getWriter());	
									return;
								}
							}
							else{
								nuevaSolucitud.setRespuesta("Sin respuesta desde Ensurance");
								solicitudEmisionTransaction.crear(nuevaSolucitud);															
								/***TRATAMIENTO DE ERROR***/
								Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
								String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
								/***AUDITORIA auditamos el error para el seguimiento***/
								PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
								PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
								pymeAuditoriaGeneral.setTramite(CodError);
								pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
								pymeAuditoriaGeneral.setProceso("PYMES");
								pymeAuditoriaGeneral.setObjeto("El sistema Ensurance detecto un problema al emitir la poliza.");
								try {
									pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
								} catch (Exception e1) {				
									e1.printStackTrace();
								}
								/***RESPUESTA A INTERFAZ***/
								result.put("success", Boolean.FALSE);
								result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
								result.put("autorizacion", Boolean.FALSE);
								result.put("errorMesaje", "El sistema Ensurance detecto un problema al emitir la poliza.");
								result.put("ex", "El sistema Ensurance detecto un problema al emitir la poliza.");
								response.setContentType("application/json; charset=ISO-8859-1"); 
								result.write(response.getWriter());
								return;
							}
						}
						catch(Exception ex){
							nuevaSolucitud.setRespuesta("Error de comunicación con el servicio de emisión en Ensurance.");
							solicitudEmisionTransaction.crear(nuevaSolucitud);
							/***TRATAMIENTO DE ERROR***/
							Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
							String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
							/***AUDITORIA auditamos el error para el seguimiento***/
							PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
							PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
							pymeAuditoriaGeneral.setTramite(CodError);
							pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
							pymeAuditoriaGeneral.setProceso("PYMES");
							pymeAuditoriaGeneral.setObjeto(ex.getMessage()+"||"+ex.getLocalizedMessage());
							try {
								pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
							} catch (Exception e1) {				
								e1.printStackTrace();
							}
							/***RESPUESTA A INTERFAZ***/
							result.put("success", Boolean.FALSE);
							result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
							result.put("autorizacion", Boolean.FALSE);
							result.put("errorMesaje", "Error de comunicación con el servicio de emisión en Ensurance.");
							result.put("ex", ex.getLocalizedMessage());
							response.setContentType("application/json; charset=ISO-8859-1"); 
							result.write(response.getWriter());
							ex.printStackTrace();
							return;
						}
						
					}
				}else{
					/***TRATAMIENTO DE ERROR***/
					Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
					String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
					/***AUDITORIA auditamos el error para el seguimiento***/
					PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
					PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
					pymeAuditoriaGeneral.setTramite(CodError);
					pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
					pymeAuditoriaGeneral.setProceso("PYMES");
					pymeAuditoriaGeneral.setObjeto("No esta dentro de las fechas de emisión revisar tabla de cierre de fin de mes.");
					try {
						pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
					} catch (Exception e1) {				
						e1.printStackTrace();
					}
					/***RESPUESTA A INTERFAZ***/
					result.put("success", Boolean.FALSE);
					result.put("error", "No se emitio ya que no se encuentra en fechas permitidas de emisión || codigo de Error: "+CodError);
					result.put("autorizacion", Boolean.FALSE);
					result.put("errorMesaje", "No se emitio ya que no se encuentra en fechas permitidas de emisión || codigo de Error: "+CodError);
					result.put("ex", "No se emitio ya que no se encuentra en fechas de permitidas emisión || codigo de Error: "+CodError);
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());
					return;
				}
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());

		}catch(Exception e){
			/***TRATAMIENTO DE ERROR***/
			Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
			String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
			/***AUDITORIA auditamos el error para el seguimiento***/
			PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
			PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral.setTramite(CodError);
			pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
			pymeAuditoriaGeneral.setProceso("PYMES");
			pymeAuditoriaGeneral.setObjeto(e.getMessage());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", ""+e.getMessage()+"|| Error código: "+CodError);
			result.put("errorMesaje", ""+e.getMessage()+"|| Error código: "+CodError);
			result.put("autorizacion", Boolean.FALSE);			
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

	public JSONArray consultarTodos(){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarTodos();

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizacion.get(i).getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto", pro.getNombre());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONArray consultarPorTipoObjeto(String fecha1, String fecha2, TipoObjeto tipoObjeto, String cotizacionId,String puntoVenta, String agenteId,String identificacion,String usuarioId,String estadoCotizacion){
		JSONObject retorno= new JSONObject();
		CotizacionDAO cDAO= new CotizacionDAO();	

		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjetoNoEmitidoxFecha(fecha1, fecha2, tipoObjeto,cotizacionId,puntoVenta,agenteId,identificacion,usuarioId, estadoCotizacion);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			ProductoXPuntoVentaDAO productoPorPuntoVentaDAO=new ProductoXPuntoVentaDAO();  
			ProductoXPuntoVenta productoXPuntoVenta= productoPorPuntoVentaDAO.buscarPorId(cotizacion.get(i).getProductoXPuntoVentaId().toString());
			GrupoPorProducto grupoPorProducto =productoXPuntoVenta.getGrupoPorProducto();

			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizacion.get(i).getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto",grupoPorProducto.getNombreComercialProducto());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	//Ganadero
	public JSONArray consultarPorTipoObjetoPorEstado(String fecha1, String fecha2, TipoObjeto tipoObjeto, String Estado){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizaciones=cDAO.buscarPorTipoObjetoPorEstadoxFecha(fecha1, fecha2, tipoObjeto, Estado);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		for(Cotizacion cotizacion: cotizaciones){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.getProducto().getId().toString());
			ProductoXPuntoVentaDAO productoPorPuntoVentaDAO=new ProductoXPuntoVentaDAO();  
			ProductoXPuntoVenta productoXPuntoVenta= productoPorPuntoVentaDAO.buscarPorId(cotizacion.getProductoXPuntoVentaId().toString());
			GrupoPorProducto grupoPorProducto =productoXPuntoVenta.getGrupoPorProducto();

			if(cotizacion.getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizacion.getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto",grupoPorProducto.getNombreComercialProducto());
				cotizacionJSONObject.put("estado", cotizacion.getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}
		return cotizacionJSONArray;
	}

	//Filtro para las ventana de cotizaciones
	public JSONArray consultarPorTipoObjetoParaPymes(String fecha1, String fecha2, TipoObjeto tipoObjeto, String cotizacionId,String puntoVenta, String agenteId,String identificacion,String usuarioId, String FiltroEstado){
		JSONObject retorno= new JSONObject();
		CotizacionDAO cDAO= new CotizacionDAO();	

		List<Cotizacion> cotizaciones=cDAO.buscarPorTipoObjetoParaCanal(fecha1, fecha2, tipoObjeto,cotizacionId,puntoVenta,agenteId,identificacion,usuarioId, FiltroEstado);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		for(Cotizacion cotizaActual:cotizaciones){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizaActual.getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizaActual.getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizaActual.getProducto().getId().toString());
			GrupoPorProductoDAO grupoPorProductoDAO=new GrupoPorProductoDAO();  
			GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizaActual.getGrupoPorProductoId().toString());

			if(cotizaActual.getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizaActual.getId());
				cotizacionJSONObject.put("punto_venta", cotizaActual.getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizaActual.getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizaActual.getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto",grupoPorProducto.getNombreComercialProducto());
				cotizacionJSONObject.put("estado", cotizaActual.getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizaActual.getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizaActual.getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizaActual.getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizaActual.getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizaActual.getPrimaNetaTotal());
				if(grupoPorProducto.getTieneMarca())
                    cotizacionJSONObject.put("tipo_producto", "Cerrado");
				else
                    cotizacionJSONObject.put("tipo_producto", "Dinamico");
			}
			cotizacionJSONArray.add(cotizacionJSONObject);
		}

		//retorno.put("numRegistros",i);
		retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONArray consultarPorTipoObjetoPuntoVenta(String fecha1, String fecha2, TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String numeroCotizacion,String agenteId,String identificacion,String usuarioId,String estadoCotizacion){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizaciones=cDAO.buscarPorTipoObjetoNoEmitidoxFechaPuntoVenta(fecha1, fecha2, tipoObjeto, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		for(Cotizacion cotizacionActual:cotizaciones){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacionActual.getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacionActual.getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacionActual.getProducto().getId().toString());
			
			GrupoPorProductoDAO grupoPorProductoDAO=new GrupoPorProductoDAO();  
			GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacionActual.getGrupoPorProductoId().toString());

			if(cotizacionActual.getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacionActual.getId());
				cotizacionJSONObject.put("punto_venta", cotizacionActual.getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacionActual.getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizacionActual.getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto",grupoPorProducto.getNombreComercialProducto());
				cotizacionJSONObject.put("estado", cotizacionActual.getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacionActual.getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacionActual.getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacionActual.getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacionActual.getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacionActual.getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	//PJacome
	public JSONArray consultarPorTipoObjetoEmitidos(String fecha1, String fecha2, TipoObjeto tipoObjeto){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizaciones=cDAO.buscarCotizacionxFecha(fecha1, fecha2,tipoObjeto);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		for(Cotizacion cotizaActual: cotizaciones){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizaActual.getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizaActual.getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizaActual.getProducto().getId().toString());
			if(cotizaActual.getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizaActual.getId());
				cotizacionJSONObject.put("punto_venta", cotizaActual.getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizaActual.getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizaActual.getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto", pro.getNombre());
				cotizacionJSONObject.put("estado", cotizaActual.getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizaActual.getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizaActual.getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizaActual.getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizaActual.getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizaActual.getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONObject encontrarPorId(String id){

		CotizacionDAO cotizacionDAO=new CotizacionDAO();
		Cotizacion cotizacion = cotizacionDAO.buscarPorId(id);
		JSONObject retorno= new JSONObject();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente= clienteDAO.buscarPorId(cotizacion.getClienteId().toString());

		AgenteDAO agenteDAO = new AgenteDAO();
		Agente agente= agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());

		SucursalDAO sucursalDAO = new SucursalDAO();
		List<Sucursal> sucursalArr= sucursalDAO.buscarActivos();

		JSONArray sucursales= new JSONArray(); 
		JSONObject sucursalJSON= new JSONObject();

		for(int i=0;i<sucursalArr.size();i++){
			sucursalJSON.put("nombre", sucursalArr.get(i).getNombre());
			sucursalJSON.put("id", sucursalArr.get(i).getId());
			sucursales.add(sucursalJSON);
		}

		retorno.put("sucursales", sucursales);

		//Aumento codigo para obtener el estado fzurita
		retorno.put("estadoCotizacion", cotizacion.getEstado().getNombre());

		//etapa 1
		retorno.put("estadoCotizacion", cotizacion.getEstado().getNombre());
		retorno.put("etapaWizard", cotizacion.getEtapaWizard()-1);

		JSONObject etapa1= new JSONObject();
		if(cotizacion.getEtapaWizard()>=1){
			etapa1.put("grupoProductos", cotizacion.getGrupoProductoId());
			etapa1.put("productos", cotizacion.getGrupoPorProductoId());
			etapa1.put("tasa", cotizacion.getTasaProductoId());
			etapa1.put("puntoVenta", cotizacion.getPuntoVenta().getId());
			etapa1.put("vigenciaPoliza", cotizacion.getVigenciaPoliza().getId());
			etapa1.put("agente", agente.getId());
			etapa1.put("porComisionAgente", cotizacion.getPorcentajeComision());
			etapa1.put("tipoIdentificacion", cliente.getEntidad().getTipoIdentificacion().getId() );
			etapa1.put("identificacion", cliente.getEntidad().getIdentificacion());
			etapa1.put("nombres", cliente.getEntidad().getNombres());
			etapa1.put("nombreCompleto", cliente.getEntidad().getNombreCompleto());
			etapa1.put("apellidos", cliente.getEntidad().getApellidos());
			etapa1.put("mail", cliente.getEntidad().getMail());
			etapa1.put("celular", cliente.getEntidad().getCelular());
			etapa1.put("telefono", cliente.getEntidad().getTelefono());
			etapa1.put("estado", cotizacion.getEstado().getNombre());

			GrupoPorProducto grupoPorProducto = new GrupoPorProducto();
			GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
			grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());

			ProductoXPuntoVenta productoXPuntoVenta =  new ProductoXPuntoVenta();
			ProductoXPuntoVentaDAO productoXPuntoVentaDAO =  new ProductoXPuntoVentaDAO();
			productoXPuntoVenta = (ProductoXPuntoVenta) productoXPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, cotizacion.getPuntoVenta());
			etapa1.put("unidadNegocioId", productoXPuntoVenta.getUnidadNegocio().getId());
			if (cotizacion.getSolicitudDescuentos().size() > 0)
				for (int j=0; j<cotizacion.getSolicitudDescuentos().size(); j++){
					SolicitudDescuento solicitudDescuento = cotizacion.getSolicitudDescuentos().get(j);
					etapa1.put("descuentoId", solicitudDescuento.getDescuento().getId());
				}
			else
				etapa1.put("descuentoId", "");
			retorno.put("etapa1", etapa1);
		}

		JSONObject etapa2= new JSONObject();
		if(cotizacion.getEtapaWizard()>=2){
			JSONObject objectDetalle= new JSONObject();
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);		
			JSONArray jsonDetallesDirecciones= new JSONArray(); 

			for(CotizacionDetalle detalleActual: detalles){
				objectDetalle.put("cotizacionDetalleId", detalleActual.getId());
				objectDetalle.put("sumaAsegurada", detalleActual.getSumaAseguradaItem());
				objectDetalle.put("primaNeta", detalleActual.getPrimaNetaItem());
				PymeObjetoCotizacionDAO objetoCotizacionDAO=new PymeObjetoCotizacionDAO();
				PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
				objectDetalle.put("provinciaId", objetoCotizacion.getProvinciaId());
				objectDetalle.put("cantonId", objetoCotizacion.getCiudadId());
				objectDetalle.put("callePrincipal", objetoCotizacion.getCallePrincipal());
				objectDetalle.put("numeroDireccion", objetoCotizacion.getNumeroDireccion());
				objectDetalle.put("calleSecundaria", objetoCotizacion.getCalleSecundaria());
				jsonDetallesDirecciones.add(objectDetalle);
			}

			JSONArray coberturasJSONArray = new JSONArray();
			JSONObject coberturaJSONObject = new JSONObject();
			PymeObjetoCotizacionCoberturaDAO objetoCCDAO=new PymeObjetoCotizacionCoberturaDAO();

			List<PymeObjetoCotizacionCobertura> listado=objetoCCDAO.buscarPorObjetoPymeId(new BigInteger(cotizacion.getId()));
			for(PymeObjetoCotizacionCobertura coberturaActual:listado){
				coberturaJSONObject.put("configuracionCoberturaId", coberturaActual.getObjetoOrigenId());
				coberturaJSONObject.put("tasaSugerida",coberturaActual.getTasaSugerida());
				coberturaJSONObject.put("tasaIngresada",coberturaActual.getTasaIngresada());
				coberturaJSONObject.put("valorIngresado",coberturaActual.getValorIngresado());
				coberturaJSONObject.put("primaCalculada",coberturaActual.getPrimaCalculada());
				coberturaJSONObject.put("tipoOrigen",coberturaActual.getTipoOrigen());
				coberturaJSONObject.put("textoDeducible",coberturaActual.getTextoCompletoDeducible());
				coberturaJSONObject.put("idsDeducible",coberturaActual.getIdsDeducible());
				coberturaJSONObject.put("valoresDeducible",coberturaActual.getValoresDeducible());
				coberturaJSONObject.put("textosDeducible",coberturaActual.getTextosDeducible());
				coberturaJSONObject.put("planId",coberturaActual.getPlanId());
				coberturasJSONArray.add(coberturaJSONObject);
			}

			etapa2.put("coberturas", coberturasJSONArray);
			etapa2.put("direcciones", jsonDetallesDirecciones);
			retorno.put("etapa2", etapa2);
		}


		//fin etapa2

		//etapa3
		if(cotizacion.getEtapaWizard()>=3){
			JSONObject etapa3= new JSONObject();
			JSONObject valoresCalculados = new JSONObject();

			valoresCalculados.put("valorPrima",cotizacion.getPrimaNetaTotal());
			valoresCalculados.put("valorAsegurado", 0);
			valoresCalculados.put("valorDerechosEmision", cotizacion.getImpDerechoEmision());
			valoresCalculados.put("valorSeguroCampesino", cotizacion.getImpSeguroCampesino());
			valoresCalculados.put("valorSuperBancos", cotizacion.getImpSuperBancos());
			valoresCalculados.put("valorSubTotal", cotizacion.getTotalFactura()-cotizacion.getImpIva());
			valoresCalculados.put("valorIva", cotizacion.getImpIva());
			valoresCalculados.put("valorTotal", cotizacion.getTotalFactura());

			etapa3.put("valoresCalculados", valoresCalculados);
			retorno.put("etapa3", etapa3);
		}
		//fin etapa 3

		//etapa 4
		if(cotizacion.getEtapaWizard()>=4){
			JSONObject etapa4= new JSONObject();

			JSONObject solicitudDescuento = new JSONObject();
			List<SolicitudDescuento> solicitudesDescuento = cotizacion.getSolicitudDescuentos();
			for(int i=0;i<solicitudesDescuento.size();i++){
				UsuarioDAO usuDAO=new UsuarioDAO();
				if (!solicitudesDescuento.get(i).getEstado().getNombre().toLowerCase().equals("eliminada")) {
					solicitudDescuento.put("descuentoId", solicitudesDescuento.get(i).getDescuento().getId());
					solicitudDescuento.put("porcentaje", solicitudesDescuento.get(i).getPorcentaje());
					solicitudDescuento.put("motivo", solicitudesDescuento.get(i).getMotivoDescuento().getId());
					solicitudDescuento.put("descripcion", solicitudesDescuento.get(i).getDescripcion());
					solicitudDescuento.put("estado", solicitudesDescuento.get(i).getEstado().getNombre());
					if (solicitudesDescuento.get(i).getUsuarioId() != null && !solicitudesDescuento.get(i).getUsuarioId().equals(""))
						solicitudDescuento.put("usuarioActualiza",usuDAO.buscarPorId(solicitudesDescuento.get(i).getUsuarioId() + "").getEntidad().getNombreCompleto());
				}
			}
			JSONArray cuotasJSONArray = new JSONArray();
			JSONObject cuotasJSONObject = new JSONObject();
			JSONObject formaPago = new JSONObject();
			JSONObject endosoBeneficiarioJSONObject = new JSONObject();

			Entidad asegurado=new Entidad(); 

			if(cotizacion.getAsegurado()!=null&&cotizacion.getAsegurado().getId()!=null){
				asegurado=cotizacion.getAsegurado();
				endosoBeneficiarioJSONObject.put("entidadId", asegurado.getId());
				endosoBeneficiarioJSONObject.put("identificacion", asegurado.getIdentificacion());
				endosoBeneficiarioJSONObject.put("tipoIdentificacion", asegurado.getTipoIdentificacion().getId());
				endosoBeneficiarioJSONObject.put("nombres", asegurado.getNombres());
				endosoBeneficiarioJSONObject.put("apellidos", asegurado.getApellidos());
				endosoBeneficiarioJSONObject.put("nombreCompleto", asegurado.getNombreCompleto());
			}

			JSONArray beneficiariosJSONArray = new JSONArray();
			JSONObject beneficiarioJSONObject = new JSONObject();
			
			PymeEndosoBeneficiarioDAO endosoBeneficiarioDAO=new PymeEndosoBeneficiarioDAO();
			List<PymeEndosoBeneficiario> endososBeneficiario=endosoBeneficiarioDAO.buscarPorCotizacion(new BigInteger(cotizacion.getId()));

			for(PymeEndosoBeneficiario endosoBeneficiarioActual:endososBeneficiario){
				beneficiarioJSONObject.put("endosoBeneficiarioId", endosoBeneficiarioActual.getId());
				beneficiarioJSONObject.put("beneficiarioId", endosoBeneficiarioActual.getBeneficiarioId());
				beneficiarioJSONObject.put("monto", endosoBeneficiarioActual.getMonto());
				beneficiarioJSONObject.put("rubro", endosoBeneficiarioActual.getRubro());
				
				beneficiariosJSONArray.add(beneficiarioJSONObject);
			}

			endosoBeneficiarioJSONObject.put("endososBeneficiario", beneficiariosJSONArray);


			//JSONObject solicitudInspeccion = new JSONObject();
			if(cotizacion.getPago() != null){
				formaPago.put("pagoId", cotizacion.getPago().getId());
				formaPago.put("formaPagoId", cotizacion.getPago().getFormaPago().getId());
				formaPago.put("valorTotal", cotizacion.getPago().getValorTotal());
				if(cotizacion.getPago().getCuotaInicial()==0)
					formaPago.put("plazo", cotizacion.getPago().getPlazonEnMes());
				else
					formaPago.put("plazo", Integer.parseInt(cotizacion.getPago().getPlazonEnMes())-1);

				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("DEBITO BANCARIO")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
					formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
					formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
					formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
					formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
					formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
					formaPago.put("cuotaInicial", cotizacion.getPago().getCuotaInicial());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					CuotaDAO cuotaDAO=new CuotaDAO();
					List<Cuota> cuotas = cuotaDAO.buscarPorPago(cotizacion.getPago());
					//formaPago.put("fechaDebito", df.format(cuotaDAO.buscarPorPago(cotizacion.getPago()).get(0).getFechaPago()));
					if(cuotas.size() > 0)
						formaPago.put("fechaDebito", df.format(cuotas.get(0).getFechaPago()));
				}

				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("DEBITO TARJETA")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
					formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
					formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
					formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
					formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
					formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
					formaPago.put("anioExpTarjeta", cotizacion.getPago().getAnioExpiracionTarjeta());
					formaPago.put("mesExpTarjeta", cotizacion.getPago().getMesExpiracionTarjeta());		
					formaPago.put("cuotaInicial", cotizacion.getPago().getCuotaInicial());
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					CuotaDAO cuotaDAO=new CuotaDAO();
					List<Cuota> cuotas = cuotaDAO.buscarPorPago(cotizacion.getPago());
					//formaPago.put("fechaDebito", df.format(cuotaDAO.buscarPorPago(cotizacion.getPago()).get(0).getFechaPago()));
					if(cuotas.size() > 0)
						formaPago.put("fechaDebito", df.format(cuotas.get(0).getFechaPago()));
				}

				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("CREDITO CUOTAS")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("cuotaInicial", cotizacion.getPago().getCuotaInicial());
					Cuota cuota = new Cuota();
					CuotaDAO cuotaDAO = new CuotaDAO();
					List <Cuota> listaCuotas= cuotaDAO.buscarPorPago(cotizacion.getPago());
					SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
					for (int i=0; i<listaCuotas.size(); i++){
						cuota = listaCuotas.get(i);
						cuotasJSONObject.put("cuotaValor", cuota.getValor());
						cuotasJSONObject.put("cuotaFechaPago", dt.format(cuota.getFechaPago().getTime()));
						cuotasJSONObject.put("cuotaNumCheque", cuota.getNumeroCheque());
						cuotasJSONObject.put("cuotaOrden", cuota.getOrden());
						if(cuota.getOrden()!=0)
							cuotasJSONArray.add(cuotasJSONObject);
					}

				}

				if (cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("CONTADO")) {
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					// formaPago.put("institucionFinancieraId",
					// cotizacion.getPago().getInstitucionFinanciera().getId());
					// formaPago.put("nombreTitular",
					// cotizacion.getPago().getNombreTitular());
					// formaPago.put("identificacionTitular",
					// cotizacion.getPago().getIdentificacionTitular());
					// formaPago.put("numCuentaTarjeta",
					// cotizacion.getPago().getNumeroCuentaTarjeta());
					// formaPago.put("tipoIdentificacion",
					// cotizacion.getPago().getTipoIdentificacionId().getId());
					// formaPago.put("tipoCuenta",
					// cotizacion.getPago().getTipoCuenta());
				}
			}
			/*if(cotizacion.getSolicitudInspeccions().size()>0){
				solicitudInspeccion.put("id", cotizacion.getSolicitudInspeccions().get(0).getId());
				solicitudInspeccion.put("destino", cotizacion.getSolicitudInspeccions().get(0).getDestino());
				solicitudInspeccion.put("origen", cotizacion.getSolicitudInspeccions().get(0).getOrigen());
				solicitudInspeccion.put("fechaSolicitud", cotizacion.getSolicitudInspeccions().get(0).getFechaSolicitud());
				solicitudInspeccion.put("inspector", cotizacion.getSolicitudInspeccions().get(0).getInspector().getNombre());
				solicitudInspeccion.put("telefonoContacto", cotizacion.getSolicitudInspeccions().get(0).getTelfContacto());
				solicitudInspeccion.put("estado", cotizacion.getSolicitudInspeccions().get(0).getEstado().getNombre());
				solicitudInspeccion.put("zona", cotizacion.getSolicitudInspeccions().get(0).getZona().getId());

			}*/

			//etapa3.put("solicitudInspeccion", solicitudInspeccion);
			etapa4.put("endosoBeneficiario", endosoBeneficiarioJSONObject);
			etapa4.put("listadoCuotas", cuotasJSONArray);
			etapa4.put("formaPago", formaPago);
			etapa4.put("solicitudDescuento", solicitudDescuento);

			retorno.put("etapa4", etapa4);
		}


		//datos FACTURA
		//etapa 4

		JSONObject etapa6=new JSONObject();
		TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
		TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("POLIZA COPIA DEVOLVER FIRMADO"); 
		DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
		List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
		DocumentoVisado dv=new DocumentoVisado();
		if(dvLista.size()>0)
			dv=dvLista.get(0);

		if(dv.getId()!=null)
			etapa6.put("tieneArchivoPolizaFirmada", "1");
		else
			etapa6.put("tieneArchivoPolizaFirmada", "0");

		tipoDocumento = tipoDocumentoDAO.buscarPorNombre("AUTORIZACION DEBITO"); 
		dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
		dv=new DocumentoVisado();
		if(dvLista.size()>0)
			dv=dvLista.get(0);

		if(dv.getId()!=null)
			etapa6.put("tieneArchivoAutorizacionDebito", "1");
		else
			etapa6.put("tieneArchivoAutorizacionDebito", "0");

		tipoDocumento = tipoDocumentoDAO.buscarPorNombre("FORMULARIO CONOCE A TU CLIENTE"); 
		dvLista=dvDAO.buscarPorEntidadTipoDocumento(cliente.getEntidad(), tipoDocumento);
		dv=new DocumentoVisado();
		if(dvLista.size()>0)
			dv=dvLista.get(0);

		if(dv.getId()!=null)
			etapa6.put("tieneArchivoFormularioUPLA", "1");
		else
			etapa6.put("tieneArchivoFormularioUPLA", "0");

		tipoDocumento = tipoDocumentoDAO.buscarPorNombre("CaratulaCotizacion"); 
		dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
		dv=new DocumentoVisado();
		if(dvLista.size()>0)
			dv=dvLista.get(0);

		if(dv.getId()!=null)
			etapa6.put("tieneArchivoCaratulaCotizacion", "1");
		else
			etapa6.put("tieneArchivoCaratulaCotizacion", "0");


		if(cotizacion.getNumeroTramite()!=null&&!cotizacion.getNumeroTramite().equals(""))
			etapa6.put("numeroTramite",cotizacion.getNumeroTramite());

		retorno.put("etapa6", etapa6);
		retorno.put("datosFacturaCliente",datosFactura(cliente.getEntidad()));
		if(cotizacion.getAsegurado()!=null&&cotizacion.getAsegurado().getId()!=null)
			retorno.put("datosFacturaAsegurado",datosFactura(cotizacion.getAsegurado()));

		return retorno;
	}

	public JSONObject getDatosUpla(Upla upla){
		JSONObject datosUPLA= new JSONObject();

		if(upla.getTipoCliente().equals("N")){
			if(upla.getLugarNacimientoNatural()!=null)
				datosUPLA.put("lugarNacimiento", upla.getLugarNacimientoNatural());
			if(upla.getFechaNacimientoNatural()!=null)
				datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoNatural());
			if(upla.getDireccion()!=null)
				datosUPLA.put("zonaDireccionCliente",upla.getDireccion().getZona().getNombre().charAt(0));
			if(upla.getDireccion()!=null)
				if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
					if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
						datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
					if(upla.getDireccion().getParroquia().getCanton()!=null)
						datosUPLA.put("cantonDireccionCliente", upla.getDireccion().getParroquia().getCanton().getId());
					if(upla.getDireccion().getParroquia()!=null)
						datosUPLA.put("parroquiaDireccionCliente", upla.getDireccion().getParroquia().getId());		
				}

			if(upla.getDireccion() != null)
				if(upla.getDireccion().getZona()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
						if(upla.getDireccion().getCiudad()!=null){
							datosUPLA.put("ciudadDireccionCliente", upla.getDireccion().getCiudad().getId());
							if(upla.getDireccion().getCiudad().getProvincia()!=null)
								datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getCiudad().getProvincia().getId());
						}
						if(upla.getDireccion().getCallePrincipal()!=null)
							datosUPLA.put("callePrincipalCliente",upla.getDireccion().getCallePrincipal());
						if(upla.getDireccion().getNumero()!=null)
							datosUPLA.put("numeroDireccionCliente",upla.getDireccion().getNumero());
						if(upla.getDireccion().getCalleSecundaria()!=null)
							datosUPLA.put("calleSecundariaCliente",upla.getDireccion().getCalleSecundaria());
						if(upla.getDireccion().getDatosDeReferencia()!=null)
							datosUPLA.put("referenciaDireccionCliente",upla.getDireccion().getDatosDeReferencia());
					}

			if(upla.getTelefonoNatural()!=null)
				datosUPLA.put("telefonoCliente",upla.getTelefonoNatural());
			if(upla.getCelularNatural()!=null)
				datosUPLA.put("celularCliente",upla.getCelularNatural());
			if(upla.getGeneroNatural()!=null)
				datosUPLA.put("generoCliente",upla.getGeneroNatural());
			if(upla.getEmailNatural()!=null)
				datosUPLA.put("mail",upla.getEmailNatural());
			if(upla.getCliente().getActividadEconomica()!=null)
				datosUPLA.put("actividadCliente",upla.getCliente().getActividadEconomica().getId());
			if(upla.getTipoActividadNatural()!=null)
				datosUPLA.put("tipoActividadCliente",upla.getTipoActividadNatural());
			if(upla.getCargoOcupaNatural()!=null)
				datosUPLA.put("cargoOcupaCliente",upla.getCargoOcupaNatural());
			if(upla.getRamo()!=null)
				datosUPLA.put("tipoRamoContrata",upla.getRamo().getId());
			datosUPLA.put("expuestoCliente",upla.getExpuestaPoliticamenteNatural());
			if(upla.getCargoDesempenaNatural()!=null)
				datosUPLA.put("cargoExpuestoCliente",upla.getCargoDesempenaNatural());
			datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
			if(upla.getParentescoFamiliarExpuesto()!=null)
				datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
			if(upla.getCargoFamiliarExpuesto()!=null)
				datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
			if(upla.getApellidoPaternoConyuge()!=null)
				datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
			if(upla.getApellidoMaternoConyuge()!=null)
				datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
			if(upla.getNombreConyuge()!=null)
				datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
			if(upla.getTipoIdentificacionIdConyuge()!=null)
				datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
			if(upla.getIdentificacionConyuge()!=null)
				datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
			datosUPLA.put("salarioMensual",upla.getSalarioMensual());
			datosUPLA.put("activos",upla.getActivos());
			datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
			datosUPLA.put("pasivos",upla.getPasivos());
			datosUPLA.put("egresos",upla.getEgresos());
			datosUPLA.put("patrimonio",upla.getPatrimonio());
			datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
			datosUPLA.put("esAsegurado",upla.getEsAsegurado());
			datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
			if(upla.getTipoIdentificacionIdAsegurado()!=null)
				datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
			if(upla.getIdentificacionAsegurado()!=null)
				datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
			if(upla.getNombreCompletoAsegurado()!=null)
				datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
			if(upla.getDireccionAsegurado()!=null)
				datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
			if(upla.getTelefonoAsegurado()!=null)
				datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
			if(upla.getCelularAsegurado()!=null)
				datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
			if(upla.getRelacionAsegurado()!=null)
				datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
			if(upla.getTipoIdentificacionIdBeneficiario()!=null)
				datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
			if(upla.getIdentificacionBeneficiario()!=null)
				datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
			if(upla.getNombreBeneficiario()!=null)
				datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
			if(upla.getDireccionBeneficiario()!=null)
				datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
			if(upla.getTelefonoBeneficiario()!=null)
				datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
			if(upla.getCelularBeneficiario()!=null)
				datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
			if(upla.getRelacionBeneficiario()!=null)
				datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());

		}

		if(upla.getTipoCliente().equals("J")){
			if(upla.getObjetoSocialJuridica()!=null)
				datosUPLA.put("objetoSocial", upla.getObjetoSocialJuridica());
			if(upla.getCiudadPaisJuridica()!=null)
				datosUPLA.put("ciudadJuridica", upla.getCiudadPaisJuridica());
			if(upla.getFechaNacimientoNatural()!=null)
				datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoRepresentanteLegal());
			if(upla.getDireccion().getZona()!=null)
				datosUPLA.put("zonaDireccionMatriz",upla.getDireccion().getZona().getNombre().charAt(0));
			if(upla.getDireccion().getZona()!=null)
				if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
					if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
						datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
					if(upla.getDireccion().getParroquia().getCanton()!=null)
						datosUPLA.put("cantonDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getId());
					if(upla.getDireccion().getParroquia()!=null)
						datosUPLA.put("parroquiaDireccionMatriz", upla.getDireccion().getParroquia().getId());		
				}
			if(upla.getDireccion().getZona().getNombre()!=null)
				if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
					if(upla.getDireccion().getCiudad().getProvincia()!=null)
						datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getCiudad().getProvincia().getId());
					if(upla.getDireccion().getCiudad()!=null)
						datosUPLA.put("ciudadDireccionMatriz", upla.getDireccion().getCiudad().getId());		
				}
			if(upla.getDireccion().getCallePrincipal()!=null)
				datosUPLA.put("callePrincipalMatriz",upla.getDireccion().getCallePrincipal());
			if(upla.getDireccion().getNumero()!=null)
				datosUPLA.put("numeroDireccionMatriz",upla.getDireccion().getNumero());
			if(upla.getDireccion().getCalleSecundaria()!=null)
				datosUPLA.put("calleSecundariaMatriz",upla.getDireccion().getCalleSecundaria());
			if(upla.getDireccion().getDatosDeReferencia()!=null)
				datosUPLA.put("referenciaDireccionMatriz",upla.getDireccion().getDatosDeReferencia());
			if(upla.getSucursalDireccionJuridica()!=null)
				datosUPLA.put("sucursalDireccion",upla.getSucursalDireccionJuridica());
			if(upla.getSucursalCiudadJuridica()!=null)
				datosUPLA.put("sucursalCiudad",upla.getSucursalCiudadJuridica());
			if(upla.getTelefonoEmpresa()!=null)
				datosUPLA.put("telefono",upla.getTelefonoEmpresa());
			if(upla.getFaxEmpresa()!=null)
				datosUPLA.put("fax",upla.getFaxEmpresa());
			if(upla.getCliente().getActividadEconomica()!=null)
				datosUPLA.put("actividadJuridica",upla.getCliente().getActividadEconomica().getId());
			if(upla.getNombresRepresentanteLegal()!=null)
				datosUPLA.put("nombresRepresentanteLegal",upla.getNombresRepresentanteLegal());
			if(upla.getApellidosRepresentanteLegal()!=null)
				datosUPLA.put("apellidosRepresentanteLegal",upla.getApellidosRepresentanteLegal());
			if(upla.getTipoIdentificacionIdRepresentanteLegal()!=null)
				datosUPLA.put("tipoIdentificacionRepresentante",upla.getTipoIdentificacionIdRepresentanteLegal());
			if(upla.getIdentificacionRepresentanteLegal()!=null)
				datosUPLA.put("identificacionRepresentante",upla.getIdentificacionRepresentanteLegal());
			if(upla.getLugarNacimientoRepresentanteLegal()!=null)
				datosUPLA.put("lugarNacimientoRepresentante",upla.getLugarNacimientoRepresentanteLegal());
			if(upla.getFechaNacimientoRepresentanteLegal()!=null)
				datosUPLA.put("fechaNacimientoRepresentante",upla.getFechaNacimientoRepresentanteLegal());
			if(upla.getDireccionRepresentanteLegal()!=null)
				datosUPLA.put("residenciaRepresentante",upla.getDireccionRepresentanteLegal());
			datosUPLA.put("paisRepresentante","");
			if(upla.getProvinciaIdRepresentanteLegal()!=null)
				datosUPLA.put("provinciaRepresentante",upla.getProvinciaIdRepresentanteLegal());
			if(upla.getCiudadIdRepresentanteLegal()!=null)
				datosUPLA.put("ciudadRepresentante",upla.getCiudadIdRepresentanteLegal());
			if(upla.getTelefonoRepresentanteLegal()!=null)
				datosUPLA.put("telefonoRepresentante",upla.getTelefonoRepresentanteLegal());
			if(upla.getCelularRepresentanteLegal()!=null)
				datosUPLA.put("celularRepresentante",upla.getCelularRepresentanteLegal());
			datosUPLA.put("expuestoRepresentante",upla.getExpuestaPoliticamenteNatural());
			if(upla.getCargoDesempenaNatural()!=null)
				datosUPLA.put("cargoExpuesta",upla.getCargoDesempenaNatural());
			datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
			if(upla.getParentescoFamiliarExpuesto()!=null)
				datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
			if(upla.getCargoFamiliarExpuesto()!=null)
				datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
			if(upla.getApellidoPaternoConyuge()!=null)
				datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
			if(upla.getApellidoMaternoConyuge()!=null)
				datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
			if(upla.getNombreConyuge()!=null)
				datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
			if(upla.getTipoIdentificacionIdConyuge()!=null)
				datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
			if(upla.getIdentificacionConyuge()!=null)
				datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
			datosUPLA.put("salarioMensual",upla.getSalarioMensual());
			datosUPLA.put("activos",upla.getActivos());
			datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
			datosUPLA.put("pasivos",upla.getPasivos());
			datosUPLA.put("egresos",upla.getEgresos());
			datosUPLA.put("patrimonio",upla.getPatrimonio());
			datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
			datosUPLA.put("esAsegurado",upla.getEsAsegurado());
			datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
			if(upla.getTipoIdentificacionIdAsegurado()!=null)
				datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
			if(upla.getIdentificacionAsegurado()!=null)
				datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
			if(upla.getNombreCompletoAsegurado()!=null)
				datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
			if(upla.getDireccionAsegurado()!=null)
				datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
			if(upla.getTelefonoAsegurado()!=null)
				datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
			if(upla.getCelularAsegurado()!=null)
				datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
			if(upla.getRelacionAsegurado()!=null)
				datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
			if(upla.getTipoIdentificacionIdBeneficiario()!=null)
				datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
			if(upla.getIdentificacionBeneficiario()!=null)
				datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
			if(upla.getNombreBeneficiario()!=null)
				datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
			if(upla.getDireccionBeneficiario()!=null)
				datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
			if(upla.getTelefonoBeneficiario()!=null)
				datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
			if(upla.getCelularBeneficiario()!=null)
				datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
			if(upla.getRelacionBeneficiario()!=null)
				datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());

		}
		return datosUPLA;
	}

	// Metodo eliminar un vehiculo a la cotizacionayanez
	public String eliminarVehiculoCotizacionDetalle(Cotizacion cotizacion, String vehiculoId){
		String result = "";
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(vehiculoId, cotizacion);
		cotizacionDetalleDAO.eliminar(cotizacionDetalle);
		result = "Se ha eliminado correctamente";
		return result;
	}
	
	public void enviarCertificado(String id,String correos,String casoEspecial, HttpServletRequest request){
		String [] correosArr=correos.split(",");
		String path="/static/reportes/CertificadosVehiculos/CertificadoCotizacion/certificadoVhc.jasper";
		if(casoEspecial.equals("1")||casoEspecial.equals("true"))
			path="/static/reportes/CertificadosVehiculos/CertificadoCotizacionCasosEspeciales/certificadoVhc.jasper";
		File reportFile = new File(getServletConfig().getServletContext().getRealPath(path));
		byte[] bytes = null;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("COTIZACION", id);
		String cuerpoMail = "";
		try
		{
			Reportes reporte=new Reportes();
			bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parametros, reporte.getConnection());
			for(int i=0;i<correosArr.length;i++){
				String rutaPlantilla = this.getServletContext().getRealPath("") + "/static/plantillas/correoEnvioCotizacion.html";
				FileReader fr = null;
				BufferedReader br = null;


				String link = request.getRequestURL().toString();

				try {
					File archivo = new File(rutaPlantilla);
					fr = new FileReader(archivo);
					br = new BufferedReader(fr);

					String linea;

					while ((linea = br.readLine()) != null) {
						cuerpoMail = cuerpoMail + linea;
					}
					cuerpoMail = cuerpoMail.replace("#urlImagenes#",
							link.replace("/CotizacionController", ""));

					cuerpoMail = cuerpoMail.replace("#urlCotizador#",
							link.replace("/CotizacionController", ""));


				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Utilitarios.envioMailPDFAdjunto(correosArr[i], "Certificado Cotizacion "+id, cuerpoMail, bytes);
			}
		}
		catch (JRException e)
		{

		}

	}

	public JSONArray cargarInspectoresInternos(){
		JSONArray retorno=new JSONArray();
		InspectorDAO iDAO = new InspectorDAO();
		TipoInspectorDAO tipoInspectorDAO = new TipoInspectorDAO();
		TipoInspector tipoInspector = tipoInspectorDAO.buscarPorId("1");
		List<Inspector> inspectores=iDAO.buscarPorTipo(tipoInspector) ;
		for(Inspector inspectorActual:inspectores){
			JSONObject inspector=new JSONObject();

			inspector.put("id",inspectorActual.getId());
			inspector.put("nombre",inspectorActual.getNombre());
			inspector.put("sucursal",inspectorActual.getSucursal().getNombre());
			retorno.add(inspector);
		}

		return retorno;
	}

	public JSONArray consultarPorTipoObjetoPuntoDeVenta(TipoObjeto tipoObjeto,PuntoVenta puntoVenta){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjetoPuntoVenta(tipoObjeto,puntoVenta);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto", pro.getNombre());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONArray consultarPorEstadoPuntoVenta(Estado estado,PuntoVenta puntoVenta){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorEstadoPuntoVenta(estado, puntoVenta);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto", pro.getNombre());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONArray consultarPorEstado(Estado estado){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorEstado(estado);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto", pro.getNombre());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public JSONArray consultarPorTipoObjetoPuntoVentaYEstado(String fecha1, String fecha2, TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String numeroCotizacion,String agenteId,String identificacion,String usuarioId, String FiltroEstado){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjetoxFechaPuntoVentaYEstado(fecha1, fecha2, tipoObjeto, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,FiltroEstado);

		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());

			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			ProductoDAO proDAO= new ProductoDAO();
			Producto pro = proDAO.buscarPorId(cotizacion.get(i).getProducto().getId().toString());
			ProductoXPuntoVentaDAO productoPorPuntoVentaDAO=new ProductoXPuntoVentaDAO();  
			ProductoXPuntoVenta productoXPuntoVenta= productoPorPuntoVentaDAO.buscarPorId(cotizacion.get(i).getProductoXPuntoVentaId().toString());
			GrupoPorProducto grupoPorProducto =productoXPuntoVenta.getGrupoPorProducto();

			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null&&pro.getId()!=null){
				cotizacionJSONObject.put("codigo", cotizacion.get(i).getId());
				cotizacionJSONObject.put("punto_venta", cotizacion.get(i).getPuntoVenta().getNombre());
				cotizacionJSONObject.put("vigencia_poliza", cotizacion.get(i).getVigenciaPoliza().getValor());
				cotizacionJSONObject.put("cliente", cli.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("vendedor", cotizacion.get(i).getUsuario().getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("agente", age.getEntidad().getNombreCompleto());
				cotizacionJSONObject.put("producto",grupoPorProducto.getNombreComercialProducto());
				cotizacionJSONObject.put("estado", cotizacion.get(i).getEstado().getNombre());
				cotizacionJSONObject.put("tipo_objeto", cotizacion.get(i).getTipoObjeto().getNombre());
				cotizacionJSONObject.put("fecha_elaboracion", cotizacion.get(i).getFechaElaboracion().toString());
				cotizacionJSONObject.put("por_comision", cotizacion.get(i).getPorcentajeComision());
				cotizacionJSONObject.put("suma_total", cotizacion.get(i).getSumaAseguradaTotal());
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	public String guardarBeneficiarioAseguradoCotizacion(HttpServletRequest request, HttpServletResponse response){
		String tipoIdentificacion = request.getParameter("tipoIdentificacion") == null ? "" : request.getParameter("tipoIdentificacion");
		String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
		String nombres = request.getParameter("nombres") == null ? "" : request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos");
		String nombreCompleto = request.getParameter("nombreCompleto") == null ? "" : request.getParameter("nombreCompleto");
		String callePrincipal = request.getParameter("callePrincipal") == null ? "" : request.getParameter("callePrincipal");
		String numero = request.getParameter("numero") == null ? "" : request.getParameter("numero");
		String calleSecundaria = request.getParameter("calleSecundaria") == null ? "" : request.getParameter("calleSecundaria");
		String idEnsurance = request.getParameter("idEnsurance") == null ? "" : request.getParameter("idEnsurance");

		EntidadDAO entidadDAO=new EntidadDAO();
		EntidadTransaction entidadTransaction = new EntidadTransaction();

		Entidad entidad= entidadDAO.buscarEntidadPorIdentificacion(identificacion);

		TipoIdentificacionDAO tipoIdentificacionDAO=new TipoIdentificacionDAO();

		TipoIdentificacion tipoId = new TipoIdentificacion();

		if(tipoIdentificacion!=null&&!tipoIdentificacion.equals(""))
			tipoId=tipoIdentificacionDAO.buscarPorId(tipoIdentificacion);

		entidad.setTipoIdentificacion(tipoId);		

		if(!idEnsurance.equals(""))
			entidad.setEntEnsurance(idEnsurance);

		if(entidad.getId()==null)
			entidad=entidadTransaction.crear(entidad);
		else
			entidad=entidadTransaction.editar(entidad);

		DireccionDAO direccionDAO=new DireccionDAO();

		Direccion direccion=new Direccion();

		if( direccionDAO.buscarCobroPorEntidadId(entidad).size()>0)
			direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(0);
		direccion.setCallePrincipal(callePrincipal);
		direccion.setCalleSecundaria(calleSecundaria);
		direccion.setEntidad(entidad);
		direccion.setEsCobro(true);
		direccion.setNumero(numero);
		//direccion.se

		entidad.setApellidos(apellidos.toUpperCase());
		entidad.setNombres(nombres.toUpperCase());
		entidad.setNombreCompleto(nombreCompleto.toUpperCase());
		//entidad.setTipoIdentificacion(tipoIdentificacion);		

		return "";
	}

	public JSONObject datosFactura (Entidad entidad){
		JSONObject retorno=new JSONObject();
		if(entidad!=null&&entidad.getId()!=null){
			retorno.put("nombre", entidad.getNombres());
			retorno.put("apellido", entidad.getApellidos());
			retorno.put("nombreCompleto", entidad.getNombreCompleto());
			retorno.put("identificacion", entidad.getIdentificacion());
			retorno.put("tipoIdentificacion", entidad.getTipoIdentificacion().getId());
			retorno.put("telefono", entidad.getTelefono());
			retorno.put("celular", entidad.getCelular());
			retorno.put("email", entidad.getMail());
			DireccionDAO dDAO=new DireccionDAO(); 
			List<Direccion> direcciones=dDAO.buscarPorEntidadId(entidad);

			if(direcciones.size()>0){
				if(direcciones.get(0)!=null){
					Direccion direccion=direcciones.get(0);

					if(direcciones.get(0).getZona()!=null)
						retorno.put("zona", direcciones.get(0).getZona().getId());

					if(direccion.getZona().getId().equals("1")){//urbana

						if(direcciones.get(0).getCiudad()!=null){
							retorno.put("ciudad", direcciones.get(0).getCiudad().getId());

						if(direcciones.get(0).getCiudad().getProvincia()!=null)
							retorno.put("provincia", direcciones.get(0).getCiudad().getProvincia().getId());
						}

						if(direcciones.get(0).getCallePrincipal()!=null)
							retorno.put("callePrincipal", direcciones.get(0).getCallePrincipal());

						if(direcciones.get(0).getCalleSecundaria()!=null)
							retorno.put("calleSecundaria", direcciones.get(0).getCalleSecundaria());

						if(direcciones.get(0).getDatosDeReferencia()!=null)
							retorno.put("datosReferencia", direcciones.get(0).getDatosDeReferencia());

						if(direcciones.get(0).getNumero()!=null)
							retorno.put("numero", direcciones.get(0).getNumero());
					}

					if(direccion.getZona().getId().equals("2")){//rural

						if(direcciones.get(0).getParroquia()!=null){
							retorno.put("parroquia", direcciones.get(0).getParroquia().getId());

						if(direcciones.get(0).getParroquia().getCanton()!=null)
							retorno.put("canton", direcciones.get(0).getParroquia().getCanton().getId());

						if(direcciones.get(0).getParroquia().getCanton().getProvincia()!=null)
							retorno.put("provincia", direcciones.get(0).getParroquia().getCanton().getProvincia().getId());
						}

						if(direcciones.get(0).getCallePrincipal()!=null)
							retorno.put("callePrincipal", direcciones.get(0).getCallePrincipal());

						if(direcciones.get(0).getCalleSecundaria()!=null)
							retorno.put("calleSecundaria", direcciones.get(0).getCalleSecundaria());

						if(direcciones.get(0).getDatosDeReferencia()!=null)
							retorno.put("datosReferencia", direcciones.get(0).getDatosDeReferencia());

						if(direcciones.get(0).getNumero()!=null)
							retorno.put("numero", direcciones.get(0).getNumero());
					}
				}
			}
		}
		return retorno;
	}	

	public String generarHtmlCotizacion(String cotizacionID, String plantilla){

		try
		{
			PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
			PymeTextoCoberturaCotizacionDAO textoCoberturaDAO=new PymeTextoCoberturaCotizacionDAO();
			PymeCoberturaCotizacionValorDAO coberturaValorDAO=new PymeCoberturaCotizacionValorDAO();
			CotizacionDAO cotizacionDAO=new CotizacionDAO();
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			ClienteDAO clienteDAO=new ClienteDAO();
			AgenteDAO agenteDAO=new AgenteDAO();
			ActividadEconomicaDAO actividadDAO=new ActividadEconomicaDAO();
	
			Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionID);
	
			//Obtengo el cliente que cotizo
			Cliente cliente=clienteDAO.buscarPorId(String.valueOf(cotizacion.getClienteId()));
	
			//Obtengo los datos del agente
			Agente agente=agenteDAO.buscarPorId(String.valueOf(cotizacion.getAgenteId()));
	
			String rutaPlantilla = this.getServletContext().getRealPath("") + plantilla;
			String html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
	
			double subtotal=Double.parseDouble(cotizacion.getPrimaNetaTotal())+cotizacion.getImpSuperBancos()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpRecargoSeguroCampesino()+cotizacion.getImpDerechoEmision();
			java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
			parametersHeader.put("CotizacionId", cotizacionID);
			parametersHeader.put("PuntoVenta", cotizacion.getPuntoVenta().getNombre());
			String fechaFormat = new SimpleDateFormat("dd-MM-yyyy").format(cotizacion.getFechaElaboracion());
			parametersHeader.put("FechaCotizacion", fechaFormat);
			parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
			if(cliente.getEntidad().getTelefono()!=null)
				parametersHeader.put("TelefonoCliente", cliente.getEntidad().getTelefono());
			else
				parametersHeader.put("TelefonoCliente", "");
			parametersHeader.put("BrokerNombre", agente.getEntidad().getNombreCompleto());
	
			//Aquí poner las direcciones
			String HtmlDirecciones="<table style='width: 100%'>";
			int ContadorDirecciones=1;
			PymeObjetoCotizacion objetoCotizacion=new PymeObjetoCotizacion();
			List<CotizacionDetalle> detallesCotizacion=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
			for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
				objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
				String  actividadEconomicaNombre="";
				if(objetoCotizacion.getActividadEconomicaId()!=null){
					ActividadEconomica actividadEconomica=actividadDAO.buscarPorId(objetoCotizacion.getActividadEconomicaId().toString());
					actividadEconomicaNombre=actividadEconomica.getNombre();
				}
				String DireccionCompleta=objetoCotizacion.getCallePrincipal()+" "+objetoCotizacion.getNumeroDireccion()+" "+objetoCotizacion.getCalleSecundaria();
				HtmlDirecciones+="<tr><td colspan='2' style='font-family: Verdana; font-size: small; font-weight: bold'>RIESGO "+
						+ContadorDirecciones+":</td></tr>";
	
				HtmlDirecciones+="<tr><td style='font-family: Verdana; font-size: small; width:60%'>"
						+DireccionCompleta
						+"</td><td style='font-family: Verdana; font-size: small; width:40%'>"
						+actividadEconomicaNombre
						+"</td></tr>";
				ContadorDirecciones++;
			}
			HtmlDirecciones+="</table>";
			parametersHeader.put("DireccionesAseguradas", HtmlDirecciones);
	
			//Para ver el componente financiero de compensación
			double respuesta [] = {0,0};
			double valorIVA = 0.0;
			double compensacion =2.0;
			double valorCompensacion =0.0;
			double valorIVATotal = 0.0;
			
			respuesta = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);				
								
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		    Date fechaNuevoIVA = (Date) formatoFecha.parse("01/06/2016");				   					
			if(cotizacion.getFechaElaboracion().before(new Timestamp(fechaNuevoIVA.getTime()))){
				valorIVATotal = cotizacion.getImpIva();
				valorIVA=12.00;
			}else{
				if(respuesta[0] == 12.00){						
					valorCompensacion = (cotizacion.getImpIva()*compensacion)/14;
					valorIVATotal = (cotizacion.getImpIva()*12)/14; 
					valorIVA=12.00;
				}else{
					valorIVATotal = cotizacion.getImpIva();
					valorIVA=14.00;
				}	
			}												
			      
			valorCompensacion = Math.round(valorCompensacion*100.0)/100.0;
			valorIVATotal = Math.round(valorIVATotal*100.0)/100.0;
	
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			
			parametersHeader.put("ValorPrimaNeta", formatter.format(Double.parseDouble(cotizacion.getPrimaNetaTotal())));
			parametersHeader.put("ValorSuperintendenciaBancos", formatter.format(cotizacion.getImpSuperBancos()).toString());
			parametersHeader.put("ValorSeguroCampesino", formatter.format(cotizacion.getImpSeguroCampesino()).toString());
			parametersHeader.put("ValorDerechos", formatter.format(cotizacion.getImpDerechoEmision()).toString());
			parametersHeader.put("ValorSubtotal", formatter.format(subtotal).toString());
			parametersHeader.put("ValorIva", formatter.format(valorIVATotal).toString());
			parametersHeader.put("ValorCompensacionIva", formatter.format(valorCompensacion).toString());
			parametersHeader.put("PorcetajeIva", ""+valorIVA);
			parametersHeader.put("PorcetajeCompensacion", ""+compensacion);
			parametersHeader.put("ValorTotal", formatter.format(cotizacion.getTotalFactura()).toString());
	
	
			//Obtener la coberturas de seleccionada en la cotizacion
			textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(),""+ cotizacion.getGrupoPorProductoId());
			//comentado por proceso de optimizacion
			//List<PymeTextoGrupoCoberturaCotizacion> listadoCoberturas=textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionId(new BigInteger(cotizacion.getId()), cotizacion.getGrupoPorProductoId());
			List<PymeTextoGrupoCoberturaCotizacion>listadoCoberturas=textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(),""+ cotizacion.getGrupoPorProductoId());
			
			
			String HtmlValoresCoberturas="";
			String HtmlTextoCoberturasAdicionales="<table>";
			for(PymeTextoGrupoCoberturaCotizacion coberturaTexto:listadoCoberturas){
				//Recupero las coberturas del ramo y la cotización
				//Comento por Optimizacion
				//List<PymeCoberturaCotizacionValor> coberturasValor=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
				List<PymeCoberturaCotizacionValor> coberturasValor=coberturaValorDAO.buscarValorCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(), ""+coberturaTexto.getRamoId());
				
				
				
				if(coberturaTexto.getRamoId().equals(new BigInteger("7208961"))){ //Determino si es el ramo incendio ya que el tratamiento es diferente
					double valorPrimaCobertura=0;
					double valorAseguradoCobertura=0;
					
					HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
					for(PymeCoberturaCotizacionValor coberturaDetalleActual: coberturasValor){
						//if(coberturaDetalleActual.getTipoCoberturaId().toString().equals("3"))
						//{
							HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaDetalleActual.getNombre()+"  ";
							if(coberturaDetalleActual.getValorIngresado()==0)
								if(coberturaDetalleActual.getValorMaximoLimiteAsegurado()==0)
									HtmlTextoCoberturasAdicionales+=" - Al "+coberturaDetalleActual.getPorcentajeLimiteAsegurado()+"% de Incendio";
								else
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorMaximoLimiteAsegurado());
							else
								HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorIngresado());
							HtmlTextoCoberturasAdicionales+="</td></tr>";
						//}
						valorPrimaCobertura+=coberturaDetalleActual.getPrimaCalculada();					
					}
					//Recorro las direcciones y voy poniendo los valores de cada dirección y voy armando los textos de las coberturas
					ContadorDirecciones=1;
					HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='4' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
							+"font-size: small; font-weight: bold'>"
							+coberturaTexto.getNombre()
							+"</td></tr>";
					
					for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
						objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
						
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
								+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
								+"RIESGO " +ContadorDirecciones
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
								+"</td></tr>";
						if(objetoCotizacion.getValorEstructuras()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Estructuras:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEstructuras())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorEstructuras();
						}
						if(objetoCotizacion.getValorContenidos()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Contenidos:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorContenidos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorContenidos();
						}
						if(objetoCotizacion.getValorMueblesEnseres()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Muebles, enseres y equipos de oficina:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMueblesEnseres())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorMueblesEnseres();
						}
						if(objetoCotizacion.getValorMaquinaria()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Maquinaria:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMaquinaria())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorMaquinaria();
						}
						if(objetoCotizacion.getValorMercaderia()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Mercadería:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMercaderia())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorMercaderia();
						}
						if(objetoCotizacion.getValorInsumosNoelectronicos()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Insumos Médicos y/o de Laboratorio (no electrónicos):</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorInsumosNoelectronicos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorInsumosNoelectronicos();
						}
						if(objetoCotizacion.getValorEquipoHerramienta()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Equipos y herramientas:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEquipoHerramienta())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorEquipoHerramienta();
						}
						//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
						/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(cotizaDetalle.getId()), coberturaTexto.getRamoId());
						for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
							if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
							{
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
								HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							}
						}*/
						ContadorDirecciones++;
					}
					//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
					/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
					for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
						if(coberturaDireccion.getTipoOrigen().equals("GENERAL") || coberturaDireccion.getTipoOrigen().equals("ADICIONALES")){
							if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
							{
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
								HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							}
						}
					}*/
					HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";
					HtmlValoresCoberturas+= "</table>";
				}
				else{ //Si no es incendio
					double valorPrimaCobertura=0;
					double valorAseguradoCobertura=0;
					ContadorDirecciones=1;
					HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='4' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
							+"font-size: small; fot-wenight: bold'>"
							+coberturaTexto.getNombre()
							+"</td></tr>";
					for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
						int contadorDirecciones = 0;
						for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
							if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
								contadorDirecciones++;
						}
						if(contadorDirecciones>0){
							
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
									+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO " + ContadorDirecciones
									+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
									+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
									+"</td></tr>";
							HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
							HtmlTextoCoberturasAdicionales+="<tr><td colspan='2'>RIESGO " + ContadorDirecciones+"</td></tr>";
							for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
								if(coberturaValor.getTipoOrigen().equals("POR DIRECCION")==false)
								{
									HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"  ";
									if(coberturaValor.getValorIngresado()==0)
										HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorMaximoLimiteAsegurado());
									else
										HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorIngresado());
									HtmlTextoCoberturasAdicionales+="</td></tr>";
								}
								if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
								{
									HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"</td><td>"+formatter.format(coberturaValor.getValorIngresado())+"</td></tr>";
									HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaValor.getNombre()+":</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaValor.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
									valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
									valorAseguradoCobertura+=coberturaValor.getValorIngresado();
								}
							}
						}
						ContadorDirecciones++;
					}
	
					//Hacemos la tabla de las coberturas generales
					int contadorCoberturasGenerales=0;
					for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
						if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger("0")))
							contadorCoberturasGenerales++;
					}
					if(contadorCoberturasGenerales>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
								+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO"
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
								+"</td></tr>";
						HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
						HtmlTextoCoberturasAdicionales+="<tr><td colspan='2'>RIESGO</td></tr>";
						for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
							HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"  ";
							if(coberturaValor.getValorIngresado()==0)
								HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorMaximoLimiteAsegurado());
							else
								HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorIngresado());
							HtmlTextoCoberturasAdicionales+="</td></tr>";
							valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
							valorAseguradoCobertura+=coberturaValor.getValorIngresado();
						}
					}
					if(valorPrimaCobertura>0 || valorAseguradoCobertura>0)
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";
	
					HtmlValoresCoberturas+="</table>";
				}
	
			}
			HtmlTextoCoberturasAdicionales+="</table>";
			
			
			PymeObjetoCotizacionCoberturaDAO objetoCCDAO=new PymeObjetoCotizacionCoberturaDAO();
			PymeAsistenciaDAO asistenciaDAO=new PymeAsistenciaDAO();
			List<PymeObjetoCotizacionCobertura> asistenciasCotizacion=objetoCCDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()), "ASISTENCIA");
			String HtmlAsistencias="<table width='100%'>";
			for(PymeObjetoCotizacionCobertura asistenciaCotizacion:asistenciasCotizacion){
				PymeAsistencia asistenciaActual=asistenciaDAO.buscarPorId(asistenciaCotizacion.getObjetoOrigenId());
				if(asistenciaActual!=null){
					HtmlAsistencias+="<tr><td style='font-weight: bold'>";
					HtmlAsistencias+=asistenciaActual.getNombre();
					HtmlAsistencias+="</td></tr>";
					if(asistenciaActual.getTexto()!=null){
						String cadena=new String(asistenciaActual.getTexto());
						HtmlAsistencias+="<tr><td>";
						HtmlAsistencias+=cadena;
						HtmlAsistencias+="</td></tr>";
					}
				}
			}
			HtmlAsistencias+="</table>";
			
			//Armo la tablas para los deducibles
			/*PymeDeducibleCotizacionValorDAO deducibleDAO=new PymeDeducibleCotizacionValorDAO();
			List<PymeDeducibleCotizacionValor> deduciblesCotizacion=deducibleDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
			
			String HtmlDeducibles="<table width='100%'>";
			for(PymeDeducibleCotizacionValor deducibleCotizacion:deduciblesCotizacion){
				if(!deducibleCotizacion.getTextoCompletoDeducible().equals("")){
					HtmlDeducibles+="<tr><td style='font-weight: bold'>";
					HtmlDeducibles+=deducibleCotizacion.getNombre();
					HtmlDeducibles+="</td></tr>";
					HtmlDeducibles+="<tr><td>";
					HtmlDeducibles+=deducibleCotizacion.getTextoCompletoDeducible();
					HtmlDeducibles+="</td></tr>";
				}
			}*/
			
			PymeDeducibleCotizacionValorDAO deducibleDAO=new PymeDeducibleCotizacionValorDAO();
			List<PymeDeducibleCotizacionValor> deduciblesCotizacion=deducibleDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
			
			/*ajuse l.caiza quitar del reporte deducibles repetidos*/
			List<PymeDeducibleCotizacionValor> all = deduciblesCotizacion;//this is the object that you have already  and filled it.
			List<PymeDeducibleCotizacionValor> noRepeat= new ArrayList<PymeDeducibleCotizacionValor>();

		    for (PymeDeducibleCotizacionValor al: all) 
		    {
		        boolean isPresent = false;
		        // check if the current objects subtitle already exists in noRepeat
		        for (PymeDeducibleCotizacionValor nr : noRepeat) 
		       {
		            if (nr.getTextoCompletoDeducible().equals(al.getTextoCompletoDeducible()) && 
		            		nr.getNombre().equals(al.getNombre()))
		              {
		            	isPresent = true;//yes we have already
		                 break;
		              }
		        }

		        if (!isPresent) noRepeat.add(al);//we are adding if we don't have already
		    }
			
			String HtmlDeducibles="<table width='100%'>";
			for(PymeDeducibleCotizacionValor deducibleCotizacion:noRepeat){
				if(!deducibleCotizacion.getTextoCompletoDeducible().equals("")){
					HtmlDeducibles+="<tr><td style='font-weight: bold'>";
					HtmlDeducibles+=deducibleCotizacion.getNombre();
					HtmlDeducibles+="</td></tr>";
					HtmlDeducibles+="<tr><td>";
					HtmlDeducibles+=deducibleCotizacion.getTextoCompletoDeducible();
					HtmlDeducibles+="</td></tr>";
				}
			}
			//fin de ajuste
			HtmlDeducibles+="</table>";
			
			parametersHeader.put("ValoresCoberturas", HtmlValoresCoberturas);
	
			parametersHeader.put("HtmlAsistencias", HtmlAsistencias);
			
			parametersHeader.put("HtmlDeducibles", HtmlDeducibles);
			
			parametersHeader.put("HtmlCoberturas", HtmlTextoCoberturasAdicionales);
	
			parametersHeader.put("NombreUsuario", cotizacion.getUsuario().getEntidad().getNombreCompleto());
			if(cotizacion.getUsuario().getEntidad().getMail()!=null)
				parametersHeader.put("EmailUsuario", cotizacion.getUsuario().getEntidad().getMail());
			else
				parametersHeader.put("EmailUsuario", "");
			
			//Poner la tabla de pagos
			double valor=cotizacion.getTotalFactura();
			String htmTablaPagos="<table width='100%' style='border-style: solid'><tr><td style='font-weight: bold'>Cuotas.</td><td style='font-weight: bold'>Valor</td></tr>";
			for(int i=1;i<=10;i++){
				double cuota=valor/i;
				if(i==1 || i==3 || i==5 || i==7 || i==9)
					htmTablaPagos+="<tr><td style='background-color: #CCCCCC;'>"+i+"</td><td style='background-color: #CCCCCC;'>"+formatter.format(cuota)+"</td></tr>";
				else
					htmTablaPagos+="<tr><td>"+i+"</td><td>"+formatter.format(cuota)+"</td></tr>";
			}
			htmTablaPagos+="</table>";
			parametersHeader.put("TablaDePagos", htmTablaPagos);
			
			/**RUTA DE IMAGENES l.caiza**/

			String rutaPrincipal = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();//tomamos la ruta de la clase
			rutaPrincipal=rutaPrincipal.replace("AgriSucreNotificacionErrores.class", "");//quitamos el nombre de la clase
			//ruta del logo
			String rutaRelativaImagenLogo=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"logo_pymes_color.png";
			String rutaFinalFirma=rutaPrincipal+rutaRelativaImagenLogo;
			parametersHeader.put("Logo", "file:///"+rutaFinalFirma);//enviamos como parametros la imagen
			//ruta del mapa
			String rutaRelativaImagenMapa=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"logoMapa.png";
			String rutaFinalMapa=rutaPrincipal+rutaRelativaImagenMapa;
			parametersHeader.put("Mapa", "file:///"+rutaFinalMapa);
			//ruta del pie de pagina
			String rutaRelativaPiePagina=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"fondoPie.png";
			String rutaFinalPie=rutaPrincipal+rutaRelativaPiePagina;
			parametersHeader.put("Pie", "file:///"+rutaFinalPie);
			//ruta del pie de logo Nova
			String rutaRelativaPieNova=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"logonova.png";
			String rutaFinalNova=rutaPrincipal+rutaRelativaPieNova;
			parametersHeader.put("Nova", "file:///"+rutaFinalNova);	
			String htmlGenerado=GenerarContenido(html, parametersHeader);
				
			return htmlGenerado;
		}
		catch(Exception ex){
			
		}
		return "";
	}
	
	public String generarHtml(String cotizacionID, String plantilla, Boolean borrador){

		try
		{
			PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
			PymeTextoCoberturaCotizacionDAO textoCoberturaDAO=new PymeTextoCoberturaCotizacionDAO();
			PymeCoberturaCotizacionValorDAO coberturaValorDAO=new PymeCoberturaCotizacionValorDAO();
			PymeDeducibleCotizacionValorDAO deducibleValorDAO=new PymeDeducibleCotizacionValorDAO();
			CotizacionDAO cotizacionDAO=new CotizacionDAO();
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			ClienteDAO clienteDAO=new ClienteDAO();
			AgenteDAO agenteDAO=new AgenteDAO();
			ActividadEconomicaDAO actividadDAO=new ActividadEconomicaDAO();
	
			Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionID);
	
			//Obtengo el cliente que cotizo
			Cliente cliente=clienteDAO.buscarPorId(String.valueOf(cotizacion.getClienteId()));
	
			//Obtengo los datos del agente
			Agente agente=agenteDAO.buscarPorId(String.valueOf(cotizacion.getAgenteId()));
	
			String html="";
			
			String rutaPlantilla = this.getServletContext().getRealPath("") + plantilla;
			html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			
	
			double subtotal=Double.parseDouble(cotizacion.getPrimaNetaTotal())+cotizacion.getImpSuperBancos()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpRecargoSeguroCampesino()+cotizacion.getImpDerechoEmision();
			java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
			if(borrador)
				parametersHeader.put("CotizacionId", cotizacionID);
			else{
				CotizacionRespuestaDAO cotizacionRespuestaDAO=new CotizacionRespuestaDAO();
				CotizacionRespuesta cotizacionRespuesta=cotizacionRespuestaDAO.buscarPorCotizacionId(new BigInteger(cotizacionID));
				if(cotizacionRespuesta.getPolizaNumero()!=null)
					parametersHeader.put("CotizacionId", cotizacionRespuesta.getPolizaNumero());
				else
					parametersHeader.put("CotizacionId", cotizacionID);
			}
			parametersHeader.put("PuntoVenta", cotizacion.getPuntoVenta().getNombre());
			String fechaFormat = new SimpleDateFormat("dd-MM-yyyy").format(cotizacion.getFechaElaboracion());
			parametersHeader.put("FechaCotizacion", fechaFormat);
			parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
			if(cliente.getEntidad().getTelefono()!=null)
				parametersHeader.put("TelefonoCliente", cliente.getEntidad().getTelefono());
			else
				parametersHeader.put("TelefonoCliente", "");
			parametersHeader.put("BrokerNombre", agente.getEntidad().getNombreCompleto());
			
			
			
			Date fechaA=new Date();
			String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(fechaA);
			
			parametersHeader.put("SucursalNombre", cotizacion.getPuntoVenta().getSucursal().getNombre());
			parametersHeader.put("FechaActual", fechaActual);
			
			
			//Determino la vigencia desde hasta
			if(cotizacion.getVigenciaDesde()!=null){
				String fechaVigenciaD = new SimpleDateFormat("dd-MM-yyyy").format(cotizacion.getVigenciaDesde());
				parametersHeader.put("VigenciaDesde", fechaVigenciaD);
				Calendar c = Calendar.getInstance();
				c.setTime(cotizacion.getVigenciaDesde());
				c.add(Calendar.YEAR, new Integer(cotizacion.getVigenciaPoliza().getValor().toString()));
				Date vigenciaHasta= c.getTime();
				String fechaVigenciaH = new SimpleDateFormat("dd-MM-yyyy").format(vigenciaHasta);
				parametersHeader.put("VigenciaHasta", fechaVigenciaH);
			}
			else
			{
				parametersHeader.put("VigenciaDesde", "");
				parametersHeader.put("VigenciaHasta", "");
			}
			
	
			//Aquí poner las direcciones
			String HtmlDirecciones="<table style='width: 100%'>";
			int ContadorDirecciones=1;
			PymeObjetoCotizacion objetoCotizacion=new PymeObjetoCotizacion();
			List<CotizacionDetalle> detallesCotizacion=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
			for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
				objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
				String  actividadEconomicaNombre="";
				if(objetoCotizacion.getActividadEconomicaId()!=null){
					ActividadEconomica actividadEconomica=actividadDAO.buscarPorId(objetoCotizacion.getActividadEconomicaId().toString());
					actividadEconomicaNombre=actividadEconomica.getNombre();
				}
				String DireccionCompleta=objetoCotizacion.getCallePrincipal()+" "+objetoCotizacion.getNumeroDireccion()+" "+objetoCotizacion.getCalleSecundaria();
				HtmlDirecciones+="<tr><td colspan='2' style='font-family: Verdana; font-size: small; font-weight: bold'>RIESGO "+
						+ContadorDirecciones+":</td></tr>";
	
				HtmlDirecciones+="<tr><td style='font-family: Verdana; font-size: small; width:60%'>"
						+DireccionCompleta
						+"</td><td style='font-family: Verdana; font-size: small; width:40%'>"
						+actividadEconomicaNombre
						+"</td></tr>";
				ContadorDirecciones++;
			}
			HtmlDirecciones+="</table>";
			parametersHeader.put("DireccionesAseguradas", HtmlDirecciones);
	
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			
			//Para ver el componente financiero de compensación
			double respuesta [] = {0,0};
			double valorIVA = 0.0;
			double compensacion =2.0;
			double valorCompensacion =0.0;
			double valorIVATotal = 0.0;
			
			respuesta = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);				
								
			DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		    Date fechaNuevoIVA = (Date) formatoFecha.parse("01/06/2016");				   					
			if(cotizacion.getFechaElaboracion().before(new Timestamp(fechaNuevoIVA.getTime()))){
				valorIVATotal = cotizacion.getImpIva();
			}else{
				if(respuesta[0] == 12.00){						
					valorCompensacion = (cotizacion.getImpIva()*compensacion)/14;
					valorIVATotal = (cotizacion.getImpIva()*12)/14; 
				}else{
					valorIVATotal = cotizacion.getImpIva();
				}	
			}												
			      
			valorCompensacion = Math.round(valorCompensacion*100.0)/100.0;
			valorIVATotal = Math.round(valorIVATotal*100.0)/100.0;
	
			parametersHeader.put("ValorPrimaNeta", formatter.format(Double.parseDouble(cotizacion.getPrimaNetaTotal())));
			parametersHeader.put("ValorSuperintendenciaBancos", formatter.format(cotizacion.getImpSuperBancos()).toString());
			parametersHeader.put("ValorSeguroCampesino", formatter.format(cotizacion.getImpSeguroCampesino()).toString());
			parametersHeader.put("ValorDerechos", formatter.format(cotizacion.getImpDerechoEmision()).toString());
			parametersHeader.put("ValorSubtotal", formatter.format(subtotal).toString());
			parametersHeader.put("ValorIva", formatter.format(valorIVATotal).toString());
			parametersHeader.put("ValorCompensacionIva", formatter.format(valorCompensacion).toString());
			parametersHeader.put("PorcetajeIva", ""+valorIVA);
			parametersHeader.put("PorcetajeCompensacion", ""+compensacion);
			parametersHeader.put("ValorTotal", formatter.format(cotizacion.getTotalFactura()).toString());
	
	
			//Obtener la coberturas de seleccionada en la cotizacion
			textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(),""+ cotizacion.getGrupoPorProductoId());
			//comentado por proceso de optimizacion
			//List<PymeTextoGrupoCoberturaCotizacion> listadoCoberturas=textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionId(new BigInteger(cotizacion.getId()), cotizacion.getGrupoPorProductoId());
			List<PymeTextoGrupoCoberturaCotizacion>listadoCoberturas=textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(),""+ cotizacion.getGrupoPorProductoId());
			
			
			String HtmlCoberturas="";
			String HtmlValoresCoberturas="";
			for(PymeTextoGrupoCoberturaCotizacion coberturaTexto:listadoCoberturas){
				//Recupero los deducibles del ramo y la cotización
				List<PymeDeducibleCotizacionValor> deduciblesValor=deducibleValorDAO.buscarPorCotizacionIdRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
				//Recupero las coberturas del ramo y la cotización
				//Comento por Optimizacion
				//List<PymeCoberturaCotizacionValor> coberturasValor=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
				List<PymeCoberturaCotizacionValor> coberturasValor=coberturaValorDAO.buscarValorCoberturaCotizacionPorCotizacionIdSP(cotizacion.getId(), ""+coberturaTexto.getRamoId());
				if(coberturaTexto.getRamoId().equals(new BigInteger("7208961"))){ //Determino si es el ramo incendio ya que el tratamiento es diferente
					double valorPrimaCobertura=0;
					double valorAseguradoCobertura=0;
					
					String HtmlTextoCoberturasAdicionales="<ul>";
					for(PymeCoberturaCotizacionValor coberturaDetalleActual: coberturasValor){
						//if(coberturaDetalleActual.getTipoCoberturaId().toString().equals("3")){
						if(coberturaDetalleActual.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false){
							HtmlTextoCoberturasAdicionales+="<li>"+coberturaDetalleActual.getNombre()+"  ";
							if(coberturaDetalleActual.getValorIngresado()==0)
								if(coberturaDetalleActual.getValorMaximoLimiteAsegurado()==0)
									HtmlTextoCoberturasAdicionales+=" - Al "+coberturaDetalleActual.getPorcentajeLimiteAsegurado()+"% de Incendio";
								else
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorMaximoLimiteAsegurado());
							else
								HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorIngresado());
							HtmlTextoCoberturasAdicionales+="</li>";
						}
						valorPrimaCobertura+=coberturaDetalleActual.getPrimaCalculada();
					}
					HtmlTextoCoberturasAdicionales+="</ul>";
					
					//Recorro las direcciones y voy poniendo los valores de cada dirección y voy armando los textos de las coberturas
					ContadorDirecciones=1;
					String HtmlTextoCoberturas="";
					//VARIABLE QUE CONTIENE EL HTML DEL LOS VALORES CUADRO SUPERIOS 
					HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='3' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
							+"font-size: small; font-weight: bold'>"
							+coberturaTexto.getNombre()
							+"</td></tr>";
					HtmlTextoCoberturas="<table width='100%'>";
					Boolean boolEstructuras=false;
					Boolean boolMuebles=false;
					Boolean boolMaquinaria=false;
					Boolean boolMercaderia=false;
					Boolean boolInsumos=false;
					Boolean boolEquipos=false;
					for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
						objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
						HtmlTextoCoberturas+="<tr><td style='font-weight: bold'>"
								+"RIESGO " +ContadorDirecciones
								+"</td><td></td></tr>";
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:50%; font-weight: bold'>"
								+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:25%; font-weight: bold'>"
								+"RIESGO " +ContadorDirecciones
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:25%; font-weight: bold'>PRIMA NETA"
								+"</td></tr>";
						if(objetoCotizacion.getValorEstructuras()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Estructuras:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEstructuras())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Estructuras:</td><td>"+formatter.format(objetoCotizacion.getValorEstructuras())+"</td></tr>";
							boolEstructuras=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorEstructuras();
						}
						if(objetoCotizacion.getValorMueblesEnseres()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Muebles, enseres y equipos de oficina:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMueblesEnseres())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Muebles, enseres y equipos de oficina:</td><td>"+formatter.format(objetoCotizacion.getValorMueblesEnseres())+"</td></tr>";
							boolMuebles=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorMueblesEnseres();
						}
						if(objetoCotizacion.getValorMaquinaria()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Maquinaria:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMaquinaria())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Maquinaria:</td><td>"+formatter.format(objetoCotizacion.getValorMaquinaria())+"</td></tr>";
							boolMaquinaria=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorMaquinaria();
						}
						if(objetoCotizacion.getValorMercaderia()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Mercadería:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMercaderia())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Mercadería:</td><td>"+formatter.format(objetoCotizacion.getValorMercaderia())+"</td></tr>";
							boolMercaderia=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorMercaderia();
						}
						if(objetoCotizacion.getValorInsumosNoelectronicos()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Insumos Médicos y/o de Laboratorio (no electrónicos):</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorInsumosNoelectronicos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Insumos Médicos y/o de Laboratorio (no electrónicos):</td><td>"+formatter.format(objetoCotizacion.getValorInsumosNoelectronicos())+"</td></tr>";
							boolInsumos=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorInsumosNoelectronicos();
						}
						if(objetoCotizacion.getValorEquipoHerramienta()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Equipos y herramientas:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEquipoHerramienta())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Equipos y herramientas:</td><td>"+formatter.format(objetoCotizacion.getValorEquipoHerramienta())+"</td></tr>";
							boolEquipos=true;
							valorAseguradoCobertura+=objetoCotizacion.getValorEquipoHerramienta();
						}
						if(objetoCotizacion.getValorContenidos()>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Contenidos:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorContenidos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							HtmlTextoCoberturas+="<tr><td>Contenidos:</td><td>"+formatter.format(objetoCotizacion.getValorContenidos())+"</td></tr>";
							valorAseguradoCobertura+=objetoCotizacion.getValorContenidos();
						}
						
						//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
						/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(cotizaDetalle.getId()), coberturaTexto.getRamoId());
						for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
							if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
							{
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
								HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							}
						}*/
						
						ContadorDirecciones++;
					}
					//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
					/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
					for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
						if(coberturaDireccion.getTipoOrigen().equals("GENERAL") || coberturaDireccion.getTipoOrigen().equals("ADICIONALES")){
							if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
							{
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
								HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
							}
						}
					}*/
					HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";
					HtmlValoresCoberturas+= "</table>";
	
					HtmlTextoCoberturas+= "</table>";
					
					/*ajuse l.caiza quitar del reporte deducibles repetidos*/
					List<PymeDeducibleCotizacionValor> all = deduciblesValor;//this is the object that you have already  and filled it.
					List<PymeDeducibleCotizacionValor> noRepeat= new ArrayList<PymeDeducibleCotizacionValor>();

				    for (PymeDeducibleCotizacionValor al: all) 
				    {
				        boolean isPresent = false;
				        // check if the current objects subtitle already exists in noRepeat
				        for (PymeDeducibleCotizacionValor nr : noRepeat) 
				       {
				            if (nr.getTextoCompletoDeducible().equals(al.getTextoCompletoDeducible()) && 
				            		nr.getNombre().equals(al.getNombre()))
				              {
				            	isPresent = true;//yes we have already
				                 break;
				              }
				        }

				        if (!isPresent) noRepeat.add(al);//we are adding if we don't have already
				    }
					
				    deduciblesValor.clear();
				    deduciblesValor=noRepeat;
					
					//Recorro los deducibles
					String deduciblesPorCobertura="<table width='100%'>";
					for(PymeDeducibleCotizacionValor deducibleActual: deduciblesValor){
						if(!deducibleActual.getTextoCompletoDeducible().equals("")){
							deduciblesPorCobertura+="<tr><td style='font-weight: bold'>";
							deduciblesPorCobertura+=deducibleActual.getNombre();
							deduciblesPorCobertura+="</td></tr>";
							deduciblesPorCobertura+="<tr><td style='font-weight: normal'>";
							deduciblesPorCobertura+=deducibleActual.getTextoCompletoDeducible();
							deduciblesPorCobertura+="</td></tr>";
						}
					}
					deduciblesPorCobertura+="</table>";
	
					//Determino el texto del objeto asegurado
					String HtmlTextoObjetoAsegurado="";
					if(boolEstructuras)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+"Edificios,";
					if(boolMaquinaria)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+" maquinaria y equipos,";
					if(boolInsumos)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+" insumos no electrónicos,";
					if(boolMuebles)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+" muebles y enseres,";
					if(boolMercaderia)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+" inventario de mercadería,";
					if(boolEquipos)
						HtmlTextoObjetoAsegurado=HtmlTextoObjetoAsegurado+" equipos y herramientas,";
					
					java.util.Hashtable<String, String> parameters = new java.util.Hashtable<String, String>();
					parameters.put("ObjetoAsegurado", HtmlTextoObjetoAsegurado);
					parameters.put("TablaBienesAsegurados", HtmlTextoCoberturas);
					parameters.put("TablaCoberturasAdicionales", HtmlTextoCoberturasAdicionales);
					parameters.put("DeduciblesPorCobertura", deduciblesPorCobertura);
	
					String cadena=new String(coberturaTexto.getTexto());
					HtmlCoberturas+=GenerarContenido(cadena, parameters);
					
				}
				else{ //Si no es incendio
					double valorPrimaCobertura=0;
					double valorAseguradoCobertura=0;
					ContadorDirecciones=1;
					HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='3' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
							+"font-size: small; fot-wenight: bold'>"
							+coberturaTexto.getNombre()
							+"</td></tr>";
					String HtmlTextoCoberturas="<table width='100%'>";
					for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
						int contadorDirecciones = 0;
						for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
							if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
								contadorDirecciones++;
						}
						if(contadorDirecciones>0){
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
									+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO " + ContadorDirecciones
									+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
									+"</td></tr>";
							HtmlTextoCoberturas+="<tr><td colspan='2'>RIESGO " + ContadorDirecciones;
							HtmlTextoCoberturas+="</td></tr>";
							for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
								
								if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
								{
									HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaValor.getNombre()+":</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaValor.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
									HtmlTextoCoberturas+="<tr><td>"+coberturaValor.getNombre()+"</td><td>"+formatter.format(coberturaValor.getValorIngresado())+"</td></tr>";
									valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
									valorAseguradoCobertura+=coberturaValor.getValorIngresado();
								}
							}
						}
						ContadorDirecciones++;
					}
	
					//Hacemos la tabla de las coberturas generales
					String HtmlTextoCoberturasAdicionales="<ul>";
					int contadorCoberturasGenerales=0;
					for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
						if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger("0")))
							contadorCoberturasGenerales++;
					}
					if(contadorCoberturasGenerales>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
								+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO"
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
								+"</td></tr>";
						HtmlTextoCoberturas+="<tr><td colspan='2'>RIESGO</td></tr>";
						for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
							if(coberturaValor.getTipoCoberturaId().toString().equals("3")){
								HtmlTextoCoberturasAdicionales+="<li>"+coberturaValor.getNombre()+"  ";
								if(coberturaValor.getValorIngresado()==0)
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorMaximoLimiteAsegurado());
								else
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorIngresado());
								HtmlTextoCoberturasAdicionales+="</li>";
							}
							if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger("0")))
							{
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaValor.getNombre()+":</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaValor.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
								HtmlTextoCoberturas+="<tr><td>"+coberturaValor.getNombre()+"</td><td>"+formatter.format(coberturaValor.getValorIngresado())+"</td></tr>";
								valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
								valorAseguradoCobertura+=coberturaValor.getValorIngresado();
							}
						}
						HtmlTextoCoberturasAdicionales+="</ul>";
						
						
					}
					if(valorPrimaCobertura>0 || valorAseguradoCobertura>0)
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";
					HtmlTextoCoberturas+="</table>";
	
					HtmlValoresCoberturas+="</table>";
					
					/*ajuse l.caiza quitar del reporte deducibles repetidos*/
					List<PymeDeducibleCotizacionValor> all = deduciblesValor;//this is the object that you have already  and filled it.
					List<PymeDeducibleCotizacionValor> noRepeat= new ArrayList<PymeDeducibleCotizacionValor>();

				    for (PymeDeducibleCotizacionValor al: all) 
				    {
				        boolean isPresent = false;
				        // check if the current objects subtitle already exists in noRepeat
				        for (PymeDeducibleCotizacionValor nr : noRepeat) 
				       {
				            if (nr.getTextoCompletoDeducible().equals(al.getTextoCompletoDeducible()) && 
				            		nr.getNombre().equals(al.getNombre()))
				              {
				            	isPresent = true;//yes we have already
				                 break;
				              }
				        }

				        if (!isPresent) noRepeat.add(al);//we are adding if we don't have already
				    }
					
				    deduciblesValor.clear();
				    deduciblesValor=noRepeat;
				    
					//Recorro los deducibles
					String deduciblesPorCobertura="<table width='100%'>";
					for(PymeDeducibleCotizacionValor deducibleActual: deduciblesValor){
						if(!deducibleActual.getTextoCompletoDeducible().equals("")){
							deduciblesPorCobertura+="<tr><td style='font-weight: bold'>";
							deduciblesPorCobertura+=deducibleActual.getNombre();
							deduciblesPorCobertura+="</td></tr>";
							deduciblesPorCobertura+="<tr><td style='font-weight: normal'>";
							deduciblesPorCobertura+=deducibleActual.getTextoCompletoDeducible();
							deduciblesPorCobertura+="</td></tr>";
						}
					}
					deduciblesPorCobertura+="</table>";
	
					java.util.Hashtable<String, String> parameters = new java.util.Hashtable<String, String>();
					parameters.put("TablaBienesAsegurados", HtmlTextoCoberturas);
					parameters.put("TablaSumaAsegurada", HtmlTextoCoberturas);
					parameters.put("TablaCoberturasAdicionales", HtmlTextoCoberturasAdicionales);
					parameters.put("DeduciblesPorCobertura", deduciblesPorCobertura);
					String cadena=new String(coberturaTexto.getTexto());
					HtmlCoberturas+=GenerarContenido(cadena, parameters);
				}
	
			}
			
			//Recupero las asistencias esto es solo para incendio
			String HtmlAsistencias="";
			PymeObjetoCotizacionCoberturaDAO objetoCCDAO=new PymeObjetoCotizacionCoberturaDAO();
			PymeAsistenciaDAO asistenciaDAO=new PymeAsistenciaDAO();
			List<PymeObjetoCotizacionCobertura> asistenciasCotizacion=objetoCCDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()), "ASISTENCIA");
			HtmlAsistencias="<table width='100%'>";
			for(PymeObjetoCotizacionCobertura asistenciaCotizacion:asistenciasCotizacion){
				PymeAsistencia asistenciaActual=asistenciaDAO.buscarPorId(asistenciaCotizacion.getObjetoOrigenId());
				if(asistenciaActual!=null){
					HtmlAsistencias+="<tr><td style='font-weight: bold'>- ";
					HtmlAsistencias+=asistenciaActual.getNombre();
					HtmlAsistencias+="</td></tr>";
					if(asistenciaActual.getTexto()!=null){
						String cadena=new String(asistenciaActual.getTexto());
						HtmlAsistencias+="<tr><td>";
						HtmlAsistencias+=cadena;
						HtmlAsistencias+="</td></tr>";
					}
				}
			}
			HtmlAsistencias+="</table>";
			parametersHeader.put("AsistenciasAdicionales", HtmlAsistencias);
			
			parametersHeader.put("ValoresCoberturas", HtmlValoresCoberturas);
	
			parametersHeader.put("HtmlCoberturas", HtmlCoberturas);
			
			/**RUTA DE IMAGENES l.caiza**/
			String rutaPrincipal = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();//tomamos la ruta de la clase
			rutaPrincipal=rutaPrincipal.replace("AgriSucreNotificacionErrores.class", "");//quitamos el nombre de la clase
	
			//ruta del logo
			String rutaRelativaImagenLogo=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"logo_pymes_color.png";
			String rutaFinalFirma=rutaPrincipal+rutaRelativaImagenLogo;
			parametersHeader.put("Logo", "file:///"+rutaFinalFirma);//enviamos como parametros la imagen
			
			//ruta del mapa
			String rutaRelativaImagenFirma=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"images"+File.separator+"FirmaAseguradora.png";
			String rutaFinalFirmaDigital=rutaPrincipal+rutaRelativaImagenFirma;
			parametersHeader.put("Firma", "file:///"+rutaFinalFirmaDigital);
			
			String htmlGenerado=GenerarContenido(html, parametersHeader);
	
			return htmlGenerado;
		}
		catch(Exception ex){
			
		}
		return "";
	}
	
	
	public String generarHtmlEndosoBeneficiario(Cotizacion cotizacion, PymeEndosoBeneficiario endosoBeneficiario, String plantilla, String nombreRamo){

		PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
		ClienteDAO clienteDAO=new ClienteDAO();
		AgenteDAO agenteDAO=new AgenteDAO();
		ProvinciaDAO provinciaDAO=new ProvinciaDAO();
		CantonDAO cantonDAO=new CantonDAO();

		//Obtengo el cliente que cotizo
		Cliente cliente=clienteDAO.buscarPorId(String.valueOf(cotizacion.getClienteId()));

		//Obtengo los datos del agente
		Agente agente=agenteDAO.buscarPorId(String.valueOf(cotizacion.getAgenteId()));

		String html="";
		try{
			String rutaPlantilla = this.getServletContext().getRealPath("") + plantilla;
			html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
		}
		catch(IOException ex){
		}

		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();

		CotizacionRespuestaDAO cotizacionRespuestaDAO=new CotizacionRespuestaDAO();
		CotizacionRespuesta cotizacionRespuesta=cotizacionRespuestaDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
		if(cotizacionRespuesta.getPolizaNumero()!=null)
			parametersHeader.put("PolizaNumero", cotizacionRespuesta.getPolizaNumero());
		else
			parametersHeader.put("PolizaNumero", cotizacion.getId());
		
		parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
		parametersHeader.put("AgenteNombre", agente.getEntidad().getNombreCompleto());
		
		Date fechaA=new Date();
		String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(fechaA);
		
		parametersHeader.put("SucursalNombre", cotizacion.getPuntoVenta().getSucursal().getNombre());
		parametersHeader.put("FechaActual", fechaActual);
		
		
		//Determino la vigencia desde hasta
		if(cotizacion.getVigenciaDesde()!=null){
			String fechaVigenciaD = new SimpleDateFormat("dd-MM-yyyy").format(cotizacion.getVigenciaDesde());
			parametersHeader.put("VigenciaDesde", fechaVigenciaD);
			Calendar c = Calendar.getInstance();
			c.setTime(cotizacion.getVigenciaDesde());
			c.add(Calendar.YEAR, new Integer(cotizacion.getVigenciaPoliza().getValor().toString()));
			Date vigenciaHasta= c.getTime();
			String fechaVigenciaH = new SimpleDateFormat("dd-MM-yyyy").format(vigenciaHasta);
			parametersHeader.put("VigenciaHasta", fechaVigenciaH);
		}
		else
		{
			parametersHeader.put("VigenciaDesde", "");
			parametersHeader.put("VigenciaHasta", "");
		}

		//Aquí poner las direcciones
		String HtmlDirecciones="<table style='width: 100%'>";
		int ContadorDirecciones=1;
		PymeObjetoCotizacion objetoCotizacion=new PymeObjetoCotizacion();
		List<CotizacionDetalle> detallesCotizacion=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
		for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
			objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
			Provincia provincia= provinciaDAO.buscarPorId(""+objetoCotizacion.getProvinciaId());
			Canton canton=cantonDAO.buscarPorId(objetoCotizacion.getCiudadId().toString());
			String provinciaNombre="";
			if(provincia.getId()!=null)
				provinciaNombre="PROVINCIA - "+provincia.getNombre()+" - ";
			String cantonNombre="";
			if(canton.getId()!=null)
				cantonNombre=canton.getNombre()+" - ";
			String DireccionCompleta=provinciaNombre+cantonNombre+objetoCotizacion.getCallePrincipal()+" "+objetoCotizacion.getNumeroDireccion()+" "+objetoCotizacion.getCalleSecundaria();
			HtmlDirecciones+="<tr><td colspan='2' style='font-family: Verdana; font-size: small; font-weight: bold'>UBICACION DEL RIESGO "+
					+ContadorDirecciones+":</td></tr>";

			HtmlDirecciones+="<tr><td style='font-family: Verdana; font-size: x-small; width:60%'>"
					+DireccionCompleta
					+"</td></tr>";
			ContadorDirecciones++;
		}
		HtmlDirecciones+="</table>";
		parametersHeader.put("UbicacionesRiesgo", HtmlDirecciones);

		parametersHeader.put("RamoNombre", nombreRamo);

		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		BeneficiarioDAO beneficiarioDAO=new BeneficiarioDAO();
		Beneficiario beneficiarioActual=beneficiarioDAO.buscarPorId(endosoBeneficiario.getBeneficiarioId().toString());
		parametersHeader.put("BeneficiarioNombre", beneficiarioActual.getNombre());
		parametersHeader.put("BeneficiarioMonto", formatter.format(endosoBeneficiario.getMonto()));
		

		//Armo el tabla de rubros
		String RubroValorBeneficiario="<table><tr><td>";
		String NombreRubro="";
		switch(endosoBeneficiario.getRubro()){
			case 1:
				NombreRubro="Estructuras";
				break;
			case 2:
				NombreRubro="Muebles y Enseres";
				break;
			case 3:
				NombreRubro="Maquinaria";
				break;
			case 4:
				NombreRubro="Mercaderia";
				break;
			case 5:
				NombreRubro="Insumos(No electrónicos)";
				break;
			case 6:
				NombreRubro="Equipos y Herramientas";
				break;
		}
		RubroValorBeneficiario+=NombreRubro+"</td><td>"+formatter.format(endosoBeneficiario.getMonto())+"</td>";
		RubroValorBeneficiario+="</tr></table>";
		
		parametersHeader.put("RubroValorBeneficiario", RubroValorBeneficiario);
		
		String htmlGenerado=GenerarContenido(html, parametersHeader);

		return htmlGenerado;
	}
	
	private String GenerarContenido(String html, java.util.Hashtable<String, String> ParamValues){
		List<String> detectedParams = new ArrayList<String>();
		Pattern params=Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Matcher mat=params.matcher(html);
		while(mat.find()) {
			detectedParams.add(mat.group());
		}
		for(String detectedParam:detectedParams)
		{
			String valor=ParamValues.get(detectedParam.replace("[", "").replace("]", ""));
			html=html.replace(detectedParam,  valor);
		}
		return html;
	}

	private byte[] GenerarPDF(String html, String CotizacionId){
		java.io.ByteArrayOutputStream out = null;

		//FileOutputStream out = null;
		try {
			CYaHPConverter converter = new CYaHPConverter(false);

			List headerFooterList = new ArrayList();

			// cabecera y pie de página
			//headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("", IHtmlToPdfTransformer.CHeaderFooter.HEADER));

			headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("Página: <pagenumber>/<pagecount><hr />", IHtmlToPdfTransformer.CHeaderFooter.FOOTER));


			Map properties = new HashMap();

			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);

			// Soporte para fuentes
			properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, "c:\\Windows\\Fonts");

			//File fout = new File("D:\\Archivos\\Escritorio\\YaHP-Converter-master\\YaHP-Converter-master\\YaHPConverter\\out\\artifacts\\YaHPConverter\\cosa4.pdf");
			//out = new FileOutputStream(fout);
			out = new ByteArrayOutputStream();

			// si no se pone la etiqueta head, no valen los saltos de línea
			converter.convertToPdf(html,
					IHtmlToPdfTransformer.A4P,
					headerFooterList,
					null,
					out,
					properties);

			out.flush();
			out.close();

			// PDF renderizado en Byte Array
			byte[] result = out.toByteArray();
			return result;

		}catch (Exception ex)
		{
			try {

				out.flush();
				out.close();
			}catch (Exception ignore){}
		}
		return null;
	}

	private PolizaDTO generarPolizaMultiriesgo(String agenteID, BigInteger contenedorID, String cotizacionID, String unidadProduccionId)
	{
		AgenteDAO agenteDAO=new AgenteDAO();

		//Ensamblo el objeto de la poliza
		PolizaDTO polizaDTO=new PolizaDTO();
		Agente agente=agenteDAO.buscarPorId(agenteID);
		polizaDTO.setAgenteId(agente.getAgeEnsurance());
		if(contenedorID!=null)
			polizaDTO.setContenedorId(contenedorID.toString());
		polizaDTO.setEsPymes(true);
		polizaDTO.setEsRenovacion(false);
		polizaDTO.setMonedaId("11141120");
		polizaDTO.setPolizaid(null);
		polizaDTO.setRamo("MIC");
		polizaDTO.setRamoid("1516276756602"); //Ramo MULTIRIESGO INDUSTRIAL COMERCIAL
		polizaDTO.setTipoItemContenedorId("240");
		polizaDTO.setUnidadProduccionId(unidadProduccionId);
		polizaDTO.setCotizacionId(cotizacionID);

		return polizaDTO;
	}

	private PolizaDTO generarPolizaProgramaSeguros(String agenteID, BigInteger contenedorID, String cotizacionID, String unidadProduccionId)
	{
		AgenteDAO agenteDAO=new AgenteDAO();

		//Ensamblo el objeto de la poliza
		PolizaDTO polizaDTO=new PolizaDTO();
		Agente agente=agenteDAO.buscarPorId(agenteID);
		polizaDTO.setAgenteId(agente.getAgeEnsurance());
		if(contenedorID!=null)
			polizaDTO.setContenedorId(contenedorID.toString());
		polizaDTO.setEsPymes(true);
		polizaDTO.setEsRenovacion(false);
		polizaDTO.setMonedaId("11141120");
		polizaDTO.setPolizaid(null);
		polizaDTO.setRamo("IN");
		polizaDTO.setRamoid("7208961"); //Ramo INCENDIO
		polizaDTO.setTipoItemContenedorId("104");
		polizaDTO.setUnidadProduccionId(unidadProduccionId);
		polizaDTO.setCotizacionId(cotizacionID);

		return polizaDTO;
	}

	private HashMap<String, EndosoDTO> generarEndosoMultiriesgo(Cotizacion cotizacion, BigInteger plantillaId){
		PymeObjetoCotizacionDAO pymeObjetoDAO=new PymeObjetoCotizacionDAO();
		PymeCoberturaCotizacionValorDAO coberturaValorDAO=new PymeCoberturaCotizacionValorDAO();
		PymeDeducibleCotizacionValorDAO deducibleValorDAO=new PymeDeducibleCotizacionValorDAO();
		ProvinciaDAO provinciaDAO=new ProvinciaDAO();
		CantonDAO cantonDAO=new CantonDAO();
		//Creo el objeto ITEM
		ItemDTO nuevoItem=new ItemDTO();
		nuevoItem.setClaseRiesgoId("1572052086202"); //Tomado de Ensurance = PRIMERA
		
		nuevoItem.setId("");
		nuevoItem.setTexto("");
		nuevoItem.setTipoItemId("240"); //Tomado de Ensurance
		nuevoItem.setTipoRiesgoId("1572052086203"); //Tomado de Ensurance = A
		nuevoItem.setValorAsegurado(new BigDecimal(0));

		//Declaro el objeto endoso
		EndosoDTO endosoDTO=new EndosoDTO();

		//Creo las coberturas de la direccion
		List<PredioDTO> predios=new ArrayList<PredioDTO>();

		double valorAsegurado=0;
		double valorPrima=0;
		
		if(cotizacion.getCotizacionDetalles().size()>1){ //Si la cotizacion tiene mas de una dirección armo todas las coberturas a nivel del predio
			Boolean esPrimeraDireccion=true;
			for(CotizacionDetalle detalleActual:cotizacion.getCotizacionDetalles()){
				//Obtengo el objeto pyme cotizacion de acuerdo al detalle
				PymeObjetoCotizacion pymeObjeto=pymeObjetoDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));

				List<CoberturaDTO> coberturasAdicionales=new ArrayList<CoberturaDTO>();
				valorAsegurado=0;
				valorPrima=0;
				
				//Si es la primera dirección agrego las coberturas y asistencias generales
				if(esPrimeraDireccion)
				{
					List<PymeCoberturaCotizacionValor> listadoCoberturasGenerales=coberturaValorDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
					for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasGenerales){
						if(coberturaValor.getTipoOrigen().equals("GENERAL") || coberturaValor.getTipoOrigen().equals("ASISTENCIA") || coberturaValor.getTipoOrigen().equals("ADICIONALES")){
							CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
							nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsMultiId());
							nuevaCoberturaDTO.setLimite(0);
							nuevaCoberturaDTO.setRestaPrincipal(false);
							nuevaCoberturaDTO.setServicio(false);
							nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
							nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
							nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
							nuevaCoberturaDTO.setAfectaMonto(true);
							
							//Determino los deducibles
							List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
							if(deduciblesCotizacion.size()!=0)
								nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
							else
								nuevaCoberturaDTO.setDeducibles(null);
							
							coberturasAdicionales.add(nuevaCoberturaDTO);
							valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
							valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
						}
					}
				}
				
				//Obtengo las coberturas de esta direccion(Item)
				List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionDetalleId(new BigInteger(detalleActual.getId()));
				
				//Veo la distribución
				PymeDistribucionCoberturaDAO pymeDistribucionCoberturaDAO=new PymeDistribucionCoberturaDAO();
				PymeDistribucionCobertura pymeDistribucionCobertura=null;
										
				//Obtengo el valor de la cobertua de incendio
				double valorTotalAseguradoIcendio=0;
				double valorTotalPrimaIcendio=0;
				for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
					if(coberturaValor.getCoberturaPymesId().toString().equals("21")){
						valorTotalAseguradoIcendio=coberturaValor.getValorIngresado();
						valorTotalPrimaIcendio=coberturaValor.getPrimaCalculada();
						break;
					}
				}
				
				for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
					pymeDistribucionCobertura=pymeDistribucionCoberturaDAO.buscarPorCoberturaId(coberturaValor.getCoberturaPymesId());
					double valorAseguradoCobertura=coberturaValor.getValorIngresado();
					double primaNetaCobertura=coberturaValor.getPrimaCalculada();
					if(pymeDistribucionCobertura.getDistribucionCoberturaId()!=null){
						valorAseguradoCobertura=valorTotalAseguradoIcendio;
						primaNetaCobertura=(valorTotalPrimaIcendio*pymeDistribucionCobertura.getPorcentaje())/100;
					}
					CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
					nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsMultiId());
					nuevaCoberturaDTO.setLimite(0);
					nuevaCoberturaDTO.setRestaPrincipal(false);
					nuevaCoberturaDTO.setServicio(false);
					nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
					nuevaCoberturaDTO.setValorMonto(valorAseguradoCobertura);
					nuevaCoberturaDTO.setValorPrima(primaNetaCobertura);
					//nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
					//nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
					nuevaCoberturaDTO.setAfectaMonto(true);
					//Determino los deducibles
					List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
					if(deduciblesCotizacion.size()!=0)
						nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
					else
						nuevaCoberturaDTO.setDeducibles(null);
					coberturasAdicionales.add(nuevaCoberturaDTO);
					valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
					valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
				}

				esPrimeraDireccion=false;
				//Creo el objeto del predio
				PredioDTO nuevoPredio=new PredioDTO();
				nuevoPredio.setAnioConstruccion(pymeObjeto.getAnioConstruccion());
				nuevoPredio.setClaseRiesgoId("1572052086202"); //Tomado de Ensurance = PRIMERA
				nuevoPredio.setCoberturasAdicionales(coberturasAdicionales.toArray(new CoberturaDTO[coberturasAdicionales.size()]));
				nuevoPredio.setDireccionCallePrincipal(pymeObjeto.getCallePrincipal());
				nuevoPredio.setDireccionCalleTransversal(pymeObjeto.getCalleSecundaria());
				nuevoPredio.setDireccionNumero(pymeObjeto.getNumeroDireccion());
				nuevoPredio.setEsPrimaFija(true);
				nuevoPredio.setEstadoInformacion("D");
				nuevoPredio.setGpsX(""+pymeObjeto.getLatitud());
				nuevoPredio.setGpsY(""+pymeObjeto.getLonguitud());
				nuevoPredio.setId(null);
				if(pymeObjeto.getTipoConstruccionId()==null)
					nuevoPredio.setMaterialConstruccionId("1");
				else
					nuevoPredio.setMaterialConstruccionId(pymeObjeto.getTipoConstruccionId());
				nuevoPredio.setNombre(pymeObjeto.getNombreEdificio());
				if(pymeObjeto.getNumeroPiso()!=null)
					nuevoPredio.setNumeroPisos(new Integer(pymeObjeto.getNumeroPiso()));
				else
					nuevoPredio.setNumeroPisos(0);
				nuevoPredio.setPaisId("6815744");
				nuevoPredio.setPorcentajePrimaDeposito(0);

				String provinciaID=""+pymeObjeto.getProvinciaId();
				Provincia provincia=provinciaDAO.buscarPorId(provinciaID);
				nuevoPredio.setProvinciaId(provincia.getCodigoSbs());

				String ciudadID=""+pymeObjeto.getCiudadId();
				Canton canton=cantonDAO.buscarPorId(ciudadID);
				nuevoPredio.setCiduadId(provincia.getCodigoSbs()+canton.getCodigoSbs());

				nuevoPredio.setTasa(0);
				nuevoPredio.setTexto("");
				nuevoPredio.setTieneProteccionInundacion(false);
				nuevoPredio.setTieneSotano(false);
				nuevoPredio.setTipoItemId("240");
				if(pymeObjeto.getTipoPredioId()!=null){
					TipoPredioDAO tipoPredioDAO=new TipoPredioDAO();
					TipoPredio tipoPredio=tipoPredioDAO.buscarPorId(pymeObjeto.getTipoPredioId());
					nuevoPredio.setTipoPredioId(tipoPredio.getCodigoEnsurance());
				}
				else
					nuevoPredio.setTipoPredioId("1453362118656");
				
				nuevoPredio.setTipoRiesgoId("1572052086203");
				nuevoPredio.setValorAsegurado(new BigDecimal(valorAsegurado));
				nuevoPredio.setValorAseguradoPredio(pymeObjeto.getValorEstructuras());
				double valorContenidos=pymeObjeto.getValorEquipoHerramienta()+pymeObjeto.getValorInsumosNoelectronicos()+pymeObjeto.getValorMaquinaria()+pymeObjeto.getValorMercaderia()+pymeObjeto.getValorMueblesEnseres();
				nuevoPredio.setValorContenido(valorContenidos);
				nuevoPredio.setValorEdificio(0);
				nuevoPredio.setValorFlotante(0);
				nuevoPredio.setValorPrimaDeposito(0);
				nuevoPredio.setValorPrimaPredio(valorPrima);
				predios.add(nuevoPredio);
			}
			endosoDTO.setCoberturasAdicionales(null); //Es porque en este caso es mas importante las coberturas a nivel del item
		}
		else{ //Si la cotizacion tiene solamete un predio armo las coberturas a nivel general

			valorAsegurado=0;
			valorPrima=0;
			
			//Obtengo el objeto pyme cotizacion de acuerdo a la única dirección
			CotizacionDetalleDAO detalleDAO=new CotizacionDetalleDAO();
			CotizacionDetalle detalleActual=null;
			List<CotizacionDetalle> detalles=detalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
			if(detalles.size()>0)
				detalleActual=detalles.get(0);

			PymeObjetoCotizacion pymeObjeto=pymeObjetoDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));

			//Obtengo las coberturas de este detalle
			List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
			
			//Veo la distribución
			PymeDistribucionCoberturaDAO pymeDistribucionCoberturaDAO=new PymeDistribucionCoberturaDAO();
			PymeDistribucionCobertura pymeDistribucionCobertura=null;
									
			//Obtengo el valor de la cobertua de incendio
			double valorTotalAseguradoIcendio=0;
			double valorTotalPrimaIcendio=0;
			for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
				if(coberturaValor.getCoberturaPymesId().toString().equals("21")){
					valorTotalAseguradoIcendio=coberturaValor.getValorIngresado();
					valorTotalPrimaIcendio=coberturaValor.getPrimaCalculada();
					break;
				}
			}
			
			HashMap<String, Object> coberturasAdicionales=new HashMap<String, Object>();
			for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
				pymeDistribucionCobertura=pymeDistribucionCoberturaDAO.buscarPorCoberturaId(coberturaValor.getCoberturaPymesId());
				double valorAseguradoCobertura=coberturaValor.getValorIngresado();
				double primaNetaCobertura=coberturaValor.getPrimaCalculada();
				if(pymeDistribucionCobertura.getDistribucionCoberturaId()!=null){
					valorAseguradoCobertura=valorTotalAseguradoIcendio;
					primaNetaCobertura=(valorTotalPrimaIcendio*pymeDistribucionCobertura.getPorcentaje())/100;
				}
				CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
				nuevaCoberturaDTO.setAfectaMonto(true);
				nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsMultiId());
				nuevaCoberturaDTO.setLimite(0);
				nuevaCoberturaDTO.setRestaPrincipal(false);
				nuevaCoberturaDTO.setServicio(false);
				nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
				nuevaCoberturaDTO.setValorMonto(valorAseguradoCobertura);
				nuevaCoberturaDTO.setValorPrima(primaNetaCobertura);
				//nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
				//nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
				valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
				valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
				//Determino los deducibles
				List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
				if(deduciblesCotizacion.size()!=0)
					nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
				else
					nuevaCoberturaDTO.setDeducibles(null);
				
				//Agrego la cobertura al hashmap 
				coberturasAdicionales.put(coberturaValor.getCoberturaEnsMultiId(), nuevaCoberturaDTO);
			}
			endosoDTO.setCoberturasAdicionales(coberturasAdicionales); //Es porque en este caso es mas importante las coberturas a nivel del endoso

			//Creo el objeto del predio
			PredioDTO nuevoPredio=new PredioDTO();
			nuevoPredio.setAnioConstruccion(pymeObjeto.getAnioConstruccion());
			nuevoPredio.setClaseRiesgoId("1572052086202"); //Tomado de Ensurance = PRIMERA
			nuevoPredio.setCoberturasAdicionales(null); //es porque cuando existe una sola direccion las coberturas van a nivel de endoso
			nuevoPredio.setDireccionCallePrincipal(pymeObjeto.getCallePrincipal());
			nuevoPredio.setDireccionCalleTransversal(pymeObjeto.getCalleSecundaria());
			nuevoPredio.setDireccionNumero(pymeObjeto.getNumeroDireccion());
			nuevoPredio.setEsPrimaFija(true);
			nuevoPredio.setEstadoInformacion("D");
			nuevoPredio.setGpsX(""+pymeObjeto.getLatitud());
			nuevoPredio.setGpsY(""+pymeObjeto.getLonguitud());
			nuevoPredio.setId(null);
			if(pymeObjeto.getTipoConstruccionId()==null)
				nuevoPredio.setMaterialConstruccionId("1");
			else
				nuevoPredio.setMaterialConstruccionId(pymeObjeto.getTipoConstruccionId());
			nuevoPredio.setNombre(pymeObjeto.getNombreEdificio());
			if(pymeObjeto.getNumeroPiso()!=null)
				nuevoPredio.setNumeroPisos(new Integer(pymeObjeto.getNumeroPiso()));
			else
				nuevoPredio.setNumeroPisos(0);
			nuevoPredio.setPaisId("6815744");
			nuevoPredio.setPorcentajePrimaDeposito(0);

			String provinciaID=""+pymeObjeto.getProvinciaId();
			Provincia provincia=provinciaDAO.buscarPorId(provinciaID);
			nuevoPredio.setProvinciaId(provincia.getCodigoSbs());

			String ciudadID=""+pymeObjeto.getCiudadId();
			Canton canton=cantonDAO.buscarPorId(ciudadID);
			nuevoPredio.setCiduadId(provincia.getCodigoSbs()+canton.getCodigoSbs());

			nuevoPredio.setTasa(0);
			nuevoPredio.setTexto("");
			nuevoPredio.setTieneProteccionInundacion(false);
			nuevoPredio.setTieneSotano(false);
			nuevoPredio.setTipoItemId("240");
			if(pymeObjeto.getTipoPredioId()!=null){
				TipoPredioDAO tipoPredioDAO=new TipoPredioDAO();
				TipoPredio tipoPredio=tipoPredioDAO.buscarPorId(pymeObjeto.getTipoPredioId());
				nuevoPredio.setTipoPredioId(tipoPredio.getCodigoEnsurance());
			}
			else
				nuevoPredio.setTipoPredioId("1453362118656");
			nuevoPredio.setTipoRiesgoId("1572052086203");
			nuevoPredio.setValorAsegurado(new BigDecimal(valorAsegurado));
			nuevoPredio.setValorAseguradoPredio(pymeObjeto.getValorEstructuras());
			double valorContenidos=pymeObjeto.getValorEquipoHerramienta()+pymeObjeto.getValorInsumosNoelectronicos()+pymeObjeto.getValorMaquinaria()+pymeObjeto.getValorMercaderia()+pymeObjeto.getValorMueblesEnseres();
			nuevoPredio.setValorContenido(valorContenidos);
			nuevoPredio.setValorEdificio(0);
			nuevoPredio.setValorFlotante(0);
			nuevoPredio.setValorPrimaDeposito(0);
			nuevoPredio.setValorPrimaPredio(valorPrima);
			predios.add(nuevoPredio);
		}
		//Creo el objeto producto
		ProductoDTO nuevoProducto=new ProductoDTO();
		nuevoProducto.setPlanProducto("1");
		if(plantillaId!=null)
			nuevoProducto.setPlantillaId(plantillaId.toString());
		
		nuevoProducto.setProductoid("10756577365154");

		//Determino la vigencia desde hasta
		Calendar c = Calendar.getInstance();
		c.setTime(cotizacion.getVigenciaDesde());
		c.add(Calendar.YEAR, new Integer(cotizacion.getVigenciaPoliza().getValor().toString()));
		Date vigenciaHasta= c.getTime();
		
		//Continuo llenando el objeto endoso
		FirmasSucursalDAO firmasSucursalDAO=new FirmasSucursalDAO();
		FirmasSucursal firmaSucursal= firmasSucursalDAO.buscarPorSucursal(cotizacion.getPuntoVenta().getSucursal(), "1");
		if(firmaSucursal.getId()!=null)
			endosoDTO.setFirmaSucursalId(firmaSucursal.getId());
		else
			endosoDTO.setFirmaSucursalId("1");
		endosoDTO.setItem(nuevoItem);
		endosoDTO.setPorcentajeComision(new BigDecimal("0"));
		endosoDTO.setPredios(predios.toArray(new PredioDTO[predios.size()]));
		endosoDTO.setProducto(nuevoProducto);
		endosoDTO.setPuntoVentaId(cotizacion.getPuntoVenta().getPtoEnsurance());
		endosoDTO.setRecargoSeguroCampesino(new BigDecimal(cotizacion.getImpRecargoSeguroCampesino()));
		endosoDTO.setSistemaEmisor("");//??
		endosoDTO.setSistemaEmisorId(""); //??
		endosoDTO.setSucursalId(cotizacion.getPuntoVenta().getSucursal().getSucEnsurance());
		endosoDTO.setUnidadNegocioId("65537"); //??
		endosoDTO.setValorAsegurado(new BigDecimal(cotizacion.getSumaAseguradaTotal()));
		endosoDTO.setValorComision(new BigDecimal("0"));
		endosoDTO.setValorPrima(new BigDecimal(cotizacion.getPrimaNetaTotal()));
		endosoDTO.setVigenciaDesde(cotizacion.getVigenciaDesde().getTime());
		endosoDTO.setVigenciaHasta(vigenciaHasta.getTime());

		HashMap<String, EndosoDTO> endosos=new HashMap<String, EndosoDTO>();
		endosos.put("1516276756602", endosoDTO);
		
		return endosos;
	}
	
	private HashMap<String, EndosoDTO> generarEndosoProgramaSeguros(Cotizacion cotizacion, BigInteger plantillaId){
		PymeObjetoCotizacionDAO pymeObjetoDAO=new PymeObjetoCotizacionDAO();
		PymeCoberturaCotizacionValorDAO coberturaValorDAO=new PymeCoberturaCotizacionValorDAO();
		PymeDeducibleCotizacionValorDAO deducibleValorDAO=new PymeDeducibleCotizacionValorDAO();
		ProvinciaDAO provinciaDAO=new ProvinciaDAO();
		CantonDAO cantonDAO=new CantonDAO();
		PymeRamoCotizacionDAO ramoCotizacionDAO=new PymeRamoCotizacionDAO();
		PymeExtRamoDAO extRamoDAO=new PymeExtRamoDAO();
		
		HashMap<String, EndosoDTO> endosos=new HashMap<String, EndosoDTO>();
		
		List<PymeRamoCotizacion> ramosCotizados=ramoCotizacionDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
		for(PymeRamoCotizacion ramoCotizacionActual: ramosCotizados){
			//Obtenemos la configuración del Ramo
			PymeExtRamo extRamo=extRamoDAO.buscarPorId(ramoCotizacionActual.getRamoId());
			//Creo el objeto ITEM
			ItemDTO nuevoItem=new ItemDTO();
			nuevoItem.setClaseRiesgoId(extRamo.getClaseRiesgoId()); //Tomado de Ensurance = PRIMERA
			nuevoItem.setId("");
			nuevoItem.setTexto("");
			nuevoItem.setTipoItemId(extRamo.getTipoItemId()); //Tomado de Ensurance
			nuevoItem.setTipoRiesgoId(extRamo.getTipoRiesgoId()); //Tomado de Ensurance = A
			nuevoItem.setValorAsegurado(new BigDecimal(0));

			//Declaro el objeto endoso
			EndosoDTO endosoDTO=new EndosoDTO();
			
			double valorAsegurado=0;
			double valorPrima=0;
			if(ramoCotizacionActual.getRamoId().toString().equals("7208961")){ //Determinado si el ramo es incendio para armar el objeto PredioDTO

				//Creo las coberturas de la direccion
				List<PredioDTO> predios=new ArrayList<PredioDTO>();

				if(cotizacion.getCotizacionDetalles().size()>1){ //Si la cotizacion tiene mas de una dirección armo todas las coberturas a nivel del predio
					Boolean esPrimeraVez = true;
					for(CotizacionDetalle detalleActual:cotizacion.getCotizacionDetalles()){
						//Obtengo el objeto pyme cotizacion de acuerdo al detalle
						PymeObjetoCotizacion pymeObjeto=pymeObjetoDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));

						List<CoberturaDTO> coberturasAdicionales=new ArrayList<CoberturaDTO>();
						valorAsegurado=0;
						valorPrima=0;
						
						//Al ser la primera dirección agrego las asistencias las coberturas
						if(esPrimeraVez){
							List<PymeCoberturaCotizacionValor> listadoAsistencias=coberturaValorDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
							for(PymeCoberturaCotizacionValor coberturaValor: listadoAsistencias){
								if(coberturaValor.getTipoOrigen().equals("ASISTENCIA") || coberturaValor.getTipoOrigen().equals("ADICIONALES")){
									CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
									nuevaCoberturaDTO.setAfectaMonto(true);
									nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
									nuevaCoberturaDTO.setLimite(0);
									nuevaCoberturaDTO.setRestaPrincipal(false);
									nuevaCoberturaDTO.setServicio(false);
									nuevaCoberturaDTO.setTasa(coberturaValor.getTasaIngresada());
									nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
									nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
									//Determino los deducibles
									List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
									if(deduciblesCotizacion.size()!=0)
										nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
									else
										nuevaCoberturaDTO.setDeducibles(null);
									valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
									valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
									coberturasAdicionales.add(nuevaCoberturaDTO);
								}
							}
						}
						
						//Obtengo las coberturas de esta direccion
						List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(detalleActual.getId()), ramoCotizacionActual.getRamoId());
						
						//Veo la distribución
						PymeDistribucionCoberturaDAO pymeDistribucionCoberturaDAO=new PymeDistribucionCoberturaDAO();
						PymeDistribucionCobertura pymeDistribucionCobertura=null;
						
						//Obtengo el valor de la cobertua de incendio
						double valorTotalAseguradoIcendio=0;
						double valorTotalPrimaIcendio=0;
						for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
							if(coberturaValor.getCoberturaPymesId().toString().equals("21")){
								valorTotalAseguradoIcendio=coberturaValor.getValorIngresado();
								valorTotalPrimaIcendio=coberturaValor.getPrimaCalculada();
								break;
							}
						}
						
						//Recorro las cobertuas para ir agregando
						for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
							pymeDistribucionCobertura=pymeDistribucionCoberturaDAO.buscarPorCoberturaId(coberturaValor.getCoberturaPymesId());
							double valorAseguradoCobertura=coberturaValor.getValorIngresado();
							double primaNetaCobertura=coberturaValor.getPrimaCalculada();
							if(pymeDistribucionCobertura.getDistribucionCoberturaId()!=null){
								valorAseguradoCobertura=valorTotalAseguradoIcendio;
								primaNetaCobertura=(valorTotalPrimaIcendio*pymeDistribucionCobertura.getPorcentaje())/100;
							}
							CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
							nuevaCoberturaDTO.setAfectaMonto(true);
							nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
							nuevaCoberturaDTO.setLimite(0);
							nuevaCoberturaDTO.setRestaPrincipal(false);
							nuevaCoberturaDTO.setServicio(false);
							nuevaCoberturaDTO.setTasa(coberturaValor.getTasaIngresada());
							nuevaCoberturaDTO.setValorMonto(valorAseguradoCobertura);
							nuevaCoberturaDTO.setValorPrima(primaNetaCobertura);
							//nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
							//nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
							//Determino los deducibles
							List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
							if(deduciblesCotizacion.size()!=0)
								nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
							else
								nuevaCoberturaDTO.setDeducibles(null);
							valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
							valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
							coberturasAdicionales.add(nuevaCoberturaDTO);
						}
						esPrimeraVez = false;
						//Creo el objeto del predio
						PredioDTO nuevoPredio=new PredioDTO();
						nuevoPredio.setAnioConstruccion(pymeObjeto.getAnioConstruccion());
						nuevoPredio.setClaseRiesgoId(extRamo.getClaseRiesgoId()); 
						nuevoPredio.setCoberturasAdicionales(coberturasAdicionales.toArray(new CoberturaDTO[coberturasAdicionales.size()])); //Es porque para cuando hay varias direcciones agrego las coberturas al predio y no al endoso
						nuevoPredio.setDireccionCallePrincipal(pymeObjeto.getCallePrincipal());
						nuevoPredio.setDireccionCalleTransversal(pymeObjeto.getCalleSecundaria());
						nuevoPredio.setDireccionNumero(pymeObjeto.getNumeroDireccion());
						nuevoPredio.setGpsX(""+pymeObjeto.getLatitud());
						nuevoPredio.setGpsY(""+pymeObjeto.getLonguitud());
						nuevoPredio.setEsPrimaFija(true);
						nuevoPredio.setEstadoInformacion("D");
						nuevoPredio.setId(null);
						if(pymeObjeto.getTipoConstruccionId()==null)
							nuevoPredio.setMaterialConstruccionId("1");
						else
							nuevoPredio.setMaterialConstruccionId(pymeObjeto.getTipoConstruccionId());
						nuevoPredio.setNombre(pymeObjeto.getNombreEdificio());
						if(pymeObjeto.getNumeroPiso()!=null)
							nuevoPredio.setNumeroPisos(new Integer(pymeObjeto.getNumeroPiso()));
						else
							nuevoPredio.setNumeroPisos(0);
						nuevoPredio.setPaisId("6815744");
						nuevoPredio.setPorcentajePrimaDeposito(0);

						String provinciaID=""+pymeObjeto.getProvinciaId();
						Provincia provincia=provinciaDAO.buscarPorId(provinciaID);
						nuevoPredio.setProvinciaId(provincia.getCodigoSbs());

						String ciudadID=""+pymeObjeto.getCiudadId();
						Canton canton=cantonDAO.buscarPorId(ciudadID);
						nuevoPredio.setCiduadId(provincia.getCodigoSbs()+canton.getCodigoSbs());

						nuevoPredio.setTasa(0);
						nuevoPredio.setTexto("");
						nuevoPredio.setTieneProteccionInundacion(false);
						nuevoPredio.setTieneSotano(false);
						nuevoPredio.setTipoItemId(extRamo.getTipoItemId());
						if(pymeObjeto.getTipoPredioId()!=null){
							TipoPredioDAO tipoPredioDAO=new TipoPredioDAO();
							TipoPredio tipoPredio=tipoPredioDAO.buscarPorId(pymeObjeto.getTipoPredioId());
							nuevoPredio.setTipoPredioId(tipoPredio.getCodigoEnsurance());
						}
						else
							nuevoPredio.setTipoPredioId("1453362118656");
						nuevoPredio.setTipoRiesgoId(extRamo.getTipoRiesgoId());
						nuevoPredio.setValorAsegurado(new BigDecimal(valorAsegurado));
						nuevoPredio.setValorAseguradoPredio(valorAsegurado);
						double valorContenidos=pymeObjeto.getValorEquipoHerramienta()+pymeObjeto.getValorInsumosNoelectronicos()+pymeObjeto.getValorMaquinaria()+pymeObjeto.getValorMercaderia()+pymeObjeto.getValorMueblesEnseres();
						nuevoPredio.setValorContenido(valorContenidos);
						nuevoPredio.setValorEdificio(0);
						nuevoPredio.setValorFlotante(0);
						nuevoPredio.setValorPrimaDeposito(0);
						nuevoPredio.setValorPrimaPredio(valorPrima);
						predios.add(nuevoPredio);
					}
					endosoDTO.setCoberturasAdicionales(null); //Es porque para cuando hay varias direcciones agrego las coberturas al predio y no al endoso
					//Asigno los predios al endoso
					endosoDTO.setPredios(predios.toArray(new PredioDTO[predios.size()]));
				}
				else{ //Si la cotizacion tiene solamete un predio armo las coberturas a nivel general

					//Obtengo el objeto pyme cotizacion de acuerdo a la única dirección
					CotizacionDetalle detalleActual=cotizacion.getCotizacionDetalles().get(0);

					PymeObjetoCotizacion pymeObjeto=pymeObjetoDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));

					HashMap<String, Object> coberturasAdicionales=new HashMap<String, Object>();
					
					//Obtengo todas las coberturas seleccionadas para el ramo en esa cotización
					List<PymeCoberturaCotizacionValor> listadoAsistencias=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), ramoCotizacionActual.getRamoId());
					
					//Veo la distribución
					PymeDistribucionCoberturaDAO pymeDistribucionCoberturaDAO=new PymeDistribucionCoberturaDAO();
					PymeDistribucionCobertura pymeDistribucionCobertura=null;
									
					
					//Obtengo el valor de la cobertua de incendio
					double valorTotalAseguradoIcendio=0;
					double valorTotalPrimaIcendio=0;
					for(PymeCoberturaCotizacionValor coberturaValor: listadoAsistencias){
						if(coberturaValor.getCoberturaPymesId().toString().equals("21")){
							valorTotalAseguradoIcendio=coberturaValor.getValorIngresado();
							valorTotalPrimaIcendio=coberturaValor.getPrimaCalculada();
							break;
						}
					}
					
					for(PymeCoberturaCotizacionValor coberturaValor: listadoAsistencias){
						pymeDistribucionCobertura=pymeDistribucionCoberturaDAO.buscarPorCoberturaId(coberturaValor.getCoberturaPymesId());
						double valorAseguradoCobertura=coberturaValor.getValorIngresado();
						double primaNetaCobertura=coberturaValor.getPrimaCalculada();
						if(pymeDistribucionCobertura.getDistribucionCoberturaId()!=null){
							valorAseguradoCobertura=valorTotalAseguradoIcendio;
							primaNetaCobertura=(valorTotalPrimaIcendio*pymeDistribucionCobertura.getPorcentaje())/100;
						}
						CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
						nuevaCoberturaDTO.setAfectaMonto(true);
						nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
						nuevaCoberturaDTO.setLimite(0);
						nuevaCoberturaDTO.setRestaPrincipal(false);
						nuevaCoberturaDTO.setServicio(false);
						nuevaCoberturaDTO.setTasa(coberturaValor.getTasaIngresada());
						nuevaCoberturaDTO.setValorMonto(valorAseguradoCobertura);
						nuevaCoberturaDTO.setValorPrima(primaNetaCobertura);
						//nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
						//nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
						//Determino los deducibles
						List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
						if(deduciblesCotizacion.size()!=0)
							nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
						else
							nuevaCoberturaDTO.setDeducibles(null);
						valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
						valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
						
						//Agrego la cobertura al hashmap 
						coberturasAdicionales.put(coberturaValor.getCoberturaEnsProgrId(), nuevaCoberturaDTO);
					}
					
					//Agrego solamente las asistencias
					listadoAsistencias=coberturaValorDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
					for(PymeCoberturaCotizacionValor coberturaValor: listadoAsistencias){
						if(coberturaValor.getTipoOrigen().equals("ASISTENCIA")){
							CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
							nuevaCoberturaDTO.setAfectaMonto(true);
							nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
							nuevaCoberturaDTO.setLimite(0);
							nuevaCoberturaDTO.setRestaPrincipal(false);
							nuevaCoberturaDTO.setServicio(false);
							nuevaCoberturaDTO.setTasa(coberturaValor.getTasaIngresada());
							nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
							nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
							
							//Determino los deducibles
							List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
							if(deduciblesCotizacion.size()!=0)
								nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
							else
								nuevaCoberturaDTO.setDeducibles(null);
							valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
							valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
							
							//Agrego la cobertura al hashmap 
							coberturasAdicionales.put(coberturaValor.getCoberturaEnsProgrId(), nuevaCoberturaDTO);
						}
					}
					
					//Es porque cuando hay una sola dirección las coberturas se agregan a nivel de endoso no a nivel de item
					endosoDTO.setCoberturasAdicionales(coberturasAdicionales); 

					//Creo el objeto del predio
					PredioDTO nuevoPredio=new PredioDTO();
					nuevoPredio.setAnioConstruccion(pymeObjeto.getAnioConstruccion());
					nuevoPredio.setClaseRiesgoId(extRamo.getClaseRiesgoId()); //Tomado de Ensurance = PRIMERA
					nuevoPredio.setCoberturasAdicionales(null); //es porque en esta caso es mas importante a nivel de endoso
					nuevoPredio.setDireccionCallePrincipal(pymeObjeto.getCallePrincipal());
					nuevoPredio.setDireccionCalleTransversal(pymeObjeto.getCalleSecundaria());
					nuevoPredio.setDireccionNumero(pymeObjeto.getNumeroDireccion());
					nuevoPredio.setEsPrimaFija(true);
					nuevoPredio.setEstadoInformacion("D");
					nuevoPredio.setGpsX(""+pymeObjeto.getLatitud());
					nuevoPredio.setGpsY(""+pymeObjeto.getLonguitud());
					nuevoPredio.setId(null);
					if(pymeObjeto.getTipoConstruccionId()==null)
						nuevoPredio.setMaterialConstruccionId("1");
					else
						nuevoPredio.setMaterialConstruccionId(pymeObjeto.getTipoConstruccionId());
					nuevoPredio.setNombre(pymeObjeto.getNombreEdificio());
					if(pymeObjeto.getNumeroPiso()!=null)
						nuevoPredio.setNumeroPisos(new Integer(pymeObjeto.getNumeroPiso()));
					else
						nuevoPredio.setNumeroPisos(0);
					nuevoPredio.setPaisId("6815744");
					nuevoPredio.setPorcentajePrimaDeposito(0);

					String provinciaID=""+pymeObjeto.getProvinciaId();
					Provincia provincia=provinciaDAO.buscarPorId(provinciaID);
					nuevoPredio.setProvinciaId(provincia.getCodigoSbs());

					String ciudadID=""+pymeObjeto.getCiudadId();
					Canton canton=cantonDAO.buscarPorId(ciudadID);
					nuevoPredio.setCiduadId(provincia.getCodigoSbs()+canton.getCodigoSbs());

					nuevoPredio.setTasa(0);
					nuevoPredio.setTexto("");
					nuevoPredio.setTieneProteccionInundacion(false);
					nuevoPredio.setTieneSotano(false);
					nuevoPredio.setTipoItemId(extRamo.getTipoItemId());
					if(pymeObjeto.getTipoPredioId()!=null){
						TipoPredioDAO tipoPredioDAO=new TipoPredioDAO();
						TipoPredio tipoPredio=tipoPredioDAO.buscarPorId(pymeObjeto.getTipoPredioId());
						nuevoPredio.setTipoPredioId(tipoPredio.getCodigoEnsurance());
					}
					else
						nuevoPredio.setTipoPredioId("1453362118656");
					nuevoPredio.setTipoRiesgoId(extRamo.getTipoRiesgoId());
					nuevoPredio.setValorAsegurado(new BigDecimal(valorAsegurado));
					nuevoPredio.setValorAseguradoPredio(valorAsegurado);
					double valorContenidos=pymeObjeto.getValorEquipoHerramienta()+pymeObjeto.getValorInsumosNoelectronicos()+pymeObjeto.getValorMaquinaria()+pymeObjeto.getValorMercaderia()+pymeObjeto.getValorMueblesEnseres();
					nuevoPredio.setValorContenido(valorContenidos);
					nuevoPredio.setValorEdificio(0);
					nuevoPredio.setValorFlotante(0);
					nuevoPredio.setValorPrimaDeposito(0);
					nuevoPredio.setValorPrimaPredio(valorPrima);
					predios.add(nuevoPredio);
					endosoDTO.setPredios(predios.toArray(new PredioDTO[predios.size()]));
				}
				
			}
			else if(ramoCotizacionActual.getRamoId().toString().equals("7274505")){ //Determinado si el RC
				//Creo las coberturas de la direccion
				List<BlanketDTO> blankets=new ArrayList<BlanketDTO>();

				//Obtengo las coberturas de este detalle
				List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), ramoCotizacionActual.getRamoId());

				HashMap<String, Object> coberturasAdicionales=new HashMap<String, Object>();
				for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
					CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
					nuevaCoberturaDTO.setAfectaMonto(true);
					nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
					nuevaCoberturaDTO.setLimite(0);
					nuevaCoberturaDTO.setRestaPrincipal(false);
					nuevaCoberturaDTO.setServicio(false);
					nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
					nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
					nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
					//Determino los deducibles
					List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
					if(deduciblesCotizacion.size()!=0)
						nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
					else
						nuevaCoberturaDTO.setDeducibles(null);
					
					valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
					valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
					
					//Agrego la cobertura al hashmap 
					coberturasAdicionales.put(coberturaValor.getCoberturaEnsProgrId(), nuevaCoberturaDTO);
				}
				endosoDTO.setCoberturasAdicionales(coberturasAdicionales); //Es porque en este caso es mas importante las coberturas a nivel del endoso

				//Creo el objeto del predio
				BlanketDTO nuevoBlanket=new BlanketDTO();
				nuevoBlanket.setCantidad(1);
				nuevoBlanket.setNombre("Blanket Pymes");
				nuevoBlanket.setClaseRiesgoId(extRamo.getClaseRiesgoId());
				nuevoBlanket.setTipoRiesgoId(extRamo.getTipoRiesgoId());
				nuevoBlanket.setTipoItemId(extRamo.getTipoItemId());
				nuevoBlanket.setValorAsegurado(new BigDecimal(valorAsegurado));
				nuevoBlanket.setValorTotal(new BigDecimal(valorAsegurado));
				nuevoBlanket.setValorUnitario(new BigDecimal(valorAsegurado));
				nuevoBlanket.setDescripcion("Blanket Pymes");
				blankets.add(nuevoBlanket);
				endosoDTO.setBlankets(blankets.toArray(new BlanketDTO[blankets.size()]));
			}
			else{ //Si no es incendio debo crear el objeto BlanketDTO como item
				//Creo las coberturas de la direccion
				List<BlanketDTO> blankets=new ArrayList<BlanketDTO>();

				if(cotizacion.getCotizacionDetalles().size()>1){ //Si la cotizacion tiene mas de una dirección armo todas las coberturas a nivel del predio
					for(CotizacionDetalle detalleActual:cotizacion.getCotizacionDetalles()){
						//Obtengo las coberturas de este detalle
						List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(detalleActual.getId()), ramoCotizacionActual.getRamoId());
						List<CoberturaDTO> coberturasAdicionales=new ArrayList<CoberturaDTO>();
						valorAsegurado=0;
						valorPrima=0;
						for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
							CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
							nuevaCoberturaDTO.setAfectaMonto(true);
							nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
							nuevaCoberturaDTO.setLimite(0);
							nuevaCoberturaDTO.setRestaPrincipal(false);
							nuevaCoberturaDTO.setServicio(false);
							nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
							nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
							nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
							valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
							valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
							//Determino los deducibles
							List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
							if(deduciblesCotizacion.size()!=0)
								nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
							else
								nuevaCoberturaDTO.setDeducibles(null);
							
							coberturasAdicionales.add(nuevaCoberturaDTO);
						}

						//Creo el objeto del predio
						BlanketDTO nuevoBlanket=new BlanketDTO();
						nuevoBlanket.setCantidad(1);
						nuevoBlanket.setClaseRiesgoId(extRamo.getClaseRiesgoId());
						nuevoBlanket.setTipoItemId(extRamo.getTipoItemId());
						nuevoBlanket.setTipoRiesgoId(extRamo.getTipoRiesgoId());
						nuevoBlanket.setValorAsegurado(new BigDecimal(valorAsegurado));
						nuevoBlanket.setValorTotal(new BigDecimal("0"));
						nuevoBlanket.setValorUnitario(new BigDecimal("0"));
						nuevoBlanket.setDescripcion("Item");
						blankets.add(nuevoBlanket);
					}
					endosoDTO.setCoberturasAdicionales(null); //Es porque en este caso es mas importante las coberturas a nivel del item
					//Asigno los predios al endoso
					endosoDTO.setBlankets(blankets.toArray(new BlanketDTO[blankets.size()]));
				}
				else{ //Si la cotizacion tiene solamete un predio armo las coberturas a nivel general
					
					//Obtengo el objeto pyme cotizacion de acuerdo a la única dirección
					CotizacionDetalle detalleActual=cotizacion.getCotizacionDetalles().get(0);

					//Obtengo las coberturas de este detalle
					List<PymeCoberturaCotizacionValor> listadoCoberturasDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(detalleActual.getId()), ramoCotizacionActual.getRamoId());

					HashMap<String, Object> coberturasAdicionales=new HashMap<String, Object>();
					for(PymeCoberturaCotizacionValor coberturaValor: listadoCoberturasDireccion){
						CoberturaDTO nuevaCoberturaDTO=new CoberturaDTO();
						nuevaCoberturaDTO.setAfectaMonto(true);
						nuevaCoberturaDTO.setId(coberturaValor.getCoberturaEnsProgrId());
						nuevaCoberturaDTO.setLimite(0);
						nuevaCoberturaDTO.setRestaPrincipal(false);
						nuevaCoberturaDTO.setServicio(false);
						nuevaCoberturaDTO.setTasa(coberturaValor.getTasaSugerida());
						nuevaCoberturaDTO.setValorMonto(coberturaValor.getValorIngresado());
						nuevaCoberturaDTO.setValorPrima(coberturaValor.getPrimaCalculada());
						valorAsegurado=valorAsegurado+coberturaValor.getValorIngresado();
						valorPrima=valorPrima+coberturaValor.getPrimaCalculada();
						
						//Determino los deducibles
						List<PymeDeducibleCotizacionValor> deduciblesCotizacion = deducibleValorDAO.buscarPorCotizacionIdCoberturaId(new BigInteger(cotizacion.getId()), coberturaValor.getCoberturaPymesId());
						if(deduciblesCotizacion.size()!=0)
							nuevaCoberturaDTO.setDeducibles(generarDeducibles(deduciblesCotizacion));
						else
							nuevaCoberturaDTO.setDeducibles(null);
						
						//Agrego la cobertura al hashmap 
						coberturasAdicionales.put(coberturaValor.getCoberturaEnsProgrId(), nuevaCoberturaDTO);
					}
					endosoDTO.setCoberturasAdicionales(coberturasAdicionales); //Es porque en este caso es mas importante las coberturas a nivel del endoso

					//Creo el objeto del predio
					BlanketDTO nuevoBlanket=new BlanketDTO();
					nuevoBlanket.setCantidad(1);
					nuevoBlanket.setNombre("Blanket Pymes");
					nuevoBlanket.setClaseRiesgoId(extRamo.getClaseRiesgoId());
					nuevoBlanket.setTipoRiesgoId(extRamo.getTipoRiesgoId());
					nuevoBlanket.setTipoItemId(extRamo.getTipoItemId());
					nuevoBlanket.setValorAsegurado(new BigDecimal(valorAsegurado));
					nuevoBlanket.setValorTotal(new BigDecimal(valorAsegurado));
					nuevoBlanket.setValorUnitario(new BigDecimal(valorAsegurado));
					nuevoBlanket.setDescripcion("Blanket Pymes");
					blankets.add(nuevoBlanket);
					endosoDTO.setBlankets(blankets.toArray(new BlanketDTO[blankets.size()]));
				}
			}
			//Creo el objeto producto
			ProductoDTO nuevoProducto=new ProductoDTO();
			nuevoProducto.setPlanProducto("1");
			if(plantillaId!=null)
				nuevoProducto.setPlantillaId(plantillaId.toString());
			nuevoProducto.setProductoid(cotizacion.getProducto().getId());

			//Determino la vigencia desde hasta
			Calendar c = Calendar.getInstance();
			c.setTime(cotizacion.getVigenciaDesde());
			c.add(Calendar.YEAR, new Integer(cotizacion.getVigenciaPoliza().getValor().toString()));
			Date vigenciaHasta= c.getTime();
			
			//Continuo llenando el objeto endoso
			FirmasSucursalDAO firmasSucursalDAO=new FirmasSucursalDAO();
			FirmasSucursal firmaSucursal= firmasSucursalDAO.buscarPorSucursal(cotizacion.getPuntoVenta().getSucursal(), "1");
			if(firmaSucursal.getId()!=null)
				endosoDTO.setFirmaSucursalId(firmaSucursal.getId());
			else
				endosoDTO.setFirmaSucursalId("1");
			endosoDTO.setItem(nuevoItem);
			endosoDTO.setPorcentajeComision(new BigDecimal("0"));
			endosoDTO.setProducto(nuevoProducto);
			endosoDTO.setPuntoVentaId(cotizacion.getPuntoVenta().getPtoEnsurance());
			endosoDTO.setRecargoSeguroCampesino(new BigDecimal(cotizacion.getImpRecargoSeguroCampesino()));
			endosoDTO.setSistemaEmisor("");//??
			endosoDTO.setSistemaEmisorId(""); //??
			endosoDTO.setSucursalId(cotizacion.getPuntoVenta().getSucursal().getSucEnsurance());
			endosoDTO.setUnidadNegocioId("65537"); //??
			endosoDTO.setValorAsegurado(new BigDecimal(valorAsegurado));
			endosoDTO.setValorComision(new BigDecimal("0"));
			endosoDTO.setValorPrima(new BigDecimal(valorPrima));
			endosoDTO.setVigenciaDesde(cotizacion.getVigenciaDesde().getTime());
			endosoDTO.setVigenciaHasta(vigenciaHasta.getTime());

			//Agrego el endoso del ramo al Hash
			endosos.put(ramoCotizacionActual.getRamoId().toString(), endosoDTO);
		}
		return endosos;
	}

	
	private DeducibleDTO[] generarDeducibles(List<PymeDeducibleCotizacionValor> deduciblesPorCobertura){
		List<DeducibleDTO> deducibles=new ArrayList<DeducibleDTO>();
		for(PymeDeducibleCotizacionValor deducibleCotizacionActual:deduciblesPorCobertura){
			if(!deducibleCotizacionActual.getIdsDeducible().equals("")){
				String idsFinal=deducibleCotizacionActual.getIdsDeducible().substring(0, deducibleCotizacionActual.getIdsDeducible().length()-1);
				String valoresFinal=deducibleCotizacionActual.getValoresDeducible().substring(0, deducibleCotizacionActual.getValoresDeducible().length()-1);
				String textosFinal=deducibleCotizacionActual.getTextosDeducible().substring(0, deducibleCotizacionActual.getTextosDeducible().length()-1);
				String[] ids=idsFinal.split(";");
				String[] valores=valoresFinal.split(";");
				String[] textos=textosFinal.split(";");
				for(int i=0; i<ids.length;i++){
					DeducibleDTO nuevoDeducible=new DeducibleDTO();
					nuevoDeducible.setId(ids[i]); //el id del tipo de deducible
					nuevoDeducible.setTexto(textos[i]);
					nuevoDeducible.setValor(valores[i]);
					deducibles.add(nuevoDeducible);
				}
			}
		}
		return deducibles.toArray(new DeducibleDTO[deducibles.size()]);
	}
	
	private ClienteDTO generarObjetoClienteDTO(Entidad entidad)
	{
		EntidadDAO entidadDAO=new EntidadDAO();
		Entidad entidadLocal=entidadDAO.buscarPorId(entidad.getId());
		ClienteDTO nuevoCliente=new ClienteDTO();
		nuevoCliente.setApellidos(entidadLocal.getApellidos());
		nuevoCliente.setIdentificacion(entidadLocal.getIdentificacion());
		nuevoCliente.setCorreoElectronico(entidadLocal.getMail());
		nuevoCliente.setNombres(entidadLocal.getNombreCompleto());
		nuevoCliente.setEntidadId(entidadLocal.getEntEnsurance());
		nuevoCliente.setNombreCorto(entidadLocal.getNombres());
		return nuevoCliente;
	}

	private ClienteDTO generarObjetoAseguradoDTO(Entidad entidad)
	{
		EntidadDAO entidadDAO=new EntidadDAO();
		Entidad entidadLocal=entidadDAO.buscarPorId(entidad.getId());
		ClienteDTO nuevoCliente=new ClienteDTO();
		nuevoCliente.setApellidos(entidadLocal.getApellidos());
		nuevoCliente.setIdentificacion(entidadLocal.getIdentificacion());
		if(entidadLocal.getTipoIdentificacion().getNombre().equals("Cédula"))
		{
			nuevoCliente.setTipoIdentificacion("c");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidadLocal.getTipoIdentificacion().getNombre().equals("Pasaporte"))
		{
			nuevoCliente.setTipoIdentificacion("p");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidadLocal.getTipoIdentificacion().getNombre().equals("RUC Persona Natural"))
		{
			nuevoCliente.setTipoIdentificacion("r");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidadLocal.getTipoIdentificacion().getNombre().equals("RUC Persona Jurídica"))
		{
			nuevoCliente.setTipoIdentificacion("r");
			nuevoCliente.setTipoEntidad("2");
		}
		nuevoCliente.setGenero("f");
		nuevoCliente.setCorreoElectronico(entidadLocal.getMail());
		nuevoCliente.setNombres(entidadLocal.getNombreCompleto());
		nuevoCliente.setEntidadId(entidadLocal.getEntEnsurance());
		nuevoCliente.setNombreCorto(entidadLocal.getNombres());

		//Obtener el cliente
		if (entidadLocal.getClientes().size()!=0)
		{
			UplaDAO uplaDAO=new UplaDAO();
			Upla entidadUpla=uplaDAO.buscarPorCliente(entidadLocal.getClientes().get(0));
			Direccion direccionPersona=entidadUpla.getDireccion();
			DireccionDTO[] direcciones=new DireccionDTO[1];
			DireccionDTO nuevaDireccion=new DireccionDTO();
			nuevaDireccion.setProvinciaId(direccionPersona.getCiudad().getProvincia().getCodigoSbs());
			nuevaDireccion.setCallePrincipal(direccionPersona.getCallePrincipal());
			nuevaDireccion.setCalleSecundaria(direccionPersona.getCalleSecundaria());
			nuevaDireccion.setNumero(direccionPersona.getNumero());
			nuevaDireccion.setTipoDireccion("1");
			nuevaDireccion.setPaisId("6815744");
			nuevaDireccion.setCantonId(direccionPersona.getCiudad().getId());
			if(direccionPersona.getParroquia()!=null)
				nuevaDireccion.setParroquiaId(direccionPersona.getParroquia().getId());
			nuevaDireccion.setDireccion(direccionPersona.getCallePrincipal() + " "+direccionPersona.getNumero()+" "+direccionPersona.getCalleSecundaria());
			direcciones[0]=nuevaDireccion;
			nuevoCliente.setDirecciones(direcciones);
		}
		return nuevoCliente;
	}

	private ConfiguracionPagoDTO generarObjetoPagoDTO(Pago pago)
	{
		ConfiguracionPagoDTO nuevoConfigPago=new ConfiguracionPagoDTO();
		if(pago.getAnioExpiracionTarjeta()!=null && pago.getMesExpiracionTarjeta()!=null)
		{
			if(!pago.getAnioExpiracionTarjeta().equals("") && !pago.getMesExpiracionTarjeta().equals(""))
			{
				Calendar fechaVencimiento=GregorianCalendar.getInstance();
				fechaVencimiento.set(Integer.parseInt(pago.getAnioExpiracionTarjeta()), Integer.parseInt(pago.getMesExpiracionTarjeta()), 1);
				nuevoConfigPago.setFechaVencimientoTarjeta(fechaVencimiento);
			}
		}
		if(pago.getInstitucionFinanciera()!=null)
			nuevoConfigPago.setInstitucionaDebitar(pago.getInstitucionFinanciera().getNombre());
		if(pago.getNumeroCuentaTarjeta()!=null)
			nuevoConfigPago.setNumeroCuenta(pago.getNumeroCuentaTarjeta());
		nuevoConfigPago.setTipoCuenta(pago.getTipoCuenta());
		nuevoConfigPago.setNumeroPagos(Integer.parseInt(pago.getPlazonEnMes()));
		nuevoConfigPago.setTipoPagoId(pago.getFormaPago().getCodigoEnsurance());

		List<PagoDTO> nuevosPagos=new ArrayList<PagoDTO>();
		
		PagoDTO nuevoPago=null;
		if(pago.getFormaPago().getNombre().equals("CONTADO")){
			nuevoPago=new PagoDTO();
			nuevoPago.setEsCuota(true);
			Calendar fechaPago=GregorianCalendar.getInstance();
			Date fechaActual=new Date();
			fechaPago.set(fechaActual.getYear(), fechaActual.getMonth(), fechaActual.getDay());
			nuevoPago.setFechaPago(fechaPago);
			nuevoPago.setOrden(1);
			nuevoPago.setValorPago(new BigDecimal(pago.getValorTotal()));
			nuevosPagos.add(nuevoPago);
		}
		else{
			int orden=1;
			CuotaDAO cuotaDAO = new CuotaDAO();
			List <Cuota> cuotas = cuotaDAO.buscarPorPago(pago);
			for(Cuota cuota:cuotas){
				nuevoPago=new PagoDTO();
				Calendar fechaPago=GregorianCalendar.getInstance();
				fechaPago.set(cuota.getFechaPago().getYear(), cuota.getFechaPago().getMonth(), cuota.getFechaPago().getDay());
				nuevoPago.setFechaPago(fechaPago);
				nuevoPago.setOrden(orden);
				nuevoPago.setEsCuota(true);
				nuevoPago.setValorPago(new BigDecimal(cuota.getValor()));
				nuevosPagos.add(nuevoPago);
				orden++;
			}
		}
		nuevoConfigPago.setPagos(nuevosPagos.toArray(new PagoDTO[nuevosPagos.size()]));

		return nuevoConfigPago;
	}
	
	public static Map<String, Object> toMap(JSONObject object) throws JSONException
    {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext())
        {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray)
            {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject)
            {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
       
        map.put(JRParameter.REPORT_LOCALE,new Locale("ES"));//se agrega el idioma del reporte a los parametros
        return map;
    }
	
	public static List toList(JSONArray array) throws JSONException
    {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size() ; i++)
        {
            list.add(array.get(i));
        }
        return list;
    }
}

