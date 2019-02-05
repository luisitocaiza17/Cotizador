
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for QBE_Entidad complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_Entidad">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="EntidadID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIRUC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoDomicilio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoCelular" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TelefonoOficina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorreoElectronico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoEntidad" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="EnsuranceEntityID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EsDatosVerificados" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FechaNacimiento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EsNuevo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EsDatosActualizados" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TipoDireccion" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="TipoDocumento" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="EsDireccionCobro" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="PaisID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProvinciaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CiudadID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CantonID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Genero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DireccionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NacionalidadID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NombreRepresentante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApellidoRepresentante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorreoElectronicoOpcional" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActividadEconomica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_Entidad", propOrder = {
    "entidadID",
    "solicitudID",
    "nombre",
    "apellido",
    "ciruc",
    "direccion",
    "telefonoDomicilio",
    "telefonoCelular",
    "telefonoOficina",
    "correoElectronico",
    "ocupacion",
    "tipoEntidad",
    "ensuranceEntityID",
    "empresa",
    "esDatosVerificados",
    "fechaNacimiento",
    "esNuevo",
    "esDatosActualizados",
    "tipoDireccion",
    "tipoDocumento",
    "esDireccionCobro",
    "paisID",
    "provinciaID",
    "ciudadID",
    "cantonID",
    "genero",
    "direccionID",
    "tipoPersona",
    "nacionalidadID",
    "nacionalidad",
    "nombreRepresentante",
    "apellidoRepresentante",
    "correoElectronicoOpcional",
    "actividadEconomica"
})
public class QBEEntidad
    extends EntityObject
{

    @XmlElement(name = "EntidadID", required = true)
    protected String entidadID;
    @XmlElement(name = "SolicitudID", required = true)
    protected String solicitudID;
    @XmlElement(name = "Nombre")
    protected String nombre;
    @XmlElement(name = "Apellido")
    protected String apellido;
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
    @XmlElement(name = "TipoEntidad", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short tipoEntidad;
    @XmlElement(name = "EnsuranceEntityID")
    protected String ensuranceEntityID;
    @XmlElement(name = "Empresa")
    protected String empresa;
    @XmlElement(name = "EsDatosVerificados", required = true, type = Boolean.class, nillable = true)
    protected Boolean esDatosVerificados;
    @XmlElement(name = "FechaNacimiento", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNacimiento;
    @XmlElement(name = "EsNuevo", required = true, type = Boolean.class, nillable = true)
    protected Boolean esNuevo;
    @XmlElement(name = "EsDatosActualizados", required = true, type = Boolean.class, nillable = true)
    protected Boolean esDatosActualizados;
    @XmlElement(name = "TipoDireccion", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short tipoDireccion;
    @XmlElement(name = "TipoDocumento", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short tipoDocumento;
    @XmlElement(name = "EsDireccionCobro", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short esDireccionCobro;
    @XmlElement(name = "PaisID")
    protected String paisID;
    @XmlElement(name = "ProvinciaID")
    protected String provinciaID;
    @XmlElement(name = "CiudadID")
    protected String ciudadID;
    @XmlElement(name = "CantonID")
    protected String cantonID;
    @XmlElement(name = "Genero")
    protected String genero;
    @XmlElement(name = "DireccionID")
    protected String direccionID;
    @XmlElement(name = "TipoPersona")
    protected String tipoPersona;
    @XmlElement(name = "NacionalidadID")
    protected String nacionalidadID;
    @XmlElement(name = "Nacionalidad")
    protected String nacionalidad;
    @XmlElement(name = "NombreRepresentante")
    protected String nombreRepresentante;
    @XmlElement(name = "ApellidoRepresentante")
    protected String apellidoRepresentante;
    @XmlElement(name = "CorreoElectronicoOpcional")
    protected String correoElectronicoOpcional;
    @XmlElement(name = "ActividadEconomica")
    protected String actividadEconomica;

    /**
     * Gets the value of the entidadID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntidadID() {
        return entidadID;
    }

    /**
     * Sets the value of the entidadID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntidadID(String value) {
        this.entidadID = value;
    }

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
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the apellido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the value of the apellido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
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
     * Gets the value of the tipoEntidad property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTipoEntidad() {
        return tipoEntidad;
    }

    /**
     * Sets the value of the tipoEntidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTipoEntidad(Short value) {
        this.tipoEntidad = value;
    }

    /**
     * Gets the value of the ensuranceEntityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnsuranceEntityID() {
        return ensuranceEntityID;
    }

    /**
     * Sets the value of the ensuranceEntityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnsuranceEntityID(String value) {
        this.ensuranceEntityID = value;
    }

    /**
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the esDatosVerificados property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsDatosVerificados() {
        return esDatosVerificados;
    }

    /**
     * Sets the value of the esDatosVerificados property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsDatosVerificados(Boolean value) {
        this.esDatosVerificados = value;
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
     * Gets the value of the esNuevo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsNuevo() {
        return esNuevo;
    }

    /**
     * Sets the value of the esNuevo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsNuevo(Boolean value) {
        this.esNuevo = value;
    }

    /**
     * Gets the value of the esDatosActualizados property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsDatosActualizados() {
        return esDatosActualizados;
    }

    /**
     * Sets the value of the esDatosActualizados property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsDatosActualizados(Boolean value) {
        this.esDatosActualizados = value;
    }

    /**
     * Gets the value of the tipoDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTipoDireccion() {
        return tipoDireccion;
    }

    /**
     * Sets the value of the tipoDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTipoDireccion(Short value) {
        this.tipoDireccion = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTipoDocumento(Short value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the esDireccionCobro property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getEsDireccionCobro() {
        return esDireccionCobro;
    }

    /**
     * Sets the value of the esDireccionCobro property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setEsDireccionCobro(Short value) {
        this.esDireccionCobro = value;
    }

    /**
     * Gets the value of the paisID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaisID() {
        return paisID;
    }

    /**
     * Sets the value of the paisID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaisID(String value) {
        this.paisID = value;
    }

    /**
     * Gets the value of the provinciaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaID() {
        return provinciaID;
    }

    /**
     * Sets the value of the provinciaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaID(String value) {
        this.provinciaID = value;
    }

    /**
     * Gets the value of the ciudadID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadID() {
        return ciudadID;
    }

    /**
     * Sets the value of the ciudadID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadID(String value) {
        this.ciudadID = value;
    }

    /**
     * Gets the value of the cantonID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantonID() {
        return cantonID;
    }

    /**
     * Sets the value of the cantonID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantonID(String value) {
        this.cantonID = value;
    }

    /**
     * Gets the value of the genero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Sets the value of the genero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    /**
     * Gets the value of the direccionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionID() {
        return direccionID;
    }

    /**
     * Sets the value of the direccionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionID(String value) {
        this.direccionID = value;
    }

    /**
     * Gets the value of the tipoPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the value of the tipoPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPersona(String value) {
        this.tipoPersona = value;
    }

    /**
     * Gets the value of the nacionalidadID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidadID() {
        return nacionalidadID;
    }

    /**
     * Sets the value of the nacionalidadID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidadID(String value) {
        this.nacionalidadID = value;
    }

    /**
     * Gets the value of the nacionalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the value of the nacionalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the nombreRepresentante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    /**
     * Sets the value of the nombreRepresentante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRepresentante(String value) {
        this.nombreRepresentante = value;
    }

    /**
     * Gets the value of the apellidoRepresentante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoRepresentante() {
        return apellidoRepresentante;
    }

    /**
     * Sets the value of the apellidoRepresentante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoRepresentante(String value) {
        this.apellidoRepresentante = value;
    }

    /**
     * Gets the value of the correoElectronicoOpcional property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreoElectronicoOpcional() {
        return correoElectronicoOpcional;
    }

    /**
     * Sets the value of the correoElectronicoOpcional property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreoElectronicoOpcional(String value) {
        this.correoElectronicoOpcional = value;
    }

    /**
     * Gets the value of the actividadEconomica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     * Sets the value of the actividadEconomica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadEconomica(String value) {
        this.actividadEconomica = value;
    }

}
