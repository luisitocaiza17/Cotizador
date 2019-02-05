package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the RAZA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Raza.buscarTodos", query="SELECT r FROM Raza r"),
	@NamedQuery(name="Raza.buscarPorId", query="SELECT r FROM Raza r WHERE r.id =:id")
})
public class Raza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String especieid;

	@Temporal(TemporalType.DATE)
	private Date fechaactualiza;

	private String nombre;

	private String usuarioactualiza;

	public Raza() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEspecieid() {
		return this.especieid;
	}

	public void setEspecieid(String especieid) {
		this.especieid = especieid;
	}

	public Date getFechaactualiza() {
		return this.fechaactualiza;
	}

	public void setFechaactualiza(Date fechaactualiza) {
		this.fechaactualiza = fechaactualiza;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioactualiza() {
		return this.usuarioactualiza;
	}

	public void setUsuarioactualiza(String usuarioactualiza) {
		this.usuarioactualiza = usuarioactualiza;
	}

}