package com.qbe.cotizador.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "AGRI_AGENCIA")
@NamedQueries({
		@NamedQuery(name = "AgriAgencia.buscarTodos", query = "SELECT a FROM AgriAgencia a"),
		@NamedQuery(name="AgriAgencia.buscarId", query="SELECT p FROM AgriAgencia p where p.Agencia_id=:Agencia_id")
})
public class AgriAgencia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger Agencia_id;
	
	@Column(name = "PUNTO_VENTA_ID")
	private BigInteger puntoVentaId;
	
	@Column(name = "NOMBRE")
	private String AgenciaNombre;
	
	@Column(name = "CODIGO")
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public AgriAgencia() {
		// TODO Auto-generated constructor stub
	}
	public BigInteger getId() {
		return this.Agencia_id;
	}
	
	public void setId(BigInteger id) {
		this.Agencia_id = id;
	}
	public BigInteger getpuntoVentaId() {
		return this.puntoVentaId;
	}
	
	public void setpuntoVentaIdId(BigInteger puntoVentaId) {
		this.puntoVentaId = puntoVentaId;
	}
	public String getAgenciaNombre() {
		return this.AgenciaNombre;
	}
	
	public void setAgenciaNombre(String agenciaNombre) {
		AgenciaNombre = agenciaNombre;
	}
}
