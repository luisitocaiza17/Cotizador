package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="USUARIOS_OFFLINE")
@NamedQueries({
	@NamedQuery(name="UsuariosOffline.buscarPorId", query="SELECT c FROM UsuariosOffline c WHERE c.id = :id"),
	@NamedQuery(name="UsuariosOffline.buscarUsuario", query="SELECT c FROM UsuariosOffline c WHERE c.usuario = :usuario AND c.clave = :clave"),
	@NamedQuery(name="UsuariosOffline.buscarTodos", query="SELECT c FROM UsuariosOffline c"),
	@NamedQuery(name="UsuariosOffline.buscarNombre", query="SELECT c FROM UsuariosOffline c WHERE CONCAT(c.nombres,' ',c.apellidos) = :nombres")
	})

public class UsuariosOffline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="CLAVE")
	private String clave;

	@Column(name="AGENCIA")
	private String agencia;
	
	@Column(name="IDENTIFICACION")
	private String identificacion;
	
	@Column(name="NOMBRES")
	private String nombres;
		
	@Column(name="APELLIDOS")
	private String apellidos;
	
	@Column(name="PUNTOVENTA")
	private String puntoVenta;

	@Column(name="CORREOELECTRONICO")
	private String correoElectronico;
	
	@Column(name="UNIDADNEGOCIO")
	private String unidadNegocio;
	
	public String getUnidadNegocio() {
		return unidadNegocio;
	}

	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getPuntoVenta() {
		return puntoVenta;
	}

	public void setPuntoVenta(String puntoVenta) {
		this.puntoVenta = puntoVenta;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}