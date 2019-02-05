package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the CUOTAS database table.
 * 
 */
@Entity
@Table(name="CUOTAS")
@NamedQueries({
	@NamedQuery(name="Cuota.buscarPorId", query="SELECT c FROM Cuota c where c.id = :id"),
	@NamedQuery(name="Cuota.buscarTodos", query="SELECT c FROM Cuota c"),
	@NamedQuery(name="Cuota.buscarPorPago", query="SELECT c FROM Cuota c where c.pago = :pago")
})		
public class Cuota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pago")
	private Date fechaPago;

	@Column(name="numero_cheque")
	private String numeroCheque;

	private int orden;

	private double valor;

	//bi-directional many-to-one association to Pago
	@ManyToOne
	private Pago pago;

	public Cuota() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getNumeroCheque() {
		return this.numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Pago getPago() {
		return this.pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

}