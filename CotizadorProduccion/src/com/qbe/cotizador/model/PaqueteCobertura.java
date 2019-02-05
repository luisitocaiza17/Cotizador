package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the paquete_cobertura database table.
 * 
 */
@Entity
@Table(name="PAQUETE_COBERTURA")
@NamedQueries({
	@NamedQuery(name="PaqueteCobertura.buscarPaqueteCoberturasPorGrupoPorProductoId", query="SELECT p FROM PaqueteCobertura p WHERE p.grupoPorProducto=:grupoPorProducto"),
	@NamedQuery(name="PaqueteCobertura.buscarPorId", query="SELECT p FROM PaqueteCobertura p WHERE p.id=:id"),
	@NamedQuery(name="PaqueteCobertura.buscarTodos", query="SELECT p FROM PaqueteCobertura p")
})	
	
public class PaqueteCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to Paquete
	@ManyToOne
	private Paquete paquete;

	//bi-directional many-to-one association to GrupoPorProducto
	@ManyToOne
	@JoinColumn(name="grupo_por_producto_id")
	private GrupoPorProducto grupoPorProducto;

	public PaqueteCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public Paquete getPaquete() {
		return this.paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public GrupoPorProducto getGrupoPorProducto() {
		return this.grupoPorProducto;
	}

	public void setGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		this.grupoPorProducto = grupoPorProducto;
	}

}