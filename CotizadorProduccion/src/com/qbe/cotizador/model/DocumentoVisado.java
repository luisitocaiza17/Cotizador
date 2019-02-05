package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the DOCUMENTO_VISADO database table.
 * 
 */
@Entity
@Table(name="DOCUMENTO_VISADO")
@NamedQueries({
	@NamedQuery(name="DocumentoVisado.buscarPorId", query="SELECT c FROM DocumentoVisado c where c.id = :id order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarTodos", query="SELECT d FROM DocumentoVisado d"),
	@NamedQuery(name="DocumentoVisado.buscarPorEntidad", query="SELECT c FROM DocumentoVisado c where c.entidad = :entidad order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarPorObjetoId", query="SELECT c FROM DocumentoVisado c where c.objetoId = :objetoId order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarPorCotizacionId", query="SELECT c FROM DocumentoVisado c where c.cotizacion = :cotizacion order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarPorCotizacionTipoDocumento", query="SELECT c FROM DocumentoVisado c where c.cotizacion = :cotizacion and c.tipoDocumento =:tipoDocumento order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarPorObjetoIdTipoDocumento", query="SELECT c FROM DocumentoVisado c where c.objetoId = :objetoId and c.tipoDocumento =:tipoDocumento order by c.fechaSubida desc"),
	@NamedQuery(name="DocumentoVisado.buscarPorEntidadTipoDocumento", query="SELECT c FROM DocumentoVisado c where c.entidad = :entidad and c.tipoDocumento =:tipoDocumento order by c.fechaSubida desc")
})
public class DocumentoVisado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private byte[] contenido;

	@JoinColumn(name="cotizacion_id")
	private Cotizacion cotizacion;

	@JoinColumn(name="entidad_id")
	private Entidad entidad;

	@Column(name="fecha_subida")
	private Timestamp fechaSubida;

	private String nombre;

	@Column(name="objeto_id")
	private String objetoId;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipo_documento_id")
	private TipoDocumento tipoDocumento;

	public DocumentoVisado() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[]  getContenido() {
		return this.contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Timestamp getFechaSubida() {
		return this.fechaSubida;
	}

	public void setFechaSubida(Timestamp fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(String objetoId) {
		this.objetoId = objetoId;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

}