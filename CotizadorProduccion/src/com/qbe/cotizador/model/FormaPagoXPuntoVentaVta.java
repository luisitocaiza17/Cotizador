package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the FORMA_PAGO_X_PUNTO_VENTA_VTA database table.
 * 
 */
@Entity
@Table(name="FORMA_PAGO_X_PUNTO_VENTA_VTA")
@NamedQuery(name="FormaPagoXPuntoVentaVta.findAll", query="SELECT f FROM FormaPagoXPuntoVentaVta f")
public class FormaPagoXPuntoVentaVta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="forma_pago_id")
	private BigInteger formaPagoId;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nombre;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;

	public FormaPagoXPuntoVentaVta() {
	}

	public BigInteger getFormaPagoId() {
		return this.formaPagoId;
	}

	public void setFormaPagoId(BigInteger formaPagoId) {
		this.formaPagoId = formaPagoId;
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

}