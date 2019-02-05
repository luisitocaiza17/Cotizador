package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeExtRamoDAO;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeExtRamo;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeExtRamoTransaction;

/**
 * Servlet implementation class PymeExtRamoController
 */
@WebServlet("/PymeExtRamoController")
public class PymeExtRamoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeExtRamoController() {
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
			String ramoId = request.getParameter("ramoId") == null ? "" : request.getParameter("ramoId");
			String tipoItemId = request.getParameter("tipoItemId") == null ? "" : request.getParameter("tipoItemId");			
			String tipoRiesgoId = request.getParameter("tipoRiesgoId") == null ? "" : request.getParameter("tipoRiesgoId");
			String claseRiesgoId = request.getParameter("claseRiesgoId") == null ? "" : request.getParameter("claseRiesgoId");

			PymeExtRamo pymeExtRamo = new PymeExtRamo();
			PymeExtRamoDAO pymeExtRamoDAO = new PymeExtRamoDAO();
			PymeExtRamoTransaction pymeExtRamoTransaction = new PymeExtRamoTransaction();
			
			Ramo ramo = new Ramo();
			RamoDAO ramoDAO = new RamoDAO();

			JSONObject pymeExtRamoJSONObject = new JSONObject();
			JSONArray pymeExtRamoJSONArray = new JSONArray();	
			
			JSONObject ramoJSONObject = new JSONObject();
			JSONArray ramoJSONArray = new JSONArray();

			if(!ramoId.equals(""))
				pymeExtRamo.setRamoId(new BigInteger(ramoId));	
			if(!tipoItemId.equals(""))
				pymeExtRamo.setTipoItemId(tipoItemId);
			if(!tipoRiesgoId.equals(""))
				pymeExtRamo.setTipoRiesgoId(tipoRiesgoId);
			if(!claseRiesgoId.equals(""))
				pymeExtRamo.setClaseRiesgoId(claseRiesgoId);			

			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeExtRamo> results = pymeExtRamoDAO.buscarTodos();
				for(PymeExtRamo pymeExt: results){
					ramo = ramoDAO.buscarPorId(pymeExt.getRamoId().toString());
					pymeExtRamoJSONObject.put("ramoNombre", ramo.getNombre());
					pymeExtRamoJSONObject.put("ramoId", pymeExt.getRamoId());
					pymeExtRamoJSONObject.put("tipoItemId", pymeExt.getTipoItemId());
					pymeExtRamoJSONObject.put("tipoRiesgoId", pymeExt.getTipoRiesgoId());
					pymeExtRamoJSONObject.put("claseRiesgoId", pymeExt.getClaseRiesgoId());
					
					pymeExtRamoJSONArray.add(pymeExtRamoJSONObject);
				}
				result.put("listadoPymeExtRamo", pymeExtRamoJSONArray);


				
				List<Ramo> resultR = ramoDAO.buscarTodos();
				for(Ramo r: resultR){
					ramoJSONObject.put("ramoId", r.getId());
					ramoJSONObject.put("ramoNombre", r.getNombre());
					ramoJSONArray.add(ramoJSONObject);
				}
				result.put("ramoArr", ramoJSONArray);				
				
			}

			if(tipoConsulta.equals("obtenerPorId")){
				PymeExtRamo results = pymeExtRamoDAO.buscarPorId(new BigInteger(ramoId));	
				result.put("ramoId", results.getRamoId());
				result.put("tipoItemId", results.getTipoItemId());
				result.put("tipoRiesgoId", results.getTipoRiesgoId());
				result.put("claseRiesgoId", results.getClaseRiesgoId());				
			}

			if(tipoConsulta.equals("crear")){
				PymeExtRamo res = pymeExtRamoDAO.buscarPorId(pymeExtRamo.getRamoId());
				if(res != null)
					pymeExtRamoTransaction.editar(pymeExtRamo);					
				else
					pymeExtRamoTransaction.crear(pymeExtRamo);
					
					
			}

			/*if(tipoConsulta.equals("actualizar")){
				pymeExtRamoTransaction.editar(pymeExtRamo);
			}*/

			if(tipoConsulta.equals("eliminar")){
				pymeExtRamoTransaction.eliminar(pymeExtRamo);
			}


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
			pymeAuditoriaGeneral.setObjeto(e.getMessage());
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
