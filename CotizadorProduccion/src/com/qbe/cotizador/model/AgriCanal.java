package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="AGRI_CANAL")
@NamedQueries({
	@NamedQuery(name="AgriCanal.findAll", query="SELECT a FROM AgriCanal a"),
	@NamedQuery(name="AgriCanal.obtenerPorId", query="SELECT a FROM AgriCanal a where a.canalId=:canalId"),
	@NamedQuery(name="AgriCanal.obtenerPorNombre", query="SELECT a FROM AgriCanal a where a.nombre=:nombre")
})
public class AgriCanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CANAL_ID")
	private BigInteger canalId;

	private String nombre;

	public BigInteger getCanalId() {
		return canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}