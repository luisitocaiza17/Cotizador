package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="PYME_DISTRIBUCION_COBERTURA")
@NamedQueries({
	@NamedQuery(name="PymeDistribucionCobertura.buscarTodos", query="SELECT p FROM PymeDistribucionCobertura p"),
	@NamedQuery(name="PymeDistribucionCobertura.buscarPorId", query="SELECT c FROM PymeDistribucionCobertura c where c.distribucionCoberturaId = :distribucionCoberturaId"),
	@NamedQuery(name="PymeDistribucionCobertura.buscarPorCoberturaId", query="SELECT c FROM PymeDistribucionCobertura c where c.coberturaPymesId = :coberturaPymesId")
})
public class PymeDistribucionCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DISTRIBUCION_COBERTURA_ID")
	private BigInteger distribucionCoberturaId;

	@Column(name="COBERTURA_PYMES_ID")
	private BigInteger coberturaPymesId;

	private double porcentaje;

	public PymeDistribucionCobertura() {
	}

	public BigInteger getDistribucionCoberturaId() {
		return distribucionCoberturaId;
	}

	public void setDistribucionCoberturaId(BigInteger distribucionCoberturaId) {
		this.distribucionCoberturaId = distribucionCoberturaId;
	}

	public BigInteger getCoberturaPymesId() {
		return coberturaPymesId;
	}

	public void setCoberturaPymesId(BigInteger coberturaPymesId) {
		this.coberturaPymesId = coberturaPymesId;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
}