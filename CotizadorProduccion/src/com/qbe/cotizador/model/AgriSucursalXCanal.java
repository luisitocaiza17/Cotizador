package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;



/**
 * The persistent class for the AGRI_TIPO_CULTIVO database table.
 * 
 */
@Entity
@Table(name="AGRI_SUCURSAL_X_CANAL")
@NamedQueries({
	@NamedQuery(name="AgriSucursalXCanal.findAll", query="SELECT a FROM AgriSucursalXCanal a"),
	@NamedQuery(name="AgriSucursalXCanal.buscarPorId", query="SELECT c FROM AgriSucursalXCanal c where c.sucursalCanalId = :sucursalCanalId"),		
	@NamedQuery(name="AgriSucursalXCanal.buscarCanalIntegracion", query="SELECT c FROM AgriSucursalXCanal c where c.sucursalIntegracion = :sucursalIntegracion and c.canalId = :canalId"),		
	@NamedQuery(name="AgriSucursalXCanal.buscarPorCanalId", query="SELECT c FROM AgriSucursalXCanal c where c.canalId = :canalId")
})

public class AgriSucursalXCanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUCURSAL_CANAL_ID")
	private BigInteger sucursalCanalId;

	@Column(name="CANAL_ID")
	private BigInteger canalId;

	@Column(name="SUCURSAL_ID")
	private BigInteger sucursalId;
	
	@Column(name="SUCURSAL_INTEGRACION")
	private String sucursalIntegracion;
	
	
	public String getSucursalIntegracion() {
		return sucursalIntegracion;
	}

	public void setSucursalIntegracion(String sucursalIntegracion) {
		this.sucursalIntegracion = sucursalIntegracion;
	}

	public BigInteger getSucursalCanalId() {
		return sucursalCanalId;
	}

	public void setSucursalCanalId(BigInteger sucursalCanalId) {
		this.sucursalCanalId = sucursalCanalId;
	}

	public BigInteger getCanalId() {
		return canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public BigInteger getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(BigInteger sucursalId) {
		this.sucursalId = sucursalId;
	}

}