package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="PYME_CONFIGURACION_LOG")
@NamedQueries({
	@NamedQuery(name="PymeConfiguracionLog.buscarPorId", query="SELECT c FROM PymeConfiguracionLog c where c.configuracionLogId = :id"),
	@NamedQuery(name="PymeConfiguracionLog.buscarTodos", query="SELECT s FROM PymeConfiguracionLog s")
})
public class PymeConfiguracionLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONFIGURACION_LOG_ID")
	private BigInteger configuracionLogId; 
	
	@Column(name="CONFIGURACION_ORIGINAL_ID")
	private BigInteger configuracionOriginalId; 
	
	@Column(name="GRUPO_POR_PRODUCTO_ID")
	private BigInteger grupoPorProductoId;
	
	@Column(name="COBERTURA_PYMES_ID")
	private BigInteger coberturaPyesID;
	
	@Column(name="CAMPO")
	private String campo;
	
	@Column(name="VALOR_ANTERIOR")
	private String valorAnterior;
	
	@Lob
	@Column(name="VALOR_NUEVO")
	private String valorNuevo;
	
	@Column(name="USUARIO")
	private String usuario;
	
	@Column(name="FECHA_CAMBIO")
	private Timestamp fechaCambio;

	public BigInteger getConfiguracionLogId() {
		return configuracionLogId;
	}

	public void setConfiguracionLogId(BigInteger configuracionLogId) {
		this.configuracionLogId = configuracionLogId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Timestamp getFechaCambio() {
		return fechaCambio;
	}

	public void setFechaCambio(Timestamp fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public BigInteger getConfiguracionOriginalId() {
		return configuracionOriginalId;
	}

	public void setConfiguracionOriginalId(BigInteger configuracionOriginalId) {
		this.configuracionOriginalId = configuracionOriginalId;
	}

	public BigInteger getGrupoPorProductoId() {
		return grupoPorProductoId;
	}

	public void setGrupoPorProductoId(BigInteger grupoPorProductoId) {
		this.grupoPorProductoId = grupoPorProductoId;
	}

	public BigInteger getCoberturaPyesID() {
		return coberturaPyesID;
	}

	public void setCoberturaPyesID(BigInteger coberturaPyesID) {
		this.coberturaPyesID = coberturaPyesID;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}
}
