/**
 * SdtResponseQBE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sucreagrop;

public class SdtResponseQBE  implements java.io.Serializable {
    private boolean huboErrores;

    private java.lang.String observaciones;

    private sucreagrop.SdtResponseQBEErroresItem[] errores;

    public SdtResponseQBE() {
    }

    public SdtResponseQBE(
           boolean huboErrores,
           java.lang.String observaciones,
           sucreagrop.SdtResponseQBEErroresItem[] errores) {
           this.huboErrores = huboErrores;
           this.observaciones = observaciones;
           this.errores = errores;
    }


    /**
     * Gets the huboErrores value for this SdtResponseQBE.
     * 
     * @return huboErrores
     */
    public boolean isHuboErrores() {
        return huboErrores;
    }


    /**
     * Sets the huboErrores value for this SdtResponseQBE.
     * 
     * @param huboErrores
     */
    public void setHuboErrores(boolean huboErrores) {
        this.huboErrores = huboErrores;
    }


    /**
     * Gets the observaciones value for this SdtResponseQBE.
     * 
     * @return observaciones
     */
    public java.lang.String getObservaciones() {
        return observaciones;
    }


    /**
     * Sets the observaciones value for this SdtResponseQBE.
     * 
     * @param observaciones
     */
    public void setObservaciones(java.lang.String observaciones) {
        this.observaciones = observaciones;
    }


    /**
     * Gets the errores value for this SdtResponseQBE.
     * 
     * @return errores
     */
    public sucreagrop.SdtResponseQBEErroresItem[] getErrores() {
        return errores;
    }


    /**
     * Sets the errores value for this SdtResponseQBE.
     * 
     * @param errores
     */
    public void setErrores(sucreagrop.SdtResponseQBEErroresItem[] errores) {
        this.errores = errores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SdtResponseQBE)) return false;
        SdtResponseQBE other = (SdtResponseQBE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.huboErrores == other.isHuboErrores() &&
            ((this.observaciones==null && other.getObservaciones()==null) || 
             (this.observaciones!=null &&
              this.observaciones.equals(other.getObservaciones()))) &&
            ((this.errores==null && other.getErrores()==null) || 
             (this.errores!=null &&
              java.util.Arrays.equals(this.errores, other.getErrores())));
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
        _hashCode += (isHuboErrores() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getObservaciones() != null) {
            _hashCode += getObservaciones().hashCode();
        }
        if (getErrores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getErrores(), i);
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
        new org.apache.axis.description.TypeDesc(SdtResponseQBE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("sucreagrop", "sdtResponseQBE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("huboErrores");
        elemField.setXmlName(new javax.xml.namespace.QName("sucreagrop", "HuboErrores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("sucreagrop", "Observaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errores");
        elemField.setXmlName(new javax.xml.namespace.QName("sucreagrop", "Errores"));
        elemField.setXmlType(new javax.xml.namespace.QName("sucreagrop", "sdtResponseQBE.ErroresItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("sucreagrop", "ErroresItem"));
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
