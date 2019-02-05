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
@Table(name="AGRI_CANAL_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="AgriCanal_Punto_Venta.findAll", query="SELECT a FROM AgriCanal_Punto_Venta a"),
	@NamedQuery(name="AgriCanal_Punto_Venta.obtenerPorId", query="SELECT a FROM AgriCanal_Punto_Venta a where a.PuntoVentaId=:PuntoVentaId")
})
public class AgriCanal_Punto_Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PuntoVentaId")
	private BigInteger PuntoVentaId;
	
	@Column(name = "CanalId")
	private BigInteger CanalId;
	
	@Column(name = "NombrePuntoVenta")
	private String NombrePuntoVenta;

	public AgriCanal_Punto_Venta() {
		// TODO Auto-generated constructor stub
	}
	
	public BigInteger getPuntoVentaId() {
		return this.PuntoVentaId;
	}
	
	public void setId(BigInteger puntoVentaId) {
		this.PuntoVentaId = puntoVentaId;
	}
	
	public BigInteger getCanalId() {
		return this.CanalId;
	}
	
	public void setCanalId(BigInteger canalId) {
		this.CanalId = canalId;
	}
	public String getNombrePuntoVenta() {
		return this.NombrePuntoVenta;
	}
	
	public void setNombrePuntoVenta(String nombrePuntoVenta) {
		this.NombrePuntoVenta = nombrePuntoVenta;
	}
}
