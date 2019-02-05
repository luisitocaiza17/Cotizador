package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Ciudad.buscarPorId", query="SELECT c FROM Ciudad c where c.id = :id"),
	@NamedQuery(name="Ciudad.buscarTodos", query="SELECT c FROM Ciudad c"),
	@NamedQuery(name="Ciudad.buscarPorProvincia", query="SELECT c FROM Ciudad c where c.provincia=:provincia")
})
public class Ciudad implements Serializable {
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

	//bi-directional many-to-one association to ObjetoPyme
	@OneToMany(mappedBy="ciudad")
	private List<ObjetoPyme> objetoPymes;

	public Ciudad() {
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

	public List<ObjetoPyme> getObjetoPymes() {
		return this.objetoPymes;
	}

	public void setObjetoPymes(List<ObjetoPyme> objetoPymes) {
		this.objetoPymes = objetoPymes;
	}

	public ObjetoPyme addObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().add(objetoPyme);
		objetoPyme.setCiudad(this);

		return objetoPyme;
	}

	public ObjetoPyme removeObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().remove(objetoPyme);
		objetoPyme.setCiudad(null);

		return objetoPyme;
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