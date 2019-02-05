package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the SOLICITUD_EMISION database table.
 * 
 */
@Entity
@Table(name="SOLICITUD_EMISION")
@NamedQueries({
	@NamedQuery(name="SolicitudEmision.buscarPorId", query="SELECT c FROM SolicitudEmision c where c.id = :id"),
	@NamedQuery(name="SolicitudEmision.buscarTodos", query="SELECT s FROM SolicitudEmision s"),
	@NamedQuery(name="SolicitudEmision.buscarPorCotizacionId", query="SELECT c FROM SolicitudEmision c where c.cotizacionId = :cotizacionId")
})
public class SolicitudEmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="cotizacion_id")
	private String cotizacionId;

	private Timestamp fecha;

	@Lob
	private String respuesta;

	@Lob
	private String xml;

	public SolicitudEmision() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(String cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

}