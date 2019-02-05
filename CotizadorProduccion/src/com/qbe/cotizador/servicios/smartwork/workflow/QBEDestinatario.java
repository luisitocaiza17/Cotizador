
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for QBE_Destinatario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_Destinatario">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="EntregaID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AtencionA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Direccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Telefonos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Tipo" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="EFecharecepcion" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EPersonaEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EFechaRetornoGuia" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ENovedades" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sector" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="EFechaRecepcionCourier" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_Destinatario", propOrder = {
    "entregaID",
    "solicitudID",
    "empresa",
    "atencionA",
    "direccion",
    "ciudad",
    "telefonos",
    "observaciones",
    "tipo",
    "eFecharecepcion",
    "ePersonaEntrega",
    "eEmpresa",
    "eFechaRetornoGuia",
    "eNovedades",
    "sector",
    "eFechaRecepcionCourier"
})
public class QBEDestinatario
    extends EntityObject
{

    @XmlElement(name = "EntregaID", required = true)
    protected String entregaID;
    @XmlElement(name = "SolicitudID", required = true)
    protected String solicitudID;
    @XmlElement(name = "Empresa")
    protected String empresa;
    @XmlElement(name = "AtencionA")
    protected String atencionA;
    @XmlElement(name = "Direccion")
    protected String direccion;
    @XmlElement(name = "Ciudad")
    protected String ciudad;
    @XmlElement(name = "Telefonos")
    protected String telefonos;
    @XmlElement(name = "Observaciones")
    protected String observaciones;
    @XmlElement(name = "Tipo", required = true, type = Short.class, nillable = true)
    @XmlSchemaType(name = "unsignedByte")
    protected Short tipo;
    @XmlElement(name = "EFecharecepcion", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eFecharecepcion;
    @XmlElement(name = "EPersonaEntrega")
    protected String ePersonaEntrega;
    @XmlElement(name = "EEmpresa")
    protected String eEmpresa;
    @XmlElement(name = "EFechaRetornoGuia", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eFechaRetornoGuia;
    @XmlElement(name = "ENovedades")
    protected String eNovedades;
    @XmlElement(name = "Sector", required = true, nillable = true)
    protected String sector;
    @XmlElement(name = "EFechaRecepcionCourier", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eFechaRecepcionCourier;

    /**
     * Gets the value of the entregaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntregaID() {
        return entregaID;
    }

    /**
     * Sets the value of the entregaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntregaID(String value) {
        this.entregaID = value;
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
     * Gets the value of the atencionA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtencionA() {
        return atencionA;
    }

    /**
     * Sets the value of the atencionA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtencionA(String value) {
        this.atencionA = value;
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
     * Gets the value of the ciudad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets the value of the ciudad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Gets the value of the telefonos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * Sets the value of the telefonos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonos(String value) {
        this.telefonos = value;
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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setTipo(Short value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the eFecharecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEFecharecepcion() {
        return eFecharecepcion;
    }

    /**
     * Sets the value of the eFecharecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEFecharecepcion(XMLGregorianCalendar value) {
        this.eFecharecepcion = value;
    }

    /**
     * Gets the value of the ePersonaEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEPersonaEntrega() {
        return ePersonaEntrega;
    }

    /**
     * Sets the value of the ePersonaEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEPersonaEntrega(String value) {
        this.ePersonaEntrega = value;
    }

    /**
     * Gets the value of the eEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEEmpresa() {
        return eEmpresa;
    }

    /**
     * Sets the value of the eEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEEmpresa(String value) {
        this.eEmpresa = value;
    }

    /**
     * Gets the value of the eFechaRetornoGuia property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEFechaRetornoGuia() {
        return eFechaRetornoGuia;
    }

    /**
     * Sets the value of the eFechaRetornoGuia property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEFechaRetornoGuia(XMLGregorianCalendar value) {
        this.eFechaRetornoGuia = value;
    }

    /**
     * Gets the value of the eNovedades property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENovedades() {
        return eNovedades;
    }

    /**
     * Sets the value of the eNovedades property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENovedades(String value) {
        this.eNovedades = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSector(String value) {
        this.sector = value;
    }

    /**
     * Gets the value of the eFechaRecepcionCourier property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEFechaRecepcionCourier() {
        return eFechaRecepcionCourier;
    }

    /**
     * Sets the value of the eFechaRecepcionCourier property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEFechaRecepcionCourier(XMLGregorianCalendar value) {
        this.eFechaRecepcionCourier = value;
    }

}
