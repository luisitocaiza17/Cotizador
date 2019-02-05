package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_USUARIO_INSPECTOR database table.
 * 
 */
@Entity
@Table(name="PYME_USUARIO_INSPECTOR")
@NamedQueries({
	@NamedQuery(name="PymeUsuarioInspector.buscarTodos", query="SELECT p FROM PymeUsuarioInspector p"),
	@NamedQuery(name="PymeUsuarioInspector.buscarPorId", query="SELECT c FROM PymeUsuarioInspector c where c.id = :id"),
	@NamedQuery(name="PymeUsuarioInspector.buscarPorUsuarioId", query="SELECT c FROM PymeUsuarioInspector c where c.usuarioid = :id"),
	@NamedQuery(name="PymeUsuarioInspector.buscarPorRolId", query="SELECT c FROM PymeUsuarioInspector c where c.rolId=:rolId")
})
public class PymeUsuarioInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	@Column(name="rol_id")
	private BigInteger rolId;

	private BigInteger usuarioid;

	public PymeUsuarioInspector() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public BigInteger getRolId() {
		return this.rolId;
	}

	public void setRolId(BigInteger rolId) {
		this.rolId = rolId;
	}

	public BigInteger getUsuarioid() {
		return this.usuarioid;
	}

	public void setUsuarioid(BigInteger usuarioid) {
		this.usuarioid = usuarioid;
	}

}