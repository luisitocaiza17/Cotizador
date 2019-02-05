package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the AGRI_TIPO_CALCULO database table.
 * 
 */
@Entity
@Table(name="AGRI_TIPO_CALCULO")
@NamedQueries({
	@NamedQuery(name="AgriTipoCalculo.findAll", query="SELECT a FROM AgriTipoCalculo a"),
	@NamedQuery(name="AgriTipoCalculo.buscarPorId", query="SELECT c FROM AgriTipoCalculo c where c.tipoCalculoId=:tipoCalculoId"),
	@NamedQuery(name="AgriTipoCalculo.buscarPorNombre", query="SELECT c FROM AgriTipoCalculo c where c.nombre=:nombre")
})

public class AgriTipoCalculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;

	private String nombre;

	public AgriTipoCalculo() {
	}

	public BigInteger getTipoCalculoId() {
		return this.tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}