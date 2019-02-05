
package com.qbe.cotizador.servicios.recargoSC.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarRecargoSeguroAgricolaResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarRecargoSeguroAgricolaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecargoSeguroAgricola" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarRecargoSeguroAgricolaResponse1", propOrder = {
    "recargoSeguroAgricola"
})
public class ConsultarRecargoSeguroAgricolaResponse {

    @XmlElement(name = "RecargoSeguroAgricola")
    protected Double recargoSeguroAgricola;

    /**
     * Obtiene el valor de la propiedad recargoSeguroAgricola.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRecargoSeguroAgricola() {
        return recargoSeguroAgricola;
    }

    /**
     * Define el valor de la propiedad recargoSeguroAgricola.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRecargoSeguroAgricola(Double value) {
        this.recargoSeguroAgricola = value;
    }

}
