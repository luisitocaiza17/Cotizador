package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the PAGO database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Pago.buscarPorId", query="SELECT c FROM Pago c where c.id = :id"),
	@NamedQuery(name="Pago.buscarTodos", query="SELECT c FROM Pago c")
})
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="anio_expiracion_tarjeta")
	private String anioExpiracionTarjeta;

	@Column(name="identificacion_titular")
	private String identificacionTitular;

	@Column(name="mes_expiracion_tarjeta")
	private String mesExpiracionTarjeta;

	@Column(name="nombre_titular")
	private String nombreTitular;

	@Column(name="numero_cuenta_tarjeta")
	private String numeroCuentaTarjeta;

	@Column(name="plazon_en_mes")
	private String plazonEnMes;

	@Column(name="tipo_cuenta")
	private String tipoCuenta;

	@Column(name="valor_total")
	private float valorTotal;
	
	@Column(name="cuota_inicial")
	private float cuotaInicial;
	
	//bi-directional many-to-one association to FormaPago
	@ManyToOne
	@JoinColumn(name="forma_pago_id")
	private FormaPago formaPago;

	//bi-directional many-to-one association to InstitucionFinanciera
	@ManyToOne
	@JoinColumn(name="institucion_financiera_id")
	private InstitucionFinanciera institucionFinanciera;

	//bi-directional many-to-one association to TipoIdentificacion
	@ManyToOne
	@JoinColumn(name="tipo_identificacion_id")
	private TipoIdentificacion tipoIdentificacion;
	
	public Pago() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnioExpiracionTarjeta() {
		return this.anioExpiracionTarjeta;
	}

	public void setAnioExpiracionTarjeta(String anioExpiracionTarjeta) {
		this.anioExpiracionTarjeta = anioExpiracionTarjeta;
	}

	public String getIdentificacionTitular() {
		return this.identificacionTitular;
	}

	public void setIdentificacionTitular(String identificacionTitular) {
		this.identificacionTitular = identificacionTitular;
	}

	public String getMesExpiracionTarjeta() {
		return this.mesExpiracionTarjeta;
	}

	public void setMesExpiracionTarjeta(String mesExpiracionTarjeta) {
		this.mesExpiracionTarjeta = mesExpiracionTarjeta;
	}

	public String getNombreTitular() {
		return this.nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getNumeroCuentaTarjeta() {
		return this.numeroCuentaTarjeta;
	}

	public void setNumeroCuentaTarjeta(String numeroCuentaTarjeta) {
		this.numeroCuentaTarjeta = numeroCuentaTarjeta;
	}

	public String getPlazonEnMes() {
		return this.plazonEnMes;
	}

	public void setPlazonEnMes(String plazonEnMes) {
		this.plazonEnMes = plazonEnMes;
	}

	public String getTipoCuenta() {
		return this.tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public float getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public FormaPago getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public InstitucionFinanciera getInstitucionFinanciera() {
		return this.institucionFinanciera;
	}

	public void setInstitucionFinanciera(InstitucionFinanciera institucionFinanciera) {
		this.institucionFinanciera = institucionFinanciera;
	}
	
	public TipoIdentificacion getTipoIdentificacionId() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacionId(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public double getCuotaInicial() {
		return this.cuotaInicial;
	}

	public void setCuotaInicial(float cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}
}