package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="PYME_PLANES_POR_COBERTURAS")
@NamedQueries({
	@NamedQuery(name="PymePlanesPorCoberturas.buscarTodos", query="SELECT c FROM PymePlanesPorCoberturas c"),
	@NamedQuery(name="PymePlanesPorCoberturas.buscarPorPlan", query="SELECT c FROM PymePlanesPorCoberturas c WHERE c.grupoPorProductoId=:grupoPorProductoId and c.tipoDeclaracion=:tipoDeclaracion"),
	@NamedQuery(name="PymePlanesPorCoberturas.buscarPorProductoId", query="SELECT c FROM PymePlanesPorCoberturas c WHERE c.grupoPorProductoId=:grupoPorProductoId"),
	@NamedQuery(name="PymePlanesPorCoberturas.buscarPorGeneral", query="SELECT c FROM PymePlanesPorCoberturas c WHERE c.grupoPorProductoId=:grupoPorProductoId and c.tipoDeclaracion=:tipoDeclaracion and c.configuracionCoberturaId=:configuracionCoberturaId")
})
public class PymePlanesPorCoberturas implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private String grupoPorProductoId;
	
	@Column(name="CONFIGURACION_COBERTURA_ID")
	private String configuracionCoberturaId;
	
	
	@Column(name="TIPO_COBERTURA_ID")
	private String tipoCoberturaId;
	
	public String getTipoCoberturaId() {
		return tipoCoberturaId;
	}

	public void setTipoCoberturaId(String tipoCoberturaId) {
		this.tipoCoberturaId = tipoCoberturaId;
	}

	@Column(name="NOMBRECOMERCIAL")
	private String nombreComercial;

	@Column(name="PLAN_ID")
	private String planId;
	
	@Column(name="COBERTURA_PYMES_ID")
	private String cobeturaPymesId;
	@Id
	@Column(name="PLAN_CONFIGURACION_ID")
	private String planConfiguracionId;
	
	@Column(name="TASA")
	private double tasa;
	
	@Column(name="TASA_ESTRUCTURAS")
	private double tasaEstructuras;
	
	@Column(name="NOMBRE_PLAN")
	private String nombrePlan;
	
	@Column(name="DEPENDE")
	private int depende;
	
	@Column(name="VALOR_MAXIMO")
	private double valormaximo;
	
	@Column(name="TIPO_DECLARACION")
	private int tipoDeclaracion;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PRIMA")
	private double prima;
	
	@Column(name="TEXTO_DEDUCIBLE")
	private String textoDeducible;

	@Column(name="IDS_DEDUCIBLE")
	private String idsDeducible;
	
	@Column(name="VALORES_DEDUCIBLE")
	private String valoresDeducible;
	
	@Column(name="TEXTOS_DEDUCIBLE")
	private String textosDeducible;
	
	@Column(name="TIPO_TASA")
	private int tipoTasa;
	
	@Column(name="RAMO")
	private BigInteger ramo;
	
	@Column(name="RAMO_NOMBRE")
	private String  ramoNombre;
	
	
	
	public BigInteger getRamo() {
		return ramo;
	}

	public void setRamo(BigInteger ramo) {
		this.ramo = ramo;
	}

	public String getRamoNombre() {
		return ramoNombre;
	}

	public void setRamoNombre(String ramoNombre) {
		this.ramoNombre = ramoNombre;
	}

	public String getTextoDeducible() {
		return textoDeducible;
	}

	public void setTextoDeducible(String textoDeducible) {
		this.textoDeducible = textoDeducible;
	}

	public String getIdsDeducible() {
		return idsDeducible;
	}

	public void setIdsDeducible(String idsDeducible) {
		this.idsDeducible = idsDeducible;
	}

	public String getValoresDeducible() {
		return valoresDeducible;
	}

	public void setValoresDeducible(String valoresDeducible) {
		this.valoresDeducible = valoresDeducible;
	}

	public String getTextosDeducible() {
		return textosDeducible;
	}

	public void setTextosDeducible(String textosDeducible) {
		this.textosDeducible = textosDeducible;
	}

	public String getGrupoPorProductoId() {
		return grupoPorProductoId;
	}

	public void setGrupoPorProductoId(String grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCobeturaPymesId() {
		return cobeturaPymesId;
	}

	public void setCobeturaPymesId(String cobeturaPymesId) {
		this.cobeturaPymesId = cobeturaPymesId;
	}

	public String getPlanConfiguracionId() {
		return planConfiguracionId;
	}

	public void setPlanConfiguracionId(String planConfiguracionId) {
		this.planConfiguracionId = planConfiguracionId;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	public double getValormaximo() {
		return valormaximo;
	}

	public void setValormaximo(double valormaximo) {
		this.valormaximo = valormaximo;
	}

	public int getTipoDeclaracion() {
		return tipoDeclaracion;
	}

	public void setTipoDeclaracion(int tipoDeclaracion) {
		this.tipoDeclaracion = tipoDeclaracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrima() {
		return prima;
	}

	public void setPrima(double prima) {
		this.prima = prima;
	}
	
	public int getDepende() {
		return depende;
	}

	public void setDepende(int depende) {
		this.depende = depende;
	}
	
	public PymePlanesPorCoberturas() {
		
	}
	
	public String getConfiguracionCoberturaId() {
		return configuracionCoberturaId;
	}

	public void setConfiguracionCoberturaId(String configuracionCoberturaId) {
		this.configuracionCoberturaId = configuracionCoberturaId;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public int getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(int tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public double getTasaEstructuras() {
		return tasaEstructuras;
	}

	public void setTasaEstructuras(double tasaEstructuras) {
		this.tasaEstructuras = tasaEstructuras;
	}
}