/**
 * ENDOSOAGENTE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ENDOSOAGENTE  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ACUERDOID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE AGENTE;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfAGENTEFG7C7FF7 AGENTEReference;

    private java.lang.String ENDOSOID;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.math.BigDecimal PARTICIPACION;

    private java.lang.String TIPOAGENTE;

    private java.lang.String USUARIOACTUALIZA;

    public ENDOSOAGENTE() {
    }

    public ENDOSOAGENTE(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ACUERDOID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE AGENTE,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfAGENTEFG7C7FF7 AGENTEReference,
           java.lang.String ENDOSOID,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.math.BigDecimal PARTICIPACION,
           java.lang.String TIPOAGENTE,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.ACUERDOID = ACUERDOID;
        this.AGENTE = AGENTE;
        this.AGENTEReference = AGENTEReference;
        this.ENDOSOID = ENDOSOID;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.PARTICIPACION = PARTICIPACION;
        this.TIPOAGENTE = TIPOAGENTE;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the ACUERDOID value for this ENDOSOAGENTE.
     * 
     * @return ACUERDOID
     */
    public java.lang.String getACUERDOID() {
        return ACUERDOID;
    }


    /**
     * Sets the ACUERDOID value for this ENDOSOAGENTE.
     * 
     * @param ACUERDOID
     */
    public void setACUERDOID(java.lang.String ACUERDOID) {
        this.ACUERDOID = ACUERDOID;
    }


    /**
     * Gets the AGENTE value for this ENDOSOAGENTE.
     * 
     * @return AGENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE getAGENTE() {
        return AGENTE;
    }


    /**
     * Sets the AGENTE value for this ENDOSOAGENTE.
     * 
     * @param AGENTE
     */
    public void setAGENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE AGENTE) {
        this.AGENTE = AGENTE;
    }


    /**
     * Gets the AGENTEReference value for this ENDOSOAGENTE.
     * 
     * @return AGENTEReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfAGENTEFG7C7FF7 getAGENTEReference() {
        return AGENTEReference;
    }


    /**
     * Sets the AGENTEReference value for this ENDOSOAGENTE.
     * 
     * @param AGENTEReference
     */
    public void setAGENTEReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfAGENTEFG7C7FF7 AGENTEReference) {
        this.AGENTEReference = AGENTEReference;
    }


    /**
     * Gets the ENDOSOID value for this ENDOSOAGENTE.
     * 
     * @return ENDOSOID
     */
    public java.lang.String getENDOSOID() {
        return ENDOSOID;
    }


    /**
     * Sets the ENDOSOID value for this ENDOSOAGENTE.
     * 
     * @param ENDOSOID
     */
    public void setENDOSOID(java.lang.String ENDOSOID) {
        this.ENDOSOID = ENDOSOID;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ENDOSOAGENTE.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ENDOSOAGENTE.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this ENDOSOAGENTE.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ENDOSOAGENTE.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the PARTICIPACION value for this ENDOSOAGENTE.
     * 
     * @return PARTICIPACION
     */
    public java.math.BigDecimal getPARTICIPACION() {
        return PARTICIPACION;
    }


    /**
     * Sets the PARTICIPACION value for this ENDOSOAGENTE.
     * 
     * @param PARTICIPACION
     */
    public void setPARTICIPACION(java.math.BigDecimal PARTICIPACION) {
        this.PARTICIPACION = PARTICIPACION;
    }


    /**
     * Gets the TIPOAGENTE value for this ENDOSOAGENTE.
     * 
     * @return TIPOAGENTE
     */
    public java.lang.String getTIPOAGENTE() {
        return TIPOAGENTE;
    }


    /**
     * Sets the TIPOAGENTE value for this ENDOSOAGENTE.
     * 
     * @param TIPOAGENTE
     */
    public void setTIPOAGENTE(java.lang.String TIPOAGENTE) {
        this.TIPOAGENTE = TIPOAGENTE;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ENDOSOAGENTE.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ENDOSOAGENTE.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENDOSOAGENTE)) return false;
        ENDOSOAGENTE other = (ENDOSOAGENTE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACUERDOID==null && other.getACUERDOID()==null) || 
             (this.ACUERDOID!=null &&
              this.ACUERDOID.equals(other.getACUERDOID()))) &&
            ((this.AGENTE==null && other.getAGENTE()==null) || 
             (this.AGENTE!=null &&
              this.AGENTE.equals(other.getAGENTE()))) &&
            ((this.AGENTEReference==null && other.getAGENTEReference()==null) || 
             (this.AGENTEReference!=null &&
              this.AGENTEReference.equals(other.getAGENTEReference()))) &&
            ((this.ENDOSOID==null && other.getENDOSOID()==null) || 
             (this.ENDOSOID!=null &&
              this.ENDOSOID.equals(other.getENDOSOID()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.PARTICIPACION==null && other.getPARTICIPACION()==null) || 
             (this.PARTICIPACION!=null &&
              this.PARTICIPACION.equals(other.getPARTICIPACION()))) &&
            ((this.TIPOAGENTE==null && other.getTIPOAGENTE()==null) || 
             (this.TIPOAGENTE!=null &&
              this.TIPOAGENTE.equals(other.getTIPOAGENTE()))) &&
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
        if (getACUERDOID() != null) {
            _hashCode += getACUERDOID().hashCode();
        }
        if (getAGENTE() != null) {
            _hashCode += getAGENTE().hashCode();
        }
        if (getAGENTEReference() != null) {
            _hashCode += getAGENTEReference().hashCode();
        }
        if (getENDOSOID() != null) {
            _hashCode += getENDOSOID().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getPARTICIPACION() != null) {
            _hashCode += getPARTICIPACION().hashCode();
        }
        if (getTIPOAGENTE() != null) {
            _hashCode += getTIPOAGENTE().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENDOSOAGENTE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACUERDOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGENTEReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTEReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfAGENTEFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOID"));
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
        elemField.setFieldName("PARTICIPACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PARTICIPACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOAGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOAGENTE"));
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
