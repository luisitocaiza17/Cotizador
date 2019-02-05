/**
 * CLIENTE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class CLIENTE  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String BLOQUEADO;

    private java.lang.String CONFIGPRODUCTOID;

    private java.lang.String DESCRIPCION_BLOQ;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESPRIMAEXCENTA;

    private java.lang.String ESTAIMPRESA;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String ID;

    private java.math.BigDecimal LIMITECREDITO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA;

    private java.lang.String REFERIDO;

    private java.lang.String REFERIDOID;

    private java.lang.String TIPIFICACIONID;

    private java.lang.String USUARIOACTUALIZA;

    public CLIENTE() {
    }

    public CLIENTE(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String BLOQUEADO,
           java.lang.String CONFIGPRODUCTOID,
           java.lang.String DESCRIPCION_BLOQ,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESPRIMAEXCENTA,
           java.lang.String ESTAIMPRESA,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String ID,
           java.math.BigDecimal LIMITECREDITO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA,
           java.lang.String REFERIDO,
           java.lang.String REFERIDOID,
           java.lang.String TIPIFICACIONID,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.BLOQUEADO = BLOQUEADO;
        this.CONFIGPRODUCTOID = CONFIGPRODUCTOID;
        this.DESCRIPCION_BLOQ = DESCRIPCION_BLOQ;
        this.ENDOSO = ENDOSO;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESPRIMAEXCENTA = ESPRIMAEXCENTA;
        this.ESTAIMPRESA = ESTAIMPRESA;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.ID = ID;
        this.LIMITECREDITO = LIMITECREDITO;
        this.POLIZA = POLIZA;
        this.REFERIDO = REFERIDO;
        this.REFERIDOID = REFERIDOID;
        this.TIPIFICACIONID = TIPIFICACIONID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the BLOQUEADO value for this CLIENTE.
     * 
     * @return BLOQUEADO
     */
    public java.lang.String getBLOQUEADO() {
        return BLOQUEADO;
    }


    /**
     * Sets the BLOQUEADO value for this CLIENTE.
     * 
     * @param BLOQUEADO
     */
    public void setBLOQUEADO(java.lang.String BLOQUEADO) {
        this.BLOQUEADO = BLOQUEADO;
    }


    /**
     * Gets the CONFIGPRODUCTOID value for this CLIENTE.
     * 
     * @return CONFIGPRODUCTOID
     */
    public java.lang.String getCONFIGPRODUCTOID() {
        return CONFIGPRODUCTOID;
    }


    /**
     * Sets the CONFIGPRODUCTOID value for this CLIENTE.
     * 
     * @param CONFIGPRODUCTOID
     */
    public void setCONFIGPRODUCTOID(java.lang.String CONFIGPRODUCTOID) {
        this.CONFIGPRODUCTOID = CONFIGPRODUCTOID;
    }


    /**
     * Gets the DESCRIPCION_BLOQ value for this CLIENTE.
     * 
     * @return DESCRIPCION_BLOQ
     */
    public java.lang.String getDESCRIPCION_BLOQ() {
        return DESCRIPCION_BLOQ;
    }


    /**
     * Sets the DESCRIPCION_BLOQ value for this CLIENTE.
     * 
     * @param DESCRIPCION_BLOQ
     */
    public void setDESCRIPCION_BLOQ(java.lang.String DESCRIPCION_BLOQ) {
        this.DESCRIPCION_BLOQ = DESCRIPCION_BLOQ;
    }


    /**
     * Gets the ENDOSO value for this CLIENTE.
     * 
     * @return ENDOSO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] getENDOSO() {
        return ENDOSO;
    }


    /**
     * Sets the ENDOSO value for this CLIENTE.
     * 
     * @param ENDOSO
     */
    public void setENDOSO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO) {
        this.ENDOSO = ENDOSO;
    }


    /**
     * Gets the ENTIDAD value for this CLIENTE.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this CLIENTE.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this CLIENTE.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this CLIENTE.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESPRIMAEXCENTA value for this CLIENTE.
     * 
     * @return ESPRIMAEXCENTA
     */
    public java.lang.String getESPRIMAEXCENTA() {
        return ESPRIMAEXCENTA;
    }


    /**
     * Sets the ESPRIMAEXCENTA value for this CLIENTE.
     * 
     * @param ESPRIMAEXCENTA
     */
    public void setESPRIMAEXCENTA(java.lang.String ESPRIMAEXCENTA) {
        this.ESPRIMAEXCENTA = ESPRIMAEXCENTA;
    }


    /**
     * Gets the ESTAIMPRESA value for this CLIENTE.
     * 
     * @return ESTAIMPRESA
     */
    public java.lang.String getESTAIMPRESA() {
        return ESTAIMPRESA;
    }


    /**
     * Sets the ESTAIMPRESA value for this CLIENTE.
     * 
     * @param ESTAIMPRESA
     */
    public void setESTAIMPRESA(java.lang.String ESTAIMPRESA) {
        this.ESTAIMPRESA = ESTAIMPRESA;
    }


    /**
     * Gets the FECHAACTUALIZA value for this CLIENTE.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this CLIENTE.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the ID value for this CLIENTE.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this CLIENTE.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the LIMITECREDITO value for this CLIENTE.
     * 
     * @return LIMITECREDITO
     */
    public java.math.BigDecimal getLIMITECREDITO() {
        return LIMITECREDITO;
    }


    /**
     * Sets the LIMITECREDITO value for this CLIENTE.
     * 
     * @param LIMITECREDITO
     */
    public void setLIMITECREDITO(java.math.BigDecimal LIMITECREDITO) {
        this.LIMITECREDITO = LIMITECREDITO;
    }


    /**
     * Gets the POLIZA value for this CLIENTE.
     * 
     * @return POLIZA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] getPOLIZA() {
        return POLIZA;
    }


    /**
     * Sets the POLIZA value for this CLIENTE.
     * 
     * @param POLIZA
     */
    public void setPOLIZA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA) {
        this.POLIZA = POLIZA;
    }


    /**
     * Gets the REFERIDO value for this CLIENTE.
     * 
     * @return REFERIDO
     */
    public java.lang.String getREFERIDO() {
        return REFERIDO;
    }


    /**
     * Sets the REFERIDO value for this CLIENTE.
     * 
     * @param REFERIDO
     */
    public void setREFERIDO(java.lang.String REFERIDO) {
        this.REFERIDO = REFERIDO;
    }


    /**
     * Gets the REFERIDOID value for this CLIENTE.
     * 
     * @return REFERIDOID
     */
    public java.lang.String getREFERIDOID() {
        return REFERIDOID;
    }


    /**
     * Sets the REFERIDOID value for this CLIENTE.
     * 
     * @param REFERIDOID
     */
    public void setREFERIDOID(java.lang.String REFERIDOID) {
        this.REFERIDOID = REFERIDOID;
    }


    /**
     * Gets the TIPIFICACIONID value for this CLIENTE.
     * 
     * @return TIPIFICACIONID
     */
    public java.lang.String getTIPIFICACIONID() {
        return TIPIFICACIONID;
    }


    /**
     * Sets the TIPIFICACIONID value for this CLIENTE.
     * 
     * @param TIPIFICACIONID
     */
    public void setTIPIFICACIONID(java.lang.String TIPIFICACIONID) {
        this.TIPIFICACIONID = TIPIFICACIONID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this CLIENTE.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this CLIENTE.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CLIENTE)) return false;
        CLIENTE other = (CLIENTE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.BLOQUEADO==null && other.getBLOQUEADO()==null) || 
             (this.BLOQUEADO!=null &&
              this.BLOQUEADO.equals(other.getBLOQUEADO()))) &&
            ((this.CONFIGPRODUCTOID==null && other.getCONFIGPRODUCTOID()==null) || 
             (this.CONFIGPRODUCTOID!=null &&
              this.CONFIGPRODUCTOID.equals(other.getCONFIGPRODUCTOID()))) &&
            ((this.DESCRIPCION_BLOQ==null && other.getDESCRIPCION_BLOQ()==null) || 
             (this.DESCRIPCION_BLOQ!=null &&
              this.DESCRIPCION_BLOQ.equals(other.getDESCRIPCION_BLOQ()))) &&
            ((this.ENDOSO==null && other.getENDOSO()==null) || 
             (this.ENDOSO!=null &&
              java.util.Arrays.equals(this.ENDOSO, other.getENDOSO()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESPRIMAEXCENTA==null && other.getESPRIMAEXCENTA()==null) || 
             (this.ESPRIMAEXCENTA!=null &&
              this.ESPRIMAEXCENTA.equals(other.getESPRIMAEXCENTA()))) &&
            ((this.ESTAIMPRESA==null && other.getESTAIMPRESA()==null) || 
             (this.ESTAIMPRESA!=null &&
              this.ESTAIMPRESA.equals(other.getESTAIMPRESA()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.LIMITECREDITO==null && other.getLIMITECREDITO()==null) || 
             (this.LIMITECREDITO!=null &&
              this.LIMITECREDITO.equals(other.getLIMITECREDITO()))) &&
            ((this.POLIZA==null && other.getPOLIZA()==null) || 
             (this.POLIZA!=null &&
              java.util.Arrays.equals(this.POLIZA, other.getPOLIZA()))) &&
            ((this.REFERIDO==null && other.getREFERIDO()==null) || 
             (this.REFERIDO!=null &&
              this.REFERIDO.equals(other.getREFERIDO()))) &&
            ((this.REFERIDOID==null && other.getREFERIDOID()==null) || 
             (this.REFERIDOID!=null &&
              this.REFERIDOID.equals(other.getREFERIDOID()))) &&
            ((this.TIPIFICACIONID==null && other.getTIPIFICACIONID()==null) || 
             (this.TIPIFICACIONID!=null &&
              this.TIPIFICACIONID.equals(other.getTIPIFICACIONID()))) &&
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
        if (getBLOQUEADO() != null) {
            _hashCode += getBLOQUEADO().hashCode();
        }
        if (getCONFIGPRODUCTOID() != null) {
            _hashCode += getCONFIGPRODUCTOID().hashCode();
        }
        if (getDESCRIPCION_BLOQ() != null) {
            _hashCode += getDESCRIPCION_BLOQ().hashCode();
        }
        if (getENDOSO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getENDOSO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getENDOSO(), i);
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
        if (getESPRIMAEXCENTA() != null) {
            _hashCode += getESPRIMAEXCENTA().hashCode();
        }
        if (getESTAIMPRESA() != null) {
            _hashCode += getESTAIMPRESA().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getLIMITECREDITO() != null) {
            _hashCode += getLIMITECREDITO().hashCode();
        }
        if (getPOLIZA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPOLIZA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPOLIZA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getREFERIDO() != null) {
            _hashCode += getREFERIDO().hashCode();
        }
        if (getREFERIDOID() != null) {
            _hashCode += getREFERIDOID().hashCode();
        }
        if (getTIPIFICACIONID() != null) {
            _hashCode += getTIPIFICACIONID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CLIENTE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BLOQUEADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BLOQUEADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONFIGPRODUCTOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONFIGPRODUCTOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION_BLOQ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DESCRIPCION_BLOQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
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
        elemField.setFieldName("ESPRIMAEXCENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESPRIMAEXCENTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTAIMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTAIMPRESA"));
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
        elemField.setFieldName("LIMITECREDITO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LIMITECREDITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REFERIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "REFERIDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REFERIDOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "REFERIDOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPIFICACIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPIFICACIONID"));
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
