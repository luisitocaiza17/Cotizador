package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the COTIZACION_COBERTURA database table.
 * 
 */
@Entity
@Table(name="COTIZACION_COBERTURA")
@NamedQueries({
	@NamedQuery(name="CotizacionCobertura.buscarPorId", query="SELECT c FROM CotizacionCobertura c where c.id = :id"),
	@NamedQuery(name="CotizacionCobertura.buscarTodos", query="SELECT c FROM CotizacionCobertura c"),
	@NamedQuery(name="CotizacionCobertura.buscarPorCotizacionDetalleIdCoberturas", query="SELECT c FROM CotizacionCobertura c WHERE c.cotizacionDetalle =:cotizacionDetalle and c.cobertura in :ids"),
	@NamedQuery(name="CotizacionCobertura.buscarPorCotizacionDetalle", query="SELECT c FROM CotizacionCobertura c WHERE c.cotizacionDetalle =:cotizacionDetalle"),
	@NamedQuery(name="CotizacionCobertura.buscarCotizacionCoberturaPorCotizacionDetalle", query="SELECT c FROM CotizacionCobertura c WHERE c.cotizacionDetalle =:cotizacionDetalle")
})
public class CotizacionCobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	//bi-directional many-to-one association to CotizacionDetalle
	@ManyToOne
	@JoinColumn(name="cotizacion_detalle_id")
	private CotizacionDetalle cotizacionDetalle;

	@Column(name="monto_fijo")
	private double montoFijo;

	@Column(name="porcentaje_suma_asegurada")
	private double porcentajeSumaAsegurada;

	@Column(name="porcentaje_valor_siniestro")
	private double porcentajeValorSiniestro;

	@Column(name="valor_prima")
	private double valorPrima;

	@Column(name="valor_prima_origen")
	private double valorPrimaOrigen;

	@Column(name="valor_monto")
	private double valorMonto;

	@Column(name="valor_prima_otros")
	private double valorPrimaOtros;
	
	@Column(name="valor_prima_otros_iva")
	private double valorPrimaOtrosIva;
	
	//bi-directional many-to-one association to Cobertura
	@ManyToOne
	private Cobertura cobertura;

	//bi-directional many-to-one association to DetalleDesgloseCobertura
	@OneToMany(mappedBy="cotizacionCobertura")
	private List<DetalleDesgloseCobertura> detalleDesgloseCoberturas;

	public CotizacionCobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CotizacionDetalle getCotizacionDetalle() {
		return this.cotizacionDetalle;
	}

	public void setCotizacionDetalle(CotizacionDetalle cotizacionDetalle) {
		this.cotizacionDetalle = cotizacionDetalle;
	}
	
	public double getMontoFijo() {
		return this.montoFijo;
	}

	public void setMontoFijo(double montoFijo) {
		this.montoFijo = montoFijo;
	}

	public double getPorcentajeSumaAsegurada() {
		return this.porcentajeSumaAsegurada;
	}

	public void setPorcentajeSumaAsegurada(double porcentajeSumaAsegurada) {
		this.porcentajeSumaAsegurada = porcentajeSumaAsegurada;
	}

	public double getPorcentajeValorSiniestro() {
		return this.porcentajeValorSiniestro;
	}

	public void setPorcentajeValorSiniestro(double porcentajeValorSiniestro) {
		this.porcentajeValorSiniestro = porcentajeValorSiniestro;
	}

	public double getValorPrima() {
		return this.valorPrima;
	}

	public void setValorPrima(double valorPrima) {
		this.valorPrima = valorPrima;
	}

	public double getValorPrimaOrigen() {
		return this.valorPrimaOrigen;
	}

	public void setValorPrimaOrigen(double valorPrimaOrigen) {
		this.valorPrimaOrigen = valorPrimaOrigen;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public List<DetalleDesgloseCobertura> getDetalleDesgloseCoberturas() {
		return this.detalleDesgloseCoberturas;
	}

	public void setDetalleDesgloseCoberturas(List<DetalleDesgloseCobertura> detalleDesgloseCoberturas) {
		this.detalleDesgloseCoberturas = detalleDesgloseCoberturas;
	}

	public DetalleDesgloseCobertura addDetalleDesgloseCobertura(DetalleDesgloseCobertura detalleDesgloseCobertura) {
		getDetalleDesgloseCoberturas().add(detalleDesgloseCobertura);
		detalleDesgloseCobertura.setCotizacionCobertura(this);

		return detalleDesgloseCobertura;
	}

	public DetalleDesgloseCobertura removeDetalleDesgloseCobertura(DetalleDesgloseCobertura detalleDesgloseCobertura) {
		getDetalleDesgloseCoberturas().remove(detalleDesgloseCobertura);
		detalleDesgloseCobertura.setCotizacionCobertura(null);

		return detalleDesgloseCobertura;
	}
	
	public double getValorMonto() {
		return this.valorMonto;
	}

	public void setValorMonto(double valorMonto) {
		this.valorMonto = valorMonto;
	}

	public double getValorPrimaOtros() {
		return valorPrimaOtros;
	}

	public void setValorPrimaOtros(double valorPrimaOtros) {
		this.valorPrimaOtros = valorPrimaOtros;
	}

	public double getValorPrimaOtrosIva() {
		return valorPrimaOtrosIva;
	}

	public void setValorPrimaOtrosIva(double valorPrimaOtrosIva) {
		this.valorPrimaOtrosIva = valorPrimaOtrosIva;
	}

}