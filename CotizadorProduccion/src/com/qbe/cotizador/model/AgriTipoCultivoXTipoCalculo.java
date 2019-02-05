package com.qbe.cotizador.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;


/**
 * The persistent class for the AGRI_REGLA database table.
 * 
 */
@Entity
@Table(name="AGRI_TIPO_CULTIVO_X_TIPO_CALCULO")
@NamedQueries({
	@NamedQuery(name="AgriTipoCultivoXTipoCalculo.findAll", query="SELECT a FROM AgriTipoCultivoXTipoCalculo a"),
	@NamedQuery(name="AgriTipoCultivoXTipoCalculo.obtenerPorId", query="SELECT a FROM AgriTipoCultivoXTipoCalculo a where a.tipoCultivoTipoCalculoId=:tipoCultivoTipoCalculoId"),
	})
public class AgriTipoCultivoXTipoCalculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TIPO_CULTIVO_TIPO_CALCULO_ID")
	private BigInteger tipoCultivoTipoCalculoId;

	@Column(name="TIPO_CALCULO_ID")
	private BigInteger tipoCalculoId;

	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;

	@Lob
	@Column(name="COBERTURA_TEXTO")
	private byte[] coberturaText;

	@Lob
	@Column(name="DEDUCIBLE_TEXTO")
	private byte[] deducibleTexto;
	
	@Lob
	@Column(name="METODO_INDEMNIZACION_TEXTO")
	private byte[] metodoIndemnizacionTexto;

	public BigInteger getTipoCultivoTipoCalculoId() {
		return tipoCultivoTipoCalculoId;
	}

	public void setTipoCultivoTipoCalculoId(BigInteger tipoCultivoTipoCalculoId) {
		this.tipoCultivoTipoCalculoId = tipoCultivoTipoCalculoId;
	}

	public BigInteger getTipoCalculoId() {
		return tipoCalculoId;
	}

	public void setTipoCalculoId(BigInteger tipoCalculoId) {
		this.tipoCalculoId = tipoCalculoId;
	}

	public BigInteger getTipoCultivoId() {
		return tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public byte[] getCoberturaText() {
		return coberturaText;
	}

	public void setCoberturaText(byte[] coberturaText) {
		this.coberturaText = coberturaText;
	}

	public byte[] getDeducibleTexto() {
		return deducibleTexto;
	}

	public void setDeducibleTexto(byte[] deducibleTexto) {
		this.deducibleTexto = deducibleTexto;
	}

	public byte[] getMetodoIndemnizacionTexto() {
		return metodoIndemnizacionTexto;
	}

	public void setMetodoIndemnizacionTexto(byte[] metodoIndemnizacionTexto) {
		this.metodoIndemnizacionTexto = metodoIndemnizacionTexto;
	}	
}