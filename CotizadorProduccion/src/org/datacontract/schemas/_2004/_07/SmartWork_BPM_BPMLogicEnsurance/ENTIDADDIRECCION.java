/**
 * ENTIDADDIRECCION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ENTIDADDIRECCION  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String DIRECCIONID;

    private java.lang.String ENTIDADID;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.lang.String TIPODIRECCIONID;

    private java.lang.String USUARIOACTUALIZA;

    public ENTIDADDIRECCION() {
    }

    public ENTIDADDIRECCION(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String DIRECCIONID,
           java.lang.String ENTIDADID,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.lang.String TIPODIRECCIONID,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.DIRECCIONID = DIRECCIONID;
        this.ENTIDADID = ENTIDADID;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.TIPODIRECCIONID = TIPODIRECCIONID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the DIRECCIONID value for this ENTIDADDIRECCION.
     * 
     * @return DIRECCIONID
     */
    public java.lang.String getDIRECCIONID() {
        return DIRECCIONID;
    }


    /**
     * Sets the DIRECCIONID value for this ENTIDADDIRECCION.
     * 
     * @param DIRECCIONID
     */
    public void setDIRECCIONID(java.lang.String DIRECCIONID) {
        this.DIRECCIONID = DIRECCIONID;
    }


    /**
     * Gets the ENTIDADID value for this ENTIDADDIRECCION.
     * 
     * @return ENTIDADID
     */
    public java.lang.String getENTIDADID() {
        return ENTIDADID;
    }


    /**
     * Sets the ENTIDADID value for this ENTIDADDIRECCION.
     * 
     * @param ENTIDADID
     */
    public void setENTIDADID(java.lang.String ENTIDADID) {
        this.ENTIDADID = ENTIDADID;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ENTIDADDIRECCION.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ENTIDADDIRECCION.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this ENTIDADDIRECCION.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ENTIDADDIRECCION.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the TIPODIRECCIONID value for this ENTIDADDIRECCION.
     * 
     * @return TIPODIRECCIONID
     */
    public java.lang.String getTIPODIRECCIONID() {
        return TIPODIRECCIONID;
    }


    /**
     * Sets the TIPODIRECCIONID value for this ENTIDADDIRECCION.
     * 
     * @param TIPODIRECCIONID
     */
    public void setTIPODIRECCIONID(java.lang.String TIPODIRECCIONID) {
        this.TIPODIRECCIONID = TIPODIRECCIONID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ENTIDADDIRECCION.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ENTIDADDIRECCION.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENTIDADDIRECCION)) return false;
        ENTIDADDIRECCION other = (ENTIDADDIRECCION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.DIRECCIONID==null && other.getDIRECCIONID()==null) || 
             (this.DIRECCIONID!=null &&
              this.DIRECCIONID.equals(other.getDIRECCIONID()))) &&
            ((this.ENTIDADID==null && other.getENTIDADID()==null) || 
             (this.ENTIDADID!=null &&
              this.ENTIDADID.equals(other.getENTIDADID()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.TIPODIRECCIONID==null && other.getTIPODIRECCIONID()==null) || 
             (this.TIPODIRECCIONID!=null &&
              this.TIPODIRECCIONID.equals(other.getTIPODIRECCIONID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA())));
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
        if (getDIRECCIONID() != null) {
            _hashCode += getDIRECCIONID().hashCode();
        }
        if (getENTIDADID() != null) {
            _hashCode += getENTIDADID().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getTIPODIRECCIONID() != null) {
            _hashCode += getTIPODIRECCIONID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENTIDADDIRECCION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADDIRECCION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIRECCIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCIONID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDADID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAACTUALIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPODIRECCIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPODIRECCIONID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIOACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOACTUALIZA"));
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
