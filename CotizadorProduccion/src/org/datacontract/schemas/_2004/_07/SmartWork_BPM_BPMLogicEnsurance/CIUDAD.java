/**
 * CIUDAD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class CIUDAD  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String CODIGOSUPER;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.lang.String NOMBRE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA PROVINCIA;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPROVINCIAFG7C7FF7 PROVINCIAReference;

    private java.lang.String USUARIOACTUALIZA;

    public CIUDAD() {
    }

    public CIUDAD(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String CODIGOSUPER,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.lang.String NOMBRE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA PROVINCIA,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPROVINCIAFG7C7FF7 PROVINCIAReference,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.CODIGOSUPER = CODIGOSUPER;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.PROVINCIA = PROVINCIA;
        this.PROVINCIAReference = PROVINCIAReference;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the CODIGOSUPER value for this CIUDAD.
     * 
     * @return CODIGOSUPER
     */
    public java.lang.String getCODIGOSUPER() {
        return CODIGOSUPER;
    }


    /**
     * Sets the CODIGOSUPER value for this CIUDAD.
     * 
     * @param CODIGOSUPER
     */
    public void setCODIGOSUPER(java.lang.String CODIGOSUPER) {
        this.CODIGOSUPER = CODIGOSUPER;
    }


    /**
     * Gets the FECHAACTUALIZA value for this CIUDAD.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this CIUDAD.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this CIUDAD.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this CIUDAD.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NOMBRE value for this CIUDAD.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this CIUDAD.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the PROVINCIA value for this CIUDAD.
     * 
     * @return PROVINCIA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA getPROVINCIA() {
        return PROVINCIA;
    }


    /**
     * Sets the PROVINCIA value for this CIUDAD.
     * 
     * @param PROVINCIA
     */
    public void setPROVINCIA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA PROVINCIA) {
        this.PROVINCIA = PROVINCIA;
    }


    /**
     * Gets the PROVINCIAReference value for this CIUDAD.
     * 
     * @return PROVINCIAReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPROVINCIAFG7C7FF7 getPROVINCIAReference() {
        return PROVINCIAReference;
    }


    /**
     * Sets the PROVINCIAReference value for this CIUDAD.
     * 
     * @param PROVINCIAReference
     */
    public void setPROVINCIAReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPROVINCIAFG7C7FF7 PROVINCIAReference) {
        this.PROVINCIAReference = PROVINCIAReference;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this CIUDAD.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this CIUDAD.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CIUDAD)) return false;
        CIUDAD other = (CIUDAD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.CODIGOSUPER==null && other.getCODIGOSUPER()==null) || 
             (this.CODIGOSUPER!=null &&
              this.CODIGOSUPER.equals(other.getCODIGOSUPER()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.PROVINCIA==null && other.getPROVINCIA()==null) || 
             (this.PROVINCIA!=null &&
              this.PROVINCIA.equals(other.getPROVINCIA()))) &&
            ((this.PROVINCIAReference==null && other.getPROVINCIAReference()==null) || 
             (this.PROVINCIAReference!=null &&
              this.PROVINCIAReference.equals(other.getPROVINCIAReference()))) &&
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
        if (getCODIGOSUPER() != null) {
            _hashCode += getCODIGOSUPER().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getPROVINCIA() != null) {
            _hashCode += getPROVINCIA().hashCode();
        }
        if (getPROVINCIAReference() != null) {
            _hashCode += getPROVINCIAReference().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CIUDAD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOSUPER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CODIGOSUPER"));
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
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROVINCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROVINCIAReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIAReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPROVINCIAFG7c7FF7"));
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
