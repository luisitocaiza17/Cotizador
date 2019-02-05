package com.qbe.cotizador.servicios.QBE.archivoPlano;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriParametroXCanal;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionReporte;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;

public class GeneradorReporte {
	public byte[] generarReportes(Cotizacion cotizacion,
			AgriCotizacionAprobacion detalleCotizacion) {
		
		String html = generarHtmlCondicionesParticularesBlob(
				detalleCotizacion);
		byte[] data = GenerarPDFCotizacion(html);
		return data;
	}
	
	public byte[] GenerarPDFCotizacion(String html){
		java.io.ByteArrayOutputStream out = null;

		//FileOutputStream out = null;
		try {
			CYaHPConverter converter = new CYaHPConverter(false);

			List headerFooterList = new ArrayList();

			// cabecera y pie de página
			//headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("", IHtmlToPdfTransformer.CHeaderFooter.HEADER));

			headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("Página: <pagenumber>/<pagecount><hr />", IHtmlToPdfTransformer.CHeaderFooter.FOOTER));


			Map properties = new HashMap();

			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);

			// Soporte para fuentes
			properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, "c:\\Windows\\Fonts");

			//File fout = new File("D:\\Archivos\\Escritorio\\YaHP-Converter-master\\YaHP-Converter-master\\YaHPConverter\\out\\artifacts\\YaHPConverter\\cosa4.pdf");
			//out = new FileOutputStream(fout);
			out = new ByteArrayOutputStream();

			// si no se pone la etiqueta head, no valen los saltos de línea
			converter.convertToPdf(html,
					IHtmlToPdfTransformer.A4P,
					headerFooterList,
					null,
					out,
					properties);

			out.flush();
			out.close();

			// PDF renderizado en Byte Array
			byte[] result = out.toByteArray();
			return result;

			//System.out.println(result.length);



			//FileOutputStream fos = new FileOutputStream("D:\\Proyectos\\QBE_COTIZADOR\\PYMES\\Reporte\\cotizacion"+CotizacionId+".pdf");
			//FileOutputStream fos = new FileOutputStream("Cotizacion_"+CotizacionId+".pdf");
			//fos.write(result);
			//fos.close();


		}catch (Exception ex)
		{
			String data = ex.getMessage();
			try {

				out.flush();
				out.close();
			}catch (Exception ignore){}
		}
		return null;
	}
	
	
	public String generarHtmlCondicionesParticulares(AgriCotizacionAprobacion detalleCotizacion) {
		String html = "";
		String rutaImage ="";
		String PlantillaName="";
		CotizacionDAO cotiDAO = new CotizacionDAO();
		Cotizacion cotizacion = cotiDAO.buscarPorId(detalleCotizacion.getId().toString());
		AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
		AgriTipoCultivo tipoCultivo = tipoCultivoDAO.BuscarPorId(detalleCotizacion.getTipoCultivoId());
		//TODO:Buscar plantilla por canal y tipo de cultivo
		AgriParametroXCanalDAO parametroCanalDAO = new AgriParametroXCanalDAO();
		AgriParametroXCanal parametroCanal = parametroCanalDAO.obtenerPorCanalTipoCultivo(detalleCotizacion.getCanalId(), detalleCotizacion.getTipoCultivoId());
		PlantillaName=parametroCanal.getPlantillaNombre();
		
		String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
		String rutaRelativaReporte="../../../../../../../../static/plantillas/";
		//String rutaImagen="../../../../../../../../static/images/colonial.png";
		rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
		
		if (PlantillaName==null)
			PlantillaName="PlantillaTipoCultivoMaiz";
		
		rutaPlantilla=rutaPlantilla+PlantillaName+".html";
		
		try {
			
			html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
		BigInteger VigenciaCoberura = detalleCotizacion.getVigenciaDias();
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		Calendar calVigenciaHasta = Calendar.getInstance();
		calVigenciaHasta.setTime(detalleCotizacion.getFechaSiembra());
		calVigenciaHasta.add(Calendar.DAY_OF_MONTH,Integer.parseInt(VigenciaCoberura.toString()==null?"0":VigenciaCoberura.toString()));
        
		parametersHeader.put("SRC_IMAGE", "file:///"+rutaImage);
		parametersHeader.put("VIGENCIA_DESDE", Fecha.format(detalleCotizacion.getFechaSiembra()));
		parametersHeader.put("VIGENCIA_HASTA", Fecha.format(calVigenciaHasta.getTime()));
		String PrecioAjuste = "";
		if (detalleCotizacion.getTipoCultivoId()!=null){
//			AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
//			AgriTipoCultivo tipoCultivo = tipoCultivoDAO.BuscarPorId(detalleCotizacion.getTipoCultivoId());
			if (tipoCultivo.getPrecioAjuste()!=null)
				PrecioAjuste = tipoCultivo.getPrecioAjuste();
		}
		parametersHeader.put("NRO_POLIZA", detalleCotizacion.getId().toString());
		parametersHeader.put("Identificacion", cotizacion.getAsegurado().getIdentificacion());		
		parametersHeader.put("VALOR_ASEGURADO", (new BigDecimal(detalleCotizacion.getSumaAseguradaTotal()).setScale(2,RoundingMode.HALF_UP)).toString());
		parametersHeader.put("VALOR_ASEGURADO_TOTAL", (new BigDecimal(detalleCotizacion.getSumaAseguradaTotal()).setScale(2,RoundingMode.HALF_UP)).toString());

		// /Buscar el detalle de cotizacion
		//parametersHeader.put("CANAL_NOMBRE", detalleCotizacion.getPuntoVenta());
		parametersHeader.put("TIPO_CULTIVO",(detalleCotizacion.getTipoCultivoNombre()==null?"":detalleCotizacion.getTipoCultivoNombre()));
		parametersHeader.put("CLIENTE_NOMBRE",detalleCotizacion.getnombresCliente() );
		parametersHeader.put("HECTAREAS_ASEGURABLES", (new BigDecimal(detalleCotizacion.getHectareasAsegurables()).setScale(2,RoundingMode.HALF_UP)).toString());
		parametersHeader.put("PROVINCIA", detalleCotizacion.getProvinciaNombre());
		parametersHeader.put("CANTON",detalleCotizacion.getCantonNombre());
		parametersHeader.put("PRIMA_TOTAL",(new BigDecimal(detalleCotizacion.getTotalFactura()).setScale(2, RoundingMode.HALF_UP)).toString());
		parametersHeader.put("ASEGURADO", cotizacion.getAsegurado().getNombres()+" "+(cotizacion.getAsegurado().getApellidos()==null?"":cotizacion.getAsegurado().getApellidos()));
		//parametersHeader.put("DERECHO_EMISION", (new BigDecimal(detalleCotizacion.getDerechoEmision()).setScale(2,RoundingMode.HALF_UP)).toString());
		parametersHeader.put("NRO_ANEXO", detalleCotizacion.getId().toString());
		parametersHeader.put("SITIO", (detalleCotizacion.getParroquiaNombre()==null?"":detalleCotizacion.getParroquiaNombre().toUpperCase())+" "+
		(detalleCotizacion.getDireccionSiembra()==null?"":detalleCotizacion.getDireccionSiembra().toUpperCase()));
		parametersHeader.put("MONTO_ASEGURADO",(new BigDecimal(detalleCotizacion.getSumaAseguradaTotal()).setScale(2, RoundingMode.HALF_UP)).toString());
		///TODO: Agregar el precio de ajuste 
		parametersHeader.put("PRECIO_AJUSTE",PrecioAjuste.toString());
		//TODO: Si el valor del precio de ajuste es 0 ocultar la clausula
		if (PrecioAjuste==null)
			parametersHeader.put("PRECIO_AJUSTE_CLASS","oculto");
		else 
			parametersHeader.put("PRECIO_AJUSTE_CLASS","visible");
		// /Recibir parametros de coberturas
//		String coberturaTexto = "";
//		String deducibleTexto = "";
//		String metodoIndemnizacion = "";
//		for (AgriTipoCultivoXTipoCalculo cobertura : coberturas) {
//			if (cobertura.getTipoCultivoId().equals(
//					detalleCotizacion.getTipoCultivoId())) {
//				try {
//					coberturaTexto = new String(cobertura.getCoberturaText(),"UTF-8");
//					deducibleTexto = new String(cobertura.getDeducibleTexto(),"UTF-8");
//					metodoIndemnizacion = new String(cobertura.getMetodoIndemnizacionTexto(), "UTF-8");
//					break;
//				} catch (Exception ex) {
//					continue;
//				}
//			}
//		}
//		parametersHeader.put("COBERTURA_TEXTO", coberturaTexto);
//		parametersHeader.put("DEDUCIBLE_TEXTO", deducibleTexto);
//		parametersHeader.put("METODO_INDEMNIZACION_TEXTO", metodoIndemnizacion);
		parametersHeader.put("VIGENCIA_DIAS", (VigenciaCoberura==null?"0":VigenciaCoberura.toString()));
		//TODO: SI la vigencia de dias es menor a 365 aparece clausula
		if (VigenciaCoberura!=null){
			if (VigenciaCoberura.compareTo(new BigInteger("0"))<365)
				parametersHeader.put("VIGENCIA_CLASS","visible");
			else 
				parametersHeader.put("VIGENCIA_CLASS","oculto");
		}
		else 
			parametersHeader.put("VIGENCIA_CLASS","oculto");

		String Html = GenerarContenido(html, parametersHeader);
		return Html;

	}
	
	public String generarHtmlCondicionesParticularesBlob(AgriCotizacionAprobacion detalleCotizacion) {
		String html = "";
		String rutaImage ="";
		String PlantillaName="";
		CotizacionDAO cotiDAO = new CotizacionDAO();
		Cotizacion cotizacion = cotiDAO.buscarPorId(detalleCotizacion.getId().toString());
		AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
		AgriTipoCultivo tipoCultivo = tipoCultivoDAO.BuscarPorId(detalleCotizacion.getTipoCultivoId());
		//TODO:Buscar plantilla por canal y tipo de cultivo
		AgriParametroXCanalDAO parametroCanalDAO = new AgriParametroXCanalDAO();
		AgriParametroXCanal parametroCanal = parametroCanalDAO.obtenerPorCanalTipoCultivo(detalleCotizacion.getCanalId(), detalleCotizacion.getTipoCultivoId());
		PlantillaName=parametroCanal.getPlantillaNombre();
		
		AgriParametroXCanal agriParametroXCanal= new AgriParametroXCanal();
		AgriParametroXCanalDAO agriParametroXCanalDAO = new AgriParametroXCanalDAO();
		agriParametroXCanal=agriParametroXCanalDAO.obtenerPorCanalTipoCultivo(new BigInteger(cotizacion.getPuntoVenta().getId()), tipoCultivo.getTipoCultivoId());
	
		
		byte[] buffer=agriParametroXCanal.getPlantillaHtml();
		
		try {
			 
			html = new String( buffer);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	   EndosoBeneficiarioDAO endosoBeneficiarioDAO = new EndosoBeneficiarioDAO();
	   EndosoBeneficiario endosoBeneficiario=	endosoBeneficiarioDAO.buscarPorCotizacion(cotizacion);
	  
		SimpleDateFormat Fecha = new SimpleDateFormat("dd/MM/yyyy");
		BigInteger VigenciaCoberura = detalleCotizacion.getVigenciaDias();
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		Calendar calVigenciaHasta = Calendar.getInstance();
		calVigenciaHasta.setTime(detalleCotizacion.getFechaSiembra());
		calVigenciaHasta.add(Calendar.DAY_OF_MONTH,Integer.parseInt(VigenciaCoberura.toString()==null?"0":VigenciaCoberura.toString()));
        Date fechaHasta=calVigenciaHasta.getTime();
		Date fechaActual = new Date();
		//Numero de contenedor
		
		//Armado Final de parametros a mostrar en la poliza
		parametersHeader.put("NumeroPoliza", agriParametroXCanal.getContenedorNumero()+"-"+detalleCotizacion.getIdCotizacion2().toString());
		//buscamos el cliente
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		cliente= clienteDAO.buscarPorId(""+cotizacion.getClienteId());
		parametersHeader.put("AseguradoNombre", cliente.getEntidad().getNombreCompleto());
		parametersHeader.put("VigenciaDesde","DESDE "+ Fecha.format(detalleCotizacion.getFechaSiembra())+" HASTA "+Fecha.format(fechaHasta));
		parametersHeader.put("Anexo", detalleCotizacion.getIdCotizacion2().toString());
		//buscamos el asegurado
		parametersHeader.put("Cliente", cotizacion.getAsegurado().getNombreCompleto());
		parametersHeader.put("Identificacion", cotizacion.getAsegurado().getIdentificacion());		
		parametersHeader.put("Provincia", detalleCotizacion.getProvinciaNombre());
		parametersHeader.put("Canton", detalleCotizacion.getCantonNombre());
		if(detalleCotizacion.getParroquiaNombre()==null || detalleCotizacion.getParroquiaNombre().equals("")){
			//nada
		}else
			parametersHeader.put("Parroquia", detalleCotizacion.getParroquiaNombre());
		
		parametersHeader.put("SitioReferencia", detalleCotizacion.getDireccionSiembra());
		parametersHeader.put("Cultivo", detalleCotizacion.getTipoCultivoNombre());
		parametersHeader.put("CostoProduccion", ""+detalleCotizacion.getCostoProduccion());
		parametersHeader.put("Hectareas", ""+detalleCotizacion.getHectareasAsegurables());
		parametersHeader.put("SumaAsegurada", (new BigDecimal(detalleCotizacion.getSumaAseguradaTotal()).setScale(2,RoundingMode.HALF_UP)).toString());
		parametersHeader.put("PrecioAjuste", tipoCultivo.getPrecioAjuste());
		parametersHeader.put("UnidadMedida", tipoCultivo.getUnidadMedida());
		parametersHeader.put("CultivoDias", ""+VigenciaCoberura);
		
		if(tipoCultivo.getTipo()==2){
			
			parametersHeader.put("VigenciaCultivo1",VigenciaCoberura+ " d&iacute;as (Desde la siembra hasta la madurez fisiol&oacute;gica del cultivo).");
		}else{
			parametersHeader.put("VigenciaCultivo1", "365 D&iacute;as." );
			
		}
		
		
		try{
			parametersHeader.put("PrecioAjuste2", tipoCultivo.getPrecioAjuste2());
			parametersHeader.put("UnidadMedida2", tipoCultivo.getUnidadMedida2());
		}catch(Exception e){
			
		}
		parametersHeader.put("Prima",""+ detalleCotizacion.getTotalFactura());
		parametersHeader.put("Tasa",""+""+ detalleCotizacion.getTasa());
		if(cotizacion.getPuntoVenta().getNombre().equals("COOPROGRESO")){
			parametersHeader.put("Endoso", "FIDEICOMISO AGROINVERSIONES DOS");
		}
		else{
			if(cotizacion.getPuntoVenta().getNombre().equals("AGRIPAC")){
				parametersHeader.put("Endoso", "AGRIPAC S.A");
			}else{
				if(cotizacion.getPuntoVenta().getNombre().equals("ECUAQUIMICA")){
					parametersHeader.put("Endoso", "ECUAQUIMICA ECUATORIANA DE PRODUCTOS QUIMICOS C.A.");
				}else{
					parametersHeader.put("Endoso", "BANCO PICHINCHA C.A");
				}								
			}			
		}
		parametersHeader.put("FechaEmision", Fecha.format(fechaActual.getTime()));
		
		String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
		String rutaRelativaReporte="../../../../../../../../static/images/firmaAgricola.png";
		String rutaFinalFirma=rutaPlantilla+rutaRelativaReporte;
		parametersHeader.put("SRC_IMAGE", "file:///"+rutaFinalFirma);
		String rutaImagen="../../../../../../../../static/images/logoQBE.png";;
		String rutaFinalLogo=rutaPlantilla+rutaImagen;
		parametersHeader.put("SRC_LOGO", "file:///"+rutaFinalLogo);
		String Html = GenerarContenido(html, parametersHeader);
		return Html;

	}
	
	private String GenerarContenido(String html, java.util.Hashtable<String, String> ParamValues){
		List<String> detectedParams = new ArrayList<String>();
		//Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Pattern params = Pattern.compile("\\[[a-zA-Z0-9\\._]*\\]");
		Matcher mat=params.matcher(html);
		while(mat.find()) {
			detectedParams.add(mat.group());
		}

		for(String detectedParam:detectedParams)
		{
			String valor=(ParamValues.get(detectedParam.replace("[", "").replace("]", ""))==null?"":ParamValues.get(detectedParam.replace("[", "").replace("]", "")));
			html=html.replace(detectedParam,  valor);
		}
		return html;
	}
}

