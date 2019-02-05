package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the COBERTURA database table.
 * 
 */
@Entity
@NamedQueries({	
	@NamedQuery(name="Cobertura.buscarMostrarCotizador", query="SELECT c FROM Cobertura c where c.mostrarCotizador = '1' order by c.nombre"),
	@NamedQuery(name="Cobertura.buscarPorId", query="SELECT c FROM Cobertura c where c.id = :id"),
	@NamedQuery(name="Cobertura.buscarPorIds", query="SELECT c FROM Cobertura c WHERE c.id in :ids"),
	@NamedQuery(name="Cobertura.buscarTodos", query="SELECT c FROM Cobertura c"),
    @NamedQuery(name="Cobertura.buscarPorNombre", query="SELECT c FROM Cobertura c where c.nombre = :nombre"),    
    @NamedQuery(name="Cobertura.buscarPorNemotecnico", query="SELECT c FROM Cobertura c where c.nemotecnico = :nemotecnico"),
    @NamedQuery(name="Cobertura.buscarPorGrupoCobertura", query="SELECT c FROM Cobertura c WHERE c.grupoCobertura =:grupoCobertura"),
    @NamedQuery(name="Cobertura.buscarPorRamoNemotecnico", query="SELECT c FROM Cobertura c WHERE c.mostrarCotizador = '1' and c.grupoCobertura.ramo.nemotecnico like :ramoNemotecnico"),
    @NamedQuery(name="Cobertura.buscarCoberturasGrupoCobertura", query="SELECT c FROM Cobertura c WHERE c.grupoCobertura =:grupoCobertura order by c.nombre")
    })
