package com.qbe.cotizador.servlets.producto.ganadero;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.pagos.FormaPagoDAO;
import com.qbe.cotizador.dao.producto.ganadero.FormaPagoXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.ganadero.TipoGanadoDAO;
import com.qbe.cotizador.dao.producto.ganadero.VigenciaPolizaXPuntoVentaDAO;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.model.FormaPagoXPuntoVenta;
import com.qbe.cotizador.model.FormaPagoXPuntoVentaVta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoGanado;
import com.qbe.cotizador.model.VigenciaPoliza;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVenta;
import com.qbe.cotizador.servlets.entidad.PuntoVentaController;
import com.qbe.cotizador.transaction.producto.ganadero.FormaPagoXPuntoVentaTransaction;

/**
 * Servlet implementation class FormaPagoXPuntoVentaController
 */
@WebServlet("/FormaPagoXPuntoVentaController")
public class FormaPagoXPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormaPagoXPuntoVentaController() {
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
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String formaPagoId = request.getParameter("formaPagoId") == null ? "" : request.getParameter("formaPagoId");
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
			
			JSONObject formaPagoJSONObject = new JSONObject();
			JSONArray formaPagoJSONArray = new JSONArray();
			
			FormaPagoXPuntoVenta formaPagoXPuntoVenta = new FormaPagoXPuntoVenta();
 			FormaPagoXPuntoVentaDAO formaPagoPVDAO = new FormaPagoXPuntoVentaDAO();
 			
 			FormaPagoXPuntoVentaTransaction FormaPagoXPuntoVentaTransaction = new FormaPagoXPuntoVentaTransaction();
 			
 			if(!codigo.equals(""))
 				formaPagoXPuntoVenta.setId(Integer.parseInt(codigo));
 			
 			if(formaPagoId != null && !formaPagoId.equals(""))
 				formaPagoXPuntoVenta.setFormaPagoId(new BigInteger(formaPagoId));
 			
 			if(puntoVentaId != null && !puntoVentaId.equals(""))
 				formaPagoXPuntoVenta.setPuntoVentaId(new BigInteger(puntoVentaId));
 			
 						
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<FormaPagoXPuntoVentaVta> results = formaPagoPVDAO.obtenerPorPuntoVentaId(new BigInteger(puntoVentaId));
				
				for(FormaPagoXPuntoVentaVta formaPagoXPV:results){
					formaPagoJSONObject.put("id", formaPagoXPV.getFormaPagoId());
					formaPagoJSONObject.put("nombre", formaPagoXPV.getNombre());
					formaPagoJSONArray.add(formaPagoJSONObject);
				}
				result.put("listadoFormaPagoXPV", formaPagoJSONArray);
			}			
			
			if(tipoConsulta.equals("crear")){
				FormaPagoXPuntoVenta results = formaPagoPVDAO.buscarFormaPago(new BigInteger(formaPagoId), new BigInteger(puntoVentaId));
				if(!(results.getId()>0)){
					FormaPagoXPuntoVentaTransaction.crear(formaPagoXPuntoVenta);
					for(FormaPagoXPuntoVentaVta formaPagoXPV:formaPagoPVDAO.obtenerPorPuntoVentaId(new BigInteger(puntoVentaId))){
						formaPagoJSONObject.put("id", formaPagoXPV.getFormaPagoId());
						formaPagoJSONObject.put("nombre", formaPagoXPV.getNombre());
						formaPagoJSONArray.add(formaPagoJSONObject);
					}
					result.put("listadoFormaPagoXPV", formaPagoJSONArray);
				}				
			}
			
			if(tipoConsulta.equals("actualizar")){
				FormaPagoXPuntoVentaTransaction.editar(formaPagoXPuntoVenta);
			}
			
			if(tipoConsulta.equals("eliminar")){
				FormaPagoXPuntoVentaTransaction.eliminar(formaPagoXPuntoVenta);
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();

		}
	}

}
