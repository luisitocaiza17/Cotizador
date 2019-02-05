package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the FORMA_PAGO_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="FORMA_PAGO_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="FormaPagoXPuntoVenta.buscarTodosPV", query="SELECT c FROM FormaPagoXPuntoVenta c where c.puntoVentaId = :puntoVentaId ORDER BY c.formaPagoId ASC"),
	@NamedQuery(name="FormaPagoXPuntoVenta.buscarTodos", query="SELECT c FROM FormaPagoXPuntoVenta c"),
	@NamedQuery(name="FormaPagoXPuntoVenta.buscarId", query="SELECT c FROM FormaPagoXPuntoVenta c where c.id=:id"),
	@NamedQuery(name="FormaPagoXPuntoVenta.obtenerPorPuntoVentaId", query="SELECT c FROM FormaPagoXPuntoVentaVta c where c.puntoVentaId =:id"),
	@NamedQuery(name="FormaPagoXPuntoVenta.buscarFormaPago", query="SELECT c FROM FormaPagoXPuntoVenta c WHERE c.formaPagoId =:formaPagoId AND c.puntoVentaId =:puntoVentaId")	
})
public class FormaPagoXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="forma_pago_id")
	private BigInteger formaPagoId;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;

	public FormaPagoXPuntoVenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFormaPagoId() {
		return this.formaPagoId;
	}

	public void setFormaPagoId(BigInteger formaPagoId) {
		this.formaPagoId = formaPagoId;
	}

	public BigInteger getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

}