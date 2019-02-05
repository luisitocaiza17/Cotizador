package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the PYME_AUDITORIA_GENERAL database table.
 * 
 */


@Entity
@Table(name="PYME_AUDITORIA_GENERAL")
@NamedQueries({
	@NamedQuery(name="PymeAuditoriaGeneral.buscarPorId", query="SELECT c FROM PymeAuditoriaGeneral c where c.id = :id"),
	@NamedQuery(name="PymeAuditoriaGeneral.buscarTodos", query="SELECT p FROM PymeAuditoriaGeneral p")
})

public class PymeAuditoriaGeneral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String estado;

	private Timestamp fecha;

	private String objeto;
	
	private String tramite;
	
	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	private String proceso;

	public PymeAuditoriaGeneral() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getObjeto() {
		return this.objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

}