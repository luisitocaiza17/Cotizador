package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the COBERTURAS_CONJUNTO database table.
 * 
 */
@Entity
@Table(name="COBERTURAS_CONJUNTO")
@NamedQueries({
	@NamedQuery(name="CoberturasConjunto.buscarPorId", query="SELECT c FROM CoberturasConjunto c where c.id = :id"),
	@NamedQuery(name="CoberturasConjunto.buscarTodos", query="SELECT c FROM CoberturasConjunto c"),
	@NamedQuery(name="CoberturasConjunto.buscarPorCobertura", query="SELECT c FROM CoberturasConjunto c where c.cobertura =:cobertura"),
    @NamedQuery(name="CoberturasConjunto.eliminarTodos", query="delete FROM CoberturasConjunto "),
    @NamedQuery(name="CoberturasConjunto.buscarPorRamoCoberturasConjunto", query="SELECT cc FROM DetalleProducto dp,ConfiguracionProducto cp,Producto p,Ramo r,CoberturasConjunto cc WHERE dp.configuracionProducto.id=cp.id and cp.producto.id=p.id and p.ramoId=r.id and r.nemotecnico=:ramoNemotecnico and dp.coberturasConjunto.id=cc.id") 
})
public class CoberturasConjunto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	//bi-directional many-to-one association to ConjuntoCobertura
	@ManyToOne
	@JoinColumn(name="conjunto_cober_id")
	private ConjuntoCobertura conjuntoCobertura;

	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to DetalleProducto
	@OneToMany(mappedBy="coberturasConjunto")
	private List<DetalleProducto> detalleProductos;

	public CoberturasConjunto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ConjuntoCobertura getConjuntoCobertura() {
		return this.conjuntoCobertura;
	}

	public void setConjuntoCobertura(ConjuntoCobertura conjuntoCobertura) {
		this.conjuntoCobertura = conjuntoCobertura;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public List<DetalleProducto> getDetalleProductos() {
		return this.detalleProductos;
	}

	public void setDetalleProductos(List<DetalleProducto> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}

	public DetalleProducto addDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().add(detalleProducto);
		detalleProducto.setCoberturasConjunto(this);

		return detalleProducto;
	}

	public DetalleProducto removeDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().remove(detalleProducto);
		detalleProducto.setCoberturasConjunto(null);

		return detalleProducto;
	}

}