/**
 * ExtraVehiculoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ExtraVehiculoDTO  implements java.io.Serializable {
    private java.lang.String extraId;

    private java.lang.String nombre;

    private java.lang.String tipoExtraId;

    private java.math.BigDecimal valorExtra;

    public ExtraVehiculoDTO() {
    }

    public ExtraVehiculoDTO(
           java.lang.String extraId,
           java.lang.String nombre,
           java.lang.String tipoExtraId,
           java.math.BigDecimal valorExtra) {
           this.extraId = extraId;
           this.nombre = nombre;
           this.tipoExtraId = tipoExtraId;
           this.valorExtra = valorExtra;
    }


    /**
     * Gets the extraId value for this ExtraVehiculoDTO.
     * 
     * @return extraId
     */
    public java.lang.String getExtraId() {
        return extraId;
    }


    /**
     * Sets the extraId value for this ExtraVehiculoDTO.
     * 
     * @param extraId
     */
    public void setExtraId(java.lang.String extraId) {
        this.extraId = extraId;
    }


    /**
     * Gets the nombre value for this ExtraVehiculoDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ExtraVehiculoDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the tipoExtraId value for this ExtraVehiculoDTO.
     * 
     * @return tipoExtraId
     */
    public java.lang.String getTipoExtraId() {
        return tipoExtraId;
    }


    /**
     * Sets the tipoExtraId value for this ExtraVehiculoDTO.
     * 
     * @param tipoExtraId
     */
    public void setTipoExtraId(java.lang.String tipoExtraId) {
        this.tipoExtraId = tipoExtraId;
    }


    /**
     * Gets the valorExtra value for this ExtraVehiculoDTO.
     * 
     * @return valorExtra
     */
    public java.math.BigDecimal getValorExtra() {
        return valorExtra;
    }


    /**
     * Sets the valorExtra value for this ExtraVehiculoDTO.
     * 
     * @param valorExtra
     */
    public void setValorExtra(java.math.BigDecimal valorExtra) {
        this.valorExtra = valorExtra;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtraVehiculoDTO)) return false;
        ExtraVehiculoDTO other = (ExtraVehiculoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.extraId==null && other.getExtraId()==null) || 
             (this.extraId!=null &&
              this.extraId.equals(other.getExtraId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.tipoExtraId==null && other.getTipoExtraId()==null) || 
             (this.tipoExtraId!=null &&
              this.tipoExtraId.equals(other.getTipoExtraId()))) &&
            ((this.valorExtra==null && other.getValorExtra()==null) || 
             (this.valorExtra!=null &&
              this.valorExtra.equals(other.getValorExtra())));
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
        if (getExtraId() != null) {
            _hashCode += getExtraId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getTipoExtraId() != null) {
            _hashCode += getTipoExtraId().hashCode();
        }
        if (getValorExtra() != null) {
            _hashCode += getValorExtra().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtraVehiculoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ExtraVehiculoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extraId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoExtraId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoExtraId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorExtra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorExtra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
