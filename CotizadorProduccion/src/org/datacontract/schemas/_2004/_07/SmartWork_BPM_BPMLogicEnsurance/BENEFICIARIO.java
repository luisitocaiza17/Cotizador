/**
 * BENEFICIARIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class BENEFICIARIO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM ENDOSOITEM;

    private java.lang.String ENDOSOITEMDETALLEID;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOITEMFG7C7FF7 ENDOSOITEMReference;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESACREEDOR;

    private java.lang.String ESACTIVO;

    private java.lang.String ESMENORDEEDAD;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.lang.String PARENTESCOID;

    private java.lang.String PARENTESCOOLD;

    private java.math.BigDecimal PROPORCION;

    private java.lang.String USUARIOACTUALIZA;

    private java.math.BigDecimal VALORACREDITADO;

    public BENEFICIARIO() {
    }

    public BENEFICIARIO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM ENDOSOITEM,
           java.lang.String ENDOSOITEMDETALLEID,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOITEMFG7C7FF7 ENDOSOITEMReference,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESACREEDOR,
           java.lang.String ESACTIVO,
           java.lang.String ESMENORDEEDAD,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.lang.String PARENTESCOID,
           java.lang.String PARENTESCOOLD,
           java.math.BigDecimal PROPORCION,
           java.lang.String USUARIOACTUALIZA,
           java.math.BigDecimal VALORACREDITADO) {
        super(
            id,
            ref,
            entityKey);
        this.ENDOSOITEM = ENDOSOITEM;
        this.ENDOSOITEMDETALLEID = ENDOSOITEMDETALLEID;
        this.ENDOSOITEMReference = ENDOSOITEMReference;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESACREEDOR = ESACREEDOR;
        this.ESACTIVO = ESACTIVO;
        this.ESMENORDEEDAD = ESMENORDEEDAD;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.PARENTESCOID = PARENTESCOID;
        this.PARENTESCOOLD = PARENTESCOOLD;
        this.PROPORCION = PROPORCION;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.VALORACREDITADO = VALORACREDITADO;
    }


    /**
     * Gets the ENDOSOITEM value for this BENEFICIARIO.
     * 
     * @return ENDOSOITEM
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM getENDOSOITEM() {
        return ENDOSOITEM;
    }


    /**
     * Sets the ENDOSOITEM value for this BENEFICIARIO.
     * 
     * @param ENDOSOITEM
     */
    public void setENDOSOITEM(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM ENDOSOITEM) {
        this.ENDOSOITEM = ENDOSOITEM;
    }


    /**
     * Gets the ENDOSOITEMDETALLEID value for this BENEFICIARIO.
     * 
     * @return ENDOSOITEMDETALLEID
     */
    public java.lang.String getENDOSOITEMDETALLEID() {
        return ENDOSOITEMDETALLEID;
    }


    /**
     * Sets the ENDOSOITEMDETALLEID value for this BENEFICIARIO.
     * 
     * @param ENDOSOITEMDETALLEID
     */
    public void setENDOSOITEMDETALLEID(java.lang.String ENDOSOITEMDETALLEID) {
        this.ENDOSOITEMDETALLEID = ENDOSOITEMDETALLEID;
    }


    /**
     * Gets the ENDOSOITEMReference value for this BENEFICIARIO.
     * 
     * @return ENDOSOITEMReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOITEMFG7C7FF7 getENDOSOITEMReference() {
        return ENDOSOITEMReference;
    }


    /**
     * Sets the ENDOSOITEMReference value for this BENEFICIARIO.
     * 
     * @param ENDOSOITEMReference
     */
    public void setENDOSOITEMReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOITEMFG7C7FF7 ENDOSOITEMReference) {
        this.ENDOSOITEMReference = ENDOSOITEMReference;
    }


    /**
     * Gets the ENTIDAD value for this BENEFICIARIO.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this BENEFICIARIO.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this BENEFICIARIO.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this BENEFICIARIO.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESACREEDOR value for this BENEFICIARIO.
     * 
     * @return ESACREEDOR
     */
    public java.lang.String getESACREEDOR() {
        return ESACREEDOR;
    }


    /**
     * Sets the ESACREEDOR value for this BENEFICIARIO.
     * 
     * @param ESACREEDOR
     */
    public void setESACREEDOR(java.lang.String ESACREEDOR) {
        this.ESACREEDOR = ESACREEDOR;
    }


    /**
     * Gets the ESACTIVO value for this BENEFICIARIO.
     * 
     * @return ESACTIVO
     */
    public java.lang.String getESACTIVO() {
        return ESACTIVO;
    }


    /**
     * Sets the ESACTIVO value for this BENEFICIARIO.
     * 
     * @param ESACTIVO
     */
    public void setESACTIVO(java.lang.String ESACTIVO) {
        this.ESACTIVO = ESACTIVO;
    }


    /**
     * Gets the ESMENORDEEDAD value for this BENEFICIARIO.
     * 
     * @return ESMENORDEEDAD
     */
    public java.lang.String getESMENORDEEDAD() {
        return ESMENORDEEDAD;
    }


    /**
     * Sets the ESMENORDEEDAD value for this BENEFICIARIO.
     * 
     * @param ESMENORDEEDAD
     */
    public void setESMENORDEEDAD(java.lang.String ESMENORDEEDAD) {
        this.ESMENORDEEDAD = ESMENORDEEDAD;
    }


    /**
     * Gets the FECHAACTUALIZA value for this BENEFICIARIO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this BENEFICIARIO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this BENEFICIARIO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this BENEFICIARIO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the PARENTESCOID value for this BENEFICIARIO.
     * 
     * @return PARENTESCOID
     */
    public java.lang.String getPARENTESCOID() {
        return PARENTESCOID;
    }


    /**
     * Sets the PARENTESCOID value for this BENEFICIARIO.
     * 
     * @param PARENTESCOID
     */
    public void setPARENTESCOID(java.lang.String PARENTESCOID) {
        this.PARENTESCOID = PARENTESCOID;
    }


    /**
     * Gets the PARENTESCOOLD value for this BENEFICIARIO.
     * 
     * @return PARENTESCOOLD
     */
    public java.lang.String getPARENTESCOOLD() {
        return PARENTESCOOLD;
    }


    /**
     * Sets the PARENTESCOOLD value for this BENEFICIARIO.
     * 
     * @param PARENTESCOOLD
     */
    public void setPARENTESCOOLD(java.lang.String PARENTESCOOLD) {
        this.PARENTESCOOLD = PARENTESCOOLD;
    }


    /**
     * Gets the PROPORCION value for this BENEFICIARIO.
     * 
     * @return PROPORCION
     */
    public java.math.BigDecimal getPROPORCION() {
        return PROPORCION;
    }


    /**
     * Sets the PROPORCION value for this BENEFICIARIO.
     * 
     * @param PROPORCION
     */
    public void setPROPORCION(java.math.BigDecimal PROPORCION) {
        this.PROPORCION = PROPORCION;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this BENEFICIARIO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this BENEFICIARIO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the VALORACREDITADO value for this BENEFICIARIO.
     * 
     * @return VALORACREDITADO
     */
    public java.math.BigDecimal getVALORACREDITADO() {
        return VALORACREDITADO;
    }


    /**
     * Sets the VALORACREDITADO value for this BENEFICIARIO.
     * 
     * @param VALORACREDITADO
     */
    public void setVALORACREDITADO(java.math.BigDecimal VALORACREDITADO) {
        this.VALORACREDITADO = VALORACREDITADO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BENEFICIARIO)) return false;
        BENEFICIARIO other = (BENEFICIARIO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ENDOSOITEM==null && other.getENDOSOITEM()==null) || 
             (this.ENDOSOITEM!=null &&
              this.ENDOSOITEM.equals(other.getENDOSOITEM()))) &&
            ((this.ENDOSOITEMDETALLEID==null && other.getENDOSOITEMDETALLEID()==null) || 
             (this.ENDOSOITEMDETALLEID!=null &&
              this.ENDOSOITEMDETALLEID.equals(other.getENDOSOITEMDETALLEID()))) &&
            ((this.ENDOSOITEMReference==null && other.getENDOSOITEMReference()==null) || 
             (this.ENDOSOITEMReference!=null &&
              this.ENDOSOITEMReference.equals(other.getENDOSOITEMReference()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESACREEDOR==null && other.getESACREEDOR()==null) || 
             (this.ESACREEDOR!=null &&
              this.ESACREEDOR.equals(other.getESACREEDOR()))) &&
            ((this.ESACTIVO==null && other.getESACTIVO()==null) || 
             (this.ESACTIVO!=null &&
              this.ESACTIVO.equals(other.getESACTIVO()))) &&
            ((this.ESMENORDEEDAD==null && other.getESMENORDEEDAD()==null) || 
             (this.ESMENORDEEDAD!=null &&
              this.ESMENORDEEDAD.equals(other.getESMENORDEEDAD()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.PARENTESCOID==null && other.getPARENTESCOID()==null) || 
             (this.PARENTESCOID!=null &&
              this.PARENTESCOID.equals(other.getPARENTESCOID()))) &&
            ((this.PARENTESCOOLD==null && other.getPARENTESCOOLD()==null) || 
             (this.PARENTESCOOLD!=null &&
              this.PARENTESCOOLD.equals(other.getPARENTESCOOLD()))) &&
            ((this.PROPORCION==null && other.getPROPORCION()==null) || 
             (this.PROPORCION!=null &&
              this.PROPORCION.equals(other.getPROPORCION()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.VALORACREDITADO==null && other.getVALORACREDITADO()==null) || 
             (this.VALORACREDITADO!=null &&
              this.VALORACREDITADO.equals(other.getVALORACREDITADO())));
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
        if (getENDOSOITEM() != null) {
            _hashCode += getENDOSOITEM().hashCode();
        }
        if (getENDOSOITEMDETALLEID() != null) {
            _hashCode += getENDOSOITEMDETALLEID().hashCode();
        }
        if (getENDOSOITEMReference() != null) {
            _hashCode += getENDOSOITEMReference().hashCode();
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESACREEDOR() != null) {
            _hashCode += getESACREEDOR().hashCode();
        }
        if (getESACTIVO() != null) {
            _hashCode += getESACTIVO().hashCode();
        }
        if (getESMENORDEEDAD() != null) {
            _hashCode += getESMENORDEEDAD().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getPARENTESCOID() != null) {
            _hashCode += getPARENTESCOID().hashCode();
        }
        if (getPARENTESCOOLD() != null) {
            _hashCode += getPARENTESCOOLD().hashCode();
        }
        if (getPROPORCION() != null) {
            _hashCode += getPROPORCION().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getVALORACREDITADO() != null) {
            _hashCode += getVALORACREDITADO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BENEFICIARIO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOITEMDETALLEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEMDETALLEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOITEMReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEMReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENDOSOITEMFG7c7FF7"));
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
        elemField.setFieldName("ESACREEDOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESACREEDOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESACTIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESACTIVO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESMENORDEEDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESMENORDEEDAD"));
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
        elemField.setFieldName("PARENTESCOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PARENTESCOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARENTESCOOLD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PARENTESCOOLD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROPORCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROPORCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIOACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOACTUALIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORACREDITADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORACREDITADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
