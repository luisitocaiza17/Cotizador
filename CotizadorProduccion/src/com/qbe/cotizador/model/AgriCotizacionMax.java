package com.qbe.cotizador.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="AGRI_COTIZACION_MAX")
@NamedQueries({
	@NamedQuery(name="AgriCotizacionMax.buscar", query="SELECT p FROM AgriCotizacionMax p")
})
public class AgriCotizacionMax {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private BigInteger id;
	
	@Column(name="MAXIMO")
	private BigInteger maximo;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getMaximo() {
		return maximo;
	}

	public void setMaximo(BigInteger maximo) {
		this.maximo = maximo;
	}

	
}
