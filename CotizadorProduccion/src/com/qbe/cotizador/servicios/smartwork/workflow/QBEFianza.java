
package com.qbe.cotizador.servicios.smartwork.workflow;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for QBE_Fianza complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_Fianza">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="FianzasID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Sector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MontoGarantia" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="VigenciaHasta" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TiempoVigencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Tasa" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Incondicionalrrevocable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ObjetoAsegurado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroOficio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_Fianza", propOrder = {
    "fianzasID",
    "solicitudID",
    "sector",
    "montoGarantia",
    "vigenciaHasta",
    "tiempoVigencia",
    "tasa",
    "incondicionalrrevocable",
    "objetoAsegurado",
    "numeroOficio"
})
public class QBEFianza
    extends EntityObject
{

    @XmlElement(name = "FianzasID", required = true)
    protected String fianzasID;
    @XmlElement(name = "SolicitudID", required = true, nillable = true)
    protected String solicitudID;
    @XmlElement(name = "Sector")
    protected String sector;
    @XmlElement(name = "MontoGarantia", required = true, nillable = true)
    protected BigDecimal montoGarantia;
    @XmlElement(name = "VigenciaHasta", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar vigenciaHasta;
    @XmlElement(name = "TiempoVigencia", required = true, type = Integer.class, nillable = true)
    protected Integer tiempoVigencia;
    @XmlElement(name = "Tasa", required = true, nillable = true)
    protected BigDecimal tasa;
    @XmlElement(name = "Incondicionalrrevocable", required = true, type = Boolean.class, nillable = true)
    protected Boolean incondicionalrrevocable;
    @XmlElement(name = "ObjetoAsegurado")
    protected String objetoAsegurado;
    @XmlElement(name = "NumeroOficio")
    protected String numeroOficio;

    /**
     * Gets the value of the fianzasID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFianzasID() {
        return fianzasID;
    }

    /**
     * Sets the value of the fianzasID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFianzasID(String value) {
        this.fianzasID = value;
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
     * Gets the value of the montoGarantia property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoGarantia() {
        return montoGarantia;
    }

    /**
     * Sets the value of the montoGarantia property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoGarantia(BigDecimal value) {
        this.montoGarantia = value;
    }

    /**
     * Gets the value of the vigenciaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVigenciaHasta() {
        return vigenciaHasta;
    }

    /**
     * Sets the value of the vigenciaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVigenciaHasta(XMLGregorianCalendar value) {
        this.vigenciaHasta = value;
    }

    /**
     * Gets the value of the tiempoVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTiempoVigencia() {
        return tiempoVigencia;
    }

    /**
     * Sets the value of the tiempoVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTiempoVigencia(Integer value) {
        this.tiempoVigencia = value;
    }

    /**
     * Gets the value of the tasa property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTasa() {
        return tasa;
    }

    /**
     * Sets the value of the tasa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTasa(BigDecimal value) {
        this.tasa = value;
    }

    /**
     * Gets the value of the incondicionalrrevocable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncondicionalrrevocable() {
        return incondicionalrrevocable;
    }

    /**
     * Sets the value of the incondicionalrrevocable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncondicionalrrevocable(Boolean value) {
        this.incondicionalrrevocable = value;
    }

    /**
     * Gets the value of the objetoAsegurado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjetoAsegurado() {
        return objetoAsegurado;
    }

    /**
     * Sets the value of the objetoAsegurado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjetoAsegurado(String value) {
        this.objetoAsegurado = value;
    }

    /**
     * Gets the value of the numeroOficio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroOficio() {
        return numeroOficio;
    }

    /**
     * Sets the value of the numeroOficio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroOficio(String value) {
        this.numeroOficio = value;
    }

}
