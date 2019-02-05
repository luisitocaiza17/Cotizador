package com.qbe.cotizador.servicios.QBE.serviciosExpuestos.sistema;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Seguros123 {

	
	public static String obtenerMarcaModeloSeguros123(String modeloId){
	   
	    String retorno = "";	
	   // Obtenemos la ruta del archivo en el servidor
	   String rutaDeLosArchivos = Seguros123.class.getProtectionDomain().getCodeSource().getLocation().toString();	   
	   rutaDeLosArchivos = rutaDeLosArchivos.replaceAll("Seguros123.class", "");
	   rutaDeLosArchivos = rutaDeLosArchivos.replaceAll("file:/", "");
	  
	   // Obtenemos el archivo
		File archivoModelos = new File(rutaDeLosArchivos+"seguros123modelos.csv");
		BufferedReader archivo= null;
        try {
        	
        	if (archivoModelos.exists()) {
			archivo = new BufferedReader(new FileReader(archivoModelos));
			String filaTexto = null;        
	        while ((filaTexto = archivo.readLine()) != null) {          	
	            String []valorMarcaModelo = filaTexto.split(";");	            
	            if(valorMarcaModelo[2].equalsIgnoreCase(modeloId)){
	            	retorno = valorMarcaModelo[0].toString();
	            	break;
	            }
	         }
        	}else{
        		archivo.close();
	        	throw new Exception("No se cargo el archivo seguros123modelos.csv");
        	}
        	
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 			
		    return retorno;
		}
		
	}
	

