/**
 * TransporteDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class TransporteDTO  extends com.tandi.servicios.DTOs.ItemDTO  implements java.io.Serializable {
    private java.lang.String descripcion;

    private float porcentajePrimaDeposito;

    private java.lang.String subRamoTransporte;

    private java.lang.String tipoMercaderiaId;

    private java.lang.String tipoTransporteId;

    private double valorPrimaDeposito;

    public TransporteDTO() {
    }

    public TransporteDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado,
           java.lang.String descripcion,
           float porcentajePrimaDeposito,
           java.lang.String subRamoTransporte,
           java.lang.String tipoMercaderiaId,
           java.lang.String tipoTransporteId,
           double valorPrimaDeposito) {
        super(
            claseRiesgoId,
            id,
            texto,
            tipoItemId,
            tipoRiesgoId,
            valorAsegurado);
        this.descripcion = descripcion;
        this.porcentajePrimaDeposito = porcentajePrimaDeposito;
        this.subRamoTransporte = subRamoTransporte;
        this.tipoMercaderiaId = tipoMercaderiaId;
        this.tipoTransporteId = tipoTransporteId;
        this.valorPrimaDeposito = valorPrimaDeposito;
    }


    /**
     * Gets the descripcion value for this TransporteDTO.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this TransporteDTO.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the porcentajePrimaDeposito value for this TransporteDTO.
     * 
     * @return porcentajePrimaDeposito
     */
    public float getPorcentajePrimaDeposito() {
        return porcentajePrimaDeposito;
    }


    /**
     * Sets the porcentajePrimaDeposito value for this TransporteDTO.
     * 
     * @param porcentajePrimaDeposito
     */
    public void setPorcentajePrimaDeposito(float porcentajePrimaDeposito) {
        this.porcentajePrimaDeposito = porcentajePrimaDeposito;
    }


    /**
     * Gets the subRamoTransporte value for this TransporteDTO.
     * 
     * @return subRamoTransporte
     */
    public java.lang.String getSubRamoTransporte() {
        return subRamoTransporte;
    }


    /**
     * Sets the subRamoTransporte value for this TransporteDTO.
     * 
     * @param subRamoTransporte
     */
    public void setSubRamoTransporte(java.lang.String subRamoTransporte) {
        this.subRamoTransporte = subRamoTransporte;
    }


    /**
     * Gets the tipoMercaderiaId value for this TransporteDTO.
     * 
     * @return tipoMercaderiaId
     */
    public java.lang.String getTipoMercaderiaId() {
        return tipoMercaderiaId;
    }


    /**
     * Sets the tipoMercaderiaId value for this TransporteDTO.
     * 
     * @param tipoMercaderiaId
     */
    public void setTipoMercaderiaId(java.lang.String tipoMercaderiaId) {
        this.tipoMercaderiaId = tipoMercaderiaId;
    }


    /**
     * Gets the tipoTransporteId value for this TransporteDTO.
     * 
     * @return tipoTransporteId
     */
    public java.lang.String getTipoTransporteId() {
        return tipoTransporteId;
    }


    /**
     * Sets the tipoTransporteId value for this TransporteDTO.
     * 
     * @param tipoTransporteId
     */
    public void setTipoTransporteId(java.lang.String tipoTransporteId) {
        this.tipoTransporteId = tipoTransporteId;
    }


    /**
     * Gets the valorPrimaDeposito value for this TransporteDTO.
     * 
     * @return valorPrimaDeposito
     */
    public double getValorPrimaDeposito() {
        return valorPrimaDeposito;
    }


    /**
     * Sets the valorPrimaDeposito value for this TransporteDTO.
     * 
     * @param valorPrimaDeposito
     */
    public void setValorPrimaDeposito(double valorPrimaDeposito) {
        this.valorPrimaDeposito = valorPrimaDeposito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransporteDTO)) return false;
        TransporteDTO other = (TransporteDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            this.porcentajePrimaDeposito == other.getPorcentajePrimaDeposito() &&
            ((this.subRamoTransporte==null && other.getSubRamoTransporte()==null) || 
             (this.subRamoTransporte!=null &&
              this.subRamoTransporte.equals(other.getSubRamoTransporte()))) &&
            ((this.tipoMercaderiaId==null && other.getTipoMercaderiaId()==null) || 
             (this.tipoMercaderiaId!=null &&
              this.tipoMercaderiaId.equals(other.getTipoMercaderiaId()))) &&
            ((this.tipoTransporteId==null && other.getTipoTransporteId()==null) || 
             (this.tipoTransporteId!=null &&
              this.tipoTransporteId.equals(other.getTipoTransporteId()))) &&
            this.valorPrimaDeposito == other.getValorPrimaDeposito();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        _hashCode += new Float(getPorcentajePrimaDeposito()).hashCode();
        if (getSubRamoTransporte() != null) {
            _hashCode += getSubRamoTransporte().hashCode();
        }
        if (getTipoMercaderiaId() != null) {
            _hashCode += getTipoMercaderiaId().hashCode();
        }
        if (getTipoTransporteId() != null) {
            _hashCode += getTipoTransporteId().hashCode();
        }
        _hashCode += new Double(getValorPrimaDeposito()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransporteDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "TransporteDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentajePrimaDeposito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentajePrimaDeposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subRamoTransporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subRamoTransporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoMercaderiaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoMercaderiaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoTransporteId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoTransporteId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaDeposito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaDeposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
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
