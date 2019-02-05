
package com.qbe.cotizador.servicios.smartwork.workflow;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfQBE_ControlDocumentos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfQBE_ControlDocumentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QBE_ControlDocumentos" type="{http://smartwork.com.ec/}QBE_ControlDocumentos" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfQBE_ControlDocumentos", propOrder = {
    "qbeControlDocumentos"
})
public class ArrayOfQBEControlDocumentos {

    @XmlElement(name = "QBE_ControlDocumentos", nillable = true)
    protected List<QBEControlDocumentos> qbeControlDocumentos;

    /**
     * Gets the value of the qbeControlDocumentos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qbeControlDocumentos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQBEControlDocumentos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QBEControlDocumentos }
     * 
     * 
     */
    public List<QBEControlDocumentos> getQBEControlDocumentos() {
        if (qbeControlDocumentos == null) {
            qbeControlDocumentos = new ArrayList<QBEControlDocumentos>();
        }
        return this.qbeControlDocumentos;
    }

}
