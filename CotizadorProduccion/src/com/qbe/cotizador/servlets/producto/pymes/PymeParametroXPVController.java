package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;
import com.qbe.cotizador.transaction.cotizacion.PuntoVentaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeParametroXPuntoVentaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class PymeParametroXPVController
 */
@WebServlet("/PymeParametroXPVController")
public class PymeParametroXPVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeParametroXPVController() {
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
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String parametroPPVId = request.getParameter("parametroPPVId")== null ? "" : request.getParameter("parametroPPVId");
			String esMultiriesgo = request.getParameter("esMultiriesgo")== null ? "" : request.getParameter("esMultiriesgo");			
			String puntoVentaId = request.getParameter("puntoVentaId")== null ? "" : request.getParameter("puntoVentaId");			
			
			PymeParametroXPuntoVenta parametroPymeXPV = new PymeParametroXPuntoVenta();
			PymeParametroXPuntoVentaDAO parametroPymeXPVDAO = new PymeParametroXPuntoVentaDAO();
			PymeParametroXPuntoVentaTransaction parametroPymeXPVTransaction=new PymeParametroXPuntoVentaTransaction();
			
			Agente agente = new Agente();
			AgenteDAO agenteDAO = new AgenteDAO();
 			
 			JSONObject parametroXPVJSONbject = new JSONObject();
			JSONArray parametroXPVJSONArray = new JSONArray();
			
			if(!parametroPPVId.equals(""))
				parametroPymeXPV.setParametroPuntoVentaId(new BigInteger(parametroPPVId));			
			if(!esMultiriesgo.equals(""))
				parametroPymeXPV.setTieneMultiriesgo(Boolean.parseBoolean(esMultiriesgo));
			if(!puntoVentaId.equals(""))
				parametroPymeXPV.setPuntoVentaId(new BigInteger(puntoVentaId));;
				
			if(tipoConsulta.equals("buscarTodos")){
				List<PymeParametroXPuntoVenta> listParametro = parametroPymeXPVDAO.buscarTodos();
				for(PymeParametroXPuntoVenta parametro: listParametro) {
					parametroXPVJSONbject.put("parametroPPVId", parametro.getParametroPuntoVentaId());
					agente = agenteDAO.buscarPorId(parametro.getPuntoVentaId().toString());
					parametroXPVJSONbject.put("puntoVentaNombre", agente.getEntidad().getNombreCompleto());
					parametroXPVJSONbject.put("puntoVentaId", parametro.getPuntoVentaId());
					if(parametro.getTieneMultiriesgo())
						parametroXPVJSONbject.put("esMultiriesgo", "SI");
					else
						parametroXPVJSONbject.put("esMultiriesgo", "NO");
					parametroXPVJSONArray.add(parametroXPVJSONbject);
				}				
				result.put("parametroArr", parametroXPVJSONArray);
			}
					
			if(tipoConsulta.equals("buscarPorId")){
				 parametroPymeXPV = parametroPymeXPVDAO.buscarPorId(new BigInteger(parametroPPVId));				 
				 result.put("parametroPPVId", parametroPymeXPV.getParametroPuntoVentaId());
				 agente = agenteDAO.buscarPorId(parametroPymeXPV.getPuntoVentaId().toString());
				 result.put("puntoVentaNombre", agente.getEntidad().getNombreCompleto());
				 result.put("puntoVentaId", parametroPymeXPV.getPuntoVentaId());
				 result.put("esMultiriesgo", parametroPymeXPV.getTieneMultiriesgo());
				 result.put("puntoVentaId", parametroPymeXPV.getPuntoVentaId());
			}
			
			if(tipoConsulta.equals("crear")){
				parametroPymeXPVTransaction.crear(parametroPymeXPV);
			}
			
			if(tipoConsulta.equals("actualizar")){
				parametroPymeXPVTransaction.editar(parametroPymeXPV);
			}
			
			if(tipoConsulta.equals("eliminar")){
				parametroPymeXPVTransaction.eliminar(parametroPymeXPV);
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		} catch (Exception e) {
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
