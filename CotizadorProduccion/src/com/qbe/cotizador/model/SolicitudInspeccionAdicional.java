package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the SOLICITUD_INSPECCION_ADICIONAL database table.
 * 
 */
@Entity
@Table(name="SOLICITUD_INSPECCION_ADICIONAL")
@NamedQuery(name="SolicitudInspeccionAdicional.findAll", query="SELECT s FROM SolicitudInspeccionAdicional s")
public class SolicitudInspeccionAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="adicional_id")
	private BigInteger adicionalId;

	private String marca;

	private String modelo;

	private int porcentaje;

	@Column(name="solicitud_inspeccion_id")
	private BigInteger solicitudInspeccionId;

	public SolicitudInspeccionAdicional() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getAdicionalId() {
		return this.adicionalId;
	}

	public void setAdicionalId(BigInteger adicionalId) {
		this.adicionalId = adicionalId;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public BigInteger getSolicitudInspeccionId() {
		return this.solicitudInspeccionId;
	}

	public void setSolicitudInspeccionId(BigInteger solicitudInspeccionId) {
		this.solicitudInspeccionId = solicitudInspeccionId;
	}

}