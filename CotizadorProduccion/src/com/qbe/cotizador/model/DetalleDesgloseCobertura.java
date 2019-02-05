package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DETALLE_DESGLOSE_COBERTURA database table.
 * 
 */
@Entity
@Table(name="DETALLE_DESGLOSE_COBERTURA")
@NamedQuery(name="DetalleDesgloseCobertura.findAll", query="SELECT d FROM DetalleDesgloseCobertura d")
public class DetalleDesgloseCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private double valor;

	//bi-directional many-to-one association to DesgloseCobertura
	@ManyToOne
	@JoinColumn(name="desgloce_cobertura_id")
	private DesgloseCobertura desgloseCobertura;

	//bi-directional many-to-one association to CotizacionCobertura
	@ManyToOne
	@JoinColumn(name="cotizacion_cobertura_id")
	private CotizacionCobertura cotizacionCobertura;

	public DetalleDesgloseCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public DesgloseCobertura getDesgloseCobertura() {
		return this.desgloseCobertura;
	}

	public void setDesgloseCobertura(DesgloseCobertura desgloseCobertura) {
		this.desgloseCobertura = desgloseCobertura;
	}

	public CotizacionCobertura getCotizacionCobertura() {
		return this.cotizacionCobertura;
	}

	public void setCotizacionCobertura(CotizacionCobertura cotizacionCobertura) {
		this.cotizacionCobertura = cotizacionCobertura;
	}

}