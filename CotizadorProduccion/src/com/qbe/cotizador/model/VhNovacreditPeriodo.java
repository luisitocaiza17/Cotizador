package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VH_NOVACREDIT_PERIODO database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_PERIODO")
@NamedQueries({
	@NamedQuery(name="VhNovacreditPeriodo.buscarPorId", query="SELECT c FROM VhNovacreditPeriodo c where c.id = :id"),
	@NamedQuery(name="VhNovacreditPeriodo.buscarTodos", query="SELECT c FROM VhNovacreditPeriodo c"),
	@NamedQuery(name="VhNovacreditPeriodo.buscarPorAnio", query="SELECT c FROM VhNovacreditPeriodo c where c.anios = :anios")
})
public class VhNovacreditPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private int anios;

	private double depreciacion;

	public VhNovacreditPeriodo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAnios() {
		return this.anios;
	}

	public void setAnios(int anios) {
		this.anios = anios;
	}

	public double getDepreciacion() {
		return this.depreciacion;
	}

	public void setDepreciacion(double depreciacion) {
		this.depreciacion = depreciacion;
	}

}