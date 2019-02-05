package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.sql.Timestamp;

/**
 * The persistent class for the 
 * 
 */
@Entity
@Table(name = "RESUMEN_VENTAS_PYMES")
@NamedQueries({
		@NamedQuery(name = "PymeCotizacionResumen.buscarTodos", query = "SELECT a FROM PymeCotizacionResumen a"),
		@NamedQuery(name="PymeCotizacionResumen.buscarCotizacionId", query="SELECT p FROM PymeCotizacionResumen p where p.id=:id")
})
public class PymeCotizacionResumen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	

	@Column(name = "Sucursal")
	private String sucursal;
	@Column(name = "PUNTOVENTAID")
	private BigInteger puntoVentaId;
	
	@Column(name = "PuntoVenta")
	private String puntoVenta;
	@Column(name = "tipoObjetoId")
	private String tipoObjetoId;
	
	
	@Column(name = "AGENTE_ID")
	private BigInteger agenteId;
	
	

	@Column(name = "usuario_Id")
	private BigInteger usuarioId;
	
	

	@Column(name = "UnidadProductora")
	private String unidadProductora;
	
	@Column(name = "AgenteDeSeguro")
	private String agenteDeSeguro;
	
	@Column(name = "NombreProducto")
	private String nombreProducto;
	
	@Column(name = "NombreCliente")
	private String nombreCliente;
	@Column(name = "identificacionCliente")
	private String identificacionCliente;
	
	
	@Column(name = "TipoPoliza")
	private String tipoPoliza;
	
	@Column(name = "ValorAsegurado")
	private double valorAsegurado;
	
	@Column(name = "PrimaNeta")
	private double primaNeta;
	
	@Column(name = "FechaCotizacion")
	private Timestamp fechaCotizacion;
	
	@Column(name = "FechaEmision")
	private Timestamp fechaEmision;
	
	@Column(name = "fecha_vigencia_desde")
	private String fechaVigenciaDesde;
	
	@Column(name = "fecha_vigencia_hasta")
	private String fechaVigenciaHasta;
	
	@Column(name = "NoPoliza")
	private String noPoliza;
	
	@Column(name = "FormaPago")
	private String formaPago;
	
	
	@Column(name = "Usuario")
	private String usuario;

	@Column(name = "PROVEEDOR")
	private String proveedor;
	
	@Column(name = "FechaSolicitud")
	private Timestamp fechaSolicitud;
	
	@Column(name = "FechaInspeccion")
	private Timestamp fechaInspeccion;
	
	@Column(name = "EstadoCotizacion")
	private String estadoCotizacion;
	@Column(name = "ESTADO_ID")
	private BigInteger estadoId;
	
	public BigInteger getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(BigInteger estadoId) {
		this.estadoId = estadoId;
	}
	public String getSucursal() {
		return sucursal;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}

	

	public String getUnidadProductora() {
		return unidadProductora;
	}

	public void setUnidadProductora(String unidadProductora) {
		this.unidadProductora = unidadProductora;
	}

	public String getAgenteDeSeguro() {
		return agenteDeSeguro;
	}

	public void setAgenteDeSeguro(String agenteDeSeguro) {
		this.agenteDeSeguro = agenteDeSeguro;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTipoPoliza() {
		return tipoPoliza;
	}

	public void setTipoPoliza(String tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}

	public double getValorAsegurado() {
		return valorAsegurado;
	}

	public void setValorAsegurado(double valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public double getPrimaNeta() {
		return primaNeta;
	}

	public void setPrimaNeta(double primaNeta) {
		this.primaNeta = primaNeta;
	}

	public Timestamp getFechaCotizacion() {
		return fechaCotizacion;
	}

	public void setFechaCotizacion(Timestamp fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaVigenciaDesde() {
		return fechaVigenciaDesde;
	}

	public void setFechaVigenciaDesde(String fechaVigenciaDesde) {
		this.fechaVigenciaDesde = fechaVigenciaDesde;
	}

	public String getFechaVigenciaHasta() {
		return fechaVigenciaHasta;
	}

	public void setFechaVigenciaHasta(String fechaVigenciaHasta) {
		this.fechaVigenciaHasta = fechaVigenciaHasta;
	}

	public String getNoPoliza() {
		return noPoliza;
	}

	public void setNoPoliza(String noPoliza) {
		this.noPoliza = noPoliza;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public Timestamp getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Timestamp fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public Timestamp getFechaInspeccion() {
		return fechaInspeccion;
	}

	public void setFechaInspeccion(Timestamp fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}

	public String getEstadoCotizacion() {
		return estadoCotizacion;
	}

	public void setEstadoCotizacion(String estadoCotizacion) {
		this.estadoCotizacion = estadoCotizacion;
	}
	public BigInteger getAgenteId() {
		return agenteId;
	}
	public void setAgenteId(BigInteger agenteId) {
		this.agenteId = agenteId;
	}
	public BigInteger getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public BigInteger getPuntoVentaId() {
		return puntoVentaId;
	}
	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public String getPuntoVenta() {
		return puntoVenta;
	}
	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}
	public String getTipoObjetoId() {
		return tipoObjetoId;
	}
	public void setTipoObjetoId(String tipoObjetoId) {
		this.tipoObjetoId = tipoObjetoId;
	}
	
}
