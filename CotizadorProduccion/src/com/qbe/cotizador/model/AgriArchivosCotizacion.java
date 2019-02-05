package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author vzhagui
 * The persistent class for the AGRI_AUDITORIA_COTIZACION database table.
 *
 */
@Entity
@Table(name="AGRI_ARCHIVOS_COTIZACION")
@NamedQueries({
	@NamedQuery(name="AgriArchivosCotizacion.findAll", query="SELECT a FROM AgriArchivosCotizacion a"),
	@NamedQuery(name="AgriArchivosCotizacion.buscarPorId", query="SELECT c FROM AgriArchivosCotizacion c where c.Id = :Id")		
	})
public class AgriArchivosCotizacion implements Serializable 
{
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private BigInteger Id;
	
	private BigInteger UsuarioId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_GENERACION")
	private Date FechaGeneracion;

	private String Tipo;
	
	private String NombreArchivo;
	
	private byte[] File;
	
	public AgriArchivosCotizacion() {
		// TODO Auto-generated constructor stub
	}
	public BigInteger getId() {
		return this.Id;
	}

	public void setId(BigInteger id) {
		this.Id = id;
	}
	public BigInteger getUsuarioId() {
		return this.UsuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.UsuarioId = usuarioId;
	}
	public Date getFechaGeneracion() {
		return this.FechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.FechaGeneracion = fechaGeneracion;
	}
	public String getTipo() {
		return this.Tipo;
	}

	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	public String getNombreArchivo() {
		return this.NombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.NombreArchivo = nombreArchivo;
	}
	
	public byte[] getFile() {
		return this.File;
	}

	public void setFile(byte[] file) {
		this.File = file;
	}
}
