/**
 * PERSONA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class PERSONA  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ACTIVIDADEMPRESA;

    private java.lang.String APELLIDOCONYUGE;

    private java.lang.String CARGO;

    private java.lang.String DIRECCIONEMPRESA;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.math.BigDecimal FAX;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHANACIMIENTO;

    private java.lang.String GENERO;

    private java.lang.String ID;

    private java.lang.String LUGARNACIMIENTO;

    private java.lang.String NACIONALIDAD;

    private java.lang.String NOMBRECONYUGE;

    private java.lang.String NOMBREEMPRESA;

    private java.math.BigDecimal TELEFONOEMPRESA;

    private java.lang.String TITULO;

    private java.lang.String USUARIOACTUALIZA;

    public PERSONA() {
    }

    public PERSONA(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ACTIVIDADEMPRESA,
           java.lang.String APELLIDOCONYUGE,
           java.lang.String CARGO,
           java.lang.String DIRECCIONEMPRESA,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.math.BigDecimal FAX,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHANACIMIENTO,
           java.lang.String GENERO,
           java.lang.String ID,
           java.lang.String LUGARNACIMIENTO,
           java.lang.String NACIONALIDAD,
           java.lang.String NOMBRECONYUGE,
           java.lang.String NOMBREEMPRESA,
           java.math.BigDecimal TELEFONOEMPRESA,
           java.lang.String TITULO,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.ACTIVIDADEMPRESA = ACTIVIDADEMPRESA;
        this.APELLIDOCONYUGE = APELLIDOCONYUGE;
        this.CARGO = CARGO;
        this.DIRECCIONEMPRESA = DIRECCIONEMPRESA;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.FAX = FAX;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHANACIMIENTO = FECHANACIMIENTO;
        this.GENERO = GENERO;
        this.ID = ID;
        this.LUGARNACIMIENTO = LUGARNACIMIENTO;
        this.NACIONALIDAD = NACIONALIDAD;
        this.NOMBRECONYUGE = NOMBRECONYUGE;
        this.NOMBREEMPRESA = NOMBREEMPRESA;
        this.TELEFONOEMPRESA = TELEFONOEMPRESA;
        this.TITULO = TITULO;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the ACTIVIDADEMPRESA value for this PERSONA.
     * 
     * @return ACTIVIDADEMPRESA
     */
    public java.lang.String getACTIVIDADEMPRESA() {
        return ACTIVIDADEMPRESA;
    }


    /**
     * Sets the ACTIVIDADEMPRESA value for this PERSONA.
     * 
     * @param ACTIVIDADEMPRESA
     */
    public void setACTIVIDADEMPRESA(java.lang.String ACTIVIDADEMPRESA) {
        this.ACTIVIDADEMPRESA = ACTIVIDADEMPRESA;
    }


    /**
     * Gets the APELLIDOCONYUGE value for this PERSONA.
     * 
     * @return APELLIDOCONYUGE
     */
    public java.lang.String getAPELLIDOCONYUGE() {
        return APELLIDOCONYUGE;
    }


    /**
     * Sets the APELLIDOCONYUGE value for this PERSONA.
     * 
     * @param APELLIDOCONYUGE
     */
    public void setAPELLIDOCONYUGE(java.lang.String APELLIDOCONYUGE) {
        this.APELLIDOCONYUGE = APELLIDOCONYUGE;
    }


    /**
     * Gets the CARGO value for this PERSONA.
     * 
     * @return CARGO
     */
    public java.lang.String getCARGO() {
        return CARGO;
    }


    /**
     * Sets the CARGO value for this PERSONA.
     * 
     * @param CARGO
     */
    public void setCARGO(java.lang.String CARGO) {
        this.CARGO = CARGO;
    }


    /**
     * Gets the DIRECCIONEMPRESA value for this PERSONA.
     * 
     * @return DIRECCIONEMPRESA
     */
    public java.lang.String getDIRECCIONEMPRESA() {
        return DIRECCIONEMPRESA;
    }


    /**
     * Sets the DIRECCIONEMPRESA value for this PERSONA.
     * 
     * @param DIRECCIONEMPRESA
     */
    public void setDIRECCIONEMPRESA(java.lang.String DIRECCIONEMPRESA) {
        this.DIRECCIONEMPRESA = DIRECCIONEMPRESA;
    }


    /**
     * Gets the ENTIDAD value for this PERSONA.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this PERSONA.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this PERSONA.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this PERSONA.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the FAX value for this PERSONA.
     * 
     * @return FAX
     */
    public java.math.BigDecimal getFAX() {
        return FAX;
    }


    /**
     * Sets the FAX value for this PERSONA.
     * 
     * @param FAX
     */
    public void setFAX(java.math.BigDecimal FAX) {
        this.FAX = FAX;
    }


    /**
     * Gets the FECHAACTUALIZA value for this PERSONA.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this PERSONA.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHANACIMIENTO value for this PERSONA.
     * 
     * @return FECHANACIMIENTO
     */
    public java.util.Calendar getFECHANACIMIENTO() {
        return FECHANACIMIENTO;
    }


    /**
     * Sets the FECHANACIMIENTO value for this PERSONA.
     * 
     * @param FECHANACIMIENTO
     */
    public void setFECHANACIMIENTO(java.util.Calendar FECHANACIMIENTO) {
        this.FECHANACIMIENTO = FECHANACIMIENTO;
    }


    /**
     * Gets the GENERO value for this PERSONA.
     * 
     * @return GENERO
     */
    public java.lang.String getGENERO() {
        return GENERO;
    }


    /**
     * Sets the GENERO value for this PERSONA.
     * 
     * @param GENERO
     */
    public void setGENERO(java.lang.String GENERO) {
        this.GENERO = GENERO;
    }


    /**
     * Gets the ID value for this PERSONA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this PERSONA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the LUGARNACIMIENTO value for this PERSONA.
     * 
     * @return LUGARNACIMIENTO
     */
    public java.lang.String getLUGARNACIMIENTO() {
        return LUGARNACIMIENTO;
    }


    /**
     * Sets the LUGARNACIMIENTO value for this PERSONA.
     * 
     * @param LUGARNACIMIENTO
     */
    public void setLUGARNACIMIENTO(java.lang.String LUGARNACIMIENTO) {
        this.LUGARNACIMIENTO = LUGARNACIMIENTO;
    }


    /**
     * Gets the NACIONALIDAD value for this PERSONA.
     * 
     * @return NACIONALIDAD
     */
    public java.lang.String getNACIONALIDAD() {
        return NACIONALIDAD;
    }


    /**
     * Sets the NACIONALIDAD value for this PERSONA.
     * 
     * @param NACIONALIDAD
     */
    public void setNACIONALIDAD(java.lang.String NACIONALIDAD) {
        this.NACIONALIDAD = NACIONALIDAD;
    }


    /**
     * Gets the NOMBRECONYUGE value for this PERSONA.
     * 
     * @return NOMBRECONYUGE
     */
    public java.lang.String getNOMBRECONYUGE() {
        return NOMBRECONYUGE;
    }


    /**
     * Sets the NOMBRECONYUGE value for this PERSONA.
     * 
     * @param NOMBRECONYUGE
     */
    public void setNOMBRECONYUGE(java.lang.String NOMBRECONYUGE) {
        this.NOMBRECONYUGE = NOMBRECONYUGE;
    }


    /**
     * Gets the NOMBREEMPRESA value for this PERSONA.
     * 
     * @return NOMBREEMPRESA
     */
    public java.lang.String getNOMBREEMPRESA() {
        return NOMBREEMPRESA;
    }


    /**
     * Sets the NOMBREEMPRESA value for this PERSONA.
     * 
     * @param NOMBREEMPRESA
     */
    public void setNOMBREEMPRESA(java.lang.String NOMBREEMPRESA) {
        this.NOMBREEMPRESA = NOMBREEMPRESA;
    }


    /**
     * Gets the TELEFONOEMPRESA value for this PERSONA.
     * 
     * @return TELEFONOEMPRESA
     */
    public java.math.BigDecimal getTELEFONOEMPRESA() {
        return TELEFONOEMPRESA;
    }


    /**
     * Sets the TELEFONOEMPRESA value for this PERSONA.
     * 
     * @param TELEFONOEMPRESA
     */
    public void setTELEFONOEMPRESA(java.math.BigDecimal TELEFONOEMPRESA) {
        this.TELEFONOEMPRESA = TELEFONOEMPRESA;
    }


    /**
     * Gets the TITULO value for this PERSONA.
     * 
     * @return TITULO
     */
    public java.lang.String getTITULO() {
        return TITULO;
    }


    /**
     * Sets the TITULO value for this PERSONA.
     * 
     * @param TITULO
     */
    public void setTITULO(java.lang.String TITULO) {
        this.TITULO = TITULO;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this PERSONA.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this PERSONA.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PERSONA)) return false;
        PERSONA other = (PERSONA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACTIVIDADEMPRESA==null && other.getACTIVIDADEMPRESA()==null) || 
             (this.ACTIVIDADEMPRESA!=null &&
              this.ACTIVIDADEMPRESA.equals(other.getACTIVIDADEMPRESA()))) &&
            ((this.APELLIDOCONYUGE==null && other.getAPELLIDOCONYUGE()==null) || 
             (this.APELLIDOCONYUGE!=null &&
              this.APELLIDOCONYUGE.equals(other.getAPELLIDOCONYUGE()))) &&
            ((this.CARGO==null && other.getCARGO()==null) || 
             (this.CARGO!=null &&
              this.CARGO.equals(other.getCARGO()))) &&
            ((this.DIRECCIONEMPRESA==null && other.getDIRECCIONEMPRESA()==null) || 
             (this.DIRECCIONEMPRESA!=null &&
              this.DIRECCIONEMPRESA.equals(other.getDIRECCIONEMPRESA()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.FAX==null && other.getFAX()==null) || 
             (this.FAX!=null &&
              this.FAX.equals(other.getFAX()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHANACIMIENTO==null && other.getFECHANACIMIENTO()==null) || 
             (this.FECHANACIMIENTO!=null &&
              this.FECHANACIMIENTO.equals(other.getFECHANACIMIENTO()))) &&
            ((this.GENERO==null && other.getGENERO()==null) || 
             (this.GENERO!=null &&
              this.GENERO.equals(other.getGENERO()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.LUGARNACIMIENTO==null && other.getLUGARNACIMIENTO()==null) || 
             (this.LUGARNACIMIENTO!=null &&
              this.LUGARNACIMIENTO.equals(other.getLUGARNACIMIENTO()))) &&
            ((this.NACIONALIDAD==null && other.getNACIONALIDAD()==null) || 
             (this.NACIONALIDAD!=null &&
              this.NACIONALIDAD.equals(other.getNACIONALIDAD()))) &&
            ((this.NOMBRECONYUGE==null && other.getNOMBRECONYUGE()==null) || 
             (this.NOMBRECONYUGE!=null &&
              this.NOMBRECONYUGE.equals(other.getNOMBRECONYUGE()))) &&
            ((this.NOMBREEMPRESA==null && other.getNOMBREEMPRESA()==null) || 
             (this.NOMBREEMPRESA!=null &&
              this.NOMBREEMPRESA.equals(other.getNOMBREEMPRESA()))) &&
            ((this.TELEFONOEMPRESA==null && other.getTELEFONOEMPRESA()==null) || 
             (this.TELEFONOEMPRESA!=null &&
              this.TELEFONOEMPRESA.equals(other.getTELEFONOEMPRESA()))) &&
            ((this.TITULO==null && other.getTITULO()==null) || 
             (this.TITULO!=null &&
              this.TITULO.equals(other.getTITULO()))) &&
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
        if (getACTIVIDADEMPRESA() != null) {
            _hashCode += getACTIVIDADEMPRESA().hashCode();
        }
        if (getAPELLIDOCONYUGE() != null) {
            _hashCode += getAPELLIDOCONYUGE().hashCode();
        }
        if (getCARGO() != null) {
            _hashCode += getCARGO().hashCode();
        }
        if (getDIRECCIONEMPRESA() != null) {
            _hashCode += getDIRECCIONEMPRESA().hashCode();
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getFAX() != null) {
            _hashCode += getFAX().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHANACIMIENTO() != null) {
            _hashCode += getFECHANACIMIENTO().hashCode();
        }
        if (getGENERO() != null) {
            _hashCode += getGENERO().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getLUGARNACIMIENTO() != null) {
            _hashCode += getLUGARNACIMIENTO().hashCode();
        }
        if (getNACIONALIDAD() != null) {
            _hashCode += getNACIONALIDAD().hashCode();
        }
        if (getNOMBRECONYUGE() != null) {
            _hashCode += getNOMBRECONYUGE().hashCode();
        }
        if (getNOMBREEMPRESA() != null) {
            _hashCode += getNOMBREEMPRESA().hashCode();
        }
        if (getTELEFONOEMPRESA() != null) {
            _hashCode += getTELEFONOEMPRESA().hashCode();
        }
        if (getTITULO() != null) {
            _hashCode += getTITULO().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PERSONA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PERSONA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVIDADEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADEMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APELLIDOCONYUGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "APELLIDOCONYUGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CARGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CARGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIRECCIONEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCIONEMPRESA"));
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
        elemField.setFieldName("FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("FECHANACIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHANACIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GENERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "GENERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("LUGARNACIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LUGARNACIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NACIONALIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRECONYUGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRECONYUGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBREEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBREEMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONOEMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONOEMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TITULO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TITULO"));
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
