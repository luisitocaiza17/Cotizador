/**
 * SdtResponseQBEErroresItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package SucreAgro7;

public class SdtResponseQBEErroresItem  implements java.io.Serializable {
    private SucreAgro7.ErrorUN01 error;

    public SdtResponseQBEErroresItem() {
    }

    public SdtResponseQBEErroresItem(
           SucreAgro7.ErrorUN01 error) {
           this.error = error;
    }


    /**
     * Gets the error value for this SdtResponseQBEErroresItem.
     * 
     * @return error
     */
    public SucreAgro7.ErrorUN01 getError() {
        return error;
    }


    /**
     * Sets the error value for this SdtResponseQBEErroresItem.
     * 
     * @param error
     */
    public void setError(SucreAgro7.ErrorUN01 error) {
        this.error = error;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SdtResponseQBEErroresItem)) return false;
        SdtResponseQBEErroresItem other = (SdtResponseQBEErroresItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError())));
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SdtResponseQBEErroresItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("SucreAgro7", "sdtResponseQBE.ErroresItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("SucreAgro7", "Error"));
        elemField.setXmlType(new javax.xml.namespace.QName("SucreAgro7", "ErrorUN01"));
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