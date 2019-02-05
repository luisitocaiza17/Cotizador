package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CLASIFICACION_RIESGO database table.
 * 
 */
@Entity
@Table(name="CLASIFICACION_RIESGO")
@NamedQuery(name="ClasificacionRiesgo.findAll", query="SELECT c FROM ClasificacionRiesgo c")
public class ClasificacionRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="indice_asociado")
	private String indiceAsociado;

	private String nemotecnico;

	private String nombre;

	//bi-directional many-to-one association to ModeloClasificaRiesgo
	@OneToMany(mappedBy="clasificacionRiesgo")
	private List<ModeloClasificaRiesgo> modeloClasificaRiesgos;

	public ClasificacionRiesgo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndiceAsociado() {
		return this.indiceAsociado;
	}

	public void setIndiceAsociado(String indiceAsociado) {
		this.indiceAsociado = indiceAsociado;
	}

	public String getNemotecnico() {
		return this.nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ModeloClasificaRiesgo> getModeloClasificaRiesgos() {
		return this.modeloClasificaRiesgos;
	}

	public void setModeloClasificaRiesgos(List<ModeloClasificaRiesgo> modeloClasificaRiesgos) {
		this.modeloClasificaRiesgos = modeloClasificaRiesgos;
	}

	public ModeloClasificaRiesgo addModeloClasificaRiesgo(ModeloClasificaRiesgo modeloClasificaRiesgo) {
		getModeloClasificaRiesgos().add(modeloClasificaRiesgo);
		modeloClasificaRiesgo.setClasificacionRiesgo(this);

		return modeloClasificaRiesgo;
	}

	public ModeloClasificaRiesgo removeModeloClasificaRiesgo(ModeloClasificaRiesgo modeloClasificaRiesgo) {
		getModeloClasificaRiesgos().remove(modeloClasificaRiesgo);
		modeloClasificaRiesgo.setClasificacionRiesgo(null);

		return modeloClasificaRiesgo;
	}

}