/**
 * SdtEstado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package SucreAgro7;

public class SdtEstado  implements java.io.Serializable {
    private int autorizacion;

    private java.lang.String diferenciaMonto;

    private java.lang.String estado;

    private java.util.Calendar fechaAprobacion;

    private double montoAprobadoQBE;

    private double montoRecomendadoQBE;

    private java.lang.String numeroCotizacion;

    private java.lang.String observacion;

    private double prima;

    public SdtEstado() {
    }

    public SdtEstado(
           int autorizacion,
           java.lang.String diferenciaMonto,
           java.lang.String estado,
           java.util.Calendar fechaAprobacion,
           double montoAprobadoQBE,
           double montoRecomendadoQBE,
           java.lang.String numeroCotizacion,
           java.lang.String observacion,
           double prima) {
           this.autorizacion = autorizacion;
           this.diferenciaMonto = diferenciaMonto;
           this.estado = estado;
           this.fechaAprobacion = fechaAprobacion;
           this.montoAprobadoQBE = montoAprobadoQBE;
           this.montoRecomendadoQBE = montoRecomendadoQBE;
           this.numeroCotizacion = numeroCotizacion;
           this.observacion = observacion;
           this.prima = prima;
    }


    /**
     * Gets the autorizacion value for this SdtEstado.
     * 
     * @return autorizacion
     */
    public int getAutorizacion() {
        return autorizacion;
    }


    /**
     * Sets the autorizacion value for this SdtEstado.
     * 
     * @param autorizacion
     */
    public void setAutorizacion(int autorizacion) {
        this.autorizacion = autorizacion;
    }


    /**
     * Gets the diferenciaMonto value for this SdtEstado.
     * 
     * @return diferenciaMonto
     */
    public java.lang.String getDiferenciaMonto() {
        return diferenciaMonto;
    }


    /**
     * Sets the diferenciaMonto value for this SdtEstado.
     * 
     * @param diferenciaMonto
     */
    public void setDiferenciaMonto(java.lang.String diferenciaMonto) {
        this.diferenciaMonto = diferenciaMonto;
    }


    /**
     * Gets the estado value for this SdtEstado.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this SdtEstado.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the fechaAprobacion value for this SdtEstado.
     * 
     * @return fechaAprobacion
     */
    public java.util.Calendar getFechaAprobacion() {
        return fechaAprobacion;
    }


    /**
     * Sets the fechaAprobacion value for this SdtEstado.
     * 
     * @param fechaAprobacion
     */
    public void setFechaAprobacion(java.util.Calendar fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }


    /**
     * Gets the montoAprobadoQBE value for this SdtEstado.
     * 
     * @return montoAprobadoQBE
     */
    public double getMontoAprobadoQBE() {
        return montoAprobadoQBE;
    }


    /**
     * Sets the montoAprobadoQBE value for this SdtEstado.
     * 
     * @param montoAprobadoQBE
     */
    public void setMontoAprobadoQBE(double montoAprobadoQBE) {
        this.montoAprobadoQBE = montoAprobadoQBE;
    }


    /**
     * Gets the montoRecomendadoQBE value for this SdtEstado.
     * 
     * @return montoRecomendadoQBE
     */
    public double getMontoRecomendadoQBE() {
        return montoRecomendadoQBE;
    }


    /**
     * Sets the montoRecomendadoQBE value for this SdtEstado.
     * 
     * @param montoRecomendadoQBE
     */
    public void setMontoRecomendadoQBE(double montoRecomendadoQBE) {
        this.montoRecomendadoQBE = montoRecomendadoQBE;
    }


    /**
     * Gets the numeroCotizacion value for this SdtEstado.
     * 
     * @return numeroCotizacion
     */
    public java.lang.String getNumeroCotizacion() {
        return numeroCotizacion;
    }


    /**
     * Sets the numeroCotizacion value for this SdtEstado.
     * 
     * @param numeroCotizacion
     */
    public void setNumeroCotizacion(java.lang.String numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }


    /**
     * Gets the observacion value for this SdtEstado.
     * 
     * @return observacion
     */
    public java.lang.String getObservacion() {
        return observacion;
    }


    /**
     * Sets the observacion value for this SdtEstado.
     * 
     * @param observacion
     */
    public void setObservacion(java.lang.String observacion) {
        this.observacion = observacion;
    }


    /**
     * Gets the prima value for this SdtEstado.
     * 
     * @return prima
     */
    public double getPrima() {
        return prima;
    }


    /**
     * Sets the prima value for this SdtEstado.
     * 
     * @param prima
     */
    public void setPrima(double prima) {
        this.prima = prima;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SdtEstado)) return false;
        SdtEstado other = (SdtEstado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.autorizacion == other.getAutorizacion() &&
            ((this.diferenciaMonto==null && other.getDiferenciaMonto()==null) || 
             (this.diferenciaMonto!=null &&
              this.diferenciaMonto.equals(other.getDiferenciaMonto()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.fechaAprobacion==null && other.getFechaAprobacion()==null) || 
             (this.fechaAprobacion!=null &&
              this.fechaAprobacion.equals(other.getFechaAprobacion()))) &&
            this.montoAprobadoQBE == other.getMontoAprobadoQBE() &&
            this.montoRecomendadoQBE == other.getMontoRecomendadoQBE() &&
            ((this.numeroCotizacion==null && other.getNumeroCotizacion()==null) || 
             (this.numeroCotizacion!=null &&
              this.numeroCotizacion.equals(other.getNumeroCotizacion()))) &&
            ((this.observacion==null && other.getObservacion()==null) || 
             (this.observacion!=null &&
              this.observacion.equals(other.getObservacion()))) &&
            this.prima == other.getPrima();
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
        _hashCode += getAutorizacion();
        if (getDiferenciaMonto() != null) {
            _hashCode += getDiferenciaMonto().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getFechaAprobacion() != null) {
            _hashCode += getFechaAprobacion().hashCode();
        }
        _hashCode += new Double(getMontoAprobadoQBE()).hashCode();
        _hashCode += new Double(getMontoRecomendadoQBE()).hashCode();
        if (getNumeroCotizacion() != null) {
            _hashCode += getNumeroCotizacion().hashCode();
        }
        if (getObservacion() != null) {
            _hashCode += getObservacion().hashCode();
        }
        _hashCode += new Double(getPrima()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SdtEstado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("SucreAgro7", "sdtEstado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autorizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autorizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diferenciaMonto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "diferenciaMonto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaAprobacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaAprobacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoAprobadoQBE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoAprobadoQBE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("montoRecomendadoQBE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "montoRecomendadoQBE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCotizacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroCotizacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "observacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prima");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prima"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
