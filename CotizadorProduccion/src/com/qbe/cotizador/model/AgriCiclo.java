package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the AGRI_CICLO database table.
 * 
 */
@Entity
@Table(name="AGRI_CICLO")
@NamedQueries({
	@NamedQuery(name="AgriCiclo.findAll", query="SELECT a FROM AgriCiclo a"),
	@NamedQuery(name="AgriCiclo.buscarPorId", query="SELECT c FROM AgriCiclo c where c.clicloId = :clicloId")		
	})
public class AgriCiclo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CLICLO_ID")
	private BigInteger clicloId;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	private String nombre;

	public AgriCiclo() {
	}

	public BigInteger getClicloId() {
		return this.clicloId;
	}

	public void setClicloId(BigInteger clicloId) {
		this.clicloId = clicloId;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}