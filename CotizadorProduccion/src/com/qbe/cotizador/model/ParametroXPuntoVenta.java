package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the PARAMETRO_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="PARAMETRO_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="ParametroXPuntoVenta.buscarTodos", query="SELECT p FROM ParametroXPuntoVenta p"),
	@NamedQuery(name="ParametroXPuntoVenta.obtenerPorPuntoVentaId", query="SELECT c FROM ParametroXPuntoVenta c where c.puntoVentaId = :puntoVentaId")
})
public class ParametroXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="punto_venta_id")
	private BigInteger puntoVentaId;
	
	@Column(name="contenedor_ensuranceid")
	private String contenedorEnsuranceid;

	@Column(name="emision_directa")
	private int emisionDirecta;

	@Column(name="plantilla_ensurancid")
	private String plantillaEnsurancid;

	@Column(name="tipo_canal")
	private String tipoCanal;

	public ParametroXPuntoVenta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenedorEnsuranceid() {
		return this.contenedorEnsuranceid;
	}

	public void setContenedorEnsuranceid(String contenedorEnsuranceid) {
		this.contenedorEnsuranceid = contenedorEnsuranceid;
	}

	public int getEmisionDirecta() {
		return this.emisionDirecta;
	}

	public void setEmisionDirecta(int emisionDirecta) {
		this.emisionDirecta = emisionDirecta;
	}

	public String getPlantillaEnsurancid() {
		return this.plantillaEnsurancid;
	}

	public void setPlantillaEnsurancid(String plantillaEnsurancid) {
		this.plantillaEnsurancid = plantillaEnsurancid;
	}

	public String getTipoCanal() {
		return this.tipoCanal;
	}

	public void setTipoCanal(String tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public BigInteger getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}
}