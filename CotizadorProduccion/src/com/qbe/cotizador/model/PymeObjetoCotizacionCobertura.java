package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_OBJETO_COTIZACION_COBERTURA database table.
 * 
 */
@Entity
@Table(name="PYME_OBJETO_COTIZACION_COBERTURA")
@NamedQueries({
	@NamedQuery(name="PymeObjetoCotizacionCobertura.buscarTodos", query="SELECT a FROM PymeObjetoCotizacionCobertura a")
})
public class PymeObjetoCotizacionCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OBJETO_PYMES_COBERTURA_ID")
	private BigInteger objetoPymesCoberturaId;

	@Column(name="OBJETO_ORIGEN_ID")
	private BigInteger objetoOrigenId;
	
	@Column(name="PLAN_ID")
	private String planId;

	

	@Column(name="OBJETO_PYMES_ID")
	private BigInteger objetoPymesId;

	@Column(name="PRIMA_CALCULADA")
	private double primaCalculada;

	@Column(name="TASA_INGRESADA")
	private double tasaIngresada;
	

	@Column(name="TASA_SUGERIDA")
	private double tasaSugerida;

	@Column(name="TEXTO_COMPLETO_DEDUCIBLE")
	private String textoCompletoDeducible;
	
	@Column(name="TEXTOS_DEDUCIBLE")
	private String textosDeducible;
	
	@Column(name="IDS_DEDUCIBLE")
	private String idsDeducible;
	
	@Column(name="VALORES_DEDUCIBLE")
	private String valoresDeducible;

	@Column(name="TIPO_ORIGEN")
	private String tipoOrigen;

	@Column(name="VALOR_INGRESADO")
	private double valorIngresado;
	
	@Column(name="VALOR_INGRESADO2")
	private double valorIngresado2;

	public PymeObjetoCotizacionCobertura() {
	}

	public BigInteger getObjetoPymesCoberturaId() {
		return this.objetoPymesCoberturaId;
	}

	public void setObjetoPymesCoberturaId(BigInteger objetoPymesCoberturaId) {
		this.objetoPymesCoberturaId = objetoPymesCoberturaId;
	}

	public BigInteger getObjetoOrigenId() {
		return this.objetoOrigenId;
	}

	public void setObjetoOrigenId(BigInteger objetoOrigenId) {
		this.objetoOrigenId = objetoOrigenId;
	}

	public BigInteger getObjetoPymesId() {
		return this.objetoPymesId;
	}

	public void setObjetoPymesId(BigInteger objetoPymesId) {
		this.objetoPymesId = objetoPymesId;
	}

	public double getPrimaCalculada() {
		return this.primaCalculada;
	}

	public void setPrimaCalculada(double primaCalculada) {
		this.primaCalculada = primaCalculada;
	}

	public double getTasaIngresada() {
		return this.tasaIngresada;
	}

	public void setTasaIngresada(double tasaIngresada) {
		this.tasaIngresada = tasaIngresada;
	}

	public double getTasaSugerida() {
		return this.tasaSugerida;
	}

	public void setTasaSugerida(double tasaSugerida) {
		this.tasaSugerida = tasaSugerida;
	}

	public String getTipoOrigen() {
		return this.tipoOrigen;
	}

	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}

	public double getValorIngresado() {
		return this.valorIngresado;
	}

	public void setValorIngresado(double valorIngresado) {
		this.valorIngresado = valorIngresado;
	}

	public String getValoresDeducible() {
		return valoresDeducible;
	}

	public void setValoresDeducible(String valoresDeducible) {
		this.valoresDeducible = valoresDeducible;
	}

	public String getIdsDeducible() {
		return idsDeducible;
	}

	public void setIdsDeducible(String idsDeducible) {
		this.idsDeducible = idsDeducible;
	}

	public String getTextoCompletoDeducible() {
		return textoCompletoDeducible;
	}

	public void setTextoCompletoDeducible(String textoCompletoDeducible) {
		this.textoCompletoDeducible = textoCompletoDeducible;
	}

	public String getTextosDeducible() {
		return textosDeducible;
	}

	public void setTextosDeducible(String textosDeducibles) {
		this.textosDeducible = textosDeducibles;
	}

	public double getValorIngresado2() {
		return valorIngresado2;
	}

	public void setValorIngresado2(double valorIngresado2) {
		this.valorIngresado2 = valorIngresado2;
	}
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
}