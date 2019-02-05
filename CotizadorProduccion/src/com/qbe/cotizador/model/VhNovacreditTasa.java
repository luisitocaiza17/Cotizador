package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the VH_NOVACREDIT_TASA database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_TASA")
@NamedQueries({
	@NamedQuery(name="VhNovacreditTasa.buscarPorId", query="SELECT c FROM VhNovacreditTasa c where c.id = :id"),
	@NamedQuery(name="VhNovacreditTasa.buscarTodos", query="SELECT c FROM VhNovacreditTasa c"),
	@NamedQuery(name="VhNovacreditTasa.buscarPorTipoCoberturaTipoTasa", query="SELECT c FROM VhNovacreditTasa c where c.vhNovacreditTipoTasa = :vhNovacreditTipoTasa and c.vhNovacreditTipoCobertura = :vhNovacreditTipoCobertura"),
})
public class VhNovacreditTasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private double tasa;

	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_cobertura_id")
	private VhNovacreditTipoCobertura vhNovacreditTipoCobertura;

	//bi-directional many-to-one association to VhNovacreditTipoTasa
	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_tasa_id")
	private VhNovacreditTipoTasa vhNovacreditTipoTasa;

	public VhNovacreditTasa() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
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