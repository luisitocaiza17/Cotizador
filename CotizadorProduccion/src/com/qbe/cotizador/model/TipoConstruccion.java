package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_CONSTRUCCION database table.
 * 
 */
@Entity
@Table(name="TIPO_CONSTRUCCION")
@NamedQueries({
	@NamedQuery(name="TipoConstruccion.buscarPorId", query="SELECT c FROM TipoConstruccion c where c.id = :id"),
	@NamedQuery(name="TipoConstruccion.buscarTodos", query="SELECT c FROM TipoConstruccion c"),
	@NamedQuery(name="TipoConstruccion.buscarPorNombre", query="SELECT c FROM TipoConstruccion c where c.nombre = :nombre")
})
public class TipoConstruccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to ObjetoPyme
	@OneToMany(mappedBy="tipoConstruccion")
	private List<ObjetoPyme> objetoPymes;

	public TipoConstruccion() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ObjetoPyme> getObjetoPymes() {
		return this.objetoPymes;
	}

	public void setObjetoPymes(List<ObjetoPyme> objetoPymes) {
		this.objetoPymes = objetoPymes;
	}

	public ObjetoPyme addObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().add(objetoPyme);
		objetoPyme.setTipoConstruccion(this);

		return objetoPyme;
	}

	public ObjetoPyme removeObjetoPyme(ObjetoPyme objetoPyme) {
		getObjetoPymes().remove(objetoPyme);
		objetoPyme.setTipoConstruccion(null);

		return objetoPyme;
	}

}