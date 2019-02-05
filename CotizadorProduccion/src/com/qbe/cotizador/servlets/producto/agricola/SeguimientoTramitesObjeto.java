package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;
import java.util.Date;

public class SeguimientoTramitesObjeto {
	private BigInteger cotizacionId;
	private String periodo;
	private String cedulaCliente;
	private String cliente;
	private String Id2;
	private String canal;
	private String puntoVenta;
	private String numeroTramite;
	private int diasVigencia;
	private Date vigenciaDesde;
	private Date vigenciaHasta;
	private String cultivo;
	private String provincia;
	private String canton;
	private String parroquia;
	private String sitioInvesion;
	private String telefono;
	private String hectareasAseguradas;
	private String hectareasLote;
	private Date fechaSiembra;
	private double montoAsegurado;
	private double costroProduccion;
	private double tasa;
	private double superBancos;
	private double derechoEmision;
	private double iva;
	private double primaNeta;
	private double primaTotal;
	private double latitud;
	private double longitud;
	private String disponeRiego;
	private String disponeAsistencia;
	private String agricultoTecnificado;
	private String observacion;
	private String observacionQBE;
	private Date fechaCreacion;
	
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getId2() {
		return Id2;
	}
	public void setId2(String id2) {
		Id2 = id2;
	}
	public BigInteger getCotizacionId() {
		return cotizacionId;
	}
	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
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
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public int getDiasVigencia() {
		return diasVigencia;
	}
	public void setDiasVigencia(int diasVigencia) {
		this.diasVigencia = diasVigencia;
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
	public String getCultivo() {
		return cultivo;
	}
	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCanton() {
		return canton;
	}
	public void setCanton(String canton) {
		this.canton = canton;
	}
	public String getParroquia() {
		return parroquia;
	}
	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}
	public String getSitioInvesion() {
		return sitioInvesion;
	}
	public void setSitioInvesion(String sitioInvesion) {
		this.sitioInvesion = sitioInvesion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getHectareasAseguradas() {
		return hectareasAseguradas;
	}
	public void setHectareasAseguradas(String hectareasAseguradas) {
		this.hectareasAseguradas = hectareasAseguradas;
	}
	public String getHectareasLote() {
		return hectareasLote;
	}
	public void setHectareasLote(String hectareasLote) {
		this.hectareasLote = hectareasLote;
	}
	public Date getFechaSiembra() {
		return fechaSiembra;
	}
	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}
	public double getMontoAsegurado() {
		return montoAsegurado;
	}
	public void setMontoAsegurado(double montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}
	public double getCostroProduccion() {
		return costroProduccion;
	}
	public void setCostroProduccion(double costroProduccion) {
		this.costroProduccion = costroProduccion;
	}
	public double getTasa() {
		return tasa;
	}
	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
	public double getSuperBancos() {
		return superBancos;
	}
	public void setSuperBancos(double superBancos) {
		this.superBancos = superBancos;
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
	public double getPrimaNeta() {
		return primaNeta;
	}
	public void setPrimaNeta(double primaNeta) {
		this.primaNeta = primaNeta;
	}
	public double getPrimaTotal() {
		return primaTotal;
	}
	public void setPrimaTotal(double primaTotal) {
		this.primaTotal = primaTotal;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String getDisponeRiego() {
		return disponeRiego;
	}
	public void setDisponeRiego(String disponeRiego) {
		this.disponeRiego = disponeRiego;
	}
	public String getDisponeAsistencia() {
		return disponeAsistencia;
	}
	public void setDisponeAsistencia(String disponeAsistencia) {
		this.disponeAsistencia = disponeAsistencia;
	}
	public String getAgricultoTecnificado() {
		return agricultoTecnificado;
	}
	public void setAgricultoTecnificado(String agricultoTecnificado) {
		this.agricultoTecnificado = agricultoTecnificado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getObservacionQBE() {
		return observacionQBE;
	}
	public void setObservacionQBE(String observacionQBE) {
		this.observacionQBE = observacionQBE;
	}
	
	
	
	
}
