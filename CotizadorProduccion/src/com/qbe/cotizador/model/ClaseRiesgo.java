package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CLASE_RIESGO database table.
 * 
 */
@Entity
@Table(name="CLASE_RIESGO")
@NamedQuery(name="ClaseRiesgo.findAll", query="SELECT c FROM ClaseRiesgo c")
public class ClaseRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	private Ramo ramo;

	public ClaseRiesgo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}