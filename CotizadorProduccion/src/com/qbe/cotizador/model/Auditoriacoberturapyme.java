package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the AUDITORIACOBERTURAPYME database table.
 * 
 */
@Entity
@NamedQuery(name="Auditoriacoberturapyme.findAll", query="SELECT a FROM Auditoriacoberturapyme a")
public class Auditoriacoberturapyme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private BigInteger id;
	
	@Column(name="FECHA_CAMBIO")
	private Timestamp fechaCambio;

	private String nombre;

	@Column(name="nombre_comercial_producto")
	private String nombreComercialProducto;

	private String usuario;

	@Column(name="VALOR_ANTERIOR")
	private String valorAnterior;

	@Column(name="VALOR_NUEVO")
	private String valorNuevo;

	@Column(name="DETALLE")
	private String detalle;
	
	
	
	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Auditoriacoberturapyme() {
	}

	public Timestamp getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreComercialProducto() {
		return this.nombreComercialProducto;
	}

	public void setNombreComercialProducto(String nombreComercialProducto) {
		this.nombreComercialProducto = nombreComercialProducto;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorNuevo() {
		return this.valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

}