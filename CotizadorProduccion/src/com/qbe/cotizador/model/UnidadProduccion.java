package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the UNIDAD_PRODUCCION database table.
 * 
 */
@Entity
@Table(name="UNIDAD_PRODUCCION")
@NamedQueries({
	@NamedQuery(name="UnidadProduccion.buscarPorId", query="SELECT c FROM UnidadProduccion c where c.id = :id"),
	@NamedQuery(name="UnidadProduccion.buscarPorIdEnsurance", query="SELECT c FROM UnidadProduccion c where c.upEnsurance = :upEnsurance"),
	@NamedQuery(name="UnidadProduccion.buscarActivos", query="SELECT c FROM UnidadProduccion c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="UnidadProduccion.buscarPorNombre", query="SELECT c FROM UnidadProduccion c WHERE c.nombre =:nombre"),
	@NamedQuery(name="UnidadProduccion.buscarTodos", query="SELECT c FROM UnidadProduccion c"),
	@NamedQuery(name="UnidadProduccion.buscarPorUnidadNegocio", query="SELECT c FROM UnidadProduccion c where c.unidadNegocio=:unidadNegocio")
})
public class UnidadProduccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	@Column(name="up_ensurance")
	private String upEnsurance;

	@Column(name="entidad_id")
	private String entidadId;

	//bi-directional many-to-one association to ProductoXPuntoVenta
	@OneToMany(mappedBy="unidadProduccion")
	private List<ProductoXPuntoVenta> productoXPuntoVentas;

	//bi-directional many-to-one association to UnidadNegocio
	@ManyToOne
	@JoinColumn(name="unidad_negocio_id")
	private UnidadNegocio unidadNegocio;

	public UnidadProduccion() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUpEnsurance() {
		return this.upEnsurance;
	}

	public void setUpEnsurance(String upEnsurance) {
		this.upEnsurance = upEnsurance;
	}

	public List<ProductoXPuntoVenta> getProductoXPuntoVentas() {
		return this.productoXPuntoVentas;
	}

	public void setProductoXPuntoVentas(List<ProductoXPuntoVenta> productoXPuntoVentas) {
		this.productoXPuntoVentas = productoXPuntoVentas;
	}

	public ProductoXPuntoVenta addProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().add(productoXPuntoVenta);
		productoXPuntoVenta.setUnidadProduccion(this);

		return productoXPuntoVenta;
	}

	public ProductoXPuntoVenta removeProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().remove(productoXPuntoVenta);
		productoXPuntoVenta.setUnidadProduccion(null);

		return productoXPuntoVenta;
	}

	public UnidadNegocio getUnidadNegocio() {
		return this.unidadNegocio;
	}

	public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	
	public String getEntidadId() {
		return this.entidadId;
	}

	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}

}