package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the AGRI_PARROQUIAS_DETALLE database table.
 * 
 */
@Entity
@Table(name="AGRI_PARROQUIAS_DETALLE")
@NamedQuery(name="AgriParroquiasDetalle.findAll", query="SELECT a FROM AgriParroquiasDetalle a")
public class AgriParroquiasDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	private String canton;

	@Column(name="CANTON_ID")
	private BigInteger cantonId;

	@Column(name="CODIGO_ENSURANCE")
	private String codigoEnsurance;
	@Id
	private int id;

	private String parroquia;

	@Column(name="PARROQUIA_SBS")
	private String parroquiaSbs;

	private String provincia;

	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;

	public AgriParroquiasDetalle() {
	}

	public String getCanton() {
		return this.canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public BigInteger getCantonId() {
		return this.cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public String getCodigoEnsurance() {
		return this.codigoEnsurance;
	}

	public void setCodigoEnsurance(String codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public String getParroquiaSbs() {
		return this.parroquiaSbs;
	}

	public void setParroquiaSbs(String parroquiaSbs) {
		this.parroquiaSbs = parroquiaSbs;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public BigInteger getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

}