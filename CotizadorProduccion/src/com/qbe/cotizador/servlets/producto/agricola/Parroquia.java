package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

public class Parroquia {
	
	private String ParroquiaId;
	
	private String CantonId;
	
	private String nombre;

	public String getParroquiaId() {
		return ParroquiaId;
	}

	public void setParroquiaId(String parroquiaId) {
		ParroquiaId = parroquiaId;
	}

	public String getCantonId() {
		return CantonId;
	}

	public void setCantonId(String cantonId) {
		CantonId = cantonId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
