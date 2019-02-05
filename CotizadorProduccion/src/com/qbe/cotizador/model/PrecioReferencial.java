package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRECIO_REFERENCIAL database table.
 * 
 */
@Entity
@Table(name="PRECIO_REFERENCIAL")
@NamedQueries({
	@NamedQuery(name="PrecioReferencial.buscarPorId", query="SELECT c FROM PrecioReferencial c where c.id = :id"),
	@NamedQuery(name="PrecioReferencial.buscarTodos", query="SELECT c FROM PrecioReferencial c")
})	
public class PrecioReferencial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String ano;

	private float precio;

	//bi-directional many-to-one association to Modelo
	@ManyToOne
	private Modelo modelo;

	public PrecioReferencial() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

}