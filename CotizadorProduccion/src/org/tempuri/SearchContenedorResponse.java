/**
 * SearchContenedorResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchContenedorResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedorResult;

    public SearchContenedorResponse() {
    }

    public SearchContenedorResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedorResult) {
           this.searchContenedorResult = searchContenedorResult;
    }


    /**
     * Gets the searchContenedorResult value for this SearchContenedorResponse.
     * 
     * @return searchContenedorResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA getSearchContenedorResult() {
        return searchContenedorResult;
    }


    /**
     * Sets the searchContenedorResult value for this SearchContenedorResponse.
     * 
     * @param searchContenedorResult
     */
    public void setSearchContenedorResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedorResult) {
        this.searchContenedorResult = searchContenedorResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchContenedorResponse)) return false;
        SearchContenedorResponse other = (SearchContenedorResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchContenedorResult==null && other.getSearchContenedorResult()==null) || 
             (this.searchContenedorResult!=null &&
              this.searchContenedorResult.equals(other.getSearchContenedorResult())));
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
        if (getSearchContenedorResult() != null) {
            _hashCode += getSearchContenedorResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchContenedorResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchContenedorResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchContenedorResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchContenedorResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
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
