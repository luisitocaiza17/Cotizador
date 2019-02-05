package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the UNIDAD_NEGOCIO database table.
 * 
 */
@Entity
@Table(name="UNIDAD_NEGOCIO")
@NamedQueries({
	@NamedQuery(name="UnidadNegocio.buscarPorId", query="SELECT c FROM UnidadNegocio c where c.id = :id"),
	@NamedQuery(name="UnidadNegocio.buscarPorIdEnsurance", query="SELECT c FROM UnidadNegocio c where c.unEnsurance = :unEnsurance"),
	@NamedQuery(name="UnidadNegocio.buscarActivos", query="SELECT c FROM UnidadNegocio c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="UnidadNegocio.buscarTodos", query="SELECT c FROM UnidadNegocio c"),
	@NamedQuery(name="UnidadNegocio.buscarPorNombre", query="SELECT c FROM UnidadNegocio c WHERE c.nombre =:nombre")
})
public class UnidadNegocio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	@Column(name="un_ensurance")
	private String unEnsurance;

	//bi-directional many-to-one association to GrupoAutorizacion
	@OneToMany(mappedBy="unidadNegocio")
	private List<GrupoAutorizacion> grupoAutorizacions;

	public UnidadNegocio() {
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

	public String getUnEnsurance() {
		return this.unEnsurance;
	}

	public void setUnEnsurance(String unEnsurance) {
		this.unEnsurance = unEnsurance;
	}

	public List<GrupoAutorizacion> getGrupoAutorizacions() {
		return this.grupoAutorizacions;
	}

	public void setGrupoAutorizacions(List<GrupoAutorizacion> grupoAutorizacions) {
		this.grupoAutorizacions = grupoAutorizacions;
	}

	public GrupoAutorizacion addGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		getGrupoAutorizacions().add(grupoAutorizacion);
		grupoAutorizacion.setUnidadNegocio(this);

		return grupoAutorizacion;
	}

	public GrupoAutorizacion removeGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		getGrupoAutorizacions().remove(grupoAutorizacion);
		grupoAutorizacion.setUnidadNegocio(null);

		return grupoAutorizacion;
	}

}