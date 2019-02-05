package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the CANTON database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Canton.buscarPorId", query="SELECT c FROM Canton c where c.id = :id"),
	@NamedQuery(name="Canton.buscarTodos", query="SELECT c FROM Canton c"),
	@NamedQuery(name="Canton.buscarPorProvincia", query="SELECT c FROM Canton c where c.provincia = :provincia"),
	@NamedQuery(name="Canton.buscarPorNombre", query="SELECT c FROM Canton c where c.nombre = :nombre"),
	@NamedQuery(name="Canton.buscarCodigoSBS", query="SELECT c FROM Canton c where c.codigoSbs = :id"),
	@NamedQuery(name="Canton.buscarCodigoSBSProvincia", query="SELECT c FROM Canton c where c.codigoSbs = :id and c.provincia = :provincia"),	
	@NamedQuery(name="Canton.buscarCantonProvincia", query="SELECT c FROM Canton c where c.nombre = :nombre and c.provincia = :provincia")
})
public class Canton implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_sbs")
	private String codigoSbs;

	private String nombre;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	private Provincia provincia;

	//bi-directional many-to-one association to Parroquia
	@OneToMany(mappedBy="canton")
	private List<Parroquia> parroquias;

	public Canton() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoSbs() {
		return this.codigoSbs;
	}

	public void setCodigoSbs(String codigoSbs) {
		this.codigoSbs = codigoSbs;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public List<Parroquia> getParroquias() {
		return this.parroquias;
	}

	public void setParroquias(List<Parroquia> parroquias) {
		this.parroquias = parroquias;
	}

	public Parroquia addParroquia(Parroquia parroquia) {
		getParroquias().add(parroquia);
		parroquia.setCanton(this);

		return parroquia;
	}

	public Parroquia removeParroquia(Parroquia parroquia) {
		getParroquias().remove(parroquia);
		parroquia.setCanton(null);

		return parroquia;
	}

	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("codigoSbs", codigoSbs);
		retorno.put("provincia", provincia.getJSON());
		return retorno;
	}
}