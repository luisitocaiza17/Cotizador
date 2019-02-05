package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the OBJETO_GANADERO_DEDUCIBLE database table.
 * 
 */
@Entity
@Table(name="OBJETO_GANADERO_DEDUCIBLE")
@NamedQueries({
	@NamedQuery(name="ObjetoGanaderoDeducible.buscarPorObjetoGanadero", query="SELECT c FROM ObjetoGanaderoDeducible c where c.objetoGanaderoId = :id")
})
public class ObjetoGanaderoDeducible implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="DEDUCIBLE_CATEGORIA")
	private String deducibleCategoria;

	@Column(name="DEDUCIBLE_DEDUCIBLE")
	private int deducibleDeducible;

	@Column(name="DEDUCIBLE_NUMERO_SINIESTRO")
	private String deducibleNumeroSiniestro;

	@Column(name="DEDUCIBLE_PAGO_SINIESTRO")
	private int deduciblePagoSiniestro;

	@Column(name="DEDUCIBLE_PRIMA_APLICADA")
	private int deduciblePrimaAplicada;

	@Column(name="DEDUCIBLE_RANGO_FINAL")
	private int deducibleRangoFinal;

	@Column(name="DEDUCIBLE_RANGO_INICIAL")
	private int deducibleRangoInicial;

	@Column(name="NUMERO_ANIMALES")
	private int numeroAnimales;

	@Column(name="OBJETO_GANADERO_ID")
	private BigInteger objetoGanaderoId;

	public ObjetoGanaderoDeducible() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeducibleCategoria() {
		return this.deducibleCategoria;
	}

	public void setDeducibleCategoria(String deducibleCategoria) {
		this.deducibleCategoria = deducibleCategoria;
	}

	public int getDeducibleDeducible() {
		return this.deducibleDeducible;
	}

	public void setDeducibleDeducible(int deducibleDeducible) {
		this.deducibleDeducible = deducibleDeducible;
	}

	public String getDeducibleNumeroSiniestro() {
		return this.deducibleNumeroSiniestro;
	}

	public void setDeducibleNumeroSiniestro(String deducibleNumeroSiniestro) {
		this.deducibleNumeroSiniestro = deducibleNumeroSiniestro;
	}

	public int getDeduciblePagoSiniestro() {
		return this.deduciblePagoSiniestro;
	}

	public void setDeduciblePagoSiniestro(int deduciblePagoSiniestro) {
		this.deduciblePagoSiniestro = deduciblePagoSiniestro;
	}

	public int getDeduciblePrimaAplicada() {
		return this.deduciblePrimaAplicada;
	}

	public void setDeduciblePrimaAplicada(int deduciblePrimaAplicada) {
		this.deduciblePrimaAplicada = deduciblePrimaAplicada;
	}

	public int getDeducibleRangoFinal() {
		return this.deducibleRangoFinal;
	}

	public void setDeducibleRangoFinal(int deducibleRangoFinal) {
		this.deducibleRangoFinal = deducibleRangoFinal;
	}

	public int getDeducibleRangoInicial() {
		return this.deducibleRangoInicial;
	}

	public void setDeducibleRangoInicial(int deducibleRangoInicial) {
		this.deducibleRangoInicial = deducibleRangoInicial;
	}

	public int getNumeroAnimales() {
		return this.numeroAnimales;
	}

	public void setNumeroAnimales(int numeroAnimales) {
		this.numeroAnimales = numeroAnimales;
	}

	public BigInteger getObjetoGanaderoId() {
		return this.objetoGanaderoId;
	}

	public void setObjetoGanaderoId(BigInteger objetoGanaderoId) {
		this.objetoGanaderoId = objetoGanaderoId;
	}

}