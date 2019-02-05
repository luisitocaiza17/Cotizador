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

import com.qbe.cotizador.dao.producto.pymes.TipoConstruccionDAO;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.TipoConstruccion;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

/**
 * Servlet implementation class TipoConstruccionController
 */
@WebServlet("/TipoConstruccionController")
public class TipoConstruccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoConstruccionController() {
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
		JSONObject result = new JSONObject();
		try{
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject tipoConstruccionJSONObject = new JSONObject();
			JSONArray tipoConstruccionJSONArray = new JSONArray();
			
			TipoConstruccion tipoConstruccion = new TipoConstruccion();
			TipoConstruccionDAO tipoConstruccionDAO = new TipoConstruccionDAO();
			
			if(!codigo.equals(""))
				tipoConstruccion.setId(codigo);;

			if(!nombre.equals(""))
				tipoConstruccion.setNombre(nombre);
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<TipoConstruccion> results = tipoConstruccionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoConstruccion = results.get(i);
					tipoConstruccionJSONObject.put("codigo", tipoConstruccion.getId());
					tipoConstruccionJSONObject.put("nombre", tipoConstruccion.getNombre());
					tipoConstruccionJSONArray.add(tipoConstruccionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoTipoConstruccion", tipoConstruccionJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
				tipoConstruccionDAO.crear(tipoConstruccion);

			if(tipoConsulta.equals("actualizar"))
				tipoConstruccionDAO.editar(tipoConstruccion);

			if(tipoConsulta.equals("eliminar"))
				tipoConstruccionDAO.eliminar(tipoConstruccion);			

			if(tipoConsulta.equals("autocomplete")){
				List<TipoConstruccion> results = tipoConstruccionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					tipoConstruccion = results.get(i);
					tipoConstruccionJSONObject.put("codigo", tipoConstruccion.getId());
					tipoConstruccionJSONObject.put("nombre", tipoConstruccion.getNombre());
					tipoConstruccionJSONArray.add(tipoConstruccionJSONObject);
				}
				result.put("listadoTipoConstruccion", tipoConstruccionJSONArray);				
			}
			
			if(tipoConsulta.equals("cargarSelect2")){
				List<TipoConstruccion> results = tipoConstruccionDAO.buscarTodos();
				int i=0;
				//tipoConstruccionJSONObject.put("id", "-1");
				//tipoConstruccionJSONObject.put("text", "Escoja una tipoConstruccion");
				//tipoConstruccionJSONArray.add(tipoConstruccionJSONObject);
				for(i=0; i<results.size(); i++){
					tipoConstruccion = results.get(i);
					//tipoConstruccionJSONObject.put("codigo", tipoConstruccion.getId());
					tipoConstruccionJSONObject.put("id", tipoConstruccion.getId());
					tipoConstruccionJSONObject.put("text", tipoConstruccion.getNombre());
					tipoConstruccionJSONArray.add(tipoConstruccionJSONObject);
				}
				result.put("listadoTipoConstruccion", tipoConstruccionJSONArray);				
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
