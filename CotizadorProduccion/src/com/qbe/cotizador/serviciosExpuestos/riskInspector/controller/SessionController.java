package com.qbe.cotizador.serviciosExpuestos.riskInspector.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import com.opensymphony.module.sitemesh.mapper.SessionDecoratorMapper;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.seguridad.CredencialDAO;
import com.qbe.cotizador.dao.seguridad.SessionDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Credencial;
import com.qbe.cotizador.model.Session;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.seguridad.SessionTransaction;
import com.qbe.cotizador.util.Utilitarios;

import net.sf.json.JSONObject;

public class SessionController {

	public static JSONObject iniciarSesion(JSONObject parametros) throws Exception {
		JSONObject retorno = new JSONObject();

		String user = parametros.getString("user");
		String password = parametros.getString("password");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorLogin(user);
		VariableSistemaDAO vsDAO = new VariableSistemaDAO();

		if (usuario == null || usuario.getId() == null)
			throw new Exception("EL USUARIO NO EXISTE");

		String encriptarClave = Utilitarios.encriptacionClave(password);
		CredencialDAO credencialDAO = new CredencialDAO();
		Credencial credencial = credencialDAO.buscarPorUsuarioId(usuario);

		if (!credencial.getClave().equals(encriptarClave))
			throw new Exception("CLAVE INCORRECTA");
		else {
			// crear token
			// elimino todas las sesiones que existan del usuario apra prevenir
			// q pueda ingrrsar de mas de un dispositivo al mismo tiempo
			VariableSistema vs = vsDAO.buscarPorNombre("MINUTOS_DURACION_SESSION_RISKINSPECTOR");
			SessionDAO sessionDAO = new SessionDAO();
			SessionTransaction sessionTransaction = new SessionTransaction();
			sessionDAO.eliminarPorUsuario(usuario);
			Calendar cal = Calendar.getInstance();
			Date actual = cal.getTime();
			cal.add(Calendar.MINUTE, Integer.parseInt(vs.getValor()));
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			Date masMinutos = cal.getTime();
			Session session = new Session(new Timestamp(masMinutos.getTime()), new Timestamp(actual.getTime()), token,
					usuario);
			sessionTransaction.crear(session);
			retorno.put("horaDesde", Utilitarios.datetimeToString(actual));
			retorno.put("horaHasta", Utilitarios.datetimeToString(masMinutos));
			retorno.put("token", token);
		}

		return retorno;
	}
	
	public static Usuario obtenerUsuario(String token) throws Exception{
		SessionDAO sessionDAO = new SessionDAO();
		Session session=sessionDAO.buscarPorToken(token);
		if(session==null||session.getId()==null)
			throw new Exception("TOKEN NO VALIDO");
		
		Date date= new Date();
		Timestamp actual=new Timestamp(date.getTime());
		if(actual.after(session.getHoraInicio()) && actual.before(session.getHoraFin())){
			if(session.getUsuario().getActivo())
				return session.getUsuario();
			else
				throw new Exception("USUARIO NO ACTIVO");
		}
		else{
			throw new Exception("TOKEN VECIDO");
		}
	}

}
