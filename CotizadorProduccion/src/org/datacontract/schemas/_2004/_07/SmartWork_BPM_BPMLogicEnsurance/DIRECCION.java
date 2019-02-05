/**
 * DIRECCION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class DIRECCION  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String CANTONID;

    private java.lang.String CIUDADID;

    private java.lang.String ESTADOINFORMACION;

    private java.lang.String FAX;

    private java.util.Calendar FECHAACTUALIZA;

    private java.lang.String GPSX;

    private java.lang.String GPSY;

    private java.lang.String ID;

    private java.lang.String NOMBRE;

    private java.lang.String NOMBREOPCIONAL;

    private java.lang.String NOMBREPRINCIPAL;

    private java.lang.String NOMBRESECUNDARIA;

    private java.lang.String NUMERO;

    private java.lang.String PAISID;

    private java.lang.String PARROQUIAID;

    private java.lang.String PROVINCIAID;

    private java.lang.String SITIO;

    private java.lang.String TELEFONO1;

    private java.lang.String TELEFONO2;

    private java.lang.String TELEFONO3;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String ZONA;

    public DIRECCION() {
    }

    public DIRECCION(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String CANTONID,
           java.lang.String CIUDADID,
           java.lang.String ESTADOINFORMACION,
           java.lang.String FAX,
           java.util.Calendar FECHAACTUALIZA,
           java.lang.String GPSX,
           java.lang.String GPSY,
           java.lang.String ID,
           java.lang.String NOMBRE,
           java.lang.String NOMBREOPCIONAL,
           java.lang.String NOMBREPRINCIPAL,
           java.lang.String NOMBRESECUNDARIA,
           java.lang.String NUMERO,
           java.lang.String PAISID,
           java.lang.String PARROQUIAID,
           java.lang.String PROVINCIAID,
           java.lang.String SITIO,
           java.lang.String TELEFONO1,
           java.lang.String TELEFONO2,
           java.lang.String TELEFONO3,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String ZONA) {
        super(
            id,
            ref,
            entityKey);
        this.CANTONID = CANTONID;
        this.CIUDADID = CIUDADID;
        this.ESTADOINFORMACION = ESTADOINFORMACION;
        this.FAX = FAX;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.GPSX = GPSX;
        this.GPSY = GPSY;
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.NOMBREOPCIONAL = NOMBREOPCIONAL;
        this.NOMBREPRINCIPAL = NOMBREPRINCIPAL;
        this.NOMBRESECUNDARIA = NOMBRESECUNDARIA;
        this.NUMERO = NUMERO;
        this.PAISID = PAISID;
        this.PARROQUIAID = PARROQUIAID;
        this.PROVINCIAID = PROVINCIAID;
        this.SITIO = SITIO;
        this.TELEFONO1 = TELEFONO1;
        this.TELEFONO2 = TELEFONO2;
        this.TELEFONO3 = TELEFONO3;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.ZONA = ZONA;
    }


    /**
     * Gets the CANTONID value for this DIRECCION.
     * 
     * @return CANTONID
     */
    public java.lang.String getCANTONID() {
        return CANTONID;
    }


    /**
     * Sets the CANTONID value for this DIRECCION.
     * 
     * @param CANTONID
     */
    public void setCANTONID(java.lang.String CANTONID) {
        this.CANTONID = CANTONID;
    }


    /**
     * Gets the CIUDADID value for this DIRECCION.
     * 
     * @return CIUDADID
     */
    public java.lang.String getCIUDADID() {
        return CIUDADID;
    }


    /**
     * Sets the CIUDADID value for this DIRECCION.
     * 
     * @param CIUDADID
     */
    public void setCIUDADID(java.lang.String CIUDADID) {
        this.CIUDADID = CIUDADID;
    }


    /**
     * Gets the ESTADOINFORMACION value for this DIRECCION.
     * 
     * @return ESTADOINFORMACION
     */
    public java.lang.String getESTADOINFORMACION() {
        return ESTADOINFORMACION;
    }


    /**
     * Sets the ESTADOINFORMACION value for this DIRECCION.
     * 
     * @param ESTADOINFORMACION
     */
    public void setESTADOINFORMACION(java.lang.String ESTADOINFORMACION) {
        this.ESTADOINFORMACION = ESTADOINFORMACION;
    }


    /**
     * Gets the FAX value for this DIRECCION.
     * 
     * @return FAX
     */
    public java.lang.String getFAX() {
        return FAX;
    }


    /**
     * Sets the FAX value for this DIRECCION.
     * 
     * @param FAX
     */
    public void setFAX(java.lang.String FAX) {
        this.FAX = FAX;
    }


    /**
     * Gets the FECHAACTUALIZA value for this DIRECCION.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this DIRECCION.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the GPSX value for this DIRECCION.
     * 
     * @return GPSX
     */
    public java.lang.String getGPSX() {
        return GPSX;
    }


    /**
     * Sets the GPSX value for this DIRECCION.
     * 
     * @param GPSX
     */
    public void setGPSX(java.lang.String GPSX) {
        this.GPSX = GPSX;
    }


    /**
     * Gets the GPSY value for this DIRECCION.
     * 
     * @return GPSY
     */
    public java.lang.String getGPSY() {
        return GPSY;
    }


    /**
     * Sets the GPSY value for this DIRECCION.
     * 
     * @param GPSY
     */
    public void setGPSY(java.lang.String GPSY) {
        this.GPSY = GPSY;
    }


    /**
     * Gets the ID value for this DIRECCION.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DIRECCION.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NOMBRE value for this DIRECCION.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this DIRECCION.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the NOMBREOPCIONAL value for this DIRECCION.
     * 
     * @return NOMBREOPCIONAL
     */
    public java.lang.String getNOMBREOPCIONAL() {
        return NOMBREOPCIONAL;
    }


    /**
     * Sets the NOMBREOPCIONAL value for this DIRECCION.
     * 
     * @param NOMBREOPCIONAL
     */
    public void setNOMBREOPCIONAL(java.lang.String NOMBREOPCIONAL) {
        this.NOMBREOPCIONAL = NOMBREOPCIONAL;
    }


    /**
     * Gets the NOMBREPRINCIPAL value for this DIRECCION.
     * 
     * @return NOMBREPRINCIPAL
     */
    public java.lang.String getNOMBREPRINCIPAL() {
        return NOMBREPRINCIPAL;
    }


    /**
     * Sets the NOMBREPRINCIPAL value for this DIRECCION.
     * 
     * @param NOMBREPRINCIPAL
     */
    public void setNOMBREPRINCIPAL(java.lang.String NOMBREPRINCIPAL) {
        this.NOMBREPRINCIPAL = NOMBREPRINCIPAL;
    }


    /**
     * Gets the NOMBRESECUNDARIA value for this DIRECCION.
     * 
     * @return NOMBRESECUNDARIA
     */
    public java.lang.String getNOMBRESECUNDARIA() {
        return NOMBRESECUNDARIA;
    }


    /**
     * Sets the NOMBRESECUNDARIA value for this DIRECCION.
     * 
     * @param NOMBRESECUNDARIA
     */
    public void setNOMBRESECUNDARIA(java.lang.String NOMBRESECUNDARIA) {
        this.NOMBRESECUNDARIA = NOMBRESECUNDARIA;
    }


    /**
     * Gets the NUMERO value for this DIRECCION.
     * 
     * @return NUMERO
     */
    public java.lang.String getNUMERO() {
        return NUMERO;
    }


    /**
     * Sets the NUMERO value for this DIRECCION.
     * 
     * @param NUMERO
     */
    public void setNUMERO(java.lang.String NUMERO) {
        this.NUMERO = NUMERO;
    }


    /**
     * Gets the PAISID value for this DIRECCION.
     * 
     * @return PAISID
     */
    public java.lang.String getPAISID() {
        return PAISID;
    }


    /**
     * Sets the PAISID value for this DIRECCION.
     * 
     * @param PAISID
     */
    public void setPAISID(java.lang.String PAISID) {
        this.PAISID = PAISID;
    }


    /**
     * Gets the PARROQUIAID value for this DIRECCION.
     * 
     * @return PARROQUIAID
     */
    public java.lang.String getPARROQUIAID() {
        return PARROQUIAID;
    }


    /**
     * Sets the PARROQUIAID value for this DIRECCION.
     * 
     * @param PARROQUIAID
     */
    public void setPARROQUIAID(java.lang.String PARROQUIAID) {
        this.PARROQUIAID = PARROQUIAID;
    }


    /**
     * Gets the PROVINCIAID value for this DIRECCION.
     * 
     * @return PROVINCIAID
     */
    public java.lang.String getPROVINCIAID() {
        return PROVINCIAID;
    }


    /**
     * Sets the PROVINCIAID value for this DIRECCION.
     * 
     * @param PROVINCIAID
     */
    public void setPROVINCIAID(java.lang.String PROVINCIAID) {
        this.PROVINCIAID = PROVINCIAID;
    }


    /**
     * Gets the SITIO value for this DIRECCION.
     * 
     * @return SITIO
     */
    public java.lang.String getSITIO() {
        return SITIO;
    }


    /**
     * Sets the SITIO value for this DIRECCION.
     * 
     * @param SITIO
     */
    public void setSITIO(java.lang.String SITIO) {
        this.SITIO = SITIO;
    }


    /**
     * Gets the TELEFONO1 value for this DIRECCION.
     * 
     * @return TELEFONO1
     */
    public java.lang.String getTELEFONO1() {
        return TELEFONO1;
    }


    /**
     * Sets the TELEFONO1 value for this DIRECCION.
     * 
     * @param TELEFONO1
     */
    public void setTELEFONO1(java.lang.String TELEFONO1) {
        this.TELEFONO1 = TELEFONO1;
    }


    /**
     * Gets the TELEFONO2 value for this DIRECCION.
     * 
     * @return TELEFONO2
     */
    public java.lang.String getTELEFONO2() {
        return TELEFONO2;
    }


    /**
     * Sets the TELEFONO2 value for this DIRECCION.
     * 
     * @param TELEFONO2
     */
    public void setTELEFONO2(java.lang.String TELEFONO2) {
        this.TELEFONO2 = TELEFONO2;
    }


    /**
     * Gets the TELEFONO3 value for this DIRECCION.
     * 
     * @return TELEFONO3
     */
    public java.lang.String getTELEFONO3() {
        return TELEFONO3;
    }


    /**
     * Sets the TELEFONO3 value for this DIRECCION.
     * 
     * @param TELEFONO3
     */
    public void setTELEFONO3(java.lang.String TELEFONO3) {
        this.TELEFONO3 = TELEFONO3;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this DIRECCION.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this DIRECCION.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the ZONA value for this DIRECCION.
     * 
     * @return ZONA
     */
    public java.lang.String getZONA() {
        return ZONA;
    }


    /**
     * Sets the ZONA value for this DIRECCION.
     * 
     * @param ZONA
     */
    public void setZONA(java.lang.String ZONA) {
        this.ZONA = ZONA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DIRECCION)) return false;
        DIRECCION other = (DIRECCION) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.CANTONID==null && other.getCANTONID()==null) || 
             (this.CANTONID!=null &&
              this.CANTONID.equals(other.getCANTONID()))) &&
            ((this.CIUDADID==null && other.getCIUDADID()==null) || 
             (this.CIUDADID!=null &&
              this.CIUDADID.equals(other.getCIUDADID()))) &&
            ((this.ESTADOINFORMACION==null && other.getESTADOINFORMACION()==null) || 
             (this.ESTADOINFORMACION!=null &&
              this.ESTADOINFORMACION.equals(other.getESTADOINFORMACION()))) &&
            ((this.FAX==null && other.getFAX()==null) || 
             (this.FAX!=null &&
              this.FAX.equals(other.getFAX()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.GPSX==null && other.getGPSX()==null) || 
             (this.GPSX!=null &&
              this.GPSX.equals(other.getGPSX()))) &&
            ((this.GPSY==null && other.getGPSY()==null) || 
             (this.GPSY!=null &&
              this.GPSY.equals(other.getGPSY()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.NOMBREOPCIONAL==null && other.getNOMBREOPCIONAL()==null) || 
             (this.NOMBREOPCIONAL!=null &&
              this.NOMBREOPCIONAL.equals(other.getNOMBREOPCIONAL()))) &&
            ((this.NOMBREPRINCIPAL==null && other.getNOMBREPRINCIPAL()==null) || 
             (this.NOMBREPRINCIPAL!=null &&
              this.NOMBREPRINCIPAL.equals(other.getNOMBREPRINCIPAL()))) &&
            ((this.NOMBRESECUNDARIA==null && other.getNOMBRESECUNDARIA()==null) || 
             (this.NOMBRESECUNDARIA!=null &&
              this.NOMBRESECUNDARIA.equals(other.getNOMBRESECUNDARIA()))) &&
            ((this.NUMERO==null && other.getNUMERO()==null) || 
             (this.NUMERO!=null &&
              this.NUMERO.equals(other.getNUMERO()))) &&
            ((this.PAISID==null && other.getPAISID()==null) || 
             (this.PAISID!=null &&
              this.PAISID.equals(other.getPAISID()))) &&
            ((this.PARROQUIAID==null && other.getPARROQUIAID()==null) || 
             (this.PARROQUIAID!=null &&
              this.PARROQUIAID.equals(other.getPARROQUIAID()))) &&
            ((this.PROVINCIAID==null && other.getPROVINCIAID()==null) || 
             (this.PROVINCIAID!=null &&
              this.PROVINCIAID.equals(other.getPROVINCIAID()))) &&
            ((this.SITIO==null && other.getSITIO()==null) || 
             (this.SITIO!=null &&
              this.SITIO.equals(other.getSITIO()))) &&
            ((this.TELEFONO1==null && other.getTELEFONO1()==null) || 
             (this.TELEFONO1!=null &&
              this.TELEFONO1.equals(other.getTELEFONO1()))) &&
            ((this.TELEFONO2==null && other.getTELEFONO2()==null) || 
             (this.TELEFONO2!=null &&
              this.TELEFONO2.equals(other.getTELEFONO2()))) &&
            ((this.TELEFONO3==null && other.getTELEFONO3()==null) || 
             (this.TELEFONO3!=null &&
              this.TELEFONO3.equals(other.getTELEFONO3()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.ZONA==null && other.getZONA()==null) || 
             (this.ZONA!=null &&
              this.ZONA.equals(other.getZONA())));
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
        if (getCANTONID() != null) {
            _hashCode += getCANTONID().hashCode();
        }
        if (getCIUDADID() != null) {
            _hashCode += getCIUDADID().hashCode();
        }
        if (getESTADOINFORMACION() != null) {
            _hashCode += getESTADOINFORMACION().hashCode();
        }
        if (getFAX() != null) {
            _hashCode += getFAX().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getGPSX() != null) {
            _hashCode += getGPSX().hashCode();
        }
        if (getGPSY() != null) {
            _hashCode += getGPSY().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getNOMBREOPCIONAL() != null) {
            _hashCode += getNOMBREOPCIONAL().hashCode();
        }
        if (getNOMBREPRINCIPAL() != null) {
            _hashCode += getNOMBREPRINCIPAL().hashCode();
        }
        if (getNOMBRESECUNDARIA() != null) {
            _hashCode += getNOMBRESECUNDARIA().hashCode();
        }
        if (getNUMERO() != null) {
            _hashCode += getNUMERO().hashCode();
        }
        if (getPAISID() != null) {
            _hashCode += getPAISID().hashCode();
        }
        if (getPARROQUIAID() != null) {
            _hashCode += getPARROQUIAID().hashCode();
        }
        if (getPROVINCIAID() != null) {
            _hashCode += getPROVINCIAID().hashCode();
        }
        if (getSITIO() != null) {
            _hashCode += getSITIO().hashCode();
        }
        if (getTELEFONO1() != null) {
            _hashCode += getTELEFONO1().hashCode();
        }
        if (getTELEFONO2() != null) {
            _hashCode += getTELEFONO2().hashCode();
        }
        if (getTELEFONO3() != null) {
            _hashCode += getTELEFONO3().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getZONA() != null) {
            _hashCode += getZONA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DIRECCION.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANTONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTONID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIUDADID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDADID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOINFORMACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADOINFORMACION"));
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
        elemField.setFieldName("GPSX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "GPSX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GPSY");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "GPSY"));
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
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBREOPCIONAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBREOPCIONAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBREPRINCIPAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBREPRINCIPAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRESECUNDARIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRESECUNDARIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAISID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAISID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARROQUIAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PARROQUIAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROVINCIAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SITIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SITIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONO1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONO2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONO3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONO3"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZONA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ZONA"));
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
