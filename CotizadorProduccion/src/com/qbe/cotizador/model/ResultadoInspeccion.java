package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RESULTADO_INSPECCION database table.
 * 
 */
@Entity
@Table(name="RESULTADO_INSPECCION")
@NamedQuery(name="ResultadoInspeccion.findAll", query="SELECT r FROM ResultadoInspeccion r")
public class ResultadoInspeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	public ResultadoInspeccion() {
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

}