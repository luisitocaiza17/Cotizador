package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_TIPO_DEDUCIBLE_COBERTURA database table.
 * 
 */
@Entity
@Table(name="PYME_TIPO_DEDUCIBLE_COBERTURA")
@NamedQuery(name="PymeTipoDeducibleCobertura.findAll", query="SELECT c FROM PymeTipoDeducibleCobertura c")
public class PymeTipoDeducibleCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TIPO_DEDUCIBLE_COBERTURA_ID")
	private String tipoDeducibleCoberturaId;

	@Column(name="CONFIGURACION_COBERTURA_ID")
	private BigInteger configuracionCoberturaId;

	@Column(name="TIPO_DEDUCIBLE_ID")
	private BigInteger tipoDeducibleId;
	@Column(name="SEGURIDAD_ADECUADA")
	private boolean seguridadAdecuada;
	
	public boolean getSeguridadAdecuada() {
		return seguridadAdecuada;
	}

	public void setSeguridadAdecuada(boolean seguridadAdecuada) {
		this.seguridadAdecuada = seguridadAdecuada;
	}

	private double valor;
	
	private String texto;

	public PymeTipoDeducibleCobertura() {
	}

	public String getTipoDeducibleCoberturaId() {
		return this.tipoDeducibleCoberturaId;
	}

	public void setTipoDeducibleCoberturaId(String tipoDeducibleCoberturaId) {
		this.tipoDeducibleCoberturaId = tipoDeducibleCoberturaId;
	}

	public BigInteger getConfiguracionCoberturaId() {
		return this.configuracionCoberturaId;
	}

	public void setConfiguracionCoberturaId(BigInteger configuracionCoberturaId) {
		this.configuracionCoberturaId = configuracionCoberturaId;
	}

	public BigInteger getTipoDeducibleId() {
		return this.tipoDeducibleId;
	}

	public void setTipoDeducibleId(BigInteger tipoDeducibleId) {
		this.tipoDeducibleId = tipoDeducibleId;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}