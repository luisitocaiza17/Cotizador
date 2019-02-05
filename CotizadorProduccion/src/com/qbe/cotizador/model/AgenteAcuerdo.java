package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Date;


/**
 * The persistent class for the AGENTE_ACUERDO database table.
 * 
 */
@Entity
@Table(name="AGENTE_ACUERDO")
@NamedQueries({
	@NamedQuery(name="AgenteAcuerdo.buscarAgentesAcuerdo", query="SELECT c FROM AgenteAcuerdo c WHERE c.activo =:valorActivo and c.agenteId=:agenteId  ORDER BY c.nombre ASC"),
	@NamedQuery(name="AgenteAcuerdo.buscarTodos", query="SELECT c FROM AgenteAcuerdo c")
	
})
public class AgenteAcuerdo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;
	
	@Column(name="vigencia_desde")
	private Date vigenciaDesde;
	
	@Column(name="vigencia_hasta")
	private Date vigenciaHasta;
	
	@Column(name="agente_id")
	private String agenteId;
	
	@Column(name="acuerdo_ensurance")
	private String acuerdoEnsurance;
	
	private boolean activo;
	
	public AgenteAcuerdo() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public String getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(String agenteId) {
		this.agenteId = agenteId;
	}

	public String getAcuerdoEnsurance() {
		return acuerdoEnsurance;
	}

	public void setAcuerdoEnsurance(String acuerdoEnsurance) {
		this.acuerdoEnsurance = acuerdoEnsurance;
	}

}