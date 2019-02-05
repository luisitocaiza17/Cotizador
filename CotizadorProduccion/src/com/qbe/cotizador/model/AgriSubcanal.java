package com.qbe.cotizador.model;


import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the AGRI_SUBCANAL database table.
 * 
 */
@Entity
@Table(name="AGRI_SUBCANAL")
@NamedQueries({
	@NamedQuery(name="AgriSubcanal.findAll", query="SELECT a FROM AgriSubcanal a"),
	@NamedQuery(name="AgriSubcanal.buscarPorId", query="SELECT a FROM AgriSubcanal a where a.id = :id"),		
	@NamedQuery(name="AgriSubcanal.findAllCanal", query="SELECT a FROM AgriSubcanal a where a.agriCanalId = :CanalId and a.agriParametroXPuntoVenta =:agriParametroXPuntoVenta and a.codIntegracion =:CodIntegracion")		
	
})

public class AgriSubcanal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="AGRI_CANAL_ID")
	private String agriCanalId;

	@Column(name="AGRI_PARAMETRO_X_PUNTO_VENTA")
	private String agriParametroXPuntoVenta;

	@Column(name="COD_INTEGRACION")
	private String codIntegracion;

	@Column(name="PUNTO_VENTA_SUBCANAL")
	private String puntoVentaSubCanal;
	
	
	public AgriSubcanal() {
	}
	
	public String getPuntoVentaSubCanal() {
		return puntoVentaSubCanal;
	}



	public void setPuntoVentaSubCanal(String puntoVentaSubCanal) {
		this.puntoVentaSubCanal = puntoVentaSubCanal;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgriCanalId() {
		return this.agriCanalId;
	}

	public void setAgriCanalId(String agriCanalId) {
		this.agriCanalId = agriCanalId;
	}

	public String getAgriParametroXPuntoVenta() {
		return this.agriParametroXPuntoVenta;
	}

	public void setAgriParametroXPuntoVenta(String agriParametroXPuntoVenta) {
		this.agriParametroXPuntoVenta = agriParametroXPuntoVenta;
	}

	public String getCodIntegracion() {
		return this.codIntegracion;
	}

	public void setCodIntegracion(String codIntegracion) {
		this.codIntegracion = codIntegracion;
	}

}