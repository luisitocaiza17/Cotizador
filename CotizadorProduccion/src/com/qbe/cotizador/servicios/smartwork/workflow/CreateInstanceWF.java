
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
 *         &lt;element name="Token" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WorkFlowType" type="{http://smartwork.com.ec/}WorkFlowType"/>
 *         &lt;element name="Solicitud" type="{http://smartwork.com.ec/}QBE_SolicitudEmision" minOccurs="0"/>
 *         &lt;element name="Entidades" type="{http://smartwork.com.ec/}QBE_Entidad" minOccurs="0"/>
 *         &lt;element name="Documentos" type="{http://smartwork.com.ec/}ArrayOfQBE_ControlDocumentos" minOccurs="0"/>
 *         &lt;element name="Deuda" type="{http://smartwork.com.ec/}QBE_Deuda" minOccurs="0"/>
 *         &lt;element name="Destinatarios" type="{http://smartwork.com.ec/}QBE_Destinatario" minOccurs="0"/>
 *         &lt;element name="DatosFianza" type="{http://smartwork.com.ec/}QBE_Fianza" minOccurs="0"/>
 *         &lt;element name="Archivos" type="{http://smartwork.com.ec/}ArrayOfFileList" minOccurs="0"/>
 *         &lt;element name="VisadoDocumentos" type="{http://smartwork.com.ec/}ArrayOfDocumentList" minOccurs="0"/>
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
    "token",
    "username",
    "workFlowType",
    "solicitud",
    "entidades",
    "documentos",
    "deuda",
    "destinatarios",
    "datosFianza",
    "archivos",
    "visadoDocumentos"
})
@XmlRootElement(name = "CreateInstanceWF")
public class CreateInstanceWF {

    @XmlElement(name = "Token")
    protected String token;
    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "WorkFlowType", required = true)
    protected WorkFlowType workFlowType;
    @XmlElement(name = "Solicitud")
    protected QBESolicitudEmision solicitud;
    @XmlElement(name = "Entidades")
    protected QBEEntidad entidades;
    @XmlElement(name = "Documentos")
    protected ArrayOfQBEControlDocumentos documentos;
    @XmlElement(name = "Deuda")
    protected QBEDeuda deuda;
    @XmlElement(name = "Destinatarios")
    protected QBEDestinatario destinatarios;
    @XmlElement(name = "DatosFianza")
    protected QBEFianza datosFianza;
    @XmlElement(name = "Archivos")
    protected ArrayOfFileList archivos;
    @XmlElement(name = "VisadoDocumentos")
    protected ArrayOfDocumentList visadoDocumentos;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the workFlowType property.
     * 
     * @return
     *     possible object is
     *     {@link WorkFlowType }
     *     
     */
    public WorkFlowType getWorkFlowType() {
        return workFlowType;
    }

    /**
     * Sets the value of the workFlowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkFlowType }
     *     
     */
    public void setWorkFlowType(WorkFlowType value) {
        this.workFlowType = value;
    }

    /**
     * Gets the value of the solicitud property.
     * 
     * @return
     *     possible object is
     *     {@link QBESolicitudEmision }
     *     
     */
    public QBESolicitudEmision getSolicitud() {
        return solicitud;
    }

    /**
     * Sets the value of the solicitud property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBESolicitudEmision }
     *     
     */
    public void setSolicitud(QBESolicitudEmision value) {
        this.solicitud = value;
    }

    /**
     * Gets the value of the entidades property.
     * 
     * @return
     *     possible object is
     *     {@link QBEEntidad }
     *     
     */
    public QBEEntidad getEntidades() {
        return entidades;
    }

    /**
     * Sets the value of the entidades property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBEEntidad }
     *     
     */
    public void setEntidades(QBEEntidad value) {
        this.entidades = value;
    }

    /**
     * Gets the value of the documentos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfQBEControlDocumentos }
     *     
     */
    public ArrayOfQBEControlDocumentos getDocumentos() {
        return documentos;
    }

    /**
     * Sets the value of the documentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfQBEControlDocumentos }
     *     
     */
    public void setDocumentos(ArrayOfQBEControlDocumentos value) {
        this.documentos = value;
    }

    /**
     * Gets the value of the deuda property.
     * 
     * @return
     *     possible object is
     *     {@link QBEDeuda }
     *     
     */
    public QBEDeuda getDeuda() {
        return deuda;
    }

    /**
     * Sets the value of the deuda property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBEDeuda }
     *     
     */
    public void setDeuda(QBEDeuda value) {
        this.deuda = value;
    }

    /**
     * Gets the value of the destinatarios property.
     * 
     * @return
     *     possible object is
     *     {@link QBEDestinatario }
     *     
     */
    public QBEDestinatario getDestinatarios() {
        return destinatarios;
    }

    /**
     * Sets the value of the destinatarios property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBEDestinatario }
     *     
     */
    public void setDestinatarios(QBEDestinatario value) {
        this.destinatarios = value;
    }

    /**
     * Gets the value of the datosFianza property.
     * 
     * @return
     *     possible object is
     *     {@link QBEFianza }
     *     
     */
    public QBEFianza getDatosFianza() {
        return datosFianza;
    }

    /**
     * Sets the value of the datosFianza property.
     * 
     * @param value
     *     allowed object is
     *     {@link QBEFianza }
     *     
     */
    public void setDatosFianza(QBEFianza value) {
        this.datosFianza = value;
    }

    /**
     * Gets the value of the archivos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFileList }
     *     
     */
    public ArrayOfFileList getArchivos() {
        return archivos;
    }

    /**
     * Sets the value of the archivos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFileList }
     *     
     */
    public void setArchivos(ArrayOfFileList value) {
        this.archivos = value;
    }

    /**
     * Gets the value of the visadoDocumentos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentList }
     *     
     */
    public ArrayOfDocumentList getVisadoDocumentos() {
        return visadoDocumentos;
    }

    /**
     * Sets the value of the visadoDocumentos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentList }
     *     
     */
    public void setVisadoDocumentos(ArrayOfDocumentList value) {
        this.visadoDocumentos = value;
    }

}
