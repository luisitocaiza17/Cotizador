package com.qbe.cotizador.servlets.producto.agricola;

public class AgriProcesoEmisiones {
	
	private String numeroTramite;
	private String fechaEmision;
	private Double numeroHectares;
	private Double sumaAsegurada;
	private Double primaNeta;
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Double getNumeroHectares() {
		return numeroHectares;
	}
	public void setNumeroHectares(Double numeroHectares) {
		this.numeroHectares = numeroHectares;
	}
	public Double getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(Double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public Double getPrimaNeta() {
		return primaNeta;
	}
	public void setPrimaNeta(Double primaNeta) {
		this.primaNeta = primaNeta;
	}
	
}
