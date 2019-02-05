package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The persistent class for the AGRI_COTIZACIONES database table.
 * 
 */
@Entity
@Table(name = "REPORTE_COTIZACON_VTA")
@NamedQueries({
		@NamedQuery(name = "AgriReporteCotizacionVta.buscarTodos", query = "SELECT a FROM AgriReporteCotizacionVta a"),
		@NamedQuery(name = "AgriReporteCotizacionVta.buscarPorParametros", query = "SELECT c FROM AgriReporteCotizacionVta c WHERE c.productoId=:productoId and c.estadoId=:estadoId and c.tipoObjetoId=:tipoObjetoId and (c.fechaElaboracion BETWEEN :fechaDesde AND :fechaHasta)"),
		@NamedQuery(name = "AgriReporteCotizacionVta.buscarPorParametrosPV", query = "SELECT c FROM AgriReporteCotizacionVta c WHERE c.puntoVentaId=:puntoVentaId and c.productoId=:productoId and c.estadoId=:estadoId and c.tipoObjetoId=:tipoObjetoId and (c.fechaElaboracion BETWEEN :fechaDesde AND :fechaHasta)")		
		})
public class AgriReporteCotizacionVta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name = "PuntoVenta")
	private String PuntoVenta;

	@Column(name = "punto_venta_id")
	private BigInteger puntoVentaId;
	
	@Column(name = "cliente_id")
	private BigInteger clienteId;
	
	public BigInteger getClienteId() {
		return clienteId;
	}

	public void setClienteId(BigInteger clienteId) {
		this.clienteId = clienteId;
	}

	public BigInteger getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Column(name = "usuario_id")
	private BigInteger usuarioId;

	@Column(name = "Vigencia")
	private String vigencia;

	@Column(name = "vigencia_poliza_id")
	private BigInteger vigenciaPolizaId;

	@Column(name = "agente_id")
	private BigInteger agenteId;

	@Column(name = "producto_id")
	private BigInteger productoId;

	@Column(name = "Producto")
	private String producto;

	@Column(name = "estado_id")
	private BigInteger estadoId;

	@Column(name = "Estado")
	private String estado;

	@Column(name = "tipo_objeto_id")
	private BigInteger tipoObjetoId;

	@Column(name = "Objeto")
	private String objeto;

	@Column(name = "fecha_elaboracion")
	private Timestamp fechaElaboracion;

	@Column(name = "porcentaje_comision")
	private BigInteger porcentajeComision;

	@Column(name = "suma_asegurada_total")
	private double sumaAseguradaTotal;

	@Column(name = "prima_neta_total")
	private double primaNetaTotal;

	@Column(name = "imp_iva")
	private double iva;

	@Column(name = "totalFactura")
	private double totalFactura;
	
	@Column(name = "numeroImpresion")
	private int numeroImpresion;
	
	@Column(name = "numeroTramite")
	private String numeroTramite;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getPuntoVenta() {
		return PuntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		PuntoVenta = puntoVenta;
	}

	public BigInteger getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public BigInteger getVigenciaPolizaId() {
		return vigenciaPolizaId;
	}

	public void setVigenciaPolizaId(BigInteger vigenciaPolizaId) {
		this.vigenciaPolizaId = vigenciaPolizaId;
	}

	public BigInteger getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(BigInteger agenteId) {
		this.agenteId = agenteId;
	}

	public BigInteger getProductoId() {
		return productoId;
	}

	public void setProductoId(BigInteger productoId) {
		this.productoId = productoId;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public BigInteger getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(BigInteger estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigInteger getTipoObjetoId() {
		return tipoObjetoId;
	}

	public void setTipoObjetoId(BigInteger tipoObjetoId) {
		this.tipoObjetoId = tipoObjetoId;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Timestamp getFechaElaboracion() {
		return fechaElaboracion;
	}

	public void setFechaElaboracion(Timestamp fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public BigInteger getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(BigInteger porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public double getSumaAseguradaTotal() {
		return sumaAseguradaTotal;
	}

	public void setSumaAseguradaTotal(double sumaAseguradaTotal) {
		this.sumaAseguradaTotal = sumaAseguradaTotal;
	}

	public double getPrimaNetaTotal() {
		return primaNetaTotal;
	}

	public void setPrimaNetaTotal(double primaNetaTotal) {
		this.primaNetaTotal = primaNetaTotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNumeroImpresion() {
		return numeroImpresion;
	}

	public void setNumeroImpresion(int numeroImpresion) {
		this.numeroImpresion = numeroImpresion;
	}

	public String getNumeroTramite() {
		return numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
}