package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEndosoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriCotizacionEndoso;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriCotizacionEndosoTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.util.Utilitarios;

/**
 * Servlet implementation class AgriProcesosEmisionesController
 */
@WebServlet("/AgriProcesosEmisionesController")
public class AgriProcesosEmisionesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriProcesosEmisionesController() {
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
					
					//importamos las cotizaciones que se encuentran en el archivo excel
					if(tipoConsulta.equals("importarKendo")){
						String proceso = request.getParameter("proceso") == null ? "":request.getParameter("proceso");
						System.out.println("Proceso Masivo: "+proceso);
						
						String rutaPlantilla = this.getServletContext().getRealPath("") + ""+File.separator+"static"+File.separator+"plantillas"+File.separator+"Agricola"+File.separator+"CotizacionesArchivoPlano"+File.separator+""+nombreArchivo;
						System.out.println("rutaRelativaReporte almacenada: " + rutaPlantilla);
						List<AgriProcesoEmisiones> cotizacionList = new ArrayList<AgriProcesoEmisiones>();
						
						JSONArray listCotError = new JSONArray();
						
						
						if(nombreArchivo.endsWith(".xlsx")){
							cotizacionList = ReadExelFile.readXLSXFileEmisiones(rutaPlantilla);		
						}else if(nombreArchivo.endsWith(".xls")){
							cotizacionList = ReadExelFile.readXLSXFileEmisiones(rutaPlantilla);					 
						}							
						
						if(proceso.equals("General")){
							listCotError = ProcesoGeneral(cotizacionList);
						}else if(proceso.equals("Confirmacion")){
							listCotError =ProcesoConfirmacionGeneral(cotizacionList);
						}else if(proceso.equals("Aumento")){
							listCotError =ProcesoAumentoGeneral(cotizacionList);
						}else if(proceso.equals("Rebaja")){
							listCotError =ProcesoRebajaGeneral(cotizacionList);
						}else if(proceso.equals("Cancelacion")){
							listCotError =ProcesoCancelacionGeneral(cotizacionList);
						}
						
						long total =listCotError.size();
						DataSourceResult pg = new DataSourceResult();
						pg.setTotal((int)total);
						pg.setData(listCotError);
						
						Gson gson = new Gson();
						// convert the DataSourceReslt to JSON and write it to the response
						response.setContentType("application/json; charset=ISO-8859-1");
					    response.getWriter().print(gson.toJson(pg));
					    return;
						
					}
					
				}catch (Exception e) {
					result.put("success", Boolean.FALSE);
					result.put("mensaje", e.getLocalizedMessage());
					response.setContentType("application/json; charset=ISO-8859-1");
					result.write(response.getWriter());
					e.printStackTrace();
				}
			}
			
			
			//Proceso de confirmacion de archivos
			private JSONArray ProcesoConfirmacionGeneral(List<AgriProcesoEmisiones> cotizacionList){
					
					JSONArray resultado = new JSONArray();
					JSONObject individual= new JSONObject();
					for(AgriProcesoEmisiones rs :cotizacionList){
						try{
							//1) verificamos si la cotizacion existe en la base del cotizador.
							CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
							AgriCotizacionAprobacion cotizacionesEncontradas=cDAO.buscarPorTramiteCoincidencia(rs.getNumeroTramite().trim());
							
							/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
							if(cotizacionesEncontradas.getConfirmacion().equals("CONFIRMADO"))
								throw new Exception("La cotizacion ya fue confirmada como Emitida Anteriormente.");
							
							//si esta revocado no se puede emitir de nuevo
							if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
								throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
							}
							
							BigDecimal T = new BigDecimal(""+cotizacionesEncontradas.getSumaAseguradaTotal());
							BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);					
							Double sumaAseguradaCot=Double.parseDouble(""+roundOffT);
							T = new BigDecimal(""+rs.getSumaAsegurada());
							roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							Double sumaAseguradaArch=Double.parseDouble(""+roundOffT);
													
							//Verificamos Primas						
							T = new BigDecimal(""+cotizacionesEncontradas.getPrimaNetaTotal());
							roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							Double primaCot=Double.parseDouble(""+roundOffT);
							T = new BigDecimal(""+rs.getPrimaNeta());
							roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							Double primaArch=Double.parseDouble(""+roundOffT);
							
							int comparacionPrima=primaArch.compareTo(primaCot);
							if(comparacionPrima!=0)
								throw new Exception("Las primas son diferentes no se puede confirmar, PrimaQBE: "+primaCot+ " || PrimaArchivo: "+primaArch);
							
							int comparacionSumas=sumaAseguradaArch.compareTo(sumaAseguradaCot);
							if(comparacionSumas!=0)
								throw new Exception("Las Sumas Aseguradas son diferentes no se puede confirmar, SumaQBE: "+sumaAseguradaCot+ " || SumaArchivo: "+sumaAseguradaArch);
							
							
							//3) Independientemente del proceso lo primero que se hace es la confirmacion de la emision.
							boolean confirmacionCorrecta=confirmacionEmision(cotizacionesEncontradas,rs.getFechaEmision());
							if(!confirmacionCorrecta)
								throw new Exception("No logreo realizar la el aumento a la emisión");
							
							
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", "Proceso de Confirmación Emisión.");
						}catch(Exception e){
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", e.getMessage());
						}
						finally{
							resultado.add(individual);
						}
					}		
					return resultado;
			}

			//Proceso de Aumento de cotizaciones
			private JSONArray ProcesoAumentoGeneral(List<AgriProcesoEmisiones> cotizacionList){
					
					JSONArray resultado = new JSONArray();
					JSONObject individual= new JSONObject();
					for(AgriProcesoEmisiones rs :cotizacionList){
						try{
							//1) verificamos si la cotizacion existe en la base del cotizador.
							CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
							AgriCotizacionAprobacion cotizacionesEncontradas=cDAO.buscarPorTramiteCoincidencia(rs.getNumeroTramite().trim());
							
							/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
							if(cotizacionesEncontradas.getConfirmacion().equals("NO CONFIRMADO"))
								throw new Exception("No se puede realizar el aumento, ya que la cotizacion no esta confirmada como Emitida.");
							
							//si esta revocado no se puede emitir de nuevo
							if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
								throw new Exception("No se puede realizar un aumento en una cotizacion ya cancelada");
							}
							
							//3) Independientemente del proceso lo primero que se hace es la confirmacion de la emision.
							boolean confirmacionCorrecta=confirmacionEmision(cotizacionesEncontradas,rs.getFechaEmision());
							if(!confirmacionCorrecta)
								throw new Exception("No logreo realizar la el aumento a la emisión");
							
							BigDecimal T = new BigDecimal(""+rs.getSumaAsegurada());
							BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);					
							Double sumaAseguradaCot=Double.parseDouble(""+roundOffT);
							
							T = new BigDecimal(""+rs.getPrimaNeta());
							roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							Double primaArch=Double.parseDouble(""+roundOffT);
							
							confirmacionCorrecta=AumentoEmision(cotizacionesEncontradas,sumaAseguradaCot,primaArch,rs.getFechaEmision());
									
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", "Proceso de Aumento Correcto.");
							
						}catch(Exception e){
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", e.getMessage());
						}
						finally{
							resultado.add(individual);
						}
					}		
					return resultado;
			}
			
			//Proceso de rebaja de cotizaciones
			private JSONArray ProcesoRebajaGeneral(List<AgriProcesoEmisiones> cotizacionList){
					
					JSONArray resultado = new JSONArray();
					JSONObject individual= new JSONObject();
					for(AgriProcesoEmisiones rs :cotizacionList){
						try{
							//1) verificamos si la cotizacion existe en la base del cotizador.
							CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
							AgriCotizacionAprobacion cotizacionesEncontradas=cDAO.buscarPorTramiteCoincidencia(rs.getNumeroTramite().trim());
							
							/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
							if(cotizacionesEncontradas.getConfirmacion().equals("NO CONFIRMADO"))
								throw new Exception("No se puede realizar la rebaja, ya que la cotizacion no esta confirmada como Emitida.");
							
							//si esta revocado no se puede emitir de nuevo
							if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
								throw new Exception("No se puede realizar la rebaja en una cotizacion ya cancelada");
							}
							
							
							BigDecimal T = new BigDecimal(""+rs.getSumaAsegurada());
							BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);					
							Double sumaAseguradaCot=Double.parseDouble(""+roundOffT);
							
							T = new BigDecimal(""+rs.getPrimaNeta());
							roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
							Double primaArch=Double.parseDouble(""+roundOffT);
							
							boolean confirmacionCorrecta=RebajaEmision(cotizacionesEncontradas,sumaAseguradaCot,primaArch,rs.getFechaEmision());
									
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", "Proceso de Rebaja Correcto.");
							
						}catch(Exception e){
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", e.getMessage());
						}
						finally{
							resultado.add(individual);
						}
					}		
					return resultado;
			}
			
			//Proceso de Aumento de cotizaciones
			private JSONArray ProcesoCancelacionGeneral(List<AgriProcesoEmisiones> cotizacionList){

				JSONArray resultado = new JSONArray();
				JSONObject individual= new JSONObject();
				for(AgriProcesoEmisiones rs :cotizacionList){
					try{
						//1) verificamos si la cotizacion existe en la base del cotizador.
						CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
						AgriCotizacionAprobacion cotizacionesEncontradas=cDAO.buscarPorTramiteCoincidencia(rs.getNumeroTramite().trim());
						
						/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
						if(cotizacionesEncontradas.getConfirmacion().equals("NO CONFIRMADO"))
							throw new Exception("No se puede realizar la rebaja, ya que la cotizacion no esta confirmada como Emitida.");
						
						//si esta revocado no se puede emitir de nuevo
						if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
							throw new Exception("No se puede realizar la rebaja en una cotizacion ya cancelada");
						}
						
						BigDecimal T = new BigDecimal(""+cotizacionesEncontradas.getSumaAseguradaTotal());
						BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);					
						Double sumaAseguradaCot=Double.parseDouble(""+roundOffT);
						T = new BigDecimal(""+Math.abs(rs.getSumaAsegurada()));
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double sumaAseguradaArch=Double.parseDouble(""+roundOffT);
												
						//Verificamos Primas						
						T = new BigDecimal(""+cotizacionesEncontradas.getPrimaNetaTotal());
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double primaCot=Double.parseDouble(""+roundOffT);
						T = new BigDecimal(""+Math.abs(rs.getPrimaNeta()));
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double primaArch=Double.parseDouble(""+roundOffT);
						
						int comparacionPrima=primaArch.compareTo(primaCot);
						if(comparacionPrima!=0)
							throw new Exception("Las primas son diferentes no se puede confirmar, PrimaQBE: "+primaCot+ " || PrimaArchivo: -"+primaArch);
						
						int comparacionSumas=sumaAseguradaArch.compareTo(sumaAseguradaCot);
						if(comparacionSumas!=0)
							throw new Exception("Las Sumas Aseguradas son diferentes no se puede confirmar, SumaQBE: "+sumaAseguradaCot+ " || SumaArchivo: -"+sumaAseguradaArch);
												
						boolean confirmacionCorrecta=cancelacionEmision(cotizacionesEncontradas,rs.getFechaEmision());
								
						individual.put("id", rs.getNumeroTramite().toString());
						individual.put("observacion", "Proceso de Cancelación Correcto.");
						
					}catch(Exception e){
						individual.put("id", rs.getNumeroTramite().toString());
						individual.put("observacion", e.getMessage());
					}
					finally{
						resultado.add(individual);
					}
				}		
				return resultado;
			}
			
			
			//Procesamos mediante el cual se hace una emision, aumento, rebaja o cancelacion
			private JSONArray ProcesoGeneral(List<AgriProcesoEmisiones> cotizacionList){
				
				JSONArray resultado = new JSONArray();
				JSONObject individual= new JSONObject();
				for(AgriProcesoEmisiones rs :cotizacionList){
					try{
						//1) verificamos si la cotizacion existe en la base del cotizador.
						CotizacionAprobacionDAO cDAO = new CotizacionAprobacionDAO();
						AgriCotizacionAprobacion cotizacionesEncontradas=cDAO.buscarPorTramiteCoincidencia(rs.getNumeroTramite().trim());
						
						/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
						if(cotizacionesEncontradas.getConfirmacion().equals("CONFIRMADO"))
							throw new Exception("La cotizacion ya fue confirmada como Emitida Anteriormente.");
						
						//si esta revocado no se puede emitir de nuevo
						if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
							throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
						}
						
						
						/*** 2) verificamos el proceso a realizar.
						 * 0. Error ningun proceso
						 * 1. Si la suma asegura es igual entonces el proceso es una confirmacion;
						 * 2. Si la suma es mayor a la suma asegurada de la cotizacion existe, un Aumento
						 * 3. Si la suma es menor a la suma asegurad de la cotizacion, una Rebaja
						 * 4. Si la suma es igual a la suma asegurada pero negativa, es una cancelacion
						 * ***/
						
						int proceso=0;// definimos el proceso en base a los items comentados
						BigDecimal T = new BigDecimal(""+cotizacionesEncontradas.getSumaAseguradaTotal());
						BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);					
						Double sumaAseguradaCot=Double.parseDouble(""+roundOffT);
						T = new BigDecimal(""+rs.getSumaAsegurada());
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double sumaAseguradaArch=Double.parseDouble(""+roundOffT);
												
						//Verificamos Primas						
						T = new BigDecimal(""+cotizacionesEncontradas.getPrimaNetaTotal());
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double primaCot=Double.parseDouble(""+roundOffT);
						T = new BigDecimal(""+rs.getPrimaNeta());
						roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double primaArch=Double.parseDouble(""+roundOffT);
						
						int comparacionPrima=primaArch.compareTo(primaCot);
						
						int comparacionSumas=sumaAseguradaArch.compareTo(sumaAseguradaCot);
						
						if(comparacionSumas==0 && comparacionPrima==0)//solo si las sumas y primas son iguales
							proceso=1;
						else
							if(comparacionSumas>0||comparacionPrima>0)
								proceso=2;
							else{
								Double sumaAseguradaArchPositiva=sumaAseguradaArch*(-1);
								int comparacionCancelacion=sumaAseguradaArchPositiva.compareTo(sumaAseguradaCot);
								if(comparacionCancelacion==0)
									proceso=4;
								else
									proceso=3;	
							}
							
						if(proceso==0)
							throw new Exception("No se encontro proceso a realizar en la cotización");
						
						//3) Independientemente del proceso lo primero que se hace es la confirmacion de la emision.
						boolean confirmacionCorrecta=confirmacionEmision(cotizacionesEncontradas,rs.getFechaEmision());
						
						if(!confirmacionCorrecta)
							throw new Exception("No logreo realizar la confirmación de la emisión");
						
						
						if(proceso==1){
							//Es solo confirmacion
							individual.put("id", rs.getNumeroTramite().toString());
							individual.put("observacion", "Proceso de Confirmación Emision Correcto.");							
						}else{
							if(proceso==2){
								//Si es un aumento
								//resto la suma total y la prima neta total
								double PrimaAumento=primaArch-primaCot;	
								double SumaAumento=sumaAseguradaArch-sumaAseguradaCot;
								confirmacionCorrecta=AumentoEmision(cotizacionesEncontradas,SumaAumento,PrimaAumento,rs.getFechaEmision());
								if(!confirmacionCorrecta)
									throw new Exception("No logreo realizar la el aumento a la emisión");
								individual.put("id", rs.getNumeroTramite().toString());
								individual.put("observacion", "Proceso de Confirmación Emisión y Aumento Correcto.");
							}else{
								if(proceso==3){
									//Rebajas
									double PrimaRebaja=primaArch-primaCot;	
									double SumaRebaja=sumaAseguradaArch-sumaAseguradaCot;
									confirmacionCorrecta=RebajaEmision(cotizacionesEncontradas,SumaRebaja,PrimaRebaja,rs.getFechaEmision());
									if(!confirmacionCorrecta)
										throw new Exception("No logreo realizar la el aumento a la emisión");
									individual.put("id", rs.getNumeroTramite().toString());
									individual.put("observacion", "Proceso de Confirmación Emisión y Aumento Correcto.");
								}else{
									//cancelaciones
									confirmacionCorrecta=cancelacionEmision(cotizacionesEncontradas,rs.getFechaEmision());
									if(!confirmacionCorrecta)
										throw new Exception("No logreo realizar la el aumento a la emisión");
									individual.put("id", rs.getNumeroTramite().toString());
									individual.put("observacion", "Proceso de Cancelacion Correcto y Aumento Correcto.");
								}									
							}							
						}
					}catch(Exception e){
						individual.put("id", rs.getNumeroTramite().toString());
						individual.put("observacion", e.getMessage());
					}
					finally{
						resultado.add(individual);
					}
				}		
				return resultado;				
			}
			
			private boolean confirmacionEmision(AgriCotizacionAprobacion cotizacionesEncontradas, String fechaEmision){
				try{
					//se llena la cotizacion con valores para registrar el endoso
					AgriCotizacionEndosoTransaction agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
					AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();					
					//llenamos el endoso.					
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaEmisionArch= new Date();
					if(fechaEmision==null|| fechaEmision.equals(""))
						fechaEmisionArch=formatoDelTexto.parse("02/01/2016");	
					else
						fechaEmisionArch=formatoDelTexto.parse(fechaEmision);
					
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion= agriObjetoCotizacionDAO.buscarPorIdPoliza(cotizacionesEncontradas.getIdCotizacion2()).get(0);
					agriCotizacionEndoso.setCotizacionId(cotizacionesEncontradas.getId());
					agriCotizacionEndoso.setTipo("CONFIRMACION");
					agriCotizacionEndoso.setValor(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setPrimaNeta(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setSumaAsegurada(cotizacionesEncontradas.getSumaAseguradaTotal());
					agriCotizacionEndoso.setSumaAnteriorMov(cotizacionesEncontradas.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrimaAnteriorMov(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setTipoSubEndoso(3);
					agriCotizacionEndoso.setNumHectareas(0);
					agriCotizacionEndoso.setAnexo("0");
					agriCotizacionEndoso.setSuperBancos(cotizacionesEncontradas.getSeguroBancos());
					agriCotizacionEndoso.setSeguroCanpesino(cotizacionesEncontradas.getSeguroCampesino());
					agriCotizacionEndoso.setDerechoEmision(cotizacionesEncontradas.getDerechoEmision());
					agriCotizacionEndoso.setIva(cotizacionesEncontradas.getIva());
					agriCotizacionEndoso.setPrimaTotal(cotizacionesEncontradas.getTotalFactura());
					agriCotizacionEndoso.setTipoSubEndoso(2);//subEndosoNormal
					agriCotizacionEndoso.setFecha_Proceso(fechaEmisionArch);
					//almacenamos la prima y la suma
					agriCotizacionEndoso.setPrimaCotizacion(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setSumaCotizacion(cotizacionesEncontradas.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrima_total_cotizacion(cotizacionesEncontradas.getTotalFactura());
					//datos para busqueda.
					agriCotizacionEndoso.setTramite(cotizacionesEncontradas.getNumeroTramite());
					agriCotizacionEndoso.setCanal("1");
					agriCotizacionEndoso.setPuntoVenta(cotizacionesEncontradas.getPuntoVentaId().toString());
					agriCotizacionEndoso.setCliente(cotizacionesEncontradas.getIdentificacionCliente());
					agriCotizacionEndoso.setHectareasAntMov(Double.parseDouble(""+cotizacionesEncontradas.getHectareasAsegurables()));
					agriCotizacionEndoso.setFechaCanal(fechaEmisionArch);					
					agriObjetoCotizacion.setConfirEmiCanal(true);
					agriObjetoCotizacion.setFechaConfirEmiCanal(fechaEmisionArch);
					agriObjetoCotizacion.setTiposMovimientos("CONF");					
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
					//ENDOSO
					agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
					return true;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			private boolean cancelacionEmision(AgriCotizacionAprobacion cotizacionesEncontradas,String fechaEmision){
				try{
					//si ya esta anulada solo respondo OK no realizo el proceso de nuevo, para mantener la integridad de los movientos
					if(cotizacionesEncontradas.getConfirmacion().equals("Anulado")||cotizacionesEncontradas.getConfirmacion().equals("RevocadoCanal")||cotizacionesEncontradas.getConfirmacion().equals("Cancelada")){
						throw new Exception("La cotizacion ya fue Cancelada Anteriormente");
					}
					
					/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
					AgriCotizacionEndosoDAO consultaEndoso= new AgriCotizacionEndosoDAO();
					int endosos=consultaEndoso.buscarNumAnexoPorCotizacionId(cotizacionesEncontradas.getId());
					int anexo=endosos+1;//creamoes el identificador unico del anexo
										
					//EN CASO DE UNA ANULACION LA COTIZACION CAMBIA DE ESTADO 
					Cotizacion detallesImpuestos = new Cotizacion();
					CotizacionDAO cotizacionDAO= new CotizacionDAO();
					detallesImpuestos=cotizacionDAO.buscarPorId(cotizacionesEncontradas.getId().toString());
					CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(detallesImpuestos).get(0);
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO  = new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					
					/**Guardamos el endoso**/
					//se llena la cotizacion con valores para registrar el endoso
					AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
					AgriCotizacionEndosoTransaction agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
					
					//llenamos el endoso.
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaEmisionArch= new Date();
					if(fechaEmision==null|| fechaEmision.equals(""))
						fechaEmisionArch=formatoDelTexto.parse("02/01/2016");	
					else
						fechaEmisionArch=formatoDelTexto.parse(fechaEmision);
					agriCotizacionEndoso.setFechaCanal(fechaEmisionArch);
					agriCotizacionEndoso.setCotizacionId(new BigInteger(detallesImpuestos.getId()));
					agriCotizacionEndoso.setTipo("CANCELACION");
					agriCotizacionEndoso.setValor(Math.abs(detallesImpuestos.getPrimaOrigen())*(-1));
					agriCotizacionEndoso.setPrimaNeta(Math.abs(detallesImpuestos.getPrimaOrigen())*(-1));
					agriCotizacionEndoso.setSumaAsegurada(Math.abs(detallesImpuestos.getSumaAseguradaTotal())*(-1));	
					agriCotizacionEndoso.setSumaAnteriorMov(detallesImpuestos.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrimaAnteriorMov(detallesImpuestos.getPrimaOrigen());
					agriCotizacionEndoso.setTipoSubEndoso(2);
					agriCotizacionEndoso.setNumHectareas(0);
					agriCotizacionEndoso.setAnexo(""+anexo);
					agriCotizacionEndoso.setFecha_Proceso(fechaEmisionArch);
					agriCotizacionEndoso.setHectareasAntMov(Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables()));
					AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
					
					detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(detallesImpuestos, Math.abs(detallesImpuestos.getPrimaOrigen()));
					//asignoImpuestos
					agriCotizacionEndoso.setSeguroCanpesino(Math.abs(detallesImpuestos.getImpSeguroCampesino())*(-1));
					agriCotizacionEndoso.setSuperBancos(Math.abs(detallesImpuestos.getImpSuperBancos())*(-1));
					agriCotizacionEndoso.setDerechoEmision(Math.abs(detallesImpuestos.getImpDerechoEmision())*(-1));
					agriCotizacionEndoso.setIva(Math.abs(detallesImpuestos.getImpIva())*(-1));
					agriCotizacionEndoso.setPrimaTotal(Math.abs(detallesImpuestos.getTotalFactura())*(-1));
					
					//datos para busqueda.
					agriCotizacionEndoso.setTramite(detallesImpuestos.getNumeroTramite());
					agriCotizacionEndoso.setCanal("1");
					agriCotizacionEndoso.setPuntoVenta(detallesImpuestos.getPuntoVenta().getId());
					agriCotizacionEndoso.setCliente(detallesImpuestos.getAsegurado().getIdentificacion());
					agriCotizacionEndoso.setFecha_Proceso(fechaEmisionArch);
					/**RECALCULO DE LOS VALORES DE LA COTIZACION
					 * 1) Se resta el valor recibido a la prima y suma asegurada
					 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
					 * 3) Recalculo los impuestos
					 * 4) guardo nuevo estado para auditoria
					 * **/
					//actualizo los valores de la cotizacion
					detallesImpuestos.setPrimaNetaTotal("-"+detallesImpuestos.getPrimaNetaTotal());
					detallesImpuestos.setPrimaOrigen(detallesImpuestos.getPrimaOrigen()*(-1));
					detallesImpuestos.setSumaAseguradaTotal(detallesImpuestos.getSumaAseguradaTotal()*(-1));
					detallesImpuestos.setImpDerechoEmision(detallesImpuestos.getImpDerechoEmision()*(-1));
					detallesImpuestos.setImpSeguroCampesino(detallesImpuestos.getImpSeguroCampesino()*(-1));
					detallesImpuestos.setImpSuperBancos(detallesImpuestos.getImpSuperBancos()*(-1));
					detallesImpuestos.setImpIva(detallesImpuestos.getImpIva()*(-1));
					detallesImpuestos.setTotalFactura(detallesImpuestos.getTotalFactura()*(-1));
					
					EstadoDAO estadoDAO = new EstadoDAO();
					Estado estado=estadoDAO.buscarPorNombre("Cancelada", "Cotizacion");
					detallesImpuestos.setEstado(estado);
					//almacenamos la prima y la suma
					agriCotizacionEndoso.setPrimaCotizacion(detallesImpuestos.getPrimaOrigen());//es negativa 
					agriCotizacionEndoso.setSumaCotizacion(detallesImpuestos.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrima_total_cotizacion(detallesImpuestos.getTotalFactura());
					
					//actualizo el estado de la cotizacion
					CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
					cotizacionTransaction.editar(detallesImpuestos);	
					
					//Actualizo la cotizacion detalle
					//Cotizacion Detalle
					cotizacionDetalle.setSumaAseguradaItem(cotizacionDetalle.getSumaAseguradaItem()*(-1));
					cotizacionDetalle.setPrimaNetaItem(cotizacionDetalle.getPrimaNetaItem()*(-1));
					CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
					cotizacionDetalleTransaction.editar(cotizacionDetalle);
					
					
					//ENDOSO
					agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
					
					//actulizo la AGRIOBJETOCOTIZACION
					
					//actualizo la agriObjeto cotizacion con las causa del problema
					agriObjetoCotizacion.setObservacionCotizacion("Cancelacion Emisión");
					String tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
					agriObjetoCotizacion.setMontoCredito(agriObjetoCotizacion.getMontoCredito()*(-1));
					agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-CAN");
					agriObjetoCotizacion.setFechaConfirEmiCanal(fechaEmisionArch);
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);					
					return true;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			private boolean AumentoEmision(AgriCotizacionAprobacion cotizacionesEncontradas,double sumaAsegurada,double primaNeta,String fechaEmision){
				try{

					//si esta revocado no se puede emitir de nuevo
					if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
						throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
					}
					
					/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
					AgriCotizacionEndosoDAO consultaEndoso= new AgriCotizacionEndosoDAO();
					int endosos=consultaEndoso.buscarNumAnexoPorCotizacionId(cotizacionesEncontradas.getId());
					int anexo=endosos+1;//creamoes el identificador unico del anexo
										
					/**Guardamos el endoso**/
					//se llena la cotizacion con valores para registrar el endoso
					AgriCotizacionEndosoTransaction agriCotizacionEndosoTransactionAgriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction ();
					AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
					
					//llenamos el endoso.
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaEmisionArch= new Date();
					if(fechaEmision==null|| fechaEmision.equals(""))
						fechaEmisionArch=formatoDelTexto.parse("02/01/2016");	
					else
						fechaEmisionArch=formatoDelTexto.parse(fechaEmision);					
					agriCotizacionEndoso.setFechaCanal(fechaEmisionArch);
					
					agriCotizacionEndoso.setCotizacionId(cotizacionesEncontradas.getId());
					agriCotizacionEndoso.setTipo("AUMENTO");
					agriCotizacionEndoso.setValor(primaNeta);
					agriCotizacionEndoso.setPrimaNeta(primaNeta);
					agriCotizacionEndoso.setSumaAsegurada(sumaAsegurada);
					agriCotizacionEndoso.setSumaAnteriorMov(cotizacionesEncontradas.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrimaAnteriorMov(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setAnexo(""+anexo);
					agriCotizacionEndoso.setTipoSubEndoso(0);
					agriCotizacionEndoso.setNumHectareas(0);
					
					//RECALCULAMOS LOS IMPUESTOS
					AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
					
					Cotizacion detallesImpuestos = new Cotizacion();
					CotizacionDAO cotizacionDAO= new CotizacionDAO();
					detallesImpuestos=cotizacionDAO.buscarPorId(cotizacionesEncontradas.getId().toString());
					CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(detallesImpuestos).get(0);
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO  = new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					
					detallesImpuestos=cotizacionDAO.buscarPorId(cotizacionesEncontradas.getId().toString());
					detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(detallesImpuestos, primaNeta);
					
					
					//asignoImpuestos
					agriCotizacionEndoso.setSeguroCanpesino(detallesImpuestos.getImpSeguroCampesino());
					agriCotizacionEndoso.setSuperBancos(detallesImpuestos.getImpSuperBancos());
					agriCotizacionEndoso.setDerechoEmision(detallesImpuestos.getImpDerechoEmision());
					agriCotizacionEndoso.setIva(detallesImpuestos.getImpIva());
					agriCotizacionEndoso.setPrimaTotal(detallesImpuestos.getTotalFactura());
					//datos para busqueda.
					agriCotizacionEndoso.setTramite(cotizacionesEncontradas.getNumeroTramite());
					agriCotizacionEndoso.setCanal("1");
					agriCotizacionEndoso.setPuntoVenta(cotizacionesEncontradas.getPuntoVentaId().toString());
					agriCotizacionEndoso.setCliente(cotizacionesEncontradas.getIdentificacionCliente());
					
					/**RECALCULO DE LOS VALORES DE LA COTIZACION
					 * 1) Se suma el valor recibido a la prima y suma asegurada
					 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
					 * 3) Recalculo los impuestos
					 * 4) guardo nuevo estado para auditoria
					 * **/
					
					//tomamos la prima y suma asegura, sumamos y luego dividimos para el numero de hectareas para calcularo el costo de produccion. :)
					Double primaActual = detallesImpuestos.getPrimaOrigen();
					Double sumaAseguradaActual=detallesImpuestos.getSumaAseguradaTotal();
					
					double PrimaTotal= primaActual+primaNeta;
					BigDecimal a = new BigDecimal(""+PrimaTotal);//prima total
					BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
					PrimaTotal= Double.parseDouble(""+roundOff);
					
					
					double SumaAseguradaTotal=sumaAseguradaActual+sumaAsegurada;
					a = new BigDecimal(""+SumaAseguradaTotal);//prima total
					roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
					SumaAseguradaTotal= Double.parseDouble(""+roundOff);
					
					//hallamos el costo de produccion;
					double hectareasAseguradas=Double.parseDouble(""+cotizacionesEncontradas.getHectareasAsegurables());//hectareas aseguradas.
					double costoProduccionTotal=SumaAseguradaTotal/hectareasAseguradas;
					
					/* PROCESO DE CALCULO DE COMPONENTES TOTALES DE LA COTIZACION */
					
					detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(detallesImpuestos, PrimaTotal);
					detallesImpuestos.setPrimaNetaTotal(""+PrimaTotal);
					detallesImpuestos.setPrimaOrigen(PrimaTotal);
					detallesImpuestos.setSumaAseguradaTotal(SumaAseguradaTotal);
					//almacenamos la prima y la suma
					agriCotizacionEndoso.setPrimaCotizacion(detallesImpuestos.getPrimaOrigen());
					agriCotizacionEndoso.setSumaCotizacion(detallesImpuestos.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrima_total_cotizacion(detallesImpuestos.getTotalFactura());
					agriCotizacionEndoso.setFecha_Proceso(fechaEmisionArch);
					
					
					CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
					cotizacionTransaction.editar(detallesImpuestos);//actulizamo la cotizacion
					
					//Cotizacion Detalle
					cotizacionDetalle.setSumaAseguradaItem(SumaAseguradaTotal);
					cotizacionDetalle.setPrimaNetaItem(PrimaTotal);
					CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
					cotizacionDetalleTransaction.editar(cotizacionDetalle);
					
					//agriObjetoCotizacion
					agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));//si es normal se recalcula el costo de produccion
					agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+SumaAseguradaTotal));
					agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));
					agriObjetoCotizacion.setTieneMov(true);
					String tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
					agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-AUM");
					agriObjetoCotizacion.setFechaConfirEmiCanal(fechaEmisionArch);
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction2= new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction2.editar(agriObjetoCotizacion);
					
					//ENDOSO
					agriCotizacionEndoso.setHectareasAntMov(agriObjetoCotizacion.getHectareasAsegurables());
					AgriCotizacionEndosoTransaction agriCotizacionEndosoTransaction= new AgriCotizacionEndosoTransaction();
					agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
					return true;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
			private boolean RebajaEmision(AgriCotizacionAprobacion cotizacionesEncontradas,double sumaAsegurada,double primaNeta,String fechaEmision){
				try{

					//si esta revocado no se puede emitir de nuevo
					if(cotizacionesEncontradas.getEstadoCotizacion().equals("Cancelada")||cotizacionesEncontradas.getEstadoCotizacion().equals("RevocadoCanal")||cotizacionesEncontradas.getEstadoCotizacion().equals("Revocado")){
						throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
					}
					
					/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
					AgriCotizacionEndosoDAO consultaEndoso= new AgriCotizacionEndosoDAO();
					int endosos=consultaEndoso.buscarNumAnexoPorCotizacionId(cotizacionesEncontradas.getId());
					int anexo=endosos+1;//creamoes el identificador unico del anexo	
					
											
					/**Guardamos el endoso**/
					//se llena la cotizacion con valores para registrar el endoso
					AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
					AgriCotizacionEndosoTransaction agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
					
					//llenamos el endoso.
					//llenamos el endoso.
					SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaEmisionArch= new Date();
					if(fechaEmision==null|| fechaEmision.equals(""))
						fechaEmisionArch=formatoDelTexto.parse("02/01/2016");	
					else
						fechaEmisionArch=formatoDelTexto.parse(fechaEmision);
					
					agriCotizacionEndoso.setFechaCanal(fechaEmisionArch);
					agriCotizacionEndoso.setCotizacionId(cotizacionesEncontradas.getId());
					agriCotizacionEndoso.setTipo("REBAJA");
					agriCotizacionEndoso.setValor(Math.abs(primaNeta)*(-1));
					agriCotizacionEndoso.setPrimaNeta(Math.abs(primaNeta)*(-1));
					agriCotizacionEndoso.setSumaAsegurada(Math.abs(sumaAsegurada)*(-1));	
					agriCotizacionEndoso.setSumaAnteriorMov(cotizacionesEncontradas.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrimaAnteriorMov(cotizacionesEncontradas.getPrimaNetaTotal());
					agriCotizacionEndoso.setAnexo(""+anexo);
					agriCotizacionEndoso.setTipoSubEndoso(0);
					agriCotizacionEndoso.setNumHectareas(0);

					//RECALCULAMOS LOS IMPUESTOS
					AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
					
					Cotizacion detallesImpuestos = new Cotizacion();
					CotizacionDAO cotizacionDAO= new CotizacionDAO();
					detallesImpuestos=cotizacionDAO.buscarPorId(cotizacionesEncontradas.getId().toString());
					CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
					CotizacionDetalle cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(detallesImpuestos).get(0);
					AgriObjetoCotizacionDAO agriObjetoCotizacionDAO  = new AgriObjetoCotizacionDAO();
					AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					
					detallesImpuestos=cotizacionDAO.buscarPorId(cotizacionesEncontradas.getId().toString());
					
					detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(detallesImpuestos, Math.abs(primaNeta));
					//asignoImpuestos
					agriCotizacionEndoso.setSeguroCanpesino(Math.abs(detallesImpuestos.getImpSeguroCampesino())*(-1));
					agriCotizacionEndoso.setSuperBancos(Math.abs(detallesImpuestos.getImpSuperBancos())*(-1));
					agriCotizacionEndoso.setDerechoEmision(Math.abs(detallesImpuestos.getImpDerechoEmision())*(-1));
					agriCotizacionEndoso.setIva(Math.abs(detallesImpuestos.getImpIva())*(-1));
					agriCotizacionEndoso.setPrimaTotal(Math.abs(detallesImpuestos.getTotalFactura())*(-1));
					//datos para busqueda.
					agriCotizacionEndoso.setTramite(cotizacionesEncontradas.getNumeroTramite());
					agriCotizacionEndoso.setCanal("1");
					agriCotizacionEndoso.setPuntoVenta(cotizacionesEncontradas.getPuntoVentaId().toString());
					agriCotizacionEndoso.setCliente(cotizacionesEncontradas.getIdentificacionCliente());
					
					/**RECALCULO DE LOS VALORES DE LA COTIZACION
					 * 1) Se resta el valor recibido a la prima y suma asegurada
					 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
					 * 3) Recalculo los impuestos
					 * 4) guardo nuevo estado para auditoria
					 * **/
					
					//verificamos que el valor no sea menos al que se tiene de prima
					Double primaActual = detallesImpuestos.getPrimaOrigen();
					Double sumaAseguradaActual=detallesImpuestos.getSumaAseguradaTotal();
					
					
					//tomamos la prima y suma asegura, sumamos y luego dividimos para el numero de hectareas para calcularo el costo de produccion. :)
					double PrimaTotal= primaActual-Math.abs(primaNeta);
					BigDecimal a = new BigDecimal(""+PrimaTotal);//prima total
					BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
					PrimaTotal= Double.parseDouble(""+roundOff);
					
					//si la prima ingresada es mayor a la prima calculada
					if(PrimaTotal<0){
						throw new  Exception ("Rebaja invalida la prima no puede ser mayor a la prima total de la cotización");
					}
					
					double SumaAseguradaTotal=sumaAseguradaActual-Math.abs(sumaAsegurada);
					a = new BigDecimal(""+SumaAseguradaTotal);//prima total
					roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
					SumaAseguradaTotal= Double.parseDouble(""+roundOff);
					
					// si la suma ingresada es mayor a la suma cotizada
					if(SumaAseguradaTotal<0){
						throw new  Exception ("Rebaja invalida la suma Asegurada no puede ser mayor a la suma total de la cotizacion");
					}
											
					//hallamos el costo de produccion;
					double hectareasAseguradas=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());//hectareas aseguradas.
					double costoProduccionTotal=SumaAseguradaTotal/hectareasAseguradas;
					
					/* PROCESO DE CALCULO DE COMPONENTES TOTALES DE LA COTIZACION */
					
					detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(detallesImpuestos, PrimaTotal);
					detallesImpuestos.setPrimaNetaTotal(""+PrimaTotal);
					detallesImpuestos.setPrimaOrigen(PrimaTotal);
					detallesImpuestos.setSumaAseguradaTotal(SumaAseguradaTotal);
					//almacenamos la prima y la suma
					agriCotizacionEndoso.setPrimaCotizacion(detallesImpuestos.getPrimaOrigen());
					agriCotizacionEndoso.setSumaCotizacion(detallesImpuestos.getSumaAseguradaTotal());
					agriCotizacionEndoso.setPrima_total_cotizacion(detallesImpuestos.getTotalFactura());
					agriCotizacionEndoso.setFecha_Proceso(fechaEmisionArch);
					
					CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
					cotizacionTransaction.editar(detallesImpuestos);//actulizamo la cotizacion
					
					//Cotizacion Detalle
					cotizacionDetalle.setSumaAseguradaItem(SumaAseguradaTotal);
					cotizacionDetalle.setPrimaNetaItem(PrimaTotal);
					CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
					cotizacionDetalleTransaction.editar(cotizacionDetalle);

					agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));//si es normal se recalcula el costo de produccion
					
					agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+SumaAseguradaTotal));
					agriObjetoCotizacion.setTieneMov(true);
					String tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
					agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-REB");
					agriObjetoCotizacion.setFechaConfirEmiCanal(fechaEmisionArch);
					AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction2= new AgriObjetoCotizacionTransaction();
					agriObjetoCotizacionTransaction2.editar(agriObjetoCotizacion);
					
					//ENDOSO
					agriCotizacionEndoso.setHectareasAntMov(agriObjetoCotizacion.getHectareasAsegurables());
					agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
					return true;
				}
				catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			
		}
