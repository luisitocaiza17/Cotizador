package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

import net.sf.json.JSONObject;

/**
 * The persistent class for the ADICIONAL database table.
 * 
 */
@Entity
@Table(name="TIPO_ADICIONAL")
@NamedQueries({ @NamedQuery(name = "TipoAdicional.buscarPorId", query = "SELECT c FROM TipoAdicional c WHERE c.id=:id"),
		@NamedQuery(name = "TipoAdicional.buscarTodos", query = "SELECT c FROM TipoAdicional c"),
		@NamedQuery(name = "TipoAdicional.buscarPorRamo", query = "SELECT c FROM TipoAdicional c where c.ramo = :ramo"),
		@NamedQuery(name = "TipoAdicional.buscarActivos", query = "SELECT c FROM TipoAdicional c WHERE c.activo =:valorActivo"),
		@NamedQuery(name = "TipoAdicional.buscarActivosRamo", query = "SELECT c FROM TipoAdicional c WHERE c.activo =:valorActivo and  c.ramo = :ramo") })
public class TipoAdicional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ramo_id")
	private Ramo ramo;

	public TipoAdicional() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

	public JSONObject getJSON() {
		JSONObject retorno = new JSONObject();

		retorno.put("nombre", nombre);
		retorno.put("id", id);
		retorno.put("activo", activo);

		return retorno;
	}

}