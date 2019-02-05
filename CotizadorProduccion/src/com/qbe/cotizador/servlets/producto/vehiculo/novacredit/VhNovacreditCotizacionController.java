package com.qbe.cotizador.servlets.producto.vehiculo.novacredit;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditCotizacionDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditCotizacionXPeriodoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditPeriodoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditTasaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditTipoCoberturaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditTipoTasaDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Color;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoCobertura;
import com.qbe.cotizador.model.TipoDireccion;
import com.qbe.cotizador.model.TipoEntidad;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.model.VhNovacreditCotizacion;
import com.qbe.cotizador.model.VhNovacreditCotizacionXPeriodo;
import com.qbe.cotizador.model.VhNovacreditPeriodo;
import com.qbe.cotizador.model.VhNovacreditTasa;
import com.qbe.cotizador.model.VhNovacreditTipoCobertura;
import com.qbe.cotizador.model.VhNovacreditTipoTasa;
import com.qbe.cotizador.model.Zona;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.servlets.entidad.EntidadController;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.novacredit.VhNovacreditCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.novacredit.VhNovacreditCotizacionXPeriodoTransaction;
import com.qbe.cotizador.transaction.producto.vehiculo.ObjetoVehiculoTransaction;
import com.qbe.cotizador.util.Utilitarios;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class VhNovacreditCotizacionController
 */
@WebServlet("/VhNovacreditCotizacionController")
public class VhNovacreditCotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VhNovacreditCotizacionController() {
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
			String numeroEndoso = request.getParameter("numeroEndoso") == null ? ""
					: request.getParameter("numeroEndoso");
			String lugar = request.getParameter("lugar") == null ? "" : request.getParameter("lugar");
			String fecha = request.getParameter("fecha") == null ? "" : request.getParameter("fecha");
			String vigenciaHasta = request.getParameter("vigenciaHasta") == null ? ""
					: request.getParameter("vigenciaHasta");
			String vigenciaDesde = request.getParameter("vigenciaDesde") == null ? ""
					: request.getParameter("vigenciaDesde");
			String identificacion = request.getParameter("identificacion") == null ? ""
					: request.getParameter("identificacion");
			String nombre = request.getParameter("nombres") == null ? "" : request.getParameter("nombres");
			String apellido = request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos");
			String fechaNacimiento = request.getParameter("fechaNacimiento") == null ? ""
					: request.getParameter("fechaNacimiento");
			String callePrincipal = request.getParameter("callePrincipal") == null ? ""
					: request.getParameter("callePrincipal");
			String numeroDireccion = request.getParameter("numeroDireccion") == null ? ""
					: request.getParameter("numeroDireccion");
			String calleSecundaria = request.getParameter("calleSecundaria") == null ? ""
					: request.getParameter("calleSecundaria");
			String telefono = request.getParameter("telefono") == null ? "" : request.getParameter("telefono");
			String celular = request.getParameter("celular") == null ? "" : request.getParameter("celular");
			String correo = request.getParameter("correo") == null ? "" : request.getParameter("correo");
			String modeloTexto = request.getParameter("modeloTexto") == null ? "" : request.getParameter("modeloTexto");
			String marcaId = request.getParameter("marca") == null ? "" : request.getParameter("marca");
			String anio = request.getParameter("anio") == null ? "" : request.getParameter("anio");
			String motor = request.getParameter("motor") == null ? "" : request.getParameter("motor");
			String chasis = request.getParameter("chasis") == null ? "" : request.getParameter("chasis");
			String placa = request.getParameter("placa") == null ? "" : request.getParameter("placa");
			String color = request.getParameter("color") == null ? "" : request.getParameter("color");
			String valorCasco = request.getParameter("valorCasco") == null ? "" : request.getParameter("valorCasco");
			String valorExtras = request.getParameter("valorExtras") == null ? "0"
					: request.getParameter("valorExtras");
			String iva = request.getParameter("iva") == null ? "14" : request.getParameter("iva");
			String periodo = request.getParameter("periodo") == null ? "" : request.getParameter("periodo");
			String tipoCoberturaId = request.getParameter("tipoCobertura") == null ? ""
					: request.getParameter("tipoCobertura");
			String tipoTasaId = request.getParameter("tipoTasa") == null ? "" : request.getParameter("tipoTasa");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? ""
					: request.getParameter("tipoConsulta");
			String fechaDesde = request.getParameter("fechaDesde") == null ? "" : request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta") == null ? "" : request.getParameter("fechaHasta");
			JSONObject VhNovacreditCotizacionJSONObject = new JSONObject();
			JSONArray VhNovacreditCotizacionJSONArray = new JSONArray();

			VhNovacreditCotizacionTransaction VhNovacreditCotizacionTransaction = new VhNovacreditCotizacionTransaction();

			VhNovacreditCotizacion vhNovacreditCotizacion = new VhNovacreditCotizacion();
			VhNovacreditCotizacionDAO VhNovacreditCotizacionDAO = new VhNovacreditCotizacionDAO();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			if (!codigo.equals("")) {
				vhNovacreditCotizacion.setId(codigo);
				vhNovacreditCotizacion = VhNovacreditCotizacionDAO.buscarPorId(codigo);
			}

			if (!numeroEndoso.equals(""))
				vhNovacreditCotizacion.setNumeroEndoso(numeroEndoso);

			if (!fecha.equals(""))
				vhNovacreditCotizacion.setFecha(sdf.parse(fecha));

