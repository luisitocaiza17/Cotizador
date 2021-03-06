package com.qbe.cotizador.servicios.QBE.archivoPlano;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.BufferedReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionAprobacionController;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionReporte;
import com.qbe.cotizador.servlets.producto.agricola.AgriProcesarCotizacionOffline;
import com.qbe.cotizador.servlets.producto.agricola.AgriRespuestaOffline;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.servlets.producto.agricola.CotizacionAgricola;
import com.qbe.cotizador.servlets.producto.agricola.TransporteCotizaciones;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.qbe.cotizador.util.AES_Helper;
import com.qbe.cotizador.util.ResultAdjuntos;
import com.qbe.cotizador.util.Utilitarios;

public class ImportarCotizaciones {
	
	public ObjetosRespuesta[] importarCotizaciones(String textoJson, String correo,String CCCorreos){
			
		
			// decripto el mensaje
			if(textoJson!=null)
			{
				String decrypted="";
				try
				{
					decrypted = AES_Helper.decrypt(textoJson);
				}
				catch(Exception ex){
					ex.printStackTrace();
				}

				Gson g = new Gson();
				TransporteCotizaciones cotizaciones = g.fromJson(decrypted, TransporteCotizaciones.class);
				AgriObjetoCotizacionDAO objetoCotizacionDAO=new AgriObjetoCotizacionDAO();
				List<AgriObjetoCotizacion> listaCotizaciones;
				JSONArray cotizacionesJSONArray = new JSONArray();
				JSONObject cotizaJSONObject = new JSONObject();
				
				ObjetosRespuesta[] respuestaFinal= new ObjetosRespuesta[cotizaciones.getCotizacionAgricola().size()];
				int contador=0;
				for(CotizacionAgricola cotizaActual:cotizaciones.getCotizacionAgricola()){
					listaCotizaciones=objetoCotizacionDAO.buscarPorObjetoOfflineId(cotizaActual.getObjetoCotizacionId());
					if(listaCotizaciones.size()==0){
						AgriRespuestaOffline respuestaProceso=AgriProcesarCotizacionOffline.procesarCotizacion(cotizaActual,"COTIZADOR_OFFLINE", correo, CCCorreos);
						if(!respuestaProceso.getError().contains("ERROR:")){
							ObjetosRespuesta respuesta = new ObjetosRespuesta();
							respuesta.setObjetoCotizacionID(cotizaActual.getObjetoCotizacionId());
							respuesta.setCotizacionID(respuestaProceso.getCotizacion());
							respuesta.setFacturaID("");
							respuesta.setComentario("Cotizacion procesada con exito.");
							respuesta.setEstado(cotizaActual.getEstado());
							//hallamos el beneficiario en base a la cotizacion:
							CotizacionDAO cotizacionDAO = new CotizacionDAO();
							Cotizacion  cotizacion =cotizacionDAO.buscarPorId(respuestaProceso.getCotizacion());
							
							if(cotizacion.getEstado().getNombre().equals("Pendiente de Emitir")){
								if(cotizacion.getPuntoVenta().getNombre().equals("CREDIFE")){
									respuesta.setBeneficiario("BANCO PICHICHA");
								}else{
									if(cotizacion.getPuntoVenta().getNombre().equals("COOPROGRESO")){
										respuesta.setBeneficiario("COOPROGRESO");
									}else{
										respuesta.setBeneficiario("AGRIPAC");
									}
								}
							}
							respuestaFinal[contador]=respuesta;	
						}else{
							/**MENSAJES EN CASO DE ERROR**/
							java.util.Date date2 = new java.util.Date();
							java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
							SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
							//nueva auditoria
							AgriSucreAuditoria auditoria2 = new AgriSucreAuditoria();
							AgriSucreAuditoriaTransaction procesoAuditoria2 = new AgriSucreAuditoriaTransaction();
							auditoria2.setTramite(cotizaActual.getNumeroIdentificacion());
							auditoria2.setObjeto(respuestaProceso.getCotizacion()+" || "+respuestaProceso.getError()+"|| "+respuestaProceso.getClienteIdentificar()
									+" || "+respuestaProceso.getCorreo());
							auditoria2.setFecha(sq2);
							auditoria2.setCanal("OFFLINE");
							auditoria2=procesoAuditoria2.crear(auditoria2);
							
							List<String> usuario = new ArrayList<>();
							usuario.add("luis.caiza@smartwork.com.ec");
							usuario.add(correo);
							usuario.add("David.Garzon@qbe.com.ec");
							usuario.add("Sandy.Jaramillo@qbe.com.ec");
							
							String asunto="Error en Proceso de Cotizacion Offline";
							AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
							String Html=notificacionErrores.GeneradorHtml(cotizaActual.getNumeroIdentificacion(), 
									" La cotizacion realizada por el agente "+respuestaProceso.getAgente()+" hacia el cliente "+
									respuestaProceso.getClienteIdentificar()+" "+respuestaProceso.getClienteNombre()+" No pudo ser cotizada por la siguiente razon <b>"	
								    +respuestaProceso.getError()+"</b> remitente: "+respuestaProceso.getCorreo(), "");
							try{
								for(String receptor:usuario){
									//Utilitarios.envioMail(receptor, asunto, cuerpo);
									Utilitarios.envioMail(receptor, asunto, Html);
								}
							}
							catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					else{
						/**MENSAJE EN CASO DE REPETICION**/
						ObjetosRespuesta respuesta = new ObjetosRespuesta();
						respuesta.setObjetoCotizacionID(cotizaActual.getObjetoCotizacionId());
						CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
						String objetoID=listaCotizaciones.get(0).getObjetoCotizacionId().toString();
						CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionesDetallePorObjetoId(objetoID).get(0);
						respuesta.setCotizacionID(cotizacionDetalle.getCotizacion().getId());
						respuesta.setFacturaID("");
						respuesta.setComentario("La cotizacion ya fue procesada anteriormente.");
						//hallamos el beneficiario en base a la cotizacion:
						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion  cotizacion =cotizacionDAO.buscarPorId(cotizacionDetalle.getCotizacion().getId());
						if(cotizacion.getEstado().getNombre().equals("Pendiente de Emitir")){
							if(cotizacion.getPuntoVenta().getNombre().equals("CREDIFE")){
								respuesta.setBeneficiario("BANCO PICHICHA");
							}else{
								if(cotizacion.getPuntoVenta().getNombre().equals("COOPROGRESO")){
									respuesta.setBeneficiario("COOPROGRESO");
								}else{
									respuesta.setBeneficiario("AGRIPAC");
								}
							}
						}
						respuestaFinal[contador]=respuesta;
					}
					contador ++;
				}
				
				if(respuestaFinal.length!=0)
						enviarMailCambioEstado(respuestaFinal,correo,CCCorreos);
				
				return respuestaFinal;
			}else
				return null;
		}
	
	public void enviarMailCambioEstado(ObjetosRespuesta[] respuestaFinal,String correo,String CCCorreos) {
		
		try{
		String[] Cotizaciones = new String[respuestaFinal.length];
		String cadenaCotizaciones = "";
		String asunto = "QBE Seguros Colonial: Detalle de Aprobación Cotización";
		String detalle = "";
		String estado = "";
		String Usuario_ = "";
		
		ArrayList<ResultAdjuntos> adjuntos = new ArrayList<>();		
		for(int a=0; a<respuestaFinal.length;a++){
			//Proceso de envio de mail con pdf adjunto
			ResultAdjuntos adjunto = new ResultAdjuntos();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			Cotizacion cotizacion = cotizacionDAO.buscarPorId(respuestaFinal[a].getCotizacionID());
			CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			
			//tomamos y  guardamos los correos en el campo correos de agriObjetoCotizacion
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
			String conjuntoCorreos=correo;
			if(CCCorreos==null || CCCorreos.equals("")||CCCorreos.equals(","))
				System.out.println("Sin Adjuntos");
			else
				conjuntoCorreos=conjuntoCorreos+","+CCCorreos;
			agriObjetoCotizacion.setCorreos(conjuntoCorreos);
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
			agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);			
			//fin de proceso de guardado
			
			CotizacionAprobacionDAO aprobacion= new CotizacionAprobacionDAO();
			AgriCotizacionAprobacion  detalleCotizacion = aprobacion.buscarPorId(cotizacion.getId());
			
			String estadoRespuesta="";
			estadoRespuesta=cotizacion.getEstado().getNombre();
			String estaActual=""+cotizacion.getEstado().getId();
			if(estaActual.equals("13"))
				estadoRespuesta="Aprobada";
			if(estaActual.equals("21"))
				estadoRespuesta="Pendiente de Aprobación";
			String observacionRespuesta="Ninguna";
			try{
				if(agriObjetoCotizacion.getObservacionCotizacion()==null||agriObjetoCotizacion.getObservacionCotizacion().equals(""))
					observacionRespuesta="Ninguna";
				else{
					observacionRespuesta=agriObjetoCotizacion.getObservacionCotizacion();
				}
			}catch(Exception e){
				
			}
			
			detalle=detalle+ "Cotizaci&oacute;n : <b>" +detalleCotizacion.getIdCotizacion2()+ "</b> a sido procesada con &eacute;xito y se encuentra en estado: <b>"+estadoRespuesta+ " </b>|| Registro QBE: "+detalleCotizacion.getId()+" || <b>Observaci&oacute;n:</b> "+observacionRespuesta+"<br>";
			byte[] data = null;
			cadenaCotizaciones = cadenaCotizaciones+" [" +detalleCotizacion.getIdCotizacion2()+ "] ";
			GeneradorReporte generadorReporte= new GeneradorReporte();
			
			if(cotizacion.getEstado().getNombre().equals("Pendiente de Emitir")||
					cotizacion.getEstado().getNombre().equals("Emitido")){
				data = generadorReporte.generarReportes(cotizacion, detalleCotizacion);
				adjunto.setDataAdjunto(data);
				adjunto.setNombreAdjunto("CotizacionAgricola_"
						+ detalleCotizacion.getIdCotizacion2());
				adjuntos.add(adjunto);
			}
				
		}
		//envioMailPrePoliza(correo, adjuntos, asunto,cadenaCotizaciones,detalle);
		
		String html = "";
		//tomamos la ruta relativa de la clase para referenciar la plantilla
		String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
		String rutaRelativaReporte="../../../../../../../../static/plantillas/mailRecepcionCotizacion.html";
		//String rutaImagen="../../../../../../../../static/images/colonial.png";
		rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
		
		try {
			//html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			File CurrentStructureFile = new File(rutaPlantilla);
			System.out.println("ruta: "+rutaPlantilla);
			Path CurrentStructurePath = FileSystems.getDefault().getPath(CurrentStructureFile.getPath());
			html = new String(Files.readAllBytes(CurrentStructurePath));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// parametros html
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		parametersHeader.put("USUARIO", "USUARIO QBE");
		parametersHeader.put("COTIZACIONES", cadenaCotizaciones);
		parametersHeader.put("DETALLE", detalle);
		String cuerpoMail = "";
		cuerpoMail = generarContenido(html, parametersHeader);
		String []Adjuntos=CCCorreos.split(",");
		List<String> listadoCorreos= new ArrayList<String>(Arrays.asList(Adjuntos));
		Utilitarios.envioMailPDFVariosAdjuntos2(correo,listadoCorreos, asunto, cuerpoMail,adjuntos);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/*public void envioMailPrePoliza(String correos, List<ResultAdjuntos> adjunto,String asunto,String cadenaCotizaciones,String detalle) {
		// String[] correosArr = correos.split(";");
		// String rutaPlantilla = request.getServletContext().getRealPath("")+
		// plantillaRuta;
		String html = "";
		//tomamos la ruta relativa de la clase para referenciar la plantilla
		String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
		String rutaRelativaReporte="../../../../../../../../static/plantillas/mailAprobacionCotizacion.html";
		//String rutaImagen="../../../../../../../../static/images/colonial.png";
		rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
		
		try {
			//html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			File CurrentStructureFile = new File(rutaPlantilla);
			Path CurrentStructurePath = FileSystems.getDefault().getPath(CurrentStructureFile.getPath());
			html = new String(Files.readAllBytes(CurrentStructurePath), StandardCharsets.UTF_8);
		} catch (IOException ex) {
		}
		
		// parametros html
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		parametersHeader.put("USUARIO", "USUARIO QBE");
		parametersHeader.put("COTIZACIONES", cadenaCotizaciones);
		parametersHeader.put("DETALLE", detalle);
		String cuerpoMail = "";
		cuerpoMail = generarContenido(html, parametersHeader);
		Utilitarios.envioMailPDFVariosAdjuntos(correos, asunto, cuerpoMail,adjunto);
		// for (int i = 0; i < correosArr.length; i++) {
		//
		// cuerpoMail = GenerarContenido(html, parametersHeader);
		// Utilitarios.envioMailPDFVariosAdjunto(correosArr,asunto, cuerpoMail,
		// adjunto.getDataAdjunto());
		//
		// }
	}*/
	
	
	
	private String generarContenido(String html,
			java.util.Hashtable<String, String> paramValues) {
		List<String> detectedParams = new ArrayList<String>();
		// Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\._]*\\]");
		Matcher mat = params.matcher(html);
		while (mat.find()) {
			detectedParams.add(mat.group());
		}

		for (String detectedParam : detectedParams) {
			String valor = paramValues.get(detectedParam.replace("[", "")
					.replace("]", ""));
			html = html.replace(detectedParam, valor);
		}
		return html;
	}
	
	
}
