/**
 * ConfiguracionPagoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ConfiguracionPagoDTO  implements java.io.Serializable {
    private java.lang.String convenioPagoId;

    private java.util.Calendar fechaVencimientoTarjeta;

    private java.lang.String institucionaDebitar;

    private java.lang.String numeroCuenta;

    private int numeroPagos;

    private com.tandi.servicios.DTOs.PagoDTO[] pagos;

    private java.lang.String tipoCuenta;

    private java.lang.String tipoPagoId;

    public ConfiguracionPagoDTO() {
    }

    public ConfiguracionPagoDTO(
           java.lang.String convenioPagoId,
           java.util.Calendar fechaVencimientoTarjeta,
           java.lang.String institucionaDebitar,
           java.lang.String numeroCuenta,
           int numeroPagos,
           com.tandi.servicios.DTOs.PagoDTO[] pagos,
           java.lang.String tipoCuenta,
           java.lang.String tipoPagoId) {
           this.convenioPagoId = convenioPagoId;
           this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
           this.institucionaDebitar = institucionaDebitar;
           this.numeroCuenta = numeroCuenta;
           this.numeroPagos = numeroPagos;
           this.pagos = pagos;
           this.tipoCuenta = tipoCuenta;
           this.tipoPagoId = tipoPagoId;
    }


    /**
     * Gets the convenioPagoId value for this ConfiguracionPagoDTO.
     * 
     * @return convenioPagoId
     */
    public java.lang.String getConvenioPagoId() {
        return convenioPagoId;
    }


    /**
     * Sets the convenioPagoId value for this ConfiguracionPagoDTO.
     * 
     * @param convenioPagoId
     */
    public void setConvenioPagoId(java.lang.String convenioPagoId) {
        this.convenioPagoId = convenioPagoId;
    }


    /**
     * Gets the fechaVencimientoTarjeta value for this ConfiguracionPagoDTO.
     * 
     * @return fechaVencimientoTarjeta
     */
    public java.util.Calendar getFechaVencimientoTarjeta() {
        return fechaVencimientoTarjeta;
    }


    /**
     * Sets the fechaVencimientoTarjeta value for this ConfiguracionPagoDTO.
     * 
     * @param fechaVencimientoTarjeta
     */
    public void setFechaVencimientoTarjeta(java.util.Calendar fechaVencimientoTarjeta) {
        this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
    }


    /**
     * Gets the institucionaDebitar value for this ConfiguracionPagoDTO.
     * 
     * @return institucionaDebitar
     */
    public java.lang.String getInstitucionaDebitar() {
        return institucionaDebitar;
    }


    /**
     * Sets the institucionaDebitar value for this ConfiguracionPagoDTO.
     * 
     * @param institucionaDebitar
     */
    public void setInstitucionaDebitar(java.lang.String institucionaDebitar) {
        this.institucionaDebitar = institucionaDebitar;
    }


    /**
     * Gets the numeroCuenta value for this ConfiguracionPagoDTO.
     * 
     * @return numeroCuenta
     */
    public java.lang.String getNumeroCuenta() {
        return numeroCuenta;
    }


    /**
     * Sets the numeroCuenta value for this ConfiguracionPagoDTO.
     * 
     * @param numeroCuenta
     */
    public void setNumeroCuenta(java.lang.String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    /**
     * Gets the numeroPagos value for this ConfiguracionPagoDTO.
     * 
     * @return numeroPagos
     */
    public int getNumeroPagos() {
        return numeroPagos;
    }


    /**
     * Sets the numeroPagos value for this ConfiguracionPagoDTO.
     * 
     * @param numeroPagos
     */
    public void setNumeroPagos(int numeroPagos) {
        this.numeroPagos = numeroPagos;
    }


    /**
     * Gets the pagos value for this ConfiguracionPagoDTO.
     * 
     * @return pagos
     */
    public com.tandi.servicios.DTOs.PagoDTO[] getPagos() {
        return pagos;
    }


    /**
     * Sets the pagos value for this ConfiguracionPagoDTO.
     * 
     * @param pagos
     */
    public void setPagos(com.tandi.servicios.DTOs.PagoDTO[] pagos) {
        this.pagos = pagos;
    }


    /**
     * Gets the tipoCuenta value for this ConfiguracionPagoDTO.
     * 
     * @return tipoCuenta
     */
    public java.lang.String getTipoCuenta() {
        return tipoCuenta;
    }


    /**
     * Sets the tipoCuenta value for this ConfiguracionPagoDTO.
     * 
     * @param tipoCuenta
     */
    public void setTipoCuenta(java.lang.String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }


    /**
     * Gets the tipoPagoId value for this ConfiguracionPagoDTO.
     * 
     * @return tipoPagoId
     */
    public java.lang.String getTipoPagoId() {
        return tipoPagoId;
    }


    /**
     * Sets the tipoPagoId value for this ConfiguracionPagoDTO.
     * 
     * @param tipoPagoId
     */
    public void setTipoPagoId(java.lang.String tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfiguracionPagoDTO)) return false;
        ConfiguracionPagoDTO other = (ConfiguracionPagoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.convenioPagoId==null && other.getConvenioPagoId()==null) || 
             (this.convenioPagoId!=null &&
              this.convenioPagoId.equals(other.getConvenioPagoId()))) &&
            ((this.fechaVencimientoTarjeta==null && other.getFechaVencimientoTarjeta()==null) || 
             (this.fechaVencimientoTarjeta!=null &&
              this.fechaVencimientoTarjeta.equals(other.getFechaVencimientoTarjeta()))) &&
            ((this.institucionaDebitar==null && other.getInstitucionaDebitar()==null) || 
             (this.institucionaDebitar!=null &&
              this.institucionaDebitar.equals(other.getInstitucionaDebitar()))) &&
            ((this.numeroCuenta==null && other.getNumeroCuenta()==null) || 
             (this.numeroCuenta!=null &&
              this.numeroCuenta.equals(other.getNumeroCuenta()))) &&
            this.numeroPagos == other.getNumeroPagos() &&
            ((this.pagos==null && other.getPagos()==null) || 
             (this.pagos!=null &&
              java.util.Arrays.equals(this.pagos, other.getPagos()))) &&
            ((this.tipoCuenta==null && other.getTipoCuenta()==null) || 
             (this.tipoCuenta!=null &&
              this.tipoCuenta.equals(other.getTipoCuenta()))) &&
            ((this.tipoPagoId==null && other.getTipoPagoId()==null) || 
             (this.tipoPagoId!=null &&
              this.tipoPagoId.equals(other.getTipoPagoId())));
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
        if (getConvenioPagoId() != null) {
            _hashCode += getConvenioPagoId().hashCode();
        }
        if (getFechaVencimientoTarjeta() != null) {
            _hashCode += getFechaVencimientoTarjeta().hashCode();
        }
        if (getInstitucionaDebitar() != null) {
            _hashCode += getInstitucionaDebitar().hashCode();
        }
        if (getNumeroCuenta() != null) {
            _hashCode += getNumeroCuenta().hashCode();
        }
        _hashCode += getNumeroPagos();
        if (getPagos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPagos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPagos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipoCuenta() != null) {
            _hashCode += getTipoCuenta().hashCode();
        }
        if (getTipoPagoId() != null) {
            _hashCode += getTipoPagoId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfiguracionPagoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ConfiguracionPagoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("convenioPagoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "convenioPagoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimientoTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaVencimientoTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institucionaDebitar");
        elemField.setXmlName(new javax.xml.namespace.QName("", "institucionaDebitar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPagos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPagos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pagos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pagos"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PagoDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoCuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPagoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPagoId"));
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
