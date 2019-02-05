package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OPCION_MENU_PANTALLA_ROL database table.
 * 
 */
@Entity
@Table(name="OPCION_MENU_PANTALLA_ROL")
@NamedQuery(name="OpcionMenuPantallaRol.findAll", query="SELECT o FROM OpcionMenuPantallaRol o")
public class OpcionMenuPantallaRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean activo;

	//bi-directional many-to-one association to ItemMenu
	@ManyToOne
	@JoinColumn(name="item_menu_id")
	private ItemMenu itemMenu;

	//bi-directional many-to-one association to OpcionPantalla
	@ManyToOne
	@JoinColumn(name="opcion_pantalla_id")
	private OpcionPantalla opcionPantalla;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	private Rol rol;

	public OpcionMenuPantallaRol() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public ItemMenu getItemMenu() {
		return this.itemMenu;
	}

	public void setItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

	public OpcionPantalla getOpcionPantalla() {
		return this.opcionPantalla;
	}

	public void setOpcionPantalla(OpcionPantalla opcionPantalla) {
		this.opcionPantalla = opcionPantalla;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}