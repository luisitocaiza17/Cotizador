/**
 * EndosoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class EndosoDTO  implements java.io.Serializable {
    private com.tandi.servicios.DTOs.BlanketDTO[] blankets;

    private java.util.HashMap coberturasAdicionales;

    private com.tandi.servicios.DTOs.DeducibleDTO[] deducibles;

    private java.lang.String firmaSucursalId;

    private com.tandi.servicios.DTOs.ItemDTO item;

    private java.lang.String numeroTramite;

    private java.math.BigDecimal porcentajeComision;

    private com.tandi.servicios.DTOs.PredioDTO[] predios;

    private com.tandi.servicios.DTOs.ProductoDTO producto;

    private java.lang.String puntoVentaId;

    private java.math.BigDecimal recargoSeguroCampesino;

    private java.lang.String sistemaEmisor;

    private java.lang.String sistemaEmisorId;

    private java.lang.String sucursalId;

    private java.lang.String unidadNegocioId;

    private java.math.BigDecimal valorAsegurado;

    private java.math.BigDecimal valorComision;

    private java.math.BigDecimal valorPrima;

    private long vigenciaDesde;

    private long vigenciaHasta;

    public EndosoDTO() {
    }

    public EndosoDTO(
           com.tandi.servicios.DTOs.BlanketDTO[] blankets,
           java.util.HashMap coberturasAdicionales,
           com.tandi.servicios.DTOs.DeducibleDTO[] deducibles,
           java.lang.String firmaSucursalId,
           com.tandi.servicios.DTOs.ItemDTO item,
           java.lang.String numeroTramite,
           java.math.BigDecimal porcentajeComision,
           com.tandi.servicios.DTOs.PredioDTO[] predios,
           com.tandi.servicios.DTOs.ProductoDTO producto,
           java.lang.String puntoVentaId,
           java.math.BigDecimal recargoSeguroCampesino,
           java.lang.String sistemaEmisor,
           java.lang.String sistemaEmisorId,
           java.lang.String sucursalId,
           java.lang.String unidadNegocioId,
           java.math.BigDecimal valorAsegurado,
           java.math.BigDecimal valorComision,
           java.math.BigDecimal valorPrima,
           long vigenciaDesde,
           long vigenciaHasta) {
           this.blankets = blankets;
           this.coberturasAdicionales = coberturasAdicionales;
           this.deducibles = deducibles;
           this.firmaSucursalId = firmaSucursalId;
           this.item = item;
           this.numeroTramite = numeroTramite;
           this.porcentajeComision = porcentajeComision;
           this.predios = predios;
           this.producto = producto;
           this.puntoVentaId = puntoVentaId;
           this.recargoSeguroCampesino = recargoSeguroCampesino;
           this.sistemaEmisor = sistemaEmisor;
           this.sistemaEmisorId = sistemaEmisorId;
           this.sucursalId = sucursalId;
           this.unidadNegocioId = unidadNegocioId;
           this.valorAsegurado = valorAsegurado;
           this.valorComision = valorComision;
           this.valorPrima = valorPrima;
           this.vigenciaDesde = vigenciaDesde;
           this.vigenciaHasta = vigenciaHasta;
    }


    /**
     * Gets the blankets value for this EndosoDTO.
     * 
     * @return blankets
     */
    public com.tandi.servicios.DTOs.BlanketDTO[] getBlankets() {
        return blankets;
    }


    /**
     * Sets the blankets value for this EndosoDTO.
     * 
     * @param blankets
     */
    public void setBlankets(com.tandi.servicios.DTOs.BlanketDTO[] blankets) {
        this.blankets = blankets;
    }


    /**
     * Gets the coberturasAdicionales value for this EndosoDTO.
     * 
     * @return coberturasAdicionales
     */
    public java.util.HashMap getCoberturasAdicionales() {
        return coberturasAdicionales;
    }


    /**
     * Sets the coberturasAdicionales value for this EndosoDTO.
     * 
     * @param coberturasAdicionales
     */
    public void setCoberturasAdicionales(java.util.HashMap coberturasAdicionales) {
        this.coberturasAdicionales = coberturasAdicionales;
    }


    /**
     * Gets the deducibles value for this EndosoDTO.
     * 
     * @return deducibles
     */
    public com.tandi.servicios.DTOs.DeducibleDTO[] getDeducibles() {
        return deducibles;
    }


    /**
     * Sets the deducibles value for this EndosoDTO.
     * 
     * @param deducibles
     */
    public void setDeducibles(com.tandi.servicios.DTOs.DeducibleDTO[] deducibles) {
        this.deducibles = deducibles;
    }


    /**
     * Gets the firmaSucursalId value for this EndosoDTO.
     * 
     * @return firmaSucursalId
     */
    public java.lang.String getFirmaSucursalId() {
        return firmaSucursalId;
    }


    /**
     * Sets the firmaSucursalId value for this EndosoDTO.
     * 
     * @param firmaSucursalId
     */
    public void setFirmaSucursalId(java.lang.String firmaSucursalId) {
        this.firmaSucursalId = firmaSucursalId;
    }


    /**
     * Gets the item value for this EndosoDTO.
     * 
     * @return item
     */
    public com.tandi.servicios.DTOs.ItemDTO getItem() {
        return item;
    }


    /**
     * Sets the item value for this EndosoDTO.
     * 
     * @param item
     */
    public void setItem(com.tandi.servicios.DTOs.ItemDTO item) {
        this.item = item;
    }


    /**
     * Gets the numeroTramite value for this EndosoDTO.
     * 
     * @return numeroTramite
     */
    public java.lang.String getNumeroTramite() {
        return numeroTramite;
    }


    /**
     * Sets the numeroTramite value for this EndosoDTO.
     * 
     * @param numeroTramite
     */
    public void setNumeroTramite(java.lang.String numeroTramite) {
        this.numeroTramite = numeroTramite;
    }


    /**
     * Gets the porcentajeComision value for this EndosoDTO.
     * 
     * @return porcentajeComision
     */
    public java.math.BigDecimal getPorcentajeComision() {
        return porcentajeComision;
    }


    /**
     * Sets the porcentajeComision value for this EndosoDTO.
     * 
     * @param porcentajeComision
     */
    public void setPorcentajeComision(java.math.BigDecimal porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }


    /**
     * Gets the predios value for this EndosoDTO.
     * 
     * @return predios
     */
    public com.tandi.servicios.DTOs.PredioDTO[] getPredios() {
        return predios;
    }


    /**
     * Sets the predios value for this EndosoDTO.
     * 
     * @param predios
     */
    public void setPredios(com.tandi.servicios.DTOs.PredioDTO[] predios) {
        this.predios = predios;
    }


    /**
     * Gets the producto value for this EndosoDTO.
     * 
     * @return producto
     */
    public com.tandi.servicios.DTOs.ProductoDTO getProducto() {
        return producto;
    }


    /**
     * Sets the producto value for this EndosoDTO.
     * 
     * @param producto
     */
    public void setProducto(com.tandi.servicios.DTOs.ProductoDTO producto) {
        this.producto = producto;
    }


    /**
     * Gets the puntoVentaId value for this EndosoDTO.
     * 
     * @return puntoVentaId
     */
    public java.lang.String getPuntoVentaId() {
        return puntoVentaId;
    }


    /**
     * Sets the puntoVentaId value for this EndosoDTO.
     * 
     * @param puntoVentaId
     */
    public void setPuntoVentaId(java.lang.String puntoVentaId) {
        this.puntoVentaId = puntoVentaId;
    }


    /**
     * Gets the recargoSeguroCampesino value for this EndosoDTO.
     * 
     * @return recargoSeguroCampesino
     */
    public java.math.BigDecimal getRecargoSeguroCampesino() {
        return recargoSeguroCampesino;
    }


    /**
     * Sets the recargoSeguroCampesino value for this EndosoDTO.
     * 
     * @param recargoSeguroCampesino
     */
    public void setRecargoSeguroCampesino(java.math.BigDecimal recargoSeguroCampesino) {
        this.recargoSeguroCampesino = recargoSeguroCampesino;
    }


    /**
     * Gets the sistemaEmisor value for this EndosoDTO.
     * 
     * @return sistemaEmisor
     */
    public java.lang.String getSistemaEmisor() {
        return sistemaEmisor;
    }


    /**
     * Sets the sistemaEmisor value for this EndosoDTO.
     * 
     * @param sistemaEmisor
     */
    public void setSistemaEmisor(java.lang.String sistemaEmisor) {
        this.sistemaEmisor = sistemaEmisor;
    }


    /**
     * Gets the sistemaEmisorId value for this EndosoDTO.
     * 
     * @return sistemaEmisorId
     */
    public java.lang.String getSistemaEmisorId() {
        return sistemaEmisorId;
    }


    /**
     * Sets the sistemaEmisorId value for this EndosoDTO.
     * 
     * @param sistemaEmisorId
     */
    public void setSistemaEmisorId(java.lang.String sistemaEmisorId) {
        this.sistemaEmisorId = sistemaEmisorId;
    }


    /**
     * Gets the sucursalId value for this EndosoDTO.
     * 
     * @return sucursalId
     */
    public java.lang.String getSucursalId() {
        return sucursalId;
    }


    /**
     * Sets the sucursalId value for this EndosoDTO.
     * 
     * @param sucursalId
     */
    public void setSucursalId(java.lang.String sucursalId) {
        this.sucursalId = sucursalId;
    }


    /**
     * Gets the unidadNegocioId value for this EndosoDTO.
     * 
     * @return unidadNegocioId
     */
    public java.lang.String getUnidadNegocioId() {
        return unidadNegocioId;
    }


    /**
     * Sets the unidadNegocioId value for this EndosoDTO.
     * 
     * @param unidadNegocioId
     */
    public void setUnidadNegocioId(java.lang.String unidadNegocioId) {
        this.unidadNegocioId = unidadNegocioId;
    }


    /**
     * Gets the valorAsegurado value for this EndosoDTO.
     * 
     * @return valorAsegurado
     */
    public java.math.BigDecimal getValorAsegurado() {
        return valorAsegurado;
    }


    /**
     * Sets the valorAsegurado value for this EndosoDTO.
     * 
     * @param valorAsegurado
     */
    public void setValorAsegurado(java.math.BigDecimal valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }


    /**
     * Gets the valorComision value for this EndosoDTO.
     * 
     * @return valorComision
     */
    public java.math.BigDecimal getValorComision() {
        return valorComision;
    }


    /**
     * Sets the valorComision value for this EndosoDTO.
     * 
     * @param valorComision
     */
    public void setValorComision(java.math.BigDecimal valorComision) {
        this.valorComision = valorComision;
    }


    /**
     * Gets the valorPrima value for this EndosoDTO.
     * 
     * @return valorPrima
     */
    public java.math.BigDecimal getValorPrima() {
        return valorPrima;
    }


    /**
     * Sets the valorPrima value for this EndosoDTO.
     * 
     * @param valorPrima
     */
    public void setValorPrima(java.math.BigDecimal valorPrima) {
        this.valorPrima = valorPrima;
    }


    /**
     * Gets the vigenciaDesde value for this EndosoDTO.
     * 
     * @return vigenciaDesde
     */
    public long getVigenciaDesde() {
        return vigenciaDesde;
    }


    /**
     * Sets the vigenciaDesde value for this EndosoDTO.
     * 
     * @param vigenciaDesde
     */
    public void setVigenciaDesde(long vigenciaDesde) {
        this.vigenciaDesde = vigenciaDesde;
    }


    /**
     * Gets the vigenciaHasta value for this EndosoDTO.
     * 
     * @return vigenciaHasta
     */
    public long getVigenciaHasta() {
        return vigenciaHasta;
    }


    /**
     * Sets the vigenciaHasta value for this EndosoDTO.
     * 
     * @param vigenciaHasta
     */
    public void setVigenciaHasta(long vigenciaHasta) {
        this.vigenciaHasta = vigenciaHasta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EndosoDTO)) return false;
        EndosoDTO other = (EndosoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.blankets==null && other.getBlankets()==null) || 
             (this.blankets!=null &&
              java.util.Arrays.equals(this.blankets, other.getBlankets()))) &&
            ((this.coberturasAdicionales==null && other.getCoberturasAdicionales()==null) || 
             (this.coberturasAdicionales!=null &&
              this.coberturasAdicionales.equals(other.getCoberturasAdicionales()))) &&
            ((this.deducibles==null && other.getDeducibles()==null) || 
             (this.deducibles!=null &&
              java.util.Arrays.equals(this.deducibles, other.getDeducibles()))) &&
            ((this.firmaSucursalId==null && other.getFirmaSucursalId()==null) || 
             (this.firmaSucursalId!=null &&
              this.firmaSucursalId.equals(other.getFirmaSucursalId()))) &&
            ((this.item==null && other.getItem()==null) || 
             (this.item!=null &&
              this.item.equals(other.getItem()))) &&
            ((this.numeroTramite==null && other.getNumeroTramite()==null) || 
             (this.numeroTramite!=null &&
              this.numeroTramite.equals(other.getNumeroTramite()))) &&
            ((this.porcentajeComision==null && other.getPorcentajeComision()==null) || 
             (this.porcentajeComision!=null &&
              this.porcentajeComision.equals(other.getPorcentajeComision()))) &&
            ((this.predios==null && other.getPredios()==null) || 
             (this.predios!=null &&
              java.util.Arrays.equals(this.predios, other.getPredios()))) &&
            ((this.producto==null && other.getProducto()==null) || 
             (this.producto!=null &&
              this.producto.equals(other.getProducto()))) &&
            ((this.puntoVentaId==null && other.getPuntoVentaId()==null) || 
             (this.puntoVentaId!=null &&
              this.puntoVentaId.equals(other.getPuntoVentaId()))) &&
            ((this.recargoSeguroCampesino==null && other.getRecargoSeguroCampesino()==null) || 
             (this.recargoSeguroCampesino!=null &&
              this.recargoSeguroCampesino.equals(other.getRecargoSeguroCampesino()))) &&
            ((this.sistemaEmisor==null && other.getSistemaEmisor()==null) || 
             (this.sistemaEmisor!=null &&
              this.sistemaEmisor.equals(other.getSistemaEmisor()))) &&
            ((this.sistemaEmisorId==null && other.getSistemaEmisorId()==null) || 
             (this.sistemaEmisorId!=null &&
              this.sistemaEmisorId.equals(other.getSistemaEmisorId()))) &&
            ((this.sucursalId==null && other.getSucursalId()==null) || 
             (this.sucursalId!=null &&
              this.sucursalId.equals(other.getSucursalId()))) &&
            ((this.unidadNegocioId==null && other.getUnidadNegocioId()==null) || 
             (this.unidadNegocioId!=null &&
              this.unidadNegocioId.equals(other.getUnidadNegocioId()))) &&
            ((this.valorAsegurado==null && other.getValorAsegurado()==null) || 
             (this.valorAsegurado!=null &&
              this.valorAsegurado.equals(other.getValorAsegurado()))) &&
            ((this.valorComision==null && other.getValorComision()==null) || 
             (this.valorComision!=null &&
              this.valorComision.equals(other.getValorComision()))) &&
            ((this.valorPrima==null && other.getValorPrima()==null) || 
             (this.valorPrima!=null &&
              this.valorPrima.equals(other.getValorPrima()))) &&
            this.vigenciaDesde == other.getVigenciaDesde() &&
            this.vigenciaHasta == other.getVigenciaHasta();
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
        if (getBlankets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBlankets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBlankets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCoberturasAdicionales() != null) {
            _hashCode += getCoberturasAdicionales().hashCode();
        }
        if (getDeducibles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDeducibles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDeducibles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFirmaSucursalId() != null) {
            _hashCode += getFirmaSucursalId().hashCode();
        }
        if (getItem() != null) {
            _hashCode += getItem().hashCode();
        }
        if (getNumeroTramite() != null) {
            _hashCode += getNumeroTramite().hashCode();
        }
        if (getPorcentajeComision() != null) {
            _hashCode += getPorcentajeComision().hashCode();
        }
        if (getPredios() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPredios());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPredios(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getProducto() != null) {
            _hashCode += getProducto().hashCode();
        }
        if (getPuntoVentaId() != null) {
            _hashCode += getPuntoVentaId().hashCode();
        }
        if (getRecargoSeguroCampesino() != null) {
            _hashCode += getRecargoSeguroCampesino().hashCode();
        }
        if (getSistemaEmisor() != null) {
            _hashCode += getSistemaEmisor().hashCode();
        }
        if (getSistemaEmisorId() != null) {
            _hashCode += getSistemaEmisorId().hashCode();
        }
        if (getSucursalId() != null) {
            _hashCode += getSucursalId().hashCode();
        }
        if (getUnidadNegocioId() != null) {
            _hashCode += getUnidadNegocioId().hashCode();
        }
        if (getValorAsegurado() != null) {
            _hashCode += getValorAsegurado().hashCode();
        }
        if (getValorComision() != null) {
            _hashCode += getValorComision().hashCode();
        }
        if (getValorPrima() != null) {
            _hashCode += getValorPrima().hashCode();
        }
        _hashCode += new Long(getVigenciaDesde()).hashCode();
        _hashCode += new Long(getVigenciaHasta()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EndosoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "EndosoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blankets");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blankets"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "BlanketDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coberturasAdicionales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "coberturasAdicionales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xml.apache.org/xml-soap", "Map"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deducibles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "deducibles"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DeducibleDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firmaSucursalId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "firmaSucursalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ItemDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTramite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTramite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentajeComision");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentajeComision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("predios");
        elemField.setXmlName(new javax.xml.namespace.QName("", "predios"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PredioDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("producto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "producto"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ProductoDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("puntoVentaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "puntoVentaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recargoSeguroCampesino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recargoSeguroCampesino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sistemaEmisor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sistemaEmisor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sistemaEmisorId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sistemaEmisorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sucursalId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sucursalId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unidadNegocioId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unidadNegocioId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAsegurado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAsegurado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorComision");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorComision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrima");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrima"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vigenciaDesde");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vigenciaDesde"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vigenciaHasta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vigenciaHasta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
