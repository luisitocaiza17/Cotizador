package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the DESGLOSE_COBERTURA database table.
 * 
 */
@Entity
@Table(name="DESGLOSE_COBERTURA")
@NamedQuery(name="DesgloseCobertura.findAll", query="SELECT d FROM DesgloseCobertura d")
public class DesgloseCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to DetalleDesgloseCobertura
	@OneToMany(mappedBy="desgloseCobertura", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<DetalleDesgloseCobertura> detalleDesgloseCoberturas;

	public DesgloseCobertura() {
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

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public List<DetalleDesgloseCobertura> getDetalleDesgloseCoberturas() {
		return this.detalleDesgloseCoberturas;
	}

	public void setDetalleDesgloseCoberturas(List<DetalleDesgloseCobertura> detalleDesgloseCoberturas) {
		this.detalleDesgloseCoberturas = detalleDesgloseCoberturas;
	}

	public DetalleDesgloseCobertura addDetalleDesgloseCobertura(DetalleDesgloseCobertura detalleDesgloseCobertura) {
		getDetalleDesgloseCoberturas().add(detalleDesgloseCobertura);
		detalleDesgloseCobertura.setDesgloseCobertura(this);

		return detalleDesgloseCobertura;
	}

	public DetalleDesgloseCobertura removeDetalleDesgloseCobertura(DetalleDesgloseCobertura detalleDesgloseCobertura) {
		getDetalleDesgloseCoberturas().remove(detalleDesgloseCobertura);
		detalleDesgloseCobertura.setDesgloseCobertura(null);

		return detalleDesgloseCobertura;
	}

}