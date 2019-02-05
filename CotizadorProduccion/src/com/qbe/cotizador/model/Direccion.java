package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.util.List;


/**
 * The persistent class for the DIRECCION database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Direccion.buscarPorId", query="SELECT d FROM Direccion d WHERE d.id=:id"),
	@NamedQuery(name="Direccion.buscarTodos", query="SELECT d FROM Direccion d"),
	@NamedQuery(name="Direccion.buscarPorEntidadId", query="SELECT c FROM Direccion c WHERE c.entidad =:entidad"),
	@NamedQuery(name="Direccion.buscarCobroPorEntidadId", query="SELECT c FROM Direccion c WHERE c.entidad =:entidad and c.esCobro=true"),
	@NamedQuery(name="Direccion.buscarPorTipoDireccionEntidad", query="SELECT c FROM Direccion c WHERE c.entidad =:entidad and c.tipoDireccion=:tipoDireccion")
})
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="calle_principal")
	private String callePrincipal;

	@Column(name="calle_secundaria")
	private String calleSecundaria;

	@Column(name="`datos de referencia`")
	private String datosDeReferencia;

	@Column(name="es_cobro")
	private boolean esCobro;

	private String numero;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	private Ciudad ciudad;

	//bi-directional many-to-one association to Entidad
	@ManyToOne
	private Entidad entidad;

	//bi-directional many-to-one association to Parroquia
	@ManyToOne
	private Parroquia parroquia;

	//bi-directional many-to-one association to TipoDireccion
	@ManyToOne
	@JoinColumn(name="tipo_direccion_id")
	private TipoDireccion tipoDireccion;

	//bi-directional many-to-one association to Zona
	@ManyToOne
	private Zona zona;

	//bi-directional many-to-one association to Upla
	@OneToMany(mappedBy="direccion")
	private List<Upla> uplas;

	public Direccion() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallePrincipal() {
		return this.callePrincipal;
	}

	public void setCallePrincipal(String callePrincipal) {
		this.callePrincipal = callePrincipal;
	}

	public String getCalleSecundaria() {
		return this.calleSecundaria;
	}

	public void setCalleSecundaria(String calleSecundaria) {
		this.calleSecundaria = calleSecundaria;
	}

	public String getDatosDeReferencia() {
		return this.datosDeReferencia;
	}

	public void setDatosDeReferencia(String datosDeReferencia) {
		this.datosDeReferencia = datosDeReferencia;
	}

	public boolean getEsCobro() {
		return this.esCobro;
	}

	public void setEsCobro(boolean esCobro) {
		this.esCobro = esCobro;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	public TipoDireccion getTipoDireccion() {
		return this.tipoDireccion;
	}

	public void setTipoDireccion(TipoDireccion tipoDireccion) {
		this.tipoDireccion = tipoDireccion;
	}

	public Zona getZona() {
		return this.zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<Upla> getUplas() {
		return this.uplas;
	}

	public void setUplas(List<Upla> uplas) {
		this.uplas = uplas;
	}

	public Upla addUpla(Upla upla) {
		getUplas().add(upla);
		upla.setDireccion(this);

		return upla;
	}

	public Upla removeUpla(Upla upla) {
		getUplas().remove(upla);
		upla.setDireccion(null);

		return upla;
	}
	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("callePrincipal", callePrincipal);
		retorno.put("calleSecundaria", calleSecundaria);
		retorno.put("numero", numero);
		if(zona!=null)
			retorno.put("zona", zona.getNombre());
		
		if(ciudad!=null)
			retorno.put("ciudad", ciudad.getJSON());

		if(parroquia!=null)
			retorno.put("parroquia", parroquia.getJSON());
		
		return retorno;	
	}

}