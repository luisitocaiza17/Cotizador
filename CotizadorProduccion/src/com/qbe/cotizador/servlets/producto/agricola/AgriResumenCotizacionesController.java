package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReporteCotizacionVtaDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriReporteCotizacionVta;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.util.Utilitarios;

/**
 * Servlet implementation class AgriResumenCotizacionesController
 */
@WebServlet("/AgriResumenCotizacionesController")
public class AgriResumenCotizacionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriResumenCotizacionesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject ();
		try 
		{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId");
			String vigenciaPolizaId = request.getParameter("vigenciaPolizaId") == null ? "" : request.getParameter("vigenciaPolizaId");
			String agenteId = request.getParameter("agenteId") == null ? "" : request.getParameter("agenteId");
			String productoId = request.getParameter("productoId") == null ? "" : request.getParameter("productoId");
			String estadoId = request.getParameter("estadoId") == null ? "" : request.getParameter("estadoId");
			String tipoObjetoId = request.getParameter("tipoObjetoId") == null ? "" : request.getParameter("tipoObjetoId");
			String fechaDesde = request.getParameter("fechaDesde") == null ? "" : request.getParameter("fechaDesde");
			String fechaHasta = request.getParameter("fechaHasta") == null ? "" : request.getParameter("fechaHasta");
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
			String misCotizaciones = request.getParameter("misCotizaciones") == null ? "" : request.getParameter("misCotizaciones");
			String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
			String pendientesImprimir = request.getParameter("pendientesImprimir") == null ? "" : request.getParameter("pendientesImprimir");
			String estadoCotizacion = request.getParameter("estadoCotizacion") == null ? "" : request.getParameter("estadoCotizacion");
			String numeroTramite = request.getParameter("numeroTramite") == null ? "" : request.getParameter("numeroTramite");
			
			String usuarioId = "";
			
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");

			JSONObject cotizacionJsonObject = new JSONObject();
			JSONArray cotizacionJsonArray = new JSONArray();

			JSONObject puntoVentaJsonObject = new JSONObject();
			JSONArray puntoVentaJsonArray = new JSONArray();

			JSONObject productoJsonObject = new JSONObject();
			JSONArray productoJsonArray = new JSONArray();

			JSONObject estadoJsonObject = new JSONObject();
			JSONArray estadoJsonArray = new JSONArray();

			JSONObject tipoObjetoJsonObject = new JSONObject();
			JSONArray tipoObjetoJsonArray = new JSONArray();
			
			Estado estado = new Estado();

			PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
			VigenciaPolizaDAO vigenciaPolizaDAO = new VigenciaPolizaDAO();
			AgenteDAO agenteDAO = new AgenteDAO();
			ProductoDAO productoDAO = new ProductoDAO();
			EstadoDAO estadoDAO = new EstadoDAO();
			TipoObjeto tipoObjeto = new TipoObjeto();
			TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();			
					

			AgriReporteCotizacionVta reporteCotizacionVta = new AgriReporteCotizacionVta();
			AgriReporteCotizacionVtaDAO reporteCotizacionVtaDAO = new AgriReporteCotizacionVtaDAO();

			if(!puntoVentaId.equals("")){
				reporteCotizacionVta.setPuntoVentaId(new BigInteger(puntoVentaId));
			}
			if(!vigenciaPolizaId.equals("")){
				reporteCotizacionVta.setVigenciaPolizaId(new BigInteger(vigenciaPolizaId));
			}
			if(!puntoVentaId.equals("")){
				reporteCotizacionVta.setPuntoVentaId(new BigInteger(puntoVentaId));
			}
			if(!productoId.equals("")){
				reporteCotizacionVta.setProductoId(new BigInteger(productoId));
			}
			if(!estadoId.equals("")){
				reporteCotizacionVta.setEstadoId(new BigInteger(estadoId));
			}
			/*if(!tipoObjetoId.equals("")){
				reporteCotizacionVta.setTipoObjetoId(new BigInteger(tipoObjetoId));
			}*/
			if(!puntoVentaId.equals("")){
				reporteCotizacionVta.setPuntoVentaId(new BigInteger(puntoVentaId));
			}			
			
			///TODO:Aprobacion/Emision Masiva
			if (tipoConsulta.equals("importar")) {
				
				//verificamos que el usuario este en secion;
				HttpSession sessionUsuario = request.getSession(true);
				Usuario usuarioSession = (Usuario) session.getAttribute("usuario");
				if(usuarioSession==null)
					throw new Exception("El usuario no esta en seccion, debe refrescar la p�gina");
				
				String FileName = request.getParameter("FileName") == null ? "": request.getParameter("FileName");
								
				String rutaPlantilla = this.getServletContext().getRealPath("")+ "/static/CotizacionesAprobarMasivo/" + FileName;
				
				List<Cotizacion> cotizacionList = new ArrayList<Cotizacion>();

				if (FileName.endsWith(".xlsx")) {cotizacionList = ReadExelFile.readXLSXFileAprobacionMasiva(rutaPlantilla);
				} else if (FileName.endsWith(".xls")) {cotizacionList = ReadExelFile.readXLSFileAprovacionMasiva(rutaPlantilla);
				}
				
				//respuesta de consulta
				List<String> listadoCotizaciones = new ArrayList<String>();
				List<String> listadoTramites = new ArrayList<String>();
				
				//verifica buscamos el numero de cotizacion si no la hay buscamoes el numero de tramite
				
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion= cotizacionDAO.buscarPorId(cotizacionList.get(0).getId());
				
				if(cotizacion.getId()!=null){
					for(Cotizacion rs:cotizacionList){
						listadoCotizaciones.add(rs.getId());
					}
				}else{
					for(Cotizacion rs:cotizacionList){
						listadoTramites.add(rs.getId());
					}
				}
				
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				listaCotizacionesJSONArray = new JSONArray();
				
				CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
				
				List<AgriCotizacionAprobacion> data = cDAO.buscarCotizacionesMasivas(listadoCotizaciones,listadoTramites);
				long total = cDAO.buscarCotizacionesMasivasNum(listadoCotizaciones,listadoTramites);
					
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)total);
				pg.setData(data);
				
				//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
			}
			
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
					estadoJsonObject.put("estadoId", r.getId());
					if(r.getId().equals("13")){
						estadoJsonObject.put("nombre", "Aprobado Agricola");
					}else{
					if(r.getId().equals("21"))
						estadoJsonObject.put("nombre", "Pendiente Agricola");
					else
						estadoJsonObject.put("nombre", r.getNombre());
					}
					estadoJsonArray.add(estadoJsonObject);
				}
				result.put("listEstado", estadoJsonArray);

				tipoObjeto = tipoObjetoDAO.buscarPorNombre("AGRICOLA");				
				tipoObjetoJsonObject.put("objetoId", tipoObjeto.getId());
				tipoObjetoJsonObject.put("nombre", tipoObjeto.getNombre());
				tipoObjetoJsonArray.add(tipoObjetoJsonObject);
				
				result.put("listObjeto", tipoObjetoJsonArray);

			}
			
			if(tipoConsulta.equalsIgnoreCase("encontrarCotizacionesPorEstado")) {
				/*Busco el estado archivado con la calse cotizaci�n*/
				estado = estadoDAO.buscarPorId(estadoCotizacion);
				
				if(request.getSession().getAttribute("usuario")!= null)
					usuario = (Usuario)request.getSession().getAttribute("usuario");				
				
				tipoObjeto = tipoObjetoDAO.buscarPorNombre("AGRICOLA");
					

				if(misCotizaciones.equalsIgnoreCase("true"))
					usuarioId = usuario.getId();
								
				
				RolDAO rolDAO=new RolDAO();
				Rol rol=new Rol();
				
				JSONArray listaCotizacionesJSONArray = new JSONArray();
				if(usuario.getUsuarioRols().size()>0)
					rol=usuario.getUsuarioRols().get(0).getRol();
				
				
				if((Utilitarios.verificarAdministradorBusqueda(rol))){// Validacion para administradores segun el rol										
					cotizacionJsonArray = consultarPorTipoObjetoParaAgricola(fechaDesde, fechaHasta, tipoObjeto,cotizacionId,puntoVentaId,agenteId,identificacion,usuarioId,estado, "",numeroTramite);
					result.put("listadoCotizacion", cotizacionJsonArray);					
				}else{
					PuntoVenta puntoVenta=new PuntoVenta();					
					if(usuario.getUsuarioXPuntoVentas().size()>0){
						puntoVenta=usuario.getUsuarioXPuntoVentas().get(0).getPuntoVenta();
						listaCotizacionesJSONArray = consultarPorTipoObjetoPuntoVentaYEstado(fechaDesde, fechaHasta, tipoObjeto, puntoVenta,cotizacionId,agenteId,identificacion,usuarioId, estado,"",numeroTramite);
						result.put("listadoCotizacion", cotizacionJsonArray);						
					}
				}
			}
			
			
			

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());		

		}
		catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

	public static java.sql.Timestamp stringToTimestamp(String str_date) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			// you can change format of date
			Date date = formatter.parse(str_date);
			java.sql.Timestamp timeStampDate = new java.sql.Timestamp(date.getTime());

			return timeStampDate;
		} catch (Exception e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
	
	//Filtro para las ventana de canales en ganadero
	public JSONArray consultarPorTipoObjetoParaAgricola(String fecha1, String fecha2, TipoObjeto tipoObjeto, String cotizacionId,String puntoVenta, String agenteId,String identificacion,String usuarioId, Estado FiltroEstado, String pendientesImprimir, String numeroTramite){
		JSONObject retorno= new JSONObject();
		AgriReporteCotizacionVtaDAO cDAO= new AgriReporteCotizacionVtaDAO();	
		
		JSONObject cotizacionJsonObject = new JSONObject();
		JSONArray cotizacionJsonArray = new JSONArray();
		Usuario usuario = new Usuario();		
		
		if(pendientesImprimir.equalsIgnoreCase("true"))
			pendientesImprimir = "0";
		else if(pendientesImprimir.equalsIgnoreCase("false"))
			pendientesImprimir = "1";
			
		
		List<AgriReporteCotizacionVta> cotizacion=cDAO.buscarPorTipoObjetoParaCanalyEstado(fecha1, fecha2, tipoObjeto,cotizacionId,puntoVenta,agenteId,identificacion,usuarioId, FiltroEstado, pendientesImprimir,numeroTramite);
		 
		for(AgriReporteCotizacionVta r: cotizacion){
			cotizacionJsonObject.put("id", r.getId());
			cotizacionJsonObject.put("puntoVenta", r.getPuntoVenta());
			cotizacionJsonObject.put("vigencia", r.getVigencia());
			
			Cliente cliente = new Cliente();
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscarPorId(r.getClienteId().toString());
			cotizacionJsonObject.put("cliente", cliente.getEntidad().getNombreCompleto());
			
			//Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.buscarPorId(r.getUsuarioId().toString());
			cotizacionJsonObject.put("usuario", usuario.getEntidad().getNombreCompleto());
			
			Agente agente = new Agente();
			AgenteDAO agenteDAO2 = new AgenteDAO();
			agente = agenteDAO2.buscarPorId(r.getAgenteId().toString());					
			cotizacionJsonObject.put("agente", agente.getEntidad().getNombreCompleto());	
			
			cotizacionJsonObject.put("producto", r.getProducto());
			cotizacionJsonObject.put("estado", r.getEstado());
			cotizacionJsonObject.put("objeto", r.getObjeto());
			cotizacionJsonObject.put("fecha", r.getFechaElaboracion().toString());
			cotizacionJsonObject.put("comision", r.getPorcentajeComision());
			cotizacionJsonObject.put("sumaTotal", r.getSumaAseguradaTotal());
			cotizacionJsonObject.put("primaNetaTotal", r.getPrimaNetaTotal());
			cotizacionJsonArray.add(cotizacionJsonObject);
		}
		//result.put("cotizacionList", cotizacionJsonArray);	
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJsonArray;
	}
	
	public JSONArray consultarPorTipoObjetoPuntoVentaYEstado(String fecha1, String fecha2, TipoObjeto tipoObjeto, PuntoVenta puntoVenta,String numeroCotizacion,String agenteId,String identificacion,String usuarioId, Estado FiltroEstado, String pendientesImprimir, String numeroTramite){
		JSONObject retorno= new JSONObject();
		AgriReporteCotizacionVtaDAO cDAO= new AgriReporteCotizacionVtaDAO();
				
		if(pendientesImprimir.equalsIgnoreCase("true"))
			pendientesImprimir = "0";
		else if(pendientesImprimir.equalsIgnoreCase("false"))
			pendientesImprimir = "1";
		
		List<AgriReporteCotizacionVta> cotizacion=cDAO.buscarPorTipoObjetoxFechaPuntoVentaYEstado(fecha1, fecha2, tipoObjeto, puntoVenta,numeroCotizacion,agenteId,identificacion,usuarioId,FiltroEstado,pendientesImprimir,numeroTramite);
		
		JSONObject cotizacionJsonObject = new JSONObject();
		JSONArray cotizacionJsonArray = new JSONArray();
		Usuario usuario = new Usuario();for(AgriReporteCotizacionVta r: cotizacion){
			cotizacionJsonObject.put("id", r.getId());
			cotizacionJsonObject.put("puntoVenta", r.getPuntoVenta());
			cotizacionJsonObject.put("vigencia", r.getVigencia());
			
			Cliente cliente = new Cliente();
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.buscarPorId(r.getClienteId().toString());
			cotizacionJsonObject.put("cliente", cliente.getEntidad().getNombreCompleto());
			
			//Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario = usuarioDAO.buscarPorId(r.getUsuarioId().toString());
			cotizacionJsonObject.put("usuario", usuario.getEntidad().getNombreCompleto());
			
			Agente agente = new Agente();
			AgenteDAO agenteDAO2 = new AgenteDAO();
			agente = agenteDAO2.buscarPorId(r.getAgenteId().toString());					
			cotizacionJsonObject.put("agente", agente.getEntidad().getNombreCompleto());	
			
			cotizacionJsonObject.put("producto", r.getProducto());
			cotizacionJsonObject.put("estado", r.getEstado());
			cotizacionJsonObject.put("objeto", r.getObjeto());
			cotizacionJsonObject.put("fecha", r.getFechaElaboracion().toString());
			cotizacionJsonObject.put("comision", r.getPorcentajeComision());
			cotizacionJsonObject.put("sumaTotal", r.getSumaAseguradaTotal());
			cotizacionJsonObject.put("primaNetaTotal", r.getPrimaNetaTotal());
			cotizacionJsonArray.add(cotizacionJsonObject);
		}
		//result.put("cotizacionList", cotizacionJsonArray);	
		//retorno.put("listadoCotizacion", cotizacionJSONArray);
		return cotizacionJsonArray;
	}


}
