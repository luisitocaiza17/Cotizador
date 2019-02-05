package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the PYME_EXT_RAMO database table.
 * 
 */
@Entity
@Table(name="PYME_EXT_RAMO")
@NamedQueries({
	@NamedQuery(name="PymeExtRamo.buscarTodos", query="SELECT p FROM PymeExtRamo p"),
	@NamedQuery(name="PymeExtRamo.buscarPorId", query="SELECT p FROM PymeExtRamo p where p.ramoId = :ramoId")
})
public class PymeExtRamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RAMO_ID")
	private BigInteger ramoId;

	@Column(name="CLASE_RIESGO_ID")
	private String claseRiesgoId;

	@Column(name="TIPO_ITEM_ID")
	private String tipoItemId;

	@Column(name="TIPO_RIESGO_ID")
	private String tipoRiesgoId;

	public PymeExtRamo() {
	}

	public BigInteger getRamoId() {
		return this.ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

	public String getClaseRiesgoId() {
		return this.claseRiesgoId;
	}

	public void setClaseRiesgoId(String claseRiesgoId) {
		this.claseRiesgoId = claseRiesgoId;
	}

	public String getTipoItemId() {
		return this.tipoItemId;
	}

	public void setTipoItemId(String tipoItemId) {
		this.tipoItemId = tipoItemId;
	}

	public String getTipoRiesgoId() {
		return this.tipoRiesgoId;
	}

	public void setTipoRiesgoId(String tipoRiesgoId) {
		this.tipoRiesgoId = tipoRiesgoId;
	}

}