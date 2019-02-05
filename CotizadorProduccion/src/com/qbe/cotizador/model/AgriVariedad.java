package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the AGRI_VARIEDAD database table.
 * 
 */
@Entity
@Table(name="AGRI_VARIEDAD")
@NamedQueries({
		@NamedQuery(name="AgriVariedad.findAll", query="SELECT a FROM AgriVariedad a"),
		@NamedQuery(name="AgriVariedad.buscarPorId", query="SELECT a FROM AgriVariedad a where a.variedadId=:variedadId"),
		@NamedQuery(name="AgriVariedad.buscarPorTipoCultivoId", query="SELECT a FROM AgriVariedad a where a.tipoCultivoId=:tipoCultivoId")
})
public class AgriVariedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VARIEDAD_ID")
	private BigInteger variedadId;

	private String nombre;

	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;

	public AgriVariedad() {
	}

	public BigInteger getVariedadId() {
		return this.variedadId;
	}

	public void setVariedadId(BigInteger variedadId) {
		this.variedadId = variedadId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getTipoCultivoId() {
		return this.tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

}