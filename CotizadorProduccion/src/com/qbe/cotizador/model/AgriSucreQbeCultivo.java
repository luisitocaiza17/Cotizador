package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AGRI_SUCRE_QBE_CULTIVOS database table.
 * 
 */
@Entity
@Table(name="AGRI_SUCRE_QBE_CULTIVOS")
@NamedQuery(name="AgriSucreQbeCultivo.BuscarCultivos", query="SELECT a FROM AgriSucreQbeCultivo a where a.codSucre=:codSucre")
public class AgriSucreQbeCultivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="AGRI_TIPO_CULTIVO_ID")
	private String agriTipoCultivoId;

	@Column(name="COD_SUCRE")
	private String codSucre;

	@Column(name="NOMBRE_SUCRE")
	private String nombreSucre;

	public AgriSucreQbeCultivo() {
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

	public String getCodSucre() {
		return this.codSucre;
	}

	public void setCodSucre(String codSucre) {
		this.codSucre = codSucre;
	}

	public String getNombreSucre() {
		return this.nombreSucre;
	}

	public void setNombreSucre(String nombreSucre) {
		this.nombreSucre = nombreSucre;
	}

}