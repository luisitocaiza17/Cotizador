package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the FIRMAS_DIGITALES database table.
 * 
 */
@Entity
@Table(name="FIRMAS_DIGITALES")
@NamedQueries({
	@NamedQuery(name="FirmasDigitales.buscarPorId", query="SELECT c FROM FirmasDigitales c where c.id = :id"),
	@NamedQuery(name="FirmasDigitales.buscarTodos", query="SELECT c FROM FirmasDigitales c"),
	@NamedQuery(name="FirmasDigitales.buscarPorRamoSucursalEntidad", query="SELECT c FROM FirmasDigitales c where c.ramo = :ramo and c.sucursal = :sucursal and c.entidad = :entidad")
})
public class FirmasDigitales implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Lob
	private byte[] firma;
	
	@Column(name="id_ensurance")
	private String idEnsurance;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	@JoinColumn(name="ramo_id")
	private Ramo ramo;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="sucursal_id")
	private Sucursal sucursal;

	//bi-directional many-to-one association to UnidadNegocio
	@ManyToOne
	@JoinColumn(name="entidad_id")
	private Entidad entidad;

	public FirmasDigitales() {
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

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public String getIdEnsurance() {
		return this.idEnsurance;
	}

	public void setIdEnsurance(String idEnsurance) {
		this.idEnsurance = idEnsurance;
	}

}