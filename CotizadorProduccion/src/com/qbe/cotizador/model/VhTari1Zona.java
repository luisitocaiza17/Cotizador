package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VH_TARI_1_ZONAS database table.
 * 
 */
@Entity
@Table(name="VH_TARI_1_ZONAS")
@NamedQueries({
	@NamedQuery(name="VhTari1Zona.buscarPorId", query="SELECT a FROM VhTari1Zona a WHERE a.id =:id"),
	@NamedQuery(name="VhTari1Zona.buscarTodos", query="SELECT a FROM VhTari1Zona a ORDER BY a.nombre")
})
public class VhTari1Zona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	public VhTari1Zona() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}