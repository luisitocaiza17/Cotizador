package com.qbe.cotizador.servicios.QBE.ganadero;

public class ConsultaOBJ 
{
	private int numeroCotizacion;
	
	private String estadoActual;
	
	private String NumeroFactura;
	
	private String observaciones;

	public int getNumeroCotizacion() {
		return numeroCotizacion;
	}

	public void setNumeroCotizacion(int numeroCotizacion) {
		this.numeroCotizacion = numeroCotizacion;
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(String estadoActual) {
		this.estadoActual = estadoActual;
	}

	public String getNumeroFactura() {
		return NumeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		NumeroFactura = numeroFactura;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}
