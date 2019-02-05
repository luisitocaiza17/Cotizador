package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the producto_cobertura database table.
 * 
 */
@Entity
@Table(name="PRODUCTO_COBERTURA")
@NamedQueries({
	@NamedQuery(name ="ProductoCobertura.buscarCoberturasPorGrupoProducto", query ="SELECT c FROM ProductoCobertura c WHERE c.grupoPorProducto = :grupoPorProductoObjeto"),
	@NamedQuery(name ="ProductoCobertura.buscarCoberturasPorGrupoProductoId", query ="SELECT c FROM ProductoCobertura c WHERE c.grupoPorProducto.id = :grupoProducto and c.cobertura.grupoCobertura.ramo.nemotecnico like :ramoNemotecnico"),
	@NamedQuery(name ="ProductoCobertura.buscarCoberturasNemotecnicoGrupoPorProducto", query ="SELECT c FROM ProductoCobertura c WHERE c.cobertura.nombre=:nombre and c.grupoPorProducto=:grupoPorProducto"),
	@NamedQuery(name ="ProductoCobertura.buscarTodos", query ="SELECT c FROM ProductoCobertura c"),
	@NamedQuery(name ="ProductoCobertura.buscarPorId", query ="SELECT c FROM ProductoCobertura c where c.id = :id")
})
public class ProductoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to GrupoPorProducto
	@ManyToOne
	@JoinColumn(name="grupo_por_producto_id")
	private GrupoPorProducto grupoPorProducto;

	public ProductoCobertura() {
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

	public GrupoPorProducto getGrupoPorProducto() {
		return this.grupoPorProducto;
	}

	public void setGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		this.grupoPorProducto = grupoPorProducto;
	}

}