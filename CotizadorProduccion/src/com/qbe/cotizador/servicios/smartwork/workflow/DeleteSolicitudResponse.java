
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
 *         &lt;element name="DeleteSolicitudResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "deleteSolicitudResult"
})
@XmlRootElement(name = "DeleteSolicitudResponse")
public class DeleteSolicitudResponse {

    @XmlElement(name = "DeleteSolicitudResult")
    protected boolean deleteSolicitudResult;

    /**
     * Gets the value of the deleteSolicitudResult property.
     * 
     */
    public boolean isDeleteSolicitudResult() {
        return deleteSolicitudResult;
    }

    /**
     * Sets the value of the deleteSolicitudResult property.
     * 
     */
    public void setDeleteSolicitudResult(boolean value) {
        this.deleteSolicitudResult = value;
    }

}
