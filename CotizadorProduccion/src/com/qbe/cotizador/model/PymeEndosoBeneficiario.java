package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the ENDOSO_BENEFICIARIO database table.
 * 
 */
@Entity
@Table(name="PYME_ENDOSO_BENEFICIARIO")
@NamedQueries({
	@NamedQuery(name="PymeEndosoBeneficiario.buscarPorId", query="SELECT c FROM PymeEndosoBeneficiario c where c.id = :id"),
	@NamedQuery(name="PymeEndosoBeneficiario.buscarPorCotizacion", query="SELECT c FROM PymeEndosoBeneficiario c WHERE c.cotizacionId =:cotizacionId"),
	@NamedQuery(name="PymeEndosoBeneficiario.buscarTodos", query="SELECT c FROM PymeEndosoBeneficiario c")
})
public class PymeEndosoBeneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name="COTIZACION_ID")
	private BigInteger cotizacionId;
	
	@Column(name="BENEFICIARIO_ID")
	private BigInteger beneficiarioId;
	
	@Column(name="RUBRO")
	private int rubro;

	@Column(name="MONTO")
	private double monto;


	public void setId(BigInteger id) {
		this.id = id;
	}


	public BigInteger getCotizacionId() {
		return cotizacionId;
	}


	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}


	public BigInteger getBeneficiarioId() {
		return beneficiarioId;
	}


	public void setBeneficiarioId(BigInteger beneficiarioId) {
		this.beneficiarioId = beneficiarioId;
	}


	public int getRubro() {
		return rubro;
	}


	public void setRubro(int rubro) {
		this.rubro = rubro;
	}


	public double getMonto() {
		return monto;
	}


	public void setMonto(double monto) {
		this.monto = monto;
	}


	public BigInteger getId() {
		return id;
	}

	

}