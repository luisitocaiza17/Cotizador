package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_DIRECCION database table.
 * 
 */
@Entity
@Table(name="TIPO_DIRECCION")
@NamedQueries({
	@NamedQuery(name="TipoDireccion.buscarPorId", query="SELECT c FROM TipoDireccion c where c.id = :id"),
	@NamedQuery(name="TipoDireccion.buscarTodos", query="SELECT c FROM TipoDireccion c")
})
public class TipoDireccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="tipoDireccion")
	private List<Direccion> direccions;

	public TipoDireccion() {
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

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setTipoDireccion(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setTipoDireccion(null);

		return direccion;
	}

}