/**
 * SearchStateOrdenPagoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SearchStateOrdenPagoResponse  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPagoResult;

    public SearchStateOrdenPagoResponse() {
    }

    public SearchStateOrdenPagoResponse(
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPagoResult) {
           this.searchStateOrdenPagoResult = searchStateOrdenPagoResult;
    }


    /**
     * Gets the searchStateOrdenPagoResult value for this SearchStateOrdenPagoResponse.
     * 
     * @return searchStateOrdenPagoResult
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO getSearchStateOrdenPagoResult() {
        return searchStateOrdenPagoResult;
    }


    /**
     * Sets the searchStateOrdenPagoResult value for this SearchStateOrdenPagoResponse.
     * 
     * @param searchStateOrdenPagoResult
     */
    public void setSearchStateOrdenPagoResult(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPagoResult) {
        this.searchStateOrdenPagoResult = searchStateOrdenPagoResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SearchStateOrdenPagoResponse)) return false;
        SearchStateOrdenPagoResponse other = (SearchStateOrdenPagoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.searchStateOrdenPagoResult==null && other.getSearchStateOrdenPagoResult()==null) || 
             (this.searchStateOrdenPagoResult!=null &&
              this.searchStateOrdenPagoResult.equals(other.getSearchStateOrdenPagoResult())));
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
        if (getSearchStateOrdenPagoResult() != null) {
            _hashCode += getSearchStateOrdenPagoResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SearchStateOrdenPagoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">searchStateOrdenPagoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchStateOrdenPagoResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "searchStateOrdenPagoResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
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
