package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the OPCION_MENU database table.
 * 
 */
@Entity
@Table(name="OPCION_MENU")
@NamedQueries({
	@NamedQuery(name="OpcionMenu.buscarTodos", query="SELECT o FROM OpcionMenu o"),
	@NamedQuery(name="OpcionMenu.buscarId", query="SELECT o FROM OpcionMenu o where o.id = :id")
})
public class OpcionMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String icono;

	private String nombre;

	@Column(name="padre_id")
	private String padreId;

	private int posicion;

	private String url;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	//bi-directional many-to-one association to NivelMenu
	@ManyToOne
	@JoinColumn(name="nivel_menu_id")
	private NivelMenu nivelMenu;

	public OpcionMenu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPadreId() {
		return this.padreId;
	}

	public void setPadreId(String padreId) {
		this.padreId = padreId;
	}

	public int getPosicion() {
		return this.posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public NivelMenu getNivelMenu() {
		return this.nivelMenu;
	}

	public void setNivelMenu(NivelMenu nivelMenu) {
		this.nivelMenu = nivelMenu;
	}

}