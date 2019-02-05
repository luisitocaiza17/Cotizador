package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the GRUPO_AUTORIZACION database table.
 * 
 */
@Entity
@Table(name="GRUPO_AUTORIZACION")
@NamedQueries({
	@NamedQuery(name="GrupoAutorizacion.buscarPorId", query="SELECT c FROM GrupoAutorizacion c where c.id = :id"),
	@NamedQuery(name="GrupoAutorizacion.buscarPorLider", query="SELECT c FROM GrupoAutorizacion c where c.empleado =:empleado"),
	@NamedQuery(name="GrupoAutorizacion.buscarActivos", query="SELECT c FROM GrupoAutorizacion c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="GrupoAutorizacion.buscarTodos", query="SELECT c FROM GrupoAutorizacion c"),
	@NamedQuery(name="GrupoAutorizacion.buscarPorUnidadNegocio", query="SELECT c FROM GrupoAutorizacion c where c.unidadNegocio =:unidadNegocio")
})
public class GrupoAutorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

    //bi-directional many-to-one association to Empleado
    @ManyToOne
    private Empleado empleado;


	//bi-directional many-to-one association to UnidadNegocio
	@ManyToOne
	@JoinColumn(name="unidad_negocio_id")
	private UnidadNegocio unidadNegocio;

	public GrupoAutorizacion() {
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public UnidadNegocio getUnidadNegocio() {
		return this.unidadNegocio;
	}

	public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

}