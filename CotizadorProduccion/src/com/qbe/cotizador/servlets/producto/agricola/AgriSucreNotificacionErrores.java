package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class AgriSucreNotificacionErrores {

	public String GeneradorHtml(String tramite, String error,String plantilla){
		String html = "";
		plantilla="confirmarMail.html";
		
		//tomamos la ruta relativa de la clase para referenciar la plantilla
		String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
		String rutaRelativaReporte="../../../../../../../../static/plantillas/mailErrorSucre.html";
		//String rutaImagen="../../../../../../../../static/images/colonial.png";
		rutaPlantilla=rutaPlantilla+rutaRelativaReporte;
		
		try {
			html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
			java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
			parametersHeader.put("TRAMITE",tramite);
			parametersHeader.put("ERROR",error);
			//parametersHeader.put("IMAGEN",error);
			html=GenerarContenido(html,parametersHeader);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
		
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
