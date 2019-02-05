/**
 * ItemDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ItemDTO  implements java.io.Serializable {
    private java.lang.String claseRiesgoId;

    private java.lang.String id;

    private java.lang.String texto;

    private java.lang.String tipoItemId;

    private java.lang.String tipoRiesgoId;

    private java.math.BigDecimal valorAsegurado;

    public ItemDTO() {
    }

    public ItemDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado) {
           this.claseRiesgoId = claseRiesgoId;
           this.id = id;
           this.texto = texto;
           this.tipoItemId = tipoItemId;
           this.tipoRiesgoId = tipoRiesgoId;
           this.valorAsegurado = valorAsegurado;
    }


    /**
     * Gets the claseRiesgoId value for this ItemDTO.
     * 
     * @return claseRiesgoId
     */
    public java.lang.String getClaseRiesgoId() {
        return claseRiesgoId;
    }


    /**
     * Sets the claseRiesgoId value for this ItemDTO.
     * 
     * @param claseRiesgoId
     */
    public void setClaseRiesgoId(java.lang.String claseRiesgoId) {
        this.claseRiesgoId = claseRiesgoId;
    }


    /**
     * Gets the id value for this ItemDTO.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ItemDTO.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the texto value for this ItemDTO.
     * 
     * @return texto
     */
    public java.lang.String getTexto() {
        return texto;
    }


    /**
     * Sets the texto value for this ItemDTO.
     * 
     * @param texto
     */
    public void setTexto(java.lang.String texto) {
        this.texto = texto;
    }


    /**
     * Gets the tipoItemId value for this ItemDTO.
     * 
     * @return tipoItemId
     */
    public java.lang.String getTipoItemId() {
        return tipoItemId;
    }


    /**
     * Sets the tipoItemId value for this ItemDTO.
     * 
     * @param tipoItemId
     */
    public void setTipoItemId(java.lang.String tipoItemId) {
        this.tipoItemId = tipoItemId;
    }


    /**
     * Gets the tipoRiesgoId value for this ItemDTO.
     * 
     * @return tipoRiesgoId
     */
    public java.lang.String getTipoRiesgoId() {
        return tipoRiesgoId;
    }


    /**
     * Sets the tipoRiesgoId value for this ItemDTO.
     * 
     * @param tipoRiesgoId
     */
    public void setTipoRiesgoId(java.lang.String tipoRiesgoId) {
        this.tipoRiesgoId = tipoRiesgoId;
    }


    /**
     * Gets the valorAsegurado value for this ItemDTO.
     * 
     * @return valorAsegurado
     */
    public java.math.BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }


    /**
     * Sets the valorAsegurado value for this ItemDTO.
     * 
     * @param valorAsegurado
     */
    public void setValorAsegurado(java.math.BigDecimal valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItemDTO)) return false;
        ItemDTO other = (ItemDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.claseRiesgoId==null && other.getClaseRiesgoId()==null) || 
             (this.claseRiesgoId!=null &&
              this.claseRiesgoId.equals(other.getClaseRiesgoId()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.texto==null && other.getTexto()==null) || 
             (this.texto!=null &&
              this.texto.equals(other.getTexto()))) &&
            ((this.tipoItemId==null && other.getTipoItemId()==null) || 
             (this.tipoItemId!=null &&
              this.tipoItemId.equals(other.getTipoItemId()))) &&
            ((this.tipoRiesgoId==null && other.getTipoRiesgoId()==null) || 
             (this.tipoRiesgoId!=null &&
              this.tipoRiesgoId.equals(other.getTipoRiesgoId()))) &&
            ((this.valorAsegurado==null && other.getValorAsegurado()==null) || 
             (this.valorAsegurado!=null &&
              this.valorAsegurado.equals(other.getValorAsegurado())));
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
        if (getClaseRiesgoId() != null) {
            _hashCode += getClaseRiesgoId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getTexto() != null) {
            _hashCode += getTexto().hashCode();
        }
        if (getTipoItemId() != null) {
            _hashCode += getTipoItemId().hashCode();
        }
        if (getTipoRiesgoId() != null) {
            _hashCode += getTipoRiesgoId().hashCode();
        }
        if (getValorAsegurado() != null) {
            _hashCode += getValorAsegurado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ItemDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ItemDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseRiesgoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseRiesgoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("texto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "texto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoItemId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoItemId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRiesgoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRiesgoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAsegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAsegurado"));
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
