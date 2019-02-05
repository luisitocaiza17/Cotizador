/**
 * SearchCiudadResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchCiudadResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudadResult;

    public SearchCiudadResponse() {
    }

    public SearchCiudadResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudadResult) {
           this.searchCiudadResult = searchCiudadResult;
    }


    /**
     * Gets the searchCiudadResult value for this SearchCiudadResponse.
     * 
     * @return searchCiudadResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] getSearchCiudadResult() {
        return searchCiudadResult;
    }


    /**
     * Sets the searchCiudadResult value for this SearchCiudadResponse.
     * 
     * @param searchCiudadResult
     */
    public void setSearchCiudadResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudadResult) {
        this.searchCiudadResult = searchCiudadResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchCiudadResponse)) return false;
        SearchCiudadResponse other = (SearchCiudadResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchCiudadResult==null && other.getSearchCiudadResult()==null) || 
             (this.searchCiudadResult!=null &&
              java.util.Arrays.equals(this.searchCiudadResult, other.getSearchCiudadResult())));
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
        if (getSearchCiudadResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchCiudadResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchCiudadResult(), i);
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
        new org.apache.axis.description.TypeDesc(SearchCiudadResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchCiudadResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchCiudadResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchCiudadResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
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
