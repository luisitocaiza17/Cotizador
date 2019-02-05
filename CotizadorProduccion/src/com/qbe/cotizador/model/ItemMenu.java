package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ITEM_MENU database table.
 * 
 */
@Entity
@Table(name="ITEM_MENU")
@NamedQueries({
	@NamedQuery(name="ItemMenu.buscarTodos", query="SELECT c FROM ItemMenu c"),
	@NamedQuery(name="ItemMenu.buscarPorOpcionMenu", query="SELECT c FROM ItemMenu c where c.opcionMenu = :opcionMenu AND c.activo = :activo")
})
public class ItemMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	private String url;

	//bi-directional many-to-one association to OpcionMenu
	@ManyToOne
	@JoinColumn(name="opcion_menu_id")
	private OpcionMenu opcionMenu;

	public ItemMenu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OpcionMenu getOpcionMenu() {
		return this.opcionMenu;
	}

	public void setOpcionMenu(OpcionMenu opcionMenu) {
		this.opcionMenu = opcionMenu;
	}

}