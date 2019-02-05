package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ARCHIVO_SOLICITUD_INSPECCION database table.
 * 
 */
@Entity
@Table(name="ARCHIVO_SOLICITUD_INSPECCION")
@NamedQueries({
	@NamedQuery(name="ArchivoSolicitudInspeccion.buscarPorId", query="SELECT c FROM ArchivoSolicitudInspeccion c where c.id = :id"),
	@NamedQuery(name="ArchivoSolicitudInspeccion.buscarTodos", query="SELECT c FROM ArchivoSolicitudInspeccion c"),
	@NamedQuery(name="ArchivoSolicitudInspeccion.buscarPorSolicitudInspeccion", query="SELECT c FROM ArchivoSolicitudInspeccion c where c.solicitudInspeccion = :solicitudInspeccion")
})
public class ArchivoSolicitudInspeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Lob
	@Column(name="contenido_archivo")
	private byte[] contenidoArchivo;

	@Column(name="nombre_archivo")
	private String nombreArchivo;

	//bi-directional many-to-one association to SolicitudInspeccion
	@ManyToOne
	@JoinColumn(name="solicitud_inspeccion_id")
	private SolicitudInspeccion solicitudInspeccion;

	public ArchivoSolicitudInspeccion() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getContenidoArchivo() {
		return this.contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public SolicitudInspeccion getSolicitudInspeccion() {
		return this.solicitudInspeccion;
	}

	public void setSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		this.solicitudInspeccion = solicitudInspeccion;
	}

}