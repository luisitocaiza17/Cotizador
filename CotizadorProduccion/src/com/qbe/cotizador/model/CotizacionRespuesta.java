package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="COTIZACION_RESPUESTA")
@NamedQueries({
	@NamedQuery(name="CotizacionRespuesta.buscarPorId", query="SELECT c FROM CotizacionRespuesta c where c.id = :id"),
	@NamedQuery(name="CotizacionRespuesta.buscarPorCotizacionId", query="SELECT c FROM CotizacionRespuesta c where c.cotizacionId = :cotizacionId"),
})
public class CotizacionRespuesta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger id;
	
	@Column(name="cotizacion_id")
	private BigInteger cotizacionId;
	
	@Column(name="poliza_id")
	private String polizaId;
	
	@Column(name="poliza_numero")
	private String polizaNumero;
	
	@Column(name="factura_id")
	private String facturaId;
	
	@Column(name="factura_numero")
	private String facturaNumero;
	
	@Column(name="fecha_emision")
	private String fechaEmision;

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

	public String getPolizaId() {
		return polizaId;
	}

	public void setPolizaId(String polizaId) {
		this.polizaId = polizaId;
	}

	public String getPolizaNumero() {
		return polizaNumero;
	}

	public void setPolizaNumero(String polizaNumero) {
		this.polizaNumero = polizaNumero;
	}

	public String getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(String facturaId) {
		this.facturaId = facturaId;
	}

	public String getFacturaNumero() {
		return facturaNumero;
	}

	public void setFacturaNumero(String facturaNumero) {
		this.facturaNumero = facturaNumero;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	
}
