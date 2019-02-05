package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OPCION_PANTALLA database table.
 * 
 */
@Entity
@Table(name="OPCION_PANTALLA_USUARIO")
@NamedQueries({
	@NamedQuery(name="OpcionPantallaUsuario.buscarOpcionesPantallaUsuario", query="SELECT c FROM OpcionPantallaUsuario c where c.usuario=:usuario")
})
public class OpcionPantallaUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	//bi-directional many-to-one association to OpcionPantalla
	@ManyToOne
	@JoinColumn(name="opcion_pantalla_id")
	private OpcionPantalla opcionPantalla;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
		
	public OpcionPantallaUsuario() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public OpcionPantalla getOpcionPantalla() {
		return opcionPantalla;
	}

	public void setOpcionPantalla(OpcionPantalla opcionPantalla) {
		this.opcionPantalla = opcionPantalla;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
}