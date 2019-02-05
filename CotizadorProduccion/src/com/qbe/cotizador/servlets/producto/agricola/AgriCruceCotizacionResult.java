package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;
import java.util.Date;

public class AgriCruceCotizacionResult {

	private String numeroTramite;
	private String idCotizacion2;
	private String nombresCliente;
	private String identificacionCliente;
	private String tipoCultivoNombre;
	private String provinciaNombre;
	private String cantonNombre;
	private String parroquiaNombre;
	private String direccionSiembra;
	private BigInteger vigenciaDias;
	private String fechaVigenciaDesde;
	private String fechaVigenciaHasta;
	private double hectareasAsegurables;
	private double sumaAseguradaPorCiento;
	private Float tasa;
	private double primaNetaPorCiento;
	private double comision;
		
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public String getNumeroTramite() {
		return numeroTramite;
	}
	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}
	public String getIdCotizacion2() {
		return idCotizacion2;
	}
	public void setIdCotizacion2(String idCotizacion2) {
		this.idCotizacion2 = idCotizacion2;
	}
	public String getNombresCliente() {
		return nombresCliente;
	}
	public void setNombresCliente(String nombresCliente) {
		this.nombresCliente = nombresCliente;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public String getTipoCultivoNombre() {
		return tipoCultivoNombre;
	}
	public void setTipoCultivoNombre(String tipoCultivoNombre) {
		this.tipoCultivoNombre = tipoCultivoNombre;
	}
	public String getProvinciaNombre() {
		return provinciaNombre;
	}
	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}
	public String getCantonNombre() {
		return cantonNombre;
	}
	public void setCantonNombre(String cantonNombre) {
		this.cantonNombre = cantonNombre;
	}
	public String getParroquiaNombre() {
		return parroquiaNombre;
	}
	public void setParroquiaNombre(String parroquiaNombre) {
		this.parroquiaNombre = parroquiaNombre;
	}
	public String getDireccionSiembra() {
		return direccionSiembra;
	}
	public void setDireccionSiembra(String direccionSiembra) {
		this.direccionSiembra = direccionSiembra;
	}
	public BigInteger getVigenciaDias() {
		return vigenciaDias;
	}
	public void setVigenciaDias(BigInteger vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
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
	public double getHectareasAsegurables() {
		return hectareasAsegurables;
	}
	public void setHectareasAsegurables(double hectareasAsegurables) {
		this.hectareasAsegurables = hectareasAsegurables;
	}
	public double getSumaAseguradaPorCiento() {
		return sumaAseguradaPorCiento;
	}
	public void setSumaAseguradaPorCiento(double sumaAseguradaPorCiento) {
		this.sumaAseguradaPorCiento = sumaAseguradaPorCiento;
	}
	public Float getTasa() {
		return tasa;
	}
	public void setTasa(Float tasa) {
		this.tasa = tasa;
	}
	public double getPrimaNetaPorCiento() {
		return primaNetaPorCiento;
	}
	public void setPrimaNetaPorCiento(double primaNetaPorCiento) {
		this.primaNetaPorCiento = primaNetaPorCiento;
	}
	
	
}
