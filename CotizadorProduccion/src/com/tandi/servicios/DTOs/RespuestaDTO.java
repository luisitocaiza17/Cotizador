/**
 * RespuestaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class RespuestaDTO  implements java.io.Serializable {
    private java.lang.String clase;

    private java.lang.String claseId;

    private com.tandi.servicios.DTOs.ErrorDTO errorDTO;

    private boolean exito;

    private com.tandi.servicios.DTOs.PolizaDTO poliza;

    public RespuestaDTO() {
    }

    public RespuestaDTO(
           java.lang.String clase,
           java.lang.String claseId,
           com.tandi.servicios.DTOs.ErrorDTO errorDTO,
           boolean exito,
           com.tandi.servicios.DTOs.PolizaDTO poliza) {
           this.clase = clase;
           this.claseId = claseId;
           this.errorDTO = errorDTO;
           this.exito = exito;
           this.poliza = poliza;
    }


    /**
     * Gets the clase value for this RespuestaDTO.
     * 
     * @return clase
     */
    public java.lang.String getClase() {
        return clase;
    }


    /**
     * Sets the clase value for this RespuestaDTO.
     * 
     * @param clase
     */
    public void setClase(java.lang.String clase) {
        this.clase = clase;
    }


    /**
     * Gets the claseId value for this RespuestaDTO.
     * 
     * @return claseId
     */
    public java.lang.String getClaseId() {
        return claseId;
    }


    /**
     * Sets the claseId value for this RespuestaDTO.
     * 
     * @param claseId
     */
    public void setClaseId(java.lang.String claseId) {
        this.claseId = claseId;
    }


    /**
     * Gets the errorDTO value for this RespuestaDTO.
     * 
     * @return errorDTO
     */
    public com.tandi.servicios.DTOs.ErrorDTO getErrorDTO() {
        return errorDTO;
    }


    /**
     * Sets the errorDTO value for this RespuestaDTO.
     * 
     * @param errorDTO
     */
    public void setErrorDTO(com.tandi.servicios.DTOs.ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }


    /**
     * Gets the exito value for this RespuestaDTO.
     * 
     * @return exito
     */
    public boolean isExito() {
        return exito;
    }


    /**
     * Sets the exito value for this RespuestaDTO.
     * 
     * @param exito
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }


    /**
     * Gets the poliza value for this RespuestaDTO.
     * 
     * @return poliza
     */
    public com.tandi.servicios.DTOs.PolizaDTO getPoliza() {
        return poliza;
    }


    /**
     * Sets the poliza value for this RespuestaDTO.
     * 
     * @param poliza
     */
    public void setPoliza(com.tandi.servicios.DTOs.PolizaDTO poliza) {
        this.poliza = poliza;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaDTO)) return false;
        RespuestaDTO other = (RespuestaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clase==null && other.getClase()==null) || 
             (this.clase!=null &&
              this.clase.equals(other.getClase()))) &&
            ((this.claseId==null && other.getClaseId()==null) || 
             (this.claseId!=null &&
              this.claseId.equals(other.getClaseId()))) &&
            ((this.errorDTO==null && other.getErrorDTO()==null) || 
             (this.errorDTO!=null &&
              this.errorDTO.equals(other.getErrorDTO()))) &&
            this.exito == other.isExito() &&
            ((this.poliza==null && other.getPoliza()==null) || 
             (this.poliza!=null &&
              this.poliza.equals(other.getPoliza())));
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
        if (getClase() != null) {
            _hashCode += getClase().hashCode();
        }
        if (getClaseId() != null) {
            _hashCode += getClaseId().hashCode();
        }
        if (getErrorDTO() != null) {
            _hashCode += getErrorDTO().hashCode();
        }
        _hashCode += (isExito() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPoliza() != null) {
            _hashCode += getPoliza().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RespuestaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDTO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorDTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ErrorDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poliza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "poliza"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PolizaDTO"));
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
