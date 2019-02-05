package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Producto.buscarPorId", query="SELECT c FROM Producto c where c.id=:id"),
	@NamedQuery(name="Producto.buscarActivos", query="SELECT c FROM Producto c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Producto.buscarPorNemotecnico", query="SELECT c FROM Producto c where c.nemotecnico = :nemotecnico"),
	@NamedQuery(name="Producto.buscarTodos", query="SELECT c FROM Producto c order by c.nombre"),
	@NamedQuery(name="Producto.buscarPorNombre", query="SELECT c FROM Producto c where c.nombre = :nombre"),
	@NamedQuery(name="Producto.buscarPorRamo", query="SELECT c FROM Producto c where c.ramoId =:ramoId"),
	@NamedQuery(name="Producto.buscarPorIdRamo", query="SELECT c FROM Producto c where c.id =:id And c.ramoId =:ramoId"),
	@NamedQuery(name="Producto.buscarPorNombreRamo", query="SELECT c FROM Producto c where c.nombre = :nombre and c.ramoId =:ramoId")
})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private boolean activo;

	private String defecto;

	private String dinamico;

	private String nemotecnico;

	private String nombre;

	@Column(name="ramo_id")
	private BigInteger ramoId;

	private int vigencia;

	//bi-directional many-to-many association to Agente
	@ManyToMany(mappedBy="productos")
	private List<Agente> agentes;

	//bi-directional many-to-one association to ConfiguracionProducto
	@OneToMany(mappedBy="producto")
	private List<ConfiguracionProducto> configuracionProductos;

	//bi-directional many-to-one association to Cotizacion
	@OneToMany(mappedBy="producto")
	private List<Cotizacion> cotizacions;

	//bi-directional many-to-one association to GrupoPorProducto
	@OneToMany(mappedBy="producto")
	private List<GrupoPorProducto> grupoPorProductos;

	public Producto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getDefecto() {
		return this.defecto;
	}

	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}

	public String getDinamico() {
		return this.dinamico;
	}

	public void setDinamico(String dinamico) {
		this.dinamico = dinamico;
	}

	public String getNemotecnico() {
		return this.nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getRamoId() {
		return this.ramoId;
	}

	public void setRamoId(BigInteger ramoId) {
		this.ramoId = ramoId;
	}

	public int getVigencia() {
		return this.vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public List<Agente> getAgentes() {
		return this.agentes;
	}

	public void setAgentes(List<Agente> agentes) {
		this.agentes = agentes;
	}

	public List<ConfiguracionProducto> getConfiguracionProductos() {
		return this.configuracionProductos;
	}

	public void setConfiguracionProductos(List<ConfiguracionProducto> configuracionProductos) {
		this.configuracionProductos = configuracionProductos;
	}

	public ConfiguracionProducto addConfiguracionProducto(ConfiguracionProducto configuracionProducto) {
		getConfiguracionProductos().add(configuracionProducto);
		configuracionProducto.setProducto(this);

		return configuracionProducto;
	}

	public ConfiguracionProducto removeConfiguracionProducto(ConfiguracionProducto configuracionProducto) {
		getConfiguracionProductos().remove(configuracionProducto);
		configuracionProducto.setProducto(null);

		return configuracionProducto;
	}

	public List<Cotizacion> getCotizacions() {
		return this.cotizacions;
	}

	public void setCotizacions(List<Cotizacion> cotizacions) {
		this.cotizacions = cotizacions;
	}

	public Cotizacion addCotizacion(Cotizacion cotizacion) {
		getCotizacions().add(cotizacion);
		cotizacion.setProducto(this);

		return cotizacion;
	}

	public Cotizacion removeCotizacion(Cotizacion cotizacion) {
		getCotizacions().remove(cotizacion);
		cotizacion.setProducto(null);

		return cotizacion;
	}

	public List<GrupoPorProducto> getGrupoPorProductos() {
		return this.grupoPorProductos;
	}

	public void setGrupoPorProductos(List<GrupoPorProducto> grupoPorProductos) {
		this.grupoPorProductos = grupoPorProductos;
	}

	public GrupoPorProducto addGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().add(grupoPorProducto);
		grupoPorProducto.setProducto(this);

		return grupoPorProducto;
	}

	public GrupoPorProducto removeGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		getGrupoPorProductos().remove(grupoPorProducto);
		grupoPorProducto.setProducto(null);

		return grupoPorProducto;
	}

}