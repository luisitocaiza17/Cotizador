package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the AGRI_CULTIVO_CANAL database table.
 * 
 */
@Entity
@Table(name="AGRI_CULTIVO_CANAL")
@NamedQueries
({
	@NamedQuery(name="AgriCultivoCanal.findAll", query="SELECT a FROM AgriCultivoCanal a"),
	@NamedQuery(name="AgriCultivoCanal.buscarTipoCalculo", query="SELECT a FROM AgriCultivoCanal a WHERE a.tipoCalculoId=:tipoCalculoId ORDER BY A.nombre")
})
public class AgriCultivoCanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COD_ENSURANCE")
	private String codEnsurance;

	@Column(name="COD_INT_OTROS")
	private String codIntOtros;

	@Column(name="COD_INT_SUCRE")
	private String codIntSucre;

	@Column(name="COD_INTEGRACION")
	private String codIntegracion;

	@Id
	private BigInteger id;

	private String nombre;

	@Column(name="PRECIO_AJUSTE")
	private String precioAjuste;

	@Column(name="PRECIO_AJUSTE2")
	private String precioAjuste2;

	private double tasa;

	private int tipo;

	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;

	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;

	@Column(name="UNIDAD_MEDIDA")
	private String unidadMedida;

	@Column(name="UNIDAD_MEDIDA2")
	private String unidadMedida2;

	@Column(name="VIGENCIA_DIAS")
	private int vigenciaDias;

	public AgriCultivoCanal() {
	}

	public String getCodEnsurance() {
		return this.codEnsurance;
	}

	public void setCodEnsurance(String codEnsurance) {
		this.codEnsurance = codEnsurance;
	}

	public String getCodIntOtros() {
		return this.codIntOtros;
	}

	public void setCodIntOtros(String codIntOtros) {
		this.codIntOtros = codIntOtros;
	}

	public String getCodIntSucre() {
		return this.codIntSucre;
	}

	public void setCodIntSucre(String codIntSucre) {
		this.codIntSucre = codIntSucre;
	}

	public String getCodIntegracion() {
		return this.codIntegracion;
	}

	public void setCodIntegracion(String codIntegracion) {
		this.codIntegracion = codIntegracion;
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecioAjuste() {
		return this.precioAjuste;
	}

	public void setPrecioAjuste(String precioAjuste) {
		this.precioAjuste = precioAjuste;
	}

	public String getPrecioAjuste2() {
		return this.precioAjuste2;
	}

	public void setPrecioAjuste2(String precioAjuste2) {
		this.precioAjuste2 = precioAjuste2;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
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

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getUnidadMedida2() {
		return this.unidadMedida2;
	}

	public void setUnidadMedida2(String unidadMedida2) {
		this.unidadMedida2 = unidadMedida2;
	}

	public int getVigenciaDias() {
		return this.vigenciaDias;
	}

	public void setVigenciaDias(int vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
	}

}