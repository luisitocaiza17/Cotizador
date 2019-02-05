package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the plan database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Plan.buscarPorId", query="SELECT c FROM Plan c where c.id = :id"),
	@NamedQuery(name="Plan.buscarTodos", query="SELECT c FROM Plan c"),
	@NamedQuery(name="Plan.buscarPorNombre", query="SELECT c FROM Plan c where c.nombre = :nombre")
})
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String descripcion;

	private String nombre;

	private String sigla;

	//bi-directional many-to-one association to DetalleProducto
	@OneToMany(mappedBy="plan")
	private List<DetalleProducto> detalleProductos;

	//bi-directional many-to-one association to ProductoXPuntoVenta
	@OneToMany(mappedBy="plan")
	private List<ProductoXPuntoVenta> productoXPuntoVentas;

	public Plan() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<DetalleProducto> getDetalleProductos() {
		return this.detalleProductos;
	}

	public void setDetalleProductos(List<DetalleProducto> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}

	public DetalleProducto addDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().add(detalleProducto);
		detalleProducto.setPlan(this);

		return detalleProducto;
	}

	public DetalleProducto removeDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().remove(detalleProducto);
		detalleProducto.setPlan(null);

		return detalleProducto;
	}

	public List<ProductoXPuntoVenta> getProductoXPuntoVentas() {
		return this.productoXPuntoVentas;
	}

	public void setProductoXPuntoVentas(List<ProductoXPuntoVenta> productoXPuntoVentas) {
		this.productoXPuntoVentas = productoXPuntoVentas;
	}

	public ProductoXPuntoVenta addProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().add(productoXPuntoVenta);
		productoXPuntoVenta.setPlan(this);

		return productoXPuntoVenta;
	}

	public ProductoXPuntoVenta removeProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().remove(productoXPuntoVenta);
		productoXPuntoVenta.setPlan(null);

		return productoXPuntoVenta;
	}

}