package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the CONJUNTO_COBERTURAS database table.
 * 
 */
@Entity
@Table(name="CONJUNTO_COBERTURAS")
@NamedQueries({
	@NamedQuery(name="ConjuntoCobertura.buscarPorId", query="SELECT c FROM ConjuntoCobertura c where c.id = :id"),
	@NamedQuery(name="ConjuntoCobertura.buscarTodos", query="SELECT c FROM ConjuntoCobertura c"),
	@NamedQuery(name="ConjuntoCobertura.eliminarTodos", query="delete from ConjuntoCobertura c")
})
public class ConjuntoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String nombre;

	private String orden;

	//bi-directional many-to-one association to CoberturasConjunto
	@OneToMany(mappedBy="conjuntoCobertura")
	private List<CoberturasConjunto> coberturasConjuntos;

	//bi-directional many-to-one association to ConfiguracionProducto
	@ManyToOne
	@JoinColumn(name="configu_producto_id")
	private ConfiguracionProducto configuracionProducto;

	public ConjuntoCobertura() {
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

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public List<CoberturasConjunto> getCoberturasConjuntos() {
		return this.coberturasConjuntos;
	}

	public void setCoberturasConjuntos(List<CoberturasConjunto> coberturasConjuntos) {
		this.coberturasConjuntos = coberturasConjuntos;
	}

	public CoberturasConjunto addCoberturasConjunto(CoberturasConjunto coberturasConjunto) {
		getCoberturasConjuntos().add(coberturasConjunto);
		coberturasConjunto.setConjuntoCobertura(this);

		return coberturasConjunto;
	}

	public CoberturasConjunto removeCoberturasConjunto(CoberturasConjunto coberturasConjunto) {
		getCoberturasConjuntos().remove(coberturasConjunto);
		coberturasConjunto.setConjuntoCobertura(null);

		return coberturasConjunto;
	}

	public ConfiguracionProducto getConfiguracionProducto() {
		return this.configuracionProducto;
	}

	public void setConfiguracionProducto(ConfiguracionProducto configuracionProducto) {
		this.configuracionProducto = configuracionProducto;
	}

}