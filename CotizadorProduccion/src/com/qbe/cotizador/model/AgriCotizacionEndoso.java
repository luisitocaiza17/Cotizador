package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the AGRI_COTIZACION_ENDOSO database table.
 * 
 */
@Entity
@Table(name="AGRI_COTIZACION_ENDOSO")
@NamedQueries({
	@NamedQuery(name="AgriCotizacionEndoso.findAll", query="SELECT a FROM AgriCotizacionEndoso a"),
	@NamedQuery(name="AgriCotizacionEndoso.buscarCotizacionId", query="SELECT a FROM AgriCotizacionEndoso a where a.cotizacionId=:cotizacionId"),
	@NamedQuery(name="AgriCotizacionEndoso.buscarCotizacionIdAnexo", query="SELECT a FROM AgriCotizacionEndoso a where a.cotizacionId=:cotizacionId and a.anexo=:anexo"),
	@NamedQuery(name="AgriCotizacionEndoso.buscarId", query="SELECT a FROM AgriCotizacionEndoso a where a.id=:id")
})

public class AgriCotizacionEndoso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigInteger id;

	@Column(name="cotizacion_id")
	private BigInteger cotizacionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_Proceso")
	private Date fecha_Proceso;

	private String tipo;

	private double valor;
	
	private int tipoSubEndoso;
	
	private double numHectareas;
	
	private double sumaAnteriorMov;
	
	private double primaAnteriorMov;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_canal")
	private Date fechaCanal;
	
	private String anexo;
	
	@Column(name = "prima_cotizacion")
	private double primaCotizacion;
	
	@Column(name = "suma_cotizacion")
	private double sumaCotizacion;
	
	@Column(name = "prima_total_cotizacion")
	private double prima_total_cotizacion;
	
	private String tramite;
	
	private String canal;
	
	private String puntoVenta;
	
	private String cliente;
	
	private double hectareasAntMov;
	
	
	
	public double getHectareasAntMov() {
		return hectareasAntMov;
	}

	public void setHectareasAntMov(double hectareasAntMov) {
		this.hectareasAntMov = hectareasAntMov;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getPrima_total_cotizacion() {
		return prima_total_cotizacion;
	}

	public void setPrima_total_cotizacion(double prima_total_cotizacion) {
		this.prima_total_cotizacion = prima_total_cotizacion;
	}

	public double getPrimaCotizacion() {
		return primaCotizacion;
	}

	public void setPrimaCotizacion(double primaCotizacion) {
		this.primaCotizacion = primaCotizacion;
	}

	public double getSumaCotizacion() {
		return sumaCotizacion;
	}

	public void setSumaCotizacion(double sumaCotizacion) {
		this.sumaCotizacion = sumaCotizacion;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public Date getFechaCanal() {
		return fechaCanal;
	}

	public void setFechaCanal(Date fechaCanal) {
		this.fechaCanal = fechaCanal;
	}

	public double getSumaAnteriorMov() {
		return sumaAnteriorMov;
	}

	public void setSumaAnteriorMov(double sumaAnteriorMov) {
		this.sumaAnteriorMov = sumaAnteriorMov;
	}

	public double getPrimaAnteriorMov() {
		return primaAnteriorMov;
	}

	public void setPrimaAnteriorMov(double primaAnteriorMov) {
		this.primaAnteriorMov = primaAnteriorMov;
	}

	public int getTipoSubEndoso() {
		return tipoSubEndoso;
	}

	public void setTipoSubEndoso(int tipoSubEndoso) {
		this.tipoSubEndoso = tipoSubEndoso;
	}

	public double getNumHectareas() {
		return numHectareas;
	}

	public void setNumHectareas(double numHectareas) {
		this.numHectareas = numHectareas;
	}

	@Column(name="suma_Asegurada")
	private double sumaAsegurada;
	
	@Column(name="prima_neta")
	private double primaNeta;
	
	
	@Column(name="super_bancos")
	private double superBancos;
	
	@Column(name="seguro_campesino")
	private double seguroCanpesino;
	
	@Column(name="derecho_emision")
	private double derechoEmision;
	
	@Column(name="iva")
	private double iva;
	
	@Column(name="prima_total")
	private double primaTotal;
	
	
	
	public double getPrimaNeta() {
		return primaNeta;
	}

	public void setPrimaNeta(double primaNeta) {
		this.primaNeta = primaNeta;
	}

	public double getSuperBancos() {
		return superBancos;
	}

	public void setSuperBancos(double superBancos) {
		this.superBancos = superBancos;
	}

	public double getSeguroCanpesino() {
		return seguroCanpesino;
	}

	public void setSeguroCanpesino(double seguroCanpesino) {
		this.seguroCanpesino = seguroCanpesino;
	}

	public double getDerechoEmision() {
		return derechoEmision;
	}

	public void setDerechoEmision(double derechoEmision) {
		this.derechoEmision = derechoEmision;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getPrimaTotal() {
		return primaTotal;
	}

	public void setPrimaTotal(double primaTotal) {
		this.primaTotal = primaTotal;
	}

	public double getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	
	public AgriCotizacionEndoso() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public Date getFecha_Proceso() {
		return fecha_Proceso;
	}

	public void setFecha_Proceso(Date fecha_Proceso) {
		this.fecha_Proceso = fecha_Proceso;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}