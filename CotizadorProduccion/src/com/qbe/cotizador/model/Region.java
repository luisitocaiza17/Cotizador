package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="REGION")
@NamedQueries({
	@NamedQuery(name="Region.buscarPorId", query="SELECT c FROM Region c where c.id = :id"),
	@NamedQuery(name="Region.buscarPorNombre", query="SELECT c FROM Region c where upper(c.nombre) = :nombre"),
	@NamedQuery(name="Region.buscarTodos", query="SELECT c FROM Region c"),	
	@NamedQuery(name="Region.buscarPorId", query="SELECT c FROM Region c where c.id = :id")
})
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Sucursal
	@OneToMany(mappedBy="region")
	private List<Sucursal> sucursals;

	public Region() {
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

	public List<Sucursal> getSucursals() {
		return this.sucursals;
	}

	public void setSucursals(List<Sucursal> sucursals) {
		this.sucursals = sucursals;
	}

	public Sucursal addSucursal(Sucursal sucursal) {
		getSucursals().add(sucursal);
		sucursal.setRegion(this);

		return sucursal;
	}

	public Sucursal removeSucursal(Sucursal sucursal) {
		getSucursals().remove(sucursal);
		sucursal.setRegion(null);

		return sucursal;
	}

}