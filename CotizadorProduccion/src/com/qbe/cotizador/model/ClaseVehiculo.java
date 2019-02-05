package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the clase_vehiculo database table.
 * 
 */
@Entity
@Table(name="CLASE_VEHICULO")
@NamedQueries({
	@NamedQuery(name="ClaseVehiculo.buscarPorId", query="SELECT c FROM ClaseVehiculo c where c.id =:id"),
	@NamedQuery(name="ClaseVehiculo.buscarTodos", query="SELECT c FROM ClaseVehiculo c "),
	@NamedQuery(name="ClaseVehiculo.buscarActivos", query="SELECT c FROM ClaseVehiculo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="ClaseVehiculo.buscarPorNombre", query="SELECT c FROM ClaseVehiculo c where c.nombre = :nombre")
})
public class ClaseVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	//bi-directional many-to-one association to ModeloGenerico
	@OneToMany(mappedBy="claseVehiculo")
	private List<Modelo> modelo;

	public ClaseVehiculo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Modelo> getModelo() {
		return this.modelo;
	}

	public void setModelo(List<Modelo> modelo) {
		this.modelo = modelo;
	}

	public Modelo addModelo(Modelo modelo) {
		getModelo().add(modelo);
		modelo.setClaseVehiculo(this);

		return modelo;
	}

	public Modelo removeModeloGenerico(Modelo modelo) {
		getModelo().remove(modelo);
		modelo.setClaseVehiculo(null);

		return modelo;
	}

}