			if (!lugar.equals(""))
				vhNovacreditCotizacion.setLugar(lugar);

			if (!vigenciaDesde.equals(""))
				vhNovacreditCotizacion.setVigenciaDesde(sdf.parse(vigenciaDesde));

			if (!iva.equals(""))
				vhNovacreditCotizacion.setPorcentajeIva(new Double(iva));

			if (!periodo.equals("")) {
				VhNovacreditPeriodoDAO periodoDAO = new VhNovacreditPeriodoDAO();
				VhNovacreditPeriodo periodoObj = periodoDAO.buscarPorId(periodo);
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(vigenciaDesde));
				c.add(Calendar.YEAR, periodoObj.getAnios()); // number of days
																// to add
				vhNovacreditCotizacion.setVigenciaHasta(c.getTime());
				vhNovacreditCotizacion.setVhNovacreditPeriodo(periodoObj);

			}

			if (!tipoCoberturaId.equals("")) {
				VhNovacreditTipoCoberturaDAO vhNovacreditTipoCoberturaDAO = new VhNovacreditTipoCoberturaDAO();
				VhNovacreditTipoCobertura vhNovacreditTipoCobertura = vhNovacreditTipoCoberturaDAO
						.buscarPorId(tipoCoberturaId);
				vhNovacreditCotizacion.setVhNovacreditTipoCobertura(vhNovacreditTipoCobertura);
			}

			if (!tipoTasaId.equals("")) {
				VhNovacreditTipoTasaDAO vhNovacreditTipoTasaDAO = new VhNovacreditTipoTasaDAO();
				VhNovacreditTipoTasa vhNovacreditTipoTasa = vhNovacreditTipoTasaDAO.buscarPorId(tipoTasaId);
				vhNovacreditCotizacion.setVhNovacreditTipoTasa(vhNovacreditTipoTasa);
			}

			if (tipoConsulta.equals("guardar")) {
				CiudadDAO ciudadDAO = new CiudadDAO();
				Ciudad ciudad = ciudadDAO.buscarPorNombre("CUENCA");
				if (validarExistenciaVehiculo(chasis, motor, vhNovacreditCotizacion.getId()))
					throw new Exception("El vehiculo ingresado ya existe en otra cotizacion!");
				vhNovacreditCotizacion = guardarDatosEntidad(identificacion, nombre, apellido, fechaNacimiento,
						ciudad.getId(), callePrincipal, numeroDireccion, calleSecundaria, telefono, celular, correo,
						vhNovacreditCotizacion);
				vhNovacreditCotizacion = guardarVehiculo(modeloTexto, color, chasis, motor, placa,
						Integer.parseInt(anio), Double.valueOf(valorCasco), Double.valueOf(valorExtras), marcaId,
						vhNovacreditCotizacion);

				// actualizo el estado de la cotizacion a emitido????? revisar
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorNombreClase("Emitida", "CotizacionNovacredit");
				vhNovacreditCotizacion.setEstado(estado);

				if (vhNovacreditCotizacion.getId() == null)
					vhNovacreditCotizacion = VhNovacreditCotizacionTransaction.crear(vhNovacreditCotizacion);
				else
					vhNovacreditCotizacion = VhNovacreditCotizacionTransaction.editar(vhNovacreditCotizacion);

				JSONObject resultado = guardarCotizacionXPeriodo(vhNovacreditCotizacion);
				vhNovacreditCotizacion.setPrimaTotal(resultado.getDouble("primaTotal"));
				vhNovacreditCotizacion.setValorAseguradoTotal(resultado.getDouble("valorAseguradoTotalAcumulado"));
				vhNovacreditCotizacion.setValorScvsTotal(resultado.getDouble("valorSCVSTotal"));
				vhNovacreditCotizacion.setValorSeguroCampesinoTotal(resultado.getDouble("valorSeguroCampesinoTotal"));
				vhNovacreditCotizacion.setValorDerechosEmisionTotal(resultado.getDouble("valorDerechosEmisionTotal"));
				vhNovacreditCotizacion.setValorIvaTotal(resultado.getDouble("valorIVATotal"));
				vhNovacreditCotizacion.setValorTotal(resultado.getDouble("valorTotal"));
				vhNovacreditCotizacion = VhNovacreditCotizacionTransaction.editar(vhNovacreditCotizacion);
				result.put("datos", resultado);
			}

			if (tipoConsulta.equals("encontrarTodos")) {
				List<VhNovacreditCotizacion> results = VhNovacreditCotizacionDAO.buscarTodos();
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					// vhNovacreditCotizacion = results.get(i);
					// VhNovacreditCotizacionJSONObject.put("codigo",
					// vhNovacreditCotizacion.getId());
					// VhNovacreditCotizacionJSONObject.put("codigoEnsurance",
					// vhNovacreditCotizacion.get());
					// VhNovacreditCotizacionJSONObject.put("nombre",
					// vhNovacreditCotizacion.getNombre());
					// VhNovacreditCotizacionJSONObject.put("activo",
					// vhNovacreditCotizacion.getActivo());
					// VhNovacreditCotizacionJSONArray.add(VhNovacreditCotizacionJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoVhNovacreditCotizacion", VhNovacreditCotizacionJSONArray);
			}

			if (tipoConsulta.equals("cargaInicial")) {
				if (vhNovacreditCotizacion.getId() != null && !vhNovacreditCotizacion.getId().equals("")) {

					JSONArray tiposCoberturas = new JSONArray();
					VhNovacreditTipoCoberturaDAO vhNovacreditTipoCoberturaDAO = new VhNovacreditTipoCoberturaDAO();
					List<VhNovacreditTipoCobertura> results = vhNovacreditTipoCoberturaDAO.buscarTodos();
					int i = 0;
					for (i = 0; i < results.size(); i++) {
						VhNovacreditTipoCobertura aux = results.get(i);
						JSONObject tipoCobertura = new JSONObject();
						tipoCobertura.put("id", aux.getId());
						tipoCobertura.put("nombre", aux.getNombre());
						tiposCoberturas.add(tipoCobertura);
					}
					JSONArray tiposTasa = new JSONArray();
					VhNovacreditTipoTasaDAO vhNovacreditTipoTasaDAO = new VhNovacreditTipoTasaDAO();
					List<VhNovacreditTipoTasa> results2 = vhNovacreditTipoTasaDAO.buscarTodos();
					int j = 0;
					for (j = 0; j < results2.size(); j++) {
						VhNovacreditTipoTasa aux = results2.get(j);
						JSONObject tipoTasa = new JSONObject();
						tipoTasa.put("id", aux.getId());
						tipoTasa.put("nombre", aux.getNombre());
						tiposTasa.add(tipoTasa);
					}
					JSONArray periodos = new JSONArray();
					VhNovacreditPeriodoDAO vhNovacreditPeriodoDAO = new VhNovacreditPeriodoDAO();
					List<VhNovacreditPeriodo> results3 = vhNovacreditPeriodoDAO.buscarTodos();
					int k = 0;
					for (j = 0; j < results3.size(); j++) {
						VhNovacreditPeriodo aux = results3.get(j);
						JSONObject periodoJson = new JSONObject();
						periodoJson.put("id", aux.getId());
						periodoJson.put("anios", aux.getAnios());
						periodos.add(periodoJson);
					}

					result.put("periodos", periodos);
					result.put("tiposTasa", tiposTasa);
					result.put("tiposCoberturas", tiposCoberturas);
					result.put("id", vhNovacreditCotizacion.getId());
					result.put("numeroEndoso", vhNovacreditCotizacion.getNumeroEndoso());
					result.put("lugar", vhNovacreditCotizacion.getLugar());
					result.put("fecha", sdf.format(vhNovacreditCotizacion.getFecha()));

					Entidad ent = vhNovacreditCotizacion.getEntidad();
					result.put("cedula", ent.getIdentificacion());
					result.put("nombres", ent.getNombres());
					result.put("apellidos", ent.getApellidos());
					result.put("fechaNacimiento", sdf.format(vhNovacreditCotizacion.getFechaNacimientoEntidad()));
					result.put("telefono", ent.getTelefono());
					result.put("celular", ent.getCelular());
					result.put("correo", ent.getMail());

					DireccionDAO direccionDAO = new DireccionDAO();

					Direccion dir = direccionDAO.buscarPorId(vhNovacreditCotizacion.getDireccionId());
					result.put("callePrincipal", dir.getCallePrincipal());
					result.put("numeroDireccion", dir.getNumero());
					result.put("calleSecundaria", dir.getCalleSecundaria());
					result.put("vigenciaDesde", sdf.format(vhNovacreditCotizacion.getVigenciaDesde()));
					result.put("periodoId", vhNovacreditCotizacion.getVhNovacreditPeriodo().getId());
					ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
					ObjetoVehiculo ov = ovDAO.buscarPorId(vhNovacreditCotizacion.getObjetoId());
					result.put("colorId", ov.getColor().getId());
					result.put("marcaId", ov.getModelo().getMarca().getId());
					result.put("modeloId", ov.getModelo().getId());
					result.put("modeloTexto", vhNovacreditCotizacion.getModeloTexto());
					result.put("anio", ov.getAnioFabricacion());
					result.put("motor", ov.getMotor());
					result.put("chasis", ov.getChasis());
					result.put("tipoCoberturaId", vhNovacreditCotizacion.getVhNovacreditTipoCobertura().getId());
					result.put("tipoTasaId", vhNovacreditCotizacion.getVhNovacreditTipoTasa().getId());
					result.put("valorCasco", vhNovacreditCotizacion.getValorCasco());
					result.put("valorExtras", vhNovacreditCotizacion.getValorExtras());
					VhNovacreditCotizacionXPeriodoDAO cxpDAO = new VhNovacreditCotizacionXPeriodoDAO();
					List<VhNovacreditCotizacionXPeriodo> cxps = cxpDAO.buscarPorCotazacion(vhNovacreditCotizacion);
					JSONArray cotizacionesXPerdiodo = new JSONArray();
					for (VhNovacreditCotizacionXPeriodo cxp : cxps) {
						cotizacionesXPerdiodo.add(cxp.obtenerJSON());
					}

					result.put("cotizacionXPeriodo", cotizacionesXPerdiodo);
					result.put("primaTotal", Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getPrimaTotal()));
					result.put("valorAseguradoTotalAcumulado",
							Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorAseguradoTotal()));
					result.put("valorSCVSTotal",
							Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorScvsTotal()));
					result.put("valorSeguroCampesinoTotal",
							Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorSeguroCampesinoTotal()));
					result.put("valorDerechosEmisionTotal",
							Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorDerechosEmisionTotal()));
					result.put("valorIVATotal",
							Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorIvaTotal()));
					result.put("valorTotal", Utilitarios.redondear2Decimales(vhNovacreditCotizacion.getValorTotal()));

					ModeloDAO modeloDAO = new ModeloDAO();
					List<Modelo> modelos = modeloDAO.buscarPorMarca(ov.getModelo().getMarca().getId());
					JSONArray modelosJSON = new JSONArray();
					for (Modelo modelo : modelos) {
						modelosJSON.add(modelo.getJSON());
					}
					result.put("modelos", modelosJSON);

					MarcaDAO marcaDAO = new MarcaDAO();
					List<Marca> marcas = marcaDAO.buscarActivos();
					JSONArray marcasJSON = new JSONArray();
					for (Marca marca : marcas) {
						marcasJSON.add(marca.getJSON());
					}
					result.put("marcas", marcasJSON);

					ColorDAO colorDAO = new ColorDAO();
					List<Color> colors = colorDAO.buscarActivos();
					JSONArray coloresJSON = new JSONArray();
					for (Color colorObj : colors) {
						coloresJSON.add(colorObj.getJSON());
					}
					result.put("colores", coloresJSON);
				} else {
					JSONArray tiposCoberturas = new JSONArray();
					VhNovacreditTipoCoberturaDAO vhNovacreditTipoCoberturaDAO = new VhNovacreditTipoCoberturaDAO();
					List<VhNovacreditTipoCobertura> results = vhNovacreditTipoCoberturaDAO.buscarTodos();
					int i = 0;
					for (i = 0; i < results.size(); i++) {
						VhNovacreditTipoCobertura aux = results.get(i);
						JSONObject tipoCobertura = new JSONObject();
						tipoCobertura.put("id", aux.getId());
						tipoCobertura.put("nombre", aux.getNombre());
						tiposCoberturas.add(tipoCobertura);
					}
					JSONArray tiposTasa = new JSONArray();
					VhNovacreditTipoTasaDAO vhNovacreditTipoTasaDAO = new VhNovacreditTipoTasaDAO();
					List<VhNovacreditTipoTasa> results2 = vhNovacreditTipoTasaDAO.buscarTodos();
					int j = 0;
					for (j = 0; j < results2.size(); j++) {
						VhNovacreditTipoTasa aux = results2.get(j);
						JSONObject tipoTasa = new JSONObject();
						tipoTasa.put("id", aux.getId());
						tipoTasa.put("nombre", aux.getNombre());
						tiposTasa.add(tipoTasa);
					}
					JSONArray periodos = new JSONArray();
					VhNovacreditPeriodoDAO vhNovacreditPeriodoDAO = new VhNovacreditPeriodoDAO();
					List<VhNovacreditPeriodo> results3 = vhNovacreditPeriodoDAO.buscarTodos();
					int k = 0;
					for (j = 0; j < results3.size(); j++) {
						VhNovacreditPeriodo aux = results3.get(j);
						JSONObject periodoJson = new JSONObject();
						periodoJson.put("id", aux.getId());
						periodoJson.put("anios", aux.getAnios());
						periodos.add(periodoJson);
					}
					result.put("periodos", periodos);
					result.put("tiposTasa", tiposTasa);
					result.put("tiposCoberturas", tiposCoberturas);
				}

			}

			if (tipoConsulta.equals("consultarPorParametros")) {
				List<VhNovacreditCotizacion> resultado = VhNovacreditCotizacionDAO.buscarPorParametros(fechaDesde,
						fechaHasta, numeroEndoso, identificacion);
				JSONArray cotizaciones = new JSONArray();
				for (VhNovacreditCotizacion cotizacion : resultado) {
					JSONObject aux = new JSONObject();
					VhNovacreditTipoCobertura tc = cotizacion.getVhNovacreditTipoCobertura();
					Entidad ent = cotizacion.getEntidad();
					aux.put("id", cotizacion.getId());
					aux.put("contenedor", tc.getPoliza());
					aux.put("numeroEndoso", cotizacion.getNumeroEndoso());
					aux.put("nombreCompleto", ent.getNombreCompleto());
					aux.put("identificacion", ent.getIdentificacion());
					aux.put("vigenciaDesde", sdf.format(cotizacion.getVigenciaDesde()));
					aux.put("vigenciaHasta", sdf.format(cotizacion.getVigenciaHasta()));
					ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
					ObjetoVehiculo ov = ovDAO.buscarPorId(cotizacion.getObjetoId());

					aux.put("marca", (ov.getModelo().getMarca().getNombre()));
					aux.put("modelo", (ov.getModelo().getNombre()));
					aux.put("motor", (ov.getMotor()));
					aux.put("chasis", (ov.getChasis()));
					aux.put("anio", (ov.getAnioFabricacion()));
					aux.put("estado", (cotizacion.getEstado().getNombre()));
					aux.put("cargaInicial", cotizacion.isCargaInicial());
					cotizaciones.add(aux);
					// cotizacion.get
				}
				result.put("listadoCotizacion", cotizaciones);

			}

			if (tipoConsulta.equals("cancelar")) {
				cancelarCotizacion(codigo);
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

	public static JSONObject cargarDatosEntidad(String identificacion) {
		JSONObject retorno = new JSONObject();
		// Obtener datos del usuario desde ensurance
		EntidadDAO entidadDAO = new EntidadDAO();
		WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
		String resultado = webService.getWebServiceCotizadorImplPort().datosUsuario(identificacion);
		JSONObject entidadEnsurance = EntidadController.cargarPorIdentificacionWS(resultado);
		JSONObject agenteJson = new JSONObject();
		Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
		DireccionDAO dDAO = new DireccionDAO();
		JSONObject datosFactura = new JSONObject();
		if (entidad != null && entidad.getId() != null) {
			entidadEnsurance.put("codigo", entidad.getId());
			entidadEnsurance.put("mail", entidad.getMail());
			if (entidadEnsurance.getInt("excepcion") == 0) {
				entidadEnsurance.put("telefono", entidad.getTelefono());
				entidadEnsurance.put("celular", entidad.getCelular());
				entidadEnsurance.put("email", entidad.getMail());
				entidadEnsurance.put("nombre", entidad.getNombres());
				entidadEnsurance.put("nombreCompleto", entidad.getNombreCompleto());
				entidadEnsurance.put("apellido", entidad.getApellidos());
				entidadEnsurance.put("identificacion", entidad.getIdentificacion());
				entidadEnsurance.put("tipoIdentificacion", entidad.getTipoIdentificacion().getId());
				entidadEnsurance.put("tipoIdentificacionNombre", entidad.getTipoIdentificacion().getNombre());

				if (entidad.getEntEnsurance() == null)
					entidadEnsurance.put("entidadIdEnsurance", "");
				else
					entidadEnsurance.put("entidadIdEnsurance", entidad.getEntEnsurance());

				if (entidadEnsurance != null && entidadEnsurance.getString("direccionId") != null
						&& !entidadEnsurance.getString("direccionId").trim().equals("")) {
					JSONObject direccionJson = entidadEnsurance.getJSONObject("direccion");

					if (direccionJson.getString("zona").equals("U"))
						datosFactura.put("zona", 1);
					if (direccionJson.getString("zona").equals("R"))
						datosFactura.put("zona", 2);

					// eliminar los 0 a la izquierda
					datosFactura.put("ciudad", direccionJson.getString("ciudadId").trim().replaceFirst("^0+(?!$)", ""));
					datosFactura.put("provincia",
							direccionJson.getString("provinciaId").trim().replaceFirst("^0+(?!$)", ""));
					datosFactura.put("parroquia",
							direccionJson.getString("parroquiaId").trim().replaceFirst("^0+(?!$)", ""));
					datosFactura.put("canton", direccionJson.getString("cantonId").trim().replaceFirst("^0+(?!$)", ""));
					datosFactura.put("callePrincipal", direccionJson.getString("nombrePrincipal"));
					datosFactura.put("calleSecundaria", direccionJson.getString("nombreSecundaria"));
					datosFactura.put("numero", direccionJson.getString("numero"));

				} else {
					List<Direccion> direcciones = dDAO.buscarPorEntidadId(entidad);

					if (direcciones.size() > 0) {
						if (direcciones.get(0) != null) {
							Direccion direccion = direcciones.get(0);

							if (direcciones.get(0).getZona() != null)
								datosFactura.put("zona", direcciones.get(0).getZona().getId());

							if (direccion.getZona().getId().equals("1")) { // urbana

								if (direcciones.get(0).getCiudad() != null)
									datosFactura.put("ciudad", direcciones.get(0).getCiudad().getId());

								if (direcciones.get(0).getCiudad().getProvincia() != null)
									datosFactura.put("provincia",
											direcciones.get(0).getCiudad().getProvincia().getId());

								if (direcciones.get(0).getCallePrincipal() != null)
									datosFactura.put("callePrincipal", direcciones.get(0).getCallePrincipal());

								if (direcciones.get(0).getCalleSecundaria() != null)
									datosFactura.put("calleSecundaria", direcciones.get(0).getCalleSecundaria());

								if (direcciones.get(0).getNumero() != null)
									datosFactura.put("numero", direcciones.get(0).getNumero());
							}

							if (direccion.getZona().getId().equals("2")) { // rural

								if (direcciones.get(0).getParroquia() != null)
									datosFactura.put("parroquia", direcciones.get(0).getParroquia().getId());

								if (direcciones.get(0).getParroquia() != null
										&& direcciones.get(0).getParroquia().getCanton() != null)
									datosFactura.put("canton", direcciones.get(0).getParroquia().getCanton().getId());

								if (direcciones.get(0).getParroquia() != null
										&& direcciones.get(0).getParroquia().getCanton().getProvincia() != null)
									datosFactura.put("provincia",
											direcciones.get(0).getParroquia().getCanton().getProvincia().getId());

								if (direcciones.get(0).getCallePrincipal() != null)
									datosFactura.put("callePrincipal", direcciones.get(0).getCallePrincipal());

								if (direcciones.get(0).getCalleSecundaria() != null)
									datosFactura.put("calleSecundaria", direcciones.get(0).getCalleSecundaria());

								if (direcciones.get(0).getNumero() != null)
									datosFactura.put("numero", direcciones.get(0).getNumero());

							}
							retorno.put("datosFactura", datosFactura);
						}
					}

				}
			}
		}

		else {
			if (entidadEnsurance != null && entidadEnsurance.getString("direccionId") != null
					&& !entidadEnsurance.getString("direccionId").trim().equals("")) {
				JSONObject direccionJson = entidadEnsurance.getJSONObject("direccion");

				if (direccionJson.getString("zona").equals("U"))
					datosFactura.put("zona", 1);
				if (direccionJson.getString("zona").equals("R"))
					datosFactura.put("zona", 2);

				// eliminar los 0 a la izquierda
				datosFactura.put("ciudad", direccionJson.getString("ciudadId").trim().replaceFirst("^0+(?!$)", ""));
				datosFactura.put("provincia",
						direccionJson.getString("provinciaId").trim().replaceFirst("^0+(?!$)", ""));
				datosFactura.put("parroquia",
						direccionJson.getString("parroquiaId").trim().replaceFirst("^0+(?!$)", ""));
				datosFactura.put("canton", direccionJson.getString("cantonId").trim().replaceFirst("^0+(?!$)", ""));
				datosFactura.put("callePrincipal", direccionJson.getString("nombrePrincipal"));
				datosFactura.put("calleSecundaria", direccionJson.getString("nombreSecundaria"));
				datosFactura.put("numero", direccionJson.getString("numero"));

				retorno.put("datosFactura", datosFactura);
			}
		}
		return retorno;
	}

	public VhNovacreditCotizacion guardarDatosEntidad(String identificacion, String nombre, String apellido,
			String fechaNacimiento, String ciudadId, String callePrincipal, String numeroDireccion,
			String calleSecundaria, String telefono, String celular, String correoElectronico,
			VhNovacreditCotizacion cotizacion) throws Exception {
		Entidad entidad = new Entidad();
		EntidadDAO entidadDAO = new EntidadDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		EntidadTransaction entidadTransaccion = new EntidadTransaction();

		entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
		// if (entidad.getId() == null) {// solo actualizo nombre si no existe,
		// si
		// ya
		// existe actualizo los otros datos, no
		// edito
		// nada de la entidad me afecta a otros
		// sistemas
		entidad.setIdentificacion(identificacion);
		entidad.setNombres(nombre);
		entidad.setApellidos(apellido);
		entidad.setNombreCompleto(apellido + " " + nombre);
		TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
		TipoEntidad tipoEntidad = tipoEntidadDAO.buscarPorId("1");// siempre
																	// natural

		entidad.setTipoEntidad(tipoEntidad);

		TipoIdentificacionDAO tipoIdentificacionDAO = new TipoIdentificacionDAO();
		TipoIdentificacion tipoIdentificacion = tipoIdentificacionDAO.buscarPorId("1");// siempre
																						// cedula

		entidad.setTipoIdentificacion(tipoIdentificacion);
		// }

		entidad.setCelular(celular);
		entidad.setTelefono(telefono);
		entidad.setMail(correoElectronico);

		if (entidad.getId() == null) {
			entidad = entidadTransaccion.crear(entidad);
		} else
			entidad = entidadTransaccion.editar(entidad);
		// creo otra direccion

		Direccion direccion = new Direccion();
		DireccionDAO direccionDAO = new DireccionDAO();
		DireccionTransaction direccionTransaccion = new DireccionTransaction();
		direccion.setCallePrincipal(callePrincipal);
		direccion.setCalleSecundaria(calleSecundaria);
		direccion.setNumero(numeroDireccion);
		direccion.setEntidad(entidad);
		direccion.setEsCobro(false);
		// direccion siempre guardo como urbana
		ZonaDAO zonaDAO = new ZonaDAO();
		Zona zona = zonaDAO.buscarPorId("1");
		direccion.setZona(zona);
		TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
		TipoDireccion tipoDireccion = tipoDireccionDAO.buscarPorId("4");// direccion
																		// novacredit
		direccion.setTipoDireccion(tipoDireccion);
		direccion = direccionTransaccion.crear(direccion);

		// se guarda la fecha de nacimiento en la cotizacion xq no se tiene en
		// entidad
		cotizacion.setFechaNacimientoEntidad(sdf.parse(fechaNacimiento));
		cotizacion.setDireccionId(direccion.getId());
		cotizacion.setEntidad(entidad);
		// vhNovacreditCotizacionTransaction.editar(cotizacion);
		return cotizacion;

	}

	public VhNovacreditCotizacion guardarVehiculo(String modeloTexto, String colorId, String chasis, String motor,
			String placa, int anio, Double valorCasco, Double valorExtras, String marcaId,
			VhNovacreditCotizacion cotizacion) {

		ObjetoVehiculoTransaction objetoVehiculoTransaction = new ObjetoVehiculoTransaction();
		ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo objetoVehiculo = new ObjetoVehiculo();
		objetoVehiculo.setAnioFabricacion(Integer.toString(anio));
		objetoVehiculo.setChasis(chasis);
		objetoVehiculo.setMotor(motor);
		objetoVehiculo.setPlaca(placa);
		objetoVehiculo.setSumaAsegurada(valorCasco + valorExtras);

		VhNovacreditTipoCobertura vhntc = cotizacion.getVhNovacreditTipoCobertura();
		objetoVehiculo.setTipoUso(vhntc.getTipoUso());
		objetoVehiculo.setTipoVehiculo(vhntc.getTipoVehiculo());

		ColorDAO colorDAO = new ColorDAO();
		Color color = colorDAO.buscarPorId(colorId);
		objetoVehiculo.setColor(color);

		ModeloDAO modeloDAO = new ModeloDAO();
		List<Modelo> modelos = modeloDAO.buscarPorMarca(marcaId);
		// grabo un modelo cualquiera!, el modelo valido se cuarda en
		// vh_novacredit_cotizacion.
		objetoVehiculo.setModelo(modelos.get(0));

		objetoVehiculo.setDispositivoRastreo(false);

		int antiguedad = Utilitarios.actualDate().getYear() - anio;
		if (antiguedad < 0)
			antiguedad = 0;

		objetoVehiculo.setAntiguedadVh(Integer.toString(antiguedad));

		cotizacion.setValorCasco(valorCasco);
		cotizacion.setValorExtras(valorExtras);
		cotizacion.setModeloTexto(modeloTexto);
		// objetoVehiculoDAO.buscarPorChasis(chasis);

		SucursalDAO sucursalDAO = new SucursalDAO();
		Sucursal sucursal = sucursalDAO.buscarPorNombre("CUENCA");// siempre va
																	// cuenca

		objetoVehiculo.setSucursalId(sucursal.getId());
		objetoVehiculo = objetoVehiculoTransaction.crear(objetoVehiculo);
		cotizacion.setObjetoId(objetoVehiculo.getId());

		return cotizacion;
	}

	public boolean validarExistenciaVehiculo(String chasis, String motor, String cotizacionActualId) {
		boolean existe = false;

		ObjetoVehiculoDAO ovDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo ov = ovDAO.buscarPorChasis(chasis);
		if (ov.getId() == null)
			ov = ovDAO.buscarPorMotor(motor);

		if (cotizacionActualId == null)
			cotizacionActualId = "";

		VhNovacreditCotizacionDAO vhNovacreditCotizacionDAO = new VhNovacreditCotizacionDAO();
		List<VhNovacreditCotizacion> cotizaciones = vhNovacreditCotizacionDAO.buscarPorObjetoId(ov.getId());
		for (VhNovacreditCotizacion cotizacion : cotizaciones) {
			Date fechaActual = Utilitarios.actualDate();
			if (cotizacion.getVigenciaHasta().after(fechaActual) && cotizacion.getVigenciaDesde().before(fechaActual)
					&& !cotizacion.getId().equals(cotizacionActualId)
					&& !cotizacion.getEstado().getNombre().equals("Cancelada")) {
				existe = true;
				break;
			}
		}

		return existe;
	}

	public static JSONObject cargarDatos(VhNovacreditCotizacion vhNovacreditCotizacion) {
		JSONObject retorno = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		retorno.put("numeroEndoso", vhNovacreditCotizacion.getNumeroEndoso());
		retorno.put("vigenciaDesde", sdf.format(vhNovacreditCotizacion.getVigenciaDesde()));
		retorno.put("vigenciaHasta", sdf.format(vhNovacreditCotizacion.getVigenciaHasta()));
		Entidad entidad = vhNovacreditCotizacion.getEntidad();
		retorno.put("nombre", entidad.getNombres());
		retorno.put("apellido", entidad.getApellidos());
		retorno.put("identificacion", entidad.getIdentificacion());
		retorno.put("fechaNacimiento", sdf.format(vhNovacreditCotizacion.getFechaNacimientoEntidad()));
		retorno.put("celular", entidad.getCelular());
		retorno.put("telefono", entidad.getTelefono());
		retorno.put("correo", entidad.getMail());

		DireccionDAO direccionDAO = new DireccionDAO();
		Direccion direccion = direccionDAO.buscarPorId(vhNovacreditCotizacion.getDireccionId());
		retorno.put("callePrincipal", direccion.getCallePrincipal());
		retorno.put("numeroDireccion", direccion.getNumero());
		retorno.put("calleSecundaria", direccion.getCalleSecundaria());

		ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo ov = objetoVehiculoDAO.buscarPorId(vhNovacreditCotizacion.getObjetoId());
		retorno.put("modeloId", ov.getModelo().getId());
		retorno.put("marcaId", ov.getModelo().getMarca().getId());
		retorno.put("anio", ov.getAnioFabricacion());
		retorno.put("motor", ov.getMotor());
		retorno.put("chasis", ov.getChasis());
		retorno.put("placa", ov.getPlaca());
		retorno.put("colorId", ov.getColor().getId());
		retorno.put("valorCasco", vhNovacreditCotizacion.getValorCasco());
		retorno.put("valorExtras", vhNovacreditCotizacion.getValorExtras());
		retorno.put("tiempoCredito", vhNovacreditCotizacion.getVhNovacreditPeriodo().getAnios());
		retorno.put("tipoCobertura", vhNovacreditCotizacion.getVhNovacreditTipoCobertura().getId());
		retorno.put("tipoTasa", vhNovacreditCotizacion.getVhNovacreditTipoTasa().getId());

		return retorno;
	}

	public JSONObject guardarCotizacionXPeriodo(VhNovacreditCotizacion cotizacion) {
		JSONObject retorno = new JSONObject();
		VhNovacreditCotizacionXPeriodoTransaction cxpt = new VhNovacreditCotizacionXPeriodoTransaction();
		VhNovacreditCotizacionXPeriodoDAO cxpDAO = new VhNovacreditCotizacionXPeriodoDAO();

		// elimino todas las anteriores
		List<VhNovacreditCotizacionXPeriodo> cxpEliminar = cxpDAO.buscarPorCotazacion(cotizacion);
		for (VhNovacreditCotizacionXPeriodo cxpe : cxpEliminar) {
			cxpt.eliminar(cxpe);
		}
		// obtengo el periodo del credito
		VhNovacreditPeriodo periodo = cotizacion.getVhNovacreditPeriodo();
		double anios = new Double(periodo.getAnios());
		VhNovacreditPeriodoDAO vhNovacreditPeriodoDAO = new VhNovacreditPeriodoDAO();

		double valorAseguradoTotal = cotizacion.getValorCasco() + cotizacion.getValorExtras();

		// obtengo la tasa
		VhNovacreditTasaDAO vhNovacreditTasaDAO = new VhNovacreditTasaDAO();
		VhNovacreditTasa vhNovacreditTasa = vhNovacreditTasaDAO.buscarPorTipoCoberturaTipoTasa(
				cotizacion.getVhNovacreditTipoCobertura(), cotizacion.getVhNovacreditTipoTasa());
		double tasa = vhNovacreditTasa.getTasa();

		VariableSistemaDAO variableSistemaDAO = new VariableSistemaDAO();
		// variables para calcular impuestos
		double derechosEmision = new Double(
				variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_NOVACREDIT").getValor());
		double porcentajeSCVS = new Double(variableSistemaDAO.buscarPorNombre("PORCENTAJE_SCVS_NOVACREDIT").getValor());
		double seguroCampesino = new Double(
				variableSistemaDAO.buscarPorNombre("SEGURO_CAMPESINO_NOVACREDIT").getValor());
		double porcentajeIva = cotizacion.getPorcentajeIva();

		// variables para acumular valores
		double primaTotal = 0.0;
		double valorAseguradoTotalAcumulado = 0.0;
		double valorSCVSTotal = 0.0;
		double valorSeguroCampesinoTotal = 0.0;
		double valorDerechosEmisionTotal = 0.0;
		double valorIVATotal = 0.0;
		double valorTotal = 0.0;

		JSONArray cotizacionXPeriodo = new JSONArray();
		java.util.Date vigenciaDesde = cotizacion.getVigenciaDesde();
		java.util.Date vigenciaHasta = new java.util.Date();

		for (int i = 1; i <= anios; i++) {
			VhNovacreditPeriodo periodoAux = vhNovacreditPeriodoDAO.buscarPorAnio(i);
			VhNovacreditCotizacionXPeriodo cxp = new VhNovacreditCotizacionXPeriodo();
			cxp.setVigenciaDesde(vigenciaDesde);
			Calendar c = Calendar.getInstance();
			c.setTime(cotizacion.getVigenciaDesde());
			c.add(Calendar.YEAR, i);
			vigenciaHasta = c.getTime();
			vigenciaDesde = c.getTime();
			cxp.setVigenciaHasta(vigenciaHasta);
			cxp.setVhNovacreditCotizacion(cotizacion);
			cxp.setVhNovacreditPeriodo(periodoAux);
			// deprecio el valor dependiendo del periodo
			double valorAsegurado = Utilitarios.redondear2Decimales(periodoAux.getDepreciacion() * valorAseguradoTotal);
			cxp.setValorAsegurado(valorAsegurado);
			valorAseguradoTotalAcumulado += valorAsegurado;
			// para el proximo periodo deprecio en referencia al valor del
			// periodo actual
			valorAseguradoTotal = valorAsegurado;

			double valorPrima = Utilitarios.redondear2Decimales(valorAsegurado * (tasa / 100));
			cxp.setValorPrima(valorPrima);
			primaTotal += valorPrima;

			double valorSCVS = Utilitarios.redondear2Decimales(valorPrima * porcentajeSCVS);
			cxp.setValorScvs(valorSCVS);
			valorSCVSTotal += valorSCVS;

			double valorSeguroCampesino = Utilitarios.redondear2Decimales(valorPrima * seguroCampesino);
			cxp.setValorSeguroCampesino(valorSeguroCampesino);
			valorSeguroCampesinoTotal += valorSeguroCampesino;

			cxp.setDerechosEmision(derechosEmision);
			valorDerechosEmisionTotal += derechosEmision;

			double subtotal = valorPrima + valorSCVS + valorSeguroCampesino + derechosEmision;
			double valorIVA = Utilitarios.redondear2Decimales(subtotal * (porcentajeIva / 100));
			cxp.setIva(valorIVA);
			valorIVATotal += valorIVA;

			double total = Utilitarios.redondear2Decimales(subtotal + valorIVA);
			cxp.setTotal(total);
			valorTotal += total;

			cxp = cxpt.crear(cxp);

			cotizacionXPeriodo.add(cxp.obtenerJSON());
		}

		retorno.put("cotizacionXPeriodo", cotizacionXPeriodo);
		retorno.put("primaTotal", Utilitarios.redondear2Decimales(primaTotal));
		retorno.put("valorAseguradoTotalAcumulado", Utilitarios.redondear2Decimales(valorAseguradoTotalAcumulado));
		retorno.put("valorSCVSTotal", Utilitarios.redondear2Decimales(valorSCVSTotal));
		retorno.put("valorSeguroCampesinoTotal", Utilitarios.redondear2Decimales(valorSeguroCampesinoTotal));
		retorno.put("valorDerechosEmisionTotal", Utilitarios.redondear2Decimales(valorDerechosEmisionTotal));
		retorno.put("valorIVATotal", Utilitarios.redondear2Decimales(valorIVATotal));
		retorno.put("valorTotal", Utilitarios.redondear2Decimales(valorTotal));
		retorno.put("id", cotizacion.getId());

		return retorno;
	}

	public void cancelarCotizacion(String id) {
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscarPorNombreClase("Cancelada", "CotizacionNovacredit");

		VhNovacreditCotizacionDAO cDAO = new VhNovacreditCotizacionDAO();
		VhNovacreditCotizacion vhNovacreditCotizacion = cDAO.buscarPorId(id);
		vhNovacreditCotizacion.setEstado(estado);
		VhNovacreditCotizacionTransaction vhNovacreditCotizacionTransaction = new VhNovacreditCotizacionTransaction();
		vhNovacreditCotizacionTransaction.editar(vhNovacreditCotizacion);
	}

}
