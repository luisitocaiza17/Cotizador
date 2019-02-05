package com.qbe.cotizador.servlets.producto.agricola;

public class AgriCruceFacturacion {

	private String tramite;
	
	private String cliente;
	
	private double sumaAsegurada;
	
	private double sumaAseguradaDes;
	
	private double primaAsegurada;
	
	private double primaAseguradaDes;
	
	private String Observacion;
	
	public String getObservacion() {
		return Observacion;
	}

	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public double getSumaAseguradaDes() {
		return sumaAseguradaDes;
	}

	public void setSumaAseguradaDes(double sumaAseguradaDes) {
		this.sumaAseguradaDes = sumaAseguradaDes;
	}

	public double getPrimaAsegurada() {
		return primaAsegurada;
	}

	public void setPrimaAsegurada(double primaAsegurada) {
		this.primaAsegurada = primaAsegurada;
	}

	public double getPrimaAseguradaDes() {
		return primaAseguradaDes;
	}

	public void setPrimaAseguradaDes(double primaAseguradaDes) {
		this.primaAseguradaDes = primaAseguradaDes;
	}
	
	
	
}
