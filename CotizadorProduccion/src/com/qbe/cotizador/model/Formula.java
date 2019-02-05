package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FORMULA database table.
 * 
 */
@Entity
@NamedQuery(name="Formula.findAll", query="SELECT f FROM Formula f")
public class Formula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String formula;

	private String nemotecnico;

	private String nombre;

	//bi-directional many-to-many association to Variable
	@ManyToMany
	@JoinTable(
		name="FORMULA_VARIABLE"
		, joinColumns={
			@JoinColumn(name="formula_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="variable_id")
			}
		)
	private List<Variable> variables;

	public Formula() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getNemotecnico() {
		return this.nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Variable> getVariables() {
		return this.variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

}