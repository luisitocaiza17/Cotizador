/**
 * ENTIDAD.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ENTIDAD  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ACTIVIDADECONOMICAID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE[] AGENTE;

    private java.lang.String APELLIDO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO;

    private java.lang.String BLOQUEADA;

    private java.lang.String CAMBIOPASSWORD;

    private java.lang.String CIRCULARPROVIDENCIA;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE[] CLIENTE;

    private java.lang.String COMERCIAL;

    private java.lang.String CONOCETUCLIENTE;

    private java.lang.String CONSUEP;

    private java.lang.String DEPARTAMENTOID;

    private java.lang.String DIRECCIONFAM;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO[] DOCUMENTO;

    private java.math.BigDecimal EGRESOMENSUAL;

    private java.lang.String EMAILOPCIONAL;

    private java.lang.String EMAILPRINCIPAL;

    private java.lang.String EMAILRETENCION;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA EMPRESA;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEMPRESAFG7C7FF7 EMPRESAReference;

    private java.lang.String ENTEEMISORPROV;

    private java.lang.String ESQUEMAINTERFACEID;

    private java.lang.String ESTACALIFICADO;

    private java.lang.String ESTADO;

    private java.lang.String ESTADOCIVILID;

    private java.lang.String ESTADOMIGRATORIO;

    private java.lang.String ESTAIMPRESA;

    private java.lang.String FAMILIAR;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHACADPASAPORTE;

    private java.util.Calendar FECHACAMBIOPASSWORD;

    private java.util.Calendar FECHACIRCULARPROV;

    private java.util.Calendar FECHAEXPPASAPORTE;

    private java.util.Calendar FECHAINGRESOPAIS;

    private java.util.Calendar FECHAPROVIDENCIA;

    private java.lang.String FUENTE;

    private java.lang.String GRUPOECONOMICOID;

    private java.lang.String ID;

    private java.lang.String IDENTIFICACION;

    private java.math.BigDecimal INGRESOMENSUAL;

    private java.lang.String LOGIN;

    private java.lang.String MOTIVOCONSEP;

    private java.lang.String MOTIVOINTERPOL;

    private java.lang.String NACIONALIDADID;

    private java.lang.String NOMBRE;

    private java.lang.String NOMBRECOMERCIAL;

    private java.lang.String NOMBRECOMPLETO;

    private java.lang.String NOMBRECORTO;

    private java.lang.Short NUMEROINTENTOS;

    private java.lang.String NUMJUICIOPROVIDENCIA;

    private java.lang.String OBSERVACIONESPROV;

    private java.lang.String OFAC;

    private java.math.BigDecimal OTROSINGRESOS;

    private java.lang.String PARENTESCOID;

    private java.lang.String PASSWORD;

    private java.math.BigDecimal PATRIMONIO;

    private java.lang.String PEPS;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA PERSONA;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPERSONAFG7C7FF7 PERSONAReference;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA;

    private java.lang.String PREGUNTA;

    private java.lang.String PROVIDENCIA;

    private java.lang.String PUBLICA;

    private java.lang.String RESPUESTA;

    private java.lang.String RESP_NOMBRECOMPLETO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD[] ROLENTIDAD;

    private java.lang.String SERVIDOR_REP;

    private java.lang.String TELEFONOCELULAR1;

    private java.lang.String TELEFONOCELULAR2;

    private java.lang.String TELEFONOCELULAR3;

    private java.lang.String TELEFONOFAM;

    private java.lang.String TIPOEMPLEADOID;

    private java.lang.String TIPOEMPRESAID;

    private java.lang.String TIPOENTIDAD;

    private java.lang.String TIPOENTIDADID;

    private java.lang.String TIPOID;

    private java.lang.String TIPOJUICIOPROVIDENCIA;

    private java.lang.String TIPOOBJETO;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String WEBSITE;

    public ENTIDAD() {
    }

    public ENTIDAD(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ACTIVIDADECONOMICAID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE[] AGENTE,
           java.lang.String APELLIDO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO,
           java.lang.String BLOQUEADA,
           java.lang.String CAMBIOPASSWORD,
           java.lang.String CIRCULARPROVIDENCIA,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE[] CLIENTE,
           java.lang.String COMERCIAL,
           java.lang.String CONOCETUCLIENTE,
           java.lang.String CONSUEP,
           java.lang.String DEPARTAMENTOID,
           java.lang.String DIRECCIONFAM,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO[] DOCUMENTO,
           java.math.BigDecimal EGRESOMENSUAL,
           java.lang.String EMAILOPCIONAL,
           java.lang.String EMAILPRINCIPAL,
           java.lang.String EMAILRETENCION,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA EMPRESA,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEMPRESAFG7C7FF7 EMPRESAReference,
           java.lang.String ENTEEMISORPROV,
           java.lang.String ESQUEMAINTERFACEID,
           java.lang.String ESTACALIFICADO,
           java.lang.String ESTADO,
           java.lang.String ESTADOCIVILID,
           java.lang.String ESTADOMIGRATORIO,
           java.lang.String ESTAIMPRESA,
           java.lang.String FAMILIAR,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHACADPASAPORTE,
           java.util.Calendar FECHACAMBIOPASSWORD,
           java.util.Calendar FECHACIRCULARPROV,
           java.util.Calendar FECHAEXPPASAPORTE,
           java.util.Calendar FECHAINGRESOPAIS,
           java.util.Calendar FECHAPROVIDENCIA,
           java.lang.String FUENTE,
           java.lang.String GRUPOECONOMICOID,
           java.lang.String ID,
           java.lang.String IDENTIFICACION,
           java.math.BigDecimal INGRESOMENSUAL,
           java.lang.String LOGIN,
           java.lang.String MOTIVOCONSEP,
           java.lang.String MOTIVOINTERPOL,
           java.lang.String NACIONALIDADID,
           java.lang.String NOMBRE,
           java.lang.String NOMBRECOMERCIAL,
           java.lang.String NOMBRECOMPLETO,
           java.lang.String NOMBRECORTO,
           java.lang.Short NUMEROINTENTOS,
           java.lang.String NUMJUICIOPROVIDENCIA,
           java.lang.String OBSERVACIONESPROV,
           java.lang.String OFAC,
           java.math.BigDecimal OTROSINGRESOS,
           java.lang.String PARENTESCOID,
           java.lang.String PASSWORD,
           java.math.BigDecimal PATRIMONIO,
           java.lang.String PEPS,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA PERSONA,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPERSONAFG7C7FF7 PERSONAReference,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA,
           java.lang.String PREGUNTA,
           java.lang.String PROVIDENCIA,
           java.lang.String PUBLICA,
           java.lang.String RESPUESTA,
           java.lang.String RESP_NOMBRECOMPLETO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD[] ROLENTIDAD,
           java.lang.String SERVIDOR_REP,
           java.lang.String TELEFONOCELULAR1,
           java.lang.String TELEFONOCELULAR2,
           java.lang.String TELEFONOCELULAR3,
           java.lang.String TELEFONOFAM,
           java.lang.String TIPOEMPLEADOID,
           java.lang.String TIPOEMPRESAID,
           java.lang.String TIPOENTIDAD,
           java.lang.String TIPOENTIDADID,
           java.lang.String TIPOID,
           java.lang.String TIPOJUICIOPROVIDENCIA,
           java.lang.String TIPOOBJETO,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String WEBSITE) {
        super(
            id,
            ref,
            entityKey);
        this.ACTIVIDADECONOMICAID = ACTIVIDADECONOMICAID;
        this.AGENTE = AGENTE;
        this.APELLIDO = APELLIDO;
        this.BENEFICIARIO = BENEFICIARIO;
        this.BLOQUEADA = BLOQUEADA;
        this.CAMBIOPASSWORD = CAMBIOPASSWORD;
        this.CIRCULARPROVIDENCIA = CIRCULARPROVIDENCIA;
        this.CLIENTE = CLIENTE;
        this.COMERCIAL = COMERCIAL;
        this.CONOCETUCLIENTE = CONOCETUCLIENTE;
        this.CONSUEP = CONSUEP;
        this.DEPARTAMENTOID = DEPARTAMENTOID;
        this.DIRECCIONFAM = DIRECCIONFAM;
        this.DOCUMENTO = DOCUMENTO;
        this.EGRESOMENSUAL = EGRESOMENSUAL;
        this.EMAILOPCIONAL = EMAILOPCIONAL;
        this.EMAILPRINCIPAL = EMAILPRINCIPAL;
        this.EMAILRETENCION = EMAILRETENCION;
        this.EMPRESA = EMPRESA;
        this.EMPRESAReference = EMPRESAReference;
        this.ENTEEMISORPROV = ENTEEMISORPROV;
        this.ESQUEMAINTERFACEID = ESQUEMAINTERFACEID;
        this.ESTACALIFICADO = ESTACALIFICADO;
        this.ESTADO = ESTADO;
        this.ESTADOCIVILID = ESTADOCIVILID;
        this.ESTADOMIGRATORIO = ESTADOMIGRATORIO;
        this.ESTAIMPRESA = ESTAIMPRESA;
        this.FAMILIAR = FAMILIAR;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHACADPASAPORTE = FECHACADPASAPORTE;
        this.FECHACAMBIOPASSWORD = FECHACAMBIOPASSWORD;
        this.FECHACIRCULARPROV = FECHACIRCULARPROV;
        this.FECHAEXPPASAPORTE = FECHAEXPPASAPORTE;
        this.FECHAINGRESOPAIS = FECHAINGRESOPAIS;
        this.FECHAPROVIDENCIA = FECHAPROVIDENCIA;
        this.FUENTE = FUENTE;
        this.GRUPOECONOMICOID = GRUPOECONOMICOID;
        this.ID = ID;
        this.IDENTIFICACION = IDENTIFICACION;
        this.INGRESOMENSUAL = INGRESOMENSUAL;
        this.LOGIN = LOGIN;
        this.MOTIVOCONSEP = MOTIVOCONSEP;
        this.MOTIVOINTERPOL = MOTIVOINTERPOL;
        this.NACIONALIDADID = NACIONALIDADID;
        this.NOMBRE = NOMBRE;
        this.NOMBRECOMERCIAL = NOMBRECOMERCIAL;
        this.NOMBRECOMPLETO = NOMBRECOMPLETO;
        this.NOMBRECORTO = NOMBRECORTO;
        this.NUMEROINTENTOS = NUMEROINTENTOS;
        this.NUMJUICIOPROVIDENCIA = NUMJUICIOPROVIDENCIA;
        this.OBSERVACIONESPROV = OBSERVACIONESPROV;
        this.OFAC = OFAC;
        this.OTROSINGRESOS = OTROSINGRESOS;
        this.PARENTESCOID = PARENTESCOID;
        this.PASSWORD = PASSWORD;
        this.PATRIMONIO = PATRIMONIO;
        this.PEPS = PEPS;
        this.PERSONA = PERSONA;
        this.PERSONAReference = PERSONAReference;
        this.POLIZA = POLIZA;
        this.PREGUNTA = PREGUNTA;
        this.PROVIDENCIA = PROVIDENCIA;
        this.PUBLICA = PUBLICA;
        this.RESPUESTA = RESPUESTA;
        this.RESP_NOMBRECOMPLETO = RESP_NOMBRECOMPLETO;
        this.ROLENTIDAD = ROLENTIDAD;
        this.SERVIDOR_REP = SERVIDOR_REP;
        this.TELEFONOCELULAR1 = TELEFONOCELULAR1;
        this.TELEFONOCELULAR2 = TELEFONOCELULAR2;
        this.TELEFONOCELULAR3 = TELEFONOCELULAR3;
        this.TELEFONOFAM = TELEFONOFAM;
        this.TIPOEMPLEADOID = TIPOEMPLEADOID;
        this.TIPOEMPRESAID = TIPOEMPRESAID;
        this.TIPOENTIDAD = TIPOENTIDAD;
        this.TIPOENTIDADID = TIPOENTIDADID;
        this.TIPOID = TIPOID;
        this.TIPOJUICIOPROVIDENCIA = TIPOJUICIOPROVIDENCIA;
        this.TIPOOBJETO = TIPOOBJETO;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.WEBSITE = WEBSITE;
    }


    /**
     * Gets the ACTIVIDADECONOMICAID value for this ENTIDAD.
     * 
     * @return ACTIVIDADECONOMICAID
     */
    public java.lang.String getACTIVIDADECONOMICAID() {
        return ACTIVIDADECONOMICAID;
    }


    /**
     * Sets the ACTIVIDADECONOMICAID value for this ENTIDAD.
     * 
     * @param ACTIVIDADECONOMICAID
     */
    public void setACTIVIDADECONOMICAID(java.lang.String ACTIVIDADECONOMICAID) {
        this.ACTIVIDADECONOMICAID = ACTIVIDADECONOMICAID;
    }


    /**
     * Gets the AGENTE value for this ENTIDAD.
     * 
     * @return AGENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE[] getAGENTE() {
        return AGENTE;
    }


    /**
     * Sets the AGENTE value for this ENTIDAD.
     * 
     * @param AGENTE
     */
    public void setAGENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE[] AGENTE) {
        this.AGENTE = AGENTE;
    }


    /**
     * Gets the APELLIDO value for this ENTIDAD.
     * 
     * @return APELLIDO
     */
    public java.lang.String getAPELLIDO() {
        return APELLIDO;
    }


    /**
     * Sets the APELLIDO value for this ENTIDAD.
     * 
     * @param APELLIDO
     */
    public void setAPELLIDO(java.lang.String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }


    /**
     * Gets the BENEFICIARIO value for this ENTIDAD.
     * 
     * @return BENEFICIARIO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] getBENEFICIARIO() {
        return BENEFICIARIO;
    }


    /**
     * Sets the BENEFICIARIO value for this ENTIDAD.
     * 
     * @param BENEFICIARIO
     */
    public void setBENEFICIARIO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO) {
        this.BENEFICIARIO = BENEFICIARIO;
    }


    /**
     * Gets the BLOQUEADA value for this ENTIDAD.
     * 
     * @return BLOQUEADA
     */
    public java.lang.String getBLOQUEADA() {
        return BLOQUEADA;
    }


    /**
     * Sets the BLOQUEADA value for this ENTIDAD.
     * 
     * @param BLOQUEADA
     */
    public void setBLOQUEADA(java.lang.String BLOQUEADA) {
        this.BLOQUEADA = BLOQUEADA;
    }


    /**
     * Gets the CAMBIOPASSWORD value for this ENTIDAD.
     * 
     * @return CAMBIOPASSWORD
     */
    public java.lang.String getCAMBIOPASSWORD() {
        return CAMBIOPASSWORD;
    }


    /**
     * Sets the CAMBIOPASSWORD value for this ENTIDAD.
     * 
     * @param CAMBIOPASSWORD
     */
    public void setCAMBIOPASSWORD(java.lang.String CAMBIOPASSWORD) {
        this.CAMBIOPASSWORD = CAMBIOPASSWORD;
    }


    /**
     * Gets the CIRCULARPROVIDENCIA value for this ENTIDAD.
     * 
     * @return CIRCULARPROVIDENCIA
     */
    public java.lang.String getCIRCULARPROVIDENCIA() {
        return CIRCULARPROVIDENCIA;
    }


    /**
     * Sets the CIRCULARPROVIDENCIA value for this ENTIDAD.
     * 
     * @param CIRCULARPROVIDENCIA
     */
    public void setCIRCULARPROVIDENCIA(java.lang.String CIRCULARPROVIDENCIA) {
        this.CIRCULARPROVIDENCIA = CIRCULARPROVIDENCIA;
    }


    /**
     * Gets the CLIENTE value for this ENTIDAD.
     * 
     * @return CLIENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE[] getCLIENTE() {
        return CLIENTE;
    }


    /**
     * Sets the CLIENTE value for this ENTIDAD.
     * 
     * @param CLIENTE
     */
    public void setCLIENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE[] CLIENTE) {
        this.CLIENTE = CLIENTE;
    }


    /**
     * Gets the COMERCIAL value for this ENTIDAD.
     * 
     * @return COMERCIAL
     */
    public java.lang.String getCOMERCIAL() {
        return COMERCIAL;
    }


    /**
     * Sets the COMERCIAL value for this ENTIDAD.
     * 
     * @param COMERCIAL
     */
    public void setCOMERCIAL(java.lang.String COMERCIAL) {
        this.COMERCIAL = COMERCIAL;
    }


    /**
     * Gets the CONOCETUCLIENTE value for this ENTIDAD.
     * 
     * @return CONOCETUCLIENTE
     */
    public java.lang.String getCONOCETUCLIENTE() {
        return CONOCETUCLIENTE;
    }


    /**
     * Sets the CONOCETUCLIENTE value for this ENTIDAD.
     * 
     * @param CONOCETUCLIENTE
     */
    public void setCONOCETUCLIENTE(java.lang.String CONOCETUCLIENTE) {
        this.CONOCETUCLIENTE = CONOCETUCLIENTE;
    }


    /**
     * Gets the CONSUEP value for this ENTIDAD.
     * 
     * @return CONSUEP
     */
    public java.lang.String getCONSUEP() {
        return CONSUEP;
    }


    /**
     * Sets the CONSUEP value for this ENTIDAD.
     * 
     * @param CONSUEP
     */
    public void setCONSUEP(java.lang.String CONSUEP) {
        this.CONSUEP = CONSUEP;
    }


    /**
     * Gets the DEPARTAMENTOID value for this ENTIDAD.
     * 
     * @return DEPARTAMENTOID
     */
    public java.lang.String getDEPARTAMENTOID() {
        return DEPARTAMENTOID;
    }


    /**
     * Sets the DEPARTAMENTOID value for this ENTIDAD.
     * 
     * @param DEPARTAMENTOID
     */
    public void setDEPARTAMENTOID(java.lang.String DEPARTAMENTOID) {
        this.DEPARTAMENTOID = DEPARTAMENTOID;
    }


    /**
     * Gets the DIRECCIONFAM value for this ENTIDAD.
     * 
     * @return DIRECCIONFAM
     */
    public java.lang.String getDIRECCIONFAM() {
        return DIRECCIONFAM;
    }


    /**
     * Sets the DIRECCIONFAM value for this ENTIDAD.
     * 
     * @param DIRECCIONFAM
     */
    public void setDIRECCIONFAM(java.lang.String DIRECCIONFAM) {
        this.DIRECCIONFAM = DIRECCIONFAM;
    }


    /**
     * Gets the DOCUMENTO value for this ENTIDAD.
     * 
     * @return DOCUMENTO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO[] getDOCUMENTO() {
        return DOCUMENTO;
    }


    /**
     * Sets the DOCUMENTO value for this ENTIDAD.
     * 
     * @param DOCUMENTO
     */
    public void setDOCUMENTO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO[] DOCUMENTO) {
        this.DOCUMENTO = DOCUMENTO;
    }


    /**
     * Gets the EGRESOMENSUAL value for this ENTIDAD.
     * 
     * @return EGRESOMENSUAL
     */
    public java.math.BigDecimal getEGRESOMENSUAL() {
        return EGRESOMENSUAL;
    }


    /**
     * Sets the EGRESOMENSUAL value for this ENTIDAD.
     * 
     * @param EGRESOMENSUAL
     */
    public void setEGRESOMENSUAL(java.math.BigDecimal EGRESOMENSUAL) {
        this.EGRESOMENSUAL = EGRESOMENSUAL;
    }


    /**
     * Gets the EMAILOPCIONAL value for this ENTIDAD.
     * 
     * @return EMAILOPCIONAL
     */
    public java.lang.String getEMAILOPCIONAL() {
        return EMAILOPCIONAL;
    }


    /**
     * Sets the EMAILOPCIONAL value for this ENTIDAD.
     * 
     * @param EMAILOPCIONAL
     */
    public void setEMAILOPCIONAL(java.lang.String EMAILOPCIONAL) {
        this.EMAILOPCIONAL = EMAILOPCIONAL;
    }


    /**
     * Gets the EMAILPRINCIPAL value for this ENTIDAD.
     * 
     * @return EMAILPRINCIPAL
     */
    public java.lang.String getEMAILPRINCIPAL() {
        return EMAILPRINCIPAL;
    }


    /**
     * Sets the EMAILPRINCIPAL value for this ENTIDAD.
     * 
     * @param EMAILPRINCIPAL
     */
    public void setEMAILPRINCIPAL(java.lang.String EMAILPRINCIPAL) {
        this.EMAILPRINCIPAL = EMAILPRINCIPAL;
    }


    /**
     * Gets the EMAILRETENCION value for this ENTIDAD.
     * 
     * @return EMAILRETENCION
     */
    public java.lang.String getEMAILRETENCION() {
        return EMAILRETENCION;
    }


    /**
     * Sets the EMAILRETENCION value for this ENTIDAD.
     * 
     * @param EMAILRETENCION
     */
    public void setEMAILRETENCION(java.lang.String EMAILRETENCION) {
        this.EMAILRETENCION = EMAILRETENCION;
    }


    /**
     * Gets the EMPRESA value for this ENTIDAD.
     * 
     * @return EMPRESA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA getEMPRESA() {
        return EMPRESA;
    }


    /**
     * Sets the EMPRESA value for this ENTIDAD.
     * 
     * @param EMPRESA
     */
    public void setEMPRESA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA EMPRESA) {
        this.EMPRESA = EMPRESA;
    }


    /**
     * Gets the EMPRESAReference value for this ENTIDAD.
     * 
     * @return EMPRESAReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEMPRESAFG7C7FF7 getEMPRESAReference() {
        return EMPRESAReference;
    }


    /**
     * Sets the EMPRESAReference value for this ENTIDAD.
     * 
     * @param EMPRESAReference
     */
    public void setEMPRESAReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEMPRESAFG7C7FF7 EMPRESAReference) {
        this.EMPRESAReference = EMPRESAReference;
    }


    /**
     * Gets the ENTEEMISORPROV value for this ENTIDAD.
     * 
     * @return ENTEEMISORPROV
     */
    public java.lang.String getENTEEMISORPROV() {
        return ENTEEMISORPROV;
    }


    /**
     * Sets the ENTEEMISORPROV value for this ENTIDAD.
     * 
     * @param ENTEEMISORPROV
     */
    public void setENTEEMISORPROV(java.lang.String ENTEEMISORPROV) {
        this.ENTEEMISORPROV = ENTEEMISORPROV;
    }


    /**
     * Gets the ESQUEMAINTERFACEID value for this ENTIDAD.
     * 
     * @return ESQUEMAINTERFACEID
     */
    public java.lang.String getESQUEMAINTERFACEID() {
        return ESQUEMAINTERFACEID;
    }


    /**
     * Sets the ESQUEMAINTERFACEID value for this ENTIDAD.
     * 
     * @param ESQUEMAINTERFACEID
     */
    public void setESQUEMAINTERFACEID(java.lang.String ESQUEMAINTERFACEID) {
        this.ESQUEMAINTERFACEID = ESQUEMAINTERFACEID;
    }


    /**
     * Gets the ESTACALIFICADO value for this ENTIDAD.
     * 
     * @return ESTACALIFICADO
     */
    public java.lang.String getESTACALIFICADO() {
        return ESTACALIFICADO;
    }


    /**
     * Sets the ESTACALIFICADO value for this ENTIDAD.
     * 
     * @param ESTACALIFICADO
     */
    public void setESTACALIFICADO(java.lang.String ESTACALIFICADO) {
        this.ESTACALIFICADO = ESTACALIFICADO;
    }


    /**
     * Gets the ESTADO value for this ENTIDAD.
     * 
     * @return ESTADO
     */
    public java.lang.String getESTADO() {
        return ESTADO;
    }


    /**
     * Sets the ESTADO value for this ENTIDAD.
     * 
     * @param ESTADO
     */
    public void setESTADO(java.lang.String ESTADO) {
        this.ESTADO = ESTADO;
    }


    /**
     * Gets the ESTADOCIVILID value for this ENTIDAD.
     * 
     * @return ESTADOCIVILID
     */
    public java.lang.String getESTADOCIVILID() {
        return ESTADOCIVILID;
    }


    /**
     * Sets the ESTADOCIVILID value for this ENTIDAD.
     * 
     * @param ESTADOCIVILID
     */
    public void setESTADOCIVILID(java.lang.String ESTADOCIVILID) {
        this.ESTADOCIVILID = ESTADOCIVILID;
    }


    /**
     * Gets the ESTADOMIGRATORIO value for this ENTIDAD.
     * 
     * @return ESTADOMIGRATORIO
     */
    public java.lang.String getESTADOMIGRATORIO() {
        return ESTADOMIGRATORIO;
    }


    /**
     * Sets the ESTADOMIGRATORIO value for this ENTIDAD.
     * 
     * @param ESTADOMIGRATORIO
     */
    public void setESTADOMIGRATORIO(java.lang.String ESTADOMIGRATORIO) {
        this.ESTADOMIGRATORIO = ESTADOMIGRATORIO;
    }


    /**
     * Gets the ESTAIMPRESA value for this ENTIDAD.
     * 
     * @return ESTAIMPRESA
     */
    public java.lang.String getESTAIMPRESA() {
        return ESTAIMPRESA;
    }


    /**
     * Sets the ESTAIMPRESA value for this ENTIDAD.
     * 
     * @param ESTAIMPRESA
     */
    public void setESTAIMPRESA(java.lang.String ESTAIMPRESA) {
        this.ESTAIMPRESA = ESTAIMPRESA;
    }


    /**
     * Gets the FAMILIAR value for this ENTIDAD.
     * 
     * @return FAMILIAR
     */
    public java.lang.String getFAMILIAR() {
        return FAMILIAR;
    }


    /**
     * Sets the FAMILIAR value for this ENTIDAD.
     * 
     * @param FAMILIAR
     */
    public void setFAMILIAR(java.lang.String FAMILIAR) {
        this.FAMILIAR = FAMILIAR;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ENTIDAD.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ENTIDAD.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHACADPASAPORTE value for this ENTIDAD.
     * 
     * @return FECHACADPASAPORTE
     */
    public java.util.Calendar getFECHACADPASAPORTE() {
        return FECHACADPASAPORTE;
    }


    /**
     * Sets the FECHACADPASAPORTE value for this ENTIDAD.
     * 
     * @param FECHACADPASAPORTE
     */
    public void setFECHACADPASAPORTE(java.util.Calendar FECHACADPASAPORTE) {
        this.FECHACADPASAPORTE = FECHACADPASAPORTE;
    }


    /**
     * Gets the FECHACAMBIOPASSWORD value for this ENTIDAD.
     * 
     * @return FECHACAMBIOPASSWORD
     */
    public java.util.Calendar getFECHACAMBIOPASSWORD() {
        return FECHACAMBIOPASSWORD;
    }


    /**
     * Sets the FECHACAMBIOPASSWORD value for this ENTIDAD.
     * 
     * @param FECHACAMBIOPASSWORD
     */
    public void setFECHACAMBIOPASSWORD(java.util.Calendar FECHACAMBIOPASSWORD) {
        this.FECHACAMBIOPASSWORD = FECHACAMBIOPASSWORD;
    }


    /**
     * Gets the FECHACIRCULARPROV value for this ENTIDAD.
     * 
     * @return FECHACIRCULARPROV
     */
    public java.util.Calendar getFECHACIRCULARPROV() {
        return FECHACIRCULARPROV;
    }


    /**
     * Sets the FECHACIRCULARPROV value for this ENTIDAD.
     * 
     * @param FECHACIRCULARPROV
     */
    public void setFECHACIRCULARPROV(java.util.Calendar FECHACIRCULARPROV) {
        this.FECHACIRCULARPROV = FECHACIRCULARPROV;
    }


    /**
     * Gets the FECHAEXPPASAPORTE value for this ENTIDAD.
     * 
     * @return FECHAEXPPASAPORTE
     */
    public java.util.Calendar getFECHAEXPPASAPORTE() {
        return FECHAEXPPASAPORTE;
    }


    /**
     * Sets the FECHAEXPPASAPORTE value for this ENTIDAD.
     * 
     * @param FECHAEXPPASAPORTE
     */
    public void setFECHAEXPPASAPORTE(java.util.Calendar FECHAEXPPASAPORTE) {
        this.FECHAEXPPASAPORTE = FECHAEXPPASAPORTE;
    }


    /**
     * Gets the FECHAINGRESOPAIS value for this ENTIDAD.
     * 
     * @return FECHAINGRESOPAIS
     */
    public java.util.Calendar getFECHAINGRESOPAIS() {
        return FECHAINGRESOPAIS;
    }


    /**
     * Sets the FECHAINGRESOPAIS value for this ENTIDAD.
     * 
     * @param FECHAINGRESOPAIS
     */
    public void setFECHAINGRESOPAIS(java.util.Calendar FECHAINGRESOPAIS) {
        this.FECHAINGRESOPAIS = FECHAINGRESOPAIS;
    }


    /**
     * Gets the FECHAPROVIDENCIA value for this ENTIDAD.
     * 
     * @return FECHAPROVIDENCIA
     */
    public java.util.Calendar getFECHAPROVIDENCIA() {
        return FECHAPROVIDENCIA;
    }


    /**
     * Sets the FECHAPROVIDENCIA value for this ENTIDAD.
     * 
     * @param FECHAPROVIDENCIA
     */
    public void setFECHAPROVIDENCIA(java.util.Calendar FECHAPROVIDENCIA) {
        this.FECHAPROVIDENCIA = FECHAPROVIDENCIA;
    }


    /**
     * Gets the FUENTE value for this ENTIDAD.
     * 
     * @return FUENTE
     */
    public java.lang.String getFUENTE() {
        return FUENTE;
    }


    /**
     * Sets the FUENTE value for this ENTIDAD.
     * 
     * @param FUENTE
     */
    public void setFUENTE(java.lang.String FUENTE) {
        this.FUENTE = FUENTE;
    }


    /**
     * Gets the GRUPOECONOMICOID value for this ENTIDAD.
     * 
     * @return GRUPOECONOMICOID
     */
    public java.lang.String getGRUPOECONOMICOID() {
        return GRUPOECONOMICOID;
    }


    /**
     * Sets the GRUPOECONOMICOID value for this ENTIDAD.
     * 
     * @param GRUPOECONOMICOID
     */
    public void setGRUPOECONOMICOID(java.lang.String GRUPOECONOMICOID) {
        this.GRUPOECONOMICOID = GRUPOECONOMICOID;
    }


    /**
     * Gets the ID value for this ENTIDAD.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ENTIDAD.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the IDENTIFICACION value for this ENTIDAD.
     * 
     * @return IDENTIFICACION
     */
    public java.lang.String getIDENTIFICACION() {
        return IDENTIFICACION;
    }


    /**
     * Sets the IDENTIFICACION value for this ENTIDAD.
     * 
     * @param IDENTIFICACION
     */
    public void setIDENTIFICACION(java.lang.String IDENTIFICACION) {
        this.IDENTIFICACION = IDENTIFICACION;
    }


    /**
     * Gets the INGRESOMENSUAL value for this ENTIDAD.
     * 
     * @return INGRESOMENSUAL
     */
    public java.math.BigDecimal getINGRESOMENSUAL() {
        return INGRESOMENSUAL;
    }


    /**
     * Sets the INGRESOMENSUAL value for this ENTIDAD.
     * 
     * @param INGRESOMENSUAL
     */
    public void setINGRESOMENSUAL(java.math.BigDecimal INGRESOMENSUAL) {
        this.INGRESOMENSUAL = INGRESOMENSUAL;
    }


    /**
     * Gets the LOGIN value for this ENTIDAD.
     * 
     * @return LOGIN
     */
    public java.lang.String getLOGIN() {
        return LOGIN;
    }


    /**
     * Sets the LOGIN value for this ENTIDAD.
     * 
     * @param LOGIN
     */
    public void setLOGIN(java.lang.String LOGIN) {
        this.LOGIN = LOGIN;
    }


    /**
     * Gets the MOTIVOCONSEP value for this ENTIDAD.
     * 
     * @return MOTIVOCONSEP
     */
    public java.lang.String getMOTIVOCONSEP() {
        return MOTIVOCONSEP;
    }


    /**
     * Sets the MOTIVOCONSEP value for this ENTIDAD.
     * 
     * @param MOTIVOCONSEP
     */
    public void setMOTIVOCONSEP(java.lang.String MOTIVOCONSEP) {
        this.MOTIVOCONSEP = MOTIVOCONSEP;
    }


    /**
     * Gets the MOTIVOINTERPOL value for this ENTIDAD.
     * 
     * @return MOTIVOINTERPOL
     */
    public java.lang.String getMOTIVOINTERPOL() {
        return MOTIVOINTERPOL;
    }


    /**
     * Sets the MOTIVOINTERPOL value for this ENTIDAD.
     * 
     * @param MOTIVOINTERPOL
     */
    public void setMOTIVOINTERPOL(java.lang.String MOTIVOINTERPOL) {
        this.MOTIVOINTERPOL = MOTIVOINTERPOL;
    }


    /**
     * Gets the NACIONALIDADID value for this ENTIDAD.
     * 
     * @return NACIONALIDADID
     */
    public java.lang.String getNACIONALIDADID() {
        return NACIONALIDADID;
    }


    /**
     * Sets the NACIONALIDADID value for this ENTIDAD.
     * 
     * @param NACIONALIDADID
     */
    public void setNACIONALIDADID(java.lang.String NACIONALIDADID) {
        this.NACIONALIDADID = NACIONALIDADID;
    }


    /**
     * Gets the NOMBRE value for this ENTIDAD.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this ENTIDAD.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the NOMBRECOMERCIAL value for this ENTIDAD.
     * 
     * @return NOMBRECOMERCIAL
     */
    public java.lang.String getNOMBRECOMERCIAL() {
        return NOMBRECOMERCIAL;
    }


    /**
     * Sets the NOMBRECOMERCIAL value for this ENTIDAD.
     * 
     * @param NOMBRECOMERCIAL
     */
    public void setNOMBRECOMERCIAL(java.lang.String NOMBRECOMERCIAL) {
        this.NOMBRECOMERCIAL = NOMBRECOMERCIAL;
    }


    /**
     * Gets the NOMBRECOMPLETO value for this ENTIDAD.
     * 
     * @return NOMBRECOMPLETO
     */
    public java.lang.String getNOMBRECOMPLETO() {
        return NOMBRECOMPLETO;
    }


    /**
     * Sets the NOMBRECOMPLETO value for this ENTIDAD.
     * 
     * @param NOMBRECOMPLETO
     */
    public void setNOMBRECOMPLETO(java.lang.String NOMBRECOMPLETO) {
        this.NOMBRECOMPLETO = NOMBRECOMPLETO;
    }


    /**
     * Gets the NOMBRECORTO value for this ENTIDAD.
     * 
     * @return NOMBRECORTO
     */
    public java.lang.String getNOMBRECORTO() {
        return NOMBRECORTO;
    }


    /**
     * Sets the NOMBRECORTO value for this ENTIDAD.
     * 
     * @param NOMBRECORTO
     */
    public void setNOMBRECORTO(java.lang.String NOMBRECORTO) {
        this.NOMBRECORTO = NOMBRECORTO;
    }


    /**
     * Gets the NUMEROINTENTOS value for this ENTIDAD.
     * 
     * @return NUMEROINTENTOS
     */
    public java.lang.Short getNUMEROINTENTOS() {
        return NUMEROINTENTOS;
    }


    /**
     * Sets the NUMEROINTENTOS value for this ENTIDAD.
     * 
     * @param NUMEROINTENTOS
     */
    public void setNUMEROINTENTOS(java.lang.Short NUMEROINTENTOS) {
        this.NUMEROINTENTOS = NUMEROINTENTOS;
    }


    /**
     * Gets the NUMJUICIOPROVIDENCIA value for this ENTIDAD.
     * 
     * @return NUMJUICIOPROVIDENCIA
     */
    public java.lang.String getNUMJUICIOPROVIDENCIA() {
        return NUMJUICIOPROVIDENCIA;
    }


    /**
     * Sets the NUMJUICIOPROVIDENCIA value for this ENTIDAD.
     * 
     * @param NUMJUICIOPROVIDENCIA
     */
    public void setNUMJUICIOPROVIDENCIA(java.lang.String NUMJUICIOPROVIDENCIA) {
        this.NUMJUICIOPROVIDENCIA = NUMJUICIOPROVIDENCIA;
    }


    /**
     * Gets the OBSERVACIONESPROV value for this ENTIDAD.
     * 
     * @return OBSERVACIONESPROV
     */
    public java.lang.String getOBSERVACIONESPROV() {
        return OBSERVACIONESPROV;
    }


    /**
     * Sets the OBSERVACIONESPROV value for this ENTIDAD.
     * 
     * @param OBSERVACIONESPROV
     */
    public void setOBSERVACIONESPROV(java.lang.String OBSERVACIONESPROV) {
        this.OBSERVACIONESPROV = OBSERVACIONESPROV;
    }


    /**
     * Gets the OFAC value for this ENTIDAD.
     * 
     * @return OFAC
     */
    public java.lang.String getOFAC() {
        return OFAC;
    }


    /**
     * Sets the OFAC value for this ENTIDAD.
     * 
     * @param OFAC
     */
    public void setOFAC(java.lang.String OFAC) {
        this.OFAC = OFAC;
    }


    /**
     * Gets the OTROSINGRESOS value for this ENTIDAD.
     * 
     * @return OTROSINGRESOS
     */
    public java.math.BigDecimal getOTROSINGRESOS() {
        return OTROSINGRESOS;
    }


    /**
     * Sets the OTROSINGRESOS value for this ENTIDAD.
     * 
     * @param OTROSINGRESOS
     */
    public void setOTROSINGRESOS(java.math.BigDecimal OTROSINGRESOS) {
        this.OTROSINGRESOS = OTROSINGRESOS;
    }


    /**
     * Gets the PARENTESCOID value for this ENTIDAD.
     * 
     * @return PARENTESCOID
     */
    public java.lang.String getPARENTESCOID() {
        return PARENTESCOID;
    }


    /**
     * Sets the PARENTESCOID value for this ENTIDAD.
     * 
     * @param PARENTESCOID
     */
    public void setPARENTESCOID(java.lang.String PARENTESCOID) {
        this.PARENTESCOID = PARENTESCOID;
    }


    /**
     * Gets the PASSWORD value for this ENTIDAD.
     * 
     * @return PASSWORD
     */
    public java.lang.String getPASSWORD() {
        return PASSWORD;
    }


    /**
     * Sets the PASSWORD value for this ENTIDAD.
     * 
     * @param PASSWORD
     */
    public void setPASSWORD(java.lang.String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }


    /**
     * Gets the PATRIMONIO value for this ENTIDAD.
     * 
     * @return PATRIMONIO
     */
    public java.math.BigDecimal getPATRIMONIO() {
        return PATRIMONIO;
    }


    /**
     * Sets the PATRIMONIO value for this ENTIDAD.
     * 
     * @param PATRIMONIO
     */
    public void setPATRIMONIO(java.math.BigDecimal PATRIMONIO) {
        this.PATRIMONIO = PATRIMONIO;
    }


    /**
     * Gets the PEPS value for this ENTIDAD.
     * 
     * @return PEPS
     */
    public java.lang.String getPEPS() {
        return PEPS;
    }


    /**
     * Sets the PEPS value for this ENTIDAD.
     * 
     * @param PEPS
     */
    public void setPEPS(java.lang.String PEPS) {
        this.PEPS = PEPS;
    }


    /**
     * Gets the PERSONA value for this ENTIDAD.
     * 
     * @return PERSONA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA getPERSONA() {
        return PERSONA;
    }


    /**
     * Sets the PERSONA value for this ENTIDAD.
     * 
     * @param PERSONA
     */
    public void setPERSONA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA PERSONA) {
        this.PERSONA = PERSONA;
    }


    /**
     * Gets the PERSONAReference value for this ENTIDAD.
     * 
     * @return PERSONAReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPERSONAFG7C7FF7 getPERSONAReference() {
        return PERSONAReference;
    }


    /**
     * Sets the PERSONAReference value for this ENTIDAD.
     * 
     * @param PERSONAReference
     */
    public void setPERSONAReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPERSONAFG7C7FF7 PERSONAReference) {
        this.PERSONAReference = PERSONAReference;
    }


    /**
     * Gets the POLIZA value for this ENTIDAD.
     * 
     * @return POLIZA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] getPOLIZA() {
        return POLIZA;
    }


    /**
     * Sets the POLIZA value for this ENTIDAD.
     * 
     * @param POLIZA
     */
    public void setPOLIZA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] POLIZA) {
        this.POLIZA = POLIZA;
    }


    /**
     * Gets the PREGUNTA value for this ENTIDAD.
     * 
     * @return PREGUNTA
     */
    public java.lang.String getPREGUNTA() {
        return PREGUNTA;
    }


    /**
     * Sets the PREGUNTA value for this ENTIDAD.
     * 
     * @param PREGUNTA
     */
    public void setPREGUNTA(java.lang.String PREGUNTA) {
        this.PREGUNTA = PREGUNTA;
    }


    /**
     * Gets the PROVIDENCIA value for this ENTIDAD.
     * 
     * @return PROVIDENCIA
     */
    public java.lang.String getPROVIDENCIA() {
        return PROVIDENCIA;
    }


    /**
     * Sets the PROVIDENCIA value for this ENTIDAD.
     * 
     * @param PROVIDENCIA
     */
    public void setPROVIDENCIA(java.lang.String PROVIDENCIA) {
        this.PROVIDENCIA = PROVIDENCIA;
    }


    /**
     * Gets the PUBLICA value for this ENTIDAD.
     * 
     * @return PUBLICA
     */
    public java.lang.String getPUBLICA() {
        return PUBLICA;
    }


    /**
     * Sets the PUBLICA value for this ENTIDAD.
     * 
     * @param PUBLICA
     */
    public void setPUBLICA(java.lang.String PUBLICA) {
        this.PUBLICA = PUBLICA;
    }


    /**
     * Gets the RESPUESTA value for this ENTIDAD.
     * 
     * @return RESPUESTA
     */
    public java.lang.String getRESPUESTA() {
        return RESPUESTA;
    }


    /**
     * Sets the RESPUESTA value for this ENTIDAD.
     * 
     * @param RESPUESTA
     */
    public void setRESPUESTA(java.lang.String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }


    /**
     * Gets the RESP_NOMBRECOMPLETO value for this ENTIDAD.
     * 
     * @return RESP_NOMBRECOMPLETO
     */
    public java.lang.String getRESP_NOMBRECOMPLETO() {
        return RESP_NOMBRECOMPLETO;
    }


    /**
     * Sets the RESP_NOMBRECOMPLETO value for this ENTIDAD.
     * 
     * @param RESP_NOMBRECOMPLETO
     */
    public void setRESP_NOMBRECOMPLETO(java.lang.String RESP_NOMBRECOMPLETO) {
        this.RESP_NOMBRECOMPLETO = RESP_NOMBRECOMPLETO;
    }


    /**
     * Gets the ROLENTIDAD value for this ENTIDAD.
     * 
     * @return ROLENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD[] getROLENTIDAD() {
        return ROLENTIDAD;
    }


    /**
     * Sets the ROLENTIDAD value for this ENTIDAD.
     * 
     * @param ROLENTIDAD
     */
    public void setROLENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD[] ROLENTIDAD) {
        this.ROLENTIDAD = ROLENTIDAD;
    }


    /**
     * Gets the SERVIDOR_REP value for this ENTIDAD.
     * 
     * @return SERVIDOR_REP
     */
    public java.lang.String getSERVIDOR_REP() {
        return SERVIDOR_REP;
    }


    /**
     * Sets the SERVIDOR_REP value for this ENTIDAD.
     * 
     * @param SERVIDOR_REP
     */
    public void setSERVIDOR_REP(java.lang.String SERVIDOR_REP) {
        this.SERVIDOR_REP = SERVIDOR_REP;
    }


    /**
     * Gets the TELEFONOCELULAR1 value for this ENTIDAD.
     * 
     * @return TELEFONOCELULAR1
     */
    public java.lang.String getTELEFONOCELULAR1() {
        return TELEFONOCELULAR1;
    }


    /**
     * Sets the TELEFONOCELULAR1 value for this ENTIDAD.
     * 
     * @param TELEFONOCELULAR1
     */
    public void setTELEFONOCELULAR1(java.lang.String TELEFONOCELULAR1) {
        this.TELEFONOCELULAR1 = TELEFONOCELULAR1;
    }


    /**
     * Gets the TELEFONOCELULAR2 value for this ENTIDAD.
     * 
     * @return TELEFONOCELULAR2
     */
    public java.lang.String getTELEFONOCELULAR2() {
        return TELEFONOCELULAR2;
    }


    /**
     * Sets the TELEFONOCELULAR2 value for this ENTIDAD.
     * 
     * @param TELEFONOCELULAR2
     */
    public void setTELEFONOCELULAR2(java.lang.String TELEFONOCELULAR2) {
        this.TELEFONOCELULAR2 = TELEFONOCELULAR2;
    }


    /**
     * Gets the TELEFONOCELULAR3 value for this ENTIDAD.
     * 
     * @return TELEFONOCELULAR3
     */
    public java.lang.String getTELEFONOCELULAR3() {
        return TELEFONOCELULAR3;
    }


    /**
     * Sets the TELEFONOCELULAR3 value for this ENTIDAD.
     * 
     * @param TELEFONOCELULAR3
     */
    public void setTELEFONOCELULAR3(java.lang.String TELEFONOCELULAR3) {
        this.TELEFONOCELULAR3 = TELEFONOCELULAR3;
    }


    /**
     * Gets the TELEFONOFAM value for this ENTIDAD.
     * 
     * @return TELEFONOFAM
     */
    public java.lang.String getTELEFONOFAM() {
        return TELEFONOFAM;
    }


    /**
     * Sets the TELEFONOFAM value for this ENTIDAD.
     * 
     * @param TELEFONOFAM
     */
    public void setTELEFONOFAM(java.lang.String TELEFONOFAM) {
        this.TELEFONOFAM = TELEFONOFAM;
    }


    /**
     * Gets the TIPOEMPLEADOID value for this ENTIDAD.
     * 
     * @return TIPOEMPLEADOID
     */
    public java.lang.String getTIPOEMPLEADOID() {
        return TIPOEMPLEADOID;
    }


    /**
     * Sets the TIPOEMPLEADOID value for this ENTIDAD.
     * 
     * @param TIPOEMPLEADOID
     */
    public void setTIPOEMPLEADOID(java.lang.String TIPOEMPLEADOID) {
        this.TIPOEMPLEADOID = TIPOEMPLEADOID;
    }


    /**
     * Gets the TIPOEMPRESAID value for this ENTIDAD.
     * 
     * @return TIPOEMPRESAID
     */
    public java.lang.String getTIPOEMPRESAID() {
        return TIPOEMPRESAID;
    }


    /**
     * Sets the TIPOEMPRESAID value for this ENTIDAD.
     * 
     * @param TIPOEMPRESAID
     */
    public void setTIPOEMPRESAID(java.lang.String TIPOEMPRESAID) {
        this.TIPOEMPRESAID = TIPOEMPRESAID;
    }


    /**
     * Gets the TIPOENTIDAD value for this ENTIDAD.
     * 
     * @return TIPOENTIDAD
     */
    public java.lang.String getTIPOENTIDAD() {
        return TIPOENTIDAD;
    }


    /**
     * Sets the TIPOENTIDAD value for this ENTIDAD.
     * 
     * @param TIPOENTIDAD
     */
    public void setTIPOENTIDAD(java.lang.String TIPOENTIDAD) {
        this.TIPOENTIDAD = TIPOENTIDAD;
    }


    /**
     * Gets the TIPOENTIDADID value for this ENTIDAD.
     * 
     * @return TIPOENTIDADID
     */
    public java.lang.String getTIPOENTIDADID() {
        return TIPOENTIDADID;
    }


    /**
     * Sets the TIPOENTIDADID value for this ENTIDAD.
     * 
     * @param TIPOENTIDADID
     */
    public void setTIPOENTIDADID(java.lang.String TIPOENTIDADID) {
        this.TIPOENTIDADID = TIPOENTIDADID;
    }


    /**
     * Gets the TIPOID value for this ENTIDAD.
     * 
     * @return TIPOID
     */
    public java.lang.String getTIPOID() {
        return TIPOID;
    }


    /**
     * Sets the TIPOID value for this ENTIDAD.
     * 
     * @param TIPOID
     */
    public void setTIPOID(java.lang.String TIPOID) {
        this.TIPOID = TIPOID;
    }


    /**
     * Gets the TIPOJUICIOPROVIDENCIA value for this ENTIDAD.
     * 
     * @return TIPOJUICIOPROVIDENCIA
     */
    public java.lang.String getTIPOJUICIOPROVIDENCIA() {
        return TIPOJUICIOPROVIDENCIA;
    }


    /**
     * Sets the TIPOJUICIOPROVIDENCIA value for this ENTIDAD.
     * 
     * @param TIPOJUICIOPROVIDENCIA
     */
    public void setTIPOJUICIOPROVIDENCIA(java.lang.String TIPOJUICIOPROVIDENCIA) {
        this.TIPOJUICIOPROVIDENCIA = TIPOJUICIOPROVIDENCIA;
    }


    /**
     * Gets the TIPOOBJETO value for this ENTIDAD.
     * 
     * @return TIPOOBJETO
     */
    public java.lang.String getTIPOOBJETO() {
        return TIPOOBJETO;
    }


    /**
     * Sets the TIPOOBJETO value for this ENTIDAD.
     * 
     * @param TIPOOBJETO
     */
    public void setTIPOOBJETO(java.lang.String TIPOOBJETO) {
        this.TIPOOBJETO = TIPOOBJETO;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ENTIDAD.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ENTIDAD.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the WEBSITE value for this ENTIDAD.
     * 
     * @return WEBSITE
     */
    public java.lang.String getWEBSITE() {
        return WEBSITE;
    }


    /**
     * Sets the WEBSITE value for this ENTIDAD.
     * 
     * @param WEBSITE
     */
    public void setWEBSITE(java.lang.String WEBSITE) {
        this.WEBSITE = WEBSITE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENTIDAD)) return false;
        ENTIDAD other = (ENTIDAD) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACTIVIDADECONOMICAID==null && other.getACTIVIDADECONOMICAID()==null) || 
             (this.ACTIVIDADECONOMICAID!=null &&
              this.ACTIVIDADECONOMICAID.equals(other.getACTIVIDADECONOMICAID()))) &&
            ((this.AGENTE==null && other.getAGENTE()==null) || 
             (this.AGENTE!=null &&
              java.util.Arrays.equals(this.AGENTE, other.getAGENTE()))) &&
            ((this.APELLIDO==null && other.getAPELLIDO()==null) || 
             (this.APELLIDO!=null &&
              this.APELLIDO.equals(other.getAPELLIDO()))) &&
            ((this.BENEFICIARIO==null && other.getBENEFICIARIO()==null) || 
             (this.BENEFICIARIO!=null &&
              java.util.Arrays.equals(this.BENEFICIARIO, other.getBENEFICIARIO()))) &&
            ((this.BLOQUEADA==null && other.getBLOQUEADA()==null) || 
             (this.BLOQUEADA!=null &&
              this.BLOQUEADA.equals(other.getBLOQUEADA()))) &&
            ((this.CAMBIOPASSWORD==null && other.getCAMBIOPASSWORD()==null) || 
             (this.CAMBIOPASSWORD!=null &&
              this.CAMBIOPASSWORD.equals(other.getCAMBIOPASSWORD()))) &&
            ((this.CIRCULARPROVIDENCIA==null && other.getCIRCULARPROVIDENCIA()==null) || 
             (this.CIRCULARPROVIDENCIA!=null &&
              this.CIRCULARPROVIDENCIA.equals(other.getCIRCULARPROVIDENCIA()))) &&
            ((this.CLIENTE==null && other.getCLIENTE()==null) || 
             (this.CLIENTE!=null &&
              java.util.Arrays.equals(this.CLIENTE, other.getCLIENTE()))) &&
            ((this.COMERCIAL==null && other.getCOMERCIAL()==null) || 
             (this.COMERCIAL!=null &&
              this.COMERCIAL.equals(other.getCOMERCIAL()))) &&
            ((this.CONOCETUCLIENTE==null && other.getCONOCETUCLIENTE()==null) || 
             (this.CONOCETUCLIENTE!=null &&
              this.CONOCETUCLIENTE.equals(other.getCONOCETUCLIENTE()))) &&
            ((this.CONSUEP==null && other.getCONSUEP()==null) || 
             (this.CONSUEP!=null &&
              this.CONSUEP.equals(other.getCONSUEP()))) &&
            ((this.DEPARTAMENTOID==null && other.getDEPARTAMENTOID()==null) || 
             (this.DEPARTAMENTOID!=null &&
              this.DEPARTAMENTOID.equals(other.getDEPARTAMENTOID()))) &&
            ((this.DIRECCIONFAM==null && other.getDIRECCIONFAM()==null) || 
             (this.DIRECCIONFAM!=null &&
              this.DIRECCIONFAM.equals(other.getDIRECCIONFAM()))) &&
            ((this.DOCUMENTO==null && other.getDOCUMENTO()==null) || 
             (this.DOCUMENTO!=null &&
              java.util.Arrays.equals(this.DOCUMENTO, other.getDOCUMENTO()))) &&
            ((this.EGRESOMENSUAL==null && other.getEGRESOMENSUAL()==null) || 
             (this.EGRESOMENSUAL!=null &&
              this.EGRESOMENSUAL.equals(other.getEGRESOMENSUAL()))) &&
            ((this.EMAILOPCIONAL==null && other.getEMAILOPCIONAL()==null) || 
             (this.EMAILOPCIONAL!=null &&
              this.EMAILOPCIONAL.equals(other.getEMAILOPCIONAL()))) &&
            ((this.EMAILPRINCIPAL==null && other.getEMAILPRINCIPAL()==null) || 
             (this.EMAILPRINCIPAL!=null &&
              this.EMAILPRINCIPAL.equals(other.getEMAILPRINCIPAL()))) &&
            ((this.EMAILRETENCION==null && other.getEMAILRETENCION()==null) || 
             (this.EMAILRETENCION!=null &&
              this.EMAILRETENCION.equals(other.getEMAILRETENCION()))) &&
            ((this.EMPRESA==null && other.getEMPRESA()==null) || 
             (this.EMPRESA!=null &&
              this.EMPRESA.equals(other.getEMPRESA()))) &&
            ((this.EMPRESAReference==null && other.getEMPRESAReference()==null) || 
             (this.EMPRESAReference!=null &&
              this.EMPRESAReference.equals(other.getEMPRESAReference()))) &&
            ((this.ENTEEMISORPROV==null && other.getENTEEMISORPROV()==null) || 
             (this.ENTEEMISORPROV!=null &&
              this.ENTEEMISORPROV.equals(other.getENTEEMISORPROV()))) &&
            ((this.ESQUEMAINTERFACEID==null && other.getESQUEMAINTERFACEID()==null) || 
             (this.ESQUEMAINTERFACEID!=null &&
              this.ESQUEMAINTERFACEID.equals(other.getESQUEMAINTERFACEID()))) &&
            ((this.ESTACALIFICADO==null && other.getESTACALIFICADO()==null) || 
             (this.ESTACALIFICADO!=null &&
              this.ESTACALIFICADO.equals(other.getESTACALIFICADO()))) &&
            ((this.ESTADO==null && other.getESTADO()==null) || 
             (this.ESTADO!=null &&
              this.ESTADO.equals(other.getESTADO()))) &&
            ((this.ESTADOCIVILID==null && other.getESTADOCIVILID()==null) || 
             (this.ESTADOCIVILID!=null &&
              this.ESTADOCIVILID.equals(other.getESTADOCIVILID()))) &&
            ((this.ESTADOMIGRATORIO==null && other.getESTADOMIGRATORIO()==null) || 
             (this.ESTADOMIGRATORIO!=null &&
              this.ESTADOMIGRATORIO.equals(other.getESTADOMIGRATORIO()))) &&
            ((this.ESTAIMPRESA==null && other.getESTAIMPRESA()==null) || 
             (this.ESTAIMPRESA!=null &&
              this.ESTAIMPRESA.equals(other.getESTAIMPRESA()))) &&
            ((this.FAMILIAR==null && other.getFAMILIAR()==null) || 
             (this.FAMILIAR!=null &&
              this.FAMILIAR.equals(other.getFAMILIAR()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHACADPASAPORTE==null && other.getFECHACADPASAPORTE()==null) || 
             (this.FECHACADPASAPORTE!=null &&
              this.FECHACADPASAPORTE.equals(other.getFECHACADPASAPORTE()))) &&
            ((this.FECHACAMBIOPASSWORD==null && other.getFECHACAMBIOPASSWORD()==null) || 
             (this.FECHACAMBIOPASSWORD!=null &&
              this.FECHACAMBIOPASSWORD.equals(other.getFECHACAMBIOPASSWORD()))) &&
            ((this.FECHACIRCULARPROV==null && other.getFECHACIRCULARPROV()==null) || 
             (this.FECHACIRCULARPROV!=null &&
              this.FECHACIRCULARPROV.equals(other.getFECHACIRCULARPROV()))) &&
            ((this.FECHAEXPPASAPORTE==null && other.getFECHAEXPPASAPORTE()==null) || 
             (this.FECHAEXPPASAPORTE!=null &&
              this.FECHAEXPPASAPORTE.equals(other.getFECHAEXPPASAPORTE()))) &&
            ((this.FECHAINGRESOPAIS==null && other.getFECHAINGRESOPAIS()==null) || 
             (this.FECHAINGRESOPAIS!=null &&
              this.FECHAINGRESOPAIS.equals(other.getFECHAINGRESOPAIS()))) &&
            ((this.FECHAPROVIDENCIA==null && other.getFECHAPROVIDENCIA()==null) || 
             (this.FECHAPROVIDENCIA!=null &&
              this.FECHAPROVIDENCIA.equals(other.getFECHAPROVIDENCIA()))) &&
            ((this.FUENTE==null && other.getFUENTE()==null) || 
             (this.FUENTE!=null &&
              this.FUENTE.equals(other.getFUENTE()))) &&
            ((this.GRUPOECONOMICOID==null && other.getGRUPOECONOMICOID()==null) || 
             (this.GRUPOECONOMICOID!=null &&
              this.GRUPOECONOMICOID.equals(other.getGRUPOECONOMICOID()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.IDENTIFICACION==null && other.getIDENTIFICACION()==null) || 
             (this.IDENTIFICACION!=null &&
              this.IDENTIFICACION.equals(other.getIDENTIFICACION()))) &&
            ((this.INGRESOMENSUAL==null && other.getINGRESOMENSUAL()==null) || 
             (this.INGRESOMENSUAL!=null &&
              this.INGRESOMENSUAL.equals(other.getINGRESOMENSUAL()))) &&
            ((this.LOGIN==null && other.getLOGIN()==null) || 
             (this.LOGIN!=null &&
              this.LOGIN.equals(other.getLOGIN()))) &&
            ((this.MOTIVOCONSEP==null && other.getMOTIVOCONSEP()==null) || 
             (this.MOTIVOCONSEP!=null &&
              this.MOTIVOCONSEP.equals(other.getMOTIVOCONSEP()))) &&
            ((this.MOTIVOINTERPOL==null && other.getMOTIVOINTERPOL()==null) || 
             (this.MOTIVOINTERPOL!=null &&
              this.MOTIVOINTERPOL.equals(other.getMOTIVOINTERPOL()))) &&
            ((this.NACIONALIDADID==null && other.getNACIONALIDADID()==null) || 
             (this.NACIONALIDADID!=null &&
              this.NACIONALIDADID.equals(other.getNACIONALIDADID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.NOMBRECOMERCIAL==null && other.getNOMBRECOMERCIAL()==null) || 
             (this.NOMBRECOMERCIAL!=null &&
              this.NOMBRECOMERCIAL.equals(other.getNOMBRECOMERCIAL()))) &&
            ((this.NOMBRECOMPLETO==null && other.getNOMBRECOMPLETO()==null) || 
             (this.NOMBRECOMPLETO!=null &&
              this.NOMBRECOMPLETO.equals(other.getNOMBRECOMPLETO()))) &&
            ((this.NOMBRECORTO==null && other.getNOMBRECORTO()==null) || 
             (this.NOMBRECORTO!=null &&
              this.NOMBRECORTO.equals(other.getNOMBRECORTO()))) &&
            ((this.NUMEROINTENTOS==null && other.getNUMEROINTENTOS()==null) || 
             (this.NUMEROINTENTOS!=null &&
              this.NUMEROINTENTOS.equals(other.getNUMEROINTENTOS()))) &&
            ((this.NUMJUICIOPROVIDENCIA==null && other.getNUMJUICIOPROVIDENCIA()==null) || 
             (this.NUMJUICIOPROVIDENCIA!=null &&
              this.NUMJUICIOPROVIDENCIA.equals(other.getNUMJUICIOPROVIDENCIA()))) &&
            ((this.OBSERVACIONESPROV==null && other.getOBSERVACIONESPROV()==null) || 
             (this.OBSERVACIONESPROV!=null &&
              this.OBSERVACIONESPROV.equals(other.getOBSERVACIONESPROV()))) &&
            ((this.OFAC==null && other.getOFAC()==null) || 
             (this.OFAC!=null &&
              this.OFAC.equals(other.getOFAC()))) &&
            ((this.OTROSINGRESOS==null && other.getOTROSINGRESOS()==null) || 
             (this.OTROSINGRESOS!=null &&
              this.OTROSINGRESOS.equals(other.getOTROSINGRESOS()))) &&
            ((this.PARENTESCOID==null && other.getPARENTESCOID()==null) || 
             (this.PARENTESCOID!=null &&
              this.PARENTESCOID.equals(other.getPARENTESCOID()))) &&
            ((this.PASSWORD==null && other.getPASSWORD()==null) || 
             (this.PASSWORD!=null &&
              this.PASSWORD.equals(other.getPASSWORD()))) &&
            ((this.PATRIMONIO==null && other.getPATRIMONIO()==null) || 
             (this.PATRIMONIO!=null &&
              this.PATRIMONIO.equals(other.getPATRIMONIO()))) &&
            ((this.PEPS==null && other.getPEPS()==null) || 
             (this.PEPS!=null &&
              this.PEPS.equals(other.getPEPS()))) &&
            ((this.PERSONA==null && other.getPERSONA()==null) || 
             (this.PERSONA!=null &&
              this.PERSONA.equals(other.getPERSONA()))) &&
            ((this.PERSONAReference==null && other.getPERSONAReference()==null) || 
             (this.PERSONAReference!=null &&
              this.PERSONAReference.equals(other.getPERSONAReference()))) &&
            ((this.POLIZA==null && other.getPOLIZA()==null) || 
             (this.POLIZA!=null &&
              java.util.Arrays.equals(this.POLIZA, other.getPOLIZA()))) &&
            ((this.PREGUNTA==null && other.getPREGUNTA()==null) || 
             (this.PREGUNTA!=null &&
              this.PREGUNTA.equals(other.getPREGUNTA()))) &&
            ((this.PROVIDENCIA==null && other.getPROVIDENCIA()==null) || 
             (this.PROVIDENCIA!=null &&
              this.PROVIDENCIA.equals(other.getPROVIDENCIA()))) &&
            ((this.PUBLICA==null && other.getPUBLICA()==null) || 
             (this.PUBLICA!=null &&
              this.PUBLICA.equals(other.getPUBLICA()))) &&
            ((this.RESPUESTA==null && other.getRESPUESTA()==null) || 
             (this.RESPUESTA!=null &&
              this.RESPUESTA.equals(other.getRESPUESTA()))) &&
            ((this.RESP_NOMBRECOMPLETO==null && other.getRESP_NOMBRECOMPLETO()==null) || 
             (this.RESP_NOMBRECOMPLETO!=null &&
              this.RESP_NOMBRECOMPLETO.equals(other.getRESP_NOMBRECOMPLETO()))) &&
            ((this.ROLENTIDAD==null && other.getROLENTIDAD()==null) || 
             (this.ROLENTIDAD!=null &&
              java.util.Arrays.equals(this.ROLENTIDAD, other.getROLENTIDAD()))) &&
            ((this.SERVIDOR_REP==null && other.getSERVIDOR_REP()==null) || 
             (this.SERVIDOR_REP!=null &&
              this.SERVIDOR_REP.equals(other.getSERVIDOR_REP()))) &&
            ((this.TELEFONOCELULAR1==null && other.getTELEFONOCELULAR1()==null) || 
             (this.TELEFONOCELULAR1!=null &&
              this.TELEFONOCELULAR1.equals(other.getTELEFONOCELULAR1()))) &&
            ((this.TELEFONOCELULAR2==null && other.getTELEFONOCELULAR2()==null) || 
             (this.TELEFONOCELULAR2!=null &&
              this.TELEFONOCELULAR2.equals(other.getTELEFONOCELULAR2()))) &&
            ((this.TELEFONOCELULAR3==null && other.getTELEFONOCELULAR3()==null) || 
             (this.TELEFONOCELULAR3!=null &&
              this.TELEFONOCELULAR3.equals(other.getTELEFONOCELULAR3()))) &&
            ((this.TELEFONOFAM==null && other.getTELEFONOFAM()==null) || 
             (this.TELEFONOFAM!=null &&
              this.TELEFONOFAM.equals(other.getTELEFONOFAM()))) &&
            ((this.TIPOEMPLEADOID==null && other.getTIPOEMPLEADOID()==null) || 
             (this.TIPOEMPLEADOID!=null &&
              this.TIPOEMPLEADOID.equals(other.getTIPOEMPLEADOID()))) &&
            ((this.TIPOEMPRESAID==null && other.getTIPOEMPRESAID()==null) || 
             (this.TIPOEMPRESAID!=null &&
              this.TIPOEMPRESAID.equals(other.getTIPOEMPRESAID()))) &&
            ((this.TIPOENTIDAD==null && other.getTIPOENTIDAD()==null) || 
             (this.TIPOENTIDAD!=null &&
              this.TIPOENTIDAD.equals(other.getTIPOENTIDAD()))) &&
            ((this.TIPOENTIDADID==null && other.getTIPOENTIDADID()==null) || 
             (this.TIPOENTIDADID!=null &&
              this.TIPOENTIDADID.equals(other.getTIPOENTIDADID()))) &&
            ((this.TIPOID==null && other.getTIPOID()==null) || 
             (this.TIPOID!=null &&
              this.TIPOID.equals(other.getTIPOID()))) &&
            ((this.TIPOJUICIOPROVIDENCIA==null && other.getTIPOJUICIOPROVIDENCIA()==null) || 
             (this.TIPOJUICIOPROVIDENCIA!=null &&
              this.TIPOJUICIOPROVIDENCIA.equals(other.getTIPOJUICIOPROVIDENCIA()))) &&
            ((this.TIPOOBJETO==null && other.getTIPOOBJETO()==null) || 
             (this.TIPOOBJETO!=null &&
              this.TIPOOBJETO.equals(other.getTIPOOBJETO()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.WEBSITE==null && other.getWEBSITE()==null) || 
             (this.WEBSITE!=null &&
              this.WEBSITE.equals(other.getWEBSITE())));
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
        if (getACTIVIDADECONOMICAID() != null) {
            _hashCode += getACTIVIDADECONOMICAID().hashCode();
        }
        if (getAGENTE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAGENTE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAGENTE(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAPELLIDO() != null) {
            _hashCode += getAPELLIDO().hashCode();
        }
        if (getBENEFICIARIO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBENEFICIARIO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBENEFICIARIO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBLOQUEADA() != null) {
            _hashCode += getBLOQUEADA().hashCode();
        }
        if (getCAMBIOPASSWORD() != null) {
            _hashCode += getCAMBIOPASSWORD().hashCode();
        }
        if (getCIRCULARPROVIDENCIA() != null) {
            _hashCode += getCIRCULARPROVIDENCIA().hashCode();
        }
        if (getCLIENTE() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCLIENTE());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCLIENTE(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCOMERCIAL() != null) {
            _hashCode += getCOMERCIAL().hashCode();
        }
        if (getCONOCETUCLIENTE() != null) {
            _hashCode += getCONOCETUCLIENTE().hashCode();
        }
        if (getCONSUEP() != null) {
            _hashCode += getCONSUEP().hashCode();
        }
        if (getDEPARTAMENTOID() != null) {
            _hashCode += getDEPARTAMENTOID().hashCode();
        }
        if (getDIRECCIONFAM() != null) {
            _hashCode += getDIRECCIONFAM().hashCode();
        }
        if (getDOCUMENTO() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDOCUMENTO());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDOCUMENTO(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEGRESOMENSUAL() != null) {
            _hashCode += getEGRESOMENSUAL().hashCode();
        }
        if (getEMAILOPCIONAL() != null) {
            _hashCode += getEMAILOPCIONAL().hashCode();
        }
        if (getEMAILPRINCIPAL() != null) {
            _hashCode += getEMAILPRINCIPAL().hashCode();
        }
        if (getEMAILRETENCION() != null) {
            _hashCode += getEMAILRETENCION().hashCode();
        }
        if (getEMPRESA() != null) {
            _hashCode += getEMPRESA().hashCode();
        }
        if (getEMPRESAReference() != null) {
            _hashCode += getEMPRESAReference().hashCode();
        }
        if (getENTEEMISORPROV() != null) {
            _hashCode += getENTEEMISORPROV().hashCode();
        }
        if (getESQUEMAINTERFACEID() != null) {
            _hashCode += getESQUEMAINTERFACEID().hashCode();
        }
        if (getESTACALIFICADO() != null) {
            _hashCode += getESTACALIFICADO().hashCode();
        }
        if (getESTADO() != null) {
            _hashCode += getESTADO().hashCode();
        }
        if (getESTADOCIVILID() != null) {
            _hashCode += getESTADOCIVILID().hashCode();
        }
        if (getESTADOMIGRATORIO() != null) {
            _hashCode += getESTADOMIGRATORIO().hashCode();
        }
        if (getESTAIMPRESA() != null) {
            _hashCode += getESTAIMPRESA().hashCode();
        }
        if (getFAMILIAR() != null) {
            _hashCode += getFAMILIAR().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHACADPASAPORTE() != null) {
            _hashCode += getFECHACADPASAPORTE().hashCode();
        }
        if (getFECHACAMBIOPASSWORD() != null) {
            _hashCode += getFECHACAMBIOPASSWORD().hashCode();
        }
        if (getFECHACIRCULARPROV() != null) {
            _hashCode += getFECHACIRCULARPROV().hashCode();
        }
        if (getFECHAEXPPASAPORTE() != null) {
            _hashCode += getFECHAEXPPASAPORTE().hashCode();
        }
        if (getFECHAINGRESOPAIS() != null) {
            _hashCode += getFECHAINGRESOPAIS().hashCode();
        }
        if (getFECHAPROVIDENCIA() != null) {
            _hashCode += getFECHAPROVIDENCIA().hashCode();
        }
        if (getFUENTE() != null) {
            _hashCode += getFUENTE().hashCode();
        }
        if (getGRUPOECONOMICOID() != null) {
            _hashCode += getGRUPOECONOMICOID().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getIDENTIFICACION() != null) {
            _hashCode += getIDENTIFICACION().hashCode();
        }
        if (getINGRESOMENSUAL() != null) {
            _hashCode += getINGRESOMENSUAL().hashCode();
        }
        if (getLOGIN() != null) {
            _hashCode += getLOGIN().hashCode();
        }
        if (getMOTIVOCONSEP() != null) {
            _hashCode += getMOTIVOCONSEP().hashCode();
        }
        if (getMOTIVOINTERPOL() != null) {
            _hashCode += getMOTIVOINTERPOL().hashCode();
        }
        if (getNACIONALIDADID() != null) {
            _hashCode += getNACIONALIDADID().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getNOMBRECOMERCIAL() != null) {
            _hashCode += getNOMBRECOMERCIAL().hashCode();
        }
        if (getNOMBRECOMPLETO() != null) {
            _hashCode += getNOMBRECOMPLETO().hashCode();
        }
        if (getNOMBRECORTO() != null) {
            _hashCode += getNOMBRECORTO().hashCode();
        }
        if (getNUMEROINTENTOS() != null) {
            _hashCode += getNUMEROINTENTOS().hashCode();
        }
        if (getNUMJUICIOPROVIDENCIA() != null) {
            _hashCode += getNUMJUICIOPROVIDENCIA().hashCode();
        }
        if (getOBSERVACIONESPROV() != null) {
            _hashCode += getOBSERVACIONESPROV().hashCode();
        }
        if (getOFAC() != null) {
            _hashCode += getOFAC().hashCode();
        }
        if (getOTROSINGRESOS() != null) {
            _hashCode += getOTROSINGRESOS().hashCode();
        }
        if (getPARENTESCOID() != null) {
            _hashCode += getPARENTESCOID().hashCode();
        }
        if (getPASSWORD() != null) {
            _hashCode += getPASSWORD().hashCode();
        }
        if (getPATRIMONIO() != null) {
            _hashCode += getPATRIMONIO().hashCode();
        }
        if (getPEPS() != null) {
            _hashCode += getPEPS().hashCode();
        }
        if (getPERSONA() != null) {
            _hashCode += getPERSONA().hashCode();
        }
        if (getPERSONAReference() != null) {
            _hashCode += getPERSONAReference().hashCode();
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
        if (getPREGUNTA() != null) {
            _hashCode += getPREGUNTA().hashCode();
        }
        if (getPROVIDENCIA() != null) {
            _hashCode += getPROVIDENCIA().hashCode();
        }
        if (getPUBLICA() != null) {
            _hashCode += getPUBLICA().hashCode();
        }
        if (getRESPUESTA() != null) {
            _hashCode += getRESPUESTA().hashCode();
        }
        if (getRESP_NOMBRECOMPLETO() != null) {
            _hashCode += getRESP_NOMBRECOMPLETO().hashCode();
        }
        if (getROLENTIDAD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getROLENTIDAD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getROLENTIDAD(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSERVIDOR_REP() != null) {
            _hashCode += getSERVIDOR_REP().hashCode();
        }
        if (getTELEFONOCELULAR1() != null) {
            _hashCode += getTELEFONOCELULAR1().hashCode();
        }
        if (getTELEFONOCELULAR2() != null) {
            _hashCode += getTELEFONOCELULAR2().hashCode();
        }
        if (getTELEFONOCELULAR3() != null) {
            _hashCode += getTELEFONOCELULAR3().hashCode();
        }
        if (getTELEFONOFAM() != null) {
            _hashCode += getTELEFONOFAM().hashCode();
        }
        if (getTIPOEMPLEADOID() != null) {
            _hashCode += getTIPOEMPLEADOID().hashCode();
        }
        if (getTIPOEMPRESAID() != null) {
            _hashCode += getTIPOEMPRESAID().hashCode();
        }
        if (getTIPOENTIDAD() != null) {
            _hashCode += getTIPOENTIDAD().hashCode();
        }
        if (getTIPOENTIDADID() != null) {
            _hashCode += getTIPOENTIDADID().hashCode();
        }
        if (getTIPOID() != null) {
            _hashCode += getTIPOID().hashCode();
        }
        if (getTIPOJUICIOPROVIDENCIA() != null) {
            _hashCode += getTIPOJUICIOPROVIDENCIA().hashCode();
        }
        if (getTIPOOBJETO() != null) {
            _hashCode += getTIPOOBJETO().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getWEBSITE() != null) {
            _hashCode += getWEBSITE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENTIDAD.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACTIVIDADECONOMICAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("APELLIDO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "APELLIDO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BENEFICIARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BLOQUEADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BLOQUEADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAMBIOPASSWORD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CAMBIOPASSWORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CIRCULARPROVIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIRCULARPROVIDENCIA"));
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
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMERCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "COMERCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONOCETUCLIENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONOCETUCLIENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONSUEP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CONSUEP"));
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
        elemField.setFieldName("DIRECCIONFAM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCIONFAM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCUMENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EGRESOMENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EGRESOMENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAILOPCIONAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMAILOPCIONAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAILPRINCIPAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMAILPRINCIPAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMAILRETENCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMAILRETENCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMPRESA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EMPRESAReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMPRESAReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfEMPRESAFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTEEMISORPROV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTEEMISORPROV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESQUEMAINTERFACEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESQUEMAINTERFACEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTACALIFICADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTACALIFICADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOCIVILID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADOCIVILID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTADOMIGRATORIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADOMIGRATORIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESTAIMPRESA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTAIMPRESA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FAMILIAR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FAMILIAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAACTUALIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACADPASAPORTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACADPASAPORTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACAMBIOPASSWORD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACAMBIOPASSWORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACIRCULARPROV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACIRCULARPROV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAEXPPASAPORTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAEXPPASAPORTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAINGRESOPAIS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAINGRESOPAIS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAPROVIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAPROVIDENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FUENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FUENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GRUPOECONOMICOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "GRUPOECONOMICOID"));
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
        elemField.setFieldName("IDENTIFICACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "IDENTIFICACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INGRESOMENSUAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "INGRESOMENSUAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOGIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LOGIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOCONSEP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOCONSEP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOINTERPOL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOINTERPOL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NACIONALIDADID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDADID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("NOMBRECOMERCIAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRECOMERCIAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRECOMPLETO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRECOMPLETO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRECORTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRECORTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROINTENTOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROINTENTOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMJUICIOPROVIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMJUICIOPROVIDENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBSERVACIONESPROV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "OBSERVACIONESPROV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OFAC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "OFAC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OTROSINGRESOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "OTROSINGRESOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARENTESCOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PARENTESCOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PASSWORD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PASSWORD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PATRIMONIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PATRIMONIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PEPS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PEPS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERSONA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PERSONA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PERSONA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PERSONAReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PERSONAReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPERSONAFG7c7FF7"));
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
        elemField.setFieldName("PREGUNTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PREGUNTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PROVIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVIDENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUBLICA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PUBLICA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPUESTA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RESPUESTA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESP_NOMBRECOMPLETO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RESP_NOMBRECOMPLETO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ROLENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SERVIDOR_REP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SERVIDOR_REP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONOCELULAR1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONOCELULAR1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONOCELULAR2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONOCELULAR2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONOCELULAR3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONOCELULAR3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TELEFONOFAM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TELEFONOFAM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOEMPLEADOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOEMPLEADOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOEMPRESAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOEMPRESAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOENTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOENTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOENTIDADID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOENTIDADID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOJUICIOPROVIDENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOJUICIOPROVIDENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOOBJETO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOOBJETO"));
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
        elemField.setFieldName("WEBSITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "WEBSITE"));
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
