/**
 * LoteCultivoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class LoteCultivoDTO  extends com.tandi.servicios.DTOs.ItemDTO  implements java.io.Serializable {
    private int diasVigencia;

    private boolean esTecnificado;

    private java.util.Calendar fechaTentativaSiembra;

    private java.util.Calendar finVigenciaCultivo;

    private java.lang.String gpsLoteX;

    private java.lang.String gpsLoteY;

    private java.util.Calendar inicioVigenciaCultivo;

    private java.lang.String loteCultivoId;

    private java.lang.String nombre;

    private double numeroHectareas;

    private double numeroHectareasAsegurables;

    private java.lang.String propiedadId;

    private double tasa;

    private java.lang.String tipoCultivoId;

    private java.math.BigDecimal valorPorHectarea;

    private java.lang.String variedad;

    public LoteCultivoDTO() {
    }

    public LoteCultivoDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado,
           int diasVigencia,
           boolean esTecnificado,
           java.util.Calendar fechaTentativaSiembra,
           java.util.Calendar finVigenciaCultivo,
           java.lang.String gpsLoteX,
           java.lang.String gpsLoteY,
           java.util.Calendar inicioVigenciaCultivo,
           java.lang.String loteCultivoId,
           java.lang.String nombre,
           double numeroHectareas,
           double numeroHectareasAsegurables,
           java.lang.String propiedadId,
           double tasa,
           java.lang.String tipoCultivoId,
           java.math.BigDecimal valorPorHectarea,
           java.lang.String variedad) {
        super(
            claseRiesgoId,
            id,
            texto,
            tipoItemId,
            tipoRiesgoId,
            valorAsegurado);
        this.diasVigencia = diasVigencia;
        this.esTecnificado = esTecnificado;
        this.fechaTentativaSiembra = fechaTentativaSiembra;
        this.finVigenciaCultivo = finVigenciaCultivo;
        this.gpsLoteX = gpsLoteX;
        this.gpsLoteY = gpsLoteY;
        this.inicioVigenciaCultivo = inicioVigenciaCultivo;
        this.loteCultivoId = loteCultivoId;
        this.nombre = nombre;
        this.numeroHectareas = numeroHectareas;
        this.numeroHectareasAsegurables = numeroHectareasAsegurables;
        this.propiedadId = propiedadId;
        this.tasa = tasa;
        this.tipoCultivoId = tipoCultivoId;
        this.valorPorHectarea = valorPorHectarea;
        this.variedad = variedad;
    }


    /**
     * Gets the diasVigencia value for this LoteCultivoDTO.
     * 
     * @return diasVigencia
     */
    public int getDiasVigencia() {
        return diasVigencia;
    }


    /**
     * Sets the diasVigencia value for this LoteCultivoDTO.
     * 
     * @param diasVigencia
     */
    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }


    /**
     * Gets the esTecnificado value for this LoteCultivoDTO.
     * 
     * @return esTecnificado
     */
    public boolean isEsTecnificado() {
        return esTecnificado;
    }


    /**
     * Sets the esTecnificado value for this LoteCultivoDTO.
     * 
     * @param esTecnificado
     */
    public void setEsTecnificado(boolean esTecnificado) {
        this.esTecnificado = esTecnificado;
    }


    /**
     * Gets the fechaTentativaSiembra value for this LoteCultivoDTO.
     * 
     * @return fechaTentativaSiembra
     */
    public java.util.Calendar getFechaTentativaSiembra() {
        return fechaTentativaSiembra;
    }


    /**
     * Sets the fechaTentativaSiembra value for this LoteCultivoDTO.
     * 
     * @param fechaTentativaSiembra
     */
    public void setFechaTentativaSiembra(java.util.Calendar fechaTentativaSiembra) {
        this.fechaTentativaSiembra = fechaTentativaSiembra;
    }


    /**
     * Gets the finVigenciaCultivo value for this LoteCultivoDTO.
     * 
     * @return finVigenciaCultivo
     */
    public java.util.Calendar getFinVigenciaCultivo() {
        return finVigenciaCultivo;
    }


    /**
     * Sets the finVigenciaCultivo value for this LoteCultivoDTO.
     * 
     * @param finVigenciaCultivo
     */
    public void setFinVigenciaCultivo(java.util.Calendar finVigenciaCultivo) {
        this.finVigenciaCultivo = finVigenciaCultivo;
    }


    /**
     * Gets the gpsLoteX value for this LoteCultivoDTO.
     * 
     * @return gpsLoteX
     */
    public java.lang.String getGpsLoteX() {
        return gpsLoteX;
    }


    /**
     * Sets the gpsLoteX value for this LoteCultivoDTO.
     * 
     * @param gpsLoteX
     */
    public void setGpsLoteX(java.lang.String gpsLoteX) {
        this.gpsLoteX = gpsLoteX;
    }


    /**
     * Gets the gpsLoteY value for this LoteCultivoDTO.
     * 
     * @return gpsLoteY
     */
    public java.lang.String getGpsLoteY() {
        return gpsLoteY;
    }


    /**
     * Sets the gpsLoteY value for this LoteCultivoDTO.
     * 
     * @param gpsLoteY
     */
    public void setGpsLoteY(java.lang.String gpsLoteY) {
        this.gpsLoteY = gpsLoteY;
    }


    /**
     * Gets the inicioVigenciaCultivo value for this LoteCultivoDTO.
     * 
     * @return inicioVigenciaCultivo
     */
    public java.util.Calendar getInicioVigenciaCultivo() {
        return inicioVigenciaCultivo;
    }


    /**
     * Sets the inicioVigenciaCultivo value for this LoteCultivoDTO.
     * 
     * @param inicioVigenciaCultivo
     */
    public void setInicioVigenciaCultivo(java.util.Calendar inicioVigenciaCultivo) {
        this.inicioVigenciaCultivo = inicioVigenciaCultivo;
    }


    /**
     * Gets the loteCultivoId value for this LoteCultivoDTO.
     * 
     * @return loteCultivoId
     */
    public java.lang.String getLoteCultivoId() {
        return loteCultivoId;
    }


    /**
     * Sets the loteCultivoId value for this LoteCultivoDTO.
     * 
     * @param loteCultivoId
     */
    public void setLoteCultivoId(java.lang.String loteCultivoId) {
        this.loteCultivoId = loteCultivoId;
    }


    /**
     * Gets the nombre value for this LoteCultivoDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this LoteCultivoDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the numeroHectareas value for this LoteCultivoDTO.
     * 
     * @return numeroHectareas
     */
    public double getNumeroHectareas() {
        return numeroHectareas;
    }


    /**
     * Sets the numeroHectareas value for this LoteCultivoDTO.
     * 
     * @param numeroHectareas
     */
    public void setNumeroHectareas(double numeroHectareas) {
        this.numeroHectareas = numeroHectareas;
    }


    /**
     * Gets the numeroHectareasAsegurables value for this LoteCultivoDTO.
     * 
     * @return numeroHectareasAsegurables
     */
    public double getNumeroHectareasAsegurables() {
        return numeroHectareasAsegurables;
    }


    /**
     * Sets the numeroHectareasAsegurables value for this LoteCultivoDTO.
     * 
     * @param numeroHectareasAsegurables
     */
    public void setNumeroHectareasAsegurables(double numeroHectareasAsegurables) {
        this.numeroHectareasAsegurables = numeroHectareasAsegurables;
    }


    /**
     * Gets the propiedadId value for this LoteCultivoDTO.
     * 
     * @return propiedadId
     */
    public java.lang.String getPropiedadId() {
        return propiedadId;
    }


    /**
     * Sets the propiedadId value for this LoteCultivoDTO.
     * 
     * @param propiedadId
     */
    public void setPropiedadId(java.lang.String propiedadId) {
        this.propiedadId = propiedadId;
    }


    /**
     * Gets the tasa value for this LoteCultivoDTO.
     * 
     * @return tasa
     */
    public double getTasa() {
        return tasa;
    }


    /**
     * Sets the tasa value for this LoteCultivoDTO.
     * 
     * @param tasa
     */
    public void setTasa(double tasa) {
        this.tasa = tasa;
    }


    /**
     * Gets the tipoCultivoId value for this LoteCultivoDTO.
     * 
     * @return tipoCultivoId
     */
    public java.lang.String getTipoCultivoId() {
        return tipoCultivoId;
    }


    /**
     * Sets the tipoCultivoId value for this LoteCultivoDTO.
     * 
     * @param tipoCultivoId
     */
    public void setTipoCultivoId(java.lang.String tipoCultivoId) {
        this.tipoCultivoId = tipoCultivoId;
    }


    /**
     * Gets the valorPorHectarea value for this LoteCultivoDTO.
     * 
     * @return valorPorHectarea
     */
    public java.math.BigDecimal getValorPorHectarea() {
        return valorPorHectarea;
    }


    /**
     * Sets the valorPorHectarea value for this LoteCultivoDTO.
     * 
     * @param valorPorHectarea
     */
    public void setValorPorHectarea(java.math.BigDecimal valorPorHectarea) {
        this.valorPorHectarea = valorPorHectarea;
    }


    /**
     * Gets the variedad value for this LoteCultivoDTO.
     * 
     * @return variedad
     */
    public java.lang.String getVariedad() {
        return variedad;
    }


    /**
     * Sets the variedad value for this LoteCultivoDTO.
     * 
     * @param variedad
     */
    public void setVariedad(java.lang.String variedad) {
        this.variedad = variedad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LoteCultivoDTO)) return false;
        LoteCultivoDTO other = (LoteCultivoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.diasVigencia == other.getDiasVigencia() &&
            this.esTecnificado == other.isEsTecnificado() &&
            ((this.fechaTentativaSiembra==null && other.getFechaTentativaSiembra()==null) || 
             (this.fechaTentativaSiembra!=null &&
              this.fechaTentativaSiembra.equals(other.getFechaTentativaSiembra()))) &&
            ((this.finVigenciaCultivo==null && other.getFinVigenciaCultivo()==null) || 
             (this.finVigenciaCultivo!=null &&
              this.finVigenciaCultivo.equals(other.getFinVigenciaCultivo()))) &&
            ((this.gpsLoteX==null && other.getGpsLoteX()==null) || 
             (this.gpsLoteX!=null &&
              this.gpsLoteX.equals(other.getGpsLoteX()))) &&
            ((this.gpsLoteY==null && other.getGpsLoteY()==null) || 
             (this.gpsLoteY!=null &&
              this.gpsLoteY.equals(other.getGpsLoteY()))) &&
            ((this.inicioVigenciaCultivo==null && other.getInicioVigenciaCultivo()==null) || 
             (this.inicioVigenciaCultivo!=null &&
              this.inicioVigenciaCultivo.equals(other.getInicioVigenciaCultivo()))) &&
            ((this.loteCultivoId==null && other.getLoteCultivoId()==null) || 
             (this.loteCultivoId!=null &&
              this.loteCultivoId.equals(other.getLoteCultivoId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.numeroHectareas == other.getNumeroHectareas() &&
            this.numeroHectareasAsegurables == other.getNumeroHectareasAsegurables() &&
            ((this.propiedadId==null && other.getPropiedadId()==null) || 
             (this.propiedadId!=null &&
              this.propiedadId.equals(other.getPropiedadId()))) &&
            this.tasa == other.getTasa() &&
            ((this.tipoCultivoId==null && other.getTipoCultivoId()==null) || 
             (this.tipoCultivoId!=null &&
              this.tipoCultivoId.equals(other.getTipoCultivoId()))) &&
            ((this.valorPorHectarea==null && other.getValorPorHectarea()==null) || 
             (this.valorPorHectarea!=null &&
              this.valorPorHectarea.equals(other.getValorPorHectarea()))) &&
            ((this.variedad==null && other.getVariedad()==null) || 
             (this.variedad!=null &&
              this.variedad.equals(other.getVariedad())));
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
        _hashCode += getDiasVigencia();
        _hashCode += (isEsTecnificado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFechaTentativaSiembra() != null) {
            _hashCode += getFechaTentativaSiembra().hashCode();
        }
        if (getFinVigenciaCultivo() != null) {
            _hashCode += getFinVigenciaCultivo().hashCode();
        }
        if (getGpsLoteX() != null) {
            _hashCode += getGpsLoteX().hashCode();
        }
        if (getGpsLoteY() != null) {
            _hashCode += getGpsLoteY().hashCode();
        }
        if (getInicioVigenciaCultivo() != null) {
            _hashCode += getInicioVigenciaCultivo().hashCode();
        }
        if (getLoteCultivoId() != null) {
            _hashCode += getLoteCultivoId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += new Double(getNumeroHectareas()).hashCode();
        _hashCode += new Double(getNumeroHectareasAsegurables()).hashCode();
        if (getPropiedadId() != null) {
            _hashCode += getPropiedadId().hashCode();
        }
        _hashCode += new Double(getTasa()).hashCode();
        if (getTipoCultivoId() != null) {
            _hashCode += getTipoCultivoId().hashCode();
        }
        if (getValorPorHectarea() != null) {
            _hashCode += getValorPorHectarea().hashCode();
        }
        if (getVariedad() != null) {
            _hashCode += getVariedad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LoteCultivoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "LoteCultivoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diasVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "diasVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esTecnificado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esTecnificado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaTentativaSiembra");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaTentativaSiembra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finVigenciaCultivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "finVigenciaCultivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gpsLoteX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gpsLoteX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gpsLoteY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gpsLoteY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inicioVigenciaCultivo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "inicioVigenciaCultivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loteCultivoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loteCultivoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroHectareas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroHectareas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroHectareasAsegurables");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroHectareasAsegurables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propiedadId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propiedadId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tasa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCultivoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoCultivoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPorHectarea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPorHectarea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variedad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "variedad"));
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
