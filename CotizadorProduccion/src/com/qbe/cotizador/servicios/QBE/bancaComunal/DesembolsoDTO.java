package com.qbe.cotizador.servicios.QBE.bancaComunal;

public class DesembolsoDTO {
	private int codigoEntidadFinanciera;
	private String fechaDesembolso;
	private String numeroOperacion;
	private String numeroResolucion;
	private String valorDesembolso;
	private String Resultado;
	
	public String getResultado() {
		return Resultado;
	}
	public void setResultado(String resultado) {
		Resultado = resultado;
	}
	public int getCodigoEntidadFinanciera() {
		return codigoEntidadFinanciera;
	}
	public void setCodigoEntidadFinanciera(int codigoEntidadFinanciera) {
		this.codigoEntidadFinanciera = codigoEntidadFinanciera;
	}
	public String getFechaDesembolso() {
		return fechaDesembolso;
	}
	public void setFechaDesembolso(String fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}
	public String getNumeroOperacion() {
		return numeroOperacion;
	}
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}
	public String getNumeroResolucion() {
		return numeroResolucion;
	}
	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}
	public String getValorDesembolso() {
		return valorDesembolso;
	}
	public void setValorDesembolso(String valorDesembolso) {
		this.valorDesembolso = valorDesembolso;
	}
	
}
