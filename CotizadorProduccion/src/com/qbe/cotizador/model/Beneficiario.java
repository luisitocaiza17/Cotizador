package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the BENEFICIARIO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Beneficiario.buscarPorId", query="SELECT c FROM Beneficiario c where c.id = :id"),
	@NamedQuery(name="Beneficiario.buscarTodos", query="SELECT c FROM Beneficiario c"),
	@NamedQuery(name="Beneficiario.buscarActivos", query="SELECT c FROM Beneficiario c WHERE c.activo =:valorActivo")
})
public class Beneficiario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="codigo_ensurance")
	private BigInteger codigoEnsurance;

	private String nombre;

	//bi-directional many-to-one association to EndosoBeneficiario
	@OneToMany(mappedBy="beneficiario")
	private List<EndosoBeneficiario> endosoBeneficiarios;

	public Beneficiario() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public BigInteger getCodigoEnsurance() {
		return this.codigoEnsurance;
	}

	public void setCodigoEnsurance(BigInteger codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<EndosoBeneficiario> getEndosoBeneficiarios() {
		return this.endosoBeneficiarios;
	}

	public void setEndosoBeneficiarios(List<EndosoBeneficiario> endosoBeneficiarios) {
		this.endosoBeneficiarios = endosoBeneficiarios;
	}

	public EndosoBeneficiario addEndosoBeneficiario(EndosoBeneficiario endosoBeneficiario) {
		getEndosoBeneficiarios().add(endosoBeneficiario);
		endosoBeneficiario.setBeneficiario(this);

		return endosoBeneficiario;
	}

	public EndosoBeneficiario removeEndosoBeneficiario(EndosoBeneficiario endosoBeneficiario) {
		getEndosoBeneficiarios().remove(endosoBeneficiario);
		endosoBeneficiario.setBeneficiario(null);

		return endosoBeneficiario;
	}

}