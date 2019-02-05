package com.qbe.cotizador.servlets.producto.pymes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import com.qbe.cotizador.util.Utilitarios;

public  class MailGenericoPlantillas {

	public static void EnvioPlantillaGenerica(String correos, java.util.Hashtable<String, String> ParamValues, String plantillaRuta,HttpServletRequest request){
		String [] correosArr=correos.split(";");
		String rutaPlantilla = request.getServletContext().getRealPath("")+plantillaRuta;
		BufferedReader br = null;

		String cuerpoMail="";
		String link = request.getRequestURL().toString();
		for(int i=0;i<correosArr.length;i++){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaPlantilla), "UTF-8"));

				String linea;

				while ((linea = br.readLine()) != null) {
					cuerpoMail = cuerpoMail + linea;
				}

				//Estimamos que solo tenemos como parametros 5 variables

				cuerpoMail = GenerarContenido(cuerpoMail, ParamValues);

			} catch (Exception e) {
				e.printStackTrace();
			}

			Utilitarios.envioMail(correosArr[i], "QBE Seguros Colonial: Insepecci&oacute;n", cuerpoMail);
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public static void EnvioPlantillaGenerica(String correos, java.util.Hashtable<String, String> ParamValues, String plantillaRuta,
												HttpServletRequest request, byte[] adjunto){
		String [] correosArr=correos.split(";");
		String rutaPlantilla = request.getServletContext().getRealPath("")+plantillaRuta;
		BufferedReader br = null;

		String cuerpoMail="";
		String link = request.getRequestURL().toString();
		for(int i=0;i<correosArr.length;i++){
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaPlantilla), "UTF-8"));

				String linea;

				while ((linea = br.readLine()) != null) {
					cuerpoMail = cuerpoMail + linea;
				}

				//Estimamos que solo tenemos como parametros 5 variables

				cuerpoMail = GenerarContenido(cuerpoMail, ParamValues);

			} catch (Exception e) {
				e.printStackTrace();
			}

			Utilitarios.envioMailPDFAdjunto(correosArr[i], "QBE Seguros Colonial: Cotización", cuerpoMail, adjunto);
		}

		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void EnvioPlantillaGenerica(String correos, java.util.Hashtable<String, String> ParamValues, String plantillaRuta,
			HttpServletRequest request, String correoCopiaA){

		BufferedReader br = null;
		try
		{
			InternetAddress[] listaDeCorreos = InternetAddress.parse(correos);
			InternetAddress[] listaDeCopias = InternetAddress.parse(correoCopiaA);
			
			String rutaPlantilla = request.getServletContext().getRealPath("")+plantillaRuta;
			
			
			String cuerpoMail="";
			String link = request.getRequestURL().toString();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaPlantilla), "UTF-8"));
			
			String linea;
			
			while ((linea = br.readLine()) != null) {
			cuerpoMail = cuerpoMail + linea;
			}
			
			//Estimamos que solo tenemos como parametros 5 variables
			cuerpoMail = GenerarContenido(cuerpoMail, ParamValues);
			
			try {
			br.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			Utilitarios.envioMail(listaDeCorreos, listaDeCopias, "QBE Seguros Colonial: Insepecci&oacute;n", cuerpoMail);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
	public static void EnvioPlantillaGenerica(String correos, java.util.Hashtable<String, String> ParamValues, String plantillaRuta, 
												HttpServletRequest request, byte[] adjunto, String correoCopiaA){
		
		BufferedReader br = null;
		try{
			
			InternetAddress[] listaDeCorreos = InternetAddress.parse(correos);
			InternetAddress[] listaDeCopias = InternetAddress.parse(correoCopiaA);
			
			String rutaPlantilla = request.getServletContext().getRealPath("")+plantillaRuta;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(rutaPlantilla), "UTF-8"));
			
			String cuerpoMail="";

			String linea;

			while ((linea = br.readLine()) != null) {
				cuerpoMail = cuerpoMail + linea;
			}

			//Estimamos que solo tenemos como parametros 5 variables
			cuerpoMail = GenerarContenido(cuerpoMail, ParamValues);

			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Utilitarios.envioMailPDFAdjunto(listaDeCorreos, listaDeCopias, "QBE Seguros Colonial: Insepecci&oacute;n", cuerpoMail, adjunto);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String GenerarContenido(String html, java.util.Hashtable<String, String> ParamValues){
		List<String> detectedParams = new ArrayList<String>();
		Pattern params=Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Matcher mat=params.matcher(html);
		while(mat.find()) {
			detectedParams.add(mat.group());
		}

		for(String detectedParam:detectedParams)
		{
			String valor=ParamValues.get(detectedParam.replace("[", "").replace("]", ""));
			html=html.replace(detectedParam,  valor);
		}
		return html;
	}
}
