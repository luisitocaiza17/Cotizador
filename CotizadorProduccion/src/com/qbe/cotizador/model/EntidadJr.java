package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ENTIDAD_JR database table.
 * 
 */
@Entity
@Table(name="ENTIDAD_JR")
@NamedQueries({
	@NamedQuery(name="EntidadJr.buscarPorIdentificacion", query="SELECT c FROM EntidadJr c where c.identificacion = :identificacion"),
	@NamedQuery(name="EntidadJr.buscarTodos", query="SELECT c FROM EntidadJr c"),
	@NamedQuery(name="EntidadJr.buscarPorId", query="SELECT c FROM EntidadJr c where c.id = :id")
})
public class EntidadJr implements Serializable {
	private static final long serialVersionUID = 1L;

	private String apellido;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String identificacion;

	private String nombre;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	public EntidadJr() {
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}