package com.qbe.cotizador.servlets.cotizacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Days;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.qbe.cotizador.dao.cotizacion.ArchivoCotizacionMasivosDAO;
import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.DescuentoDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.GrupoUsuarioAutorizacionDAO;
import com.qbe.cotizador.dao.cotizacion.MotivoDescuentoDAO;
import com.qbe.cotizador.dao.cotizacion.MovimientoCotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.PaqueteDAO;
import com.qbe.cotizador.dao.cotizacion.PrecioReferencialDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.SolicitudDescuentoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.TipoInspectorDAO;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ExtraDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.VhTarificadorDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.ArchivoCotizacionMasivo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Descuento;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Extra;
import com.qbe.cotizador.model.GrupoAutorizacion;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoUsuarioAutorizacion;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.MovimientoCotizacion;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Pago;
import com.qbe.cotizador.model.Paquete;
import com.qbe.cotizador.model.ParametroXPuntoVenta;
import com.qbe.cotizador.model.PrecioReferencial;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TasaProducto;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.model.TipoGanado;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoInspector;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoUso;
import com.qbe.cotizador.model.TipoVehiculo;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.model.VhTarificador;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.transaction.cotizacion.CotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.MovimientoCotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.SolicitudDescuentoTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ExtraTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ObjetoVehiculoTransaction;
import com.qbe.cotizador.util.ManejoColas;
import com.qbe.cotizador.util.ManejoFTP;
import com.qbe.cotizador.util.MotorTarifador;
import com.qbe.cotizador.util.Reportes;
import com.qbe.cotizador.util.Utilitarios;

//Ganadero
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDAO;
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDetalleDAO;
import com.qbe.cotizador.dao.producto.ganadero.ParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.ganadero.RazaDAO;
import com.qbe.cotizador.dao.producto.ganadero.TipoGanadoDAO;
import com.qbe.cotizador.model.ObjetoGanadero;
import com.qbe.cotizador.model.ObjetoGanaderoDetalle;
import com.qbe.cotizador.model.Raza;

/**
 * Servlet implementation class CotizacionController
 */
