package com.qbe.cotizador.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the AGRI_SUCRE_AUDITORIA database table.
 * 
 */
@Entity
@Table(name="AGRI_SUCRE_AUDITORIA")
@NamedQuery(name="AgriSucreAuditoria.findAll", query="SELECT a FROM AgriSucreAuditoria a")
public class AgriSucreAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String objeto;

	private String tramite;
	
	private String canal;
	
	private String estado;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public AgriSucreAuditoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObjeto() {
		return this.objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getTramite() {
		return this.tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

}