package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_TASA database table.
 * 
 */
@Entity
@Table(name="TIPO_TASA")
@NamedQueries({
	@NamedQuery(name="TipoTasa.buscarPorId", query="SELECT c FROM TipoTasa c where c.id=:id"),
	@NamedQuery(name="TipoTasa.buscarTodos", query="SELECT c FROM TipoTasa c")
})
public class TipoTasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Cobertura
	@OneToMany(mappedBy="tipoTasa")
	private List<Cobertura> coberturas;

	public TipoTasa() {
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
		cobertura.setTipoTasa(this);

		return cobertura;
	}

	public Cobertura removeCobertura(Cobertura cobertura) {
		getCoberturas().remove(cobertura);
		cobertura.setTipoTasa(null);

		return cobertura;
	}

}