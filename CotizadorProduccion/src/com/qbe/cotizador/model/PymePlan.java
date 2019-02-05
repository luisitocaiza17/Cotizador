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
@Table(name="PYME_PLAN")
@NamedQueries({
	@NamedQuery(name="PymePlan.buscarTodos", query="SELECT p FROM PymePlan p"),
	@NamedQuery(name="PymePlan.buscarPorId", query="SELECT p FROM PymePlan p where p.planId = :planId")
})
public class PymePlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PLAN_ID")
	private BigInteger planId;

	@Column(name="NOMBRE")
	private String nombre;

	public BigInteger getPlanId() {
		return planId;
	}

	public void setPlanId(BigInteger planId) {
		this.planId = planId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}