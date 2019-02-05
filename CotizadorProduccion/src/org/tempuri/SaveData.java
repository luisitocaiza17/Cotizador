/**
 * SaveData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SaveData  implements java.io.Serializable {
    private java.lang.String rowID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj;

    public SaveData() {
    }

    public SaveData(
           java.lang.String rowID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj) {
           this.rowID = rowID;
           this.rq = rq;
           this.obj = obj;
    }


    /**
     * Gets the rowID value for this SaveData.
     * 
     * @return rowID
     */
    public java.lang.String getRowID() {
        return rowID;
    }


    /**
     * Sets the rowID value for this SaveData.
     * 
     * @param rowID
     */
    public void setRowID(java.lang.String rowID) {
        this.rowID = rowID;
    }


    /**
     * Gets the rq value for this SaveData.
     * 
     * @return rq
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket getRq() {
        return rq;
    }


    /**
     * Sets the rq value for this SaveData.
     * 
     * @param rq
     */
    public void setRq(org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq) {
        this.rq = rq;
    }


    /**
     * Gets the obj value for this SaveData.
     * 
     * @return obj
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity getObj() {
        return obj;
    }


    /**
     * Sets the obj value for this SaveData.
     * 
     * @param obj
     */
    public void setObj(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj) {
        this.obj = obj;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaveData)) return false;
        SaveData other = (SaveData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rowID==null && other.getRowID()==null) || 
             (this.rowID!=null &&
              this.rowID.equals(other.getRowID()))) &&
            ((this.rq==null && other.getRq()==null) || 
             (this.rq!=null &&
              this.rq.equals(other.getRq()))) &&
            ((this.obj==null && other.getObj()==null) || 
             (this.obj!=null &&
              this.obj.equals(other.getObj())));
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
        if (getRowID() != null) {
            _hashCode += getRowID().hashCode();
        }
        if (getRq() != null) {
            _hashCode += getRq().hashCode();
        }
        if (getObj() != null) {
            _hashCode += getObj().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaveData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">SaveData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "RowID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "rq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "RequestTicket"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("obj");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "obj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EnsuranceEntity"));
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
