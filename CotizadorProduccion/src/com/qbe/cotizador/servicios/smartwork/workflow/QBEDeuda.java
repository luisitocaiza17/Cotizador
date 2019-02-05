
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QBE_Deuda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QBE_Deuda">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}EntityObject">
 *       &lt;sequence>
 *         &lt;element name="DeudaID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="SolicitudID" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Rama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SaldoPendiente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QBE_Deuda", propOrder = {
    "deudaID",
    "solicitudID",
    "rama",
    "saldoPendiente"
})
public class QBEDeuda
    extends EntityObject
{

    @XmlElement(name = "DeudaID", required = true)
    protected String deudaID;
    @XmlElement(name = "SolicitudID", required = true, nillable = true)
    protected String solicitudID;
    @XmlElement(name = "Rama")
    protected String rama;
    @XmlElement(name = "SaldoPendiente")
    protected String saldoPendiente;

    /**
     * Gets the value of the deudaID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeudaID() {
        return deudaID;
    }

    /**
     * Sets the value of the deudaID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeudaID(String value) {
        this.deudaID = value;
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
     * Gets the value of the rama property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRama() {
        return rama;
    }

    /**
     * Sets the value of the rama property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRama(String value) {
        this.rama = value;
    }

    /**
     * Gets the value of the saldoPendiente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaldoPendiente() {
        return saldoPendiente;
    }

    /**
     * Sets the value of the saldoPendiente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaldoPendiente(String value) {
        this.saldoPendiente = value;
    }

}
