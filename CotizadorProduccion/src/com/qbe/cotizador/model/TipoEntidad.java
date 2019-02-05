package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_ENTIDAD database table.
 * 
 */
@Entity
@Table(name="TIPO_ENTIDAD")
@NamedQueries({
	@NamedQuery(name="TipoEntidad.buscarPorId", query="SELECT t FROM TipoEntidad t where t.id = :id"),	
	@NamedQuery(name="TipoEntidad.buscarTodos", query="SELECT t FROM TipoEntidad t")
})
public class TipoEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_contable")
	private String codigoContable;

	private String nombre;

	//bi-directional many-to-one association to Entidad
	@OneToMany(mappedBy="tipoEntidad")
	private List<Entidad> entidads;

	public TipoEntidad() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoContable() {
		return this.codigoContable;
	}

	public void setCodigoContable(String codigoContable) {
		this.codigoContable = codigoContable;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Entidad> getEntidads() {
		return this.entidads;
	}

	public void setEntidads(List<Entidad> entidads) {
		this.entidads = entidads;
	}

	public Entidad addEntidad(Entidad entidad) {
		getEntidads().add(entidad);
		entidad.setTipoEntidad(this);

		return entidad;
	}

	public Entidad removeEntidad(Entidad entidad) {
		getEntidads().remove(entidad);
		entidad.setTipoEntidad(null);

		return entidad;
	}

}