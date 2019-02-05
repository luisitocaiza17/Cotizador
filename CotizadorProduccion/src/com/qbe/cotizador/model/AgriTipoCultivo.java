package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;



/**
 * The persistent class for the AGRI_TIPO_CULTIVO database table.
 * 
 */
@Entity
@Table(name="AGRI_TIPO_CULTIVO")
@NamedQueries({
		@NamedQuery(name="AgriTipoCultivo.findAll", query="SELECT a FROM AgriTipoCultivo a ORDER BY a.nombre ASC"),
		@NamedQuery(name="AgriTipoCultivo.buscarPorId", query="SELECT c FROM AgriTipoCultivo c where c.tipoCultivoId = :tipoCultivoId"),
		@NamedQuery(name="AgriTipoCultivo.buscarPorNombre", query="SELECT c FROM AgriTipoCultivo c where c.nombre = :tipoCultivoNombre"),
		@NamedQuery(name="AgriTipoCultivo.buscarPorCodIntegracionSucre", query="SELECT c FROM AgriTipoCultivo c where c.codIntSucre = :codigo"),
		@NamedQuery(name="AgriTipoCultivo.buscarPorCodIntegracion", query="SELECT c FROM AgriTipoCultivo c where c.codIntegracion = :codigo"),
		@NamedQuery(name="AgriTipoCultivo.buscarTodosCodIntegracion", query="SELECT c FROM AgriTipoCultivo c where c.codIntSucre = :codigo"),
		@NamedQuery(name="AgriTipoCultivo.buscarTodosCodInt", query="SELECT c FROM AgriTipoCultivo c where c.codIntegracion = :codigo"),
		@NamedQuery(name="AgriTipoCultivo.buscarPorCoincidencias", query="SELECT c FROM AgriTipoCultivo c where c.nombre like :codigo"),
		@NamedQuery(name="AgriTipoCultivo.buscarTodosCodIntOtros", query="SELECT c FROM AgriTipoCultivo c where c.codIntOtros = :codigo"),
		
})
	
public class AgriTipoCultivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TIPO_CULTIVO_ID")
	private BigInteger tipoCultivoId;

	private String nombre;
	
	private int tipo;

	@Column(name="VIGENCIA_DIAS")
	private int vigenciaDias;
	
	@Column(name="COD_ENSURANCE")
	private String codEnsurance;
	
	@Column(name="COD_INTEGRACION")
	private String codIntegracion;	

	@Column(name="COD_INT_SUCRE")
	private String codIntSucre;
	
	@Column(name="COD_INT_OTROS")
	private String codIntOtros;
	
	@Column(name="PRECIO_AJUSTE")
	private String precioAjuste;
	
	@Column(name="UNIDAD_MEDIDA")
	private String unidadMedida;	
	
	@Column(name="PRECIO_AJUSTE2")
	private String precioAjuste2;
	
	@Column(name="UNIDAD_MEDIDA2")
	private String unidadMedida2;
	
	@Column(name="TASA")
	private double tasa;
	
	
	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getPrecioAjuste() {
		return precioAjuste;
	}

	public void setPrecioAjuste(String precioAjuste) {
		this.precioAjuste = precioAjuste;
	}

	public String getCodIntOtros() {
		return codIntOtros;
	}

	public void setCodIntOtros(String codIntOtros) {
		this.codIntOtros = codIntOtros;
	}

	public String getCodIntSucre() {
		return codIntSucre;
	}

	public void setCodIntSucre(String codIntSucre) {
		this.codIntSucre = codIntSucre;
	}

	public String getCodIntegracion() {
		return codIntegracion;
	}

	public void setCodIntegracion(String codIntegracion) {
		this.codIntegracion = codIntegracion;
	}

	public AgriTipoCultivo() {
	}

	public BigInteger getTipoCultivoId() {
		return this.tipoCultivoId;
	}

	public void setTipoCultivoId(BigInteger tipoCultivoId) {
		this.tipoCultivoId = tipoCultivoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipo() {
		return this.tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getVigenciaDias() {
		return this.vigenciaDias;
	}

	public void setVigenciaDias(int vigenciaDias) {
		this.vigenciaDias = vigenciaDias;
	}

	public String getCodEnsurance() {
		return codEnsurance;
	}

	public void setCodEnsurance(String codEnsurance) {
		this.codEnsurance = codEnsurance;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getPrecioAjuste2() {
		return precioAjuste2;
	}

	public void setPrecioAjuste2(String precioAjuste2) {
		this.precioAjuste2 = precioAjuste2;
	}

	public String getUnidadMedida2() {
		return unidadMedida2;
	}

	public void setUnidadMedida2(String unidadMedida2) {
		this.unidadMedida2 = unidadMedida2;
	}
	
	
}
	