package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the SOLICITUD_INSPECCION_COMPONENTE database table.
 * 
 */
@Entity
@Table(name="SOLICITUD_INSPECCION_COMPONENTE")
@NamedQuery(name="SolicitudInspeccionComponente.findAll", query="SELECT s FROM SolicitudInspeccionComponente s")
public class SolicitudInspeccionComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="componente_id")
	private BigInteger componenteId;

	@Column(name="estado_id")
	private BigInteger estadoId;

	@Column(name="solicitud_inspeccion_id")
	private BigInteger solicitudInspeccionId;

	public SolicitudInspeccionComponente() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getComponenteId() {
		return this.componenteId;
	}

	public void setComponenteId(BigInteger componenteId) {
		this.componenteId = componenteId;
	}

	public BigInteger getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(BigInteger estadoId) {
		this.estadoId = estadoId;
	}

	public BigInteger getSolicitudInspeccionId() {
		return this.solicitudInspeccionId;
	}

	public void setSolicitudInspeccionId(BigInteger solicitudInspeccionId) {
		this.solicitudInspeccionId = solicitudInspeccionId;
	}

}