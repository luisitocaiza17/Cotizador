package com.qbe.cotizador.model;
/**
 * The persistent class for the AGRI_INDEMNIZACION database table.
 * 
 */
import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name="AGRI_INDEMNIZACION")

@NamedQueries({
	@NamedQuery(name="AgriIndemnizacion.findAll", query="SELECT a FROM AgriIndemnizacion a"),
	@NamedQuery(name="AgriIndemnizacion.buscarId", query="SELECT a FROM AgriIndemnizacion a where a.id=:id")
})
public class AgriIndemnizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	private BigInteger clienteId;

	private BigInteger cultivoId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNotificacionCliente;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;

	private Timestamp fechaProceso;

	private double hectareas;

	private double sumaAseguradaIndemnizacion;

	private String tramite;

	private double valorIndemnizacion;
	
	private String tipoIndemnizacion;
	
	public String getTipoIndemnizacion() {
		return tipoIndemnizacion;
	}

	public void setTipoIndemnizacion(String tipoIndemnizacion) {
		this.tipoIndemnizacion = tipoIndemnizacion;
	}

	public AgriIndemnizacion() {
	}
	
	public BigInteger getId() {
		return id;
	}



	public void setId(BigInteger id) {
		this.id = id;
	}



	public BigInteger getClienteId() {
		return this.clienteId;
	}

	public void setClienteId(BigInteger clienteId) {
		this.clienteId = clienteId;
	}

	

	public BigInteger getCultivoId() {
		return cultivoId;
	}

	public void setCultivoId(BigInteger cultivoId) {
		this.cultivoId = cultivoId;
	}

	public Date getFechaNotificacionCliente() {
		return this.fechaNotificacionCliente;
	}

	public void setFechaNotificacionCliente(Date fechaNotificacionCliente) {
		this.fechaNotificacionCliente = fechaNotificacionCliente;
	}

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Timestamp getFechaProceso() {
		return this.fechaProceso;
	}

	public void setFechaProceso(Timestamp fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public double getHectareas() {
		return this.hectareas;
	}

	public void setHectareas(double hectareas) {
		this.hectareas = hectareas;
	}

	public double getSumaAseguradaIndemnizacion() {
		return this.sumaAseguradaIndemnizacion;
	}

	public void setSumaAseguradaIndemnizacion(double sumaAseguradaIndemnizacion) {
		this.sumaAseguradaIndemnizacion = sumaAseguradaIndemnizacion;
	}

	public String getTramite() {
		return this.tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public double getValorIndemnizacion() {
		return this.valorIndemnizacion;
	}

	public void setValorIndemnizacion(double valorIndemnizacion) {
		this.valorIndemnizacion = valorIndemnizacion;
	}

}