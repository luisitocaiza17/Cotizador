package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the `cotizador`.`PYME_PARAMETROS_PUNTO_VENTA` database table.
 * 
 */
@Entity
@Table(name="PYME_PARAMETROS_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="PymeParametroPuntoVenta.buscarTodos", query="SELECT p FROM PymeParametroPuntoVenta p"),
	@NamedQuery(name="PymeParametroPuntoVenta.buscarPorId", query="SELECT p FROM PymeParametroPuntoVenta p  where p.parametroPuntoventaId = :parametroPuntoventaId"),
	@NamedQuery(name="PymeParametroPuntoVenta.obtenerPorPuntoVentaId", query="SELECT c FROM PymeParametroPuntoVenta c where c.puntoVentaId = :puntoVentaId")
})
public class PymeParametroPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PARAMETRO_PUNTO_VENTA_ID")
	private BigInteger parametroPuntoventaId;

	@Column(name="PUNTO_VENTA_ID")
	private BigInteger puntoVentaId;
	
	@Column(name="ENTIDAD_ID")
	private BigInteger entidadId;

	

	@Column(name="plantilla_nombre")
	private String plantillaNombre;

	

	public BigInteger getParametroPuntoventaId() {
		return parametroPuntoventaId;
	}



	public void setParametroPuntoventaId(BigInteger parametroPuntoventaId) {
		this.parametroPuntoventaId = parametroPuntoventaId;
	}



	public BigInteger getPuntoVentaId() {
		return puntoVentaId;
	}



	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}



	public BigInteger getEntidadId() {
		return entidadId;
	}



	public void setEntidadId(BigInteger entidadId) {
		this.entidadId = entidadId;
	}



	public String getPlantillaNombre() {
		return plantillaNombre;
	}



	public void setPlantillaNombre(String plantillaNombre) {
		this.plantillaNombre = plantillaNombre;
	}



	public PymeParametroPuntoVenta() {
	}

	
}