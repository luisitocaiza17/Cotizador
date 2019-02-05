package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the MODELO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Modelo.buscarPorId", query="SELECT c FROM Modelo c where c.id =:id"),
	@NamedQuery(name="Modelo.buscarActivos", query="SELECT c FROM Modelo c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Modelo.buscarTodos", query="SELECT c FROM Modelo c"),
	@NamedQuery(name="Modelo.buscarPorNombre", query="SELECT c FROM Modelo c where c.nombre =:nombre"),
	@NamedQuery(name="Modelo.buscarPorMarcaYNombre", query="SELECT c FROM Modelo c where c.marca = :marca and upper(c.nombre) = :nombre"),
	@NamedQuery(name="Modelo.buscarPorCodigoEnsurance", query="SELECT c FROM Modelo c where c.modEnsurance = :mod_ensurance"),
	@NamedQuery(name="Modelo.buscarPorMarca", query="SELECT distinct c FROM Modelo c where c.marca = :marca order by c.nombre")
})
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private Boolean activo;

	@Column(name="indice_dano_parcial_frecuencia")
	private String indiceDanoParcialFrecuencia;

	@Column(name="mod_ensurance")
	private String modEnsurance;

	private String nombre;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	private Marca marca;

	//bi-directional many-to-one association to ModeloClasificaRiesgo
	@OneToMany(mappedBy="modelo")
	private List<ModeloClasificaRiesgo> modeloClasificaRiesgos;


	//bi-directional many-to-one association to ObjetoVehiculo
	@OneToMany(mappedBy="modelo")
	private List<ObjetoVehiculo> objetoVehiculos;

	//bi-directional many-to-one association to PrecioReferencial
	@OneToMany(mappedBy="modelo")
	private List<PrecioReferencial> precioReferencials;

	private double tonelaje;

	//bi-directional many-to-one association to ModeloGenerico
	@ManyToOne
	@JoinColumn(name="modelo_generico_id")
	private ModeloGenerico modeloGenerico;

	//bi-directional many-to-one association to ModeloGenerico
	@ManyToOne
	@JoinColumn(name="clase_vehiculo_id")
	private ClaseVehiculo claseVehiculo;
	
	//bi-directional many-to-one association to ModeloGenerico
	@ManyToOne
	@JoinColumn(name="modelo_tarificador_1_id")
	private  VhTari1MarcasModelos vhTari1MarcasModelos;

	
	public Modelo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getIndiceDanoParcialFrecuencia() {
		return this.indiceDanoParcialFrecuencia;
	}

	public void setIndiceDanoParcialFrecuencia(String indiceDanoParcialFrecuencia) {
		this.indiceDanoParcialFrecuencia = indiceDanoParcialFrecuencia;
	}

	public String getModEnsurance() {
		return this.modEnsurance;
	}

	public void setModEnsurance(String modEnsurance) {
		this.modEnsurance = modEnsurance;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<ModeloClasificaRiesgo> getModeloClasificaRiesgos() {
		return this.modeloClasificaRiesgos;
	}

	public void setModeloClasificaRiesgos(List<ModeloClasificaRiesgo> modeloClasificaRiesgos) {
		this.modeloClasificaRiesgos = modeloClasificaRiesgos;
	}

	public ModeloClasificaRiesgo addModeloClasificaRiesgo(ModeloClasificaRiesgo modeloClasificaRiesgo) {
		getModeloClasificaRiesgos().add(modeloClasificaRiesgo);
		modeloClasificaRiesgo.setModelo(this);

		return modeloClasificaRiesgo;
	}

	public ModeloClasificaRiesgo removeModeloClasificaRiesgo(ModeloClasificaRiesgo modeloClasificaRiesgo) {
		getModeloClasificaRiesgos().remove(modeloClasificaRiesgo);
		modeloClasificaRiesgo.setModelo(null);

		return modeloClasificaRiesgo;
	}

	public List<ObjetoVehiculo> getObjetoVehiculos() {
		return this.objetoVehiculos;
	}

	public void setObjetoVehiculos(List<ObjetoVehiculo> objetoVehiculos) {
		this.objetoVehiculos = objetoVehiculos;
	}

	public ObjetoVehiculo addObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().add(objetoVehiculo);
		objetoVehiculo.setModelo(this);

		return objetoVehiculo;
	}

	public ObjetoVehiculo removeObjetoVehiculo(ObjetoVehiculo objetoVehiculo) {
		getObjetoVehiculos().remove(objetoVehiculo);
		objetoVehiculo.setModelo(null);

		return objetoVehiculo;
	}

	public List<PrecioReferencial> getPrecioReferencials() {
		return this.precioReferencials;
	}

	public void setPrecioReferencials(List<PrecioReferencial> precioReferencials) {
		this.precioReferencials = precioReferencials;
	}

	public PrecioReferencial addPrecioReferencial(PrecioReferencial precioReferencial) {
		getPrecioReferencials().add(precioReferencial);
		precioReferencial.setModelo(this);

		return precioReferencial;
	}

	public PrecioReferencial removePrecioReferencial(PrecioReferencial precioReferencial) {
		getPrecioReferencials().remove(precioReferencial);
		precioReferencial.setModelo(null);

		return precioReferencial;
	}

	public double getTonelaje() {
		return tonelaje;
	}

	public void setTonelaje(double tonelaje) {
		this.tonelaje = tonelaje;
	}

	public ModeloGenerico getModeloGenerico() {
		return modeloGenerico;
	}

	public void setModeloGenerico(ModeloGenerico modeloGenerico) {
		this.modeloGenerico = modeloGenerico;
	}

	public ClaseVehiculo getClaseVehiculo() {
		return claseVehiculo;
	}

	public void setClaseVehiculo(ClaseVehiculo claseVehiculo) {
		this.claseVehiculo = claseVehiculo;
	}

	public VhTari1MarcasModelos getVhTari1MarcasModelos() {
		return vhTari1MarcasModelos;
	}

	public void setVhTari1MarcasModelos(VhTari1MarcasModelos vhTari1MarcasModelos) {
		this.vhTari1MarcasModelos = vhTari1MarcasModelos;
	}
	
	public JSONObject getJSON(){
		JSONObject modelo=new JSONObject();
		modelo.put("id", id);
		modelo.put("marca", marca.getJSON());
		modelo.put("activo", activo);
		modelo.put("nombre", nombre);
		return modelo;
	}
}