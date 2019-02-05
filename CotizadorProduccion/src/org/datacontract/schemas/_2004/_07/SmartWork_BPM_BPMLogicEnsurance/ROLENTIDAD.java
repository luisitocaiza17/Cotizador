/**
 * ROLENTIDAD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ROLENTIDAD  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.TIPOROL TIPOROL;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfTIPOROLFG7C7FF7 TIPOROLReference;

    private java.lang.String USUARIOACTUALIZA;

    public ROLENTIDAD() {
    }

    public ROLENTIDAD(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.TIPOROL TIPOROL,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfTIPOROLFG7C7FF7 TIPOROLReference,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.ROLENTIDADACUERDO = ROLENTIDADACUERDO;
        this.TIPOROL = TIPOROL;
        this.TIPOROLReference = TIPOROLReference;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the ENTIDAD value for this ROLENTIDAD.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this ROLENTIDAD.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this ROLENTIDAD.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this ROLENTIDAD.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ROLENTIDAD.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ROLENTIDAD.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this ROLENTIDAD.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ROLENTIDAD.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the ROLENTIDADACUERDO value for this ROLENTIDAD.
     * 
     * @return ROLENTIDADACUERDO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] getROLENTIDADACUERDO() {
        return ROLENTIDADACUERDO;
    }


    /**
     * Sets the ROLENTIDADACUERDO value for this ROLENTIDAD.
     * 
     * @param ROLENTIDADACUERDO
     */
    public void setROLENTIDADACUERDO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO) {
        this.ROLENTIDADACUERDO = ROLENTIDADACUERDO;
    }


    /**
     * Gets the TIPOROL value for this ROLENTIDAD.
     * 
     * @return TIPOROL
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.TIPOROL getTIPOROL() {
        return TIPOROL;
    }


    /**
     * Sets the TIPOROL value for this ROLENTIDAD.
     * 
     * @param TIPOROL
     */
    public void setTIPOROL(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.TIPOROL TIPOROL) {
        this.TIPOROL = TIPOROL;
    }


    /**
     * Gets the TIPOROLReference value for this ROLENTIDAD.
     * 
     * @return TIPOROLReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfTIPOROLFG7C7FF7 getTIPOROLReference() {
        return TIPOROLReference;
    }


    /**
     * Sets the TIPOROLReference value for this ROLENTIDAD.
     * 
     * @param TIPOROLReference
     */
    public void setTIPOROLReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfTIPOROLFG7C7FF7 TIPOROLReference) {
        this.TIPOROLReference = TIPOROLReference;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ROLENTIDAD.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ROLENTIDAD.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROLENTIDAD)) return false;
        ROLENTIDAD other = (ROLENTIDAD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.ROLENTIDADACUERDO==null && other.getROLENTIDADACUERDO()==null) || 
             (this.ROLENTIDADACUERDO!=null &&
              java.util.Arrays.equals(this.ROLENTIDADACUERDO, other.getROLENTIDADACUERDO()))) &&
            ((this.TIPOROL==null && other.getTIPOROL()==null) || 
             (this.TIPOROL!=null &&
              this.TIPOROL.equals(other.getTIPOROL()))) &&
            ((this.TIPOROLReference==null && other.getTIPOROLReference()==null) || 
             (this.TIPOROLReference!=null &&
              this.TIPOROLReference.equals(other.getTIPOROLReference()))) &&
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
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getROLENTIDADACUERDO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getROLENTIDADACUERDO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getROLENTIDADACUERDO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTIPOROL() != null) {
            _hashCode += getTIPOROL().hashCode();
        }
        if (getTIPOROLReference() != null) {
            _hashCode += getTIPOROLReference().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROLENTIDAD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDADReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENTIDADFG7c7FF7"));
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
        elemField.setFieldName("ROLENTIDADACUERDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOROL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOROL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOROL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOROLReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOROLReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfTIPOROLFG7c7FF7"));
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
