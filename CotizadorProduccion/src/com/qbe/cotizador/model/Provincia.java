package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the PROVINCIA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Provincia.buscarPorId", query="SELECT c FROM Provincia c where c.id = :id"),
	@NamedQuery(name="Provincia.buscarTodos", query="SELECT c FROM Provincia c"),
	@NamedQuery(name="Provincia.buscarPorNombre", query="SELECT c FROM Provincia c where c.nombre = :nombre"),
	@NamedQuery(name="Provincia.buscarCodigoSBS", query="SELECT c FROM Provincia c where c.codigoSbs = :id")
})
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_sbs")
	private String codigoSbs;

	private String nombre;
	
	@Column(name="capital_id")
	private String capitalId;

	//bi-directional many-to-one association to Pai
	@ManyToOne
	@JoinColumn(name="pais_id")
	private Pais pais;

	//bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy="provincia")
	private List<Ciudad> ciudads;

	/*//bi-directional many-to-one association to ObjetoPyme
	@OneToMany(mappedBy="provincia")
	private List<ObjetoPyme> objetoPymes;*/

	public Provincia() {
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}


	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public List<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setProvincia(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setProvincia(null);

		return ciudad;
	}

	/*public List<ObjetoPyme> getObjetoPymes() {
		return this.objetoPymes;
	}

	public void setObjetoPymes(List<ObjetoPyme> objetoPymes) {
		this.objetoPymes = objetoPymes;
	}

	public ObjetoPyme addObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().add(objetoPyme);
		objetoPyme.setProvincia(this);

		return objetoPyme;
	}

	public ObjetoPyme removeObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().remove(objetoPyme);
		objetoPyme.setProvincia(null);

		return objetoPyme;
	}*/

	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("codigoSbs", codigoSbs);
		retorno.put("pais", pais.getJSON());
		return retorno;
	}
}