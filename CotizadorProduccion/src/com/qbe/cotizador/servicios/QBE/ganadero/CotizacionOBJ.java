package com.qbe.cotizador.servicios.QBE.ganadero;

import java.math.BigDecimal;
import java.util.Date;

public class CotizacionOBJ 
{
	private String NumeroCotizacionOrigen;
	
	private String agenteId;
	
	private Date fechaElaboracion;
	
	private BigDecimal sumaAsegurada;
	
	private String UsuarioCreacion;
	
	private SolicitanteOBJ solicitanteOBJ;
	
	private BeneficiarioOBJ beneficiarioOBJ;
	
	private AseguradoOBJ aseguradoOBJ;
	
	private FichaGanaderoOBJ fichaGanaderoOBJ;

	public String getNumeroCotizacionOrigen() {
		return NumeroCotizacionOrigen;
	}

	public void setNumeroCotizacionOrigen(String numeroCotizacionOrigen) {
		NumeroCotizacionOrigen = numeroCotizacionOrigen;
	}

	public String getAgenteId() {
		return agenteId;
	}

	public void setAgenteId(String agenteId) {
		this.agenteId = agenteId;
	}

	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public BigDecimal getSumaAsegurada() {
		return sumaAsegurada;
	}

	public void setSumaAsegurada(BigDecimal sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}

	public String getUsuarioCreacion() {
		return UsuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		UsuarioCreacion = usuarioCreacion;
	}

	public SolicitanteOBJ getSolicitanteOBJ() {
		return solicitanteOBJ;
	}

	public void setSolicitanteOBJ(SolicitanteOBJ solicitanteOBJ) {
		this.solicitanteOBJ = solicitanteOBJ;
	}

	public BeneficiarioOBJ getBeneficiarioOBJ() {
		return beneficiarioOBJ;
	}

	public void setBeneficiarioOBJ(BeneficiarioOBJ beneficiarioOBJ) {
		this.beneficiarioOBJ = beneficiarioOBJ;
	}

	public AseguradoOBJ getAseguradoOBJ() {
		return aseguradoOBJ;
	}

	public void setAseguradoOBJ(AseguradoOBJ aseguradoOBJ) {
		this.aseguradoOBJ = aseguradoOBJ;
	}

	public FichaGanaderoOBJ getFichaGanaderoOBJ() {
		return fichaGanaderoOBJ;
	}

	public void setFichaGanaderoOBJ(FichaGanaderoOBJ fichaGanaderoOBJ) {
		this.fichaGanaderoOBJ = fichaGanaderoOBJ;
	}
	
	
	
}
