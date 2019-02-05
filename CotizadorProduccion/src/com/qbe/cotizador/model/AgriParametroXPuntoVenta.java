package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the AGRI_PARAMETRO_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="AGRI_PARAMETRO_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="AgriParametroXPuntoVenta.obtenerTodos", query="SELECT a FROM AgriParametroXPuntoVenta a"),
	@NamedQuery(name="AgriParametroXPuntoVenta.obtenerPorId", query="SELECT a FROM AgriParametroXPuntoVenta a where a.puntoVentaId=:parametroPuntoVentaId"),
	@NamedQuery(name="AgriParametroXPuntoVenta.obtenerPorPuntoVentaId", query="SELECT a FROM AgriParametroXPuntoVenta a where a.puntoVentaId=:puntoVentaId"),
	@NamedQuery(name="AgriParametroXPuntoVenta.obtenerCodigoIntegracion", query="SELECT a FROM AgriParametroXPuntoVenta a where a.codigoIntegracion=:codigoIntegracion"),
	@NamedQuery(name="AgriParametroXPuntoVenta.obtenerCanal", query="SELECT a FROM AgriParametroXPuntoVenta a where a.canalId=:canalId")
	})
public class AgriParametroXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PUNTO_VENTA_ID")
	private BigInteger puntoVentaId;

	@Column(name="EMISION_DIRECTA")
	private boolean emisionDirecta;
	
	@Column(name="EMISION_OBLIGATORIA")
	private boolean emisionObligatoria;

	@Column(name="CANAL_ID")
	private BigInteger canalId;

	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;

	@Column(name="CODIGO_INTEGRACION")
	private String codigoIntegracion;
	
	@Column(name="EMAIL_PUNTO_VENTA")
	private String EmailPuntoVenta;
	
	//Forma notificacion 1 ) NINGUNA , 2-POR WEB SERVICE SUCRE, 3-POR EMAIL
	@Column(name="FORMA_NOTIFICACION")
	private int FormaNotificacion;
	
	@Column(name="SUCURSAL_EMISION_ID")
	private int sucursalEmisionId;
	
	@Column(name="BENEFICIARIO_ID")
	private String BeneficiarioId;
	
	@Column(name="EXCEPCIONES_DIRECTAS_CULTIVOS")
	private String excepcionesDirectasCultivos;
	
	@Column(name="AGENTE_ID")
	private String agenteId;
	
	@Column(name="PORCENTAJE_COMISION")
	private String porcentajeComision;
	
	public String getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(String agenteId) {
		this.agenteId = agenteId;
	}

	public String getPorcentajeComision() {
		return porcentajeComision;
	}

	public void setPorcentajeComision(String porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	public String getExcepcionesDirectasCultivos() {
		return excepcionesDirectasCultivos;
	}

	public void setExcepcionesDirectasCultivos(String excepcionesDirectasCultivos) {
		this.excepcionesDirectasCultivos = excepcionesDirectasCultivos;
	}

	public AgriParametroXPuntoVenta() {
	}

	public boolean getEmisionDirecta() {
		return this.emisionDirecta;
	}

	public void setEmisionDirecta(boolean emisionDirecta) {
		this.emisionDirecta = emisionDirecta;
	}

	public BigInteger getPuntoVentaId() {
		return this.puntoVentaId;
	}

	public void setPuntoVentaId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}

	public BigInteger getTipoCalculoId() {
		return this.tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public String getCodigoIntegracion() {
		return codigoIntegracion;
	}

	public void setCodigoIntegracion(String codigoIntegracion) {
		this.codigoIntegracion = codigoIntegracion;
	}
	public String getEmailPuntoVenta() {
		return EmailPuntoVenta;
	}

	public void setEmailPuntoVenta(String emailPuntoVenta) {
		this.EmailPuntoVenta = emailPuntoVenta;
	}
	
	public int getFormaNotificacion() {
		return FormaNotificacion;
	}

	public void setFormaNotificacion(int formaNotificacion) {
		this.FormaNotificacion = formaNotificacion;
	}

	public boolean isEmisionObligatoria() {
		return emisionObligatoria;
	}

	public void setEmisionObligatoria(boolean emisionObligatoria) {
		this.emisionObligatoria = emisionObligatoria;
	}

	public BigInteger getCanalId() {
		return canalId;
	}

	public void setCanalId(BigInteger canalId) {
		this.canalId = canalId;
	}

	public int getSucursalEmisionId() {
		return sucursalEmisionId;
	}

	public void setSucursalEmisionId(int sucursalEmisionId) {
		this.sucursalEmisionId = sucursalEmisionId;
	}
	
	public String getBeneficiarioId() {
		return BeneficiarioId;
	}

	public void setBeneficiarioId(String BeneficiarioId) {
		this.BeneficiarioId = BeneficiarioId;
	}
}