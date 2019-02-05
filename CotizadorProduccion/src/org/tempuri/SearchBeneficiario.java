/**
 * SearchBeneficiario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchBeneficiario  implements java.io.Serializable {
    private java.lang.String ramo;

    private java.lang.String parametro;

    public SearchBeneficiario() {
    }

    public SearchBeneficiario(
           java.lang.String ramo,
           java.lang.String parametro) {
           this.ramo = ramo;
           this.parametro = parametro;
    }


    /**
     * Gets the ramo value for this SearchBeneficiario.
     * 
     * @return ramo
     */
    public java.lang.String getRamo() {
        return ramo;
    }


    /**
     * Sets the ramo value for this SearchBeneficiario.
     * 
     * @param ramo
     */
    public void setRamo(java.lang.String ramo) {
        this.ramo = ramo;
    }


    /**
     * Gets the parametro value for this SearchBeneficiario.
     * 
     * @return parametro
     */
    public java.lang.String getParametro() {
        return parametro;
    }


    /**
     * Sets the parametro value for this SearchBeneficiario.
     * 
     * @param parametro
     */
    public void setParametro(java.lang.String parametro) {
        this.parametro = parametro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchBeneficiario)) return false;
        SearchBeneficiario other = (SearchBeneficiario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ramo==null && other.getRamo()==null) || 
             (this.ramo!=null &&
              this.ramo.equals(other.getRamo()))) &&
            ((this.parametro==null && other.getParametro()==null) || 
             (this.parametro!=null &&
              this.parametro.equals(other.getParametro())));
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
        if (getRamo() != null) {
            _hashCode += getRamo().hashCode();
        }
        if (getParametro() != null) {
            _hashCode += getParametro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchBeneficiario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchBeneficiario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ramo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "ramo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "parametro"));
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
