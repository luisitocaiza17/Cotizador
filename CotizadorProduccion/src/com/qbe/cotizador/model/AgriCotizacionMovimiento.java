package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the AGRI_COTIZACION_MOVIMIENTOS database table.
 * 
 */
@Entity
@Table(name="AGRI_COTIZACION_MOVIMIENTOS")
@NamedQuery(name="AgriCotizacionMovimiento.findAll", query="SELECT a FROM AgriCotizacionMovimiento a")
public class AgriCotizacionMovimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private BigInteger id;
		
	private String anexo;

	private String canal;
	
	@Column(name="canal_id")
	private BigInteger canalId;
	
	private BigInteger puntoVenta_id;

	private String canton;

	private String cultivo;

	@Column(name="derecho_emision")
	private double derechoEmision;

	private String direccion_Siembra;

	private String estado;

	private String estadoGeneral;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_siembra")
	private Date fechaSiembra;

	private Timestamp fechaEmisionSucre;

	private Timestamp fechaNotificacionQBE;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaVigencia;

	@Column(name="hectareas_asegurables")
	private float hectareasAsegurables;

	@Column(name="hectareas_lote")
	private float hectareasLote;
	
	private BigInteger idCotizacion;

	private BigInteger idEmision;

	private String identificacion;

	private double iva;

	private String nombresCliente;

	@Column(name="numero_tramite")
	private String numeroTramite;

	private String observacionQBE;

	private String parroquia;

	@Column(name="prima_neta")
	private double primaNeta;

	@Column(name="prima_total")
	private double primaTotal;

	private String provincia;

	@Column(name="seguro_campesino")
	private double seguroCampesino;

	private String sponsor;

	@Column(name="super_bancos")
	private double superBancos;

	@Column(name="vigencia_dias")
	private int vigenciaDias;

	@Column(name="tipoSubEndoso")
	private String tipoSubEndoso;

	@Column(name="sumaCotizacion")
	private double sumaCotizacion;
	
	@Column(name="primaCotizacion")
	private double primaCotizacion;
	
	@Column(name="primaTotalCotizacion")
	private double primaTotalCotizacion;
	
	@Column(name="sumaEndoso")
	private double sumaEndoso;
	
	@Column(name="hectareasModificadas")
	private double hectareasModificadas;
	
		
	public double getHectareasModificadas() {
		return hectareasModificadas;
	}

	public void setHectareasModificadas(double hectareasModificadas) {
		this.hectareasModificadas = hectareasModificadas;
	}

	public String getTipoSubEndoso() {
		return tipoSubEndoso;
	}

	public void setTipoSubEndoso(String tipoSubEndoso) {
		this.tipoSubEndoso = tipoSubEndoso;
	}

	public double getSumaCotizacion() {
		return sumaCotizacion;
	}

	public void setSumaCotizacion(double sumaCotizacion) {
		this.sumaCotizacion = sumaCotizacion;
	}

	public double getPrimaCotizacion() {
		return primaCotizacion;
	}

	public void setPrimaCotizacion(double primaCotizacion) {
		this.primaCotizacion = primaCotizacion;
	}

	public BigInteger getCanalId() {
		return canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public BigInteger getPuntoVenta_id() {
		return puntoVenta_id;
	}

	public void setPuntoVenta_id(BigInteger puntoVenta_id) {
		this.puntoVenta_id = puntoVenta_id;
	}

	public AgriCotizacionMovimiento() {
	}

	public String getAnexo() {
		return this.anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public String getCanal() {
		return this.canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getCanton() {
		return this.canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getCultivo() {
		return this.cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public double getDerechoEmision() {
		return this.derechoEmision;
	}

	public void setDerechoEmision(double derechoEmision) {
		this.derechoEmision = derechoEmision;
	}

	public String getDireccion_Siembra() {
		return this.direccion_Siembra;
	}

	public void setDireccion_Siembra(String direccion_Siembra) {
		this.direccion_Siembra = direccion_Siembra;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstadoGeneral() {
		return this.estadoGeneral;
	}

	public void setEstadoGeneral(String estadoGeneral) {
		this.estadoGeneral = estadoGeneral;
	}

	public Date getFechaSiembra() {
		return this.fechaSiembra;
	}

	public void setFechaSiembra(Date fechaSiembra) {
		this.fechaSiembra = fechaSiembra;
	}

	public Timestamp getFechaEmisionSucre() {
		return this.fechaEmisionSucre;
	}

	public void setFechaEmisionSucre(Timestamp fechaEmisionSucre) {
		this.fechaEmisionSucre = fechaEmisionSucre;
	}

	public Timestamp getFechaNotificacionQBE() {
		return this.fechaNotificacionQBE;
	}

	public void setFechaNotificacionQBE(Timestamp fechaNotificacionQBE) {
		this.fechaNotificacionQBE = fechaNotificacionQBE;
	}

	public Date getFechaVigencia() {
		return this.fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
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

	public BigInteger getIdCotizacion() {
		return this.idCotizacion;
	}

	public void setIdCotizacion(BigInteger idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public BigInteger getIdEmision() {
		return this.idEmision;
	}

	public void setIdEmision(BigInteger idEmision) {
		this.idEmision = idEmision;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public String getNombresCliente() {
		return this.nombresCliente;
	}

	public void setNombresCliente(String nombresCliente) {
		this.nombresCliente = nombresCliente;
	}

	public String getNumeroTramite() {
		return this.numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	public String getObservacionQBE() {
		return this.observacionQBE;
	}

	public void setObservacionQBE(String observacionQBE) {
		this.observacionQBE = observacionQBE;
	}

	public String getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
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

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public double getSeguroCampesino() {
		return this.seguroCampesino;
	}

	public void setSeguroCampesino(double seguroCampesino) {
		this.seguroCampesino = seguroCampesino;
	}

	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public double getSuperBancos() {
		return this.superBancos;
	}

	public void setSuperBancos(double superBancos) {
		this.superBancos = superBancos;
	}

	public int getVigenciaDias() {
		return this.vigenciaDias;
	}

	public void setVigenciaDias(int vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
	}

}