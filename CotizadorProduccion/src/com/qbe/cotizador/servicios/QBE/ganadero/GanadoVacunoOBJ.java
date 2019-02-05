package com.qbe.cotizador.servicios.QBE.ganadero;

import java.math.BigDecimal;

public class GanadoVacunoOBJ 
{

	private int edadMeses;

	private String numeroArete;

	private String numeroChip;

	private String razaId;

	private String tipoId;

	private BigDecimal valorAsegurar;
	
	private String color;
	
	//1 - Importada
	//2 - Nacional
	private int origen;
	
	private String procedencia;

	public int getEdadMeses() {
		return edadMeses;
	}

	public void setEdadMeses(int edadMeses) {
		this.edadMeses = edadMeses;
	}

	public String getNumeroArete() {
		return numeroArete;
	}

	public void setNumeroArete(String numeroArete) {
		this.numeroArete = numeroArete;
	}

	public String getNumeroChip() {
		return numeroChip;
	}

	public void setNumeroChip(String numeroChip) {
		this.numeroChip = numeroChip;
	}

	public String getRazaId() {
		return razaId;
	}

	public void setRazaId(String razaId) {
		this.razaId = razaId;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public BigDecimal getValorAsegurar() {
		return valorAsegurar;
	}

	public void setValorAsegurar(BigDecimal valorAsegurar) {
		this.valorAsegurar = valorAsegurar;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
