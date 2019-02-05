package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_COBERTURA database table.
 * 
 */
@Entity
@Table(name="PYME_COBERTURA")
@NamedQueries({
	@NamedQuery(name="PymeCobertura.buscarTodos", query="SELECT c FROM PymeCobertura c "),
	@NamedQuery(name="PymeCobertura.buscarPorId", query="SELECT c FROM PymeCobertura c where c.coberturaPymesId = :id")
})
public class PymeCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COBERTURA_PYMES_ID")
	private BigInteger coberturaPymesId;

	@Column(name="GRUPO_COBERTURA_ID")
	private BigInteger grupoCoberturaId;

	private String nombre;

	@Column(name="RAMO_ID")
	private BigInteger ramoId;

	@Column(name="TIPO_COBERTURA_ID")
	private BigInteger tipoCoberturaId;

	public PymeCobertura() {
	}

	public BigInteger getCoberturaPymesId() {
		return this.coberturaPymesId;
	}

	public void setCoberturaPymesId(BigInteger coberturaPymesId) {
		this.coberturaPymesId = coberturaPymesId;
	}

	public BigInteger getGrupoCoberturaId() {
		return this.grupoCoberturaId;
	}

	public void setGrupoCoberturaId(BigInteger grupoCoberturaId) {
		this.grupoCoberturaId = grupoCoberturaId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getRamoId() {
		return this.ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

	public BigInteger getTipoCoberturaId() {
		return this.tipoCoberturaId;
	}

	public void setTipoCoberturaId(BigInteger tipoCoberturaId) {
		this.tipoCoberturaId = tipoCoberturaId;
	}

	
}