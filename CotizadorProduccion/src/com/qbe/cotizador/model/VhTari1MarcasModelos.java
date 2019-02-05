package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the MODELO database table.
 * 
 */
@Entity
@Table(name="VH_TARI_1_MARCAS_MODELOS")
@NamedQueries({
	@NamedQuery(name="VhTari1MarcasModelos.buscarPorId", query="SELECT c FROM VhTari1MarcasModelos c where c.id =:id")	
})
public class VhTari1MarcasModelos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	private String nombre;
		
	public VhTari1MarcasModelos() {
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