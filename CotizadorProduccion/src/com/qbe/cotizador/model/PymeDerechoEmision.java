package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="PYME_DERECHO_EMISION")
@NamedQueries({
	@NamedQuery(name="PymeDerechoEmision.buscarTodos", query="SELECT p FROM PymeDerechoEmision p"),
	@NamedQuery(name="PymeDerechoEmision.buscarPorId", query="SELECT p FROM PymeDerechoEmision p where p.derechoEmisionId=:Id"),
	@NamedQuery(name="PymeDerechoEmision.buscarIntervalo", query="SELECT p FROM PymeDerechoEmision p where :valorPrima between p.valorPrimaInicial and p.valorPrimaFinal"),
})
public class PymeDerechoEmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DERECHO_EMISION_ID")
	private BigInteger derechoEmisionId;

	@Column(name="VALOR_PRIMA_INICIAL")
	private double valorPrimaInicial;

	@Column(name="VALOR_PRIMA_FINAL")
	private double valorPrimaFinal;

	@Column(name="VALOR_DERECHO_EMISION")
	private double valorDerechoEmision;

	public BigInteger getDerechoEmisionId() {
		return derechoEmisionId;
	}

	public void setDerechoEmisionId(BigInteger derechoEmisionId) {
		this.derechoEmisionId = derechoEmisionId;
	}

	public double getValorPrimaInicial() {
		return valorPrimaInicial;
	}

	public void setValorPrimaInicial(double valorPrimaInicial) {
		this.valorPrimaInicial = valorPrimaInicial;
	}

	public double getValorPrimaFinal() {
		return valorPrimaFinal;
	}

	public void setValorPrimaFinal(double valorPrimaFinal) {
		this.valorPrimaFinal = valorPrimaFinal;
	}

	public double getValorDerechoEmision() {
		return valorDerechoEmision;
	}

	public void setValorDerechoEmision(double valorDerechoEmision) {
		this.valorDerechoEmision = valorDerechoEmision;
	}
}