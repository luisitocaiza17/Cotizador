
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreateInstanceWFResult" type="{http://smartwork.com.ec/}WorkFlowResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "createInstanceWFResult"
})
@XmlRootElement(name = "CreateInstanceWFResponse")
public class CreateInstanceWFResponse {

    @XmlElement(name = "CreateInstanceWFResult")
    protected WorkFlowResult createInstanceWFResult;

    /**
     * Gets the value of the createInstanceWFResult property.
     * 
     * @return
     *     possible object is
     *     {@link WorkFlowResult }
     *     
     */
    public WorkFlowResult getCreateInstanceWFResult() {
        return createInstanceWFResult;
    }

    /**
     * Sets the value of the createInstanceWFResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkFlowResult }
     *     
     */
    public void setCreateInstanceWFResult(WorkFlowResult value) {
        this.createInstanceWFResult = value;
    }

}
