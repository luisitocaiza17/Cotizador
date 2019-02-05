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
@Table(name="AGRI_REGLA")
@NamedQueries({
	@NamedQuery(name="AgriRegla.findAll", query="SELECT a FROM AgriRegla a"),
	@NamedQuery(name="AgriRegla.obtenerPorId", query="SELECT a FROM AgriRegla a where a.reglaId=:reglaId and a.estado=:estado"),
	@NamedQuery(name="AgriRegla.obtenerPorIdTodos", query="SELECT a FROM AgriRegla a where a.reglaId=:reglaId"),
	@NamedQuery(name="AgriRegla.obtenerPorParametros", query="SELECT a FROM AgriRegla a where a.cantonId=:cantonId and a.provinciaId=:provinciaId and a.tipoCultivoId=:tipoCultivoId and a.estado=:estado"),
	@NamedQuery(name="AgriRegla.obtenerPorParametros2", query="SELECT a FROM AgriRegla a where a.cantonId=:cantonId and a.provinciaId=:provinciaId and a.tipoCultivoId=:tipoCultivoId and a.tipoCalculoId=:tipoCalculoId and a.estado=:estado"),
	@NamedQuery(name="AgriRegla.obtenerPorTipoCultivo", query="SELECT a FROM AgriRegla a where  a.tipoCultivoId=:tipoCultivoId and a.tipoCalculoId=:tipoCalculoId and a.estado=:estado")
	
})
public class AgriRegla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REGLA_ID")
	private BigInteger reglaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_DESDE")
	private Date aceptabilidadDesde;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_HASTA")
	private Date aceptabilidadHasta;

	@Column(name="CANTON_ID")
	private BigInteger cantonId;

	@Column(name="CLICLO_ID")
	private BigInteger clicloId;

	@Column(name="COSTO_PRODUCCION")
	private float costoProduccion;

	@Column(name="ESTADO")
	private int estado;

	private String observaciones;

	@Column(name="COSTO_MANTENIMIENTO")
	private float costoMantenimiento;

	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;

	private float tasa;

	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;

	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;

	public AgriRegla() {
	}

	public BigInteger getReglaId() {
		return this.reglaId;
	}

	public void setReglaId(BigInteger reglaId) {
		this.reglaId = reglaId;
	}

	public Date getAceptabilidadDesde() {
		return this.aceptabilidadDesde;
	}

	public void setAceptabilidadDesde(Date aceptabilidadDesde) {
		this.aceptabilidadDesde = aceptabilidadDesde;
	}

	public Date getAceptabilidadHasta() {
		return this.aceptabilidadHasta;
	}

	public void setAceptabilidadHasta(Date aceptabilidadHasta) {
		this.aceptabilidadHasta = aceptabilidadHasta;
	}

	public BigInteger getCantonId() {
		return this.cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public BigInteger getClicloId() {
		return this.clicloId;
	}

	public void setClicloId(BigInteger clicloId) {
		this.clicloId = clicloId;
	}

	public float getCostoProduccion() {
		return this.costoProduccion;
	}

	public void setCostoProduccion(float costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public BigInteger getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public float getTasa() {
		return this.tasa;
	}

	public void setTasa(float tasa) {
		this.tasa = tasa;
	}

	public BigInteger getTipoCalculoId() {
		return this.tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public BigInteger getTipoCultivoId() {
		return this.tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public float getCostoMantenimiento() {
		return this.costoMantenimiento;
	}

	public void setCostoMantenimiento(float costoMantenimiento) {
		this.costoMantenimiento = costoMantenimiento;
	}

}