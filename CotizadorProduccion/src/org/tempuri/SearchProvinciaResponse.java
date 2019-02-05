/**
 * SearchProvinciaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchProvinciaResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvinciaResult;

    public SearchProvinciaResponse() {
    }

    public SearchProvinciaResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvinciaResult) {
           this.searchProvinciaResult = searchProvinciaResult;
    }


    /**
     * Gets the searchProvinciaResult value for this SearchProvinciaResponse.
     * 
     * @return searchProvinciaResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] getSearchProvinciaResult() {
        return searchProvinciaResult;
    }


    /**
     * Sets the searchProvinciaResult value for this SearchProvinciaResponse.
     * 
     * @param searchProvinciaResult
     */
    public void setSearchProvinciaResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvinciaResult) {
        this.searchProvinciaResult = searchProvinciaResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchProvinciaResponse)) return false;
        SearchProvinciaResponse other = (SearchProvinciaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchProvinciaResult==null && other.getSearchProvinciaResult()==null) || 
             (this.searchProvinciaResult!=null &&
              java.util.Arrays.equals(this.searchProvinciaResult, other.getSearchProvinciaResult())));
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
        if (getSearchProvinciaResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchProvinciaResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSearchProvinciaResult(), i);
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
        new org.apache.axis.description.TypeDesc(SearchProvinciaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SearchProvinciaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchProvinciaResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchProvinciaResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
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
