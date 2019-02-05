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

import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeFechaCierreDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeFechaCierre;
import com.qbe.cotizador.transaction.producto.pymes.PymeAsistenciaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeFechaCierreTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeAsistenciaController
 */
@WebServlet("/PymeFechaCierreController")
public class PymeFechaCierreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeFechaCierreController() {
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
			String fechaCierreId = request.getParameter("fechaCierreId") == null ? "" : request.getParameter("fechaCierreId");
			String fecha = request.getParameter("fecha") == null ? "" : request.getParameter("fecha");
						
			JSONObject fechaCierreJSONObject = new JSONObject();
			JSONArray fechaCierreJSONArray = new JSONArray();
			
			PymeFechaCierre pymeFechaCierre = new PymeFechaCierre();
			PymeFechaCierreDAO pymeFechaCierreDAO = new PymeFechaCierreDAO();
			PymeFechaCierreTransaction pymeAsistenciaTransaction=new PymeFechaCierreTransaction();
			
			
			if(!fechaCierreId.equals(""))
				pymeFechaCierre.setFechaCierreId(new BigInteger(fechaCierreId));
			
			if(!fecha.equals("") && fecha!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				pymeFechaCierre.setFechaCierre(formatter.parse(fecha));
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeFechaCierre> results = pymeFechaCierreDAO.buscarTodos();
				
				for(PymeFechaCierre fechaCierre : results){
					fechaCierreJSONObject.put("fechaCierreId", fechaCierre.getFechaCierreId());
					String fechaCi = df.format(fechaCierre.getFechaCierre());
					fechaCierreJSONObject.put("fecha", fechaCi);
					fechaCierreJSONArray.add(fechaCierreJSONObject);
				}
				result.put("fechaCierreJSONArray", fechaCierreJSONArray);
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				PymeFechaCierre results = pymeFechaCierreDAO.buscarPorId(new BigInteger(fechaCierreId));
				
				result.put("fechaCierreId", results.getFechaCierreId());
				String fechaCi = df.format(results.getFechaCierre());
				result.put("fecha", fechaCi);
			}
			
			if(tipoConsulta.equals("crear"))
				pymeAsistenciaTransaction.crear(pymeFechaCierre);
			
			if(tipoConsulta.equals("editar"))
				pymeAsistenciaTransaction.editar(pymeFechaCierre);
			
			if(tipoConsulta.equals("eliminar"))
				pymeAsistenciaTransaction.eliminar(pymeFechaCierre);
			
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
