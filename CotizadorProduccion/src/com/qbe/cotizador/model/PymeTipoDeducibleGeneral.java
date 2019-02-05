package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PYME_TIPO_DEDUCIBLE_GENERAL")
public class PymeTipoDeducibleGeneral implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEDUCIBLE_GRUPO_COBERTURA_ID")
	private BigInteger tipoDeducibleGeneralId;
	
	@Column(name="RAMO_ID")
	private BigInteger ramoId;
	
	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;
	
	@Column(name="TIPO_DEDUCIBLE_ID")
	private BigInteger tipoDeducibleId;
	
	@Column(name="VALOR")
	private double valor;

	public BigInteger getTipoDeducibleGeneralId() {
		return tipoDeducibleGeneralId;
	}

	public void setTipoDeducibleGeneralId(BigInteger tipoDeducibleGeneralId) {
		this.tipoDeducibleGeneralId = tipoDeducibleGeneralId;
	}

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

	public BigInteger getTipoDeducibleId() {
		return tipoDeducibleId;
	}

	public void setTipoDeducibleId(BigInteger tipoDeducibleId) {
		this.tipoDeducibleId = tipoDeducibleId;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
}
