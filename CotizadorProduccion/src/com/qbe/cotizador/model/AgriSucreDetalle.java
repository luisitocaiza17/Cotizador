package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the AGRI_SUCRE_DETALLE database table.
 * 
 */
@Entity
@Table(name="AGRI_SUCRE_DETALLE")
@NamedQueries({
@NamedQuery(name="AgriSucreDetalle.buscarObjetoAgricolaId", query="SELECT a FROM AgriSucreDetalle a where a.agriObjetoCotizacion = :id"),
@NamedQuery(name="AgriSucreDetalle.buscarEntidadId", query="SELECT p FROM AgriSucreDetalle p where p.entidadId = :id"),
@NamedQuery(name="AgriSucreDetalle.buscarId", query="SELECT p FROM AgriSucreDetalle p where p.id = :id")
})
public class AgriSucreDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="AGRI_OBJETO_COTIZACION")
	private String agriObjetoCotizacion;

	@Column(name="CONDICION_PREDIO")
	private String condicionPredio;

	@Column(name="CONDICION_PREDIOOTRA")
	private String condicionPrediootra;

	@Column(name="COSTO_ESTABLECIMIENTO_CULTIVO")
	private String costoEstablecimientoCultivo;

	@Column(name="COSTO_MANTENIMIENTO_CULTIVO")
	private String costoMantenimientoCultivo;

	@Column(name="ENTIDAD_ID")
	private String entidadId;

	@Column(name="ESTADO_SOLICITUD")
	private String estadoSolicitud;

	private String lote;

	@Column(name="NUMERO_RESOLUCION")
	private String numeroResolucion;

	@Column(name="OTRO_RIESGO")
	private String otroRiesgo;

	private String paquetetecnologico;

	@Column(name="TIPO_CANAL")
	private String tipoCanal;

	@Column(name="TIPO_RIESGO")
	private String tipoRiesgo;

	@Column(name="VALOR_SUBSIDIO")
	private String valorSubsidio;

	public AgriSucreDetalle() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgriObjetoCotizacion() {
		return this.agriObjetoCotizacion;
	}

	public void setAgriObjetoCotizacion(String agriObjetoCotizacion) {
		this.agriObjetoCotizacion = agriObjetoCotizacion;
	}

	public String getCondicionPredio() {
		return this.condicionPredio;
	}

	public void setCondicionPredio(String condicionPredio) {
		this.condicionPredio = condicionPredio;
	}

	public String getCondicionPrediootra() {
		return this.condicionPrediootra;
	}

	public void setCondicionPrediootra(String condicionPrediootra) {
		this.condicionPrediootra = condicionPrediootra;
	}

	public String getCostoEstablecimientoCultivo() {
		return this.costoEstablecimientoCultivo;
	}

	public void setCostoEstablecimientoCultivo(String costoEstablecimientoCultivo) {
		this.costoEstablecimientoCultivo = costoEstablecimientoCultivo;
	}

	public String getCostoMantenimientoCultivo() {
		return this.costoMantenimientoCultivo;
	}

	public void setCostoMantenimientoCultivo(String costoMantenimientoCultivo) {
		this.costoMantenimientoCultivo = costoMantenimientoCultivo;
	}

	public String getEntidadId() {
		return this.entidadId;
	}

	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}

	public String getEstadoSolicitud() {
		return this.estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNumeroResolucion() {
		return this.numeroResolucion;
	}

	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public String getOtroRiesgo() {
		return this.otroRiesgo;
	}

	public void setOtroRiesgo(String otroRiesgo) {
		this.otroRiesgo = otroRiesgo;
	}

	public String getPaquetetecnologico() {
		return this.paquetetecnologico;
	}

	public void setPaquetetecnologico(String paquetetecnologico) {
		this.paquetetecnologico = paquetetecnologico;
	}

	public String getTipoCanal() {
		return this.tipoCanal;
	}

	public void setTipoCanal(String tipoCanal) {
		this.tipoCanal = tipoCanal;
	}

	public String getTipoRiesgo() {
		return this.tipoRiesgo;
	}

	public void setTipoRiesgo(String tipoRiesgo) {
		this.tipoRiesgo = tipoRiesgo;
	}

	public String getValorSubsidio() {
		return this.valorSubsidio;
	}

	public void setValorSubsidio(String valorSubsidio) {
		this.valorSubsidio = valorSubsidio;
	}

}