
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QBE_ControlDocumentos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_ControlDocumentos">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="RequisitoID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="ClasificadorID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="EsActivo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="VisadoValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OptionalValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AditionalValue" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Observaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_ControlDocumentos", propOrder = {
    "requisitoID",
    "solicitudID",
    "clasificadorID",
    "esActivo",
    "visadoValue",
    "optionalValue",
    "aditionalValue",
    "observaciones"
})
public class QBEControlDocumentos
    extends EntityObject
{

    @XmlElement(name = "RequisitoID", required = true)
    protected String requisitoID;
    @XmlElement(name = "SolicitudID", required = true, nillable = true)
    protected String solicitudID;
    @XmlElement(name = "ClasificadorID", required = true, nillable = true)
    protected String clasificadorID;
    @XmlElement(name = "EsActivo", required = true, type = Boolean.class, nillable = true)
    protected Boolean esActivo;
    @XmlElement(name = "VisadoValue", required = true, type = Boolean.class, nillable = true)
    protected Boolean visadoValue;
    @XmlElement(name = "OptionalValue", required = true, type = Boolean.class, nillable = true)
    protected Boolean optionalValue;
    @XmlElement(name = "AditionalValue", required = true, type = Boolean.class, nillable = true)
    protected Boolean aditionalValue;
    @XmlElement(name = "Observaciones")
    protected String observaciones;

    /**
     * Gets the value of the requisitoID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequisitoID() {
        return requisitoID;
    }

    /**
     * Sets the value of the requisitoID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequisitoID(String value) {
        this.requisitoID = value;
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
     * Gets the value of the clasificadorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasificadorID() {
        return clasificadorID;
    }

    /**
     * Sets the value of the clasificadorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasificadorID(String value) {
        this.clasificadorID = value;
    }

    /**
     * Gets the value of the esActivo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEsActivo() {
        return esActivo;
    }

    /**
     * Sets the value of the esActivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEsActivo(Boolean value) {
        this.esActivo = value;
    }

    /**
     * Gets the value of the visadoValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisadoValue() {
        return visadoValue;
    }

    /**
     * Sets the value of the visadoValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisadoValue(Boolean value) {
        this.visadoValue = value;
    }

    /**
     * Gets the value of the optionalValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptionalValue() {
        return optionalValue;
    }

    /**
     * Sets the value of the optionalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptionalValue(Boolean value) {
        this.optionalValue = value;
    }

    /**
     * Gets the value of the aditionalValue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAditionalValue() {
        return aditionalValue;
    }

    /**
     * Sets the value of the aditionalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAditionalValue(Boolean value) {
        this.aditionalValue = value;
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

}
