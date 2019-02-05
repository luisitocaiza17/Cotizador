package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;

import net.sf.json.JSONObject;

import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the VH_NOVACREDIT_COTIZACION_X_PERIODO database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_COTIZACION_X_PERIODO")
@NamedQueries({
	@NamedQuery(name="VhNovacreditCotizacionXPeriodo.buscarPorId", query="SELECT c FROM VhNovacreditCotizacionXPeriodo c where c.id = :id"),
	@NamedQuery(name="VhNovacreditCotizacionXPeriodo.buscarTodos", query="SELECT c FROM VhNovacreditCotizacionXPeriodo c"),
	@NamedQuery(name="VhNovacreditCotizacionXPeriodo.buscarPorCotizacionPeriodo", query="SELECT c FROM VhNovacreditCotizacionXPeriodo c where c.vhNovacreditCotizacion = :vhNovacreditCotizacion and c.vhNovacreditPeriodo =:vhNovacreditPeriodo"),
	@NamedQuery(name="VhNovacreditCotizacionXPeriodo.buscarPorCotizacion", query="SELECT c FROM VhNovacreditCotizacionXPeriodo c where c.vhNovacreditCotizacion = :vhNovacreditCotizacion")
})
public class VhNovacreditCotizacionXPeriodo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="derechos_emision")
	private double derechosEmision;

	private double iva;

	private double total;

	@Column(name="vigencia_desde")
	@Temporal(TemporalType.DATE)
	private Date vigenciaDesde;

	@Column(name="vigencia_hasta")
	@Temporal(TemporalType.DATE)
	private Date vigenciaHasta;

	@Column(name="valor_asegurado")
	private double valorAsegurado;

	@Column(name="valor_prima")
	private double valorPrima;

	@Column(name="valor_scvs")
	private double valorScvs;

	@Column(name="valor_seguro_campesino")
	private double valorSeguroCampesino;

	@ManyToOne
	@JoinColumn(name="vh_novacredit_cotizacion_id")
	private VhNovacreditCotizacion vhNovacreditCotizacion;
	
	@ManyToOne
	@JoinColumn(name="vh_novacredit_periodo_id")
	private VhNovacreditPeriodo vhNovacreditPeriodo;

	public VhNovacreditCotizacionXPeriodo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getDerechosEmision() {
		return this.derechosEmision;
	}

	public void setDerechosEmision(double derechosEmision) {
		this.derechosEmision = derechosEmision;
	}

	public double getIva() {
		return this.iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getValorAsegurado() {
		return this.valorAsegurado;
	}

	public void setValorAsegurado(double valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public double getValorPrima() {
		return this.valorPrima;
	}

	public void setValorPrima(double valorPrima) {
		this.valorPrima = valorPrima;
	}

	public double getValorScvs() {
		return this.valorScvs;
	}

	public void setValorScvs(double valorScvs) {
		this.valorScvs = valorScvs;
	}

	public double getValorSeguroCampesino() {
		return this.valorSeguroCampesino;
	}

	public void setValorSeguroCampesino(double valorSeguroCampesino) {
		this.valorSeguroCampesino = valorSeguroCampesino;
	}

	public VhNovacreditCotizacion getVhNovacreditCotizacion() {
		return vhNovacreditCotizacion;
	}

	public void setVhNovacreditCotizacion(VhNovacreditCotizacion vhNovacreditCotizacion) {
		this.vhNovacreditCotizacion = vhNovacreditCotizacion;
	}

	public VhNovacreditPeriodo getVhNovacreditPeriodo() {
		return vhNovacreditPeriodo;
	}

	public void setVhNovacreditPeriodo(VhNovacreditPeriodo vhNovacreditPeriodo) {
		this.vhNovacreditPeriodo = vhNovacreditPeriodo;
	}
	
	public Date getVigenciaDesde() {
		return vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public JSONObject obtenerJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("id", this.id);
		retorno.put("periodoId", this.vhNovacreditPeriodo.getId());
		retorno.put("periodoAnios", this.vhNovacreditPeriodo.getAnios());
		retorno.put("cotizacionId", this.vhNovacreditCotizacion.getId());
		retorno.put("valorAsegurado", this.valorAsegurado);
		retorno.put("valorPrima", this.valorPrima);
		retorno.put("valorSCVS", this.valorScvs);
		retorno.put("valorSeguroCampesino", this.valorSeguroCampesino);
		retorno.put("derechosEmision", this.derechosEmision);
		retorno.put("iva", this.iva);
		retorno.put("total", this.total);
		return retorno;
	}

}