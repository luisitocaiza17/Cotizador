package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_COBERTURA_TASA database table.
 * 
 */
@Entity
@Table(name="PYME_COBERTURA_TASA")
@NamedQuery(name="PymeCoberturaTasa.findAll", query="SELECT c FROM PymeCoberturaTasa c")

public class PymeCoberturaTasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COBERTURA_TASA_ID")
	private BigInteger coberturaTasaId;

	@Column(name="CONFIGURACION_COBERTURA_ID")
	private BigInteger configuracionCoberturaId;

	private double tasa;

	@Column(name="VALOR_COBERTURA_FINAL")
	private double valorCoberturaFinal;

	@Column(name="VALOR_COBERTURA_INICIAL")
	private double valorCoberturaInicial;

	public PymeCoberturaTasa() {
	}

	public BigInteger getCoberturaTasaId() {
		return this.coberturaTasaId;
	}

	public void setCoberturaTasaId(BigInteger coberturaTasaId) {
		this.coberturaTasaId = coberturaTasaId;
	}

	public BigInteger getConfiguracionCoberturaId() {
		return this.configuracionCoberturaId;
	}

	public void setConfiguracionCoberturaId(BigInteger configuracionCoberturaId) {
		this.configuracionCoberturaId = configuracionCoberturaId;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public double getValorCoberturaFinal() {
		return this.valorCoberturaFinal;
	}

	public void setValorCoberturaFinal(double valorCoberturaFinal) {
		this.valorCoberturaFinal = valorCoberturaFinal;
	}

	public double getValorCoberturaInicial() {
		return this.valorCoberturaInicial;
	}

	public void setValorCoberturaInicial(double valorCoberturaInicial) {
		this.valorCoberturaInicial = valorCoberturaInicial;
	}

}