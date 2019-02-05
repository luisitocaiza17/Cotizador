/**
 * SearchPaisResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchPaisResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPaisResult;

    public SearchPaisResponse() {
    }

    public SearchPaisResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPaisResult) {
           this.searchPaisResult = searchPaisResult;
    }


    /**
     * Gets the searchPaisResult value for this SearchPaisResponse.
     * 
     * @return searchPaisResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] getSearchPaisResult() {
        return searchPaisResult;
    }


    /**
     * Sets the searchPaisResult value for this SearchPaisResponse.
     * 
     * @param searchPaisResult
     */
    public void setSearchPaisResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPaisResult) {
        this.searchPaisResult = searchPaisResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchPaisResponse)) return false;
        SearchPaisResponse other = (SearchPaisResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchPaisResult==null && other.getSearchPaisResult()==null) || 
             (this.searchPaisResult!=null &&
              java.util.Arrays.equals(this.searchPaisResult, other.getSearchPaisResult())));
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
        if (getSearchPaisResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchPaisResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchPaisResult(), i);
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
        new org.apache.axis.description.TypeDesc(SearchPaisResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchPaisResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchPaisResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchPaisResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS"));
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
