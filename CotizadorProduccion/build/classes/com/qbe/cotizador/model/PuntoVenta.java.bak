package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="PuntoVenta.buscarPorId", query="SELECT c FROM PuntoVenta c where c.id=:id"),
	@NamedQuery(name="PuntoVenta.buscarPorNombre", query="SELECT c FROM PuntoVenta c where upper(c.nombre) = :nombre"),
	@NamedQuery(name="PuntoVenta.buscarActivos", query="SELECT c FROM PuntoVenta c WHERE c.activo =:valorActivo  ORDER BY c.nombre"),
	@NamedQuery(name="PuntoVenta.buscarTodos", query="SELECT c FROM PuntoVenta c"),
	@NamedQuery(name="PuntoVenta.buscarPtosEnsurance", query="SELECT DISTINCT c.ptoEnsurance FROM PuntoVenta c")
})
public class PuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="agente_id")
	private String agenteId;

	private String descripcion;

	private String nombre;

	@Column(name="pto_ensurance")
	private String ptoEnsurance;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="puntoVenta")
	private List<Cotizacion> cotizacions;

	//bi-directional many-to-one association to ProductoXPuntoVenta
	@OneToMany(mappedBy="puntoVenta")
	private List<ProductoXPuntoVenta> productoXPuntoVentas;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	private Sucursal sucursal;

	//bi-directional many-to-one association to UsuarioXPuntoVenta
	@OneToMany(mappedBy="puntoVenta")
	private List<UsuarioXPuntoVenta> usuarioXPuntoVentas;

	public PuntoVenta() {
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

	public String getAgenteId() {
		return this.agenteId;
	}

	public void setAgenteId(String agenteId) {
		this.agenteId = agenteId;
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

	public String getPtoEnsurance() {
		return this.ptoEnsurance;
	}

	public void setPtoEnsurance(String ptoEnsurance) {
		this.ptoEnsurance = ptoEnsurance;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setPuntoVenta(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setPuntoVenta(null);

		return cotizacion;
	}

	public List<ProductoXPuntoVenta> getProductoXPuntoVentas() {
		return this.productoXPuntoVentas;
	}

	public void setProductoXPuntoVentas(List<ProductoXPuntoVenta> productoXPuntoVentas) {
		this.productoXPuntoVentas = productoXPuntoVentas;
	}

	public ProductoXPuntoVenta addProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().add(productoXPuntoVenta);
		productoXPuntoVenta.setPuntoVenta(this);

		return productoXPuntoVenta;
	}

	public ProductoXPuntoVenta removeProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().remove(productoXPuntoVenta);
		productoXPuntoVenta.setPuntoVenta(null);

		return productoXPuntoVenta;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public List<UsuarioXPuntoVenta> getUsuarioXPuntoVentas() {
		return this.usuarioXPuntoVentas;
	}

	public void setUsuarioXPuntoVentas(List<UsuarioXPuntoVenta> usuarioXPuntoVentas) {
		this.usuarioXPuntoVentas = usuarioXPuntoVentas;
	}

	public UsuarioXPuntoVenta addUsuarioXPuntoVenta(UsuarioXPuntoVenta usuarioXPuntoVenta) {
		getUsuarioXPuntoVentas().add(usuarioXPuntoVenta);
		usuarioXPuntoVenta.setPuntoVenta(this);

		return usuarioXPuntoVenta;
	}

	public UsuarioXPuntoVenta removeUsuarioXPuntoVenta(UsuarioXPuntoVenta usuarioXPuntoVenta) {
		getUsuarioXPuntoVentas().remove(usuarioXPuntoVenta);
		usuarioXPuntoVenta.setPuntoVenta(null);

		return usuarioXPuntoVenta;
	}

}