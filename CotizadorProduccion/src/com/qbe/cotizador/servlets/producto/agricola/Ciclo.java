package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;
import java.util.Date;

public class Ciclo {
	
	private BigInteger CicloId;

	private int estado;

	private Date fechaFin;

	private Date fechaInicio;

	private String nombre;

	public BigInteger getCicloId() {
		return CicloId;
	}

	public void setCicloId(BigInteger cicloId) {
		CicloId = cicloId;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
