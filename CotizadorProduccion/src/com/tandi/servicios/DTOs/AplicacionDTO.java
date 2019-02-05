/**
 * AplicacionDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class AplicacionDTO  implements java.io.Serializable {
    private java.lang.String numeroAplicacion;

    private java.lang.Long ordenPoliza;

    private java.lang.String polizaid;

    public AplicacionDTO() {
    }

    public AplicacionDTO(
           java.lang.String numeroAplicacion,
           java.lang.Long ordenPoliza,
           java.lang.String polizaid) {
           this.numeroAplicacion = numeroAplicacion;
           this.ordenPoliza = ordenPoliza;
           this.polizaid = polizaid;
    }


    /**
     * Gets the numeroAplicacion value for this AplicacionDTO.
     * 
     * @return numeroAplicacion
     */
    public java.lang.String getNumeroAplicacion() {
        return numeroAplicacion;
    }


    /**
     * Sets the numeroAplicacion value for this AplicacionDTO.
     * 
     * @param numeroAplicacion
     */
    public void setNumeroAplicacion(java.lang.String numeroAplicacion) {
        this.numeroAplicacion = numeroAplicacion;
    }


    /**
     * Gets the ordenPoliza value for this AplicacionDTO.
     * 
     * @return ordenPoliza
     */
    public java.lang.Long getOrdenPoliza() {
        return ordenPoliza;
    }


    /**
     * Sets the ordenPoliza value for this AplicacionDTO.
     * 
     * @param ordenPoliza
     */
    public void setOrdenPoliza(java.lang.Long ordenPoliza) {
        this.ordenPoliza = ordenPoliza;
    }


    /**
     * Gets the polizaid value for this AplicacionDTO.
     * 
     * @return polizaid
     */
    public java.lang.String getPolizaid() {
        return polizaid;
    }


    /**
     * Sets the polizaid value for this AplicacionDTO.
     * 
     * @param polizaid
     */
    public void setPolizaid(java.lang.String polizaid) {
        this.polizaid = polizaid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AplicacionDTO)) return false;
        AplicacionDTO other = (AplicacionDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroAplicacion==null && other.getNumeroAplicacion()==null) || 
             (this.numeroAplicacion!=null &&
              this.numeroAplicacion.equals(other.getNumeroAplicacion()))) &&
            ((this.ordenPoliza==null && other.getOrdenPoliza()==null) || 
             (this.ordenPoliza!=null &&
              this.ordenPoliza.equals(other.getOrdenPoliza()))) &&
            ((this.polizaid==null && other.getPolizaid()==null) || 
             (this.polizaid!=null &&
              this.polizaid.equals(other.getPolizaid())));
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
        if (getNumeroAplicacion() != null) {
            _hashCode += getNumeroAplicacion().hashCode();
        }
        if (getOrdenPoliza() != null) {
            _hashCode += getOrdenPoliza().hashCode();
        }
        if (getPolizaid() != null) {
            _hashCode += getPolizaid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AplicacionDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "AplicacionDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAplicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroAplicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordenPoliza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordenPoliza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("polizaid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "polizaid"));
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
