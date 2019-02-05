package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the OBJETO_PYMES database table.
 * 
 */
@Entity
@Table(name="OBJETO_PYMES")
@NamedQueries({
	@NamedQuery(name="ObjetoPyme.buscarPorId", query="SELECT c FROM ObjetoPyme c where c.id = :id"),
	@NamedQuery(name="ObjetoPyme.buscarTodos", query="SELECT c FROM ObjetoPyme c")
})
public class ObjetoPyme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="alarma_monitoreada")
	private Boolean alarmaMonitoreada;

	@Column(name="anio_construccion")
	private int anioConstruccion;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="calle_secundaria")
	private String calleSecundaria;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	private Ciudad ciudad;

	@Column(name="contabilidad_formal")
	private Boolean contabilidadFormal;

	@Column(name="contituida_hace_dos_anios")
	private Boolean contituidaHaceDosAnios;

	@Column(name="descripcion_siniestro_anterior")
	private String descripcionSiniestroAnterior;

	@Column(name="detector_humo")
	private Boolean detectorHumo;

	private Boolean extintores;

	private Boolean guardiania;

	@Column(name="nombre_edificio_conjunto")
	private String nombreEdificioConjunto;

	@Column(name="numero_direccion")
	private String numeroDireccion;

	@Column(name="numero_oficina_piso")
	private String numeroOficinaPiso;

	@Column(name="numero_pisos")
	private int numeroPisos;

	private String otros;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	private Provincia provincia;

	private String sector;

	private Boolean sprinklers;

	//bi-directional many-to-one association to TipoConstruccion
	@ManyToOne
	@JoinColumn(name="tipo_construccion_id")
	private TipoConstruccion tipoConstruccion;

	//bi-directional many-to-one association to TipoOcupacion
	@ManyToOne
	@JoinColumn(name="tipo_ocupacion_id")
	private TipoOcupacion tipoOcupacion;

	@Column(name="valor_siniestro_anterior")
	private double valorSiniestroAnterior;

	//bi-directional many-to-one association to ActividadEconomica
	@ManyToOne
	@JoinColumn(name="actividad_economica_id")
	private ActividadEconomica actividadEconomica;

	public ObjetoPyme() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getAlarmaMonitoreada() {
		return this.alarmaMonitoreada;
	}

	public void setAlarmaMonitoreada(Boolean alarmaMonitoreada) {
		this.alarmaMonitoreada = alarmaMonitoreada;
	}

	public int getAnioConstruccion() {
		return this.anioConstruccion;
	}

	public void setAnioConstruccion(int anioConstruccion) {
		this.anioConstruccion = anioConstruccion;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return this.calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Boolean getContabilidadFormal() {
		return this.contabilidadFormal;
	}

	public void setContabilidadFormal(Boolean contabilidadFormal) {
		this.contabilidadFormal = contabilidadFormal;
	}

	public Boolean getContituidaHaceDosAnios() {
		return this.contituidaHaceDosAnios;
	}

	public void setContituidaHaceDosAnios(Boolean contituidaHaceDosAnios) {
		this.contituidaHaceDosAnios = contituidaHaceDosAnios;
	}

	public String getDescripcionSiniestroAnterior() {
		return this.descripcionSiniestroAnterior;
	}

	public void setDescripcionSiniestroAnterior(String descripcionSiniestroAnterior) {
		this.descripcionSiniestroAnterior = descripcionSiniestroAnterior;
	}

	public Boolean getDetectorHumo() {
		return this.detectorHumo;
	}

	public void setDetectorHumo(Boolean detectorHumo) {
		this.detectorHumo = detectorHumo;
	}

	public Boolean getExtintores() {
		return this.extintores;
	}

	public void setExtintores(Boolean extintores) {
		this.extintores = extintores;
	}

	public Boolean getGuardiania() {
		return this.guardiania;
	}

	public void setGuardiania(Boolean guardiania) {
		this.guardiania = guardiania;
	}

	public String getNombreEdificioConjunto() {
		return this.nombreEdificioConjunto;
	}

	public void setNombreEdificioConjunto(String nombreEdificioConjunto) {
		this.nombreEdificioConjunto = nombreEdificioConjunto;
	}

	public String getNumeroDireccion() {
		return this.numeroDireccion;
	}

	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}

	public String getNumeroOficinaPiso() {
		return this.numeroOficinaPiso;
	}

	public void setNumeroOficinaPiso(String numeroOficinaPiso) {
		this.numeroOficinaPiso = numeroOficinaPiso;
	}

	public int getNumeroPisos() {
		return this.numeroPisos;
	}

	public void setNumeroPisos(int numeroPisos) {
		this.numeroPisos = numeroPisos;
	}

	public String getOtros() {
		return this.otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Boolean getSprinklers() {
		return this.sprinklers;
	}

	public void setSprinklers(Boolean sprinklers) {
		this.sprinklers = sprinklers;
	}

	public TipoConstruccion getTipoConstruccion() {
		return tipoConstruccion;
	}

	public void setTipoConstruccion(TipoConstruccion tipoConstruccion) {
		this.tipoConstruccion = tipoConstruccion;
	}

	public TipoOcupacion getTipoOcupacion() {
		return tipoOcupacion;
	}

	public void setTipoOcupacion(TipoOcupacion tipoOcupacion) {
		this.tipoOcupacion = tipoOcupacion;
	}

	public double getValorSiniestroAnterior() {
		return this.valorSiniestroAnterior;
	}

	public void setValorSiniestroAnterior(double valorSiniestroAnterior) {
		this.valorSiniestroAnterior = valorSiniestroAnterior;
	}

	public ActividadEconomica getActividadEconomica() {
		return this.actividadEconomica;
	}

	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

}