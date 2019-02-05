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
@Table(name="AGRI_COTIZACIONES")
@NamedQueries({
	@NamedQuery(name="AgriCotizaciones.buscarTodos", query="SELECT p FROM AgriCotizaciones p"),
	@NamedQuery(name="AgriCotizaciones.buscarCotizacionId", query="SELECT p FROM AgriCotizaciones p where p.tramite=:id"),
	@NamedQuery(name="AgriCotizaciones.buscarPorIdentificacion", query="SELECT p FROM AgriCotizaciones p where p.identificacion=:identificacion")
})
public class AgriCotizaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="agricultor_tecnificado")
	private boolean agricultorTecnificado;

	@Column(name="altitud_nivel_mar")
	private float altitudNivelMar;

	private String apellidos;

	private String canal;

	@Column(name="canton_id")
	private BigInteger cantonId;

	@Column(name="canton_nombre")
	private String cantonNombre;

	@Column(name="direccion_siembra")
	private String direccionSiembra;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_siembra")
	private Date fechaSiembra;

	@Column(name="hectareas_asegurables")
	private float hectareasAsegurables;

	@Column(name="hectareas_lote")
	private float hectareasLote;

	
	private String identificacion;

	private float latitud;

	private float longitud;

	private String nombres;

	@Column(name="parroquia_id")
	private BigInteger parroquiaId;

	@Column(name="parroquia_nombre")
	private String parroquiaNombre;

	@Column(name="poliza_numero")
	private String polizaNumero;

	@Column(name="prima_neta_total")
	private String primaNetaTotal;

	@Column(name="provincia_id")
	private BigInteger provinciaId;

	@Column(name="provincia_nombre")
	private String provinciaNombre;

	private String sucursal;

	@Column(name="suma_asegurada_total")
	private double sumaAseguradaTotal;

	@Column(name="tipo_cultivo_id")
	private BigInteger tipoCultivoId;

	@Column(name="tipo_cultivo_nombre")
	private String tipoCultivoNombre;

	@Id
	private BigInteger tramite;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_desde")
	private Date vigenciaDesde;

	public AgriCotizaciones() {
	}

	public boolean getAgricultorTecnificado() {
		return this.agricultorTecnificado;
	}

	public void setAgricultorTecnificado(boolean agricultorTecnificado) {
		this.agricultorTecnificado = agricultorTecnificado;
	}

	public float getAltitudNivelMar() {
		return this.altitudNivelMar;
	}

	public void setAltitudNivelMar(float altitudNivelMar) {
		this.altitudNivelMar = altitudNivelMar;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCanal() {
		return this.canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public BigInteger getCantonId() {
		return this.cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonNombre() {
		return this.cantonNombre;
	}

	public void setCantonNombre(String cantonNombre) {
		this.cantonNombre = cantonNombre;
	}

	public String getDireccionSiembra() {
		return this.direccionSiembra;
	}

	public void setDireccionSiembra(String direccionSiembra) {
		this.direccionSiembra = direccionSiembra;
	}

	public Date getFechaSiembra() {
		return this.fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	public float getHectareasAsegurables() {
		return this.hectareasAsegurables;
	}

	public void setHectareasAsegurables(float hectareasAsegurables) {
		this.hectareasAsegurables = hectareasAsegurables;
	}

	public float getHectareasLote() {
		return this.hectareasLote;
	}

	public void setHectareasLote(float hectareasLote) {
		this.hectareasLote = hectareasLote;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public float getLatitud() {
		return this.latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return this.longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public BigInteger getParroquiaId() {
		return this.parroquiaId;
	}

	public void setParroquiaId(BigInteger parroquiaId) {
		this.parroquiaId = parroquiaId;
	}

	public String getParroquiaNombre() {
		return this.parroquiaNombre;
	}

	public void setParroquiaNombre(String parroquiaNombre) {
		this.parroquiaNombre = parroquiaNombre;
	}

	public String getPolizaNumero() {
		return this.polizaNumero;
	}

	public void setPolizaNumero(String polizaNumero) {
		this.polizaNumero = polizaNumero;
	}

	public String getPrimaNetaTotal() {
		return this.primaNetaTotal;
	}

	public void setPrimaNetaTotal(String primaNetaTotal) {
		this.primaNetaTotal = primaNetaTotal;
	}

	public BigInteger getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return this.provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public double getSumaAseguradaTotal() {
		return this.sumaAseguradaTotal;
	}

	public void setSumaAseguradaTotal(double sumaAseguradaTotal) {
		this.sumaAseguradaTotal = sumaAseguradaTotal;
	}

	public BigInteger getTipoCultivoId() {
		return this.tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getTipoCultivoNombre() {
		return this.tipoCultivoNombre;
	}

	public void setTipoCultivoNombre(String tipoCultivoNombre) {
		this.tipoCultivoNombre = tipoCultivoNombre;
	}

	public BigInteger getTramite() {
		return this.tramite;
	}

	public void setTramite(BigInteger tramite) {
		this.tramite = tramite;
	}

	public Date getVigenciaDesde() {
		return this.vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

}