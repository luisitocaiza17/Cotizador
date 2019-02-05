package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="PYME_INSPECTOR_PROVINCIA_ASIGNADO")
@NamedQueries({
	@NamedQuery(name="PymeInspectorProvinciaAsignado.buscarTodos", query="SELECT c FROM PymeInspectorProvinciaAsignado c"),
})
public class PymeInspectorProvinciaAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USUARIO_ID")
	private BigInteger usuarioId;
	
	@Column(name="EMITE")
	private int emite;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="NOMBRE_COMPLETO")
	private String nombreCompleto;
	
	@Column(name="ENTIDAD_ID")
	private BigInteger entidadId;
	
	@Column(name="ACTIVO")
	private int activo;

	@Id
	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;
	
	@Id
	@Column(name="CIUDAD_ID")
	private BigInteger ciudadId;
	
	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public BigInteger getUsuarioId() {
		return usuarioId;
	}
	
	public int getEmite() {
		return emite;
	}

	public void setEmite(int emite) {
		this.emite = emite;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public BigInteger getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(BigInteger entidadId) {
		this.entidadId = entidadId;
	}	

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	public BigInteger getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public BigInteger getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(BigInteger ciudadId) {
		this.ciudadId = ciudadId;
	}
}