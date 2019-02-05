package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the USUARIO_ROL database table.
 * 
 */
@Entity
@Table(name="USUARIO_ROL")
@NamedQueries({
	@NamedQuery(name="UsuarioRol.buscarPorUsuario", query="SELECT c FROM UsuarioRol c where c.usuario = :usuario"),
	@NamedQuery(name="UsuarioRol.buscarPorId", query="SELECT c FROM UsuarioRol c where c.id = :id")
})
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	private Rol rol;

	public UsuarioRol() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}