/**
 * VehiculoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class VehiculoDTO  extends com.tandi.servicios.DTOs.ItemDTO  implements java.io.Serializable {
    private int anoFabricacion;

    private boolean autoAsistencia;

    private java.lang.String chasis;

    private java.lang.String claseVehiculoId;

    private com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales;

    private java.lang.String colorId;

    private com.tandi.servicios.DTOs.ClienteDTO[] conductores;

    private com.tandi.servicios.DTOs.ExtraVehiculoDTO[] extras;

    private java.lang.String marcaId;

    private java.lang.String modeloId;

    private java.lang.String motor;

    private java.lang.String nombreColor;

    private java.lang.String nombreMarca;

    private java.lang.String nombreModelo;

    private int ocupantes;

    private java.lang.String placas;

    private java.lang.String propietarioVehiculo;

    private java.lang.String tipoVehiculoId;

    private java.lang.String tipousoId;

    private java.math.BigDecimal valorAseguradoVehiculo;

    private java.math.BigDecimal valorPrimaExtras;

    private java.math.BigDecimal valorPrimaVehiculo;

    private java.lang.String vehiculoId;

    public VehiculoDTO() {
    }

    public VehiculoDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado,
           int anoFabricacion,
           boolean autoAsistencia,
           java.lang.String chasis,
           java.lang.String claseVehiculoId,
           com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales,
           java.lang.String colorId,
           com.tandi.servicios.DTOs.ClienteDTO[] conductores,
           com.tandi.servicios.DTOs.ExtraVehiculoDTO[] extras,
           java.lang.String marcaId,
           java.lang.String modeloId,
           java.lang.String motor,
           java.lang.String nombreColor,
           java.lang.String nombreMarca,
           java.lang.String nombreModelo,
           int ocupantes,
           java.lang.String placas,
           java.lang.String propietarioVehiculo,
           java.lang.String tipoVehiculoId,
           java.lang.String tipousoId,
           java.math.BigDecimal valorAseguradoVehiculo,
           java.math.BigDecimal valorPrimaExtras,
           java.math.BigDecimal valorPrimaVehiculo,
           java.lang.String vehiculoId) {
        super(
            claseRiesgoId,
            id,
            texto,
            tipoItemId,
            tipoRiesgoId,
            valorAsegurado);
        this.anoFabricacion = anoFabricacion;
        this.autoAsistencia = autoAsistencia;
        this.chasis = chasis;
        this.claseVehiculoId = claseVehiculoId;
        this.coberturasAdicionales = coberturasAdicionales;
        this.colorId = colorId;
        this.conductores = conductores;
        this.extras = extras;
        this.marcaId = marcaId;
        this.modeloId = modeloId;
        this.motor = motor;
        this.nombreColor = nombreColor;
        this.nombreMarca = nombreMarca;
        this.nombreModelo = nombreModelo;
        this.ocupantes = ocupantes;
        this.placas = placas;
        this.propietarioVehiculo = propietarioVehiculo;
        this.tipoVehiculoId = tipoVehiculoId;
        this.tipousoId = tipousoId;
        this.valorAseguradoVehiculo = valorAseguradoVehiculo;
        this.valorPrimaExtras = valorPrimaExtras;
        this.valorPrimaVehiculo = valorPrimaVehiculo;
        this.vehiculoId = vehiculoId;
    }


    /**
     * Gets the anoFabricacion value for this VehiculoDTO.
     * 
     * @return anoFabricacion
     */
    public int getAnoFabricacion() {
        return anoFabricacion;
    }


    /**
     * Sets the anoFabricacion value for this VehiculoDTO.
     * 
     * @param anoFabricacion
     */
    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }


    /**
     * Gets the autoAsistencia value for this VehiculoDTO.
     * 
     * @return autoAsistencia
     */
    public boolean isAutoAsistencia() {
        return autoAsistencia;
    }


    /**
     * Sets the autoAsistencia value for this VehiculoDTO.
     * 
     * @param autoAsistencia
     */
    public void setAutoAsistencia(boolean autoAsistencia) {
        this.autoAsistencia = autoAsistencia;
    }


    /**
     * Gets the chasis value for this VehiculoDTO.
     * 
     * @return chasis
     */
    public java.lang.String getChasis() {
        return chasis;
    }


    /**
     * Sets the chasis value for this VehiculoDTO.
     * 
     * @param chasis
     */
    public void setChasis(java.lang.String chasis) {
        this.chasis = chasis;
    }


    /**
     * Gets the claseVehiculoId value for this VehiculoDTO.
     * 
     * @return claseVehiculoId
     */
    public java.lang.String getClaseVehiculoId() {
        return claseVehiculoId;
    }


    /**
     * Sets the claseVehiculoId value for this VehiculoDTO.
     * 
     * @param claseVehiculoId
     */
    public void setClaseVehiculoId(java.lang.String claseVehiculoId) {
        this.claseVehiculoId = claseVehiculoId;
    }


    /**
     * Gets the coberturasAdicionales value for this VehiculoDTO.
     * 
     * @return coberturasAdicionales
     */
    public com.tandi.servicios.DTOs.CoberturaDTO[] getCoberturasAdicionales() {
        return coberturasAdicionales;
    }


    /**
     * Sets the coberturasAdicionales value for this VehiculoDTO.
     * 
     * @param coberturasAdicionales
     */
    public void setCoberturasAdicionales(com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales) {
        this.coberturasAdicionales = coberturasAdicionales;
    }


    /**
     * Gets the colorId value for this VehiculoDTO.
     * 
     * @return colorId
     */
    public java.lang.String getColorId() {
        return colorId;
    }


    /**
     * Sets the colorId value for this VehiculoDTO.
     * 
     * @param colorId
     */
    public void setColorId(java.lang.String colorId) {
        this.colorId = colorId;
    }


    /**
     * Gets the conductores value for this VehiculoDTO.
     * 
     * @return conductores
     */
    public com.tandi.servicios.DTOs.ClienteDTO[] getConductores() {
        return conductores;
    }


    /**
     * Sets the conductores value for this VehiculoDTO.
     * 
     * @param conductores
     */
    public void setConductores(com.tandi.servicios.DTOs.ClienteDTO[] conductores) {
        this.conductores = conductores;
    }


    /**
     * Gets the extras value for this VehiculoDTO.
     * 
     * @return extras
     */
    public com.tandi.servicios.DTOs.ExtraVehiculoDTO[] getExtras() {
        return extras;
    }


    /**
     * Sets the extras value for this VehiculoDTO.
     * 
     * @param extras
     */
    public void setExtras(com.tandi.servicios.DTOs.ExtraVehiculoDTO[] extras) {
        this.extras = extras;
    }


    /**
     * Gets the marcaId value for this VehiculoDTO.
     * 
     * @return marcaId
     */
    public java.lang.String getMarcaId() {
        return marcaId;
    }


    /**
     * Sets the marcaId value for this VehiculoDTO.
     * 
     * @param marcaId
     */
    public void setMarcaId(java.lang.String marcaId) {
        this.marcaId = marcaId;
    }


    /**
     * Gets the modeloId value for this VehiculoDTO.
     * 
     * @return modeloId
     */
    public java.lang.String getModeloId() {
        return modeloId;
    }


    /**
     * Sets the modeloId value for this VehiculoDTO.
     * 
     * @param modeloId
     */
    public void setModeloId(java.lang.String modeloId) {
        this.modeloId = modeloId;
    }


    /**
     * Gets the motor value for this VehiculoDTO.
     * 
     * @return motor
     */
    public java.lang.String getMotor() {
        return motor;
    }


    /**
     * Sets the motor value for this VehiculoDTO.
     * 
     * @param motor
     */
    public void setMotor(java.lang.String motor) {
        this.motor = motor;
    }


    /**
     * Gets the nombreColor value for this VehiculoDTO.
     * 
     * @return nombreColor
     */
    public java.lang.String getNombreColor() {
        return nombreColor;
    }


    /**
     * Sets the nombreColor value for this VehiculoDTO.
     * 
     * @param nombreColor
     */
    public void setNombreColor(java.lang.String nombreColor) {
        this.nombreColor = nombreColor;
    }


    /**
     * Gets the nombreMarca value for this VehiculoDTO.
     * 
     * @return nombreMarca
     */
    public java.lang.String getNombreMarca() {
        return nombreMarca;
    }


    /**
     * Sets the nombreMarca value for this VehiculoDTO.
     * 
     * @param nombreMarca
     */
    public void setNombreMarca(java.lang.String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }


    /**
     * Gets the nombreModelo value for this VehiculoDTO.
     * 
     * @return nombreModelo
     */
    public java.lang.String getNombreModelo() {
        return nombreModelo;
    }


    /**
     * Sets the nombreModelo value for this VehiculoDTO.
     * 
     * @param nombreModelo
     */
    public void setNombreModelo(java.lang.String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }


    /**
     * Gets the ocupantes value for this VehiculoDTO.
     * 
     * @return ocupantes
     */
    public int getOcupantes() {
        return ocupantes;
    }


    /**
     * Sets the ocupantes value for this VehiculoDTO.
     * 
     * @param ocupantes
     */
    public void setOcupantes(int ocupantes) {
        this.ocupantes = ocupantes;
    }


    /**
     * Gets the placas value for this VehiculoDTO.
     * 
     * @return placas
     */
    public java.lang.String getPlacas() {
        return placas;
    }


    /**
     * Sets the placas value for this VehiculoDTO.
     * 
     * @param placas
     */
    public void setPlacas(java.lang.String placas) {
        this.placas = placas;
    }


    /**
     * Gets the propietarioVehiculo value for this VehiculoDTO.
     * 
     * @return propietarioVehiculo
     */
    public java.lang.String getPropietarioVehiculo() {
        return propietarioVehiculo;
    }


    /**
     * Sets the propietarioVehiculo value for this VehiculoDTO.
     * 
     * @param propietarioVehiculo
     */
    public void setPropietarioVehiculo(java.lang.String propietarioVehiculo) {
        this.propietarioVehiculo = propietarioVehiculo;
    }


    /**
     * Gets the tipoVehiculoId value for this VehiculoDTO.
     * 
     * @return tipoVehiculoId
     */
    public java.lang.String getTipoVehiculoId() {
        return tipoVehiculoId;
    }


    /**
     * Sets the tipoVehiculoId value for this VehiculoDTO.
     * 
     * @param tipoVehiculoId
     */
    public void setTipoVehiculoId(java.lang.String tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }


    /**
     * Gets the tipousoId value for this VehiculoDTO.
     * 
     * @return tipousoId
     */
    public java.lang.String getTipousoId() {
        return tipousoId;
    }


    /**
     * Sets the tipousoId value for this VehiculoDTO.
     * 
     * @param tipousoId
     */
    public void setTipousoId(java.lang.String tipousoId) {
        this.tipousoId = tipousoId;
    }


    /**
     * Gets the valorAseguradoVehiculo value for this VehiculoDTO.
     * 
     * @return valorAseguradoVehiculo
     */
    public java.math.BigDecimal getValorAseguradoVehiculo() {
        return valorAseguradoVehiculo;
    }


    /**
     * Sets the valorAseguradoVehiculo value for this VehiculoDTO.
     * 
     * @param valorAseguradoVehiculo
     */
    public void setValorAseguradoVehiculo(java.math.BigDecimal valorAseguradoVehiculo) {
        this.valorAseguradoVehiculo = valorAseguradoVehiculo;
    }


    /**
     * Gets the valorPrimaExtras value for this VehiculoDTO.
     * 
     * @return valorPrimaExtras
     */
    public java.math.BigDecimal getValorPrimaExtras() {
        return valorPrimaExtras;
    }


    /**
     * Sets the valorPrimaExtras value for this VehiculoDTO.
     * 
     * @param valorPrimaExtras
     */
    public void setValorPrimaExtras(java.math.BigDecimal valorPrimaExtras) {
        this.valorPrimaExtras = valorPrimaExtras;
    }


    /**
     * Gets the valorPrimaVehiculo value for this VehiculoDTO.
     * 
     * @return valorPrimaVehiculo
     */
    public java.math.BigDecimal getValorPrimaVehiculo() {
        return valorPrimaVehiculo;
    }


    /**
     * Sets the valorPrimaVehiculo value for this VehiculoDTO.
     * 
     * @param valorPrimaVehiculo
     */
    public void setValorPrimaVehiculo(java.math.BigDecimal valorPrimaVehiculo) {
        this.valorPrimaVehiculo = valorPrimaVehiculo;
    }


    /**
     * Gets the vehiculoId value for this VehiculoDTO.
     * 
     * @return vehiculoId
     */
    public java.lang.String getVehiculoId() {
        return vehiculoId;
    }


    /**
     * Sets the vehiculoId value for this VehiculoDTO.
     * 
     * @param vehiculoId
     */
    public void setVehiculoId(java.lang.String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VehiculoDTO)) return false;
        VehiculoDTO other = (VehiculoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.anoFabricacion == other.getAnoFabricacion() &&
            this.autoAsistencia == other.isAutoAsistencia() &&
            ((this.chasis==null && other.getChasis()==null) || 
             (this.chasis!=null &&
              this.chasis.equals(other.getChasis()))) &&
            ((this.claseVehiculoId==null && other.getClaseVehiculoId()==null) || 
             (this.claseVehiculoId!=null &&
              this.claseVehiculoId.equals(other.getClaseVehiculoId()))) &&
            ((this.coberturasAdicionales==null && other.getCoberturasAdicionales()==null) || 
             (this.coberturasAdicionales!=null &&
              java.util.Arrays.equals(this.coberturasAdicionales, other.getCoberturasAdicionales()))) &&
            ((this.colorId==null && other.getColorId()==null) || 
             (this.colorId!=null &&
              this.colorId.equals(other.getColorId()))) &&
            ((this.conductores==null && other.getConductores()==null) || 
             (this.conductores!=null &&
              java.util.Arrays.equals(this.conductores, other.getConductores()))) &&
            ((this.extras==null && other.getExtras()==null) || 
             (this.extras!=null &&
              java.util.Arrays.equals(this.extras, other.getExtras()))) &&
            ((this.marcaId==null && other.getMarcaId()==null) || 
             (this.marcaId!=null &&
              this.marcaId.equals(other.getMarcaId()))) &&
            ((this.modeloId==null && other.getModeloId()==null) || 
             (this.modeloId!=null &&
              this.modeloId.equals(other.getModeloId()))) &&
            ((this.motor==null && other.getMotor()==null) || 
             (this.motor!=null &&
              this.motor.equals(other.getMotor()))) &&
            ((this.nombreColor==null && other.getNombreColor()==null) || 
             (this.nombreColor!=null &&
              this.nombreColor.equals(other.getNombreColor()))) &&
            ((this.nombreMarca==null && other.getNombreMarca()==null) || 
             (this.nombreMarca!=null &&
              this.nombreMarca.equals(other.getNombreMarca()))) &&
            ((this.nombreModelo==null && other.getNombreModelo()==null) || 
             (this.nombreModelo!=null &&
              this.nombreModelo.equals(other.getNombreModelo()))) &&
            this.ocupantes == other.getOcupantes() &&
            ((this.placas==null && other.getPlacas()==null) || 
             (this.placas!=null &&
              this.placas.equals(other.getPlacas()))) &&
            ((this.propietarioVehiculo==null && other.getPropietarioVehiculo()==null) || 
             (this.propietarioVehiculo!=null &&
              this.propietarioVehiculo.equals(other.getPropietarioVehiculo()))) &&
            ((this.tipoVehiculoId==null && other.getTipoVehiculoId()==null) || 
             (this.tipoVehiculoId!=null &&
              this.tipoVehiculoId.equals(other.getTipoVehiculoId()))) &&
            ((this.tipousoId==null && other.getTipousoId()==null) || 
             (this.tipousoId!=null &&
              this.tipousoId.equals(other.getTipousoId()))) &&
            ((this.valorAseguradoVehiculo==null && other.getValorAseguradoVehiculo()==null) || 
             (this.valorAseguradoVehiculo!=null &&
              this.valorAseguradoVehiculo.equals(other.getValorAseguradoVehiculo()))) &&
            ((this.valorPrimaExtras==null && other.getValorPrimaExtras()==null) || 
             (this.valorPrimaExtras!=null &&
              this.valorPrimaExtras.equals(other.getValorPrimaExtras()))) &&
            ((this.valorPrimaVehiculo==null && other.getValorPrimaVehiculo()==null) || 
             (this.valorPrimaVehiculo!=null &&
              this.valorPrimaVehiculo.equals(other.getValorPrimaVehiculo()))) &&
            ((this.vehiculoId==null && other.getVehiculoId()==null) || 
             (this.vehiculoId!=null &&
              this.vehiculoId.equals(other.getVehiculoId())));
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
        _hashCode += getAnoFabricacion();
        _hashCode += (isAutoAsistencia() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChasis() != null) {
            _hashCode += getChasis().hashCode();
        }
        if (getClaseVehiculoId() != null) {
            _hashCode += getClaseVehiculoId().hashCode();
        }
        if (getCoberturasAdicionales() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCoberturasAdicionales());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCoberturasAdicionales(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getColorId() != null) {
            _hashCode += getColorId().hashCode();
        }
        if (getConductores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConductores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConductores(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtras() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtras());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtras(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMarcaId() != null) {
            _hashCode += getMarcaId().hashCode();
        }
        if (getModeloId() != null) {
            _hashCode += getModeloId().hashCode();
        }
        if (getMotor() != null) {
            _hashCode += getMotor().hashCode();
        }
        if (getNombreColor() != null) {
            _hashCode += getNombreColor().hashCode();
        }
        if (getNombreMarca() != null) {
            _hashCode += getNombreMarca().hashCode();
        }
        if (getNombreModelo() != null) {
            _hashCode += getNombreModelo().hashCode();
        }
        _hashCode += getOcupantes();
        if (getPlacas() != null) {
            _hashCode += getPlacas().hashCode();
        }
        if (getPropietarioVehiculo() != null) {
            _hashCode += getPropietarioVehiculo().hashCode();
        }
        if (getTipoVehiculoId() != null) {
            _hashCode += getTipoVehiculoId().hashCode();
        }
        if (getTipousoId() != null) {
            _hashCode += getTipousoId().hashCode();
        }
        if (getValorAseguradoVehiculo() != null) {
            _hashCode += getValorAseguradoVehiculo().hashCode();
        }
        if (getValorPrimaExtras() != null) {
            _hashCode += getValorPrimaExtras().hashCode();
        }
        if (getValorPrimaVehiculo() != null) {
            _hashCode += getValorPrimaVehiculo().hashCode();
        }
        if (getVehiculoId() != null) {
            _hashCode += getVehiculoId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VehiculoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "VehiculoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anoFabricacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anoFabricacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoAsistencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "autoAsistencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chasis");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chasis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claseVehiculoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "claseVehiculoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturasAdicionales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coberturasAdicionales"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "CoberturaDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "colorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conductores");
        elemField.setXmlName(new javax.xml.namespace.QName("", "conductores"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extras"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ExtraVehiculoDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marcaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "marcaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modeloId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modeloId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "motor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreColor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreColor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreMarca");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreMarca"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ocupantes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ocupantes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("placas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "placas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propietarioVehiculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "propietarioVehiculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoVehiculoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoVehiculoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipousoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipousoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAseguradoVehiculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAseguradoVehiculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaExtras");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaExtras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaVehiculo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaVehiculo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vehiculoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vehiculoId"));
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
