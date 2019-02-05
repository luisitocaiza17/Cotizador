package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TIPO_VARIABLE database table.
 * 
 */
@Entity
@Table(name="TIPO_VARIABLE")
@NamedQueries({
	@NamedQuery(name="TipoVariable.buscarPorId", query="SELECT t FROM TipoVariable t WHERE t.id=:id"),
	@NamedQuery(name="TipoVariable.buscarTodos", query="SELECT t FROM TipoVariable t")
})
public class TipoVariable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to Variable
	@OneToMany(mappedBy="tipoVariable")
	private List<Variable> variables;

	//bi-directional many-to-one association to VariableSistema
	@OneToMany(mappedBy="tipoVariable")
	private List<VariableSistema> variableSistemas;

	public TipoVariable() {
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

	public List<Variable> getVariables() {
		return this.variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public Variable addVariable(Variable variable) {
		getVariables().add(variable);
		variable.setTipoVariable(this);

		return variable;
	}

	public Variable removeVariable(Variable variable) {
		getVariables().remove(variable);
		variable.setTipoVariable(null);

		return variable;
	}

	public List<VariableSistema> getVariableSistemas() {
		return this.variableSistemas;
	}

	public void setVariableSistemas(List<VariableSistema> variableSistemas) {
		this.variableSistemas = variableSistemas;
	}

	public VariableSistema addVariableSistema(VariableSistema variableSistema) {
		getVariableSistemas().add(variableSistema);
		variableSistema.setTipoVariable(this);

		return variableSistema;
	}

	public VariableSistema removeVariableSistema(VariableSistema variableSistema) {
		getVariableSistemas().remove(variableSistema);
		variableSistema.setTipoVariable(null);

		return variableSistema;
	}

}