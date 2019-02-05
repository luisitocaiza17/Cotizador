package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the OPCION_PANTALLA database table.
 * 
 */
@Entity
@Table(name="OPCION_PANTALLA")
@NamedQueries({
	@NamedQuery(name="OpcionPantalla.findAll", query="SELECT o FROM OpcionPantalla o"),
	@NamedQuery(name="OpcionPantalla.findId", query="SELECT o FROM OpcionPantalla o where o.id = :id")
})

public class OpcionPantalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String icono;

	private String identificador;

	private String nombre;

	private String url;

	//bi-directional many-to-one association to OpcionMenuPantallaRol
	@OneToMany(mappedBy="opcionPantalla")
	private List<OpcionMenuPantallaRol> opcionMenuPantallaRols;
	
	//bi-directional many-to-one association to OpcionPantallaUsuario
	@OneToMany(mappedBy="opcionPantalla")
	private List<OpcionPantallaUsuario> opcionPantallaUsuarios;

	public OpcionPantalla() {
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

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<OpcionMenuPantallaRol> getOpcionMenuPantallaRols() {
		return this.opcionMenuPantallaRols;
	}

	public void setOpcionMenuPantallaRols(List<OpcionMenuPantallaRol> opcionMenuPantallaRols) {
		this.opcionMenuPantallaRols = opcionMenuPantallaRols;
	}
	
	public List<OpcionPantallaUsuario> getOpcionPantallaUsuarios() {
		return opcionPantallaUsuarios;
	}

	public void setOpcionPantallaUsuarios(
			List<OpcionPantallaUsuario> opcionPantallaUsuarios) {
		this.opcionPantallaUsuarios = opcionPantallaUsuarios;
	}

	public OpcionMenuPantallaRol addOpcionMenuPantallaRol(OpcionMenuPantallaRol opcionMenuPantallaRol) {
		getOpcionMenuPantallaRols().add(opcionMenuPantallaRol);
		opcionMenuPantallaRol.setOpcionPantalla(this);

		return opcionMenuPantallaRol;
	}

	public OpcionMenuPantallaRol removeOpcionMenuPantallaRol(OpcionMenuPantallaRol opcionMenuPantallaRol) {
		getOpcionMenuPantallaRols().remove(opcionMenuPantallaRol);
		opcionMenuPantallaRol.setOpcionPantalla(null);

		return opcionMenuPantallaRol;
	}
	
	public OpcionPantallaUsuario addOpcionPantallaUsuario(OpcionPantallaUsuario opcionPantallaUsuario) {
		getOpcionPantallaUsuarios().add(opcionPantallaUsuario);
		opcionPantallaUsuario.setOpcionPantalla(this);

		return opcionPantallaUsuario;
	}

	public OpcionPantallaUsuario removeOpcionPantallaUsuario(OpcionPantallaUsuario opcionPantallaUsuario) {
		getOpcionPantallaUsuarios().remove(opcionPantallaUsuario);
		opcionPantallaUsuario.setUsuario(null);

		return opcionPantallaUsuario;
	}

}