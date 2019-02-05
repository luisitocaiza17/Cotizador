package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the RAMO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Ramo.buscarPorId", query="SELECT c FROM Ramo c where c.id = :id"),
	@NamedQuery(name="Ramo.buscarActivos", query="SELECT c FROM Ramo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Ramo.buscarTodos", query="SELECT c FROM Ramo c"),
	@NamedQuery(name="Ramo.buscarPorNemotecnico", query="SELECT c FROM Ramo c WHERE c.nemotecnico =:nemotecnico")
})
public class Ramo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private boolean activo;

	private String nemotecnico;

	private String nombre;

	//bi-directional many-to-one association to ClaseRiesgo
	@OneToMany(mappedBy="ramo")
	private List<ClaseRiesgo> claseRiesgos;

	//bi-directional many-to-one association to GrupoCobertura
	@OneToMany(mappedBy="ramo")
	private List<GrupoCobertura> grupoCoberturas;

	//bi-directional many-to-one association to TipoRiesgo
	@OneToMany(mappedBy="ramo")
	private List<TipoRiesgo> tipoRiesgos;

	//bi-directional many-to-one association to Upla
	@OneToMany(mappedBy="ramo")
	private List<Upla> uplas;

	public Ramo() {
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

	public List<ClaseRiesgo> getClaseRiesgos() {
		return this.claseRiesgos;
	}

	public void setClaseRiesgos(List<ClaseRiesgo> claseRiesgos) {
		this.claseRiesgos = claseRiesgos;
	}

	public ClaseRiesgo addClaseRiesgo(ClaseRiesgo claseRiesgo) {
		getClaseRiesgos().add(claseRiesgo);
		claseRiesgo.setRamo(this);

		return claseRiesgo;
	}

	public ClaseRiesgo removeClaseRiesgo(ClaseRiesgo claseRiesgo) {
		getClaseRiesgos().remove(claseRiesgo);
		claseRiesgo.setRamo(null);

		return claseRiesgo;
	}

	public List<GrupoCobertura> getGrupoCoberturas() {
		return this.grupoCoberturas;
	}

	public void setGrupoCoberturas(List<GrupoCobertura> grupoCoberturas) {
		this.grupoCoberturas = grupoCoberturas;
	}

	public GrupoCobertura addGrupoCobertura(GrupoCobertura grupoCobertura) {
		getGrupoCoberturas().add(grupoCobertura);
		grupoCobertura.setRamo(this);

		return grupoCobertura;
	}

	public GrupoCobertura removeGrupoCobertura(GrupoCobertura grupoCobertura) {
		getGrupoCoberturas().remove(grupoCobertura);
		grupoCobertura.setRamo(null);

		return grupoCobertura;
	}

	public List<TipoRiesgo> getTipoRiesgos() {
		return this.tipoRiesgos;
	}

	public void setTipoRiesgos(List<TipoRiesgo> tipoRiesgos) {
		this.tipoRiesgos = tipoRiesgos;
	}

	public TipoRiesgo addTipoRiesgo(TipoRiesgo tipoRiesgo) {
		getTipoRiesgos().add(tipoRiesgo);
		tipoRiesgo.setRamo(this);

		return tipoRiesgo;
	}

	public TipoRiesgo removeTipoRiesgo(TipoRiesgo tipoRiesgo) {
		getTipoRiesgos().remove(tipoRiesgo);
		tipoRiesgo.setRamo(null);

		return tipoRiesgo;
	}

	public List<Upla> getUplas() {
		return this.uplas;
	}

	public void setUplas(List<Upla> uplas) {
		this.uplas = uplas;
	}

	public Upla addUpla(Upla upla) {
		getUplas().add(upla);
		upla.setRamo(this);

		return upla;
	}

	public Upla removeUpla(Upla upla) {
		getUplas().remove(upla);
		upla.setRamo(null);

		return upla;
	}
	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombre", nombre);
		retorno.put("nemoTecnico", nemotecnico);
		retorno.put("activo", activo);
		
		return retorno;
	}
}