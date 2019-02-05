package com.qbe.cotizador.servlets.producto.agricola;

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

import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriVariedadDAO;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.AgriVariedad;
import com.qbe.cotizador.transaction.producto.agricola.AgriVariedadTransaction;

/**
 * Servlet implementation class AgriVariedadController
 */
@WebServlet("/AgriVariedadController")
public class AgriVariedadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriVariedadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String VariedadId = request.getParameter("VariedadId") == null ? "" : request.getParameter("VariedadId");
			String Nombre = request.getParameter("Nombre") == null ? "" : request.getParameter("Nombre");
			String TipoCultivoId = request.getParameter("TipoCultivoId") == null ? "" : request.getParameter("TipoCultivoId");
			
			JSONObject VariedadJSONObjetc = new JSONObject();
			JSONArray VariedadJSONArray = new JSONArray();

			AgriVariedad agriVariedad = new AgriVariedad();
			AgriVariedadDAO agriVariedadDAO = new AgriVariedadDAO();
			AgriVariedadTransaction agriVariedadTransaction = new AgriVariedadTransaction();

			if (!VariedadId.equals(""))
				agriVariedad.setVariedadId(new BigInteger(VariedadId));
			if (!Nombre.equals(""))
				agriVariedad.setNombre(Nombre);
			if (!TipoCultivoId.equals(""))
				agriVariedad.setTipoCultivoId(new BigInteger(TipoCultivoId));
			if (tipoConsulta.equals("encontrarTodos")) {
				List<AgriVariedad> results = agriVariedadDAO
						.BuscarTodos();
				for (AgriVariedad Variedad : results) {
					VariedadJSONObjetc.put("VariedadId",Variedad.getVariedadId());
					VariedadJSONObjetc.put("Nombre", Variedad.getNombre());
					
					AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
					AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorId(Variedad.getTipoCultivoId());
					VariedadJSONObjetc.put("TipoCultivo", agriTipoCultivo.getNombre());
					VariedadJSONArray.add(VariedadJSONObjetc);
				}
				result.put("VariedadJSONArray", VariedadJSONArray);
			}
			if (tipoConsulta.equals("obtenerPorId")) {
				AgriVariedad results = agriVariedadDAO.BuscarPorId(new BigInteger(VariedadId));
				result.put("VariedadId", results.getVariedadId());
				result.put("Nombre", results.getNombre());
				result.put("TipoCultivoId", results.getTipoCultivoId());
			}
			if (tipoConsulta.equals("encontrarPorTipoCultivo")) {
				List<AgriVariedad> results = agriVariedadDAO.BuscarPorTipoCultivoId(new BigInteger(TipoCultivoId));
				for (AgriVariedad Variedad : results) {
					VariedadJSONObjetc.put("VariedadId",Variedad.getVariedadId());
					VariedadJSONObjetc.put("Nombre", Variedad.getNombre());
					
					AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
					AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorId(Variedad.getTipoCultivoId());
					VariedadJSONObjetc.put("TipoCultivo", agriTipoCultivo.getNombre());
					VariedadJSONArray.add(VariedadJSONObjetc);
				}
				result.put("VariedadJSONArray", VariedadJSONArray);
			}
			if (tipoConsulta.equals("crear"))
				agriVariedadTransaction.crear(agriVariedad);
			if (tipoConsulta.equals("editar"))
				agriVariedadTransaction.editar(agriVariedad);
			if (tipoConsulta.equals("eliminar"))
				agriVariedadTransaction.eliminar(agriVariedad);
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
