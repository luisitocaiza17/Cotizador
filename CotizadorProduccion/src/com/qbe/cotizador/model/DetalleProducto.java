package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the DETALLE_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="DETALLE_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="DetalleProducto.buscarPorId", query="SELECT c FROM DetalleProducto c where c.id = :id"),
	@NamedQuery(name="DetalleProducto.buscarTodos", query="SELECT c FROM DetalleProducto c"),
	@NamedQuery(name="DetalleProducto.buscarPorCobertura", query="SELECT c FROM DetalleProducto c WHERE c.coberturasConjunto =:coberturasConjunto"),
	@NamedQuery(name="DetalleProducto.buscarPorCoberturaPlanNull", query="SELECT c FROM DetalleProducto c WHERE c.plan=null and c.coberturasConjunto =:coberturasConjunto"),
	@NamedQuery(name="DetalleProducto.buscarPorConfiguracionProducto", query="SELECT c FROM DetalleProducto c WHERE c.configuracionProducto =:configuracionProducto"),
	@NamedQuery(name="DetalleProducto.buscarPorConfiguracionConjuntoPlan", query="SELECT c FROM DetalleProducto c WHERE c.configuracionProducto =:configuracionProducto and c.coberturasConjunto =:coberturasConjunto and c.plan =:plan"),
	@NamedQuery(name="DetalleProducto.buscarPorRamoDetalleProducto", query="SELECT dp FROM DetalleProducto dp,ConfiguracionProducto cp,Producto p,Ramo r WHERE dp.configuracionProducto.id =cp.id and cp.producto.id=p.id and p.ramoId=r.id and r.nemotecnico=:ramoNemotecnico ")
})
public class DetalleProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Column(name="afecta_prima")
	private String afectaPrima;

	@Column(name="afecta_valor_asegurado")
	private String afectaValorAsegurado;

	private String defecto;

	private double monto;

	private int periodicidad;

	private double porccomisionvendedor;

	@Column(name="porcentaje_comision")
	private double porcentajeComision;

	private double porcotros;

	private double porcutilidad;

	private double prima;

	@Column(name="prima_basica")
	private double primaBasica;

	private double tasa;
	
	@Column(name="activado_por_esb")
    private String activadoPorEsb;

  @Lob
	private byte[] texto;

	@Column(name="valor_periodo")
	private double valorPeriodo;

	//bi-directional many-to-one association to ConfiguracionProducto
	@ManyToOne
	@JoinColumn(name="config_producto_id")
	private ConfiguracionProducto configuracionProducto;

	//bi-directional many-to-one association to Plan
	@ManyToOne
	private Plan plan;

	//bi-directional many-to-one association to CoberturasConjunto
	@ManyToOne
	@JoinColumn(name="coberturas_conj_id")
	private CoberturasConjunto coberturasConjunto;

	public DetalleProducto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAfectaPrima() {
		return this.afectaPrima;
	}

	public void setAfectaPrima(String afectaPrima) {
		this.afectaPrima = afectaPrima;
	}

	public String getAfectaValorAsegurado() {
		return this.afectaValorAsegurado;
	}

	public void setAfectaValorAsegurado(String afectaValorAsegurado) {
		this.afectaValorAsegurado = afectaValorAsegurado;
	}

	public String getDefecto() {
		return this.defecto;
	}

	public void setDefecto(String defecto) {
		this.defecto = defecto;
	}

	public double getMonto() {
		return this.monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getPeriodicidad() {
		return this.periodicidad;
	}

	public void setPeriodicidad(int periodicidad) {
		this.periodicidad = periodicidad;
	}

	public double getPorccomisionvendedor() {
		return this.porccomisionvendedor;
	}

	public void setPorccomisionvendedor(double porccomisionvendedor) {
		this.porccomisionvendedor = porccomisionvendedor;
	}

	public double getPorcentajeComision() {
		return this.porcentajeComision;
	}

	public void setPorcentajeComision(double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public double getPorcotros() {
		return this.porcotros;
	}

	public void setPorcotros(double porcotros) {
		this.porcotros = porcotros;
	}

	public double getPorcutilidad() {
		return this.porcutilidad;
	}

	public void setPorcutilidad(double porcutilidad) {
		this.porcutilidad = porcutilidad;
	}

	public double getPrima() {
		return this.prima;
	}

	public void setPrima(double prima) {
		this.prima = prima;
	}

	public double getPrimaBasica() {
		return this.primaBasica;
	}

	public void setPrimaBasica(double primaBasica) {
		this.primaBasica = primaBasica;
	}
	
	public String getActivadoPorEsb() {
	    return activadoPorEsb;
	 }

	 public void setActivadoPorEsb(String activadoPorEsb) {
	    this.activadoPorEsb = activadoPorEsb;
	 }

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}

	public double getValorPeriodo() {
		return this.valorPeriodo;
	}

	public void setValorPeriodo(double valorPeriodo) {
		this.valorPeriodo = valorPeriodo;
	}

	public ConfiguracionProducto getConfiguracionProducto() {
		return this.configuracionProducto;
	}

	public void setConfiguracionProducto(ConfiguracionProducto configuracionProducto) {
		this.configuracionProducto = configuracionProducto;
	}

	public Plan getPlan() {
		return this.plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public CoberturasConjunto getCoberturasConjunto() {
		return this.coberturasConjunto;
	}

	public void setCoberturasConjunto(CoberturasConjunto coberturasConjunto) {
		this.coberturasConjunto = coberturasConjunto;
	}

}