package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tipo_deducible database table.
 * 
 */
@Entity
@Table(name="TIPO_DEDUCIBLE")
@NamedQueries({
	@NamedQuery(name="TipoDeducible.buscarPorId", query="SELECT c FROM TipoDeducible c where c.id=:id"),
	@NamedQuery(name="TipoDeducible.buscarTodos", query="SELECT t FROM TipoDeducible t")
})
public class TipoDeducible implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	@Column(name="texto_descripcion")
	private String textoDescripcion;

	public TipoDeducible() {
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

	public String getTextoDescripcion() {
		return this.textoDescripcion;
	}

	public void setTextoDescripcion(String textoDescripcion) {
		this.textoDescripcion = textoDescripcion;
	}

}