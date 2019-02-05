package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the HONORARIOS_INSPECTOR database table.
 * 
 */
@Entity
@Table(name="HONORARIOS_INSPECTOR")
@NamedQueries({
	@NamedQuery(name="HonorariosInspector.buscarPorId", query="SELECT c FROM HonorariosInspector c where c.id = :id"),
	@NamedQuery(name="HonorariosInspector.buscarTodos", query="SELECT c FROM HonorariosInspector c"),
	@NamedQuery(name="HonorariosInspector.buscarPorInspectorZonaTipoObjeto", query="SELECT c FROM HonorariosInspector c where c.zona = :zona and c.inspector = :inspector and c.tipoObjeto = :tipoObjeto")
})
public class HonorariosInspector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="tipo_objeto")
	private String tipoObjeto;

	private double valor;

	//bi-directional many-to-one association to Inspector
	@ManyToOne
	private Inspector inspector;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	private Zona zona;

	public HonorariosInspector() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoObjeto() {
		return this.tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Inspector getInspector() {
		return this.inspector;
	}

	public void setInspector(Inspector inspector) {
		this.inspector = inspector;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

}