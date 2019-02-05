package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the VARIABLE_SISTEMA database table.
 * 
 */
@Entity
@Table(name="VARIABLE_SISTEMA")
@NamedQueries({
	@NamedQuery(name="VariableSistema.buscarPorId", query="SELECT c FROM VariableSistema c where c.id = :id"),
	@NamedQuery(name="VariableSistema.buscarTodos", query="SELECT c FROM VariableSistema c"),
	@NamedQuery(name="VariableSistema.buscarPorNombre", query="SELECT c FROM VariableSistema c where c.nombre = :nombre"),
	@NamedQuery(name="VariableSistema.buscarActivos", query="SELECT c FROM VariableSistema c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="VariableSistema.buscarTipoVariable", query="SELECT c FROM VariableSistema c WHERE c.tipoVariable =:tipoVariable order by c.id")	
})
public class VariableSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean activo;

	private String nombre;

	private String valor;

	//bi-directional many-to-one association to TipoVariable
	@ManyToOne
	@JoinColumn(name="tipo_variable_id")
	private TipoVariable tipoVariable;

	//bi-directional many-to-one association to Modulo
	@ManyToOne
	private Modulo modulo;

	public VariableSistema() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public TipoVariable getTipoVariable() {
		return this.tipoVariable;
	}

	public void setTipoVariable(TipoVariable tipoVariable) {
		this.tipoVariable = tipoVariable;
	}

	public Modulo getModulo() {
		return this.modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

}