package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the AGRI_OBJETO_DETALLE database table.
 * 
 */
@Entity
@Table(name="AGRI_OBJETO_DETALLE")
@NamedQueries({
	@NamedQuery(name="AgriObjetoDetalle.findAll", query="SELECT a FROM AgriObjetoDetalle a"),
	@NamedQuery(name="AgriObjetoDetalle.buscarId", query="SELECT p FROM AgriObjetoDetalle p where p.id=:id")
})


@NamedQuery(name="AgriObjetoDetalle.findAll", query="SELECT a FROM AgriObjetoDetalle a")
public class AgriObjetoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="agricola_id")
	private BigInteger agricolaId;

	@Column(name="agricultor_tecnificado")
	private boolean agricultorTecnificado;

	@Column(name="canal_id")
	private BigInteger canalId;

	@Column(name="canton_id")
	private BigInteger cantonId;

	@Column(name="cliente_id")
	private BigInteger clienteId;

	@Column(name="costo_produccion")
	private double costoProduccion;

	@Column(name="cotizacion_id")
	private BigInteger cotizacionId;

	@Column(name="cultivo_id")
	private BigInteger cultivoId;

	@Column(name="derecho_emision")
	private double derechoEmision;

	@Column(name="dispone_asistencia")
	private boolean disponeAsistencia;

	@Column(name="dispone_riego")
	private boolean disponeRiego;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_siembra")
	private Date fechaSiembra;

	@Column(name="hectareas_aseguradas")
	private double hectareasAseguradas;

	@Column(name="hectareas_lote")
	private double hectareasLote;

	private double iva;

	private String latitud;

	private String longitud;

	@Column(name="monto_asegurado")
	private double montoAsegurado;

	@Column(name="numero_tramite")
	private String numeroTramite;

	private String observacion;

	@Column(name="observacion_qbe")
	private String observacionQbe;

	@Column(name="parroquia_id")
	private BigInteger parroquiaId;

	@Column(name="periodo_id")
	private BigInteger periodoId;

	@Column(name="prima_neta")
	private double primaNeta;

	@Column(name="prima_total")
	private double primaTotal;

	@Column(name="provincia_id")
	private BigInteger provinciaId;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;

	@Column(name="sitio_inversion")
	private String sitioInversion;

	@Column(name="super_bancos")
	private double superBancos;

	private double tasa;

	private String telefono;

	@Column(name="usuario_offline")
	private String usuarioOffline;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_desde")
	private Date vigenciaDesde;

	@Column(name="vigencia_dias")
	private double vigenciaDias;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_hasta")
	private Date vigenciaHasta;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_elaboracion")
	private Date fechaElaboracion;
	
	@Column(name="estado")
	private String estado;
	
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public boolean isAgricultorTecnificado() {
		return agricultorTecnificado;
	}


	public void setAgricultorTecnificado(boolean agricultorTecnificado) {
		this.agricultorTecnificado = agricultorTecnificado;
	}


	public boolean isDisponeAsistencia() {
		return disponeAsistencia;
	}


	public void setDisponeAsistencia(boolean disponeAsistencia) {
		this.disponeAsistencia = disponeAsistencia;
	}


	public boolean isDisponeRiego() {
		return disponeRiego;
	}


	public void setDisponeRiego(boolean disponeRiego) {
		this.disponeRiego = disponeRiego;
	}


	public AgriObjetoDetalle() {
	}

	
	public Date getFechaSiembra() {
		return fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}


	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}




	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}




	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}




	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}




	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}




	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getAgricolaId() {
		return this.agricolaId;
	}

	public void setAgricolaId(BigInteger agricolaId) {
		this.agricolaId = agricolaId;
	}

	
	public BigInteger getCanalId() {
		return this.canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public BigInteger getCantonId() {
		return this.cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public BigInteger getClienteId() {
		return this.clienteId;
	}

	public void setClienteId(BigInteger clienteId) {
		this.clienteId = clienteId;
	}

	public double getCostoProduccion() {
		return this.costoProduccion;
	}

	public void setCostoProduccion(double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public BigInteger getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public BigInteger getCultivoId() {
		return this.cultivoId;
	}

	public void setCultivoId(BigInteger cultivoId) {
		this.cultivoId = cultivoId;
	}

	public double getDerechoEmision() {
		return this.derechoEmision;
	}

	public void setDerechoEmision(double derechoEmision) {
		this.derechoEmision = derechoEmision;
	}

	
	public double getHectareasAseguradas() {
		return this.hectareasAseguradas;
	}

	public void setHectareasAseguradas(double hectareasAseguradas) {
		this.hectareasAseguradas = hectareasAseguradas;
	}

	public double getHectareasLote() {
		return this.hectareasLote;
	}

	public void setHectareasLote(double hectareasLote) {
		this.hectareasLote = hectareasLote;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public double getMontoAsegurado() {
		return this.montoAsegurado;
	}

	public void setMontoAsegurado(double montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}

	public String getNumeroTramite() {
		return this.numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getObservacionQbe() {
		return this.observacionQbe;
	}

	public void setObservacionQbe(String observacionQbe) {
		this.observacionQbe = observacionQbe;
	}

	public BigInteger getParroquiaId() {
		return this.parroquiaId;
	}

	public void setParroquiaId(BigInteger parroquiaId) {
		this.parroquiaId = parroquiaId;
	}

	public BigInteger getPeriodoId() {
		return this.periodoId;
	}

	public void setPeriodoId(BigInteger periodoId) {
		this.periodoId = periodoId;
	}

	public double getPrimaNeta() {
		return this.primaNeta;
	}

	public void setPrimaNeta(double primaNeta) {
		this.primaNeta = primaNeta;
	}

	public double getPrimaTotal() {
		return this.primaTotal;
	}

	public void setPrimaTotal(double primaTotal) {
		this.primaTotal = primaTotal;
	}

	public BigInteger getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public BigInteger getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public String getSitioInversion() {
		return this.sitioInversion;
	}

	public void setSitioInversion(String sitioInversion) {
		this.sitioInversion = sitioInversion;
	}

	public double getSuperBancos() {
		return this.superBancos;
	}

	public void setSuperBancos(double superBancos) {
		this.superBancos = superBancos;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuarioOffline() {
		return this.usuarioOffline;
	}

	public void setUsuarioOffline(String usuarioOffline) {
		this.usuarioOffline = usuarioOffline;
	}
	
	public double getVigenciaDias() {
		return this.vigenciaDias;
	}

	public void setVigenciaDias(double vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
	}	

}