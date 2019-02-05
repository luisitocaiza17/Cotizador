package com.qbe.cotizador.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * The persistent class for the AGRI_OBJETO_COTIZACION database table.
 * 
 */
@Entity
@Table(name="AGRI_CARGA_PREVIA_ARCHIVO_PLANO")
@NamedQueries({
	@NamedQuery(name="AgriCargaPreviaArchivoPlano.buscarTodos", query="SELECT p FROM AgriCargaPreviaArchivoPlano p"),
	@NamedQuery(name="AgriCargaPreviaArchivoPlano.buscarPorId", query="SELECT c FROM AgriCargaPreviaArchivoPlano c where c.id =:id"),
	@NamedQuery(name="AgriCargaPreviaArchivoPlano.buscarPorIdentificacion", query="SELECT c FROM AgriCargaPreviaArchivoPlano c where c.clienteIdentificacion =:clienteIdentificacion and c.estado =:estado")
})

public class AgriCargaPreviaArchivoPlano implements Serializable{	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CARGA_PREVIA_ARCHIVO_PLANO_ID")
	private BigInteger id;

	@Column(name="CANAL_NOMBRE")
	private String canalNombre;
	
	@Column(name="CLIENTE_ID")
	private BigInteger clienteId;
	
	@Column(name="CLIENTE_NOMBRE")
	private String clienteNombre;		
	
	@Column(name="CLIENTE_IDENTIFICACION")
	private String clienteIdentificacion;

	@Column(name="MONTO_ASEGURADO")
	private Double montoAsegurado;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SOLICITUD_FECHA")
	private Date solicitudFecha;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SIEMBRA_FECHA")
	private Date siembraFecha;
	
	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;
	
	@Column(name="TIPO_CULTIVO_NOMBRE")
	private String tipoCultivoNombre;
	
	@Column(name="NUMERO_HAS_ASEGURADAS")
	private Double numerHasAseguradas;
	
	@Column(name="NUMERO_HAS_LOTE")
	private Double numeroHasLote;
	
	@Column(name="ES_TECNIFICADO")
	private boolean esTecnificado;
		
	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;
	
	@Column(name="PROVINCIA_NOMBRE")
	private String provinciaNombre;	
	
	@Column(name="CANTON_ID")
	private BigInteger cantonId;
	
	@Column(name="CANTON_NOMBRE")
	private String cantonNombre;
	
	@Column(name="PARROQUIA_ID")
	private BigInteger parroquiaId;
	
	@Column(name="PARROQUIA_NOMBRE")
	private String parroquiaNombre;
	
	@Column(name="UBICACION_CULTIVO")
	private String ubicacionCultivo;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="GPS_X")
	private String gpsX;
	
	@Column(name="GPS_Y")
	private String gpsY;
	
	@Column(name="USUARIO_ID")
	private BigInteger usuarioId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CARGA_FECHA")
	private Date cargaFecha;
	
	@Column(name="ESTADO")
	private int estado;
	
	@Column(name="CLIENTE_APELLIDO")
	private String clienteApellido;
	
	@Column(name="NOMBRE_COMPLETO")
	private String nombre_Completo;
	
	@Column(name="ACTIVO")
	private boolean activo;
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getNombre_Completo() {
		return nombre_Completo;
	}

	public void setNombre_Completo(String nombre_Completo) {
		this.nombre_Completo = nombre_Completo;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCanalNombre() {
		return canalNombre;
	}

	public void setCanalNombre(String canalNombre) {
		this.canalNombre = canalNombre;
	}

	public BigInteger getClienteId() {
		return clienteId;
	}

	public void setClienteId(BigInteger clienteId) {
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

	public Double getMontoAsegurado() {
		return montoAsegurado;
	}

	public void setMontoAsegurado(Double montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}

	public Date getSolicitudFecha() {
		return solicitudFecha;
	}

	public void setSolicitudFecha(Date solicitudFecha) {
		this.solicitudFecha = solicitudFecha;
	}

	public Date getSiembraFecha() {
		return siembraFecha;
	}

	public void setSiembraFecha(Date siembraFecha) {
		this.siembraFecha = siembraFecha;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getTipoCultivoNombre() {
		return tipoCultivoNombre;
	}

	public void setTipoCultivoNombre(String tipoCultivoNombre) {
		this.tipoCultivoNombre = tipoCultivoNombre;
	}

	public Double getNumerHasAseguradas() {
		return numerHasAseguradas;
	}

	public void setNumerHasAseguradas(Double numerHasAseguradas) {
		this.numerHasAseguradas = numerHasAseguradas;
	}

	public Double getNumeroHasLote() {
		return numeroHasLote;
	}

	public void setNumeroHasLote(Double numeroHasLote) {
		this.numeroHasLote = numeroHasLote;
	}

	public boolean getEsTecnificado() {
		return esTecnificado;
	}

	public void setEsTecnificado(boolean esTecnificado) {
		this.esTecnificado = esTecnificado;
	}

	public BigInteger getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public BigInteger getCantonId() {
		return cantonId;
	}

	public void setCantonId(BigInteger cantonId) {
		this.cantonId = cantonId;
	}

	public String getCantonNombre() {
		return cantonNombre;
	}

	public void setCantonNombre(String cantonNombre) {
		this.cantonNombre = cantonNombre;
	}

	public BigInteger getParroquiaId() {
		return parroquiaId;
	}

	public void setParroquiaId(BigInteger parroquiaId) {
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

	public BigInteger getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getCargaFecha() {
		return cargaFecha;
	}

	public void setCargaFecha(Date cargaFecha) {
		this.cargaFecha = cargaFecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}		
	
	public String getClienteApellido() {
		return clienteApellido;
	}

	public void setClienteApellido(String clienteApellido) {
		this.clienteApellido = clienteApellido;
	}	
}
