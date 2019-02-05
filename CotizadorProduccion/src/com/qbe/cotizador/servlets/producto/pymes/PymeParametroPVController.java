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
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroPuntoVentaDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;
import com.qbe.cotizador.transaction.cotizacion.PuntoVentaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeParametroPuntoVentaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class PymeParametroXPVController
 */
@WebServlet("/PymeParametroPVController")
public class PymeParametroPVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeParametroPVController() {
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
			String plantillaNombre = request.getParameter("plantillaNombre")== null ? "" : request.getParameter("plantillaNombre");			
			String puntoVentaId = request.getParameter("puntoVentaId")== null ? "" : request.getParameter("puntoVentaId");
			String entidadId = request.getParameter("entidadId")== null ? "" : request.getParameter("entidadId");
			
			PymeParametroPuntoVenta parametroPymeXPV = new PymeParametroPuntoVenta();
			PymeParametroPuntoVentaDAO parametroPymeXPVDAO = new PymeParametroPuntoVentaDAO();
			PymeParametroPuntoVentaTransaction parametroPymeXPVTransaction=new PymeParametroPuntoVentaTransaction();
			
			Agente agente = new Agente();
			AgenteDAO agenteDAO = new AgenteDAO();
			
 			
 			JSONObject parametroXPVJSONbject = new JSONObject();
			JSONArray parametroXPVJSONArray = new JSONArray();
			
			if(!parametroPPVId.equals(""))
				parametroPymeXPV.setParametroPuntoventaId(new BigInteger(parametroPPVId));			
			if(!plantillaNombre.equals(""))
				parametroPymeXPV.setPlantillaNombre(plantillaNombre);
			if(!puntoVentaId.equals(""))
				parametroPymeXPV.setPuntoVentaId(new BigInteger(puntoVentaId));
			if(!entidadId.equals(""))
				parametroPymeXPV.setEntidadId(new BigInteger(entidadId));
				
			if(tipoConsulta.equals("buscarTodos")){
				PuntoVenta puntoVenta = new PuntoVenta();
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				List<PymeParametroPuntoVenta> listParametro = parametroPymeXPVDAO.buscarTodos();
				for(PymeParametroPuntoVenta parametro: listParametro) {
					parametroXPVJSONbject.put("parametroPPVId", parametro.getParametroPuntoventaId());
					puntoVenta = puntoVentaDAO.buscarPorId(parametro.getPuntoVentaId().toString());
					parametroXPVJSONbject.put("puntoVentaNombre", puntoVenta.getNombre());
					parametroXPVJSONbject.put("puntoVentaId", parametro.getPuntoVentaId());
					parametroXPVJSONbject.put("plantillaNombre", parametro.getPlantillaNombre());
					parametroXPVJSONbject.put("entidadId", parametro.getEntidadId());
					parametroXPVJSONArray.add(parametroXPVJSONbject);
				}
				result.put("parametroArr", parametroXPVJSONArray);
			}
			if(tipoConsulta.equals("consultarEntidades")){
				JSONObject entidadObject = new JSONObject();
				JSONArray listadoEntidad = new JSONArray();
				
				EntidadDAO entidadDAO = new EntidadDAO();
				Entidad entidades=entidadDAO.buscarPorId("11");
				
				entidadObject.put("nombreCompleto", entidades.getNombreCompleto());
				entidadObject.put("codigo", entidades.getId());
				listadoEntidad.add(entidadObject);
				
				result.put("listadoEntidad", listadoEntidad);
				
			}
			if(tipoConsulta.equals("buscarPorId")){
				 PuntoVenta puntoVenta = new PuntoVenta();
				 PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				 parametroPymeXPV = parametroPymeXPVDAO.buscarPorId(new BigInteger(parametroPPVId));				 
				 result.put("parametroPPVId", parametroPymeXPV.getParametroPuntoventaId());
				 puntoVenta = puntoVentaDAO.buscarPorId(parametroPymeXPV.getPuntoVentaId().toString());
				 result.put("puntoVentaNombre", puntoVenta.getNombre());
				 result.put("puntoVentaId", parametroPymeXPV.getPuntoVentaId());
				 result.put("plantillaNombre", parametroPymeXPV.getPlantillaNombre());
				 result.put("entidadId", parametroPymeXPV.getEntidadId());
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
