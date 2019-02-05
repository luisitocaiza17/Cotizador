/**
 * SearchEntityDressResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchEntityDressResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDressResult;

    public SearchEntityDressResponse() {
    }

    public SearchEntityDressResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDressResult) {
           this.searchEntityDressResult = searchEntityDressResult;
    }


    /**
     * Gets the searchEntityDressResult value for this SearchEntityDressResponse.
     * 
     * @return searchEntityDressResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION getSearchEntityDressResult() {
        return searchEntityDressResult;
    }


    /**
     * Sets the searchEntityDressResult value for this SearchEntityDressResponse.
     * 
     * @param searchEntityDressResult
     */
    public void setSearchEntityDressResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDressResult) {
        this.searchEntityDressResult = searchEntityDressResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchEntityDressResponse)) return false;
        SearchEntityDressResponse other = (SearchEntityDressResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchEntityDressResult==null && other.getSearchEntityDressResult()==null) || 
             (this.searchEntityDressResult!=null &&
              this.searchEntityDressResult.equals(other.getSearchEntityDressResult())));
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
        if (getSearchEntityDressResult() != null) {
            _hashCode += getSearchEntityDressResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchEntityDressResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchEntityDressResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchEntityDressResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchEntityDressResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADDIRECCION"));
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
