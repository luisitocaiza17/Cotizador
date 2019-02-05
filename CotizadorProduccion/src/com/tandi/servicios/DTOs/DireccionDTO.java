/**
 * DireccionDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class DireccionDTO  implements java.io.Serializable {
    private java.lang.String callePrincipal;

    private java.lang.String calleSecundaria;

    private java.lang.String cantonId;

    private java.lang.String ciudadId;

    private java.lang.String direccion;

    private java.lang.String numero;

    private java.lang.String paisId;

    private java.lang.String parroquiaId;

    private java.lang.String provinciaId;

    private java.lang.String tipoDireccion;

    public DireccionDTO() {
    }

    public DireccionDTO(
           java.lang.String callePrincipal,
           java.lang.String calleSecundaria,
           java.lang.String cantonId,
           java.lang.String ciudadId,
           java.lang.String direccion,
           java.lang.String numero,
           java.lang.String paisId,
           java.lang.String parroquiaId,
           java.lang.String provinciaId,
           java.lang.String tipoDireccion) {
           this.callePrincipal = callePrincipal;
           this.calleSecundaria = calleSecundaria;
           this.cantonId = cantonId;
           this.ciudadId = ciudadId;
           this.direccion = direccion;
           this.numero = numero;
           this.paisId = paisId;
           this.parroquiaId = parroquiaId;
           this.provinciaId = provinciaId;
           this.tipoDireccion = tipoDireccion;
    }


    /**
     * Gets the callePrincipal value for this DireccionDTO.
     * 
     * @return callePrincipal
     */
    public java.lang.String getCallePrincipal() {
        return callePrincipal;
    }


    /**
     * Sets the callePrincipal value for this DireccionDTO.
     * 
     * @param callePrincipal
     */
    public void setCallePrincipal(java.lang.String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }


    /**
     * Gets the calleSecundaria value for this DireccionDTO.
     * 
     * @return calleSecundaria
     */
    public java.lang.String getCalleSecundaria() {
        return calleSecundaria;
    }


    /**
     * Sets the calleSecundaria value for this DireccionDTO.
     * 
     * @param calleSecundaria
     */
    public void setCalleSecundaria(java.lang.String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }


    /**
     * Gets the cantonId value for this DireccionDTO.
     * 
     * @return cantonId
     */
    public java.lang.String getCantonId() {
        return cantonId;
    }


    /**
     * Sets the cantonId value for this DireccionDTO.
     * 
     * @param cantonId
     */
    public void setCantonId(java.lang.String cantonId) {
        this.cantonId = cantonId;
    }


    /**
     * Gets the ciudadId value for this DireccionDTO.
     * 
     * @return ciudadId
     */
    public java.lang.String getCiudadId() {
        return ciudadId;
    }


    /**
     * Sets the ciudadId value for this DireccionDTO.
     * 
     * @param ciudadId
     */
    public void setCiudadId(java.lang.String ciudadId) {
        this.ciudadId = ciudadId;
    }


    /**
     * Gets the direccion value for this DireccionDTO.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this DireccionDTO.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the numero value for this DireccionDTO.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this DireccionDTO.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the paisId value for this DireccionDTO.
     * 
     * @return paisId
     */
    public java.lang.String getPaisId() {
        return paisId;
    }


    /**
     * Sets the paisId value for this DireccionDTO.
     * 
     * @param paisId
     */
    public void setPaisId(java.lang.String paisId) {
        this.paisId = paisId;
    }


    /**
     * Gets the parroquiaId value for this DireccionDTO.
     * 
     * @return parroquiaId
     */
    public java.lang.String getParroquiaId() {
        return parroquiaId;
    }


    /**
     * Sets the parroquiaId value for this DireccionDTO.
     * 
     * @param parroquiaId
     */
    public void setParroquiaId(java.lang.String parroquiaId) {
        this.parroquiaId = parroquiaId;
    }


    /**
     * Gets the provinciaId value for this DireccionDTO.
     * 
     * @return provinciaId
     */
    public java.lang.String getProvinciaId() {
        return provinciaId;
    }


    /**
     * Sets the provinciaId value for this DireccionDTO.
     * 
     * @param provinciaId
     */
    public void setProvinciaId(java.lang.String provinciaId) {
        this.provinciaId = provinciaId;
    }


    /**
     * Gets the tipoDireccion value for this DireccionDTO.
     * 
     * @return tipoDireccion
     */
    public java.lang.String getTipoDireccion() {
        return tipoDireccion;
    }


    /**
     * Sets the tipoDireccion value for this DireccionDTO.
     * 
     * @param tipoDireccion
     */
    public void setTipoDireccion(java.lang.String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DireccionDTO)) return false;
        DireccionDTO other = (DireccionDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.callePrincipal==null && other.getCallePrincipal()==null) || 
             (this.callePrincipal!=null &&
              this.callePrincipal.equals(other.getCallePrincipal()))) &&
            ((this.calleSecundaria==null && other.getCalleSecundaria()==null) || 
             (this.calleSecundaria!=null &&
              this.calleSecundaria.equals(other.getCalleSecundaria()))) &&
            ((this.cantonId==null && other.getCantonId()==null) || 
             (this.cantonId!=null &&
              this.cantonId.equals(other.getCantonId()))) &&
            ((this.ciudadId==null && other.getCiudadId()==null) || 
             (this.ciudadId!=null &&
              this.ciudadId.equals(other.getCiudadId()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.paisId==null && other.getPaisId()==null) || 
             (this.paisId!=null &&
              this.paisId.equals(other.getPaisId()))) &&
            ((this.parroquiaId==null && other.getParroquiaId()==null) || 
             (this.parroquiaId!=null &&
              this.parroquiaId.equals(other.getParroquiaId()))) &&
            ((this.provinciaId==null && other.getProvinciaId()==null) || 
             (this.provinciaId!=null &&
              this.provinciaId.equals(other.getProvinciaId()))) &&
            ((this.tipoDireccion==null && other.getTipoDireccion()==null) || 
             (this.tipoDireccion!=null &&
              this.tipoDireccion.equals(other.getTipoDireccion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCallePrincipal() != null) {
            _hashCode += getCallePrincipal().hashCode();
        }
        if (getCalleSecundaria() != null) {
            _hashCode += getCalleSecundaria().hashCode();
        }
        if (getCantonId() != null) {
            _hashCode += getCantonId().hashCode();
        }
        if (getCiudadId() != null) {
            _hashCode += getCiudadId().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getPaisId() != null) {
            _hashCode += getPaisId().hashCode();
        }
        if (getParroquiaId() != null) {
            _hashCode += getParroquiaId().hashCode();
        }
        if (getProvinciaId() != null) {
            _hashCode += getProvinciaId().hashCode();
        }
        if (getTipoDireccion() != null) {
            _hashCode += getTipoDireccion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DireccionDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DireccionDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callePrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "callePrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calleSecundaria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "calleSecundaria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cantonId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cantonId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudadId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ciudadId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paisId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paisId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parroquiaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parroquiaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provinciaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "provinciaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDireccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoDireccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
