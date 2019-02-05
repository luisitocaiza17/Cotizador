package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the CONFIGURACION_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="CONFIGURACION_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="ConfiguracionProducto.buscarPorId", query="SELECT c FROM ConfiguracionProducto c WHERE c.id =:id"),
	@NamedQuery(name="ConfiguracionProducto.buscarPorProductosIN", query="SELECT c FROM ConfiguracionProducto c WHERE c.producto in :listProductos"),
	@NamedQuery(name="ConfiguracionProducto.buscarTodos", query="SELECT c FROM ConfiguracionProducto c"),
	@NamedQuery(name="ConfiguracionProducto.buscarPorProducto", query="SELECT c FROM ConfiguracionProducto c WHERE c.producto =:producto")
})
public class ConfiguracionProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String modificable;

	@Temporal(TemporalType.DATE)
	private Date vigenciadesde;

	@Temporal(TemporalType.DATE)
	private Date vigenciahasta;

	private String vigente;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	private Producto producto;

	//bi-directional many-to-one association to ConjuntoCobertura
	@OneToMany(mappedBy="configuracionProducto")
	private List<ConjuntoCobertura> conjuntoCoberturas;

	//bi-directional many-to-one association to DetalleProducto
	@OneToMany(mappedBy="configuracionProducto")
	private List<DetalleProducto> detalleProductos;

	public ConfiguracionProducto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getModificable() {
		return this.modificable;
	}

	public void setModificable(String modificable) {
		this.modificable = modificable;
	}

	public Date getVigenciadesde() {
		return this.vigenciadesde;
	}

	public void setVigenciadesde(Date vigenciadesde) {
		this.vigenciadesde = vigenciadesde;
	}

	public Date getVigenciahasta() {
		return this.vigenciahasta;
	}

	public void setVigenciahasta(Date vigenciahasta) {
		this.vigenciahasta = vigenciahasta;
	}

	public String getVigente() {
		return this.vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<ConjuntoCobertura> getConjuntoCoberturas() {
		return this.conjuntoCoberturas;
	}

	public void setConjuntoCoberturas(List<ConjuntoCobertura> conjuntoCoberturas) {
		this.conjuntoCoberturas = conjuntoCoberturas;
	}

	public ConjuntoCobertura addConjuntoCobertura(ConjuntoCobertura conjuntoCobertura) {
		getConjuntoCoberturas().add(conjuntoCobertura);
		conjuntoCobertura.setConfiguracionProducto(this);

		return conjuntoCobertura;
	}

	public ConjuntoCobertura removeConjuntoCobertura(ConjuntoCobertura conjuntoCobertura) {
		getConjuntoCoberturas().remove(conjuntoCobertura);
		conjuntoCobertura.setConfiguracionProducto(null);

		return conjuntoCobertura;
	}

	public List<DetalleProducto> getDetalleProductos() {
		return this.detalleProductos;
	}

	public void setDetalleProductos(List<DetalleProducto> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}

	public DetalleProducto addDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().add(detalleProducto);
		detalleProducto.setConfiguracionProducto(this);

		return detalleProducto;
	}

	public DetalleProducto removeDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().remove(detalleProducto);
		detalleProducto.setConfiguracionProducto(null);

		return detalleProducto;
	}

}