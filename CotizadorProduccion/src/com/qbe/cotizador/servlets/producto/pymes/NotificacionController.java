package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.pymes.NotificacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAuditoriaGeneralDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeFechaCierreDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.Notificacion;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeFechaCierre;
import com.qbe.cotizador.transaction.producto.pymes.NotificacionTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAsistenciaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeFechaCierreTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeAsistenciaController
 */
@WebServlet("/NotificacionController")
public class NotificacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotificacionController() {
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
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String notificacionId = request.getParameter("notificacionId") == null ? "" : request.getParameter("notificacionId");
			String proceso = request.getParameter("proceso") == null ? "" : request.getParameter("proceso");
			String notificacionCliente = request.getParameter("notificacionCliente") == null ? "" : request.getParameter("notificacionCliente");
			String notificacionUsuario = request.getParameter("notificacionUsuario") == null ? "" : request.getParameter("notificacionUsuario");
			String notificacionInspector = request.getParameter("notificacionInspector") == null ? "" : request.getParameter("notificacionInspector");
			String notificacionEjecutivo = request.getParameter("notificacionEjecutivo") == null ? "" : request.getParameter("notificacionEjecutivo");
			String emailInspector = request.getParameter("emailInspector") == null ? "" : request.getParameter("emailInspector");
			String plantillaNombre = request.getParameter("plantillaNombre") == null ? "" : request.getParameter("plantillaNombre");
			
						
			JSONObject notificacionJSONObject = new JSONObject();
			JSONArray notificacionJSONArray = new JSONArray();
			
			Notificacion notificacion = new Notificacion();
			NotificacionDAO notificacionDAO = new NotificacionDAO();
			NotificacionTransaction notificacionTransaction=new NotificacionTransaction();
			
			
			if(!notificacionId.equals(""))
				notificacion=notificacionDAO.buscarPorId(new BigInteger(notificacionId));
			
			if(!proceso.equals("")){
				notificacion.setProceso(proceso);
			}
			
			if(!notificacionCliente.equals("")){
				if(notificacionCliente.equals("1"))
					notificacion.setNotificacionCliente(true);
				else
					notificacion.setNotificacionCliente(false);
			}
			
			if(!notificacionUsuario.equals("")){
				if(notificacionUsuario.equals("1"))
					notificacion.setNotificacionUsuario(true);
				else
					notificacion.setNotificacionUsuario(false);
			}
			
			if(!notificacionEjecutivo.equals("")){
				if(notificacionEjecutivo.equals("1"))
					notificacion.setNotificacionEjecutivo(true);
				else
					notificacion.setNotificacionEjecutivo(false);
			}
			
			if(!notificacionInspector.equals("")){
				if(notificacionInspector.equals("1"))
					notificacion.setNotificacionInspector(true);
				else
					notificacion.setNotificacionInspector(false);
			}
			
			if(!emailInspector.equals("")){
				notificacion.setEmailInspector(emailInspector);
			}
			
			if(!plantillaNombre.equals("")){
				notificacion.setPlantillaNombre(plantillaNombre);
			}
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<Notificacion> results = notificacionDAO.buscarTodos();
				
				for(Notificacion notif : results){
					notificacionJSONObject.put("notificacionId", notif.getNotificacionId());
					notificacionJSONObject.put("proceso", notif.getProceso());
					notificacionJSONObject.put("notificacionCliente", notif.isNotificacionCliente());
					notificacionJSONObject.put("notificacionUsuario", notif.isNotificacionUsuario());
					notificacionJSONObject.put("notificacionInspector", notif.isNotificacionInspector());
					notificacionJSONObject.put("emailInspector", notif.getEmailInspector());
					notificacionJSONObject.put("plantillaNombre", notif.getPlantillaNombre());
					notificacionJSONArray.add(notificacionJSONObject);
				}
				result.put("notificacionJSONArray", notificacionJSONArray);
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				Notificacion notif = notificacionDAO.buscarPorId(new BigInteger(notificacionId));
				
				result.put("notificacionId", notif.getNotificacionId());
				result.put("proceso", notif.getProceso());
				result.put("notificacionCliente", notif.isNotificacionCliente());
				result.put("notificacionUsuario", notif.isNotificacionUsuario());
				result.put("notificacionInspector", notif.isNotificacionInspector());
				result.put("notificacionEjecutivo", notif.isNotificacionEjecutivo());
				result.put("emailInspector", notif.getEmailInspector());
				result.put("plantillaNombre", notif.getPlantillaNombre());
			}
			
			if(tipoConsulta.equals("crear"))
				notificacionTransaction.crear(notificacion);
			
			if(tipoConsulta.equals("editar"))
				notificacionTransaction.editar(notificacion);
			
			if(tipoConsulta.equals("eliminar"))
				notificacionTransaction.eliminar(notificacion);
			
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
			pymeAuditoriaGeneral.setObjeto(e.getMessage()+"||"+e.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

}
