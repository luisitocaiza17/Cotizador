package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the AGRI_PARROQUIAS database table.
 * 
 */
@Entity
@Table(name="AGRI_PARROQUIAS")
@NamedQueries({
	@NamedQuery(name="AgriParroquia.buscarTodos", query="SELECT a FROM AgriParroquia a"),
	@NamedQuery(name="AgriParroquia.obtenerPorSBS", query="SELECT a FROM AgriParroquia a where a.parroquiaSbs=:CodigoSBS"),
	@NamedQuery(name="AgriParroquia.obtenerPorNombre", query="SELECT a FROM AgriParroquia a where a.parroquiaNombre=:nombre"),
	@NamedQuery(name="AgriParroquia.obtenerPorNombreYCanton", query="SELECT a FROM AgriParroquia a where a.parroquiaNombre LIKE :nombre AND a.cantonId=:canton"),
	@NamedQuery(name="AgriParroquia.obtenerPorId", query="SELECT a FROM AgriParroquia a where a.id=:id"),
	@NamedQuery(name="AgriParroquia.obtenerPorCanton", query="SELECT a FROM AgriParroquia a where a.cantonId=:canton")
})

public class AgriParroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="PARROQUIA_NOMBRE")
	private String parroquiaNombre;

	@Column(name="PARROQUIA_SBS")
	private String parroquiaSbs;
	
	@Column(name="CANTON_ID")
	private String cantonId;
	
	@Column(name="CODIGO_ENSURANCE")
	private String codigoEnsurance;
	
	
		
	public String getCodigoEnsurance() {
		return codigoEnsurance;
	}

	public void setCodigoEnsurance(String codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getCantonId() {
		return cantonId;
	}

	public void setCantonId(String cantonId) {
		this.cantonId = cantonId;
	}

	public AgriParroquia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParroquiaNombre() {
		return this.parroquiaNombre;
	}

	public void setParroquiaNombre(String parroquiaNombre) {
		this.parroquiaNombre = parroquiaNombre;
	}

	public String getParroquiaSbs() {
		return this.parroquiaSbs;
	}

	public void setParroquiaSbs(String parroquiaSbs) {
		this.parroquiaSbs = parroquiaSbs;
	}

}