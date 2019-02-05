package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the FIRMAS_DIGITALES database table.
 * 
 */
@Entity
@Table(name="FIRMAS_SUCURSAL")
@NamedQueries({
	@NamedQuery(name="FirmasSucursal.buscarPorId", query="SELECT c FROM FirmasSucursal c where c.id = :id"),
	@NamedQuery(name="FirmasSucursal.buscarTodos", query="SELECT c FROM FirmasSucursal c"),
	@NamedQuery(name="FirmasSucursal.buscarPorSucursal", query="SELECT c FROM FirmasSucursal c where c.sucursal = :sucursal and c.defecto = :defecto")
})
public class FirmasSucursal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private byte[] firma;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="sucursal_id")
	private Sucursal sucursal;

	@Column(name="entidad_id")
	private String entidadId;

	@Column(name="usuarioactualiza")
	private String usuarioactualiza;
	
	@Column(name="defecto")
	private String defecto;
	
	public FirmasSucursal() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFirma() {
		return this.firma;
	}

	public void setFirma(byte[] firma) {
		this.firma = firma;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}

	public String getUsuarioactualiza() {
		return usuarioactualiza;
	}

	public void setUsuarioactualiza(String usuarioactualiza) {
		this.usuarioactualiza = usuarioactualiza;
	}

	public String getDefecto() {
		return defecto;
	}

	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}

}