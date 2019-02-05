package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the INSTITUCION_FINANCIERA database table.
 * 
 */
@Entity
@Table(name="INSTITUCION_FINANCIERA")
@NamedQueries({
	@NamedQuery(name="InstitucionFinanciera.buscarPorId", query="SELECT c FROM InstitucionFinanciera c where c.id = :id"),
	@NamedQuery(name="InstitucionFinanciera.buscarTodos", query="SELECT c FROM InstitucionFinanciera c"),
	@NamedQuery(name="InstitucionFinanciera.buscarInstitucionFinancierasDebito", query="SELECT c FROM InstitucionFinanciera c WHERE c.esDebito = '1'"),
	@NamedQuery(name="InstitucionFinanciera.buscarActivos", query="SELECT c FROM InstitucionFinanciera c WHERE c.activo =:valorActivo"),
})
public class InstitucionFinanciera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_ensurance")
	private BigInteger codigoEnsurance;

	@Column(name="convenio_id")
	private String convenioId;

	@Column(name="doce_meses")
	private float doceMeses;

	@Column(name="es_debito")
	private boolean esDebito;

	@Column(name="activo")
	private boolean activo;

	@Column(name="es_tarjeta_credito")
	private boolean esTarjetaCredito;

	private String nombre;

	@Column(name="nombre_nemotecnico")
	private String nombreNemotecnico;

	@Column(name="nueve_meses")
	private float nueveMeses;

	@Column(name="seis_meses")
	private float seisMeses;

	@Column(name="tres_meses")
	private float tresMeses;

	public InstitucionFinanciera() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getCodigoEnsurance() {
		return this.codigoEnsurance;
	}

	public void setCodigoEnsurance(BigInteger codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getConvenioId() {
		return this.convenioId;
	}

	public void setConvenioId(String convenioId) {
		this.convenioId = convenioId;
	}

	public float getDoceMeses() {
		return this.doceMeses;
	}

	public void setDoceMeses(float doceMeses) {
		this.doceMeses = doceMeses;
	}

	public boolean getEsDebito() {
		return this.esDebito;
	}

	public void setEsDebito(boolean esDebito) {
		this.esDebito = esDebito;
	}

	public boolean getEsTarjetaCredito() {
		return this.esTarjetaCredito;
	}

	public void setEsTarjetaCredito(boolean esTarjetaCredito) {
		this.esTarjetaCredito = esTarjetaCredito;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreNemotecnico() {
		return this.nombreNemotecnico;
	}

	public void setNombreNemotecnico(String nombreNemotecnico) {
		this.nombreNemotecnico = nombreNemotecnico;
	}

	public float getNueveMeses() {
		return this.nueveMeses;
	}

	public void setNueveMeses(float nueveMeses) {
		this.nueveMeses = nueveMeses;
	}

	public float getSeisMeses() {
		return this.seisMeses;
	}

	public void setSeisMeses(float seisMeses) {
		this.seisMeses = seisMeses;
	}

	public float getTresMeses() {
		return this.tresMeses;
	}

	public void setTresMeses(float tresMeses) {
		this.tresMeses = tresMeses;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}