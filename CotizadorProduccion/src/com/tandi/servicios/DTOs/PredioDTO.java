/**
 * PredioDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class PredioDTO  extends com.tandi.servicios.DTOs.ItemDTO  implements java.io.Serializable {
    private int anioConstruccion;

    private java.lang.String ciduadId;

    private com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales;

    private java.lang.String direccionCallePrincipal;

    private java.lang.String direccionCalleTransversal;

    private java.lang.String direccionNumero;

    private boolean esPrimaFija;

    private java.lang.String estadoInformacion;

    private java.lang.String gpsX;

    private java.lang.String gpsY;

    private java.lang.String materialConstruccionId;

    private java.lang.String nombre;

    private int numeroPisos;

    private java.lang.String paisId;

    private double porcentajePrimaDeposito;

    private java.lang.String provinciaId;

    private double tasa;

    private boolean tieneProteccionInundacion;

    private boolean tieneSotano;

    private java.lang.String tipoPredioId;

    private double valorAseguradoPredio;

    private double valorContenido;

    private double valorEdificio;

    private double valorFlotante;

    private double valorPrimaDeposito;

    private double valorPrimaPredio;

    public PredioDTO() {
    }

    public PredioDTO(
           java.lang.String claseRiesgoId,
           java.lang.String id,
           java.lang.String texto,
           java.lang.String tipoItemId,
           java.lang.String tipoRiesgoId,
           java.math.BigDecimal valorAsegurado,
           int anioConstruccion,
           java.lang.String ciduadId,
           com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales,
           java.lang.String direccionCallePrincipal,
           java.lang.String direccionCalleTransversal,
           java.lang.String direccionNumero,
           boolean esPrimaFija,
           java.lang.String estadoInformacion,
           java.lang.String gpsX,
           java.lang.String gpsY,
           java.lang.String materialConstruccionId,
           java.lang.String nombre,
           int numeroPisos,
           java.lang.String paisId,
           double porcentajePrimaDeposito,
           java.lang.String provinciaId,
           double tasa,
           boolean tieneProteccionInundacion,
           boolean tieneSotano,
           java.lang.String tipoPredioId,
           double valorAseguradoPredio,
           double valorContenido,
           double valorEdificio,
           double valorFlotante,
           double valorPrimaDeposito,
           double valorPrimaPredio) {
        super(
            claseRiesgoId,
            id,
            texto,
            tipoItemId,
            tipoRiesgoId,
            valorAsegurado);
        this.anioConstruccion = anioConstruccion;
        this.ciduadId = ciduadId;
        this.coberturasAdicionales = coberturasAdicionales;
        this.direccionCallePrincipal = direccionCallePrincipal;
        this.direccionCalleTransversal = direccionCalleTransversal;
        this.direccionNumero = direccionNumero;
        this.esPrimaFija = esPrimaFija;
        this.estadoInformacion = estadoInformacion;
        this.gpsX = gpsX;
        this.gpsY = gpsY;
        this.materialConstruccionId = materialConstruccionId;
        this.nombre = nombre;
        this.numeroPisos = numeroPisos;
        this.paisId = paisId;
        this.porcentajePrimaDeposito = porcentajePrimaDeposito;
        this.provinciaId = provinciaId;
        this.tasa = tasa;
        this.tieneProteccionInundacion = tieneProteccionInundacion;
        this.tieneSotano = tieneSotano;
        this.tipoPredioId = tipoPredioId;
        this.valorAseguradoPredio = valorAseguradoPredio;
        this.valorContenido = valorContenido;
        this.valorEdificio = valorEdificio;
        this.valorFlotante = valorFlotante;
        this.valorPrimaDeposito = valorPrimaDeposito;
        this.valorPrimaPredio = valorPrimaPredio;
    }


    /**
     * Gets the anioConstruccion value for this PredioDTO.
     * 
     * @return anioConstruccion
     */
    public int getAnioConstruccion() {
        return anioConstruccion;
    }


    /**
     * Sets the anioConstruccion value for this PredioDTO.
     * 
     * @param anioConstruccion
     */
    public void setAnioConstruccion(int anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }


    /**
     * Gets the ciduadId value for this PredioDTO.
     * 
     * @return ciduadId
     */
    public java.lang.String getCiduadId() {
        return ciduadId;
    }


    /**
     * Sets the ciduadId value for this PredioDTO.
     * 
     * @param ciduadId
     */
    public void setCiduadId(java.lang.String ciduadId) {
        this.ciduadId = ciduadId;
    }


    /**
     * Gets the coberturasAdicionales value for this PredioDTO.
     * 
     * @return coberturasAdicionales
     */
    public com.tandi.servicios.DTOs.CoberturaDTO[] getCoberturasAdicionales() {
        return coberturasAdicionales;
    }


    /**
     * Sets the coberturasAdicionales value for this PredioDTO.
     * 
     * @param coberturasAdicionales
     */
    public void setCoberturasAdicionales(com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales) {
        this.coberturasAdicionales = coberturasAdicionales;
    }


    /**
     * Gets the direccionCallePrincipal value for this PredioDTO.
     * 
     * @return direccionCallePrincipal
     */
    public java.lang.String getDireccionCallePrincipal() {
        return direccionCallePrincipal;
    }


    /**
     * Sets the direccionCallePrincipal value for this PredioDTO.
     * 
     * @param direccionCallePrincipal
     */
    public void setDireccionCallePrincipal(java.lang.String direccionCallePrincipal) {
        this.direccionCallePrincipal = direccionCallePrincipal;
    }


    /**
     * Gets the direccionCalleTransversal value for this PredioDTO.
     * 
     * @return direccionCalleTransversal
     */
    public java.lang.String getDireccionCalleTransversal() {
        return direccionCalleTransversal;
    }


    /**
     * Sets the direccionCalleTransversal value for this PredioDTO.
     * 
     * @param direccionCalleTransversal
     */
    public void setDireccionCalleTransversal(java.lang.String direccionCalleTransversal) {
        this.direccionCalleTransversal = direccionCalleTransversal;
    }


    /**
     * Gets the direccionNumero value for this PredioDTO.
     * 
     * @return direccionNumero
     */
    public java.lang.String getDireccionNumero() {
        return direccionNumero;
    }


    /**
     * Sets the direccionNumero value for this PredioDTO.
     * 
     * @param direccionNumero
     */
    public void setDireccionNumero(java.lang.String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }


    /**
     * Gets the esPrimaFija value for this PredioDTO.
     * 
     * @return esPrimaFija
     */
    public boolean isEsPrimaFija() {
        return esPrimaFija;
    }


    /**
     * Sets the esPrimaFija value for this PredioDTO.
     * 
     * @param esPrimaFija
     */
    public void setEsPrimaFija(boolean esPrimaFija) {
        this.esPrimaFija = esPrimaFija;
    }


    /**
     * Gets the estadoInformacion value for this PredioDTO.
     * 
     * @return estadoInformacion
     */
    public java.lang.String getEstadoInformacion() {
        return estadoInformacion;
    }


    /**
     * Sets the estadoInformacion value for this PredioDTO.
     * 
     * @param estadoInformacion
     */
    public void setEstadoInformacion(java.lang.String estadoInformacion) {
        this.estadoInformacion = estadoInformacion;
    }


    /**
     * Gets the gpsX value for this PredioDTO.
     * 
     * @return gpsX
     */
    public java.lang.String getGpsX() {
        return gpsX;
    }


    /**
     * Sets the gpsX value for this PredioDTO.
     * 
     * @param gpsX
     */
    public void setGpsX(java.lang.String gpsX) {
        this.gpsX = gpsX;
    }


    /**
     * Gets the gpsY value for this PredioDTO.
     * 
     * @return gpsY
     */
    public java.lang.String getGpsY() {
        return gpsY;
    }


    /**
     * Sets the gpsY value for this PredioDTO.
     * 
     * @param gpsY
     */
    public void setGpsY(java.lang.String gpsY) {
        this.gpsY = gpsY;
    }


    /**
     * Gets the materialConstruccionId value for this PredioDTO.
     * 
     * @return materialConstruccionId
     */
    public java.lang.String getMaterialConstruccionId() {
        return materialConstruccionId;
    }


    /**
     * Sets the materialConstruccionId value for this PredioDTO.
     * 
     * @param materialConstruccionId
     */
    public void setMaterialConstruccionId(java.lang.String materialConstruccionId) {
        this.materialConstruccionId = materialConstruccionId;
    }


    /**
     * Gets the nombre value for this PredioDTO.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this PredioDTO.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the numeroPisos value for this PredioDTO.
     * 
     * @return numeroPisos
     */
    public int getNumeroPisos() {
        return numeroPisos;
    }


    /**
     * Sets the numeroPisos value for this PredioDTO.
     * 
     * @param numeroPisos
     */
    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos;
    }


    /**
     * Gets the paisId value for this PredioDTO.
     * 
     * @return paisId
     */
    public java.lang.String getPaisId() {
        return paisId;
    }


    /**
     * Sets the paisId value for this PredioDTO.
     * 
     * @param paisId
     */
    public void setPaisId(java.lang.String paisId) {
        this.paisId = paisId;
    }


    /**
     * Gets the porcentajePrimaDeposito value for this PredioDTO.
     * 
     * @return porcentajePrimaDeposito
     */
    public double getPorcentajePrimaDeposito() {
        return porcentajePrimaDeposito;
    }


    /**
     * Sets the porcentajePrimaDeposito value for this PredioDTO.
     * 
     * @param porcentajePrimaDeposito
     */
    public void setPorcentajePrimaDeposito(double porcentajePrimaDeposito) {
        this.porcentajePrimaDeposito = porcentajePrimaDeposito;
    }


    /**
     * Gets the provinciaId value for this PredioDTO.
     * 
     * @return provinciaId
     */
    public java.lang.String getProvinciaId() {
        return provinciaId;
    }


    /**
     * Sets the provinciaId value for this PredioDTO.
     * 
     * @param provinciaId
     */
    public void setProvinciaId(java.lang.String provinciaId) {
        this.provinciaId = provinciaId;
    }


    /**
     * Gets the tasa value for this PredioDTO.
     * 
     * @return tasa
     */
    public double getTasa() {
        return tasa;
    }


    /**
     * Sets the tasa value for this PredioDTO.
     * 
     * @param tasa
     */
    public void setTasa(double tasa) {
        this.tasa = tasa;
    }


    /**
     * Gets the tieneProteccionInundacion value for this PredioDTO.
     * 
     * @return tieneProteccionInundacion
     */
    public boolean isTieneProteccionInundacion() {
        return tieneProteccionInundacion;
    }


    /**
     * Sets the tieneProteccionInundacion value for this PredioDTO.
     * 
     * @param tieneProteccionInundacion
     */
    public void setTieneProteccionInundacion(boolean tieneProteccionInundacion) {
        this.tieneProteccionInundacion = tieneProteccionInundacion;
    }


    /**
     * Gets the tieneSotano value for this PredioDTO.
     * 
     * @return tieneSotano
     */
    public boolean isTieneSotano() {
        return tieneSotano;
    }


    /**
     * Sets the tieneSotano value for this PredioDTO.
     * 
     * @param tieneSotano
     */
    public void setTieneSotano(boolean tieneSotano) {
        this.tieneSotano = tieneSotano;
    }


    /**
     * Gets the tipoPredioId value for this PredioDTO.
     * 
     * @return tipoPredioId
     */
    public java.lang.String getTipoPredioId() {
        return tipoPredioId;
    }


    /**
     * Sets the tipoPredioId value for this PredioDTO.
     * 
     * @param tipoPredioId
     */
    public void setTipoPredioId(java.lang.String tipoPredioId) {
        this.tipoPredioId = tipoPredioId;
    }


    /**
     * Gets the valorAseguradoPredio value for this PredioDTO.
     * 
     * @return valorAseguradoPredio
     */
    public double getValorAseguradoPredio() {
        return valorAseguradoPredio;
    }


    /**
     * Sets the valorAseguradoPredio value for this PredioDTO.
     * 
     * @param valorAseguradoPredio
     */
    public void setValorAseguradoPredio(double valorAseguradoPredio) {
        this.valorAseguradoPredio = valorAseguradoPredio;
    }


    /**
     * Gets the valorContenido value for this PredioDTO.
     * 
     * @return valorContenido
     */
    public double getValorContenido() {
        return valorContenido;
    }


    /**
     * Sets the valorContenido value for this PredioDTO.
     * 
     * @param valorContenido
     */
    public void setValorContenido(double valorContenido) {
        this.valorContenido = valorContenido;
    }


    /**
     * Gets the valorEdificio value for this PredioDTO.
     * 
     * @return valorEdificio
     */
    public double getValorEdificio() {
        return valorEdificio;
    }


    /**
     * Sets the valorEdificio value for this PredioDTO.
     * 
     * @param valorEdificio
     */
    public void setValorEdificio(double valorEdificio) {
        this.valorEdificio = valorEdificio;
    }


    /**
     * Gets the valorFlotante value for this PredioDTO.
     * 
     * @return valorFlotante
     */
    public double getValorFlotante() {
        return valorFlotante;
    }


    /**
     * Sets the valorFlotante value for this PredioDTO.
     * 
     * @param valorFlotante
     */
    public void setValorFlotante(double valorFlotante) {
        this.valorFlotante = valorFlotante;
    }


    /**
     * Gets the valorPrimaDeposito value for this PredioDTO.
     * 
     * @return valorPrimaDeposito
     */
    public double getValorPrimaDeposito() {
        return valorPrimaDeposito;
    }


    /**
     * Sets the valorPrimaDeposito value for this PredioDTO.
     * 
     * @param valorPrimaDeposito
     */
    public void setValorPrimaDeposito(double valorPrimaDeposito) {
        this.valorPrimaDeposito = valorPrimaDeposito;
    }


    /**
     * Gets the valorPrimaPredio value for this PredioDTO.
     * 
     * @return valorPrimaPredio
     */
    public double getValorPrimaPredio() {
        return valorPrimaPredio;
    }


    /**
     * Sets the valorPrimaPredio value for this PredioDTO.
     * 
     * @param valorPrimaPredio
     */
    public void setValorPrimaPredio(double valorPrimaPredio) {
        this.valorPrimaPredio = valorPrimaPredio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PredioDTO)) return false;
        PredioDTO other = (PredioDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.anioConstruccion == other.getAnioConstruccion() &&
            ((this.ciduadId==null && other.getCiduadId()==null) || 
             (this.ciduadId!=null &&
              this.ciduadId.equals(other.getCiduadId()))) &&
            ((this.coberturasAdicionales==null && other.getCoberturasAdicionales()==null) || 
             (this.coberturasAdicionales!=null &&
              java.util.Arrays.equals(this.coberturasAdicionales, other.getCoberturasAdicionales()))) &&
            ((this.direccionCallePrincipal==null && other.getDireccionCallePrincipal()==null) || 
             (this.direccionCallePrincipal!=null &&
              this.direccionCallePrincipal.equals(other.getDireccionCallePrincipal()))) &&
            ((this.direccionCalleTransversal==null && other.getDireccionCalleTransversal()==null) || 
             (this.direccionCalleTransversal!=null &&
              this.direccionCalleTransversal.equals(other.getDireccionCalleTransversal()))) &&
            ((this.direccionNumero==null && other.getDireccionNumero()==null) || 
             (this.direccionNumero!=null &&
              this.direccionNumero.equals(other.getDireccionNumero()))) &&
            this.esPrimaFija == other.isEsPrimaFija() &&
            ((this.estadoInformacion==null && other.getEstadoInformacion()==null) || 
             (this.estadoInformacion!=null &&
              this.estadoInformacion.equals(other.getEstadoInformacion()))) &&
            ((this.gpsX==null && other.getGpsX()==null) || 
             (this.gpsX!=null &&
              this.gpsX.equals(other.getGpsX()))) &&
            ((this.gpsY==null && other.getGpsY()==null) || 
             (this.gpsY!=null &&
              this.gpsY.equals(other.getGpsY()))) &&
            ((this.materialConstruccionId==null && other.getMaterialConstruccionId()==null) || 
             (this.materialConstruccionId!=null &&
              this.materialConstruccionId.equals(other.getMaterialConstruccionId()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            this.numeroPisos == other.getNumeroPisos() &&
            ((this.paisId==null && other.getPaisId()==null) || 
             (this.paisId!=null &&
              this.paisId.equals(other.getPaisId()))) &&
            this.porcentajePrimaDeposito == other.getPorcentajePrimaDeposito() &&
            ((this.provinciaId==null && other.getProvinciaId()==null) || 
             (this.provinciaId!=null &&
              this.provinciaId.equals(other.getProvinciaId()))) &&
            this.tasa == other.getTasa() &&
            this.tieneProteccionInundacion == other.isTieneProteccionInundacion() &&
            this.tieneSotano == other.isTieneSotano() &&
            ((this.tipoPredioId==null && other.getTipoPredioId()==null) || 
             (this.tipoPredioId!=null &&
              this.tipoPredioId.equals(other.getTipoPredioId()))) &&
            this.valorAseguradoPredio == other.getValorAseguradoPredio() &&
            this.valorContenido == other.getValorContenido() &&
            this.valorEdificio == other.getValorEdificio() &&
            this.valorFlotante == other.getValorFlotante() &&
            this.valorPrimaDeposito == other.getValorPrimaDeposito() &&
            this.valorPrimaPredio == other.getValorPrimaPredio();
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
        _hashCode += getAnioConstruccion();
        if (getCiduadId() != null) {
            _hashCode += getCiduadId().hashCode();
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
        if (getDireccionCallePrincipal() != null) {
            _hashCode += getDireccionCallePrincipal().hashCode();
        }
        if (getDireccionCalleTransversal() != null) {
            _hashCode += getDireccionCalleTransversal().hashCode();
        }
        if (getDireccionNumero() != null) {
            _hashCode += getDireccionNumero().hashCode();
        }
        _hashCode += (isEsPrimaFija() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getEstadoInformacion() != null) {
            _hashCode += getEstadoInformacion().hashCode();
        }
        if (getGpsX() != null) {
            _hashCode += getGpsX().hashCode();
        }
        if (getGpsY() != null) {
            _hashCode += getGpsY().hashCode();
        }
        if (getMaterialConstruccionId() != null) {
            _hashCode += getMaterialConstruccionId().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        _hashCode += getNumeroPisos();
        if (getPaisId() != null) {
            _hashCode += getPaisId().hashCode();
        }
        _hashCode += new Double(getPorcentajePrimaDeposito()).hashCode();
        if (getProvinciaId() != null) {
            _hashCode += getProvinciaId().hashCode();
        }
        _hashCode += new Double(getTasa()).hashCode();
        _hashCode += (isTieneProteccionInundacion() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isTieneSotano() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTipoPredioId() != null) {
            _hashCode += getTipoPredioId().hashCode();
        }
        _hashCode += new Double(getValorAseguradoPredio()).hashCode();
        _hashCode += new Double(getValorContenido()).hashCode();
        _hashCode += new Double(getValorEdificio()).hashCode();
        _hashCode += new Double(getValorFlotante()).hashCode();
        _hashCode += new Double(getValorPrimaDeposito()).hashCode();
        _hashCode += new Double(getValorPrimaPredio()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PredioDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PredioDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anioConstruccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anioConstruccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciduadId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ciduadId"));
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
        elemField.setFieldName("direccionCallePrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccionCallePrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccionCalleTransversal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccionCalleTransversal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccionNumero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccionNumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esPrimaFija");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esPrimaFija"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoInformacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoInformacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gpsX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gpsX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gpsY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "gpsY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("materialConstruccionId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "materialConstruccionId"));
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
        elemField.setFieldName("numeroPisos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroPisos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paisId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "paisId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentajePrimaDeposito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentajePrimaDeposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provinciaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "provinciaId"));
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
        elemField.setFieldName("tieneProteccionInundacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tieneProteccionInundacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tieneSotano");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tieneSotano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoPredioId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoPredioId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAseguradoPredio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorAseguradoPredio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorContenido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorContenido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorEdificio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorEdificio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorFlotante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorFlotante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaDeposito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaDeposito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPrimaPredio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorPrimaPredio"));
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
