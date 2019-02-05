package com.qbe.cotizador.servlets.producto.vehiculo;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.PaqueteDAO;
import com.qbe.cotizador.dao.cotizacion.SolicitudDescuentoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ExtraDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.VhTari1ZonaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.VhTarificadorDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Extra;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Paquete;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TasaProducto;
import com.qbe.cotizador.model.TipoExtra;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoUso;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.TipoVehiculo;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.model.VhTari1Zona;
import com.qbe.cotizador.model.VhTarificador;
import com.qbe.cotizador.model.VigenciaPoliza;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.servlets.cotizacion.CoberturaComparator;
import com.qbe.cotizador.transaction.cotizacion.CotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ExtraTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ObjetoVehiculoTransaction;
import com.qbe.cotizador.util.ManejoColas;
import com.qbe.cotizador.util.ManejoFTP;
import com.qbe.cotizador.util.MotorTarifador;
import com.qbe.cotizador.util.Utilitarios;




/**
 * Servlet implementation class ObjetoVehiculoController
 */
@WebServlet("/ObjetoVehiculoController")
public class ObjetoVehiculoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObjetoVehiculoController() {
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
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");
			String tipoObjeto = request.getParameter("tipoObjeto") == null ? "" : request.getParameter("tipoObjeto").trim();
			JSONObject vehiculoJSONObject = new JSONObject();
			JSONArray vehiculoJSONArray = new JSONArray();
			JSONArray coberturasTextosJSONArray = new JSONArray();
			JSONObject coberturaTextosJSONObject = new JSONObject();
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
			ExtraTransaction extraTransaction = new ExtraTransaction();
			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
			
			
			// Metodo obtener todos vehiculos de una cotizacion ayanez
			if(tipoConsulta.equalsIgnoreCase("obtenerVehiculos") && producto.equalsIgnoreCase("productoVehiculo"))
			{			
 				cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = new Cliente();
											
				if(cotizacion.getClienteId() != null)
					cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
								
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();		
				List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
								
				ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
				List<ObjetoVehiculo> listadoVehiculos = objetoVehiculoDAO.buscarObjetoVehiculoPorCotizacionDetalle(cotizacionesDetalle);
				ObjetoVehiculo objetoVehiculo = new ObjetoVehiculo();
				SolicitudDescuentoDAO solicitudDescuentoDAO = new SolicitudDescuentoDAO();
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorId("7");
				SolicitudDescuento solicitudDescuento = solicitudDescuentoDAO.buscarPorCotizacion(cotizacion,estado);
				int i = 0;
				if(listadoVehiculos.size() > 0) {
					Double sumaAseguradaTotal = 0.0;
					Double sumaPrimaOrigen = 0.0;
					Double sumaPrimaNeta = 0.0;
					for(i=0; i<listadoVehiculos.size(); i++) {
						objetoVehiculo = (ObjetoVehiculo) listadoVehiculos.get(i);		
						vehiculoJSONObject.put("codigo_vehiculo", objetoVehiculo.getId());
						vehiculoJSONObject.put("marca_modelo", objetoVehiculo.getModelo().getMarca().getNombre() +" :: "+ objetoVehiculo.getModelo().getNombre());
						vehiculoJSONObject.put("ano_fabricacion", objetoVehiculo.getAnioFabricacion());
						
						SucursalDAO sucursalDAO = new SucursalDAO ();
						Sucursal sucursal =sucursalDAO.buscarPorId(objetoVehiculo.getSucursalId());
						vehiculoJSONObject.put("sucursal", sucursal.getNombre());
						
						CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionesDetalle.get(i).getId());
						
						sumaAseguradaTotal += cotizacionDetalle.getSumaAseguradaItem();
						sumaPrimaOrigen += cotizacionDetalle.getPrimaNetaItemOrigen();
						sumaPrimaNeta += cotizacionDetalle.getPrimaNetaItem();
						
						vehiculoJSONObject.put("suma_asegurada", cotizacionDetalle.getSumaAseguradaItem());
						vehiculoJSONObject.put("prima_vehiculo", cotizacionDetalle.getPrimaNetaItem());
						vehiculoJSONObject.put("tasa_vehiculo", cotizacionDetalle.getTasa());
						String valorRastreo = "No";
						
						if (objetoVehiculo.getDispositivoRastreo())
							valorRastreo = "Si";							
						vehiculoJSONObject.put("dispositivo_rastreo", valorRastreo);
						
						int numeroExtras = objetoVehiculo.getExtras().size();
						
						vehiculoJSONArray.add(vehiculoJSONObject);
					}
					cotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
					cotizacion.setPrimaOrigen(sumaPrimaOrigen);
					cotizacion.setPrimaNetaTotal(sumaPrimaNeta.toString());
					SolicitudDescuentoDAO sdDAO=new SolicitudDescuentoDAO();
					SolicitudDescuento sd=sdDAO.buscarPorCotizacion(cotizacion);
					if(sd==null||sd.getId()==null)
						cotizacion.setSolicitudDescuentos(new ArrayList<SolicitudDescuento>());
					if(cotizacion.getNumeroFactura()==null)
						cotizacionTransaction.editar(cotizacion);
				}
				
				result.put("vehiculosCotizacion", vehiculoJSONArray);
				result.put("numeroVehiculos", i);
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
				VariableSistema variable = new VariableSistema();  
				VariableSistemaDAO variableDAO = new VariableSistemaDAO(); 
				double valorPrima = 0;
				double valorAsegurado = 0;
				double valorPrimaDescuento = 0;
				double valorFinalPrima = 0;
				if(cotizacionesDetalle.size() > 0) {
					for(i=0; i<cotizacionesDetalle.size(); i++) {
						cotizacionDetalle = (CotizacionDetalle) cotizacionesDetalle.get(i);					
						valorPrima = valorPrima + cotizacionDetalle.getPrimaNetaItem();
						valorAsegurado = valorAsegurado+ cotizacionDetalle.getSumaAseguradaItem();
						valorPrima = (valorPrima*100)/100;
						valorAsegurado = (valorAsegurado*100)/100;
					}
					valorFinalPrima = valorPrima;
					if(solicitudDescuento != null && solicitudDescuento.getId()!=null){
						if(solicitudDescuento.getEstado().getId().equals("7")){	
							valorPrimaDescuento = ((Double.parseDouble(solicitudDescuento.getPorcentaje())/100)*valorFinalPrima);
						    valorPrimaDescuento = valorFinalPrima - valorPrimaDescuento;
							//valorFinalPrima = valorPrimaDescuento;
							cotizacion.setPrimaNetaTotal(valorFinalPrima+"");
							result.put("porcentajeDescuento", solicitudDescuento.getPorcentaje());
						}	
					}else{
						result.put("porcentajeDescuento", 0.0);  
					}
					TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
					TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
					List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
					double valorBase = 0;
					double valorDerechosEmision = 0;
					double valorSeguroCampesino = 0;
					double valorSuperBancos = 0;
					double valorIva= 0;
					double valorSubTotal = 0;
					double valorTotal = 0;
					double compensacionIVA = 0;
					double valorPrimaOtrosIva = 0.0;
					double valorPrimaOtrosSinIva = 0.0;

					
					result.put("valorPrima", valorFinalPrima);
					result.put("valorAsegurado", valorAsegurado);
					
					
					if(variablesistema.size() > 0) {
						for(i=0; i<variablesistema.size(); i++) {
							variable = (VariableSistema) variablesistema.get(i);
							if(variable.getNombre().equals("DERECHOS_EMISION")){
							   valorBase = Double.parseDouble(variable.getValor())+ valorFinalPrima;
							   valorDerechosEmision = Double.parseDouble(variable.getValor());
							   cotizacion.setImpDerechoEmision(valorDerechosEmision);
							   result.put("valorDerechosEmision", valorDerechosEmision);
							   
							}
							else if(variable.getNombre().equals("SEGURO_CAMPESINO")){
								valorSeguroCampesino = (Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								valorBase = valorBase + valorSeguroCampesino;
								cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
								result.put("valorSeguroCampesino", valorSeguroCampesino);
							}
							/*else if(variable.getNombre().equals("RECARGO_SEGURO_CAMPESINO")){
								InquiredServiceInterfaceService servicio = new InquiredServiceInterfaceService();
								valorRecargoCampesino = servicio.getInquiredServiceInterfacePort().consultarRecargoSeguroAgricola(cliente.getEntidad().getIdentificacion(), valorFinalPrima);
								cotizacion.setImpDerechoEmision(valorRecargoCampesino);
								result.put("valorRecargoCampesino", valorRecargoCampesino);
								valorBase = valorBase + valorRecargoCampesino;
								
							}*/
							else if(variable.getNombre().equals("SUPER_DE_BANCOS")){
								valorSuperBancos = (Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								result.put("valorSuperBancos", valorSuperBancos);
								cotizacion.setImpSuperBancos(valorSuperBancos);
								valorBase = valorBase + valorSuperBancos;
								
							}
							else if(variable.getNombre().equals("OTROS IVA"))
							{
								for(CotizacionCobertura cb : cotizacionDetalle.getCotizacionCoberturas())
								{
									valorPrimaOtrosIva += cb.getValorPrimaOtrosIva();
								}	
								result.put("valorPrimaOtrosIva", valorPrimaOtrosIva);
								valorBase = valorBase + valorPrimaOtrosIva;
							}
							else if(variable.getNombre().equals("OTROS SIN IVA"))
							{
								for(CotizacionCobertura cb : cotizacionDetalle.getCotizacionCoberturas())
								{
									valorPrimaOtrosSinIva += cb.getValorPrimaOtros();
								}	
								result.put("valorPrimaOtros", valorPrimaOtrosSinIva);
							}
							else if(variable.getNombre().equals("SUBTOTAL")){
								valorSubTotal = (valorBase*100)/100;
								result.put("valorSubTotal", valorSubTotal);
							}
							else if(variable.getNombre().equals("IVA")){
								
								// Calculo del IVA segun el punto de Venta								
								double resultadoIVA [] = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);
								
								valorIva = (resultadoIVA[0]*valorSubTotal/100*100)/100;
								cotizacion.setImpIva(valorIva);								
								result.put("valorIva", valorIva);

								
								// Valor de la compensacion 2% del IVA
								if(resultadoIVA [1] == 2){
									compensacionIVA = (valorIva*2)/12;
								}
							}					
						}
						valorTotal = (valorBase+valorIva+valorPrimaOtrosSinIva)*100/100;
						cotizacion.setTotalFactura(valorTotal);
						result.put("valorTotal", valorTotal);
						if(cotizacion.getNumeroFactura()==null)
							cotizacionTransaction.editar(cotizacion);
						
					}
					
				}
				
				// Obtenemos los textos de las coberturas y presentar en orden 
				for(CotizacionDetalle cotizacionDetalleItem:cotizacionesDetalle){
					List<CotizacionCobertura> cotizacionCoberturas = cotizacionDetalleItem.getCotizacionCoberturas();
					List<Cobertura> coberturas = new ArrayList<Cobertura>();
					
					// Obtenemos las coberturas de las cotizaciones coberturas
					for(CotizacionCobertura cotizacionCobertura:cotizacionCoberturas){
						Cobertura cobertura = cotizacionCobertura.getCobertura();
						coberturas.add(cobertura);
					}
					// Ordenamiento de los las coberturas por medio de las secciones y el orden
					Collections.sort(coberturas, new CoberturaComparator());
					
					// Recorre las coberturas y se les agrega dentro de un objeto json y se graba en un jsonarray
					for(Cobertura cobertura:coberturas){						
						coberturaTextosJSONObject.put("tipo_cobertura_nombre", cobertura.getTipoCobertura().getNombre());						
						coberturaTextosJSONObject.put("texto", cobertura.getNombre());
						coberturasTextosJSONArray.add(coberturaTextosJSONObject);
					}
				}
				// Enviamos los textos ordenamos de las coberturas
				result.put("textosCoberturas", coberturasTextosJSONArray);
			}
			
			
			// Metodo para agregar extras al vehiculo
			if(tipoConsulta.equalsIgnoreCase("extrasCotizacion") && producto.equalsIgnoreCase("productoVehiculo"))
			{			
				ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
				ObjetoVehiculo objetoVehiculo = objetoVehiculoDAO.buscarPorId(request.getParameter("vehiculoId"));
				
				TipoExtraDAO tipoExtraDAO = new TipoExtraDAO();
				TipoExtra tipoExtra = tipoExtraDAO.buscarPorId(request.getParameter("extraId")); 
				
				Extra extra = new Extra(); 
				extra.setObjetoVehiculo(objetoVehiculo);
				extra.setTipoExtra(tipoExtra);
				extra.setDescripcion(request.getParameter("detalleExtra"));
				extra.setValorAsegurado(Double.parseDouble(request.getParameter("valorExtra")));
				
				extra = extraTransaction.crear(extra);
				result.put("Id", extra.getId());				
			}
			
