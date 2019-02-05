package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PYME_RAMO_COTIZACION database table.
 * 
 */
@Entity
@Table(name="PYME_RAMO_COTIZACION")
@NamedQueries({
	@NamedQuery(name="PymeRamoCotizacion.findAll", query="SELECT p FROM PymeRamoCotizacion p")
})
		
public class PymeRamoCotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RAMO_ID")
	private BigInteger ramoId;
	
	@Column(name="COTIZACION_ID")
	private BigInteger cotizacionId;
	
	@Column(name="PRIMA_CALCULADA")
	private double primaCalculada;
	
	@Column(name="SUMA_ASEGURADA")
	private double sumaAsegurada;
	
	@Column(name="NOMBRE_RAMO")
	private String nombreRamo;
	
	public BigInteger getRamoId() {
		return ramoId;
	}

	public void setRamoId(BigInteger ramoID) {
		this.ramoId = ramoID;
	}

	public BigInteger getCotizacionId() {
		return cotizacionId;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.cotizacionId = cotizacionId;
	}

	public double getPrimaCalculada() {
		return primaCalculada;
	}

	public void setPrimaCalculada(double primaCalculada) {
		this.primaCalculada = primaCalculada;
	}

	public double getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public String getNombreRamo() {
		return nombreRamo;
	}

	public void setNombreRamo(String nombreRamo) {
		this.nombreRamo = nombreRamo;
	}
}