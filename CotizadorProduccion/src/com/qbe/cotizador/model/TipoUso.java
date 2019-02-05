package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_USO database table.
 * 
 */
@Entity
@Table(name="TIPO_USO")
@NamedQueries({
	@NamedQuery(name="TipoUso.buscarPorId", query="SELECT t FROM TipoUso t WHERE t.id=:id"),
	@NamedQuery(name="TipoUso.buscarTodos", query="SELECT t FROM TipoUso t"),
	@NamedQuery(name="TipoUso.buscarActivos", query="SELECT c FROM TipoUso c WHERE c.activo =:valorActivo")
})
public class TipoUso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	@Column(name="tipo_ensurance")
	private String tipoEnsurance;

	//bi-directional many-to-one association to ObjetoVehiculo
	@OneToMany(mappedBy="tipoUso")
	private List<ObjetoVehiculo> objetoVehiculos;

	public TipoUso() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoEnsurance() {
		return this.tipoEnsurance;
	}

	public void setTipoEnsurance(String tipoEnsurance) {
		this.tipoEnsurance = tipoEnsurance;
	}

	public List<ObjetoVehiculo> getObjetoVehiculos() {
		return this.objetoVehiculos;
	}

	public void setObjetoVehiculos(List<ObjetoVehiculo> objetoVehiculos) {
		this.objetoVehiculos = objetoVehiculos;
	}

	public ObjetoVehiculo addObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().add(objetoVehiculo);
		objetoVehiculo.setTipoUso(this);

		return objetoVehiculo;
	}

	public ObjetoVehiculo removeObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().remove(objetoVehiculo);
		objetoVehiculo.setTipoUso(null);

		return objetoVehiculo;
	}

}