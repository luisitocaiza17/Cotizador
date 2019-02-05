package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

@Entity
@Table(name="NOTIFICACION")
@NamedQueries({
	@NamedQuery(name="Notificacion.buscarTodos", query="SELECT p FROM Notificacion p"),
	@NamedQuery(name="Notificacion.buscarPorId", query="SELECT p FROM Notificacion p where p.notificacionId = :notificacionId"),
	@NamedQuery(name="Notificacion.buscarPorProceso", query="SELECT p FROM Notificacion p where p.proceso = :proceso")
})
public class Notificacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NOTIFICACION_ID")
	private BigInteger notificacionId;
	
	@Column(name="PROCESO")
	private String proceso;
	
	@Column(name="NOTIFICA_CLIENTE")
	private boolean notificacionCliente;
	
	@Column(name="NOTIFICA_USUARIO")
	private boolean notificacionUsuario;
	
	@Column(name="NOTIFICA_INSPECTOR")
	private boolean notificacionInspector;
	
	@Column(name="NOTIFICA_EJECUTIVO")
	private boolean notificacionEjecutivo;
	
	@Column(name="EMAIL_INSPECTOR")
	private String emailInspector;
	
	@Column(name="PLANTILLA_NOMBRE")
	private String plantillaNombre;

	public BigInteger getNotificacionId() {
		return notificacionId;
	}

	public void setNotificacionId(BigInteger notificacionId) {
		this.notificacionId = notificacionId;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public boolean isNotificacionCliente() {
		return notificacionCliente;
	}

	public void setNotificacionCliente(boolean notificacionCliente) {
		this.notificacionCliente = notificacionCliente;
	}
	
	public boolean isNotificacionEjecutivo() {
		return notificacionEjecutivo;
	}

	public void setNotificacionEjecutivo(boolean notificacionEjecutivo) {
		this.notificacionEjecutivo = notificacionEjecutivo;
	}

	public boolean isNotificacionUsuario() {
		return notificacionUsuario;
	}

	public void setNotificacionUsuario(boolean notificacionUsuario) {
		this.notificacionUsuario = notificacionUsuario;
	}

	public boolean isNotificacionInspector() {
		return notificacionInspector;
	}

	public void setNotificacionInspector(boolean notificacionInspector) {
		this.notificacionInspector = notificacionInspector;
	}

	public String getPlantillaNombre() {
		return plantillaNombre;
	}

	public void setPlantillaNombre(String plantillaNombre) {
		this.plantillaNombre = plantillaNombre;
	}

	public String getEmailInspector() {
		return emailInspector;
	}

	public void setEmailInspector(String emailInspector) {
		this.emailInspector = emailInspector;
	}
}
