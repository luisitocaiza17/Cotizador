package com.qbe.cotizador.serviciosExpuestos.riskInspector.implementacion;


import javax.jws.WebService;

import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.dao.inspeccion.UsuarioInspectorDAO;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioInspector;
import com.qbe.cotizador.serviciosExpuestos.riskInspector.controller.SessionController;
import com.qbe.cotizador.serviciosExpuestos.riskInspector.interfaces.WebServiceRiskInspectorInterface;
import com.qbe.cotizador.servlets.inspeccion.TipoAdicionalController;
import com.qbe.cotizador.servlets.inspeccion.TipoComponenteController;
import com.qbe.cotizador.servlets.inspeccion.SolicitudInspeccionController;
import com.qbe.cotizador.servlets.producto.vehiculo.TipoExtraController;

import net.sf.json.JSONObject;

@WebService(endpointInterface = "com.qbe.cotizador.serviciosExpuestos.riskInspector.interfaces.WebServiceRiskInspectorInterface")
public class WebServiceRiskInspectorImpl implements WebServiceRiskInspectorInterface {

	@Override
	public String servicio(String parametros) {
		JSONObject retorno = new JSONObject();

		try {
			JSONObject parametrosJSON = JSONObject.fromObject(parametros);
			String accion = parametrosJSON.getString("accion");
			String appSecret = parametrosJSON.getString("appSecret");

			if (accion == null || appSecret == null)
				throw new Exception("PARAMETROS NECESARIOS NO ENVIADOS");

			// comentado por prueba
			// Utilitarios.validarAppSecretRiskInspector(appSecret);

			if (accion.equals("login")) {
				retorno = SessionController.iniciarSesion(parametrosJSON);
			}

			// DESDE AQUI PONER ACCIONES PARA LAS QUE SE NECESITA TOKEN
			// comentado por prueba
			Usuario usuario = new Usuario();
			String token = parametrosJSON.has("token") ? parametrosJSON.getString("token") : null;
			if (token == null && !accion.equals("login")) {
				throw new Exception("PARAMETROS NECESARIOS NO ENVIADOS");
			} else {
				if (!accion.equals("login"))
					usuario = SessionController.obtenerUsuario(token);
			}

			if (accion.equals("cargarInspeccionesUsuario")) {
				UsuarioInspectorDAO uiDAO = new UsuarioInspectorDAO();
				UsuarioInspector ui = uiDAO.buscarPorUsuario(usuario);
				retorno.put("inspeccionesPendientes",
						SolicitudInspeccionController.buscarPendientesPorUsuarioInspector(ui));
				retorno.put("inspeccionesAgendadas",
						SolicitudInspeccionController.buscarAgendadasPorUsuarioInspector(ui));
			}

			if (accion.equals("cargarDetalleInspeccion")) {
				String numeroSolicitud = parametrosJSON.getString("numeroInspeccion");
				SolicitudInspeccionDAO siDAO = new SolicitudInspeccionDAO();
				SolicitudInspeccion si = siDAO.buscarPorId(numeroSolicitud);
				if (!si.getUsuarioInspector().getUsuario().getId().equals(usuario.getId()))
					throw new Exception("LA INSPECCIÓN NO ESTÁ ASIGNADA AL USUARIO");
				retorno.put("inspeccion", SolicitudInspeccionController.obtenerDetalleRiskInspector(numeroSolicitud));
			}

			if (accion.equals("cargarCatalogos")) {
				retorno.put("tiposExtra", TipoExtraController.getActivos());
				retorno.put("tiposComponente", TipoComponenteController.getActivosVehiculos());
				retorno.put("tiposAdicional", TipoAdicionalController.getActivosVehiculos());
			}

			if (accion.equals("citaAgendada")) {
				String numeroSolicitud = parametrosJSON.getString("numeroInspeccion");
				SolicitudInspeccionDAO siDAO = new SolicitudInspeccionDAO();
				SolicitudInspeccion si = siDAO.buscarPorId(numeroSolicitud);
				if (!si.getUsuarioInspector().getUsuario().getId().equals(usuario.getId()))
					throw new Exception("LA INSPECCIÓN NO ESTÁ ASIGNADA AL USUARIO");
				SolicitudInspeccionController.citaAgendada(si);
			}

			if (accion.equals("citaAgendada")) {
				String numeroSolicitud = parametrosJSON.getString("numeroInspeccion");
				SolicitudInspeccionDAO siDAO = new SolicitudInspeccionDAO();
				SolicitudInspeccion si = siDAO.buscarPorId(numeroSolicitud);
				if (!si.getUsuarioInspector().getUsuario().getId().equals(usuario.getId()))
					throw new Exception("LA INSPECCIÓN NO ESTÁ ASIGNADA AL USUARIO");
				SolicitudInspeccionController.citaAgendada(si);
			}

			retorno.put("success", true);

		} catch (Exception e) {
			retorno.put("success", false);
			retorno.put("mensaje", e.getMessage());
			e.printStackTrace();
		}
		return retorno.toString();

	}
	/*
	 * @Override public String login(String parametros) {
	 * 
	 * JSONObject retorno = new JSONObject();
	 * 
	 * try { JSONObject parametrosJSON = JSONObject.fromObject(parametros);
	 * String appSecret = parametrosJSON.getString("appSecret");
	 * 
	 * if (appSecret == null) throw new Exception(
	 * "PARAMETROS NECESARIOS NO ENVIADOS");
	 * 
	 * // comentado por prueba //
	 * Utilitarios.validarAppSecretRiskInspector(appSecret);
	 * 
	 * retorno = SessionController.iniciarSesion(parametrosJSON);
	 * 
	 * retorno.put("success", true);
	 * 
	 * } catch (Exception e) { retorno.put("success", false);
	 * retorno.put("mensaje", e.getMessage()); e.printStackTrace(); } return
	 * retorno.toString(); }
	 * 
	 * @Override public String cargarInspeccionesPendientesUsuario(String
	 * parametros) { JSONObject retorno = new JSONObject();
	 * 
	 * try { JSONObject parametrosJSON = JSONObject.fromObject(parametros);
	 * String appSecret = parametrosJSON.getString("appSecret");
	 * 
	 * // DESDE AQUI PONER ACCIONES PARA LAS QUE SE NECESITA TOKEN // comentado
	 * por prueba Usuario usuario = new Usuario(); String token =
	 * parametrosJSON.has("token") ? parametrosJSON.getString("token") : null;
	 * if (token == null) { throw new Exception(
	 * "PARAMETROS NECESARIOS NO ENVIADOS"); } else { usuario =
	 * SessionController.obtenerUsuario(token); }
	 * 
	 * UsuarioInspectorDAO uiDAO = new UsuarioInspectorDAO(); UsuarioInspector
	 * ui = uiDAO.buscarPorUsuario(usuario);
	 * retorno.put("inspeccionesPendientes",
	 * SolicitudInspeccionController.buscarPendientesPorUsuarioInspector(ui));
	 * 
	 * retorno.put("success", true);
	 * 
	 * } catch (Exception e) { retorno.put("success", false);
	 * retorno.put("mensaje", e.getMessage()); e.printStackTrace(); } return
	 * retorno.toString();
	 * 
	 * }
	 */

	/*
	 * @Override public byte[] obtenerFirmasDigitalesParametros(String sucursal,
	 * String entidad, String ramo) {
	 * 
	 * List<byte[]> firmaDigital =
	 * MantenimientoDAO.buscarFirmasDigitalesParametros(sucursal, entidad,
	 * ramo);
	 * 
	 * byte[] retorno=new byte[1]; try{ if(firmaDigital.size()>0)
	 * retorno=firmaDigital.get(0); }catch (Exception e) { e.printStackTrace();
	 * } return retorno; }
	 */

}
