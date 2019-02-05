package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * The persistent class for the AgriPronacaQbeCultivos database table.
 * 
 */
@Entity
@Table(name="AGRI_PRONACA_QBE_CULTIVOS")
@NamedQuery(name="AgriPronacaQbeCultivos.BuscarCultivos", query="SELECT a FROM AgriPronacaQbeCultivos a where a.nombrePronaca=:nombrePronaca")

public class AgriPronacaQbeCultivos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="AGRI_TIPO_CULTIVO_ID")
	private String agriTipoCultivoId;

	@Column(name="COD_PRONACA")
	private String CodPronaca;

	@Column(name="NOMBRE_PRONACA")
	private String nombrePronaca;

	public AgriPronacaQbeCultivos() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgriTipoCultivoId() {
		return this.agriTipoCultivoId;
	}

	public void setAgriTipoCultivoId(String agriTipoCultivoId) {
		this.agriTipoCultivoId = agriTipoCultivoId;
	}

	public String getCodPronaca() {
		return CodPronaca;
	}

	public void setCodPronaca(String codPronaca) {
		CodPronaca = codPronaca;
	}

	public String getNombrePronaca() {
		return nombrePronaca;
	}

	public void setNombrePronaca(String nombrePronaca) {
		this.nombrePronaca = nombrePronaca;
	}
}