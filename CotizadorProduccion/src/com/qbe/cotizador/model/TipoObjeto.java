package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_OBJETO database table.
 * 
 */
@Entity
@Table(name="TIPO_OBJETO")
@NamedQueries({
	@NamedQuery(name="TipoObjeto.buscarPorId", query="SELECT c FROM TipoObjeto c where c.id = :id"),
	@NamedQuery(name="TipoObjeto.buscarTodos", query="SELECT c FROM TipoObjeto c"),
	@NamedQuery(name="TipoObjeto.buscarPorNombre", query="SELECT c FROM TipoObjeto c WHERE c.nombre =:nombre")
})
public class TipoObjeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="tipoObjeto")
	private List<Cotizacion> cotizacions;

	public TipoObjeto() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setTipoObjeto(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setTipoObjeto(null);

		return cotizacion;
	}

}