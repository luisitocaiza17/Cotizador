/**
 * ErrorUN01.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sucreagrop;

public class ErrorUN01  implements java.io.Serializable {
    private java.lang.String codigoTramite;

    private java.lang.String[] errorB;

    public ErrorUN01() {
    }

    public ErrorUN01(
           java.lang.String codigoTramite,
           java.lang.String[] errorB) {
           this.codigoTramite = codigoTramite;
           this.errorB = errorB;
    }


    /**
     * Gets the codigoTramite value for this ErrorUN01.
     * 
     * @return codigoTramite
     */
    public java.lang.String getCodigoTramite() {
        return codigoTramite;
    }


    /**
     * Sets the codigoTramite value for this ErrorUN01.
     * 
     * @param codigoTramite
     */
    public void setCodigoTramite(java.lang.String codigoTramite) {
        this.codigoTramite = codigoTramite;
    }


    /**
     * Gets the errorB value for this ErrorUN01.
     * 
     * @return errorB
     */
    public java.lang.String[] getErrorB() {
        return errorB;
    }


    /**
     * Sets the errorB value for this ErrorUN01.
     * 
     * @param errorB
     */
    public void setErrorB(java.lang.String[] errorB) {
        this.errorB = errorB;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ErrorUN01)) return false;
        ErrorUN01 other = (ErrorUN01) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoTramite==null && other.getCodigoTramite()==null) || 
             (this.codigoTramite!=null &&
              this.codigoTramite.equals(other.getCodigoTramite()))) &&
            ((this.errorB==null && other.getErrorB()==null) || 
             (this.errorB!=null &&
              java.util.Arrays.equals(this.errorB, other.getErrorB())));
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
        if (getCodigoTramite() != null) {
            _hashCode += getCodigoTramite().hashCode();
        }
        if (getErrorB() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrorB());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrorB(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ErrorUN01.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("sucreagrop", "ErrorUN01"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("sucreagrop", "CodigoTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorB");
        elemField.setXmlName(new javax.xml.namespace.QName("sucreagrop", "ErrorB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("sucreagrop", "item"));
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
