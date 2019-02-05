/**
 * RAMO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class RAMO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String CODIGOCONTABLE;

    private java.lang.String CONTABILIZA;

    private java.lang.String CUPOSSINIESTROID;

    private java.lang.Integer DIVIDENDOTASA;

    private java.lang.String ESFIANZA;

    private java.lang.String ESPRIMAEXCENTA;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHAREGISTRO;

    private java.util.Calendar FECHARESOLUCION;

    private java.lang.String ID;

    private java.math.BigDecimal LIMITECUMULO;

    private java.lang.String NOMBRE;

    private java.lang.String NOMBRECONTABLE;

    private java.lang.String NOMBRENEMOTECNICO;

    private java.lang.String NUEVOCODCONTABLE;

    private java.lang.String NUEVONOMBRECONTABLE;

    private java.lang.String NUMEROREGISTRO;

    private java.lang.String NUMERORESOLUCION;

    private java.lang.Short ORDEN;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA;

    private java.math.BigDecimal RESERVAMINIMA;

    private java.math.BigDecimal TASAPROMEDIO;

    private java.lang.String TIPORAMOID;

    private java.lang.String USUARIOACTUALIZA;

    public RAMO() {
    }

    public RAMO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String CODIGOCONTABLE,
           java.lang.String CONTABILIZA,
           java.lang.String CUPOSSINIESTROID,
           java.lang.Integer DIVIDENDOTASA,
           java.lang.String ESFIANZA,
           java.lang.String ESPRIMAEXCENTA,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHAREGISTRO,
           java.util.Calendar FECHARESOLUCION,
           java.lang.String ID,
           java.math.BigDecimal LIMITECUMULO,
           java.lang.String NOMBRE,
           java.lang.String NOMBRECONTABLE,
           java.lang.String NOMBRENEMOTECNICO,
           java.lang.String NUEVOCODCONTABLE,
           java.lang.String NUEVONOMBRECONTABLE,
           java.lang.String NUMEROREGISTRO,
           java.lang.String NUMERORESOLUCION,
           java.lang.Short ORDEN,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA,
           java.math.BigDecimal RESERVAMINIMA,
           java.math.BigDecimal TASAPROMEDIO,
           java.lang.String TIPORAMOID,
           java.lang.String USUARIOACTUALIZA) {
        super(
            id,
            ref,
            entityKey);
        this.CODIGOCONTABLE = CODIGOCONTABLE;
        this.CONTABILIZA = CONTABILIZA;
        this.CUPOSSINIESTROID = CUPOSSINIESTROID;
        this.DIVIDENDOTASA = DIVIDENDOTASA;
        this.ESFIANZA = ESFIANZA;
        this.ESPRIMAEXCENTA = ESPRIMAEXCENTA;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHAREGISTRO = FECHAREGISTRO;
        this.FECHARESOLUCION = FECHARESOLUCION;
        this.ID = ID;
        this.LIMITECUMULO = LIMITECUMULO;
        this.NOMBRE = NOMBRE;
        this.NOMBRECONTABLE = NOMBRECONTABLE;
        this.NOMBRENEMOTECNICO = NOMBRENEMOTECNICO;
        this.NUEVOCODCONTABLE = NUEVOCODCONTABLE;
        this.NUEVONOMBRECONTABLE = NUEVONOMBRECONTABLE;
        this.NUMEROREGISTRO = NUMEROREGISTRO;
        this.NUMERORESOLUCION = NUMERORESOLUCION;
        this.ORDEN = ORDEN;
        this.POLIZA = POLIZA;
        this.RESERVAMINIMA = RESERVAMINIMA;
        this.TASAPROMEDIO = TASAPROMEDIO;
        this.TIPORAMOID = TIPORAMOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the CODIGOCONTABLE value for this RAMO.
     * 
     * @return CODIGOCONTABLE
     */
    public java.lang.String getCODIGOCONTABLE() {
        return CODIGOCONTABLE;
    }


    /**
     * Sets the CODIGOCONTABLE value for this RAMO.
     * 
     * @param CODIGOCONTABLE
     */
    public void setCODIGOCONTABLE(java.lang.String CODIGOCONTABLE) {
        this.CODIGOCONTABLE = CODIGOCONTABLE;
    }


    /**
     * Gets the CONTABILIZA value for this RAMO.
     * 
     * @return CONTABILIZA
     */
    public java.lang.String getCONTABILIZA() {
        return CONTABILIZA;
    }


    /**
     * Sets the CONTABILIZA value for this RAMO.
     * 
     * @param CONTABILIZA
     */
    public void setCONTABILIZA(java.lang.String CONTABILIZA) {
        this.CONTABILIZA = CONTABILIZA;
    }


    /**
     * Gets the CUPOSSINIESTROID value for this RAMO.
     * 
     * @return CUPOSSINIESTROID
     */
    public java.lang.String getCUPOSSINIESTROID() {
        return CUPOSSINIESTROID;
    }


    /**
     * Sets the CUPOSSINIESTROID value for this RAMO.
     * 
     * @param CUPOSSINIESTROID
     */
    public void setCUPOSSINIESTROID(java.lang.String CUPOSSINIESTROID) {
        this.CUPOSSINIESTROID = CUPOSSINIESTROID;
    }


    /**
     * Gets the DIVIDENDOTASA value for this RAMO.
     * 
     * @return DIVIDENDOTASA
     */
    public java.lang.Integer getDIVIDENDOTASA() {
        return DIVIDENDOTASA;
    }


    /**
     * Sets the DIVIDENDOTASA value for this RAMO.
     * 
     * @param DIVIDENDOTASA
     */
    public void setDIVIDENDOTASA(java.lang.Integer DIVIDENDOTASA) {
        this.DIVIDENDOTASA = DIVIDENDOTASA;
    }


    /**
     * Gets the ESFIANZA value for this RAMO.
     * 
     * @return ESFIANZA
     */
    public java.lang.String getESFIANZA() {
        return ESFIANZA;
    }


    /**
     * Sets the ESFIANZA value for this RAMO.
     * 
     * @param ESFIANZA
     */
    public void setESFIANZA(java.lang.String ESFIANZA) {
        this.ESFIANZA = ESFIANZA;
    }


    /**
     * Gets the ESPRIMAEXCENTA value for this RAMO.
     * 
     * @return ESPRIMAEXCENTA
     */
    public java.lang.String getESPRIMAEXCENTA() {
        return ESPRIMAEXCENTA;
    }


    /**
     * Sets the ESPRIMAEXCENTA value for this RAMO.
     * 
     * @param ESPRIMAEXCENTA
     */
    public void setESPRIMAEXCENTA(java.lang.String ESPRIMAEXCENTA) {
        this.ESPRIMAEXCENTA = ESPRIMAEXCENTA;
    }


    /**
     * Gets the FECHAACTUALIZA value for this RAMO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this RAMO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHAREGISTRO value for this RAMO.
     * 
     * @return FECHAREGISTRO
     */
    public java.util.Calendar getFECHAREGISTRO() {
        return FECHAREGISTRO;
    }


    /**
     * Sets the FECHAREGISTRO value for this RAMO.
     * 
     * @param FECHAREGISTRO
     */
    public void setFECHAREGISTRO(java.util.Calendar FECHAREGISTRO) {
        this.FECHAREGISTRO = FECHAREGISTRO;
    }


    /**
     * Gets the FECHARESOLUCION value for this RAMO.
     * 
     * @return FECHARESOLUCION
     */
    public java.util.Calendar getFECHARESOLUCION() {
        return FECHARESOLUCION;
    }


    /**
     * Sets the FECHARESOLUCION value for this RAMO.
     * 
     * @param FECHARESOLUCION
     */
    public void setFECHARESOLUCION(java.util.Calendar FECHARESOLUCION) {
        this.FECHARESOLUCION = FECHARESOLUCION;
    }


    /**
     * Gets the ID value for this RAMO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this RAMO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the LIMITECUMULO value for this RAMO.
     * 
     * @return LIMITECUMULO
     */
    public java.math.BigDecimal getLIMITECUMULO() {
        return LIMITECUMULO;
    }


    /**
     * Sets the LIMITECUMULO value for this RAMO.
     * 
     * @param LIMITECUMULO
     */
    public void setLIMITECUMULO(java.math.BigDecimal LIMITECUMULO) {
        this.LIMITECUMULO = LIMITECUMULO;
    }


    /**
     * Gets the NOMBRE value for this RAMO.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this RAMO.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the NOMBRECONTABLE value for this RAMO.
     * 
     * @return NOMBRECONTABLE
     */
    public java.lang.String getNOMBRECONTABLE() {
        return NOMBRECONTABLE;
    }


    /**
     * Sets the NOMBRECONTABLE value for this RAMO.
     * 
     * @param NOMBRECONTABLE
     */
    public void setNOMBRECONTABLE(java.lang.String NOMBRECONTABLE) {
        this.NOMBRECONTABLE = NOMBRECONTABLE;
    }


    /**
     * Gets the NOMBRENEMOTECNICO value for this RAMO.
     * 
     * @return NOMBRENEMOTECNICO
     */
    public java.lang.String getNOMBRENEMOTECNICO() {
        return NOMBRENEMOTECNICO;
    }


    /**
     * Sets the NOMBRENEMOTECNICO value for this RAMO.
     * 
     * @param NOMBRENEMOTECNICO
     */
    public void setNOMBRENEMOTECNICO(java.lang.String NOMBRENEMOTECNICO) {
        this.NOMBRENEMOTECNICO = NOMBRENEMOTECNICO;
    }


    /**
     * Gets the NUEVOCODCONTABLE value for this RAMO.
     * 
     * @return NUEVOCODCONTABLE
     */
    public java.lang.String getNUEVOCODCONTABLE() {
        return NUEVOCODCONTABLE;
    }


    /**
     * Sets the NUEVOCODCONTABLE value for this RAMO.
     * 
     * @param NUEVOCODCONTABLE
     */
    public void setNUEVOCODCONTABLE(java.lang.String NUEVOCODCONTABLE) {
        this.NUEVOCODCONTABLE = NUEVOCODCONTABLE;
    }


    /**
     * Gets the NUEVONOMBRECONTABLE value for this RAMO.
     * 
     * @return NUEVONOMBRECONTABLE
     */
    public java.lang.String getNUEVONOMBRECONTABLE() {
        return NUEVONOMBRECONTABLE;
    }


    /**
     * Sets the NUEVONOMBRECONTABLE value for this RAMO.
     * 
     * @param NUEVONOMBRECONTABLE
     */
    public void setNUEVONOMBRECONTABLE(java.lang.String NUEVONOMBRECONTABLE) {
        this.NUEVONOMBRECONTABLE = NUEVONOMBRECONTABLE;
    }


    /**
     * Gets the NUMEROREGISTRO value for this RAMO.
     * 
     * @return NUMEROREGISTRO
     */
    public java.lang.String getNUMEROREGISTRO() {
        return NUMEROREGISTRO;
    }


    /**
     * Sets the NUMEROREGISTRO value for this RAMO.
     * 
     * @param NUMEROREGISTRO
     */
    public void setNUMEROREGISTRO(java.lang.String NUMEROREGISTRO) {
        this.NUMEROREGISTRO = NUMEROREGISTRO;
    }


    /**
     * Gets the NUMERORESOLUCION value for this RAMO.
     * 
     * @return NUMERORESOLUCION
     */
    public java.lang.String getNUMERORESOLUCION() {
        return NUMERORESOLUCION;
    }


    /**
     * Sets the NUMERORESOLUCION value for this RAMO.
     * 
     * @param NUMERORESOLUCION
     */
    public void setNUMERORESOLUCION(java.lang.String NUMERORESOLUCION) {
        this.NUMERORESOLUCION = NUMERORESOLUCION;
    }


    /**
     * Gets the ORDEN value for this RAMO.
     * 
     * @return ORDEN
     */
    public java.lang.Short getORDEN() {
        return ORDEN;
    }


    /**
     * Sets the ORDEN value for this RAMO.
     * 
     * @param ORDEN
     */
    public void setORDEN(java.lang.Short ORDEN) {
        this.ORDEN = ORDEN;
    }


    /**
     * Gets the POLIZA value for this RAMO.
     * 
     * @return POLIZA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] getPOLIZA() {
        return POLIZA;
    }


    /**
     * Sets the POLIZA value for this RAMO.
     * 
     * @param POLIZA
     */
    public void setPOLIZA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA) {
        this.POLIZA = POLIZA;
    }


    /**
     * Gets the RESERVAMINIMA value for this RAMO.
     * 
     * @return RESERVAMINIMA
     */
    public java.math.BigDecimal getRESERVAMINIMA() {
        return RESERVAMINIMA;
    }


    /**
     * Sets the RESERVAMINIMA value for this RAMO.
     * 
     * @param RESERVAMINIMA
     */
    public void setRESERVAMINIMA(java.math.BigDecimal RESERVAMINIMA) {
        this.RESERVAMINIMA = RESERVAMINIMA;
    }


    /**
     * Gets the TASAPROMEDIO value for this RAMO.
     * 
     * @return TASAPROMEDIO
     */
    public java.math.BigDecimal getTASAPROMEDIO() {
        return TASAPROMEDIO;
    }


    /**
     * Sets the TASAPROMEDIO value for this RAMO.
     * 
     * @param TASAPROMEDIO
     */
    public void setTASAPROMEDIO(java.math.BigDecimal TASAPROMEDIO) {
        this.TASAPROMEDIO = TASAPROMEDIO;
    }


    /**
     * Gets the TIPORAMOID value for this RAMO.
     * 
     * @return TIPORAMOID
     */
    public java.lang.String getTIPORAMOID() {
        return TIPORAMOID;
    }


    /**
     * Sets the TIPORAMOID value for this RAMO.
     * 
     * @param TIPORAMOID
     */
    public void setTIPORAMOID(java.lang.String TIPORAMOID) {
        this.TIPORAMOID = TIPORAMOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this RAMO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this RAMO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RAMO)) return false;
        RAMO other = (RAMO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.CODIGOCONTABLE==null && other.getCODIGOCONTABLE()==null) || 
             (this.CODIGOCONTABLE!=null &&
              this.CODIGOCONTABLE.equals(other.getCODIGOCONTABLE()))) &&
            ((this.CONTABILIZA==null && other.getCONTABILIZA()==null) || 
             (this.CONTABILIZA!=null &&
              this.CONTABILIZA.equals(other.getCONTABILIZA()))) &&
            ((this.CUPOSSINIESTROID==null && other.getCUPOSSINIESTROID()==null) || 
             (this.CUPOSSINIESTROID!=null &&
              this.CUPOSSINIESTROID.equals(other.getCUPOSSINIESTROID()))) &&
            ((this.DIVIDENDOTASA==null && other.getDIVIDENDOTASA()==null) || 
             (this.DIVIDENDOTASA!=null &&
              this.DIVIDENDOTASA.equals(other.getDIVIDENDOTASA()))) &&
            ((this.ESFIANZA==null && other.getESFIANZA()==null) || 
             (this.ESFIANZA!=null &&
              this.ESFIANZA.equals(other.getESFIANZA()))) &&
            ((this.ESPRIMAEXCENTA==null && other.getESPRIMAEXCENTA()==null) || 
             (this.ESPRIMAEXCENTA!=null &&
              this.ESPRIMAEXCENTA.equals(other.getESPRIMAEXCENTA()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHAREGISTRO==null && other.getFECHAREGISTRO()==null) || 
             (this.FECHAREGISTRO!=null &&
              this.FECHAREGISTRO.equals(other.getFECHAREGISTRO()))) &&
            ((this.FECHARESOLUCION==null && other.getFECHARESOLUCION()==null) || 
             (this.FECHARESOLUCION!=null &&
              this.FECHARESOLUCION.equals(other.getFECHARESOLUCION()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.LIMITECUMULO==null && other.getLIMITECUMULO()==null) || 
             (this.LIMITECUMULO!=null &&
              this.LIMITECUMULO.equals(other.getLIMITECUMULO()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.NOMBRECONTABLE==null && other.getNOMBRECONTABLE()==null) || 
             (this.NOMBRECONTABLE!=null &&
              this.NOMBRECONTABLE.equals(other.getNOMBRECONTABLE()))) &&
            ((this.NOMBRENEMOTECNICO==null && other.getNOMBRENEMOTECNICO()==null) || 
             (this.NOMBRENEMOTECNICO!=null &&
              this.NOMBRENEMOTECNICO.equals(other.getNOMBRENEMOTECNICO()))) &&
            ((this.NUEVOCODCONTABLE==null && other.getNUEVOCODCONTABLE()==null) || 
             (this.NUEVOCODCONTABLE!=null &&
              this.NUEVOCODCONTABLE.equals(other.getNUEVOCODCONTABLE()))) &&
            ((this.NUEVONOMBRECONTABLE==null && other.getNUEVONOMBRECONTABLE()==null) || 
             (this.NUEVONOMBRECONTABLE!=null &&
              this.NUEVONOMBRECONTABLE.equals(other.getNUEVONOMBRECONTABLE()))) &&
            ((this.NUMEROREGISTRO==null && other.getNUMEROREGISTRO()==null) || 
             (this.NUMEROREGISTRO!=null &&
              this.NUMEROREGISTRO.equals(other.getNUMEROREGISTRO()))) &&
            ((this.NUMERORESOLUCION==null && other.getNUMERORESOLUCION()==null) || 
             (this.NUMERORESOLUCION!=null &&
              this.NUMERORESOLUCION.equals(other.getNUMERORESOLUCION()))) &&
            ((this.ORDEN==null && other.getORDEN()==null) || 
             (this.ORDEN!=null &&
              this.ORDEN.equals(other.getORDEN()))) &&
            ((this.POLIZA==null && other.getPOLIZA()==null) || 
             (this.POLIZA!=null &&
              java.util.Arrays.equals(this.POLIZA, other.getPOLIZA()))) &&
            ((this.RESERVAMINIMA==null && other.getRESERVAMINIMA()==null) || 
             (this.RESERVAMINIMA!=null &&
              this.RESERVAMINIMA.equals(other.getRESERVAMINIMA()))) &&
            ((this.TASAPROMEDIO==null && other.getTASAPROMEDIO()==null) || 
             (this.TASAPROMEDIO!=null &&
              this.TASAPROMEDIO.equals(other.getTASAPROMEDIO()))) &&
            ((this.TIPORAMOID==null && other.getTIPORAMOID()==null) || 
             (this.TIPORAMOID!=null &&
              this.TIPORAMOID.equals(other.getTIPORAMOID()))) &&
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
        if (getCODIGOCONTABLE() != null) {
            _hashCode += getCODIGOCONTABLE().hashCode();
        }
        if (getCONTABILIZA() != null) {
            _hashCode += getCONTABILIZA().hashCode();
        }
        if (getCUPOSSINIESTROID() != null) {
            _hashCode += getCUPOSSINIESTROID().hashCode();
        }
        if (getDIVIDENDOTASA() != null) {
            _hashCode += getDIVIDENDOTASA().hashCode();
        }
        if (getESFIANZA() != null) {
            _hashCode += getESFIANZA().hashCode();
        }
        if (getESPRIMAEXCENTA() != null) {
            _hashCode += getESPRIMAEXCENTA().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHAREGISTRO() != null) {
            _hashCode += getFECHAREGISTRO().hashCode();
        }
        if (getFECHARESOLUCION() != null) {
            _hashCode += getFECHARESOLUCION().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getLIMITECUMULO() != null) {
            _hashCode += getLIMITECUMULO().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getNOMBRECONTABLE() != null) {
            _hashCode += getNOMBRECONTABLE().hashCode();
        }
        if (getNOMBRENEMOTECNICO() != null) {
            _hashCode += getNOMBRENEMOTECNICO().hashCode();
        }
        if (getNUEVOCODCONTABLE() != null) {
            _hashCode += getNUEVOCODCONTABLE().hashCode();
        }
        if (getNUEVONOMBRECONTABLE() != null) {
            _hashCode += getNUEVONOMBRECONTABLE().hashCode();
        }
        if (getNUMEROREGISTRO() != null) {
            _hashCode += getNUMEROREGISTRO().hashCode();
        }
        if (getNUMERORESOLUCION() != null) {
            _hashCode += getNUMERORESOLUCION().hashCode();
        }
        if (getORDEN() != null) {
            _hashCode += getORDEN().hashCode();
        }
        if (getPOLIZA() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPOLIZA());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPOLIZA(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRESERVAMINIMA() != null) {
            _hashCode += getRESERVAMINIMA().hashCode();
        }
        if (getTASAPROMEDIO() != null) {
            _hashCode += getTASAPROMEDIO().hashCode();
        }
        if (getTIPORAMOID() != null) {
            _hashCode += getTIPORAMOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RAMO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CODIGOCONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CODIGOCONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTABILIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONTABILIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUPOSSINIESTROID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CUPOSSINIESTROID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIVIDENDOTASA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIVIDENDOTASA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESFIANZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESFIANZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESPRIMAEXCENTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESPRIMAEXCENTA"));
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
        elemField.setFieldName("FECHAREGISTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAREGISTRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHARESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHARESOLUCION"));
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
        elemField.setFieldName("LIMITECUMULO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LIMITECUMULO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("NOMBRECONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRECONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRENEMOTECNICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRENEMOTECNICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUEVOCODCONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUEVOCODCONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUEVONOMBRECONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUEVONOMBRECONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROREGISTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROREGISTRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERORESOLUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMERORESOLUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORDEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESERVAMINIMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RESERVAMINIMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASAPROMEDIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TASAPROMEDIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPORAMOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPORAMOID"));
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
