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
@Table(name="AGRI_PARAMETRO_X_CANAL")
@NamedQueries({
	@NamedQuery(name="AgriParametroXCanal.findAll", query="SELECT a FROM AgriParametroXCanal a ORDER BY a.puntoVentaId, a.tipoCultivoId"),
	@NamedQuery(name="AgriParametroXCanal.buscarPorId", query="SELECT a FROM AgriParametroXCanal a where a.parametroCanalId=:parametroCanalId"),
	@NamedQuery(name="AgriParametroXCanal.buscarPorPuntoVenta", query="SELECT a FROM AgriParametroXCanal a where a.puntoVentaId=:puntoVentaId"),
	@NamedQuery(name="AgriParametroXCanal.obtenerPorCanalTipoCultivo", query="SELECT a FROM AgriParametroXCanal a where a.puntoVentaId=:canalId and a.tipoCultivoId=:tipoCultivoId")
})
public class AgriParametroXCanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PARAMETRO_CANAL_ID")
	private BigInteger parametroCanalId;
	
	@Column(name="PUNTO_VENTA_ID")
	private BigInteger puntoVentaId;
	
	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;
	
	@Column(name="PLANTILLA_ID")
	private String plantillaId;
	
	@Column(name="CONTENEDOR_ENSURANCE_ID")
	private String contenedorEnsuranceId;
	
	
	@Column(name="NOMBRE_PLANTILLA")
	private String plantillaNombre;
	
	@Lob
	@Column(name="PLANTILLA_HTML")
    private byte[] plantillaHtml;
	
	@Column(name="CONTENEDOR_NUMERO")
	private String contenedorNumero;
	
	public String getContenedorNumero() {
		return contenedorNumero;
	}

	public void setContenedorNumero(String contenedorNumero) {
		this.contenedorNumero = contenedorNumero;
	}

	public byte[] getPlantillaHtml() {
		return plantillaHtml;
	}

	public void setPlantillaHtml(byte[] plantillaHtml) {
		this.plantillaHtml = plantillaHtml;
	}

	public BigInteger getParametroCanalId() {
		return parametroCanalId;
	}

	public void setParametroCanalId(BigInteger parametroCanalId) {
		this.parametroCanalId = parametroCanalId;
	}

	public BigInteger getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getPlantillaId() {
		return plantillaId;
	}

	public void setPlantillaId(String plantillaId) {
		this.plantillaId = plantillaId;
	}

	public String getPlantillaNombre() {
		return plantillaNombre;
	}

	public void setPlantillaNombre(String plantillaNombre) {
		this.plantillaNombre = plantillaNombre;
	}	
	
	public String getContenedorEnsuranceId() {
		return this.contenedorEnsuranceId;
	}

	public void setContenedorEnsuranceId(String contenedorEnsuranceId) {
		this.contenedorEnsuranceId = contenedorEnsuranceId;
	}
}