@WebServlet("/CotizacionController")
public class CotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**o
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");
			
			String listaExtrasIds = request.getParameter("listaExtrasIds") == null ? "" : request.getParameter("listaExtrasIds");
			String listaExtrasDetalles = request.getParameter("listaExtrasDetalles") == null ? "" : request.getParameter("listaExtrasDetalles");
			String listaExtrasValores = request.getParameter("listaExtrasValores") == null ? "" : request.getParameter("listaExtrasValores");
			String codigoTipoUso = request.getParameter("codigoTipoUso") == null ? "" : request.getParameter("codigoTipoUso").trim();
			String codigoTipoVehiculo = request.getParameter("codigoTipoVehiculo") == null ? "" : request.getParameter("codigoTipoVehiculo").trim();
			String tipoObjeto = request.getParameter("tipoObjeto") == null ? "" : request.getParameter("tipoObjeto").trim();

			
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			JSONObject cotizacionJSONObject = new JSONObject();
			JSONArray cotizacionJSONArray = new JSONArray();
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			EntidadTransaction entidadTransaction = new EntidadTransaction();
			ClienteTransaction clienteTransaction = new ClienteTransaction();
			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
			SolicitudDescuentoTransaction solicitudDescuentoTransaction = new SolicitudDescuentoTransaction();
			ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
			ExtraTransaction extraTransaction = new ExtraTransaction();
			CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();

			// Crear la cotizacion - vehiculos 
			if(tipoConsulta.equalsIgnoreCase("crear") && producto.equalsIgnoreCase("productoVehiculo"))
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
				String mail = request.getParameter("mail") == null ? "" : request.getParameter("mail").trim();
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String porcentajeComision = request.getParameter("porcentajeComisionAgente") == null ? "" : request.getParameter("porcentajeComisionAgente").trim();
				String pxpv = request.getParameter("productoXPuntoDeVenta") == null ? "" : request.getParameter("productoXPuntoDeVenta").trim();
				
				String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");				
				String tasaProductoId = request.getParameter("tasaProductoId") == null ? "" : request.getParameter("tasaProductoId");
				String tasaProductoValor = request.getParameter("tasaProductoValor") == null ? "" : request.getParameter("tasaProductoValor");
				
				// Variables autoconsa - plan v
				String fechaCompra = request.getParameter("fechaCompra") == null ? "" : request.getParameter("fechaCompra");
				String diasExtras = request.getParameter("diasExtras") == null ? "" : request.getParameter("diasExtras");
				String mesPago = request.getParameter("mesPago") == null ? "" : request.getParameter("mesPago");
				
				// Variable agente acuerdo
				String acuerdoId = request.getParameter("acuerdoId") == null ? "" : request.getParameter("acuerdoId");
				
				String error = "";
				
				EntidadDAO entidadDAO = new EntidadDAO();
				Entidad entidad = new Entidad();
				
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = new Cliente();
				
				TipoIdentificacionDAO tipoDAO = new TipoIdentificacionDAO();
				
				entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				
					if(!identificacion.equals(""))
						entidad.setIdentificacion(identificacion);
					
					if(!codigoEntidadEnsurance.equals(""))
						entidad.setEntEnsurance(codigoEntidadEnsurance);
					
					if(!tipoIdentificacion.equals("")){
						entidad.setTipoIdentificacion(tipoDAO.buscarPorId(tipoIdentificacion));
						TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
					if(tipoDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")){
						entidad.setNombres("");
						entidad.setApellidos("");
						
						if(nombreCompleto.equals("")  && cotizacionId=="")
							error ="Ingrese Nombre Empresa";
						
						entidad.setNombreCompleto(nombreCompleto.toUpperCase());
						entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));
					}else{
						if(nombres.equals("")  && cotizacionId=="")
							error ="Ingrese Nombres Cliente";
						if(apellidos.equals("")  && cotizacionId=="")
							error ="Ingrese Apellidos Cliente";
						
						entidad.setNombres(nombres.toUpperCase());
						entidad.setApellidos(apellidos.toUpperCase());
						entidad.setNombreCompleto(nombres.toUpperCase() + " " + apellidos.toUpperCase());
						entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
					}
					}
					if(!mail.equals(""))
					entidad.setMail(mail);
														
					if(puntoVentaId.equals("")  && cotizacionId=="")
						error ="Ingrese un Punto de Venta";
					if(vigenciaPoliza.equals("") && cotizacionId=="")
						error ="Ingrese la Vigencia de la Poliza";
					if(identificacion.equals("") && cotizacionId=="")
						error ="Ingrese la Identificacion del Cliente";
					if(agenteId.equals("") && cotizacionId=="")
						error ="Seleccione un Agente";
					
					
					
				// Validacion para evitar errores al crear la cotizacion
				if(error.equalsIgnoreCase("")){
					
					if(entidad.getId()==null)
						entidad=entidadTransaction.crear(entidad);
					
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
				
				TipoObjetoDAO toDAO = new TipoObjetoDAO();
				
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
				
				if(!porcentajeComision.equals("")){
		 	        cotizacion.setPorcentajeComision(Double.parseDouble(porcentajeComision)); // Porcentaje comision agente
				}
				
				if(!acuerdoId.equals("")){
		 	        cotizacion.setAgenteAcuerdo(acuerdoId);
				}

				if(cotizacion.getVigenciaDesde()==null){
					// Grabar fecha de vigencia - Autoconsa - Plan V
					if(fechaCompra.equals(""))
						cotizacion.setVigenciaDesde(new Date());
					else{
						Date fechaCompraValor = new Date();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");						
						fechaCompraValor = df.parse(fechaCompra); 	
						Calendar calendar = Calendar.getInstance();
					    calendar.setTime(fechaCompraValor);					    
					    int diasExtrasValor = Integer.parseInt(diasExtras);
					    calendar.add(7, diasExtrasValor);
					    Date fechaActual = new Date();
					    Timestamp fechaInicioVigencia = new Timestamp(fechaActual.getTime());					    
					    fechaInicioVigencia.setTime(calendar.getTime().getTime());
					    cotizacion.setVigenciaDesde(fechaInicioVigencia);													
					}
				}
									
				if(cliente.getId() != null)
					cotizacion.setClienteId(BigInteger.valueOf(Long.valueOf(cliente.getId())));
				if(cotizacion.getAsegurado()==null)
					cotizacion.setAsegurado(cliente.getEntidad());

				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
				
				if(tasaProductoValor.length()==0){
			    	tasaProductoValor="0";
			    }
				
				GrupoPorProducto grupoPorProducto = new GrupoPorProducto();
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
					grupoPorProducto = grupoPorProductoDAO.buscarPorId("148");					
					cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
					cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
					cotizacion.setTipoObjeto(toDAO.buscarPorId("1"));
					cotizacion.setProducto(grupoPorProducto.getProducto());					
				}else{
					if(!grupoPorProductoId.equals("")){
						grupoPorProducto =  grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
						cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
						cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
						cotizacion.setProducto(grupoPorProducto.getProducto());
						
						// Agregamos la tasa
						if(tasaProductoId.length() == 0) {
							cotizacion.setTasaProductoId(new BigInteger("0"));
							// Valor por defecto productos formulados
							if(tasaProductoValor.equalsIgnoreCase("Formulada"))
								cotizacion.setTasaProductoValor(new Double(0));
							else
								cotizacion.setTasaProductoValor(new Double(tasaProductoValor));
						}else{	
							if(tasaProductoId.length()>0){ // Validacion tasaProductoId null							
								cotizacion.setTasaProductoId(BigInteger.valueOf(Long.valueOf(tasaProductoId)));					
								TasaProducto tasaProducto = tasaProductoDAO.buscarPorId(tasaProductoId);
								cotizacion.setTasaProductoValor(tasaProducto.getPorcentajeCasco());
							}	
						}				
					}
				}

				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();	
				TipoObjeto tipoObjetoVehiculo = new TipoObjeto();
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("1");
				}				
				if(tipoObjeto.equalsIgnoreCase("Livianos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("4");
				}
				if(tipoObjeto.equalsIgnoreCase("Motos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("5");
				}
				if(tipoObjeto.equalsIgnoreCase("Pesados")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("2");
				}
				if(tipoObjeto.equalsIgnoreCase("Publicos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("6");
				}
				
				cotizacion.setTipoObjeto(tipoObjetoVehiculo);
				if(cotizacion.getEstado()==null)
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));
				
				if(cotizacion.getUsuario()==null){
				cotizacion.setUsuario(usuario);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());						
				if(!fechaActual.equals(""))
					// Grabar fecha de compra - Autoconsa - Plan V
					if(fechaCompra.equals(""))
						cotizacion.setFechaElaboracion(fechaActual);
					else{
						Date fechaCompraValor = new Date();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						fechaCompraValor = df.parse(fechaCompra); 	
						java.sql.Timestamp timestampFechaCompraValor = new Timestamp(fechaCompraValor.getTime());
						cotizacion.setFechaElaboracion(timestampFechaCompraValor);						
						// Guardamos los valores de mes de pago y dias extras de plan v
						cotizacion.setInformacionAdicional("mesPago="+mesPago+"|diasExtras="+diasExtras);
					}
				}
				
				if(cotizacion.getEtapaWizard()<1){
					cotizacion.setEtapaWizard(1);
				}
				
				if(cotizacion.getNumeroFactura()==null){
					if(cotizacion.getId()!=null)
						cotizacion = cotizacionTransaction.editar(cotizacion);	
					else
						cotizacion = cotizacionTransaction.crear(cotizacion);
				}
				
				grupoPorProducto = new GrupoPorProducto();
				grupoPorProductoDAO = new GrupoPorProductoDAO();
				grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());
				
				ProductoXPuntoVenta productoXPuntoVenta =  new ProductoXPuntoVenta();
				ProductoXPuntoVentaDAO productoXPuntoVentaDAO =  new ProductoXPuntoVentaDAO();
				productoXPuntoVenta = (ProductoXPuntoVenta) productoXPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, cotizacion.getPuntoVenta());
				result.put("unidadNegocioId", productoXPuntoVenta.getUnidadNegocio().getId());
				result.put("cotizacionId",cotizacion.getId());				
				}else{
					result.put("error",error);
				}
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
				if(estadoNombre.equals("Revision Aprobada"))
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
				}
				cotizacion.setEtapaWizard(4);
				cotizacion = cotizacionTransaction.editar(cotizacion);
				
				result.put("cotizacionId",cotizacion.getId());
			}
			
			//Crear la cotización de ganadero fzurita
			if(tipoConsulta.equalsIgnoreCase("crear") && producto.equalsIgnoreCase("ganadero"))
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
				Entidad entidad = new Entidad();
				
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = new Cliente();
				
				TipoIdentificacionDAO tipoDAO = new TipoIdentificacionDAO();
				
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
					entidad.setTipoIdentificacion(tipoDAO.buscarPorId(tipoIdentificacion));
				
				entidad.setMail(mail);
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				
				if(tipoDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")){
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
				cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorId("7"));
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));
				
				if(cotizacion.getId()==null)
				cotizacion.setUsuario(usuario);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
				
				if(!fechaActual.equals(""))
					cotizacion.setFechaElaboracion(fechaActual);
				
				if(cotizacion.getEtapaWizard()<1){
					cotizacion.setEtapaWizard(1);
				}
				
				if(cotizacion.getId()!=null)
					cotizacion = cotizacionTransaction.editar(cotizacion);	
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
			//Fin de la creación de la cotización de ganadero
			
			//evaldez agregar solicitudes de descuento
 			if(tipoConsulta.equalsIgnoreCase("agregarSolicitudDescuento"))
			{			
				//String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");		
				String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");		
				String porcentaje = request.getParameter("porcentaje") == null ? "" : request.getParameter("porcentaje");		
				String descuentoId = request.getParameter("descuento") == null ? "" : request.getParameter("descuento");		
				String cotizacionId = request.getParameter("cotizacion") == null ? "" : request.getParameter("cotizacion");		
				String punto_venta = request.getParameter("punto_venta") == null ? "" : request.getParameter("punto_venta");		
				String motivoId = request.getParameter("motivoId") == null ? "" : request.getParameter("motivoId");	
				
				if(usuario==null||usuario.getId()==null)
					throw new Exception("No se pudo solicitar el descuento! Por favor vuelva a iniciar sesión!");
				
				SolicitudDescuento sd= new SolicitudDescuento();
				SolicitudDescuentoDAO sdDAO= new SolicitudDescuentoDAO();
				
				sd=sdDAO.buscarPorCotizacion(cotizacion);
								
				CotizacionDAO cDAO= new CotizacionDAO();
				cotizacion=cDAO.buscarPorId(cotizacionId);
				sd.setCotizacion(cotizacion);
				
				Descuento descuento= new Descuento();
				DescuentoDAO dDAO = new DescuentoDAO();
				descuento=dDAO.buscarPorId(descuentoId);
				sd.setDescuento(descuento);
				
				EstadoDAO estadoDAO= new EstadoDAO();
				sd.setEstado(estadoDAO.buscarPorId("6"));
				
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				sd.setSucursal( puntoVentaDAO.buscarPorId(punto_venta).getSucursal() );
				
				MotivoDescuentoDAO mdDAO = new MotivoDescuentoDAO();
				sd.setMotivoDescuento(mdDAO.buscarPorId(motivoId) );
				
				GrupoAutorizacion grupoAutorizacion=descuento.getGrupoAutorizacion();
				
				GrupoUsuarioAutorizacionDAO guaDAO= new GrupoUsuarioAutorizacionDAO();
				List<GrupoUsuarioAutorizacion> gruposUsuarioA = guaDAO.buscarPorGrupoAutorizacion(grupoAutorizacion);
				
				//falta enviar la unidad de negocio del usuario				
				UnidadNegocioDAO unidadNegocioDAO = new UnidadNegocioDAO();
				sd.setUnidadNegocio(unidadNegocioDAO.buscarPorId("1"));
				
				sd.setPorcentaje(porcentaje);
				
				sd.setDescripcion(descripcion);
				
				//	sd.setNombre(nombre);
				if(sd.getId()==null)
					sd=solicitudDescuentoTransaction.crear(sd);
				else
					sd=solicitudDescuentoTransaction.editar(sd);
				
				cotizacion.setEtapaWizard(3);
				cotizacionTransaction.editar(cotizacion);
				
				String cargoAutoriza =descuento.getCargoAutoriza() ;
				
				String rutaPlantilla = this.getServletContext()
						.getRealPath("")
						+ "/static/plantillas/confirmarSolicitudDescuento.html";
				FileReader fr = null;
				BufferedReader br = null;

				String cuerpoMail = "";
				try {
					File archivo = new File(rutaPlantilla);
					fr = new FileReader(archivo);
					br = new BufferedReader(fr);

					String linea;

					while ((linea = br.readLine()) != null) {
						cuerpoMail = cuerpoMail + linea;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				br.close();
				fr.close();

				Cliente cli = new Cliente();
				ClienteDAO cliDAO = new ClienteDAO();
				cli = cliDAO.buscarPorId(cotizacion.getClienteId().toString());
				

				List<CotizacionDetalle> cd = cotizacion.getCotizacionDetalles();
				ProductoDAO pDAO=new ProductoDAO();
				Producto productoObjeto = pDAO.buscarPorId(sd.getCotizacion().getProducto().getId().toString());
				
				cuerpoMail = cuerpoMail.replace("#usuarioSolicita#",usuario.getEntidad().getNombreCompleto());
				cuerpoMail = cuerpoMail.replace("#porcentajeDescuento#", sd.getPorcentaje());
				cuerpoMail = cuerpoMail.replace("#nombreProducto#",productoObjeto.getNombre());
				cuerpoMail = cuerpoMail.replace("#valorPrima#",new DecimalFormat("#.##").format(new Double(cotizacion.getPrimaOrigen())));
				cuerpoMail = cuerpoMail.replace("#nombreMotivo#", sd
						.getMotivoDescuento().getNombre());
				cuerpoMail = cuerpoMail.replace(
						"#descripcionDescuento#", sd.getDescripcion());
				
				String tablaCliente ="";
				tablaCliente += "</br><table cellpadding=\"5\" style=\"font-family: Arial; font-size: 14px;\"><tbody><tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Sucursal</b></td>";
				tablaCliente += "<td>" + sd.getSucursal().getNombre()
						+ "</td></tr>";

				tablaCliente += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Punto de Venta</b></td>";
				tablaCliente += "<td>" + puntoVentaDAO.buscarPorId(punto_venta).getNombre() + "</td></tr>";

				tablaCliente += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Nombre del Cliente</b></td>";
				tablaCliente += "<td>" + cli.getEntidad().getNombreCompleto() + "</td></tr>";
				
				tablaCliente += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Identificacion del Cliente</b></td>";
				tablaCliente += "<td>" + cli.getEntidad().getIdentificacion() + "</td></tr>";
				
				tablaCliente += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Mail del Cliente</b></td>";
				tablaCliente += "<td>" + cli.getEntidad().getMail() + "</td></tr>";
				
				tablaCliente +="</tbody></table>";
				
				
				String tablaDetalle = "<table><tbody>";

				for (int j = 0; j < cd.size(); j++) {
					ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
					ObjetoVehiculo ov = ovDAO.buscarPorId(cd.get(j)
							.getObjetoId());
					tablaDetalle += "<tr><td><span style=\"font-weight: bold; color: #009AE4;\"font-family: Gotham, 'Helvetica Neue', Helvetica, Arial, sans-serif; font-weight: normal; font-size: 16px;\">Vehiculo "
							+ (j + 1) + "</span></td>";
					tablaDetalle += "<tr><table  cellpadding=\"5\" style=\"font-family: Arial; font-size: 14px;\"><tbody>";
					// marca
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Marca</b></td>";
					tablaDetalle += "<td>"
							+ ov.getModelo().getMarca().getNombre()
							+ "</td>";
					// modelo
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Modelo</b></td>";
					tablaDetalle += "<td>" + ov.getModelo().getNombre()
							+ "</td>";
					// año
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Año de Fabricaci&oacute;n</b></td>";
					tablaDetalle += "<td>" + ov.getAnioFabricacion()
							+ "</td>";
					// monto
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Suma Asegurada</b></td>";
					tablaDetalle += "<td>" + cd.get(j).getSumaAseguradaItem() + "</td>";
					
					// tasaRiesgo
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Tasa Riesgo</b></td>";
					tablaDetalle += "<td>" + new DecimalFormat("#.##").format(cd.get(j).getTasa()*100) + "</td>";
					
					// edad
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Edad Conductor</b></td>";
					tablaDetalle += "<td>"+ov.getConductorEdad()+"</td>";
					
					// genero
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Genero Conductor</b></td>";
					tablaDetalle += "<td>" +ov.getConductorGenero()+ "</td>";
					
					// estado civil
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Estado Civil</b></td>";
					tablaDetalle += "<td>" + ov.getConductorEstadoCivil() + "</td>";
					
					// Tipo de uso
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Tipo de Uso</b></td>";
					tablaDetalle += "<td>" + ov.getTipoUso().getNombre() + "</td>";
					
					// Tipo de Vehículo.
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Tipo Vehiculo</b></td>";
					tablaDetalle += "<td>" + ov.getTipoVehiculo().getNombre() + "</td>";
					
					// Kms. Recorridos.
					tablaDetalle += "<tr><td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Kilometros Recorridos</b></td>";
					if(ov.getKilometrosRecorridos()==null||ov.getKilometrosRecorridos().equals(""))
						tablaDetalle += "<td>0</td>";
					else
						tablaDetalle += "<td>" + ov.getKilometrosRecorridos() + "</td>";
					
					tablaDetalle +="</tr></tbody></table>";
					
					
				}

				tablaDetalle+="</tbody></table>";

				cuerpoMail = cuerpoMail.replace("#tablaDetalles#",
						tablaDetalle);
				
				cuerpoMail = cuerpoMail.replace("#tablaCliente#",
						tablaCliente);

				String link = request.getRequestURL().toString();

				String urlAutorizar = link.replace(
						"/CotizacionController", "")
						+ "/SolicitudDescuentoController?sdid="
						+ Utilitarios.numeroRandomico(4)
						+ sd.getId()
						+ Utilitarios.numeroRandomico(9);
						
				cuerpoMail = cuerpoMail.replace("#urlImagenes#",
						link.replace("/CotizacionController", ""));
				cuerpoMail = cuerpoMail.replace(
						"#linkAutorizarSolicitud#", urlAutorizar);

				
				if(gruposUsuarioA.size() > 0 && sd.getId()!=null){
					for (int i = 0; i < gruposUsuarioA.size(); i++) {
						System.out.println(gruposUsuarioA.get(i).getEmpleado().getCargo().getNombreGenerico());
						if (gruposUsuarioA.get(i).getEmpleado().getCargo().getNombreGenerico().equals(cargoAutoriza)) {
							Empleado emp = new Empleado();
							EmpleadoDAO empDAO = new EmpleadoDAO();
							emp = empDAO.buscarPorId(gruposUsuarioA.get(i).getEmpleado().getId());
							// reemplazo de valores del titulo
							if(cargoAutoriza.equals("VICEPRESIDENTE"))	
								Utilitarios.envioMail(emp.getEntidad().getMail(),"Mail de Solicitud de Descuento", cuerpoMail);
							else
								if(emp.getSucursal()!=null&&emp.getSucursal().getSucEnsurance().equals(sd.getSucursal().getSucEnsurance()))
									Utilitarios.envioMail(emp.getEntidad().getMail(),"Mail de Solicitud de Descuento", cuerpoMail);
						}
					}
				}else{
					if(descuento.getGrupoAutorizacion().getEmpleado().getCargo().getNombreGenerico().equals(cargoAutoriza)){
						Empleado emp = new Empleado();
						EmpleadoDAO empDAO = new EmpleadoDAO();
						emp = empDAO.buscarPorId(descuento.getGrupoAutorizacion().getEmpleado().getId());
						// reemplazo de valores del titulo
						if(cargoAutoriza.equals("VICEPRESIDENTE"))	
							Utilitarios.envioMail(emp.getEntidad().getMail(),"Mail de Solicitud de Descuento", cuerpoMail);
						else
							if(emp.getSucursal()!=null&&emp.getSucursal().getSucEnsurance().equals(sd.getSucursal().getSucEnsurance()))
								Utilitarios.envioMail(emp.getEntidad().getMail(),"Mail de Solicitud de Descuento", cuerpoMail);
					}
				}
				
			}
			
			// Metodo agregar un vehiculo a la cotizacionayanez
			if(tipoConsulta.equalsIgnoreCase("actualizarEstado") )
			{	
				String estadoId = request.getParameter("estado") == null ? "" : request.getParameter("estado");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				
				cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorNombreClase(estadoId, "Cotizacion");
				
				if(!cotizacion.getEstado().getNombre().equals("Borrador"))
					throw new Exception("La poliza se encuentra en estado "+cotizacion.getEstado().getNombre()+" no la puede actualizar");
				
				if(estado!=null)
					if(estado.getId()!=null)
						cotizacion.setEstado(estado);
				cotizacionTransaction.editar(cotizacion);
			}
			
			// Metodo agregar un vehiculo a la cotizacionayanez
			if(tipoConsulta.equalsIgnoreCase("agregarVehiculoCotizacion") && producto.equalsIgnoreCase("productoVehiculo"))
			{			
				String codigoVehiculoEnsurance = request.getParameter("codigoVehiculoEnsurance")  == null ? "" : request.getParameter("codigoVehiculoEnsurance");
				String placa = request.getParameter("placa")  == null ? "" : request.getParameter("placa").trim();
				String chasis = request.getParameter("chasis")  == null ? "" : request.getParameter("chasis").trim();
				String motor = request.getParameter("motor")  == null ? "" : request.getParameter("motor").trim();
				String vehiculoId = request.getParameter("vehiculoId")  == null ? "" : request.getParameter("vehiculoId");
				String modeloId = request.getParameter("modelo") == null ? "" : request.getParameter("modelo");
				String sucursalId = request.getParameter("sucursal_id") == null ? "" : request.getParameter("sucursal_id");
				String anioFabricacion = request.getParameter("anio_fabricacion") == null ? "" : request.getParameter("anio_fabricacion");
				String sumaAsegurada = request.getParameter("suma_asegurada_valor") == null ? "" : request.getParameter("suma_asegurada_valor");
				String sumaAseguradaArr = request.getParameter("suma_asegurada_valor_arr") == null ? "" : request.getParameter("suma_asegurada_valor_arr");
				String conDispositivoRastreo = request.getParameter("disposito_rastreo") == null ? "" : request.getParameter("disposito_rastreo");
				String esCeroKilometro = request.getParameter("cero_kilometros") == null ? "" : request.getParameter("cero_kilometros");
				String antiguedadVh = request.getParameter("antiguedad_vh") == null ? "" : request.getParameter("antiguedad_vh");
				String conductorEdad = request.getParameter("conductor_edad") == null ? "" : request.getParameter("conductor_edad");
				String conductorGenero = request.getParameter("conductor_genero") == null ? "" : request.getParameter("conductor_genero");
				String conductorEstadoCivil = request.getParameter("conductor_estado_civil") == null ? "" : request.getParameter("conductor_estado_civil");
				String numeroHijos = request.getParameter("numero_hijos") == null ? "" : request.getParameter("numero_hijos");
				String kmRecorridos = request.getParameter("km_recorridos") == null ? "" : request.getParameter("km_recorridos");
				String combustible = request.getParameter("combustible") == null ? "" : request.getParameter("combustible");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String coberturasAdicionalesStr = request.getParameter("coberturasAdicionales");
				String colorId = request.getParameter("color") == null ? "" : request.getParameter("color");
				String tipoAdquisicionId = request.getParameter("codigoTipoAdquisicion") == null ? "" : request.getParameter("codigoTipoAdquisicion");
				String tonelaje = request.getParameter("tonelaje") == null ? "" : request.getParameter("tonelaje");
				String necesitaInspeccion = request.getParameter("necesitaInspeccion") == null ? "" : request.getParameter("necesitaInspeccion");
				String tasaVehiculosCerrados = request.getParameter("tasaVehiculosCerrados") == null ? "" : request.getParameter("tasaVehiculosCerrados");
				String valorExcesoRC = request.getParameter("valorExcesoRC") == null ? "" : request.getParameter("valorExcesoRC");
				String valoresExtras = request.getParameter("valoresExtras") == null ? "" : request.getParameter("valoresExtras");
				//String recalculoTasa = request.getParameter("recalculoTasa") == null ? "" : request.getParameter("recalculoTasa");
				
				ModeloDAO modeloDAO = new ModeloDAO();				
				Modelo modelo = modeloDAO.buscarPorId(modeloId);
				
				ColorDAO colorDAO = new ColorDAO();				
				Color color = colorDAO.buscarPorId(colorId);
				
				TipoVehiculoDAO tipoVehiculoDAO = new TipoVehiculoDAO();
				TipoVehiculo tipoVehiculo = null;
				
				if(!codigoTipoVehiculo.equals(""))
					tipoVehiculo= tipoVehiculoDAO.buscarPorId(codigoTipoVehiculo);

				TipoUsoDAO tipoUsoDAO = new TipoUsoDAO();
				TipoUso tipoUso = tipoUsoDAO.buscarPorId(codigoTipoUso); 				
				
				SucursalDAO sucursalDAO = new SucursalDAO();
				Sucursal sucursal = sucursalDAO.buscarPorId(sucursalId);
				
				GrupoPorProducto grupoPorProducto  = new GrupoPorProducto();
				
				ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
				ObjetoVehiculo vehiculo = new ObjetoVehiculo();
				
				if(!placa.equals("") && vehiculoId.equals(""))
					vehiculo = objetoVehiculoDAO.buscarPorPlaca(placa);
				else if(!chasis.equals("") && vehiculoId.equals(""))
					vehiculo = objetoVehiculoDAO.buscarPorChasis(chasis);	

				if(vehiculoId!="")
					vehiculo=objetoVehiculoDAO.buscarPorId(vehiculoId);
				
				vehiculo.setCodigoEnsurance(codigoVehiculoEnsurance);
				vehiculo.setModelo(modelo);
				vehiculo.setColor(color);
				vehiculo.setTipoVehiculo(tipoVehiculo);
				vehiculo.setMotor(motor.toUpperCase());
				vehiculo.setChasis(chasis.toUpperCase());
				vehiculo.setPlaca(placa.toUpperCase());
				vehiculo.setAnioFabricacion(anioFabricacion);
				vehiculo.setAntiguedadVh(antiguedadVh);
				vehiculo.setConductorEdad(conductorEdad);
 				vehiculo.setConductorGenero(conductorGenero);
				vehiculo.setConductorEstadoCivil(conductorEstadoCivil);
				vehiculo.setNumeroHijos(numeroHijos);
				vehiculo.setZona(request.getParameter("zona"));
				
				if(kmRecorridos.trim().equals(""))
					kmRecorridos="-1";
				
				vehiculo.setKilometrosRecorridos(kmRecorridos);
				vehiculo.setCombustible(combustible);
				vehiculo.setTipoAdquisicion(tipoAdquisicionId);
				vehiculo.setSucursalId(sucursal.getId());
				vehiculo.setTipoUso(tipoUso);
				if(tonelaje!=""){
					Double tonelajeValor = Double.parseDouble(tonelaje);
					vehiculo.setTonelajeVehiculo(tonelajeValor);	
				}
				
				Boolean valorRastreo = false;
				if (conDispositivoRastreo.equalsIgnoreCase("1"))
					valorRastreo = true;						
				vehiculo.setDispositivoRastreo(valorRastreo);
				
				Boolean valorCeroKilometros = false;
				if (esCeroKilometro.equalsIgnoreCase("1"))
					valorCeroKilometros = true;
				vehiculo.setCeroKilometros(valorCeroKilometros);
								
				if(sumaAsegurada==null){
					sumaAsegurada = "0";
				}
				Double sumaAseguradaValor = Double.parseDouble(sumaAsegurada);
				vehiculo.setSumaAsegurada(sumaAseguradaValor);
				
				// Obtener los numeros de reclamos pagados por medio de la placa
				//WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				//int anoSinSiniestro = webService.getWebServiceCotizadorImplPort().obtenerNumeroReclamosPorPlaca(request.getParameter("placa"));
				vehiculo.setAnosSin_Siniestro(String.valueOf("0"));

				Boolean valorGuardaGarage = false;
				if (request.getParameter("guarda_garage").equalsIgnoreCase("1"))
					valorGuardaGarage = true;		
				vehiculo.setGuardaGarage(valorGuardaGarage);

				boolean vehiculoExistente = false;
				if(vehiculo.getId() != null && vehiculo.getId() != "")
					vehiculoExistente = true;
								
				if(vehiculoExistente)
					vehiculo=objetoVehiculoTransaction.editar(vehiculo);
				else
					vehiculo=objetoVehiculoTransaction.crear(vehiculo);
				
				TipoExtraDAO tipoExtraDAO = new TipoExtraDAO();
				Extra extra = new Extra();
				ExtraDAO extraDAO = new ExtraDAO();
				
				String[] arrListaExtrasIds = listaExtrasIds.split(","); 
				String[] arrListaExtrasDetalles = listaExtrasDetalles.split(","); 
				String[] arrListaExtrasValores = listaExtrasValores.split(",");
				String[] arrListaExtrasValoresOtrosAnios = valoresExtras.split(";"); 
				
				
				if(!cotizacionId.equals("")){
					cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
					//evaldez cambiamos la etapa de la cotizacion
					if(cotizacion.getEtapaWizard()<2){
						cotizacion.setEtapaWizard(2);
						if(cotizacion.getNumeroFactura()==null)
							cotizacion=cotizacionTransaction.editar(cotizacion);
					}
				}
				
				double valorDescuento=1-(cotizacion.getValorDescuento()/100);
				
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();	
				TipoObjeto tipoObjetoVehiculo = new TipoObjeto();
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("1");
				}				
				if(tipoObjeto.equalsIgnoreCase("Livianos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("4");
				}
				if(tipoObjeto.equalsIgnoreCase("Motos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("5");
				}
				if(tipoObjeto.equalsIgnoreCase("Pesados")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("2");
				}
				if(tipoObjeto.equalsIgnoreCase("Publicos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorId("6");
				}
				
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
				CotizacionDetalle cotizacionDetalleExiste = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(vehiculoId, cotizacion);
				if (cotizacionDetalleExiste.getId() != null ){
					cotizacionDetalle = cotizacionDetalleExiste;
				}else{
					cotizacionDetalle.setCotizacion(cotizacion);
					cotizacionDetalle.setTipoObjetoId(tipoObjetoVehiculo.getId());
					cotizacionDetalle.setObjetoId(vehiculo.getId());
					if(cotizacion.getNumeroFactura()==null)
						cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
				}
				
				// Asignación de la coberturas del vehiculo
				String coberturaTR = request.getParameter("coberturaTR_check");
				String coberturaDT = request.getParameter("coberturaDT_check");
				String coberturaRC = request.getParameter("coberturaRC_check");
				String porcentajeSumaAsegurada = request.getParameter("porcentaje_suma_asegurada");
				String montoFijo = request.getParameter("monto_fijo");
				String valorSiniestro = request.getParameter("valor_siniestro");
				
				int dispositivoRastreo = Integer.parseInt(request.getParameter("disposito_rastreo"));
				// Asignacion de la prima cotizador local

				Double valorPrimaPuraRT = 0.0;
				Double valorPrimaPuraCHT = 0.0;
				Double valorPrimaPuraRC = 0.0;
				Double valorPrimaPuraDP = 0.0;
				Double primaTasa = 0.0;

				AgenteDAO agenteDAO = new AgenteDAO();
				Agente agente = new Agente();
				
				String agenteId = request.getParameter("agenteId") == null ? "" : request.getParameter("agenteId");
				
				if(!agenteId.equals(""))
					agente = agenteDAO.buscarPorId(agenteId);


				VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
				List<String> variableSistemaList = new ArrayList<String>();
				variableSistemaList.add("TARIFICADOR_LOCAL");
				String tarificadorLocal = variableSistemaDAO.buscarPorNombres(variableSistemaList).get(0);
				Double totalTodoRisgo=0.0;
				Double totalDanioTotal=0.0;
				Double totalResponsabilidadCivil=0.0;
				
				/**
				 * C A L C U L O   T O T A L   D E   E X T R A S 
				 */

				//eliminar extras antiguos
				List<Extra> extrasAntiguos=extraDAO.buscarPorObjetoVehiculo(vehiculo);
				if(extrasAntiguos!=null&&extrasAntiguos.size()>0){
					for(int i=0;i<extrasAntiguos.size();i++){
						extraTransaction.eliminar(extrasAntiguos.get(i));
					}
				}

				double primaNoAfectaMonto=0;
				// Verificamos valores de prima
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
				
				if(tarificadorLocal.equalsIgnoreCase("1")){
					if(coberturaTR.equalsIgnoreCase("true")){
						valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), dispositivoRastreo, modelo);
						valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
						valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal,Integer.parseInt(anioFabricacion), modelo);
						valorPrimaPuraDP = MotorTarifador.calcularPrimaDanoParcial(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), modelo,Double.parseDouble(montoFijo),Double.parseDouble(valorSiniestro),Double.parseDouble(porcentajeSumaAsegurada));
						totalTodoRisgo += MotorTarifador.calcularPrimaTasaTodoRiesgo(valorPrimaPuraRT, valorPrimaPuraCHT, valorPrimaPuraRC, valorPrimaPuraDP, agente.getComisionVh(), Double.parseDouble(sumaAsegurada));
						primaTasa+=totalTodoRisgo;
					}
					
					if(coberturaDT.equalsIgnoreCase("true")&&coberturaRC.equalsIgnoreCase("false")){					
						valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), dispositivoRastreo, modelo);
						valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
						totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT, valorPrimaPuraCHT, agente.getComisionVh(), Double.parseDouble(sumaAsegurada));
						primaTasa+=totalDanioTotal;
						
					}
					
					if(coberturaRC.equalsIgnoreCase("true")&&coberturaDT.equalsIgnoreCase("false")){
						valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), modelo);
						totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(valorPrimaPuraRC, agente.getComisionVh(), Double.parseDouble(sumaAsegurada));
						primaTasa+=totalResponsabilidadCivil;
					}
					
					if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")){
						valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), dispositivoRastreo, modelo);
						valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
						totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT, valorPrimaPuraCHT, agente.getComisionVh(), Double.parseDouble(sumaAsegurada));
						
						valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal, Integer.parseInt(anioFabricacion), modelo);
						totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(valorPrimaPuraRC, agente.getComisionVh(), Double.parseDouble(sumaAsegurada));
						primaNoAfectaMonto+=totalResponsabilidadCivil;
						primaTasa+=totalDanioTotal;
					}
				}else{
					
					List<ObjetoVehiculo> listadoVehiculos = new ArrayList<ObjetoVehiculo>();
					listadoVehiculos.add(vehiculo);
					String xml = Utilitarios.generarEstructuraXMLVH(cotizacion, listadoVehiculos);
					ManejoColas.productorMensajes(xml);
					System.out.println(xml);
					ManejoColas.consumiMensajes();
					ManejoFTP.subirXMLFTP(xml, cotizacion.getId());
				}
				}else{
					//
					int numeroCoberturasPrincipales=0;
					if(coberturaDT.equalsIgnoreCase("true")){
						numeroCoberturasPrincipales+=1;
					}
					if(coberturaRC.equalsIgnoreCase("true")){
						numeroCoberturasPrincipales+=1;
					}
					if(coberturaTR.equalsIgnoreCase("true")){
						numeroCoberturasPrincipales+=1;
					}
					
					
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();	
					grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());
					// Verificamos si la tasa es fija, formulada o tiene tasa variable
					if(grupoPorProducto.getTasaFija() && grupoPorProducto.getFormulada()== false ){ // Tasa productos tasa fija
						primaTasa = (grupoPorProducto.getPorcentajeTasaFija()*sumaAseguradaValor)/100;
					}
					if(grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada()== false ){ // Tasa productos tasa varios valores
						TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
						TasaProducto tasaProducto = new TasaProducto();
						tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());						
						primaTasa = (tasaProducto.getPorcentajeCasco()*sumaAseguradaValor)/100;
						// porcentajeExtras = Double.parseDouble(resultado.get(key).toString());
					}
					if(grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada() ){ // Tasa productos formulados
						primaTasa = (Double.parseDouble(tasaVehiculosCerrados)*sumaAseguradaValor)/100;						
					}
					
					totalResponsabilidadCivil=primaTasa/numeroCoberturasPrincipales;
					totalDanioTotal=primaTasa/numeroCoberturasPrincipales;
					totalTodoRisgo=primaTasa/numeroCoberturasPrincipales;
				}
				
				/*EXTRAS*/
				double sumaExtrasTodosAnios=0.0;
				double sumaExtras=0.0;
				double sumaVehiculoTodosAnios=0.0;
				double sumaVehiculo=sumaAseguradaValor;
				double tasa=primaTasa/sumaAseguradaValor;
				double primaTotalExtras=0.0;
				double primaTotalVehiculo=0.0;
				
				//se agregan extras nuevos
				for(int i=1; i< arrListaExtrasIds.length; i++){
					String [] valoresExtra=arrListaExtrasValoresOtrosAnios[i-1].split(",");
					double sumaExtraTodosAnios=0.0;
					for(int j=0;j<valoresExtra.length;j++){
						sumaExtraTodosAnios+=Double.parseDouble(valoresExtra[j]);
					}
					
					Double valorExtra=Double.parseDouble(arrListaExtrasValores[i]);
					extra.setTipoExtra(tipoExtraDAO.buscarPorId(arrListaExtrasIds[i]));
					extra.setObjetoVehiculo(vehiculo);
					extra.setDescripcion(arrListaExtrasDetalles[i]);
					extra.setValorAsegurado(valorExtra);
					sumaExtrasTodosAnios+=sumaExtraTodosAnios;
					sumaExtras+=valorExtra;
					if(cotizacion.getNumeroFactura()==null)
						extraTransaction.crear(extra);
				}				
				
				//tasa minima evaldez
				
				Double tasaMinima=Double.parseDouble(variableSistemaDAO.buscarPorNombre("TASA_MINIMA").getValor());
				
				//entra solo cuando la tasa calculada es menor a la minima
				if(tipoObjeto.equals("VHDinamico")&&tasa<tasaMinima&&coberturaTR.equalsIgnoreCase("true"))	{	//solo aplica a todo riesgo de dinamicos evaldez		
					tasa=tasaMinima;
					Double primaTasaAnterior=primaTasa;
					primaTasa = vehiculo.getSumaAsegurada() * tasa;
					totalTodoRisgo=primaTasa*totalTodoRisgo/primaTasaAnterior;
					totalResponsabilidadCivil=primaTasa*totalResponsabilidadCivil/primaTasaAnterior;
					totalDanioTotal=primaTasa*totalDanioTotal/primaTasaAnterior;
					
				}
				
				//cuando se solicita aplicar tasa minima ssiempre que sea mayor a la tasa calculada
				if(tipoObjeto.equals("VHDinamico")&&cotizacion.getTasaMinima()!=0&&cotizacion.getTasaMinima()>tasa){
					tasa=cotizacion.getTasaMinima();
					Double primaTasaAnterior=primaTasa;
					primaTasa = vehiculo.getSumaAsegurada() * tasa;
					totalTodoRisgo=primaTasa*totalTodoRisgo/primaTasaAnterior;
					totalResponsabilidadCivil=primaTasa*totalResponsabilidadCivil/primaTasaAnterior;
					totalDanioTotal=primaTasa*totalDanioTotal/primaTasaAnterior;
				}	
				
				primaTotalExtras=sumaExtrasTodosAnios*tasa;
				
				String[] arrValoresAsegurados = sumaAseguradaArr.split("[|]",0);
				for(int i=1; i<arrValoresAsegurados.length; i++){
					Double valorAseguradoDepreciado = new Double(arrValoresAsegurados[i]);
					sumaVehiculoTodosAnios+=valorAseguradoDepreciado;
				}			
				sumaVehiculoTodosAnios+=sumaVehiculo;
				primaTotalVehiculo+=sumaVehiculoTodosAnios * tasa;
				
				CotizacionCoberturaDAO cotizacionCoberturaDAO = new CotizacionCoberturaDAO();
				
				if(cotizacion.getNumeroFactura()==null)
					cotizacionCoberturaDAO.eliminarPorCotizacionDetalle(cotizacionDetalle);
				cotizacionDetalle.setCotizacionCoberturas(null);
				CoberturaDAO coberturaDAO = new CoberturaDAO();
				List<CotizacionCobertura> listaCoberturas=new ArrayList<CotizacionCobertura> ();
				
				cotizacionDetalle.setTasa(tasa);
				if(necesitaInspeccion!=null&&!necesitaInspeccion.equals("")){
					if(necesitaInspeccion.equals("true"))
						cotizacionDetalle.setNecesitaInspeccion(true);
					else
						cotizacionDetalle.setNecesitaInspeccion(false);
				}
				
				/*******************************************INICIO COBERTURA EXTRAS****************************/
				if(sumaExtrasTodosAnios>0){
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					Cobertura coberturaAAObjeto = new Cobertura();//amparo accesorios
					
					// Grabar la cobertura de amparo de accesorios
					if(tipoObjeto.equalsIgnoreCase("Livianos") || tipoObjeto.equalsIgnoreCase("Motos") || tipoObjeto.equalsIgnoreCase("Publicos")|| tipoObjeto.equalsIgnoreCase("VHDinamico")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMAC");
					}
					if(tipoObjeto.equalsIgnoreCase("Pesados")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMCE");
					}
					
					if(!tipoObjeto.equalsIgnoreCase("VHDinamico")){
						if(grupoPorProducto.getTasaFija()== false){ 					
							TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
							TasaProducto tasaProducto = new TasaProducto();
							/*Double nuevaSumaAseguradaConExtras =0.0;
							Double nuevoPrimaConExtras = 0.0;
							Double nuevaTasaConExtras = 0.0;
							*/
							// Calculo de tasa para vehiculos tasa variable
							if(cotizacion.getTasaProductoId().bitLength() != 0){
								tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());
								// Recalculo de los datos agregando los extras
								primaTotalExtras=sumaExtrasTodosAnios*(tasaProducto.getPorcentajeExtras()/100);
							}
					}
					}
										
					cotizacionCobertura.setCobertura(coberturaAAObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(0);
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setValorMonto(sumaExtras);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					cotizacionCobertura.setValorPrima(primaTotalExtras*valorDescuento);
					cotizacionCobertura.setValorPrimaOrigen(primaTotalExtras);
					//listaCoberturas.add(cotizacionCobertura);
					if(cotizacion.getNumeroFactura()==null)
						cotizacionCoberturaTransaction.crear(cotizacionCobertura);
				}
				//SE GUARDA EN LA BASE, EL MONTO SIEMPRE ES EL VALOR DE LOS EXTRAS
				/*******************************************FIN COBERTURA EXTRAS****************************/

				
				
				/*******************************************INICIO COBERTURAS PRINCIPALES****************************/

				if (coberturaTR.equalsIgnoreCase("true")){
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					Cobertura coberturaTRObjeto = new Cobertura();
					// Grabar la cobertura de todo riesgo segun el tipo de vehiculo
					if(tipoObjeto.equalsIgnoreCase("Livianos")||tipoObjeto.equalsIgnoreCase("Motos")||tipoObjeto.equalsIgnoreCase("Publicos")||tipoObjeto.equalsIgnoreCase("VHDinamico")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TORI");
					}					
					if(tipoObjeto.equalsIgnoreCase("Pesados")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TRCE");
					}

					cotizacionCobertura.setCobertura(coberturaTRObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
					cotizacionCobertura.setMontoFijo(Double.parseDouble(montoFijo));
					cotizacionCobertura.setPorcentajeValorSiniestro(Double.parseDouble(valorSiniestro));
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrimaOrigen(primaTotalVehiculo*totalTodoRisgo/primaTasa);
					cotizacionCobertura.setValorPrima(cotizacionCobertura.getValorPrimaOrigen()*valorDescuento);
					//cotizacionCobertura.setValorMonto((sumaAseguradaValor)*((totalTodoRisgo)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
				}
				
				if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")){
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					//perdida total es la suma de daño total y robo total
					Cobertura coberturaDTObjeto = coberturaDAO.buscarPorNemotecnico("DATO");
					cotizacionCobertura.setCobertura(coberturaDTObjeto);
					if(!porcentajeSumaAsegurada.equals(""))
						cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
					else
						cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble("1"));
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrimaOrigen(primaTotalVehiculo*totalDanioTotal/primaTasa);
					cotizacionCobertura.setValorPrima(cotizacionCobertura.getValorPrimaOrigen()*valorDescuento);
					//cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalDanioTotal)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
					//responsabilidad civil
					cotizacionCobertura=new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("RECI");
					cotizacionCobertura.setCobertura(coberturaTRObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(0);
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrima(primaTotalVehiculo*totalResponsabilidadCivil/primaTasa);
					//cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalResponsabilidadCivil)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
					
				}
	
				if (coberturaDT.equalsIgnoreCase("false") && coberturaRC.equalsIgnoreCase("true")){
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("RECI");
					cotizacionCobertura.setCobertura(coberturaTRObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(0);
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrimaOrigen(primaTotalVehiculo*totalResponsabilidadCivil/primaTasa);
					cotizacionCobertura.setValorPrima(cotizacionCobertura.getValorPrimaOrigen()*valorDescuento);
					//cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalResponsabilidadCivil)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
				}
				
				if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("false")){
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					Cobertura coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("DATO");
					cotizacionCobertura.setCobertura(coberturaTRObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble(porcentajeSumaAsegurada));
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrimaOrigen(primaTotalVehiculo*totalDanioTotal/primaTasa);
					cotizacionCobertura.setValorPrima(cotizacionCobertura.getValorPrimaOrigen()*valorDescuento);
					//cotizacionCobertura.setValorMonto((sumaAseguradaTotalExtras+sumaAseguradaValor)*((totalDanioTotal)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
				}
				 
				/*******************************************FIN COBERTURAS PRINCIPALES****************************/

				/*******************************************INICIO COBERTURAS ADICIONALES****************************/
				
				//coberturas adicionales evaldez
				double primaAdicionales=0.0;
				String [] coberturasAdicionales = coberturasAdicionalesStr.split(",");
				int vigenciaCotizacion=cotizacion.getVigenciaPoliza().getValor().intValue();
				double primaAfectaMonto=primaTotalVehiculo;
				primaNoAfectaMonto+=primaTotalExtras;
							
				if(coberturasAdicionales.length >= 1&&!coberturasAdicionalesStr.equals(""))
					for(int i=0;i<coberturasAdicionales.length;i++){
						CotizacionCobertura ccAdicional=new CotizacionCobertura();
						Cobertura adicional = coberturaDAO.buscarPorId(coberturasAdicionales[i]);
						if(!adicional.getId().toString().equals("6348540415022")&&!adicional.getId().toString().equals("6349173767914")){
						if (adicional.getTipoTasa().getId().equals("1")) {
							ccAdicional.setCobertura(adicional);
							ccAdicional.setCotizacionDetalle(cotizacionDetalle);
							ccAdicional.setMontoFijo(0);
							ccAdicional.setPorcentajeSumaAsegurada(0);
							ccAdicional.setPorcentajeValorSiniestro(0);
							ccAdicional.setValorPrimaOrigen(vigenciaCotizacion*adicional.getTasaValor());
							ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*valorDescuento);
							if(adicional.getAfectaValorAsegurado().equals("1")){
								primaAfectaMonto+=adicional.getTasaValor()*vigenciaCotizacion;
								primaAdicionales+=adicional.getTasaValor()*vigenciaCotizacion;
							}
							else{
								primaNoAfectaMonto+=adicional.getTasaValor()*vigenciaCotizacion;
								primaAdicionales+=adicional.getTasaValor()*vigenciaCotizacion;
							}
							
							listaCoberturas.add(ccAdicional);
							//cotizacionCoberturaDAO.crear(ccAdicional);
							//primaTasa += adicional.getTasaValor();
						}
						if (adicional.getTipoTasa().getId().equals("2")) {
							ccAdicional.setCobertura(adicional);
							ccAdicional.setCotizacionDetalle(cotizacionDetalle);
							ccAdicional.setMontoFijo(0);
							ccAdicional.setPorcentajeSumaAsegurada(0);
							ccAdicional.setPorcentajeValorSiniestro(0);
							ccAdicional.setValorPrimaOrigen(vigenciaCotizacion*adicional.getTasaValor()* (sumaVehiculoTodosAnios)/100);
							ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*valorDescuento);
							if(adicional.getAfectaValorAsegurado().equals("1")){
								primaAfectaMonto+=vigenciaCotizacion*adicional.getTasaValor()*(sumaVehiculoTodosAnios)/100;
								primaAdicionales+=vigenciaCotizacion*adicional.getTasaValor()*(sumaVehiculoTodosAnios)/100;
							}
							else{
								primaNoAfectaMonto+=vigenciaCotizacion*adicional.getTasaValor()* (sumaVehiculoTodosAnios)/100;
								primaAdicionales+=vigenciaCotizacion*adicional.getTasaValor()* (sumaVehiculoTodosAnios)/100;
						}
							listaCoberturas.add(ccAdicional);
							//cotizacionCoberturaDAO.crear(ccAdicional);
							//primaTasa += (adicional.getTasaValor() * vehiculo.getSumaAsegurada()/100);
						}
						}
						else{
							ccAdicional.setCobertura(adicional);
							ccAdicional.setCotizacionDetalle(cotizacionDetalle);
							ccAdicional.setMontoFijo(0);
							ccAdicional.setPorcentajeSumaAsegurada(0);
							ccAdicional.setPorcentajeValorSiniestro(0);
							ccAdicional.setValorPrimaOrigen(adicional.getTasaValor()*Double.parseDouble(valorExcesoRC)/100);
							ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*valorDescuento);
							ccAdicional.setValorMonto(Double.parseDouble(valorExcesoRC));
							if(adicional.getAfectaValorAsegurado().equals("1")){
								primaAfectaMonto+=adicional.getTasaValor()*Double.parseDouble(valorExcesoRC)/100;
								primaAdicionales+=adicional.getTasaValor()*Double.parseDouble(valorExcesoRC)/100;
							}
							else{
								primaNoAfectaMonto+=adicional.getTasaValor()*Double.parseDouble(valorExcesoRC)/100;
								primaAdicionales+=adicional.getTasaValor()*Double.parseDouble(valorExcesoRC)/100;
							}
							
							listaCoberturas.add(ccAdicional);
							//cotizacionCoberturaDAO.crear(ccAdicional);
							//primaTasa += adicional.getTasaValor();
						}
					}
				
				/*******************************************FIN COBERTURAS ADICIONALES****************************/
				
				
				for(int i=0;i<listaCoberturas.size();i++){
					CotizacionCobertura ccAGrabar=listaCoberturas.get(i);
					if(!ccAGrabar.getCobertura().getId().toString().equals("6348540415022")&&!ccAGrabar.getCobertura().getId().toString().equals("6349173767914")){
						if(ccAGrabar.getCobertura().getAfectaValorAsegurado().equals("1"))
						//ccAGrabar.setValorMonto((sumaAseguradaTotal)*ccAGrabar.getValorPrima()/totalPrimaAfectaMonto);
						ccAGrabar.setValorMonto((sumaAseguradaValor)*(ccAGrabar.getValorPrimaOrigen()/(primaAfectaMonto)));
					else
						ccAGrabar.setValorMonto(0);}
					//ccAGrabar.setValorPrima(primaAfectaMonto*ccAGrabar.getValorPrima()/primaAfectaMonto);
					if(cotizacion.getNumeroFactura()==null)
						cotizacionCoberturaTransaction.crear(ccAGrabar);
				}
				
				double primaTotal=primaAfectaMonto+primaNoAfectaMonto;
			//	tasa = primaTotal / sumaVehiculoTodosAnios;
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
				//	if(tasa>cotizacion.getTasaMinima()){
					cotizacionDetalle.setPrimaNetaItem(primaTotal*valorDescuento);											
					cotizacionDetalle.setPrimaNetaItemOrigen(primaTotal);											
					cotizacionDetalle.setTasa(tasa);						
				/*	}else{
						primaTotal =  cotizacion.getTasaMinima() * vehiculo.getSumaAsegurada();
						cotizacionDetalle.setPrimaNetaItem(primaTotal);
						cotizacionDetalle.setTasa(cotizacion.getTasaMinima());
					}	*/															
					cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada()+sumaExtras);
					cotizacionDetalle.setValorExtras(sumaExtras);
				}else{
					
					if(grupoPorProducto.getTasaFija()== false){ 					
						TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
						TasaProducto tasaProducto = new TasaProducto();
						Double nuevaSumaAseguradaConExtras =0.0;
						Double nuevoPrimaConExtras = 0.0;
						Double nuevaTasaConExtras = 0.0;
						
						// Calculo de tasa para vehiculos tasa variable
						if(cotizacion.getTasaProductoId().bitLength() != 0){
							tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());
							// Recalculo de los datos agregando los extras
							nuevaSumaAseguradaConExtras = sumaExtrasTodosAnios+sumaVehiculoTodosAnios;
							nuevoPrimaConExtras = ((tasaProducto.getPorcentajeExtras()/100)*sumaExtrasTodosAnios)+(tasa*sumaVehiculoTodosAnios);						
							nuevaTasaConExtras = (nuevoPrimaConExtras* 100)/nuevaSumaAseguradaConExtras;																		
							cotizacionDetalle.setPrimaNetaItemOrigen(nuevoPrimaConExtras+primaAdicionales);
							cotizacionDetalle.setPrimaNetaItem(cotizacionDetalle.getPrimaNetaItemOrigen()*valorDescuento);
							cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada()+sumaExtras);
							cotizacionDetalle.setTasa(nuevaTasaConExtras/100);
							cotizacionDetalle.setValorExtras(sumaExtras);
						}else{
							nuevaSumaAseguradaConExtras = sumaExtrasTodosAnios+sumaVehiculoTodosAnios;
							nuevoPrimaConExtras = ((tasa/100)*sumaExtrasTodosAnios)+(tasa*sumaVehiculoTodosAnios);
							nuevaTasaConExtras = (nuevoPrimaConExtras* 100)/nuevaSumaAseguradaConExtras;
							cotizacionDetalle.setPrimaNetaItemOrigen(nuevoPrimaConExtras+primaAdicionales);
							cotizacionDetalle.setPrimaNetaItem(cotizacionDetalle.getPrimaNetaItemOrigen()*valorDescuento);
							cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada()+sumaExtras);
							cotizacionDetalle.setTasa(nuevaTasaConExtras/100);
							cotizacionDetalle.setValorExtras(sumaExtras);
						}
							
						
							
						
					}else{
						cotizacionDetalle.setPrimaNetaItemOrigen(primaTotal);					
						cotizacionDetalle.setPrimaNetaItem(cotizacionDetalle.getPrimaNetaItemOrigen()*valorDescuento);
						cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada()+sumaExtras);
						cotizacionDetalle.setTasa(tasa);
						cotizacionDetalle.setValorExtras(sumaExtras);
					}
				}
					
				
				//se guarda el paquete 
				PaqueteDAO paqueteDAO = new PaqueteDAO();
				String paquete1_check = request.getParameter("paquete1_check");
				String paquete2_check = request.getParameter("paquete2_check");
				String paquete3_check = request.getParameter("paquete3_check");
				String paquete4_check = request.getParameter("paquete4_check");
				String paquete5_check = request.getParameter("paquete5_check");
				
				Paquete paquete = new Paquete();
				if(paquete1_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("1");
				}
				if(paquete2_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("2");
				}
				if(paquete3_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("3");
				}
				if(paquete4_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("4");
				}
				if(paquete5_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("5");
				}
				if(paquete5_check.equalsIgnoreCase("true")){
					paquete = paqueteDAO.buscarPorId("5");
				}
				
				if(paquete.getId()!=null)
					cotizacionDetalle.setPaqueteId(BigInteger.valueOf(Long.parseLong(paquete.getId())));
				else
					cotizacionDetalle.setPaqueteId(null);
	
				//evaldez se controla que exista 
				
				if(cotizacionDetalle.getId() != null && cotizacionDetalle.getCotizacion() != null && cotizacionDetalle.getCotizacion().getId() != null){
						//cotizacionDetalle.setId(cotizacionDetalleExiste.getId());
					if(cotizacion.getNumeroFactura()==null)
						cotizacionDetalle = cotizacionDetalleTransaction.editar(cotizacionDetalle);
				}
				else
					if(cotizacion.getNumeroFactura()==null)
						cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
				
								
				// Detectar Planes seleccionados
				
				//cotizacionCobertura = cotizacionCoberturaDAO.crear(cotizacionCobertura);	
				result.put("tasaVehiculo", tasa*100);
				result.put("vehiculoId",vehiculo.getId());				
			}
			
			
			//Metodo para consultar el Precio Referencial- PJacome
			if(tipoConsulta.equalsIgnoreCase("consultaPrecioReferencial"))
			{			
				
				String modelo = request.getParameter("modelo") == null ? "" : request.getParameter("modelo");		
				String aniofab = request.getParameter("aniofab") == null ? "" : request.getParameter("aniofab");
				String marca = request.getParameter("marca") == null ? "" : request.getParameter("marca"); 
				PrecioReferencial precioReferencial = new PrecioReferencial();
				PrecioReferencialDAO precioReferencialDAO = new PrecioReferencialDAO ();
				JSONObject precioReferencialJSONObject = new JSONObject();
				JSONArray precioReferencialJSONArray = new JSONArray();
				List<PrecioReferencial> results = precioReferencialDAO.buscarPrecio(modelo, aniofab, marca);
				int i=0;
				for(i=0; i<results.size(); i++){
					precioReferencial = (PrecioReferencial) results.get(i);
					precioReferencialJSONObject.put("marca", precioReferencial.getModelo().getMarca().getNombre());
					precioReferencialJSONObject.put("modelo", precioReferencial.getModelo().getNombre());
					precioReferencialJSONObject.put("aniofab", precioReferencial.getAno());
					precioReferencialJSONObject.put("precio", precioReferencial.getPrecio());
					precioReferencialJSONArray.add(precioReferencialJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoPrecioReferencial", precioReferencialJSONArray);
			}
			//Metodo para consultar polizas x fecha - PJacome
			if(tipoConsulta.equalsIgnoreCase("encontrarxFecha")){ 
				String fInicio= request.getParameter("fInicio") == null ? "" : request.getParameter("fInicio");
				String fFinal= request.getParameter("fFinal") == null ? "" : request.getParameter("fFinal");
				
				ClienteDAO clienteDAO = new ClienteDAO();
				AgenteDAO agenteDAO = new AgenteDAO();
				ProductoDAO productoDAO = new ProductoDAO();
				EstadoDAO estadoDAO = new EstadoDAO();
				
				List<Cotizacion> results = cotizacionDAO.buscarCotizacionxFecha2(fInicio, fFinal);
				int i;
				for(i=0; i<results.size(); i++){
					cotizacion = results.get(i);
					
					String idcliente = cotizacion.getClienteId().toString();
					Entidad clientea = clienteDAO.buscarPorId(idcliente).getEntidad();
					String entidadc = clientea.getNombres() +" " + clientea.getApellidos();
					
					String idagente = cotizacion.getAgenteId().toString();
					Entidad agentea = agenteDAO.buscarPorId(idagente).getEntidad();
					String entidada = agentea.getNombreCompleto();
					
					String idproducto = cotizacion.getProducto().getId().toString();
					Producto productoa = productoDAO.buscarPorId(idproducto);
					String productof = productoa.getNombre();
					
					String idestado = cotizacion.getEstado().getId();
					Estado estadoa = estadoDAO.buscarPorId(idestado);
					String estadof = estadoa.getNombre();
					
					cotizacionJSONObject.put("codigo", cotizacion.getId());
					cotizacionJSONObject.put("punto_venta", cotizacion.getPuntoVenta().getNombre());
					cotizacionJSONObject.put("vigencia_poliza", cotizacion.getVigenciaPoliza().getNombre().toString());
					cotizacionJSONObject.put("cliente", entidadc);
					cotizacionJSONObject.put("agente",entidada);
					cotizacionJSONObject.put("producto",productof);
					cotizacionJSONObject.put("estado",estadof);
					cotizacionJSONObject.put("tipo_objeto", cotizacion.getTipoObjeto().getNombre());
					cotizacionJSONObject.put("fecha_elaboracion", cotizacion.getFechaElaboracion().toString());
					cotizacionJSONObject.put("por_comision", cotizacion.getPorcentajeComision());
					cotizacionJSONObject.put("suma_total",cotizacion.getSumaAseguradaTotal());
					cotizacionJSONObject.put("prima_neta_total", cotizacion.getPrimaNetaTotal());
					cotizacionJSONArray.add(cotizacionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoCotizacion", cotizacionJSONArray);
			}
			
			if(tipoConsulta.equalsIgnoreCase("consultarPlaca"))
			{		
				String placa = request.getParameter("placa") == null ? "" : request.getParameter("placa");
			
 			    if (placa.length() >5){
				  final WebClient webClient = new WebClient();

  				  // Get the first page
				  final HtmlPage page1 = webClient.getPage("http://www.policiaecuador.gob.ec/aplicaciones/consultasws/informacionvehicular.php");

				  // Get the form that we are dealing with and within that form, 
				  // find the submit button and the field that we want to change.
				  final HtmlForm form = page1.getFormByName("form1");
				  final HtmlSubmitInput button = form.getInputByName("buscar");
				  final HtmlTextInput textField = form.getInputByName("placa");

				  // Change the value of the text field
				  textField.setValueAttribute(placa);

				  // Now submit the form by clicking the button and get back the second page.
				  final HtmlPage page2 = button.click();
				  String asText = page2.asText().toLowerCase();
				  webClient.closeAllWindows();	
				  //System.out.print(asText.length());
				  if(asText.toLowerCase().contains("servicio no disponible"))
					  throw new Exception("Servicio de la ANT no disponible");
				  String infAuto = asText.substring(170, asText.indexOf("cooperativa")).replaceAll("\n", ":");
				  String [] arrInfAuto = infAuto.split(":");

				  Color colorEnsurance = new Color();
				  ColorDAO colorDAO =  new ColorDAO();
				  
				  Marca marcaEnsurance = new Marca();
				  MarcaDAO marcaDAO = new MarcaDAO();
				  
				  Modelo modeloEnsurance = new Modelo();
				  ModeloDAO modeloDAO = new ModeloDAO();
				  String modelo="";
				  String marca="";
				  //String placaValor="";
				  String chasis="";
				  String motor="";
				  String tipo="";
				  String clase="";
				  String anio="";
				  String color="";
				  int cont=0;
				  
				  for(String s:arrInfAuto){
					  if(s.equals("chasis")&&arrInfAuto[cont+1]!=null)
						  chasis=arrInfAuto[cont+1];
					  if(s.equals("modelo")&&arrInfAuto[cont+1]!=null)
						  modelo=arrInfAuto[cont+1];
					  if(s.equals("marca")&&arrInfAuto[cont+1]!=null)
						  marca=arrInfAuto[cont+1];
					  /*if(s.equals("placa")&&arrInfAuto[cont+1]!=null)
						  placaValor=arrInfAuto[cont+1];*/
					  if(s.equals("motor")&&arrInfAuto[cont+1]!=null)
						  motor=arrInfAuto[cont+1];
					  if(s.equals("tipo")&&arrInfAuto[cont+1]!=null)
						  tipo=arrInfAuto[cont+1];
					  if(s.equals("clase")&&arrInfAuto[cont+1]!=null)
						  clase=arrInfAuto[cont+1];
					  if(s.equals("año")&&arrInfAuto[cont+1]!=null)
						  anio=arrInfAuto[cont+1];
					  if(s.equals("color")&&arrInfAuto[cont+1]!=null)
						  color=arrInfAuto[cont+1];
					  cont++;
				  }
				  				  
				 String valorColor="";
				  marcaEnsurance = marcaDAO.buscarPorNombre(marca);
				  if(color.length()>0){
					  colorEnsurance = colorDAO.buscarPorNombre(color);
					  valorColor = colorEnsurance.getId();
				  }
				  
				  modeloEnsurance = modeloDAO.buscarPorMarcaYNombre(marcaEnsurance, arrInfAuto[9]);
				  result.put("placa", placa);
				  result.put("chasis", chasis);
				  result.put("motor", motor);
				  result.put("marcaEnsurance", marcaEnsurance.getId());
				  result.put("marca", marcaEnsurance.getNombre());
				  result.put("tipo", tipo);
				  result.put("clase", clase); 	
				  result.put("color", valorColor);
				  result.put("anioFabricacion", anio);	
					if (modeloEnsurance.getId() != null) {
						result.put("modeloEnsurance", modeloEnsurance.getId());
						result.put("modelo", modeloEnsurance.getNombre());
					}
					else{
						result.put("modeloEnsurance", -1);
						result.put("modelo", modelo);
					}
 			    }

 			    String cotizacionId="";
				  
				  CotizacionDetalleDAO cdDAO = new CotizacionDetalleDAO(); 
				  ObjetoVehiculoDAO ovDAO= new ObjetoVehiculoDAO();
				  ObjetoVehiculo ov=ovDAO.buscarPorPlaca(placa.toUpperCase());
				  List<CotizacionDetalle>  cd=new ArrayList<CotizacionDetalle>();
				  
				  if(ov.getId()!=null)
					   cd=cdDAO.buscarCotizacionesDetallePorObjetoId(ov.getId());
				  
				  for(int i=0;i<cd.size();i++){
				  if(cd.get(i).getId()!=null)
					  if(cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Borrador"))
						  cotizacionId=cd.get(i).getCotizacion().getId();
				  }
				  
				  result.put("cotizacionId", cotizacionId);
			}		
			
			if(tipoConsulta.equalsIgnoreCase("consultarVigencia")){
				String parametro = request.getParameter("parametro") == null ? "" : request.getParameter("parametro");
				String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
				
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				  String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo(valor, parametro);
				  JSONArray jsonArray = new JSONArray();
				  jsonArray.add(Utilitarios.cargarParametroWS(resultado));
				  result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
				  result.put("anioEnsurance", jsonArray.getJSONObject(0).get("anio"));
				  result.put("marcaEnsurance", jsonArray.getJSONObject(0).get("marca"));
				  result.put("modeloEnsurance", jsonArray.getJSONObject(0).get("modelo"));
				  result.put("colorEnsurance", jsonArray.getJSONObject(0).get("color"));
				  result.put("dispositivoEnsurance", jsonArray.getJSONObject(0).get("dispositivo"));
				  result.put("sucursalEnsurance", jsonArray.getJSONObject(0).get("sucursal"));
				  result.put("valorEnsurance", jsonArray.getJSONObject(0).get("valor"));
				  result.put("usoVehiculoEnsurance", jsonArray.getJSONObject(0).get("usoVehiculo"));
				  result.put("tipoVehiculoEnsurance", jsonArray.getJSONObject(0).get("tipoVehiculo"));
				  result.put("agenteIdEnsurance", jsonArray.getJSONObject(0).get("agenteId"));
				  result.put("entidadAgenteIdEnsurance", jsonArray.getJSONObject(0).get("entidadAgenteId"));
					if(jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("POL")||jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("INC"))
						result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
					else
						result.put("vigenciaEnsurance", "");
				  result.put("tasaEnsurance", jsonArray.getJSONObject(0).get("tasa"));
				  result.put("placasEnsurance", jsonArray.getJSONObject(0).get("placas"));
				  result.put("motorEnsurance", jsonArray.getJSONObject(0).get("motor"));
				  result.put("chasisEnsurance", jsonArray.getJSONObject(0).get("chasis"));
				
			}
			
			
			if(tipoConsulta.equalsIgnoreCase("eliminar"))
			{		
				
			  if(usuario==null){
			    result.put("success", Boolean.FALSE);
			    result.put("error", "Usuario sin sesión!");
			  }
			  
			  String cotizacion_id = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
              cotizacion = cotizacionDAO.buscarPorId(cotizacion_id);
              
              MovimientoCotizacion movco=new MovimientoCotizacion();
			  movco.setCotizacionId(cotizacion.getId());
			  movco.setUsuario(usuario);
			  movco.setFecha(new Date());
			  movco.setMovimiento("ELIMINAR");
			  MovimientoCotizacionTransaction mct=new MovimientoCotizacionTransaction();
			  
			  mct.crear(movco);
			  
			  if(cotizacion.getId()!=null)
				  cotizacionTransaction.eliminar(cotizacion);
			}
			
			if(tipoConsulta.equalsIgnoreCase("elminarObjetoDetalle"))
			{
				String vehiculoId = request.getParameter("vehiculoId") == null ? "" : request.getParameter("vehiculoId");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				result.put("msgEliminarObjeto",  eliminarVehiculoCotizacionDetalle(cotizacion, vehiculoId));
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
				JSONArray jSONArrayTemp = new JSONArray();
				PuntoVenta puntoVenta=new PuntoVenta();
				PuntoVentaDAO puntoVentaDAO =new PuntoVentaDAO();
				
				if(usuario==null|| usuario.getId()==null)
					throw new Exception("Por favor vuelva a iniciar sesión");
				
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol										
						listaCotizacionesJSONArray.add(i,consultarPorTipoObjeto(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId, estadoCotizacion));
						result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));									
						result.put("numeroListas", i);
				}else{
					if(puntoVentaId.length()>0){					
						puntoVenta = puntoVentaDAO.buscarPorId(puntoVentaId);						
						if(puntoVenta.getEsMatriz() && puntoVentaId.length()!=0){																					
							// Obtenemos los puntos de venta de la matriz
							List<PuntoVenta> puntosVentaPoseeMatriz = puntoVentaDAO.buscarPuntosVentaPorMatriz(puntoVenta.getId());
							// Agregamos el punto de venta de la matriz al listado
							puntosVentaPoseeMatriz.add(puntoVenta);
							
							for(PuntoVenta puntoVentaDeMatriz:puntosVentaPoseeMatriz){
								JSONArray arrayJson = consultarPorTipoObjetoPuntoVenta(fInicio, fFinal, tipoObjetoEncontrar, puntoVentaDeMatriz,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion);
								jSONArrayTemp.addAll(arrayJson);																																				
							}
							listaCotizacionesJSONArray.add(i,jSONArrayTemp);
							
							if(listaCotizacionesJSONArray.size()>0)
								result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));
							else
								result.put("listadoCotizacion", "");
							
							result.put("numeroListas", i);
						}else{												
								listaCotizacionesJSONArray = new JSONArray();															
								listaCotizacionesJSONArray.add(i,consultarPorTipoObjetoPuntoVenta(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion));
									result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));														
							result.put("numeroListas", i);
						}						
					}else{
						
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();						
						if(puntoVenta.getEsMatriz() && puntoVentaId.length()!=0){																					
							// Obtenemos los puntos de venta de la matriz
							List<PuntoVenta> puntosVentaPoseeMatriz = puntoVentaDAO.buscarPuntosVentaPorMatriz(puntoVenta.getId());
							// Agregamos el punto de venta de la matriz al listado
							puntosVentaPoseeMatriz.add(puntoVenta);
								
							for(PuntoVenta puntoVentaDeMatriz:puntosVentaPoseeMatriz){
								JSONArray arrayJson = consultarPorTipoObjetoPuntoVenta(fInicio, fFinal, tipoObjetoEncontrar, puntoVentaDeMatriz,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion);
								jSONArrayTemp.addAll(arrayJson);																																				
							}
							listaCotizacionesJSONArray.add(i,jSONArrayTemp);
							
							if(listaCotizacionesJSONArray.size()>0)
								result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));
							else
								result.put("listadoCotizacion", "");
							
							result.put("numeroListas", i);
						}else{							
								listaCotizacionesJSONArray = new JSONArray();															
									listaCotizacionesJSONArray.add(i,consultarPorTipoObjetoPuntoVenta(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion));
									result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));																			
							result.put("numeroListas", i);
						}												
					}
				}
			}
			
			
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
				//int i=0;
				//JSONArray listaCotizacionesJSONArray = new JSONArray();
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
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoParaGanadero(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId,estadoConsulta));
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
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				if(cotizacion.getId() == null){
					throw new Exception("Cotizacion no existe");
				}else{
									
				if(request.getSession().getAttribute("usuario")!=null)
					 usuario = (Usuario)request.getSession().getAttribute("usuario");
				Rol rol=new Rol();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol)))// Validacion para administradores segun el rol
					result.put("datosCotizacion",  encontrarPorId(cotizacionId));
				else {			
					PuntoVenta puntoVenta = new PuntoVenta();	
					PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
					
					puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();						
					if(puntoVenta.getEsMatriz()){																					
						// Obtenemos los puntos de venta de la matriz
						List<PuntoVenta> puntosVentaPoseeMatriz = puntoVentaDAO.buscarPuntosVentaPorMatriz(puntoVenta.getId());
						// Agregamos el punto de venta de la matriz al listado
						puntosVentaPoseeMatriz.add(puntoVenta);
						boolean existePuntoVenta = false;	
						for(PuntoVenta puntoVentaDeMatriz:puntosVentaPoseeMatriz){
							if (cotizacion.getPuntoVenta().getId().equals(puntoVentaDeMatriz.getId()))
								existePuntoVenta = true;					
						}
						if(existePuntoVenta)
							result.put("datosCotizacion",encontrarPorId(cotizacionId));
						else
							throw new Exception("El usuario no puede ver esta cotizacion");													
					}else{
						if (usuario.getUsuarioXPuntoVentas().size() > 0) {
							puntoVenta = usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
							if (cotizacion.getPuntoVenta().getId().equals(puntoVenta.getId()))
								result.put("datosCotizacion",encontrarPorId(cotizacionId));
							else
								throw new Exception("El usuario no puede ver esta cotizacion");
						} else
							throw new Exception("El usuario no tiene punto de venta");						
					}				
				}	
			  }
			}
			
			if(tipoConsulta.equalsIgnoreCase("enviarCertificado"))
			{
				String correos = request.getParameter("correos") == null ? "" : request.getParameter("correos");		
				String id = request.getParameter("id") == null ? "" : request.getParameter("id");		
				String casoEspecial = request.getParameter("casoEspecial") == null ? "" : request.getParameter("casoEspecial");		
				
				enviarCertificado(id, correos, casoEspecial,request);
								
			}
			
			if(tipoConsulta.equalsIgnoreCase("cargarTablaVehiculos"))
			{
				JSONArray listadoVehiculosJSONArray = new JSONArray();
				
				String id = request.getParameter("id") == null ? "" : request.getParameter("id");
				if(id!="")
					cotizacion = cotizacionDAO.buscarPorId(id);
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO (); 
				List <CotizacionDetalle> cotizacionesDetalle =cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				int i=0;
				for(i=0;i<cotizacionesDetalle.size();i++){
					JSONObject vehiculoJSONObject = new JSONObject();
					ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO();
					ObjetoVehiculo ov=ovDAO.buscarPorId(cotizacionesDetalle.get(i).getObjetoId());
					vehiculoJSONObject.put("placa", ov.getPlaca()==null?"":ov.getPlaca());
					vehiculoJSONObject.put("id", ov.getId());
					vehiculoJSONObject.put("chasis", ov.getChasis()==null?"":ov.getChasis());
					vehiculoJSONObject.put("motor", ov.getMotor()==null?"":ov.getMotor());
					vehiculoJSONObject.put("modelo", ov.getModelo().getNombre());
					vehiculoJSONObject.put("marca", ov.getModelo().getMarca().getNombre());
					vehiculoJSONObject.put("valor", cotizacionesDetalle.get(i).getSumaAseguradaItem());
					listadoVehiculosJSONArray.add(vehiculoJSONObject);
				}
				result.put("listadoVehiculos", listadoVehiculosJSONArray);
								
			}
			
			if(tipoConsulta.equalsIgnoreCase("cargarInspectoresInternos"))
			{
				result.put("listadoInspectoresInternos", cargarInspectoresInternos());
								
			}
			
			if (tipoConsulta.equalsIgnoreCase("actualizarFechaInicioVigencia")) {
				String vigenciaDesde = request.getParameter("vigenciaDesde") == null ? "" : request.getParameter("vigenciaDesde");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				Date vigencia = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				vigencia = df.parse(vigenciaDesde); // new Date(Integer.parseInt(vigenciaDesde.substring(0, 4))-1900,Integer.parseInt(vigenciaDesde.substring(5, 7))-1,Integer.parseInt(vigenciaDesde.substring(8)));

				Date fechaActual = new Date();

				int diferencia = Days.daysBetween(new DateTime(fechaActual), new DateTime(vigencia)).getDays();
				VariableSistemaDAO variableDAO = new VariableSistemaDAO(); 
				VariableSistema variableMaximoDiasAnteriores = variableDAO.buscarPorNombre("MAXIMO_DIAS_ANTERIORES_INICIO_VIGENCIA");
				int valorVariableMaximoDiasAnteriores = Integer.parseInt(variableMaximoDiasAnteriores.getValor());
				if(diferencia<0)
					diferencia*=-1;
				
				if (vigencia.before(fechaActual) && diferencia > valorVariableMaximoDiasAnteriores) throw new Exception("El inicio de vigencia debe ser máximo "+valorVariableMaximoDiasAnteriores+" días anteriores a la fecha actual");

				VariableSistema variableMaximoDiasPosteriores = variableDAO.buscarPorNombre("MAXIMO_DIAS_POSTERIORES_INICIO_VIGENCIA");
				int valorVariableMaximoDiasPosteriores = Integer.parseInt(variableMaximoDiasPosteriores.getValor());
				
				if (vigencia.after(fechaActual) && diferencia > valorVariableMaximoDiasPosteriores) throw new Exception("El inicio de vigencia debe ser máximo "+valorVariableMaximoDiasPosteriores+" días despues a la fecha actual");


				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				cotizacion.setVigenciaDesde(vigencia);
				cotizacion = cotizacionTransaction.editar(cotizacion);

				//validacion de las fechas de pago
				Pago pago = cotizacion.getPago();
				if (pago != null && pago.getId() != null) {
					if (pago.getFormaPago().getId().equals("2") || pago.getFormaPago().getId().equals("3") || pago.getFormaPago().getId().equals("4")) {
						CuotaDAO cuotaDAO = new CuotaDAO();
						List < Cuota > cuotas = cuotaDAO.buscarPorPago(pago);
						if (cuotas.size() > 0) {
							Date aux = new Date();
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(aux);
							int cont = 0;
							for (Cuota objeto: cuotas) {
								if (cont == 0) {
									//SimpleDateFormat curFormater2 = new SimpleDateFormat("yyyy-MM-dd"); 
									Date fechaPagoDate=new Date();
									Date inicioVigencia=cotizacion.getVigenciaDesde();
									int diferenciaActualPago=Days.daysBetween(new DateTime(fechaActual), new DateTime(fechaPagoDate)).getDays();
									int diferenciaVigenciaPago=Days.daysBetween(new DateTime(inicioVigencia), new DateTime(fechaPagoDate)).getDays();
									
									VariableSistemaDAO vsDAO=new VariableSistemaDAO();
									VariableSistema vs= vsDAO.buscarPorNombre("MAXIMO_DIAS_PAGO");
									
									int numMaximoDiasPago = new Integer(vs.getValor());	
									
									if(inicioVigencia.after(fechaActual)){
										if(fechaPagoDate.after(inicioVigencia)&&diferenciaVigenciaPago>numMaximoDiasPago)
											throw new Exception ("La fecha de pago debe ser hasta "+numMaximoDiasPago+" dias despues del inicio de vigencia");
										if(fechaPagoDate.before(fechaActual))
											throw new Exception ("La fecha de pago no puede ser anterior a la fecha actual");
									}
									else{
										if(fechaPagoDate.before(fechaActual))
											throw new Exception ("La fecha de pago no puede ser anterior a la fecha actual");
										if(fechaPagoDate.after(fechaActual)&&diferenciaActualPago>numMaximoDiasPago)
											throw new Exception ("La fecha de pago debe ser hasta "+numMaximoDiasPago+" dias despues de la fecha actual");
									}

								}
								cont++;
							}
						}
					}
				}
			}
			
			// Actualizar Cotizacion - Programa de Seguros
			if(tipoConsulta.equalsIgnoreCase("emisionProgramaSeguros"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String tienePrograma = request.getParameter("tienePrograma") == null ? "" : request.getParameter("tienePrograma");
				
				cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				// Validamos si tienePrograma es true o false
				if(tienePrograma.equalsIgnoreCase("true")){
					cotizacion.setEmitirProgramaSeguros(true);
					cotizacion=cotizacionTransaction.editar(cotizacion);
					result.put("resultado_programa", "1");
				}else{
					cotizacion.setEmitirProgramaSeguros(false);
					cotizacion=cotizacionTransaction.editar(cotizacion);
					result.put("resultado_programa", "0");
				}

			}
		
			
			// Tasa minima actualizar vehiculos
			if(tipoConsulta.equalsIgnoreCase("actualizarTasa"))
			{			
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();					
				Double tasaValor = request.getParameter("tasaValor") == null ? 0 : Double.parseDouble(request.getParameter("tasaValor").trim())/100;
				
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				double porcentajeConDescuento=1-(cotizacion.getValorDescuento()/100);
				// Obtenemos todos los vehiculos que se encuentran dentro de la cotizacion
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();				
				List<CotizacionDetalle> cotizacionDetallesLista = new ArrayList<CotizacionDetalle>();
				cotizacionDetallesLista = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
								
				for(CotizacionDetalle cotizacionDetalleItem:cotizacionDetallesLista){			
					Double valorTasaObjeto = cotizacionDetalleItem.getTasa();
					if(valorTasaObjeto < tasaValor){						
						CotizacionCoberturaDAO ccDAO = new CotizacionCoberturaDAO();
						List<String> ids = new ArrayList<String>();// COBERTURAS PRINCIPALES Y DE EXTRAS
						ids.add("40010")	;															
						ids.add("30075")	;															
						ids.add("30081")	;															
						ids.add("40016")	;															
						ids.add("40037")	;															
						ids.add("4540881508289")	;															
						CoberturaDAO cDAO=new CoberturaDAO();
						List <Cobertura> coberturas=cDAO.buscarPorIds(ids);
						List<CotizacionCobertura> cotizacionesCobertura = ccDAO.buscarPorCotizacionDetalleIdCoberturas(cotizacionDetalleItem,coberturas);
						for (CotizacionCobertura objeto : cotizacionesCobertura) {
							objeto.setValorPrimaOrigen(objeto.getValorPrimaOrigen() / cotizacionDetalleItem.getTasa() * tasaValor);
							objeto.setValorPrima(objeto.getValorPrima() / cotizacionDetalleItem.getTasa() * tasaValor);	
							objeto = cotizacionCoberturaTransaction.editar(objeto);
						}

						List<CotizacionCobertura> listaCoberturas = ccDAO.buscarPorCotizacionDetalle(cotizacionDetalleItem);
						double primaAcumuladaTotal = 0.0;
						for (CotizacionCobertura objeto : listaCoberturas) {
							primaAcumuladaTotal += objeto.getValorPrimaOrigen();
						}

						cotizacionDetalleItem.setPrimaNetaItemOrigen(primaAcumuladaTotal);
						cotizacionDetalleItem.setPrimaNetaItem(primaAcumuladaTotal * porcentajeConDescuento);
						cotizacionDetalleItem.setTasa(tasaValor);
						cotizacionDetalleItem = cotizacionDetalleTransaction.editar(cotizacionDetalleItem);										
					}
				}
				
				// Actualizamos la cotizacion con la nueva tasa
				cotizacion.setTasaMinima(tasaValor);
				cotizacion = cotizacionTransaction.editar(cotizacion);
			}
			
			if(tipoConsulta.equalsIgnoreCase("reestrablecerTasa"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();	
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				cotizacion.setTasaMinima(0);
				cotizacion=cotizacionTransaction.editar(cotizacion);				
			}
			//  Obtenemos el IVA segun el Punto de Venta
			if(tipoConsulta.equalsIgnoreCase("ivaSegunPuntoVenta")){				
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();									
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				double valorIVA = 0.0;
				double compensacion =2.0;
				double valorCompensacion =0.0;
				double valorIVATotal = 0.0;
				
				if(cotizacion.getId() != null){					
					double resultadoIVA [] = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);
					valorIVA = resultadoIVA[0];					
					// Valor de la compensacion 2% del IVA
					if(resultadoIVA [1] == 2){
						valorCompensacion = (cotizacion.getImpIva()*2)/12;
						valorIVATotal = cotizacion.getImpIva();
					}else{
						valorIVATotal = cotizacion.getImpIva();
					}									
				}								
				      
				valorCompensacion = Math.round(valorCompensacion*100.0)/100.0;
				valorIVATotal = Math.round(valorIVATotal*100.0)/100.0;
				
				result.put("iva", valorIVA);
				result.put("compensacion", compensacion);
				result.put("valor_compensacion", valorCompensacion);
				result.put("valor_iva", valorIVATotal);
			}	
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
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
			cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());
			
			// Verificamos la nueva version del Tarificador
		    VhTarificadorDAO vhTarificadorDAO = new VhTarificadorDAO(); 
			java.sql.Date fechaBusqueda;
			fechaBusqueda = new java.sql.Date(cotizacion.get(i).getFechaElaboracion().getTime());	   							
			VhTarificador tarificador = vhTarificadorDAO.buscarPorFecha(fechaBusqueda);
						
			cotizacionJSONObject.put("version_tarificador", tarificador.getNumero());
									
			ArchivoCotizacionMasivosDAO acmDAO=new ArchivoCotizacionMasivosDAO();
			ArchivoCotizacionMasivo acm= acmDAO.buscarPorCotizacionId(cotizacion.get(i).getId());
			
            if(acm.getId()!=null)
              cotizacionJSONObject.put("es_masivo", true);
            else
              cotizacionJSONObject.put("es_masivo", false);
            
			}
			
			cotizacionJSONArray.add(cotizacionJSONObject);
			
		}
		
		//retorno.put("numRegistros",i);
		retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}
	
	//Ganadero
	public JSONArray consultarPorTipoObjetoPorEstado(String fecha1, String fecha2, TipoObjeto tipoObjeto, String Estado){
		//JSONObject retorno= new JSONObject();
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
	
	//Filtro para las ventana de canales en ganadero
	public JSONArray consultarPorTipoObjetoParaGanadero(String fecha1, String fecha2, TipoObjeto tipoObjeto, String cotizacionId,String puntoVenta, String agenteId,String identificacion,String usuarioId, String FiltroEstado){
		JSONObject retorno= new JSONObject();
		CotizacionDAO cDAO= new CotizacionDAO();	
		
		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjetoParaCanal(fecha1, fecha2, tipoObjeto,cotizacionId,puntoVenta,agenteId,identificacion,usuarioId, FiltroEstado);
		 
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
	
	public JSONArray consultarPorTipoObjetoPuntoVenta(String fecha1, String fecha2, TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String numeroCotizacion,String agenteId,String identificacion,String usuarioId,String estadoCotizacion){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjetoNoEmitidoxFechaPuntoVenta(fecha1, fecha2, tipoObjeto, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,estadoCotizacion);
		 
		JSONObject cotizacionJSONObject = new JSONObject();
		JSONArray cotizacionJSONArray = new JSONArray();
		int i;
		for( i=0;i<cotizacion.size();i++){
			ClienteDAO cliDAO= new ClienteDAO();
			Cliente cli = cliDAO.buscarPorId(cotizacion.get(i).getClienteId().toString());
			
			AgenteDAO ageDAO= new AgenteDAO();
			Agente age = ageDAO.buscarPorId(cotizacion.get(i).getAgenteId().toString());

			if(cotizacion.get(i).getId()!=null&&age.getId()!=null&&cli.getId()!=null){
			
			GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();			
			GrupoPorProducto grupoPorProducto    = grupoPorProductoDAO.buscarPorId(cotizacion.get(i).getGrupoPorProductoId().toString());
			
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
		return cotizacionJSONArray;
	}
	
	//PJacome
	public JSONArray consultarPorTipoObjetoEmitidos(String fecha1, String fecha2, TipoObjeto tipoObjeto){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarCotizacionxFecha(fecha1, fecha2,tipoObjeto);
		 
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
			
			// Cargar la fecha de Compra
			if(cotizacion.getGrupoProductoId().toString().equalsIgnoreCase("11")){
				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");				
				etapa1.put("fechaCompra", formatoFecha.format(cotizacion.getFechaElaboracion()));
				String informacionAdicional=cotizacion.getInformacionAdicional();
				if(informacionAdicional!=null){
					String [] informacion = informacionAdicional.split("\\|");
					String [] diasMesPago = informacion[0].toString().split("\\=");
					String [] diasExtras = informacion[1].toString().split("\\=");					
					etapa1.put("diaMesPago",diasMesPago[1].toString());
					etapa1.put("diasExtras", diasExtras[1].toString());
				}													
			}												
			// Cargar agente acuerdo
			if(cotizacion.getAgenteAcuerdo()!=null )
				etapa1.put("agenteAcuerdoId", cotizacion.getAgenteAcuerdo());
						
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
		
		
		//fin etapa1
		
		//etapa2
		
		JSONObject etapa2= new JSONObject();
		if(cotizacion.getEtapaWizard()>=2){
			JSONObject objeto= new JSONObject();
			List<CotizacionDetalle> detalles= cotizacion.getCotizacionDetalles();		
			JSONArray objetos= new JSONArray(); 
			for(int i=0;i<detalles.size();i++){
				
				
				if(cotizacion.getTipoObjeto().getId().equals("1") || cotizacion.getTipoObjeto().getId().equals("2") || cotizacion.getTipoObjeto().getId().equals("4") || 
						cotizacion.getTipoObjeto().getId().equals("5")|| cotizacion.getTipoObjeto().getId().equals("6")){
				
				ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
				ObjetoVehiculo ov = ovDAO.buscarPorId(detalles.get(i).getObjetoId());
				sucursalDAO = new SucursalDAO();
				Sucursal sucursal = sucursalDAO.buscarPorId(ov.getSucursalId());
				objeto.put("numero", (i+1));
				objeto.put("id", ov.getId());
				objeto.put("placa", ov.getPlaca() );
				objeto.put("motor", ov.getMotor() );
				objeto.put("chasis", ov.getChasis());
				objeto.put("anio", ov.getAnioFabricacion());
				objeto.put("marca", ov.getModelo().getMarca().getId());
				objeto.put("modelo", ov.getModelo().getId());
				objeto.put("color", ov.getColor().getId());
				objeto.put("sumaAsegurada", ov.getSumaAsegurada());
				objeto.put("dispositivoRastreo", ov.getDispositivoRastreo()?1:0);
				objeto.put("ceroKilometros", ov.getCeroKilometros()?1:0);
				objeto.put("antiguedadVehiculo", ov.getAntiguedadVh());
				objeto.put("conductorEdad", ov.getConductorEdad());
				objeto.put("conductorGenero", ov.getConductorGenero());
				objeto.put("conductorEstadoCivil", ov.getConductorEstadoCivil());
				objeto.put("conductorNumeroHijos", ov.getNumeroHijos());
				objeto.put("sucursal",sucursal.getId() );
				objeto.put("zona", ov.getZona());
				objeto.put("guardaGaraje", ov.getGuardaGarage()?1:0);
				objeto.put("kilometrosRecorridos", ov.getKilometrosRecorridos());
				objeto.put("combustible", ov.getCombustible());
				objeto.put("tipoVehiculo", ov.getTipoVehiculo().getId());
				objeto.put("tipoUso", ov.getTipoUso().getId());
				objeto.put("tasa", detalles.get(i).getTasa()*100);
				objeto.put("paquete", detalles.get(i).getPaqueteId());
				objeto.put("necesitaInspeccion", detalles.get(i).getNecesitaInspeccion());
				objeto.put("tipoAdquisicion", ov.getTipoAdquisicion());
				objeto.put("tonelaje", ov.getTonelajeVehiculo());
				objeto.put("hijosConduzcan", ov.getHijosConduzcan()?"SI":"NO");
				objeto.put("kilometrajeAnual", ov.getKilometrajeAnual());
				objeto.put("zonaTransito", ov.getVhTari1Zona()==null?"":ov.getVhTari1Zona().getId());
				// Validacion cuando hay conductor o no
				if(ov.getConductor()!= null){
					objeto.put("identificacionConductor", ov.getConductor().getIdentificacion());
					objeto.put("nombresConductor", ov.getConductor().getNombres());
					objeto.put("apellidosConductor", ov.getConductor().getApellidos());
					objeto.put("codigoConductorEnsurance", ov.getConductor().getEntEnsurance());
				}else{
					objeto.put("identificacionConductor", "");
					objeto.put("nombresConductor","");
					objeto.put("apellidosConductor", "");
					objeto.put("codigoConductorEnsurance", "");
				}

				
				JSONArray coberturasJSONArray= new JSONArray(); 
				
				JSONArray coberturasPrincipales=new JSONArray ();
				
				
				List<CotizacionCobertura> coberturas=detalles.get(i).getCotizacionCoberturas();
				Double valorExcesoRC=0.0;
				for(int j=0;j<coberturas.size();j++){
					JSONObject coberturaJSON = new JSONObject();
					coberturaJSON.put("id", coberturas.get(j).getId() );
					coberturaJSON.put("montoFijo", coberturas.get(j).getMontoFijo() );
					coberturaJSON.put("sumaAsegurada", coberturas.get(j).getPorcentajeSumaAsegurada() );
					coberturaJSON.put("valorSinisestro", coberturas.get(j).getPorcentajeValorSiniestro() );
					coberturaJSON.put("coberturaId", coberturas.get(j).getCobertura().getId());
					coberturaJSON.put("nemotecnico", coberturas.get(j).getCobertura().getNemotecnico());
					if(coberturas.get(j).getCobertura().getId().equals("6348540415022")||coberturas.get(j).getCobertura().getId().equals("6349173767914"))
						valorExcesoRC=coberturas.get(j).getValorMonto();
					
					if(coberturas.get(j).getCobertura().getTipoCobertura().getId().equals("1")){
						JSONObject coberturaPrincipal=new JSONObject();
						coberturaPrincipal.put("id",coberturas.get(j).getCobertura().getId());
						coberturaPrincipal.put("nombre",coberturas.get(j).getCobertura().getNombre());
						coberturaPrincipal.put("porSumaAsegurada",coberturas.get(j).getPorcentajeSumaAsegurada());
						coberturaPrincipal.put("montoFijo",coberturas.get(j).getMontoFijo());
						coberturaPrincipal.put("porValorSiniestro",coberturas.get(j).getPorcentajeValorSiniestro());
						coberturasPrincipales.add(coberturaPrincipal);
					}
					coberturasJSONArray.add(coberturaJSON);
				}
				objeto.put("valorExcesoRC", valorExcesoRC);
				objeto.put("coberturas", coberturasJSONArray);
				objeto.put("coberturasPrincipales", coberturasPrincipales);
				
				JSONArray extrasJSONArray= new JSONArray(); 
				for(int j=0; j<ov.getExtras().size(); j++){
					JSONObject extrasJSON = new JSONObject();
					extrasJSON.put("id", ov.getExtras().get(j).getId());
					extrasJSON.put("tipoExtraId", ov.getExtras().get(j).getTipoExtra().getId());
					extrasJSON.put("nombreTipoExtra", ov.getExtras().get(j).getTipoExtra().getNombre());
					extrasJSON.put("descripcion", ov.getExtras().get(j).getDescripcion());
					extrasJSON.put("valor", ov.getExtras().get(j).getValorAsegurado());
					extrasJSONArray.add(extrasJSON);
				}
				
				objeto.put("extras", extrasJSONArray);
				
				objetos.add(objeto);
				
				}
				else if(cotizacion.getTipoObjeto().getNombre().equals("Ganadero"))
				{
					ObjetoGanaderoDAO objetoGanaderoDAO=new ObjetoGanaderoDAO();
					ObjetoGanadero os=objetoGanaderoDAO.buscarPorId(detalles.get(i).getObjetoId());
					objeto.put("id", os.getId());
					objeto.put("provinciaId", os.getProvinciaid());
					objeto.put("cantonId", os.getCantonid());
					objeto.put("parroquiaId", os.getParroquiaid());
					objeto.put("ubicacion", os.getUbicacion());
					objeto.put("recinto", os.getRecinto());
					objeto.put("region", os.getRegion());
					objeto.put("tipoProduccion", os.getTipoProduccion());
					objeto.put("fincaAltitud", os.getFincaAltitud());
					objeto.put("fincaTopografia1", os.getFincaTopografia1());
					objeto.put("fincaTopografia2", os.getFincaTopografia2());
					objeto.put("fincaTopografia3", os.getFincaTopografia3());
					objeto.put("pastoTipo", os.getPastoTipoid());
					objeto.put("pastoHectareas", os.getPastoHectareas());
					objeto.put("pastoVolumen", os.getPastoVolumneanio());
					objeto.put("pastoObservaciones", os.getPastoObservaciones());
					objeto.put("animalesVacunos", os.getAnimalesVacunos());
					objeto.put("mortalidadVacas", os.getMortalidadVacas());
					objeto.put("mortalidadVacasCausa", os.getMortalidadVacasCausa());
					objeto.put("mortalidadVaconasV", os.getMortalidadVaconasv());
					objeto.put("mortalidadVaconasVCausa", os.getMortalidadVaconasvCausa());
					objeto.put("mortalidadVaconasF", os.getMortalidadVaconasf());
					objeto.put("mortalidadVaconasFCausa", os.getMortalidadVaconasfCausa());
					objeto.put("mortalidadVaconasM", os.getMortalidadVaconasm());
					objeto.put("mortalidadVaconasMCausa", os.getMortalidadVaconasmCausa());
					objeto.put("mortalidadToros", os.getMortalidadToros());
					objeto.put("mortalidadTorosCausa", os.getMortalidadTorosCausa());
					objeto.put("mortalidadToretes", os.getMortalidadToretes());
					objeto.put("mortalidadToretesCausa", os.getMortalidadToretesCausa());
					objeto.put("mortalidadTeneros", os.getMortalidadTerneros());
					objeto.put("mortalidadTenerosCausa", os.getMortalidadTernerosCausa());
					objeto.put("mortalidadTerneras", os.getMortalidadTerneras());
					objeto.put("mortalidadTernerasCausa", os.getMortalidadTernerasCausa());
					objeto.put("alimentacionPastoreo", os.getAlimentacionPastoreo());
					objeto.put("alimentacionCorte", os.getAlimentacionCorte());
					objeto.put("alimentacionSogueo", os.getAlimentacionSogueo());
					objeto.put("alimentacionOtros", os.getAlimentacionOtros());
					objeto.put("accesoAlAgua", os.getAccesoAlAgua());
					objeto.put("vacunacionesAftosa", os.getVacunacionesAftosa());
					objeto.put("vacunacionesBrucelosis", os.getVacunacionesBrucelosis());
					objeto.put("vacunacionesTriple", os.getVacunacionesTriple());
					objeto.put("vacunacionesLeptospirosis", os.getVacunacionesLeptospirosis());
					objeto.put("vacunacionesIbrBvd", os.getVacunacionesIbrbvd());
					objeto.put("vacunacionesOtras", os.getVacunacionesOtras());
					objeto.put("enfermedadMastitis", os.getEnfermedadMastisis());
					objeto.put("enfermedadPanadizo", os.getEnfermedadPanadizo());
					objeto.put("enfermedadFiebreleche", os.getEnfermedadFiebreleche());
					objeto.put("enfermedadLesionubres", os.getEnfermedadLesionubres());
					objeto.put("enfermedadNeumonias", os.getEnfermedadNeumonias());
					objeto.put("enfermedadOtras", os.getEnfermedadOtras());
					objeto.put("enfermedadCual", os.getEnfermedadCual());
					objeto.put("parasitosInternos", os.getParasitosInternos());
					objeto.put("parasitosInternosTrata", os.getParasitosInternosTrata());
					objeto.put("parasitosInternosFrecu", os.getParasitosInternosFrecu());
					objeto.put("parasitosExternos", os.getParasitosExternos());
					objeto.put("parasitosExternosTrata", os.getParasitosExternosTrata());
					objeto.put("parasitosExternosFrecu", os.getParasitosExternosFrecu());
					objeto.put("asistenciaVeterinaria", os.getAsistenciaVeterinaria());
					objeto.put("asistenciaVeterinariaFrecu", os.getAsistenciaVeterinariaFrec());
					objeto.put("asistenciaVeterinariaProf", os.getAsistenciaVeterinariaProf());
					objeto.put("asistenciaVeterinariaTele", os.getAsistenciaVeterinariaTele());
					objeto.put("experienciaGanaderoAnios", os.getExperienciaGanaderoAnios());
					objeto.put("espricipalIngreso", os.getEsprincipalIngreso());
					
					JSONArray animalesJSONArray= new JSONArray(); 
					ObjetoGanaderoDetalleDAO ganaderoDetalleDAO=new ObjetoGanaderoDetalleDAO();
					List<ObjetoGanaderoDetalle> detallesGanadero=ganaderoDetalleDAO.buscarPorObjetoGanadero(new BigInteger(os.getId()));
					Raza raza;
					TipoGanado tipoganado;
					RazaDAO razaDAO=new RazaDAO();
					TipoGanadoDAO tipoGanadoDAO=new TipoGanadoDAO();
					for(ObjetoGanaderoDetalle detActual: detallesGanadero)
					{
						JSONObject animalJSON = new JSONObject();
						animalJSON.put("id", detActual.getId());
						animalJSON.put("objetoGanaderoId", detActual.getObjetoGanaderoId());
						tipoganado=tipoGanadoDAO.buscarPorId(detActual.getTipoId());
						if(tipoganado!=null)
							animalJSON.put("tipoNombre", tipoganado.getNombre());
						else
							animalJSON.put("tipoNombre", "");
						animalJSON.put("tipoId", detActual.getTipoId());
						animalJSON.put("numeroArete", detActual.getNumeroArete());
						animalJSON.put("numeroChip", detActual.getNumeroChip());
						raza=razaDAO.buscarPorId(detActual.getRazaId());
						if(raza!=null)
							animalJSON.put("razaNombre", raza.getNombre());
						else
							animalJSON.put("razaNombre", "");
						
						animalJSON.put("razaId", detActual.getRazaId());
						animalJSON.put("origen", detActual.getOrigen());
						animalJSON.put("procedencia", detActual.getProcedencia());
						animalJSON.put("color", detActual.getColor());
						animalJSON.put("edad", detActual.getEdad());
						animalJSON.put("valorAsegurar", detActual.getValorAsegurar());
						animalesJSONArray.add(animalJSON);
					}
					
					objeto.put("animales", animalesJSONArray);
					
					objetos.add(objeto);
				}
				if(cotizacion.getVigenciaDesde()!=null)
				etapa2.put("vigenciaDesde", cotizacion.getVigenciaDesde());			
			}
			etapa2.put("objetos", objetos);
			retorno.put("etapa2", etapa2);
		}
		
		
		//fin etapa2
		
		//etapa 3
		if(cotizacion.getEtapaWizard()>=3){
			JSONObject etapa3= new JSONObject();
			
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
			
			EndosoBeneficiario endosoBeneficiario=new EndosoBeneficiario();
			EndosoBeneficiarioDAO endosoBeneficiarioDAO=new EndosoBeneficiarioDAO();
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
			
			endosoBeneficiario=endosoBeneficiarioDAO.buscarPorCotizacion(cotizacion);
			
			if(endosoBeneficiario!=null){
				if(endosoBeneficiario.getId()!=null){
			
				endosoBeneficiarioJSONObject.put("endosoBeneficiarioId", endosoBeneficiario.getId());
				endosoBeneficiarioJSONObject.put("beneficiarioId", endosoBeneficiario.getBeneficiario().getId());
				endosoBeneficiarioJSONObject.put("monto", endosoBeneficiario.getMonto());
				}
				
			}
			
			

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
			etapa3.put("endosoBeneficiario", endosoBeneficiarioJSONObject);
			etapa3.put("listadoCuotas", cuotasJSONArray);
			etapa3.put("formaPago", formaPago);
			etapa3.put("solicitudDescuento", solicitudDescuento);
			
			retorno.put("etapa3", etapa3);
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
		
		
		tipoDocumento = tipoDocumentoDAO.buscarPorNombre("Factura"); 
		dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
		dv=new DocumentoVisado();
		if(dvLista.size()>0)
			dv=dvLista.get(0);
		
		if(dv.getId()!=null)
			etapa6.put("tieneArchivoFactura", "1");
		else
			etapa6.put("tieneArchivoFactura", "0");
		
		
		
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
		CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
		cotizacionDetalleTransaction.eliminar(cotizacionDetalle);
		result = "Se ha eliminado correctamente";
		return result;
	}
	public void enviarCertificado(String id,String correos,String casoEspecial, HttpServletRequest request){
		String [] correosArr=correos.split(",");
		String path="/static/reportes/CertificadosVehiculos/nuevaCotizacion/certificadoVhc.jasper";		
		if(casoEspecial.equals("1")||casoEspecial.equals("true"))
			path="/static/reportes/CertificadosVehiculos/CertificadoCotizacionCasosEspeciales/certificadoVhc.jasper";
		File reportFile = new File(getServletConfig().getServletContext().getRealPath(path));
		    byte[] bytes = null;
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("COTIZACION", id);
		    String cuerpoMail = "";
		    try
		    {
		    	bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parametros, Reportes.getConnection());
		    	Reportes.cerrarConexion();
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
		for(Inspector inspectorObjeto :inspectores){
			JSONObject inspector=new JSONObject();			
			inspector.put("id",inspectorObjeto.getId());
			inspector.put("nombre",inspectorObjeto.getNombre());
			inspector.put("sucursal",inspectorObjeto.getSucursal().getNombre());
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
		//JSONObject retorno= new JSONObject();
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
		
		EntidadTransaction entidadTransaction = new EntidadTransaction();
		
		EntidadDAO entidadDAO=new EntidadDAO();
		
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
					
						if (direcciones.get(0).getCiudad() != null) {
							retorno.put("ciudad", direcciones.get(0).getCiudad().getId());

							if (direcciones.get(0).getCiudad().getProvincia() != null)
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
						
						if (direcciones.get(0).getParroquia() != null) {
							retorno.put("parroquia", direcciones.get(0).getParroquia().getId());

							if (direcciones.get(0).getParroquia().getCanton() != null) {
								retorno.put("canton", direcciones.get(0).getParroquia().getCanton().getId());

								if (direcciones.get(0).getParroquia().getCanton().getProvincia() != null)
									retorno.put("provincia", direcciones.get(0).getParroquia().getCanton().getProvincia().getId());
							}
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
	
	public JSONObject agregarVehiculo(){
		JSONObject retorno=new JSONObject();
		
		return retorno;
		
	}
}
	
