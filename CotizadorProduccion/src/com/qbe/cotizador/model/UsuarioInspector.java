package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USUARIO_INSPECTOR database table.
 * 
 */
@Entity
@Table(name="USUARIO_INSPECTOR")
@NamedQueries({
	@NamedQuery(name="UsuarioInspector.buscarPorUsuario", query="SELECT c FROM UsuarioInspector c WHERE c.usuario=:usuario"),
	@NamedQuery(name="UsuarioInspector.buscarPorId", query="SELECT c FROM UsuarioInspector c where c.id = :id"),
	@NamedQuery(name="UsuarioInspector.buscarTodos", query="SELECT c FROM UsuarioInspector c")
})
public class UsuarioInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean activo;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@ManyToOne
	@JoinColumn(name="inspector_id")
	private Inspector inspector;

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public UsuarioInspector() {
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Inspector getInspector() {
		return inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}