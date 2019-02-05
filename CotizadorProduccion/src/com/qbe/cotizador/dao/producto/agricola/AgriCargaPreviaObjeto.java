package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AgriCargaPreviaObjeto {

	private String id;

	private String canalNombre;
	
	private String clienteId;
	
	private String clienteNombre;		
	
	private String clienteIdentificacion;

	private String montoAsegurado;	
	
	private String solicitudFecha;
	
	private String siembraFecha;
	
	private String tipoCultivoId;
	
	private String tipoCultivoNombre;
	
	private String numerHasAseguradas;
	
	private String numeroHasLote;
	
	private String esTecnificado;
		
	private String provinciaId;
	
	private String provinciaNombre;	
	
	private String cantonId;
	
	private String cantonNombre;
	
	private String parroquiaId;
	
	private String parroquiaNombre;
	
	private String ubicacionCultivo;
	
	private String telefono;
	
	private String gpsX;
	
	private String gpsY;
	
	private String usuarioId;
	
	private String cargaFecha;
	
	private String estado;
	
	private String clienteApellido;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCanalNombre() {
		return canalNombre;
	}

	public void setCanalNombre(String canalNombre) {
		this.canalNombre = canalNombre;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public String getClienteIdentificacion() {
		return clienteIdentificacion;
	}

	public void setClienteIdentificacion(String clienteIdentificacion) {
		this.clienteIdentificacion = clienteIdentificacion;
	}

	public String getMontoAsegurado() {
		return montoAsegurado;
	}

	public void setMontoAsegurado(String montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}

	public String getSolicitudFecha() {
		return solicitudFecha;
	}

	public void setSolicitudFecha(String solicitudFecha) {
		this.solicitudFecha = solicitudFecha;
	}

	public String getSiembraFecha() {
		return siembraFecha;
	}

	public void setSiembraFecha(String siembraFecha) {
		this.siembraFecha = siembraFecha;
	}

	public String getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(String tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getTipoCultivoNombre() {
		return tipoCultivoNombre;
	}

	public void setTipoCultivoNombre(String tipoCultivoNombre) {
		this.tipoCultivoNombre = tipoCultivoNombre;
	}

	public String getNumerHasAseguradas() {
		return numerHasAseguradas;
	}

	public void setNumerHasAseguradas(String numerHasAseguradas) {
		this.numerHasAseguradas = numerHasAseguradas;
	}

	public String getNumeroHasLote() {
		return numeroHasLote;
	}

	public void setNumeroHasLote(String numeroHasLote) {
		this.numeroHasLote = numeroHasLote;
	}

	public String isEsTecnificado() {
		return esTecnificado;
	}

	public void setEsTecnificado(String esTecnificado) {
		this.esTecnificado = esTecnificado;
	}

	public String getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(String provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public String getCantonId() {
		return cantonId;
	}

	public void setCantonId(String cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonNombre() {
		return cantonNombre;
	}

	public void setCantonNombre(String cantonNombre) {
		this.cantonNombre = cantonNombre;
	}

	public String getParroquiaId() {
		return parroquiaId;
	}

	public void setParroquiaId(String parroquiaId) {
		this.parroquiaId = parroquiaId;
	}

	public String getParroquiaNombre() {
		return parroquiaNombre;
	}

	public void setParroquiaNombre(String parroquiaNombre) {
		this.parroquiaNombre = parroquiaNombre;
	}

	public String getUbicacionCultivo() {
		return ubicacionCultivo;
	}

	public void setUbicacionCultivo(String ubicacionCultivo) {
		this.ubicacionCultivo = ubicacionCultivo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getGpsX() {
		return gpsX;
	}

	public void setGpsX(String gpsX) {
		this.gpsX = gpsX;
	}

	public String getGpsY() {
		return gpsY;
	}

	public void setGpsY(String gpsY) {
		this.gpsY = gpsY;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCargaFecha() {
		return cargaFecha;
	}

	public void setCargaFecha(String cargaFecha) {
		this.cargaFecha = cargaFecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getClienteApellido() {
		return clienteApellido;
	}

	public void setClienteApellido(String clienteApellido) {
		this.clienteApellido = clienteApellido;
	}
	
}
