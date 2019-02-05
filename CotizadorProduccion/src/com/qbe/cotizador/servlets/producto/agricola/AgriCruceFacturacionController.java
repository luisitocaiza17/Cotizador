package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEM;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.exceptionMappingType;

/**
 * Servlet implementation class AgriCruceFacturacionController
 */
@WebServlet("/AgriCruceFacturacionController")
public class AgriCruceFacturacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private List<AgriCruceFacturacion> listadoProblemas;
	private List<AgriCotizacionAprobacion> listadoCorrectos;
	
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCruceFacturacionController() {
        super();
        // TODO Auto-generated constructor stub
        listadoProblemas= new ArrayList<AgriCruceFacturacion>();
        listadoCorrectos= new ArrayList<AgriCotizacionAprobacion>();        
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
			String nombreArchivo = request.getParameter("nombreArchivo") == null ? "":request.getParameter("nombreArchivo");
			
			
			//primero cargamos el archivo de excel y verificamos que este en el formato .excel
			if(tipoConsulta.equals("")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (!item.isFormField()) {							
							String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
									.getLocation().getPath();
							rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
							String rutaRelativaReporte=".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano";
							System.out.println("rutaRelativaReporte " + rutaRelativaReporte);
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
							
							String fileName = item.getName();
							try {
								File saveFile = new File(rutaPlantilla,fileName);
								saveFile.createNewFile();
								item.write(saveFile);								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (FileUploadException e) {
					try {
						throw new ServletException("Cannot parse multipart request.", e);
					} catch (ServletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}   
			}
			
			//Proceso de lectura de archivo excel y analisis de informacion.
			//importamos las cotizaciones que se encuentran en el archivo excel
			if(tipoConsulta.equals("importarKendo")){
				System.out.println("Cruce de Informacion Reaseguros Agricola");
				long total =AgriCruceAgricolaGeneral.listaCorrectos.size();
				DataSourceResult pg = new DataSourceResult();
				//Ponemos en formato condiciones de emision de reaseguros
				/**
				 * -	APELLIDOS Y NOMBRES:                       Mayúsculas, sin tildes, no “ñ”, no signos
				 * -	CULTIVO:                                   De acuerdo a la parametrización en sistema; si acepta ñ “piña, caña” sin tildes
				 * -	PROV/CANT/PARROQUIA                        Mayúsculas, sin tildes, no “ñ”, no signos
				 * -	VALORES:                                   Con 2 decimales, sin signos de dólar, cambiar la coma por punto
				 * -	Comisión:                                  6% de la Prima Neta
				 * -	Dirección:                                 Si puede tener signos (, /).				 
				 * **/
				List<AgriCruceCotizacionResult> listadoFinal= new ArrayList<AgriCruceCotizacionResult>();
				
				for(AgriCotizacionAprobacion rs:AgriCruceAgricolaGeneral.listaCorrectos){
					AgriCruceCotizacionResult cot= new AgriCruceCotizacionResult();
					cot.setNumeroTramite(rs.getNumeroTramite());
					cot.setIdCotizacion2(rs.getIdCotizacion2().toString());
					cot.setNombresCliente(rs.getNombresCliente().toUpperCase().replace("Ñ", "N").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U"));
					cot.setIdentificacionCliente(rs.getIdentificacionCliente());
					cot.setTipoCultivoNombre(rs.getTipoCultivoNombre().toUpperCase().replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U"));
					cot.setProvinciaNombre(rs.getProvinciaNombre().replace("Ñ", "N").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U"));
					cot.setCantonNombre(rs.getCantonNombre().replace("Ñ", "N").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U"));
					cot.setParroquiaNombre(rs.getParroquiaNombre().replace("Ñ", "N").replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U"));
					cot.setDireccionSiembra(rs.getDireccionSiembra());
					cot.setVigenciaDias(rs.getVigenciaDias());
					cot.setFechaVigenciaDesde(rs.getFechaVigenciaDesde());
					cot.setFechaVigenciaHasta(rs.getFechaVigenciaHasta());
					cot.setHectareasAsegurables(rs.getHectareasAsegurables());
					cot.setSumaAseguradaPorCiento(rs.getSumaAseguradaPorCiento());
					cot.setTasa(rs.getTasa());
					cot.setPrimaNetaPorCiento(rs.getPrimaNetaPorCiento());
					BigDecimal T = new BigDecimal(""+(rs.getPrimaNetaPorCiento()*0.06));
					BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
					cot.setComision(Double.parseDouble(""+roundOffT));					
					listadoFinal.add(cot);
				}
				
				
				pg.setTotal((int)total);
				pg.setData(listadoFinal);
				
				Gson gson = new Gson();
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;
				
			}
			
			if(tipoConsulta.equals("importarErrores")){
				String proceso = request.getParameter("proceso") == null ? "":request.getParameter("proceso");
				System.out.println("Cruce de Informacion Reaseguros Agricola");
				
				String rutaPlantilla = this.getServletContext().getRealPath("") + ""+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano"+File.separator+""+nombreArchivo;
				System.out.println("rutaRelativaReporte almacenada: " + rutaPlantilla);
				AgriCruceAgricolaGeneral.listaExcel= new ArrayList<AgriCruceFacturacion>();
				
				if(nombreArchivo.endsWith(".xlsx")){
					AgriCruceAgricolaGeneral.listaExcel = ReadExelFile.readXLSXFileCruceFacturacionList(rutaPlantilla);		
				}else if(nombreArchivo.endsWith(".xls")){
					AgriCruceAgricolaGeneral.listaExcel = ReadExelFile.readXLSXFileCruceFacturacionList(rutaPlantilla);					 
				}
					
				//enviamos a que se procesen los tramites
				busquedaCotizaciones(AgriCruceAgricolaGeneral.listaExcel);
				
				JSONObject item = new JSONObject();
				JSONArray listCotError = new JSONArray();
				//llenamos el listado de cotizaciones con errores
				for (AgriCruceFacturacion rs: AgriCruceAgricolaGeneral.listaIncorrectos)
				{
					item.put("tramite", rs.getTramite());
					item.put("cliente", rs.getCliente());
					item.put("sumaAsegurada", rs.getSumaAsegurada());
					item.put("sumaAseguradaDes", rs.getSumaAseguradaDes());
					item.put("primaAsegurada", rs.getPrimaAsegurada());
					item.put("primaAseguradaDes", rs.getPrimaAseguradaDes());
					item.put("observacion", rs.getObservacion());
					listCotError.add(item);
				}
				result.put("listCotError",listCotError);
				result.put("mensaje","Se procesaron "+AgriCruceAgricolaGeneral.listaExcel.size()+" Trámites de las cuales "+AgriCruceAgricolaGeneral.listaCorrectos.size()+" son CORRECTOS y "
						+AgriCruceAgricolaGeneral.listaIncorrectos.size()+" son INCORRECTOS , ¿DESEA PONER EN ESTADO FACTURADO LAS COTIZACIONES CORRECTAS?");
			}
			
			if(tipoConsulta.equals("cambiosEstados")){
				
				/**PROCESO DE AUDITORIA PARA CONOCER EL CAMBIO DE ESTADO**/
				
				AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
				AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
				java.util.Date date2 = new java.util.Date();
				java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				auditoria.setFecha(sq2);
				auditoria.setCanal("SUCRE");
				
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorNombre("Facturado", "Cotizacion");
				try{
					for(AgriCotizacionAprobacion rs:AgriCruceAgricolaGeneral.listaCorrectos){
						auditoria.setTramite("" + rs.getNumeroTramite());
						auditoria.setEstado("FACTURADO");
						String DatosRecibidos = " La cotizcion:"+rs.getId()+" con el número de trámite "+rs.getNumeroTramite()+" fue cambiado de estado a facturado";
						auditoria.setObjeto(DatosRecibidos);
						
						
						CotizacionDAO cotizacionDAO= new CotizacionDAO();
						CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
						Cotizacion cotizacion=cotizacionDAO.buscarPorId(rs.getId().toString());
						cotizacion.setEstado(estado);
						cotizacionTransaction.editar(cotizacion);
						procesoAuditoria.crear(auditoria);
					}
					result.put("correcto", Boolean.TRUE);
				}catch(Exception e){
					auditoria.setObjeto("Error: "+e.getMessage());
					auditoria.setEstado("ERROR");					
					procesoAuditoria.crear(auditoria);
					result.put("correcto", Boolean.FALSE);
					result.put("mensaje", e.getMessage());
					e.printStackTrace();
				}
				
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		
		}catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("mensaje", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}
	
	//Proceso de revision de los tramites, existen o no?!!
	private void busquedaCotizaciones(List<AgriCruceFacturacion> cotizacionList){
		//buscamos en la base de cotizaciones en base al numero de tramite, verificamos que coincida:
		/**
		 * 1)Verificamos que exista la cotizacion por el numero de tramite.
		 * 2)Verificamos que las cotizaciones esten confirmadas como emitidas
		 * 3)Verificamos que las sumas sean aseguradas 100% y 90%
		 * 4)Verificamos que las primas sean iguales al 100% y al 90%
		 **/
		AgriCruceAgricolaGeneral.listaIncorrectos= new ArrayList<AgriCruceFacturacion>();
		AgriCruceAgricolaGeneral.listaCorrectos= new ArrayList<AgriCotizacionAprobacion>();
		
		for(AgriCruceFacturacion consulta: cotizacionList){
			CotizacionAprobacionDAO cotizacionAprobacionDAO= new CotizacionAprobacionDAO();
			AgriCotizacionAprobacion cotizacion = cotizacionAprobacionDAO.buscarPorTramite(consulta.getTramite().trim());
			/**1) Verificamos si existe**/
			if(cotizacion.getNumeroTramite()==null){
				//busco los codigos de canales sucre				
				cotizacion = cotizacionAprobacionDAO.buscarPorTramiteCoincidencia(consulta.getTramite()
						.replace("190115798001-", "").replace("0990018707001-", "").replace("12345678912-", "").replace("1290068068001-", "")
						.replace("1291722039001-", "").replace("1768173990001-", "").replace("1792344859001-", "").replace("0990247536001-", "")						
						.replace("0290003180001-", "").replace("1360001600001-", "").replace("1360041580001-", "").replace("1360030890001-", "")						
						.replace("1390091474001-", "").replace("1792607566001-", "").replace("1360086170001-", "")						
						.replace("1053-", "").replace("1053-2-", "").replace("1053-3-", "").replace("4378-2-", "").replace("4378-", "")
						.replace("1028-", "").replace("1025-", ""));
				if(cotizacion.getNumeroTramite()==null){
					//realizamos la busqueda de un tramite por coincidencias, ya que sucre puede enviar numeros de tramites compuestos por otro numero
					cotizacion = cotizacionAprobacionDAO.buscarPorTramiteCoincidencia(consulta.getTramite().trim());
					if(cotizacion.getNumeroTramite()==null){
						consulta.setObservacion("El numero de trámite no esta registrado en el sistema");
						AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
						continue;
					}				
				}
				
			}
			
			
			/**2) Verificamos que esten confirmados como emitidos**/
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.buscarPorNombre("Pendiente de Emitir", "Cotizacion");
			if(cotizacion.getConfirmacion().equals("CONFIRMADO")&&cotizacion.getEstadoId().toString().equals(estado.getId()))
				System.out.println("Estados facturacion Correctos");
			else{
				consulta.setObservacion("El tramite se encuentra en estado: "+cotizacion.getEstadoCotizacion()+" emision: "+cotizacion.getConfirmacion()+", no se puede"
						+ " facturar debe estar en estado pendiente de emitir(Aprobada) y confirmada para emisión por parte del canal.");
				AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
				continue;
			}
			
			/**3) Verificamos las sumas aseguradas al 100% y al 90%**/
			
			BigDecimal T = new BigDecimal(""+cotizacion.getSumaAseguradaTotal());
			BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			Double sumaAseguradaQBETotal=Double.parseDouble(""+roundOffT);
			
			T = new BigDecimal(""+consulta.getSumaAsegurada());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
			Double sumaAseguradaReaseguroTotal=Double.parseDouble(""+roundOffT);
			
			int sumasTotalesIguales=sumaAseguradaQBETotal.compareTo(sumaAseguradaReaseguroTotal);
			if(sumasTotalesIguales!=0){
				consulta.setObservacion("El valor de la suma Asegurada del tramite es distinta, QBE: "+cotizacion.getSumaAseguradaPorCiento()+"||Archivo "+consulta.getPrimaAseguradaDes());
				AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
				continue;
			}
			
			T = new BigDecimal(""+cotizacion.getSumaAseguradaPorCiento());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);			
			Double sumaAseguradaQBE=Double.parseDouble(""+roundOffT);
			
			T = new BigDecimal(""+consulta.getSumaAseguradaDes());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);			
			Double sumaAseguradaReaseguro=Double.parseDouble(""+roundOffT);
			
			int sumasPorcentualesIguales=sumaAseguradaQBE.compareTo(sumaAseguradaReaseguro);
			if(sumasPorcentualesIguales!=0){
				consulta.setObservacion("El valor porcentual de la suma Asegurada del tramite es distinto, QBE: "+cotizacion.getSumaAseguradaPorCiento()+"||Archivo "+consulta.getPrimaAseguradaDes());
				AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
				continue;
			}
			
			/**4) Revisamos los valores de las primas Netas**/
			T = new BigDecimal(""+cotizacion.getPrimaNetaTotal());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);	
			Double primaNetaQBETotal=Double.parseDouble(""+roundOffT);
			
			
			T = new BigDecimal(""+consulta.getPrimaAsegurada());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);				
			Double primaNetaReaseguroTotal=Double.parseDouble(""+roundOffT);
			
			int primasTotalesIguales=primaNetaQBETotal.compareTo(primaNetaReaseguroTotal);
			if(primasTotalesIguales!=0){
				consulta.setObservacion("El valor de la prima Neta del tramite es distinta, QBE: "+cotizacion.getSumaAseguradaPorCiento()+"||Archivo "+consulta.getPrimaAseguradaDes());
				AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
				continue;
			}
			
			T = new BigDecimal(""+cotizacion.getPrimaNetaPorCiento());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);				
			Double primaNetaQBE=Double.parseDouble(""+roundOffT);
			
			T = new BigDecimal(""+consulta.getPrimaAseguradaDes());
			roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
			Double primaNetaReaseguro=Double.parseDouble(""+roundOffT);
			
			int primasNetasIguales=primaNetaQBE.compareTo(primaNetaReaseguro);
			if(primasNetasIguales!=0){
				consulta.setObservacion("El valor porcentual de la prima Neta del tramite es distinto, QBE: "+cotizacion.getSumaAseguradaPorCiento()+"||Archivo "+consulta.getPrimaAseguradaDes());
				AgriCruceAgricolaGeneral.listaIncorrectos.add(consulta);
				continue;
			}
			AgriCruceAgricolaGeneral.listaCorrectos.add(cotizacion);
		}
	}
}
