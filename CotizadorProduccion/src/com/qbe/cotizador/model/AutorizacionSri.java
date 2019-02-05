package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the AUTORIZACION_SRI database table.
 * 
 */
@Entity
@Table(name="AUTORIZACION_SRI")
@NamedQueries({
	@NamedQuery(name="AutorizacionSri.buscarTodos", query="SELECT a FROM AutorizacionSri a"),
	@NamedQuery(name="AutorizacionSri.buscarPorId", query="SELECT a FROM AutorizacionSri a WHERE a.id = :id"),
	@NamedQuery(name="AutorizacionSri.buscarActivos", query="SELECT a FROM AutorizacionSri a WHERE a.activo =:valorActivo order by a.vigenciaHasta desc"),
	@NamedQuery(name="AutorizacionSri.buscarPorIdEnsurance", query="SELECT a FROM AutorizacionSri a WHERE a.idEnsurance = :idEnsurance")
})
public class AutorizacionSri implements Serializable { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="id_ensurance")
	private String idEnsurance;

	private String numero;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_desde")
	private Date vigenciaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_hasta")
	private Date vigenciaHasta;

	public AutorizacionSri() {
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

	public String getIdEnsurance() {
		return this.idEnsurance;
	}

	public void setIdEnsurance(String idEnsurance) {
		this.idEnsurance = idEnsurance;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getVigenciaDesde() {
		return this.vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return this.vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

}