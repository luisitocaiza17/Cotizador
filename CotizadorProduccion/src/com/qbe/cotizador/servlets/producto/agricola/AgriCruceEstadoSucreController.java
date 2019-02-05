package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;

/**
 * Servlet implementation class AgriCruceEstadoSucreController
 */
@WebServlet("/AgriCruceEstadoSucreController")
public class AgriCruceEstadoSucreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCruceEstadoSucreController() {
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
		// TODO Auto-generated method stub
				JSONObject result = new JSONObject();
				try {
					String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
					
					if(tipoConsulta.equals("encontrarPorParametros") ){
						String tramite = request.getParameter("nro_tramite") == null ? "": request.getParameter("nro_tramite").trim();
						String fInicio = request.getParameter("fInicio") == null ? "": request.getParameter("fInicio");
						String fFinal = request.getParameter("fFinal") == null ? "": request.getParameter("fFinal");
						
						List<AgriCotizacionAprobacion> cotizacionList = new ArrayList<AgriCotizacionAprobacion>();
						CotizacionAprobacionDAO cotizacionAprobacionDAO = new CotizacionAprobacionDAO();
						Estado estado = new Estado();
						cotizacionList=cotizacionAprobacionDAO
								.consultarPorEstado2
								(fInicio, fFinal, "", 
								tramite,estado);
						
						/**Enviamos el numero de tramite al WS Sucre**/
						List<AgriCrucesEstadoSucre> listAgriCrucesEstadoSucre= new ArrayList<AgriCrucesEstadoSucre>();
						for(AgriCotizacionAprobacion rs:cotizacionList){
							AgriCrucesEstadoSucre agriCrucesEstadoSucre = new AgriCrucesEstadoSucre();
							agriCrucesEstadoSucre.setIdCotizacion(rs.getId().toString());
							agriCrucesEstadoSucre.setTramite(rs.getNumeroTramite());
							agriCrucesEstadoSucre.setEstadoQbe(rs.getEstadoCotizacion());
							agriCrucesEstadoSucre.setEstadoSucre("EL ESTADO DE SUCRE");//ESTADO PROVENIENTE DE SUCRE
							
							if(rs.getEstadoCotizacion().equals("Aprobado")){//ESTADOS PROVENIENTE DE SUCRE
								agriCrucesEstadoSucre.setObservacion("DIFERENCIAS");								
							}else{
								agriCrucesEstadoSucre.setObservacion("CORRECTO");	
							}
							listAgriCrucesEstadoSucre.add(agriCrucesEstadoSucre);							
						}
																		
						long total=listAgriCrucesEstadoSucre.size();
						
						DataSourceResult pg = new DataSourceResult();
						pg.setTotal((int)total);
						pg.setData(listAgriCrucesEstadoSucre);
						
						Gson gson = new Gson();
						// convert the DataSourceReslt to JSON and write it to the response
						response.setContentType("application/json; charset=ISO-8859-1");
					    response.getWriter().print(gson.toJson(pg));
					    return;
						
					}
					
					if(tipoConsulta.equals("procesoCambioEstado") ){
						
						String cotizacionId = request.getParameter("cotizacion") == null ? "": request.getParameter("cotizacion").trim();
						String estadoSucre = request.getParameter("estadoSucre") == null ? "": request.getParameter("estadoSucre").trim();
						
						/**Auditoria Interna**/
						AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
						AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
						auditoria.setTramite("" +cotizacionId);
						String DatosRecibidos = " cotizacion "+cotizacionId+" estado sucre "+estadoSucre;
						
						auditoria.setObjeto(DatosRecibidos);
						java.util.Date date2 = new java.util.Date();
						java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
						auditoria.setFecha(sq2);
						auditoria.setCanal("SUCRE");
						auditoria.setEstado("ACTUALIZACION E");
						procesoAuditoria.crear(auditoria);
						
						if(estadoSucre.trim().toUpperCase().equals("N/A")){
							throw new Exception("COTIZACION: "+cotizacionId+" No se puede actualizar un tramite que no esta registrado en sucre");
						}
						
						Cotizacion cotizacion = new Cotizacion();
						CotizacionDAO  cotizacionDAO= new CotizacionDAO();
						Estado estado= new Estado();
						EstadoDAO estadoDAO= new EstadoDAO();						
						
						cotizacion=cotizacionDAO.buscarPorId(cotizacionId);						
						
						switch(estadoSucre.trim().toUpperCase()){
							case "PENDIENTE":
								estado=estadoDAO.buscarPorId("21");
								break;
							case "APROBADO":
								estado=estadoDAO.buscarPorId("13");
								break;
							case "RECHAZADO":
								estado=estadoDAO.buscarPorId("30");
								break;						
						}
						
						if(estado.getNombre()==null){
							throw new Exception("TRAMITE: "+cotizacion.getNumeroTramite()+" NO SE PUEDE ACTUALIZAR AL ESTADO: "+ estadoSucre+ " ESTADO NO ENCONTRADO");
						}
							
						
						cotizacion.setEstado(estado);
						CotizacionTransaction  cotizacionTransaction = new CotizacionTransaction();
						cotizacionTransaction.editar(cotizacion);
						auditoria.setObjeto(auditoria.getObjeto()+" - PROCESO CORRECTO. ");
						procesoAuditoria.editar(auditoria);
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
					
					/**Auditoria Interna**/
					AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
					AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
					auditoria.setTramite("ERROR ACTUALIZACION");
					String DatosRecibidos = " ERROR: "+e.getMessage();					
					auditoria.setObjeto(DatosRecibidos);
					java.util.Date date2 = new java.util.Date();
					java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
					auditoria.setFecha(sq2);
					auditoria.setCanal("SUCRE");
					auditoria.setEstado("ACTUALIZACION E");
					procesoAuditoria.crear(auditoria);
				}

	}

}
