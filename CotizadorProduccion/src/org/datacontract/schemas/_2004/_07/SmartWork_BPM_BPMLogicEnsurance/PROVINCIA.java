/**
 * PROVINCIA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class PROVINCIA  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] CANTON;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] CIUDAD;

    private java.lang.String CODIGOSUPER;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.lang.String NEMOTECNICO;

    private java.lang.String NOMBRE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS PAIS;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPAISFG7C7FF7 PAISReference;

    private java.lang.String USUARIOACTUALIZA;

    public PROVINCIA() {
    }

    public PROVINCIA(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] CANTON,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] CIUDAD,
           java.lang.String CODIGOSUPER,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.lang.String NEMOTECNICO,
           java.lang.String NOMBRE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS PAIS,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPAISFG7C7FF7 PAISReference,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.CANTON = CANTON;
        this.CIUDAD = CIUDAD;
        this.CODIGOSUPER = CODIGOSUPER;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.NEMOTECNICO = NEMOTECNICO;
        this.NOMBRE = NOMBRE;
        this.PAIS = PAIS;
        this.PAISReference = PAISReference;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the CANTON value for this PROVINCIA.
     * 
     * @return CANTON
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] getCANTON() {
        return CANTON;
    }


    /**
     * Sets the CANTON value for this PROVINCIA.
     * 
     * @param CANTON
     */
    public void setCANTON(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] CANTON) {
        this.CANTON = CANTON;
    }


    /**
     * Gets the CIUDAD value for this PROVINCIA.
     * 
     * @return CIUDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] getCIUDAD() {
        return CIUDAD;
    }


    /**
     * Sets the CIUDAD value for this PROVINCIA.
     * 
     * @param CIUDAD
     */
    public void setCIUDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] CIUDAD) {
        this.CIUDAD = CIUDAD;
    }


    /**
     * Gets the CODIGOSUPER value for this PROVINCIA.
     * 
     * @return CODIGOSUPER
     */
    public java.lang.String getCODIGOSUPER() {
        return CODIGOSUPER;
    }


    /**
     * Sets the CODIGOSUPER value for this PROVINCIA.
     * 
     * @param CODIGOSUPER
     */
    public void setCODIGOSUPER(java.lang.String CODIGOSUPER) {
        this.CODIGOSUPER = CODIGOSUPER;
    }


    /**
     * Gets the FECHAACTUALIZA value for this PROVINCIA.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this PROVINCIA.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this PROVINCIA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this PROVINCIA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NEMOTECNICO value for this PROVINCIA.
     * 
     * @return NEMOTECNICO
     */
    public java.lang.String getNEMOTECNICO() {
        return NEMOTECNICO;
    }


    /**
     * Sets the NEMOTECNICO value for this PROVINCIA.
     * 
     * @param NEMOTECNICO
     */
    public void setNEMOTECNICO(java.lang.String NEMOTECNICO) {
        this.NEMOTECNICO = NEMOTECNICO;
    }


    /**
     * Gets the NOMBRE value for this PROVINCIA.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this PROVINCIA.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the PAIS value for this PROVINCIA.
     * 
     * @return PAIS
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS getPAIS() {
        return PAIS;
    }


    /**
     * Sets the PAIS value for this PROVINCIA.
     * 
     * @param PAIS
     */
    public void setPAIS(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS PAIS) {
        this.PAIS = PAIS;
    }


    /**
     * Gets the PAISReference value for this PROVINCIA.
     * 
     * @return PAISReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPAISFG7C7FF7 getPAISReference() {
        return PAISReference;
    }


    /**
     * Sets the PAISReference value for this PROVINCIA.
     * 
     * @param PAISReference
     */
    public void setPAISReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPAISFG7C7FF7 PAISReference) {
        this.PAISReference = PAISReference;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this PROVINCIA.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this PROVINCIA.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PROVINCIA)) return false;
        PROVINCIA other = (PROVINCIA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.CANTON==null && other.getCANTON()==null) || 
             (this.CANTON!=null &&
              java.util.Arrays.equals(this.CANTON, other.getCANTON()))) &&
            ((this.CIUDAD==null && other.getCIUDAD()==null) || 
             (this.CIUDAD!=null &&
              java.util.Arrays.equals(this.CIUDAD, other.getCIUDAD()))) &&
            ((this.CODIGOSUPER==null && other.getCODIGOSUPER()==null) || 
             (this.CODIGOSUPER!=null &&
              this.CODIGOSUPER.equals(other.getCODIGOSUPER()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NEMOTECNICO==null && other.getNEMOTECNICO()==null) || 
             (this.NEMOTECNICO!=null &&
              this.NEMOTECNICO.equals(other.getNEMOTECNICO()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.PAIS==null && other.getPAIS()==null) || 
             (this.PAIS!=null &&
              this.PAIS.equals(other.getPAIS()))) &&
            ((this.PAISReference==null && other.getPAISReference()==null) || 
             (this.PAISReference!=null &&
              this.PAISReference.equals(other.getPAISReference()))) &&
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
        if (getCANTON() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCANTON());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCANTON(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCIUDAD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCIUDAD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCIUDAD(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCODIGOSUPER() != null) {
            _hashCode += getCODIGOSUPER().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getNEMOTECNICO() != null) {
            _hashCode += getNEMOTECNICO().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getPAIS() != null) {
            _hashCode += getPAIS().hashCode();
        }
        if (getPAISReference() != null) {
            _hashCode += getPAISReference().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PROVINCIA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANTON");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIUDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("NEMOTECNICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NEMOTECNICO"));
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
        elemField.setFieldName("PAIS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAISReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAISReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPAISFG7c7FF7"));
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
