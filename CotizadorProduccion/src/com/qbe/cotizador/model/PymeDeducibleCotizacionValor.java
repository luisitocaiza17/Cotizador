package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PYME_COBERTURA_COTIZACION_VALOR database table.
 * 
 */
@Entity
@Table(name="PYME_DEDUCIBLE_COTIZACION_VALOR")
@NamedQueries({
	@NamedQuery(name="PymeDeducibleCotizacionValor.findAll", query="SELECT p FROM PymeCoberturaCotizacionValor p")
})
		
public class PymeDeducibleCotizacionValor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="cotizacion_id")
	private BigInteger cotizacionId;
	
	@Column(name="COTIZACION_DETALLE_ID")
	private BigInteger cotizacionDetalleId;

	@Column(name="GRUPO_PRODUCTO_ID")
	private BigInteger grupoProductoId;
	
	@Column(name="RAMO_ID")
	private BigInteger ramoId;

	private String nombre;
	
	
	@Column(name="COBERTURA_ENS_MULTI_ID")
	private String coberturaEnsMultiId;
	
	@Column(name="COBERTURA_ENS_PROGR_ID")
	private String coberturaEnsProgrId;
	
	@Column(name="TEXTO_COMPLETO_DEDUCIBLE")
	private String textoCompletoDeducible;
	
	@Column(name="IDS_DEDUCIBLE")
	private String idsDeducible;
	
	@Column(name="VALORES_DEDUCIBLE")
	private String valoresDeducible;
	
	@Column(name="TEXTOS_DEDUCIBLE")
	private String textosDeducible;
	
	@Column(name="COBERTURA_PYMES_ID")
	private BigInteger coberturaPymesId; 
	
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

	public PymeDeducibleCotizacionValor() {
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

	public BigInteger getCotizacionDetalleId() {
		return cotizacionDetalleId;
	}

	public void setCotizacionDetalleId(BigInteger cotizacionDetalleId) {
		this.cotizacionDetalleId = cotizacionDetalleId;
	}

	public String getTextoCompletoDeducible() {
		return textoCompletoDeducible;
	}

	public void setTextoCompletoDeducible(String textoCompletoDeducible) {
		this.textoCompletoDeducible = textoCompletoDeducible;
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

	public BigInteger getCoberturaPymesId() {
		return coberturaPymesId;
	}

	public void setCoberturaPymesId(BigInteger coberturaPymesId) {
		this.coberturaPymesId = coberturaPymesId;
	}
}