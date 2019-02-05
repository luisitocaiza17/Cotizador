package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_COBERTURAS_CONFIGURACION database table.
 * 
 */
@Entity
@Table(name="PYME_COBERTURAS_CONFIGURACION")
@NamedQueries({
	@NamedQuery(name="PymeCoberturasConfiguracion.buscarTodos", query="SELECT p FROM PymeCoberturasConfiguracion p"),
	@NamedQuery(name="PymeCoberturasConfiguracion.buscarPorGrupoCobertura", query="SELECT c FROM PymeCoberturasConfiguracion c where c.configProductoId = :configProductoId "),
	@NamedQuery(name="PymeCoberturasConfiguracion.buscarPorGrupoCoberturaIN", query="SELECT c FROM PymeCoberturasConfiguracion c where c.configProductoId in :listConfigProductoId GROUP BY c.ramo,c.coberturaId order by c.cobertura")
})

public class PymeCoberturasConfiguracion implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cobertura;

	@Column(name="cobertura_id")
	private BigInteger coberturaId;

	@Id
	@Column(name="detalle_producto_id")
	private BigInteger detalleProductoId;
	
	@Column(name="config_producto_id")
	private BigInteger configProductoId;

	@Column(name="grupo_cobertura")
	private String grupoCobertura;

	@Column(name="grupo_cobertura_id")
	private BigInteger grupoCoberturaId;

	@Column(name="plan_id")
	private BigInteger planId;

	private String ramo;

	@Column(name="ramo_id")
	private BigInteger ramoId;

	@Column(name="ramotc")
	private String ramotc;
			
	public String getRamotc() {
		return ramotc;
	}

	public void setRamotc(String ramotc) {
		this.ramotc = ramotc;
	}

	public BigInteger getConfigProductoId() {
		return configProductoId;
	}

	public void setConfigProductoId(BigInteger configProductoId) {
		this.configProductoId = configProductoId;
	}

	public PymeCoberturasConfiguracion() {
	}

	public String getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}

	public BigInteger getCoberturaId() {
		return this.coberturaId;
	}

	public void setCoberturaId(BigInteger coberturaId) {
		this.coberturaId = coberturaId;
	}

	public BigInteger getDetalleProductoId() {
		return this.detalleProductoId;
	}

	public void setDetalleProductoId(BigInteger detalleProductoId) {
		this.detalleProductoId = detalleProductoId;
	}

	public String getGrupoCobertura() {
		return this.grupoCobertura;
	}

	public void setGrupoCobertura(String grupoCobertura) {
		this.grupoCobertura = grupoCobertura;
	}

	public BigInteger getGrupoCoberturaId() {
		return this.grupoCoberturaId;
	}

	public void setGrupoCoberturaId(BigInteger grupoCoberturaId) {
		this.grupoCoberturaId = grupoCoberturaId;
	}

	public BigInteger getPlanId() {
		return this.planId;
	}

	public void setPlanId(BigInteger planId) {
		this.planId = planId;
	}

	public String getRamo() {
		return this.ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public BigInteger getRamoId() {
		return this.ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

}