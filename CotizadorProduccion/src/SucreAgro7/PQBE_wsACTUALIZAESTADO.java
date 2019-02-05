/**
 * PQBE_wsACTUALIZAESTADO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package SucreAgro7;

public class PQBE_wsACTUALIZAESTADO  implements java.io.Serializable {
    private java.lang.String codigotramite;

    private SucreAgro7.SdtEstado estado;

    public PQBE_wsACTUALIZAESTADO() {
    }

    public PQBE_wsACTUALIZAESTADO(
           java.lang.String codigotramite,
           SucreAgro7.SdtEstado estado) {
           this.codigotramite = codigotramite;
           this.estado = estado;
    }


    /**
     * Gets the codigotramite value for this PQBE_wsACTUALIZAESTADO.
     * 
     * @return codigotramite
     */
    public java.lang.String getCodigotramite() {
        return codigotramite;
    }


    /**
     * Sets the codigotramite value for this PQBE_wsACTUALIZAESTADO.
     * 
     * @param codigotramite
     */
    public void setCodigotramite(java.lang.String codigotramite) {
        this.codigotramite = codigotramite;
    }


    /**
     * Gets the estado value for this PQBE_wsACTUALIZAESTADO.
     * 
     * @return estado
     */
    public SucreAgro7.SdtEstado getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this PQBE_wsACTUALIZAESTADO.
     * 
     * @param estado
     */
    public void setEstado(SucreAgro7.SdtEstado estado) {
        this.estado = estado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PQBE_wsACTUALIZAESTADO)) return false;
        PQBE_wsACTUALIZAESTADO other = (PQBE_wsACTUALIZAESTADO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigotramite==null && other.getCodigotramite()==null) || 
             (this.codigotramite!=null &&
              this.codigotramite.equals(other.getCodigotramite()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado())));
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
        if (getCodigotramite() != null) {
            _hashCode += getCodigotramite().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PQBE_wsACTUALIZAESTADO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("SucreAgro7", ">pQBE_ws.ACTUALIZAESTADO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigotramite");
        elemField.setXmlName(new javax.xml.namespace.QName("SucreAgro7", "Codigotramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("SucreAgro7", "Estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("SucreAgro7", "sdtEstado"));
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
