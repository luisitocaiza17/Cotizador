package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the VIGENCIA_POLIZA_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="VIGENCIA_POLIZA_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="VigenciaPolizaXPuntoVenta.buscarTodos", query="SELECT v FROM VigenciaPolizaXPuntoVenta v"),
	@NamedQuery(name="VigenciaPolizaXPuntoVenta.buscarPorId", query="SELECT c FROM VigenciaPolizaXPuntoVenta c where c.id = :id"),
	@NamedQuery(name="VigenciaPolizaXPuntoVenta.buscarTodosPV", query="SELECT c FROM VigenciaPolizaXPuntoVenta c where c.puntoVentaId = :puntoVentaId ORDER BY c.vigenciaPolizaId ASC"),
	@NamedQuery(name="VigenciaPolizaXPuntoVenta.buscarVigenciaPoliza", query="SELECT c FROM VigenciaPolizaXPuntoVenta c where c.vigenciaPolizaId =:vigenciaPolizaId AND c.puntoVentaId =:puntoVentaId")
})
public class VigenciaPolizaXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;

	@Column(name="vigencia_poliza_id")
	private BigInteger vigenciaPolizaId;

	public VigenciaPolizaXPuntoVenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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