package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_GRUPOPRODUCTO_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="PYME_GRUPOPRODUCTO_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="PymeGrupoproductoProducto.findAll", query="SELECT p FROM PymeGrupoproductoProducto p"),
	@NamedQuery(name="PymeGrupoproductoProducto.buscarPorGrupoProducto", query="SELECT p FROM PymeGrupoproductoProducto p WHERE p.grupoProductoId = :grupoProductoId"),
	@NamedQuery(name="PymeGrupoproductoProducto.buscarPorId", query="SELECT p FROM PymeGrupoproductoProducto p WHERE p.id = :id")
})
@NamedQuery(name="PymeGrupoproductoProducto.findAll", query="SELECT p FROM PymeGrupoproductoProducto p")
public class PymeGrupoproductoProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name="grupo_producto_id")
	private BigInteger grupoProductoId;

	@Column(name="producto_id")
	private BigInteger productoId;

	public PymeGrupoproductoProducto() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getGrupoProductoId() {
		return this.grupoProductoId;
	}

	public void setGrupoProductoId(BigInteger grupoProductoId) {
		this.grupoProductoId = grupoProductoId;
	}

	public BigInteger getProductoId() {
		return this.productoId;
	}

	public void setProductoId(BigInteger productoId) {
		this.productoId = productoId;
	}

}