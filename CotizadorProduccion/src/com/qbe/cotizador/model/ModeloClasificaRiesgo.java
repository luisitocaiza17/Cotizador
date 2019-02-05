package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the MODELO_CLASIFICA_RIESGO database table.
 * 
 */
@Entity
@Table(name="MODELO_CLASIFICA_RIESGO")
@NamedQueries({
	@NamedQuery(name="ModeloClasificaRiesgo.buscarPorClasificacionRiesgoPorModelo", query="SELECT c FROM ModeloClasificaRiesgo c where c.modelo = :modelo and c.cobertura =:cobertura")
})
public class ModeloClasificaRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to ClasificacionRiesgo
	@ManyToOne
	@JoinColumn(name="clasificacion_riesgo_id")
	private ClasificacionRiesgo clasificacionRiesgo;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	private Modelo modelo;

	public ModeloClasificaRiesgo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClasificacionRiesgo getClasificacionRiesgo() {
		return this.clasificacionRiesgo;
	}

	public void setClasificacionRiesgo(ClasificacionRiesgo clasificacionRiesgo) {
		this.clasificacionRiesgo = clasificacionRiesgo;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}