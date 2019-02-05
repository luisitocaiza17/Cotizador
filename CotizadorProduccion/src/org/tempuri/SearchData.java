/**
 * SearchData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchData  implements java.io.Serializable {
    private java.lang.String parametro;

    private java.lang.Integer tipoBusqueda;

    private java.lang.String ramo;

    public SearchData() {
    }

    public SearchData(
           java.lang.String parametro,
           java.lang.Integer tipoBusqueda,
           java.lang.String ramo) {
           this.parametro = parametro;
           this.tipoBusqueda = tipoBusqueda;
           this.ramo = ramo;
    }


    /**
     * Gets the parametro value for this SearchData.
     * 
     * @return parametro
     */
    public java.lang.String getParametro() {
        return parametro;
    }


    /**
     * Sets the parametro value for this SearchData.
     * 
     * @param parametro
     */
    public void setParametro(java.lang.String parametro) {
        this.parametro = parametro;
    }


    /**
     * Gets the tipoBusqueda value for this SearchData.
     * 
     * @return tipoBusqueda
     */
    public java.lang.Integer getTipoBusqueda() {
        return tipoBusqueda;
    }


    /**
     * Sets the tipoBusqueda value for this SearchData.
     * 
     * @param tipoBusqueda
     */
    public void setTipoBusqueda(java.lang.Integer tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }


    /**
     * Gets the ramo value for this SearchData.
     * 
     * @return ramo
     */
    public java.lang.String getRamo() {
        return ramo;
    }


    /**
     * Sets the ramo value for this SearchData.
     * 
     * @param ramo
     */
    public void setRamo(java.lang.String ramo) {
        this.ramo = ramo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchData)) return false;
        SearchData other = (SearchData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametro==null && other.getParametro()==null) || 
             (this.parametro!=null &&
              this.parametro.equals(other.getParametro()))) &&
            ((this.tipoBusqueda==null && other.getTipoBusqueda()==null) || 
             (this.tipoBusqueda!=null &&
              this.tipoBusqueda.equals(other.getTipoBusqueda()))) &&
            ((this.ramo==null && other.getRamo()==null) || 
             (this.ramo!=null &&
              this.ramo.equals(other.getRamo())));
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
        if (getParametro() != null) {
            _hashCode += getParametro().hashCode();
        }
        if (getTipoBusqueda() != null) {
            _hashCode += getTipoBusqueda().hashCode();
        }
        if (getRamo() != null) {
            _hashCode += getRamo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "Parametro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoBusqueda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "TipoBusqueda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ramo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
