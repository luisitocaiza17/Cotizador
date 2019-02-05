package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PYME_TEXTO_GRUPO_COBERTURA_COTIZACION database table.
 * 
 */
@Entity
@Table(name="PYME_TEXTO_GRUPO_COBERTURA_COTIZACION")
@NamedQuery(name="PymeTextoGrupoCoberturaCotizacion.findAll", query="SELECT p FROM PymeTextoGrupoCoberturaCotizacion p")
public class PymeTextoGrupoCoberturaCotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name="COTIZACION_ID")
	private BigInteger cotizacionId;
	
	@Column(name="RAMO_ID")
	private BigInteger ramoId;
	
	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;
	
	private String nombre;

	@Lob
	private byte[] texto;

	@Column(name="RAMO_ORDEN")
	private int ramoOrden;
	
	public PymeTextoGrupoCoberturaCotizacion() {
	}

	public BigInteger getCotizacionId() {
		return this.cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
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

	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}

	public BigInteger getGrupoPorProductoId() {
		return grupoPorProductoId;
	}

	public void setGrupoPorProductoId(BigInteger grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public BigInteger getRamoId() {
		return ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}
	
	public int getRamoOrden() {
		return ramoOrden;
	}

	public void setRamoOrden(int ramoOrden) {
		this.ramoOrden = ramoOrden;
	}
	
}