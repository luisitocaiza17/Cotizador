
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EntityObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityObject">
 *   &lt;complexContent>
 *     &lt;extension base="{http://smartwork.com.ec/}StructuralObject">
 *       &lt;sequence>
 *         &lt;element name="EntityKey" type="{http://smartwork.com.ec/}EntityKey" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityObject", propOrder = {
    "entityKey"
})
@XmlSeeAlso({
    QBESolicitudEmision.class,
    QBEEntidad.class,
    QBEDeuda.class,
    QBEDestinatario.class,
    QBEFianza.class,
    QBEControlDocumentos.class
})
public abstract class EntityObject
    extends StructuralObject
{

    @XmlElement(name = "EntityKey")
    protected EntityKey entityKey;

    /**
     * Gets the value of the entityKey property.
     * 
     * @return
     *     possible object is
     *     {@link EntityKey }
     *     
     */
    public EntityKey getEntityKey() {
        return entityKey;
    }

    /**
     * Sets the value of the entityKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityKey }
     *     
     */
    public void setEntityKey(EntityKey value) {
        this.entityKey = value;
    }

}
