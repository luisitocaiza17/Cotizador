package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PRECIO_REFERENCIAL_GANADERO database table.
 * 
 */
@Entity
@Table(name="PRECIO_REFERENCIAL_GANADERO")
@NamedQueries({
	@NamedQuery(name="PrecioReferencialGanadero.buscarTodos", query="SELECT c FROM PrecioReferencialGanadero c ORDER BY c.tipoGanadoId, c.tipoProduccion, c.region DESC"),
	@NamedQuery(name="PrecioReferencialGanadero.buscarPorId", query="SELECT c FROM PrecioReferencialGanadero c where c.id = :id"),
	@NamedQuery(name="PrecioReferencialGanadero.buscarPorParametros", query="SELECT c FROM PrecioReferencialGanadero c where c.tipoGanadoId = :tipoGanadoId and c.tipoProduccion = :tipoProduccion and c.region = :region")
})
public class PrecioReferencialGanadero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="precio_maximo")
	private double precioMaximo;

	@Column(name="precio_minimo")
	private double precioMinimo;

	private String region;

	@Column(name="tipo_ganado_id")
	private String tipoGanadoId;

	@Column(name="tipo_produccion")
	private String tipoProduccion;

	public PrecioReferencialGanadero() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecioMaximo() {
		return this.precioMaximo;
	}

	public void setPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	public double getPrecioMinimo() {
		return this.precioMinimo;
	}

	public void setPrecioMinimo(double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTipoGanadoId() {
		return this.tipoGanadoId;
	}

	public void setTipoGanadoId(String tipoGanadoId) {
		this.tipoGanadoId = tipoGanadoId;
	}

	public String getTipoProduccion() {
		return this.tipoProduccion;
	}

	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

}