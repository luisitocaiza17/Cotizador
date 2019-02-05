package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ZONA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Zona.buscarPorId", query="SELECT c FROM Zona c where c.id = :id"),
	@NamedQuery(name="Zona.buscarTodos", query="SELECT c FROM Zona c"),
	@NamedQuery(name="Zona.buscarPorNombre", query="SELECT c FROM Zona c where c.nombre = :nombre")
})
public class Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="zona")
	private List<Direccion> direccions;
	
	//bi-directional many-to-one association to HonorariosInspector
	@OneToMany(mappedBy="zona")
	private List<HonorariosInspector> honorariosInspectors;
	
	//bi-directional many-to-one association to SolicitudInspeccion
		@OneToMany(mappedBy="zona")
		private List<SolicitudInspeccion> solicitudInspeccions;

	public Zona() {
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

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setZona(this);

		return direccion;
	}
	
	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setZona(null);

		return direccion;
	}

	public HonorariosInspector removeHonorariosInspector(HonorariosInspector honorariosInspector) {
		getHonorariosInspector().remove(honorariosInspector);
		honorariosInspector.setZona(null);

		return honorariosInspector;
	}
	
	public List<HonorariosInspector> getHonorariosInspector() {
		return this.honorariosInspectors;
	}

	public void setHonorariosInspector(List<HonorariosInspector> honorariosInspector) {
		this.honorariosInspectors = honorariosInspector;
	}

	public HonorariosInspector addHonorariosInspector(HonorariosInspector honorariosInspector) {
		getHonorariosInspector().add(honorariosInspector);
		honorariosInspector.setZona(this);

		return honorariosInspector;
	}

	public SolicitudInspeccion removeSolicitudInspeccion(SolicitudInspeccion honorariosInspector) {
		getSolicitudInspeccion().remove(honorariosInspector);
		honorariosInspector.setZona(null);

		return honorariosInspector;
	}
	
	public List<SolicitudInspeccion> getSolicitudInspeccion() {
		return this.solicitudInspeccions;
	}

	public void setSolicitudInspeccion(List<SolicitudInspeccion> solicitudInspeccion) {
		this.solicitudInspeccions = solicitudInspeccion;
	}

	public SolicitudInspeccion addHonorariosInspector(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccion().add(solicitudInspeccion);
		solicitudInspeccion.setZona(this);

		return solicitudInspeccion;
	}

}