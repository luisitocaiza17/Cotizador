package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SUMA_ASEGURADA database table.
 * 
 */
@Entity
@Table(name="SUMA_ASEGURADA")
@NamedQueries({
	@NamedQuery(name="SumaAsegurada.buscarTodos", query="SELECT s FROM SumaAsegurada s")
})
public class SumaAsegurada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String desde;

	private String hasta;

	@Column(name="indice_choque_total")
	private String indiceChoqueTotal;

	@Column(name="indice_dano_parcial_severidad")
	private String indiceDanoParcialSeveridad;

	@Column(name="indice_responsabilidad_civil_frecuencia")
	private String indiceResponsabilidadCivilFrecuencia;

	@Column(name="indice_responsabilidad_civil_severidad")
	private String indiceResponsabilidadCivilSeveridad;

	@Column(name="indice_robo_total")
	private String indiceRoboTotal;

	private String nombre;

	public SumaAsegurada() {
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

	public String getIndiceChoqueTotal() {
		return this.indiceChoqueTotal;
	}

	public void setIndiceChoqueTotal(String indiceChoqueTotal) {
		this.indiceChoqueTotal = indiceChoqueTotal;
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

	public String getIndiceResponsabilidadCivilSeveridad() {
		return this.indiceResponsabilidadCivilSeveridad;
	}

	public void setIndiceResponsabilidadCivilSeveridad(String indiceResponsabilidadCivilSeveridad) {
		this.indiceResponsabilidadCivilSeveridad = indiceResponsabilidadCivilSeveridad;
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