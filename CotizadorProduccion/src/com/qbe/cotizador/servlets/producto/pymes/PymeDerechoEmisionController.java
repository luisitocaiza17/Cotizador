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

import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDerechoEmisionDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeDerechoEmision;
import com.qbe.cotizador.transaction.producto.pymes.PymeAsistenciaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeDerechoEmisionTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class PymeAsistenciaController
 */
@WebServlet("/PymeDerechoEmisionController")
public class PymeDerechoEmisionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeDerechoEmisionController() {
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
			String derechoEmisionId = request.getParameter("derechoEmisionId") == null ? "" : request.getParameter("derechoEmisionId");
			String valorPrimaInicial = request.getParameter("valorPrimaInicial") == null ? "" : request.getParameter("valorPrimaInicial");
			String valorPrimaFinal = request.getParameter("valorPrimaFinal") == null ? "" : request.getParameter("valorPrimaFinal");
			String valorDerechoEmision = request.getParameter("valorDerechoEmision") == null ? "" : request.getParameter("valorDerechoEmision");
			
			JSONObject derechoEmisionJSONObject = new JSONObject();
			JSONArray derechoEmisionJSONArray = new JSONArray();
			
			PymeDerechoEmision pymeDerechoEmision = new PymeDerechoEmision();
			PymeDerechoEmisionDAO pymeDerechoEmisionDAO = new PymeDerechoEmisionDAO();
			PymeDerechoEmisionTransaction pymeDerechoEmisionTransaction=new PymeDerechoEmisionTransaction();
			
			if(!derechoEmisionId.equals(""))
				pymeDerechoEmision.setDerechoEmisionId(new BigInteger(derechoEmisionId));
			if(!valorPrimaInicial.equals("") && valorPrimaInicial!=null)
				pymeDerechoEmision.setValorPrimaInicial(Double.parseDouble(valorPrimaInicial));
			if(!valorPrimaFinal.equals("") && valorPrimaFinal!=null)
				pymeDerechoEmision.setValorPrimaFinal(Double.parseDouble(valorPrimaFinal));
			if(!valorDerechoEmision.equals("") && valorDerechoEmision!=null)
				pymeDerechoEmision.setValorDerechoEmision(Double.parseDouble(valorDerechoEmision));
			
			if(tipoConsulta.equals("encontrarTodos")){
				List<PymeDerechoEmision> results = pymeDerechoEmisionDAO.buscarTodos();
				
				for(PymeDerechoEmision derechoEmision : results){
					derechoEmisionJSONObject.put("derechoEmisionId", derechoEmision.getDerechoEmisionId());
					derechoEmisionJSONObject.put("valorPrimaInicial", derechoEmision.getValorPrimaInicial());
					derechoEmisionJSONObject.put("valorPrimaFinal", derechoEmision.getValorPrimaFinal());
					derechoEmisionJSONObject.put("valorDerechoEmision", derechoEmision.getValorDerechoEmision());
					derechoEmisionJSONArray.add(derechoEmisionJSONObject);
				}
				result.put("derechoEmisionJSONArray", derechoEmisionJSONArray);
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				PymeDerechoEmision derechoEmision = pymeDerechoEmisionDAO.buscarPorId(new BigInteger(derechoEmisionId));
				result.put("derechoEmisionId", derechoEmision.getDerechoEmisionId());
				result.put("valorPrimaInicial", derechoEmision.getValorPrimaInicial());
				result.put("valorPrimaFinal", derechoEmision.getValorPrimaFinal());
				result.put("valorDerechoEmision", derechoEmision.getValorDerechoEmision());
			}

			if(tipoConsulta.equals("crear"))
				pymeDerechoEmisionTransaction.crear(pymeDerechoEmision);
			
			if(tipoConsulta.equals("editar"))
				pymeDerechoEmisionTransaction.editar(pymeDerechoEmision);
			
			if(tipoConsulta.equals("eliminar"))
				pymeDerechoEmisionTransaction.eliminar(pymeDerechoEmision);
			
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
