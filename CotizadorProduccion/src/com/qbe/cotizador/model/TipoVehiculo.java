package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the TIPO_VEHICULO database table.
 * 
 */
@Entity
@Table(name="TIPO_VEHICULO")
@NamedQueries({
	@NamedQuery(name="TipoVehiculo.buscarPorId", query="SELECT t FROM TipoVehiculo t WHERE t.id=:id"),
	@NamedQuery(name="TipoVehiculo.buscarTodos", query="SELECT t FROM TipoVehiculo t"),
	@NamedQuery(name="TipoVehiculo.buscarPorNombres", query="SELECT c FROM TipoVehiculo c where c.grupo in :grupos and c.activo=1"),
	@NamedQuery(name="TipoVehiculo.buscarPorIds", query="SELECT c FROM TipoVehiculo c where c.id in :ids and c.activo=1"),	
	@NamedQuery(name="TipoVehiculo.buscarPorGrupo", query="SELECT c FROM TipoVehiculo c where c.grupo=:grupo and c.activo=1"),
	@NamedQuery(name="TipoVehiculo.buscarActivos", query="SELECT c FROM TipoVehiculo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="TipoVehiculo.buscarPorNombre", query="SELECT c FROM TipoVehiculo c WHERE c.nombre =:nombre"),
	@NamedQuery(name="TipoVehiculo.buscarPorGrupoCargaOPasajeros", query="SELECT c FROM TipoVehiculo c where c.grupo=:grupo and c.activo=1 and c.cargaPasajeros=:cargaPasajeros")
})
public class TipoVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="carga_pasajeros")
	private String cargaPasajeros;

	private String grupo;

	private String nombre;

	@Column(name="tip_vh_ensurance")
	private String tipVhEnsurance;

	//bi-directional many-to-one association to ObjetoVehiculo
	@OneToMany(mappedBy="tipoVehiculo")
	private List<ObjetoVehiculo> objetoVehiculos;

	public TipoVehiculo() {
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

	public String getCargaPasajeros() {
		return this.cargaPasajeros;
	}

	public void setCargaPasajeros(String cargaPasajeros) {
		this.cargaPasajeros = cargaPasajeros;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipVhEnsurance() {
		return this.tipVhEnsurance;
	}

	public void setTipVhEnsurance(String tipVhEnsurance) {
		this.tipVhEnsurance = tipVhEnsurance;
	}

	public List<ObjetoVehiculo> getObjetoVehiculos() {
		return this.objetoVehiculos;
	}

	public void setObjetoVehiculos(List<ObjetoVehiculo> objetoVehiculos) {
		this.objetoVehiculos = objetoVehiculos;
	}

	public ObjetoVehiculo addObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().add(objetoVehiculo);
		objetoVehiculo.setTipoVehiculo(this);

		return objetoVehiculo;
	}

	public ObjetoVehiculo removeObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().remove(objetoVehiculo);
		objetoVehiculo.setTipoVehiculo(null);

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