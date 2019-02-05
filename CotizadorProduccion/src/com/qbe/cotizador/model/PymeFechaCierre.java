package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PYME_EXT_RAMO database table.
 * 
 */
@Entity
@Table(name="PYME_FECHA_CIERRE")
@NamedQueries({
	@NamedQuery(name="PymeFechaCierre.buscarTodos", query="SELECT p FROM PymeFechaCierre p"),
	@NamedQuery(name="PymeFechaCierre.buscarPorId", query="SELECT p FROM PymeFechaCierre p where p.fechaCierreId = :fechaCierreId")
})
public class PymeFechaCierre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FECHA_CIERRE_ID")
	private BigInteger fechaCierreId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_CIERRE")
	private Date fechaCierre;

	public BigInteger getFechaCierreId() {
		return fechaCierreId;
	}

	public void setFechaCierreId(BigInteger fechaCierreId) {
		this.fechaCierreId = fechaCierreId;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
}