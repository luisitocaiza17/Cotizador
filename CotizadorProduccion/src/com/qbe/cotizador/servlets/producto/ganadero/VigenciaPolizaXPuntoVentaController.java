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
import com.qbe.cotizador.dao.producto.ganadero.FormaPagoXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.ganadero.VigenciaPolizaXPuntoVentaDAO;
import com.qbe.cotizador.model.FormaPagoXPuntoVentaVta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.VigenciaPoliza;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVenta;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVentaVta;
import com.qbe.cotizador.transaction.producto.ganadero.VigenciaPolizaXPuntoVentaTransaction;

/**
 * Servlet implementation class VigenciaPolizaXPuntoVentaController
 */
@WebServlet("/VigenciaPolizaXPuntoVentaController")
public class VigenciaPolizaXPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VigenciaPolizaXPuntoVentaController() {
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
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
			String vigenciaPolizaId = request.getParameter("vigenciaPolizaId") == null ? "" : request.getParameter("vigenciaPolizaId");
						
			JSONObject formaPagoJSONObject = new JSONObject();
			JSONArray formaPagoJSONArray = new JSONArray();

			VigenciaPolizaXPuntoVenta vigenciaPolizaXPuntoVenta = new VigenciaPolizaXPuntoVenta();
			VigenciaPolizaXPuntoVentaDAO vigenciaPolizaPVDAO = new VigenciaPolizaXPuntoVentaDAO();
			
			VigenciaPolizaXPuntoVentaTransaction vigenciaPolizaXPuntoVentaTransaction = new VigenciaPolizaXPuntoVentaTransaction();
			
			if(!codigo.equals(""))
				vigenciaPolizaXPuntoVenta.setId(Integer.parseInt(codigo));
			
			if(puntoVentaId != null && !puntoVentaId.equals(""))
				vigenciaPolizaXPuntoVenta.setPuntoVentaId(new BigInteger(puntoVentaId));
			
			if(vigenciaPolizaId != null && ! vigenciaPolizaId.equals(""))
				vigenciaPolizaXPuntoVenta.setVigenciaPolizaId(new BigInteger(vigenciaPolizaId));
			
			if(tipoConsulta.equals("crear")){
				VigenciaPolizaXPuntoVenta results = vigenciaPolizaPVDAO.buscarVigenciaPoliza(new BigInteger(vigenciaPolizaId),new BigInteger(puntoVentaId));
				if(!(results.getId()>0)){
					vigenciaPolizaXPuntoVentaTransaction.crear(vigenciaPolizaXPuntoVenta);
				}				
			}
			
			if(tipoConsulta.equals("actualizar")){
				vigenciaPolizaXPuntoVentaTransaction.editar(vigenciaPolizaXPuntoVenta);
			}
			
			if(tipoConsulta.equals("eliminar")){
				vigenciaPolizaXPuntoVentaTransaction.eliminar(vigenciaPolizaXPuntoVenta);
			}			

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<VigenciaPolizaXPuntoVentaVta> results = vigenciaPolizaPVDAO.obtenerPorPuntoVentaId(new BigInteger(puntoVentaId));
				
				for(VigenciaPolizaXPuntoVentaVta vigenciaPolizaXPV:results){
					formaPagoJSONObject.put("id", vigenciaPolizaXPV.getVigenciaPolizaId());
					formaPagoJSONObject.put("nombre", vigenciaPolizaXPV.getNombre());
					formaPagoJSONArray.add(formaPagoJSONObject);
				}
				result.put("listadoVigenciaPolizaXPV", formaPagoJSONArray);
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
