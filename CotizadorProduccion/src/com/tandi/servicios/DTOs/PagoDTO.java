/**
 * PagoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class PagoDTO  implements java.io.Serializable {
    private boolean esCuota;

    private java.util.Calendar fechaPago;

    private int orden;

    private java.math.BigDecimal valorPago;

    public PagoDTO() {
    }

    public PagoDTO(
           boolean esCuota,
           java.util.Calendar fechaPago,
           int orden,
           java.math.BigDecimal valorPago) {
           this.esCuota = esCuota;
           this.fechaPago = fechaPago;
           this.orden = orden;
           this.valorPago = valorPago;
    }


    /**
     * Gets the esCuota value for this PagoDTO.
     * 
     * @return esCuota
     */
    public boolean isEsCuota() {
        return esCuota;
    }


    /**
     * Sets the esCuota value for this PagoDTO.
     * 
     * @param esCuota
     */
    public void setEsCuota(boolean esCuota) {
        this.esCuota = esCuota;
    }


    /**
     * Gets the fechaPago value for this PagoDTO.
     * 
     * @return fechaPago
     */
    public java.util.Calendar getFechaPago() {
        return fechaPago;
    }


    /**
     * Sets the fechaPago value for this PagoDTO.
     * 
     * @param fechaPago
     */
    public void setFechaPago(java.util.Calendar fechaPago) {
        this.fechaPago = fechaPago;
    }


    /**
     * Gets the orden value for this PagoDTO.
     * 
     * @return orden
     */
    public int getOrden() {
        return orden;
    }


    /**
     * Sets the orden value for this PagoDTO.
     * 
     * @param orden
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }


    /**
     * Gets the valorPago value for this PagoDTO.
     * 
     * @return valorPago
     */
    public java.math.BigDecimal getValorPago() {
        return valorPago;
    }


    /**
     * Sets the valorPago value for this PagoDTO.
     * 
     * @param valorPago
     */
    public void setValorPago(java.math.BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PagoDTO)) return false;
        PagoDTO other = (PagoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.esCuota == other.isEsCuota() &&
            ((this.fechaPago==null && other.getFechaPago()==null) || 
             (this.fechaPago!=null &&
              this.fechaPago.equals(other.getFechaPago()))) &&
            this.orden == other.getOrden() &&
            ((this.valorPago==null && other.getValorPago()==null) || 
             (this.valorPago!=null &&
              this.valorPago.equals(other.getValorPago())));
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
        _hashCode += (isEsCuota() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFechaPago() != null) {
            _hashCode += getFechaPago().hashCode();
        }
        _hashCode += getOrden();
        if (getValorPago() != null) {
            _hashCode += getValorPago().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PagoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PagoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esCuota");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esCuota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaPago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orden");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPago");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPago"));
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
