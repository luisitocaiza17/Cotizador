package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PYME_TIPO_RIESGO database table.
 * 
 */
@Entity
@Table(name="PYME_TIPO_RIESGO")

@NamedQueries({
	@NamedQuery(name="PymeTipoRiesgo.findAll", query="SELECT p FROM PymeTipoRiesgo p"),
	@NamedQuery(name="PymeTipoRiesgo.buscarPorId", query="SELECT p FROM PymeTipoRiesgo p where p.id=:id"),
})
public class PymeTipoRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	private String nombre;

	@Column(name="TEXTO_PREGUNTA")
	private String textoPregunta;

	//bi-directional many-to-one association to PymeCiudadRiesgo
	@OneToMany(mappedBy="pymeTipoRiesgo")
	private List<PymeCiudadRiesgo> pymeCiudadRiesgos;

	public PymeTipoRiesgo() {
	}



	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTextoPregunta() {
		return this.textoPregunta;
	}

	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	public List<PymeCiudadRiesgo> getPymeCiudadRiesgos() {
		return this.pymeCiudadRiesgos;
	}

	public void setPymeCiudadRiesgos(List<PymeCiudadRiesgo> pymeCiudadRiesgos) {
		this.pymeCiudadRiesgos = pymeCiudadRiesgos;
	}

	public PymeCiudadRiesgo addPymeCiudadRiesgo(PymeCiudadRiesgo pymeCiudadRiesgo) {
		getPymeCiudadRiesgos().add(pymeCiudadRiesgo);
		pymeCiudadRiesgo.setPymeTipoRiesgo(this);

		return pymeCiudadRiesgo;
	}

	public PymeCiudadRiesgo removePymeCiudadRiesgo(PymeCiudadRiesgo pymeCiudadRiesgo) {
		getPymeCiudadRiesgos().remove(pymeCiudadRiesgo);
		pymeCiudadRiesgo.setPymeTipoRiesgo(null);

		return pymeCiudadRiesgo;
	}

}