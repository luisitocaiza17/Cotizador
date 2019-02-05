package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the CREDENCIAL database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Credencial.buscarPorId", query="SELECT c FROM Credencial c WHERE c.id=:id"),
	@NamedQuery(name="Credencial.buscarTodos", query="SELECT c FROM Credencial c"),
	@NamedQuery(name="Credencial.buscarPorUsuarioId", query="SELECT c FROM Credencial c where c.usuario = :usuario")
})
public class Credencial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String clave;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public Credencial() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}