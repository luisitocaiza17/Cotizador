package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ENTIDAD database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Entidad.buscarPorId", query="SELECT c FROM Entidad c where c.id = :id"),
	@NamedQuery(name="Entidad.buscarPorIdentificacion", query="SELECT count(c) FROM Entidad c WHERE c.identificacion =:identificacion"),
	@NamedQuery(name="Entidad.buscarEntidadPorIdentificacion", query="SELECT c FROM Entidad c WHERE c.identificacion =:identificacion"),
	@NamedQuery(name="Entidad.buscarEntidadPorMail", query="SELECT c FROM Entidad c WHERE c.mail =:mail"),
	@NamedQuery(name="Entidad.buscarTodos", query="SELECT c FROM Entidad c"),
	@NamedQuery(name="Entidad.buscarEntidadPorIdEnsurance", query="SELECT c FROM Entidad c WHERE c.entEnsurance =:idEnsurance")
})
public class Entidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String apellidos;

	@Column(name="ent_ensurance")
	private String entEnsurance;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	private String identificacion;

	private String mail;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	private String nombres;
	
	private String telefono;
	
	private String celular;
	
	@Column(name="telefono_extension")
	private String telefonoExtension;

	//bi-directional many-to-one association to Agente
	@OneToMany(mappedBy="entidad")
	private List<Agente> agentes;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="entidad")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Direccion
	@OneToMany(mappedBy="entidad")
	private List<Direccion> direccions;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="entidad")
	private List<Empleado> empleados;

	//bi-directional many-to-one association to TipoEntidad
	@ManyToOne
	@JoinColumn(name="tipo_entidad_id")
	private TipoEntidad tipoEntidad;
	
	//bi-directional many-to-one association to TipoIdentificacion
	@ManyToOne
	@JoinColumn(name="tipo_identificacion_id")
	private TipoIdentificacion tipoIdentificacion;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="entidad")
	private List<Usuario> usuarios;
	
	public Entidad() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEntEnsurance() {
		return this.entEnsurance;
	}

	public void setEntEnsurance(String entEnsurance) {
		this.entEnsurance = entEnsurance;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefonoExtension() {
		return telefonoExtension;
	}

	public void setTelefonoExtension(String telefonoExtension) {
		this.telefonoExtension = telefonoExtension;
	}

	public List<Agente> getAgentes() {
		return this.agentes;
	}

	public void setAgentes(List<Agente> agentes) {
		this.agentes = agentes;
	}

	public Agente addAgente(Agente agente) {
		getAgentes().add(agente);
		agente.setEntidad(this);

		return agente;
	}

	public Agente removeAgente(Agente agente) {
		getAgentes().remove(agente);
		agente.setEntidad(null);

		return agente;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setEntidad(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setEntidad(null);

		return cliente;
	}

	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setEntidad(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setEntidad(null);

		return direccion;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setEntidad(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setEntidad(null);

		return empleado;
	}

	public TipoIdentificacion getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public TipoEntidad getTipoEntidad() {
		return this.tipoEntidad;
	}

	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEntidad(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEntidad(null);

		return usuario;
	}
	
	public JSONObject getJSON(){
		JSONObject retorno=new JSONObject();
		retorno.put("nombres", nombres);
		retorno.put("apellidos", apellidos);
		retorno.put("nombreCompleto", nombreCompleto);
		retorno.put("identificacion", identificacion);
		retorno.put("correo", mail);
		retorno.put("telefono", telefono);
		retorno.put("celular", celular);
		return retorno;
	}

}