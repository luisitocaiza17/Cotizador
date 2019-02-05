/**
 * SearchOrdenPagoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchOrdenPagoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPagoResult;

    public SearchOrdenPagoResponse() {
    }

    public SearchOrdenPagoResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPagoResult) {
           this.searchOrdenPagoResult = searchOrdenPagoResult;
    }


    /**
     * Gets the searchOrdenPagoResult value for this SearchOrdenPagoResponse.
     * 
     * @return searchOrdenPagoResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO getSearchOrdenPagoResult() {
        return searchOrdenPagoResult;
    }


    /**
     * Sets the searchOrdenPagoResult value for this SearchOrdenPagoResponse.
     * 
     * @param searchOrdenPagoResult
     */
    public void setSearchOrdenPagoResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPagoResult) {
        this.searchOrdenPagoResult = searchOrdenPagoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchOrdenPagoResponse)) return false;
        SearchOrdenPagoResponse other = (SearchOrdenPagoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchOrdenPagoResult==null && other.getSearchOrdenPagoResult()==null) || 
             (this.searchOrdenPagoResult!=null &&
              this.searchOrdenPagoResult.equals(other.getSearchOrdenPagoResult())));
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
        if (getSearchOrdenPagoResult() != null) {
            _hashCode += getSearchOrdenPagoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchOrdenPagoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">searchOrdenPagoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchOrdenPagoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "searchOrdenPagoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO"));
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
