package com.qbe.cotizador.servlets.producto.agricola;

public class AgriRespuestaOffline {
	
	public String error;
	public String correo;
	public String razon;
	public String agente;
	public String Cotizacion;
	public String clienteIdentificar;
	public String ClienteNombre;
	
	public String getClienteIdentificar() {
		return clienteIdentificar;
	}
	public void setClienteIdentificar(String clienteIdentificar) {
		this.clienteIdentificar = clienteIdentificar;
	}
	public String getClienteNombre() {
		return ClienteNombre;
	}
	public void setClienteNombre(String clienteNombre) {
		ClienteNombre = clienteNombre;
	}
	public String getCotizacion() {
		return Cotizacion;
	}
	public void setCotizacion(String cotizacion) {
		Cotizacion = cotizacion;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getAgente() {
		return agente;
	}
	public void setAgente(String agente) {
		this.agente = agente;
	}	
}
