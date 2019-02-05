/**
 * 
 */
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
@Table(name="AGRI_AUDITORIA_COTIZACION")
@NamedQueries({
	@NamedQuery(name="AgriAuditoriaCotizacion.findAll", query="SELECT a FROM AgriAuditoriaCotizacion a"),
	@NamedQuery(name="AgriAuditoriaCotizacion.buscarPorCotizacionId", query="SELECT a FROM AgriAuditoriaCotizacion a where a.CotizacionId = :CotizacionId and a.TipoActividad='APROBADO REVISION'"),
	@NamedQuery(name="AgriAuditoriaCotizacion.buscarPorId", query="SELECT c FROM AgriAuditoriaCotizacion c where c.Id = :Id")		
	})
	
public class AgriAuditoriaCotizacion implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger Id;

	private BigInteger CotizacionId;
	
	private BigInteger UsuarioId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha")
	private Date Fecha;

	private String TipoActividad;
	
	public AgriAuditoriaCotizacion() {
		// TODO Auto-generated constructor stub
	}
	
	public BigInteger getId() {
		return this.Id;
	}

	public void setId(BigInteger id) {
		this.Id = id;
	}
	
	public BigInteger getCotizacionId() {
		return this.Id;
	}

	public void setCotizacionId(BigInteger cotizacionId) {
		this.CotizacionId = cotizacionId;
	}
	
	public BigInteger getUsuarioId() {
		return this.Id;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.UsuarioId = usuarioId;
	}
	
	public Date getFecha() {
		return this.Fecha;
	}

	public void setFecha(Date fecha) {
		this.Fecha = fecha;
	}
	
	public String getTipoActividad() {
		return this.TipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.TipoActividad = tipoActividad;
	}
}
