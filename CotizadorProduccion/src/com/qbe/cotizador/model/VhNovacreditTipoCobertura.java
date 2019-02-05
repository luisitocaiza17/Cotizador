package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the VH_NOVACREDIT_TIPO_COBERTURA database table.
 * 
 */
@Entity
@Table(name = "VH_NOVACREDIT_TIPO_COBERTURA")
@NamedQueries({
		@NamedQuery(name = "VhNovacreditTipoCobertura.buscarPorId", query = "SELECT c FROM VhNovacreditTipoCobertura c where c.id = :id"),
		@NamedQuery(name = "VhNovacreditTipoCobertura.buscarTodos", query = "SELECT c FROM VhNovacreditTipoCobertura c") })
public class VhNovacreditTipoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String nombre;

	private String poliza;

	@ManyToOne
	@JoinColumn(name="tipo_uso_id")
	private TipoUso tipoUso;

	@ManyToOne
	@JoinColumn(name="tipo_vehiculo_id")
	private TipoVehiculo tipoVehiculo;

	public VhNovacreditTipoCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPoliza() {
		return this.poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public TipoUso getTipoUso() {
		return tipoUso;
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}

	
}