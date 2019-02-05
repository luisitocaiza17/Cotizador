package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * The persistent class for the COTIZACION_DETALLE database table.
 * 
 */
@Entity
@Table(name="COTIZACION_DETALLE")
@NamedQueries({
	@NamedQuery(name="CotizacionDetalle.buscarPorId", query="SELECT c FROM CotizacionDetalle c where c.id =:id"),
	@NamedQuery(name="CotizacionDetalle.buscarTodos", query="SELECT c FROM CotizacionDetalle c"),
	@NamedQuery(name="CotizacionDetalle.buscarCotizacionDetallePorCotizacion", query="SELECT c FROM CotizacionDetalle c WHERE c.cotizacion =:cotizacion"),
	@NamedQuery(name="CotizacionDetalle.buscarCotizacionesDetallePorObjetoId", query="SELECT c FROM CotizacionDetalle c WHERE c.objetoId =:objetoId"),
	@NamedQuery(name="CotizacionDetalle.buscarCotizacionDetalleIdYObjetoId", query="SELECT c FROM CotizacionDetalle c WHERE c.objetoId =:objetoId and c.cotizacion = :cotizacion"),
	@NamedQuery(name="CotizacionDetalle.buscarCotizacionesDetallePorObjetoIdTipoObjeto", query="SELECT c FROM CotizacionDetalle c WHERE c.objetoId =:objetoId and c.tipoObjetoId IN :tipoObjetoId ")
})

public class CotizacionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="objeto_id")
	private String objetoId;

	@Column(name="paquete_id")
	private BigInteger paqueteId;

	@Column(name="plan_valor")
	private BigInteger planValor;

	@Column(name="prima_neta_item")
	private double primaNetaItem;

	@Column(name="prima_neta_item_origen")
	private double primaNetaItemOrigen;

	@Column(name="suma_asegurada_item")
	private double sumaAseguradaItem;

	@Column(name="tasa")
	private double tasa;
	
	@Column(name="tasa_origen")
	private double tasaOrigen;

	@Column(name="necesita_inspeccion")
	private boolean necesitaInspeccion;

	@Column(name="tipo_objeto_id")
	private String tipoObjetoId;

	@Column(name="valor_extras")
	private double valorExtras;

	//bi-directional many-to-one association to CotizacionCobertura
	@OneToMany(mappedBy="cotizacionDetalle", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<CotizacionCobertura> cotizacionCoberturas;

	//bi-directional many-to-one association to Cotizacion
	@ManyToOne 
	private Cotizacion cotizacion;

	public CotizacionDetalle() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(String objetoId) {
		this.objetoId = objetoId;
	}

	public BigInteger getPaqueteId() {
		return this.paqueteId;
	}

	public void setPaqueteId(BigInteger paqueteId) {
		this.paqueteId = paqueteId;
	}

	public BigInteger getPlanValor() {
		return this.planValor;
	}

	public void setPlanValor(BigInteger planValor) {
		this.planValor = planValor;
	}

	public double getPrimaNetaItem() {
		return this.primaNetaItem;
	}

	public void setPrimaNetaItem(double primaNetaItem) {
		this.primaNetaItem = primaNetaItem;
	}

	public double getSumaAseguradaItem() {
		return this.sumaAseguradaItem;
	}

	public void setSumaAseguradaItem(double sumaAseguradaItem) {
		this.sumaAseguradaItem = sumaAseguradaItem;
	}

	public double getPrimaNetaItemOrigen() {
		return this.primaNetaItemOrigen;
	}

	public void setPrimaNetaItemOrigen(double primaNetaItemOrigen) {
		this.primaNetaItemOrigen = primaNetaItemOrigen;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public double getTasaOrigen() {
		return this.tasaOrigen;
	}

	public void setTasaOrigen(double tasaOrigen) {
		this.tasaOrigen = tasaOrigen;
	}

	public String getTipoObjetoId() {
		return this.tipoObjetoId;
	}

	public void setTipoObjetoId(String tipoObjetoId) {
		this.tipoObjetoId = tipoObjetoId;
	}

	public double getValorExtras() {
		return this.valorExtras;
	}

	public void setValorExtras(double valorExtras) {
		this.valorExtras = valorExtras;
	}

	public List<CotizacionCobertura> getCotizacionCoberturas() {
		return this.cotizacionCoberturas;
	}

	public void setCotizacionCoberturas(List<CotizacionCobertura> cotizacionCoberturas) {
		this.cotizacionCoberturas = cotizacionCoberturas;
	}

	public CotizacionCobertura addCotizacionCobertura(CotizacionCobertura cotizacionCobertura) {
		getCotizacionCoberturas().add(cotizacionCobertura);
		cotizacionCobertura.setCotizacionDetalle(this);

		return cotizacionCobertura;
	}

	public CotizacionCobertura removeCotizacionCobertura(CotizacionCobertura cotizacionCobertura) {
		getCotizacionCoberturas().remove(cotizacionCobertura);
		cotizacionCobertura.setCotizacionDetalle(null);

		return cotizacionCobertura;
	}

	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public boolean getNecesitaInspeccion() {
		return this.necesitaInspeccion;
	}

	public void setNecesitaInspeccion(boolean necesitaInspeccion) {
		this.necesitaInspeccion = necesitaInspeccion;
	}

}
