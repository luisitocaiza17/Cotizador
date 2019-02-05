package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.CotizacionPymeResumenDAO;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCotizacionResumen;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.servlets.producto.agricola.DataSourceResult;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;


/**
 * Servlet implementation class AgriCotizacionAprobacionController
 */
@WebServlet("/PymeCotizacionReporteController")
public class PymeCotizacionReporteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String IdFileIds = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymeCotizacionReporteController() {
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
		doPost(request, response);
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
			String cotizacionId = request.getParameter("cotizacionId") == null ? ""
					: request.getParameter("cotizacionId").trim();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? ""
					: request.getParameter("tipoConsulta");
			
			// /TODO: obtener usuario
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
			ProductoDAO productoDAO = new ProductoDAO();
			EstadoDAO estadoDAO = new EstadoDAO();
			TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
			
			TipoObjeto tipoObjeto = new TipoObjeto();
			
			JSONObject puntoVentaJsonObject = new JSONObject();
			JSONArray puntoVentaJsonArray = new JSONArray();
			
			JSONObject estadoJsonObject = new JSONObject();
			JSONArray estadoJsonArray = new JSONArray();
			
			JSONObject productoJsonObject = new JSONObject();
			JSONArray productoJsonArray = new JSONArray();
			
			JSONObject tipoObjetoJsonObject = new JSONObject();
			JSONArray tipoObjetoJsonArray = new JSONArray();
			
			if(tipoConsulta.equals("cargarCombos")){
				List<PuntoVenta> puntoVentaList = puntoVentaDAO.buscarActivos();
				for(PuntoVenta r : puntoVentaList){
					puntoVentaJsonObject.put("puntoVentaId", r.getId());
					puntoVentaJsonObject.put("nombre", r.getNombre());
					puntoVentaJsonArray.add(puntoVentaJsonObject);
				}
				result.put("listPuntoVenta", puntoVentaJsonArray);				

				Producto producto = productoDAO.buscarPorNombre("AGRICOLA");				
				productoJsonObject.put("productoId", producto.getId());
				productoJsonObject.put("nombre", producto.getNombre());
				productoJsonArray.add(productoJsonObject);
				
				result.put("listProducto", productoJsonArray);

				List<Estado> estadoList = estadoDAO.buscarPorClaseTodos("Cotizacion");
				for(Estado r : estadoList){
					if(r.getId().equals("1") || r.getId().equals("2") || r.getId().equals("3")  || r.getId().equals("13") || r.getId().equals("21") || r.getId().equals("25") || r.getId().equals("32") || r.getId().equals("33"))
					{
						estadoJsonObject.put("estadoId", r.getId());
						estadoJsonObject.put("nombre", r.getNombre());
						estadoJsonArray.add(estadoJsonObject);
					}
				}
				result.put("listEstado", estadoJsonArray);

				tipoObjeto = tipoObjetoDAO.buscarPorNombre("AGRICOLA");				
				tipoObjetoJsonObject.put("objetoId", tipoObjeto.getId());
				tipoObjetoJsonObject.put("nombre", tipoObjeto.getNombre());
				tipoObjetoJsonArray.add(tipoObjetoJsonObject);
				
				result.put("listObjeto", tipoObjetoJsonArray);

			}
			

			// Cotizaciones por estado
			if (tipoConsulta.equalsIgnoreCase("encontrarPorEstado")) {
				String fInicio = request.getParameter("fInicio") == null ? ""
						: request.getParameter("fInicio");
				String fFinal = request.getParameter("fFinal") == null ? ""
						: request.getParameter("fFinal");
				String numeroCotizacion = request
						.getParameter("numeroCotizacion") == null ? ""
						: request.getParameter("numeroCotizacion");
				String puntoVentaId = request.getParameter("puntoVenta") == null ? ""
						: request.getParameter("puntoVenta");
				String AgenteId = request.getParameter("agente") == null ? ""
						: request.getParameter("agente");
				String NroTramite = request.getParameter("NroTramite") == null ? ""
						: request.getParameter("NroTramite");
				String ApellidosCliente = request
						.getParameter("ApellidosCliente") == null ? ""
						: request.getParameter("ApellidosCliente");
				String Identificacion = request.getParameter("identificacion") == null ? ""
						: request.getParameter("identificacion");
				String CanalId = request.getParameter("CanalId") == null ? ""
						: request.getParameter("CanalId");
				String misCotizaciones = request
						.getParameter("misCotizaciones") == null ? "" : request
						.getParameter("misCotizaciones");
				
				String usuarioId = "";

				// get the take and skip parameters
				int skip = request.getParameter("skip") == null ? 0 : Integer
						.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer
						.parseInt(request.getParameter("take"));

				String estadoConsulta = request.getParameter("estadoConsulta") == null ? ""
						: request.getParameter("estadoConsulta");

				if (misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();

				Estado estado = new Estado();
				estado = estadoDAO.buscarPorId(estadoConsulta);

				Rol rol = new Rol();
				List<PymeCotizacionResumen> cotizacionList = new ArrayList<PymeCotizacionResumen>();
				CotizacionPymeResumenDAO cDAO = new CotizacionPymeResumenDAO();
				long contador = 0;

				cotizacionList = cDAO.consultarPorEstado(fInicio, fFinal,
						numeroCotizacion, puntoVentaId, AgenteId,
						Identificacion, usuarioId, 
						ApellidosCliente,  estado, (int) skip,
						(int) take);
				contador = cDAO.consultarPorEstadoContador(fInicio, fFinal,
						numeroCotizacion, puntoVentaId, AgenteId,
						Identificacion, usuarioId, "", NroTramite,
						ApellidosCliente, CanalId, estado, (int) skip,
						(int) take);
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int) contador);
				pg.setData(cotizacionList);

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				// convert the DataSourceReslt to JSON and write it to the
				// response
				response.setContentType("application/json; charset=ISO-8859-1");
				response.getWriter().print(gson.toJson(pg));
				return;
			}

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());

		} catch (Exception e) {
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
