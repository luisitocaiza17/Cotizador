package com.qbe.cotizador.servlets.producto.agricola;

public class AgriResultadoEmision 
{
	private String mensaje;
	
	private String factura;
	
	private boolean emitidoCorrectamente;
	
	private boolean generadoXMLCorrectamente;
	
	private String xmlEmision;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isEmitidoCorrectamente() {
		return emitidoCorrectamente;
	}

	public void setEmitidoCorrectamente(boolean emitidoCorrectamente) {
		this.emitidoCorrectamente = emitidoCorrectamente;
	}

	public boolean isGeneradoXMLCorrectamente() {
		return generadoXMLCorrectamente;
	}

	public void setGeneradoXMLCorrectamente(boolean generadoXMLCorrectamente) {
		this.generadoXMLCorrectamente = generadoXMLCorrectamente;
	}

	public String getXmlEmision() {
		return xmlEmision;
	}

	public void setXmlEmision(String xmlEmision) {
		this.xmlEmision = xmlEmision;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	
}
