/**
 * POLIZA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class POLIZA  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String AGENTEID;

    private java.lang.String ANIOSUSCRIPCION2006;

    private java.lang.String AUTORIZACIONID;

    private java.lang.String AUTORIZAPAGO100ID;

    private java.lang.Short CERTIFICADOSRESERVADOS;

    private java.lang.String CLASEPOLIZAID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference;

    private java.lang.String CONFIGPRODUCTOID;

    private java.lang.String DEBITOFIRMADO;

    private java.lang.Integer DEPOSITOCIERREID;

    private java.lang.Short DEPOSITOCUENTABANCARIAID;

    private java.lang.String DEPOSITOESTADO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESANIODORADO;

    private java.lang.String ESCOASEGUROAUTOMATICO;

    private java.lang.String ESDATOOTROSISTEMA;

    private java.lang.String ESFACTURACIONMENSUAL;

    private java.lang.String ESPAGO100;

    private java.lang.String ESPYMES;

    private java.lang.String ESRENOVACION;

    private java.lang.String ESTADOID;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHAANULACION;

    private java.util.Calendar FECHACADUCIDADCERTIFICADOS;

    private java.util.Calendar FECHAELABORACION;

    private java.util.Calendar FECHAVISADO;

    private java.lang.String FUNCIONDENTROTIPOSEGURO;

    private java.lang.String ID;

    private java.lang.String IMPRIMEDIRECCIONPOLIZAHIJA;

    private java.lang.String INGRESOCAJAID;

    private java.lang.String MONEDAID;

    private java.lang.String MOTIVOANULACIONID;

    private java.lang.String MOTIVOEXCLUSIONENVIOESTRUCTURA;

    private java.lang.String MOTIVOLIQ;

    private java.lang.String NOCOBRADERECHOS;

    private java.lang.String NUEVAPOLIZAID;

    private java.lang.Long NUMERO;

    private java.lang.Long NUMEROCADUCADO;

    private java.lang.Integer NUMEROITEM;

    private java.lang.Integer ORDEN;

    private java.lang.String PADREID;

    private java.lang.String PESADOS;

    private java.lang.String POLIZAFIRMA;

    private java.math.BigDecimal PRIMAANUAL;

    private java.math.BigDecimal PRIMAMINIMA;

    private java.math.BigDecimal PRIMATOTAL;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO RAMO;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfRAMOFG7C7FF7 RAMOReference;

    private java.lang.String REEMPLAZO;

    private java.lang.String RENOVACION;

    private java.lang.String RENOVACIONFIANZA;

    private java.lang.Long SECUENCIALTRANSACCION;

    private java.lang.String SINAUTORIZACIONPAGOS;

    private java.math.BigDecimal TASAMINIMA;

    private java.lang.String TIENEDERECHOS;

    private java.lang.String TIPOACEPTADOID;

    private java.lang.String TIPOCONTENEDOR;

    private java.lang.String TIPOSEGUROID;

    private java.lang.String TRATADOID;

    private java.lang.String TRAYECTOASEGURADOID;

    private java.lang.String UNIDADPRODUCCIONID;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String USUARIOVISADO;

    private java.math.BigDecimal VIGENCIA;

    private java.util.Calendar VIGENCIADESDE;

    private java.util.Calendar VIGENCIAHASTA;

    private java.lang.String VIGENCIAPOLIZAID;

    public POLIZA() {
    }

    public POLIZA(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String AGENTEID,
           java.lang.String ANIOSUSCRIPCION2006,
           java.lang.String AUTORIZACIONID,
           java.lang.String AUTORIZAPAGO100ID,
           java.lang.Short CERTIFICADOSRESERVADOS,
           java.lang.String CLASEPOLIZAID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference,
           java.lang.String CONFIGPRODUCTOID,
           java.lang.String DEBITOFIRMADO,
           java.lang.Integer DEPOSITOCIERREID,
           java.lang.Short DEPOSITOCUENTABANCARIAID,
           java.lang.String DEPOSITOESTADO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESANIODORADO,
           java.lang.String ESCOASEGUROAUTOMATICO,
           java.lang.String ESDATOOTROSISTEMA,
           java.lang.String ESFACTURACIONMENSUAL,
           java.lang.String ESPAGO100,
           java.lang.String ESPYMES,
           java.lang.String ESRENOVACION,
           java.lang.String ESTADOID,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHAANULACION,
           java.util.Calendar FECHACADUCIDADCERTIFICADOS,
           java.util.Calendar FECHAELABORACION,
           java.util.Calendar FECHAVISADO,
           java.lang.String FUNCIONDENTROTIPOSEGURO,
           java.lang.String ID,
           java.lang.String IMPRIMEDIRECCIONPOLIZAHIJA,
           java.lang.String INGRESOCAJAID,
           java.lang.String MONEDAID,
           java.lang.String MOTIVOANULACIONID,
           java.lang.String MOTIVOEXCLUSIONENVIOESTRUCTURA,
           java.lang.String MOTIVOLIQ,
           java.lang.String NOCOBRADERECHOS,
           java.lang.String NUEVAPOLIZAID,
           java.lang.Long NUMERO,
           java.lang.Long NUMEROCADUCADO,
           java.lang.Integer NUMEROITEM,
           java.lang.Integer ORDEN,
           java.lang.String PADREID,
           java.lang.String PESADOS,
           java.lang.String POLIZAFIRMA,
           java.math.BigDecimal PRIMAANUAL,
           java.math.BigDecimal PRIMAMINIMA,
           java.math.BigDecimal PRIMATOTAL,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO RAMO,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfRAMOFG7C7FF7 RAMOReference,
           java.lang.String REEMPLAZO,
           java.lang.String RENOVACION,
           java.lang.String RENOVACIONFIANZA,
           java.lang.Long SECUENCIALTRANSACCION,
           java.lang.String SINAUTORIZACIONPAGOS,
           java.math.BigDecimal TASAMINIMA,
           java.lang.String TIENEDERECHOS,
           java.lang.String TIPOACEPTADOID,
           java.lang.String TIPOCONTENEDOR,
           java.lang.String TIPOSEGUROID,
           java.lang.String TRATADOID,
           java.lang.String TRAYECTOASEGURADOID,
           java.lang.String UNIDADPRODUCCIONID,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String USUARIOVISADO,
           java.math.BigDecimal VIGENCIA,
           java.util.Calendar VIGENCIADESDE,
           java.util.Calendar VIGENCIAHASTA,
           java.lang.String VIGENCIAPOLIZAID) {
        super(
            id,
            ref,
            entityKey);
        this.AGENTEID = AGENTEID;
        this.ANIOSUSCRIPCION2006 = ANIOSUSCRIPCION2006;
        this.AUTORIZACIONID = AUTORIZACIONID;
        this.AUTORIZAPAGO100ID = AUTORIZAPAGO100ID;
        this.CERTIFICADOSRESERVADOS = CERTIFICADOSRESERVADOS;
        this.CLASEPOLIZAID = CLASEPOLIZAID;
        this.CLIENTE = CLIENTE;
        this.CLIENTEReference = CLIENTEReference;
        this.CONFIGPRODUCTOID = CONFIGPRODUCTOID;
        this.DEBITOFIRMADO = DEBITOFIRMADO;
        this.DEPOSITOCIERREID = DEPOSITOCIERREID;
        this.DEPOSITOCUENTABANCARIAID = DEPOSITOCUENTABANCARIAID;
        this.DEPOSITOESTADO = DEPOSITOESTADO;
        this.ENDOSO = ENDOSO;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESANIODORADO = ESANIODORADO;
        this.ESCOASEGUROAUTOMATICO = ESCOASEGUROAUTOMATICO;
        this.ESDATOOTROSISTEMA = ESDATOOTROSISTEMA;
        this.ESFACTURACIONMENSUAL = ESFACTURACIONMENSUAL;
        this.ESPAGO100 = ESPAGO100;
        this.ESPYMES = ESPYMES;
        this.ESRENOVACION = ESRENOVACION;
        this.ESTADOID = ESTADOID;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHAANULACION = FECHAANULACION;
        this.FECHACADUCIDADCERTIFICADOS = FECHACADUCIDADCERTIFICADOS;
        this.FECHAELABORACION = FECHAELABORACION;
        this.FECHAVISADO = FECHAVISADO;
        this.FUNCIONDENTROTIPOSEGURO = FUNCIONDENTROTIPOSEGURO;
        this.ID = ID;
        this.IMPRIMEDIRECCIONPOLIZAHIJA = IMPRIMEDIRECCIONPOLIZAHIJA;
        this.INGRESOCAJAID = INGRESOCAJAID;
        this.MONEDAID = MONEDAID;
        this.MOTIVOANULACIONID = MOTIVOANULACIONID;
        this.MOTIVOEXCLUSIONENVIOESTRUCTURA = MOTIVOEXCLUSIONENVIOESTRUCTURA;
        this.MOTIVOLIQ = MOTIVOLIQ;
        this.NOCOBRADERECHOS = NOCOBRADERECHOS;
        this.NUEVAPOLIZAID = NUEVAPOLIZAID;
        this.NUMERO = NUMERO;
        this.NUMEROCADUCADO = NUMEROCADUCADO;
        this.NUMEROITEM = NUMEROITEM;
        this.ORDEN = ORDEN;
        this.PADREID = PADREID;
        this.PESADOS = PESADOS;
        this.POLIZAFIRMA = POLIZAFIRMA;
        this.PRIMAANUAL = PRIMAANUAL;
        this.PRIMAMINIMA = PRIMAMINIMA;
        this.PRIMATOTAL = PRIMATOTAL;
        this.RAMO = RAMO;
        this.RAMOReference = RAMOReference;
        this.REEMPLAZO = REEMPLAZO;
        this.RENOVACION = RENOVACION;
        this.RENOVACIONFIANZA = RENOVACIONFIANZA;
        this.SECUENCIALTRANSACCION = SECUENCIALTRANSACCION;
        this.SINAUTORIZACIONPAGOS = SINAUTORIZACIONPAGOS;
        this.TASAMINIMA = TASAMINIMA;
        this.TIENEDERECHOS = TIENEDERECHOS;
        this.TIPOACEPTADOID = TIPOACEPTADOID;
        this.TIPOCONTENEDOR = TIPOCONTENEDOR;
        this.TIPOSEGUROID = TIPOSEGUROID;
        this.TRATADOID = TRATADOID;
        this.TRAYECTOASEGURADOID = TRAYECTOASEGURADOID;
        this.UNIDADPRODUCCIONID = UNIDADPRODUCCIONID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.USUARIOVISADO = USUARIOVISADO;
        this.VIGENCIA = VIGENCIA;
        this.VIGENCIADESDE = VIGENCIADESDE;
        this.VIGENCIAHASTA = VIGENCIAHASTA;
        this.VIGENCIAPOLIZAID = VIGENCIAPOLIZAID;
    }


    /**
     * Gets the AGENTEID value for this POLIZA.
     * 
     * @return AGENTEID
     */
    public java.lang.String getAGENTEID() {
        return AGENTEID;
    }


    /**
     * Sets the AGENTEID value for this POLIZA.
     * 
     * @param AGENTEID
     */
    public void setAGENTEID(java.lang.String AGENTEID) {
        this.AGENTEID = AGENTEID;
    }


    /**
     * Gets the ANIOSUSCRIPCION2006 value for this POLIZA.
     * 
     * @return ANIOSUSCRIPCION2006
     */
    public java.lang.String getANIOSUSCRIPCION2006() {
        return ANIOSUSCRIPCION2006;
    }


    /**
     * Sets the ANIOSUSCRIPCION2006 value for this POLIZA.
     * 
     * @param ANIOSUSCRIPCION2006
     */
    public void setANIOSUSCRIPCION2006(java.lang.String ANIOSUSCRIPCION2006) {
        this.ANIOSUSCRIPCION2006 = ANIOSUSCRIPCION2006;
    }


    /**
     * Gets the AUTORIZACIONID value for this POLIZA.
     * 
     * @return AUTORIZACIONID
     */
    public java.lang.String getAUTORIZACIONID() {
        return AUTORIZACIONID;
    }


    /**
     * Sets the AUTORIZACIONID value for this POLIZA.
     * 
     * @param AUTORIZACIONID
     */
    public void setAUTORIZACIONID(java.lang.String AUTORIZACIONID) {
        this.AUTORIZACIONID = AUTORIZACIONID;
    }


    /**
     * Gets the AUTORIZAPAGO100ID value for this POLIZA.
     * 
     * @return AUTORIZAPAGO100ID
     */
    public java.lang.String getAUTORIZAPAGO100ID() {
        return AUTORIZAPAGO100ID;
    }


    /**
     * Sets the AUTORIZAPAGO100ID value for this POLIZA.
     * 
     * @param AUTORIZAPAGO100ID
     */
    public void setAUTORIZAPAGO100ID(java.lang.String AUTORIZAPAGO100ID) {
        this.AUTORIZAPAGO100ID = AUTORIZAPAGO100ID;
    }


    /**
     * Gets the CERTIFICADOSRESERVADOS value for this POLIZA.
     * 
     * @return CERTIFICADOSRESERVADOS
     */
    public java.lang.Short getCERTIFICADOSRESERVADOS() {
        return CERTIFICADOSRESERVADOS;
    }


    /**
     * Sets the CERTIFICADOSRESERVADOS value for this POLIZA.
     * 
     * @param CERTIFICADOSRESERVADOS
     */
    public void setCERTIFICADOSRESERVADOS(java.lang.Short CERTIFICADOSRESERVADOS) {
        this.CERTIFICADOSRESERVADOS = CERTIFICADOSRESERVADOS;
    }


    /**
     * Gets the CLASEPOLIZAID value for this POLIZA.
     * 
     * @return CLASEPOLIZAID
     */
    public java.lang.String getCLASEPOLIZAID() {
        return CLASEPOLIZAID;
    }


    /**
     * Sets the CLASEPOLIZAID value for this POLIZA.
     * 
     * @param CLASEPOLIZAID
     */
    public void setCLASEPOLIZAID(java.lang.String CLASEPOLIZAID) {
        this.CLASEPOLIZAID = CLASEPOLIZAID;
    }


    /**
     * Gets the CLIENTE value for this POLIZA.
     * 
     * @return CLIENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE getCLIENTE() {
        return CLIENTE;
    }


    /**
     * Sets the CLIENTE value for this POLIZA.
     * 
     * @param CLIENTE
     */
    public void setCLIENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE) {
        this.CLIENTE = CLIENTE;
    }


    /**
     * Gets the CLIENTEReference value for this POLIZA.
     * 
     * @return CLIENTEReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 getCLIENTEReference() {
        return CLIENTEReference;
    }


    /**
     * Sets the CLIENTEReference value for this POLIZA.
     * 
     * @param CLIENTEReference
     */
    public void setCLIENTEReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference) {
        this.CLIENTEReference = CLIENTEReference;
    }


    /**
     * Gets the CONFIGPRODUCTOID value for this POLIZA.
     * 
     * @return CONFIGPRODUCTOID
     */
    public java.lang.String getCONFIGPRODUCTOID() {
        return CONFIGPRODUCTOID;
    }


    /**
     * Sets the CONFIGPRODUCTOID value for this POLIZA.
     * 
     * @param CONFIGPRODUCTOID
     */
    public void setCONFIGPRODUCTOID(java.lang.String CONFIGPRODUCTOID) {
        this.CONFIGPRODUCTOID = CONFIGPRODUCTOID;
    }


    /**
     * Gets the DEBITOFIRMADO value for this POLIZA.
     * 
     * @return DEBITOFIRMADO
     */
    public java.lang.String getDEBITOFIRMADO() {
        return DEBITOFIRMADO;
    }


    /**
     * Sets the DEBITOFIRMADO value for this POLIZA.
     * 
     * @param DEBITOFIRMADO
     */
    public void setDEBITOFIRMADO(java.lang.String DEBITOFIRMADO) {
        this.DEBITOFIRMADO = DEBITOFIRMADO;
    }


    /**
     * Gets the DEPOSITOCIERREID value for this POLIZA.
     * 
     * @return DEPOSITOCIERREID
     */
    public java.lang.Integer getDEPOSITOCIERREID() {
        return DEPOSITOCIERREID;
    }


    /**
     * Sets the DEPOSITOCIERREID value for this POLIZA.
     * 
     * @param DEPOSITOCIERREID
     */
    public void setDEPOSITOCIERREID(java.lang.Integer DEPOSITOCIERREID) {
        this.DEPOSITOCIERREID = DEPOSITOCIERREID;
    }


    /**
     * Gets the DEPOSITOCUENTABANCARIAID value for this POLIZA.
     * 
     * @return DEPOSITOCUENTABANCARIAID
     */
    public java.lang.Short getDEPOSITOCUENTABANCARIAID() {
        return DEPOSITOCUENTABANCARIAID;
    }


    /**
     * Sets the DEPOSITOCUENTABANCARIAID value for this POLIZA.
     * 
     * @param DEPOSITOCUENTABANCARIAID
     */
    public void setDEPOSITOCUENTABANCARIAID(java.lang.Short DEPOSITOCUENTABANCARIAID) {
        this.DEPOSITOCUENTABANCARIAID = DEPOSITOCUENTABANCARIAID;
    }


    /**
     * Gets the DEPOSITOESTADO value for this POLIZA.
     * 
     * @return DEPOSITOESTADO
     */
    public java.lang.String getDEPOSITOESTADO() {
        return DEPOSITOESTADO;
    }


    /**
     * Sets the DEPOSITOESTADO value for this POLIZA.
     * 
     * @param DEPOSITOESTADO
     */
    public void setDEPOSITOESTADO(java.lang.String DEPOSITOESTADO) {
        this.DEPOSITOESTADO = DEPOSITOESTADO;
    }


    /**
     * Gets the ENDOSO value for this POLIZA.
     * 
     * @return ENDOSO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] getENDOSO() {
        return ENDOSO;
    }


    /**
     * Sets the ENDOSO value for this POLIZA.
     * 
     * @param ENDOSO
     */
    public void setENDOSO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[] ENDOSO) {
        this.ENDOSO = ENDOSO;
    }


    /**
     * Gets the ENTIDAD value for this POLIZA.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this POLIZA.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this POLIZA.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this POLIZA.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESANIODORADO value for this POLIZA.
     * 
     * @return ESANIODORADO
     */
    public java.lang.String getESANIODORADO() {
        return ESANIODORADO;
    }


    /**
     * Sets the ESANIODORADO value for this POLIZA.
     * 
     * @param ESANIODORADO
     */
    public void setESANIODORADO(java.lang.String ESANIODORADO) {
        this.ESANIODORADO = ESANIODORADO;
    }


    /**
     * Gets the ESCOASEGUROAUTOMATICO value for this POLIZA.
     * 
     * @return ESCOASEGUROAUTOMATICO
     */
    public java.lang.String getESCOASEGUROAUTOMATICO() {
        return ESCOASEGUROAUTOMATICO;
    }


    /**
     * Sets the ESCOASEGUROAUTOMATICO value for this POLIZA.
     * 
     * @param ESCOASEGUROAUTOMATICO
     */
    public void setESCOASEGUROAUTOMATICO(java.lang.String ESCOASEGUROAUTOMATICO) {
        this.ESCOASEGUROAUTOMATICO = ESCOASEGUROAUTOMATICO;
    }


    /**
     * Gets the ESDATOOTROSISTEMA value for this POLIZA.
     * 
     * @return ESDATOOTROSISTEMA
     */
    public java.lang.String getESDATOOTROSISTEMA() {
        return ESDATOOTROSISTEMA;
    }


    /**
     * Sets the ESDATOOTROSISTEMA value for this POLIZA.
     * 
     * @param ESDATOOTROSISTEMA
     */
    public void setESDATOOTROSISTEMA(java.lang.String ESDATOOTROSISTEMA) {
        this.ESDATOOTROSISTEMA = ESDATOOTROSISTEMA;
    }


    /**
     * Gets the ESFACTURACIONMENSUAL value for this POLIZA.
     * 
     * @return ESFACTURACIONMENSUAL
     */
    public java.lang.String getESFACTURACIONMENSUAL() {
        return ESFACTURACIONMENSUAL;
    }


    /**
     * Sets the ESFACTURACIONMENSUAL value for this POLIZA.
     * 
     * @param ESFACTURACIONMENSUAL
     */
    public void setESFACTURACIONMENSUAL(java.lang.String ESFACTURACIONMENSUAL) {
        this.ESFACTURACIONMENSUAL = ESFACTURACIONMENSUAL;
    }


    /**
     * Gets the ESPAGO100 value for this POLIZA.
     * 
     * @return ESPAGO100
     */
    public java.lang.String getESPAGO100() {
        return ESPAGO100;
    }


    /**
     * Sets the ESPAGO100 value for this POLIZA.
     * 
     * @param ESPAGO100
     */
    public void setESPAGO100(java.lang.String ESPAGO100) {
        this.ESPAGO100 = ESPAGO100;
    }


    /**
     * Gets the ESPYMES value for this POLIZA.
     * 
     * @return ESPYMES
     */
    public java.lang.String getESPYMES() {
        return ESPYMES;
    }


    /**
     * Sets the ESPYMES value for this POLIZA.
     * 
     * @param ESPYMES
     */
    public void setESPYMES(java.lang.String ESPYMES) {
        this.ESPYMES = ESPYMES;
    }


    /**
     * Gets the ESRENOVACION value for this POLIZA.
     * 
     * @return ESRENOVACION
     */
    public java.lang.String getESRENOVACION() {
        return ESRENOVACION;
    }


    /**
     * Sets the ESRENOVACION value for this POLIZA.
     * 
     * @param ESRENOVACION
     */
    public void setESRENOVACION(java.lang.String ESRENOVACION) {
        this.ESRENOVACION = ESRENOVACION;
    }


    /**
     * Gets the ESTADOID value for this POLIZA.
     * 
     * @return ESTADOID
     */
    public java.lang.String getESTADOID() {
        return ESTADOID;
    }


    /**
     * Sets the ESTADOID value for this POLIZA.
     * 
     * @param ESTADOID
     */
    public void setESTADOID(java.lang.String ESTADOID) {
        this.ESTADOID = ESTADOID;
    }


    /**
     * Gets the FECHAACTUALIZA value for this POLIZA.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this POLIZA.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHAANULACION value for this POLIZA.
     * 
     * @return FECHAANULACION
     */
    public java.util.Calendar getFECHAANULACION() {
        return FECHAANULACION;
    }


    /**
     * Sets the FECHAANULACION value for this POLIZA.
     * 
     * @param FECHAANULACION
     */
    public void setFECHAANULACION(java.util.Calendar FECHAANULACION) {
        this.FECHAANULACION = FECHAANULACION;
    }


    /**
     * Gets the FECHACADUCIDADCERTIFICADOS value for this POLIZA.
     * 
     * @return FECHACADUCIDADCERTIFICADOS
     */
    public java.util.Calendar getFECHACADUCIDADCERTIFICADOS() {
        return FECHACADUCIDADCERTIFICADOS;
    }


    /**
     * Sets the FECHACADUCIDADCERTIFICADOS value for this POLIZA.
     * 
     * @param FECHACADUCIDADCERTIFICADOS
     */
    public void setFECHACADUCIDADCERTIFICADOS(java.util.Calendar FECHACADUCIDADCERTIFICADOS) {
        this.FECHACADUCIDADCERTIFICADOS = FECHACADUCIDADCERTIFICADOS;
    }


    /**
     * Gets the FECHAELABORACION value for this POLIZA.
     * 
     * @return FECHAELABORACION
     */
    public java.util.Calendar getFECHAELABORACION() {
        return FECHAELABORACION;
    }


    /**
     * Sets the FECHAELABORACION value for this POLIZA.
     * 
     * @param FECHAELABORACION
     */
    public void setFECHAELABORACION(java.util.Calendar FECHAELABORACION) {
        this.FECHAELABORACION = FECHAELABORACION;
    }


    /**
     * Gets the FECHAVISADO value for this POLIZA.
     * 
     * @return FECHAVISADO
     */
    public java.util.Calendar getFECHAVISADO() {
        return FECHAVISADO;
    }


    /**
     * Sets the FECHAVISADO value for this POLIZA.
     * 
     * @param FECHAVISADO
     */
    public void setFECHAVISADO(java.util.Calendar FECHAVISADO) {
        this.FECHAVISADO = FECHAVISADO;
    }


    /**
     * Gets the FUNCIONDENTROTIPOSEGURO value for this POLIZA.
     * 
     * @return FUNCIONDENTROTIPOSEGURO
     */
    public java.lang.String getFUNCIONDENTROTIPOSEGURO() {
        return FUNCIONDENTROTIPOSEGURO;
    }


    /**
     * Sets the FUNCIONDENTROTIPOSEGURO value for this POLIZA.
     * 
     * @param FUNCIONDENTROTIPOSEGURO
     */
    public void setFUNCIONDENTROTIPOSEGURO(java.lang.String FUNCIONDENTROTIPOSEGURO) {
        this.FUNCIONDENTROTIPOSEGURO = FUNCIONDENTROTIPOSEGURO;
    }


    /**
     * Gets the ID value for this POLIZA.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this POLIZA.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the IMPRIMEDIRECCIONPOLIZAHIJA value for this POLIZA.
     * 
     * @return IMPRIMEDIRECCIONPOLIZAHIJA
     */
    public java.lang.String getIMPRIMEDIRECCIONPOLIZAHIJA() {
        return IMPRIMEDIRECCIONPOLIZAHIJA;
    }


    /**
     * Sets the IMPRIMEDIRECCIONPOLIZAHIJA value for this POLIZA.
     * 
     * @param IMPRIMEDIRECCIONPOLIZAHIJA
     */
    public void setIMPRIMEDIRECCIONPOLIZAHIJA(java.lang.String IMPRIMEDIRECCIONPOLIZAHIJA) {
        this.IMPRIMEDIRECCIONPOLIZAHIJA = IMPRIMEDIRECCIONPOLIZAHIJA;
    }


    /**
     * Gets the INGRESOCAJAID value for this POLIZA.
     * 
     * @return INGRESOCAJAID
     */
    public java.lang.String getINGRESOCAJAID() {
        return INGRESOCAJAID;
    }


    /**
     * Sets the INGRESOCAJAID value for this POLIZA.
     * 
     * @param INGRESOCAJAID
     */
    public void setINGRESOCAJAID(java.lang.String INGRESOCAJAID) {
        this.INGRESOCAJAID = INGRESOCAJAID;
    }


    /**
     * Gets the MONEDAID value for this POLIZA.
     * 
     * @return MONEDAID
     */
    public java.lang.String getMONEDAID() {
        return MONEDAID;
    }


    /**
     * Sets the MONEDAID value for this POLIZA.
     * 
     * @param MONEDAID
     */
    public void setMONEDAID(java.lang.String MONEDAID) {
        this.MONEDAID = MONEDAID;
    }


    /**
     * Gets the MOTIVOANULACIONID value for this POLIZA.
     * 
     * @return MOTIVOANULACIONID
     */
    public java.lang.String getMOTIVOANULACIONID() {
        return MOTIVOANULACIONID;
    }


    /**
     * Sets the MOTIVOANULACIONID value for this POLIZA.
     * 
     * @param MOTIVOANULACIONID
     */
    public void setMOTIVOANULACIONID(java.lang.String MOTIVOANULACIONID) {
        this.MOTIVOANULACIONID = MOTIVOANULACIONID;
    }


    /**
     * Gets the MOTIVOEXCLUSIONENVIOESTRUCTURA value for this POLIZA.
     * 
     * @return MOTIVOEXCLUSIONENVIOESTRUCTURA
     */
    public java.lang.String getMOTIVOEXCLUSIONENVIOESTRUCTURA() {
        return MOTIVOEXCLUSIONENVIOESTRUCTURA;
    }


    /**
     * Sets the MOTIVOEXCLUSIONENVIOESTRUCTURA value for this POLIZA.
     * 
     * @param MOTIVOEXCLUSIONENVIOESTRUCTURA
     */
    public void setMOTIVOEXCLUSIONENVIOESTRUCTURA(java.lang.String MOTIVOEXCLUSIONENVIOESTRUCTURA) {
        this.MOTIVOEXCLUSIONENVIOESTRUCTURA = MOTIVOEXCLUSIONENVIOESTRUCTURA;
    }


    /**
     * Gets the MOTIVOLIQ value for this POLIZA.
     * 
     * @return MOTIVOLIQ
     */
    public java.lang.String getMOTIVOLIQ() {
        return MOTIVOLIQ;
    }


    /**
     * Sets the MOTIVOLIQ value for this POLIZA.
     * 
     * @param MOTIVOLIQ
     */
    public void setMOTIVOLIQ(java.lang.String MOTIVOLIQ) {
        this.MOTIVOLIQ = MOTIVOLIQ;
    }


    /**
     * Gets the NOCOBRADERECHOS value for this POLIZA.
     * 
     * @return NOCOBRADERECHOS
     */
    public java.lang.String getNOCOBRADERECHOS() {
        return NOCOBRADERECHOS;
    }


    /**
     * Sets the NOCOBRADERECHOS value for this POLIZA.
     * 
     * @param NOCOBRADERECHOS
     */
    public void setNOCOBRADERECHOS(java.lang.String NOCOBRADERECHOS) {
        this.NOCOBRADERECHOS = NOCOBRADERECHOS;
    }


    /**
     * Gets the NUEVAPOLIZAID value for this POLIZA.
     * 
     * @return NUEVAPOLIZAID
     */
    public java.lang.String getNUEVAPOLIZAID() {
        return NUEVAPOLIZAID;
    }


    /**
     * Sets the NUEVAPOLIZAID value for this POLIZA.
     * 
     * @param NUEVAPOLIZAID
     */
    public void setNUEVAPOLIZAID(java.lang.String NUEVAPOLIZAID) {
        this.NUEVAPOLIZAID = NUEVAPOLIZAID;
    }


    /**
     * Gets the NUMERO value for this POLIZA.
     * 
     * @return NUMERO
     */
    public java.lang.Long getNUMERO() {
        return NUMERO;
    }


    /**
     * Sets the NUMERO value for this POLIZA.
     * 
     * @param NUMERO
     */
    public void setNUMERO(java.lang.Long NUMERO) {
        this.NUMERO = NUMERO;
    }


    /**
     * Gets the NUMEROCADUCADO value for this POLIZA.
     * 
     * @return NUMEROCADUCADO
     */
    public java.lang.Long getNUMEROCADUCADO() {
        return NUMEROCADUCADO;
    }


    /**
     * Sets the NUMEROCADUCADO value for this POLIZA.
     * 
     * @param NUMEROCADUCADO
     */
    public void setNUMEROCADUCADO(java.lang.Long NUMEROCADUCADO) {
        this.NUMEROCADUCADO = NUMEROCADUCADO;
    }


    /**
     * Gets the NUMEROITEM value for this POLIZA.
     * 
     * @return NUMEROITEM
     */
    public java.lang.Integer getNUMEROITEM() {
        return NUMEROITEM;
    }


    /**
     * Sets the NUMEROITEM value for this POLIZA.
     * 
     * @param NUMEROITEM
     */
    public void setNUMEROITEM(java.lang.Integer NUMEROITEM) {
        this.NUMEROITEM = NUMEROITEM;
    }


    /**
     * Gets the ORDEN value for this POLIZA.
     * 
     * @return ORDEN
     */
    public java.lang.Integer getORDEN() {
        return ORDEN;
    }


    /**
     * Sets the ORDEN value for this POLIZA.
     * 
     * @param ORDEN
     */
    public void setORDEN(java.lang.Integer ORDEN) {
        this.ORDEN = ORDEN;
    }


    /**
     * Gets the PADREID value for this POLIZA.
     * 
     * @return PADREID
     */
    public java.lang.String getPADREID() {
        return PADREID;
    }


    /**
     * Sets the PADREID value for this POLIZA.
     * 
     * @param PADREID
     */
    public void setPADREID(java.lang.String PADREID) {
        this.PADREID = PADREID;
    }


    /**
     * Gets the PESADOS value for this POLIZA.
     * 
     * @return PESADOS
     */
    public java.lang.String getPESADOS() {
        return PESADOS;
    }


    /**
     * Sets the PESADOS value for this POLIZA.
     * 
     * @param PESADOS
     */
    public void setPESADOS(java.lang.String PESADOS) {
        this.PESADOS = PESADOS;
    }


    /**
     * Gets the POLIZAFIRMA value for this POLIZA.
     * 
     * @return POLIZAFIRMA
     */
    public java.lang.String getPOLIZAFIRMA() {
        return POLIZAFIRMA;
    }


    /**
     * Sets the POLIZAFIRMA value for this POLIZA.
     * 
     * @param POLIZAFIRMA
     */
    public void setPOLIZAFIRMA(java.lang.String POLIZAFIRMA) {
        this.POLIZAFIRMA = POLIZAFIRMA;
    }


    /**
     * Gets the PRIMAANUAL value for this POLIZA.
     * 
     * @return PRIMAANUAL
     */
    public java.math.BigDecimal getPRIMAANUAL() {
        return PRIMAANUAL;
    }


    /**
     * Sets the PRIMAANUAL value for this POLIZA.
     * 
     * @param PRIMAANUAL
     */
    public void setPRIMAANUAL(java.math.BigDecimal PRIMAANUAL) {
        this.PRIMAANUAL = PRIMAANUAL;
    }


    /**
     * Gets the PRIMAMINIMA value for this POLIZA.
     * 
     * @return PRIMAMINIMA
     */
    public java.math.BigDecimal getPRIMAMINIMA() {
        return PRIMAMINIMA;
    }


    /**
     * Sets the PRIMAMINIMA value for this POLIZA.
     * 
     * @param PRIMAMINIMA
     */
    public void setPRIMAMINIMA(java.math.BigDecimal PRIMAMINIMA) {
        this.PRIMAMINIMA = PRIMAMINIMA;
    }


    /**
     * Gets the PRIMATOTAL value for this POLIZA.
     * 
     * @return PRIMATOTAL
     */
    public java.math.BigDecimal getPRIMATOTAL() {
        return PRIMATOTAL;
    }


    /**
     * Sets the PRIMATOTAL value for this POLIZA.
     * 
     * @param PRIMATOTAL
     */
    public void setPRIMATOTAL(java.math.BigDecimal PRIMATOTAL) {
        this.PRIMATOTAL = PRIMATOTAL;
    }


    /**
     * Gets the RAMO value for this POLIZA.
     * 
     * @return RAMO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO getRAMO() {
        return RAMO;
    }


    /**
     * Sets the RAMO value for this POLIZA.
     * 
     * @param RAMO
     */
    public void setRAMO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO RAMO) {
        this.RAMO = RAMO;
    }


    /**
     * Gets the RAMOReference value for this POLIZA.
     * 
     * @return RAMOReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfRAMOFG7C7FF7 getRAMOReference() {
        return RAMOReference;
    }


    /**
     * Sets the RAMOReference value for this POLIZA.
     * 
     * @param RAMOReference
     */
    public void setRAMOReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfRAMOFG7C7FF7 RAMOReference) {
        this.RAMOReference = RAMOReference;
    }


    /**
     * Gets the REEMPLAZO value for this POLIZA.
     * 
     * @return REEMPLAZO
     */
    public java.lang.String getREEMPLAZO() {
        return REEMPLAZO;
    }


    /**
     * Sets the REEMPLAZO value for this POLIZA.
     * 
     * @param REEMPLAZO
     */
    public void setREEMPLAZO(java.lang.String REEMPLAZO) {
        this.REEMPLAZO = REEMPLAZO;
    }


    /**
     * Gets the RENOVACION value for this POLIZA.
     * 
     * @return RENOVACION
     */
    public java.lang.String getRENOVACION() {
        return RENOVACION;
    }


    /**
     * Sets the RENOVACION value for this POLIZA.
     * 
     * @param RENOVACION
     */
    public void setRENOVACION(java.lang.String RENOVACION) {
        this.RENOVACION = RENOVACION;
    }


    /**
     * Gets the RENOVACIONFIANZA value for this POLIZA.
     * 
     * @return RENOVACIONFIANZA
     */
    public java.lang.String getRENOVACIONFIANZA() {
        return RENOVACIONFIANZA;
    }


    /**
     * Sets the RENOVACIONFIANZA value for this POLIZA.
     * 
     * @param RENOVACIONFIANZA
     */
    public void setRENOVACIONFIANZA(java.lang.String RENOVACIONFIANZA) {
        this.RENOVACIONFIANZA = RENOVACIONFIANZA;
    }


    /**
     * Gets the SECUENCIALTRANSACCION value for this POLIZA.
     * 
     * @return SECUENCIALTRANSACCION
     */
    public java.lang.Long getSECUENCIALTRANSACCION() {
        return SECUENCIALTRANSACCION;
    }


    /**
     * Sets the SECUENCIALTRANSACCION value for this POLIZA.
     * 
     * @param SECUENCIALTRANSACCION
     */
    public void setSECUENCIALTRANSACCION(java.lang.Long SECUENCIALTRANSACCION) {
        this.SECUENCIALTRANSACCION = SECUENCIALTRANSACCION;
    }


    /**
     * Gets the SINAUTORIZACIONPAGOS value for this POLIZA.
     * 
     * @return SINAUTORIZACIONPAGOS
     */
    public java.lang.String getSINAUTORIZACIONPAGOS() {
        return SINAUTORIZACIONPAGOS;
    }


    /**
     * Sets the SINAUTORIZACIONPAGOS value for this POLIZA.
     * 
     * @param SINAUTORIZACIONPAGOS
     */
    public void setSINAUTORIZACIONPAGOS(java.lang.String SINAUTORIZACIONPAGOS) {
        this.SINAUTORIZACIONPAGOS = SINAUTORIZACIONPAGOS;
    }


    /**
     * Gets the TASAMINIMA value for this POLIZA.
     * 
     * @return TASAMINIMA
     */
    public java.math.BigDecimal getTASAMINIMA() {
        return TASAMINIMA;
    }


    /**
     * Sets the TASAMINIMA value for this POLIZA.
     * 
     * @param TASAMINIMA
     */
    public void setTASAMINIMA(java.math.BigDecimal TASAMINIMA) {
        this.TASAMINIMA = TASAMINIMA;
    }


    /**
     * Gets the TIENEDERECHOS value for this POLIZA.
     * 
     * @return TIENEDERECHOS
     */
    public java.lang.String getTIENEDERECHOS() {
        return TIENEDERECHOS;
    }


    /**
     * Sets the TIENEDERECHOS value for this POLIZA.
     * 
     * @param TIENEDERECHOS
     */
    public void setTIENEDERECHOS(java.lang.String TIENEDERECHOS) {
        this.TIENEDERECHOS = TIENEDERECHOS;
    }


    /**
     * Gets the TIPOACEPTADOID value for this POLIZA.
     * 
     * @return TIPOACEPTADOID
     */
    public java.lang.String getTIPOACEPTADOID() {
        return TIPOACEPTADOID;
    }


    /**
     * Sets the TIPOACEPTADOID value for this POLIZA.
     * 
     * @param TIPOACEPTADOID
     */
    public void setTIPOACEPTADOID(java.lang.String TIPOACEPTADOID) {
        this.TIPOACEPTADOID = TIPOACEPTADOID;
    }


    /**
     * Gets the TIPOCONTENEDOR value for this POLIZA.
     * 
     * @return TIPOCONTENEDOR
     */
    public java.lang.String getTIPOCONTENEDOR() {
        return TIPOCONTENEDOR;
    }


    /**
     * Sets the TIPOCONTENEDOR value for this POLIZA.
     * 
     * @param TIPOCONTENEDOR
     */
    public void setTIPOCONTENEDOR(java.lang.String TIPOCONTENEDOR) {
        this.TIPOCONTENEDOR = TIPOCONTENEDOR;
    }


    /**
     * Gets the TIPOSEGUROID value for this POLIZA.
     * 
     * @return TIPOSEGUROID
     */
    public java.lang.String getTIPOSEGUROID() {
        return TIPOSEGUROID;
    }


    /**
     * Sets the TIPOSEGUROID value for this POLIZA.
     * 
     * @param TIPOSEGUROID
     */
    public void setTIPOSEGUROID(java.lang.String TIPOSEGUROID) {
        this.TIPOSEGUROID = TIPOSEGUROID;
    }


    /**
     * Gets the TRATADOID value for this POLIZA.
     * 
     * @return TRATADOID
     */
    public java.lang.String getTRATADOID() {
        return TRATADOID;
    }


    /**
     * Sets the TRATADOID value for this POLIZA.
     * 
     * @param TRATADOID
     */
    public void setTRATADOID(java.lang.String TRATADOID) {
        this.TRATADOID = TRATADOID;
    }


    /**
     * Gets the TRAYECTOASEGURADOID value for this POLIZA.
     * 
     * @return TRAYECTOASEGURADOID
     */
    public java.lang.String getTRAYECTOASEGURADOID() {
        return TRAYECTOASEGURADOID;
    }


    /**
     * Sets the TRAYECTOASEGURADOID value for this POLIZA.
     * 
     * @param TRAYECTOASEGURADOID
     */
    public void setTRAYECTOASEGURADOID(java.lang.String TRAYECTOASEGURADOID) {
        this.TRAYECTOASEGURADOID = TRAYECTOASEGURADOID;
    }


    /**
     * Gets the UNIDADPRODUCCIONID value for this POLIZA.
     * 
     * @return UNIDADPRODUCCIONID
     */
    public java.lang.String getUNIDADPRODUCCIONID() {
        return UNIDADPRODUCCIONID;
    }


    /**
     * Sets the UNIDADPRODUCCIONID value for this POLIZA.
     * 
     * @param UNIDADPRODUCCIONID
     */
    public void setUNIDADPRODUCCIONID(java.lang.String UNIDADPRODUCCIONID) {
        this.UNIDADPRODUCCIONID = UNIDADPRODUCCIONID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this POLIZA.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this POLIZA.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the USUARIOVISADO value for this POLIZA.
     * 
     * @return USUARIOVISADO
     */
    public java.lang.String getUSUARIOVISADO() {
        return USUARIOVISADO;
    }


    /**
     * Sets the USUARIOVISADO value for this POLIZA.
     * 
     * @param USUARIOVISADO
     */
    public void setUSUARIOVISADO(java.lang.String USUARIOVISADO) {
        this.USUARIOVISADO = USUARIOVISADO;
    }


    /**
     * Gets the VIGENCIA value for this POLIZA.
     * 
     * @return VIGENCIA
     */
    public java.math.BigDecimal getVIGENCIA() {
        return VIGENCIA;
    }


    /**
     * Sets the VIGENCIA value for this POLIZA.
     * 
     * @param VIGENCIA
     */
    public void setVIGENCIA(java.math.BigDecimal VIGENCIA) {
        this.VIGENCIA = VIGENCIA;
    }


    /**
     * Gets the VIGENCIADESDE value for this POLIZA.
     * 
     * @return VIGENCIADESDE
     */
    public java.util.Calendar getVIGENCIADESDE() {
        return VIGENCIADESDE;
    }


    /**
     * Sets the VIGENCIADESDE value for this POLIZA.
     * 
     * @param VIGENCIADESDE
     */
    public void setVIGENCIADESDE(java.util.Calendar VIGENCIADESDE) {
        this.VIGENCIADESDE = VIGENCIADESDE;
    }


    /**
     * Gets the VIGENCIAHASTA value for this POLIZA.
     * 
     * @return VIGENCIAHASTA
     */
    public java.util.Calendar getVIGENCIAHASTA() {
        return VIGENCIAHASTA;
    }


    /**
     * Sets the VIGENCIAHASTA value for this POLIZA.
     * 
     * @param VIGENCIAHASTA
     */
    public void setVIGENCIAHASTA(java.util.Calendar VIGENCIAHASTA) {
        this.VIGENCIAHASTA = VIGENCIAHASTA;
    }


    /**
     * Gets the VIGENCIAPOLIZAID value for this POLIZA.
     * 
     * @return VIGENCIAPOLIZAID
     */
    public java.lang.String getVIGENCIAPOLIZAID() {
        return VIGENCIAPOLIZAID;
    }


    /**
     * Sets the VIGENCIAPOLIZAID value for this POLIZA.
     * 
     * @param VIGENCIAPOLIZAID
     */
    public void setVIGENCIAPOLIZAID(java.lang.String VIGENCIAPOLIZAID) {
        this.VIGENCIAPOLIZAID = VIGENCIAPOLIZAID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof POLIZA)) return false;
        POLIZA other = (POLIZA) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.AGENTEID==null && other.getAGENTEID()==null) || 
             (this.AGENTEID!=null &&
              this.AGENTEID.equals(other.getAGENTEID()))) &&
            ((this.ANIOSUSCRIPCION2006==null && other.getANIOSUSCRIPCION2006()==null) || 
             (this.ANIOSUSCRIPCION2006!=null &&
              this.ANIOSUSCRIPCION2006.equals(other.getANIOSUSCRIPCION2006()))) &&
            ((this.AUTORIZACIONID==null && other.getAUTORIZACIONID()==null) || 
             (this.AUTORIZACIONID!=null &&
              this.AUTORIZACIONID.equals(other.getAUTORIZACIONID()))) &&
            ((this.AUTORIZAPAGO100ID==null && other.getAUTORIZAPAGO100ID()==null) || 
             (this.AUTORIZAPAGO100ID!=null &&
              this.AUTORIZAPAGO100ID.equals(other.getAUTORIZAPAGO100ID()))) &&
            ((this.CERTIFICADOSRESERVADOS==null && other.getCERTIFICADOSRESERVADOS()==null) || 
             (this.CERTIFICADOSRESERVADOS!=null &&
              this.CERTIFICADOSRESERVADOS.equals(other.getCERTIFICADOSRESERVADOS()))) &&
            ((this.CLASEPOLIZAID==null && other.getCLASEPOLIZAID()==null) || 
             (this.CLASEPOLIZAID!=null &&
              this.CLASEPOLIZAID.equals(other.getCLASEPOLIZAID()))) &&
            ((this.CLIENTE==null && other.getCLIENTE()==null) || 
             (this.CLIENTE!=null &&
              this.CLIENTE.equals(other.getCLIENTE()))) &&
            ((this.CLIENTEReference==null && other.getCLIENTEReference()==null) || 
             (this.CLIENTEReference!=null &&
              this.CLIENTEReference.equals(other.getCLIENTEReference()))) &&
            ((this.CONFIGPRODUCTOID==null && other.getCONFIGPRODUCTOID()==null) || 
             (this.CONFIGPRODUCTOID!=null &&
              this.CONFIGPRODUCTOID.equals(other.getCONFIGPRODUCTOID()))) &&
            ((this.DEBITOFIRMADO==null && other.getDEBITOFIRMADO()==null) || 
             (this.DEBITOFIRMADO!=null &&
              this.DEBITOFIRMADO.equals(other.getDEBITOFIRMADO()))) &&
            ((this.DEPOSITOCIERREID==null && other.getDEPOSITOCIERREID()==null) || 
             (this.DEPOSITOCIERREID!=null &&
              this.DEPOSITOCIERREID.equals(other.getDEPOSITOCIERREID()))) &&
            ((this.DEPOSITOCUENTABANCARIAID==null && other.getDEPOSITOCUENTABANCARIAID()==null) || 
             (this.DEPOSITOCUENTABANCARIAID!=null &&
              this.DEPOSITOCUENTABANCARIAID.equals(other.getDEPOSITOCUENTABANCARIAID()))) &&
            ((this.DEPOSITOESTADO==null && other.getDEPOSITOESTADO()==null) || 
             (this.DEPOSITOESTADO!=null &&
              this.DEPOSITOESTADO.equals(other.getDEPOSITOESTADO()))) &&
            ((this.ENDOSO==null && other.getENDOSO()==null) || 
             (this.ENDOSO!=null &&
              java.util.Arrays.equals(this.ENDOSO, other.getENDOSO()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESANIODORADO==null && other.getESANIODORADO()==null) || 
             (this.ESANIODORADO!=null &&
              this.ESANIODORADO.equals(other.getESANIODORADO()))) &&
            ((this.ESCOASEGUROAUTOMATICO==null && other.getESCOASEGUROAUTOMATICO()==null) || 
             (this.ESCOASEGUROAUTOMATICO!=null &&
              this.ESCOASEGUROAUTOMATICO.equals(other.getESCOASEGUROAUTOMATICO()))) &&
            ((this.ESDATOOTROSISTEMA==null && other.getESDATOOTROSISTEMA()==null) || 
             (this.ESDATOOTROSISTEMA!=null &&
              this.ESDATOOTROSISTEMA.equals(other.getESDATOOTROSISTEMA()))) &&
            ((this.ESFACTURACIONMENSUAL==null && other.getESFACTURACIONMENSUAL()==null) || 
             (this.ESFACTURACIONMENSUAL!=null &&
              this.ESFACTURACIONMENSUAL.equals(other.getESFACTURACIONMENSUAL()))) &&
            ((this.ESPAGO100==null && other.getESPAGO100()==null) || 
             (this.ESPAGO100!=null &&
              this.ESPAGO100.equals(other.getESPAGO100()))) &&
            ((this.ESPYMES==null && other.getESPYMES()==null) || 
             (this.ESPYMES!=null &&
              this.ESPYMES.equals(other.getESPYMES()))) &&
            ((this.ESRENOVACION==null && other.getESRENOVACION()==null) || 
             (this.ESRENOVACION!=null &&
              this.ESRENOVACION.equals(other.getESRENOVACION()))) &&
            ((this.ESTADOID==null && other.getESTADOID()==null) || 
             (this.ESTADOID!=null &&
              this.ESTADOID.equals(other.getESTADOID()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHAANULACION==null && other.getFECHAANULACION()==null) || 
             (this.FECHAANULACION!=null &&
              this.FECHAANULACION.equals(other.getFECHAANULACION()))) &&
            ((this.FECHACADUCIDADCERTIFICADOS==null && other.getFECHACADUCIDADCERTIFICADOS()==null) || 
             (this.FECHACADUCIDADCERTIFICADOS!=null &&
              this.FECHACADUCIDADCERTIFICADOS.equals(other.getFECHACADUCIDADCERTIFICADOS()))) &&
            ((this.FECHAELABORACION==null && other.getFECHAELABORACION()==null) || 
             (this.FECHAELABORACION!=null &&
              this.FECHAELABORACION.equals(other.getFECHAELABORACION()))) &&
            ((this.FECHAVISADO==null && other.getFECHAVISADO()==null) || 
             (this.FECHAVISADO!=null &&
              this.FECHAVISADO.equals(other.getFECHAVISADO()))) &&
            ((this.FUNCIONDENTROTIPOSEGURO==null && other.getFUNCIONDENTROTIPOSEGURO()==null) || 
             (this.FUNCIONDENTROTIPOSEGURO!=null &&
              this.FUNCIONDENTROTIPOSEGURO.equals(other.getFUNCIONDENTROTIPOSEGURO()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.IMPRIMEDIRECCIONPOLIZAHIJA==null && other.getIMPRIMEDIRECCIONPOLIZAHIJA()==null) || 
             (this.IMPRIMEDIRECCIONPOLIZAHIJA!=null &&
              this.IMPRIMEDIRECCIONPOLIZAHIJA.equals(other.getIMPRIMEDIRECCIONPOLIZAHIJA()))) &&
            ((this.INGRESOCAJAID==null && other.getINGRESOCAJAID()==null) || 
             (this.INGRESOCAJAID!=null &&
              this.INGRESOCAJAID.equals(other.getINGRESOCAJAID()))) &&
            ((this.MONEDAID==null && other.getMONEDAID()==null) || 
             (this.MONEDAID!=null &&
              this.MONEDAID.equals(other.getMONEDAID()))) &&
            ((this.MOTIVOANULACIONID==null && other.getMOTIVOANULACIONID()==null) || 
             (this.MOTIVOANULACIONID!=null &&
              this.MOTIVOANULACIONID.equals(other.getMOTIVOANULACIONID()))) &&
            ((this.MOTIVOEXCLUSIONENVIOESTRUCTURA==null && other.getMOTIVOEXCLUSIONENVIOESTRUCTURA()==null) || 
             (this.MOTIVOEXCLUSIONENVIOESTRUCTURA!=null &&
              this.MOTIVOEXCLUSIONENVIOESTRUCTURA.equals(other.getMOTIVOEXCLUSIONENVIOESTRUCTURA()))) &&
            ((this.MOTIVOLIQ==null && other.getMOTIVOLIQ()==null) || 
             (this.MOTIVOLIQ!=null &&
              this.MOTIVOLIQ.equals(other.getMOTIVOLIQ()))) &&
            ((this.NOCOBRADERECHOS==null && other.getNOCOBRADERECHOS()==null) || 
             (this.NOCOBRADERECHOS!=null &&
              this.NOCOBRADERECHOS.equals(other.getNOCOBRADERECHOS()))) &&
            ((this.NUEVAPOLIZAID==null && other.getNUEVAPOLIZAID()==null) || 
             (this.NUEVAPOLIZAID!=null &&
              this.NUEVAPOLIZAID.equals(other.getNUEVAPOLIZAID()))) &&
            ((this.NUMERO==null && other.getNUMERO()==null) || 
             (this.NUMERO!=null &&
              this.NUMERO.equals(other.getNUMERO()))) &&
            ((this.NUMEROCADUCADO==null && other.getNUMEROCADUCADO()==null) || 
             (this.NUMEROCADUCADO!=null &&
              this.NUMEROCADUCADO.equals(other.getNUMEROCADUCADO()))) &&
            ((this.NUMEROITEM==null && other.getNUMEROITEM()==null) || 
             (this.NUMEROITEM!=null &&
              this.NUMEROITEM.equals(other.getNUMEROITEM()))) &&
            ((this.ORDEN==null && other.getORDEN()==null) || 
             (this.ORDEN!=null &&
              this.ORDEN.equals(other.getORDEN()))) &&
            ((this.PADREID==null && other.getPADREID()==null) || 
             (this.PADREID!=null &&
              this.PADREID.equals(other.getPADREID()))) &&
            ((this.PESADOS==null && other.getPESADOS()==null) || 
             (this.PESADOS!=null &&
              this.PESADOS.equals(other.getPESADOS()))) &&
            ((this.POLIZAFIRMA==null && other.getPOLIZAFIRMA()==null) || 
             (this.POLIZAFIRMA!=null &&
              this.POLIZAFIRMA.equals(other.getPOLIZAFIRMA()))) &&
            ((this.PRIMAANUAL==null && other.getPRIMAANUAL()==null) || 
             (this.PRIMAANUAL!=null &&
              this.PRIMAANUAL.equals(other.getPRIMAANUAL()))) &&
            ((this.PRIMAMINIMA==null && other.getPRIMAMINIMA()==null) || 
             (this.PRIMAMINIMA!=null &&
              this.PRIMAMINIMA.equals(other.getPRIMAMINIMA()))) &&
            ((this.PRIMATOTAL==null && other.getPRIMATOTAL()==null) || 
             (this.PRIMATOTAL!=null &&
              this.PRIMATOTAL.equals(other.getPRIMATOTAL()))) &&
            ((this.RAMO==null && other.getRAMO()==null) || 
             (this.RAMO!=null &&
              this.RAMO.equals(other.getRAMO()))) &&
            ((this.RAMOReference==null && other.getRAMOReference()==null) || 
             (this.RAMOReference!=null &&
              this.RAMOReference.equals(other.getRAMOReference()))) &&
            ((this.REEMPLAZO==null && other.getREEMPLAZO()==null) || 
             (this.REEMPLAZO!=null &&
              this.REEMPLAZO.equals(other.getREEMPLAZO()))) &&
            ((this.RENOVACION==null && other.getRENOVACION()==null) || 
             (this.RENOVACION!=null &&
              this.RENOVACION.equals(other.getRENOVACION()))) &&
            ((this.RENOVACIONFIANZA==null && other.getRENOVACIONFIANZA()==null) || 
             (this.RENOVACIONFIANZA!=null &&
              this.RENOVACIONFIANZA.equals(other.getRENOVACIONFIANZA()))) &&
            ((this.SECUENCIALTRANSACCION==null && other.getSECUENCIALTRANSACCION()==null) || 
             (this.SECUENCIALTRANSACCION!=null &&
              this.SECUENCIALTRANSACCION.equals(other.getSECUENCIALTRANSACCION()))) &&
            ((this.SINAUTORIZACIONPAGOS==null && other.getSINAUTORIZACIONPAGOS()==null) || 
             (this.SINAUTORIZACIONPAGOS!=null &&
              this.SINAUTORIZACIONPAGOS.equals(other.getSINAUTORIZACIONPAGOS()))) &&
            ((this.TASAMINIMA==null && other.getTASAMINIMA()==null) || 
             (this.TASAMINIMA!=null &&
              this.TASAMINIMA.equals(other.getTASAMINIMA()))) &&
            ((this.TIENEDERECHOS==null && other.getTIENEDERECHOS()==null) || 
             (this.TIENEDERECHOS!=null &&
              this.TIENEDERECHOS.equals(other.getTIENEDERECHOS()))) &&
            ((this.TIPOACEPTADOID==null && other.getTIPOACEPTADOID()==null) || 
             (this.TIPOACEPTADOID!=null &&
              this.TIPOACEPTADOID.equals(other.getTIPOACEPTADOID()))) &&
            ((this.TIPOCONTENEDOR==null && other.getTIPOCONTENEDOR()==null) || 
             (this.TIPOCONTENEDOR!=null &&
              this.TIPOCONTENEDOR.equals(other.getTIPOCONTENEDOR()))) &&
            ((this.TIPOSEGUROID==null && other.getTIPOSEGUROID()==null) || 
             (this.TIPOSEGUROID!=null &&
              this.TIPOSEGUROID.equals(other.getTIPOSEGUROID()))) &&
            ((this.TRATADOID==null && other.getTRATADOID()==null) || 
             (this.TRATADOID!=null &&
              this.TRATADOID.equals(other.getTRATADOID()))) &&
            ((this.TRAYECTOASEGURADOID==null && other.getTRAYECTOASEGURADOID()==null) || 
             (this.TRAYECTOASEGURADOID!=null &&
              this.TRAYECTOASEGURADOID.equals(other.getTRAYECTOASEGURADOID()))) &&
            ((this.UNIDADPRODUCCIONID==null && other.getUNIDADPRODUCCIONID()==null) || 
             (this.UNIDADPRODUCCIONID!=null &&
              this.UNIDADPRODUCCIONID.equals(other.getUNIDADPRODUCCIONID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.USUARIOVISADO==null && other.getUSUARIOVISADO()==null) || 
             (this.USUARIOVISADO!=null &&
              this.USUARIOVISADO.equals(other.getUSUARIOVISADO()))) &&
            ((this.VIGENCIA==null && other.getVIGENCIA()==null) || 
             (this.VIGENCIA!=null &&
              this.VIGENCIA.equals(other.getVIGENCIA()))) &&
            ((this.VIGENCIADESDE==null && other.getVIGENCIADESDE()==null) || 
             (this.VIGENCIADESDE!=null &&
              this.VIGENCIADESDE.equals(other.getVIGENCIADESDE()))) &&
            ((this.VIGENCIAHASTA==null && other.getVIGENCIAHASTA()==null) || 
             (this.VIGENCIAHASTA!=null &&
              this.VIGENCIAHASTA.equals(other.getVIGENCIAHASTA()))) &&
            ((this.VIGENCIAPOLIZAID==null && other.getVIGENCIAPOLIZAID()==null) || 
             (this.VIGENCIAPOLIZAID!=null &&
              this.VIGENCIAPOLIZAID.equals(other.getVIGENCIAPOLIZAID())));
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
        if (getAGENTEID() != null) {
            _hashCode += getAGENTEID().hashCode();
        }
        if (getANIOSUSCRIPCION2006() != null) {
            _hashCode += getANIOSUSCRIPCION2006().hashCode();
        }
        if (getAUTORIZACIONID() != null) {
            _hashCode += getAUTORIZACIONID().hashCode();
        }
        if (getAUTORIZAPAGO100ID() != null) {
            _hashCode += getAUTORIZAPAGO100ID().hashCode();
        }
        if (getCERTIFICADOSRESERVADOS() != null) {
            _hashCode += getCERTIFICADOSRESERVADOS().hashCode();
        }
        if (getCLASEPOLIZAID() != null) {
            _hashCode += getCLASEPOLIZAID().hashCode();
        }
        if (getCLIENTE() != null) {
            _hashCode += getCLIENTE().hashCode();
        }
        if (getCLIENTEReference() != null) {
            _hashCode += getCLIENTEReference().hashCode();
        }
        if (getCONFIGPRODUCTOID() != null) {
            _hashCode += getCONFIGPRODUCTOID().hashCode();
        }
        if (getDEBITOFIRMADO() != null) {
            _hashCode += getDEBITOFIRMADO().hashCode();
        }
        if (getDEPOSITOCIERREID() != null) {
            _hashCode += getDEPOSITOCIERREID().hashCode();
        }
        if (getDEPOSITOCUENTABANCARIAID() != null) {
            _hashCode += getDEPOSITOCUENTABANCARIAID().hashCode();
        }
        if (getDEPOSITOESTADO() != null) {
            _hashCode += getDEPOSITOESTADO().hashCode();
        }
        if (getENDOSO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getENDOSO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getENDOSO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESANIODORADO() != null) {
            _hashCode += getESANIODORADO().hashCode();
        }
        if (getESCOASEGUROAUTOMATICO() != null) {
            _hashCode += getESCOASEGUROAUTOMATICO().hashCode();
        }
        if (getESDATOOTROSISTEMA() != null) {
            _hashCode += getESDATOOTROSISTEMA().hashCode();
        }
        if (getESFACTURACIONMENSUAL() != null) {
            _hashCode += getESFACTURACIONMENSUAL().hashCode();
        }
        if (getESPAGO100() != null) {
            _hashCode += getESPAGO100().hashCode();
        }
        if (getESPYMES() != null) {
            _hashCode += getESPYMES().hashCode();
        }
        if (getESRENOVACION() != null) {
            _hashCode += getESRENOVACION().hashCode();
        }
        if (getESTADOID() != null) {
            _hashCode += getESTADOID().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHAANULACION() != null) {
            _hashCode += getFECHAANULACION().hashCode();
        }
        if (getFECHACADUCIDADCERTIFICADOS() != null) {
            _hashCode += getFECHACADUCIDADCERTIFICADOS().hashCode();
        }
        if (getFECHAELABORACION() != null) {
            _hashCode += getFECHAELABORACION().hashCode();
        }
        if (getFECHAVISADO() != null) {
            _hashCode += getFECHAVISADO().hashCode();
        }
        if (getFUNCIONDENTROTIPOSEGURO() != null) {
            _hashCode += getFUNCIONDENTROTIPOSEGURO().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getIMPRIMEDIRECCIONPOLIZAHIJA() != null) {
            _hashCode += getIMPRIMEDIRECCIONPOLIZAHIJA().hashCode();
        }
        if (getINGRESOCAJAID() != null) {
            _hashCode += getINGRESOCAJAID().hashCode();
        }
        if (getMONEDAID() != null) {
            _hashCode += getMONEDAID().hashCode();
        }
        if (getMOTIVOANULACIONID() != null) {
            _hashCode += getMOTIVOANULACIONID().hashCode();
        }
        if (getMOTIVOEXCLUSIONENVIOESTRUCTURA() != null) {
            _hashCode += getMOTIVOEXCLUSIONENVIOESTRUCTURA().hashCode();
        }
        if (getMOTIVOLIQ() != null) {
            _hashCode += getMOTIVOLIQ().hashCode();
        }
        if (getNOCOBRADERECHOS() != null) {
            _hashCode += getNOCOBRADERECHOS().hashCode();
        }
        if (getNUEVAPOLIZAID() != null) {
            _hashCode += getNUEVAPOLIZAID().hashCode();
        }
        if (getNUMERO() != null) {
            _hashCode += getNUMERO().hashCode();
        }
        if (getNUMEROCADUCADO() != null) {
            _hashCode += getNUMEROCADUCADO().hashCode();
        }
        if (getNUMEROITEM() != null) {
            _hashCode += getNUMEROITEM().hashCode();
        }
        if (getORDEN() != null) {
            _hashCode += getORDEN().hashCode();
        }
        if (getPADREID() != null) {
            _hashCode += getPADREID().hashCode();
        }
        if (getPESADOS() != null) {
            _hashCode += getPESADOS().hashCode();
        }
        if (getPOLIZAFIRMA() != null) {
            _hashCode += getPOLIZAFIRMA().hashCode();
        }
        if (getPRIMAANUAL() != null) {
            _hashCode += getPRIMAANUAL().hashCode();
        }
        if (getPRIMAMINIMA() != null) {
            _hashCode += getPRIMAMINIMA().hashCode();
        }
        if (getPRIMATOTAL() != null) {
            _hashCode += getPRIMATOTAL().hashCode();
        }
        if (getRAMO() != null) {
            _hashCode += getRAMO().hashCode();
        }
        if (getRAMOReference() != null) {
            _hashCode += getRAMOReference().hashCode();
        }
        if (getREEMPLAZO() != null) {
            _hashCode += getREEMPLAZO().hashCode();
        }
        if (getRENOVACION() != null) {
            _hashCode += getRENOVACION().hashCode();
        }
        if (getRENOVACIONFIANZA() != null) {
            _hashCode += getRENOVACIONFIANZA().hashCode();
        }
        if (getSECUENCIALTRANSACCION() != null) {
            _hashCode += getSECUENCIALTRANSACCION().hashCode();
        }
        if (getSINAUTORIZACIONPAGOS() != null) {
            _hashCode += getSINAUTORIZACIONPAGOS().hashCode();
        }
        if (getTASAMINIMA() != null) {
            _hashCode += getTASAMINIMA().hashCode();
        }
        if (getTIENEDERECHOS() != null) {
            _hashCode += getTIENEDERECHOS().hashCode();
        }
        if (getTIPOACEPTADOID() != null) {
            _hashCode += getTIPOACEPTADOID().hashCode();
        }
        if (getTIPOCONTENEDOR() != null) {
            _hashCode += getTIPOCONTENEDOR().hashCode();
        }
        if (getTIPOSEGUROID() != null) {
            _hashCode += getTIPOSEGUROID().hashCode();
        }
        if (getTRATADOID() != null) {
            _hashCode += getTRATADOID().hashCode();
        }
        if (getTRAYECTOASEGURADOID() != null) {
            _hashCode += getTRAYECTOASEGURADOID().hashCode();
        }
        if (getUNIDADPRODUCCIONID() != null) {
            _hashCode += getUNIDADPRODUCCIONID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getUSUARIOVISADO() != null) {
            _hashCode += getUSUARIOVISADO().hashCode();
        }
        if (getVIGENCIA() != null) {
            _hashCode += getVIGENCIA().hashCode();
        }
        if (getVIGENCIADESDE() != null) {
            _hashCode += getVIGENCIADESDE().hashCode();
        }
        if (getVIGENCIAHASTA() != null) {
            _hashCode += getVIGENCIAHASTA().hashCode();
        }
        if (getVIGENCIAPOLIZAID() != null) {
            _hashCode += getVIGENCIAPOLIZAID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(POLIZA.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGENTEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANIOSUSCRIPCION2006");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ANIOSUSCRIPCION2006"));
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
        elemField.setFieldName("AUTORIZAPAGO100ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZAPAGO100ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CERTIFICADOSRESERVADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CERTIFICADOSRESERVADOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLASEPOLIZAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLASEPOLIZAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLIENTEReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTEReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfCLIENTEFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONFIGPRODUCTOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONFIGPRODUCTOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBITOFIRMADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEBITOFIRMADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSITOCIERREID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEPOSITOCIERREID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSITOCUENTABANCARIAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEPOSITOCUENTABANCARIAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEPOSITOESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEPOSITOESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
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
        elemField.setFieldName("ESANIODORADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESANIODORADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESCOASEGUROAUTOMATICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESCOASEGUROAUTOMATICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESDATOOTROSISTEMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESDATOOTROSISTEMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESFACTURACIONMENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESFACTURACIONMENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESPAGO100");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESPAGO100"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESPYMES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESPYMES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESRENOVACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESRENOVACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADOID"));
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
        elemField.setFieldName("FECHAANULACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAANULACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACADUCIDADCERTIFICADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACADUCIDADCERTIFICADOS"));
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
        elemField.setFieldName("FECHAVISADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAVISADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FUNCIONDENTROTIPOSEGURO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FUNCIONDENTROTIPOSEGURO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("IMPRIMEDIRECCIONPOLIZAHIJA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "IMPRIMEDIRECCIONPOLIZAHIJA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INGRESOCAJAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "INGRESOCAJAID"));
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
        elemField.setFieldName("MOTIVOANULACIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOANULACIONID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOEXCLUSIONENVIOESTRUCTURA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOEXCLUSIONENVIOESTRUCTURA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOLIQ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOLIQ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOCOBRADERECHOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOCOBRADERECHOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUEVAPOLIZAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUEVAPOLIZAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROCADUCADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROCADUCADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ORDEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PADREID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PADREID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PESADOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PESADOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLIZAFIRMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZAFIRMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMAANUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMAANUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMAMINIMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMAMINIMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMATOTAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMATOTAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RAMO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RAMOReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMOReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfRAMOFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REEMPLAZO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "REEMPLAZO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RENOVACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RENOVACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RENOVACIONFIANZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RENOVACIONFIANZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SECUENCIALTRANSACCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SECUENCIALTRANSACCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SINAUTORIZACIONPAGOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SINAUTORIZACIONPAGOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASAMINIMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TASAMINIMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIENEDERECHOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIENEDERECHOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOACEPTADOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOACEPTADOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOCONTENEDOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOCONTENEDOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOSEGUROID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOSEGUROID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRATADOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TRATADOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TRAYECTOASEGURADOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TRAYECTOASEGURADOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNIDADPRODUCCIONID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "UNIDADPRODUCCIONID"));
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
        elemField.setFieldName("USUARIOVISADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOVISADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VIGENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VIGENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VIGENCIAPOLIZAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VIGENCIAPOLIZAID"));
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
