package com.qbe.cotizador.servlets.producto.agricola;

public class AgriRecotizacionMasivas {

	private String idCotizacion;
	private String costoProduccion;
	private String numeroHectareas;
	private String tasa;
	private String lugar;
	private String fecha;
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getIdCotizacion() {
		return idCotizacion;
	}
	public void setIdCotizacion(String idCotizacion) {
		this.idCotizacion = idCotizacion;
	}
	public String getCostoProduccion() {
		return costoProduccion;
	}
	public void setCostoProduccion(String costoProduccion) {
		this.costoProduccion = costoProduccion;
	}
	public String getNumeroHectareas() {
		return numeroHectareas;
	}
	public void setNumeroHectareas(String numeroHectareas) {
		this.numeroHectareas = numeroHectareas;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
}
