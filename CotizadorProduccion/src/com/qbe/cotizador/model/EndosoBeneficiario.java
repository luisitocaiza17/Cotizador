package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ENDOSO_BENEFICIARIO database table.
 * 
 */
@Entity
@Table(name="ENDOSO_BENEFICIARIO")
@NamedQueries({
	@NamedQuery(name="EndosoBeneficiario.buscarPorId", query="SELECT c FROM EndosoBeneficiario c where c.id = :id"),
	@NamedQuery(name="EndosoBeneficiario.buscarPorCotizacion", query="SELECT c FROM EndosoBeneficiario c WHERE c.cotizacion =:cotizacion"),
	@NamedQuery(name="EndosoBeneficiario.buscarTodos", query="SELECT c FROM EndosoBeneficiario c")
})
public class EndosoBeneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Cotizacion
	@ManyToOne
	private Cotizacion cotizacion;

	private double monto;

	//bi-directional many-to-one association to Beneficiario
	@ManyToOne
	private Beneficiario beneficiario;

	public EndosoBeneficiario() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Beneficiario getBeneficiario() {
		return this.beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

}