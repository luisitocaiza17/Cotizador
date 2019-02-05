package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the GRUPO_COBERTURA database table.
 * 
 */
@Entity
@Table(name="GRUPO_COBERTURA")
@NamedQueries({
	@NamedQuery(name="GrupoCobertura.buscarPorId", query="SELECT c FROM GrupoCobertura c where c.id = :id"),
	@NamedQuery(name="GrupoCobertura.buscarTodos", query="SELECT c FROM GrupoCobertura c")
})
public class GrupoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String codcontable;

	private String cuentapolizatotal;

	private String nombre;

	@Column(name="nombre_nemotecnico")
	private String nombreNemotecnico;

	private String orden;

	private double sumaaltotal;

	//bi-directional many-to-one association to Cobertura
	@OneToMany(mappedBy="grupoCobertura")
	private List<Cobertura> coberturas;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	private Ramo ramo;

	public GrupoCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodcontable() {
		return this.codcontable;
	}

	public void setCodcontable(String codcontable) {
		this.codcontable = codcontable;
	}

	public String getCuentapolizatotal() {
		return this.cuentapolizatotal;
	}

	public void setCuentapolizatotal(String cuentapolizatotal) {
		this.cuentapolizatotal = cuentapolizatotal;
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

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public double getSumaaltotal() {
		return this.sumaaltotal;
	}

	public void setSumaaltotal(double sumaaltotal) {
		this.sumaaltotal = sumaaltotal;
	}

	public List<Cobertura> getCoberturas() {
		return this.coberturas;
	}

	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}

	public Cobertura addCobertura(Cobertura cobertura) {
		getCoberturas().add(cobertura);
		cobertura.setGrupoCobertura(this);

		return cobertura;
	}

	public Cobertura removeCobertura(Cobertura cobertura) {
		getCoberturas().remove(cobertura);
		cobertura.setGrupoCobertura(null);

		return cobertura;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}