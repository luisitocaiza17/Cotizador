package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the PYME_TEXTO_GRUPO_COBERTURA database table.
 * 
 */
@Entity
@Table(name="PYME_TEXTO_GRUPO_COBERTURA")
@NamedQueries({
	@NamedQuery(name="PymeTextoGrupoCobertura.buscarTodos", query="SELECT c FROM PymeTextoGrupoCobertura c"),
	@NamedQuery(name="PymeTextoGrupoCobertura.buscarPorId", query="SELECT c FROM PymeTextoGrupoCobertura c where c.textoGrupoCoberturaId = :textoGrupoCobertura"),
	@NamedQuery(name="PymeTextoGrupoCobertura.buscarPorRamoId", query="SELECT c FROM PymeTextoGrupoCobertura c where c.ramoId=:ramoId"),
	@NamedQuery(name="PymeTextoGrupoCobertura.buscarGrupoCoberturaId", query="SELECT c FROM PymeTextoGrupoCobertura c where c.grupoCoberturaId=:grupoCoberturaId"),
})
public class PymeTextoGrupoCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEXTO_GRUPO_COBERTURA_ID")
	private String textoGrupoCoberturaId;

	@Column(name="GRUPO_COBERTURA_ID")
	private BigInteger grupoCoberturaId;

	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;
	
	@Column(name="PRODUCTO_ENS_ID")
	private String productoEnsId;

	@Column(name="RAMO_ID")
	private String ramoId;

	@Lob
	@Column(name="TEXTO")
	private byte[] texto;

	public PymeTextoGrupoCobertura() {
	}

	public String getTextoGrupoCoberturaId() {
		return this.textoGrupoCoberturaId;
	}

	public void setTextoGrupoCoberturaId(String textoGrupoCobertura) {
		this.textoGrupoCoberturaId = textoGrupoCobertura;
	}

	public BigInteger getGrupoCoberturaId() {
		return this.grupoCoberturaId;
	}

	public void setGrupoCoberturaId(BigInteger grupoCoberturaId) {
		this.grupoCoberturaId = grupoCoberturaId;
	}

	public String getRamoId() {
		return this.ramoId;
	}

	public void setRamoId(String ramoId) {
		this.ramoId = ramoId;
	}

	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}

	public BigInteger getGrupoPorProductoId() {
		return grupoPorProductoId;
	}

	public void setGrupoPorProductoId(BigInteger grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public String getProductoEnsId() {
		return productoEnsId;
	}

	public void setProductoEnsId(String productoEnsId) {
		this.productoEnsId = productoEnsId;
	}
}