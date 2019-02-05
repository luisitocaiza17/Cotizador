package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

public class Variedad {

	private BigInteger variedadId;
	
	private BigInteger tipoCultivoId;
	
	private String nombre;

	public BigInteger getVariedadId() {
		return variedadId;
	}

	public void setVariedadId(BigInteger variedadId) {
		this.variedadId = variedadId;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
