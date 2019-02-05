package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the NIVEL_MENU database table.
 * 
 */
@Entity
@Table(name="NIVEL_MENU")
@NamedQueries({
	@NamedQuery(name="NivelMenu.buscarPorId", query="SELECT n FROM NivelMenu n WHERE n.id=:id"),
	@NamedQuery(name="NivelMenu.buscarTodos", query="SELECT n FROM NivelMenu n")
})
public class NivelMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to OpcionMenu
	@OneToMany(mappedBy="nivelMenu")
	private List<OpcionMenu> opcionMenus;

	public NivelMenu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<OpcionMenu> getOpcionMenus() {
		return this.opcionMenus;
	}

	public void setOpcionMenus(List<OpcionMenu> opcionMenus) {
		this.opcionMenus = opcionMenus;
	}

	public OpcionMenu addOpcionMenus(OpcionMenu opcionMenus) {
		getOpcionMenus().add(opcionMenus);
		opcionMenus.setNivelMenu(this);

		return opcionMenus;
	}

	public OpcionMenu removeOpcionMenus(OpcionMenu opcionMenus) {
		getOpcionMenus().remove(opcionMenus);
		opcionMenus.setNivelMenu(null);

		return opcionMenus;
	}

}