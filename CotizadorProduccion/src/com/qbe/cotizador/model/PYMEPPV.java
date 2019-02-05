package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="PYMEPPV")
@NamedQueries({
	@NamedQuery(name="PYMEPPV.findAll", query="SELECT a FROM PYMEPPV a"),
	@NamedQuery(name="PYMEPPV.obtenerPorId", query="SELECT a FROM PYMEPPV a where a.parametroPuntoVentaId=:parametroPuntoVentaId")	
})

public class PYMEPPV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PARAMETRO_PUNTO_VENTA_ID")
	private BigInteger parametroPuntoVentaId;

	@Column(name="PUNTO_VENTA")
	private String puntoVenta;
	
	@Column(name="PUNTOVENTAID")
	private String puntoVentaId;
	
	@Column(name="ENTIDAD")
	private String entidad;
	
	@Column(name="ENTIDADID")
	private String entidadId;
	
	@Column(name="PLANTILLA_NOMBRE")
	private String PlantillaNombre;

	public BigInteger getParametroPuntoVentaId() {
		return parametroPuntoVentaId;
	}

	public void setParametroPuntoVentaId(BigInteger parametroPuntoVentaId) {
		this.parametroPuntoVentaId = parametroPuntoVentaId;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public String getPuntoVentaId() {
		return puntoVentaId;
	}

	public void setPuntoVentaId(String puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}

	public String getPlantillaNombre() {
		return PlantillaNombre;
	}

	public void setPlantillaNombre(String plantillaNombre) {
		PlantillaNombre = plantillaNombre;
	}
	
	
	
}