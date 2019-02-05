package com.qbe.cotizador.servlets.producto.agricola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
//import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.TipoInspectorDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.util.Reportes;
import com.qbe.cotizador.util.Utilitarios;
//Ganadero

/**
 * Servlet implementation class CotizacionController
 */
@WebServlet("/AgriCotizacionController")
public class AgriCotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriCotizacionController() {
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
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");

			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			JSONObject cotizacionJSONObject = new JSONObject();
			JSONArray cotizacionJSONArray = new JSONArray();
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();

			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction(); 
			EntidadTransaction entidadTransaction = new EntidadTransaction();
			ClienteTransaction clienteTransaction = new ClienteTransaction();

			//Modifico el estado de la cotización del producto ganadero.
			if(tipoConsulta.equalsIgnoreCase("cambiarEstado"))
			{
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

				result.put("cotizacionId",cotizacion.getId());
			}

			//Crear la cotización de ganadero fzurita
			if(tipoConsulta.equalsIgnoreCase("crear") && producto.equalsIgnoreCase("Agricola"))
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
				cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("Agricola"));
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));

				if(cotizacion.getId()==null)
					cotizacion.setUsuario(usuario);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

				if(!fechaActual.equals(""))
					cotizacion.setFechaElaboracion(fechaActual);

				if(cotizacion.getEtapaWizard()<1){
					cotizacion.setEtapaWizard(1);
				}
				cotizacion.setAsegurado(entidad);
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
			//Fin de la creación de la cotización

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
			
			//Metodo buscar por estado y por parametros
			if(tipoConsulta.equalsIgnoreCase("encontrarAprobadoPorCanal"))
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

				RolDAO rolDAO=new RolDAO();
				Rol rol=new Rol();
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol												
					listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoParaGanadero(fInicio, fFinal, tipoObjetoEncontrar,numeroCotizacion,puntoVentaId,agenteId,identificacion,usuarioId,"AGRI"));
					result.put("listadoCotizacion", listaCotizacionesJSONArray.get(i));

					result.put("numeroListas", i);
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();
					i=0;
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						listaCotizacionesJSONArray = new JSONArray();															
						listaCotizacionesJSONArray.add(i, consultarPorTipoObjetoPuntoVentaYEstado(fInicio, fFinal, tipoObjetoEncontrar, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId, "AGRI"));
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
				int i=0;
				JSONArray listaCotizacionesJSONArray = new JSONArray();
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

				RolDAO rolDAO=new RolDAO();
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

				RolDAO rolDAO=new RolDAO();
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

			if(tipoConsulta.equalsIgnoreCase("cargarInspectoresInternos"))
			{
				result.put("listadoInspectoresInternos", cargarInspectoresInternos());
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
				cotizacionJSONObject.put("prima_neta_total", cotizacion.get(i).getPrimaNetaTotal());}

			cotizacionJSONArray.add(cotizacionJSONObject);

		}

		//retorno.put("numRegistros",i);
		retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJSONArray;
	}

	//Ganadero
	public JSONArray consultarPorTipoObjetoPorEstado(String fecha1, String fecha2, TipoObjeto tipoObjeto, String Estado){
		JSONObject retorno= new JSONObject();
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
		CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
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
			JSONObject objeto= new JSONObject();
			List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
			if(detalles.size()!=0){
				AgriObjetoCotizacionDAO objetoAgricolaDAO=new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion os=objetoAgricolaDAO.buscarPorId(new BigInteger(detalles.get(0).getObjetoId()));
				objeto.put("objetoCotizacionId", os.getObjetoCotizacionId());
				objeto.put("tipoCultivoId", os.getTipoCultivoId());
				objeto.put("variedad", os.getVariedad());
				objeto.put("provinciaId", os.getProvinciaId());
				objeto.put("cantonId", os.getCantonId());
				objeto.put("parroquiaId", os.getParroquiaId());
				objeto.put("agricultoTecnificado", os.getAgricultorTecnificado());
				objeto.put("direccionSiembra", os.getDireccionSiembra());
				objeto.put("hectareasLote", os.getHectareasLote());
				objeto.put("hectareasAsegurables", os.getHectareasAsegurables());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try{
					String fechaCredito = df.format(os.getFechaCredito());
					objeto.put("fechaCredito", fechaCredito);
				}catch(Exception e){
					System.out.println("sin fecha credito");
				}
				String fechaSiembra = df.format(os.getFechaSiembra());
				try{
					objeto.put("montoCredito", os.getMontoCredito());
				}catch(Exception e){
					System.out.println("sin montoCredito");
				}
				objeto.put("fechaSiembra", fechaSiembra);
				objeto.put("altitudNivelMar", os.getAltitudNivelMar());
				try{
					objeto.put("latitud", os.getLatitud());
				}catch(Exception e){
					System.out.println("sin latitud");
				}
				try{
					objeto.put("longitud", os.getLongitud());
				}catch(Exception e){
					System.out.println("sin longitud");
				}
				objeto.put("disponeRiego", os.getDisponeRiego());
				objeto.put("tipoSeguro", os.getTipoSeguro());
				objeto.put("sucursalCanalId", os.getSucursalCanalId());
				objeto.put("disponeAsistencia", os.getDisponeAsistencia());
				objeto.put("aniosCultivo", os.getAniosCultivo());
				objeto.put("observacionCotizacion", os.getObservacionCotizacion());

				etapa2.put("objetos", objeto);
				retorno.put("etapa2", etapa2);
			}
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
				endosoBeneficiarioJSONObject.put("endosoBeneficiarioId", endosoBeneficiario.getId());
				endosoBeneficiarioJSONObject.put("beneficiarioId", endosoBeneficiario.getBeneficiario().getId());
				endosoBeneficiarioJSONObject.put("monto", endosoBeneficiario.getMonto());
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
		List<Inspector> inspectores=iDAO.buscarPorTipo(tipoInspectorDAO.buscarPorId("1")) ;
		SucursalDAO sDAO=new SucursalDAO();
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
		JSONObject retorno= new JSONObject();
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

						if(direcciones.get(0).getCiudad()!=null)
						{
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

						if(direcciones.get(0).getParroquia()!=null)
							retorno.put("parroquia", direcciones.get(0).getParroquia().getId());

						if(direcciones.get(0).getParroquia().getCanton()!=null)
							retorno.put("canton", direcciones.get(0).getParroquia().getCanton().getId());

						if(direcciones.get(0).getParroquia().getCanton().getProvincia()!=null)
							retorno.put("provincia", direcciones.get(0).getParroquia().getCanton().getProvincia().getId());

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

