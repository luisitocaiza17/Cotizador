package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PAQUETE database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Paquete.buscarPorId", query="SELECT c FROM Paquete c where c.id = :id"),
	@NamedQuery(name="Paquete.buscarTodos", query="SELECT c FROM Paquete c"),
	@NamedQuery(name="Paquete.buscarPorNombre", query="SELECT c FROM Paquete c where c.nombre = :nombre")
})
public class Paquete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-many association to Cobertura
	@ManyToMany
	@JoinTable(
		name="PAQUETE_COBERTURA"
		, joinColumns={
			@JoinColumn(name="paquete_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="cobertura_id")
			}
		)
	private List<Cobertura> coberturas;

	public Paquete() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cobertura> getCoberturas() {
		return this.coberturas;
	}

	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}

}