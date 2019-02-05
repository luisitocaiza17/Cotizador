package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;

/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="AGRI_REGLA_VTA")
@NamedQueries({
	@NamedQuery(name="AgriReglaVta.findAll", query="SELECT a FROM AgriReglaVta a"),
	@NamedQuery(name="AgriReglaVta.obtenerPorId", query="SELECT a FROM AgriReglaVta a where a.reglaId=:reglaId")
	})
public class AgriReglaVta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="REGLA_ID")
	private BigInteger reglaId;
	
	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;
	
	@Column(name="CULTIVO_NOMBRE")
	private String cultivoNombre;
	
	@Column(name="CLICLO_ID")
	private BigInteger cicloId;
	
	@Column(name="CICLO_NOMBRE")
	private String cicloNombre;
	
	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;
	
	@Column(name="NOMBRE_CALCULO")
	private String nombreCalculo;
	
	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;
	
	@Column(name="PROVICIA_NOMBRE")
	private String provinciaNombre;
	
	@Column(name="CANTON_ID")
	private BigInteger cantonId;
	
	@Column(name="CANTON_NOMBRE")
	private String cantonNombre;
	
	@Column(name="COSTO_PRODUCCION")
	private Double costoProduccion;
	
	@Column(name="TASA")
	private Double tasa;
	
	@Column(name="COSTO_MANTENIMIENTO")
	private Double costoMantenimiento;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_DESDE")
	private Date aceptabilidadDesde;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACEPTABILIDAD_HASTA")
	private Date aceptabilidadHasta;
	
	@Column(name="OBSERVACIONES")
	private String observaciones;
	
	@Column(name="ESTADO")
	private int estado;
	
	@Column(name="DEDUCIBLE")
	private String deducible;

	public BigInteger getReglaId() {
		return reglaId;
	}

	public void setReglaId(BigInteger reglaId) {
		this.reglaId = reglaId;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getCultivoNombre() {
		return cultivoNombre;
	}

	public void setCultivoNombre(String cultivoNombre) {
		this.cultivoNombre = cultivoNombre;
	}

	public BigInteger getCicloId() {
		return cicloId;
	}

	public void setCicloId(BigInteger cicloId) {
		this.cicloId = cicloId;
	}

	public String getCicloNombre() {
		return cicloNombre;
	}

	public void setCicloNombre(String cicloNombre) {
		this.cicloNombre = cicloNombre;
	}

	public BigInteger getTipoCalculoId() {
		return tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public String getNombreCalculo() {
		return nombreCalculo;
	}

	public void setNombreCalculo(String nombreCalculo) {
		this.nombreCalculo = nombreCalculo;
	}

	public BigInteger getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public BigInteger getCantonId() {
		return cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonNombre() {
		return cantonNombre;
	}

	public void setCantonNombre(String cantonNombre) {
		this.cantonNombre = cantonNombre;
	}

	public Double getCostoProduccion() {
		return costoProduccion;
	}

	public void setCostoProduccion(Double costoProduccion) {
		this.costoProduccion = costoProduccion;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Double getCostoMantenimiento() {
		return costoMantenimiento;
	}

	public void setCostoMantenimiento(Double costoMantenimiento) {
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
