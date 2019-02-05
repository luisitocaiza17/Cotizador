package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the deducibles database table.
 * 
 */
@Entity
@Table(name="DEDUCIBLES")
@NamedQueries({
	@NamedQuery(name="Deducible.buscarPorId", query="SELECT c FROM Deducible c where c.id = :id"),
	@NamedQuery(name="Deducible.buscarTodos", query="SELECT c FROM Deducible c"),
	@NamedQuery(name="Deducible.buscarPorProductoDeducible", query="SELECT c FROM Deducible c where c.deducibleId = :deducibleId and c.productoId = :productoId"),
	@NamedQuery(name="Deducible.buscarPorProductoDeducibleLista", query="SELECT c FROM Deducible c where c.productoId = :producto"),
	@NamedQuery(name="Deducible.buscarPorCoberturaPlanDeducible", query="SELECT c FROM Deducible c where c.deducibleId = :deducibleId and c.coberturaId = :coberturaId and c.planId = :planId and c.productoId = :productoId")
})
public class Deducible implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="cobertura_id")
	private String coberturaId;

	@Column(name="deducible_id")
	private String deducibleId;

	@Column(name="orden_presentacion")
	private int ordenPresentacion;

	@Column(name="plan_id")
	private String planId;

	@Column(name="producto_id")
	private String productoId;

	private String texto;

	@Column(name="tipo_deducible_id")
	private java.math.BigInteger tipoDeducibleId;

	private double valor;

	public Deducible() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoberturaId() {
		return this.coberturaId;
	}

	public void setCoberturaId(String coberturaId) {
		this.coberturaId = coberturaId;
	}

	public String getDeducibleId() {
		return this.deducibleId;
	}

	public void setDeducibleId(String deducibleId) {
		this.deducibleId = deducibleId;
	}

	public int getOrdenPresentacion() {
		return this.ordenPresentacion;
	}

	public void setOrdenPresentacion(int ordenPresentacion) {
		this.ordenPresentacion = ordenPresentacion;
	}

	public String getPlanId() {
		return this.planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getProductoId() {
		return this.productoId;
	}

	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public java.math.BigInteger getTipoDeducibleId() {
		return this.tipoDeducibleId;
	}

	public void setTipoDeducibleId(java.math.BigInteger tipoDeducibleId) {
		this.tipoDeducibleId = tipoDeducibleId;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}