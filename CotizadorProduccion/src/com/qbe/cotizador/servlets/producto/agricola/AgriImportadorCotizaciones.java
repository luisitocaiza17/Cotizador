package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.util.AES_Helper;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.exceptionMappingType;

/**
 * Servlet implementation class AgriImportadorCotizaciones
 */
@WebServlet("/AgriImportadorCotizaciones")
public class AgriImportadorCotizaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriImportadorCotizaciones() {
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
		String dataCotizaciones = request.getParameter("dataCotizaciones") == null ? "" : request.getParameter("dataCotizaciones");

		JSONObject result = new JSONObject();
		try{

			// decripto el mensaje
			if(dataCotizaciones!=null)
			{
				String decrypted="";
				try
				{
					decrypted = AES_Helper.decrypt(dataCotizaciones);
				}
				catch(Exception ex){
					result.put("estado", Boolean.FALSE);
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.put("mensaje", "El archivo no tiene el formato adecuado para poder descomprimir.");
					result.write(response.getWriter());
				}

				Gson g = new Gson();
				TransporteCotizaciones cotizaciones = g.fromJson(decrypted, TransporteCotizaciones.class);
				AgriObjetoCotizacionDAO objetoCotizacionDAO=new AgriObjetoCotizacionDAO();
				List<AgriObjetoCotizacion> listaCotizaciones;
				JSONArray cotizacionesJSONArray = new JSONArray();
				JSONObject cotizaJSONObject = new JSONObject();
				for(CotizacionAgricola cotizaActual:cotizaciones.getCotizacionAgricola()){
					listaCotizaciones=objetoCotizacionDAO.buscarPorObjetoOfflineId(cotizaActual.getObjetoCotizacionId());
					if(listaCotizaciones.size()==0){
						AgriRespuestaOffline respuestaProceso = AgriProcesarCotizacionOffline.procesarCotizacion(cotizaActual,"COTIZADOR_OFFLINE","","");
						if(!respuestaProceso.getCotizacion().equals("")){
							CotizacionDAO cotizacionDAO = new CotizacionDAO();
							Cotizacion cotizacion=cotizacionDAO.buscarPorId(respuestaProceso.getCotizacion());
							CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
							CotizacionDetalle cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0); 
							AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
							AgriObjetoCotizacion agriObjetoCotizacion= agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
							
							String elEstado=""+cotizacion.getEstado().getId();
							cotizaJSONObject.put("objetoCotizacionID", cotizaActual.getObjetoCotizacionId());
							cotizaJSONObject.put("cotizacionID", agriObjetoCotizacion.getIdCotizacion2());
							cotizaJSONObject.put("facturaID", "");
							cotizaJSONObject.put("comentario", "Cotizacion procesada con exito.");
							cotizaJSONObject.put("Estado", elEstado);
							//Buscamos el Beneficiario
							if(cotizacion.getPuntoVenta().getNombre().equals("CREDIFE"))
								cotizaJSONObject.put("Beneficiario", "BANCO DEL PICHINCHA");
							else
								cotizaJSONObject.put("Beneficiario", "COOPROGRESO");
							cotizacionesJSONArray.add(cotizaJSONObject);
						}
					}
					else{
						cotizaJSONObject.put("objetoCotizacionID", cotizaActual.getObjetoCotizacionId());
						cotizaJSONObject.put("cotizacionID", 0);
						cotizaJSONObject.put("facturaID", "");
						cotizaJSONObject.put("comentario", "La cotizacion ya fue procesada anteriormente");
						//Buscamos el Beneficiario
						//hallamos el beneficiario en base a la cotizacion:
						cotizaJSONObject.put("Beneficiario", "Ninguno");
						cotizaJSONObject.put("Estado", 0);
						cotizacionesJSONArray.add(cotizaJSONObject);
					}
				}
				result.put("listadoCotizaciones", cotizacionesJSONArray);
			}
			result.put("estado", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.put("mensaje", "El archivo a sido procesado con exito.");
			result.write(response.getWriter());
		}
		catch(Exception ex){
			result.put("estado", Boolean.FALSE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.put("mensaje", ex.getMessage());
			result.write(response.getWriter());
			ex.printStackTrace();
		}
	}

}
