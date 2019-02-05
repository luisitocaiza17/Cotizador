package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Usuario.buscarPorId", query="SELECT c FROM Usuario c where c.id = :id"),
	@NamedQuery(name="Usuario.buscarPorEntidadId", query="SELECT c FROM Usuario c where c.entidad = :entidad"),
	@NamedQuery(name="Usuario.buscarActivos", query="SELECT c FROM Usuario c WHERE c.activo =:valorActivo"),
	@NamedQuery(name="Usuario.buscarPorLogin", query="SELECT c FROM Usuario c where c.login = :login"),
	@NamedQuery(name="Usuario.buscarTodos", query="SELECT c FROM Usuario c"),
	@NamedQuery(name="Usuario.buscarPorConfirmacionMail", query="SELECT c FROM Usuario c where c.validacionMail = :confirmacion"),	
	@NamedQuery(name="Usuario.buscarPorEntidad", query="SELECT c FROM Usuario c where c.entidad = :entidad")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private boolean activo;
	
	private boolean emite;

	private String login;

	@Column(name="validacion_mail")
	private String validacionMail;

	//bi-directional many-to-one association to Credencial
	@OneToMany(mappedBy="usuario")
	private List<Credencial> credencials;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="usuario")
	private List<Session> sessions;

	//bi-directional many-to-one association to Entidad
	@ManyToOne
	private Entidad entidad;

	//bi-directional many-to-one association to UsuarioRol
	@OneToMany(mappedBy="usuario")
	private List<UsuarioRol> usuarioRols;

	//bi-directional many-to-one association to UsuarioXPuntoVenta
	@OneToMany(mappedBy="usuario")
	private List<UsuarioXPuntoVenta> usuarioXPuntoVentas;

	//bi-directional many-to-one association to SolicitudInspeccion
	@OneToMany(mappedBy="usuario")
	private List<SolicitudInspeccion> solicitudInspeccions;
	
	public Usuario() {
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

	public boolean getEmite() {
		return this.emite;
	}

	public void setEmite(boolean emite) {
		this.emite = emite;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getValidacionMail() {
		return this.validacionMail;
	}

	public void setValidacionMail(String validacionMail) {
		this.validacionMail = validacionMail;
	}

	public List<Credencial> getCredencials() {
		return this.credencials;
	}

	public void setCredencials(List<Credencial> credencials) {
		this.credencials = credencials;
	}

	public Credencial addCredencial(Credencial credencial) {
		getCredencials().add(credencial);
		credencial.setUsuario(this);

		return credencial;
	}

	public Credencial removeCredencial(Credencial credencial) {
		getCredencials().remove(credencial);
		credencial.setUsuario(null);

		return credencial;
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setUsuario(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setUsuario(null);

		return session;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public List<UsuarioRol> getUsuarioRols() {
		return this.usuarioRols;
	}

	public void setUsuarioRols(List<UsuarioRol> usuarioRols) {
		this.usuarioRols = usuarioRols;
	}

	public UsuarioRol addUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().add(usuarioRol);
		usuarioRol.setUsuario(this);

		return usuarioRol;
	}

	public UsuarioRol removeUsuarioRol(UsuarioRol usuarioRol) {
		getUsuarioRols().remove(usuarioRol);
		usuarioRol.setUsuario(null);

		return usuarioRol;
	}

	public List<UsuarioXPuntoVenta> getUsuarioXPuntoVentas() {
		return this.usuarioXPuntoVentas;
	}

	public void setUsuarioXPuntoVentas(List<UsuarioXPuntoVenta> usuarioXPuntoVentas) {
		this.usuarioXPuntoVentas = usuarioXPuntoVentas;
	}

	public UsuarioXPuntoVenta addUsuarioXPuntoVenta(UsuarioXPuntoVenta usuarioXPuntoVenta) {
		getUsuarioXPuntoVentas().add(usuarioXPuntoVenta);
		usuarioXPuntoVenta.setUsuario(this);

		return usuarioXPuntoVenta;
	}

	public UsuarioXPuntoVenta removeUsuarioXPuntoVenta(UsuarioXPuntoVenta usuarioXPuntoVenta) {
		getUsuarioXPuntoVentas().remove(usuarioXPuntoVenta);
		usuarioXPuntoVenta.setUsuario(null);

		return usuarioXPuntoVenta;
	}
	public List<SolicitudInspeccion> getSolicitudInspeccions() {
		return this.solicitudInspeccions;
	}

	public void setSolicitudInspeccions(List<SolicitudInspeccion> solicitudInspeccions) {
		this.solicitudInspeccions = solicitudInspeccions;
	}

	public SolicitudInspeccion addSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().add(solicitudInspeccion);
		solicitudInspeccion.setUsuario(this);

		return solicitudInspeccion;
	}

	public SolicitudInspeccion removeSolicitudInspeccion(SolicitudInspeccion solicitudInspeccion) {
		getSolicitudInspeccions().remove(solicitudInspeccion);
		solicitudInspeccion.setUsuario(null);

		return solicitudInspeccion;
	}
}