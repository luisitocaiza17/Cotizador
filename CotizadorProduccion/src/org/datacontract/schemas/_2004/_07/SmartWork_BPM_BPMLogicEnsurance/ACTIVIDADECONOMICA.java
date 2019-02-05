/**
 * ACTIVIDADECONOMICA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ACTIVIDADECONOMICA  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ID;

    private java.lang.String NOMBRE;

    private java.lang.String NOMBREPRIMERNIVEL;

    private java.lang.String NOMBRESEGUNDONIVEL;

    private java.lang.String PRIMERNIVEL;

    private java.lang.String SEGUNDONIVEL;

    public ACTIVIDADECONOMICA() {
    }

    public ACTIVIDADECONOMICA(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ID,
           java.lang.String NOMBRE,
           java.lang.String NOMBREPRIMERNIVEL,
           java.lang.String NOMBRESEGUNDONIVEL,
           java.lang.String PRIMERNIVEL,
           java.lang.String SEGUNDONIVEL) {
        super(
            id,
            ref,
            entityKey);
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.NOMBREPRIMERNIVEL = NOMBREPRIMERNIVEL;
        this.NOMBRESEGUNDONIVEL = NOMBRESEGUNDONIVEL;
        this.PRIMERNIVEL = PRIMERNIVEL;
        this.SEGUNDONIVEL = SEGUNDONIVEL;
    }


    /**
     * Gets the ID value for this ACTIVIDADECONOMICA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ACTIVIDADECONOMICA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NOMBRE value for this ACTIVIDADECONOMICA.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this ACTIVIDADECONOMICA.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the NOMBREPRIMERNIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @return NOMBREPRIMERNIVEL
     */
    public java.lang.String getNOMBREPRIMERNIVEL() {
        return NOMBREPRIMERNIVEL;
    }


    /**
     * Sets the NOMBREPRIMERNIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @param NOMBREPRIMERNIVEL
     */
    public void setNOMBREPRIMERNIVEL(java.lang.String NOMBREPRIMERNIVEL) {
        this.NOMBREPRIMERNIVEL = NOMBREPRIMERNIVEL;
    }


    /**
     * Gets the NOMBRESEGUNDONIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @return NOMBRESEGUNDONIVEL
     */
    public java.lang.String getNOMBRESEGUNDONIVEL() {
        return NOMBRESEGUNDONIVEL;
    }


    /**
     * Sets the NOMBRESEGUNDONIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @param NOMBRESEGUNDONIVEL
     */
    public void setNOMBRESEGUNDONIVEL(java.lang.String NOMBRESEGUNDONIVEL) {
        this.NOMBRESEGUNDONIVEL = NOMBRESEGUNDONIVEL;
    }


    /**
     * Gets the PRIMERNIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @return PRIMERNIVEL
     */
    public java.lang.String getPRIMERNIVEL() {
        return PRIMERNIVEL;
    }


    /**
     * Sets the PRIMERNIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @param PRIMERNIVEL
     */
    public void setPRIMERNIVEL(java.lang.String PRIMERNIVEL) {
        this.PRIMERNIVEL = PRIMERNIVEL;
    }


    /**
     * Gets the SEGUNDONIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @return SEGUNDONIVEL
     */
    public java.lang.String getSEGUNDONIVEL() {
        return SEGUNDONIVEL;
    }


    /**
     * Sets the SEGUNDONIVEL value for this ACTIVIDADECONOMICA.
     * 
     * @param SEGUNDONIVEL
     */
    public void setSEGUNDONIVEL(java.lang.String SEGUNDONIVEL) {
        this.SEGUNDONIVEL = SEGUNDONIVEL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACTIVIDADECONOMICA)) return false;
        ACTIVIDADECONOMICA other = (ACTIVIDADECONOMICA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.NOMBREPRIMERNIVEL==null && other.getNOMBREPRIMERNIVEL()==null) || 
             (this.NOMBREPRIMERNIVEL!=null &&
              this.NOMBREPRIMERNIVEL.equals(other.getNOMBREPRIMERNIVEL()))) &&
            ((this.NOMBRESEGUNDONIVEL==null && other.getNOMBRESEGUNDONIVEL()==null) || 
             (this.NOMBRESEGUNDONIVEL!=null &&
              this.NOMBRESEGUNDONIVEL.equals(other.getNOMBRESEGUNDONIVEL()))) &&
            ((this.PRIMERNIVEL==null && other.getPRIMERNIVEL()==null) || 
             (this.PRIMERNIVEL!=null &&
              this.PRIMERNIVEL.equals(other.getPRIMERNIVEL()))) &&
            ((this.SEGUNDONIVEL==null && other.getSEGUNDONIVEL()==null) || 
             (this.SEGUNDONIVEL!=null &&
              this.SEGUNDONIVEL.equals(other.getSEGUNDONIVEL())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getNOMBREPRIMERNIVEL() != null) {
            _hashCode += getNOMBREPRIMERNIVEL().hashCode();
        }
        if (getNOMBRESEGUNDONIVEL() != null) {
            _hashCode += getNOMBRESEGUNDONIVEL().hashCode();
        }
        if (getPRIMERNIVEL() != null) {
            _hashCode += getPRIMERNIVEL().hashCode();
        }
        if (getSEGUNDONIVEL() != null) {
            _hashCode += getSEGUNDONIVEL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACTIVIDADECONOMICA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBREPRIMERNIVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBREPRIMERNIVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRESEGUNDONIVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRESEGUNDONIVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMERNIVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMERNIVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SEGUNDONIVEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SEGUNDONIVEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
