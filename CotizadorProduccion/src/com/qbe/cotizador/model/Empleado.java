package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the EMPLEADO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Empleado.buscarPorId", query="SELECT c FROM Empleado c where c.id = :id"),
	@NamedQuery(name="Empleado.buscarPorEntidadId", query="SELECT c FROM Empleado c where c.entidad = :entidad"),
	@NamedQuery(name="Empleado.buscarActivos", query="SELECT c FROM Empleado c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Empleado.buscarTodos", query="SELECT c FROM Empleado c"),
	@NamedQuery(name="Empleado.buscarPorCargo", query="SELECT c FROM Empleado c WHERE c.activo ='1' and c.cargo =:cargo")
})
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String firma;

	//bi-directional many-to-one association to Entidad
	@ManyToOne
	private Entidad entidad;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	private Cargo cargo;

	//bi-directional many-to-one association to Cargo
		@ManyToOne
		private Sucursal sucursal;

	//bi-directional many-to-one association to GrupoAutorizacion
	@OneToMany(mappedBy="empleado")
	private List<GrupoAutorizacion> grupoAutorizacions;

	//bi-directional many-to-one association to GrupoUsuarioAutorizacion
	@OneToMany(mappedBy="empleado")
	private List<GrupoUsuarioAutorizacion> grupoUsuarioAutorizacions;

	public Empleado() {
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

	public String getFirma() {
		return this.firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<GrupoAutorizacion> getGrupoAutorizacions() {
		return this.grupoAutorizacions;
	}

	public void setGrupoAutorizacions(List<GrupoAutorizacion> grupoAutorizacions) {
		this.grupoAutorizacions = grupoAutorizacions;
	}

	public GrupoAutorizacion addGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		getGrupoAutorizacions().add(grupoAutorizacion);
		grupoAutorizacion.setEmpleado(this);

		return grupoAutorizacion;
	}

	public GrupoAutorizacion removeGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion) {
		getGrupoAutorizacions().remove(grupoAutorizacion);
		grupoAutorizacion.setEmpleado(null);

		return grupoAutorizacion;
	}

	public List<GrupoUsuarioAutorizacion> getGrupoUsuarioAutorizacions() {
		return this.grupoUsuarioAutorizacions;
	}

	public void setGrupoUsuarioAutorizacions(List<GrupoUsuarioAutorizacion> grupoUsuarioAutorizacions) {
		this.grupoUsuarioAutorizacions = grupoUsuarioAutorizacions;
	}

	public GrupoUsuarioAutorizacion addGrupoUsuarioAutorizacion(GrupoUsuarioAutorizacion grupoUsuarioAutorizacion) {
		getGrupoUsuarioAutorizacions().add(grupoUsuarioAutorizacion);
		grupoUsuarioAutorizacion.setEmpleado(this);

		return grupoUsuarioAutorizacion;
	}

	public GrupoUsuarioAutorizacion removeGrupoUsuarioAutorizacion(GrupoUsuarioAutorizacion grupoUsuarioAutorizacion) {
		getGrupoUsuarioAutorizacions().remove(grupoUsuarioAutorizacion);
		grupoUsuarioAutorizacion.setEmpleado(null);

		return grupoUsuarioAutorizacion;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	
}