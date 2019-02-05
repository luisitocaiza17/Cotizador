package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the PYME_CIUDAD_RIESGO database table.
 * 
 */
@Entity
@Table(name="PYME_CIUDAD_RIESGO")

@NamedQueries({
	@NamedQuery(name="PymeCiudadRiesgo.findAll", query="SELECT p FROM PymeCiudadRiesgo p"),
	@NamedQuery(name="PymeCiudadRiesgo.obtenerPorId", query="SELECT p FROM PymeCiudadRiesgo p where p.id=:id"),
	@NamedQuery(name="PymeCiudadRiesgo.obtenerPorCiudad", query="SELECT p FROM PymeCiudadRiesgo p where p.canton=:canton")	
})

public class PymeCiudadRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	//bi-directional many-to-one association to Ciudad
	//bi-directional many-to-one association to Canton
	@ManyToOne
	@JoinColumn(name="CIUDAD_ID")
	private Canton canton;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	private Provincia provincia;

	//bi-directional many-to-one association to PymeTipoRiesgo
	@ManyToOne
	@JoinColumn(name="TIPO_RIESGO_ID")
	private PymeTipoRiesgo pymeTipoRiesgo;

	public PymeCiudadRiesgo() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public PymeTipoRiesgo getPymeTipoRiesgo() {
		return this.pymeTipoRiesgo;
	}

	public void setPymeTipoRiesgo(PymeTipoRiesgo pymeTipoRiesgo) {
		this.pymeTipoRiesgo = pymeTipoRiesgo;
	}

}