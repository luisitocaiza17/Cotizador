package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;


/**
 * The persistent class for the PYME_PARAMETRO_X_PUNTO_VENTA database table.
 * 
 */
@Entity
@Table(name="PYME_PARAMETRO_X_PUNTO_VENTA")
@NamedQueries({
	@NamedQuery(name="PymeParametroXPuntoVenta.buscarTodos", query="SELECT p FROM PymeParametroXPuntoVenta p"),
	@NamedQuery(name="PymeParametroXPuntoVenta.buscarPorId", query="SELECT c FROM PymeParametroXPuntoVenta c WHERE c.parametroPuntoVentaId=:Id"),
	@NamedQuery(name="PymeParametroXPuntoVenta.buscarPorAgenteId", query="SELECT c FROM PymeParametroXPuntoVenta c WHERE c.agenteId=:agenteId")
})
public class PymeParametroXPuntoVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PARAMETRO_PUNTO_VENTA_ID")
	private BigInteger parametroPuntoVentaId;

	@Column(name="TIENE_MULTIRIESGO")
	private Boolean tieneMultiriesgo;

	@Column(name="AGENTE_ID")
	private BigInteger agenteId;

	public PymeParametroXPuntoVenta() {
	}

	public BigInteger getParametroPuntoVentaId() {
		return this.parametroPuntoVentaId;
	}

	public void setParametroPuntoVentaId(BigInteger parametroPuntoVentaId) {
		this.parametroPuntoVentaId = parametroPuntoVentaId;
	}

	public BigInteger getPuntoVentaId() {
		return this.agenteId;
	}

	public void setPuntoVentaId(BigInteger agenteId) {
		this.agenteId = agenteId;
	}

	public Boolean getTieneMultiriesgo() {
		return tieneMultiriesgo;
	}

	public void setTieneMultiriesgo(Boolean tieneMultiriesgo) {
		this.tieneMultiriesgo = tieneMultiriesgo;
	}
}