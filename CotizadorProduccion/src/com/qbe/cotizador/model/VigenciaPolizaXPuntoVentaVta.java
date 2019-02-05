package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the VIGENCIA_POLIZA_X_PUNTO_VENTA_VTA database table.
 * 
 */
@Entity
@Table(name="VIGENCIA_POLIZA_X_PUNTO_VENTA_VTA")
@NamedQueries({
	@NamedQuery(name="VigenciaPolizaXPuntoVentaVta.obtenerPorPuntoVentaId", query="SELECT c FROM VigenciaPolizaXPuntoVentaVta c where c.puntoVentaId = :id")
})
public class VigenciaPolizaXPuntoVentaVta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;

	@Column(name="vigencia_poliza_id")
	private BigInteger vigenciaPolizaId;

	public VigenciaPolizaXPuntoVentaVta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public BigInteger getVigenciaPolizaId() {
		return this.vigenciaPolizaId;
	}

	public void setVigenciaPolizaId(BigInteger vigenciaPolizaId) {
		this.vigenciaPolizaId = vigenciaPolizaId;
	}

}