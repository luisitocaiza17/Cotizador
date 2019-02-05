package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TASA_PRODUCTO database table.
 * 
 */
@Entity
@Table(name="TASA_PRODUCTO")
@NamedQueries({
	@NamedQuery(name="TasaProducto.buscarPorId", query="SELECT c FROM TasaProducto c where c.id = :id"),
	@NamedQuery(name="TasaProducto.buscarTodos", query="SELECT c FROM TasaProducto c"),
	@NamedQuery(name="TasaProducto.buscarTodosPorGrupoPorProducto", query="SELECT c FROM TasaProducto c where c.grupoPorProducto=:grupoPorProducto")
})
public class TasaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="ano_fabricacion_fin")
	private int anoFabricacionFin;

	@Column(name="ano_fabricacion_inicio")
	private int anoFabricacionInicio;

	@Column(name="antiguedad_fin")
	private int antiguedadFin;

	@Column(name="antiguedad_inicio")
	private int antiguedadInicio;

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
	private double edadConductorFin;

	@Column(name="edad_conductor_inicio")
	private double edadConductorInicio;

	@Column(name="es_flota_individual")
	private boolean esFlotaIndividual;

	@Column(name="kilometros_recorridos_fin")
	private int kilometrosRecorridosFin;

	@Column(name="kilometros_recorridos_inicio")
	private int kilometrosRecorridosInicio;

	@Column(name="marca_listadi")
	private String marcaListadi;

	@Column(name="modelo_listado")
	private String modeloListado;

	private String nombre;

	@Column(name="nombre_adquisicion")
	private String nombreAdquisicion;

	@Column(name="numero_hijos")
	private int numeroHijos;

	@Column(name="porcentaje_casco")
	private double porcentajeCasco;

	@Column(name="porcentaje_extras")
	private double porcentajeExtras;

	@Column(name="suma_asegurada_fin")
	private double sumaAseguradaFin;

	@Column(name="suma_asegurada_inicio")
	private double sumaAseguradaInicio;

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

	//bi-directional many-to-one association to GrupoPorProducto
	@ManyToOne
	@JoinColumn(name="grupo_por_producto_id")
	private GrupoPorProducto grupoPorProducto;

	public TasaProducto() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAnoFabricacionFin() {
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

	public int getAntiguedadFin() {
		return this.antiguedadFin;
	}

	public void setAntiguedadFin(int antiguedadFin) {
		this.antiguedadFin = antiguedadFin;
	}

	public int getAntiguedadInicio() {
		return this.antiguedadInicio;
	}

	public void setAntiguedadInicio(int antiguedadInicio) {
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

	public double getEdadConductorFin() {
		return this.edadConductorFin;
	}

	public void setEdadConductorFin(double edadConductorFin) {
		this.edadConductorFin = edadConductorFin;
	}

	public double getEdadConductorInicio() {
		return this.edadConductorInicio;
	}

	public void setEdadConductorInicio(double edadConductorInicio) {
		this.edadConductorInicio = edadConductorInicio;
	}

	public boolean getEsFlotaIndividual() {
		return this.esFlotaIndividual;
	}

	public void setEsFlotaIndividual(boolean esFlotaIndividual) {
		this.esFlotaIndividual = esFlotaIndividual;
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

	public String getMarcaListadi() {
		return this.marcaListadi;
	}

	public void setMarcaListadi(String marcaListadi) {
		this.marcaListadi = marcaListadi;
	}

	public String getModeloListado() {
		return this.modeloListado;
	}

	public void setModeloListado(String modeloListado) {
		this.modeloListado = modeloListado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreAdquisicion() {
		return this.nombreAdquisicion;
	}

	public void setNombreAdquisicion(String nombreAdquisicion) {
		this.nombreAdquisicion = nombreAdquisicion;
	}

	public int getNumeroHijos() {
		return this.numeroHijos;
	}

	public void setNumeroHijos(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public double getPorcentajeCasco() {
		return this.porcentajeCasco;
	}

	public void setPorcentajeCasco(double porcentajeCasco) {
		this.porcentajeCasco = porcentajeCasco;
	}

	public double getPorcentajeExtras() {
		return this.porcentajeExtras;
	}

	public void setPorcentajeExtras(double porcentajeExtras) {
		this.porcentajeExtras = porcentajeExtras;
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

	public GrupoPorProducto getGrupoPorProducto() {
		return this.grupoPorProducto;
	}

	public void setGrupoPorProducto(GrupoPorProducto grupoPorProducto) {
		this.grupoPorProducto = grupoPorProducto;
	}

}