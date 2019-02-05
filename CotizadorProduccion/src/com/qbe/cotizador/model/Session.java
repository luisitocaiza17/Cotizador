package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SESSION database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Session.buscarPorId", query="SELECT c FROM Session c where c.id = :id"),
	@NamedQuery(name="Session.buscarPorToken", query="SELECT c FROM Session c where c.token = :token"),
	@NamedQuery(name="Session.buscarPorUsuario", query="SELECT c FROM Session c where c.usuario = :usuario"),
    @NamedQuery(name="Session.eliminarPorUsuario", query="delete FROM Session c where c.usuario = :usuario ")
})
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="hora_fin")
	private Timestamp horaFin;

	@Column(name="hora_inicio")
	private Timestamp horaInicio;
	
	private String token;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public Session() {
	}
	
	public Session(Timestamp horaFin, Timestamp horaInicio, String token, Usuario usuario) {
		super();
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
		this.token = token;
		this.usuario = usuario;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}

	public Timestamp getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}