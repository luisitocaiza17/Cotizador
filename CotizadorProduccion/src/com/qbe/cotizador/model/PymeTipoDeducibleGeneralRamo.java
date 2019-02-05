package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="PYME_TIPO_DEDUCIBLE_GENERAL_RAMO")
public class PymeTipoDeducibleGeneralRamo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="RAMO_ID")
	private BigInteger ramoId;
	
	@Id
	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;
	
	@Column(name="RAMO_NOMBRE")
	private String ramoNombre;
	
	@Column(name="GRUPO_NOMBRE")
	private String grupoNombre;

	public BigInteger getRamoId() {
		return ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

	public BigInteger getGrupoPorProductoId() {
		return grupoPorProductoId;
	}

	public void setGrupoPorProductoId(BigInteger grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public String getRamoNombre() {
		return ramoNombre;
	}

	public void setRamoNombre(String ramoNombre) {
		this.ramoNombre = ramoNombre;
	}

	public String getGrupoNombre() {
		return grupoNombre;
	}

	public void setGrupoNombre(String grupoNombre) {
		this.grupoNombre = grupoNombre;
	}
}
