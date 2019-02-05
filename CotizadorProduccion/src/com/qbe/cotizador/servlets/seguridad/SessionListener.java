package com.qbe.cotizador.servlets.seguridad;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	private static int totalSessionesActivas;

	public static int getTotalSessionesActivas() {
		return totalSessionesActivas;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent session) {
		totalSessionesActivas++;
		//System.out.println("Sesiones Activas");
		// Podremos grabar los datos de la session en la base cuando ingresen
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent session) {
		totalSessionesActivas--;
		//System.out.println("Sesiones Inactivas");
		// Podremos grabar los datos de la session en la base cuando abandonen
		
			
	}

}
