package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_IDENTIFICACION database table.
 * 
 */
@Entity
@Table(name="TIPO_IDENTIFICACION")
@NamedQueries({
	@NamedQuery(name="TipoIdentificacion.buscarPorId", query="SELECT c FROM TipoIdentificacion c where c.id = :id"),
	@NamedQuery(name="TipoIdentificacion.buscarTodos", query="SELECT c FROM TipoIdentificacion c")
})
public class TipoIdentificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Entidad
	@OneToMany(mappedBy="tipoIdentificacion")
	private List<Entidad> entidads;

	//bi-directional many-to-one association to Entidad
	@OneToMany(mappedBy="tipoIdentificacion")
	private List<Pago> pagos;	
	
	public TipoIdentificacion() {
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

	public List<Entidad> getEntidads() {
		return this.entidads;
	}

	public void setEntidads(List<Entidad> entidads) {
		this.entidads = entidads;
	}

	public Entidad addEntidad(Entidad entidad) {
		getEntidads().add(entidad);
		entidad.setTipoIdentificacion(this);

		return entidad;
	}

	public Entidad removeEntidad(Entidad entidad) {
		getEntidads().remove(entidad);
		entidad.setTipoIdentificacion(null);

		return entidad;
	}
	
	public List<Pago> getPagos() {
		return this.pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public Pago addPago(Pago pago) {
		getPagos().add(pago);
		pago.setTipoIdentificacionId(this);

		return pago;
	}

	public Pago removePago(Pago pago) {
		getPagos().remove(pago);
		pago.setTipoIdentificacionId(null);

		return pago;
	}
}