package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the VH_NOVACREDIT_TIPO_TASA database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_TIPO_TASA")
@NamedQueries({
	@NamedQuery(name="VhNovacreditTipoTasa.buscarPorId", query="SELECT c FROM VhNovacreditTipoTasa c where c.id = :id"),
	@NamedQuery(name="VhNovacreditTipoTasa.buscarTodos", query="SELECT c FROM VhNovacreditTipoTasa c")
})
public class VhNovacreditTipoTasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String nombre;

	//bi-directional many-to-one association to VhNovacreditTasa
	@OneToMany(mappedBy="vhNovacreditTipoTasa", fetch=FetchType.EAGER)
	private List<VhNovacreditTasa> vhNovacreditTasas;

	public VhNovacreditTipoTasa() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<VhNovacreditTasa> getVhNovacreditTasas() {
		return this.vhNovacreditTasas;
	}

	public void setVhNovacreditTasas(List<VhNovacreditTasa> vhNovacreditTasas) {
		this.vhNovacreditTasas = vhNovacreditTasas;
	}

	public VhNovacreditTasa addVhNovacreditTasa(VhNovacreditTasa vhNovacreditTasa) {
		getVhNovacreditTasas().add(vhNovacreditTasa);
		vhNovacreditTasa.setVhNovacreditTipoTasa(this);

		return vhNovacreditTasa;
	}

	public VhNovacreditTasa removeVhNovacreditTasa(VhNovacreditTasa vhNovacreditTasa) {
		getVhNovacreditTasas().remove(vhNovacreditTasa);
		vhNovacreditTasa.setVhNovacreditTipoTasa(null);

		return vhNovacreditTasa;
	}

}