			//cargar vehiculos por web service en ensurance
			if(tipoConsulta.equals("cargarPorParametro")){
				
				// Obtener datos del usuario desde ensurance
				String parametro = request.getParameter("parametro") == null ? "" : request.getParameter("parametro");
				String nombreParametro = request.getParameter("nombreParametro") == null ? "" : request.getParameter("nombreParametro");
				
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo(parametro, nombreParametro);
				result.put("vehiculo", cargarParametroWS(resultado));
				
					
			}
			if(tipoConsulta.equals("guardarVehiculo")){
				String sumaAsegurada = request.getParameter("suma_asegurada_valor") == null ? "" : request.getParameter("suma_asegurada_valor");
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String coberturasAdicionalesStr = request.getParameter("coberturasAdicionales");
				String tasaVehiculosCerrados = request.getParameter("tasaVehiculosCerrados") == null ? "" : request.getParameter("tasaVehiculosCerrados");
				String valorExcesoRC = request.getParameter("valorExcesoRC").trim().equals("") ? "0" : request.getParameter("valorExcesoRC");
				String paquete1_check = request.getParameter("paquete1_check");
				String paquete2_check = request.getParameter("paquete2_check");
				String paquete3_check = request.getParameter("paquete3_check");
				String paquete4_check = request.getParameter("paquete4_check");
				String paquete5_check = request.getParameter("paquete5_check");
				String coberturaTR = request.getParameter("coberturaTR_check");
				String coberturaDT = request.getParameter("coberturaDT_check");
				String coberturaRC = request.getParameter("coberturaRC_check");
				String porcentajeSumaAsegurada = request.getParameter("porcentaje_suma_asegurada");
				String montoFijo = request.getParameter("monto_fijo");
				String valorSiniestro = request.getParameter("valor_siniestro");							

				String listaExtrasIds = request.getParameter("listaExtrasIds") == null ? "" : request.getParameter("listaExtrasIds");
				String listaExtrasDetalles = request.getParameter("listaExtrasDetalles") == null ? "" : request.getParameter("listaExtrasDetalles");
				String listaExtrasValores = request.getParameter("listaExtrasValores") == null ? "" : request.getParameter("listaExtrasValores");
				
				String porcentajeSumaAseguradaDT = "";
				String deducibleRC = "";

				
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();	
				TipoObjeto tipoObjetoVehiculo = new TipoObjeto();
				
				CotizacionDAO cDAO=new CotizacionDAO();
				cotizacion=cDAO.buscarPorId(cotizacionId);
				
				ObjetoVehiculo ov=agregarVehiculoCotizacion(request,cotizacion);
								
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorNombre("Vehiculos Livianos");
				}				
				if(tipoObjeto.equalsIgnoreCase("Livianos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorNombre("Vehiculos Cerrados Livianos");
				}
				if(tipoObjeto.equalsIgnoreCase("Motos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorNombre("Vehiculos Cerrados Motos");
				}
				if(tipoObjeto.equalsIgnoreCase("Pesados")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorNombre("Vehiculos Cerrados Pesados");
				}
				if(tipoObjeto.equalsIgnoreCase("Publicos")){
					tipoObjetoVehiculo = tipoObjetoDAO.buscarPorNombre("Vehículos Cerrados Publicos");
				}
				
				String[] arrListaExtrasIds = listaExtrasIds.split(","); 
				String[] arrListaExtrasDetalles = listaExtrasDetalles.split(","); 
				String[] arrListaExtrasValores = listaExtrasValores.split(",");
				
				VigenciaPoliza vp= cotizacion.getVigenciaPoliza();
				
				// Nueva funcionalidad para tener varios tarificadores
				VhTarificadorDAO vhTarificadorDAO = new VhTarificadorDAO(); 					
				java.sql.Date fechaBusqueda;
				fechaBusqueda = new java.sql.Date(cotizacion.getFechaElaboracion().getTime());	   									
				VhTarificador tarificador = vhTarificadorDAO.buscarPorFecha(fechaBusqueda);
				
				double valorTotalExtrasPrimerAnio=0.0;
				double valorTotalExtrasTodosAnios=0.0;
				double valorTotalCascoPrimerAnio=new Double(sumaAsegurada);
				double valorTotalCascoTodosAnios=depreciarValor(valorTotalCascoPrimerAnio, vp.getValor().intValue(),tarificador);

				if(arrListaExtrasDetalles.length>=0){
					valorTotalExtrasPrimerAnio = crearExtrasPorVehiculo(ov, arrListaExtrasIds, arrListaExtrasDetalles, arrListaExtrasValores);
					valorTotalExtrasTodosAnios = depreciarValor(valorTotalExtrasPrimerAnio, vp.getValor().intValue(),tarificador);
				}
				
				VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
				List<String> variableSistemaList = new ArrayList<String>();
				variableSistemaList.add("TARIFICADOR_LOCAL");
				String tarificadorLocal = variableSistemaDAO.buscarPorNombres(variableSistemaList).get(0);
								
				double primaCoberturasPrincipales=0.0;
				double tasaCasco=0.0;
				double tasaExtras=0.0;
				double primaAfectaMonto=0.0;
				double primaNoAfectaMonto=0.0;
				double primaTotalPrincipales=0.0;
				double primaExtras=0.0;
				double primaAdicionales=0.0;
				double numeroDiasVigencia=Double.parseDouble(calcularDiasVigenciaCotizacion(cotizacion)+"");
				double totalResponsabilidadCivil=0.0;
				double totalDanioTotal=0.0;
				double totalTodoRisgo=0.0;
				double porcentajeConDescuento=1-(cotizacion.getValorDescuento()/100);
				
				CotizacionDetalle cd=agregarCotizacionDetalle(ov, cotizacion, tipoObjetoVehiculo, valorTotalExtrasPrimerAnio);
				CotizacionCoberturaDAO ccDAO=new CotizacionCoberturaDAO();
				ccDAO.eliminarPorCotizacionDetalle(cd);
				cd.setCotizacionCoberturas(new ArrayList<CotizacionCobertura>());
				

				if(cotizacion.getEtapaWizard()<2)
				{
					cotizacion.setEtapaWizard(2);
					cotizacion=cotizacionTransaction.editar(cotizacion);
				}
				
				double tasaRecalculo=cotizacion.getTasaMinima();
				
				
				
				if(tipoObjeto.equalsIgnoreCase("VHDinamico")){
					
					if(coberturaTR.equalsIgnoreCase("true")){
						porcentajeSumaAseguradaDT = "0";
						deducibleRC = "0";
					}					
					if(coberturaDT.equalsIgnoreCase("true")&&!coberturaRC.equalsIgnoreCase("true")){
						porcentajeSumaAseguradaDT = porcentajeSumaAsegurada;
						 montoFijo="0";
						 valorSiniestro = "0"; 						 
						 deducibleRC = "0";
					}
					if(coberturaRC.equalsIgnoreCase("true")&&!coberturaDT.equalsIgnoreCase("true")){
						porcentajeSumaAseguradaDT = "0";
						deducibleRC = montoFijo;						 
						 valorSiniestro = "0";  
						 porcentajeSumaAsegurada = "0";
						 
					}
					if(coberturaRC.equalsIgnoreCase("true")&&coberturaDT.equalsIgnoreCase("true")){
						 porcentajeSumaAseguradaDT = porcentajeSumaAsegurada;
						 deducibleRC = montoFijo;						 
						 valorSiniestro = "0"; 						 						
					}
					

					JSONObject aux=calcularPrimaCoberturasPrincipalesVehiculosDinamicos(cotizacion, tarificadorLocal, coberturaTR, coberturaDT, 
							coberturaRC, ov, montoFijo, valorSiniestro, porcentajeSumaAsegurada, valorTotalCascoPrimerAnio,tarificador,porcentajeSumaAseguradaDT,deducibleRC);


					primaAfectaMonto = aux.getDouble("primaAfectaMonto");
					primaNoAfectaMonto = aux.getDouble("primaNoAfectaMonto");
					totalResponsabilidadCivil = aux.getDouble("totalResponsabilidadCivil");
					totalDanioTotal = aux.getDouble("totalDanioTotal");
					totalTodoRisgo = aux.getDouble("totalTodoRisgo");
					primaTotalPrincipales=primaAfectaMonto+primaNoAfectaMonto;
					tasaCasco=primaTotalPrincipales/valorTotalCascoPrimerAnio;
					double tasaMinima=new Double(variableSistemaDAO.buscarPorNombre("TASA_MINIMA").getValor());				
					
					if(tasaCasco<tasaMinima && coberturaTR.equalsIgnoreCase("true") )
						tasaCasco=tasaMinima;
					if(tasaRecalculo>0)
						tasaCasco=tasaRecalculo;
					
					tasaExtras=tasaCasco;
					primaTotalPrincipales=tasaCasco*valorTotalCascoTodosAnios;
					
					int anios=vp.getValor().intValue();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(cotizacion.getVigenciaDesde());
					
					
					double auxDepreciacion=valorTotalCascoPrimerAnio;
					double primaTotalSinImpuestos=0;

					for (int i = 1; i <= anios; i++) {
						Date fechaAnterior=calendar.getTime();
						calendar.add(Calendar.YEAR, 1);
						int numeroDias=Days.daysBetween(new DateTime(fechaAnterior), new DateTime(calendar.getTime())).getDays();
						
						double valorDepreciado=0;
						
						if (i == 1) {
							valorDepreciado = auxDepreciacion;
						} else if (i > 1) {
							if (i == 2){
								if(tarificador.getNumero().equalsIgnoreCase("1")){ // Tarificador 1
									valorDepreciado = auxDepreciacion - auxDepreciacion * 0.15;
								}
								if(tarificador.getNumero().equalsIgnoreCase("2")){ // Tarificador 2
									valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;
								}
							}
							else
								valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;

							auxDepreciacion = valorDepreciado;
						}
						primaTotalSinImpuestos+=(auxDepreciacion*tasaCasco*numeroDias)/365;
					}
					
					
					primaTotalPrincipales=primaTotalSinImpuestos;
					
					double primaAcumuladaPrincipales=guardarCotizacionCoberturaPrincipalesDinamicos(cd, new Double(porcentajeSumaAsegurada), new Double(montoFijo), new Double(valorSiniestro), totalTodoRisgo,totalDanioTotal,totalResponsabilidadCivil, porcentajeConDescuento, primaTotalPrincipales, valorTotalCascoPrimerAnio);
					double primaAcumuladaExtras=0.0;
					if(valorTotalExtrasPrimerAnio>0){
						primaAcumuladaExtras=guardarCotizacionCoberturaExtras(cd,tasaExtras,valorTotalExtrasTodosAnios, valorTotalExtrasPrimerAnio, porcentajeConDescuento, numeroDiasVigencia,tarificador);
					}
					if(coberturasAdicionalesStr.length()>0){
						primaAdicionales=guardarCotizacionCoberturasAdicionales(cd,coberturasAdicionalesStr, valorTotalCascoPrimerAnio, valorTotalCascoTodosAnios, porcentajeConDescuento, new Double(valorExcesoRC));
					}
				}
				else{
					
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();	
					GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());
					// Verificamos si la tasa es fija, formulada o tiene tasa variable
					if(grupoPorProducto.getTasaFija() && grupoPorProducto.getFormulada()== false ){ // Tasa productos tasa fija
						tasaCasco = grupoPorProducto.getPorcentajeTasaFija()/100;
						tasaExtras = grupoPorProducto.getPorcentajeExtrasTasaFija()/100;
						if(tasaExtras<=0)
							tasaExtras=tasaCasco;
					}
					if(grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada()== false ){ // Tasa productos tasa varios valores
						TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
						TasaProducto tasaProducto = new TasaProducto();
						tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());	
						tasaCasco = tasaProducto.getPorcentajeCasco()/100;
						tasaExtras =tasaProducto.getPorcentajeExtras()/100;						

					}
					if(grupoPorProducto.getTasaFija() == false && grupoPorProducto.getFormulada() ){ // Tasa productos formulados
						tasaCasco = Double.parseDouble(tasaVehiculosCerrados)/100;
						tasaExtras =Double.parseDouble(tasaVehiculosCerrados)/100;
					}
					
					primaCoberturasPrincipales=tasaCasco*valorTotalCascoTodosAnios;					


					int anios=vp.getValor().intValue();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(cotizacion.getVigenciaDesde());
					
					
					double auxDepreciacion=valorTotalCascoPrimerAnio;
					double primaTotalSinImpuestos=0;
					// Adicionamos valor para ajustar valores - Vehiculos autoconsa - plan v
					if(!grupoPorProducto.getGrupoProducto().getId().equalsIgnoreCase("11")){
					for (int i = 1; i <= anios; i++) {
						Date fechaAnterior=calendar.getTime();
						calendar.add(Calendar.YEAR, 1);
						int numeroDias=Days.daysBetween(new DateTime(fechaAnterior), new DateTime(calendar.getTime())).getDays();
						
						double valorDepreciado=0;
						
						if (i == 1) {
							valorDepreciado = auxDepreciacion;
						} else if (i > 1) {
							
							if (i == 2){
								if(tarificador.getNumero().equalsIgnoreCase("1")) // Tarificador 1
									valorDepreciado = auxDepreciacion - auxDepreciacion * 0.15;
								if(tarificador.getNumero().equalsIgnoreCase("2")){ // Tarificador 2
									valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;
								}
							}
							else
								valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;

							auxDepreciacion = valorDepreciado;
						}
						primaTotalSinImpuestos+=(auxDepreciacion*tasaCasco*numeroDias)/365;
					}					
					}else{
						primaTotalSinImpuestos = auxDepreciacion*(tasaCasco*100)/100;
						primaTotalSinImpuestos = primaTotalSinImpuestos * vp.getValor().doubleValue();
					}
					primaTotalPrincipales=primaTotalSinImpuestos;

					if(coberturaDT.equalsIgnoreCase("true")&&!coberturaRC.equalsIgnoreCase("true")){
						totalDanioTotal=primaCoberturasPrincipales;
					}
					if(coberturaRC.equalsIgnoreCase("true")&&!coberturaDT.equalsIgnoreCase("true")){
						totalResponsabilidadCivil=primaCoberturasPrincipales;
					}
					if(coberturaRC.equalsIgnoreCase("true")&&coberturaDT.equalsIgnoreCase("true")){
						totalResponsabilidadCivil=primaCoberturasPrincipales/2;
						totalResponsabilidadCivil=primaCoberturasPrincipales/2;
					}
					
					if(coberturaTR.equalsIgnoreCase("true")){
						totalTodoRisgo=primaCoberturasPrincipales;
					} 
					
					guardarCotizacionCoberturaPrincipalesCerrados(cd, new Double(porcentajeSumaAsegurada), new Double(montoFijo), new Double(valorSiniestro), totalTodoRisgo, totalDanioTotal, totalResponsabilidadCivil, porcentajeConDescuento, primaTotalPrincipales, valorTotalCascoPrimerAnio);
					
					if(valorTotalExtrasPrimerAnio>0)
						guardarCotizacionCoberturaExtras(cd, tasaExtras, valorTotalExtrasTodosAnios, valorTotalExtrasPrimerAnio, porcentajeConDescuento, numeroDiasVigencia,tarificador);
					
					if(coberturasAdicionalesStr.length()>0)
						primaAdicionales=guardarCotizacionCoberturasAdicionales(cd,coberturasAdicionalesStr, valorTotalCascoPrimerAnio, valorTotalCascoTodosAnios, porcentajeConDescuento, new Double(valorExcesoRC));
				}
				
