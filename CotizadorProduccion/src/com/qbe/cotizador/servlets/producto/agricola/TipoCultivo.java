package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

public class TipoCultivo {

	private BigInteger tipoCultivoId;

	private String nombre;

	private int tipo;
	
	private int vigenciaDias;
	
	private String PrecioAjuste;
	
	private String PrecioAjuste2;
	
	private String UnidadMedida;
	
	private String UnidadMedida2;
	
	
	
//	private String NombrePlantilla;

	public String getPrecioAjuste2() {
		return PrecioAjuste2;
	}

	public void setPrecioAjuste2(String precioAjuste2) {
		PrecioAjuste2 = precioAjuste2;
	}

	public String getUnidadMedida() {
		return UnidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		UnidadMedida = unidadMedida;
	}

	public String getUnidadMedida2() {
		return UnidadMedida2;
	}

	public void setUnidadMedida2(String unidadMedida2) {
		UnidadMedida2 = unidadMedida2;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVigenciaDias() {
		return vigenciaDias;
	}

	public void setVigenciaDias(int vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getPrecioAjuste() {
		return PrecioAjuste;
	}

	public void setPrecioAjuste(String precioAjuste) {
		this.PrecioAjuste = precioAjuste;
	}
	
//	public String getNombrePlantilla() {
//		return NombrePlantilla;
//	}
//
//	public void setNombrePlantilla(String NombrePlantilla) {
//		this.NombrePlantilla = NombrePlantilla;
//	}
}
