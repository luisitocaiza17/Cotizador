package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the VH_NOVACREDIT_TEXTO database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_TEXTO")
@NamedQueries({
	@NamedQuery(name="VhNovacreditTexto.buscarPorId", query="SELECT c FROM VhNovacreditTexto c where c.id = :id"),
	@NamedQuery(name="VhNovacreditTexto.buscarTodos", query="SELECT c FROM VhNovacreditTexto c"),
	@NamedQuery(name="VhNovacreditTexto.buscarPorTipoCoberturaTipoTasa", query="SELECT c FROM VhNovacreditTexto c where c.vhNovacreditTipoTasa = :vhNovacreditTipoTasa and c.vhNovacreditTipoCobertura = :vhNovacreditTipoCobertura"),
})
public class VhNovacreditTexto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String asegurado;

	private double monto;

	private int orden;

	@Lob
	private byte[] texto;

	private String tipo;

	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_cobertura_id")
	private VhNovacreditTipoCobertura vhNovacreditTipoCobertura;

	//bi-directional many-to-one association to VhNovacreditTipoTasa
	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_tasa_id")
	private VhNovacreditTipoTasa vhNovacreditTipoTasa;

	public VhNovacreditTexto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsegurado() {
		return this.asegurado;
	}

	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public VhNovacreditTipoCobertura getVhNovaTipoCobertura() {
		return this.vhNovacreditTipoCobertura;
	}

	public void setVhNovaTipoCoberturaId(VhNovacreditTipoCobertura vhNovacreditTipoCobertura) {
		this.vhNovacreditTipoCobertura = vhNovacreditTipoCobertura;
	}

	public VhNovacreditTipoTasa getVhNovacreditTipoTasa() {
		return this.vhNovacreditTipoTasa;
	}

	public void setVhNovacreditTipoTasa(VhNovacreditTipoTasa vhNovacreditTipoTasa) {
		this.vhNovacreditTipoTasa = vhNovacreditTipoTasa;
	}

}