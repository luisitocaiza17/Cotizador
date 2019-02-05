package com.qbe.cotizador.servlets.inspeccion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioInspector;
import com.qbe.cotizador.model.Zona;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.inspeccion.SolicitudInspeccionTransaction;
import com.qbe.cotizador.util.Utilitarios;

/**
 * Servlet implementation class SolicitudInspeccionController
 */
@WebServlet("/SolicitudInspeccionController")
public class SolicitudInspeccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SolicitudInspeccionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();

		try {
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String codigoInspector = request.getParameter("codigoInspector") == null ? ""
					: request.getParameter("codigoInspector");
			String codigoCotizacion = request.getParameter("codigoCotizacion") == null ? ""
					: request.getParameter("codigoCotizacion");
			String telfContacto = request.getParameter("telfContacto") == null ? ""
					: request.getParameter("telfContacto");
			String origenInspeccion = request.getParameter("origenInspeccion") == null ? ""
					: request.getParameter("origenInspeccion");
			String destinoInspeccion = request.getParameter("destinoInspeccion") == null ? ""
					: request.getParameter("destinoInspeccion");
			String valorInspeccion = request.getParameter("valorInspeccion") == null ? ""
					: request.getParameter("valorInspeccion");
			String estado_nombre = request.getParameter("estado") == null ? "" : request.getParameter("estado");
			String usuario_id = request.getParameter("usuario") == null ? "" : request.getParameter("usuario");
			String fechaSolicitud = request.getParameter("fechaSolicitud") == null ? ""
					: request.getParameter("fechaSolicitud");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? ""
					: request.getParameter("tipoConsulta");
			String zona_id = request.getParameter("zona") == null ? "" : request.getParameter("zona");
			String enviar_correo = request.getParameter("enviarCorreo") == null ? ""
					: request.getParameter("enviarCorreo");
			JSONObject solicitudInspeccionJSONObject = new JSONObject();
			JSONArray solicitudInspeccionJSONArray = new JSONArray();

			SolicitudInspeccion solicitudInspeccion = new SolicitudInspeccion();
			SolicitudInspeccionDAO solicitudInspeccionDAO = new SolicitudInspeccionDAO();

			SolicitudInspeccionTransaction solicitudInspeccionTransaction = new SolicitudInspeccionTransaction();
			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();

			if (tipoConsulta.equals("actualizar")) {
				solicitudInspeccion = solicitudInspeccionDAO.buscarPorId(codigo);

			} else {
				if (!codigo.equals(""))
					solicitudInspeccion.setId(codigo);
			}

			Inspector inspector = new Inspector();
			InspectorDAO inspectorDAO = new InspectorDAO();

			if (!codigoInspector.equals("")) {
				inspector = inspectorDAO.buscarPorId(codigoInspector);
				solicitudInspeccion.setInspector(inspector);
			}

			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();

			if (!codigoCotizacion.equals("")) {
				cotizacion = cotizacionDAO.buscarPorId(codigoCotizacion);
				solicitudInspeccion.setCotizacion(cotizacion);
			}

			if (!origenInspeccion.equals(""))
				solicitudInspeccion.setOrigen(origenInspeccion);

			if (!destinoInspeccion.equals(""))
				solicitudInspeccion.setDestino(destinoInspeccion);

			Estado estado = new Estado();
			EstadoDAO estadoDAO = new EstadoDAO();

			if (estado_nombre.equals("")) {
				estado = estadoDAO.buscarPorNombre("Pendiente", "Inspeccion");
				solicitudInspeccion.setEstado(estado);
			} else {
				estado = estadoDAO.buscarPorNombre(estado_nombre, "Inspeccion");
				solicitudInspeccion.setEstado(estado);
			}
			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			if (!usuario_id.equals("")) {
				usuario = usuarioDAO.buscarPorId(usuario_id);
				solicitudInspeccion.setUsuario(usuario);
				;
			}

			Zona zona = new Zona();
			ZonaDAO zonaDAO = new ZonaDAO();

			if (!zona_id.equals("")) {
				zona = zonaDAO.buscarPorId(zona_id);
				solicitudInspeccion.setZona(zona);
			}

			if (!fechaSolicitud.equals("")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES"));
				solicitudInspeccion.setFechaSolicitud(dateFormat.parse(fechaSolicitud));
			} else {
				Date date = new Date();
				solicitudInspeccion.setFechaSolicitud(date);
			}

			if (!telfContacto.equals("")) {
				solicitudInspeccion.setTelfContacto(telfContacto);
			}

			if (!valorInspeccion.equals(""))
				solicitudInspeccion.setValorInspeccion(new Double(valorInspeccion));

			solicitudInspeccion.setUsuario((Usuario) request.getSession().getAttribute("usuario"));

			if (tipoConsulta.equals("encontrarTodos")) {
				List<SolicitudInspeccion> results = solicitudInspeccionDAO.buscarTodos();
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					solicitudInspeccion = results.get(i);
					solicitudInspeccionJSONObject.put("codigo", solicitudInspeccion.getId());
					solicitudInspeccionJSONObject.put("codigoCotizacion", solicitudInspeccion.getCotizacion().getId());
					solicitudInspeccionJSONObject.put("codigoInspector",
							solicitudInspeccion.getInspector().getNombre());
					solicitudInspeccionJSONObject.put("telfContacto", solicitudInspeccion.getTelfContacto());

					solicitudInspeccionJSONArray.add(solicitudInspeccionJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoSolicitudInspeccion", solicitudInspeccionJSONArray);
			}

			if (tipoConsulta.equals("encontrarPendientes")) {
				estado = estadoDAO.buscarPorNombre("Pendiente", "Inspeccion");
				List<SolicitudInspeccion> results = new ArrayList<SolicitudInspeccion>();// =
																							// solicitudInspeccionDAO.buscarPorEstadoDias(estado);//,30);
				usuario = new Usuario();

				if (request.getSession().getAttribute("usuario") != null)
					usuario = (Usuario) request.getSession().getAttribute("usuario");
				if (usuario == null || usuario.getId() == null)
					throw new Exception("Usuario sin sesión");

				Rol rol = new Rol();
				if (usuario.getUsuarioRols().size() > 0)
					rol = usuario.getUsuarioRols().get(0).getRol();

				if (Utilitarios.verificarAdministradorInspecciones(rol))// Verificamos
																		// si se
																		// visualizan
																		// todas
																		// las
																		// inspecciones
																		// por
																		// rol
					results = solicitudInspeccionDAO.buscarPorEstado(estado);// ,30);
				else {
					// InspectorDAO inspectorDAO=new InspectorDAO();
					inspector = inspectorDAO.buscarPorUsuario(usuario);
					results = solicitudInspeccionDAO.buscarEstadoInspector(estado, inspector);
				}

				int i = 0;
				for (i = 0; i < results.size(); i++) {
					solicitudInspeccion = results.get(i);
					solicitudInspeccionJSONObject.put("codigo", solicitudInspeccion.getId());
					solicitudInspeccionJSONObject.put("codigoCotizacion", solicitudInspeccion.getCotizacion().getId());
					solicitudInspeccionJSONObject.put("inspector", solicitudInspeccion.getInspector().getNombre());
					solicitudInspeccionJSONObject.put("estado", solicitudInspeccion.getEstado().getNombre());
					solicitudInspeccionJSONObject.put("usuario",
							solicitudInspeccion.getUsuario().getEntidad().getNombreCompleto());
					solicitudInspeccionJSONObject.put("telfContacto",
							solicitudInspeccion.getTelfContacto() == null ? "" : solicitudInspeccion.getTelfContacto());
					solicitudInspeccionJSONObject.put("origen",
							solicitudInspeccion.getOrigen() == null ? "" : solicitudInspeccion.getOrigen());
					solicitudInspeccionJSONObject.put("destino",
							solicitudInspeccion.getDestino() == null ? "" : solicitudInspeccion.getDestino());
					solicitudInspeccionJSONObject.put("valorInspeccion", solicitudInspeccion.getValorInspeccion());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					solicitudInspeccionJSONObject.put("fechaSolicitud",
							dateFormat.format(solicitudInspeccion.getFechaSolicitud()));

					solicitudInspeccionJSONArray.add(solicitudInspeccionJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoSolicitudInspeccion", solicitudInspeccionJSONArray);
			}

			if (tipoConsulta.equals("encontrarRealizadas")) {
				estado = estadoDAO.buscarPorNombre("Aprobada", "Inspeccion");
				List<SolicitudInspeccion> results = solicitudInspeccionDAO.buscarPorEstado(estado);
				usuario = new Usuario();

				if (request.getSession().getAttribute("usuario") != null)
					usuario = (Usuario) request.getSession().getAttribute("usuario");
				if (usuario == null || usuario.getId() == null)
					throw new Exception("Usuario sin sesión");
				Rol rol = new Rol();
				if (usuario.getUsuarioRols().size() > 0)
					rol = usuario.getUsuarioRols().get(0).getRol();
				if (Utilitarios.verificarAdministradorInspecciones(rol)) // Verificamos
																			// si
																			// se
																			// visualizan
																			// todas
																			// las
																			// inspecciones
																			// por
																			// rol
					results = solicitudInspeccionDAO.buscarPorEstado(estado);
				else {
					// InspectorDAO inspectorDAO=new InspectorDAO();
					inspector = inspectorDAO.buscarPorUsuario(usuario);
					results = solicitudInspeccionDAO.buscarEstadoInspector(estado, inspector);
				}
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					solicitudInspeccion = results.get(i);
					solicitudInspeccionJSONObject.put("codigo", solicitudInspeccion.getId());
					solicitudInspeccionJSONObject.put("codigoCotizacion", solicitudInspeccion.getCotizacion().getId());
					solicitudInspeccionJSONObject.put("inspector", solicitudInspeccion.getInspector().getNombre());
					solicitudInspeccionJSONObject.put("estado", solicitudInspeccion.getEstado().getNombre());
					solicitudInspeccionJSONObject.put("usuario",
							solicitudInspeccion.getUsuario().getEntidad().getNombreCompleto());
					solicitudInspeccionJSONObject.put("telfContacto",
							solicitudInspeccion.getTelfContacto() == null ? "" : solicitudInspeccion.getTelfContacto());
					solicitudInspeccionJSONObject.put("origen",
							solicitudInspeccion.getOrigen() == null ? "" : solicitudInspeccion.getOrigen());
					solicitudInspeccionJSONObject.put("destino",
							solicitudInspeccion.getDestino() == null ? "" : solicitudInspeccion.getDestino());
					solicitudInspeccionJSONObject.put("valorInspeccion", solicitudInspeccion.getValorInspeccion());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					solicitudInspeccionJSONObject.put("fechaSolicitud",
							dateFormat.format(solicitudInspeccion.getFechaSolicitud()));

					solicitudInspeccionJSONArray.add(solicitudInspeccionJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoSolicitudInspeccion", solicitudInspeccionJSONArray);
			}

			if (tipoConsulta.equals("encontrarRealizadasCotizacion")) {
				cotizacion = cotizacionDAO.buscarPorId(codigoCotizacion);
				estado = estadoDAO.buscarPorNombre("Aprobada", "Inspeccion");
				List<SolicitudInspeccion> results;// =
													// solicitudInspeccionDAO.buscarPorEstadoCotizacion(estado,cotizacion);
				usuario = new Usuario();

				if (request.getSession().getAttribute("usuario") != null)
					usuario = (Usuario) request.getSession().getAttribute("usuario");
				if (usuario == null || usuario.getId() == null)
					throw new Exception("Usuario sin sesión");
				Rol rol = new Rol();
				if (usuario.getUsuarioRols().size() > 0)
					rol = usuario.getUsuarioRols().get(0).getRol();

				if (Utilitarios.verificarAdministradorInspecciones(rol)) // Verificamos
																			// si
																			// se
																			// visualizan
																			// todas
																			// las
																			// inspecciones
																			// por
																			// rol
					results = solicitudInspeccionDAO.buscarPorEstadoCotizacion(estado, cotizacion);
				else {
					// InspectorDAO inspectorDAO=new InspectorDAO();
					inspector = inspectorDAO.buscarPorUsuario(usuario);
					results = solicitudInspeccionDAO.buscarEstadoInspector(estado, inspector);
				}
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					solicitudInspeccion = results.get(i);
					solicitudInspeccionJSONObject.put("codigo", solicitudInspeccion.getId());
					solicitudInspeccionJSONObject.put("codigoCotizacion", solicitudInspeccion.getCotizacion().getId());
					solicitudInspeccionJSONObject.put("inspector", solicitudInspeccion.getInspector().getNombre());
					solicitudInspeccionJSONObject.put("estado", solicitudInspeccion.getEstado().getNombre());
					solicitudInspeccionJSONObject.put("usuario",
							solicitudInspeccion.getUsuario().getEntidad().getNombreCompleto());
					solicitudInspeccionJSONObject.put("telfContacto",
							solicitudInspeccion.getTelfContacto() == null ? "" : solicitudInspeccion.getTelfContacto());
					solicitudInspeccionJSONObject.put("origen",
							solicitudInspeccion.getOrigen() == null ? "" : solicitudInspeccion.getOrigen());
					solicitudInspeccionJSONObject.put("destino",
							solicitudInspeccion.getDestino() == null ? "" : solicitudInspeccion.getDestino());
					solicitudInspeccionJSONObject.put("valorInspeccion", solicitudInspeccion.getValorInspeccion());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					solicitudInspeccionJSONObject.put("fechaSolicitud",
							dateFormat.format(solicitudInspeccion.getFechaSolicitud()));

					solicitudInspeccionJSONArray.add(solicitudInspeccionJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoSolicitudInspeccion", solicitudInspeccionJSONArray);
			}

			if (tipoConsulta.equals("encontrarPorCotizacionId")) {

				result.put("solicitudInspeccion", cargarPorId(codigoCotizacion));
			}
			/*
			 * // Encontramos las solicitudInspecciones activas ayanez
			 * if(tipoConsulta.equals("encontrarSolicitudInspeccionesActivas")){
			 * List<SolicitudInspeccion> listado =
			 * solicitudInspeccionDAO.buscarActivos(); if(listado.size() > 0) {
			 * JSONObject solicitudInspeccionesJSON = new JSONObject(); for(int
			 * i=0; i<listado.size(); i++) { solicitudInspeccion =
			 * (SolicitudInspeccion) listado.get(i);
			 * solicitudInspeccionesJSON.put("id", solicitudInspeccion.getId());
			 * solicitudInspeccionesJSON.put("nombre",
			 * solicitudInspeccion.getNombre());
			 * solicitudInspeccionJSONArray.add(solicitudInspeccionesJSON); } }
			 * result.put("solicitudInspecciones",
			 * solicitudInspeccionJSONArray); }
			 */
			if (tipoConsulta.equals("crear")) {
				cotizacion.setEtapaWizard(3);
				cotizacionTransaction.editar(cotizacion);
				estado = estadoDAO.buscarPorNombre("Pendiente", "Inspeccion");
				solicitudInspeccion.setEstado(estado);
				solicitudInspeccion = solicitudInspeccionTransaction.crear(solicitudInspeccion);
				result.put("codigoSolicitudInspeccion", solicitudInspeccion.getId());
				if (enviar_correo.equals("si"))
					enviarCorreoSolicitudInspeccionPendiente(solicitudInspeccion, request);
			}

			if (tipoConsulta.equals("actualizar")) {
				solicitudInspeccionTransaction.editar(solicitudInspeccion);
			}

			if (tipoConsulta.equals("actualizarEstado")) {
				solicitudInspeccion = solicitudInspeccionDAO.buscarPorId(codigo);
				estado = estadoDAO.buscarPorNombre(estado_nombre, "Inspeccion");
				solicitudInspeccion.setEstado(estado);
				solicitudInspeccionTransaction.editar(solicitudInspeccion);
				if (enviar_correo.equals("si"))
					enviarCorreoSolicitudInspeccionAprobada(solicitudInspeccion, request);
			}

			if (tipoConsulta.equals("eliminar"))
				solicitudInspeccionTransaction.eliminar(solicitudInspeccion);

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

	public JSONObject cargarPorId(String id) {
		JSONObject retorno = new JSONObject();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		Cotizacion cotizacion = cotizacionDAO.buscarPorId(id);
		SolicitudInspeccionDAO solicitudInspeccionDAO = new SolicitudInspeccionDAO();
		SolicitudInspeccion solicitudInspeccion = solicitudInspeccionDAO.buscarPorCotizacion(cotizacion);
		if (solicitudInspeccion.getId() != null)
			if (!solicitudInspeccion.getId().equals("")) {
				retorno.put("tipoInspector", solicitudInspeccion.getInspector().getTipoInspector().getId());
				if (solicitudInspeccion.getInspector().getTipoInspector().getId().equals("2")) {
					retorno.put("destino", solicitudInspeccion.getDestino());
					retorno.put("estado", solicitudInspeccion.getEstado().getNombre());
					retorno.put("fechaSolicitud", solicitudInspeccion.getFechaSolicitud());
					retorno.put("id", solicitudInspeccion.getId());
					retorno.put("inspectorId", solicitudInspeccion.getInspector().getId());
					retorno.put("origen", solicitudInspeccion.getOrigen());
					retorno.put("telefonoContacto", solicitudInspeccion.getTelfContacto());
					retorno.put("usuario", solicitudInspeccion.getUsuario().getId());
					retorno.put("valor", solicitudInspeccion.getValorInspeccion());
					retorno.put("zona", solicitudInspeccion.getZona().getId());
				} else {
					retorno.put("fechaSolicitud", solicitudInspeccion.getFechaSolicitud());
					retorno.put("id", solicitudInspeccion.getId());
					retorno.put("inspectorId", solicitudInspeccion.getInspector().getId());
					retorno.put("estado", solicitudInspeccion.getEstado().getNombre());
				}
			}

		return retorno;
	}

	public void enviarCorreoSolicitudInspeccionPendiente(SolicitudInspeccion solicitudInspeccion,
			HttpServletRequest request) {
		Usuario usuario = solicitudInspeccion.getUsuario();

		Cotizacion cotizacion = solicitudInspeccion.getCotizacion();
		List<CotizacionDetalle> cd = cotizacion.getCotizacionDetalles();

		String rutaPlantilla = this.getServletContext().getRealPath("")
				+ "/static/plantillas/solicitudInspeccionPendiente.html";
		FileReader fr = null;
		BufferedReader br = null;

		String cuerpoMail = "";
		String link = request.getRequestURL().toString();

		try {
			File archivo = new File(rutaPlantilla);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) {
				cuerpoMail = cuerpoMail + linea;
			}

			String tablaCliente = "";
			tablaCliente += "<table cellpadding=\"5\" style=\"font-family: Arial; font-size: 14px;\"><tbody>";

			tablaCliente += "<tr><td colspan=\"6\" bgcolor=\"#009AE4\" style=\"text-align: center; color: #FFFFFF;\"><b>DATOS CLIENTE</b></td></tr><tr>";
			// Venta
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Id Venta</b></td>";

			// Identificacion
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Identificacion Cliente</b></td>";

			// Nombres
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Nombres</b></td>";

			// Telefono
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Telefono Contacto</b></td>";

			// Mail
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Mail Agente</b></td>";

			// Ciudad
			tablaCliente += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Ciudad Inspeccion</b></td></tr>";

			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());

			AgenteDAO agenteDAO = new AgenteDAO();
			Agente agente = agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());

			// Venta
			tablaCliente += "<tr><td><b>" + cotizacion.getId() + "</b></td>";

			// Identificacion
			tablaCliente += "<td><b>" + cliente.getEntidad().getIdentificacion() + "</b></td>";

			// Nombres
			tablaCliente += "<td><b>" + cliente.getEntidad().getNombreCompleto() + "</b></td>";

			// Telefono
			tablaCliente += "<td><b>" + solicitudInspeccion.getTelfContacto() + "</b></td>";

			// Mail
			tablaCliente += "<td><b>" + agente.getEntidad().getMail() + "</b></td>";

			// Ciudad
			tablaCliente += "<td><b>" + solicitudInspeccion.getDestino() + "</b></td></tr></tbody></table>";

			cuerpoMail = cuerpoMail.replace("#tablaCliente#", tablaCliente);

			String tablaDetalle = "";
			tablaDetalle += "<table cellpadding=\"5\" style=\"font-family: Arial; font-size: 14px;\"><tbody>";
			tablaDetalle += "<tr><td colspan=\"6\" bgcolor=\"#009AE4\" style=\"text-align: center; color: #FFFFFF;\"><b>DATOS VEHÍCULOS</b></td></tr><tr>";

			// numero
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>No.</b></td>";

			// modelo
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Modelo</b></td>";

			// año
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b>Año</b></td>";

			// placa
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b> Placa</b></td>";

			// motor
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b> Motor</b></td>";

			// chasis
			tablaDetalle += "<td bgcolor=\"#009AE4\" style=\"text-align: right; color: #FFFFFF;\"><b> Chasis</b></td></tr>";

			for (int j = 0; j < cd.size(); j++) {
				ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
				ObjetoVehiculo ov = ovDAO.buscarPorId(cd.get(j).getObjetoId());

				tablaDetalle += "<tr><td>" + (j + 1) + "</td>";

				// modelo
				tablaDetalle += "<td>" + ov.getModelo().getNombre() + "</td>";
				// año
				tablaDetalle += "<td>" + ov.getAnioFabricacion() + "</td>";
				// placa
				tablaDetalle += "<td>" + ov.getPlaca() + "</td>";
				// motor
				tablaDetalle += "<td>" + ov.getMotor() + "</td>";
				// chasis
				tablaDetalle += "<td>" + ov.getChasis() + "</td></tr>";

			}
			tablaDetalle += "</table></tbody>";

			cuerpoMail = cuerpoMail.replace("#tablaDetalles#", tablaDetalle);

			cuerpoMail = cuerpoMail.replace("#urlImagenes#", link.replace("/SolicitudInspeccionController", ""));

			cuerpoMail = cuerpoMail.replace("#urlCotizador#", link.replace("/SolicitudInspeccionController", ""));
			cuerpoMail = cuerpoMail.replace("#usuarioSolicitaNombre#", usuario.getEntidad().getNombreCompleto());
			cuerpoMail = cuerpoMail.replace("#usuarioSolicitaCorreo#", usuario.getEntidad().getMail());

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

		if (usuario.getEntidad().getMail() != null && !usuario.getEntidad().getMail().equals("")) {
			Utilitarios.envioMail(usuario.getEntidad().getMail(), "Mail de Solicitud de Inspección", cuerpoMail);
		}

		if (solicitudInspeccion.getInspector().getMail1() != null
				&& !solicitudInspeccion.getInspector().getMail1().equals(""))
			Utilitarios.envioMail(solicitudInspeccion.getInspector().getMail1(), "Mail de Solicitud de Inspeccion",
					cuerpoMail);

		if (solicitudInspeccion.getInspector().getMail2() != null
				&& !solicitudInspeccion.getInspector().getMail2().equals(""))
			Utilitarios.envioMail(solicitudInspeccion.getInspector().getMail2(), "Mail de Solicitud de Inspeccion",
					cuerpoMail);

		if (solicitudInspeccion.getInspector().getMail3() != null
				&& !solicitudInspeccion.getInspector().getMail3().equals(""))
			Utilitarios.envioMail(solicitudInspeccion.getInspector().getMail3(), "Mail de Solicitud de Inspeccion",
					cuerpoMail);

	}

	public void enviarCorreoSolicitudInspeccionAprobada(SolicitudInspeccion solicitudInspeccion,
			HttpServletRequest request) {
		Usuario usuario = solicitudInspeccion.getUsuario();

		String rutaPlantilla = this.getServletContext().getRealPath("")
				+ "/static/plantillas/solicitudInspeccionAprobada.html";
		FileReader fr = null;
		BufferedReader br = null;

		String cuerpoMail = "";
		String link = request.getRequestURL().toString();

		try {
			File archivo = new File(rutaPlantilla);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;

			while ((linea = br.readLine()) != null) {
				cuerpoMail = cuerpoMail + linea;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			cuerpoMail = cuerpoMail.replace("#fechaSolicitud#", sdf.format(solicitudInspeccion.getFechaSolicitud()));

			cuerpoMail = cuerpoMail.replace("#numeroCotizacion#", solicitudInspeccion.getCotizacion().getId());

			cuerpoMail = cuerpoMail.replace("#urlImagenes#", link.replace("/SolicitudInspeccionController", ""));

			cuerpoMail = cuerpoMail.replace("#urlCotizador#", link.replace("/SolicitudInspeccionController", ""));
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
		if (usuario.getEntidad().getMail() != null && !usuario.getEntidad().getMail().equals(""))
			Utilitarios.envioMail(usuario.getEntidad().getMail(), "Mail de Solicitud de Inspeccion", cuerpoMail);

	}

	public static JSONArray buscarPendientesPorUsuarioInspector(UsuarioInspector ui) {
		JSONArray retorno = new JSONArray();
		EstadoDAO eDAO = new EstadoDAO();
		Estado estado = eDAO.buscarPorNombreClase("Pendiente", "Inspeccion");

		SolicitudInspeccionDAO siDAO = new SolicitudInspeccionDAO();
		List<SolicitudInspeccion> solicitudes = siDAO.buscarEstadoUsuarioInspector(estado, ui);

		for (SolicitudInspeccion si : solicitudes) {
			JSONObject aux = new JSONObject();

			aux.put("numero", si.getId());
			// Cotizacion coti=si.getCotizacion();
			// EntidadDAO entiDAO=new EntidadDAO();
			// Cliente cli=cliDAO.buscarPorId(coti.getClienteId().toString());
			Entidad enti = si.getSolicitante();
			aux.put("nombre", enti.getNombreCompleto());
			aux.put("fechaSolicitud", Utilitarios.dateToString(si.getFechaSolicitud()));
			JSONObject detalleJSON = new JSONObject();
			ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
			ObjetoVehiculo ov = ovDAO.buscarPorId(si.getObjetoId());
			detalleJSON.put("marca", ov.getModelo().getMarca().getNombre());
			detalleJSON.put("modelo", ov.getModelo().getNombre());
			if (si.getCotizacion() != null) {
				aux.put("cotizacion", si.getCotizacion().getId());
			}

			/*
			 * List<CotizacionDetalle> detalles=coti.getCotizacionDetalles();
			 * JSONArray detallesJSON=new JSONArray(); for(CotizacionDetalle
			 * detalle:detalles){ JSONObject detalleJSON=new JSONObject();
			 * ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO(); ObjetoVehiculo
			 * ov=ovDAO.buscarPorId(detalle.getObjetoId());
			 * detalleJSON.put("marca", ov.getModelo().getMarca().getNombre());
			 * detalleJSON.put("modelo", ov.getModelo().getNombre());
			 * detallesJSON.add(detalleJSON); }
			 */

			aux.put("vehiculo", detalleJSON);

			retorno.add(aux);
		}

		return retorno;
	}
	public static JSONArray buscarAgendadasPorUsuarioInspector(UsuarioInspector ui) {
		JSONArray retorno = new JSONArray();
		EstadoDAO eDAO = new EstadoDAO();
		Estado estado = eDAO.buscarPorNombreClase("Agendada", "Inspeccion");

		SolicitudInspeccionDAO siDAO = new SolicitudInspeccionDAO();
		List<SolicitudInspeccion> solicitudes = siDAO.buscarEstadoUsuarioInspector(estado, ui);

		for (SolicitudInspeccion si : solicitudes) {
			JSONObject aux = new JSONObject();

			aux.put("numero", si.getId());
			// Cotizacion coti=si.getCotizacion();
			// EntidadDAO entiDAO=new EntidadDAO();
			// Cliente cli=cliDAO.buscarPorId(coti.getClienteId().toString());
			Entidad enti = si.getSolicitante();
			aux.put("nombre", enti.getNombreCompleto());
			aux.put("fechaSolicitud", Utilitarios.dateToString(si.getFechaSolicitud()));
			JSONObject detalleJSON = new JSONObject();
			ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
			ObjetoVehiculo ov = ovDAO.buscarPorId(si.getObjetoId());
			detalleJSON.put("marca", ov.getModelo().getMarca().getNombre());
			detalleJSON.put("modelo", ov.getModelo().getNombre());
			if (si.getCotizacion() != null) {
				aux.put("cotizacion", si.getCotizacion().getId());
			}

			/*
			 * List<CotizacionDetalle> detalles=coti.getCotizacionDetalles();
			 * JSONArray detallesJSON=new JSONArray(); for(CotizacionDetalle
			 * detalle:detalles){ JSONObject detalleJSON=new JSONObject();
			 * ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO(); ObjetoVehiculo
			 * ov=ovDAO.buscarPorId(detalle.getObjetoId());
			 * detalleJSON.put("marca", ov.getModelo().getMarca().getNombre());
			 * detalleJSON.put("modelo", ov.getModelo().getNombre());
			 * detallesJSON.add(detalleJSON); }
			 */

			aux.put("vehiculo", detalleJSON);

			retorno.add(aux);
		}

		return retorno;
	}

	public static JSONObject obtenerDetalleRiskInspector(String solicitudInspeccionId){
		JSONObject retorno=new JSONObject();
		SolicitudInspeccionDAO siDAO=new SolicitudInspeccionDAO();
		SolicitudInspeccion si=siDAO.buscarPorId(solicitudInspeccionId);
		
		Entidad solicitante=si.getSolicitante();
		Direccion direccion = si.getDireccion();
		ObjetoVehiculoDAO ovDAO=new ObjetoVehiculoDAO();
		ObjetoVehiculo ov=ovDAO.buscarPorId(si.getObjetoId());
		
		retorno.put("numero", si.getId());
		retorno.put("fechaSolicitud", Utilitarios.dateToString(si.getFechaSolicitud()));
		retorno.put("cliente", solicitante.getJSON());
		retorno.put("direccion", direccion.getJSON());
		retorno.put("vehiculo", ov.getJSON());
		return retorno;
	}

	public static void citaAgendada(SolicitudInspeccion si){
		EstadoDAO eDAO=new EstadoDAO();
		Estado estado=eDAO.buscarPorNombreClase("Agendada", "Inspeccion");
		si.setEstado(estado);
		SolicitudInspeccionTransaction sit=new SolicitudInspeccionTransaction();
		sit.editar(si);
	}
}
