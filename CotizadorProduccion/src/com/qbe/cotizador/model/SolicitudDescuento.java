package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SOLICITUD_DESCUENTO database table.
 * 
 */
@Entity
@Table(name="SOLICITUD_DESCUENTO")
@NamedQueries({
	@NamedQuery(name="SolicitudDescuento.buscarPorId", query="SELECT c FROM SolicitudDescuento c where c.id = :id"),
	@NamedQuery(name="SolicitudDescuento.buscarPorCotizacionEstado", query="SELECT c FROM SolicitudDescuento c WHERE c.cotizacion =:cotizacion and c.estado =:estado"),
	@NamedQuery(name="SolicitudDescuento.buscarTodos", query="SELECT c FROM SolicitudDescuento c"),
	@NamedQuery(name="SolicitudDescuento.buscarPorCotizacion", query="SELECT c FROM SolicitudDescuento c WHERE c.cotizacion =:cotizacion")
})
public class SolicitudDescuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private String descripcion;

	private String porcentaje;

	private String comentario;

	//bi-directional many-to-one association to Cotizacion
	@ManyToOne
	private Cotizacion cotizacion;

	//bi-directional many-to-one association to Descuento
	@ManyToOne
	private Descuento descuento;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	private Estado estado;

	//bi-directional many-to-one association to MotivoDescuento
	@ManyToOne
	@JoinColumn(name="motivo_descuento_id")
	private MotivoDescuento motivoDescuento;

	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	private Sucursal sucursal;

	//bi-directional many-to-one association to UnidadNegocio
	@ManyToOne
	@JoinColumn(name="unidad_negocio_id")
	private UnidadNegocio unidadNegocio;

	@Column(name="usuario_id")
	private java.math.BigInteger usuarioId;
	
	public SolicitudDescuento() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Cotizacion getCotizacion() {
		return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public Descuento getDescuento() {
		return this.descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public MotivoDescuento getMotivoDescuento() {
		return this.motivoDescuento;
	}

	public void setMotivoDescuento(MotivoDescuento motivoDescuento) {
		this.motivoDescuento = motivoDescuento;
	}

	public Sucursal getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public UnidadNegocio getUnidadNegocio() {
		return this.unidadNegocio;
	}

	public void setUnidadNegocio(UnidadNegocio unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}

	public java.math.BigInteger getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(java.math.BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}