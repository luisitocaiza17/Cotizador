package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the GRUPO_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="GRUPO_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="GrupoProducto.buscarPorId", query="SELECT c FROM GrupoProducto c where c.id=:id"),
	@NamedQuery(name="GrupoProducto.buscarTodos", query="SELECT c FROM GrupoProducto c"),
	@NamedQuery(name="GrupoProducto.buscarPorNombre", query="SELECT c FROM GrupoProducto c where c.nombre=:nombre")
})
public class GrupoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to GrupoPorProducto
	@OneToMany(mappedBy="grupoProducto")
	private List<GrupoPorProducto> grupoPorProductos;

	public GrupoProducto() {
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

	public List<GrupoPorProducto> getGrupoPorProductos() {
		return this.grupoPorProductos;
	}

	public void setGrupoPorProductos(List<GrupoPorProducto> grupoPorProductos) {
		this.grupoPorProductos = grupoPorProductos;
	}

	public GrupoPorProducto addGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().add(grupoPorProducto);
		grupoPorProducto.setGrupoProducto(this);

		return grupoPorProducto;
	}

	public GrupoPorProducto removeGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().remove(grupoPorProducto);
		grupoPorProducto.setGrupoProducto(null);

		return grupoPorProducto;
	}

}