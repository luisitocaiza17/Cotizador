package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cliente.buscarPorId", query="SELECT c FROM Cliente c where c.id=:id"),
	@NamedQuery(name="Cliente.buscarPorEntidadId", query="SELECT c FROM Cliente c where c.entidad = :entidad"),
	@NamedQuery(name="Cliente.buscarTodos", query="SELECT c FROM Cliente c"),
	@NamedQuery(name="Cliente.buscarActivos", query="SELECT c FROM Cliente c WHERE c.activo =:valorActivo")
})
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	//bi-directional many-to-one association to Entidad
	@ManyToOne
	private Entidad entidad;

	//bi-directional many-to-one association to ActividadEconomica
	@ManyToOne
	@JoinColumn(name="actividad_economica_id")
	private ActividadEconomica actividadEconomica;
	
	@Column(name="antiguedad_cliente")
	private String antiguedadCliente;


	public Cliente() {
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

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public ActividadEconomica getActividadEconomica() {
		return this.actividadEconomica;
	}

	public void setActividadEconomica(ActividadEconomica actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}
	
	public String getAntiguedadCliente() {
		return antiguedadCliente;
	}

	public void setAntiguedadCliente(String antiguedadCliente) {
		this.antiguedadCliente = antiguedadCliente;
	}


}