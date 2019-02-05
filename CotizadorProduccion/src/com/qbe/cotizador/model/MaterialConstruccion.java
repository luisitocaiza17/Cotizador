package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;


/**
 * The persistent class for the MATERIAL_CONSTRUCCION database table.
 * 
 */
@Entity
@Table(name="MATERIAL_CONSTRUCCION")
@NamedQueries({
	@NamedQuery(name="MaterialConstruccion.buscarPorId", query="SELECT c FROM MaterialConstruccion c where c.id = :id"),
	@NamedQuery(name="MaterialConstruccion.buscarTodos", query="SELECT c FROM MaterialConstruccion c"),
})
public class MaterialConstruccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;
	
	private String esseleccionable;
	
	private String usuarioactualiza;
	
	private Date fechaactualiza;
	
	private String nemotecnico;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEsseleccionable() {
		return esseleccionable;
	}

	public void setEsseleccionable(String esseleccionable) {
		this.esseleccionable = esseleccionable;
	}

	public String getUsuarioactualiza() {
		return usuarioactualiza;
	}

	public void setUsuarioactualiza(String usuarioactualiza) {
		this.usuarioactualiza = usuarioactualiza;
	}

	public Date getFechaactualiza() {
		return fechaactualiza;
	}

	public void setFechaactualiza(Date fechaactualiza) {
		this.fechaactualiza = fechaactualiza;
	}

	public String getNemotecnico() {
		return nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}
}