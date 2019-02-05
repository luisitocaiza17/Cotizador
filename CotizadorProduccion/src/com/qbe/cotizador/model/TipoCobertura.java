package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_COBERTURA database table.
 * 
 */
@Entity
@Table(name="TIPO_COBERTURA")
@NamedQueries({
	@NamedQuery(name="TipoCobertura.buscarPorId", query="SELECT c FROM TipoCobertura c where c.id=:id"),
	@NamedQuery(name="TipoCobertura.buscarTodos", query="SELECT c FROM TipoCobertura c")
})
public class TipoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Cobertura
	@OneToMany(mappedBy="tipoCobertura")
	private List<Cobertura> coberturas;

	public TipoCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cobertura> getCoberturas() {
		return this.coberturas;
	}

	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}

	public Cobertura addCobertura(Cobertura cobertura) {
		getCoberturas().add(cobertura);
		cobertura.setTipoCobertura(this);

		return cobertura;
	}

	public Cobertura removeCobertura(Cobertura cobertura) {
		getCoberturas().remove(cobertura);
		cobertura.setTipoCobertura(null);

		return cobertura;
	}

}