package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;


/**
 * The persistent class for the TIPO_PREDIO database table.
 * 
 */
@Entity
@Table(name="TIPO_PREDIO")
@NamedQueries({
	@NamedQuery(name="TipoPredio.findAll", query="SELECT t FROM TipoPredio t ORDER BY t.nombre"),
	@NamedQuery(name="TipoPredio.buscarPorId", query="SELECT t FROM TipoPredio t WHERE t.id =:id ORDER BY t.nombre"),
	@NamedQuery(name="TipoPredio.buscarPorActividadId", query="SELECT t FROM TipoPredio t WHERE t.actividadEconomicaId =:actividadEconomicaId ORDER BY t.nombre")
})
public class TipoPredio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	
	@Column(name="codigoensurance")
	private String codigoEnsurance;

	private String nemotecnico;

	private String nombre;

	private String tipoprediogeneralid;
	
	@Column(name="actividad_economica_id")
	private BigInteger actividadEconomicaId;

	public String getId() {
		return id;
	}

	public void setId(String tipoPredioId) {
		this.id = tipoPredioId;
	}

	public TipoPredio() {
	}

	

	public String getCodigoEnsurance() {
		return codigoEnsurance;
	}

	public void setCodigoEnsurance(String codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getNemotecnico() {
		return this.nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoprediogeneralid() {
		return this.tipoprediogeneralid;
	}

	public void setTipoprediogeneralid(String tipoprediogeneralid) {
		this.tipoprediogeneralid = tipoprediogeneralid;
	}
	
	public BigInteger getActividadEconomicaId() {
		return actividadEconomicaId;
	}

	public void setActividadEconomicaId(BigInteger actividadEconomicaId) {
		this.actividadEconomicaId = actividadEconomicaId;
	}

}