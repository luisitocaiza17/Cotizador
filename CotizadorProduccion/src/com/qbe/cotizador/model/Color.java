package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the COLOR database table.
 * 
 */
@Entity
@Cacheable(true)
@NamedQueries({
	@NamedQuery(name="Color.buscarActivos", query="SELECT c FROM Color c where c.activo =:valorActivo ORDER BY c.nombre"),
    @NamedQuery(name= "Color.buscarPorCodigoEnsurance", query= "SELECT c FROM Color c WHERE c.colEnsurance =:colEnsurance"),
    @NamedQuery(name= "Color.buscarPorId", query= "SELECT c FROM Color c where c.id =:id"),
    @NamedQuery(name= "Color.buscarTodos", query= "SELECT c FROM Color c"),
    @NamedQuery(name= "Color.buscarPorNombre", query= "SELECT c FROM Color c where upper(c.nombre) =:nombre")
})
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="col_ensurance")
	private String colEnsurance;

	private String nombre;

	//bi-directional many-to-one association to ObjetoVehiculo
	@OneToMany(mappedBy="color")
	private List<ObjetoVehiculo> objetoVehiculos;

	public Color() {
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

	public String getColEnsurance() {
		return this.colEnsurance;
	}

	public void setColEnsurance(String colEnsurance) {
		this.colEnsurance = colEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ObjetoVehiculo> getObjetoVehiculos() {
		return this.objetoVehiculos;
	}

	public void setObjetoVehiculos(List<ObjetoVehiculo> objetoVehiculos) {
		this.objetoVehiculos = objetoVehiculos;
	}

	public ObjetoVehiculo addObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().add(objetoVehiculo);
		objetoVehiculo.setColor(this);

		return objetoVehiculo;
	}

	public ObjetoVehiculo removeObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().remove(objetoVehiculo);
		objetoVehiculo.setColor(null);

		return objetoVehiculo;
	}

	public JSONObject getJSON(){
		JSONObject modelo=new JSONObject();
		modelo.put("id", id);
		modelo.put("activo", activo);
		modelo.put("nombre", nombre);
		return modelo;
	}

}