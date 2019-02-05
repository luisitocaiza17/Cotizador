/**
 * ProductoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ProductoDTO  implements java.io.Serializable {
    private java.lang.String planProducto;

    private java.lang.String plantillaId;

    private java.lang.String productoid;

    public ProductoDTO() {
    }

    public ProductoDTO(
           java.lang.String planProducto,
           java.lang.String plantillaId,
           java.lang.String productoid) {
           this.planProducto = planProducto;
           this.plantillaId = plantillaId;
           this.productoid = productoid;
    }


    /**
     * Gets the planProducto value for this ProductoDTO.
     * 
     * @return planProducto
     */
    public java.lang.String getPlanProducto() {
        return planProducto;
    }


    /**
     * Sets the planProducto value for this ProductoDTO.
     * 
     * @param planProducto
     */
    public void setPlanProducto(java.lang.String planProducto) {
        this.planProducto = planProducto;
    }


    /**
     * Gets the plantillaId value for this ProductoDTO.
     * 
     * @return plantillaId
     */
    public java.lang.String getPlantillaId() {
        return plantillaId;
    }


    /**
     * Sets the plantillaId value for this ProductoDTO.
     * 
     * @param plantillaId
     */
    public void setPlantillaId(java.lang.String plantillaId) {
        this.plantillaId = plantillaId;
    }


    /**
     * Gets the productoid value for this ProductoDTO.
     * 
     * @return productoid
     */
    public java.lang.String getProductoid() {
        return productoid;
    }


    /**
     * Sets the productoid value for this ProductoDTO.
     * 
     * @param productoid
     */
    public void setProductoid(java.lang.String productoid) {
        this.productoid = productoid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProductoDTO)) return false;
        ProductoDTO other = (ProductoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.planProducto==null && other.getPlanProducto()==null) || 
             (this.planProducto!=null &&
              this.planProducto.equals(other.getPlanProducto()))) &&
            ((this.plantillaId==null && other.getPlantillaId()==null) || 
             (this.plantillaId!=null &&
              this.plantillaId.equals(other.getPlantillaId()))) &&
            ((this.productoid==null && other.getProductoid()==null) || 
             (this.productoid!=null &&
              this.productoid.equals(other.getProductoid())));
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
        if (getPlanProducto() != null) {
            _hashCode += getPlanProducto().hashCode();
        }
        if (getPlantillaId() != null) {
            _hashCode += getPlantillaId().hashCode();
        }
        if (getProductoid() != null) {
            _hashCode += getProductoid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProductoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ProductoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("planProducto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "planProducto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plantillaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "plantillaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productoid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productoid"));
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
