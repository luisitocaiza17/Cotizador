/**
 * ENDOSO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ENDOSO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ANULAR;

    private java.lang.String ASOCIACIONCB;

    private java.lang.String AUTORIZACIONAID;

    private java.lang.String AUTORIZACIONBID;

    private java.lang.String AUTORIZACIONBROKERPOLID;

    private java.lang.String AUTORIZACIONCAMBIOCOMID;

    private java.lang.String AUTORIZACIONCID;

    private java.lang.String AUTORIZACIONDIASCONVENIOPAGO;

    private java.lang.String AUTORIZACIONFIRMAID;

    private java.lang.String AUTORIZACIONID;

    private java.lang.String AUTORIZACIONLIMITEEMBARQUEID;

    private java.lang.String AUTORIZACIONMONTOFIANID;

    private java.lang.String AUTORIZACIONNUEVOSOATID;

    private java.lang.String AUTORIZACIONPERSONAID;

    private java.lang.String AUTORIZACIONPRID;

    private java.lang.String AUTORIZACIONPRIMAMINID;

    private java.lang.String AUTORIZACIONVIGENCIAID;

    private java.lang.String AUTORIZACIONZFID;

    private java.lang.String AUTORIZAFIANZASID;

    private java.lang.String CAJAID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference;

    private java.math.BigDecimal COMISIONBROKER;

    private java.math.BigDecimal COMISIONMANTENIMIENTO;

    private java.lang.String CONVENIOPAGOID;

    private java.lang.String DERECHOSEMISION;

    private java.lang.String DESCRIPCION;

    private java.math.BigDecimal DIASAVISOSINIESTRO;

    private java.math.BigDecimal DIASCONVENIOPAGO;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM[] ENDOSOITEM;

    private java.lang.String ENDOSOREF;

    private java.lang.String ENTIDADAGENTEID;

    private java.lang.String ESAJUSTEVIGENCIA;

    private java.lang.String ESAUTOMATICO;

    private java.lang.String ESCANCELACION;

    private java.lang.String ESDATOOTROSISTEMA;

    private java.lang.String ESDEPOLIZAMADRE;

    private java.lang.String ESFACTURADOOTROSISTEMA;

    private java.lang.String ESVERIFICADO;

    private java.lang.String EXCENTOIVA;

    private java.util.Calendar FECFINCREDITO;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHAANULACION;

    private java.util.Calendar FECHACOTIZACION;

    private java.util.Calendar FECHAELABORACION;

    private java.util.Calendar FECHAVENCIMIENTO;

    private java.lang.String FIRMASUCURSALID;

    private java.lang.String FORMAPAGO;

    private java.lang.String ID;

    private java.lang.String IMPRIMEORIGINAL;

    private java.math.BigDecimal LIMITEINDEMNIZACION;

    private java.lang.String MOTIVOCANCELACION;

    private java.lang.String NOMBRETARJETACREDITOID;

    private java.lang.String NOTAS;

    private java.math.BigDecimal NUMERO;

    private java.lang.String NUMEROAUTORIZACION;

    private java.lang.String NUMEROCERTIFICADO;

    private java.lang.String NUMEROTARIFA;

    private java.lang.Long NUMEROTARJETA;

    private java.math.BigDecimal NUMEROTRAMITE;

    private java.lang.String OBSERVACIONES;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA POLIZA;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPOLIZAFG7C7FF7 POLIZAReference;

    private java.math.BigDecimal PORCCOMISIONAGENTE;

    private java.lang.String PREIMPRESOVERIFICADO;

    private java.lang.String PRIMAMINVERIFICADA;

    private java.lang.String PRODUCTOAPEID;

    private java.lang.String PUNTOVENTAAPEID;

    private java.lang.String PUNTOVENTAID;

    private java.lang.String SISTEMAEMISOR;

    private java.lang.String SISTEMAEMISORID;

    private java.lang.String SUCURSALID;

    private java.lang.String TARJETAID;

    private java.math.BigDecimal TASAMINIMA;

    private java.lang.String TIPOCANCELACION;

    private java.lang.String TIPOENDOSOID;

    private java.lang.String TIPOITEMID;

    private java.lang.String TIPOPAGOID;

    private java.lang.String TIPOSEGUROID;

    private java.lang.String TITULARTARJETA;

    private java.lang.String UNIDADNEGOCIOID;

    private java.lang.String USUARIOACTUALIZA;

    private java.math.BigDecimal VALORASEGURADO;

    private java.math.BigDecimal VALORCOMISION;

    private java.math.BigDecimal VALORPRIMANETA;

    private java.math.BigDecimal VALORRECARGOSC;

    private java.util.Calendar VIGENCIADESDE;

    private java.util.Calendar VIGENCIAHASTA;

    public ENDOSO() {
    }

    public ENDOSO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ANULAR,
           java.lang.String ASOCIACIONCB,
           java.lang.String AUTORIZACIONAID,
           java.lang.String AUTORIZACIONBID,
           java.lang.String AUTORIZACIONBROKERPOLID,
           java.lang.String AUTORIZACIONCAMBIOCOMID,
           java.lang.String AUTORIZACIONCID,
           java.lang.String AUTORIZACIONDIASCONVENIOPAGO,
           java.lang.String AUTORIZACIONFIRMAID,
           java.lang.String AUTORIZACIONID,
           java.lang.String AUTORIZACIONLIMITEEMBARQUEID,
           java.lang.String AUTORIZACIONMONTOFIANID,
           java.lang.String AUTORIZACIONNUEVOSOATID,
           java.lang.String AUTORIZACIONPERSONAID,
           java.lang.String AUTORIZACIONPRID,
           java.lang.String AUTORIZACIONPRIMAMINID,
           java.lang.String AUTORIZACIONVIGENCIAID,
           java.lang.String AUTORIZACIONZFID,
           java.lang.String AUTORIZAFIANZASID,
           java.lang.String CAJAID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference,
           java.math.BigDecimal COMISIONBROKER,
           java.math.BigDecimal COMISIONMANTENIMIENTO,
           java.lang.String CONVENIOPAGOID,
           java.lang.String DERECHOSEMISION,
           java.lang.String DESCRIPCION,
           java.math.BigDecimal DIASAVISOSINIESTRO,
           java.math.BigDecimal DIASCONVENIOPAGO,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM[] ENDOSOITEM,
           java.lang.String ENDOSOREF,
           java.lang.String ENTIDADAGENTEID,
           java.lang.String ESAJUSTEVIGENCIA,
           java.lang.String ESAUTOMATICO,
           java.lang.String ESCANCELACION,
           java.lang.String ESDATOOTROSISTEMA,
           java.lang.String ESDEPOLIZAMADRE,
           java.lang.String ESFACTURADOOTROSISTEMA,
           java.lang.String ESVERIFICADO,
           java.lang.String EXCENTOIVA,
           java.util.Calendar FECFINCREDITO,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHAANULACION,
           java.util.Calendar FECHACOTIZACION,
           java.util.Calendar FECHAELABORACION,
           java.util.Calendar FECHAVENCIMIENTO,
           java.lang.String FIRMASUCURSALID,
           java.lang.String FORMAPAGO,
           java.lang.String ID,
           java.lang.String IMPRIMEORIGINAL,
           java.math.BigDecimal LIMITEINDEMNIZACION,
           java.lang.String MOTIVOCANCELACION,
           java.lang.String NOMBRETARJETACREDITOID,
           java.lang.String NOTAS,
           java.math.BigDecimal NUMERO,
           java.lang.String NUMEROAUTORIZACION,
           java.lang.String NUMEROCERTIFICADO,
           java.lang.String NUMEROTARIFA,
           java.lang.Long NUMEROTARJETA,
           java.math.BigDecimal NUMEROTRAMITE,
           java.lang.String OBSERVACIONES,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA POLIZA,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPOLIZAFG7C7FF7 POLIZAReference,
           java.math.BigDecimal PORCCOMISIONAGENTE,
           java.lang.String PREIMPRESOVERIFICADO,
           java.lang.String PRIMAMINVERIFICADA,
           java.lang.String PRODUCTOAPEID,
           java.lang.String PUNTOVENTAAPEID,
           java.lang.String PUNTOVENTAID,
           java.lang.String SISTEMAEMISOR,
           java.lang.String SISTEMAEMISORID,
           java.lang.String SUCURSALID,
           java.lang.String TARJETAID,
           java.math.BigDecimal TASAMINIMA,
           java.lang.String TIPOCANCELACION,
           java.lang.String TIPOENDOSOID,
           java.lang.String TIPOITEMID,
           java.lang.String TIPOPAGOID,
           java.lang.String TIPOSEGUROID,
           java.lang.String TITULARTARJETA,
           java.lang.String UNIDADNEGOCIOID,
           java.lang.String USUARIOACTUALIZA,
           java.math.BigDecimal VALORASEGURADO,
           java.math.BigDecimal VALORCOMISION,
           java.math.BigDecimal VALORPRIMANETA,
           java.math.BigDecimal VALORRECARGOSC,
           java.util.Calendar VIGENCIADESDE,
           java.util.Calendar VIGENCIAHASTA) {
        super(
            id,
            ref,
            entityKey);
        this.ANULAR = ANULAR;
        this.ASOCIACIONCB = ASOCIACIONCB;
        this.AUTORIZACIONAID = AUTORIZACIONAID;
        this.AUTORIZACIONBID = AUTORIZACIONBID;
        this.AUTORIZACIONBROKERPOLID = AUTORIZACIONBROKERPOLID;
        this.AUTORIZACIONCAMBIOCOMID = AUTORIZACIONCAMBIOCOMID;
        this.AUTORIZACIONCID = AUTORIZACIONCID;
        this.AUTORIZACIONDIASCONVENIOPAGO = AUTORIZACIONDIASCONVENIOPAGO;
        this.AUTORIZACIONFIRMAID = AUTORIZACIONFIRMAID;
        this.AUTORIZACIONID = AUTORIZACIONID;
        this.AUTORIZACIONLIMITEEMBARQUEID = AUTORIZACIONLIMITEEMBARQUEID;
        this.AUTORIZACIONMONTOFIANID = AUTORIZACIONMONTOFIANID;
        this.AUTORIZACIONNUEVOSOATID = AUTORIZACIONNUEVOSOATID;
        this.AUTORIZACIONPERSONAID = AUTORIZACIONPERSONAID;
        this.AUTORIZACIONPRID = AUTORIZACIONPRID;
        this.AUTORIZACIONPRIMAMINID = AUTORIZACIONPRIMAMINID;
        this.AUTORIZACIONVIGENCIAID = AUTORIZACIONVIGENCIAID;
        this.AUTORIZACIONZFID = AUTORIZACIONZFID;
        this.AUTORIZAFIANZASID = AUTORIZAFIANZASID;
        this.CAJAID = CAJAID;
        this.CLIENTE = CLIENTE;
        this.CLIENTEReference = CLIENTEReference;
        this.COMISIONBROKER = COMISIONBROKER;
        this.COMISIONMANTENIMIENTO = COMISIONMANTENIMIENTO;
        this.CONVENIOPAGOID = CONVENIOPAGOID;
        this.DERECHOSEMISION = DERECHOSEMISION;
        this.DESCRIPCION = DESCRIPCION;
        this.DIASAVISOSINIESTRO = DIASAVISOSINIESTRO;
        this.DIASCONVENIOPAGO = DIASCONVENIOPAGO;
        this.ENDOSOITEM = ENDOSOITEM;
        this.ENDOSOREF = ENDOSOREF;
        this.ENTIDADAGENTEID = ENTIDADAGENTEID;
        this.ESAJUSTEVIGENCIA = ESAJUSTEVIGENCIA;
        this.ESAUTOMATICO = ESAUTOMATICO;
        this.ESCANCELACION = ESCANCELACION;
        this.ESDATOOTROSISTEMA = ESDATOOTROSISTEMA;
        this.ESDEPOLIZAMADRE = ESDEPOLIZAMADRE;
        this.ESFACTURADOOTROSISTEMA = ESFACTURADOOTROSISTEMA;
        this.ESVERIFICADO = ESVERIFICADO;
        this.EXCENTOIVA = EXCENTOIVA;
        this.FECFINCREDITO = FECFINCREDITO;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHAANULACION = FECHAANULACION;
        this.FECHACOTIZACION = FECHACOTIZACION;
        this.FECHAELABORACION = FECHAELABORACION;
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
        this.FIRMASUCURSALID = FIRMASUCURSALID;
        this.FORMAPAGO = FORMAPAGO;
        this.ID = ID;
        this.IMPRIMEORIGINAL = IMPRIMEORIGINAL;
        this.LIMITEINDEMNIZACION = LIMITEINDEMNIZACION;
        this.MOTIVOCANCELACION = MOTIVOCANCELACION;
        this.NOMBRETARJETACREDITOID = NOMBRETARJETACREDITOID;
        this.NOTAS = NOTAS;
        this.NUMERO = NUMERO;
        this.NUMEROAUTORIZACION = NUMEROAUTORIZACION;
        this.NUMEROCERTIFICADO = NUMEROCERTIFICADO;
        this.NUMEROTARIFA = NUMEROTARIFA;
        this.NUMEROTARJETA = NUMEROTARJETA;
        this.NUMEROTRAMITE = NUMEROTRAMITE;
        this.OBSERVACIONES = OBSERVACIONES;
        this.POLIZA = POLIZA;
        this.POLIZAReference = POLIZAReference;
        this.PORCCOMISIONAGENTE = PORCCOMISIONAGENTE;
        this.PREIMPRESOVERIFICADO = PREIMPRESOVERIFICADO;
        this.PRIMAMINVERIFICADA = PRIMAMINVERIFICADA;
        this.PRODUCTOAPEID = PRODUCTOAPEID;
        this.PUNTOVENTAAPEID = PUNTOVENTAAPEID;
        this.PUNTOVENTAID = PUNTOVENTAID;
        this.SISTEMAEMISOR = SISTEMAEMISOR;
        this.SISTEMAEMISORID = SISTEMAEMISORID;
        this.SUCURSALID = SUCURSALID;
        this.TARJETAID = TARJETAID;
        this.TASAMINIMA = TASAMINIMA;
        this.TIPOCANCELACION = TIPOCANCELACION;
        this.TIPOENDOSOID = TIPOENDOSOID;
        this.TIPOITEMID = TIPOITEMID;
        this.TIPOPAGOID = TIPOPAGOID;
        this.TIPOSEGUROID = TIPOSEGUROID;
        this.TITULARTARJETA = TITULARTARJETA;
        this.UNIDADNEGOCIOID = UNIDADNEGOCIOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.VALORASEGURADO = VALORASEGURADO;
        this.VALORCOMISION = VALORCOMISION;
        this.VALORPRIMANETA = VALORPRIMANETA;
        this.VALORRECARGOSC = VALORRECARGOSC;
        this.VIGENCIADESDE = VIGENCIADESDE;
        this.VIGENCIAHASTA = VIGENCIAHASTA;
    }


    /**
     * Gets the ANULAR value for this ENDOSO.
     * 
     * @return ANULAR
     */
    public java.lang.String getANULAR() {
        return ANULAR;
    }


    /**
     * Sets the ANULAR value for this ENDOSO.
     * 
     * @param ANULAR
     */
    public void setANULAR(java.lang.String ANULAR) {
        this.ANULAR = ANULAR;
    }


    /**
     * Gets the ASOCIACIONCB value for this ENDOSO.
     * 
     * @return ASOCIACIONCB
     */
    public java.lang.String getASOCIACIONCB() {
        return ASOCIACIONCB;
    }


    /**
     * Sets the ASOCIACIONCB value for this ENDOSO.
     * 
     * @param ASOCIACIONCB
     */
    public void setASOCIACIONCB(java.lang.String ASOCIACIONCB) {
        this.ASOCIACIONCB = ASOCIACIONCB;
    }


    /**
     * Gets the AUTORIZACIONAID value for this ENDOSO.
     * 
     * @return AUTORIZACIONAID
     */
    public java.lang.String getAUTORIZACIONAID() {
        return AUTORIZACIONAID;
    }


    /**
     * Sets the AUTORIZACIONAID value for this ENDOSO.
     * 
     * @param AUTORIZACIONAID
     */
    public void setAUTORIZACIONAID(java.lang.String AUTORIZACIONAID) {
        this.AUTORIZACIONAID = AUTORIZACIONAID;
    }


    /**
     * Gets the AUTORIZACIONBID value for this ENDOSO.
     * 
     * @return AUTORIZACIONBID
     */
    public java.lang.String getAUTORIZACIONBID() {
        return AUTORIZACIONBID;
    }


    /**
     * Sets the AUTORIZACIONBID value for this ENDOSO.
     * 
     * @param AUTORIZACIONBID
     */
    public void setAUTORIZACIONBID(java.lang.String AUTORIZACIONBID) {
        this.AUTORIZACIONBID = AUTORIZACIONBID;
    }


    /**
     * Gets the AUTORIZACIONBROKERPOLID value for this ENDOSO.
     * 
     * @return AUTORIZACIONBROKERPOLID
     */
    public java.lang.String getAUTORIZACIONBROKERPOLID() {
        return AUTORIZACIONBROKERPOLID;
    }


    /**
     * Sets the AUTORIZACIONBROKERPOLID value for this ENDOSO.
     * 
     * @param AUTORIZACIONBROKERPOLID
     */
    public void setAUTORIZACIONBROKERPOLID(java.lang.String AUTORIZACIONBROKERPOLID) {
        this.AUTORIZACIONBROKERPOLID = AUTORIZACIONBROKERPOLID;
    }


    /**
     * Gets the AUTORIZACIONCAMBIOCOMID value for this ENDOSO.
     * 
     * @return AUTORIZACIONCAMBIOCOMID
     */
    public java.lang.String getAUTORIZACIONCAMBIOCOMID() {
        return AUTORIZACIONCAMBIOCOMID;
    }


    /**
     * Sets the AUTORIZACIONCAMBIOCOMID value for this ENDOSO.
     * 
     * @param AUTORIZACIONCAMBIOCOMID
     */
    public void setAUTORIZACIONCAMBIOCOMID(java.lang.String AUTORIZACIONCAMBIOCOMID) {
        this.AUTORIZACIONCAMBIOCOMID = AUTORIZACIONCAMBIOCOMID;
    }


    /**
     * Gets the AUTORIZACIONCID value for this ENDOSO.
     * 
     * @return AUTORIZACIONCID
     */
    public java.lang.String getAUTORIZACIONCID() {
        return AUTORIZACIONCID;
    }


    /**
     * Sets the AUTORIZACIONCID value for this ENDOSO.
     * 
     * @param AUTORIZACIONCID
     */
    public void setAUTORIZACIONCID(java.lang.String AUTORIZACIONCID) {
        this.AUTORIZACIONCID = AUTORIZACIONCID;
    }


    /**
     * Gets the AUTORIZACIONDIASCONVENIOPAGO value for this ENDOSO.
     * 
     * @return AUTORIZACIONDIASCONVENIOPAGO
     */
    public java.lang.String getAUTORIZACIONDIASCONVENIOPAGO() {
        return AUTORIZACIONDIASCONVENIOPAGO;
    }


    /**
     * Sets the AUTORIZACIONDIASCONVENIOPAGO value for this ENDOSO.
     * 
     * @param AUTORIZACIONDIASCONVENIOPAGO
     */
    public void setAUTORIZACIONDIASCONVENIOPAGO(java.lang.String AUTORIZACIONDIASCONVENIOPAGO) {
        this.AUTORIZACIONDIASCONVENIOPAGO = AUTORIZACIONDIASCONVENIOPAGO;
    }


    /**
     * Gets the AUTORIZACIONFIRMAID value for this ENDOSO.
     * 
     * @return AUTORIZACIONFIRMAID
     */
    public java.lang.String getAUTORIZACIONFIRMAID() {
        return AUTORIZACIONFIRMAID;
    }


    /**
     * Sets the AUTORIZACIONFIRMAID value for this ENDOSO.
     * 
     * @param AUTORIZACIONFIRMAID
     */
    public void setAUTORIZACIONFIRMAID(java.lang.String AUTORIZACIONFIRMAID) {
        this.AUTORIZACIONFIRMAID = AUTORIZACIONFIRMAID;
    }


    /**
     * Gets the AUTORIZACIONID value for this ENDOSO.
     * 
     * @return AUTORIZACIONID
     */
    public java.lang.String getAUTORIZACIONID() {
        return AUTORIZACIONID;
    }


    /**
     * Sets the AUTORIZACIONID value for this ENDOSO.
     * 
     * @param AUTORIZACIONID
     */
    public void setAUTORIZACIONID(java.lang.String AUTORIZACIONID) {
        this.AUTORIZACIONID = AUTORIZACIONID;
    }


    /**
     * Gets the AUTORIZACIONLIMITEEMBARQUEID value for this ENDOSO.
     * 
     * @return AUTORIZACIONLIMITEEMBARQUEID
     */
    public java.lang.String getAUTORIZACIONLIMITEEMBARQUEID() {
        return AUTORIZACIONLIMITEEMBARQUEID;
    }


    /**
     * Sets the AUTORIZACIONLIMITEEMBARQUEID value for this ENDOSO.
     * 
     * @param AUTORIZACIONLIMITEEMBARQUEID
     */
    public void setAUTORIZACIONLIMITEEMBARQUEID(java.lang.String AUTORIZACIONLIMITEEMBARQUEID) {
        this.AUTORIZACIONLIMITEEMBARQUEID = AUTORIZACIONLIMITEEMBARQUEID;
    }


    /**
     * Gets the AUTORIZACIONMONTOFIANID value for this ENDOSO.
     * 
     * @return AUTORIZACIONMONTOFIANID
     */
    public java.lang.String getAUTORIZACIONMONTOFIANID() {
        return AUTORIZACIONMONTOFIANID;
    }


    /**
     * Sets the AUTORIZACIONMONTOFIANID value for this ENDOSO.
     * 
     * @param AUTORIZACIONMONTOFIANID
     */
    public void setAUTORIZACIONMONTOFIANID(java.lang.String AUTORIZACIONMONTOFIANID) {
        this.AUTORIZACIONMONTOFIANID = AUTORIZACIONMONTOFIANID;
    }


    /**
     * Gets the AUTORIZACIONNUEVOSOATID value for this ENDOSO.
     * 
     * @return AUTORIZACIONNUEVOSOATID
     */
    public java.lang.String getAUTORIZACIONNUEVOSOATID() {
        return AUTORIZACIONNUEVOSOATID;
    }


    /**
     * Sets the AUTORIZACIONNUEVOSOATID value for this ENDOSO.
     * 
     * @param AUTORIZACIONNUEVOSOATID
     */
    public void setAUTORIZACIONNUEVOSOATID(java.lang.String AUTORIZACIONNUEVOSOATID) {
        this.AUTORIZACIONNUEVOSOATID = AUTORIZACIONNUEVOSOATID;
    }


    /**
     * Gets the AUTORIZACIONPERSONAID value for this ENDOSO.
     * 
     * @return AUTORIZACIONPERSONAID
     */
    public java.lang.String getAUTORIZACIONPERSONAID() {
        return AUTORIZACIONPERSONAID;
    }


    /**
     * Sets the AUTORIZACIONPERSONAID value for this ENDOSO.
     * 
     * @param AUTORIZACIONPERSONAID
     */
    public void setAUTORIZACIONPERSONAID(java.lang.String AUTORIZACIONPERSONAID) {
        this.AUTORIZACIONPERSONAID = AUTORIZACIONPERSONAID;
    }


    /**
     * Gets the AUTORIZACIONPRID value for this ENDOSO.
     * 
     * @return AUTORIZACIONPRID
     */
    public java.lang.String getAUTORIZACIONPRID() {
        return AUTORIZACIONPRID;
    }


    /**
     * Sets the AUTORIZACIONPRID value for this ENDOSO.
     * 
     * @param AUTORIZACIONPRID
     */
    public void setAUTORIZACIONPRID(java.lang.String AUTORIZACIONPRID) {
        this.AUTORIZACIONPRID = AUTORIZACIONPRID;
    }


    /**
     * Gets the AUTORIZACIONPRIMAMINID value for this ENDOSO.
     * 
     * @return AUTORIZACIONPRIMAMINID
     */
    public java.lang.String getAUTORIZACIONPRIMAMINID() {
        return AUTORIZACIONPRIMAMINID;
    }


    /**
     * Sets the AUTORIZACIONPRIMAMINID value for this ENDOSO.
     * 
     * @param AUTORIZACIONPRIMAMINID
     */
    public void setAUTORIZACIONPRIMAMINID(java.lang.String AUTORIZACIONPRIMAMINID) {
        this.AUTORIZACIONPRIMAMINID = AUTORIZACIONPRIMAMINID;
    }


    /**
     * Gets the AUTORIZACIONVIGENCIAID value for this ENDOSO.
     * 
     * @return AUTORIZACIONVIGENCIAID
     */
    public java.lang.String getAUTORIZACIONVIGENCIAID() {
        return AUTORIZACIONVIGENCIAID;
    }


    /**
     * Sets the AUTORIZACIONVIGENCIAID value for this ENDOSO.
     * 
     * @param AUTORIZACIONVIGENCIAID
     */
    public void setAUTORIZACIONVIGENCIAID(java.lang.String AUTORIZACIONVIGENCIAID) {
        this.AUTORIZACIONVIGENCIAID = AUTORIZACIONVIGENCIAID;
    }


    /**
     * Gets the AUTORIZACIONZFID value for this ENDOSO.
     * 
     * @return AUTORIZACIONZFID
     */
    public java.lang.String getAUTORIZACIONZFID() {
        return AUTORIZACIONZFID;
    }


    /**
     * Sets the AUTORIZACIONZFID value for this ENDOSO.
     * 
     * @param AUTORIZACIONZFID
     */
    public void setAUTORIZACIONZFID(java.lang.String AUTORIZACIONZFID) {
        this.AUTORIZACIONZFID = AUTORIZACIONZFID;
    }


    /**
     * Gets the AUTORIZAFIANZASID value for this ENDOSO.
     * 
     * @return AUTORIZAFIANZASID
     */
    public java.lang.String getAUTORIZAFIANZASID() {
        return AUTORIZAFIANZASID;
    }


    /**
     * Sets the AUTORIZAFIANZASID value for this ENDOSO.
     * 
     * @param AUTORIZAFIANZASID
     */
    public void setAUTORIZAFIANZASID(java.lang.String AUTORIZAFIANZASID) {
        this.AUTORIZAFIANZASID = AUTORIZAFIANZASID;
    }


    /**
     * Gets the CAJAID value for this ENDOSO.
     * 
     * @return CAJAID
     */
    public java.lang.String getCAJAID() {
        return CAJAID;
    }


    /**
     * Sets the CAJAID value for this ENDOSO.
     * 
     * @param CAJAID
     */
    public void setCAJAID(java.lang.String CAJAID) {
        this.CAJAID = CAJAID;
    }


    /**
     * Gets the CLIENTE value for this ENDOSO.
     * 
     * @return CLIENTE
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE getCLIENTE() {
        return CLIENTE;
    }


    /**
     * Sets the CLIENTE value for this ENDOSO.
     * 
     * @param CLIENTE
     */
    public void setCLIENTE(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE CLIENTE) {
        this.CLIENTE = CLIENTE;
    }


    /**
     * Gets the CLIENTEReference value for this ENDOSO.
     * 
     * @return CLIENTEReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 getCLIENTEReference() {
        return CLIENTEReference;
    }


    /**
     * Sets the CLIENTEReference value for this ENDOSO.
     * 
     * @param CLIENTEReference
     */
    public void setCLIENTEReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7 CLIENTEReference) {
        this.CLIENTEReference = CLIENTEReference;
    }


    /**
     * Gets the COMISIONBROKER value for this ENDOSO.
     * 
     * @return COMISIONBROKER
     */
    public java.math.BigDecimal getCOMISIONBROKER() {
        return COMISIONBROKER;
    }


    /**
     * Sets the COMISIONBROKER value for this ENDOSO.
     * 
     * @param COMISIONBROKER
     */
    public void setCOMISIONBROKER(java.math.BigDecimal COMISIONBROKER) {
        this.COMISIONBROKER = COMISIONBROKER;
    }


    /**
     * Gets the COMISIONMANTENIMIENTO value for this ENDOSO.
     * 
     * @return COMISIONMANTENIMIENTO
     */
    public java.math.BigDecimal getCOMISIONMANTENIMIENTO() {
        return COMISIONMANTENIMIENTO;
    }


    /**
     * Sets the COMISIONMANTENIMIENTO value for this ENDOSO.
     * 
     * @param COMISIONMANTENIMIENTO
     */
    public void setCOMISIONMANTENIMIENTO(java.math.BigDecimal COMISIONMANTENIMIENTO) {
        this.COMISIONMANTENIMIENTO = COMISIONMANTENIMIENTO;
    }


    /**
     * Gets the CONVENIOPAGOID value for this ENDOSO.
     * 
     * @return CONVENIOPAGOID
     */
    public java.lang.String getCONVENIOPAGOID() {
        return CONVENIOPAGOID;
    }


    /**
     * Sets the CONVENIOPAGOID value for this ENDOSO.
     * 
     * @param CONVENIOPAGOID
     */
    public void setCONVENIOPAGOID(java.lang.String CONVENIOPAGOID) {
        this.CONVENIOPAGOID = CONVENIOPAGOID;
    }


    /**
     * Gets the DERECHOSEMISION value for this ENDOSO.
     * 
     * @return DERECHOSEMISION
     */
    public java.lang.String getDERECHOSEMISION() {
        return DERECHOSEMISION;
    }


    /**
     * Sets the DERECHOSEMISION value for this ENDOSO.
     * 
     * @param DERECHOSEMISION
     */
    public void setDERECHOSEMISION(java.lang.String DERECHOSEMISION) {
        this.DERECHOSEMISION = DERECHOSEMISION;
    }


    /**
     * Gets the DESCRIPCION value for this ENDOSO.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this ENDOSO.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the DIASAVISOSINIESTRO value for this ENDOSO.
     * 
     * @return DIASAVISOSINIESTRO
     */
    public java.math.BigDecimal getDIASAVISOSINIESTRO() {
        return DIASAVISOSINIESTRO;
    }


    /**
     * Sets the DIASAVISOSINIESTRO value for this ENDOSO.
     * 
     * @param DIASAVISOSINIESTRO
     */
    public void setDIASAVISOSINIESTRO(java.math.BigDecimal DIASAVISOSINIESTRO) {
        this.DIASAVISOSINIESTRO = DIASAVISOSINIESTRO;
    }


    /**
     * Gets the DIASCONVENIOPAGO value for this ENDOSO.
     * 
     * @return DIASCONVENIOPAGO
     */
    public java.math.BigDecimal getDIASCONVENIOPAGO() {
        return DIASCONVENIOPAGO;
    }


    /**
     * Sets the DIASCONVENIOPAGO value for this ENDOSO.
     * 
     * @param DIASCONVENIOPAGO
     */
    public void setDIASCONVENIOPAGO(java.math.BigDecimal DIASCONVENIOPAGO) {
        this.DIASCONVENIOPAGO = DIASCONVENIOPAGO;
    }


    /**
     * Gets the ENDOSOITEM value for this ENDOSO.
     * 
     * @return ENDOSOITEM
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM[] getENDOSOITEM() {
        return ENDOSOITEM;
    }


    /**
     * Sets the ENDOSOITEM value for this ENDOSO.
     * 
     * @param ENDOSOITEM
     */
    public void setENDOSOITEM(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM[] ENDOSOITEM) {
        this.ENDOSOITEM = ENDOSOITEM;
    }


    /**
     * Gets the ENDOSOREF value for this ENDOSO.
     * 
     * @return ENDOSOREF
     */
    public java.lang.String getENDOSOREF() {
        return ENDOSOREF;
    }


    /**
     * Sets the ENDOSOREF value for this ENDOSO.
     * 
     * @param ENDOSOREF
     */
    public void setENDOSOREF(java.lang.String ENDOSOREF) {
        this.ENDOSOREF = ENDOSOREF;
    }


    /**
     * Gets the ENTIDADAGENTEID value for this ENDOSO.
     * 
     * @return ENTIDADAGENTEID
     */
    public java.lang.String getENTIDADAGENTEID() {
        return ENTIDADAGENTEID;
    }


    /**
     * Sets the ENTIDADAGENTEID value for this ENDOSO.
     * 
     * @param ENTIDADAGENTEID
     */
    public void setENTIDADAGENTEID(java.lang.String ENTIDADAGENTEID) {
        this.ENTIDADAGENTEID = ENTIDADAGENTEID;
    }


    /**
     * Gets the ESAJUSTEVIGENCIA value for this ENDOSO.
     * 
     * @return ESAJUSTEVIGENCIA
     */
    public java.lang.String getESAJUSTEVIGENCIA() {
        return ESAJUSTEVIGENCIA;
    }


    /**
     * Sets the ESAJUSTEVIGENCIA value for this ENDOSO.
     * 
     * @param ESAJUSTEVIGENCIA
     */
    public void setESAJUSTEVIGENCIA(java.lang.String ESAJUSTEVIGENCIA) {
        this.ESAJUSTEVIGENCIA = ESAJUSTEVIGENCIA;
    }


    /**
     * Gets the ESAUTOMATICO value for this ENDOSO.
     * 
     * @return ESAUTOMATICO
     */
    public java.lang.String getESAUTOMATICO() {
        return ESAUTOMATICO;
    }


    /**
     * Sets the ESAUTOMATICO value for this ENDOSO.
     * 
     * @param ESAUTOMATICO
     */
    public void setESAUTOMATICO(java.lang.String ESAUTOMATICO) {
        this.ESAUTOMATICO = ESAUTOMATICO;
    }


    /**
     * Gets the ESCANCELACION value for this ENDOSO.
     * 
     * @return ESCANCELACION
     */
    public java.lang.String getESCANCELACION() {
        return ESCANCELACION;
    }


    /**
     * Sets the ESCANCELACION value for this ENDOSO.
     * 
     * @param ESCANCELACION
     */
    public void setESCANCELACION(java.lang.String ESCANCELACION) {
        this.ESCANCELACION = ESCANCELACION;
    }


    /**
     * Gets the ESDATOOTROSISTEMA value for this ENDOSO.
     * 
     * @return ESDATOOTROSISTEMA
     */
    public java.lang.String getESDATOOTROSISTEMA() {
        return ESDATOOTROSISTEMA;
    }


    /**
     * Sets the ESDATOOTROSISTEMA value for this ENDOSO.
     * 
     * @param ESDATOOTROSISTEMA
     */
    public void setESDATOOTROSISTEMA(java.lang.String ESDATOOTROSISTEMA) {
        this.ESDATOOTROSISTEMA = ESDATOOTROSISTEMA;
    }


    /**
     * Gets the ESDEPOLIZAMADRE value for this ENDOSO.
     * 
     * @return ESDEPOLIZAMADRE
     */
    public java.lang.String getESDEPOLIZAMADRE() {
        return ESDEPOLIZAMADRE;
    }


    /**
     * Sets the ESDEPOLIZAMADRE value for this ENDOSO.
     * 
     * @param ESDEPOLIZAMADRE
     */
    public void setESDEPOLIZAMADRE(java.lang.String ESDEPOLIZAMADRE) {
        this.ESDEPOLIZAMADRE = ESDEPOLIZAMADRE;
    }


    /**
     * Gets the ESFACTURADOOTROSISTEMA value for this ENDOSO.
     * 
     * @return ESFACTURADOOTROSISTEMA
     */
    public java.lang.String getESFACTURADOOTROSISTEMA() {
        return ESFACTURADOOTROSISTEMA;
    }


    /**
     * Sets the ESFACTURADOOTROSISTEMA value for this ENDOSO.
     * 
     * @param ESFACTURADOOTROSISTEMA
     */
    public void setESFACTURADOOTROSISTEMA(java.lang.String ESFACTURADOOTROSISTEMA) {
        this.ESFACTURADOOTROSISTEMA = ESFACTURADOOTROSISTEMA;
    }


    /**
     * Gets the ESVERIFICADO value for this ENDOSO.
     * 
     * @return ESVERIFICADO
     */
    public java.lang.String getESVERIFICADO() {
        return ESVERIFICADO;
    }


    /**
     * Sets the ESVERIFICADO value for this ENDOSO.
     * 
     * @param ESVERIFICADO
     */
    public void setESVERIFICADO(java.lang.String ESVERIFICADO) {
        this.ESVERIFICADO = ESVERIFICADO;
    }


    /**
     * Gets the EXCENTOIVA value for this ENDOSO.
     * 
     * @return EXCENTOIVA
     */
    public java.lang.String getEXCENTOIVA() {
        return EXCENTOIVA;
    }


    /**
     * Sets the EXCENTOIVA value for this ENDOSO.
     * 
     * @param EXCENTOIVA
     */
    public void setEXCENTOIVA(java.lang.String EXCENTOIVA) {
        this.EXCENTOIVA = EXCENTOIVA;
    }


    /**
     * Gets the FECFINCREDITO value for this ENDOSO.
     * 
     * @return FECFINCREDITO
     */
    public java.util.Calendar getFECFINCREDITO() {
        return FECFINCREDITO;
    }


    /**
     * Sets the FECFINCREDITO value for this ENDOSO.
     * 
     * @param FECFINCREDITO
     */
    public void setFECFINCREDITO(java.util.Calendar FECFINCREDITO) {
        this.FECFINCREDITO = FECFINCREDITO;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ENDOSO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ENDOSO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHAANULACION value for this ENDOSO.
     * 
     * @return FECHAANULACION
     */
    public java.util.Calendar getFECHAANULACION() {
        return FECHAANULACION;
    }


    /**
     * Sets the FECHAANULACION value for this ENDOSO.
     * 
     * @param FECHAANULACION
     */
    public void setFECHAANULACION(java.util.Calendar FECHAANULACION) {
        this.FECHAANULACION = FECHAANULACION;
    }


    /**
     * Gets the FECHACOTIZACION value for this ENDOSO.
     * 
     * @return FECHACOTIZACION
     */
    public java.util.Calendar getFECHACOTIZACION() {
        return FECHACOTIZACION;
    }


    /**
     * Sets the FECHACOTIZACION value for this ENDOSO.
     * 
     * @param FECHACOTIZACION
     */
    public void setFECHACOTIZACION(java.util.Calendar FECHACOTIZACION) {
        this.FECHACOTIZACION = FECHACOTIZACION;
    }


    /**
     * Gets the FECHAELABORACION value for this ENDOSO.
     * 
     * @return FECHAELABORACION
     */
    public java.util.Calendar getFECHAELABORACION() {
        return FECHAELABORACION;
    }


    /**
     * Sets the FECHAELABORACION value for this ENDOSO.
     * 
     * @param FECHAELABORACION
     */
    public void setFECHAELABORACION(java.util.Calendar FECHAELABORACION) {
        this.FECHAELABORACION = FECHAELABORACION;
    }


    /**
     * Gets the FECHAVENCIMIENTO value for this ENDOSO.
     * 
     * @return FECHAVENCIMIENTO
     */
    public java.util.Calendar getFECHAVENCIMIENTO() {
        return FECHAVENCIMIENTO;
    }


    /**
     * Sets the FECHAVENCIMIENTO value for this ENDOSO.
     * 
     * @param FECHAVENCIMIENTO
     */
    public void setFECHAVENCIMIENTO(java.util.Calendar FECHAVENCIMIENTO) {
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
    }


    /**
     * Gets the FIRMASUCURSALID value for this ENDOSO.
     * 
     * @return FIRMASUCURSALID
     */
    public java.lang.String getFIRMASUCURSALID() {
        return FIRMASUCURSALID;
    }


    /**
     * Sets the FIRMASUCURSALID value for this ENDOSO.
     * 
     * @param FIRMASUCURSALID
     */
    public void setFIRMASUCURSALID(java.lang.String FIRMASUCURSALID) {
        this.FIRMASUCURSALID = FIRMASUCURSALID;
    }


    /**
     * Gets the FORMAPAGO value for this ENDOSO.
     * 
     * @return FORMAPAGO
     */
    public java.lang.String getFORMAPAGO() {
        return FORMAPAGO;
    }


    /**
     * Sets the FORMAPAGO value for this ENDOSO.
     * 
     * @param FORMAPAGO
     */
    public void setFORMAPAGO(java.lang.String FORMAPAGO) {
        this.FORMAPAGO = FORMAPAGO;
    }


    /**
     * Gets the ID value for this ENDOSO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ENDOSO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the IMPRIMEORIGINAL value for this ENDOSO.
     * 
     * @return IMPRIMEORIGINAL
     */
    public java.lang.String getIMPRIMEORIGINAL() {
        return IMPRIMEORIGINAL;
    }


    /**
     * Sets the IMPRIMEORIGINAL value for this ENDOSO.
     * 
     * @param IMPRIMEORIGINAL
     */
    public void setIMPRIMEORIGINAL(java.lang.String IMPRIMEORIGINAL) {
        this.IMPRIMEORIGINAL = IMPRIMEORIGINAL;
    }


    /**
     * Gets the LIMITEINDEMNIZACION value for this ENDOSO.
     * 
     * @return LIMITEINDEMNIZACION
     */
    public java.math.BigDecimal getLIMITEINDEMNIZACION() {
        return LIMITEINDEMNIZACION;
    }


    /**
     * Sets the LIMITEINDEMNIZACION value for this ENDOSO.
     * 
     * @param LIMITEINDEMNIZACION
     */
    public void setLIMITEINDEMNIZACION(java.math.BigDecimal LIMITEINDEMNIZACION) {
        this.LIMITEINDEMNIZACION = LIMITEINDEMNIZACION;
    }


    /**
     * Gets the MOTIVOCANCELACION value for this ENDOSO.
     * 
     * @return MOTIVOCANCELACION
     */
    public java.lang.String getMOTIVOCANCELACION() {
        return MOTIVOCANCELACION;
    }


    /**
     * Sets the MOTIVOCANCELACION value for this ENDOSO.
     * 
     * @param MOTIVOCANCELACION
     */
    public void setMOTIVOCANCELACION(java.lang.String MOTIVOCANCELACION) {
        this.MOTIVOCANCELACION = MOTIVOCANCELACION;
    }


    /**
     * Gets the NOMBRETARJETACREDITOID value for this ENDOSO.
     * 
     * @return NOMBRETARJETACREDITOID
     */
    public java.lang.String getNOMBRETARJETACREDITOID() {
        return NOMBRETARJETACREDITOID;
    }


    /**
     * Sets the NOMBRETARJETACREDITOID value for this ENDOSO.
     * 
     * @param NOMBRETARJETACREDITOID
     */
    public void setNOMBRETARJETACREDITOID(java.lang.String NOMBRETARJETACREDITOID) {
        this.NOMBRETARJETACREDITOID = NOMBRETARJETACREDITOID;
    }


    /**
     * Gets the NOTAS value for this ENDOSO.
     * 
     * @return NOTAS
     */
    public java.lang.String getNOTAS() {
        return NOTAS;
    }


    /**
     * Sets the NOTAS value for this ENDOSO.
     * 
     * @param NOTAS
     */
    public void setNOTAS(java.lang.String NOTAS) {
        this.NOTAS = NOTAS;
    }


    /**
     * Gets the NUMERO value for this ENDOSO.
     * 
     * @return NUMERO
     */
    public java.math.BigDecimal getNUMERO() {
        return NUMERO;
    }


    /**
     * Sets the NUMERO value for this ENDOSO.
     * 
     * @param NUMERO
     */
    public void setNUMERO(java.math.BigDecimal NUMERO) {
        this.NUMERO = NUMERO;
    }


    /**
     * Gets the NUMEROAUTORIZACION value for this ENDOSO.
     * 
     * @return NUMEROAUTORIZACION
     */
    public java.lang.String getNUMEROAUTORIZACION() {
        return NUMEROAUTORIZACION;
    }


    /**
     * Sets the NUMEROAUTORIZACION value for this ENDOSO.
     * 
     * @param NUMEROAUTORIZACION
     */
    public void setNUMEROAUTORIZACION(java.lang.String NUMEROAUTORIZACION) {
        this.NUMEROAUTORIZACION = NUMEROAUTORIZACION;
    }


    /**
     * Gets the NUMEROCERTIFICADO value for this ENDOSO.
     * 
     * @return NUMEROCERTIFICADO
     */
    public java.lang.String getNUMEROCERTIFICADO() {
        return NUMEROCERTIFICADO;
    }


    /**
     * Sets the NUMEROCERTIFICADO value for this ENDOSO.
     * 
     * @param NUMEROCERTIFICADO
     */
    public void setNUMEROCERTIFICADO(java.lang.String NUMEROCERTIFICADO) {
        this.NUMEROCERTIFICADO = NUMEROCERTIFICADO;
    }


    /**
     * Gets the NUMEROTARIFA value for this ENDOSO.
     * 
     * @return NUMEROTARIFA
     */
    public java.lang.String getNUMEROTARIFA() {
        return NUMEROTARIFA;
    }


    /**
     * Sets the NUMEROTARIFA value for this ENDOSO.
     * 
     * @param NUMEROTARIFA
     */
    public void setNUMEROTARIFA(java.lang.String NUMEROTARIFA) {
        this.NUMEROTARIFA = NUMEROTARIFA;
    }


    /**
     * Gets the NUMEROTARJETA value for this ENDOSO.
     * 
     * @return NUMEROTARJETA
     */
    public java.lang.Long getNUMEROTARJETA() {
        return NUMEROTARJETA;
    }


    /**
     * Sets the NUMEROTARJETA value for this ENDOSO.
     * 
     * @param NUMEROTARJETA
     */
    public void setNUMEROTARJETA(java.lang.Long NUMEROTARJETA) {
        this.NUMEROTARJETA = NUMEROTARJETA;
    }


    /**
     * Gets the NUMEROTRAMITE value for this ENDOSO.
     * 
     * @return NUMEROTRAMITE
     */
    public java.math.BigDecimal getNUMEROTRAMITE() {
        return NUMEROTRAMITE;
    }


    /**
     * Sets the NUMEROTRAMITE value for this ENDOSO.
     * 
     * @param NUMEROTRAMITE
     */
    public void setNUMEROTRAMITE(java.math.BigDecimal NUMEROTRAMITE) {
        this.NUMEROTRAMITE = NUMEROTRAMITE;
    }


    /**
     * Gets the OBSERVACIONES value for this ENDOSO.
     * 
     * @return OBSERVACIONES
     */
    public java.lang.String getOBSERVACIONES() {
        return OBSERVACIONES;
    }


    /**
     * Sets the OBSERVACIONES value for this ENDOSO.
     * 
     * @param OBSERVACIONES
     */
    public void setOBSERVACIONES(java.lang.String OBSERVACIONES) {
        this.OBSERVACIONES = OBSERVACIONES;
    }


    /**
     * Gets the POLIZA value for this ENDOSO.
     * 
     * @return POLIZA
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA getPOLIZA() {
        return POLIZA;
    }


    /**
     * Sets the POLIZA value for this ENDOSO.
     * 
     * @param POLIZA
     */
    public void setPOLIZA(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA POLIZA) {
        this.POLIZA = POLIZA;
    }


    /**
     * Gets the POLIZAReference value for this ENDOSO.
     * 
     * @return POLIZAReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPOLIZAFG7C7FF7 getPOLIZAReference() {
        return POLIZAReference;
    }


    /**
     * Sets the POLIZAReference value for this ENDOSO.
     * 
     * @param POLIZAReference
     */
    public void setPOLIZAReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPOLIZAFG7C7FF7 POLIZAReference) {
        this.POLIZAReference = POLIZAReference;
    }


    /**
     * Gets the PORCCOMISIONAGENTE value for this ENDOSO.
     * 
     * @return PORCCOMISIONAGENTE
     */
    public java.math.BigDecimal getPORCCOMISIONAGENTE() {
        return PORCCOMISIONAGENTE;
    }


    /**
     * Sets the PORCCOMISIONAGENTE value for this ENDOSO.
     * 
     * @param PORCCOMISIONAGENTE
     */
    public void setPORCCOMISIONAGENTE(java.math.BigDecimal PORCCOMISIONAGENTE) {
        this.PORCCOMISIONAGENTE = PORCCOMISIONAGENTE;
    }


    /**
     * Gets the PREIMPRESOVERIFICADO value for this ENDOSO.
     * 
     * @return PREIMPRESOVERIFICADO
     */
    public java.lang.String getPREIMPRESOVERIFICADO() {
        return PREIMPRESOVERIFICADO;
    }


    /**
     * Sets the PREIMPRESOVERIFICADO value for this ENDOSO.
     * 
     * @param PREIMPRESOVERIFICADO
     */
    public void setPREIMPRESOVERIFICADO(java.lang.String PREIMPRESOVERIFICADO) {
        this.PREIMPRESOVERIFICADO = PREIMPRESOVERIFICADO;
    }


    /**
     * Gets the PRIMAMINVERIFICADA value for this ENDOSO.
     * 
     * @return PRIMAMINVERIFICADA
     */
    public java.lang.String getPRIMAMINVERIFICADA() {
        return PRIMAMINVERIFICADA;
    }


    /**
     * Sets the PRIMAMINVERIFICADA value for this ENDOSO.
     * 
     * @param PRIMAMINVERIFICADA
     */
    public void setPRIMAMINVERIFICADA(java.lang.String PRIMAMINVERIFICADA) {
        this.PRIMAMINVERIFICADA = PRIMAMINVERIFICADA;
    }


    /**
     * Gets the PRODUCTOAPEID value for this ENDOSO.
     * 
     * @return PRODUCTOAPEID
     */
    public java.lang.String getPRODUCTOAPEID() {
        return PRODUCTOAPEID;
    }


    /**
     * Sets the PRODUCTOAPEID value for this ENDOSO.
     * 
     * @param PRODUCTOAPEID
     */
    public void setPRODUCTOAPEID(java.lang.String PRODUCTOAPEID) {
        this.PRODUCTOAPEID = PRODUCTOAPEID;
    }


    /**
     * Gets the PUNTOVENTAAPEID value for this ENDOSO.
     * 
     * @return PUNTOVENTAAPEID
     */
    public java.lang.String getPUNTOVENTAAPEID() {
        return PUNTOVENTAAPEID;
    }


    /**
     * Sets the PUNTOVENTAAPEID value for this ENDOSO.
     * 
     * @param PUNTOVENTAAPEID
     */
    public void setPUNTOVENTAAPEID(java.lang.String PUNTOVENTAAPEID) {
        this.PUNTOVENTAAPEID = PUNTOVENTAAPEID;
    }


    /**
     * Gets the PUNTOVENTAID value for this ENDOSO.
     * 
     * @return PUNTOVENTAID
     */
    public java.lang.String getPUNTOVENTAID() {
        return PUNTOVENTAID;
    }


    /**
     * Sets the PUNTOVENTAID value for this ENDOSO.
     * 
     * @param PUNTOVENTAID
     */
    public void setPUNTOVENTAID(java.lang.String PUNTOVENTAID) {
        this.PUNTOVENTAID = PUNTOVENTAID;
    }


    /**
     * Gets the SISTEMAEMISOR value for this ENDOSO.
     * 
     * @return SISTEMAEMISOR
     */
    public java.lang.String getSISTEMAEMISOR() {
        return SISTEMAEMISOR;
    }


    /**
     * Sets the SISTEMAEMISOR value for this ENDOSO.
     * 
     * @param SISTEMAEMISOR
     */
    public void setSISTEMAEMISOR(java.lang.String SISTEMAEMISOR) {
        this.SISTEMAEMISOR = SISTEMAEMISOR;
    }


    /**
     * Gets the SISTEMAEMISORID value for this ENDOSO.
     * 
     * @return SISTEMAEMISORID
     */
    public java.lang.String getSISTEMAEMISORID() {
        return SISTEMAEMISORID;
    }


    /**
     * Sets the SISTEMAEMISORID value for this ENDOSO.
     * 
     * @param SISTEMAEMISORID
     */
    public void setSISTEMAEMISORID(java.lang.String SISTEMAEMISORID) {
        this.SISTEMAEMISORID = SISTEMAEMISORID;
    }


    /**
     * Gets the SUCURSALID value for this ENDOSO.
     * 
     * @return SUCURSALID
     */
    public java.lang.String getSUCURSALID() {
        return SUCURSALID;
    }


    /**
     * Sets the SUCURSALID value for this ENDOSO.
     * 
     * @param SUCURSALID
     */
    public void setSUCURSALID(java.lang.String SUCURSALID) {
        this.SUCURSALID = SUCURSALID;
    }


    /**
     * Gets the TARJETAID value for this ENDOSO.
     * 
     * @return TARJETAID
     */
    public java.lang.String getTARJETAID() {
        return TARJETAID;
    }


    /**
     * Sets the TARJETAID value for this ENDOSO.
     * 
     * @param TARJETAID
     */
    public void setTARJETAID(java.lang.String TARJETAID) {
        this.TARJETAID = TARJETAID;
    }


    /**
     * Gets the TASAMINIMA value for this ENDOSO.
     * 
     * @return TASAMINIMA
     */
    public java.math.BigDecimal getTASAMINIMA() {
        return TASAMINIMA;
    }


    /**
     * Sets the TASAMINIMA value for this ENDOSO.
     * 
     * @param TASAMINIMA
     */
    public void setTASAMINIMA(java.math.BigDecimal TASAMINIMA) {
        this.TASAMINIMA = TASAMINIMA;
    }


    /**
     * Gets the TIPOCANCELACION value for this ENDOSO.
     * 
     * @return TIPOCANCELACION
     */
    public java.lang.String getTIPOCANCELACION() {
        return TIPOCANCELACION;
    }


    /**
     * Sets the TIPOCANCELACION value for this ENDOSO.
     * 
     * @param TIPOCANCELACION
     */
    public void setTIPOCANCELACION(java.lang.String TIPOCANCELACION) {
        this.TIPOCANCELACION = TIPOCANCELACION;
    }


    /**
     * Gets the TIPOENDOSOID value for this ENDOSO.
     * 
     * @return TIPOENDOSOID
     */
    public java.lang.String getTIPOENDOSOID() {
        return TIPOENDOSOID;
    }


    /**
     * Sets the TIPOENDOSOID value for this ENDOSO.
     * 
     * @param TIPOENDOSOID
     */
    public void setTIPOENDOSOID(java.lang.String TIPOENDOSOID) {
        this.TIPOENDOSOID = TIPOENDOSOID;
    }


    /**
     * Gets the TIPOITEMID value for this ENDOSO.
     * 
     * @return TIPOITEMID
     */
    public java.lang.String getTIPOITEMID() {
        return TIPOITEMID;
    }


    /**
     * Sets the TIPOITEMID value for this ENDOSO.
     * 
     * @param TIPOITEMID
     */
    public void setTIPOITEMID(java.lang.String TIPOITEMID) {
        this.TIPOITEMID = TIPOITEMID;
    }


    /**
     * Gets the TIPOPAGOID value for this ENDOSO.
     * 
     * @return TIPOPAGOID
     */
    public java.lang.String getTIPOPAGOID() {
        return TIPOPAGOID;
    }


    /**
     * Sets the TIPOPAGOID value for this ENDOSO.
     * 
     * @param TIPOPAGOID
     */
    public void setTIPOPAGOID(java.lang.String TIPOPAGOID) {
        this.TIPOPAGOID = TIPOPAGOID;
    }


    /**
     * Gets the TIPOSEGUROID value for this ENDOSO.
     * 
     * @return TIPOSEGUROID
     */
    public java.lang.String getTIPOSEGUROID() {
        return TIPOSEGUROID;
    }


    /**
     * Sets the TIPOSEGUROID value for this ENDOSO.
     * 
     * @param TIPOSEGUROID
     */
    public void setTIPOSEGUROID(java.lang.String TIPOSEGUROID) {
        this.TIPOSEGUROID = TIPOSEGUROID;
    }


    /**
     * Gets the TITULARTARJETA value for this ENDOSO.
     * 
     * @return TITULARTARJETA
     */
    public java.lang.String getTITULARTARJETA() {
        return TITULARTARJETA;
    }


    /**
     * Sets the TITULARTARJETA value for this ENDOSO.
     * 
     * @param TITULARTARJETA
     */
    public void setTITULARTARJETA(java.lang.String TITULARTARJETA) {
        this.TITULARTARJETA = TITULARTARJETA;
    }


    /**
     * Gets the UNIDADNEGOCIOID value for this ENDOSO.
     * 
     * @return UNIDADNEGOCIOID
     */
    public java.lang.String getUNIDADNEGOCIOID() {
        return UNIDADNEGOCIOID;
    }


    /**
     * Sets the UNIDADNEGOCIOID value for this ENDOSO.
     * 
     * @param UNIDADNEGOCIOID
     */
    public void setUNIDADNEGOCIOID(java.lang.String UNIDADNEGOCIOID) {
        this.UNIDADNEGOCIOID = UNIDADNEGOCIOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ENDOSO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ENDOSO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the VALORASEGURADO value for this ENDOSO.
     * 
     * @return VALORASEGURADO
     */
    public java.math.BigDecimal getVALORASEGURADO() {
        return VALORASEGURADO;
    }


    /**
     * Sets the VALORASEGURADO value for this ENDOSO.
     * 
     * @param VALORASEGURADO
     */
    public void setVALORASEGURADO(java.math.BigDecimal VALORASEGURADO) {
        this.VALORASEGURADO = VALORASEGURADO;
    }


    /**
     * Gets the VALORCOMISION value for this ENDOSO.
     * 
     * @return VALORCOMISION
     */
    public java.math.BigDecimal getVALORCOMISION() {
        return VALORCOMISION;
    }


    /**
     * Sets the VALORCOMISION value for this ENDOSO.
     * 
     * @param VALORCOMISION
     */
    public void setVALORCOMISION(java.math.BigDecimal VALORCOMISION) {
        this.VALORCOMISION = VALORCOMISION;
    }


    /**
     * Gets the VALORPRIMANETA value for this ENDOSO.
     * 
     * @return VALORPRIMANETA
     */
    public java.math.BigDecimal getVALORPRIMANETA() {
        return VALORPRIMANETA;
    }


    /**
     * Sets the VALORPRIMANETA value for this ENDOSO.
     * 
     * @param VALORPRIMANETA
     */
    public void setVALORPRIMANETA(java.math.BigDecimal VALORPRIMANETA) {
        this.VALORPRIMANETA = VALORPRIMANETA;
    }


    /**
     * Gets the VALORRECARGOSC value for this ENDOSO.
     * 
     * @return VALORRECARGOSC
     */
    public java.math.BigDecimal getVALORRECARGOSC() {
        return VALORRECARGOSC;
    }


    /**
     * Sets the VALORRECARGOSC value for this ENDOSO.
     * 
     * @param VALORRECARGOSC
     */
    public void setVALORRECARGOSC(java.math.BigDecimal VALORRECARGOSC) {
        this.VALORRECARGOSC = VALORRECARGOSC;
    }


    /**
     * Gets the VIGENCIADESDE value for this ENDOSO.
     * 
     * @return VIGENCIADESDE
     */
    public java.util.Calendar getVIGENCIADESDE() {
        return VIGENCIADESDE;
    }


    /**
     * Sets the VIGENCIADESDE value for this ENDOSO.
     * 
     * @param VIGENCIADESDE
     */
    public void setVIGENCIADESDE(java.util.Calendar VIGENCIADESDE) {
        this.VIGENCIADESDE = VIGENCIADESDE;
    }


    /**
     * Gets the VIGENCIAHASTA value for this ENDOSO.
     * 
     * @return VIGENCIAHASTA
     */
    public java.util.Calendar getVIGENCIAHASTA() {
        return VIGENCIAHASTA;
    }


    /**
     * Sets the VIGENCIAHASTA value for this ENDOSO.
     * 
     * @param VIGENCIAHASTA
     */
    public void setVIGENCIAHASTA(java.util.Calendar VIGENCIAHASTA) {
        this.VIGENCIAHASTA = VIGENCIAHASTA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENDOSO)) return false;
        ENDOSO other = (ENDOSO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ANULAR==null && other.getANULAR()==null) || 
             (this.ANULAR!=null &&
              this.ANULAR.equals(other.getANULAR()))) &&
            ((this.ASOCIACIONCB==null && other.getASOCIACIONCB()==null) || 
             (this.ASOCIACIONCB!=null &&
              this.ASOCIACIONCB.equals(other.getASOCIACIONCB()))) &&
            ((this.AUTORIZACIONAID==null && other.getAUTORIZACIONAID()==null) || 
             (this.AUTORIZACIONAID!=null &&
              this.AUTORIZACIONAID.equals(other.getAUTORIZACIONAID()))) &&
            ((this.AUTORIZACIONBID==null && other.getAUTORIZACIONBID()==null) || 
             (this.AUTORIZACIONBID!=null &&
              this.AUTORIZACIONBID.equals(other.getAUTORIZACIONBID()))) &&
            ((this.AUTORIZACIONBROKERPOLID==null && other.getAUTORIZACIONBROKERPOLID()==null) || 
             (this.AUTORIZACIONBROKERPOLID!=null &&
              this.AUTORIZACIONBROKERPOLID.equals(other.getAUTORIZACIONBROKERPOLID()))) &&
            ((this.AUTORIZACIONCAMBIOCOMID==null && other.getAUTORIZACIONCAMBIOCOMID()==null) || 
             (this.AUTORIZACIONCAMBIOCOMID!=null &&
              this.AUTORIZACIONCAMBIOCOMID.equals(other.getAUTORIZACIONCAMBIOCOMID()))) &&
            ((this.AUTORIZACIONCID==null && other.getAUTORIZACIONCID()==null) || 
             (this.AUTORIZACIONCID!=null &&
              this.AUTORIZACIONCID.equals(other.getAUTORIZACIONCID()))) &&
            ((this.AUTORIZACIONDIASCONVENIOPAGO==null && other.getAUTORIZACIONDIASCONVENIOPAGO()==null) || 
             (this.AUTORIZACIONDIASCONVENIOPAGO!=null &&
              this.AUTORIZACIONDIASCONVENIOPAGO.equals(other.getAUTORIZACIONDIASCONVENIOPAGO()))) &&
            ((this.AUTORIZACIONFIRMAID==null && other.getAUTORIZACIONFIRMAID()==null) || 
             (this.AUTORIZACIONFIRMAID!=null &&
              this.AUTORIZACIONFIRMAID.equals(other.getAUTORIZACIONFIRMAID()))) &&
            ((this.AUTORIZACIONID==null && other.getAUTORIZACIONID()==null) || 
             (this.AUTORIZACIONID!=null &&
              this.AUTORIZACIONID.equals(other.getAUTORIZACIONID()))) &&
            ((this.AUTORIZACIONLIMITEEMBARQUEID==null && other.getAUTORIZACIONLIMITEEMBARQUEID()==null) || 
             (this.AUTORIZACIONLIMITEEMBARQUEID!=null &&
              this.AUTORIZACIONLIMITEEMBARQUEID.equals(other.getAUTORIZACIONLIMITEEMBARQUEID()))) &&
            ((this.AUTORIZACIONMONTOFIANID==null && other.getAUTORIZACIONMONTOFIANID()==null) || 
             (this.AUTORIZACIONMONTOFIANID!=null &&
              this.AUTORIZACIONMONTOFIANID.equals(other.getAUTORIZACIONMONTOFIANID()))) &&
            ((this.AUTORIZACIONNUEVOSOATID==null && other.getAUTORIZACIONNUEVOSOATID()==null) || 
             (this.AUTORIZACIONNUEVOSOATID!=null &&
              this.AUTORIZACIONNUEVOSOATID.equals(other.getAUTORIZACIONNUEVOSOATID()))) &&
            ((this.AUTORIZACIONPERSONAID==null && other.getAUTORIZACIONPERSONAID()==null) || 
             (this.AUTORIZACIONPERSONAID!=null &&
              this.AUTORIZACIONPERSONAID.equals(other.getAUTORIZACIONPERSONAID()))) &&
            ((this.AUTORIZACIONPRID==null && other.getAUTORIZACIONPRID()==null) || 
             (this.AUTORIZACIONPRID!=null &&
              this.AUTORIZACIONPRID.equals(other.getAUTORIZACIONPRID()))) &&
            ((this.AUTORIZACIONPRIMAMINID==null && other.getAUTORIZACIONPRIMAMINID()==null) || 
             (this.AUTORIZACIONPRIMAMINID!=null &&
              this.AUTORIZACIONPRIMAMINID.equals(other.getAUTORIZACIONPRIMAMINID()))) &&
            ((this.AUTORIZACIONVIGENCIAID==null && other.getAUTORIZACIONVIGENCIAID()==null) || 
             (this.AUTORIZACIONVIGENCIAID!=null &&
              this.AUTORIZACIONVIGENCIAID.equals(other.getAUTORIZACIONVIGENCIAID()))) &&
            ((this.AUTORIZACIONZFID==null && other.getAUTORIZACIONZFID()==null) || 
             (this.AUTORIZACIONZFID!=null &&
              this.AUTORIZACIONZFID.equals(other.getAUTORIZACIONZFID()))) &&
            ((this.AUTORIZAFIANZASID==null && other.getAUTORIZAFIANZASID()==null) || 
             (this.AUTORIZAFIANZASID!=null &&
              this.AUTORIZAFIANZASID.equals(other.getAUTORIZAFIANZASID()))) &&
            ((this.CAJAID==null && other.getCAJAID()==null) || 
             (this.CAJAID!=null &&
              this.CAJAID.equals(other.getCAJAID()))) &&
            ((this.CLIENTE==null && other.getCLIENTE()==null) || 
             (this.CLIENTE!=null &&
              this.CLIENTE.equals(other.getCLIENTE()))) &&
            ((this.CLIENTEReference==null && other.getCLIENTEReference()==null) || 
             (this.CLIENTEReference!=null &&
              this.CLIENTEReference.equals(other.getCLIENTEReference()))) &&
            ((this.COMISIONBROKER==null && other.getCOMISIONBROKER()==null) || 
             (this.COMISIONBROKER!=null &&
              this.COMISIONBROKER.equals(other.getCOMISIONBROKER()))) &&
            ((this.COMISIONMANTENIMIENTO==null && other.getCOMISIONMANTENIMIENTO()==null) || 
             (this.COMISIONMANTENIMIENTO!=null &&
              this.COMISIONMANTENIMIENTO.equals(other.getCOMISIONMANTENIMIENTO()))) &&
            ((this.CONVENIOPAGOID==null && other.getCONVENIOPAGOID()==null) || 
             (this.CONVENIOPAGOID!=null &&
              this.CONVENIOPAGOID.equals(other.getCONVENIOPAGOID()))) &&
            ((this.DERECHOSEMISION==null && other.getDERECHOSEMISION()==null) || 
             (this.DERECHOSEMISION!=null &&
              this.DERECHOSEMISION.equals(other.getDERECHOSEMISION()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.DIASAVISOSINIESTRO==null && other.getDIASAVISOSINIESTRO()==null) || 
             (this.DIASAVISOSINIESTRO!=null &&
              this.DIASAVISOSINIESTRO.equals(other.getDIASAVISOSINIESTRO()))) &&
            ((this.DIASCONVENIOPAGO==null && other.getDIASCONVENIOPAGO()==null) || 
             (this.DIASCONVENIOPAGO!=null &&
              this.DIASCONVENIOPAGO.equals(other.getDIASCONVENIOPAGO()))) &&
            ((this.ENDOSOITEM==null && other.getENDOSOITEM()==null) || 
             (this.ENDOSOITEM!=null &&
              java.util.Arrays.equals(this.ENDOSOITEM, other.getENDOSOITEM()))) &&
            ((this.ENDOSOREF==null && other.getENDOSOREF()==null) || 
             (this.ENDOSOREF!=null &&
              this.ENDOSOREF.equals(other.getENDOSOREF()))) &&
            ((this.ENTIDADAGENTEID==null && other.getENTIDADAGENTEID()==null) || 
             (this.ENTIDADAGENTEID!=null &&
              this.ENTIDADAGENTEID.equals(other.getENTIDADAGENTEID()))) &&
            ((this.ESAJUSTEVIGENCIA==null && other.getESAJUSTEVIGENCIA()==null) || 
             (this.ESAJUSTEVIGENCIA!=null &&
              this.ESAJUSTEVIGENCIA.equals(other.getESAJUSTEVIGENCIA()))) &&
            ((this.ESAUTOMATICO==null && other.getESAUTOMATICO()==null) || 
             (this.ESAUTOMATICO!=null &&
              this.ESAUTOMATICO.equals(other.getESAUTOMATICO()))) &&
            ((this.ESCANCELACION==null && other.getESCANCELACION()==null) || 
             (this.ESCANCELACION!=null &&
              this.ESCANCELACION.equals(other.getESCANCELACION()))) &&
            ((this.ESDATOOTROSISTEMA==null && other.getESDATOOTROSISTEMA()==null) || 
             (this.ESDATOOTROSISTEMA!=null &&
              this.ESDATOOTROSISTEMA.equals(other.getESDATOOTROSISTEMA()))) &&
            ((this.ESDEPOLIZAMADRE==null && other.getESDEPOLIZAMADRE()==null) || 
             (this.ESDEPOLIZAMADRE!=null &&
              this.ESDEPOLIZAMADRE.equals(other.getESDEPOLIZAMADRE()))) &&
            ((this.ESFACTURADOOTROSISTEMA==null && other.getESFACTURADOOTROSISTEMA()==null) || 
             (this.ESFACTURADOOTROSISTEMA!=null &&
              this.ESFACTURADOOTROSISTEMA.equals(other.getESFACTURADOOTROSISTEMA()))) &&
            ((this.ESVERIFICADO==null && other.getESVERIFICADO()==null) || 
             (this.ESVERIFICADO!=null &&
              this.ESVERIFICADO.equals(other.getESVERIFICADO()))) &&
            ((this.EXCENTOIVA==null && other.getEXCENTOIVA()==null) || 
             (this.EXCENTOIVA!=null &&
              this.EXCENTOIVA.equals(other.getEXCENTOIVA()))) &&
            ((this.FECFINCREDITO==null && other.getFECFINCREDITO()==null) || 
             (this.FECFINCREDITO!=null &&
              this.FECFINCREDITO.equals(other.getFECFINCREDITO()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHAANULACION==null && other.getFECHAANULACION()==null) || 
             (this.FECHAANULACION!=null &&
              this.FECHAANULACION.equals(other.getFECHAANULACION()))) &&
            ((this.FECHACOTIZACION==null && other.getFECHACOTIZACION()==null) || 
             (this.FECHACOTIZACION!=null &&
              this.FECHACOTIZACION.equals(other.getFECHACOTIZACION()))) &&
            ((this.FECHAELABORACION==null && other.getFECHAELABORACION()==null) || 
             (this.FECHAELABORACION!=null &&
              this.FECHAELABORACION.equals(other.getFECHAELABORACION()))) &&
            ((this.FECHAVENCIMIENTO==null && other.getFECHAVENCIMIENTO()==null) || 
             (this.FECHAVENCIMIENTO!=null &&
              this.FECHAVENCIMIENTO.equals(other.getFECHAVENCIMIENTO()))) &&
            ((this.FIRMASUCURSALID==null && other.getFIRMASUCURSALID()==null) || 
             (this.FIRMASUCURSALID!=null &&
              this.FIRMASUCURSALID.equals(other.getFIRMASUCURSALID()))) &&
            ((this.FORMAPAGO==null && other.getFORMAPAGO()==null) || 
             (this.FORMAPAGO!=null &&
              this.FORMAPAGO.equals(other.getFORMAPAGO()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.IMPRIMEORIGINAL==null && other.getIMPRIMEORIGINAL()==null) || 
             (this.IMPRIMEORIGINAL!=null &&
              this.IMPRIMEORIGINAL.equals(other.getIMPRIMEORIGINAL()))) &&
            ((this.LIMITEINDEMNIZACION==null && other.getLIMITEINDEMNIZACION()==null) || 
             (this.LIMITEINDEMNIZACION!=null &&
              this.LIMITEINDEMNIZACION.equals(other.getLIMITEINDEMNIZACION()))) &&
            ((this.MOTIVOCANCELACION==null && other.getMOTIVOCANCELACION()==null) || 
             (this.MOTIVOCANCELACION!=null &&
              this.MOTIVOCANCELACION.equals(other.getMOTIVOCANCELACION()))) &&
            ((this.NOMBRETARJETACREDITOID==null && other.getNOMBRETARJETACREDITOID()==null) || 
             (this.NOMBRETARJETACREDITOID!=null &&
              this.NOMBRETARJETACREDITOID.equals(other.getNOMBRETARJETACREDITOID()))) &&
            ((this.NOTAS==null && other.getNOTAS()==null) || 
             (this.NOTAS!=null &&
              this.NOTAS.equals(other.getNOTAS()))) &&
            ((this.NUMERO==null && other.getNUMERO()==null) || 
             (this.NUMERO!=null &&
              this.NUMERO.equals(other.getNUMERO()))) &&
            ((this.NUMEROAUTORIZACION==null && other.getNUMEROAUTORIZACION()==null) || 
             (this.NUMEROAUTORIZACION!=null &&
              this.NUMEROAUTORIZACION.equals(other.getNUMEROAUTORIZACION()))) &&
            ((this.NUMEROCERTIFICADO==null && other.getNUMEROCERTIFICADO()==null) || 
             (this.NUMEROCERTIFICADO!=null &&
              this.NUMEROCERTIFICADO.equals(other.getNUMEROCERTIFICADO()))) &&
            ((this.NUMEROTARIFA==null && other.getNUMEROTARIFA()==null) || 
             (this.NUMEROTARIFA!=null &&
              this.NUMEROTARIFA.equals(other.getNUMEROTARIFA()))) &&
            ((this.NUMEROTARJETA==null && other.getNUMEROTARJETA()==null) || 
             (this.NUMEROTARJETA!=null &&
              this.NUMEROTARJETA.equals(other.getNUMEROTARJETA()))) &&
            ((this.NUMEROTRAMITE==null && other.getNUMEROTRAMITE()==null) || 
             (this.NUMEROTRAMITE!=null &&
              this.NUMEROTRAMITE.equals(other.getNUMEROTRAMITE()))) &&
            ((this.OBSERVACIONES==null && other.getOBSERVACIONES()==null) || 
             (this.OBSERVACIONES!=null &&
              this.OBSERVACIONES.equals(other.getOBSERVACIONES()))) &&
            ((this.POLIZA==null && other.getPOLIZA()==null) || 
             (this.POLIZA!=null &&
              this.POLIZA.equals(other.getPOLIZA()))) &&
            ((this.POLIZAReference==null && other.getPOLIZAReference()==null) || 
             (this.POLIZAReference!=null &&
              this.POLIZAReference.equals(other.getPOLIZAReference()))) &&
            ((this.PORCCOMISIONAGENTE==null && other.getPORCCOMISIONAGENTE()==null) || 
             (this.PORCCOMISIONAGENTE!=null &&
              this.PORCCOMISIONAGENTE.equals(other.getPORCCOMISIONAGENTE()))) &&
            ((this.PREIMPRESOVERIFICADO==null && other.getPREIMPRESOVERIFICADO()==null) || 
             (this.PREIMPRESOVERIFICADO!=null &&
              this.PREIMPRESOVERIFICADO.equals(other.getPREIMPRESOVERIFICADO()))) &&
            ((this.PRIMAMINVERIFICADA==null && other.getPRIMAMINVERIFICADA()==null) || 
             (this.PRIMAMINVERIFICADA!=null &&
              this.PRIMAMINVERIFICADA.equals(other.getPRIMAMINVERIFICADA()))) &&
            ((this.PRODUCTOAPEID==null && other.getPRODUCTOAPEID()==null) || 
             (this.PRODUCTOAPEID!=null &&
              this.PRODUCTOAPEID.equals(other.getPRODUCTOAPEID()))) &&
            ((this.PUNTOVENTAAPEID==null && other.getPUNTOVENTAAPEID()==null) || 
             (this.PUNTOVENTAAPEID!=null &&
              this.PUNTOVENTAAPEID.equals(other.getPUNTOVENTAAPEID()))) &&
            ((this.PUNTOVENTAID==null && other.getPUNTOVENTAID()==null) || 
             (this.PUNTOVENTAID!=null &&
              this.PUNTOVENTAID.equals(other.getPUNTOVENTAID()))) &&
            ((this.SISTEMAEMISOR==null && other.getSISTEMAEMISOR()==null) || 
             (this.SISTEMAEMISOR!=null &&
              this.SISTEMAEMISOR.equals(other.getSISTEMAEMISOR()))) &&
            ((this.SISTEMAEMISORID==null && other.getSISTEMAEMISORID()==null) || 
             (this.SISTEMAEMISORID!=null &&
              this.SISTEMAEMISORID.equals(other.getSISTEMAEMISORID()))) &&
            ((this.SUCURSALID==null && other.getSUCURSALID()==null) || 
             (this.SUCURSALID!=null &&
              this.SUCURSALID.equals(other.getSUCURSALID()))) &&
            ((this.TARJETAID==null && other.getTARJETAID()==null) || 
             (this.TARJETAID!=null &&
              this.TARJETAID.equals(other.getTARJETAID()))) &&
            ((this.TASAMINIMA==null && other.getTASAMINIMA()==null) || 
             (this.TASAMINIMA!=null &&
              this.TASAMINIMA.equals(other.getTASAMINIMA()))) &&
            ((this.TIPOCANCELACION==null && other.getTIPOCANCELACION()==null) || 
             (this.TIPOCANCELACION!=null &&
              this.TIPOCANCELACION.equals(other.getTIPOCANCELACION()))) &&
            ((this.TIPOENDOSOID==null && other.getTIPOENDOSOID()==null) || 
             (this.TIPOENDOSOID!=null &&
              this.TIPOENDOSOID.equals(other.getTIPOENDOSOID()))) &&
            ((this.TIPOITEMID==null && other.getTIPOITEMID()==null) || 
             (this.TIPOITEMID!=null &&
              this.TIPOITEMID.equals(other.getTIPOITEMID()))) &&
            ((this.TIPOPAGOID==null && other.getTIPOPAGOID()==null) || 
             (this.TIPOPAGOID!=null &&
              this.TIPOPAGOID.equals(other.getTIPOPAGOID()))) &&
            ((this.TIPOSEGUROID==null && other.getTIPOSEGUROID()==null) || 
             (this.TIPOSEGUROID!=null &&
              this.TIPOSEGUROID.equals(other.getTIPOSEGUROID()))) &&
            ((this.TITULARTARJETA==null && other.getTITULARTARJETA()==null) || 
             (this.TITULARTARJETA!=null &&
              this.TITULARTARJETA.equals(other.getTITULARTARJETA()))) &&
            ((this.UNIDADNEGOCIOID==null && other.getUNIDADNEGOCIOID()==null) || 
             (this.UNIDADNEGOCIOID!=null &&
              this.UNIDADNEGOCIOID.equals(other.getUNIDADNEGOCIOID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.VALORASEGURADO==null && other.getVALORASEGURADO()==null) || 
             (this.VALORASEGURADO!=null &&
              this.VALORASEGURADO.equals(other.getVALORASEGURADO()))) &&
            ((this.VALORCOMISION==null && other.getVALORCOMISION()==null) || 
             (this.VALORCOMISION!=null &&
              this.VALORCOMISION.equals(other.getVALORCOMISION()))) &&
            ((this.VALORPRIMANETA==null && other.getVALORPRIMANETA()==null) || 
             (this.VALORPRIMANETA!=null &&
              this.VALORPRIMANETA.equals(other.getVALORPRIMANETA()))) &&
            ((this.VALORRECARGOSC==null && other.getVALORRECARGOSC()==null) || 
             (this.VALORRECARGOSC!=null &&
              this.VALORRECARGOSC.equals(other.getVALORRECARGOSC()))) &&
            ((this.VIGENCIADESDE==null && other.getVIGENCIADESDE()==null) || 
             (this.VIGENCIADESDE!=null &&
              this.VIGENCIADESDE.equals(other.getVIGENCIADESDE()))) &&
            ((this.VIGENCIAHASTA==null && other.getVIGENCIAHASTA()==null) || 
             (this.VIGENCIAHASTA!=null &&
              this.VIGENCIAHASTA.equals(other.getVIGENCIAHASTA())));
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
        if (getANULAR() != null) {
            _hashCode += getANULAR().hashCode();
        }
        if (getASOCIACIONCB() != null) {
            _hashCode += getASOCIACIONCB().hashCode();
        }
        if (getAUTORIZACIONAID() != null) {
            _hashCode += getAUTORIZACIONAID().hashCode();
        }
        if (getAUTORIZACIONBID() != null) {
            _hashCode += getAUTORIZACIONBID().hashCode();
        }
        if (getAUTORIZACIONBROKERPOLID() != null) {
            _hashCode += getAUTORIZACIONBROKERPOLID().hashCode();
        }
        if (getAUTORIZACIONCAMBIOCOMID() != null) {
            _hashCode += getAUTORIZACIONCAMBIOCOMID().hashCode();
        }
        if (getAUTORIZACIONCID() != null) {
            _hashCode += getAUTORIZACIONCID().hashCode();
        }
        if (getAUTORIZACIONDIASCONVENIOPAGO() != null) {
            _hashCode += getAUTORIZACIONDIASCONVENIOPAGO().hashCode();
        }
        if (getAUTORIZACIONFIRMAID() != null) {
            _hashCode += getAUTORIZACIONFIRMAID().hashCode();
        }
        if (getAUTORIZACIONID() != null) {
            _hashCode += getAUTORIZACIONID().hashCode();
        }
        if (getAUTORIZACIONLIMITEEMBARQUEID() != null) {
            _hashCode += getAUTORIZACIONLIMITEEMBARQUEID().hashCode();
        }
        if (getAUTORIZACIONMONTOFIANID() != null) {
            _hashCode += getAUTORIZACIONMONTOFIANID().hashCode();
        }
        if (getAUTORIZACIONNUEVOSOATID() != null) {
            _hashCode += getAUTORIZACIONNUEVOSOATID().hashCode();
        }
        if (getAUTORIZACIONPERSONAID() != null) {
            _hashCode += getAUTORIZACIONPERSONAID().hashCode();
        }
        if (getAUTORIZACIONPRID() != null) {
            _hashCode += getAUTORIZACIONPRID().hashCode();
        }
        if (getAUTORIZACIONPRIMAMINID() != null) {
            _hashCode += getAUTORIZACIONPRIMAMINID().hashCode();
        }
        if (getAUTORIZACIONVIGENCIAID() != null) {
            _hashCode += getAUTORIZACIONVIGENCIAID().hashCode();
        }
        if (getAUTORIZACIONZFID() != null) {
            _hashCode += getAUTORIZACIONZFID().hashCode();
        }
        if (getAUTORIZAFIANZASID() != null) {
            _hashCode += getAUTORIZAFIANZASID().hashCode();
        }
        if (getCAJAID() != null) {
            _hashCode += getCAJAID().hashCode();
        }
        if (getCLIENTE() != null) {
            _hashCode += getCLIENTE().hashCode();
        }
        if (getCLIENTEReference() != null) {
            _hashCode += getCLIENTEReference().hashCode();
        }
        if (getCOMISIONBROKER() != null) {
            _hashCode += getCOMISIONBROKER().hashCode();
        }
        if (getCOMISIONMANTENIMIENTO() != null) {
            _hashCode += getCOMISIONMANTENIMIENTO().hashCode();
        }
        if (getCONVENIOPAGOID() != null) {
            _hashCode += getCONVENIOPAGOID().hashCode();
        }
        if (getDERECHOSEMISION() != null) {
            _hashCode += getDERECHOSEMISION().hashCode();
        }
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getDIASAVISOSINIESTRO() != null) {
            _hashCode += getDIASAVISOSINIESTRO().hashCode();
        }
        if (getDIASCONVENIOPAGO() != null) {
            _hashCode += getDIASCONVENIOPAGO().hashCode();
        }
        if (getENDOSOITEM() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getENDOSOITEM());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getENDOSOITEM(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getENDOSOREF() != null) {
            _hashCode += getENDOSOREF().hashCode();
        }
        if (getENTIDADAGENTEID() != null) {
            _hashCode += getENTIDADAGENTEID().hashCode();
        }
        if (getESAJUSTEVIGENCIA() != null) {
            _hashCode += getESAJUSTEVIGENCIA().hashCode();
        }
        if (getESAUTOMATICO() != null) {
            _hashCode += getESAUTOMATICO().hashCode();
        }
        if (getESCANCELACION() != null) {
            _hashCode += getESCANCELACION().hashCode();
        }
        if (getESDATOOTROSISTEMA() != null) {
            _hashCode += getESDATOOTROSISTEMA().hashCode();
        }
        if (getESDEPOLIZAMADRE() != null) {
            _hashCode += getESDEPOLIZAMADRE().hashCode();
        }
        if (getESFACTURADOOTROSISTEMA() != null) {
            _hashCode += getESFACTURADOOTROSISTEMA().hashCode();
        }
        if (getESVERIFICADO() != null) {
            _hashCode += getESVERIFICADO().hashCode();
        }
        if (getEXCENTOIVA() != null) {
            _hashCode += getEXCENTOIVA().hashCode();
        }
        if (getFECFINCREDITO() != null) {
            _hashCode += getFECFINCREDITO().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHAANULACION() != null) {
            _hashCode += getFECHAANULACION().hashCode();
        }
        if (getFECHACOTIZACION() != null) {
            _hashCode += getFECHACOTIZACION().hashCode();
        }
        if (getFECHAELABORACION() != null) {
            _hashCode += getFECHAELABORACION().hashCode();
        }
        if (getFECHAVENCIMIENTO() != null) {
            _hashCode += getFECHAVENCIMIENTO().hashCode();
        }
        if (getFIRMASUCURSALID() != null) {
            _hashCode += getFIRMASUCURSALID().hashCode();
        }
        if (getFORMAPAGO() != null) {
            _hashCode += getFORMAPAGO().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getIMPRIMEORIGINAL() != null) {
            _hashCode += getIMPRIMEORIGINAL().hashCode();
        }
        if (getLIMITEINDEMNIZACION() != null) {
            _hashCode += getLIMITEINDEMNIZACION().hashCode();
        }
        if (getMOTIVOCANCELACION() != null) {
            _hashCode += getMOTIVOCANCELACION().hashCode();
        }
        if (getNOMBRETARJETACREDITOID() != null) {
            _hashCode += getNOMBRETARJETACREDITOID().hashCode();
        }
        if (getNOTAS() != null) {
            _hashCode += getNOTAS().hashCode();
        }
        if (getNUMERO() != null) {
            _hashCode += getNUMERO().hashCode();
        }
        if (getNUMEROAUTORIZACION() != null) {
            _hashCode += getNUMEROAUTORIZACION().hashCode();
        }
        if (getNUMEROCERTIFICADO() != null) {
            _hashCode += getNUMEROCERTIFICADO().hashCode();
        }
        if (getNUMEROTARIFA() != null) {
            _hashCode += getNUMEROTARIFA().hashCode();
        }
        if (getNUMEROTARJETA() != null) {
            _hashCode += getNUMEROTARJETA().hashCode();
        }
        if (getNUMEROTRAMITE() != null) {
            _hashCode += getNUMEROTRAMITE().hashCode();
        }
        if (getOBSERVACIONES() != null) {
            _hashCode += getOBSERVACIONES().hashCode();
        }
        if (getPOLIZA() != null) {
            _hashCode += getPOLIZA().hashCode();
        }
        if (getPOLIZAReference() != null) {
            _hashCode += getPOLIZAReference().hashCode();
        }
        if (getPORCCOMISIONAGENTE() != null) {
            _hashCode += getPORCCOMISIONAGENTE().hashCode();
        }
        if (getPREIMPRESOVERIFICADO() != null) {
            _hashCode += getPREIMPRESOVERIFICADO().hashCode();
        }
        if (getPRIMAMINVERIFICADA() != null) {
            _hashCode += getPRIMAMINVERIFICADA().hashCode();
        }
        if (getPRODUCTOAPEID() != null) {
            _hashCode += getPRODUCTOAPEID().hashCode();
        }
        if (getPUNTOVENTAAPEID() != null) {
            _hashCode += getPUNTOVENTAAPEID().hashCode();
        }
        if (getPUNTOVENTAID() != null) {
            _hashCode += getPUNTOVENTAID().hashCode();
        }
        if (getSISTEMAEMISOR() != null) {
            _hashCode += getSISTEMAEMISOR().hashCode();
        }
        if (getSISTEMAEMISORID() != null) {
            _hashCode += getSISTEMAEMISORID().hashCode();
        }
        if (getSUCURSALID() != null) {
            _hashCode += getSUCURSALID().hashCode();
        }
        if (getTARJETAID() != null) {
            _hashCode += getTARJETAID().hashCode();
        }
        if (getTASAMINIMA() != null) {
            _hashCode += getTASAMINIMA().hashCode();
        }
        if (getTIPOCANCELACION() != null) {
            _hashCode += getTIPOCANCELACION().hashCode();
        }
        if (getTIPOENDOSOID() != null) {
            _hashCode += getTIPOENDOSOID().hashCode();
        }
        if (getTIPOITEMID() != null) {
            _hashCode += getTIPOITEMID().hashCode();
        }
        if (getTIPOPAGOID() != null) {
            _hashCode += getTIPOPAGOID().hashCode();
        }
        if (getTIPOSEGUROID() != null) {
            _hashCode += getTIPOSEGUROID().hashCode();
        }
        if (getTITULARTARJETA() != null) {
            _hashCode += getTITULARTARJETA().hashCode();
        }
        if (getUNIDADNEGOCIOID() != null) {
            _hashCode += getUNIDADNEGOCIOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getVALORASEGURADO() != null) {
            _hashCode += getVALORASEGURADO().hashCode();
        }
        if (getVALORCOMISION() != null) {
            _hashCode += getVALORCOMISION().hashCode();
        }
        if (getVALORPRIMANETA() != null) {
            _hashCode += getVALORPRIMANETA().hashCode();
        }
        if (getVALORRECARGOSC() != null) {
            _hashCode += getVALORRECARGOSC().hashCode();
        }
        if (getVIGENCIADESDE() != null) {
            _hashCode += getVIGENCIADESDE().hashCode();
        }
        if (getVIGENCIAHASTA() != null) {
            _hashCode += getVIGENCIAHASTA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENDOSO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ANULAR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ANULAR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ASOCIACIONCB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ASOCIACIONCB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONBID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONBID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONBROKERPOLID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONBROKERPOLID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONCAMBIOCOMID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONCAMBIOCOMID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONCID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONCID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONDIASCONVENIOPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONDIASCONVENIOPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONFIRMAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONFIRMAID"));
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
        elemField.setFieldName("AUTORIZACIONLIMITEEMBARQUEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONLIMITEEMBARQUEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONMONTOFIANID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONMONTOFIANID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONNUEVOSOATID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONNUEVOSOATID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONPERSONAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONPERSONAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONPRID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONPRID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONPRIMAMINID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONPRIMAMINID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONVIGENCIAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONVIGENCIAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONZFID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONZFID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZAFIANZASID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZAFIANZASID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAJAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CAJAID"));
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
        elemField.setFieldName("COMISIONBROKER");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "COMISIONBROKER"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COMISIONMANTENIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "COMISIONMANTENIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("DERECHOSEMISION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DERECHOSEMISION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESCRIPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DESCRIPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIASAVISOSINIESTRO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIASAVISOSINIESTRO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DIASCONVENIOPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIASCONVENIOPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOREF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOREF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENTIDADAGENTEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADAGENTEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESAJUSTEVIGENCIA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESAJUSTEVIGENCIA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESAUTOMATICO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESAUTOMATICO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESCANCELACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESCANCELACION"));
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
        elemField.setFieldName("ESDEPOLIZAMADRE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESDEPOLIZAMADRE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESFACTURADOOTROSISTEMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESFACTURADOOTROSISTEMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESVERIFICADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESVERIFICADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXCENTOIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EXCENTOIVA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECFINCREDITO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECFINCREDITO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("FECHACOTIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACOTIZACION"));
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
        elemField.setFieldName("FECHAVENCIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAVENCIMIENTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FIRMASUCURSALID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FIRMASUCURSALID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FORMAPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FORMAPAGO"));
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
        elemField.setFieldName("IMPRIMEORIGINAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "IMPRIMEORIGINAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LIMITEINDEMNIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LIMITEINDEMNIZACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOCANCELACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOCANCELACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOMBRETARJETACREDITOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOMBRETARJETACREDITOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NOTAS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NOTAS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROAUTORIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROAUTORIZACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROCERTIFICADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROCERTIFICADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROTARIFA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROTARIFA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROTARJETA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROTARJETA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROTRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROTRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OBSERVACIONES");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "OBSERVACIONES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POLIZAReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZAReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPOLIZAFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCCOMISIONAGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PORCCOMISIONAGENTE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PREIMPRESOVERIFICADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PREIMPRESOVERIFICADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMAMINVERIFICADA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMAMINVERIFICADA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRODUCTOAPEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRODUCTOAPEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUNTOVENTAAPEID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PUNTOVENTAAPEID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PUNTOVENTAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PUNTOVENTAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISTEMAEMISOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SISTEMAEMISOR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISTEMAEMISORID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SISTEMAEMISORID"));
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
        elemField.setFieldName("TARJETAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TARJETAID"));
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
        elemField.setFieldName("TIPOCANCELACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOCANCELACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOENDOSOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOENDOSOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOITEMID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOITEMID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TIPOPAGOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOPAGOID"));
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
        elemField.setFieldName("TITULARTARJETA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TITULARTARJETA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UNIDADNEGOCIOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "UNIDADNEGOCIOID"));
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
        elemField.setFieldName("VALORASEGURADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORASEGURADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORCOMISION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORCOMISION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORPRIMANETA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORPRIMANETA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORRECARGOSC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORRECARGOSC"));
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
