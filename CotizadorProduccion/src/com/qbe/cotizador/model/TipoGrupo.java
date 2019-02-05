package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_GRUPO database table.
 * 
 */
@Entity
@Table(name="TIPO_GRUPO")
@NamedQueries({
	@NamedQuery(name="TipoGrupo.buscarPorId", query="SELECT t FROM TipoGrupo t where t.id=:id"),
	@NamedQuery(name="TipoGrupo.buscarTodos", query="SELECT t FROM TipoGrupo t"),
	@NamedQuery(name="TipoGrupo.buscarPorNombre", query="SELECT c FROM TipoGrupo c where c.nombre=:nombre")
})
public class TipoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to GrupoPorProducto
	@OneToMany(mappedBy="tipoGrupo")
	private List<GrupoPorProducto> grupoPorProductos;

	public TipoGrupo() {
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

	public List<GrupoPorProducto> getGrupoPorProductos() {
		return this.grupoPorProductos;
	}

	public void setGrupoPorProductos(List<GrupoPorProducto> grupoPorProductos) {
		this.grupoPorProductos = grupoPorProductos;
	}

	public GrupoPorProducto addGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().add(grupoPorProducto);
		grupoPorProducto.setTipoGrupo(this);

		return grupoPorProducto;
	}

	public GrupoPorProducto removeGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().remove(grupoPorProducto);
		grupoPorProducto.setTipoGrupo(null);

		return grupoPorProducto;
	}

}