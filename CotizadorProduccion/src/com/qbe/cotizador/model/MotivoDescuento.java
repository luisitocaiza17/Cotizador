package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MOTIVO_DESCUENTO database table.
 * 
 */
@Entity
@Table(name="MOTIVO_DESCUENTO")
@NamedQueries({
	@NamedQuery(name="MotivoDescuento.buscarPorId", query="SELECT c FROM MotivoDescuento c where c.id =:id"),
	@NamedQuery(name="MotivoDescuento.buscarPorNombre", query="SELECT c FROM MotivoDescuento c WHERE c.nombre =:nombre"),
	@NamedQuery(name="MotivoDescuento.buscarActivos", query="SELECT c FROM MotivoDescuento c WHERE c.activo=:valorActivo"),
	@NamedQuery(name="MotivoDescuento.buscarTodos", query="SELECT c FROM MotivoDescuento c")
})
public class MotivoDescuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	//bi-directional many-to-one association to SolicitudDescuento
	@OneToMany(mappedBy="motivoDescuento")
	private List<SolicitudDescuento> solicitudDescuentos;

	public MotivoDescuento() {
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

	public List<SolicitudDescuento> getSolicitudDescuentos() {
		return this.solicitudDescuentos;
	}

	public void setSolicitudDescuentos(List<SolicitudDescuento> solicitudDescuentos) {
		this.solicitudDescuentos = solicitudDescuentos;
	}

	public SolicitudDescuento addSolicitudDescuento(SolicitudDescuento solicitudDescuento) {
		getSolicitudDescuentos().add(solicitudDescuento);
		solicitudDescuento.setMotivoDescuento(this);

		return solicitudDescuento;
	}

	public SolicitudDescuento removeSolicitudDescuento(SolicitudDescuento solicitudDescuento) {
		getSolicitudDescuentos().remove(solicitudDescuento);
		solicitudDescuento.setMotivoDescuento(null);

		return solicitudDescuento;
	}

}