package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the USUARIO_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="USUARIO_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="UsuarioXPuntoVenta.buscarPorId", query="SELECT c FROM UsuarioXPuntoVenta c where c.id = :id"),
	@NamedQuery(name="UsuarioXPuntoVenta.buscarTodos", query="SELECT c FROM UsuarioXPuntoVenta c"),
	@NamedQuery(name="UsuarioXPuntoVenta.buscarPorUsuario", query="SELECT c FROM UsuarioXPuntoVenta c where c.usuario = :usuario")
})
public class UsuarioXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to PuntoVenta
	@ManyToOne
	@JoinColumn(name="punto_venta_id")
	private PuntoVenta puntoVenta;

	public UsuarioXPuntoVenta() {
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

	public PuntoVenta getPuntoVenta() {
		return this.puntoVenta;
	}

	public void setPuntoVenta(PuntoVenta puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

}