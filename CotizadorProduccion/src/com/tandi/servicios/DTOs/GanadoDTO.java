/**
 * GanadoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class GanadoDTO  extends com.tandi.servicios.DTOs.ItemDTO  implements java.io.Serializable {
    private java.lang.String areteoNombre;

    private java.lang.String color;

    private int edad;

    private java.lang.String ganadoId;

    private java.lang.String propiedadId;

    private java.lang.String razaId;

    private java.lang.String sierraoCosta;

    private java.lang.String tipoGanadoId;

    private java.math.BigDecimal valorAseguradoGanado;

    private java.math.BigDecimal valorPrimaGanado;

    public GanadoDTO() {
    }

    public GanadoDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado,
           java.lang.String areteoNombre,
           java.lang.String color,
           int edad,
           java.lang.String ganadoId,
           java.lang.String propiedadId,
           java.lang.String razaId,
           java.lang.String sierraoCosta,
           java.lang.String tipoGanadoId,
           java.math.BigDecimal valorAseguradoGanado,
           java.math.BigDecimal valorPrimaGanado) {
        super(
            claseRiesgoId,
            id,
            texto,
            tipoItemId,
            tipoRiesgoId,
            valorAsegurado);
        this.areteoNombre = areteoNombre;
        this.color = color;
        this.edad = edad;
        this.ganadoId = ganadoId;
        this.propiedadId = propiedadId;
        this.razaId = razaId;
        this.sierraoCosta = sierraoCosta;
        this.tipoGanadoId = tipoGanadoId;
        this.valorAseguradoGanado = valorAseguradoGanado;
        this.valorPrimaGanado = valorPrimaGanado;
    }


    /**
     * Gets the areteoNombre value for this GanadoDTO.
     * 
     * @return areteoNombre
     */
    public java.lang.String getAreteoNombre() {
        return areteoNombre;
    }


    /**
     * Sets the areteoNombre value for this GanadoDTO.
     * 
     * @param areteoNombre
     */
    public void setAreteoNombre(java.lang.String areteoNombre) {
        this.areteoNombre = areteoNombre;
    }


    /**
     * Gets the color value for this GanadoDTO.
     * 
     * @return color
     */
    public java.lang.String getColor() {
        return color;
    }


    /**
     * Sets the color value for this GanadoDTO.
     * 
     * @param color
     */
    public void setColor(java.lang.String color) {
        this.color = color;
    }


    /**
     * Gets the edad value for this GanadoDTO.
     * 
     * @return edad
     */
    public int getEdad() {
        return edad;
    }


    /**
     * Sets the edad value for this GanadoDTO.
     * 
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }


    /**
     * Gets the ganadoId value for this GanadoDTO.
     * 
     * @return ganadoId
     */
    public java.lang.String getGanadoId() {
        return ganadoId;
    }


    /**
     * Sets the ganadoId value for this GanadoDTO.
     * 
     * @param ganadoId
     */
    public void setGanadoId(java.lang.String ganadoId) {
        this.ganadoId = ganadoId;
    }


    /**
     * Gets the propiedadId value for this GanadoDTO.
     * 
     * @return propiedadId
     */
    public java.lang.String getPropiedadId() {
        return propiedadId;
    }


    /**
     * Sets the propiedadId value for this GanadoDTO.
     * 
     * @param propiedadId
     */
    public void setPropiedadId(java.lang.String propiedadId) {
        this.propiedadId = propiedadId;
    }


    /**
     * Gets the razaId value for this GanadoDTO.
     * 
     * @return razaId
     */
    public java.lang.String getRazaId() {
        return razaId;
    }


    /**
     * Sets the razaId value for this GanadoDTO.
     * 
     * @param razaId
     */
    public void setRazaId(java.lang.String razaId) {
        this.razaId = razaId;
    }


    /**
     * Gets the sierraoCosta value for this GanadoDTO.
     * 
     * @return sierraoCosta
     */
    public java.lang.String getSierraoCosta() {
        return sierraoCosta;
    }


    /**
     * Sets the sierraoCosta value for this GanadoDTO.
     * 
     * @param sierraoCosta
     */
    public void setSierraoCosta(java.lang.String sierraoCosta) {
        this.sierraoCosta = sierraoCosta;
    }


    /**
     * Gets the tipoGanadoId value for this GanadoDTO.
     * 
     * @return tipoGanadoId
     */
    public java.lang.String getTipoGanadoId() {
        return tipoGanadoId;
    }


    /**
     * Sets the tipoGanadoId value for this GanadoDTO.
     * 
     * @param tipoGanadoId
     */
    public void setTipoGanadoId(java.lang.String tipoGanadoId) {
        this.tipoGanadoId = tipoGanadoId;
    }


    /**
     * Gets the valorAseguradoGanado value for this GanadoDTO.
     * 
     * @return valorAseguradoGanado
     */
    public java.math.BigDecimal getValorAseguradoGanado() {
        return valorAseguradoGanado;
    }


    /**
     * Sets the valorAseguradoGanado value for this GanadoDTO.
     * 
     * @param valorAseguradoGanado
     */
    public void setValorAseguradoGanado(java.math.BigDecimal valorAseguradoGanado) {
        this.valorAseguradoGanado = valorAseguradoGanado;
    }


    /**
     * Gets the valorPrimaGanado value for this GanadoDTO.
     * 
     * @return valorPrimaGanado
     */
    public java.math.BigDecimal getValorPrimaGanado() {
        return valorPrimaGanado;
    }


    /**
     * Sets the valorPrimaGanado value for this GanadoDTO.
     * 
     * @param valorPrimaGanado
     */
    public void setValorPrimaGanado(java.math.BigDecimal valorPrimaGanado) {
        this.valorPrimaGanado = valorPrimaGanado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GanadoDTO)) return false;
        GanadoDTO other = (GanadoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.areteoNombre==null && other.getAreteoNombre()==null) || 
             (this.areteoNombre!=null &&
              this.areteoNombre.equals(other.getAreteoNombre()))) &&
            ((this.color==null && other.getColor()==null) || 
             (this.color!=null &&
              this.color.equals(other.getColor()))) &&
            this.edad == other.getEdad() &&
            ((this.ganadoId==null && other.getGanadoId()==null) || 
             (this.ganadoId!=null &&
              this.ganadoId.equals(other.getGanadoId()))) &&
            ((this.propiedadId==null && other.getPropiedadId()==null) || 
             (this.propiedadId!=null &&
              this.propiedadId.equals(other.getPropiedadId()))) &&
            ((this.razaId==null && other.getRazaId()==null) || 
             (this.razaId!=null &&
              this.razaId.equals(other.getRazaId()))) &&
            ((this.sierraoCosta==null && other.getSierraoCosta()==null) || 
             (this.sierraoCosta!=null &&
              this.sierraoCosta.equals(other.getSierraoCosta()))) &&
            ((this.tipoGanadoId==null && other.getTipoGanadoId()==null) || 
             (this.tipoGanadoId!=null &&
              this.tipoGanadoId.equals(other.getTipoGanadoId()))) &&
            ((this.valorAseguradoGanado==null && other.getValorAseguradoGanado()==null) || 
             (this.valorAseguradoGanado!=null &&
              this.valorAseguradoGanado.equals(other.getValorAseguradoGanado()))) &&
            ((this.valorPrimaGanado==null && other.getValorPrimaGanado()==null) || 
             (this.valorPrimaGanado!=null &&
              this.valorPrimaGanado.equals(other.getValorPrimaGanado())));
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
        if (getAreteoNombre() != null) {
            _hashCode += getAreteoNombre().hashCode();
        }
        if (getColor() != null) {
            _hashCode += getColor().hashCode();
        }
        _hashCode += getEdad();
        if (getGanadoId() != null) {
            _hashCode += getGanadoId().hashCode();
        }
        if (getPropiedadId() != null) {
            _hashCode += getPropiedadId().hashCode();
        }
        if (getRazaId() != null) {
            _hashCode += getRazaId().hashCode();
        }
        if (getSierraoCosta() != null) {
            _hashCode += getSierraoCosta().hashCode();
        }
        if (getTipoGanadoId() != null) {
            _hashCode += getTipoGanadoId().hashCode();
        }
        if (getValorAseguradoGanado() != null) {
            _hashCode += getValorAseguradoGanado().hashCode();
        }
        if (getValorPrimaGanado() != null) {
            _hashCode += getValorPrimaGanado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GanadoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "GanadoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areteoNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areteoNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("color");
        elemField.setXmlName(new javax.xml.namespace.QName("", "color"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "edad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ganadoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ganadoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propiedadId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propiedadId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "razaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sierraoCosta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sierraoCosta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoGanadoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoGanadoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAseguradoGanado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAseguradoGanado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaGanado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaGanado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
