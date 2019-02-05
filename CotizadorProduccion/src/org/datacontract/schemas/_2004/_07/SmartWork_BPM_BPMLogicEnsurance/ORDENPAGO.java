/**
 * ORDENPAGO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ORDENPAGO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String AUTORIZACIONANULAROPID;

    private java.lang.String AUTORIZACIONDEDUCIBLEID;

    private java.lang.String AUTORIZACIONID;

    private java.lang.String AUTORIZACIONINFRASEGUROID;

    private java.lang.String CONCEPTO;

    private java.lang.String CONVENIOPAGOID;

    private java.lang.String CUPOSXSEMANAID;

    private java.lang.String DEPARTAMENTOID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD1;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDAD1Reference;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO ESTADO;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfESTADOFG7C7FF7 ESTADOReference;

    private java.lang.String ESTAIMPRESACONTRIBUCION;

    private java.lang.String ESTAIMPRESARETENCION;

    private java.lang.String ESTRANSFERENCIA;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHAAPROBACION;

    private java.util.Calendar FECHACOBRO;

    private java.util.Calendar FECHAELABORACION;

    private java.util.Calendar FECHAFIN;

    private java.util.Calendar FECHAINICIO;

    private java.util.Calendar FECHAPAGO;

    private java.lang.String ID;

    private java.lang.String MONEDAID;

    private java.lang.String NOMBREENTIDAD;

    private java.lang.String NUMEROCESION;

    private java.lang.Integer NUMEROORDEN;

    private java.lang.String PAGORECURRENTE;

    private java.math.BigDecimal SALDO;

    private java.lang.String SOLICITANTEID;

    private java.lang.String SUCURSALID;

    private java.lang.String TIPOORDENID;

    private java.math.BigDecimal TIPOPAGO;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String USUARIOAPRUEBAID;

    private java.math.BigDecimal VALOR;

    private java.math.BigDecimal VALORAPAGAR;

    private java.math.BigDecimal VALORIMPUESTOS;

    private java.math.BigDecimal VALORRETENCIONES;

    public ORDENPAGO() {
    }

    public ORDENPAGO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String AUTORIZACIONANULAROPID,
           java.lang.String AUTORIZACIONDEDUCIBLEID,
           java.lang.String AUTORIZACIONID,
           java.lang.String AUTORIZACIONINFRASEGUROID,
           java.lang.String CONCEPTO,
           java.lang.String CONVENIOPAGOID,
           java.lang.String CUPOSXSEMANAID,
           java.lang.String DEPARTAMENTOID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD1,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDAD1Reference,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO ESTADO,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfESTADOFG7C7FF7 ESTADOReference,
           java.lang.String ESTAIMPRESACONTRIBUCION,
           java.lang.String ESTAIMPRESARETENCION,
           java.lang.String ESTRANSFERENCIA,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHAAPROBACION,
           java.util.Calendar FECHACOBRO,
           java.util.Calendar FECHAELABORACION,
           java.util.Calendar FECHAFIN,
           java.util.Calendar FECHAINICIO,
           java.util.Calendar FECHAPAGO,
           java.lang.String ID,
           java.lang.String MONEDAID,
           java.lang.String NOMBREENTIDAD,
           java.lang.String NUMEROCESION,
           java.lang.Integer NUMEROORDEN,
           java.lang.String PAGORECURRENTE,
           java.math.BigDecimal SALDO,
           java.lang.String SOLICITANTEID,
           java.lang.String SUCURSALID,
           java.lang.String TIPOORDENID,
           java.math.BigDecimal TIPOPAGO,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String USUARIOAPRUEBAID,
           java.math.BigDecimal VALOR,
           java.math.BigDecimal VALORAPAGAR,
           java.math.BigDecimal VALORIMPUESTOS,
           java.math.BigDecimal VALORRETENCIONES) {
        super(
            id,
            ref,
            entityKey);
        this.AUTORIZACIONANULAROPID = AUTORIZACIONANULAROPID;
        this.AUTORIZACIONDEDUCIBLEID = AUTORIZACIONDEDUCIBLEID;
        this.AUTORIZACIONID = AUTORIZACIONID;
        this.AUTORIZACIONINFRASEGUROID = AUTORIZACIONINFRASEGUROID;
        this.CONCEPTO = CONCEPTO;
        this.CONVENIOPAGOID = CONVENIOPAGOID;
        this.CUPOSXSEMANAID = CUPOSXSEMANAID;
        this.DEPARTAMENTOID = DEPARTAMENTOID;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDAD1 = ENTIDAD1;
        this.ENTIDAD1Reference = ENTIDAD1Reference;
        this.ENTIDADReference = ENTIDADReference;
        this.ESTADO = ESTADO;
        this.ESTADOReference = ESTADOReference;
        this.ESTAIMPRESACONTRIBUCION = ESTAIMPRESACONTRIBUCION;
        this.ESTAIMPRESARETENCION = ESTAIMPRESARETENCION;
        this.ESTRANSFERENCIA = ESTRANSFERENCIA;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHAAPROBACION = FECHAAPROBACION;
        this.FECHACOBRO = FECHACOBRO;
        this.FECHAELABORACION = FECHAELABORACION;
        this.FECHAFIN = FECHAFIN;
        this.FECHAINICIO = FECHAINICIO;
        this.FECHAPAGO = FECHAPAGO;
        this.ID = ID;
        this.MONEDAID = MONEDAID;
        this.NOMBREENTIDAD = NOMBREENTIDAD;
        this.NUMEROCESION = NUMEROCESION;
        this.NUMEROORDEN = NUMEROORDEN;
        this.PAGORECURRENTE = PAGORECURRENTE;
        this.SALDO = SALDO;
        this.SOLICITANTEID = SOLICITANTEID;
        this.SUCURSALID = SUCURSALID;
        this.TIPOORDENID = TIPOORDENID;
        this.TIPOPAGO = TIPOPAGO;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.USUARIOAPRUEBAID = USUARIOAPRUEBAID;
        this.VALOR = VALOR;
        this.VALORAPAGAR = VALORAPAGAR;
        this.VALORIMPUESTOS = VALORIMPUESTOS;
        this.VALORRETENCIONES = VALORRETENCIONES;
    }


    /**
     * Gets the AUTORIZACIONANULAROPID value for this ORDENPAGO.
     * 
     * @return AUTORIZACIONANULAROPID
     */
    public java.lang.String getAUTORIZACIONANULAROPID() {
        return AUTORIZACIONANULAROPID;
    }


    /**
     * Sets the AUTORIZACIONANULAROPID value for this ORDENPAGO.
     * 
     * @param AUTORIZACIONANULAROPID
     */
    public void setAUTORIZACIONANULAROPID(java.lang.String AUTORIZACIONANULAROPID) {
        this.AUTORIZACIONANULAROPID = AUTORIZACIONANULAROPID;
    }


    /**
     * Gets the AUTORIZACIONDEDUCIBLEID value for this ORDENPAGO.
     * 
     * @return AUTORIZACIONDEDUCIBLEID
     */
    public java.lang.String getAUTORIZACIONDEDUCIBLEID() {
        return AUTORIZACIONDEDUCIBLEID;
    }


    /**
     * Sets the AUTORIZACIONDEDUCIBLEID value for this ORDENPAGO.
     * 
     * @param AUTORIZACIONDEDUCIBLEID
     */
    public void setAUTORIZACIONDEDUCIBLEID(java.lang.String AUTORIZACIONDEDUCIBLEID) {
        this.AUTORIZACIONDEDUCIBLEID = AUTORIZACIONDEDUCIBLEID;
    }


    /**
     * Gets the AUTORIZACIONID value for this ORDENPAGO.
     * 
     * @return AUTORIZACIONID
     */
    public java.lang.String getAUTORIZACIONID() {
        return AUTORIZACIONID;
    }


    /**
     * Sets the AUTORIZACIONID value for this ORDENPAGO.
     * 
     * @param AUTORIZACIONID
     */
    public void setAUTORIZACIONID(java.lang.String AUTORIZACIONID) {
        this.AUTORIZACIONID = AUTORIZACIONID;
    }


    /**
     * Gets the AUTORIZACIONINFRASEGUROID value for this ORDENPAGO.
     * 
     * @return AUTORIZACIONINFRASEGUROID
     */
    public java.lang.String getAUTORIZACIONINFRASEGUROID() {
        return AUTORIZACIONINFRASEGUROID;
    }


    /**
     * Sets the AUTORIZACIONINFRASEGUROID value for this ORDENPAGO.
     * 
     * @param AUTORIZACIONINFRASEGUROID
     */
    public void setAUTORIZACIONINFRASEGUROID(java.lang.String AUTORIZACIONINFRASEGUROID) {
        this.AUTORIZACIONINFRASEGUROID = AUTORIZACIONINFRASEGUROID;
    }


    /**
     * Gets the CONCEPTO value for this ORDENPAGO.
     * 
     * @return CONCEPTO
     */
    public java.lang.String getCONCEPTO() {
        return CONCEPTO;
    }


    /**
     * Sets the CONCEPTO value for this ORDENPAGO.
     * 
     * @param CONCEPTO
     */
    public void setCONCEPTO(java.lang.String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }


    /**
     * Gets the CONVENIOPAGOID value for this ORDENPAGO.
     * 
     * @return CONVENIOPAGOID
     */
    public java.lang.String getCONVENIOPAGOID() {
        return CONVENIOPAGOID;
    }


    /**
     * Sets the CONVENIOPAGOID value for this ORDENPAGO.
     * 
     * @param CONVENIOPAGOID
     */
    public void setCONVENIOPAGOID(java.lang.String CONVENIOPAGOID) {
        this.CONVENIOPAGOID = CONVENIOPAGOID;
    }


    /**
     * Gets the CUPOSXSEMANAID value for this ORDENPAGO.
     * 
     * @return CUPOSXSEMANAID
     */
    public java.lang.String getCUPOSXSEMANAID() {
        return CUPOSXSEMANAID;
    }


    /**
     * Sets the CUPOSXSEMANAID value for this ORDENPAGO.
     * 
     * @param CUPOSXSEMANAID
     */
    public void setCUPOSXSEMANAID(java.lang.String CUPOSXSEMANAID) {
        this.CUPOSXSEMANAID = CUPOSXSEMANAID;
    }


    /**
     * Gets the DEPARTAMENTOID value for this ORDENPAGO.
     * 
     * @return DEPARTAMENTOID
     */
    public java.lang.String getDEPARTAMENTOID() {
        return DEPARTAMENTOID;
    }


    /**
     * Sets the DEPARTAMENTOID value for this ORDENPAGO.
     * 
     * @param DEPARTAMENTOID
     */
    public void setDEPARTAMENTOID(java.lang.String DEPARTAMENTOID) {
        this.DEPARTAMENTOID = DEPARTAMENTOID;
    }


    /**
     * Gets the ENTIDAD value for this ORDENPAGO.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this ORDENPAGO.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDAD1 value for this ORDENPAGO.
     * 
     * @return ENTIDAD1
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD1() {
        return ENTIDAD1;
    }


    /**
     * Sets the ENTIDAD1 value for this ORDENPAGO.
     * 
     * @param ENTIDAD1
     */
    public void setENTIDAD1(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD1) {
        this.ENTIDAD1 = ENTIDAD1;
    }


    /**
     * Gets the ENTIDAD1Reference value for this ORDENPAGO.
     * 
     * @return ENTIDAD1Reference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDAD1Reference() {
        return ENTIDAD1Reference;
    }


    /**
     * Sets the ENTIDAD1Reference value for this ORDENPAGO.
     * 
     * @param ENTIDAD1Reference
     */
    public void setENTIDAD1Reference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDAD1Reference) {
        this.ENTIDAD1Reference = ENTIDAD1Reference;
    }


    /**
     * Gets the ENTIDADReference value for this ORDENPAGO.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this ORDENPAGO.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESTADO value for this ORDENPAGO.
     * 
     * @return ESTADO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this ORDENPAGO.
     * 
     * @param ESTADO
     */
    public void setESTADO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the ESTADOReference value for this ORDENPAGO.
     * 
     * @return ESTADOReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfESTADOFG7C7FF7 getESTADOReference() {
        return ESTADOReference;
    }


    /**
     * Sets the ESTADOReference value for this ORDENPAGO.
     * 
     * @param ESTADOReference
     */
    public void setESTADOReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfESTADOFG7C7FF7 ESTADOReference) {
        this.ESTADOReference = ESTADOReference;
    }


    /**
     * Gets the ESTAIMPRESACONTRIBUCION value for this ORDENPAGO.
     * 
     * @return ESTAIMPRESACONTRIBUCION
     */
    public java.lang.String getESTAIMPRESACONTRIBUCION() {
        return ESTAIMPRESACONTRIBUCION;
    }


    /**
     * Sets the ESTAIMPRESACONTRIBUCION value for this ORDENPAGO.
     * 
     * @param ESTAIMPRESACONTRIBUCION
     */
    public void setESTAIMPRESACONTRIBUCION(java.lang.String ESTAIMPRESACONTRIBUCION) {
        this.ESTAIMPRESACONTRIBUCION = ESTAIMPRESACONTRIBUCION;
    }


    /**
     * Gets the ESTAIMPRESARETENCION value for this ORDENPAGO.
     * 
     * @return ESTAIMPRESARETENCION
     */
    public java.lang.String getESTAIMPRESARETENCION() {
        return ESTAIMPRESARETENCION;
    }


    /**
     * Sets the ESTAIMPRESARETENCION value for this ORDENPAGO.
     * 
     * @param ESTAIMPRESARETENCION
     */
    public void setESTAIMPRESARETENCION(java.lang.String ESTAIMPRESARETENCION) {
        this.ESTAIMPRESARETENCION = ESTAIMPRESARETENCION;
    }


    /**
     * Gets the ESTRANSFERENCIA value for this ORDENPAGO.
     * 
     * @return ESTRANSFERENCIA
     */
    public java.lang.String getESTRANSFERENCIA() {
        return ESTRANSFERENCIA;
    }


    /**
     * Sets the ESTRANSFERENCIA value for this ORDENPAGO.
     * 
     * @param ESTRANSFERENCIA
     */
    public void setESTRANSFERENCIA(java.lang.String ESTRANSFERENCIA) {
        this.ESTRANSFERENCIA = ESTRANSFERENCIA;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ORDENPAGO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ORDENPAGO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHAAPROBACION value for this ORDENPAGO.
     * 
     * @return FECHAAPROBACION
     */
    public java.util.Calendar getFECHAAPROBACION() {
        return FECHAAPROBACION;
    }


    /**
     * Sets the FECHAAPROBACION value for this ORDENPAGO.
     * 
     * @param FECHAAPROBACION
     */
    public void setFECHAAPROBACION(java.util.Calendar FECHAAPROBACION) {
        this.FECHAAPROBACION = FECHAAPROBACION;
    }


    /**
     * Gets the FECHACOBRO value for this ORDENPAGO.
     * 
     * @return FECHACOBRO
     */
    public java.util.Calendar getFECHACOBRO() {
        return FECHACOBRO;
    }


    /**
     * Sets the FECHACOBRO value for this ORDENPAGO.
     * 
     * @param FECHACOBRO
     */
    public void setFECHACOBRO(java.util.Calendar FECHACOBRO) {
        this.FECHACOBRO = FECHACOBRO;
    }


    /**
     * Gets the FECHAELABORACION value for this ORDENPAGO.
     * 
     * @return FECHAELABORACION
     */
    public java.util.Calendar getFECHAELABORACION() {
        return FECHAELABORACION;
    }


    /**
     * Sets the FECHAELABORACION value for this ORDENPAGO.
     * 
     * @param FECHAELABORACION
     */
    public void setFECHAELABORACION(java.util.Calendar FECHAELABORACION) {
        this.FECHAELABORACION = FECHAELABORACION;
    }


    /**
     * Gets the FECHAFIN value for this ORDENPAGO.
     * 
     * @return FECHAFIN
     */
    public java.util.Calendar getFECHAFIN() {
        return FECHAFIN;
    }


    /**
     * Sets the FECHAFIN value for this ORDENPAGO.
     * 
     * @param FECHAFIN
     */
    public void setFECHAFIN(java.util.Calendar FECHAFIN) {
        this.FECHAFIN = FECHAFIN;
    }


    /**
     * Gets the FECHAINICIO value for this ORDENPAGO.
     * 
     * @return FECHAINICIO
     */
    public java.util.Calendar getFECHAINICIO() {
        return FECHAINICIO;
    }


    /**
     * Sets the FECHAINICIO value for this ORDENPAGO.
     * 
     * @param FECHAINICIO
     */
    public void setFECHAINICIO(java.util.Calendar FECHAINICIO) {
        this.FECHAINICIO = FECHAINICIO;
    }


    /**
     * Gets the FECHAPAGO value for this ORDENPAGO.
     * 
     * @return FECHAPAGO
     */
    public java.util.Calendar getFECHAPAGO() {
        return FECHAPAGO;
    }


    /**
     * Sets the FECHAPAGO value for this ORDENPAGO.
     * 
     * @param FECHAPAGO
     */
    public void setFECHAPAGO(java.util.Calendar FECHAPAGO) {
        this.FECHAPAGO = FECHAPAGO;
    }


    /**
     * Gets the ID value for this ORDENPAGO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ORDENPAGO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the MONEDAID value for this ORDENPAGO.
     * 
     * @return MONEDAID
     */
    public java.lang.String getMONEDAID() {
        return MONEDAID;
    }


    /**
     * Sets the MONEDAID value for this ORDENPAGO.
     * 
     * @param MONEDAID
     */
    public void setMONEDAID(java.lang.String MONEDAID) {
        this.MONEDAID = MONEDAID;
    }


    /**
     * Gets the NOMBREENTIDAD value for this ORDENPAGO.
     * 
     * @return NOMBREENTIDAD
     */
    public java.lang.String getNOMBREENTIDAD() {
        return NOMBREENTIDAD;
    }


    /**
     * Sets the NOMBREENTIDAD value for this ORDENPAGO.
     * 
     * @param NOMBREENTIDAD
     */
    public void setNOMBREENTIDAD(java.lang.String NOMBREENTIDAD) {
        this.NOMBREENTIDAD = NOMBREENTIDAD;
    }


    /**
     * Gets the NUMEROCESION value for this ORDENPAGO.
     * 
     * @return NUMEROCESION
     */
    public java.lang.String getNUMEROCESION() {
        return NUMEROCESION;
    }


    /**
     * Sets the NUMEROCESION value for this ORDENPAGO.
     * 
     * @param NUMEROCESION
     */
    public void setNUMEROCESION(java.lang.String NUMEROCESION) {
        this.NUMEROCESION = NUMEROCESION;
    }


    /**
     * Gets the NUMEROORDEN value for this ORDENPAGO.
     * 
     * @return NUMEROORDEN
     */
    public java.lang.Integer getNUMEROORDEN() {
        return NUMEROORDEN;
    }


    /**
     * Sets the NUMEROORDEN value for this ORDENPAGO.
     * 
     * @param NUMEROORDEN
     */
    public void setNUMEROORDEN(java.lang.Integer NUMEROORDEN) {
        this.NUMEROORDEN = NUMEROORDEN;
    }


    /**
     * Gets the PAGORECURRENTE value for this ORDENPAGO.
     * 
     * @return PAGORECURRENTE
     */
    public java.lang.String getPAGORECURRENTE() {
        return PAGORECURRENTE;
    }


    /**
     * Sets the PAGORECURRENTE value for this ORDENPAGO.
     * 
     * @param PAGORECURRENTE
     */
    public void setPAGORECURRENTE(java.lang.String PAGORECURRENTE) {
        this.PAGORECURRENTE = PAGORECURRENTE;
    }


    /**
     * Gets the SALDO value for this ORDENPAGO.
     * 
     * @return SALDO
     */
    public java.math.BigDecimal getSALDO() {
        return SALDO;
    }


    /**
     * Sets the SALDO value for this ORDENPAGO.
     * 
     * @param SALDO
     */
    public void setSALDO(java.math.BigDecimal SALDO) {
        this.SALDO = SALDO;
    }


    /**
     * Gets the SOLICITANTEID value for this ORDENPAGO.
     * 
     * @return SOLICITANTEID
     */
    public java.lang.String getSOLICITANTEID() {
        return SOLICITANTEID;
    }


    /**
     * Sets the SOLICITANTEID value for this ORDENPAGO.
     * 
     * @param SOLICITANTEID
     */
    public void setSOLICITANTEID(java.lang.String SOLICITANTEID) {
        this.SOLICITANTEID = SOLICITANTEID;
    }


    /**
     * Gets the SUCURSALID value for this ORDENPAGO.
     * 
     * @return SUCURSALID
     */
    public java.lang.String getSUCURSALID() {
        return SUCURSALID;
    }


    /**
     * Sets the SUCURSALID value for this ORDENPAGO.
     * 
     * @param SUCURSALID
     */
    public void setSUCURSALID(java.lang.String SUCURSALID) {
        this.SUCURSALID = SUCURSALID;
    }


    /**
     * Gets the TIPOORDENID value for this ORDENPAGO.
     * 
     * @return TIPOORDENID
     */
    public java.lang.String getTIPOORDENID() {
        return TIPOORDENID;
    }


    /**
     * Sets the TIPOORDENID value for this ORDENPAGO.
     * 
     * @param TIPOORDENID
     */
    public void setTIPOORDENID(java.lang.String TIPOORDENID) {
        this.TIPOORDENID = TIPOORDENID;
    }


    /**
     * Gets the TIPOPAGO value for this ORDENPAGO.
     * 
     * @return TIPOPAGO
     */
    public java.math.BigDecimal getTIPOPAGO() {
        return TIPOPAGO;
    }


    /**
     * Sets the TIPOPAGO value for this ORDENPAGO.
     * 
     * @param TIPOPAGO
     */
    public void setTIPOPAGO(java.math.BigDecimal TIPOPAGO) {
        this.TIPOPAGO = TIPOPAGO;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ORDENPAGO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ORDENPAGO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the USUARIOAPRUEBAID value for this ORDENPAGO.
     * 
     * @return USUARIOAPRUEBAID
     */
    public java.lang.String getUSUARIOAPRUEBAID() {
        return USUARIOAPRUEBAID;
    }


    /**
     * Sets the USUARIOAPRUEBAID value for this ORDENPAGO.
     * 
     * @param USUARIOAPRUEBAID
     */
    public void setUSUARIOAPRUEBAID(java.lang.String USUARIOAPRUEBAID) {
        this.USUARIOAPRUEBAID = USUARIOAPRUEBAID;
    }


    /**
     * Gets the VALOR value for this ORDENPAGO.
     * 
     * @return VALOR
     */
    public java.math.BigDecimal getVALOR() {
        return VALOR;
    }


    /**
     * Sets the VALOR value for this ORDENPAGO.
     * 
     * @param VALOR
     */
    public void setVALOR(java.math.BigDecimal VALOR) {
        this.VALOR = VALOR;
    }


    /**
     * Gets the VALORAPAGAR value for this ORDENPAGO.
     * 
     * @return VALORAPAGAR
     */
    public java.math.BigDecimal getVALORAPAGAR() {
        return VALORAPAGAR;
    }


    /**
     * Sets the VALORAPAGAR value for this ORDENPAGO.
     * 
     * @param VALORAPAGAR
     */
    public void setVALORAPAGAR(java.math.BigDecimal VALORAPAGAR) {
        this.VALORAPAGAR = VALORAPAGAR;
    }


    /**
     * Gets the VALORIMPUESTOS value for this ORDENPAGO.
     * 
     * @return VALORIMPUESTOS
     */
    public java.math.BigDecimal getVALORIMPUESTOS() {
        return VALORIMPUESTOS;
    }


    /**
     * Sets the VALORIMPUESTOS value for this ORDENPAGO.
     * 
     * @param VALORIMPUESTOS
     */
    public void setVALORIMPUESTOS(java.math.BigDecimal VALORIMPUESTOS) {
        this.VALORIMPUESTOS = VALORIMPUESTOS;
    }


    /**
     * Gets the VALORRETENCIONES value for this ORDENPAGO.
     * 
     * @return VALORRETENCIONES
     */
    public java.math.BigDecimal getVALORRETENCIONES() {
        return VALORRETENCIONES;
    }


    /**
     * Sets the VALORRETENCIONES value for this ORDENPAGO.
     * 
     * @param VALORRETENCIONES
     */
    public void setVALORRETENCIONES(java.math.BigDecimal VALORRETENCIONES) {
        this.VALORRETENCIONES = VALORRETENCIONES;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ORDENPAGO)) return false;
        ORDENPAGO other = (ORDENPAGO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.AUTORIZACIONANULAROPID==null && other.getAUTORIZACIONANULAROPID()==null) || 
             (this.AUTORIZACIONANULAROPID!=null &&
              this.AUTORIZACIONANULAROPID.equals(other.getAUTORIZACIONANULAROPID()))) &&
            ((this.AUTORIZACIONDEDUCIBLEID==null && other.getAUTORIZACIONDEDUCIBLEID()==null) || 
             (this.AUTORIZACIONDEDUCIBLEID!=null &&
              this.AUTORIZACIONDEDUCIBLEID.equals(other.getAUTORIZACIONDEDUCIBLEID()))) &&
            ((this.AUTORIZACIONID==null && other.getAUTORIZACIONID()==null) || 
             (this.AUTORIZACIONID!=null &&
              this.AUTORIZACIONID.equals(other.getAUTORIZACIONID()))) &&
            ((this.AUTORIZACIONINFRASEGUROID==null && other.getAUTORIZACIONINFRASEGUROID()==null) || 
             (this.AUTORIZACIONINFRASEGUROID!=null &&
              this.AUTORIZACIONINFRASEGUROID.equals(other.getAUTORIZACIONINFRASEGUROID()))) &&
            ((this.CONCEPTO==null && other.getCONCEPTO()==null) || 
             (this.CONCEPTO!=null &&
              this.CONCEPTO.equals(other.getCONCEPTO()))) &&
            ((this.CONVENIOPAGOID==null && other.getCONVENIOPAGOID()==null) || 
             (this.CONVENIOPAGOID!=null &&
              this.CONVENIOPAGOID.equals(other.getCONVENIOPAGOID()))) &&
            ((this.CUPOSXSEMANAID==null && other.getCUPOSXSEMANAID()==null) || 
             (this.CUPOSXSEMANAID!=null &&
              this.CUPOSXSEMANAID.equals(other.getCUPOSXSEMANAID()))) &&
            ((this.DEPARTAMENTOID==null && other.getDEPARTAMENTOID()==null) || 
             (this.DEPARTAMENTOID!=null &&
              this.DEPARTAMENTOID.equals(other.getDEPARTAMENTOID()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDAD1==null && other.getENTIDAD1()==null) || 
             (this.ENTIDAD1!=null &&
              this.ENTIDAD1.equals(other.getENTIDAD1()))) &&
            ((this.ENTIDAD1Reference==null && other.getENTIDAD1Reference()==null) || 
             (this.ENTIDAD1Reference!=null &&
              this.ENTIDAD1Reference.equals(other.getENTIDAD1Reference()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.ESTADOReference==null && other.getESTADOReference()==null) || 
             (this.ESTADOReference!=null &&
              this.ESTADOReference.equals(other.getESTADOReference()))) &&
            ((this.ESTAIMPRESACONTRIBUCION==null && other.getESTAIMPRESACONTRIBUCION()==null) || 
             (this.ESTAIMPRESACONTRIBUCION!=null &&
              this.ESTAIMPRESACONTRIBUCION.equals(other.getESTAIMPRESACONTRIBUCION()))) &&
            ((this.ESTAIMPRESARETENCION==null && other.getESTAIMPRESARETENCION()==null) || 
             (this.ESTAIMPRESARETENCION!=null &&
              this.ESTAIMPRESARETENCION.equals(other.getESTAIMPRESARETENCION()))) &&
            ((this.ESTRANSFERENCIA==null && other.getESTRANSFERENCIA()==null) || 
             (this.ESTRANSFERENCIA!=null &&
              this.ESTRANSFERENCIA.equals(other.getESTRANSFERENCIA()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHAAPROBACION==null && other.getFECHAAPROBACION()==null) || 
             (this.FECHAAPROBACION!=null &&
              this.FECHAAPROBACION.equals(other.getFECHAAPROBACION()))) &&
            ((this.FECHACOBRO==null && other.getFECHACOBRO()==null) || 
             (this.FECHACOBRO!=null &&
              this.FECHACOBRO.equals(other.getFECHACOBRO()))) &&
            ((this.FECHAELABORACION==null && other.getFECHAELABORACION()==null) || 
             (this.FECHAELABORACION!=null &&
              this.FECHAELABORACION.equals(other.getFECHAELABORACION()))) &&
            ((this.FECHAFIN==null && other.getFECHAFIN()==null) || 
             (this.FECHAFIN!=null &&
              this.FECHAFIN.equals(other.getFECHAFIN()))) &&
            ((this.FECHAINICIO==null && other.getFECHAINICIO()==null) || 
             (this.FECHAINICIO!=null &&
              this.FECHAINICIO.equals(other.getFECHAINICIO()))) &&
            ((this.FECHAPAGO==null && other.getFECHAPAGO()==null) || 
             (this.FECHAPAGO!=null &&
              this.FECHAPAGO.equals(other.getFECHAPAGO()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.MONEDAID==null && other.getMONEDAID()==null) || 
             (this.MONEDAID!=null &&
              this.MONEDAID.equals(other.getMONEDAID()))) &&
            ((this.NOMBREENTIDAD==null && other.getNOMBREENTIDAD()==null) || 
             (this.NOMBREENTIDAD!=null &&
              this.NOMBREENTIDAD.equals(other.getNOMBREENTIDAD()))) &&
            ((this.NUMEROCESION==null && other.getNUMEROCESION()==null) || 
             (this.NUMEROCESION!=null &&
              this.NUMEROCESION.equals(other.getNUMEROCESION()))) &&
            ((this.NUMEROORDEN==null && other.getNUMEROORDEN()==null) || 
             (this.NUMEROORDEN!=null &&
              this.NUMEROORDEN.equals(other.getNUMEROORDEN()))) &&
            ((this.PAGORECURRENTE==null && other.getPAGORECURRENTE()==null) || 
             (this.PAGORECURRENTE!=null &&
              this.PAGORECURRENTE.equals(other.getPAGORECURRENTE()))) &&
            ((this.SALDO==null && other.getSALDO()==null) || 
             (this.SALDO!=null &&
              this.SALDO.equals(other.getSALDO()))) &&
            ((this.SOLICITANTEID==null && other.getSOLICITANTEID()==null) || 
             (this.SOLICITANTEID!=null &&
              this.SOLICITANTEID.equals(other.getSOLICITANTEID()))) &&
            ((this.SUCURSALID==null && other.getSUCURSALID()==null) || 
             (this.SUCURSALID!=null &&
              this.SUCURSALID.equals(other.getSUCURSALID()))) &&
            ((this.TIPOORDENID==null && other.getTIPOORDENID()==null) || 
             (this.TIPOORDENID!=null &&
              this.TIPOORDENID.equals(other.getTIPOORDENID()))) &&
            ((this.TIPOPAGO==null && other.getTIPOPAGO()==null) || 
             (this.TIPOPAGO!=null &&
              this.TIPOPAGO.equals(other.getTIPOPAGO()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.USUARIOAPRUEBAID==null && other.getUSUARIOAPRUEBAID()==null) || 
             (this.USUARIOAPRUEBAID!=null &&
              this.USUARIOAPRUEBAID.equals(other.getUSUARIOAPRUEBAID()))) &&
            ((this.VALOR==null && other.getVALOR()==null) || 
             (this.VALOR!=null &&
              this.VALOR.equals(other.getVALOR()))) &&
            ((this.VALORAPAGAR==null && other.getVALORAPAGAR()==null) || 
             (this.VALORAPAGAR!=null &&
              this.VALORAPAGAR.equals(other.getVALORAPAGAR()))) &&
            ((this.VALORIMPUESTOS==null && other.getVALORIMPUESTOS()==null) || 
             (this.VALORIMPUESTOS!=null &&
              this.VALORIMPUESTOS.equals(other.getVALORIMPUESTOS()))) &&
            ((this.VALORRETENCIONES==null && other.getVALORRETENCIONES()==null) || 
             (this.VALORRETENCIONES!=null &&
              this.VALORRETENCIONES.equals(other.getVALORRETENCIONES())));
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
        if (getAUTORIZACIONANULAROPID() != null) {
            _hashCode += getAUTORIZACIONANULAROPID().hashCode();
        }
        if (getAUTORIZACIONDEDUCIBLEID() != null) {
            _hashCode += getAUTORIZACIONDEDUCIBLEID().hashCode();
        }
        if (getAUTORIZACIONID() != null) {
            _hashCode += getAUTORIZACIONID().hashCode();
        }
        if (getAUTORIZACIONINFRASEGUROID() != null) {
            _hashCode += getAUTORIZACIONINFRASEGUROID().hashCode();
        }
        if (getCONCEPTO() != null) {
            _hashCode += getCONCEPTO().hashCode();
        }
        if (getCONVENIOPAGOID() != null) {
            _hashCode += getCONVENIOPAGOID().hashCode();
        }
        if (getCUPOSXSEMANAID() != null) {
            _hashCode += getCUPOSXSEMANAID().hashCode();
        }
        if (getDEPARTAMENTOID() != null) {
            _hashCode += getDEPARTAMENTOID().hashCode();
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDAD1() != null) {
            _hashCode += getENTIDAD1().hashCode();
        }
        if (getENTIDAD1Reference() != null) {
            _hashCode += getENTIDAD1Reference().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getESTADOReference() != null) {
            _hashCode += getESTADOReference().hashCode();
        }
        if (getESTAIMPRESACONTRIBUCION() != null) {
            _hashCode += getESTAIMPRESACONTRIBUCION().hashCode();
        }
        if (getESTAIMPRESARETENCION() != null) {
            _hashCode += getESTAIMPRESARETENCION().hashCode();
        }
        if (getESTRANSFERENCIA() != null) {
            _hashCode += getESTRANSFERENCIA().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHAAPROBACION() != null) {
            _hashCode += getFECHAAPROBACION().hashCode();
        }
        if (getFECHACOBRO() != null) {
            _hashCode += getFECHACOBRO().hashCode();
        }
        if (getFECHAELABORACION() != null) {
            _hashCode += getFECHAELABORACION().hashCode();
        }
        if (getFECHAFIN() != null) {
            _hashCode += getFECHAFIN().hashCode();
        }
        if (getFECHAINICIO() != null) {
            _hashCode += getFECHAINICIO().hashCode();
        }
        if (getFECHAPAGO() != null) {
            _hashCode += getFECHAPAGO().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getMONEDAID() != null) {
            _hashCode += getMONEDAID().hashCode();
        }
        if (getNOMBREENTIDAD() != null) {
            _hashCode += getNOMBREENTIDAD().hashCode();
        }
        if (getNUMEROCESION() != null) {
            _hashCode += getNUMEROCESION().hashCode();
        }
        if (getNUMEROORDEN() != null) {
            _hashCode += getNUMEROORDEN().hashCode();
        }
        if (getPAGORECURRENTE() != null) {
            _hashCode += getPAGORECURRENTE().hashCode();
        }
        if (getSALDO() != null) {
            _hashCode += getSALDO().hashCode();
        }
        if (getSOLICITANTEID() != null) {
            _hashCode += getSOLICITANTEID().hashCode();
        }
        if (getSUCURSALID() != null) {
            _hashCode += getSUCURSALID().hashCode();
        }
        if (getTIPOORDENID() != null) {
            _hashCode += getTIPOORDENID().hashCode();
        }
        if (getTIPOPAGO() != null) {
            _hashCode += getTIPOPAGO().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getUSUARIOAPRUEBAID() != null) {
            _hashCode += getUSUARIOAPRUEBAID().hashCode();
        }
        if (getVALOR() != null) {
            _hashCode += getVALOR().hashCode();
        }
        if (getVALORAPAGAR() != null) {
            _hashCode += getVALORAPAGAR().hashCode();
        }
        if (getVALORIMPUESTOS() != null) {
            _hashCode += getVALORIMPUESTOS().hashCode();
        }
        if (getVALORRETENCIONES() != null) {
            _hashCode += getVALORRETENCIONES().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ORDENPAGO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONANULAROPID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONANULAROPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONDEDUCIBLEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONDEDUCIBLEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONINFRASEGUROID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONINFRASEGUROID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONCEPTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONCEPTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONVENIOPAGOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONVENIOPAGOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CUPOSXSEMANAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CUPOSXSEMANAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPARTAMENTOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEPARTAMENTOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDAD1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDAD1Reference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD1Reference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENTIDADFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDADReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENTIDADFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADOReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfESTADOFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTAIMPRESACONTRIBUCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTAIMPRESACONTRIBUCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTAIMPRESARETENCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTAIMPRESARETENCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTRANSFERENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTRANSFERENCIA"));
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
        elemField.setFieldName("FECHAAPROBACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAAPROBACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACOBRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACOBRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAELABORACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAELABORACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAFIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAFIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAINICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAINICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("MONEDAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MONEDAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBREENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBREENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROCESION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROCESION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROORDEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROORDEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAGORECURRENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAGORECURRENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SALDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOLICITANTEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SOLICITANTEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SUCURSALID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SUCURSALID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOORDENID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOORDENID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIOACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOACTUALIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("USUARIOAPRUEBAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOAPRUEBAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORAPAGAR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORAPAGAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORIMPUESTOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORIMPUESTOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORRETENCIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORRETENCIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
