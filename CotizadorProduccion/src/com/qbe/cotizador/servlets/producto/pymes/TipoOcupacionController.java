package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.producto.pymes.TipoOcupacionDAO;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.TipoOcupacion;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

/**
 * Servlet implementation class TipoOcupacionController
 */
@WebServlet("/TipoOcupacionController")
public class TipoOcupacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoOcupacionController() {
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
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject tipoOcupacionJSONObject = new JSONObject();
			JSONArray tipoOcupacionJSONArray = new JSONArray();
			
			TipoOcupacion tipoOcupacion = new TipoOcupacion();
			TipoOcupacionDAO tipoOcupacionDAO = new TipoOcupacionDAO();
			
			if(!codigo.equals(""))
				tipoOcupacion.setId(codigo);;

			if(!nombre.equals(""))
				tipoOcupacion.setNombre(nombre);
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoOcupacion> results = tipoOcupacionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoOcupacion = results.get(i);
					tipoOcupacionJSONObject.put("codigo", tipoOcupacion.getId());
					tipoOcupacionJSONObject.put("nombre", tipoOcupacion.getNombre());
					tipoOcupacionJSONArray.add(tipoOcupacionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoOcupacion", tipoOcupacionJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				tipoOcupacionDAO.crear(tipoOcupacion);

			if(tipoConsulta.equals("actualizar"))
				tipoOcupacionDAO.editar(tipoOcupacion);

			if(tipoConsulta.equals("eliminar"))
				tipoOcupacionDAO.eliminar(tipoOcupacion);			

			if(tipoConsulta.equals("autocomplete")){
				List<TipoOcupacion> results = tipoOcupacionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoOcupacion = results.get(i);
					tipoOcupacionJSONObject.put("codigo", tipoOcupacion.getId());
					tipoOcupacionJSONObject.put("nombre", tipoOcupacion.getNombre());
					tipoOcupacionJSONArray.add(tipoOcupacionJSONObject);
				}
				result.put("listadoTipoOcupacion", tipoOcupacionJSONArray);				
			}
			
			if(tipoConsulta.equals("cargarSelect2")){
				List<TipoOcupacion> results = tipoOcupacionDAO.buscarTodos();
				int i=0;
				//tipoOcupacionJSONObject.put("id", "-1");
				//tipoOcupacionJSONObject.put("text", "Escoja una tipoOcupacion");
				//tipoOcupacionJSONArray.add(tipoOcupacionJSONObject);
				for(i=0; i<results.size(); i++){
					tipoOcupacion = results.get(i);
					//tipoOcupacionJSONObject.put("codigo", tipoOcupacion.getId());
					tipoOcupacionJSONObject.put("id", tipoOcupacion.getId());
					tipoOcupacionJSONObject.put("text", tipoOcupacion.getNombre());
					tipoOcupacionJSONArray.add(tipoOcupacionJSONObject);
				}
				result.put("listadoTipoOcupacion", tipoOcupacionJSONArray);				
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
