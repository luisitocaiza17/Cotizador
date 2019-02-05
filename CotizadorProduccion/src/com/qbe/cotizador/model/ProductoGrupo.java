package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRODUCTO_GRUPO database table.
 * 
 */
@Entity
@Table(name="PRODUCTO_GRUPO")
@NamedQueries({
	@NamedQuery(name="ProductoGrupo.buscarPorId", query="SELECT c FROM ProductoGrupo c where c.id = :id"),
	@NamedQuery(name="ProductoGrupo.buscarPorNombre", query="SELECT c FROM ProductoGrupo c where c.nombre = :nombre"),
	@NamedQuery(name="ProductoGrupo.buscarTodos", query="SELECT c FROM ProductoGrupo c")
})
public class ProductoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	public ProductoGrupo() {
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