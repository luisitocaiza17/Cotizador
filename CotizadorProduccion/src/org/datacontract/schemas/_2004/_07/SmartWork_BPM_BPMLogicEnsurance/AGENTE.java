/**
 * AGENTE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class AGENTE  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String CODIGOCONTABLE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE[] ENDOSOAGENTE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESTADO;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.lang.String NUMEROCREDENCIAL;

    private java.lang.String NUMEROREGISTRO;

    private java.lang.String USUARIOACTUALIZA;

    public AGENTE() {
    }

    public AGENTE(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String CODIGOCONTABLE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE[] ENDOSOAGENTE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESTADO,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.lang.String NUMEROCREDENCIAL,
           java.lang.String NUMEROREGISTRO,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.CODIGOCONTABLE = CODIGOCONTABLE;
        this.ENDOSOAGENTE = ENDOSOAGENTE;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESTADO = ESTADO;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.NUMEROCREDENCIAL = NUMEROCREDENCIAL;
        this.NUMEROREGISTRO = NUMEROREGISTRO;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the CODIGOCONTABLE value for this AGENTE.
     * 
     * @return CODIGOCONTABLE
     */
    public java.lang.String getCODIGOCONTABLE() {
        return CODIGOCONTABLE;
    }


    /**
     * Sets the CODIGOCONTABLE value for this AGENTE.
     * 
     * @param CODIGOCONTABLE
     */
    public void setCODIGOCONTABLE(java.lang.String CODIGOCONTABLE) {
        this.CODIGOCONTABLE = CODIGOCONTABLE;
    }


    /**
     * Gets the ENDOSOAGENTE value for this AGENTE.
     * 
     * @return ENDOSOAGENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE[] getENDOSOAGENTE() {
        return ENDOSOAGENTE;
    }


    /**
     * Sets the ENDOSOAGENTE value for this AGENTE.
     * 
     * @param ENDOSOAGENTE
     */
    public void setENDOSOAGENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE[] ENDOSOAGENTE) {
        this.ENDOSOAGENTE = ENDOSOAGENTE;
    }


    /**
     * Gets the ENTIDAD value for this AGENTE.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this AGENTE.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this AGENTE.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this AGENTE.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESTADO value for this AGENTE.
     * 
     * @return ESTADO
     */
    public java.lang.String getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this AGENTE.
     * 
     * @param ESTADO
     */
    public void setESTADO(java.lang.String ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the FECHAACTUALIZA value for this AGENTE.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this AGENTE.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this AGENTE.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this AGENTE.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NUMEROCREDENCIAL value for this AGENTE.
     * 
     * @return NUMEROCREDENCIAL
     */
    public java.lang.String getNUMEROCREDENCIAL() {
        return NUMEROCREDENCIAL;
    }


    /**
     * Sets the NUMEROCREDENCIAL value for this AGENTE.
     * 
     * @param NUMEROCREDENCIAL
     */
    public void setNUMEROCREDENCIAL(java.lang.String NUMEROCREDENCIAL) {
        this.NUMEROCREDENCIAL = NUMEROCREDENCIAL;
    }


    /**
     * Gets the NUMEROREGISTRO value for this AGENTE.
     * 
     * @return NUMEROREGISTRO
     */
    public java.lang.String getNUMEROREGISTRO() {
        return NUMEROREGISTRO;
    }


    /**
     * Sets the NUMEROREGISTRO value for this AGENTE.
     * 
     * @param NUMEROREGISTRO
     */
    public void setNUMEROREGISTRO(java.lang.String NUMEROREGISTRO) {
        this.NUMEROREGISTRO = NUMEROREGISTRO;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this AGENTE.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this AGENTE.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AGENTE)) return false;
        AGENTE other = (AGENTE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.CODIGOCONTABLE==null && other.getCODIGOCONTABLE()==null) || 
             (this.CODIGOCONTABLE!=null &&
              this.CODIGOCONTABLE.equals(other.getCODIGOCONTABLE()))) &&
            ((this.ENDOSOAGENTE==null && other.getENDOSOAGENTE()==null) || 
             (this.ENDOSOAGENTE!=null &&
              java.util.Arrays.equals(this.ENDOSOAGENTE, other.getENDOSOAGENTE()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NUMEROCREDENCIAL==null && other.getNUMEROCREDENCIAL()==null) || 
             (this.NUMEROCREDENCIAL!=null &&
              this.NUMEROCREDENCIAL.equals(other.getNUMEROCREDENCIAL()))) &&
            ((this.NUMEROREGISTRO==null && other.getNUMEROREGISTRO()==null) || 
             (this.NUMEROREGISTRO!=null &&
              this.NUMEROREGISTRO.equals(other.getNUMEROREGISTRO()))) &&
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
        if (getCODIGOCONTABLE() != null) {
            _hashCode += getCODIGOCONTABLE().hashCode();
        }
        if (getENDOSOAGENTE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getENDOSOAGENTE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getENDOSOAGENTE(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getNUMEROCREDENCIAL() != null) {
            _hashCode += getNUMEROCREDENCIAL().hashCode();
        }
        if (getNUMEROREGISTRO() != null) {
            _hashCode += getNUMEROREGISTRO().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AGENTE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOCONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CODIGOCONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOAGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
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
        elemField.setFieldName("NUMEROCREDENCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROCREDENCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROREGISTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROREGISTRO"));
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
