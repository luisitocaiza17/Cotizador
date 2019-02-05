package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

public class Canton {

	private String CantonId;
	
	public String getCantonId() {
		return CantonId;
	}

	public void setCantonId(String cantonId) {
		CantonId = cantonId;
	}

	public String getProvinciaId() {
		return ProvinciaId;
	}

	public void setProvinciaId(String provinciaId) {
		ProvinciaId = provinciaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String ProvinciaId;
	
	private String nombre;
	
}
