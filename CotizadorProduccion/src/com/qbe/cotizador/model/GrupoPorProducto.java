package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the grupo_por_producto database table.
 * 
 */
@Entity
@Table(name="GRUPO_POR_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="GrupoPorProducto.buscarTodosPorGrupo", query="SELECT c FROM GrupoPorProducto c where c.grupoProducto=:grupoProducto"),
	@NamedQuery(name="GrupoPorProducto.buscarTodosPorGrupoTipoGrupo", query="SELECT c FROM GrupoPorProducto c where c.grupoProducto=:grupoProducto and c.tipoGrupo=:tipoGrupo"),
	@NamedQuery(name="GrupoPorProducto.buscarTodosPorTipoGrupo", query="SELECT c FROM GrupoPorProducto c where c.tipoGrupo=:tipoGrupo"),
	@NamedQuery(name="GrupoPorProducto.buscarActivosPorTipoGrupo", query="SELECT c FROM GrupoPorProducto c where c.tipoGrupo=:tipoGrupo and c.activo=1"),
    @NamedQuery(name="GrupoPorProducto.buscarPorId", query="SELECT c FROM GrupoPorProducto c where c.id=:id"),
	@NamedQuery(name="GrupoPorProducto.buscarTodos", query="SELECT c FROM GrupoPorProducto c order by c.nombreComercialProducto"),
	@NamedQuery(name="GrupoPorProducto.buscarPorNombre", query="SELECT c FROM GrupoPorProducto c where c.nombreComercialProducto=:nombre")	
})
public class GrupoPorProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;

	@Column(name="ano_fabricacion_fin")
	private int anoFabricacionFin;

	@Column(name="ano_fabricacion_inicio")
	private int anoFabricacionInicio;

	@Column(name="antiguedad_fin")
	private double antiguedadFin;

	@Column(name="antiguedad_inicio")
	private double antiguedadInicio;

	@Column(name="carga_pasajeros_valor")
	private String cargaPasajerosValor;

	@Column(name="cero_kilometros")
	private boolean ceroKilometros;

	@Column(name="combustible_utilizado_valor_id")
	private String combustibleUtilizadoValorId;

	@Column(name="conductor_genero_valor")
	private String conductorGeneroValor;

	@Column(name="deducible_minimo")
	private double deducibleMinimo;

	@Column(name="deducible_perdida_total_siniestro")
	private double deduciblePerdidaTotalSiniestro;

	@Column(name="deducible_porcentaje_siniestro")
	private double deduciblePorcentajeSiniestro;

	@Column(name="deducible_porcentaje_valor_asegurado")
	private double deduciblePorcentajeValorAsegurado;

	@Column(name="edad_conductor_fin")
	private int edadConductorFin;

	@Column(name="edad_conductor_inicio")
	private int edadConductorInicio;

	@Column(name="es_flota_individual")
	private boolean esFlotaIndividual;

	private boolean formulada;

	@Column(name="inspeccion_requerida")
	private boolean inspeccionRequerida;

	@Column(name="kilometros_recorridos_fin")
	private int kilometrosRecorridosFin;

	@Column(name="kilometros_recorridos_inicio")
	private int kilometrosRecorridosInicio;

	@Column(name="marca_listado")
	private String marcaListado;

	@Column(name="modelo_listado")
	private String modeloListado;

	@Column(name="nombre_adquisicion")
	private String nombreAdquisicion;

	@Column(name="nombre_comercial_producto")
	private String nombreComercialProducto;

	@Column(name="numero_hijos")
	private int numeroHijos;

	@Column(name="porcentaje_extras_tasa_fija")
	private double porcentajeExtrasTasaFija;

	@Column(name="porcentaje_tasa_fija")
	private double porcentajeTasaFija;

	@Column(name="suma_asegurada_fin")
	private double sumaAseguradaFin;

	@Column(name="suma_asegurada_inicio")
	private double sumaAseguradaInicio;

	@Column(name="tasa_fija")
	private boolean tasaFija;

	@Column(name="tiene_adquisicion")
	private boolean tieneAdquisicion;

	@Column(name="tiene_ano_fabricacion")
	private boolean tieneAnoFabricacion;

	@Column(name="tiene_antiguedad_vh")
	private boolean tieneAntiguedadVh;

	@Column(name="tiene_carga_pasajeros")
	private boolean tieneCargaPasajeros;

	@Column(name="tiene_combustible_utilizado")
	private boolean tieneCombustibleUtilizado;

	@Column(name="tiene_conductor_genero")
	private boolean tieneConductorGenero;

	@Column(name="tiene_deducible")
	private boolean tieneDeducible;

	@Column(name="tiene_deducible_perdida_total_siniestro")
	private boolean tieneDeduciblePerdidaTotalSiniestro;

	@Column(name="tiene_dispositivo_rastreo")
	private boolean tieneDispositivoRastreo;

	@Column(name="tiene_edad_conductor")
	private boolean tieneEdadConductor;

	@Column(name="tiene_guarda_garage")
	private boolean tieneGuardaGarage;

	@Column(name="tiene_kilometros_recorridos")
	private boolean tieneKilometrosRecorridos;

	@Column(name="tiene_marca")
	private boolean tieneMarca;

	@Column(name="tiene_modelo")
	private boolean tieneModelo;

	@Column(name="tiene_numero_hijos")
	private boolean tieneNumeroHijos;

	@Column(name="tiene_region")
	private boolean tieneRegion;

	@Column(name="tiene_renovacion")
	private boolean tieneRenovacion;

	@Column(name="tiene_suma_asegurada")
	private boolean tieneSumaAsegurada;

	@Column(name="tiene_tipo_objeto_vehiculo")
	private boolean tieneTipoObjetoVehiculo;

	@Column(name="tiene_tipo_uso")
	private boolean tieneTipoUso;

	@Column(name="tiene_tipo_vehiculo")
	private boolean tieneTipoVehiculo;

	@Column(name="tiene_tonelaje")
	private boolean tieneTonelaje;

	@Column(name="tiene_zona")
	private boolean tieneZona;

	@Column(name="tipo_uso_listado")
	private String tipoUsoListado;

	@Column(name="tipo_vehiculo_nombre")
	private String tipoVehiculoNombre;

	@Column(name="valor_flota_individual")
	private String valorFlotaIndividual;

	@Column(name="valor_region")
	private String valorRegion;

	@Column(name="valor_tipo_objeto_vehiculo")
	private String valorTipoObjetoVehiculo;

	@Column(name="valor_tonelaje_fin")
	private double valorTonelajeFin;

	@Column(name="valor_tonelaje_inicio")
	private double valorTonelajeInicio;

	@Column(name="valor_zona")
	private String valorZona;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	private Producto producto;

	//bi-directional many-to-one association to GrupoProducto
	@ManyToOne
	@JoinColumn(name="grupo_producto_id")
	private GrupoProducto grupoProducto;

	//bi-directional many-to-one association to TipoGrupo
	@ManyToOne
	@JoinColumn(name="tipo_grupo_id")
	private TipoGrupo tipoGrupo;

	//bi-directional many-to-one association to PaqueteCobertura
	@OneToMany(mappedBy="grupoPorProducto")
	private List<PaqueteCobertura> paqueteCoberturas;

	//bi-directional many-to-one association to ProductoCobertura
	@OneToMany(mappedBy="grupoPorProducto")
	private List<ProductoCobertura> productoCoberturas;

	//bi-directional many-to-one association to ProductoXPuntoVenta
	@OneToMany(mappedBy="grupoPorProducto")
	private List<ProductoXPuntoVenta> productoXPuntoVentas;

	//bi-directional many-to-one association to TasaProducto
	@OneToMany(mappedBy="grupoPorProducto")
	private List<TasaProducto> tasaProductos;

	public GrupoPorProducto() {
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

	public int getAnoFabricacionFin() {
		return this.anoFabricacionFin;
	}

	public void setAnoFabricacionFin(int anoFabricacionFin) {
		this.anoFabricacionFin = anoFabricacionFin;
	}

	public int getAnoFabricacionInicio() {
		return this.anoFabricacionInicio;
	}

	public void setAnoFabricacionInicio(int anoFabricacionInicio) {
		this.anoFabricacionInicio = anoFabricacionInicio;
	}

	public double getAntiguedadFin() {
		return this.antiguedadFin;
	}

	public void setAntiguedadFin(double antiguedadFin) {
		this.antiguedadFin = antiguedadFin;
	}

	public double getAntiguedadInicio() {
		return this.antiguedadInicio;
	}

	public void setAntiguedadInicio(double antiguedadInicio) {
		this.antiguedadInicio = antiguedadInicio;
	}

	public String getCargaPasajerosValor() {
		return this.cargaPasajerosValor;
	}

	public void setCargaPasajerosValor(String cargaPasajerosValor) {
		this.cargaPasajerosValor = cargaPasajerosValor;
	}

	public boolean getCeroKilometros() {
		return this.ceroKilometros;
	}

	public void setCeroKilometros(boolean ceroKilometros) {
		this.ceroKilometros = ceroKilometros;
	}

	public String getCombustibleUtilizadoValorId() {
		return this.combustibleUtilizadoValorId;
	}

	public void setCombustibleUtilizadoValorId(String combustibleUtilizadoValorId) {
		this.combustibleUtilizadoValorId = combustibleUtilizadoValorId;
	}

	public String getConductorGeneroValor() {
		return this.conductorGeneroValor;
	}

	public void setConductorGeneroValor(String conductorGeneroValor) {
		this.conductorGeneroValor = conductorGeneroValor;
	}

	public double getDeducibleMinimo() {
		return this.deducibleMinimo;
	}

	public void setDeducibleMinimo(double deducibleMinimo) {
		this.deducibleMinimo = deducibleMinimo;
	}

	public double getDeduciblePerdidaTotalSiniestro() {
		return this.deduciblePerdidaTotalSiniestro;
	}

	public void setDeduciblePerdidaTotalSiniestro(double deduciblePerdidaTotalSiniestro) {
		this.deduciblePerdidaTotalSiniestro = deduciblePerdidaTotalSiniestro;
	}

	public double getDeduciblePorcentajeSiniestro() {
		return this.deduciblePorcentajeSiniestro;
	}

	public void setDeduciblePorcentajeSiniestro(double deduciblePorcentajeSiniestro) {
		this.deduciblePorcentajeSiniestro = deduciblePorcentajeSiniestro;
	}

	public double getDeduciblePorcentajeValorAsegurado() {
		return this.deduciblePorcentajeValorAsegurado;
	}

	public void setDeduciblePorcentajeValorAsegurado(double deduciblePorcentajeValorAsegurado) {
		this.deduciblePorcentajeValorAsegurado = deduciblePorcentajeValorAsegurado;
	}

	public int getEdadConductorFin() {
		return this.edadConductorFin;
	}

	public void setEdadConductorFin(int edadConductorFin) {
		this.edadConductorFin = edadConductorFin;
	}

	public int getEdadConductorInicio() {
		return this.edadConductorInicio;
	}

	public void setEdadConductorInicio(int edadConductorInicio) {
		this.edadConductorInicio = edadConductorInicio;
	}

	public boolean getEsFlotaIndividual() {
		return this.esFlotaIndividual;
	}

	public void setEsFlotaIndividual(boolean esFlotaIndividual) {
		this.esFlotaIndividual = esFlotaIndividual;
	}

	public boolean getFormulada() {
		return this.formulada;
	}

	public void setFormulada(boolean formulada) {
		this.formulada = formulada;
	}

	public boolean getInspeccionRequerida() {
		return this.inspeccionRequerida;
	}

	public void setInspeccionRequerida(boolean inspeccionRequerida) {
		this.inspeccionRequerida = inspeccionRequerida;
	}

	public int getKilometrosRecorridosFin() {
		return this.kilometrosRecorridosFin;
	}

	public void setKilometrosRecorridosFin(int kilometrosRecorridosFin) {
		this.kilometrosRecorridosFin = kilometrosRecorridosFin;
	}

	public int getKilometrosRecorridosInicio() {
		return this.kilometrosRecorridosInicio;
	}

	public void setKilometrosRecorridosInicio(int kilometrosRecorridosInicio) {
		this.kilometrosRecorridosInicio = kilometrosRecorridosInicio;
	}

	public String getMarcaListado() {
		return this.marcaListado;
	}

	public void setMarcaListado(String marcaListado) {
		this.marcaListado = marcaListado;
	}

	public String getModeloListado() {
		return this.modeloListado;
	}

	public void setModeloListado(String modeloListado) {
		this.modeloListado = modeloListado;
	}

	public String getNombreAdquisicion() {
		return this.nombreAdquisicion;
	}

	public void setNombreAdquisicion(String nombreAdquisicion) {
		this.nombreAdquisicion = nombreAdquisicion;
	}

	public String getNombreComercialProducto() {
		return this.nombreComercialProducto;
	}

	public void setNombreComercialProducto(String nombreComercialProducto) {
		this.nombreComercialProducto = nombreComercialProducto;
	}

	public int getNumeroHijos() {
		return this.numeroHijos;
	}

	public void setNumeroHijos(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public double getPorcentajeExtrasTasaFija() {
		return this.porcentajeExtrasTasaFija;
	}

	public void setPorcentajeExtrasTasaFija(double porcentajeExtrasTasaFija) {
		this.porcentajeExtrasTasaFija = porcentajeExtrasTasaFija;
	}

	public double getPorcentajeTasaFija() {
		return this.porcentajeTasaFija;
	}

	public void setPorcentajeTasaFija(double porcentajeTasaFija) {
		this.porcentajeTasaFija = porcentajeTasaFija;
	}

	public double getSumaAseguradaFin() {
		return this.sumaAseguradaFin;
	}

	public void setSumaAseguradaFin(double sumaAseguradaFin) {
		this.sumaAseguradaFin = sumaAseguradaFin;
	}

	public double getSumaAseguradaInicio() {
		return this.sumaAseguradaInicio;
	}

	public void setSumaAseguradaInicio(double sumaAseguradaInicio) {
		this.sumaAseguradaInicio = sumaAseguradaInicio;
	}

	public boolean getTasaFija() {
		return this.tasaFija;
	}

	public void setTasaFija(boolean tasaFija) {
		this.tasaFija = tasaFija;
	}

	public boolean getTieneAdquisicion() {
		return this.tieneAdquisicion;
	}

	public void setTieneAdquisicion(boolean tieneAdquisicion) {
		this.tieneAdquisicion = tieneAdquisicion;
	}

	public boolean getTieneAnoFabricacion() {
		return this.tieneAnoFabricacion;
	}

	public void setTieneAnoFabricacion(boolean tieneAnoFabricacion) {
		this.tieneAnoFabricacion = tieneAnoFabricacion;
	}

	public boolean getTieneAntiguedadVh() {
		return this.tieneAntiguedadVh;
	}

	public void setTieneAntiguedadVh(boolean tieneAntiguedadVh) {
		this.tieneAntiguedadVh = tieneAntiguedadVh;
	}

	public boolean getTieneCargaPasajeros() {
		return this.tieneCargaPasajeros;
	}

	public void setTieneCargaPasajeros(boolean tieneCargaPasajeros) {
		this.tieneCargaPasajeros = tieneCargaPasajeros;
	}

	public boolean getTieneCombustibleUtilizado() {
		return this.tieneCombustibleUtilizado;
	}

	public void setTieneCombustibleUtilizado(boolean tieneCombustibleUtilizado) {
		this.tieneCombustibleUtilizado = tieneCombustibleUtilizado;
	}

	public boolean getTieneConductorGenero() {
		return this.tieneConductorGenero;
	}

	public void setTieneConductorGenero(boolean tieneConductorGenero) {
		this.tieneConductorGenero = tieneConductorGenero;
	}

	public boolean getTieneDeducible() {
		return this.tieneDeducible;
	}

	public void setTieneDeducible(boolean tieneDeducible) {
		this.tieneDeducible = tieneDeducible;
	}

	public boolean getTieneDeduciblePerdidaTotalSiniestro() {
		return this.tieneDeduciblePerdidaTotalSiniestro;
	}

	public void setTieneDeduciblePerdidaTotalSiniestro(boolean tieneDeduciblePerdidaTotalSiniestro) {
		this.tieneDeduciblePerdidaTotalSiniestro = tieneDeduciblePerdidaTotalSiniestro;
	}

	public boolean getTieneDispositivoRastreo() {
		return this.tieneDispositivoRastreo;
	}

	public void setTieneDispositivoRastreo(boolean tieneDispositivoRastreo) {
		this.tieneDispositivoRastreo = tieneDispositivoRastreo;
	}

	public boolean getTieneEdadConductor() {
		return this.tieneEdadConductor;
	}

	public void setTieneEdadConductor(boolean tieneEdadConductor) {
		this.tieneEdadConductor = tieneEdadConductor;
	}

	public boolean getTieneGuardaGarage() {
		return this.tieneGuardaGarage;
	}

	public void setTieneGuardaGarage(boolean tieneGuardaGarage) {
		this.tieneGuardaGarage = tieneGuardaGarage;
	}

	public boolean getTieneKilometrosRecorridos() {
		return this.tieneKilometrosRecorridos;
	}

	public void setTieneKilometrosRecorridos(boolean tieneKilometrosRecorridos) {
		this.tieneKilometrosRecorridos = tieneKilometrosRecorridos;
	}

	public boolean getTieneMarca() {
		return this.tieneMarca;
	}

	public void setTieneMarca(boolean tieneMarca) {
		this.tieneMarca = tieneMarca;
	}

	public boolean getTieneModelo() {
		return this.tieneModelo;
	}

	public void setTieneModelo(boolean tieneModelo) {
		this.tieneModelo = tieneModelo;
	}

	public boolean getTieneNumeroHijos() {
		return this.tieneNumeroHijos;
	}

	public void setTieneNumeroHijos(boolean tieneNumeroHijos) {
		this.tieneNumeroHijos = tieneNumeroHijos;
	}

	public boolean getTieneRegion() {
		return this.tieneRegion;
	}

	public void setTieneRegion(boolean tieneRegion) {
		this.tieneRegion = tieneRegion;
	}

	public boolean getTieneRenovacion() {
		return this.tieneRenovacion;
	}

	public void setTieneRenovacion(boolean tieneRenovacion) {
		this.tieneRenovacion = tieneRenovacion;
	}

	public boolean getTieneSumaAsegurada() {
		return this.tieneSumaAsegurada;
	}

	public void setTieneSumaAsegurada(boolean tieneSumaAsegurada) {
		this.tieneSumaAsegurada = tieneSumaAsegurada;
	}

	public boolean getTieneTipoObjetoVehiculo() {
		return this.tieneTipoObjetoVehiculo;
	}

	public void setTieneTipoObjetoVehiculo(boolean tieneTipoObjetoVehiculo) {
		this.tieneTipoObjetoVehiculo = tieneTipoObjetoVehiculo;
	}

	public boolean getTieneTipoUso() {
		return this.tieneTipoUso;
	}

	public void setTieneTipoUso(boolean tieneTipoUso) {
		this.tieneTipoUso = tieneTipoUso;
	}

	public boolean getTieneTipoVehiculo() {
		return this.tieneTipoVehiculo;
	}

	public void setTieneTipoVehiculo(boolean tieneTipoVehiculo) {
		this.tieneTipoVehiculo = tieneTipoVehiculo;
	}

	public boolean getTieneTonelaje() {
		return this.tieneTonelaje;
	}

	public void setTieneTonelaje(boolean tieneTonelaje) {
		this.tieneTonelaje = tieneTonelaje;
	}

	public boolean getTieneZona() {
		return this.tieneZona;
	}

	public void setTieneZona(boolean tieneZona) {
		this.tieneZona = tieneZona;
	}

	public String getTipoUsoListado() {
		return this.tipoUsoListado;
	}

	public void setTipoUsoListado(String tipoUsoListado) {
		this.tipoUsoListado = tipoUsoListado;
	}

	public String getTipoVehiculoNombre() {
		return this.tipoVehiculoNombre;
	}

	public void setTipoVehiculoNombre(String tipoVehiculoNombre) {
		this.tipoVehiculoNombre = tipoVehiculoNombre;
	}

	public String getValorFlotaIndividual() {
		return this.valorFlotaIndividual;
	}

	public void setValorFlotaIndividual(String valorFlotaIndividual) {
		this.valorFlotaIndividual = valorFlotaIndividual;
	}

	public String getValorRegion() {
		return this.valorRegion;
	}

	public void setValorRegion(String valorRegion) {
		this.valorRegion = valorRegion;
	}

	public String getValorTipoObjetoVehiculo() {
		return this.valorTipoObjetoVehiculo;
	}

	public void setValorTipoObjetoVehiculo(String valorTipoObjetoVehiculo) {
		this.valorTipoObjetoVehiculo = valorTipoObjetoVehiculo;
	}

	public double getValorTonelajeFin() {
		return this.valorTonelajeFin;
	}

	public void setValorTonelajeFin(double valorTonelajeFin) {
		this.valorTonelajeFin = valorTonelajeFin;
	}

	public double getValorTonelajeInicio() {
		return this.valorTonelajeInicio;
	}

	public void setValorTonelajeInicio(double valorTonelajeInicio) {
		this.valorTonelajeInicio = valorTonelajeInicio;
	}

	public String getValorZona() {
		return this.valorZona;
	}

	public void setValorZona(String valorZona) {
		this.valorZona = valorZona;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public GrupoProducto getGrupoProducto() {
		return this.grupoProducto;
	}

	public void setGrupoProducto(GrupoProducto grupoProducto) {
		this.grupoProducto = grupoProducto;
	}

	public TipoGrupo getTipoGrupo() {
		return this.tipoGrupo;
	}

	public void setTipoGrupo(TipoGrupo tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public List<PaqueteCobertura> getPaqueteCoberturas() {
		return this.paqueteCoberturas;
	}

	public void setPaqueteCoberturas(List<PaqueteCobertura> paqueteCoberturas) {
		this.paqueteCoberturas = paqueteCoberturas;
	}

	public PaqueteCobertura addPaqueteCobertura(PaqueteCobertura paqueteCobertura) {
		getPaqueteCoberturas().add(paqueteCobertura);
		paqueteCobertura.setGrupoPorProducto(this);

		return paqueteCobertura;
	}

	public PaqueteCobertura removePaqueteCobertura(PaqueteCobertura paqueteCobertura) {
		getPaqueteCoberturas().remove(paqueteCobertura);
		paqueteCobertura.setGrupoPorProducto(null);

		return paqueteCobertura;
	}

	public List<ProductoCobertura> getProductoCoberturas() {
		return this.productoCoberturas;
	}

	public void setProductoCoberturas(List<ProductoCobertura> productoCoberturas) {
		this.productoCoberturas = productoCoberturas;
	}

	public ProductoCobertura addProductoCobertura(ProductoCobertura productoCobertura) {
		getProductoCoberturas().add(productoCobertura);
		productoCobertura.setGrupoPorProducto(this);

		return productoCobertura;
	}

	public ProductoCobertura removeProductoCobertura(ProductoCobertura productoCobertura) {
		getProductoCoberturas().remove(productoCobertura);
		productoCobertura.setGrupoPorProducto(null);

		return productoCobertura;
	}

	public List<ProductoXPuntoVenta> getProductoXPuntoVentas() {
		return this.productoXPuntoVentas;
	}

	public void setProductoXPuntoVentas(List<ProductoXPuntoVenta> productoXPuntoVentas) {
		this.productoXPuntoVentas = productoXPuntoVentas;
	}

	public ProductoXPuntoVenta addProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().add(productoXPuntoVenta);
		productoXPuntoVenta.setGrupoPorProducto(this);

		return productoXPuntoVenta;
	}

	public ProductoXPuntoVenta removeProductoXPuntoVenta(ProductoXPuntoVenta productoXPuntoVenta) {
		getProductoXPuntoVentas().remove(productoXPuntoVenta);
		productoXPuntoVenta.setGrupoPorProducto(null);

		return productoXPuntoVenta;
	}

	public List<TasaProducto> getTasaProductos() {
		return this.tasaProductos;
	}

	public void setTasaProductos(List<TasaProducto> tasaProductos) {
		this.tasaProductos = tasaProductos;
	}

	public TasaProducto addTasaProducto(TasaProducto tasaProducto) {
		getTasaProductos().add(tasaProducto);
		tasaProducto.setGrupoPorProducto(this);

		return tasaProducto;
	}

	public TasaProducto removeTasaProducto(TasaProducto tasaProducto) {
		getTasaProductos().remove(tasaProducto);
		tasaProducto.setGrupoPorProducto(null);

		return tasaProducto;
	}

}