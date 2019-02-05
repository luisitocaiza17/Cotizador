package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the ACTIVIDAD_ECONOMICA database table.
 * 
 */
@Entity
@Table(name="ACTIVIDAD_ECONOMICA")
@NamedQueries({
	@NamedQuery(name="ActividadEconomica.buscarPorId", query="SELECT a FROM ActividadEconomica a WHERE a.id =:id ORDER BY a.nombre"),
	@NamedQuery(name="ActividadEconomica.buscarTodos", query="SELECT a FROM ActividadEconomica a ORDER BY a.nombre"),
	@NamedQuery(name="ActividadEconomica.buscarPorGrupoProductoId", query="SELECT a FROM ActividadEconomica a WHERE a.grupoProductoId =:grupoProductoId ORDER BY a.nombre"),
	@NamedQuery(name="ActividadEconomica.buscarPorNombre", query="SELECT c FROM ActividadEconomica c WHERE c.nombre =:nombre ORDER BY c.nombre")
})
public class ActividadEconomica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="act_ensurance")
	private String actEnsurance;

	private String nombre;

	//bi-directional many-to-one association to ObjetoPyme
	/*@OneToMany(mappedBy="actividadEconomica")
	private List<ObjetoPyme> objetoPymes;*/

	public ActividadEconomica() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActEnsurance() {
		return this.actEnsurance;
	}

	public void setActEnsurance(String actEnsurance) {
		this.actEnsurance = actEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name="grupo_producto_id")
	private BigInteger grupoProductoId;

	public BigInteger getGrupoProductoId() {
		return grupoProductoId;
	}

	public void setGrupoProductoId(BigInteger grupoProductoId) {
		this.grupoProductoId = grupoProductoId;
	}

	
	/*public List<ObjetoPyme> getObjetoPymes() {
		return this.objetoPymes;
	}

	public void setObjetoPymes(List<ObjetoPyme> objetoPymes) {
		this.objetoPymes = objetoPymes;
	}

	public ObjetoPyme addObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().add(objetoPyme);
		objetoPyme.setActividadEconomica(this);

		return objetoPyme;
	}

	public ObjetoPyme removeObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().remove(objetoPyme);
		objetoPyme.setActividadEconomica(null);

		return objetoPyme;
	}*/

}