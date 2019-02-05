package com.qbe.cotizador.servlets.producto.ganadero;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.producto.ganadero.ParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.ParametroXPuntoVenta;
import com.qbe.cotizador.transaction.producto.ganadero.ParametroXPuntoVentaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class ParametroXPuntoVentaController
 */
@WebServlet("/ParametroXPuntoVentaController")
public class ParametroXPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParametroXPuntoVentaController() {
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
			String parametroXPVId = request.getParameter("parametroXPVId") == null ? "" : request.getParameter("parametroXPVId");
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
			String emisionDirecta = request.getParameter("emisionDirecta") == null ? "" : request.getParameter("emisionDirecta");
			String contenedorEnsuranceid = request.getParameter("contenedorEnsuranceId") == null ? "" : request.getParameter("contenedorEnsuranceId");
			String plantillaEnsurancId = request.getParameter("contenedorEnsuranceId") == null ? "" : request.getParameter("plantillaEnsurancId");
			String tipoCanal = request.getParameter("tipoCanal") == null ? "" : request.getParameter("tipoCanal");
			
			ParametroXPuntoVenta parametroXPV = new ParametroXPuntoVenta();
			ParametroXPuntoVentaDAO parametroXPVDAO = new ParametroXPuntoVentaDAO();
			
			ParametroXPuntoVentaTransaction parametroXPuntoVentaTransaction = new ParametroXPuntoVentaTransaction();
			
			JSONObject parametroXPVJSONbject = new JSONObject();
			JSONArray parametroXPVJSONArray = new JSONArray();
			
			if(!parametroXPVId.equals(""))
				parametroXPV.setId(Integer.parseInt(parametroXPVId));
			if(!puntoVentaId.equals(""))
				parametroXPV.setPuntoVentaId(new BigInteger(puntoVentaId));
			if(!emisionDirecta.equals(""))
				parametroXPV.setEmisionDirecta(Integer.parseInt(emisionDirecta));
			if(!contenedorEnsuranceid.equals(""))
				parametroXPV.setContenedorEnsuranceid(contenedorEnsuranceid);
			if(!plantillaEnsurancId.equals(""))
				parametroXPV.setPlantillaEnsurancid(plantillaEnsurancId);
			if(!tipoCanal.equals(""))
				parametroXPV.setTipoCanal(tipoCanal);
			
			if(tipoConsulta.equals("encontrarTodos")){
				
				parametroXPV = parametroXPVDAO.obtenerPorPuntoVentaId(new BigInteger(puntoVentaId));		
				
				result.put("parametroXPVId", parametroXPV.getId());
				result.put("puntoVentaId", parametroXPV.getPuntoVentaId());
				result.put("emisionDirecta", parametroXPV.getEmisionDirecta());
				result.put("contenedorEnsuranceId", parametroXPV.getContenedorEnsuranceid());
				result.put("plantillaEnsurancId", parametroXPV.getPlantillaEnsurancid());
				result.put("tipoCanal", parametroXPV.getTipoCanal());
					
					
			}
			
			if(tipoConsulta.equals("crear")){
				parametroXPuntoVentaTransaction.crear(parametroXPV);
			}
			
			if(tipoConsulta.equals("actualizar")){
				parametroXPuntoVentaTransaction.editar(parametroXPV);
			}

			if(tipoConsulta.equals("eliminar")){
				parametroXPuntoVentaTransaction.eliminar(parametroXPV);
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
		
		
	}

}
