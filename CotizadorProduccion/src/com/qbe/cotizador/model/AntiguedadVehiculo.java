package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ANTIGUEDAD_VEHICULO database table.
 * 
 */
@Entity
@Table(name="ANTIGUEDAD_VEHICULO")
@NamedQueries({
	@NamedQuery(name="AntiguedadVehiculo.buscarTodos", query="SELECT a FROM AntiguedadVehiculo a")
})
public class AntiguedadVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String desde;

	private String hasta;

	@Column(name="indice_dano_parcial_frecuencia")
	private String indiceDanoParcialFrecuencia;

	@Column(name="indice_dano_parcial_severidad")
	private String indiceDanoParcialSeveridad;

	@Column(name="indice_responsabilidad_civil_frecuencia")
	private String indiceResponsabilidadCivilFrecuencia;

	@Column(name="indice_robo_total")
	private String indiceRoboTotal;

	private String nombre;

	public AntiguedadVehiculo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesde() {
		return this.desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return this.hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getIndiceDanoParcialFrecuencia() {
		return this.indiceDanoParcialFrecuencia;
	}

	public void setIndiceDanoParcialFrecuencia(String indiceDanoParcialFrecuencia) {
		this.indiceDanoParcialFrecuencia = indiceDanoParcialFrecuencia;
	}

	public String getIndiceDanoParcialSeveridad() {
		return this.indiceDanoParcialSeveridad;
	}

	public void setIndiceDanoParcialSeveridad(String indiceDanoParcialSeveridad) {
		this.indiceDanoParcialSeveridad = indiceDanoParcialSeveridad;
	}

	public String getIndiceResponsabilidadCivilFrecuencia() {
		return this.indiceResponsabilidadCivilFrecuencia;
	}

	public void setIndiceResponsabilidadCivilFrecuencia(String indiceResponsabilidadCivilFrecuencia) {
		this.indiceResponsabilidadCivilFrecuencia = indiceResponsabilidadCivilFrecuencia;
	}

	public String getIndiceRoboTotal() {
		return this.indiceRoboTotal;
	}

	public void setIndiceRoboTotal(String indiceRoboTotal) {
		this.indiceRoboTotal = indiceRoboTotal;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}