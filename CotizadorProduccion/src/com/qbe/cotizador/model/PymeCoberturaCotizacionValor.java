package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PYME_COBERTURA_COTIZACION_VALOR database table.
 * 
 */
@Entity
@Table(name="PYME_COBERTURA_COTIZACION_VALOR")
@NamedQueries({
	@NamedQuery(name="PymeCoberturaCotizacionValor.findAll", query="SELECT p FROM PymeCoberturaCotizacionValor p")
})
		
public class PymeCoberturaCotizacionValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cotizacion_id")
	private BigInteger cotizacionId;
	
	@Column(name="COTIZACION_DETALLE_ID")
	private BigInteger cotizacionDetalleId;

	@Column(name="GRUPO_PRODUCTO_ID")
	private BigInteger grupoProductoId;
	
	@Column(name="RAMO_ID")
	private BigInteger ramoId;
	
	@Column(name="COBERTURA_PYMES_ID")
	private BigInteger coberturaPymesId;
	
	@Column(name="TIPO_COBERTURA_ID")
	private BigInteger tipoCoberturaId;
	
	@Column(name="TIPO_ORIGEN")
	private String tipoOrigen;

	private String nombre;
	
	@Column(name="VALOR_MAXIMO_LIMITE_ASEGURADO")
	private double valorMaximoLimiteAsegurado; 
	
	public double getValorMaximoLimiteAsegurado() {
		return valorMaximoLimiteAsegurado;
	}
	
	public void setValorMaximoLimiteAsegurado(double valorMaximoLimiteAsegurado) {
		this.valorMaximoLimiteAsegurado = valorMaximoLimiteAsegurado;
	}
	
	@Column(name="PORCENTAJE_LIMITE_ASEGURADO")
	private double porcentajeLimiteAsegurado; 

	public String getTipoOrigen() {
		return tipoOrigen;
	}

	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}

	@Column(name="COBERTURA_ENS_MULTI_ID")
	private String coberturaEnsMultiId;
	
	@Column(name="COBERTURA_ENS_PROGR_ID")
	private String coberturaEnsProgrId;
	
	public String getCoberturaEnsMultiId() {
		return coberturaEnsMultiId;
	}

	public void setCoberturaEnsMultiId(String coberturaEnsMultiId) {
		this.coberturaEnsMultiId = coberturaEnsMultiId;
	}

	public String getCoberturaEnsProgrId() {
		return coberturaEnsProgrId;
	}

	public void setCoberturaEnsProgrId(String coberturaEnsProgrId) {
		this.coberturaEnsProgrId = coberturaEnsProgrId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OBJETO_PYMES_COBERTURA_ID")
	private BigInteger objetoPymesCoberturaId;

	@Column(name="PRIMA_CALCULADA")
	private double primaCalculada;

	@Column(name="TASA_SUGERIDA")
	private double tasaSugerida;
	
	@Column(name="TASA_INGRESADA")
	private double tasaIngresada;

	@Column(name="VALOR_INGRESADO")
	private double valorIngresado;

	public PymeCoberturaCotizacionValor() {
	}

	public BigInteger getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public BigInteger getGrupoProductoId() {
		return grupoProductoId;
	}

	public void setGrupoProductoId(BigInteger grupoProductoId) {
		this.grupoProductoId = grupoProductoId;
	}

	public BigInteger getRamoId() {
		return ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getObjetoPymesCoberturaId() {
		return this.objetoPymesCoberturaId;
	}

	public void setObjetoPymesCoberturaId(BigInteger objetoPymesCoberturaId) {
		this.objetoPymesCoberturaId = objetoPymesCoberturaId;
	}

	public double getPrimaCalculada() {
		return this.primaCalculada;
	}

	public void setPrimaCalculada(double primaCalculada) {
		this.primaCalculada = primaCalculada;
	}

	public double getTasaSugerida() {
		return this.tasaSugerida;
	}

	public void setTasaSugerida(double tasaSugerida) {
		this.tasaSugerida = tasaSugerida;
	}

	public double getValorIngresado() {
		return this.valorIngresado;
	}

	public void setValorIngresado(double valorIngresado) {
		this.valorIngresado = valorIngresado;
	}

	public BigInteger getCotizacionDetalleId() {
		return cotizacionDetalleId;
	}

	public void setCotizacionDetalleId(BigInteger cotizacionDetalleId) {
		this.cotizacionDetalleId = cotizacionDetalleId;
	}

	public BigInteger getCoberturaPymesId() {
		return coberturaPymesId;
	}

	public void setCoberturaPymesId(BigInteger coberturaPymesId) {
		this.coberturaPymesId = coberturaPymesId;
	}

	public double getTasaIngresada() {
		return tasaIngresada;
	}

	public void setTasaIngresada(double tasaIngresada) {
		this.tasaIngresada = tasaIngresada;
	}

	public double getPorcentajeLimiteAsegurado() {
		return porcentajeLimiteAsegurado;
	}

	public void setPorcentajeLimiteAsegurado(double porcentajeLimiteAsegurado) {
		this.porcentajeLimiteAsegurado = porcentajeLimiteAsegurado;
	}

	public BigInteger getTipoCoberturaId() {
		return tipoCoberturaId;
	}

	public void setTipoCoberturaId(BigInteger tipoCoberturaId) {
		this.tipoCoberturaId = tipoCoberturaId;
	}
}