/**
 * EMPRESA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class EMPRESA  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String APELLIDOSREPRESENTANTE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESCONTRIBUYENTEESPECIAL;

    private java.lang.String FAX;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHACONSTITUCION;

    private java.lang.String ID;

    private java.lang.String IDENTIFICACION;

    private java.lang.String NOMBRESREPRESENTANTE;

    private java.lang.String OBJETOSOCIAL;

    private java.lang.String SECTORID;

    private java.lang.String TIPOID;

    private java.lang.String USUARIOACTUALIZA;

    public EMPRESA() {
    }

    public EMPRESA(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String APELLIDOSREPRESENTANTE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESCONTRIBUYENTEESPECIAL,
           java.lang.String FAX,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHACONSTITUCION,
           java.lang.String ID,
           java.lang.String IDENTIFICACION,
           java.lang.String NOMBRESREPRESENTANTE,
           java.lang.String OBJETOSOCIAL,
           java.lang.String SECTORID,
           java.lang.String TIPOID,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.APELLIDOSREPRESENTANTE = APELLIDOSREPRESENTANTE;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESCONTRIBUYENTEESPECIAL = ESCONTRIBUYENTEESPECIAL;
        this.FAX = FAX;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHACONSTITUCION = FECHACONSTITUCION;
        this.ID = ID;
        this.IDENTIFICACION = IDENTIFICACION;
        this.NOMBRESREPRESENTANTE = NOMBRESREPRESENTANTE;
        this.OBJETOSOCIAL = OBJETOSOCIAL;
        this.SECTORID = SECTORID;
        this.TIPOID = TIPOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the APELLIDOSREPRESENTANTE value for this EMPRESA.
     * 
     * @return APELLIDOSREPRESENTANTE
     */
    public java.lang.String getAPELLIDOSREPRESENTANTE() {
        return APELLIDOSREPRESENTANTE;
    }


    /**
     * Sets the APELLIDOSREPRESENTANTE value for this EMPRESA.
     * 
     * @param APELLIDOSREPRESENTANTE
     */
    public void setAPELLIDOSREPRESENTANTE(java.lang.String APELLIDOSREPRESENTANTE) {
        this.APELLIDOSREPRESENTANTE = APELLIDOSREPRESENTANTE;
    }


    /**
     * Gets the ENTIDAD value for this EMPRESA.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this EMPRESA.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this EMPRESA.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this EMPRESA.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESCONTRIBUYENTEESPECIAL value for this EMPRESA.
     * 
     * @return ESCONTRIBUYENTEESPECIAL
     */
    public java.lang.String getESCONTRIBUYENTEESPECIAL() {
        return ESCONTRIBUYENTEESPECIAL;
    }


    /**
     * Sets the ESCONTRIBUYENTEESPECIAL value for this EMPRESA.
     * 
     * @param ESCONTRIBUYENTEESPECIAL
     */
    public void setESCONTRIBUYENTEESPECIAL(java.lang.String ESCONTRIBUYENTEESPECIAL) {
        this.ESCONTRIBUYENTEESPECIAL = ESCONTRIBUYENTEESPECIAL;
    }


    /**
     * Gets the FAX value for this EMPRESA.
     * 
     * @return FAX
     */
    public java.lang.String getFAX() {
        return FAX;
    }


    /**
     * Sets the FAX value for this EMPRESA.
     * 
     * @param FAX
     */
    public void setFAX(java.lang.String FAX) {
        this.FAX = FAX;
    }


    /**
     * Gets the FECHAACTUALIZA value for this EMPRESA.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this EMPRESA.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHACONSTITUCION value for this EMPRESA.
     * 
     * @return FECHACONSTITUCION
     */
    public java.util.Calendar getFECHACONSTITUCION() {
        return FECHACONSTITUCION;
    }


    /**
     * Sets the FECHACONSTITUCION value for this EMPRESA.
     * 
     * @param FECHACONSTITUCION
     */
    public void setFECHACONSTITUCION(java.util.Calendar FECHACONSTITUCION) {
        this.FECHACONSTITUCION = FECHACONSTITUCION;
    }


    /**
     * Gets the ID value for this EMPRESA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this EMPRESA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the IDENTIFICACION value for this EMPRESA.
     * 
     * @return IDENTIFICACION
     */
    public java.lang.String getIDENTIFICACION() {
        return IDENTIFICACION;
    }


    /**
     * Sets the IDENTIFICACION value for this EMPRESA.
     * 
     * @param IDENTIFICACION
     */
    public void setIDENTIFICACION(java.lang.String IDENTIFICACION) {
        this.IDENTIFICACION = IDENTIFICACION;
    }


    /**
     * Gets the NOMBRESREPRESENTANTE value for this EMPRESA.
     * 
     * @return NOMBRESREPRESENTANTE
     */
    public java.lang.String getNOMBRESREPRESENTANTE() {
        return NOMBRESREPRESENTANTE;
    }


    /**
     * Sets the NOMBRESREPRESENTANTE value for this EMPRESA.
     * 
     * @param NOMBRESREPRESENTANTE
     */
    public void setNOMBRESREPRESENTANTE(java.lang.String NOMBRESREPRESENTANTE) {
        this.NOMBRESREPRESENTANTE = NOMBRESREPRESENTANTE;
    }


    /**
     * Gets the OBJETOSOCIAL value for this EMPRESA.
     * 
     * @return OBJETOSOCIAL
     */
    public java.lang.String getOBJETOSOCIAL() {
        return OBJETOSOCIAL;
    }


    /**
     * Sets the OBJETOSOCIAL value for this EMPRESA.
     * 
     * @param OBJETOSOCIAL
     */
    public void setOBJETOSOCIAL(java.lang.String OBJETOSOCIAL) {
        this.OBJETOSOCIAL = OBJETOSOCIAL;
    }


    /**
     * Gets the SECTORID value for this EMPRESA.
     * 
     * @return SECTORID
     */
    public java.lang.String getSECTORID() {
        return SECTORID;
    }


    /**
     * Sets the SECTORID value for this EMPRESA.
     * 
     * @param SECTORID
     */
    public void setSECTORID(java.lang.String SECTORID) {
        this.SECTORID = SECTORID;
    }


    /**
     * Gets the TIPOID value for this EMPRESA.
     * 
     * @return TIPOID
     */
    public java.lang.String getTIPOID() {
        return TIPOID;
    }


    /**
     * Sets the TIPOID value for this EMPRESA.
     * 
     * @param TIPOID
     */
    public void setTIPOID(java.lang.String TIPOID) {
        this.TIPOID = TIPOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this EMPRESA.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this EMPRESA.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EMPRESA)) return false;
        EMPRESA other = (EMPRESA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.APELLIDOSREPRESENTANTE==null && other.getAPELLIDOSREPRESENTANTE()==null) || 
             (this.APELLIDOSREPRESENTANTE!=null &&
              this.APELLIDOSREPRESENTANTE.equals(other.getAPELLIDOSREPRESENTANTE()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESCONTRIBUYENTEESPECIAL==null && other.getESCONTRIBUYENTEESPECIAL()==null) || 
             (this.ESCONTRIBUYENTEESPECIAL!=null &&
              this.ESCONTRIBUYENTEESPECIAL.equals(other.getESCONTRIBUYENTEESPECIAL()))) &&
            ((this.FAX==null && other.getFAX()==null) || 
             (this.FAX!=null &&
              this.FAX.equals(other.getFAX()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHACONSTITUCION==null && other.getFECHACONSTITUCION()==null) || 
             (this.FECHACONSTITUCION!=null &&
              this.FECHACONSTITUCION.equals(other.getFECHACONSTITUCION()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.IDENTIFICACION==null && other.getIDENTIFICACION()==null) || 
             (this.IDENTIFICACION!=null &&
              this.IDENTIFICACION.equals(other.getIDENTIFICACION()))) &&
            ((this.NOMBRESREPRESENTANTE==null && other.getNOMBRESREPRESENTANTE()==null) || 
             (this.NOMBRESREPRESENTANTE!=null &&
              this.NOMBRESREPRESENTANTE.equals(other.getNOMBRESREPRESENTANTE()))) &&
            ((this.OBJETOSOCIAL==null && other.getOBJETOSOCIAL()==null) || 
             (this.OBJETOSOCIAL!=null &&
              this.OBJETOSOCIAL.equals(other.getOBJETOSOCIAL()))) &&
            ((this.SECTORID==null && other.getSECTORID()==null) || 
             (this.SECTORID!=null &&
              this.SECTORID.equals(other.getSECTORID()))) &&
            ((this.TIPOID==null && other.getTIPOID()==null) || 
             (this.TIPOID!=null &&
              this.TIPOID.equals(other.getTIPOID()))) &&
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
        if (getAPELLIDOSREPRESENTANTE() != null) {
            _hashCode += getAPELLIDOSREPRESENTANTE().hashCode();
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESCONTRIBUYENTEESPECIAL() != null) {
            _hashCode += getESCONTRIBUYENTEESPECIAL().hashCode();
        }
        if (getFAX() != null) {
            _hashCode += getFAX().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHACONSTITUCION() != null) {
            _hashCode += getFECHACONSTITUCION().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getIDENTIFICACION() != null) {
            _hashCode += getIDENTIFICACION().hashCode();
        }
        if (getNOMBRESREPRESENTANTE() != null) {
            _hashCode += getNOMBRESREPRESENTANTE().hashCode();
        }
        if (getOBJETOSOCIAL() != null) {
            _hashCode += getOBJETOSOCIAL().hashCode();
        }
        if (getSECTORID() != null) {
            _hashCode += getSECTORID().hashCode();
        }
        if (getTIPOID() != null) {
            _hashCode += getTIPOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EMPRESA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMPRESA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APELLIDOSREPRESENTANTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "APELLIDOSREPRESENTANTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
        elemField.setFieldName("ESCONTRIBUYENTEESPECIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESCONTRIBUYENTEESPECIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FAX"));
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
        elemField.setFieldName("FECHACONSTITUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACONSTITUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "IDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRESREPRESENTANTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRESREPRESENTANTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBJETOSOCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "OBJETOSOCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SECTORID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SECTORID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOID"));
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
