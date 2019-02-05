package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the ARCHIVO_COTIZACION_MASIVOS database table.
 * 
 */
@Entity
@Table(name="ARCHIVO_COTIZACION_MASIVOS")
@NamedQueries({
	@NamedQuery(name="ArchivoCotizacionMasivo.buscarPorId", query="SELECT a FROM ArchivoCotizacionMasivo a WHERE a.id=:id"),
	@NamedQuery(name="ArchivoCotizacionMasivo.buscarRegistrosArchivo", query="SELECT a FROM ArchivoCotizacionMasivo a where a.nombreArchivo=:nombreArchivo"),
	@NamedQuery(name="ArchivoCotizacionMasivo.buscarPorCotizacionId", query="SELECT a FROM ArchivoCotizacionMasivo a WHERE a.cotizacionId=:cotizacionId"),
})
public class ArchivoCotizacionMasivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="CONTENIDO_ARCHIVO")
	private String contenidoArchivo;

	@Column(name="COTIZACION_ID")
	private String cotizacionId;

	private String error;

	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;

	public ArchivoCotizacionMasivo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContenidoArchivo() {
		return this.contenidoArchivo;
	}

	public void setContenidoArchivo(String contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}

	public String getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(String cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNombreArchivo() {
		return this.nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}