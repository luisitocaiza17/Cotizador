/**
 * ComponenteXDocDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ComponenteXDocDTO  implements java.io.Serializable {
    private java.lang.String componenteFinancieroId;

    private java.lang.String documentoId;

    private java.lang.String valor;

    private java.lang.String valorBase;

    public ComponenteXDocDTO() {
    }

    public ComponenteXDocDTO(
           java.lang.String componenteFinancieroId,
           java.lang.String documentoId,
           java.lang.String valor,
           java.lang.String valorBase) {
           this.componenteFinancieroId = componenteFinancieroId;
           this.documentoId = documentoId;
           this.valor = valor;
           this.valorBase = valorBase;
    }


    /**
     * Gets the componenteFinancieroId value for this ComponenteXDocDTO.
     * 
     * @return componenteFinancieroId
     */
    public java.lang.String getComponenteFinancieroId() {
        return componenteFinancieroId;
    }


    /**
     * Sets the componenteFinancieroId value for this ComponenteXDocDTO.
     * 
     * @param componenteFinancieroId
     */
    public void setComponenteFinancieroId(java.lang.String componenteFinancieroId) {
        this.componenteFinancieroId = componenteFinancieroId;
    }


    /**
     * Gets the documentoId value for this ComponenteXDocDTO.
     * 
     * @return documentoId
     */
    public java.lang.String getDocumentoId() {
        return documentoId;
    }


    /**
     * Sets the documentoId value for this ComponenteXDocDTO.
     * 
     * @param documentoId
     */
    public void setDocumentoId(java.lang.String documentoId) {
        this.documentoId = documentoId;
    }


    /**
     * Gets the valor value for this ComponenteXDocDTO.
     * 
     * @return valor
     */
    public java.lang.String getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ComponenteXDocDTO.
     * 
     * @param valor
     */
    public void setValor(java.lang.String valor) {
        this.valor = valor;
    }


    /**
     * Gets the valorBase value for this ComponenteXDocDTO.
     * 
     * @return valorBase
     */
    public java.lang.String getValorBase() {
        return valorBase;
    }


    /**
     * Sets the valorBase value for this ComponenteXDocDTO.
     * 
     * @param valorBase
     */
    public void setValorBase(java.lang.String valorBase) {
        this.valorBase = valorBase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComponenteXDocDTO)) return false;
        ComponenteXDocDTO other = (ComponenteXDocDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.componenteFinancieroId==null && other.getComponenteFinancieroId()==null) || 
             (this.componenteFinancieroId!=null &&
              this.componenteFinancieroId.equals(other.getComponenteFinancieroId()))) &&
            ((this.documentoId==null && other.getDocumentoId()==null) || 
             (this.documentoId!=null &&
              this.documentoId.equals(other.getDocumentoId()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.valorBase==null && other.getValorBase()==null) || 
             (this.valorBase!=null &&
              this.valorBase.equals(other.getValorBase())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getComponenteFinancieroId() != null) {
            _hashCode += getComponenteFinancieroId().hashCode();
        }
        if (getDocumentoId() != null) {
            _hashCode += getDocumentoId().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getValorBase() != null) {
            _hashCode += getValorBase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ComponenteXDocDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ComponenteXDocDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("componenteFinancieroId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "componenteFinancieroId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
