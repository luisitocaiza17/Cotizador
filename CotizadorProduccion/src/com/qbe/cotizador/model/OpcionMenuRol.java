package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OPCION_MENU_ROL database table.
 * 
 */
@Entity
@Table(name="OPCION_MENU_ROL")
@NamedQuery(name="OpcionMenuRol.findAll", query="SELECT o FROM OpcionMenuRol o")
public class OpcionMenuRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to TipoRolModulo
	@ManyToOne
	@JoinColumn(name="tipo_rol_modulo_id")
	private TipoRolModulo tipoRolModulo;

	//bi-directional many-to-one association to OpcionMenu
	@ManyToOne
	@JoinColumn(name="opcion_menu_id")
	private OpcionMenu opcionMenu;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "rol_id")
	private Rol rol;

	public OpcionMenuRol() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoRolModulo getTipoRolModulo() {
		return this.tipoRolModulo;
	}

	public void setTipoRolModulo(TipoRolModulo tipoRolModulo) {
		this.tipoRolModulo = tipoRolModulo;
	}

	public OpcionMenu getOpcionMenu() {
		return this.opcionMenu;
	}

	public void setOpcionMenu(OpcionMenu opcionMenu) {
		this.opcionMenu = opcionMenu;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}