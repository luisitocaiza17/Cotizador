/**
 * EnsuranceEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class EnsuranceEntity  implements java.io.Serializable {
    private boolean esActualizacion;

    private boolean esNuevo;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION d;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD e;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION ed;

    public EnsuranceEntity() {
    }

    public EnsuranceEntity(
           boolean esActualizacion,
           boolean esNuevo,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION d,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD e,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION ed) {
           this.esActualizacion = esActualizacion;
           this.esNuevo = esNuevo;
           this.d = d;
           this.e = e;
           this.ed = ed;
    }


    /**
     * Gets the esActualizacion value for this EnsuranceEntity.
     * 
     * @return esActualizacion
     */
    public boolean isEsActualizacion() {
        return esActualizacion;
    }


    /**
     * Sets the esActualizacion value for this EnsuranceEntity.
     * 
     * @param esActualizacion
     */
    public void setEsActualizacion(boolean esActualizacion) {
        this.esActualizacion = esActualizacion;
    }


    /**
     * Gets the esNuevo value for this EnsuranceEntity.
     * 
     * @return esNuevo
     */
    public boolean isEsNuevo() {
        return esNuevo;
    }


    /**
     * Sets the esNuevo value for this EnsuranceEntity.
     * 
     * @param esNuevo
     */
    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }


    /**
     * Gets the d value for this EnsuranceEntity.
     * 
     * @return d
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION getD() {
        return d;
    }


    /**
     * Sets the d value for this EnsuranceEntity.
     * 
     * @param d
     */
    public void setD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION d) {
        this.d = d;
    }


    /**
     * Gets the e value for this EnsuranceEntity.
     * 
     * @return e
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getE() {
        return e;
    }


    /**
     * Sets the e value for this EnsuranceEntity.
     * 
     * @param e
     */
    public void setE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD e) {
        this.e = e;
    }


    /**
     * Gets the ed value for this EnsuranceEntity.
     * 
     * @return ed
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION getEd() {
        return ed;
    }


    /**
     * Sets the ed value for this EnsuranceEntity.
     * 
     * @param ed
     */
    public void setEd(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION ed) {
        this.ed = ed;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EnsuranceEntity)) return false;
        EnsuranceEntity other = (EnsuranceEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.esActualizacion == other.isEsActualizacion() &&
            this.esNuevo == other.isEsNuevo() &&
            ((this.d==null && other.getD()==null) || 
             (this.d!=null &&
              this.d.equals(other.getD()))) &&
            ((this.e==null && other.getE()==null) || 
             (this.e!=null &&
              this.e.equals(other.getE()))) &&
            ((this.ed==null && other.getEd()==null) || 
             (this.ed!=null &&
              this.ed.equals(other.getEd())));
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
        _hashCode += (isEsActualizacion() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isEsNuevo() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getD() != null) {
            _hashCode += getD().hashCode();
        }
        if (getE() != null) {
            _hashCode += getE().hashCode();
        }
        if (getEd() != null) {
            _hashCode += getEd().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EnsuranceEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EnsuranceEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esActualizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EsActualizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esNuevo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EsNuevo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("d");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "d"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("e");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "e"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADDIRECCION"));
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