public class Cobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="afecta_grupo")
	private String afectaGrupo;

	@Column(name="afecta_valor_asegurado")
	private String afectaValorAsegurado;

	@Column(name="es_indemnizable")
	private String esIndemnizable;

	@Column(name="es_limite_suma")
	private String esLimiteSuma;

	@Column(name="es_masivo")
	private String esMasivo;

	@Column(name="es_predeterminada")
	private String esPredeterminada;

	@Column(name="es_prima_fija")
	private String esPrimaFija;

	@Column(name="es_principal")
	private String esPrincipal;

	@Column(name="es_todo_riesgo")
	private String esTodoRiesgo;

	@Column(name="genera_endoso_rasa")
	private String generaEndosoRasa;

	//bi-directional many-to-one association to GrupoCobertura
	@ManyToOne
	@JoinColumn(name="grupo_cobertura_id")
	private GrupoCobertura grupoCobertura;

	private String limite;

	private String nemotecnico;

	private String nombre;

	private int orden;

	@Column(name="principal_cobertura")
	private String principalCobertura;

	@Column(name="rebaja_valor_asegurado")
	private String rebajaValorAsegurado;

	private String seccion;

	@Column(name="tasa_valor")
	private double tasaValor;

	@Lob
	private byte[] texto;
	
	@Column(name="nombre_comercial")
	private String nombreComercial;
	
	@Column(name="texto_cotizador")
	private String textoCotizador;

	
	
	//bi-directional many-to-one association to TipoCobertura
	@ManyToOne
	@JoinColumn(name="tipo_cobertura_id")
	private TipoCobertura tipoCobertura;
	
	//bi-directional many-to-one association to TipoTasa
	@ManyToOne
	@JoinColumn(name="tipo_tasa_id")
	private TipoTasa tipoTasa;

	//bi-directional many-to-one association to CotizacionCobertura
	@OneToMany(mappedBy="cobertura")
	private List<CotizacionCobertura> cotizacionCoberturas;

	//bi-directional many-to-one association to DesgloseCobertura
	@OneToMany(mappedBy="cobertura")
	private List<DesgloseCobertura> desgloseCoberturas;

	@Column(name="mostrar_cotizador")
	private Boolean mostrarCotizador;
	
	public Cobertura() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAfectaGrupo() {
		return this.afectaGrupo;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public void setAfectaGrupo(String afectaGrupo) {
		this.afectaGrupo = afectaGrupo;
	}

	public String getAfectaValorAsegurado() {
		return this.afectaValorAsegurado;
	}

	public void setAfectaValorAsegurado(String afectaValorAsegurado) {
		this.afectaValorAsegurado = afectaValorAsegurado;
	}

	public String getEsIndemnizable() {
		return this.esIndemnizable;
	}

	public void setEsIndemnizable(String esIndemnizable) {
		this.esIndemnizable = esIndemnizable;
	}

	public String getEsLimiteSuma() {
		return this.esLimiteSuma;
	}

	public void setEsLimiteSuma(String esLimiteSuma) {
		this.esLimiteSuma = esLimiteSuma;
	}

	public String getEsMasivo() {
		return this.esMasivo;
	}

	public void setEsMasivo(String esMasivo) {
		this.esMasivo = esMasivo;
	}

	public String getEsPredeterminada() {
		return this.esPredeterminada;
	}

	public void setEsPredeterminada(String esPredeterminada) {
		this.esPredeterminada = esPredeterminada;
	}

	public String getEsPrimaFija() {
		return this.esPrimaFija;
	}

	public void setEsPrimaFija(String esPrimaFija) {
		this.esPrimaFija = esPrimaFija;
	}

	public String getEsPrincipal() {
		return this.esPrincipal;
	}

	public void setEsPrincipal(String esPrincipal) {
		this.esPrincipal = esPrincipal;
	}

	public String getEsTodoRiesgo() {
		return this.esTodoRiesgo;
	}

	public void setEsTodoRiesgo(String esTodoRiesgo) {
		this.esTodoRiesgo = esTodoRiesgo;
	}

	public String getGeneraEndosoRasa() {
		return this.generaEndosoRasa;
	}

	public void setGeneraEndosoRasa(String generaEndosoRasa) {
		this.generaEndosoRasa = generaEndosoRasa;
	}

	public GrupoCobertura getGrupoCobertura() {
		return this.grupoCobertura;
	}

	public void setGrupoCobertura(GrupoCobertura grupoCobertura) {
		this.grupoCobertura = grupoCobertura;
	}

	public String getLimite() {
		return this.limite;
	}

	public void setLimite(String limite) {
		this.limite = limite;
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

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getPrincipalCobertura() {
		return this.principalCobertura;
	}

	public void setPrincipalCobertura(String principalCobertura) {
		this.principalCobertura = principalCobertura;
	}

	public String getRebajaValorAsegurado() {
		return this.rebajaValorAsegurado;
	}

	public void setRebajaValorAsegurado(String rebajaValorAsegurado) {
		this.rebajaValorAsegurado = rebajaValorAsegurado;
	}

	public String getSeccion() {
		return this.seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public double getTasaValor() {
		return this.tasaValor;
	}

	public void setTasaValor(double tasaValor) {
		this.tasaValor = tasaValor;
	}

	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}
	


	public String getTextoCotizador() {
		return textoCotizador;
	}

	public void setTextoCotizador(String textoCotizador) {
		this.textoCotizador = textoCotizador;
	}

	public TipoCobertura getTipoCobertura() {
		return this.tipoCobertura;
	}

	public void setTipoCobertura(TipoCobertura tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public TipoTasa getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(TipoTasa tipoTasa) {
		this.tipoTasa = tipoTasa;
	}

	public List<CotizacionCobertura> getCotizacionCoberturas() {
		return this.cotizacionCoberturas;
	}

	public void setCotizacionCoberturas(List<CotizacionCobertura> cotizacionCoberturas) {
		this.cotizacionCoberturas = cotizacionCoberturas;
	}

	public CotizacionCobertura addCotizacionCobertura(CotizacionCobertura cotizacionCobertura) {
		getCotizacionCoberturas().add(cotizacionCobertura);
		cotizacionCobertura.setCobertura(this);

		return cotizacionCobertura;
	}

	public CotizacionCobertura removeCotizacionCobertura(CotizacionCobertura cotizacionCobertura) {
		getCotizacionCoberturas().remove(cotizacionCobertura);
		cotizacionCobertura.setCobertura(null);

		return cotizacionCobertura;
	}

	public List<DesgloseCobertura> getDesgloseCoberturas() {
		return this.desgloseCoberturas;
	}

	public void setDesgloseCoberturas(List<DesgloseCobertura> desgloseCoberturas) {
		this.desgloseCoberturas = desgloseCoberturas;
	}

	public DesgloseCobertura addDesgloseCobertura(DesgloseCobertura desgloseCobertura) {
		getDesgloseCoberturas().add(desgloseCobertura);
		desgloseCobertura.setCobertura(this);

		return desgloseCobertura;
	}

	public DesgloseCobertura removeDesgloseCobertura(DesgloseCobertura desgloseCobertura) {
		getDesgloseCoberturas().remove(desgloseCobertura);
		desgloseCobertura.setCobertura(null);

		return desgloseCobertura;
	}

	public Boolean getMostrarCotizador() {
		return mostrarCotizador;
	}

	public void setMostrarCotizador(Boolean mostrarCotizador) {
		this.mostrarCotizador = mostrarCotizador;
	}
	
}