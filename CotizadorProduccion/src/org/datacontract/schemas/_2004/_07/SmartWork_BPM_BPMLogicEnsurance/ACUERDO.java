/**
 * ACUERDO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ACUERDO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ACTIVO;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHACREACION;

    private java.lang.String ID;

    private java.lang.String NOMBRE;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO;

    private java.lang.String TIPOACUERDOID;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String USUARIOCREACION;

    private java.util.Calendar VIGENCIADESDE;

    private java.util.Calendar VIGENCIAHASTA;

    public ACUERDO() {
    }

    public ACUERDO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ACTIVO,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHACREACION,
           java.lang.String ID,
           java.lang.String NOMBRE,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO,
           java.lang.String TIPOACUERDOID,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String USUARIOCREACION,
           java.util.Calendar VIGENCIADESDE,
           java.util.Calendar VIGENCIAHASTA) {
        super(
            id,
            ref,
            entityKey);
        this.ACTIVO = ACTIVO;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHACREACION = FECHACREACION;
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.ROLENTIDADACUERDO = ROLENTIDADACUERDO;
        this.TIPOACUERDOID = TIPOACUERDOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.USUARIOCREACION = USUARIOCREACION;
        this.VIGENCIADESDE = VIGENCIADESDE;
        this.VIGENCIAHASTA = VIGENCIAHASTA;
    }


    /**
     * Gets the ACTIVO value for this ACUERDO.
     * 
     * @return ACTIVO
     */
    public java.lang.String getACTIVO() {
        return ACTIVO;
    }


    /**
     * Sets the ACTIVO value for this ACUERDO.
     * 
     * @param ACTIVO
     */
    public void setACTIVO(java.lang.String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ACUERDO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ACUERDO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHACREACION value for this ACUERDO.
     * 
     * @return FECHACREACION
     */
    public java.util.Calendar getFECHACREACION() {
        return FECHACREACION;
    }


    /**
     * Sets the FECHACREACION value for this ACUERDO.
     * 
     * @param FECHACREACION
     */
    public void setFECHACREACION(java.util.Calendar FECHACREACION) {
        this.FECHACREACION = FECHACREACION;
    }


    /**
     * Gets the ID value for this ACUERDO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ACUERDO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the NOMBRE value for this ACUERDO.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this ACUERDO.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the ROLENTIDADACUERDO value for this ACUERDO.
     * 
     * @return ROLENTIDADACUERDO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] getROLENTIDADACUERDO() {
        return ROLENTIDADACUERDO;
    }


    /**
     * Sets the ROLENTIDADACUERDO value for this ACUERDO.
     * 
     * @param ROLENTIDADACUERDO
     */
    public void setROLENTIDADACUERDO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[] ROLENTIDADACUERDO) {
        this.ROLENTIDADACUERDO = ROLENTIDADACUERDO;
    }


    /**
     * Gets the TIPOACUERDOID value for this ACUERDO.
     * 
     * @return TIPOACUERDOID
     */
    public java.lang.String getTIPOACUERDOID() {
        return TIPOACUERDOID;
    }


    /**
     * Sets the TIPOACUERDOID value for this ACUERDO.
     * 
     * @param TIPOACUERDOID
     */
    public void setTIPOACUERDOID(java.lang.String TIPOACUERDOID) {
        this.TIPOACUERDOID = TIPOACUERDOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ACUERDO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ACUERDO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the USUARIOCREACION value for this ACUERDO.
     * 
     * @return USUARIOCREACION
     */
    public java.lang.String getUSUARIOCREACION() {
        return USUARIOCREACION;
    }


    /**
     * Sets the USUARIOCREACION value for this ACUERDO.
     * 
     * @param USUARIOCREACION
     */
    public void setUSUARIOCREACION(java.lang.String USUARIOCREACION) {
        this.USUARIOCREACION = USUARIOCREACION;
    }


    /**
     * Gets the VIGENCIADESDE value for this ACUERDO.
     * 
     * @return VIGENCIADESDE
     */
    public java.util.Calendar getVIGENCIADESDE() {
        return VIGENCIADESDE;
    }


    /**
     * Sets the VIGENCIADESDE value for this ACUERDO.
     * 
     * @param VIGENCIADESDE
     */
    public void setVIGENCIADESDE(java.util.Calendar VIGENCIADESDE) {
        this.VIGENCIADESDE = VIGENCIADESDE;
    }


    /**
     * Gets the VIGENCIAHASTA value for this ACUERDO.
     * 
     * @return VIGENCIAHASTA
     */
    public java.util.Calendar getVIGENCIAHASTA() {
        return VIGENCIAHASTA;
    }


    /**
     * Sets the VIGENCIAHASTA value for this ACUERDO.
     * 
     * @param VIGENCIAHASTA
     */
    public void setVIGENCIAHASTA(java.util.Calendar VIGENCIAHASTA) {
        this.VIGENCIAHASTA = VIGENCIAHASTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ACUERDO)) return false;
        ACUERDO other = (ACUERDO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACTIVO==null && other.getACTIVO()==null) || 
             (this.ACTIVO!=null &&
              this.ACTIVO.equals(other.getACTIVO()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHACREACION==null && other.getFECHACREACION()==null) || 
             (this.FECHACREACION!=null &&
              this.FECHACREACION.equals(other.getFECHACREACION()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.ROLENTIDADACUERDO==null && other.getROLENTIDADACUERDO()==null) || 
             (this.ROLENTIDADACUERDO!=null &&
              java.util.Arrays.equals(this.ROLENTIDADACUERDO, other.getROLENTIDADACUERDO()))) &&
            ((this.TIPOACUERDOID==null && other.getTIPOACUERDOID()==null) || 
             (this.TIPOACUERDOID!=null &&
              this.TIPOACUERDOID.equals(other.getTIPOACUERDOID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.USUARIOCREACION==null && other.getUSUARIOCREACION()==null) || 
             (this.USUARIOCREACION!=null &&
              this.USUARIOCREACION.equals(other.getUSUARIOCREACION()))) &&
            ((this.VIGENCIADESDE==null && other.getVIGENCIADESDE()==null) || 
             (this.VIGENCIADESDE!=null &&
              this.VIGENCIADESDE.equals(other.getVIGENCIADESDE()))) &&
            ((this.VIGENCIAHASTA==null && other.getVIGENCIAHASTA()==null) || 
             (this.VIGENCIAHASTA!=null &&
              this.VIGENCIAHASTA.equals(other.getVIGENCIAHASTA())));
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
        if (getACTIVO() != null) {
            _hashCode += getACTIVO().hashCode();
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
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
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
        if (getTIPOACUERDOID() != null) {
            _hashCode += getTIPOACUERDOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getUSUARIOCREACION() != null) {
            _hashCode += getUSUARIOCREACION().hashCode();
        }
        if (getVIGENCIADESDE() != null) {
            _hashCode += getVIGENCIADESDE().hashCode();
        }
        if (getVIGENCIAHASTA() != null) {
            _hashCode += getVIGENCIAHASTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ACUERDO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVO"));
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
        elemField.setFieldName("NOMBRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRE"));
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
        elemField.setFieldName("TIPOACUERDOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOACUERDOID"));
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
        elemField.setFieldName("USUARIOCREACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOCREACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VIGENCIADESDE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VIGENCIADESDE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VIGENCIAHASTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VIGENCIAHASTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
