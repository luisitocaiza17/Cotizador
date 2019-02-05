package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the DEDUCIBLE_GANADERO database table.
 * 
 */
@Entity
@Table(name="DEDUCIBLE_GANADERO")
@NamedQueries({
	@NamedQuery(name="DeducibleGanadero.buscarPorId", query="SELECT c FROM  DeducibleGanadero c where c.id = :id"),
	@NamedQuery(name="DeducibleGanadero.buscarTodos", query="SELECT c FROM DeducibleGanadero c ORDER BY c.categoria, c.tipoProduccion, c.numeroSiniestro ASC"),
	@NamedQuery(name="DeducibleGanadero.buscarPorNumeroAnimale", query="SELECT c FROM DeducibleGanadero c where c.tipoProduccion = :tipoProduccionId")
})
public class DeducibleGanadero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String categoria;

	private int deducible;

	@Column(name="NUMERO_SINIESTRO")
	private String numeroSiniestro;

	@Column(name="PAGO_SINIESTRO")
	private int pagoSiniestro;

	@Column(name="RANGO_FINAL")
	private int rangoFinal;

	@Column(name="RANGO_INICIAL")
	private int rangoInicial;

	@Column(name="TIPO_PRODUCCION")
	private String tipoProduccion;

	@Column(name="VALOR_PRIMA")
	private double valorPrima;

	public DeducibleGanadero() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getDeducible() {
		return this.deducible;
	}

	public void setDeducible(int deducible) {
		this.deducible = deducible;
	}

	public String getNumeroSiniestro() {
		return this.numeroSiniestro;
	}

	public void setNumeroSiniestro(String numeroSiniestro) {
		this.numeroSiniestro = numeroSiniestro;
	}

	public int getPagoSiniestro() {
		return this.pagoSiniestro;
	}

	public void setPagoSiniestro(int pagoSiniestro) {
		this.pagoSiniestro = pagoSiniestro;
	}

	public int getRangoFinal() {
		return this.rangoFinal;
	}

	public void setRangoFinal(int rangoFinal) {
		this.rangoFinal = rangoFinal;
	}

	public int getRangoInicial() {
		return this.rangoInicial;
	}

	public void setRangoInicial(int rangoInicial) {
		this.rangoInicial = rangoInicial;
	}

	public String getTipoProduccion() {
		return this.tipoProduccion;
	}

	public void setTipoProduccion(String tipoProduccion) {
		this.tipoProduccion = tipoProduccion;
	}

	public double getValorPrima() {
		return this.valorPrima;
	}

	public void setValorPrima(double valorPrima) {
		this.valorPrima = valorPrima;
	}

}