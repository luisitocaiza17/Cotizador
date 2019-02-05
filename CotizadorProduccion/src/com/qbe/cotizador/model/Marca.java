package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;


/**
 * The persistent class for the marca database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Marca.buscarPorId", query="SELECT c FROM Marca c where c.id = :id"),
	@NamedQuery(name="Marca.buscarTodos", query="SELECT c FROM Marca c"),
	@NamedQuery(name="Marca.buscarPorCodigoEnsurance", query="SELECT c FROM Marca c WHERE c.marEnsurance =:marEnsurance"),
	@NamedQuery(name="Marca.buscarActivos", query="SELECT c FROM Marca c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Marca.buscarPorNombre", query="SELECT c FROM Marca c where upper(c.nombre) = :nombre"),
})
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="mar_ensurance")
	private String marEnsurance;

	private String nombre;

	public Marca() {
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

	public String getMarEnsurance() {
		return this.marEnsurance;
	}

	public void setMarEnsurance(String marEnsurance) {
		this.marEnsurance = marEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public JSONObject getJSON(){
		JSONObject modelo=new JSONObject();
		modelo.put("id", id);
		modelo.put("activo", activo);
		modelo.put("nombre", nombre);
		return modelo;
	}
}