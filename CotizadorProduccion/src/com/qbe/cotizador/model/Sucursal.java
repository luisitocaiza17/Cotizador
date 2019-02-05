package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the sucursal database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Sucursal.buscarPorNombre", query="SELECT c FROM Sucursal c where upper(c.nombre) = :nombre"),
	@NamedQuery(name="Sucursal.buscarPorId", query="SELECT c FROM Sucursal c WHERE c.id = :id"),
	@NamedQuery(name="Sucursal.buscarTodos", query="SELECT c FROM Sucursal c"),
	@NamedQuery(name="Sucursal.buscarPorIdEnsurance", query="SELECT c FROM Sucursal c where c.sucEnsurance = :sucEnsurance"),
	@NamedQuery(name="Sucursal.buscarActivos", query="SELECT c FROM Sucursal c WHERE c.activo =:valorActivo")
})
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="es_matriz")
	private boolean esMatriz;

	@Column(name="indice_choque_total")
	private String indiceChoqueTotal;

	@Column(name="indice_dano_parcial_frecuencia")
	private String indiceDanoParcialFrecuencia;

	@Column(name="indice_dano_parcial_severidad")
	private String indiceDanoParcialSeveridad;

	@Column(name="indice_responsabilidad_civil_frecuencia")
	private String indiceResponsabilidadCivilFrecuencia;

	@Column(name="indice_responsabilidad_civil_severidad")
	private String indiceResponsabilidadCivilSeveridad;

	@Column(name="indice_robo_total")
	private String indiceRoboTotal;

	private String nombre;

	@Column(name="suc_ensurance")
	private String sucEnsurance;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;

	public Sucursal() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getEsMatriz() {
		return this.esMatriz;
	}

	public void setEsMatriz(boolean esMatriz) {
		this.esMatriz = esMatriz;
	}

	public String getIndiceChoqueTotal() {
		return this.indiceChoqueTotal;
	}

	public void setIndiceChoqueTotal(String indiceChoqueTotal) {
		this.indiceChoqueTotal = indiceChoqueTotal;
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

	public String getSucEnsurance() {
		return this.sucEnsurance;
	}

	public void setSucEnsurance(String sucEnsurance) {
		this.sucEnsurance = sucEnsurance;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}