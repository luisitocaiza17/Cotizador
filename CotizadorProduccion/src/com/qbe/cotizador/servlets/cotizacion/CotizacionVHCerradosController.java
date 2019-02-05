package com.qbe.cotizador.servlets.cotizacion;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.qbe.cotizador.dao.cotizacion.PrecioReferencialDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.TipoInspectorDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.PaqueteDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Extra;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Paquete;
import com.qbe.cotizador.model.PrecioReferencial;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TasaProducto;
import com.qbe.cotizador.model.TipoInspector;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoUso;
import com.qbe.cotizador.model.TipoVehiculo;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.transaction.cotizacion.CotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ExtraTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ObjetoVehiculoTransaction;
import com.qbe.cotizador.util.Reportes;
import com.qbe.cotizador.util.Utilitarios;


/**
 * Servlet implementation class CotizacionVHCerradosController
 */
@WebServlet("/CotizacionVHCerradosController")
public class CotizacionVHCerradosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionVHCerradosController() {

        super();
        // TODO Auto-generated constructor stub
    }

	/**
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
			
			EntidadTransaction entidadTransaction = new EntidadTransaction();
			ClienteTransaction clienteTransaction = new ClienteTransaction();
			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
			ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
			ExtraTransaction extraTransaction = new ExtraTransaction();
			CotizacionCoberturaTransaction cotizacionCoberturaTransaction = new CotizacionCoberturaTransaction();
			
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			JSONObject cotizacionJSONObject = new JSONObject();
			JSONArray cotizacionJSONArray = new JSONArray();
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();

			// Crear la cotizacion - vehiculos cerrados
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
				String pxpv = request.getParameter("productoXPuntoDeVenta") == null ? "" : request.getParameter("productoXPuntoDeVenta").trim();
				
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
					
					if(!tipoIdentificacion.equals(""))
						entidad.setTipoIdentificacion(tipoDAO.buscarPorId(tipoIdentificacion));
					
					entidad.setMail(mail);
					
					if(tipoDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")){
						entidad.setNombres("");
						entidad.setApellidos("");
						entidad.setNombreCompleto(nombreCompleto.toUpperCase());
					}else{
						entidad.setNombres(nombres.toUpperCase());
						entidad.setApellidos(apellidos.toUpperCase());
						entidad.setNombreCompleto(nombres.toUpperCase() + " " + apellidos.toUpperCase());
					}
					
					if(entidad.getId()==null)
						entidad=entidadTransaction.crear(entidad);
					else
						entidad=entidadTransaction.editar(entidad);
				
				cliente=clienteDAO.buscarPorEntidadId(entidad);
				
				if(cliente.getId() ==null){
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
				
				cotizacion.setClienteId(BigInteger.valueOf(Long.valueOf(cliente.getId())));
								
				String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
				String grupoProductoId = request.getParameter("grupoProductoId") == null ? "" : request.getParameter("grupoProductoId");
				String tasaProductoId = request.getParameter("tasaProductoId") == null ? "" : request.getParameter("tasaProductoId");
				String tasaProductoValor = request.getParameter("tasaProductoValor") == null ? "" : request.getParameter("tasaProductoValor");
				
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				GrupoPorProducto grupoPorProducto = new GrupoPorProducto();
				Producto productoDefectoVH = new Producto();	
				TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
				
				cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoProductoId)));
				cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProductoId)));
				
				grupoPorProducto =  grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
				productoDefectoVH = grupoPorProducto.getProducto();	
				
				cotizacion.setProducto(productoDefectoVH);									
				
				
				if(tasaProductoId.length() == 0 && !tasaProductoValor.equals("")) {
					cotizacion.setTasaProductoId(new BigInteger("0"));
					cotizacion.setTasaProductoValor(new Double(tasaProductoValor));					
				}else{
					cotizacion.setTasaProductoId(BigInteger.valueOf(Long.valueOf(tasaProductoId)));					
					TasaProducto tasaProducto = tasaProductoDAO.buscarPorId(tasaProductoId);
					cotizacion.setTasaProductoValor(tasaProducto.getPorcentajeCasco());
				}
					
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));
				
				// Grabamos los diferentes tipos de objetos de vehiculos cerrados
				if(tipoObjeto.equalsIgnoreCase("Livianos")){
					cotizacion.setTipoObjeto(toDAO.buscarPorNombre("Vehiculos Cerrados Livianos"));
				}
				if(tipoObjeto.equalsIgnoreCase("Motos")){
					cotizacion.setTipoObjeto(toDAO.buscarPorNombre("Vehiculos Cerrados Motos"));
				}
				if(tipoObjeto.equalsIgnoreCase("Pesados")){
					cotizacion.setTipoObjeto(toDAO.buscarPorNombre("Vehiculos Cerrados Pesados"));
				}
				if(tipoObjeto.equalsIgnoreCase("Publicos")){
					cotizacion.setTipoObjeto(toDAO.buscarPorNombre("Vehículos Cerrados Publicos"));
				}
				
				
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
				result.put("cotizacionId",cotizacion.getId());

			}
			
						
			// Metodo agregar un vehiculo a la cotizacionayanez
			if(tipoConsulta.equalsIgnoreCase("agregarVehiculoCotizacion") && producto.equalsIgnoreCase("productoVehiculo"))
			{			
				String codigoVehiculoEnsurance = request.getParameter("codigoVehiculoEnsurance")  == null ? "" : request.getParameter("codigoVehiculoEnsurance");
				String placa = request.getParameter("placa")  == null ? "" : request.getParameter("placa");
				String chasis = request.getParameter("chasis")  == null ? "" : request.getParameter("chasis");
				String motor = request.getParameter("motor")  == null ? "" : request.getParameter("motor");
				String vehiculoId = request.getParameter("vehiculoId")  == null ? "" : request.getParameter("vehiculoId");
				String modeloNombre = request.getParameter("modelo_nombre") == null ? "" : request.getParameter("modelo_nombre");
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
				String necesitaInspeccion = request.getParameter("necesitaInspeccion") == null ? "" : request.getParameter("necesitaInspeccion");
				
				ModeloDAO modeloDAO = new ModeloDAO();				
				Modelo modelo = modeloDAO.buscarPorNombre(modeloNombre);
				
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
				vehiculo.setKilometrosRecorridos(kmRecorridos);
				vehiculo.setCombustible(combustible);
				vehiculo.setSucursalId(sucursal.getId());
				vehiculo.setTipoUso(tipoUso);
				
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
				WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				int anoSinSiniestro = webService.getWebServiceCotizadorImplPort().obtenerNumeroReclamosPorPlaca(request.getParameter("placa"));
				vehiculo.setAnosSin_Siniestro(String.valueOf(anoSinSiniestro));
				//vehiculo.setAnosSin_Siniestro(String.valueOf("0"));

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
				
				String[] arrListaExtrasIds = listaExtrasIds.split(","); 
				String[] arrListaExtrasDetalles = listaExtrasDetalles.split(","); 
				String[] arrListaExtrasValores = listaExtrasValores.split(","); 
				
				
				if(!cotizacionId.equals("")){
					cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
					//evaldez cambiamos la etapa de la cotizacion
					if(cotizacion.getEtapaWizard()<2){
						cotizacion.setEtapaWizard(2);
						cotizacion=cotizacionTransaction.editar(cotizacion);
					}
				}
				
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoVehiculo = new TipoObjeto();
				
				// Grabamos los diferentes tipos de objetos de vehiculos cerrados
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
				
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
				CotizacionDetalle cotizacionDetalleExiste = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(vehiculoId, cotizacion);
				if (cotizacionDetalleExiste.getId() != null ){
					cotizacionDetalle = cotizacionDetalleExiste;
				}else{
					cotizacionDetalle.setCotizacion(cotizacion);
					cotizacionDetalle.setTipoObjetoId(tipoObjetoVehiculo.getId());
					cotizacionDetalle.setObjetoId(vehiculo.getId());
					cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
				}
					
				// Asignación de la coberturas del vehiculo
				String coberturaTR = request.getParameter("coberturaTR_check");				

				Double tasa = 0.0;
				Double sumaAseguradaTotalExtras=0.0;
				Double primaTotalExtras=0.0;
				
				/**
				 * C A L C U L O   T O T A L   D E   E X T R A S 
				 */
				//eliminar extras antiguos
				List<Extra> extrasAntiguos=vehiculo.getExtras();
				if(extrasAntiguos!=null&&extrasAntiguos.size()>0){
					for(int i=0;i<extrasAntiguos.size();i++){
						extraTransaction.eliminar(extrasAntiguos.get(i));
					}
				}
				
				//se agregan extras nuevos
				for(int i=1; i< arrListaExtrasIds.length; i++){
					extra.setTipoExtra(tipoExtraDAO.buscarPorId(arrListaExtrasIds[i]));
					extra.setObjetoVehiculo(vehiculo);
					extra.setDescripcion(arrListaExtrasDetalles[i]);
					extra.setValorAsegurado(Double.valueOf(arrListaExtrasValores[i]));
					primaTotalExtras+=Double.valueOf(arrListaExtrasValores[i])*tasa;
					sumaAseguradaTotalExtras+=Double.valueOf(arrListaExtrasValores[i]);
					extraTransaction.crear(extra);
				}
				
				sumaAseguradaValor = sumaAseguradaValor + sumaAseguradaTotalExtras;
				vehiculo.setSumaAsegurada(sumaAseguradaValor);
				
				VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();				
				Double primaTasa = 0.0;
				
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();	
				GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion.getGrupoPorProductoId().toString());

				// Cuando el grupo por producto tiene tasa fija
				if(grupoPorProducto.getTasaFija()){
					primaTasa = (grupoPorProducto.getPorcentajeTasaFija()*sumaAseguradaValor)/100;				

				}else{
					// Cuando el grupo de producto tiene taza formulada
					TasaProductoDAO tasaProductoDAO = new TasaProductoDAO();
					TasaProducto tasaProducto = new TasaProducto();
					
					tasaProducto = tasaProductoDAO.buscarPorId(cotizacion.getTasaProductoId().toString());
										
					primaTasa = (tasaProducto.getPorcentajeCasco()*sumaAseguradaValor)/100;
					
					// porcentajeExtras = Double.parseDouble(resultado.get(key).toString());
					
				}
					
				tasa=primaTasa/vehiculo.getSumaAsegurada();
				
				//tasa minima 
				
				Double tasaMinima = Double.parseDouble(variableSistemaDAO.buscarPorNombre("TASA_MINIMA").getValor());
				
				if(tasa<tasaMinima)	{			
					tasa=tasaMinima;
					primaTasa = vehiculo.getSumaAsegurada() * tasa;
				}

				//primaTasa se guardan las primas con la que se calcula la tasa
				//primaOtrosAnios se guarda la prima perteneciente a los años de vigencia mayores a 1
				//primaAfectaMonto se guarda el total de la prima de las coberturas que afectan al monto
				//prima total se guarda la prima total de la cotizacion
				
				double primaOtrosAnios=0;
				double primaAfectaMonto=primaTasa;
				double primaTotal=0;
				double primaNoAfectaMonto=0;
				
				String[] arrValoresAsegurados = sumaAseguradaArr.split("[|]",0);
				for(int i=1; i<arrValoresAsegurados.length; i++){
					Double valorAseguradoDepreciado = new Double(arrValoresAsegurados[i]);
					primaOtrosAnios+=valorAseguradoDepreciado * tasa;
				}
				
				CotizacionCoberturaDAO cotizacionCoberturaDAO = new CotizacionCoberturaDAO();
				CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
				cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
				
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
				if(sumaAseguradaTotalExtras>0){
					cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					Cobertura coberturaAAObjeto = new Cobertura();//amparo accesorios

					// Grabar la cobertura de amparo de accesorios
					if(tipoObjeto.equalsIgnoreCase("Livianos")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMAC");
					}
					if(tipoObjeto.equalsIgnoreCase("Motos")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMAC");
					}
					if(tipoObjeto.equalsIgnoreCase("Pesados")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMCE");
					}
					if(tipoObjeto.equalsIgnoreCase("Publicos")){
						coberturaAAObjeto = coberturaDAO.buscarPorNemotecnico("AMAC");
					}	
					
					cotizacionCobertura.setCobertura(coberturaAAObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(0);
					cotizacionCobertura.setMontoFijo(0);
					cotizacionCobertura.setValorMonto(sumaAseguradaTotalExtras);
					cotizacionCobertura.setPorcentajeValorSiniestro(0);
					cotizacionCobertura.setValorPrima(primaTotalExtras);

					cotizacionCoberturaTransaction.crear(cotizacionCobertura);
				}
				
				//SE GUARDA EN LA BASE, EL MONTO SIEMPRE ES EL VALOR DE LOS EXTRAS
				/*******************************************FIN COBERTURA EXTRAS****************************/
					

				/*******************************************INICIO COBERTURAS PRINCIPALES****************************/

				if (coberturaTR.equalsIgnoreCase("true")){
					cotizacionCobertura = new CotizacionCobertura();
					cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
					
					
					
					Cobertura coberturaTRObjeto = new Cobertura();
					// Grabar la cobertura de todo riesgo segun el tipo de vehiculo
					if(tipoObjeto.equalsIgnoreCase("Livianos")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TORI");
					}
					if(tipoObjeto.equalsIgnoreCase("Motos")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TORI");
					}
					if(tipoObjeto.equalsIgnoreCase("Pesados")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TRCE");
					}
					if(tipoObjeto.equalsIgnoreCase("Publicos")){
						coberturaTRObjeto = coberturaDAO.buscarPorNemotecnico("TORI");
					}	
					
					
					cotizacionCobertura.setCobertura(coberturaTRObjeto);
					cotizacionCobertura.setPorcentajeSumaAsegurada(Double.parseDouble("0"));
					cotizacionCobertura.setMontoFijo(Double.parseDouble("0"));
					cotizacionCobertura.setPorcentajeValorSiniestro(Double.parseDouble("0"));
					//CUANTO TIENE MÁS DE UN ANIO SE SUMA LA PRIMA DE OTROS AÑOS
					cotizacionCobertura.setValorPrima((primaAfectaMonto+primaOtrosAnios)*primaTasa/primaTasa);
					//cotizacionCobertura.setValorMonto((sumaAseguradaValor)*((totalTodoRisgo)/primaTasa));
					listaCoberturas.add(cotizacionCobertura);
					//cotizacionCoberturaDAO.crear(cotizacionCobertura);
				}				
							
				//coberturas adicionales evaldez
				String [] coberturasAdicionales = coberturasAdicionalesStr.split(",");
				

				if(coberturasAdicionales.length > 0 && !coberturasAdicionales[0].equals(""))
					for(int i=0;i<coberturasAdicionales.length;i++){
						CotizacionCobertura ccAdicional=new CotizacionCobertura();
						Cobertura adicional = coberturaDAO.buscarPorId(coberturasAdicionales[i]);
						
						if (adicional.getTipoTasa().getId().equals("1")) {
							ccAdicional.setCobertura(adicional);
							ccAdicional.setCotizacionDetalle(cotizacionDetalle);
							ccAdicional.setMontoFijo(adicional.getTasaValor());
							ccAdicional.setPorcentajeSumaAsegurada(0);
							ccAdicional.setPorcentajeValorSiniestro(0);
							ccAdicional.setValorPrima(adicional.getTasaValor());
							if(adicional.getAfectaValorAsegurado().equals("1"))
								primaAfectaMonto+=adicional.getTasaValor();
							else
								primaNoAfectaMonto+=adicional.getTasaValor();
							
							listaCoberturas.add(ccAdicional);
							//cotizacionCoberturaDAO.crear(ccAdicional);
							primaTasa += adicional.getTasaValor();
						}
						if (adicional.getTipoTasa().getId().equals("2")) {
							ccAdicional.setCobertura(adicional);
							ccAdicional.setCotizacionDetalle(cotizacionDetalle);
							ccAdicional.setMontoFijo(adicional.getTasaValor());
							ccAdicional.setPorcentajeSumaAsegurada(0);
							ccAdicional.setPorcentajeValorSiniestro(0);
							ccAdicional.setValorPrima(adicional.getTasaValor()* vehiculo.getSumaAsegurada()/100);
							if(adicional.getAfectaValorAsegurado().equals("1"))
								primaAfectaMonto+=adicional.getTasaValor()* vehiculo.getSumaAsegurada()/100;
							else
								primaNoAfectaMonto+=adicional.getTasaValor()* vehiculo.getSumaAsegurada()/100;
							listaCoberturas.add(ccAdicional);
							//cotizacionCoberturaDAO.crear(ccAdicional);
							//primaTasa += (adicional.getTasaValor() * vehiculo.getSumaAsegurada()/100);
						}
					}
				
				/*******************************************FIN COBERTURAS ADICIONALES****************************/

				for(int i=0;i<listaCoberturas.size();i++){
					CotizacionCobertura ccAGrabar=listaCoberturas.get(i);
					if(ccAGrabar.getCobertura().getAfectaValorAsegurado().equals("1"))
						//ccAGrabar.setValorMonto((sumaAseguradaTotal)*ccAGrabar.getValorPrima()/totalPrimaAfectaMonto);
						ccAGrabar.setValorMonto((sumaAseguradaValor)*(ccAGrabar.getValorPrima()/(primaAfectaMonto+primaOtrosAnios)));
					else
						ccAGrabar.setValorMonto(0);
					//ccAGrabar.setValorPrima(primaAfectaMonto*ccAGrabar.getValorPrima()/primaAfectaMonto);
					cotizacionCoberturaTransaction.crear(ccAGrabar);
				}


				primaTotal = primaAfectaMonto+primaOtrosAnios+primaNoAfectaMonto+primaTotalExtras;
				tasa = primaTotal / vehiculo.getSumaAsegurada();
				
				cotizacionDetalle.setPrimaNetaItem(primaTotal);
												
				cotizacionDetalle.setSumaAseguradaItem(vehiculo.getSumaAsegurada());
				cotizacionDetalle.setTasa(tasa);
				cotizacionDetalle.setValorExtras(sumaAseguradaTotalExtras);

				
				//se guarda el paquete 
				PaqueteDAO paqueteDAO = new PaqueteDAO();
				String paquete1_check = request.getParameter("paquete1_check");
				String paquete2_check = request.getParameter("paquete2_check");
				String paquete3_check = request.getParameter("paquete3_check");
				String paquete4_check = request.getParameter("paquete4_check");
				
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
				
				if(paquete.getId()!=null)
					cotizacionDetalle.setPaqueteId(BigInteger.valueOf(Long.parseLong(paquete.getId())));
				else
					cotizacionDetalle.setPaqueteId(null);
				
				/*String existePaquete = "";				
				// Agregamos el paquete
				if(paquete != null){
					existePaquete = cotizacionDetalle.getId();
					List<Cobertura> coberturas = paqueteDAO.buscarCoberturasPorPaquete(paquete);
				    for(int i = 0;i<coberturas.size();i++){
				    	Cobertura cobertura = coberturas.get(i);
				    	cotizacionCobertura = new CotizacionCobertura();
				    	cotizacionCobertura.setCobertura(cobertura);
				    	cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
				    	cotizacionCobertura.setPorcentajeSumaAsegurada(0);
				    	cotizacionCobertura.setMontoFijo(0);
				    	cotizacionCobertura.setPorcentajeValorSiniestro(0);		
				    	cotizacionCobertura = cotizacionCoberturaDAO.crear(cotizacionCobertura);
				    }
				}*/
				
				
				//cotizacionDetalle.setPaqueteId(paquete.getId());
				
				//evaldez se controla que exista 
				
				if(cotizacionDetalle.getId() != null && cotizacionDetalle.getCotizacion() != null && cotizacionDetalle.getCotizacion().getId() != null){
						//cotizacionDetalle.setId(cotizacionDetalleExiste.getId());
						cotizacionDetalle = cotizacionDetalleTransaction.editar(cotizacionDetalle);
				}
				else
					cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
				
								
				// Detectar Planes seleccionados
				
				//cotizacionCobertura = cotizacionCoberturaDAO.crear(cotizacionCobertura);	
				result.put("tasaVehiculo", tasa * 100);
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
				  String asText = page2.asText();
				  webClient.closeAllWindows();	
				  //System.out.print(asText.length());
				  if(asText.toLowerCase().contains("servicio no disponible"))
					  throw new Exception("Servicio de la ANT no disponible");
				  String infAuto = asText.substring(214, asText.indexOf("Cooperativa")).replaceAll("\n", ":");
				  String [] arrInfAuto = infAuto.split(":");

				  Color colorEnsurance = new Color();
				  ColorDAO colorDAO =  new ColorDAO();
				  
				  Marca marcaEnsurance = new Marca();
				  MarcaDAO marcaDAO = new MarcaDAO();
				  
				  Modelo modeloEnsurance = new Modelo();
				  ModeloDAO modeloDAO = new ModeloDAO();
				  
				  marcaEnsurance = marcaDAO.buscarPorNombre(arrInfAuto[7]); 
				  colorEnsurance = colorDAO.buscarPorNombre(arrInfAuto[15]);
				  modeloEnsurance = modeloDAO.buscarPorMarcaYNombre(marcaEnsurance, arrInfAuto[9]);
				  				  
				  
				  result.put("placa", arrInfAuto[1]);
				  result.put("chasis", arrInfAuto[3]);
				  result.put("motor", arrInfAuto[5]);
				  result.put("marcaEnsurance", marcaEnsurance.getId());
				  result.put("modeloEnsurance", modeloEnsurance.getId());
				  result.put("tipo", arrInfAuto[11]);
				  result.put("clase", arrInfAuto[13]); 	
				  result.put("color", colorEnsurance.getId());
				  result.put("anioFabricacion", arrInfAuto[19]);				  
 			    }
				 /* 
				  WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
				  String resultado = webService.getWebServiceCotizadorImplPort().obtenerDatosVehiculo(placa, "placa");
				  Utilitarios util = new Utilitarios();
				  JSONArray jsonArray = new JSONArray();
				  jsonArray.add(util.cargarParametroWS(resultado));
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
				  result.put("vigenciaEnsurance", jsonArray.getJSONObject(0).get("vigencia"));
				  result.put("tasaEnsurance", jsonArray.getJSONObject(0).get("tasa"));
				  */
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
			
			if(tipoConsulta.equalsIgnoreCase("eliminar"))
			{		
				String cotizacion_id = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
				cotizacion = cotizacionDAO.buscarPorId(cotizacion_id);
			
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
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				TipoObjeto tipoObjetoEncontrado = tipoObjetoDAO.buscarPorId(codigoTipoObjeto);				
				
				result.put("listadoCotizacion",  consultarPorTipoObjeto(tipoObjetoEncontrado));
				
			}

			if(tipoConsulta.equalsIgnoreCase("encontrarPorId"))
			{
				String cotizacionId = request.getParameter("id") == null ? "" : request.getParameter("id");		
				
				result.put("datosCotizacion",  encontrarPorId(cotizacionId));
								
			}
			
			if(tipoConsulta.equalsIgnoreCase("enviarCertificado"))
			{
				String correos = request.getParameter("correos") == null ? "" : request.getParameter("correos");		
				String id = request.getParameter("id") == null ? "" : request.getParameter("id");		
				
				enviarCertificado(id, correos);
								
			}
			
			if(tipoConsulta.equalsIgnoreCase("cargarTablaVehiculos"))
			{
				JSONArray listadoVehiculosJSONArray = new JSONArray();
				
				String id = request.getParameter("id") == null ? "" : request.getParameter("id");
				if(id!="")
					cotizacion = cotizacionDAO.buscarPorId(id);
				List <CotizacionDetalle> cotizacionesDetalle =cotizacion.getCotizacionDetalles();
				int i=0;
				for(i=0;i<cotizacionesDetalle.size();i++){
					JSONObject vehiculoJSONObject = new JSONObject();
					ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO();
					ObjetoVehiculo ov=ovDAO.buscarPorId(cotizacionesDetalle.get(i).getObjetoId());
					vehiculoJSONObject.put("placa", ov.getPlaca());
					vehiculoJSONObject.put("id", ov.getId());
					vehiculoJSONObject.put("chasis", ov.getChasis());
					vehiculoJSONObject.put("motor", ov.getMotor());
					vehiculoJSONObject.put("modelo", ov.getModelo().getNombre());
					vehiculoJSONObject.put("marca", ov.getModelo().getMarca().getNombre());
					vehiculoJSONObject.put("valor", ov.getSumaAsegurada());
					listadoVehiculosJSONArray.add(vehiculoJSONObject);
				}
				result.put("listadoVehiculos", listadoVehiculosJSONArray);
								
			}

			if(tipoConsulta.equalsIgnoreCase("cargarInspectoresInternos"))
			{
				result.put("listadoInspectoresInternos", cargarInspectoresInternos());
								
			}
			
			if(tipoConsulta.equalsIgnoreCase("actualizarFechaInicioVigencia"))
			{
				String vigenciaDesde = request.getParameter("vigenciaDesde") == null ? "" : request.getParameter("vigenciaDesde");		
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");		
				Date vigencia=new Date();
				vigencia=new Date(Integer.parseInt(vigenciaDesde.substring(0, 4))-1900,Integer.parseInt(vigenciaDesde.substring(5, 7))-1,Integer.parseInt(vigenciaDesde.substring(8)));
				cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				cotizacion.setVigenciaDesde(vigencia);
				cotizacion=cotizacionTransaction.editar(cotizacion);
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


	public JSONArray consultarPorTipoObjeto(TipoObjeto tipoObjeto){
		CotizacionDAO cDAO= new CotizacionDAO();
		List<Cotizacion> cotizacion=cDAO.buscarPorTipoObjeto(tipoObjeto);
		 
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
		//etapa 1
		
		JSONObject etapa1= new JSONObject();
		if(cotizacion.getEtapaWizard()>=1){
			etapa1.put("grupoProductos", cotizacion.getGrupoProductoId());
			etapa1.put("productos", cotizacion.getGrupoPorProductoId());
			etapa1.put("tasa", cotizacion.getTasaProductoId());
			etapa1.put("puntoVenta", cotizacion.getPuntoVenta().getId());
			etapa1.put("vigenciaPoliza", cotizacion.getVigenciaPoliza().getId());
			etapa1.put("agente", agente.getId());			
			etapa1.put("tipoIdentificacion", cliente.getEntidad().getTipoIdentificacion().getId() );
			etapa1.put("identificacion", cliente.getEntidad().getIdentificacion());
			etapa1.put("nombres", cliente.getEntidad().getNombres());
			etapa1.put("nombreCompleto", cliente.getEntidad().getNombreCompleto());
			etapa1.put("apellidos", cliente.getEntidad().getApellidos());
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
				
				
//				if(cotizacion.getTipoObjeto().getNombre().equals("VehiculosPesados")){
				
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
				objeto.put("tonelaje", ov.getTonelajeVehiculo());
				objeto.put("tipoAdquisicion", ov.getTipoAdquisicion());
				objeto.put("tasa", detalles.get(i).getTasa()*100);
				objeto.put("paquete", detalles.get(i).getPaqueteId());
				objeto.put("necesitaInspeccion", detalles.get(i).getNecesitaInspeccion());
				objeto.put("grupoPorProductoId", cotizacion.getGrupoPorProductoId());

				JSONArray coberturasJSONArray= new JSONArray(); 
				
				List<CotizacionCobertura> coberturas=detalles.get(i).getCotizacionCoberturas();
				
				for(int j=0;j<coberturas.size();j++){
					JSONObject coberturaJSON = new JSONObject();
					coberturaJSON.put("id", coberturas.get(j).getId() );
					coberturaJSON.put("montoFijo", coberturas.get(j).getMontoFijo() );
					coberturaJSON.put("sumaAsegurada", coberturas.get(j).getPorcentajeSumaAsegurada() );
					coberturaJSON.put("valorSinisestro", coberturas.get(j).getPorcentajeValorSiniestro() );
					coberturaJSON.put("coberturaId", coberturas.get(j).getCobertura().getId());
					coberturaJSON.put("nemotecnico", coberturas.get(j).getCobertura().getNemotecnico());
					
					coberturasJSONArray.add(coberturaJSON);
				}
				
				objeto.put("coberturas", coberturasJSONArray);
			
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
				
//				}
							
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
				solicitudDescuento.put("descuentoId",solicitudesDescuento.get(i).getDescuento().getId());
				solicitudDescuento.put("porcentaje", solicitudesDescuento.get(i).getPorcentaje());
				solicitudDescuento.put("motivo", solicitudesDescuento.get(i).getMotivoDescuento().getId());
				solicitudDescuento.put("descripcion", solicitudesDescuento.get(i).getDescripcion());
				solicitudDescuento.put("estado", solicitudesDescuento.get(i).getEstado().getNombre());				
				if(solicitudesDescuento.get(i).getUsuarioId()!=null&&!solicitudesDescuento.get(i).getUsuarioId().equals(""))
				solicitudDescuento.put("usuarioActualiza", usuDAO.buscarPorId(solicitudesDescuento.get(i).getUsuarioId()+"").getEntidad().getNombreCompleto());				
				
			}
			JSONArray cuotasJSONArray = new JSONArray();
			JSONObject cuotasJSONObject = new JSONObject();
			JSONObject formaPago = new JSONObject();
			//JSONObject solicitudInspeccion = new JSONObject();			
			if(cotizacion.getPago() != null){
				formaPago.put("pagoId", cotizacion.getPago().getId());
				formaPago.put("formaPagoId", cotizacion.getPago().getFormaPago().getId());
				formaPago.put("valorTotal", cotizacion.getPago().getValorTotal());
				formaPago.put("plazo", cotizacion.getPago().getPlazonEnMes());
		
				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("DEBITO BANCARIO")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
					formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
					formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
					formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
					formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
					formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
					formaPago.put("cuotaInicial", cotizacion.getPago().getCuotaInicial());
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
						cuotasJSONArray.add(cuotasJSONObject);
					}
					
				}
			
			if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("CONTADO")){
				formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
				//formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
				//formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
				//formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
				//formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
				//formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
				//formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
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
			etapa3.put("listadoCuotas", cuotasJSONArray);
			etapa3.put("formaPago", formaPago);
			etapa3.put("solicitudDescuento", solicitudDescuento);

			retorno.put("etapa3", etapa3);
		}
		
		
		//datos UPLA
		//etapa 4
		//JSONObject etapa4= new JSONObject();
		JSONObject datosUPLA= new JSONObject();
				
		UplaDAO uplaDAO=new UplaDAO();
		Upla upla = uplaDAO.buscarPorCliente(cliente);
		if(upla!=null&&upla.getId()!=null){
			datosUPLA.put("datosUPLA", getDatosUpla(upla));			
		}

		retorno.put("estadoCotizacion", cotizacion.getEstado().getNombre().toLowerCase());	
		retorno.put("datosUPLA",datosUPLA );
		
		//detalleValorPagar.put("TotalSumaAsegurada", cotizacion.);
		
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
		CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(vehiculoId, cotizacion);
		cotizacionDetalleTransaction.eliminar(cotizacionDetalle);
		result = "Se ha eliminado correctamente";
		return result;
	}
	public void enviarCertificado(String id,String correos){
		String [] correosArr=correos.split(",");
		File reportFile = new File(getServletConfig().getServletContext().getRealPath("/static/reportes/CertificadoVhc.jasper"));
		    byte[] bytes = null;
		    Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("COTIZACION", id);

		    try
		    {
		    	Reportes reporte=new Reportes();
		    	bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),parametros, reporte.getConnection());
		    	Reportes.cerrarConexion();
		      
		      for(int i=0;i<correosArr.length;i++){
					Utilitarios.envioMailPDFAdjunto(correosArr[i], "Certificado Cotizacion "+id, "", bytes);
				}
		    }
		    catch (JRException e)
		    {
		    	
		    }

		
	}

	public JSONArray cargarInspectoresInternos(){
		JSONArray retorno=new JSONArray();
		InspectorDAO iDAO = new InspectorDAO();
		TipoInspectorDAO tipoInspectorDAO=new TipoInspectorDAO();
		TipoInspector tipoInspector=tipoInspectorDAO.buscarPorId("1");
		List<Inspector> inspectores=iDAO.buscarPorTipo(tipoInspector) ;
		for(int i=0;i<inspectores.size();i++){
			JSONObject inspector=new JSONObject();
			
			inspector.put("id",inspectores.get(i).getId());
			inspector.put("nombre",inspectores.get(i).getNombre());
			inspector.put("sucursal",inspectores.get(i).getSucursal().getNombre());
			retorno.add(inspector);
		}
		
		return retorno;
	}
}
	
