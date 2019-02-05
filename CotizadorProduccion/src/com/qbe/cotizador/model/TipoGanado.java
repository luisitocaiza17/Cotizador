package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TIPO_GANADO database table.
 * 
 */
@Entity
@Table(name="TIPO_GANADO")
@NamedQueries({
	@NamedQuery(name="TipoGanado.buscarTodos", query="SELECT t FROM TipoGanado t"),
	@NamedQuery(name="TipoGanado.buscarPorId", query="SELECT c FROM TipoGanado c where c.id = :id")
})
public class TipoGanado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	public TipoGanado() {
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

}