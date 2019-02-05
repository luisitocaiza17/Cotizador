/**
 * ROLENTIDADACUERDO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ROLENTIDADACUERDO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO ACUERDO;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfACUERDOFG7C7FF7 ACUERDOReference;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHACREACION;

    private java.lang.String ID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD ROLENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfROLENTIDADFG7C7FF7 ROLENTIDADReference;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String USUARIOCREACION;

    public ROLENTIDADACUERDO() {
    }

    public ROLENTIDADACUERDO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO ACUERDO,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfACUERDOFG7C7FF7 ACUERDOReference,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHACREACION,
           java.lang.String ID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD ROLENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfROLENTIDADFG7C7FF7 ROLENTIDADReference,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String USUARIOCREACION) {
        super(
            id,
            ref,
            entityKey);
        this.ACUERDO = ACUERDO;
        this.ACUERDOReference = ACUERDOReference;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHACREACION = FECHACREACION;
        this.ID = ID;
        this.ROLENTIDAD = ROLENTIDAD;
        this.ROLENTIDADReference = ROLENTIDADReference;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.USUARIOCREACION = USUARIOCREACION;
    }


    /**
     * Gets the ACUERDO value for this ROLENTIDADACUERDO.
     * 
     * @return ACUERDO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO getACUERDO() {
        return ACUERDO;
    }


    /**
     * Sets the ACUERDO value for this ROLENTIDADACUERDO.
     * 
     * @param ACUERDO
     */
    public void setACUERDO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO ACUERDO) {
        this.ACUERDO = ACUERDO;
    }


    /**
     * Gets the ACUERDOReference value for this ROLENTIDADACUERDO.
     * 
     * @return ACUERDOReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfACUERDOFG7C7FF7 getACUERDOReference() {
        return ACUERDOReference;
    }


    /**
     * Sets the ACUERDOReference value for this ROLENTIDADACUERDO.
     * 
     * @param ACUERDOReference
     */
    public void setACUERDOReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfACUERDOFG7C7FF7 ACUERDOReference) {
        this.ACUERDOReference = ACUERDOReference;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ROLENTIDADACUERDO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ROLENTIDADACUERDO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHACREACION value for this ROLENTIDADACUERDO.
     * 
     * @return FECHACREACION
     */
    public java.util.Calendar getFECHACREACION() {
        return FECHACREACION;
    }


    /**
     * Sets the FECHACREACION value for this ROLENTIDADACUERDO.
     * 
     * @param FECHACREACION
     */
    public void setFECHACREACION(java.util.Calendar FECHACREACION) {
        this.FECHACREACION = FECHACREACION;
    }


    /**
     * Gets the ID value for this ROLENTIDADACUERDO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ROLENTIDADACUERDO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the ROLENTIDAD value for this ROLENTIDADACUERDO.
     * 
     * @return ROLENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD getROLENTIDAD() {
        return ROLENTIDAD;
    }


    /**
     * Sets the ROLENTIDAD value for this ROLENTIDADACUERDO.
     * 
     * @param ROLENTIDAD
     */
    public void setROLENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD ROLENTIDAD) {
        this.ROLENTIDAD = ROLENTIDAD;
    }


    /**
     * Gets the ROLENTIDADReference value for this ROLENTIDADACUERDO.
     * 
     * @return ROLENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfROLENTIDADFG7C7FF7 getROLENTIDADReference() {
        return ROLENTIDADReference;
    }


    /**
     * Sets the ROLENTIDADReference value for this ROLENTIDADACUERDO.
     * 
     * @param ROLENTIDADReference
     */
    public void setROLENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfROLENTIDADFG7C7FF7 ROLENTIDADReference) {
        this.ROLENTIDADReference = ROLENTIDADReference;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ROLENTIDADACUERDO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ROLENTIDADACUERDO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the USUARIOCREACION value for this ROLENTIDADACUERDO.
     * 
     * @return USUARIOCREACION
     */
    public java.lang.String getUSUARIOCREACION() {
        return USUARIOCREACION;
    }


    /**
     * Sets the USUARIOCREACION value for this ROLENTIDADACUERDO.
     * 
     * @param USUARIOCREACION
     */
    public void setUSUARIOCREACION(java.lang.String USUARIOCREACION) {
        this.USUARIOCREACION = USUARIOCREACION;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ROLENTIDADACUERDO)) return false;
        ROLENTIDADACUERDO other = (ROLENTIDADACUERDO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACUERDO==null && other.getACUERDO()==null) || 
             (this.ACUERDO!=null &&
              this.ACUERDO.equals(other.getACUERDO()))) &&
            ((this.ACUERDOReference==null && other.getACUERDOReference()==null) || 
             (this.ACUERDOReference!=null &&
              this.ACUERDOReference.equals(other.getACUERDOReference()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHACREACION==null && other.getFECHACREACION()==null) || 
             (this.FECHACREACION!=null &&
              this.FECHACREACION.equals(other.getFECHACREACION()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.ROLENTIDAD==null && other.getROLENTIDAD()==null) || 
             (this.ROLENTIDAD!=null &&
              this.ROLENTIDAD.equals(other.getROLENTIDAD()))) &&
            ((this.ROLENTIDADReference==null && other.getROLENTIDADReference()==null) || 
             (this.ROLENTIDADReference!=null &&
              this.ROLENTIDADReference.equals(other.getROLENTIDADReference()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.USUARIOCREACION==null && other.getUSUARIOCREACION()==null) || 
             (this.USUARIOCREACION!=null &&
              this.USUARIOCREACION.equals(other.getUSUARIOCREACION())));
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
        if (getACUERDO() != null) {
            _hashCode += getACUERDO().hashCode();
        }
        if (getACUERDOReference() != null) {
            _hashCode += getACUERDOReference().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHACREACION() != null) {
            _hashCode += getFECHACREACION().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getROLENTIDAD() != null) {
            _hashCode += getROLENTIDAD().hashCode();
        }
        if (getROLENTIDADReference() != null) {
            _hashCode += getROLENTIDADReference().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getUSUARIOCREACION() != null) {
            _hashCode += getUSUARIOCREACION().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ROLENTIDADACUERDO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACUERDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACUERDOReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDOReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfACUERDOFG7c7FF7"));
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
        elemField.setFieldName("FECHACREACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACREACION"));
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
        elemField.setFieldName("ROLENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROLENTIDADReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfROLENTIDADFG7c7FF7"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIOCREACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOCREACION"));
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
