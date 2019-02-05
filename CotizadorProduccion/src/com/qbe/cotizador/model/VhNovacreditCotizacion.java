package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the VH_NOVACREDIT_COTIZACION database table.
 * 
 */
@Entity
@Table(name="VH_NOVACREDIT_COTIZACION")
@NamedQueries({
	@NamedQuery(name="VhNovacreditCotizacion.buscarPorId", query="SELECT c FROM VhNovacreditCotizacion c where c.id = :id"),
	@NamedQuery(name="VhNovacreditCotizacion.buscarPorObjetoId", query="SELECT c FROM VhNovacreditCotizacion c where c.objetoId = :objetoId"),
	@NamedQuery(name="VhNovacreditCotizacion.buscarTodos", query="SELECT c FROM VhNovacreditCotizacion c")
})
public class VhNovacreditCotizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="direccion_id")
	private String direccionId;

	@ManyToOne
	@JoinColumn(name="entidad_id")
	private Entidad entidad;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento_entidad")
	private Date fechaNacimientoEntidad;

	private String lugar;

	@Column(name="numero_endoso")
	private String numeroEndoso;

	@Column(name="objeto_id")
	private String objetoId;

	@Column(name="porcentaje_iva")
	private double porcentajeIva;

	@Column(name="prima_total")
	private double primaTotal;

	@Column(name="valor_asegurado_total")
	private double valorAseguradoTotal;

	@Column(name="valor_casco")
	private double valorCasco;

	@Column(name="valor_derechos_emision_total")
	private double valorDerechosEmisionTotal;

	@Column(name="valor_extras")
	private double valorExtras;

	@Column(name="valor_iva_total")
	private double valorIvaTotal;

	@Column(name="valor_scvs_total")
	private double valorScvsTotal;

	@Column(name="valor_seguro_campesino_total")
	private double valorSeguroCampesinoTotal;

	@Column(name="valor_total")
	private double valorTotal;
	
	@Column(name="modelo_texto")
	private String modeloTexto;
	
	@ManyToOne
	@JoinColumn(name="vh_novacredit_periodo_id")
	private VhNovacreditPeriodo vhNovacreditPeriodo;

	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_cobertura_id")
	private VhNovacreditTipoCobertura vhNovacreditTipoCobertura;

	//bi-directional many-to-one association to VhNovacreditTipoTasa
	@ManyToOne
	@JoinColumn(name="vh_novacredit_tipo_tasa_id")
	private VhNovacreditTipoTasa vhNovacreditTipoTasa;

	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;


	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_desde")
	private Date vigenciaDesde;

	@Temporal(TemporalType.DATE)
	@Column(name="vigencia_hasta")
	private Date vigenciaHasta;
	
	@Column(name="carga_inicial")
	private boolean cargaInicial;


	public VhNovacreditCotizacion() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccionId() {
		return this.direccionId;
	}

	public void setDireccionId(String direccionId) {
		this.direccionId = direccionId;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaNacimientoEntidad() {
		return this.fechaNacimientoEntidad;
	}

	public void setFechaNacimientoEntidad(Date fechaNacimientoEntidad) {
		this.fechaNacimientoEntidad = fechaNacimientoEntidad;
	}

	public String getLugar() {
		return this.lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNumeroEndoso() {
		return this.numeroEndoso;
	}

	public void setNumeroEndoso(String numeroEndoso) {
		this.numeroEndoso = numeroEndoso;
	}

	public String getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(String objetoId) {
		this.objetoId = objetoId;
	}

	public double getPorcentajeIva() {
		return this.porcentajeIva;
	}

	public void setPorcentajeIva(double porcentajeIva) {
		this.porcentajeIva = porcentajeIva;
	}

	public double getPrimaTotal() {
		return this.primaTotal;
	}

	public void setPrimaTotal(double primaTotal) {
		this.primaTotal = primaTotal;
	}

	public double getValorAseguradoTotal() {
		return this.valorAseguradoTotal;
	}

	public void setValorAseguradoTotal(double valorAseguradoTotal) {
		this.valorAseguradoTotal = valorAseguradoTotal;
	}

	public double getValorCasco() {
		return this.valorCasco;
	}

	public void setValorCasco(double valorCasco) {
		this.valorCasco = valorCasco;
	}

	public double getValorDerechosEmisionTotal() {
		return this.valorDerechosEmisionTotal;
	}

	public void setValorDerechosEmisionTotal(double valorDerechosEmisionTotal) {
		this.valorDerechosEmisionTotal = valorDerechosEmisionTotal;
	}

	public double getValorExtras() {
		return this.valorExtras;
	}

	public void setValorExtras(double valorExtras) {
		this.valorExtras = valorExtras;
	}

	public double getValorIvaTotal() {
		return this.valorIvaTotal;
	}

	public void setValorIvaTotal(double valorIvaTotal) {
		this.valorIvaTotal = valorIvaTotal;
	}

	public double getValorScvsTotal() {
		return this.valorScvsTotal;
	}

	public void setValorScvsTotal(double valorScvsTotal) {
		this.valorScvsTotal = valorScvsTotal;
	}

	public double getValorSeguroCampesinoTotal() {
		return this.valorSeguroCampesinoTotal;
	}

	public void setValorSeguroCampesinoTotal(double valorSeguroCampesinoTotal) {
		this.valorSeguroCampesinoTotal = valorSeguroCampesinoTotal;
	}

	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public VhNovacreditPeriodo getVhNovacreditPeriodo() {
		return vhNovacreditPeriodo;
	}

	public void setVhNovacreditPeriodo(VhNovacreditPeriodo vhNovacreditPeriodo) {
		this.vhNovacreditPeriodo = vhNovacreditPeriodo;
	}

	public VhNovacreditTipoCobertura getVhNovacreditTipoCobertura() {
		return vhNovacreditTipoCobertura;
	}

	public void setVhNovacreditTipoCobertura(VhNovacreditTipoCobertura vhNovacreditTipoCobertura) {
		this.vhNovacreditTipoCobertura = vhNovacreditTipoCobertura;
	}

	public VhNovacreditTipoTasa getVhNovacreditTipoTasa() {
		return vhNovacreditTipoTasa;
	}

	public void setVhNovacreditTipoTasa(VhNovacreditTipoTasa vhNovacreditTipoTasa) {
		this.vhNovacreditTipoTasa = vhNovacreditTipoTasa;
	}

	public Date getVigenciaDesde() {
		return this.vigenciaDesde;
	}

	public void setVigenciaDesde(Date vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}

	public Date getVigenciaHasta() {
		return this.vigenciaHasta;
	}

	public void setVigenciaHasta(Date vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getModeloTexto() {
		return modeloTexto;
	}

	public void setModeloTexto(String modeloTexto) {
		this.modeloTexto = modeloTexto;
	}

	public boolean isCargaInicial() {
		return cargaInicial;
	}

	public void setCargaInicial(boolean cargaInicial) {
		this.cargaInicial = cargaInicial;
	}
	
}