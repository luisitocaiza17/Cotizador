package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="AGRI_REGLA_NUEVA_AUDITORIA")
@NamedQueries({
	@NamedQuery(name="AgriReglaNueva.findAll", query="SELECT a FROM AgriReglaNueva a")
	})

public class AgriReglaNueva {

	private static final long serialVersionUID = 1L;
	
	public AgriReglaNueva() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AGRI_REGLA_NUEVA_ID")
	private BigInteger agriReglaNuevaId;
	
	@Column(name="AGRI_REGLA_AUDITORIA_ID")
	private BigInteger agriReglaAuditoriaId;
	
	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;
	
	@Column(name="CLICLO_ID")
	private BigInteger clicloId;
	
	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;
	
	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;
	
	@Column(name="CANTON_ID")
	private BigInteger cantonId;
	
	@Column(name="COSTO_PRODUCCION")
	private float costoProduccion;
	
	private float tasa;
	
	@Column(name="COSTO_MANTENIMIENTO")
	private float costoMantenimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_DESDE")
	private Date aceptabilidadDesde;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_HASTA")
	private Date aceptabilidadHasta;
	
	private String observaciones;
	
	private int estado;
	
	private String deducible;

	public BigInteger getAgriReglaNuevaId() {
		return agriReglaNuevaId;
	}

	public void setAgriReglaNuevaId(BigInteger agriReglaNuevaId) {
		this.agriReglaNuevaId = agriReglaNuevaId;
	}

	public BigInteger getAgriReglaAuditoriaId() {
		return agriReglaAuditoriaId;
	}

	public void setAgriReglaAuditoriaId(BigInteger agriReglaAuditoriaId) {
		this.agriReglaAuditoriaId = agriReglaAuditoriaId;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public BigInteger getClicloId() {
		return clicloId;
	}

	public void setClicloId(BigInteger clicloId) {
		this.clicloId = clicloId;
	}

	public BigInteger getTipoCalculoId() {
		return tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public BigInteger getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public BigInteger getCantonId() {
		return cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public float getCostoProduccion() {
		return costoProduccion;
	}

	public void setCostoProduccion(float costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public float getTasa() {
		return tasa;
	}

	public void setTasa(float tasa) {
		this.tasa = tasa;
	}

	public float getCostoMantenimiento() {
		return costoMantenimiento;
	}

	public void setCostoMantenimiento(float costoMantenimiento) {
		this.costoMantenimiento = costoMantenimiento;
	}

	public Date getAceptabilidadDesde() {
		return aceptabilidadDesde;
	}

	public void setAceptabilidadDesde(Date aceptabilidadDesde) {
		this.aceptabilidadDesde = aceptabilidadDesde;
	}

	public Date getAceptabilidadHasta() {
		return aceptabilidadHasta;
	}

	public void setAceptabilidadHasta(Date aceptabilidadHasta) {
		this.aceptabilidadHasta = aceptabilidadHasta;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getDeducible() {
		return deducible;
	}

	public void setDeducible(String deducible) {
		this.deducible = deducible;
	}
	
}
