package com.qbe.cotizador.servlets.producto.agricola;

public class ParametroGeneral {

	private String textoPlantilla;
	
	private int diasValidacionCultivo;
	
	private String MailFactuacionElectronica;

	public String getTextoPlantilla() {
		return textoPlantilla;
	}

	public void setTextoPlantilla(String textoPlantilla) {
		this.textoPlantilla = textoPlantilla;
	}

	public int getDiasValidacionCultivo() {
		return diasValidacionCultivo;
	}

	public void setDiasValidacionCultivo(int diasValidacionCultivo) {
		this.diasValidacionCultivo = diasValidacionCultivo;
	}
	public String getMailFactuacionElectronica() {
		return MailFactuacionElectronica;
	}

	public void setMailFactuacionElectronica(String MailFactuacionElectronica) {
		this.MailFactuacionElectronica = MailFactuacionElectronica;
	}
	
}
