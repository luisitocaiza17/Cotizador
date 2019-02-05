package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the VARIABLE database table.
 * 
 */
@Entity
@NamedQuery(name="Variable.findAll", query="SELECT v FROM Variable v")
public class Variable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-many association to Formula
	@ManyToMany(mappedBy="variables")
	private List<Formula> formulas;

	//bi-directional many-to-one association to TipoVariable
	@ManyToOne
	@JoinColumn(name="tipo_variable_id")
	private TipoVariable tipoVariable;

	public Variable() {
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

	public List<Formula> getFormulas() {
		return this.formulas;
	}

	public void setFormulas(List<Formula> formulas) {
		this.formulas = formulas;
	}

	public TipoVariable getTipoVariable() {
		return this.tipoVariable;
	}

	public void setTipoVariable(TipoVariable tipoVariable) {
		this.tipoVariable = tipoVariable;
	}

}