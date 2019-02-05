package com.qbe.cotizador.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the modelo_generico database table.
 * 
 */
@Entity
@Table(name="MODELO_GENERICO")
@NamedQueries({
	@NamedQuery(name="ModeloGenerico.buscarPorId", query="SELECT m FROM ModeloGenerico m WHERE m.id=:id"),
	@NamedQuery(name="ModeloGenerico.buscarTodos", query="SELECT m FROM ModeloGenerico m"),
	@NamedQuery(name="ModeloGenerico.buscarActivos", query="SELECT m FROM ModeloGenerico m WHERE m.activo=:valorActivo"),
	@NamedQuery(name="ModeloGenerico.buscarPorNombre", query="SELECT m FROM ModeloGenerico m WHERE m.nombre=:nombre")
})
public class ModeloGenerico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	//bi-directional many-to-one association to ModeloGenerico
	@OneToMany(mappedBy="modeloGenerico")
	private List<Modelo> modelo;

	
	public ModeloGenerico() {
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
		return modelo;
	}

	public void setModelo(List<Modelo> modelo) {
		this.modelo = modelo;
	}
}