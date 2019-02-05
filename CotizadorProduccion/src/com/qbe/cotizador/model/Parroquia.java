package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the PARROQUIA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Parroquia.buscarPorId", query="SELECT c FROM Parroquia c where c.id = :id"),
	@NamedQuery(name="Parroquia.buscarTodos", query="SELECT c FROM Parroquia c"),
	@NamedQuery(name="Parroquia.buscarPorCodigoSBS", query="SELECT c FROM Parroquia c where c.codigoSbs = :id"),
	@NamedQuery(name="Parroquia.buscarPorCanton", query="SELECT c FROM Parroquia c where c.canton = :canton"),
	@NamedQuery(name="Parroquia.buscarPorNombre", query="SELECT c FROM Parroquia c where c.nombre = :nombre"),
	@NamedQuery(name="Parroquia.buscarPorNombreYCanton", query="SELECT c FROM Parroquia c where c.nombre = :nombre and c.canton = :canton"),
})
public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="codigo_sbs")
	private String codigoSbs;

	private String nombre;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="parroquia")
	private List<Direccion> direccions;

	//bi-directional many-to-one association to Canton
	@ManyToOne
	private Canton canton;

	public Parroquia() {
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

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setParroquia(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setParroquia(null);

		return direccion;
	}

	public Canton getCanton() {
		return this.canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}
	

	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("codigoSbs", codigoSbs);
		retorno.put("canton", canton.getJSON());
		return retorno;
	}

}