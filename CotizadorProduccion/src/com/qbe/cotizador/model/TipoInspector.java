package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_INSPECTOR database table.
 *
 */
@Entity
@Table(name="TIPO_INSPECTOR")
@NamedQueries({
	@NamedQuery(name="TipoInspector.buscarPorId", query="SELECT t FROM TipoInspector t WHERE t.id=:id"),
	@NamedQuery(name="TipoInspector.buscarTodos", query="SELECT t FROM TipoInspector t")
})
public class TipoInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Inspector
	@OneToMany(mappedBy="tipoInspector")
	private List<Inspector> inspectors;

	public TipoInspector() {
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

	public List<Inspector> getInspectors() {
		return this.inspectors;
	}

	public void setInspectors(List<Inspector> inspectors) {
		this.inspectors = inspectors;
	}

	public Inspector addInspector(Inspector inspector) {
		getInspectors().add(inspector);
		inspector.setTipoInspector(this);

		return inspector;
	}

	public Inspector removeInspector(Inspector inspector) {
		getInspectors().remove(inspector);
		inspector.setTipoInspector(null);

		return inspector;
	}

}