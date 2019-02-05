package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MODULO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Modulo.buscarPorId", query="SELECT m FROM Modulo m WHERE m.id=:id"),
	@NamedQuery(name="Modulo.buscarTodos", query="SELECT m FROM Modulo m"),
	@NamedQuery(name="Modulo.buscarActivos", query="SELECT c FROM Modulo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Modulo.buscarPorNombre", query="SELECT c FROM Modulo c where c.nombre = :nombre")
})
public class Modulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to OpcionMenu
	@OneToMany(mappedBy="modulo")
	private List<OpcionMenu> opcionMenus;

	public Modulo() {
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		opcionMenus.setModulo(this);

		return opcionMenus;
	}

	public OpcionMenu removeOpcionMenus(OpcionMenu opcionMenus) {
		getOpcionMenus().remove(opcionMenus);
		opcionMenus.setModulo(null);

		return opcionMenus;
	}

}