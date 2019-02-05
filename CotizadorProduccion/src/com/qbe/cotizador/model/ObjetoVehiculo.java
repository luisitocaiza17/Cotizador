package com.qbe.cotizador.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Entity
@Table(name = "OBJETO_VEHICULO")
@NamedQueries({
		@javax.persistence.NamedQuery(name = "ObjetoVehiculo.buscarPorId", query = "SELECT c FROM ObjetoVehiculo c where c.id=:id"),
		@javax.persistence.NamedQuery(name = "ObjetoVehiculo.buscarTodos", query = "SELECT c FROM ObjetoVehiculo c"),
		@javax.persistence.NamedQuery(name = "ObjetoVehiculo.buscarPorPlaca", query = "SELECT c FROM ObjetoVehiculo c where c.placa =:placa"),
		@javax.persistence.NamedQuery(name = "ObjetoVehiculo.buscarPorChasis", query = "SELECT c FROM ObjetoVehiculo c where c.chasis =:chasis"),
		@javax.persistence.NamedQuery(name = "ObjetoVehiculo.buscarPorMotor", query = "SELECT c FROM ObjetoVehiculo c where c.motor =:motor") })
public class ObjetoVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "anio_fabricacion")
	private String anioFabricacion;

	@Column(name = "`anos_sin _siniestro`")
	private String anosSin_Siniestro;

	@Column(name = "antiguedad_vh")
	private String antiguedadVh;

	@Column(name = "carga_pasajeros")
	private String cargaPasajeros;

	@Column(name = "cero_kilometros")
	private boolean ceroKilometros;
	private String chasis;
	private String cilindraje;

	@Column(name = "codigo_ensurance")
	private String codigoEnsurance;
	private String combustible;

	@Column(name = "conductor_edad")
	private String conductorEdad;

	@Column(name = "conductor_estado_civil")
	private String conductorEstadoCivil;

	@Column(name = "conductor_genero")
	private String conductorGenero;

	@Column(name = "dispositivo_rastreo")
	private boolean dispositivoRastreo;

	@Column(name = "guarda_garage")
	private boolean guardaGarage;

	@Column(name = "kilometros_recorridos")
	private String kilometrosRecorridos;

	@Column(name = "modelo_ant")
	private String modeloAnt;
	private String motor;

	@Column(name = "numero_hijos")
	private String numeroHijos;
	private int pasajeros;
	private String placa;

	@Column(name = "porcentaje_comision")
	private double porcentajeComision;

	@Column(name = "sucursal_id")
	private String sucursalId;

	@Column(name = "suma_asegurada")
	private double sumaAsegurada;

	@Column(name = "tipo_adquisicion")
	private String tipoAdquisicion;

	@Column(name = "tonelaje_vehiculo")
	private double tonelajeVehiculo;
	private String zona;

	@Column(name = "hijos_conduzcan")
	private boolean hijosConduzcan;

	@Column(name = "kilometraje_anual")
	private String kilometrajeAnual;

	@OneToMany(mappedBy = "objetoVehiculo")
	private List<Extra> extras;

	@ManyToOne
	private Color color;

	@ManyToOne
	private Modelo modelo;

	@ManyToOne
	@JoinColumn(name = "tipo_uso_id")
	private TipoUso tipoUso;

	@ManyToOne
	@JoinColumn(name = "tipo_vehiculo_id")
	private TipoVehiculo tipoVehiculo;

	@ManyToOne
	@JoinColumn(name = "conductor_id")
	private Entidad conductor;

	@ManyToOne
	@JoinColumn(name = "zona_transito_id")
	private VhTari1Zona vhTari1Zona;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnioFabricacion() {
		return this.anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getAnosSin_Siniestro() {
		return this.anosSin_Siniestro;
	}

	public void setAnosSin_Siniestro(String anosSin_Siniestro) {
		this.anosSin_Siniestro = anosSin_Siniestro;
	}

	public String getAntiguedadVh() {
		return this.antiguedadVh;
	}

	public void setAntiguedadVh(String antiguedadVh) {
		this.antiguedadVh = antiguedadVh;
	}

	public String getCargaPasajeros() {
		return this.cargaPasajeros;
	}

	public void setCargaPasajeros(String cargaPasajeros) {
		this.cargaPasajeros = cargaPasajeros;
	}

	public boolean getCeroKilometros() {
		return this.ceroKilometros;
	}

	public void setCeroKilometros(boolean ceroKilometros) {
		this.ceroKilometros = ceroKilometros;
	}

	public String getChasis() {
		return this.chasis;
	}

	public void setChasis(String chasis) {
		this.chasis = chasis;
	}

	public String getCilindraje() {
		return this.cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getCodigoEnsurance() {
		return this.codigoEnsurance;
	}

	public void setCodigoEnsurance(String codigoEnsurance) {
		this.codigoEnsurance = codigoEnsurance;
	}

	public String getCombustible() {
		return this.combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getConductorEdad() {
		return this.conductorEdad;
	}

	public void setConductorEdad(String conductorEdad) {
		this.conductorEdad = conductorEdad;
	}

	public String getConductorEstadoCivil() {
		return this.conductorEstadoCivil;
	}

	public void setConductorEstadoCivil(String conductorEstadoCivil) {
		this.conductorEstadoCivil = conductorEstadoCivil;
	}

	public String getConductorGenero() {
		return this.conductorGenero;
	}

	public void setConductorGenero(String conductorGenero) {
		this.conductorGenero = conductorGenero;
	}

	public boolean getDispositivoRastreo() {
		return this.dispositivoRastreo;
	}

	public void setDispositivoRastreo(boolean dispositivoRastreo) {
		this.dispositivoRastreo = dispositivoRastreo;
	}

	public boolean getGuardaGarage() {
		return this.guardaGarage;
	}

	public void setGuardaGarage(boolean guardaGarage) {
		this.guardaGarage = guardaGarage;
	}

	public String getKilometrosRecorridos() {
		return this.kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(String kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

	public String getModeloAnt() {
		return this.modeloAnt;
	}

	public void setModeloAnt(String modeloAnt) {
		this.modeloAnt = modeloAnt;
	}

	public String getMotor() {
		return this.motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getNumeroHijos() {
		return this.numeroHijos;
	}

	public void setNumeroHijos(String numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public int getPasajeros() {
		return this.pasajeros;
	}

	public void setPasajeros(int pasajeros) {
		this.pasajeros = pasajeros;
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getPorcentajeComision() {
		return this.porcentajeComision;
	}

	public void setPorcentajeComision(double porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public String getSucursalId() {
		return this.sucursalId;
	}

	public void setSucursalId(String sucursalId) {
		this.sucursalId = sucursalId;
	}

	public double getSumaAsegurada() {
		return this.sumaAsegurada;
	}

	public void setSumaAsegurada(double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public String getTipoAdquisicion() {
		return this.tipoAdquisicion;
	}

	public void setTipoAdquisicion(String tipoAdquisicion) {
		this.tipoAdquisicion = tipoAdquisicion;
	}

	public double getTonelajeVehiculo() {
		return this.tonelajeVehiculo;
	}

	public void setTonelajeVehiculo(double tonelajeVehiculo) {
		this.tonelajeVehiculo = tonelajeVehiculo;
	}

	public String getZona() {
		return this.zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Extra> getExtras() {
		return this.extras;
	}

	public void setExtras(List<Extra> extras) {
		this.extras = extras;
	}

	public Extra addExtra(Extra extra) {
		getExtras().add(extra);
		extra.setObjetoVehiculo(this);

		return extra;
	}

	public Extra removeExtra(Extra extra) {
		getExtras().remove(extra);
		extra.setObjetoVehiculo(null);

		return extra;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public TipoUso getTipoUso() {
		return this.tipoUso;
	}

	public void setTipoUso(TipoUso tipoUso) {
		this.tipoUso = tipoUso;
	}

	public TipoVehiculo getTipoVehiculo() {
		return this.tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public VhTari1Zona getVhTari1Zona() {
		return this.vhTari1Zona;
	}

	public void setVhTari1Zona(VhTari1Zona vhTari1Zona) {
		this.vhTari1Zona = vhTari1Zona;
	}

	public boolean getHijosConduzcan() {
		return this.hijosConduzcan;
	}

	public void setHijosConduzcan(boolean hijosConduzcan) {
		this.hijosConduzcan = hijosConduzcan;
	}

	public String getKilometrajeAnual() {
		return this.kilometrajeAnual;
	}

	public void setKilometrajeAnual(String kilometrajeAnual) {
		this.kilometrajeAnual = kilometrajeAnual;
	}

	public Entidad getConductor() {
		return this.conductor;
	}

	public void setConductor(Entidad conductor) {
		this.conductor = conductor;
	}

	public JSONObject getJSON() {
		JSONObject retorno = new JSONObject();
		retorno.put("placa", placa);
		retorno.put("chasis", chasis);
		retorno.put("motor", motor);
		retorno.put("dispositivoRastreo", dispositivoRastreo);
		retorno.put("anio", anioFabricacion);
		retorno.put("modelo", modelo.getJSON());
		retorno.put("tipoVehiculo", tipoVehiculo.getJSON());
		if (conductor != null) {
			JSONObject conductorJSON = conductor.getJSON();
			conductorJSON.put("edad", conductorEdad);
			conductorJSON.put("genero", conductorGenero);
			conductorJSON.put("estadoCivil", conductorEstadoCivil);
			retorno.put("conductor", conductorJSON);
		}
		
		if (!extras.isEmpty()) {
			JSONArray extrasJSON = new JSONArray();
			for (Extra extra : extras) {
				extrasJSON.add(extra.getJSON());
			}
			retorno.put("extras", extrasJSON);
		}

		return retorno;
	}
}