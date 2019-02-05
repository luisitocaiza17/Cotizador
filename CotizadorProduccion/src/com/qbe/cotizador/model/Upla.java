package com.qbe.cotizador.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the UPLA database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Upla.buscarPorId", query="SELECT c FROM Upla c where c.id = :id"),
	@NamedQuery(name="Upla.buscarTodos", query="SELECT c FROM Upla c"),
	@NamedQuery(name="Upla.buscarPorCliente", query="SELECT c FROM Upla c where c.cliente = :cliente")
})
public class Upla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private double activos;

	@Column(name="apellido_materno_conyuge")
	private String apellidoMaternoConyuge;

	@Column(name="apellido_paterno_conyuge")
	private String apellidoPaternoConyuge;

	@Column(name="apellidos_representante_legal")
	private String apellidosRepresentanteLegal;

	@Column(name="cargo_desempena_natural")
	private String cargoDesempenaNatural;

	@Column(name="cargo_familiar_expuesto")
	private String cargoFamiliarExpuesto;

	@Column(name="cargo_ocupa_natural")
	private String cargoOcupaNatural;

	@Column(name="celular_asegurado")
	private String celularAsegurado;

	@Column(name="celular_beneficiario")
	private String celularBeneficiario;

	@Column(name="celular_natural")
	private String celularNatural;

	@Column(name="celular_representante_legal")
	private String celularRepresentanteLegal;

	@Column(name="ciudad_id_representante_legal")
	private BigInteger ciudadIdRepresentanteLegal;

	@Column(name="ciudad_pais_juridica")
	private String ciudadPaisJuridica;

	@Column(name="direccion_asegurado")
	private String direccionAsegurado;

	@Column(name="direccion_beneficiario")
	private String direccionBeneficiario;

	@Column(name="direccion_representante_legal")
	private String direccionRepresentanteLegal;

	private double egresos;

	@Column(name="email_natural")
	private String emailNatural;

	@Column(name="es_asegurado")
	private boolean esAsegurado;

	@Column(name="es_beneficiario")
	private boolean esBeneficiario;

	@Column(name="es_solicitante")
	private boolean esSolicitante;

	@Column(name="expuesta_politicamente_natural")
	private boolean expuestaPoliticamenteNatural;

	@Column(name="familiar_expuesto_politicamente")
	private boolean familiarExpuestoPoliticamente;

	@Column(name="fax_empresa")
	private String faxEmpresa;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento_natural")
	private Date fechaNacimientoNatural;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento_representante_legal")
	private Date fechaNacimientoRepresentanteLegal;

	@Column(name="genero_natural")
	private String generoNatural;

	@Column(name="identificacion_asegurado")
	private String identificacionAsegurado;

	@Column(name="identificacion_beneficiario")
	private String identificacionBeneficiario;

	@Column(name="identificacion_conyuge")
	private String identificacionConyuge;

	@Column(name="identificacion_representante_legal")
	private String identificacionRepresentanteLegal;

	@Column(name="ingresos_egresos")
	private double ingresosEgresos;

	@Column(name="lugar_nacimiento_natural")
	private String lugarNacimientoNatural;

	@Column(name="lugar_nacimiento_representante_legal")
	private String lugarNacimientoRepresentanteLegal;

	@Column(name="nombre_beneficiario")
	private String nombreBeneficiario;

	@Column(name="nombre_completo_asegurado")
	private String nombreCompletoAsegurado;

	@Column(name="nombre_conyuge")
	private String nombreConyuge;

	@Column(name="nombres_representante_legal")
	private String nombresRepresentanteLegal;

	@Column(name="objeto_social_juridica")
	private String objetoSocialJuridica;

	@Column(name="otros_ingresos")
	private double otrosIngresos;

	@Column(name="parentesco_familiar_expuesto")
	private String parentescoFamiliarExpuesto;

	private double pasivos;

	private double patrimonio;

	@Column(name="provincia_id_representante_legal")
	private BigInteger provinciaIdRepresentanteLegal;

	@Column(name="relacion_asegurado")
	private String relacionAsegurado;

	@Column(name="relacion_beneficiario")
	private String relacionBeneficiario;

	@Column(name="salario_mensual")
	private double salarioMensual;

	@Column(name="sucursal_ciudad_juridica")
	private String sucursalCiudadJuridica;

	@Column(name="sucursal_direccion_juridica")
	private String sucursalDireccionJuridica;

	@Column(name="telefono_asegurado")
	private String telefonoAsegurado;

	@Column(name="telefono_beneficiario")
	private String telefonoBeneficiario;

	@Column(name="telefono_empresa")
	private String telefonoEmpresa;

	@Column(name="telefono_natural")
	private String telefonoNatural;

	@Column(name="telefono_representante_legal")
	private String telefonoRepresentanteLegal;

	@Column(name="tipo_actividad_natural")
	private String tipoActividadNatural;

	@Column(name="tipo_cliente")
	private String tipoCliente;

	@Column(name="tipo_identificacion_id_asegurado")
	private BigInteger tipoIdentificacionIdAsegurado;

	@Column(name="tipo_identificacion_id_beneficiario")
	private BigInteger tipoIdentificacionIdBeneficiario;

	@Column(name="tipo_identificacion_id_conyuge")
	private BigInteger tipoIdentificacionIdConyuge;

	@Column(name="tipo_identificacion_id_representante_legal")
	private BigInteger tipoIdentificacionIdRepresentanteLegal;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	//bi-directional many-to-one association to Direccion
	@ManyToOne
	private Direccion direccion;

	//bi-directional many-to-one association to Ramo
	@ManyToOne
	private Ramo ramo;

	public Upla() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getActivos() {
		return this.activos;
	}

	public void setActivos(double activos) {
		this.activos = activos;
	}

	public String getApellidoMaternoConyuge() {
		return this.apellidoMaternoConyuge;
	}

	public void setApellidoMaternoConyuge(String apellidoMaternoConyuge) {
		this.apellidoMaternoConyuge = apellidoMaternoConyuge;
	}

	public String getApellidoPaternoConyuge() {
		return this.apellidoPaternoConyuge;
	}

	public void setApellidoPaternoConyuge(String apellidoPaternoConyuge) {
		this.apellidoPaternoConyuge = apellidoPaternoConyuge;
	}

	public String getApellidosRepresentanteLegal() {
		return this.apellidosRepresentanteLegal;
	}

	public void setApellidosRepresentanteLegal(String apellidosRepresentanteLegal) {
		this.apellidosRepresentanteLegal = apellidosRepresentanteLegal;
	}

	public String getCargoDesempenaNatural() {
		return this.cargoDesempenaNatural;
	}

	public void setCargoDesempenaNatural(String cargoDesempenaNatural) {
		this.cargoDesempenaNatural = cargoDesempenaNatural;
	}

	public String getCargoFamiliarExpuesto() {
		return this.cargoFamiliarExpuesto;
	}

	public void setCargoFamiliarExpuesto(String cargoFamiliarExpuesto) {
		this.cargoFamiliarExpuesto = cargoFamiliarExpuesto;
	}

	public String getCargoOcupaNatural() {
		return this.cargoOcupaNatural;
	}

	public void setCargoOcupaNatural(String cargoOcupaNatural) {
		this.cargoOcupaNatural = cargoOcupaNatural;
	}

	public String getCelularAsegurado() {
		return this.celularAsegurado;
	}

	public void setCelularAsegurado(String celularAsegurado) {
		this.celularAsegurado = celularAsegurado;
	}

	public String getCelularBeneficiario() {
		return this.celularBeneficiario;
	}

	public void setCelularBeneficiario(String celularBeneficiario) {
		this.celularBeneficiario = celularBeneficiario;
	}

	public String getCelularNatural() {
		return this.celularNatural;
	}

	public void setCelularNatural(String celularNatural) {
		this.celularNatural = celularNatural;
	}

	public String getCelularRepresentanteLegal() {
		return this.celularRepresentanteLegal;
	}

	public void setCelularRepresentanteLegal(String celularRepresentanteLegal) {
		this.celularRepresentanteLegal = celularRepresentanteLegal;
	}

	public BigInteger getCiudadIdRepresentanteLegal() {
		return this.ciudadIdRepresentanteLegal;
	}

	public void setCiudadIdRepresentanteLegal(BigInteger ciudadIdRepresentanteLegal) {
		this.ciudadIdRepresentanteLegal = ciudadIdRepresentanteLegal;
	}

	public String getCiudadPaisJuridica() {
		return this.ciudadPaisJuridica;
	}

	public void setCiudadPaisJuridica(String ciudadPaisJuridica) {
		this.ciudadPaisJuridica = ciudadPaisJuridica;
	}

	public String getDireccionAsegurado() {
		return this.direccionAsegurado;
	}

	public void setDireccionAsegurado(String direccionAsegurado) {
		this.direccionAsegurado = direccionAsegurado;
	}

	public String getDireccionBeneficiario() {
		return this.direccionBeneficiario;
	}

	public void setDireccionBeneficiario(String direccionBeneficiario) {
		this.direccionBeneficiario = direccionBeneficiario;
	}

	public String getDireccionRepresentanteLegal() {
		return this.direccionRepresentanteLegal;
	}

	public void setDireccionRepresentanteLegal(String direccionRepresentanteLegal) {
		this.direccionRepresentanteLegal = direccionRepresentanteLegal;
	}

	public double getEgresos() {
		return this.egresos;
	}

	public void setEgresos(double egresos) {
		this.egresos = egresos;
	}

	public String getEmailNatural() {
		return this.emailNatural;
	}

	public void setEmailNatural(String emailNatural) {
		this.emailNatural = emailNatural;
	}

	public boolean getEsAsegurado() {
		return this.esAsegurado;
	}

	public void setEsAsegurado(boolean esAsegurado) {
		this.esAsegurado = esAsegurado;
	}

	public boolean getEsBeneficiario() {
		return this.esBeneficiario;
	}

	public void setEsBeneficiario(boolean esBeneficiario) {
		this.esBeneficiario = esBeneficiario;
	}

	public boolean getEsSolicitante() {
		return this.esSolicitante;
	}

	public void setEsSolicitante(boolean esSolicitante) {
		this.esSolicitante = esSolicitante;
	}

	public boolean getExpuestaPoliticamenteNatural() {
		return this.expuestaPoliticamenteNatural;
	}

	public void setExpuestaPoliticamenteNatural(boolean expuestaPoliticamenteNatural) {
		this.expuestaPoliticamenteNatural = expuestaPoliticamenteNatural;
	}

	public boolean getFamiliarExpuestoPoliticamente() {
		return this.familiarExpuestoPoliticamente;
	}

	public void setFamiliarExpuestoPoliticamente(boolean familiarExpuestoPoliticamente) {
		this.familiarExpuestoPoliticamente = familiarExpuestoPoliticamente;
	}

	public String getFaxEmpresa() {
		return this.faxEmpresa;
	}

	public void setFaxEmpresa(String faxEmpresa) {
		this.faxEmpresa = faxEmpresa;
	}

	public Date getFechaNacimientoNatural() {
		return this.fechaNacimientoNatural;
	}

	public void setFechaNacimientoNatural(Date fechaNacimientoNatural) {
		this.fechaNacimientoNatural = fechaNacimientoNatural;
	}

	public Date getFechaNacimientoRepresentanteLegal() {
		return this.fechaNacimientoRepresentanteLegal;
	}

	public void setFechaNacimientoRepresentanteLegal(Date fechaNacimientoRepresentanteLegal) {
		this.fechaNacimientoRepresentanteLegal = fechaNacimientoRepresentanteLegal;
	}

	public String getGeneroNatural() {
		return this.generoNatural;
	}

	public void setGeneroNatural(String generoNatural) {
		this.generoNatural = generoNatural;
	}

	public String getIdentificacionAsegurado() {
		return this.identificacionAsegurado;
	}

	public void setIdentificacionAsegurado(String identificacionAsegurado) {
		this.identificacionAsegurado = identificacionAsegurado;
	}

	public String getIdentificacionBeneficiario() {
		return this.identificacionBeneficiario;
	}

	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}

	public String getIdentificacionConyuge() {
		return this.identificacionConyuge;
	}

	public void setIdentificacionConyuge(String identificacionConyuge) {
		this.identificacionConyuge = identificacionConyuge;
	}

	public String getIdentificacionRepresentanteLegal() {
		return this.identificacionRepresentanteLegal;
	}

	public void setIdentificacionRepresentanteLegal(String identificacionRepresentanteLegal) {
		this.identificacionRepresentanteLegal = identificacionRepresentanteLegal;
	}

	public double getIngresosEgresos() {
		return this.ingresosEgresos;
	}

	public void setIngresosEgresos(double ingresosEgresos) {
		this.ingresosEgresos = ingresosEgresos;
	}

	public String getLugarNacimientoNatural() {
		return this.lugarNacimientoNatural;
	}

	public void setLugarNacimientoNatural(String lugarNacimientoNatural) {
		this.lugarNacimientoNatural = lugarNacimientoNatural;
	}

	public String getLugarNacimientoRepresentanteLegal() {
		return this.lugarNacimientoRepresentanteLegal;
	}

	public void setLugarNacimientoRepresentanteLegal(String lugarNacimientoRepresentanteLegal) {
		this.lugarNacimientoRepresentanteLegal = lugarNacimientoRepresentanteLegal;
	}

	public String getNombreBeneficiario() {
		return this.nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getNombreCompletoAsegurado() {
		return this.nombreCompletoAsegurado;
	}

	public void setNombreCompletoAsegurado(String nombreCompletoAsegurado) {
		this.nombreCompletoAsegurado = nombreCompletoAsegurado;
	}

	public String getNombreConyuge() {
		return this.nombreConyuge;
	}

	public void setNombreConyuge(String nombreConyuge) {
		this.nombreConyuge = nombreConyuge;
	}

	public String getNombresRepresentanteLegal() {
		return this.nombresRepresentanteLegal;
	}

	public void setNombresRepresentanteLegal(String nombresRepresentanteLegal) {
		this.nombresRepresentanteLegal = nombresRepresentanteLegal;
	}

	public String getObjetoSocialJuridica() {
		return this.objetoSocialJuridica;
	}

	public void setObjetoSocialJuridica(String objetoSocialJuridica) {
		this.objetoSocialJuridica = objetoSocialJuridica;
	}

	public double getOtrosIngresos() {
		return this.otrosIngresos;
	}

	public void setOtrosIngresos(double otrosIngresos) {
		this.otrosIngresos = otrosIngresos;
	}

	public String getParentescoFamiliarExpuesto() {
		return this.parentescoFamiliarExpuesto;
	}

	public void setParentescoFamiliarExpuesto(String parentescoFamiliarExpuesto) {
		this.parentescoFamiliarExpuesto = parentescoFamiliarExpuesto;
	}

	public double getPasivos() {
		return this.pasivos;
	}

	public void setPasivos(double pasivos) {
		this.pasivos = pasivos;
	}

	public double getPatrimonio() {
		return this.patrimonio;
	}

	public void setPatrimonio(double patrimonio) {
		this.patrimonio = patrimonio;
	}

	public BigInteger getProvinciaIdRepresentanteLegal() {
		return this.provinciaIdRepresentanteLegal;
	}

	public void setProvinciaIdRepresentanteLegal(BigInteger provinciaIdRepresentanteLegal) {
		this.provinciaIdRepresentanteLegal = provinciaIdRepresentanteLegal;
	}

	public String getRelacionAsegurado() {
		return this.relacionAsegurado;
	}

	public void setRelacionAsegurado(String relacionAsegurado) {
		this.relacionAsegurado = relacionAsegurado;
	}

	public String getRelacionBeneficiario() {
		return this.relacionBeneficiario;
	}

	public void setRelacionBeneficiario(String relacionBeneficiario) {
		this.relacionBeneficiario = relacionBeneficiario;
	}

	public double getSalarioMensual() {
		return this.salarioMensual;
	}

	public void setSalarioMensual(double salarioMensual) {
		this.salarioMensual = salarioMensual;
	}

	public String getSucursalCiudadJuridica() {
		return this.sucursalCiudadJuridica;
	}

	public void setSucursalCiudadJuridica(String sucursalCiudadJuridica) {
		this.sucursalCiudadJuridica = sucursalCiudadJuridica;
	}

	public String getSucursalDireccionJuridica() {
		return this.sucursalDireccionJuridica;
	}

	public void setSucursalDireccionJuridica(String sucursalDireccionJuridica) {
		this.sucursalDireccionJuridica = sucursalDireccionJuridica;
	}

	public String getTelefonoAsegurado() {
		return this.telefonoAsegurado;
	}

	public void setTelefonoAsegurado(String telefonoAsegurado) {
		this.telefonoAsegurado = telefonoAsegurado;
	}

	public String getTelefonoBeneficiario() {
		return this.telefonoBeneficiario;
	}

	public void setTelefonoBeneficiario(String telefonoBeneficiario) {
		this.telefonoBeneficiario = telefonoBeneficiario;
	}

	public String getTelefonoEmpresa() {
		return this.telefonoEmpresa;
	}

	public void setTelefonoEmpresa(String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public String getTelefonoNatural() {
		return this.telefonoNatural;
	}

	public void setTelefonoNatural(String telefonoNatural) {
		this.telefonoNatural = telefonoNatural;
	}

	public String getTelefonoRepresentanteLegal() {
		return this.telefonoRepresentanteLegal;
	}

	public void setTelefonoRepresentanteLegal(String telefonoRepresentanteLegal) {
		this.telefonoRepresentanteLegal = telefonoRepresentanteLegal;
	}

	public String getTipoActividadNatural() {
		return this.tipoActividadNatural;
	}

	public void setTipoActividadNatural(String tipoActividadNatural) {
		this.tipoActividadNatural = tipoActividadNatural;
	}

	public String getTipoCliente() {
		return this.tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public BigInteger getTipoIdentificacionIdAsegurado() {
		return this.tipoIdentificacionIdAsegurado;
	}

	public void setTipoIdentificacionIdAsegurado(BigInteger tipoIdentificacionIdAsegurado) {
		this.tipoIdentificacionIdAsegurado = tipoIdentificacionIdAsegurado;
	}

	public BigInteger getTipoIdentificacionIdBeneficiario() {
		return this.tipoIdentificacionIdBeneficiario;
	}

	public void setTipoIdentificacionIdBeneficiario(BigInteger tipoIdentificacionIdBeneficiario) {
		this.tipoIdentificacionIdBeneficiario = tipoIdentificacionIdBeneficiario;
	}

	public BigInteger getTipoIdentificacionIdConyuge() {
		return this.tipoIdentificacionIdConyuge;
	}

	public void setTipoIdentificacionIdConyuge(BigInteger tipoIdentificacionIdConyuge) {
		this.tipoIdentificacionIdConyuge = tipoIdentificacionIdConyuge;
	}

	public BigInteger getTipoIdentificacionIdRepresentanteLegal() {
		return this.tipoIdentificacionIdRepresentanteLegal;
	}

	public void setTipoIdentificacionIdRepresentanteLegal(BigInteger tipoIdentificacionIdRepresentanteLegal) {
		this.tipoIdentificacionIdRepresentanteLegal = tipoIdentificacionIdRepresentanteLegal;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Ramo getRamo() {
		return this.ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}