				Paquete paquete = new Paquete();
				PaqueteDAO paqueteDAO = new PaqueteDAO();
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
				
				actualizarValoresCotizacionDetalle(cd, porcentajeConDescuento, paquete, tasaCasco);
				
				result.put("vehiculoId", ov.getId());
				result.put("tasaVehiculo", tasaCasco*100);
				
			}
			
			if(tipoConsulta.equals("actualizarVehiculo")){
				
				// Obtener datos del usuario desde ensurance
				String id = request.getParameter("id") == null ? "" : request.getParameter("id").trim();
				String placa = request.getParameter("placa") == null ? "" : request.getParameter("placa").trim();
				String motor = request.getParameter("motor") == null ? "" : request.getParameter("chasis").trim();
				String chasis = request.getParameter("chasis") == null ? "" : request.getParameter("motor").trim();
				
				ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO();
				ObjetoVehiculo ov=ovDAO.buscarPorId(id);
				
				ov.setPlaca(placa.toUpperCase());
				ov.setMotor(motor.toUpperCase());
				ov.setChasis(chasis.toUpperCase());
				
				if(ov.getId()!=null)
					objetoVehiculoTransaction.editar(ov);
					
			}
			
 			String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor").trim();
			String parametro = request.getParameter("parametro") == null ? "" : request.getParameter("parametro").trim();
			// Metodo para consultar los datos del vehiculo en la corpaire
			if (tipoConsulta.equalsIgnoreCase("consultarCorpaire")) {
				try {
					result.put("datosVehiculo",consultarCorpaire(valor,parametro));
				}
				catch (Exception e) {
					result.put("datosVehiculo", new JSONObject());
				}
			}
			
			// Metodo para consultar los datos del vehiculo en el sri por la placa
			if (tipoConsulta.equalsIgnoreCase("consultarPlacaSRI")) {
				try {
					result.put("datosVehiculo",consultarPlacaSRI(valor));
				}
				catch (Exception e) {
					result.put("datosVehiculo", new JSONObject());
				}
			}
			
			// Metodo para consultar los datos del vehiculo en el sri por el chasis
			if (tipoConsulta.equalsIgnoreCase("consultarChasisSRI")) {
				try {
					result.put("datosVehiculo",consultarChasisSRI(valor));
					
				}
				catch (Exception e) {
					result.put("datosVehiculo", new JSONObject());
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("consultarANT"))
			{		
				try {
					result.put("datosVehiculo", consultarANT(valor));
				}
				catch (Exception e) {
					result.put("datosVehiculo", new JSONObject());
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("consultaGeneral"))
			{		
				JSONObject respuesta=new JSONObject();
				String formulario = request.getParameter("formulario") == null ? "" : request.getParameter("formulario").trim();
				String numero = request.getParameter("numero") == null ? "" : request.getParameter("numero").trim();
				result.put("formulario", formulario);
				result.put("numero", numero);
				
				try {
					respuesta=consultarANT(valor);
				}
				catch (Exception e) {
					respuesta = new JSONObject();
				}
				
				if(respuesta.isEmpty())
					respuesta=consultarPlacaSRI(valor);
				result.put("datosVehiculo", respuesta);
			}
			
			if(tipoConsulta.equalsIgnoreCase("validarDatosVehiculos"))
			{		
				String placas = request.getParameter("placas") == null ? "" : request.getParameter("placas").trim().toLowerCase();
				String chasiss = request.getParameter("chasiss") == null ? "" : request.getParameter("chasiss").trim().toLowerCase();
				String motors = request.getParameter("motors") == null ? "" : request.getParameter("motors").trim().toLowerCase();
				String ids = request.getParameter("ids") == null ? "" : request.getParameter("ids").trim().toLowerCase();
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim().toLowerCase();
				
				String [] placasArr=placas.split(";");
				String [] motorsArr=motors.split(";");
				String [] chasissArr=chasiss.split(";");
				String [] idsArr=ids.split(";");
				String fechaInicioVigencia="";
				cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				SolicitudInspeccionDAO siDAO=new SolicitudInspeccionDAO();
				SolicitudInspeccion si=siDAO.buscarPorCotizacion(cotizacion);
				if(si!=null&&si.getId()!=null&&si.getEstado().getNombre().equals("Pendiente"))
					throw new Exception("Tiene una inspeccion pendiente no puede emitir!");
				
				Date aux=cotizacion.getVigenciaDesde();
				String mensaje="";
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				for(int i=0;i<idsArr.length;i++){
					JSONObject respuesta=new JSONObject(); 
					JSONObject respuesta2=new JSONObject(); 
					
					
					ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO();
					ObjetoVehiculo ov=ovDAO.buscarPorId(idsArr[i]);
					ov.setPlaca(placasArr[i].toUpperCase());
					ov.setChasis(chasissArr[i].toUpperCase());
					ov.setMotor(motorsArr[i].toUpperCase());
					objetoVehiculoTransaction.editar(ov);
					
					if (placasArr[i] != null && !placasArr[i].equals("")) {
						try {
							respuesta = consultarANT(placasArr[i]);
						}
						catch (Exception e) {
							respuesta = new JSONObject();
						}
						try {
							respuesta2 = consultarPlacaSRI(placasArr[i]);
						}
						catch (Exception e) {
							respuesta2 = new JSONObject();
						}
						if (respuesta.isEmpty())
							respuesta = respuesta2;
					}

					if(respuesta.isEmpty()&&chasissArr[i]!=null&&!chasissArr[i].equals(""))
						respuesta=consultarChasisSRI(chasissArr[i]);
					
					if(respuesta.isEmpty()){
						if(placasArr[i]!=null&&!placasArr[i].equals(""))
							throw new Exception("No se encontraron Datos para el vehiculo de placa "+placasArr[i]);
						if(chasissArr[i]!=null&&!chasissArr[i].equals(""))
							throw new Exception("No se encontraron Datos para el vehiculo de chasis "+chasissArr[i]);
					}
					
					if(!respuesta.isEmpty()){
						/*if(respuesta.get("placa")!=null&&!respuesta.get("placa").equals(""))
							if(!placasArr[i].equals(respuesta.get("placa")))
								throw new Exception("La placa del vehiculo con chasis "+chasissArr[i]+" no coincide con los datos de la ANT, por favor verifiquelo");
						
						if(respuesta.get("cpn")!=null&&!respuesta.get("cpn").equals("")&&(respuesta.get("placa")==null||respuesta.get("placa").equals("")))
							if(!placasArr[i].equals(respuesta.get("cpn")))
								throw new Exception("La placa del vehiculo con chasis "+chasissArr[i]+" no coincide con los datos de la ANT, por favor verifiquelo");
						
						if(respuesta.get("motor")!=null&&!respuesta.get("motor").equals(""))
							if(!motorsArr[i].equals(respuesta.get("motor")))
								throw new Exception("El motor del vehiculo con placa "+placasArr[i]+" no coincide con los datos de la ANT, por favor verifiquelo");
						
						if(respuesta.get("chasis")!=null&&!respuesta.get("chasis").equals(""))
							if(!chasissArr[i].equals(respuesta.get("chasis")))
								throw new Exception("El chasis del vehiculo con placa "+placasArr[i]+" no coincide con los datos de la ANT, por favor verifiquelo");
						
						if ((respuesta.get("modelo") != null && !respuesta.get("modelo").equals("")) || (respuesta2.get("modelo") != null && !respuesta2.get("modelo").equals("")))
							if ((!respuesta.get("modelo").toString().replace("-", "").replace(" ", "").contains(modelosArr[i].toString().replace("-", "")))
									&& (!respuesta2.get("modelo").toString().contains(modelosArr[i].toString().replace("-", "").split(" ")[0]))
									||
							(!modelosArr[i].toString().contains(respuesta.get("modelo").toString().replace("-", "").split(" ")[0]))
									&& (!modelosArr[i].toString().contains(respuesta2.get("modelo").toString().replace("-", "").split(" ")[0])))
								throw new Exception("El modelo del vehiculo con placa "+ placasArr[i]+ " no coincide con los datos de la ANT, por favor verifiquelo");

						if ((respuesta.get("marca") != null && !respuesta.get("marca").equals("")) || (respuesta2.get("marca") != null && !respuesta2.get("marca").equals("")))
							if ((!respuesta.get("marca").toString().replace("-","").replace(" ","").toUpperCase().contains(ov.getModelo().getMarca().getNombre().replace("-", "").split(" ")[0].toUpperCase()))
									&& (!respuesta2.get("marca").toString().replace("-","").replace(" ","").toUpperCase().contains(ov.getModelo().getMarca().getNombre().replace("-", "").split(" ")[0].toUpperCase()))
									|| 
								(!ov.getModelo().getMarca().getNombre().replace("-","").replace(" ","").toUpperCase().contains(respuesta.get("marca").toString().replace("-", "").split(" ")[0].toUpperCase()))
									&& (!ov.getModelo().getMarca().getNombre().replace("-","").replace(" ","").toUpperCase().contains(respuesta2.get("marca").toString().replace("-", "").split(" ")[0].toUpperCase())))
								throw new Exception("La marca del vehiculo con placa " + placasArr[i] + " no coincide con los datos de la ANT, por favor verifiquelo");
								*/
						
						if(respuesta.get("vigenciaEnsurance")!=null&&!respuesta.get("vigenciaEnsurance").equals("")){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
							Date fechaVigencia= sdf.parse(respuesta.get("vigenciaEnsurance").toString());
							Date fechaActual=new Date();
							int dias = Days.daysBetween(new DateTime(fechaVigencia), new DateTime(fechaActual)).getDays();
							if(dias>30&&fechaVigencia.after(fechaActual)){
								throw new Exception("El vehiculo de placa "+placasArr[i]+" se encuentra en una poliza vigente! No lo puede emitir");
							}
							if(dias<=30&&fechaVigencia.after(fechaActual)&&fechaVigencia.after(aux)){
								aux=fechaVigencia;
								fechaInicioVigencia=sdf2.format(aux);
								mensaje="El vehiculo de placa "+placasArr[i]+" se encuentra en una poliza vigente hasta el "+fechaInicioVigencia+"(yyyy-mm-dd), el inicio de la vigencia sera a partir de esta fecha";
								
							}
							if(dias>30&&fechaVigencia.after(fechaActual)&&(si==null||si.getId()==null)){
								throw new Exception("El vehiculo necesita tener una inspeccion!");
							}
						}

						
					}
					
				}
				result.put("fechaInicioVigencia", fechaInicioVigencia);
				result.put("mensaje", mensaje);
				
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
	
	public JSONObject cargarParametroWS(String xmltemp) {
		JSONObject vehiculoJSONObject = new JSONObject();
		String codigo = "";
		String anio = "";
		String chasis = "";
		String motor = "";
		String placas = "";
		String marcaId = "";
		String modeloId = "";
		String color = "";
		String dispositivo = "";
		String sucursal = "";
		String valor = "";
		String usoVehiculo = "";
		String tipoVehiculo = "";
		String agenteId = "";
		String entidadAgenteId = "";
		String vigencia = "";
		String tasa = "";

		String textoSinSaltosDeLinea = xmltemp.replaceAll("[\t\n\r]", "");
		String xmlText = textoSinSaltosDeLinea.toString();
		xmlText = xmlText.replace("<![CDATA[", "");
		xmlText = xmlText.replace("]]>", "");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Use document builder factory
		DocumentBuilder builder;

		if (!xmlText.equals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"))
			try {
				builder = factory.newDocumentBuilder();

				// Parse the document
				Reader reader = new CharArrayReader(xmlText.toCharArray());
				Document doc = builder
						.parse(new org.xml.sax.InputSource(reader));

				// Elementos del XML
				Node nodoUsuario = doc.getFirstChild();

				if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE
						&& nodoUsuario.hasChildNodes()) {

					Element usuario = (Element) nodoUsuario;
					codigo = usuario.getElementsByTagName("codigo").item(0)
							.getChildNodes().item(0).getNodeValue();
					anio = usuario.getElementsByTagName("anio").item(0)
							.getChildNodes().item(0).getNodeValue();
					chasis = usuario.getElementsByTagName("chasis").item(0)
							.getChildNodes().item(0).getNodeValue();
					motor = usuario.getElementsByTagName("motor").item(0)
							.getChildNodes().item(0).getNodeValue();
					placas = usuario.getElementsByTagName("placas").item(0)
							.getChildNodes().item(0).getNodeValue();
					marcaId = usuario.getElementsByTagName("marca").item(0)
							.getChildNodes().item(0).getNodeValue();
					modeloId = usuario.getElementsByTagName("modelo").item(0)
							.getChildNodes().item(0).getNodeValue();
					color = usuario.getElementsByTagName("color").item(0)
							.getChildNodes().item(0).getNodeValue();
					dispositivo = usuario.getElementsByTagName("dispositivo")
							.item(0).getChildNodes().item(0).getNodeValue();
					sucursal = usuario.getElementsByTagName("sucursal").item(0)
							.getChildNodes().item(0).getNodeValue();
					valor = usuario.getElementsByTagName("valor").item(0)
							.getChildNodes().item(0).getNodeValue();
					usoVehiculo = usuario.getElementsByTagName("usoVehiculo")
							.item(0).getChildNodes().item(0).getNodeValue();
					tipoVehiculo = usuario.getElementsByTagName("tipoVehiculo")
							.item(0).getChildNodes().item(0).getNodeValue();
					agenteId = usuario.getElementsByTagName("agenteId").item(0)
							.getChildNodes().item(0).getNodeValue();
					entidadAgenteId = usuario
							.getElementsByTagName("entidadAgenteId").item(0)
							.getChildNodes().item(0).getNodeValue();
					vigencia = usuario.getElementsByTagName("vigencia").item(0)
							.getChildNodes().item(0).getNodeValue();
					tasa = usuario.getElementsByTagName("tasa").item(0)
							.getChildNodes().item(0).getNodeValue();

				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		ModeloDAO modeloDAO = new ModeloDAO();
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marca = marcaDAO.buscarPorCodigoEnsurance(marcaId);
		/*String nombreMarca = "";
		if (marca.getId() != null)
			nombreMarca = marca.getNombre();*/

		Modelo modelo = modeloDAO.buscarPorCodigoEnsurance(modeloId);
		/*String nombreModelo = "";
		if (modelo.getId() != null)
			nombreModelo = modelo.getNombre();*/

		vehiculoJSONObject.put("codigo", codigo.trim());
		vehiculoJSONObject.put("anio", anio.trim());
		vehiculoJSONObject.put("chasis", chasis.trim());
		vehiculoJSONObject.put("motor", motor.trim());
		vehiculoJSONObject.put("placas", placas.trim());
		vehiculoJSONObject.put("marca", marca.getId());
		vehiculoJSONObject.put("modelo", modelo.getId());
		vehiculoJSONObject.put("color", color.trim());
		vehiculoJSONObject.put("dispositivo", dispositivo.trim());
		vehiculoJSONObject.put("sucursal", sucursal.trim());
		vehiculoJSONObject.put("valor", valor.trim());
		vehiculoJSONObject.put("usoVehiculo", usoVehiculo.trim());
		vehiculoJSONObject.put("tipoVehiculo", tipoVehiculo.trim());
		vehiculoJSONObject.put("agenteId", agenteId);
		vehiculoJSONObject.put("entidadAgenteId", entidadAgenteId);
		vehiculoJSONObject.put("vigencia", vigencia.trim());
		vehiculoJSONObject.put("tasa", tasa.trim());

		return vehiculoJSONObject;

	}

	public JSONObject consultarANT(String placa) throws Exception{
		JSONObject result=new JSONObject();
		
		
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
			  return result;
		  String infAuto = asText.replaceAll("\n", ":");
		  String [] arrInfAuto = infAuto.split(":");

		  Color colorEnsurance = new Color();
		  ColorDAO colorDAO =  new ColorDAO();
		  
		  Marca marcaEnsurance = new Marca();
		  MarcaDAO marcaDAO = new MarcaDAO();
		  
		  Modelo modeloEnsurance = new Modelo();
		  ModeloDAO modeloDAO = new ModeloDAO();
		  String modelo="";
		  String marca="";
		  String placaValor="";
		  String chasis="";
		  String motor="";
		  String tipo="";
		  String clase="";
		  String anio="";
		  String color="";
		  int cont=0;
		  
		  for(String s:arrInfAuto){
			  if(s.trim().equals("chasis")&&arrInfAuto[cont+1]!=null)
				  chasis=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("modelo")&&arrInfAuto[cont+1]!=null)
				  modelo=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("marca")&&arrInfAuto[cont+1]!=null)
				  marca=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("placa")&&arrInfAuto[cont+1]!=null)
				  placaValor=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("motor")&&arrInfAuto[cont+1]!=null)
				  motor=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("tipo")&&arrInfAuto[cont+1]!=null)
				  tipo=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("clase")&&arrInfAuto[cont+1]!=null)
				  clase=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("año")&&arrInfAuto[cont+1]!=null)
				  anio=arrInfAuto[cont+1].trim();
			  if(s.trim().equals("color")&&arrInfAuto[cont+1]!=null)
				  color=arrInfAuto[cont+1].trim();
			  cont++;
		  }
		  			
		  if(placaValor.equals(""))
				  return result;
		 String valorColor=" ";
		  marcaEnsurance = marcaDAO.buscarPorNombre(marca);
		  if(color.length()>0){
			  colorEnsurance = colorDAO.buscarPorNombre(color);
			  valorColor = colorEnsurance.getId();
		  }
		  
		  modeloEnsurance = modeloDAO.buscarPorMarcaYNombre(marcaEnsurance, modelo);
		  result.put("placa", placa.trim().toLowerCase());
		  result.put("chasis", chasis.trim().toLowerCase());
		  result.put("motor", motor.trim().toLowerCase());
		  if(marcaEnsurance.getId()!=null){
			  result.put("marcaEnsurance", marcaEnsurance.getId().toLowerCase());
			  result.put("marca", marcaEnsurance.getNombre().toLowerCase());
		  }
		  else{
				result.put("marcaEnsurance", -1);
				result.put("marca", marca);
			}
		    
			  
		  result.put("tipo", tipo.trim().toLowerCase());
		  result.put("clase", clase.trim().toLowerCase());
		  if(valorColor!=null)
			  result.put("color", valorColor.trim().toLowerCase());
		  else
			  result.put("color", "");
		  result.put("anioFabricacion", anio.trim().toLowerCase());
			if (modeloEnsurance.getId() != null) {
				result.put("modeloEnsurance", modeloEnsurance.getId().toLowerCase());
				result.put("modelo", modeloEnsurance.getNombre().toLowerCase());
			}
			else{
				result.put("modeloEnsurance", -1);
				result.put("modelo", modelo);
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
			  if(cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Borrador")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente de Emitir"))
				  cotizacionId=cd.get(i).getCotizacion().getId();
		  }
		  
		  
		  result.put("cotizacionId", cotizacionId);


			WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
			String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo( chasis.trim().toUpperCase(), "chasis");
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(Utilitarios.cargarParametroWS(resultado));
			result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
			if(jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("POL")||jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("INC"))
				result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
			else
				result.put("vigenciaEnsurance", "");

		
		return result;
	}

	
	public JSONObject consultarPlacaSRI(String placa) throws Exception{
		JSONObject result=new JSONObject();
		
		VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();			
		WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
		String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo( placa, "placa");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(Utilitarios.cargarParametroWS(resultado));
		boolean existeEnsurance = false;
		// Traemos la informacion de ensurance del vehiculo
		if(jsonArray.size()>0){
			existeEnsurance = true;
			VariableSistema habilitaConsultaEnsurance = variableSistemaDAO.buscarPorNombre("HABILITAR_CONSULTA_ENSURANCE_VH");
			if(habilitaConsultaEnsurance.getValor().equals("1")){
			  result.put("placa", placa);
			  result.put("chasis", jsonArray.getJSONObject(0).get("chasis"));
			  result.put("motor", jsonArray.getJSONObject(0).get("motor"));
			  result.put("marcaEnsurance", jsonArray.getJSONObject(0).get("marca"));
			  MarcaDAO marcaDAO = new MarcaDAO();
			  Marca marca = marcaDAO.buscarPorNombre(String.valueOf(jsonArray.getJSONObject(0).get("marca")));			  
			  result.put("marca", marca.getNombre());
			  result.put("anioFabricacion", jsonArray.getJSONObject(0).get("anio"));
			  ModeloDAO modeloDAO = new ModeloDAO();
			  Modelo modelo = modeloDAO.buscarPorId(String.valueOf(jsonArray.getJSONObject(0).get("modelo")));
			  result.put("modeloEnsurance", jsonArray.getJSONObject(0).get("modelo"));
			  result.put("modelo", modelo == null ? "" : modelo.getNombre());
			  
			  ColorDAO colorDAO = new ColorDAO();
			  //Color color = colorDAO.buscarPorId(String.valueOf(jsonArray.getJSONObject(0).get("color")));
			  //result.put("color",  color.getNombre().substring(0,1).toUpperCase()+color.getNombre().substring(1).toLowerCase());
			  TipoVehiculoDAO tipoVehiculoDAO = new TipoVehiculoDAO();
			  result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
			  if(jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("POL")||jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("INC"))
				result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
			  else
				result.put("vigenciaEnsurance", "");
			}
		}
		
		if(existeEnsurance==false){
		VariableSistema habilitaSRI = variableSistemaDAO.buscarPorNombre("HABILITAR_CONSULTA_SRI_VH");		
		if(habilitaSRI.getValor().equals("1")){											
			final WebClient webClient = new WebClient();

			// Get the first page
			final HtmlPage page1 = webClient.getPage("https://declaraciones.sri.gob.ec/mat-vehicular-internet/reportes/general/valoresAPagar.jsp");

			// Get the form that we are dealing with and within that
			// form,
			// find the submit button and the field that we want to
			// change.
			//final HtmlElement form = page1;
			final HtmlTextInput textField = (HtmlTextInput) page1.getElementByName("placaCamv");
			final HtmlSubmitInput button = (HtmlSubmitInput) page1.getElementByName("btnBuscar");

			// Change the value of the text field
			textField.setValueAttribute(placa);

			// Now submit the form by clicking the button and get back
			// the second page.
			final HtmlPage page2 = button.click();
			String asText = page2.asText().toLowerCase();

			if (!asText.contains("datos del vehículo")||asText.contains("no existe el vehículo con placa, camv o cpn")) {
				return result;
			}
			else {
				String informacionEncontrada = asText.substring(asText.indexOf("datos del vehículo")).replaceAll("\r\n",":");
				String infAuto = informacionEncontrada.replaceAll("\n", ":").replaceAll("\t", "").replaceAll("  ", ":");
				String [] arrInfAuto = infAuto.split(":");
				  Marca marcaEnsurance = new Marca();
				  MarcaDAO marcaDAO = new MarcaDAO();
				  
				  Modelo modeloEnsurance = new Modelo();
				  ModeloDAO modeloDAO = new ModeloDAO();
				  String modelo="";
				  String marca="";
				  String placaValor="";
				  String chasis="";
				  String motor="";
				  String tipo="";
				  String clase="";
				  String anio="";
				  String color="";
				  String cpn="";
				  int cont=0;
				  
				  for(String s:arrInfAuto){
					  if(s.trim().equals("chasis")&&arrInfAuto[cont+1]!=null)
						  chasis=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("modelo")&&arrInfAuto[cont+1]!=null)
						  modelo=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("marca")&&arrInfAuto[cont+1]!=null)
						  marca=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("placa")&&arrInfAuto[cont+1]!=null)
						  placaValor=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("motor")&&arrInfAuto[cont+1]!=null)
						  motor=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("tipo")&&arrInfAuto[cont+1]!=null)
						  tipo=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("clase")&&arrInfAuto[cont+1]!=null)
						  clase=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("año")&&arrInfAuto[cont+1]!=null)
						  anio=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("color 1")&&arrInfAuto[cont+1]!=null)
						  color=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("camv o cpn")&&arrInfAuto[cont+1]!=null)
						  cpn=arrInfAuto[cont+1].trim();
					  cont++;
				  }
				
				webClient.closeAllWindows();

				marcaEnsurance = marcaDAO.buscarPorNombre(marca);

				modeloEnsurance = modeloDAO.buscarPorMarcaYNombre( marcaEnsurance, modelo);
				result.put("placa", placa.trim().toLowerCase());
				result.put("chasis", chasis.trim().toLowerCase());
				result.put("marcaEnsurance", marcaEnsurance.getId().toLowerCase());
				result.put("marca", marcaEnsurance.getNombre().toLowerCase());
				result.put("anioFabricacion", anio.trim().toLowerCase());
				result.put("color", color.trim().toLowerCase());
				if (modeloEnsurance.getId() != null) {
					result.put("modeloEnsurance", modeloEnsurance.getId().toLowerCase());
					result.put("modelo", modeloEnsurance.getNombre().toLowerCase());
				}
				else {
					result.put("modeloEnsurance", -1);
					result.put("modelo", modelo);
				}
			}			
		  }
		}

		String cotizacionId="";
			  
		CotizacionDetalleDAO cdDAO = new CotizacionDetalleDAO(); 
		ObjetoVehiculoDAO ovDAO= new ObjetoVehiculoDAO();
		ObjetoVehiculo ov=ovDAO.buscarPorPlaca(placa.toUpperCase());
		List<CotizacionDetalle>  cd=new ArrayList<CotizacionDetalle>();
		
		// Solo se realiza la busqueda en VH no en los otros modulos  
		if(ov.getId()!=null){										
			List<String> tiposObjetosVehiculos = Arrays.asList("1","2","4","5","6");			
			cd=cdDAO.buscarCotizacionesDetallePorObjetoIdTipoObjeto(ov.getId(),tiposObjetosVehiculos);
			}  		
		for(int i=0;i<cd.size();i++){
		if(cd.get(i).getId()!=null)
		  if(cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Borrador")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente de Emitir"))
			  cotizacionId=cd.get(i).getCotizacion().getId();
		}
		  		  
		result.put("cotizacionId", cotizacionId);
		return result;
	}
	
	public JSONObject consultarCorpaire(String valor, String parametro) throws Exception{
		JSONObject result=new JSONObject();
		

		String valorSelect = "";
		int longitudMinima = 0;

		if (parametro.equals("placa")) {
			valorSelect = "0";
			longitudMinima = 5;
		}
		if (parametro.equals("chasis")) {
			valorSelect = "1";
			longitudMinima = 7;
		}
		if (parametro.equals("cpn")) {
			valorSelect = "2";
			longitudMinima = 5;
		}
		if (parametro.equals("placaAnterior")) {
			valorSelect = "3";
			longitudMinima = 5;
		}

		if (valor.length() > longitudMinima) {
			final WebClient webClient = new WebClient();

			// Get the first page
			final HtmlPage page1 = webClient.getPage("http://186.42.161.195/appSIMUtilesSite/rtv_onLine/new_Buscar.jsp");

			// Get the form that we are dealing with and within that
			// form,
			// find the submit button and the field that we want to
			// change.
			final HtmlForm form = page1.getFormByName("frm_Datos");
			final HtmlSelect select = form.getSelectByName("cbo_tipo");
			final HtmlTextInput textField = form.getInputByName("txt_dato");
			final HtmlSubmitInput button = form.getInputByName("btn_ingresar");

			// Change the value of the text field
			textField.setValueAttribute(valor);

			HtmlOption option1 = select.getOptionByValue(valorSelect);
			select.setSelectedAttribute(option1, true);

			// Now submit the form by clicking the button and get back
			// the second page.
			final HtmlPage page2 = button.click();
			String asText = page2.asText();

			if (asText.contains("« Inicio")) {
				return result;
			}
			else {
				String[] informacionEncontrada = asText.split("\r\n");

				String arregloPlaca[] = informacionEncontrada[3].split("\t");
				String arregloChasis[] = informacionEncontrada[4].split("\t");
				String arregloMarca[] = informacionEncontrada[5].split("\t");
				String arregloModelo[] = informacionEncontrada[6].split("\t");

				// Obtenemos los valores de placa, chasis, marca y
				// modelo

				String placaEncontrada = "";
				String chasisEncontrado = "";
				String marcaEncontrada = "";
				String motorEncontrado = "";
				String modeloEncontrado = "";
				String anoEncontrado = "";
				String cpnEncontrado = "";

				placaEncontrada = arregloPlaca[1].trim();
				chasisEncontrado = arregloChasis[1].trim();
				cpnEncontrado = arregloChasis[3].trim();
				marcaEncontrada = arregloMarca[1].trim();
				motorEncontrado = arregloMarca[3].trim();
				modeloEncontrado = arregloModelo[1].trim();
				anoEncontrado = arregloModelo[3].trim();

				webClient.closeAllWindows();

				Marca marcaEnsurance = new Marca();
				MarcaDAO marcaDAO = new MarcaDAO();

				Modelo modeloEnsurance = new Modelo();
				ModeloDAO modeloDAO = new ModeloDAO();

				marcaEnsurance = marcaDAO.buscarPorNombre(marcaEncontrada);

				modeloEnsurance = modeloDAO.buscarPorMarcaYNombre( marcaEnsurance, modeloEncontrado);
				result.put("placa", placaEncontrada);
				result.put("chasis", chasisEncontrado);
				result.put("motor", motorEncontrado);
				result.put("marcaEnsurance", marcaEnsurance.getId());
				result.put("marca", marcaEnsurance.getNombre());
				result.put("anioFabricacion", anoEncontrado);
				result.put("cpn", cpnEncontrado);
				if (modeloEnsurance.getId() != null) {
					result.put("modeloEnsurance", modeloEnsurance.getId());
					result.put("modelo", modeloEnsurance.getNombre());
				}
				else {
					result.put("modeloEnsurance", -1);
					result.put("modelo", modeloEncontrado);
				}
				if (chasisEncontrado != null && chasisEncontrado.equals("")) {
					valor = chasisEncontrado;
					parametro = "chasis";
				}
				
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo(valor, parametro);
				JSONArray jsonArray = new JSONArray();
				jsonArray.add(Utilitarios.cargarParametroWS(resultado));
				result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
				if(jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("POL")||jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("INC"))
					result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
				else
					result.put("vigenciaEnsurance", "");
			}
		}

		return result;
	}
	
	public JSONObject consultarChasisSRI(String chasis) throws Exception{
		JSONObject result=new JSONObject();
		
			/*final WebClient webClient = new WebClient();

			// Get the first page
			final HtmlPage page1 = webClient.getPage("https://declaraciones.sri.gob.ec/mat-vehicular-internet/reportes/general/reporteVehiculosChasis.jsp");

			// Get the form that we are dealing with and within that
			// form,
			// find the submit button and the field that we want to
			// change.
			//final HtmlElement form = page1;
			final HtmlTextInput textField = (HtmlTextInput) page1.getElementByName("chasis");
			final HtmlSubmitInput button = (HtmlSubmitInput) page1.getElementByName("btnBuscar");

			// Change the value of the text field
			textField.setValueAttribute(chasis);

			// Now submit the form by clicking the button and get back
			// the second page.
			final HtmlPage page2 = button.click();
			String asText = "";
			if(page2!=null)
			asText = page2.asText().toLowerCase();

			if (!asText.contains("datos del vehículo")||asText.contains("no existe el vehículo con placa, camv o cpn")) {
				return result;
			}
			else {
				String informacionEncontrada = asText.substring(asText.indexOf("datos del vehículo")).replaceAll("\r\n",":");
				String infAuto = informacionEncontrada.replaceAll("\n", ":").replaceAll("\t", "").replaceAll("  ", ":");
				String [] arrInfAuto = infAuto.split(":");
				  Marca marcaEnsurance = new Marca();
				  MarcaDAO marcaDAO = new MarcaDAO();
				  
				  Modelo modeloEnsurance = new Modelo();
				  ModeloDAO modeloDAO = new ModeloDAO();
				  String modelo="";
				  String marca="";
				  String placaValor="";
				  String chasisValor="";
				  String motor="";
				  String tipo="";
				  String clase="";
				  String anio="";
				  String color="";
				  String cpn="";
				  int cont=0;
				  
				  for(String s:arrInfAuto){
					  if(s.trim().equals("chasis")&&arrInfAuto[cont+1]!=null)
						  chasisValor=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("modelo")&&arrInfAuto[cont+1]!=null)
						  modelo=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("marca")&&arrInfAuto[cont+1]!=null)
						  marca=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("placa")&&arrInfAuto[cont+1]!=null)
						  placaValor=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("motor")&&arrInfAuto[cont+1]!=null)
						  motor=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("tipo")&&arrInfAuto[cont+1]!=null)
						  tipo=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("clase")&&arrInfAuto[cont+1]!=null)
						  clase=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("año")&&arrInfAuto[cont+1]!=null)
						  anio=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("color 1")&&arrInfAuto[cont+1]!=null)
						  color=arrInfAuto[cont+1].trim();
					  if(s.trim().equals("camv o cpn")&&arrInfAuto[cont+1]!=null)
						  cpn=arrInfAuto[cont+1].trim();
					  cont++;
				  }
				
				webClient.closeAllWindows();

				marcaEnsurance = marcaDAO.buscarPorNombre(marca);
				
				if(marcaEnsurance.getId()!=null){
				modeloEnsurance = modeloDAO.buscarPorMarcaYNombre( marcaEnsurance, modelo);
				result.put("placa", placaValor.trim().toLowerCase());
				result.put("chasis", chasis.trim().toLowerCase());
				result.put("marcaEnsurance", marcaEnsurance.getId().toLowerCase());
				result.put("marca", marcaEnsurance.getNombre().toLowerCase());
				result.put("anioFabricacion", anio.trim().toLowerCase());
				result.put("color", color.trim().toLowerCase());
				if (modeloEnsurance.getId() != null) {
					result.put("modeloEnsurance", modeloEnsurance.getId().toLowerCase());
					result.put("modelo", modeloEnsurance.getNombre().toLowerCase());
				}
				else {
					result.put("modeloEnsurance", -1);
					result.put("modelo", modelo);
				}
				}
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo( chasis, "chasis");
				JSONArray jsonArray = new JSONArray();
				jsonArray.add(Utilitarios.cargarParametroWS(resultado));
				result.put("codigoEnsurance", jsonArray.getJSONObject(0).get("codigo"));
				if(jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("POL")||jsonArray.getJSONObject(0).get("tipoEndoso").toString().equals("INC"))
					result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
				else
					result.put("vigenciaEnsurance", "");
			}*/
		  String cotizacionId="";
			  
		  CotizacionDetalleDAO cdDAO = new CotizacionDetalleDAO(); 
		  ObjetoVehiculoDAO ovDAO= new ObjetoVehiculoDAO();
		  ObjetoVehiculo ov=ovDAO.buscarPorChasis(chasis.toUpperCase());
		  List<CotizacionDetalle>  cd=new ArrayList<CotizacionDetalle>();
		  
		  if(ov.getId()!=null)
			   cd=cdDAO.buscarCotizacionesDetallePorObjetoId(ov.getId());
		  
		  for(int i=0;i<cd.size();i++){
		  if(cd.get(i).getId()!=null)
			  if(cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Borrador")||cd.get(i).getCotizacion().getEstado().getNombre().equals("Pendiente de Emitir"))
				  cotizacionId=cd.get(i).getCotizacion().getId();
		  }
		  
		  
		  result.put("cotizacionId", cotizacionId);


		return result;
		}
	
	public ObjetoVehiculo agregarVehiculoCotizacion(HttpServletRequest request,Cotizacion cotizacion){
		String codigoVehiculoEnsurance = request.getParameter("codigoVehiculoEnsurance")  == null ? "" : request.getParameter("codigoVehiculoEnsurance");
		String placa = request.getParameter("placa")  == null ? "" : request.getParameter("placa").trim();
		String chasis = request.getParameter("chasis")  == null ? "" : request.getParameter("chasis").trim();
		String motor = request.getParameter("motor")  == null ? "" : request.getParameter("motor").trim();
		String vehiculoId = request.getParameter("vehiculoId")  == null ? "" : request.getParameter("vehiculoId");
		String modeloId = request.getParameter("modelo") == null ? "" : request.getParameter("modelo");
		String sucursalId = request.getParameter("sucursal_id") == null ? "" : request.getParameter("sucursal_id");
		String anioFabricacion = request.getParameter("anio_fabricacion") == null ? "" : request.getParameter("anio_fabricacion");
		String sumaAsegurada = request.getParameter("suma_asegurada_valor") == null ? "" : request.getParameter("suma_asegurada_valor");
		String conDispositivoRastreo = request.getParameter("disposito_rastreo") == null ? "" : request.getParameter("disposito_rastreo");
		String esCeroKilometro = request.getParameter("cero_kilometros") == null ? "" : request.getParameter("cero_kilometros");
		String antiguedadVh = request.getParameter("antiguedad_vh") == null ? "" : request.getParameter("antiguedad_vh");
		String conductorEdad = request.getParameter("conductor_edad") == null ? "" : request.getParameter("conductor_edad");
		String conductorGenero = request.getParameter("conductor_genero") == null ? "" : request.getParameter("conductor_genero");
		String conductorEstadoCivil = request.getParameter("conductor_estado_civil") == null ? "" : request.getParameter("conductor_estado_civil");
		String numeroHijos = request.getParameter("numero_hijos") == null ? "" : request.getParameter("numero_hijos");
		String kmRecorridos = request.getParameter("km_recorridos") == null ? "" : request.getParameter("km_recorridos");
		String combustible = request.getParameter("combustible") == null ? "" : request.getParameter("combustible");
		String colorId = request.getParameter("color") == null ? "" : request.getParameter("color");
		String tipoAdquisicionId = request.getParameter("codigoTipoAdquisicion") == null ? "" : request.getParameter("codigoTipoAdquisicion");
		String tonelaje = request.getParameter("tonelaje") == null ? "" : request.getParameter("tonelaje");
		String codigoTipoUso = request.getParameter("codigoTipoUso") == null ? "" : request.getParameter("codigoTipoUso").trim();
		String codigoTipoVehiculo = request.getParameter("codigoTipoVehiculo") == null ? "" : request.getParameter("codigoTipoVehiculo").trim();
		String zona = request.getParameter("zona") == null ? "" : request.getParameter("zona").trim();
		String guardaGarage = request.getParameter("guarda_garage") == null ? "" : request.getParameter("guarda_garage").trim();
		String kilometrajeAnual = request.getParameter("kilometraje_anual") == null ? "" : request.getParameter("kilometraje_anual").trim();
		String hijosConduzcan = request.getParameter("hijos_conduzcan") == null ? "" : request.getParameter("hijos_conduzcan").trim();
		String zonaTransito = request.getParameter("zona_transito") == null ? "" : request.getParameter("zona_transito").trim();		
		String identificacionConductor = request.getParameter("identificacion_conductor") == null ? "" : request.getParameter("identificacion_conductor").trim();
		String nombresConductor = request.getParameter("nombres_conductor") == null ? "" : request.getParameter("nombres_conductor").trim();
		String apellidosConductor = request.getParameter("apellidos_conductor") == null ? "" : request.getParameter("apellidos_conductor").trim();
		String conductorEntidadEnsuranceId = request.getParameter("codigo_conductor_ensurance") == null ? "" : request.getParameter("codigo_conductor_ensurance").trim();
						
		if(sucursalId == ""){
			if(cotizacion.getPuntoVenta()==null){
				sucursalId ="1";	
			}else{
				sucursalId =cotizacion.getPuntoVenta().getSucursal().getId() ;
			}			
		}
		if(antiguedadVh=="")
			antiguedadVh = "0";
		
		ObjetoVehiculo vehiculo = new ObjetoVehiculo();
		vehiculo = agregarVehiculoCotizacion(modeloId,colorId,codigoTipoVehiculo,codigoTipoUso,sucursalId,placa,vehiculoId,chasis,codigoVehiculoEnsurance,motor,anioFabricacion,
											antiguedadVh,conductorEdad,conductorGenero,conductorEstadoCivil,numeroHijos,kmRecorridos,combustible,tipoAdquisicionId,tonelaje,
											conDispositivoRastreo,esCeroKilometro,sumaAsegurada,zona,guardaGarage,kilometrajeAnual,hijosConduzcan,zonaTransito,identificacionConductor,
											nombresConductor,apellidosConductor,conductorEntidadEnsuranceId);
						
		return vehiculo;
		
	}
	
	public static ObjetoVehiculo agregarVehiculoCotizacion(String modeloId,String colorId,String codigoTipoVehiculo,String codigoTipoUso,String sucursalId,String placa,
			String vehiculoId,String chasis,String codigoVehiculoEnsurance,String motor,String anioFabricacion,String antiguedadVh,String conductorEdad,String conductorGenero,
			String conductorEstadoCivil,String numeroHijos,String kmRecorridos,String combustible,String tipoAdquisicionId,String tonelaje,String conDispositivoRastreo,
			String esCeroKilometro,String sumaAsegurada,String zona,String guardaGarage,String kilometrajeAnual,String hijosConduzcan,String zonaTransito,String identificacionConductor,
			String nombresConductor,String apellidosConductor,String conductorEntidadEnsuranceId){
		
		ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
		
		ModeloDAO modeloDAO = new ModeloDAO();				
		Modelo modelo = modeloDAO.buscarPorId(modeloId);
		
		ColorDAO colorDAO = new ColorDAO();				
		Color color = colorDAO.buscarPorId(colorId);
		
		TipoVehiculoDAO tipoVehiculoDAO = new TipoVehiculoDAO();
		TipoVehiculo tipoVehiculo = null;
		
		if(!codigoTipoVehiculo.equals(""))
			tipoVehiculo= tipoVehiculoDAO.buscarPorId(codigoTipoVehiculo);

		TipoUsoDAO tipoUsoDAO = new TipoUsoDAO();
		
		if(codigoTipoUso.equals(""))
			codigoTipoUso = "1";
		
		TipoUso tipoUso = tipoUsoDAO.buscarPorId(codigoTipoUso); 				
		
		SucursalDAO sucursalDAO = new SucursalDAO();
		Sucursal sucursal = new Sucursal();
		sucursal = sucursalDAO.buscarPorId(sucursalId);
		
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
		vehiculo.setZona(zona);		
		vehiculo.setKilometrosRecorridos(kmRecorridos);
		vehiculo.setCombustible(combustible);
		vehiculo.setTipoAdquisicion(tipoAdquisicionId);
		vehiculo.setSucursalId(sucursal.getId());
		vehiculo.setTipoUso(tipoUso);
		vehiculo.setKilometrajeAnual(kilometrajeAnual);				
		
		if(!zonaTransito.equals("")){
			VhTari1ZonaDAO vhTari1ZonaDAO   = new VhTari1ZonaDAO();
			VhTari1Zona vhTari1Zona = vhTari1ZonaDAO.buscarPorId(zonaTransito);
			vehiculo.setVhTari1Zona(vhTari1Zona);
		}
		
		vehiculo.setHijosConduzcan(hijosConduzcan=="SI"?true:false);
				
		Double tonelajeValor=0.0;
		if(!tonelaje.trim().equals(""))
			tonelajeValor = Double.parseDouble(tonelaje);
		
		vehiculo.setTonelajeVehiculo(tonelajeValor);	
		
		// Agregamos el conductor de acuerdo a la nueva tarificacion
		if(!identificacionConductor.equals("")){
			EntidadDAO entidadDAO = new EntidadDAO();	
			Entidad conductor = entidadDAO.buscarEntidadPorIdentificacion(identificacionConductor);
			if(conductor != null){
				vehiculo.setConductor(conductor);	
			}else{
				EntidadTransaction entidadTransaction = new EntidadTransaction();
				TipoIdentificacionDAO tipoIdentificacionDAO = new TipoIdentificacionDAO();
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				conductor.setIdentificacion(identificacionConductor);
				conductor.setNombres(nombresConductor);
				conductor.setApellidos(apellidosConductor);
				conductor.setEntEnsurance(conductorEntidadEnsuranceId);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
				conductor.setFechaCreacion(fechaActual);
				conductor.setNombreCompleto(nombresConductor+ " "+ apellidosConductor);
				conductor.setTipoIdentificacion(tipoIdentificacionDAO.buscarPorId("1"));// Por defecto ponemos cedula
				conductor.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));// Por defecto ponemos persona natural  
				conductor=entidadTransaction.crear(conductor);
				vehiculo.setConductor(conductor);
			}				
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
		if (guardaGarage.equalsIgnoreCase("1"))
			valorGuardaGarage = true;		
		vehiculo.setGuardaGarage(valorGuardaGarage);

		boolean vehiculoExistente = false;
		if(vehiculo.getId() != null && vehiculo.getId() != "")
			vehiculoExistente = true;
						
		if(vehiculoExistente)
			vehiculo=objetoVehiculoTransaction.editar(vehiculo);
		else
			vehiculo=objetoVehiculoTransaction.crear(vehiculo);

		
		return vehiculo;
	}
	
	
	public static CotizacionDetalle agregarCotizacionDetalle(ObjetoVehiculo ov, Cotizacion cotizacion, TipoObjeto tipoObjeto, double sumaExtras){
		CotizacionDetalle cd= new CotizacionDetalle();
		CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
		CotizacionDetalleDAO cdDAO=new CotizacionDetalleDAO();
		cd=cdDAO.buscarCotizacionDetalleIdYObjetoId(ov.getId(), cotizacion);
		cd.setObjetoId(ov.getId());
		cd.setValorExtras(sumaExtras);
		cd.setSumaAseguradaItem(ov.getSumaAsegurada()+sumaExtras);
		cd.setCotizacion(cotizacion);
		cd.setTipoObjetoId(tipoObjeto.getId());
		if(cd.getId()==null)
			cotizacionDetalleTransaction.crear(cd);
		else
			cotizacionDetalleTransaction.editar(cd);
		
		
		return cd;
	}

	public double crearExtrasPorVehiculo(ObjetoVehiculo ov, String[] arrListaExtrasIds, String[] arrListaExtrasDetalles, String[] arrListaExtrasValores){
		
		//double totalExtras=0.0;
		TipoExtraDAO tipoExtraDAO = new TipoExtraDAO();
		Extra extra = new Extra();
		ExtraDAO extraDAO = new ExtraDAO();
		ExtraTransaction extraTransaction = new ExtraTransaction(); 
		
		//eliminar extras antiguos
		List<Extra> extrasAntiguos=extraDAO.buscarPorObjetoVehiculo(ov);
		if(extrasAntiguos!=null&&extrasAntiguos.size()>0){
			for(int i=0;i<extrasAntiguos.size();i++){
				extraTransaction.eliminar(extrasAntiguos.get(i));
			}
		}
		
		double sumaExtras=0.0;
		
		//se agregan extras nuevos
		for(int i=1; i< arrListaExtrasIds.length; i++){

			Double valorExtra=Double.parseDouble(arrListaExtrasValores[i]);
			extra.setTipoExtra(tipoExtraDAO.buscarPorId(arrListaExtrasIds[i]));
			extra.setObjetoVehiculo(ov);
			extra.setDescripcion(arrListaExtrasDetalles[i]);
			extra.setValorAsegurado(valorExtra);
			sumaExtras+=valorExtra;

			extraTransaction.crear(extra);
		}
		return sumaExtras;
	}

	
	public JSONObject calcularPrimaCoberturasPrincipalesVehiculosDinamicos(Cotizacion cotizacion, String tarificadorLocal, String coberturaTR, String coberturaDT, 
			String coberturaRC, ObjetoVehiculo ov, String montoFijo, String valorSiniestro, String porcentajeSumaAsegurada, double valorVehiculoTodosAnios,
			VhTarificador tarificador,String porcentajeSumaAseguradaDT,String deducibleRC) throws ParseException{
		JSONObject retorno = new JSONObject();
		
		double comisionAgente=cotizacion.getPorcentajeComision();
		double valorPrimaPuraRT = 0.0;
		double valorPrimaPuraCHT = 0.0;
		double valorPrimaPuraRC = 0.0;
		double valorPrimaPuraDP = 0.0;
		double primaTasa = 0.0;
		double totalTodoRisgo=0.0;
		double totalDanioTotal=0.0;
		double totalResponsabilidadCivil=0.0;
		double sumaAseguradaValor=ov.getSumaAsegurada();
		double primaNoAfectaMonto=0.0;
		int dispositivoRastreo=ov.getDispositivoRastreo()?1:0;
		SucursalDAO sucursalDAO=new SucursalDAO();
		Sucursal sucursal=sucursalDAO.buscarPorId(ov.getSucursalId());
		Modelo modelo=ov.getModelo();
		int anioFabricacion = Integer.parseInt(ov.getAnioFabricacion());
			
				
		if(tarificador.getNumero().equalsIgnoreCase("1")){ // Tarificador antiguo
			

			if(coberturaTR.equalsIgnoreCase("true")){
				valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, anioFabricacion, dispositivoRastreo, modelo);
				valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
				valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal,(anioFabricacion), modelo);
				valorPrimaPuraDP = MotorTarifador.calcularPrimaDanoParcial(sumaAseguradaValor, sucursal, (anioFabricacion), modelo,Double.parseDouble(montoFijo),Double.parseDouble(valorSiniestro),Double.parseDouble(porcentajeSumaAsegurada));
				totalTodoRisgo += MotorTarifador.calcularPrimaTasaTodoRiesgo(valorPrimaPuraRT, valorPrimaPuraCHT, valorPrimaPuraRC, valorPrimaPuraDP, comisionAgente, sumaAseguradaValor);
				primaTasa+=totalTodoRisgo;
			}
			
			if(coberturaDT.equalsIgnoreCase("true")&&coberturaRC.equalsIgnoreCase("false")){					
				valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, (anioFabricacion), dispositivoRastreo, modelo);
				valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
				totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT, valorPrimaPuraCHT, comisionAgente, (sumaAseguradaValor));
				primaTasa+=totalDanioTotal;
				
			}
			
			if(coberturaRC.equalsIgnoreCase("true")&&coberturaDT.equalsIgnoreCase("false")){
				valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal, anioFabricacion, modelo);
				totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(valorPrimaPuraRC, comisionAgente, sumaAseguradaValor);
				primaTasa+=totalResponsabilidadCivil;
			}
			
			if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")){
				valorPrimaPuraRT = MotorTarifador.calcularPrimaRoboTotal(sumaAseguradaValor, sucursal, (anioFabricacion), dispositivoRastreo, modelo);
				valorPrimaPuraCHT = MotorTarifador.calcularPrimaChoqueTotal(sumaAseguradaValor, sucursal, modelo);
				totalDanioTotal += MotorTarifador.calcularPrimaTasaDanoTotal(valorPrimaPuraRT, valorPrimaPuraCHT, comisionAgente, sumaAseguradaValor);
				valorPrimaPuraRC = MotorTarifador.calcularPrimaResponsabilidadCivil(sumaAseguradaValor, sucursal, anioFabricacion, modelo);
				totalResponsabilidadCivil += MotorTarifador.calcularPrimaTasaResponsabilidadCivil(valorPrimaPuraRC, comisionAgente, sumaAseguradaValor);
				primaNoAfectaMonto+=totalResponsabilidadCivil;
				primaTasa+=totalDanioTotal;
			}
		}
		if(tarificador.getNumero().equalsIgnoreCase("2")){		
			JSONObject objetoResultado = new JSONObject();
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
			String comisionAgenteValor = String.valueOf(comisionAgente/100);
			String agenteId = cotizacion.getAgenteId().toString();
			VhTarificadorDAO vhTarificadorDAO = new VhTarificadorDAO();
			
					
			objetoResultado =vhTarificadorDAO.obtenerTasasTarificador2(ov,cliente,comisionAgenteValor,montoFijo,valorSiniestro,porcentajeSumaAsegurada,agenteId,porcentajeSumaAseguradaDT, deducibleRC);
						
			totalTodoRisgo = objetoResultado.getDouble("todoRiesgoValorSinImpuestos");
			totalDanioTotal = objetoResultado.getDouble("danoTotalValorSinImpuestos");
			totalResponsabilidadCivil = objetoResultado.getDouble("rcValorSinImpuestos");

						
			if(coberturaTR.equalsIgnoreCase("true")){				
				primaTasa+= totalTodoRisgo;
				totalDanioTotal=0;
				totalResponsabilidadCivil = 0;				
			}
			if(coberturaDT.equalsIgnoreCase("true")&&coberturaRC.equalsIgnoreCase("false")){
				primaTasa+=totalDanioTotal;
				totalTodoRisgo = 0;
				totalResponsabilidadCivil = 0;
			}
			if(coberturaRC.equalsIgnoreCase("true")&&coberturaDT.equalsIgnoreCase("false")){
				primaTasa+=totalResponsabilidadCivil;
				totalDanioTotal=0;
				totalTodoRisgo = 0;
			}
			if (coberturaDT.equalsIgnoreCase("true") && coberturaRC.equalsIgnoreCase("true")){
				primaNoAfectaMonto+=totalResponsabilidadCivil;
				primaTasa+=totalDanioTotal;
				totalTodoRisgo = 0;
			}
		}
		
		retorno.put("primaAfectaMonto", primaTasa);
		retorno.put("primaNoAfectaMonto", primaNoAfectaMonto);
		retorno.put("totalResponsabilidadCivil", totalResponsabilidadCivil);
		retorno.put("totalDanioTotal", totalDanioTotal);
		retorno.put("totalTodoRisgo", totalTodoRisgo);
		
		return retorno;
	}
	
	
	public double guardarCotizacionCoberturaPrincipales(CotizacionDetalle cd, double porcentajeSumaAsegurada, double montoFijo, double valorSiniestro, double totalResponsabilidadCivil, double totalDanioTotal, double totalTodoRisgo, double porcentajeConDescuento, double valorVehiculoTodosAnios){
		double totalPrimaDetalle=0.0;	
		CoberturaDAO cDAO=new CoberturaDAO();
		Cobertura todoRiesgo=new Cobertura();
		Cobertura perdidaTotal=new Cobertura();
		Cobertura responsabilidadCivil=new Cobertura();
		
		CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
		
		double sumaAseguradaSinExtras=cd.getSumaAseguradaItem()-cd.getValorExtras();
		
		if(cd.getTipoObjetoId().equals("1")||cd.getTipoObjetoId().equals("5")||cd.getTipoObjetoId().equals("4")||cd.getTipoObjetoId().equals("6"))//dinamicos
		{
			todoRiesgo=cDAO.buscarPorNemotecnico("TORI");
			perdidaTotal=cDAO.buscarPorNemotecnico("DATO");
			responsabilidadCivil=cDAO.buscarPorNemotecnico("RECI");
		}
		else{
			todoRiesgo=cDAO.buscarPorNemotecnico("TRCE");
			perdidaTotal=cDAO.buscarPorNemotecnico("DATO");
			responsabilidadCivil=cDAO.buscarPorNemotecnico("RECI");	
		}
		if(totalTodoRisgo>0)
		{
			double tasa=totalTodoRisgo/(cd.getSumaAseguradaItem()-cd.getValorExtras());
			double valor=tasa*valorVehiculoTodosAnios;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccTR=new CotizacionCobertura();
			ccTR.setValorPrimaOrigen(valor);
			ccTR.setValorPrima(valor*porcentajeConDescuento);
			ccTR.setCobertura(todoRiesgo);
			ccTR.setCotizacionDetalle(cd);
			ccTR.setMontoFijo(montoFijo);
			ccTR.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccTR.setPorcentajeValorSiniestro(valorSiniestro);
			ccTR.setValorMonto(sumaAseguradaSinExtras);
			ccTR=cotizacionCoberturaTransaction.crear(ccTR);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil<=0)
		{
			double tasa=totalDanioTotal/(sumaAseguradaSinExtras);
			double valor=tasa*valorVehiculoTodosAnios;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(valor);
			ccDT.setValorPrima(valor*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(cd.getSumaAseguradaItem()-cd.getValorExtras());
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil>0)
		{
			double valorMontoDT=((totalDanioTotal)/(totalDanioTotal+totalResponsabilidadCivil))*(sumaAseguradaSinExtras);
			double valorMontoRC=((totalResponsabilidadCivil)/(totalDanioTotal+totalResponsabilidadCivil))*(sumaAseguradaSinExtras);
			double tasaDT=(totalDanioTotal)/sumaAseguradaSinExtras;
			double tasaRC=(totalResponsabilidadCivil)/sumaAseguradaSinExtras;
			double valorDT=tasaDT*valorVehiculoTodosAnios;
			double valorRC=tasaRC*valorVehiculoTodosAnios;
			
			totalPrimaDetalle+=valorDT+valorRC;
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(valorDT);
			ccDT.setValorPrima(valorDT*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(valorMontoDT);
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
			
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(valorRC);
			ccRC.setValorPrima(valorRC*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(valorMontoRC);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}
		
		if(totalDanioTotal<=0&&totalResponsabilidadCivil>0)
		{
		
			double tasa=totalTodoRisgo/(sumaAseguradaSinExtras);
			double valor=tasa*valorVehiculoTodosAnios;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(valor);
			ccRC.setValorPrima(valor*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(sumaAseguradaSinExtras);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}


		return totalPrimaDetalle;
	}
	
	
	public double guardarCotizacionCoberturaPrincipalesDinamicos(
			CotizacionDetalle cd, double porcentajeSumaAsegurada, double montoFijo, double valorSiniestro, double totalTodoRisgo,
			double totalDanioTotal, double totalResponsabilidadCivil, double porcentajeConDescuento, double primaTotalPrincipales,
			double valorTotalCascoPrimerAnio) {
		
		CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
	double totalPrimaDetalle=0.0;	
		CoberturaDAO cDAO=new CoberturaDAO();
		Cobertura todoRiesgo=new Cobertura();
		Cobertura perdidaTotal=new Cobertura();
		Cobertura responsabilidadCivil=new Cobertura();
		
		todoRiesgo=cDAO.buscarPorNemotecnico("TORI");
		perdidaTotal=cDAO.buscarPorNemotecnico("DATO");
		responsabilidadCivil=cDAO.buscarPorNemotecnico("RECI");
		
		if(totalTodoRisgo>0)
		{
			double valor=primaTotalPrincipales;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccTR=new CotizacionCobertura();
			ccTR.setValorPrimaOrigen(valor);
			ccTR.setValorPrima(valor*porcentajeConDescuento);
			ccTR.setCobertura(todoRiesgo);
			ccTR.setCotizacionDetalle(cd);
			ccTR.setMontoFijo(montoFijo);
			ccTR.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccTR.setPorcentajeValorSiniestro(valorSiniestro);
			ccTR.setValorMonto(valorTotalCascoPrimerAnio);
			ccTR=cotizacionCoberturaTransaction.crear(ccTR);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil<=0)
		{
			double valor=primaTotalPrincipales;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(valor);
			ccDT.setValorPrima(valor*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(valorTotalCascoPrimerAnio);
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil>0)
		{
			//double valorMontoDT=((totalDanioTotal)/(totalDanioTotal+totalResponsabilidadCivil))*(valorTotalCascoPrimerAnio);
			//double valorMontoRC=((totalResponsabilidadCivil)/(totalDanioTotal+totalResponsabilidadCivil))*(valorTotalCascoPrimerAnio);
			
			double valorMontoDT=valorTotalCascoPrimerAnio;
			double valorMontoRC=valorTotalCascoPrimerAnio;
			
			double porcentajeDT=(totalDanioTotal)/(totalDanioTotal+totalResponsabilidadCivil);
			double porcentajeRC=(totalResponsabilidadCivil)/(totalDanioTotal+totalResponsabilidadCivil);
			double primaDT=porcentajeDT*primaTotalPrincipales;
			double primaRC=porcentajeRC*primaTotalPrincipales;
			
			totalPrimaDetalle+=primaTotalPrincipales;
			
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(primaDT);
			ccDT.setValorPrima(primaDT*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(valorMontoDT);
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
			
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(primaRC);
			ccRC.setValorPrima(primaRC*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(valorMontoRC);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}
		
		if(totalDanioTotal<=0&&totalResponsabilidadCivil>0)
		{
			double valor=primaTotalPrincipales;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(valor);
			ccRC.setValorPrima(valor*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(valorTotalCascoPrimerAnio);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}


		return totalPrimaDetalle;
	}

	
	public double guardarCotizacionCoberturaPrincipalesCerrados(
			CotizacionDetalle cd, double porcentajeSumaAsegurada, double montoFijo, double valorSiniestro, double totalTodoRisgo,
			double totalDanioTotal, double totalResponsabilidadCivil, double porcentajeConDescuento, double primaTotalPrincipales,
			double valorTotalCascoPrimerAnio) {
		
		CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
		double totalPrimaDetalle=0.0;	
		CoberturaDAO cDAO=new CoberturaDAO();
		Cobertura todoRiesgo=new Cobertura();
		Cobertura perdidaTotal=new Cobertura();
		Cobertura responsabilidadCivil=new Cobertura();
		
		if(cd.getTipoObjetoId().equals("1")||cd.getTipoObjetoId().equals("5")||cd.getTipoObjetoId().equals("4")||cd.getTipoObjetoId().equals("6"))//dinamicos
			todoRiesgo = cDAO.buscarPorNemotecnico("TORI");
		else
			todoRiesgo = cDAO.buscarPorNemotecnico("TRCE");
		
		perdidaTotal=cDAO.buscarPorNemotecnico("DATO");
		responsabilidadCivil=cDAO.buscarPorNemotecnico("RECI");
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();
		
		if(totalTodoRisgo>0)
		{
			double valor=primaTotalPrincipales;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccTR=new CotizacionCobertura();
			ccTR.setValorPrimaOrigen(valor);
			ccTR.setValorPrima(valor*porcentajeConDescuento);
			ccTR.setCobertura(todoRiesgo);
			ccTR.setCotizacionDetalle(cd);
						    	
	        if(montoFijo==0){	
	        	ccTR.setMontoFijo(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_MONTO_FIJO").getValor()));
			}else{
				ccTR.setMontoFijo(montoFijo);
			}	        
	        if(porcentajeSumaAsegurada==0){	
	        	ccTR.setPorcentajeSumaAsegurada(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SUMA_ASEGURADA").getValor())/100);
			}else{
				ccTR.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			}	        	        
	        if(valorSiniestro==0){	
	        	ccTR.setPorcentajeValorSiniestro(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SINIESTRO").getValor())/100);
			}else{
				ccTR.setPorcentajeValorSiniestro(valorSiniestro);
			}	        
			ccTR.setValorMonto(valorTotalCascoPrimerAnio);
			ccTR=cotizacionCoberturaTransaction.crear(ccTR);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil<=0)
		{
			double valor=valorTotalCascoPrimerAnio;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(valor);
			ccDT.setValorPrima(valor*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			
			if(porcentajeSumaAsegurada==0){	
				ccDT.setPorcentajeSumaAsegurada(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SUMA_ASEGURADA").getValor())/100);
			}else{
				ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			}	        	        
			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(valorTotalCascoPrimerAnio);
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
		}
		
		if(totalDanioTotal>0&&totalResponsabilidadCivil>0)
		{
			double valorMontoDT=((totalDanioTotal)/(totalDanioTotal+totalResponsabilidadCivil))*(valorTotalCascoPrimerAnio);
			double valorMontoRC=((totalResponsabilidadCivil)/(totalDanioTotal+totalResponsabilidadCivil))*(valorTotalCascoPrimerAnio);
			double porcentajeDT=(totalDanioTotal)/(totalDanioTotal+totalResponsabilidadCivil);
			double porcentajeRC=(totalResponsabilidadCivil)/(totalDanioTotal+totalResponsabilidadCivil);
			double primaDT=porcentajeDT*primaTotalPrincipales;
			double primaRC=porcentajeRC*primaTotalPrincipales;
			
			totalPrimaDetalle+=primaTotalPrincipales;
			
			CotizacionCobertura ccDT=new CotizacionCobertura();
			ccDT.setValorPrimaOrigen(primaDT);
			ccDT.setValorPrima(primaDT*porcentajeConDescuento);
			ccDT.setCobertura(perdidaTotal);
			ccDT.setCotizacionDetalle(cd);
			ccDT.setMontoFijo(0);
			
			if(porcentajeSumaAsegurada==0){	
				ccDT.setPorcentajeSumaAsegurada(Double.parseDouble(variableDAO.buscarPorNombre("DANO_PARCIAL_PORCENTAJE_SUMA_ASEGURADA").getValor())/100);
			}else{
				ccDT.setPorcentajeSumaAsegurada(porcentajeSumaAsegurada);
			}	   

			ccDT.setPorcentajeValorSiniestro(0);
			ccDT.setValorMonto(valorMontoDT);
			ccDT=cotizacionCoberturaTransaction.crear(ccDT);
			
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(primaRC);
			ccRC.setValorPrima(primaRC*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(valorMontoRC);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}
		
		if(totalDanioTotal<=0&&totalResponsabilidadCivil>0)
		{
			double valor=primaTotalPrincipales;
			totalPrimaDetalle+=valor;
			CotizacionCobertura ccRC=new CotizacionCobertura();
			ccRC.setValorPrimaOrigen(valor);
			ccRC.setValorPrima(valor*porcentajeConDescuento);
			ccRC.setCobertura(responsabilidadCivil);
			ccRC.setCotizacionDetalle(cd);
			ccRC.setMontoFijo(montoFijo);
			ccRC.setPorcentajeSumaAsegurada(0);
			ccRC.setPorcentajeValorSiniestro(0);
			ccRC.setValorMonto(valorTotalCascoPrimerAnio);
			ccRC=cotizacionCoberturaTransaction.crear(ccRC);
		}


		return totalPrimaDetalle;
	}
	
	public double guardarCotizacionCoberturaExtras(CotizacionDetalle cd, double tasa, double valorExtrasTodosAnios, double valorExtrasPrimerAnio, double porcentajeConDescuento, double numeroDiasVigencia,VhTarificador tarificador){
		double totalPrimaExtras=0.0;
		CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
		VigenciaPoliza vp=cd.getCotizacion().getVigenciaPoliza();
		CoberturaDAO cDAO=new CoberturaDAO();
		Cobertura coberturaExtras=new Cobertura();
		if(cd.getTipoObjetoId().equals("1")||cd.getTipoObjetoId().equals("5")||cd.getTipoObjetoId().equals("4")||cd.getTipoObjetoId().equals("6"))//dinamicos
		{
			coberturaExtras=cDAO.buscarPorNemotecnico("AMAC");
		}
		else{
			coberturaExtras=cDAO.buscarPorNemotecnico("AMCE");
		}
		if(valorExtrasTodosAnios>0)
		{
			int anios=vp.getValor().intValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(cd.getCotizacion().getVigenciaDesde());
			
			
			double auxDepreciacion=valorExtrasPrimerAnio;
			double primaTotalSinImpuestos=0;
			for (int i = 1; i <= anios; i++) {
				Date fechaAnterior=calendar.getTime();
				calendar.add(Calendar.YEAR, 1);
				int numeroDias=Days.daysBetween(new DateTime(fechaAnterior), new DateTime(calendar.getTime())).getDays();
				
				double valorDepreciado=0;
				
				if (i == 1) {
					valorDepreciado = auxDepreciacion;
				} else if (i > 1) {
					if (i == 2){
						if(tarificador.getNumero().equalsIgnoreCase("1")){ // Tarificador 1
							valorDepreciado = auxDepreciacion - auxDepreciacion * 0.15;
						}
						if(tarificador.getNumero().equalsIgnoreCase("2")){ // Tarificador 2
							valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;
						}												
					}						
					else
						valorDepreciado = auxDepreciacion - auxDepreciacion * 0.10;

					auxDepreciacion = valorDepreciado;
				}
				primaTotalSinImpuestos+=(auxDepreciacion*tasa*numeroDias)/365;
			}
			
			totalPrimaExtras=primaTotalSinImpuestos;

		//	totalPrimaExtras+=tasa*valorExtrasTodosAnios;
		//	totalPrimaExtras=(totalPrimaExtras/(vp.getValor().doubleValue()*365.0))*numeroDiasVigencia;

			CotizacionCobertura ccTR=new CotizacionCobertura();
			ccTR.setValorPrimaOrigen(totalPrimaExtras);
			ccTR.setValorPrima(totalPrimaExtras*porcentajeConDescuento);
			ccTR.setCobertura(coberturaExtras);
			ccTR.setCotizacionDetalle(cd);
			ccTR.setMontoFijo(0);
			ccTR.setPorcentajeSumaAsegurada(0);
			ccTR.setPorcentajeValorSiniestro(0);
			ccTR.setValorMonto(valorExtrasPrimerAnio);
			ccTR=cotizacionCoberturaTransaction.crear(ccTR);
		}

		return totalPrimaExtras;
	}
	
	public double depreciarValor(double valor, int anios, VhTarificador tarificador){
		double valorExtraDepreciado = valor;
		double valorExtraTiempoVigencia = 0;
		
		for (int i = 1; i <= anios; i++) {
			if (i == 1) {
				valorExtraTiempoVigencia += valorExtraDepreciado;
			} else if (i > 1) {
				if (i == 2){
					if(tarificador.getNumero().equalsIgnoreCase("1")){ // Tarificador 1
						valorExtraDepreciado = valorExtraDepreciado - valorExtraDepreciado * 0.15;
					}
					if(tarificador.getNumero().equalsIgnoreCase("2")){ // Tarificador 2
						valorExtraDepreciado = valorExtraDepreciado - valorExtraDepreciado * 0.10;
					}
				}else
					valorExtraDepreciado = valorExtraDepreciado - valorExtraDepreciado * 0.10;

				valorExtraTiempoVigencia += valorExtraDepreciado;
			}
		}
		return valorExtraTiempoVigencia;
	}
	
	public double guardarCotizacionCoberturasAdicionales(CotizacionDetalle cd, String coberturasAdicionalesStr, double valorTotalCascoPrimerAnio, double valorTotalCascoTodosAnios, double porcentajeConDescuento, double valorExcesoRC){
		double primaAcumuladaAdicionales=0.0;
		CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
		CoberturaDAO coberturaDAO=new CoberturaDAO();
		String [] coberturasAdicionales = coberturasAdicionalesStr.split(",");
		int vigenciaCotizacion=cd.getCotizacion().getVigenciaPoliza().getValor().intValue();
		
		if(coberturasAdicionales.length >= 1&&!coberturasAdicionalesStr.equals(""))
			for(int i=0;i<coberturasAdicionales.length;i++){
				CotizacionCobertura ccAdicional=new CotizacionCobertura();
				Cobertura adicional = coberturaDAO.buscarPorId(coberturasAdicionales[i]);
				boolean coberturaOtrosIva = coberturaDAO.verificarCoberturaOtrosConIva(coberturasAdicionales[i]);
				boolean coberturaOtrosSinIva = coberturaDAO.verificarCoberturaOtrosSinIva(coberturasAdicionales[i]);
				if(!adicional.getId().toString().equals("6348540415022")&&!adicional.getId().toString().equals("6349173767914")){
				if (adicional.getTipoTasa().getId().equals("1")) {
					ccAdicional.setCobertura(adicional);
					ccAdicional.setCotizacionDetalle(cd);
					ccAdicional.setMontoFijo(0);
					ccAdicional.setPorcentajeSumaAsegurada(0);
					ccAdicional.setPorcentajeValorSiniestro(0);
					if(coberturaOtrosIva || coberturaOtrosSinIva)
					{
						ccAdicional.setValorPrimaOrigen(0);
						if(coberturaOtrosIva)
						{
							ccAdicional.setValorPrimaOtrosIva(vigenciaCotizacion*adicional.getTasaValor());
						}
						else
						{
							ccAdicional.setValorPrimaOtros(vigenciaCotizacion*adicional.getTasaValor());
						}
						ccAdicional.setValorPrima(0);
					}
					else
					{
						ccAdicional.setValorPrimaOrigen(vigenciaCotizacion*adicional.getTasaValor());
						ccAdicional.setValorPrimaOtros(0);
						ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento);
						primaAcumuladaAdicionales+=	ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento;	
					}
						
					
					ccAdicional=cotizacionCoberturaTransaction.crear(ccAdicional);
					//cotizacionCoberturaDAO.crear(ccAdicional);
					//primaTasa += adicional.getTasaValor();
				}
				if (adicional.getTipoTasa().getId().equals("2")) {
					ccAdicional.setCobertura(adicional);
					ccAdicional.setCotizacionDetalle(cd);
					ccAdicional.setMontoFijo(0);
					ccAdicional.setPorcentajeSumaAsegurada(0);
					ccAdicional.setPorcentajeValorSiniestro(0);
					if(coberturaOtrosIva || coberturaOtrosSinIva)
					{
						ccAdicional.setValorPrimaOrigen(0);
						if(coberturaOtrosIva)
						{
							ccAdicional.setValorPrimaOtrosIva(vigenciaCotizacion*adicional.getTasaValor());
						}
						else
						{
							ccAdicional.setValorPrimaOtros(vigenciaCotizacion*adicional.getTasaValor());
						}
						ccAdicional.setValorPrima(0);
					}
					else
					{
						ccAdicional.setValorPrimaOrigen(vigenciaCotizacion*adicional.getTasaValor()* (valorTotalCascoPrimerAnio)/100);
						ccAdicional.setValorPrimaOtros(0);
						ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento);
						primaAcumuladaAdicionales+=	ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento;	
					}
					
					ccAdicional=cotizacionCoberturaTransaction.crear(ccAdicional);
					//cotizacionCoberturaDAO.crear(ccAdicional);
					//primaTasa += (adicional.getTasaValor() * vehiculo.getSumaAsegurada()/100);
				}
				}
				
				else{
					ccAdicional.setCobertura(adicional);
					ccAdicional.setCotizacionDetalle(cd);
					ccAdicional.setMontoFijo(0);
					ccAdicional.setPorcentajeSumaAsegurada(0);
					ccAdicional.setPorcentajeValorSiniestro(0);
					ccAdicional.setValorMonto(valorExcesoRC);
					if(coberturaOtrosIva || coberturaOtrosSinIva)
					{
						ccAdicional.setValorPrimaOrigen(0);
						if(coberturaOtrosIva)
						{
							ccAdicional.setValorPrimaOtrosIva(vigenciaCotizacion*adicional.getTasaValor());
						}
						else
						{
							ccAdicional.setValorPrimaOtros(vigenciaCotizacion*adicional.getTasaValor());
						}
						ccAdicional.setValorPrima(0);
					}
					else
					{
						ccAdicional.setValorPrimaOrigen(vigenciaCotizacion*adicional.getTasaValor()*valorExcesoRC/100);
						ccAdicional.setValorPrimaOtros(0);
						ccAdicional.setValorPrima(ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento);
						primaAcumuladaAdicionales+=	ccAdicional.getValorPrimaOrigen()*porcentajeConDescuento;	
					}	
										
					ccAdicional=cotizacionCoberturaTransaction.crear(ccAdicional);
					//cotizacionCoberturaDAO.crear(ccAdicional);
					//primaTasa += adicional.getTasaValor();
				}
			}
		
		return primaAcumuladaAdicionales;
	}
	
	
	public double actualizarValoresCotizacionDetalle(CotizacionDetalle cotizacionDetalle , double porcentajeConDescuento, Paquete paquete, double tasaCasco){
		CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
		double primaAcumuladaTotal=0.0;
		CotizacionCoberturaDAO ccDAO= new CotizacionCoberturaDAO();
		List<CotizacionCobertura> listaCoberturas = ccDAO.buscarPorCotizacionDetalle(cotizacionDetalle);
		
		for(int i=0;i<listaCoberturas.size();i++){
			CotizacionCobertura cc=listaCoberturas.get(i);
			primaAcumuladaTotal+=cc.getValorPrimaOrigen();
		}
		
		cotizacionDetalle.setPrimaNetaItemOrigen(primaAcumuladaTotal);
		cotizacionDetalle.setPrimaNetaItem(primaAcumuladaTotal*porcentajeConDescuento);
		cotizacionDetalle.setTasaOrigen(tasaCasco);
		cotizacionDetalle.setTasa(tasaCasco);
		cotizacionDetalle = cotizacionDetalleTransaction.editar(cotizacionDetalle);
		
		return primaAcumuladaTotal;
	}	
	
	
	public int calcularDiasVigenciaCotizacion(Cotizacion cotizacion){
		int numeroDias=0;
		
		VigenciaPoliza vp=cotizacion.getVigenciaPoliza();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cotizacion.getVigenciaDesde());
		calendar.add(Calendar.YEAR, vp.getValor().intValue());
		numeroDias=Days.daysBetween(new DateTime(cotizacion.getVigenciaDesde()), new DateTime(calendar.getTime())).getDays();
		
		return numeroDias;
	}
		
	
}