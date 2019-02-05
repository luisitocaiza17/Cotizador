/**
 * FacturaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class FacturaDTO  implements java.io.Serializable {
    private java.lang.String autorizacionSRI;

    private java.lang.String cobradorId;

    private java.util.Calendar fechaVencimientoFactura;

    private java.lang.String loteImpresion;

    private java.lang.String numeroFactura;

    private java.lang.String tipoDocumentoFac;

    private java.math.BigDecimal valorFactura;

    public FacturaDTO() {
    }

    public FacturaDTO(
           java.lang.String autorizacionSRI,
           java.lang.String cobradorId,
           java.util.Calendar fechaVencimientoFactura,
           java.lang.String loteImpresion,
           java.lang.String numeroFactura,
           java.lang.String tipoDocumentoFac,
           java.math.BigDecimal valorFactura) {
           this.autorizacionSRI = autorizacionSRI;
           this.cobradorId = cobradorId;
           this.fechaVencimientoFactura = fechaVencimientoFactura;
           this.loteImpresion = loteImpresion;
           this.numeroFactura = numeroFactura;
           this.tipoDocumentoFac = tipoDocumentoFac;
           this.valorFactura = valorFactura;
    }


    /**
     * Gets the autorizacionSRI value for this FacturaDTO.
     * 
     * @return autorizacionSRI
     */
    public java.lang.String getAutorizacionSRI() {
        return autorizacionSRI;
    }


    /**
     * Sets the autorizacionSRI value for this FacturaDTO.
     * 
     * @param autorizacionSRI
     */
    public void setAutorizacionSRI(java.lang.String autorizacionSRI) {
        this.autorizacionSRI = autorizacionSRI;
    }


    /**
     * Gets the cobradorId value for this FacturaDTO.
     * 
     * @return cobradorId
     */
    public java.lang.String getCobradorId() {
        return cobradorId;
    }


    /**
     * Sets the cobradorId value for this FacturaDTO.
     * 
     * @param cobradorId
     */
    public void setCobradorId(java.lang.String cobradorId) {
        this.cobradorId = cobradorId;
    }


    /**
     * Gets the fechaVencimientoFactura value for this FacturaDTO.
     * 
     * @return fechaVencimientoFactura
     */
    public java.util.Calendar getFechaVencimientoFactura() {
        return fechaVencimientoFactura;
    }


    /**
     * Sets the fechaVencimientoFactura value for this FacturaDTO.
     * 
     * @param fechaVencimientoFactura
     */
    public void setFechaVencimientoFactura(java.util.Calendar fechaVencimientoFactura) {
        this.fechaVencimientoFactura = fechaVencimientoFactura;
    }


    /**
     * Gets the loteImpresion value for this FacturaDTO.
     * 
     * @return loteImpresion
     */
    public java.lang.String getLoteImpresion() {
        return loteImpresion;
    }


    /**
     * Sets the loteImpresion value for this FacturaDTO.
     * 
     * @param loteImpresion
     */
    public void setLoteImpresion(java.lang.String loteImpresion) {
        this.loteImpresion = loteImpresion;
    }


    /**
     * Gets the numeroFactura value for this FacturaDTO.
     * 
     * @return numeroFactura
     */
    public java.lang.String getNumeroFactura() {
        return numeroFactura;
    }


    /**
     * Sets the numeroFactura value for this FacturaDTO.
     * 
     * @param numeroFactura
     */
    public void setNumeroFactura(java.lang.String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }


    /**
     * Gets the tipoDocumentoFac value for this FacturaDTO.
     * 
     * @return tipoDocumentoFac
     */
    public java.lang.String getTipoDocumentoFac() {
        return tipoDocumentoFac;
    }


    /**
     * Sets the tipoDocumentoFac value for this FacturaDTO.
     * 
     * @param tipoDocumentoFac
     */
    public void setTipoDocumentoFac(java.lang.String tipoDocumentoFac) {
        this.tipoDocumentoFac = tipoDocumentoFac;
    }


    /**
     * Gets the valorFactura value for this FacturaDTO.
     * 
     * @return valorFactura
     */
    public java.math.BigDecimal getValorFactura() {
        return valorFactura;
    }


    /**
     * Sets the valorFactura value for this FacturaDTO.
     * 
     * @param valorFactura
     */
    public void setValorFactura(java.math.BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FacturaDTO)) return false;
        FacturaDTO other = (FacturaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.autorizacionSRI==null && other.getAutorizacionSRI()==null) || 
             (this.autorizacionSRI!=null &&
              this.autorizacionSRI.equals(other.getAutorizacionSRI()))) &&
            ((this.cobradorId==null && other.getCobradorId()==null) || 
             (this.cobradorId!=null &&
              this.cobradorId.equals(other.getCobradorId()))) &&
            ((this.fechaVencimientoFactura==null && other.getFechaVencimientoFactura()==null) || 
             (this.fechaVencimientoFactura!=null &&
              this.fechaVencimientoFactura.equals(other.getFechaVencimientoFactura()))) &&
            ((this.loteImpresion==null && other.getLoteImpresion()==null) || 
             (this.loteImpresion!=null &&
              this.loteImpresion.equals(other.getLoteImpresion()))) &&
            ((this.numeroFactura==null && other.getNumeroFactura()==null) || 
             (this.numeroFactura!=null &&
              this.numeroFactura.equals(other.getNumeroFactura()))) &&
            ((this.tipoDocumentoFac==null && other.getTipoDocumentoFac()==null) || 
             (this.tipoDocumentoFac!=null &&
              this.tipoDocumentoFac.equals(other.getTipoDocumentoFac()))) &&
            ((this.valorFactura==null && other.getValorFactura()==null) || 
             (this.valorFactura!=null &&
              this.valorFactura.equals(other.getValorFactura())));
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
        if (getAutorizacionSRI() != null) {
            _hashCode += getAutorizacionSRI().hashCode();
        }
        if (getCobradorId() != null) {
            _hashCode += getCobradorId().hashCode();
        }
        if (getFechaVencimientoFactura() != null) {
            _hashCode += getFechaVencimientoFactura().hashCode();
        }
        if (getLoteImpresion() != null) {
            _hashCode += getLoteImpresion().hashCode();
        }
        if (getNumeroFactura() != null) {
            _hashCode += getNumeroFactura().hashCode();
        }
        if (getTipoDocumentoFac() != null) {
            _hashCode += getTipoDocumentoFac().hashCode();
        }
        if (getValorFactura() != null) {
            _hashCode += getValorFactura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FacturaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "FacturaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autorizacionSRI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autorizacionSRI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cobradorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cobradorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaVencimientoFactura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaVencimientoFactura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loteImpresion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loteImpresion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFactura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroFactura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentoFac");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDocumentoFac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorFactura");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorFactura"));
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
