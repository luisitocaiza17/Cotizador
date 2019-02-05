package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

public class ParametrosXPuntoVenta {
	
	private BigInteger TipoCalculoId;
	
	private BigInteger PuntoVentaID;
	
	private Boolean EmisionDirecta;

	public BigInteger getTipoCalculoId() {
		return TipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		TipoCalculoId = tipoCalculoId;
	}

	public BigInteger getPuntoVentaID() {
		return PuntoVentaID;
	}

	public void setPuntoVentaID(BigInteger puntoVentaID) {
		PuntoVentaID = puntoVentaID;
	}

	public Boolean getEmisionDirecta() {
		return EmisionDirecta;
	}

	public void setEmisionDirecta(Boolean emisionDirecta) {
		EmisionDirecta = emisionDirecta;
	}
}
