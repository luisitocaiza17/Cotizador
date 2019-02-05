package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derecho_emision database table.
 * 
 */
@Entity
@Table(name="DERECHO_EMISION")
@NamedQuery(name="DerechoEmision.BuscarTodos", query="SELECT d FROM DerechoEmision d")
public class DerechoEmision implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String nombre;

	@Column(name="prima_desde")
	private String primaDesde;

	@Column(name="prima_hasta")
	private String primaHasta;

	private String valor;

	public DerechoEmision() {
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

	public String getPrimaDesde() {
		return this.primaDesde;
	}

	public void setPrimaDesde(String primaDesde) {
		this.primaDesde = primaDesde;
	}

	public String getPrimaHasta() {
		return this.primaHasta;
	}

	public void setPrimaHasta(String primaHasta) {
		this.primaHasta = primaHasta;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}