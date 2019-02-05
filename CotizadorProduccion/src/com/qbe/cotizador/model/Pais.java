package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the PAIS database table.
 * 
 */
@Entity
@Table(name="PAIS")
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_sbs")
	private String codigoSbs;

	private String nombre;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="pais")
	private List<Provincia> provincias;

	public Pais() {
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

	public List<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public Provincia addProvincia(Provincia provincia) {
		getProvincias().add(provincia);
		provincia.setPais(this);

		return provincia;
	}

	public Provincia removeProvincia(Provincia provincia) {
		getProvincias().remove(provincia);
		provincia.setPais(null);

		return provincia;
	}
	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("codigoSbs", codigoSbs);
		return retorno;
	}

}