package com.qbe.cotizador.servicios.QBE.ganadero;

import java.math.BigDecimal;


public class ResultadoOBJ 
{
	private String NumeroCotizacionOrigen;
	
	private int NumeroCotizacion;
	
	private BigDecimal valorImpuestoSuperBancos;
	
	private BigDecimal valorImpuestoSeguroCampesino;
	
	private BigDecimal valorRecargoSeguroCampesino;
	
	private BigDecimal 	valorDerechoEmision;
	
	private BigDecimal valorImpuestoIVA;
	
	private BigDecimal valorDescuento;
	
	private BigDecimal valorTotal;
	
	private BigDecimal totalSumaAsegurada;
	
	private BigDecimal valorPrimaSinImpuestos;
	
	private String comentarios;

	public String getNumeroCotizacionOrigen() {
		return NumeroCotizacionOrigen;
	}

	public void setNumeroCotizacionOrigen(String numeroCotizacionOrigen) {
		NumeroCotizacionOrigen = numeroCotizacionOrigen;
	}

	public int getNumeroCotizacion() {
		return NumeroCotizacion;
	}

	public void setNumeroCotizacion(int numeroCotizacion) {
		NumeroCotizacion = numeroCotizacion;
	}

	public BigDecimal getValorImpuestoSuperBancos() {
		return valorImpuestoSuperBancos;
	}

	public void setValorImpuestoSuperBancos(BigDecimal valorImpuestoSuperBancos) {
		this.valorImpuestoSuperBancos = valorImpuestoSuperBancos;
	}

	public BigDecimal getValorImpuestoSeguroCampesino() {
		return valorImpuestoSeguroCampesino;
	}

	public void setValorImpuestoSeguroCampesino(
			BigDecimal valorImpuestoSeguroCampesino) {
		this.valorImpuestoSeguroCampesino = valorImpuestoSeguroCampesino;
	}

	public BigDecimal getValorRecargoSeguroCampesino() {
		return valorRecargoSeguroCampesino;
	}

	public void setValorRecargoSeguroCampesino(
			BigDecimal valorRecargoSeguroCampesino) {
		this.valorRecargoSeguroCampesino = valorRecargoSeguroCampesino;
	}

	public BigDecimal getValorDerechoEmision() {
		return valorDerechoEmision;
	}

	public void setValorDerechoEmision(BigDecimal valorDerechoEmision) {
		this.valorDerechoEmision = valorDerechoEmision;
	}

	public BigDecimal getValorImpuestoIVA() {
		return valorImpuestoIVA;
	}

	public void setValorImpuestoIVA(BigDecimal valorImpuestoIVA) {
		this.valorImpuestoIVA = valorImpuestoIVA;
	}

	public BigDecimal getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(BigDecimal valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getTotalSumaAsegurada() {
		return totalSumaAsegurada;
	}

	public void setTotalSumaAsegurada(BigDecimal totalSumaAsegurada) {
		this.totalSumaAsegurada = totalSumaAsegurada;
	}

	public BigDecimal getValorPrimaSinImpuestos() {
		return valorPrimaSinImpuestos;
	}

	public void setValorPrimaSinImpuestos(BigDecimal valorPrimaSinImpuestos) {
		this.valorPrimaSinImpuestos = valorPrimaSinImpuestos;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
}
