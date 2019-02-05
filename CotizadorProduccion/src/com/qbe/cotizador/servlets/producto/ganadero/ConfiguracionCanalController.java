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
import com.qbe.cotizador.dao.producto.ganadero.VigenciaPolizaXPuntoVentaDAO;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.model.FormaPagoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.VigenciaPoliza;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVenta;


/**
 * Servlet implementation class ConfiguracionCanalController
 */
@WebServlet("/ConfiguracionCanalController")
public class ConfiguracionCanalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfiguracionCanalController() {
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
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
			String formaPagoId = request.getParameter("formaPagoId") == null ? "" : request.getParameter("formaPagoId");
			String formaPagoNombre = request.getParameter("formaPagoNombre") == null ? "" : request.getParameter("formaPagoNombre");
			String formaPagoXPVId = request.getParameter("formaPagoXPVId") == null ? "" : request.getParameter("formaPagoXPVId");
			String vigenciaPolizaId = request.getParameter("vigenciaPolizaId") == null ? "" : request.getParameter("vigenciaPolizaId");
			String vigenciaPolizaNombre = request.getParameter("vigenciaPolizaNombre") == null ? "" : request.getParameter("vigenciaPolizaNombre");
			String vigenciaPolizaXPVId = request.getParameter("vigenciaPolizaXPVId") == null ? "" : request.getParameter("vigenciaPolizaXPVId");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject configuracionCanalJSONObject = new JSONObject();
			JSONArray configuracionCanalJSONArray_1 = new JSONArray();
			JSONArray configuracionCanalJSONArray_2 = new JSONArray();
			
			
			FormaPagoXPuntoVenta formaPagoXPV = new FormaPagoXPuntoVenta();
			FormaPagoXPuntoVentaDAO formaPagoXPVDAO = new FormaPagoXPuntoVentaDAO();
			
			VigenciaPolizaXPuntoVenta vigenciaPolizaXPuntoVenta = new VigenciaPolizaXPuntoVenta();
			VigenciaPolizaXPuntoVentaDAO vigenciaPolizaXPuntoVentaDAO = new VigenciaPolizaXPuntoVentaDAO();
			
						
			if(!formaPagoXPVId.equals("")){
				formaPagoXPV.setId(Integer.parseInt(formaPagoXPVId));
				if(!formaPagoId.equals(""))
					formaPagoXPV.setFormaPagoId(new BigInteger(formaPagoId));
				if(!puntoVentaId.equals(""))
					formaPagoXPV.setPuntoVentaId(new BigInteger(puntoVentaId));				
			}		
			
			if(!vigenciaPolizaXPVId.equals("")){
				vigenciaPolizaXPuntoVenta.setId(Integer.parseInt(vigenciaPolizaXPVId));
				if(!vigenciaPolizaId.equals(""))
					vigenciaPolizaXPuntoVenta.setVigenciaPolizaId(new BigInteger(vigenciaPolizaId));
				if(!puntoVentaId.equals(""))
					vigenciaPolizaXPuntoVenta.setPuntoVentaId(new BigInteger(puntoVentaId));							
			}
			
			if(tipoConsulta.equals("encontrarTodos")){			
				
				List<FormaPagoXPuntoVenta> formaPagoXPVList = formaPagoXPVDAO.buscarTodosPV(new BigInteger(puntoVentaId));
				
				for(FormaPagoXPuntoVenta formaPagoXPVList2 : formaPagoXPVList){
					configuracionCanalJSONObject.put("formaPagoXPVId", formaPagoXPVList2.getId());
					configuracionCanalJSONObject.put("formaPagoId", formaPagoXPVList2.getFormaPagoId());
					
					FormaPago formaPago = new FormaPago();
					FormaPagoDAO formaPagoDAO = new FormaPagoDAO();
					
					formaPago = formaPagoDAO.buscarPorId(formaPagoXPVList2.getFormaPagoId().toString());
					
					configuracionCanalJSONObject.put("formaPagoNombre", formaPago.getNombre());					
					
					configuracionCanalJSONArray_1.add(configuracionCanalJSONObject);
				}				
					
				
				List<VigenciaPolizaXPuntoVenta> vigenciaPolizaXPVList = vigenciaPolizaXPuntoVentaDAO.buscarTodosPV(new BigInteger(puntoVentaId));
				
				for(VigenciaPolizaXPuntoVenta vigenciaPolizaXPVList2 : vigenciaPolizaXPVList){
					configuracionCanalJSONObject.put("vigenciaPolizaXPVId", vigenciaPolizaXPVList2.getId());
					configuracionCanalJSONObject.put("vigenciaPolizaId", vigenciaPolizaXPVList2.getVigenciaPolizaId());
					
					
					VigenciaPoliza vigenciaPoliza = new VigenciaPoliza();
					VigenciaPolizaDAO vigenciaPolizaDAO = new VigenciaPolizaDAO();
					vigenciaPoliza = vigenciaPolizaDAO.buscarPorId(vigenciaPolizaXPVList2.getVigenciaPolizaId().toString());
					
					configuracionCanalJSONObject.put("vigenciaPolizaNombre", vigenciaPoliza.getNombre());						
					
					configuracionCanalJSONArray_2.add(configuracionCanalJSONObject);
				}				
					
				result.put("listadoConfiguracionCanal_1", configuracionCanalJSONArray_1);
				result.put("listadoConfiguracionCanal_2", configuracionCanalJSONArray_2);
				
				
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