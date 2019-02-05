package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="AGRI_INDEMNIZACION_VIEW")
@NamedQuery(name="AgriIndemnizacionView.findAll", query="SELECT a FROM AgriIndemnizacionView a")

public class AgriIndemnizacionView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private BigInteger id;
	
	@Column(name="CANAL_ID")
	private BigInteger canalId;
	
	@Column(name="CANAL")
	private String canal;

	@Column(name="COTIZACION_ID")
	private BigInteger cotizacionId;

	@Column(name="TRAMITE")
	private String tramite;
	
	@Column(name="PUNTO_VENTA_ID")
	private BigInteger puntoVentaId;
	
	@Column(name="PUNTO_VENTA")
	private String puntoVenta;

	@Column(name="IDENTIFICACION")
	private String identificacion;

	@Column(name="CLIENTE")
	private String cliente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaProceso")
	private Date fechaProceso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaPago")
	private Date fechaPago;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fechaNotificacionCliente")
	private Date fechaNotificacionCliente;


	@Column(name="hectareas")
	private double hectareas;

	@Column(name="sumaAseguradaIndemnizacion")
	private double sumaAseguradaIndemnizacion;
	
	@Column(name="valorIndemnizacion")
	private double valorIndemnizacion;
	
	private String tipoIndemnizacion;	
	

	public BigInteger getCanalId() {
		return canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public BigInteger getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getTipoIndemnizacion() {
		return tipoIndemnizacion;
	}

	public void setTipoIndemnizacion(String tipoIndemnizacion) {
		this.tipoIndemnizacion = tipoIndemnizacion;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getCotizacionId() {
		return cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaNotificacionCliente() {
		return fechaNotificacionCliente;
	}

	public void setFechaNotificacionCliente(Date fechaNotificacionCliente) {
		this.fechaNotificacionCliente = fechaNotificacionCliente;
	}

	public double getHectareas() {
		return hectareas;
	}

	public void setHectareas(double hectareas) {
		this.hectareas = hectareas;
	}

	public double getSumaAseguradaIndemnizacion() {
		return sumaAseguradaIndemnizacion;
	}

	public void setSumaAseguradaIndemnizacion(double sumaAseguradaIndemnizacion) {
		this.sumaAseguradaIndemnizacion = sumaAseguradaIndemnizacion;
	}

	public double getValorIndemnizacion() {
		return valorIndemnizacion;
	}

	public void setValorIndemnizacion(double valorIndemnizacion) {
		this.valorIndemnizacion = valorIndemnizacion;
	}
	
	
	

}
