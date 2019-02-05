package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the OBJETO_GANADERO_DETALLE database table.
 * 
 */
@Entity
@Table(name="OBJETO_GANADERO_DETALLE")
@NamedQueries({
	@NamedQuery(name="ObjetoGanaderoDetalle.buscarPorObjetoGanadero", query="SELECT c FROM ObjetoGanaderoDetalle c where c.objetoGanaderoId = :id")
})
public class ObjetoGanaderoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String color;

	private int edad;

	@Column(name="NUMERO_ARETE")
	private String numeroArete;

	@Column(name="NUMERO_CHIP")
	private String numeroChip;

	@Column(name="OBJETO_GANADERO_ID")
	private BigInteger objetoGanaderoId;

	private String origen;

	private String procedencia;

	@Column(name="RAZA_ID")
	private String razaId;

	private double tasa;

	@Column(name="TIPO_ID")
	private String tipoId;

	@Column(name="VALOR_ASEGURAR")
	private float valorAsegurar;

	@Column(name="VALOR_PRIMA")
	private double valorPrima;

	public ObjetoGanaderoDetalle() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getEdad() {
		return this.edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNumeroArete() {
		return this.numeroArete;
	}

	public void setNumeroArete(String numeroArete) {
		this.numeroArete = numeroArete;
	}

	public String getNumeroChip() {
		return this.numeroChip;
	}

	public void setNumeroChip(String numeroChip) {
		this.numeroChip = numeroChip;
	}

	public BigInteger getObjetoGanaderoId() {
		return this.objetoGanaderoId;
	}

	public void setObjetoGanaderoId(BigInteger objetoGanaderoId) {
		this.objetoGanaderoId = objetoGanaderoId;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getProcedencia() {
		return this.procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getRazaId() {
		return this.razaId;
	}

	public void setRazaId(String razaId) {
		this.razaId = razaId;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getTipoId() {
		return this.tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public float getValorAsegurar() {
		return this.valorAsegurar;
	}

	public void setValorAsegurar(float valorAsegurar) {
		this.valorAsegurar = valorAsegurar;
	}

	public double getValorPrima() {
		return this.valorPrima;
	}

	public void setValorPrima(double valorPrima) {
		this.valorPrima = valorPrima;
	}

}