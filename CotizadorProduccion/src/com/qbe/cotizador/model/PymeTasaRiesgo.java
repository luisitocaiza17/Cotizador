package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_TASA_RIESGO database table.
 * 
 */
@Entity
@Table(name="PYME_TASA_RIESGO")
@NamedQueries({
	@NamedQuery(name="PymeTasaRiesgo.findAll", query="SELECT p FROM PymeTasaRiesgo p"),
	@NamedQuery(name="PymeTasaRiesgo.buscarPorConfiguracion", query="SELECT p FROM PymeTasaRiesgo p where p.pymeConfiguracionCoberturaId=:id"),
	@NamedQuery(name="PymeTasaRiesgo.obtenerPorId", query="SELECT p FROM PymeTasaRiesgo p where p.id=:id")	
})

public class PymeTasaRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	private double desde;

	private double hasta;

	@Column(name="PYME_CONFIGURACION_COBERTURA_ID")
	private BigInteger pymeConfiguracionCoberturaId;

	@Column(name="PYME_TIPO_RIESGO_ID")
	private BigInteger pymeTipoRiesgoId;

	private int reclamo;

	private double tasa;

	public PymeTasaRiesgo() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public double getDesde() {
		return this.desde;
	}

	public void setDesde(double desde) {
		this.desde = desde;
	}

	public double getHasta() {
		return this.hasta;
	}

	public void setHasta(double hasta) {
		this.hasta = hasta;
	}

	public BigInteger getPymeConfiguracionCoberturaId() {
		return this.pymeConfiguracionCoberturaId;
	}

	public void setPymeConfiguracionCoberturaId(BigInteger pymeConfiguracionCoberturaId) {
		this.pymeConfiguracionCoberturaId = pymeConfiguracionCoberturaId;
	}

	public BigInteger getPymeTipoRiesgoId() {
		return this.pymeTipoRiesgoId;
	}

	public void setPymeTipoRiesgoId(BigInteger pymeTipoRiesgoId) {
		this.pymeTipoRiesgoId = pymeTipoRiesgoId;
	}

	public int getReclamo() {
		return this.reclamo;
	}

	public void setReclamo(int reclamo) {
		this.reclamo = reclamo;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

}