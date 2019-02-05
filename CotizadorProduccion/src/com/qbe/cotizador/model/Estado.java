package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ESTADO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Estado.buscarPorId", query="SELECT c FROM Estado c where c.id = :id"),
	@NamedQuery(name="Estado.buscarActivos", query="SELECT c FROM Estado c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Estado.buscarPorNombre", query="SELECT c FROM Estado c WHERE c.nombre =:nombre AND c.clase =:clase"),
	@NamedQuery(name="Estado.buscarTodos", query="SELECT c FROM Estado c"),
	@NamedQuery(name="Estado.buscarPorClase", query="SELECT c FROM Estado c where c.clase = :clase ORDER BY c.nombre"),
	@NamedQuery(name="Estado.buscarPorNombreClase", query="SELECT c FROM Estado c WHERE c.nombre =:nombre AND c.clase =:clase")
})
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private Boolean activo;

	private String clase;

	private String nombre;

	//bi-directional many-to-one association to SolicitudInspeccion
	@OneToMany(mappedBy="estado")
	private List<SolicitudInspeccion> solicitudInspeccions;

	public Estado() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getClase() {
		return this.clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<SolicitudInspeccion> getSolicitudInspeccions() {
		return this.solicitudInspeccions;
	}

	public void setSolicitudInspeccions(List<SolicitudInspeccion> solicitudInspeccions) {
		this.solicitudInspeccions = solicitudInspeccions;
	}

	public SolicitudInspeccion addSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().add(solicitudInspeccion);
		solicitudInspeccion.setEstado(this);

		return solicitudInspeccion;
	}

	public SolicitudInspeccion removeSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().remove(solicitudInspeccion);
		solicitudInspeccion.setEstado(null);

		return solicitudInspeccion;
	}

}