
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
 *         &lt;element name="LoadDocumentsByClassificatorResult" type="{http://smartwork.com.ec/}ArrayOfDocumentList" minOccurs="0"/>
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
    "loadDocumentsByClassificatorResult"
})
@XmlRootElement(name = "LoadDocumentsByClassificatorResponse")
public class LoadDocumentsByClassificatorResponse {

    @XmlElement(name = "LoadDocumentsByClassificatorResult")
    protected ArrayOfDocumentList loadDocumentsByClassificatorResult;

    /**
     * Gets the value of the loadDocumentsByClassificatorResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentList }
     *     
     */
    public ArrayOfDocumentList getLoadDocumentsByClassificatorResult() {
        return loadDocumentsByClassificatorResult;
    }

    /**
     * Sets the value of the loadDocumentsByClassificatorResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentList }
     *     
     */
    public void setLoadDocumentsByClassificatorResult(ArrayOfDocumentList value) {
        this.loadDocumentsByClassificatorResult = value;
    }

}
