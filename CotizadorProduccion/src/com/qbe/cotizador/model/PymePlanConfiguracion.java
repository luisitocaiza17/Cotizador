package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PYME_EXT_RAMO database table.
 * 
 */
@Entity
@Table(name="PYME_PLAN_CONFIGURACION")
@NamedQueries({
	@NamedQuery(name="PymePlanConfiguracion.buscarTodos", query="SELECT p FROM PymePlanConfiguracion p"),
	@NamedQuery(name="PymePlanConfiguracion.buscarPorId", query="SELECT p FROM PymePlanConfiguracion p where p.planConfiguracionId = :planConfiguracionId"),
	@NamedQuery(name="PymePlanConfiguracion.buscarPorConfiguracionId", query="SELECT p FROM PymePlanConfiguracion p where p.configuracionCoberturaId = :configuracionCoberturaId")
})
public class PymePlanConfiguracion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PLAN_CONFIGURACION_ID")
	private BigInteger planConfiguracionId;

	@Column(name="PLAN_ID")
	private BigInteger planId;

	@Column(name="VALOR_MAXIMO")
	private double valorMaximo;
	
	@Column(name="CONFIGURACION_COBERTURA_ID")
	private BigInteger configuracionCoberturaId;

	public BigInteger getPlanConfiguracionId() {
		return planConfiguracionId;
	}

	public void setPlanConfiguracionId(BigInteger planConfiguracionId) {
		this.planConfiguracionId = planConfiguracionId;
	}

	public BigInteger getPlanId() {
		return planId;
	}

	public void setPlanId(BigInteger planId) {
		this.planId = planId;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigInteger getConfiguracionCoberturaId() {
		return configuracionCoberturaId;
	}

	public void setConfiguracionCoberturaId(BigInteger configuracionCoberturaId) {
		this.configuracionCoberturaId = configuracionCoberturaId;
	}
}