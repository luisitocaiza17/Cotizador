package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * The persistent class for the COMENTARIO_SOLICITUD_INSPECCION database table.
 * 
 */
@Entity
@Table(name="COMENTARIO_SOLICITUD_INSPECCION")
@NamedQueries({
	@NamedQuery(name="ComentarioSolicitudInspeccion.buscarTodos", query="SELECT c FROM ComentarioSolicitudInspeccion c"),
	@NamedQuery(name="ComentarioSolicitudInspeccion.buscarPorId", query="SELECT c FROM ComentarioSolicitudInspeccion c where c.id = :id"),
	@NamedQuery(name="ComentarioSolicitudInspeccion.buscarPorSolicitudInspeccion", query="SELECT c FROM ComentarioSolicitudInspeccion c where c.solicitudInspeccion = :solicitudInspeccion")
})
public class ComentarioSolicitudInspeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Lob
	private String descripcion;

	private Timestamp fecha;

	@Column(name="usuario_id")
	private BigInteger usuarioId;

	//bi-directional many-to-one association to SolicitudInspeccion
	@ManyToOne
	@JoinColumn(name="solicitud_inspeccion_id")
	private SolicitudInspeccion solicitudInspeccion;

	public ComentarioSolicitudInspeccion() {
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

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public BigInteger getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	public SolicitudInspeccion getSolicitudInspeccion() {
		return this.solicitudInspeccion;
	}

	public void setSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		this.solicitudInspeccion = solicitudInspeccion;
	}

}