package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_ASISTENCIA database table.
 * 
 */
@Entity
@Table(name="PYME_INSPECTOR_PROVINCIA")
@NamedQueries({
	@NamedQuery(name="PymeInspectorProvincia.buscarTodos", query="SELECT c FROM PymeInspectorProvincia c"),
	@NamedQuery(name="PymeInspectorProvincia.buscarPorId", query="SELECT c FROM PymeInspectorProvincia c where c.inspectorProvinciaId = :inspectorProvinciaId"),
	@NamedQuery(name="PymeInspectorProvincia.buscarPorUsuarioId", query="SELECT c FROM PymeInspectorProvincia c where c.usuarioId=:usuarioId and c.provinciaId=:provinciaId and c.ciudadId=:ciudadId"),
	@NamedQuery(name="PymeInspectorProvincia.buscarPorUsuarioId2", query="SELECT c FROM PymeInspectorProvincia c where c.usuarioId=:usuarioId order by c.provinciaId, c.ciudadId"),
})
public class PymeInspectorProvincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INSPECTOR_PROVINCIA_ID")
	private BigInteger inspectorProvinciaId;

	@Column(name="USUARIO_ID")
	private BigInteger usuarioId;

	@Column(name="PROVINCIA_ID")
	private BigInteger provinciaId;
	
	@Column(name="CIUDAD_ID")
	private BigInteger ciudadId;

	public BigInteger getInspectorProvinciaId() {
		return inspectorProvinciaId;
	}

	public void setInspectorProvinciaId(BigInteger inspectorProvinciaId) {
		this.inspectorProvinciaId = inspectorProvinciaId;
	}

	public BigInteger getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	public BigInteger getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(BigInteger provinciaId) {
		this.provinciaId = provinciaId;
	}

	public BigInteger getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(BigInteger ciudadId) {
		this.ciudadId = ciudadId;
	}
}