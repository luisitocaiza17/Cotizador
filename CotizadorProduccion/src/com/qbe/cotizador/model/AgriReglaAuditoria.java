package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="AGRI_REGLA_AUDITORIA")
@NamedQueries({
	@NamedQuery(name="AgriReglaAuditoria.buscarTodos", query="SELECT a FROM AgriReglaAuditoria a")
	})
public class AgriReglaAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="AGRI_REGLA_AUDITORIA_ID")
	private BigInteger agriReglaAuditoriaId;
	
	@Column(name="REGLA_ID")
	private BigInteger reglaId;
	
	@Column(name="USUARIO_ID")
	private BigInteger usuarioId;

	public BigInteger getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;	
	
	@Column(name="ACCION")
	private String accion;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public BigInteger getAgriReglaAuditoriaId() {
		return agriReglaAuditoriaId;
	}

	public void setAgriReglaAuditoriaId(BigInteger agriReglaAuditoriaId) {
		this.agriReglaAuditoriaId = agriReglaAuditoriaId;
	}

	public BigInteger getReglaId() {
		return reglaId;
	}

	public void setReglaId(BigInteger reglaId) {
		this.reglaId = reglaId;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

		

}