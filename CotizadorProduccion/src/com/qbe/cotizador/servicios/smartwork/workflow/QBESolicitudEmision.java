
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for QBE_SolicitudEmision complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_SolicitudEmision">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Numero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FechaSolicitud" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="MatrizSucursal" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="UnidadComercial" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Solicitante" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="EsClienteNuevo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="entidadId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreAsegurado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApellidoAsegurado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIRUC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoOficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorreoElectronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoEndoso" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TipoPoliza" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Broker" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TieneTasaEspecial" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Tasa" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="TasaAprobadaPor" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="PolizaReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TieneComisionEspecial" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PorcentajeComision" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FormaPago" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="InstitucionBancaria" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TipoCuenta" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="NroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RemisionDocumentos" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="DetalleDocumentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResumenSeguros" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="SlipReaseguros" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TieneEmail" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TieneDebitoBancario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TieneFormularioConCliente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TieneOtrosDocumentos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DetalleOtrosDocumentos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FechaRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="VerificacionDatosCliente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Ramo" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="NumPoliza" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumRamosIncluidos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RenovacionIgualesCondiciones" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ComisionEspecialAlBroker" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ComisionEspecialAprobador" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="FormaPagoDetalle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormaPagoNumCuotas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FormaPagoFechaPrimerPago" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RemisionEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionAtencion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionCiudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionTelefonos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionFechaRecepcion" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RemisionFechaRetorno" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RemisionPersonaRecepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemisionEmpresaRecepcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TieneInspeccion" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ProgramaVariosemisores" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Programatamano" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="DatosActualizados" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NovedadesEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroAplicaciones" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RazonCancelacion" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TipoReaseguro" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="COR" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NombreEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Urgente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NumeroMovimientos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ComisionEspecialNegociada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DetalleExtrasVehiculo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FacturaCompraTransporte" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FechaInicioVigencia" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NumeroRamos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FechaEntregaUrgente" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RazonUrgente" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="FechaRecepcionFisicos" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FacturarMesSiguiente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AutoXAuto" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EndosoBeneficiario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EndosoBeneficiarioNumero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EndosoBeneficiarioA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumContenedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CumplimientoPresupuestario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FechaRecepcionCliente" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FechaSLA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SegurosOriente" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NumeroFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroEndoso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EsSumaAsegurada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="MLDealer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PolizaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroCotizacion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_SolicitudEmision", propOrder = {
    "solicitudID",
    "numero",
    "fechaSolicitud",
    "matrizSucursal",
    "unidadComercial",
    "solicitante",
    "esClienteNuevo",
    "entidadId",
    "nombreAsegurado",
    "apellidoAsegurado",
    "ciruc",
    "direccion",
    "telefonoDomicilio",
    "telefonoCelular",
    "telefonoOficina",
    "correoElectronico",
    "ocupacion",
    "tipoEndoso",
    "tipoPoliza",
    "broker",
    "tieneTasaEspecial",
    "tasa",
    "tasaAprobadaPor",
    "polizaReferencia",
    "tieneComisionEspecial",
    "porcentajeComision",
    "formaPago",
    "institucionBancaria",
    "tipoCuenta",
    "nroCuenta",
    "fechaVencimiento",
    "remisionDocumentos",
    "detalleDocumentos",
    "resumenSeguros",
    "slipReaseguros",
    "tieneEmail",
    "tieneDebitoBancario",
    "tieneFormularioConCliente",
    "tieneOtrosDocumentos",
    "detalleOtrosDocumentos",
    "observaciones",
    "fechaRegistro",
    "verificacionDatosCliente",
    "ramo",
    "numPoliza",
    "numRamosIncluidos",
    "renovacionIgualesCondiciones",
    "comisionEspecialAlBroker",
    "comisionEspecialAprobador",
    "formaPagoDetalle",
    "formaPagoNumCuotas",
    "formaPagoFechaPrimerPago",
    "remisionEmpresa",
    "remisionAtencion",
    "remisionDireccion",
    "remisionCiudad",
    "remisionTelefonos",
    "remisionFechaRecepcion",
    "remisionFechaRetorno",
    "remisionPersonaRecepcion",
    "remisionEmpresaRecepcion",
    "tieneInspeccion",
    "programaVariosemisores",
    "programatamano",
    "datosActualizados",
    "novedadesEntrega",
    "numeroAplicaciones",
    "razonCancelacion",
    "tipoReaseguro",
    "cor",
    "fechaNacimiento",
    "nombreEmpresa",
    "urgente",
    "numeroMovimientos",
    "comisionEspecialNegociada",
    "detalleExtrasVehiculo",
    "facturaCompraTransporte",
    "fechaInicioVigencia",
    "numeroRamos",
    "fechaEntregaUrgente",
    "razonUrgente",
    "fechaRecepcionFisicos",
    "facturarMesSiguiente",
    "autoXAuto",
    "endosoBeneficiario",
    "endosoBeneficiarioNumero",
    "endosoBeneficiarioA",
    "numContenedor",
    "cumplimientoPresupuestario",
    "fechaRecepcionCliente",
    "fechaSLA",
    "segurosOriente",
    "numeroFactura",
    "numeroEndoso",
    "esSumaAsegurada",
    "mlDealer",
    "polizaID",
    "numeroCotizacion"
})
public class QBESolicitudEmision
    extends EntityObject
{

    @XmlElement(name = "SolicitudID", required = true)
    protected String solicitudID;
    @XmlElement(name = "Numero")
    protected int numero;
    @XmlElement(name = "FechaSolicitud", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSolicitud;
    @XmlElement(name = "MatrizSucursal", required = true, nillable = true)
    protected String matrizSucursal;
    @XmlElement(name = "UnidadComercial", required = true, nillable = true)
    protected String unidadComercial;
    @XmlElement(name = "Solicitante", required = true, nillable = true)
    protected String solicitante;
    @XmlElement(name = "EsClienteNuevo", required = true, type = Boolean.class, nillable = true)
    protected Boolean esClienteNuevo;
    protected String entidadId;
    @XmlElement(name = "NombreAsegurado")
    protected String nombreAsegurado;
    @XmlElement(name = "ApellidoAsegurado")
    protected String apellidoAsegurado;
    @XmlElement(name = "CIRUC")
    protected String ciruc;
    @XmlElement(name = "Direccion")
    protected String direccion;
    @XmlElement(name = "TelefonoDomicilio")
    protected String telefonoDomicilio;
    @XmlElement(name = "TelefonoCelular")
    protected String telefonoCelular;
    @XmlElement(name = "TelefonoOficina")
    protected String telefonoOficina;
    @XmlElement(name = "CorreoElectronico")
    protected String correoElectronico;
    @XmlElement(name = "Ocupacion")
    protected String ocupacion;
    @XmlElement(name = "TipoEndoso", required = true, nillable = true)
    protected String tipoEndoso;
    @XmlElement(name = "TipoPoliza", required = true, nillable = true)
    protected String tipoPoliza;
    @XmlElement(name = "Broker", required = true, nillable = true)
    protected String broker;
    @XmlElement(name = "TieneTasaEspecial", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneTasaEspecial;
    @XmlElement(name = "Tasa", required = true, type = Double.class, nillable = true)
    protected Double tasa;
    @XmlElement(name = "TasaAprobadaPor", required = true, nillable = true)
    protected String tasaAprobadaPor;
    @XmlElement(name = "PolizaReferencia")
    protected String polizaReferencia;
    @XmlElement(name = "TieneComisionEspecial", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneComisionEspecial;
    @XmlElement(name = "PorcentajeComision", required = true, type = Double.class, nillable = true)
    protected Double porcentajeComision;
    @XmlElement(name = "FormaPago", required = true, nillable = true)
    protected String formaPago;
    @XmlElement(name = "InstitucionBancaria", required = true, nillable = true)
    protected String institucionBancaria;
    @XmlElement(name = "TipoCuenta", required = true, nillable = true)
    protected String tipoCuenta;
    @XmlElement(name = "NroCuenta")
    protected String nroCuenta;
    @XmlElement(name = "FechaVencimiento", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaVencimiento;
    @XmlElement(name = "RemisionDocumentos", required = true, nillable = true)
    protected String remisionDocumentos;
    @XmlElement(name = "DetalleDocumentos")
    protected String detalleDocumentos;
    @XmlElement(name = "ResumenSeguros", required = true, type = Boolean.class, nillable = true)
    protected Boolean resumenSeguros;
    @XmlElement(name = "SlipReaseguros", required = true, type = Boolean.class, nillable = true)
    protected Boolean slipReaseguros;
    @XmlElement(name = "TieneEmail", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneEmail;
    @XmlElement(name = "TieneDebitoBancario", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneDebitoBancario;
    @XmlElement(name = "TieneFormularioConCliente", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneFormularioConCliente;
    @XmlElement(name = "TieneOtrosDocumentos", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneOtrosDocumentos;
    @XmlElement(name = "DetalleOtrosDocumentos")
    protected String detalleOtrosDocumentos;
    @XmlElement(name = "Observaciones")
    protected String observaciones;
    @XmlElement(name = "FechaRegistro", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRegistro;
    @XmlElement(name = "VerificacionDatosCliente", required = true, type = Boolean.class, nillable = true)
    protected Boolean verificacionDatosCliente;
    @XmlElement(name = "Ramo", required = true, nillable = true)
    protected String ramo;
    @XmlElement(name = "NumPoliza")
    protected String numPoliza;
    @XmlElement(name = "NumRamosIncluidos", required = true, type = Integer.class, nillable = true)
    protected Integer numRamosIncluidos;
    @XmlElement(name = "RenovacionIgualesCondiciones", required = true, type = Boolean.class, nillable = true)
    protected Boolean renovacionIgualesCondiciones;
    @XmlElement(name = "ComisionEspecialAlBroker", required = true, type = Boolean.class, nillable = true)
    protected Boolean comisionEspecialAlBroker;
    @XmlElement(name = "ComisionEspecialAprobador", required = true, nillable = true)
    protected String comisionEspecialAprobador;
    @XmlElement(name = "FormaPagoDetalle")
    protected String formaPagoDetalle;
    @XmlElement(name = "FormaPagoNumCuotas", required = true, type = Integer.class, nillable = true)
    protected Integer formaPagoNumCuotas;
    @XmlElement(name = "FormaPagoFechaPrimerPago", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar formaPagoFechaPrimerPago;
    @XmlElement(name = "RemisionEmpresa")
    protected String remisionEmpresa;
    @XmlElement(name = "RemisionAtencion")
    protected String remisionAtencion;
    @XmlElement(name = "RemisionDireccion")
    protected String remisionDireccion;
    @XmlElement(name = "RemisionCiudad")
    protected String remisionCiudad;
    @XmlElement(name = "RemisionTelefonos")
    protected String remisionTelefonos;
    @XmlElement(name = "RemisionFechaRecepcion", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar remisionFechaRecepcion;
    @XmlElement(name = "RemisionFechaRetorno", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar remisionFechaRetorno;
    @XmlElement(name = "RemisionPersonaRecepcion")
    protected String remisionPersonaRecepcion;
    @XmlElement(name = "RemisionEmpresaRecepcion")
    protected String remisionEmpresaRecepcion;
    @XmlElement(name = "TieneInspeccion", required = true, type = Boolean.class, nillable = true)
    protected Boolean tieneInspeccion;
    @XmlElement(name = "ProgramaVariosemisores", required = true, type = Boolean.class, nillable = true)
    protected Boolean programaVariosemisores;
    @XmlElement(name = "Programatamano", required = true, nillable = true)
    protected String programatamano;
    @XmlElement(name = "DatosActualizados", required = true, type = Boolean.class, nillable = true)
    protected Boolean datosActualizados;
    @XmlElement(name = "NovedadesEntrega")
    protected String novedadesEntrega;
    @XmlElement(name = "NumeroAplicaciones", required = true, type = Integer.class, nillable = true)
    protected Integer numeroAplicaciones;
    @XmlElement(name = "RazonCancelacion", required = true, nillable = true)
    protected String razonCancelacion;
    @XmlElement(name = "TipoReaseguro", required = true, nillable = true)
    protected String tipoReaseguro;
    @XmlElement(name = "COR", required = true, type = Boolean.class, nillable = true)
    protected Boolean cor;
    @XmlElement(name = "FechaNacimiento", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNacimiento;
    @XmlElement(name = "NombreEmpresa")
    protected String nombreEmpresa;
    @XmlElement(name = "Urgente", required = true, type = Boolean.class, nillable = true)
    protected Boolean urgente;
    @XmlElement(name = "NumeroMovimientos", required = true, type = Integer.class, nillable = true)
    protected Integer numeroMovimientos;
    @XmlElement(name = "ComisionEspecialNegociada", required = true, type = Boolean.class, nillable = true)
    protected Boolean comisionEspecialNegociada;
    @XmlElement(name = "DetalleExtrasVehiculo", required = true, type = Boolean.class, nillable = true)
    protected Boolean detalleExtrasVehiculo;
    @XmlElement(name = "FacturaCompraTransporte", required = true, type = Boolean.class, nillable = true)
    protected Boolean facturaCompraTransporte;
    @XmlElement(name = "FechaInicioVigencia", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicioVigencia;
    @XmlElement(name = "NumeroRamos", required = true, type = Integer.class, nillable = true)
    protected Integer numeroRamos;
    @XmlElement(name = "FechaEntregaUrgente", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEntregaUrgente;
    @XmlElement(name = "RazonUrgente", required = true, nillable = true)
    protected String razonUrgente;
    @XmlElement(name = "FechaRecepcionFisicos", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRecepcionFisicos;
    @XmlElement(name = "FacturarMesSiguiente", required = true, type = Boolean.class, nillable = true)
    protected Boolean facturarMesSiguiente;
    @XmlElement(name = "AutoXAuto", required = true, type = Boolean.class, nillable = true)
    protected Boolean autoXAuto;
    @XmlElement(name = "EndosoBeneficiario", required = true, type = Boolean.class, nillable = true)
    protected Boolean endosoBeneficiario;
    @XmlElement(name = "EndosoBeneficiarioNumero", required = true, type = Integer.class, nillable = true)
    protected Integer endosoBeneficiarioNumero;
    @XmlElement(name = "EndosoBeneficiarioA")
    protected String endosoBeneficiarioA;
    @XmlElement(name = "NumContenedor")
    protected String numContenedor;
    @XmlElement(name = "CumplimientoPresupuestario", required = true, type = Boolean.class, nillable = true)
    protected Boolean cumplimientoPresupuestario;
    @XmlElement(name = "FechaRecepcionCliente", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRecepcionCliente;
    @XmlElement(name = "FechaSLA", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSLA;
    @XmlElement(name = "SegurosOriente", required = true, type = Boolean.class, nillable = true)
    protected Boolean segurosOriente;
    @XmlElement(name = "NumeroFactura")
    protected String numeroFactura;
    @XmlElement(name = "NumeroEndoso", required = true, type = Integer.class, nillable = true)
    protected Integer numeroEndoso;
    @XmlElement(name = "EsSumaAsegurada", required = true, type = Boolean.class, nillable = true)
    protected Boolean esSumaAsegurada;
    @XmlElement(name = "MLDealer", required = true, type = Boolean.class, nillable = true)
    protected Boolean mlDealer;
    @XmlElement(name = "PolizaID")
    protected String polizaID;
    @XmlElement(name = "NumeroCotizacion", required = true, type = Integer.class, nillable = true)
    protected Integer numeroCotizacion;

    /**
     * Gets the value of the solicitudID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitudID() {
        return solicitudID;
    }

    /**
     * Sets the value of the solicitudID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitudID(String value) {
        this.solicitudID = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     */
    public void setNumero(int value) {
        this.numero = value;
    }

    /**
     * Gets the value of the fechaSolicitud property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * Sets the value of the fechaSolicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSolicitud(XMLGregorianCalendar value) {
        this.fechaSolicitud = value;
    }

    /**
     * Gets the value of the matrizSucursal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrizSucursal() {
        return matrizSucursal;
    }

    /**
     * Sets the value of the matrizSucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrizSucursal(String value) {
        this.matrizSucursal = value;
    }

    /**
     * Gets the value of the unidadComercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidadComercial() {
        return unidadComercial;
    }

    /**
     * Sets the value of the unidadComercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidadComercial(String value) {
        this.unidadComercial = value;
    }

    /**
     * Gets the value of the solicitante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitante() {
        return solicitante;
    }

    /**
     * Sets the value of the solicitante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitante(String value) {
        this.solicitante = value;
    }

    /**
     * Gets the value of the esClienteNuevo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsClienteNuevo() {
        return esClienteNuevo;
    }

    /**
     * Sets the value of the esClienteNuevo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsClienteNuevo(Boolean value) {
        this.esClienteNuevo = value;
    }

    /**
     * Gets the value of the entidadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadId() {
        return entidadId;
    }

    /**
     * Sets the value of the entidadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadId(String value) {
        this.entidadId = value;
    }

    /**
     * Gets the value of the nombreAsegurado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAsegurado() {
        return nombreAsegurado;
    }

    /**
     * Sets the value of the nombreAsegurado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAsegurado(String value) {
        this.nombreAsegurado = value;
    }

    /**
     * Gets the value of the apellidoAsegurado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoAsegurado() {
        return apellidoAsegurado;
    }

    /**
     * Sets the value of the apellidoAsegurado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoAsegurado(String value) {
        this.apellidoAsegurado = value;
    }

    /**
     * Gets the value of the ciruc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCIRUC() {
        return ciruc;
    }

    /**
     * Sets the value of the ciruc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCIRUC(String value) {
        this.ciruc = value;
    }

    /**
     * Gets the value of the direccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the value of the direccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccion(String value) {
        this.direccion = value;
    }

    /**
     * Gets the value of the telefonoDomicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoDomicilio() {
        return telefonoDomicilio;
    }

    /**
     * Sets the value of the telefonoDomicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoDomicilio(String value) {
        this.telefonoDomicilio = value;
    }

    /**
     * Gets the value of the telefonoCelular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    /**
     * Sets the value of the telefonoCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoCelular(String value) {
        this.telefonoCelular = value;
    }

    /**
     * Gets the value of the telefonoOficina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    /**
     * Sets the value of the telefonoOficina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonoOficina(String value) {
        this.telefonoOficina = value;
    }

    /**
     * Gets the value of the correoElectronico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Sets the value of the correoElectronico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }

    /**
     * Gets the value of the ocupacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * Sets the value of the ocupacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOcupacion(String value) {
        this.ocupacion = value;
    }

    /**
     * Gets the value of the tipoEndoso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoEndoso() {
        return tipoEndoso;
    }

    /**
     * Sets the value of the tipoEndoso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoEndoso(String value) {
        this.tipoEndoso = value;
    }

    /**
     * Gets the value of the tipoPoliza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPoliza() {
        return tipoPoliza;
    }

    /**
     * Sets the value of the tipoPoliza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPoliza(String value) {
        this.tipoPoliza = value;
    }

    /**
     * Gets the value of the broker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroker() {
        return broker;
    }

    /**
     * Sets the value of the broker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroker(String value) {
        this.broker = value;
    }

    /**
     * Gets the value of the tieneTasaEspecial property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneTasaEspecial() {
        return tieneTasaEspecial;
    }

    /**
     * Sets the value of the tieneTasaEspecial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneTasaEspecial(Boolean value) {
        this.tieneTasaEspecial = value;
    }

    /**
     * Gets the value of the tasa property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTasa() {
        return tasa;
    }

    /**
     * Sets the value of the tasa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTasa(Double value) {
        this.tasa = value;
    }

    /**
     * Gets the value of the tasaAprobadaPor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTasaAprobadaPor() {
        return tasaAprobadaPor;
    }

    /**
     * Sets the value of the tasaAprobadaPor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTasaAprobadaPor(String value) {
        this.tasaAprobadaPor = value;
    }

    /**
     * Gets the value of the polizaReferencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolizaReferencia() {
        return polizaReferencia;
    }

    /**
     * Sets the value of the polizaReferencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolizaReferencia(String value) {
        this.polizaReferencia = value;
    }

    /**
     * Gets the value of the tieneComisionEspecial property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneComisionEspecial() {
        return tieneComisionEspecial;
    }

    /**
     * Sets the value of the tieneComisionEspecial property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneComisionEspecial(Boolean value) {
        this.tieneComisionEspecial = value;
    }

    /**
     * Gets the value of the porcentajeComision property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentajeComision() {
        return porcentajeComision;
    }

    /**
     * Sets the value of the porcentajeComision property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentajeComision(Double value) {
        this.porcentajeComision = value;
    }

    /**
     * Gets the value of the formaPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * Sets the value of the formaPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPago(String value) {
        this.formaPago = value;
    }

    /**
     * Gets the value of the institucionBancaria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitucionBancaria() {
        return institucionBancaria;
    }

    /**
     * Sets the value of the institucionBancaria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitucionBancaria(String value) {
        this.institucionBancaria = value;
    }

    /**
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCuenta(String value) {
        this.tipoCuenta = value;
    }

    /**
     * Gets the value of the nroCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the value of the nroCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCuenta(String value) {
        this.nroCuenta = value;
    }

    /**
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaVencimiento(XMLGregorianCalendar value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the remisionDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionDocumentos() {
        return remisionDocumentos;
    }

    /**
     * Sets the value of the remisionDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionDocumentos(String value) {
        this.remisionDocumentos = value;
    }

    /**
     * Gets the value of the detalleDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalleDocumentos() {
        return detalleDocumentos;
    }

    /**
     * Sets the value of the detalleDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalleDocumentos(String value) {
        this.detalleDocumentos = value;
    }

    /**
     * Gets the value of the resumenSeguros property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResumenSeguros() {
        return resumenSeguros;
    }

    /**
     * Sets the value of the resumenSeguros property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResumenSeguros(Boolean value) {
        this.resumenSeguros = value;
    }

    /**
     * Gets the value of the slipReaseguros property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSlipReaseguros() {
        return slipReaseguros;
    }

    /**
     * Sets the value of the slipReaseguros property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSlipReaseguros(Boolean value) {
        this.slipReaseguros = value;
    }

    /**
     * Gets the value of the tieneEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneEmail() {
        return tieneEmail;
    }

    /**
     * Sets the value of the tieneEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneEmail(Boolean value) {
        this.tieneEmail = value;
    }

    /**
     * Gets the value of the tieneDebitoBancario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneDebitoBancario() {
        return tieneDebitoBancario;
    }

    /**
     * Sets the value of the tieneDebitoBancario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneDebitoBancario(Boolean value) {
        this.tieneDebitoBancario = value;
    }

    /**
     * Gets the value of the tieneFormularioConCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneFormularioConCliente() {
        return tieneFormularioConCliente;
    }

    /**
     * Sets the value of the tieneFormularioConCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneFormularioConCliente(Boolean value) {
        this.tieneFormularioConCliente = value;
    }

    /**
     * Gets the value of the tieneOtrosDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneOtrosDocumentos() {
        return tieneOtrosDocumentos;
    }

    /**
     * Sets the value of the tieneOtrosDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneOtrosDocumentos(Boolean value) {
        this.tieneOtrosDocumentos = value;
    }

    /**
     * Gets the value of the detalleOtrosDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalleOtrosDocumentos() {
        return detalleOtrosDocumentos;
    }

    /**
     * Sets the value of the detalleOtrosDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalleOtrosDocumentos(String value) {
        this.detalleOtrosDocumentos = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the fechaRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Sets the value of the fechaRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRegistro(XMLGregorianCalendar value) {
        this.fechaRegistro = value;
    }

    /**
     * Gets the value of the verificacionDatosCliente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVerificacionDatosCliente() {
        return verificacionDatosCliente;
    }

    /**
     * Sets the value of the verificacionDatosCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVerificacionDatosCliente(Boolean value) {
        this.verificacionDatosCliente = value;
    }

    /**
     * Gets the value of the ramo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * Sets the value of the ramo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRamo(String value) {
        this.ramo = value;
    }

    /**
     * Gets the value of the numPoliza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumPoliza() {
        return numPoliza;
    }

    /**
     * Sets the value of the numPoliza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumPoliza(String value) {
        this.numPoliza = value;
    }

    /**
     * Gets the value of the numRamosIncluidos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumRamosIncluidos() {
        return numRamosIncluidos;
    }

    /**
     * Sets the value of the numRamosIncluidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumRamosIncluidos(Integer value) {
        this.numRamosIncluidos = value;
    }

    /**
     * Gets the value of the renovacionIgualesCondiciones property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRenovacionIgualesCondiciones() {
        return renovacionIgualesCondiciones;
    }

    /**
     * Sets the value of the renovacionIgualesCondiciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRenovacionIgualesCondiciones(Boolean value) {
        this.renovacionIgualesCondiciones = value;
    }

    /**
     * Gets the value of the comisionEspecialAlBroker property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComisionEspecialAlBroker() {
        return comisionEspecialAlBroker;
    }

    /**
     * Sets the value of the comisionEspecialAlBroker property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComisionEspecialAlBroker(Boolean value) {
        this.comisionEspecialAlBroker = value;
    }

    /**
     * Gets the value of the comisionEspecialAprobador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComisionEspecialAprobador() {
        return comisionEspecialAprobador;
    }

    /**
     * Sets the value of the comisionEspecialAprobador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComisionEspecialAprobador(String value) {
        this.comisionEspecialAprobador = value;
    }

    /**
     * Gets the value of the formaPagoDetalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaPagoDetalle() {
        return formaPagoDetalle;
    }

    /**
     * Sets the value of the formaPagoDetalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaPagoDetalle(String value) {
        this.formaPagoDetalle = value;
    }

    /**
     * Gets the value of the formaPagoNumCuotas property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFormaPagoNumCuotas() {
        return formaPagoNumCuotas;
    }

    /**
     * Sets the value of the formaPagoNumCuotas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFormaPagoNumCuotas(Integer value) {
        this.formaPagoNumCuotas = value;
    }

    /**
     * Gets the value of the formaPagoFechaPrimerPago property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFormaPagoFechaPrimerPago() {
        return formaPagoFechaPrimerPago;
    }

    /**
     * Sets the value of the formaPagoFechaPrimerPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFormaPagoFechaPrimerPago(XMLGregorianCalendar value) {
        this.formaPagoFechaPrimerPago = value;
    }

    /**
     * Gets the value of the remisionEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionEmpresa() {
        return remisionEmpresa;
    }

    /**
     * Sets the value of the remisionEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionEmpresa(String value) {
        this.remisionEmpresa = value;
    }

    /**
     * Gets the value of the remisionAtencion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionAtencion() {
        return remisionAtencion;
    }

    /**
     * Sets the value of the remisionAtencion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionAtencion(String value) {
        this.remisionAtencion = value;
    }

    /**
     * Gets the value of the remisionDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionDireccion() {
        return remisionDireccion;
    }

    /**
     * Sets the value of the remisionDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionDireccion(String value) {
        this.remisionDireccion = value;
    }

    /**
     * Gets the value of the remisionCiudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionCiudad() {
        return remisionCiudad;
    }

    /**
     * Sets the value of the remisionCiudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionCiudad(String value) {
        this.remisionCiudad = value;
    }

    /**
     * Gets the value of the remisionTelefonos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionTelefonos() {
        return remisionTelefonos;
    }

    /**
     * Sets the value of the remisionTelefonos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionTelefonos(String value) {
        this.remisionTelefonos = value;
    }

    /**
     * Gets the value of the remisionFechaRecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRemisionFechaRecepcion() {
        return remisionFechaRecepcion;
    }

    /**
     * Sets the value of the remisionFechaRecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRemisionFechaRecepcion(XMLGregorianCalendar value) {
        this.remisionFechaRecepcion = value;
    }

    /**
     * Gets the value of the remisionFechaRetorno property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRemisionFechaRetorno() {
        return remisionFechaRetorno;
    }

    /**
     * Sets the value of the remisionFechaRetorno property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRemisionFechaRetorno(XMLGregorianCalendar value) {
        this.remisionFechaRetorno = value;
    }

    /**
     * Gets the value of the remisionPersonaRecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionPersonaRecepcion() {
        return remisionPersonaRecepcion;
    }

    /**
     * Sets the value of the remisionPersonaRecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionPersonaRecepcion(String value) {
        this.remisionPersonaRecepcion = value;
    }

    /**
     * Gets the value of the remisionEmpresaRecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemisionEmpresaRecepcion() {
        return remisionEmpresaRecepcion;
    }

    /**
     * Sets the value of the remisionEmpresaRecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemisionEmpresaRecepcion(String value) {
        this.remisionEmpresaRecepcion = value;
    }

    /**
     * Gets the value of the tieneInspeccion property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneInspeccion() {
        return tieneInspeccion;
    }

    /**
     * Sets the value of the tieneInspeccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneInspeccion(Boolean value) {
        this.tieneInspeccion = value;
    }

    /**
     * Gets the value of the programaVariosemisores property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProgramaVariosemisores() {
        return programaVariosemisores;
    }

    /**
     * Sets the value of the programaVariosemisores property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProgramaVariosemisores(Boolean value) {
        this.programaVariosemisores = value;
    }

    /**
     * Gets the value of the programatamano property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramatamano() {
        return programatamano;
    }

    /**
     * Sets the value of the programatamano property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramatamano(String value) {
        this.programatamano = value;
    }

    /**
     * Gets the value of the datosActualizados property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDatosActualizados() {
        return datosActualizados;
    }

    /**
     * Sets the value of the datosActualizados property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDatosActualizados(Boolean value) {
        this.datosActualizados = value;
    }

    /**
     * Gets the value of the novedadesEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNovedadesEntrega() {
        return novedadesEntrega;
    }

    /**
     * Sets the value of the novedadesEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNovedadesEntrega(String value) {
        this.novedadesEntrega = value;
    }

    /**
     * Gets the value of the numeroAplicaciones property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroAplicaciones() {
        return numeroAplicaciones;
    }

    /**
     * Sets the value of the numeroAplicaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroAplicaciones(Integer value) {
        this.numeroAplicaciones = value;
    }

    /**
     * Gets the value of the razonCancelacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonCancelacion() {
        return razonCancelacion;
    }

    /**
     * Sets the value of the razonCancelacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonCancelacion(String value) {
        this.razonCancelacion = value;
    }

    /**
     * Gets the value of the tipoReaseguro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoReaseguro() {
        return tipoReaseguro;
    }

    /**
     * Sets the value of the tipoReaseguro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoReaseguro(String value) {
        this.tipoReaseguro = value;
    }

    /**
     * Gets the value of the cor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCOR() {
        return cor;
    }

    /**
     * Sets the value of the cor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCOR(Boolean value) {
        this.cor = value;
    }

    /**
     * Gets the value of the fechaNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Sets the value of the fechaNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaNacimiento(XMLGregorianCalendar value) {
        this.fechaNacimiento = value;
    }

    /**
     * Gets the value of the nombreEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the value of the nombreEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmpresa(String value) {
        this.nombreEmpresa = value;
    }

    /**
     * Gets the value of the urgente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUrgente() {
        return urgente;
    }

    /**
     * Sets the value of the urgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUrgente(Boolean value) {
        this.urgente = value;
    }

    /**
     * Gets the value of the numeroMovimientos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroMovimientos() {
        return numeroMovimientos;
    }

    /**
     * Sets the value of the numeroMovimientos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroMovimientos(Integer value) {
        this.numeroMovimientos = value;
    }

    /**
     * Gets the value of the comisionEspecialNegociada property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComisionEspecialNegociada() {
        return comisionEspecialNegociada;
    }

    /**
     * Sets the value of the comisionEspecialNegociada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComisionEspecialNegociada(Boolean value) {
        this.comisionEspecialNegociada = value;
    }

    /**
     * Gets the value of the detalleExtrasVehiculo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDetalleExtrasVehiculo() {
        return detalleExtrasVehiculo;
    }

    /**
     * Sets the value of the detalleExtrasVehiculo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDetalleExtrasVehiculo(Boolean value) {
        this.detalleExtrasVehiculo = value;
    }

    /**
     * Gets the value of the facturaCompraTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFacturaCompraTransporte() {
        return facturaCompraTransporte;
    }

    /**
     * Sets the value of the facturaCompraTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFacturaCompraTransporte(Boolean value) {
        this.facturaCompraTransporte = value;
    }

    /**
     * Gets the value of the fechaInicioVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    /**
     * Sets the value of the fechaInicioVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicioVigencia(XMLGregorianCalendar value) {
        this.fechaInicioVigencia = value;
    }

    /**
     * Gets the value of the numeroRamos property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroRamos() {
        return numeroRamos;
    }

    /**
     * Sets the value of the numeroRamos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroRamos(Integer value) {
        this.numeroRamos = value;
    }

    /**
     * Gets the value of the fechaEntregaUrgente property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaEntregaUrgente() {
        return fechaEntregaUrgente;
    }

    /**
     * Sets the value of the fechaEntregaUrgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaEntregaUrgente(XMLGregorianCalendar value) {
        this.fechaEntregaUrgente = value;
    }

    /**
     * Gets the value of the razonUrgente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonUrgente() {
        return razonUrgente;
    }

    /**
     * Sets the value of the razonUrgente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonUrgente(String value) {
        this.razonUrgente = value;
    }

    /**
     * Gets the value of the fechaRecepcionFisicos property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionFisicos() {
        return fechaRecepcionFisicos;
    }

    /**
     * Sets the value of the fechaRecepcionFisicos property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionFisicos(XMLGregorianCalendar value) {
        this.fechaRecepcionFisicos = value;
    }

    /**
     * Gets the value of the facturarMesSiguiente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFacturarMesSiguiente() {
        return facturarMesSiguiente;
    }

    /**
     * Sets the value of the facturarMesSiguiente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFacturarMesSiguiente(Boolean value) {
        this.facturarMesSiguiente = value;
    }

    /**
     * Gets the value of the autoXAuto property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoXAuto() {
        return autoXAuto;
    }

    /**
     * Sets the value of the autoXAuto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoXAuto(Boolean value) {
        this.autoXAuto = value;
    }

    /**
     * Gets the value of the endosoBeneficiario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEndosoBeneficiario() {
        return endosoBeneficiario;
    }

    /**
     * Sets the value of the endosoBeneficiario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEndosoBeneficiario(Boolean value) {
        this.endosoBeneficiario = value;
    }

    /**
     * Gets the value of the endosoBeneficiarioNumero property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEndosoBeneficiarioNumero() {
        return endosoBeneficiarioNumero;
    }

    /**
     * Sets the value of the endosoBeneficiarioNumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEndosoBeneficiarioNumero(Integer value) {
        this.endosoBeneficiarioNumero = value;
    }

    /**
     * Gets the value of the endosoBeneficiarioA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndosoBeneficiarioA() {
        return endosoBeneficiarioA;
    }

    /**
     * Sets the value of the endosoBeneficiarioA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndosoBeneficiarioA(String value) {
        this.endosoBeneficiarioA = value;
    }

    /**
     * Gets the value of the numContenedor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumContenedor() {
        return numContenedor;
    }

    /**
     * Sets the value of the numContenedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumContenedor(String value) {
        this.numContenedor = value;
    }

    /**
     * Gets the value of the cumplimientoPresupuestario property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCumplimientoPresupuestario() {
        return cumplimientoPresupuestario;
    }

    /**
     * Sets the value of the cumplimientoPresupuestario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCumplimientoPresupuestario(Boolean value) {
        this.cumplimientoPresupuestario = value;
    }

    /**
     * Gets the value of the fechaRecepcionCliente property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRecepcionCliente() {
        return fechaRecepcionCliente;
    }

    /**
     * Sets the value of the fechaRecepcionCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRecepcionCliente(XMLGregorianCalendar value) {
        this.fechaRecepcionCliente = value;
    }

    /**
     * Gets the value of the fechaSLA property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSLA() {
        return fechaSLA;
    }

    /**
     * Sets the value of the fechaSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSLA(XMLGregorianCalendar value) {
        this.fechaSLA = value;
    }

    /**
     * Gets the value of the segurosOriente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSegurosOriente() {
        return segurosOriente;
    }

    /**
     * Sets the value of the segurosOriente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSegurosOriente(Boolean value) {
        this.segurosOriente = value;
    }

    /**
     * Gets the value of the numeroFactura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Sets the value of the numeroFactura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFactura(String value) {
        this.numeroFactura = value;
    }

    /**
     * Gets the value of the numeroEndoso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroEndoso() {
        return numeroEndoso;
    }

    /**
     * Sets the value of the numeroEndoso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroEndoso(Integer value) {
        this.numeroEndoso = value;
    }

    /**
     * Gets the value of the esSumaAsegurada property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsSumaAsegurada() {
        return esSumaAsegurada;
    }

    /**
     * Sets the value of the esSumaAsegurada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsSumaAsegurada(Boolean value) {
        this.esSumaAsegurada = value;
    }

    /**
     * Gets the value of the mlDealer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMLDealer() {
        return mlDealer;
    }

    /**
     * Sets the value of the mlDealer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMLDealer(Boolean value) {
        this.mlDealer = value;
    }

    /**
     * Gets the value of the polizaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolizaID() {
        return polizaID;
    }

    /**
     * Sets the value of the polizaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolizaID(String value) {
        this.polizaID = value;
    }

    /**
     * Gets the value of the numeroCotizacion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroCotizacion() {
        return numeroCotizacion;
    }

    /**
     * Sets the value of the numeroCotizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroCotizacion(Integer value) {
        this.numeroCotizacion = value;
    }

}
