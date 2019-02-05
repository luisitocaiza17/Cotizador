/**
 * DOCUMENTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class DOCUMENTO  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ASIENTOID;

    private java.lang.String AUTORIZACIONID;

    private java.lang.String AUTORIZACIONSRI;

    private java.lang.String AUTORIZACIONSRIID;

    private java.lang.String CAMBIO;

    private java.lang.String DEBITOENVIADO;

    private java.lang.String DEBITOREALIZADO;

    private java.lang.String DESCRIPCION;

    private java.lang.String DISCRIMINADOR;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference;

    private java.lang.String ESANTERIOR;

    private java.lang.String ESTADOID;

    private java.util.Calendar FECHA;

    private java.util.Calendar FECHAACTUALIZA;

    private java.util.Calendar FECHACARGA;

    private java.util.Calendar FECHACONTABILIZACION;

    private java.util.Calendar FECHADEBITOS;

    private java.util.Calendar FECHARECEPCION;

    private java.util.Calendar FECHAVENCIMIENTO;

    private java.lang.String ID;

    private java.lang.String LIBERACOMISIONAGENTE;

    private java.lang.String MONEDAID;

    private java.lang.String MOTIVOGENERAL;

    private java.lang.String MOTIVOIFI;

    private java.lang.String NUMERO;

    private java.lang.String NUMEROAUTORIZACION;

    private java.math.BigDecimal NUMTRAMITE;

    private java.lang.String PUNTOVENTAID;

    private java.lang.String RECORDATORIOID;

    private java.lang.String RESPUESTASRI;

    private java.lang.String RUBROCONTABLE;

    private java.math.BigDecimal SALDOEXTRANJERA;

    private java.math.BigDecimal SALDOLOCAL;

    private java.math.BigDecimal SALDOORIGEN;

    private java.lang.String SUCURSALID;

    private java.lang.String TIPODOCUMENTOID;

    private java.lang.String USUARIOACTUALIZA;

    private java.lang.String USUARIOCARGA;

    private java.math.BigDecimal VALOREXTRANJERA;

    private java.math.BigDecimal VALORLOCAL;

    private java.math.BigDecimal VALORORDENPAGO;

    private java.math.BigDecimal VALORORIGEN;

    public DOCUMENTO() {
    }

    public DOCUMENTO(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ASIENTOID,
           java.lang.String AUTORIZACIONID,
           java.lang.String AUTORIZACIONSRI,
           java.lang.String AUTORIZACIONSRIID,
           java.lang.String CAMBIO,
           java.lang.String DEBITOENVIADO,
           java.lang.String DEBITOREALIZADO,
           java.lang.String DESCRIPCION,
           java.lang.String DISCRIMINADOR,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference,
           java.lang.String ESANTERIOR,
           java.lang.String ESTADOID,
           java.util.Calendar FECHA,
           java.util.Calendar FECHAACTUALIZA,
           java.util.Calendar FECHACARGA,
           java.util.Calendar FECHACONTABILIZACION,
           java.util.Calendar FECHADEBITOS,
           java.util.Calendar FECHARECEPCION,
           java.util.Calendar FECHAVENCIMIENTO,
           java.lang.String ID,
           java.lang.String LIBERACOMISIONAGENTE,
           java.lang.String MONEDAID,
           java.lang.String MOTIVOGENERAL,
           java.lang.String MOTIVOIFI,
           java.lang.String NUMERO,
           java.lang.String NUMEROAUTORIZACION,
           java.math.BigDecimal NUMTRAMITE,
           java.lang.String PUNTOVENTAID,
           java.lang.String RECORDATORIOID,
           java.lang.String RESPUESTASRI,
           java.lang.String RUBROCONTABLE,
           java.math.BigDecimal SALDOEXTRANJERA,
           java.math.BigDecimal SALDOLOCAL,
           java.math.BigDecimal SALDOORIGEN,
           java.lang.String SUCURSALID,
           java.lang.String TIPODOCUMENTOID,
           java.lang.String USUARIOACTUALIZA,
           java.lang.String USUARIOCARGA,
           java.math.BigDecimal VALOREXTRANJERA,
           java.math.BigDecimal VALORLOCAL,
           java.math.BigDecimal VALORORDENPAGO,
           java.math.BigDecimal VALORORIGEN) {
        super(
            id,
            ref,
            entityKey);
        this.ASIENTOID = ASIENTOID;
        this.AUTORIZACIONID = AUTORIZACIONID;
        this.AUTORIZACIONSRI = AUTORIZACIONSRI;
        this.AUTORIZACIONSRIID = AUTORIZACIONSRIID;
        this.CAMBIO = CAMBIO;
        this.DEBITOENVIADO = DEBITOENVIADO;
        this.DEBITOREALIZADO = DEBITOREALIZADO;
        this.DESCRIPCION = DESCRIPCION;
        this.DISCRIMINADOR = DISCRIMINADOR;
        this.ENTIDAD = ENTIDAD;
        this.ENTIDADReference = ENTIDADReference;
        this.ESANTERIOR = ESANTERIOR;
        this.ESTADOID = ESTADOID;
        this.FECHA = FECHA;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FECHACARGA = FECHACARGA;
        this.FECHACONTABILIZACION = FECHACONTABILIZACION;
        this.FECHADEBITOS = FECHADEBITOS;
        this.FECHARECEPCION = FECHARECEPCION;
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
        this.ID = ID;
        this.LIBERACOMISIONAGENTE = LIBERACOMISIONAGENTE;
        this.MONEDAID = MONEDAID;
        this.MOTIVOGENERAL = MOTIVOGENERAL;
        this.MOTIVOIFI = MOTIVOIFI;
        this.NUMERO = NUMERO;
        this.NUMEROAUTORIZACION = NUMEROAUTORIZACION;
        this.NUMTRAMITE = NUMTRAMITE;
        this.PUNTOVENTAID = PUNTOVENTAID;
        this.RECORDATORIOID = RECORDATORIOID;
        this.RESPUESTASRI = RESPUESTASRI;
        this.RUBROCONTABLE = RUBROCONTABLE;
        this.SALDOEXTRANJERA = SALDOEXTRANJERA;
        this.SALDOLOCAL = SALDOLOCAL;
        this.SALDOORIGEN = SALDOORIGEN;
        this.SUCURSALID = SUCURSALID;
        this.TIPODOCUMENTOID = TIPODOCUMENTOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.USUARIOCARGA = USUARIOCARGA;
        this.VALOREXTRANJERA = VALOREXTRANJERA;
        this.VALORLOCAL = VALORLOCAL;
        this.VALORORDENPAGO = VALORORDENPAGO;
        this.VALORORIGEN = VALORORIGEN;
    }


    /**
     * Gets the ASIENTOID value for this DOCUMENTO.
     * 
     * @return ASIENTOID
     */
    public java.lang.String getASIENTOID() {
        return ASIENTOID;
    }


    /**
     * Sets the ASIENTOID value for this DOCUMENTO.
     * 
     * @param ASIENTOID
     */
    public void setASIENTOID(java.lang.String ASIENTOID) {
        this.ASIENTOID = ASIENTOID;
    }


    /**
     * Gets the AUTORIZACIONID value for this DOCUMENTO.
     * 
     * @return AUTORIZACIONID
     */
    public java.lang.String getAUTORIZACIONID() {
        return AUTORIZACIONID;
    }


    /**
     * Sets the AUTORIZACIONID value for this DOCUMENTO.
     * 
     * @param AUTORIZACIONID
     */
    public void setAUTORIZACIONID(java.lang.String AUTORIZACIONID) {
        this.AUTORIZACIONID = AUTORIZACIONID;
    }


    /**
     * Gets the AUTORIZACIONSRI value for this DOCUMENTO.
     * 
     * @return AUTORIZACIONSRI
     */
    public java.lang.String getAUTORIZACIONSRI() {
        return AUTORIZACIONSRI;
    }


    /**
     * Sets the AUTORIZACIONSRI value for this DOCUMENTO.
     * 
     * @param AUTORIZACIONSRI
     */
    public void setAUTORIZACIONSRI(java.lang.String AUTORIZACIONSRI) {
        this.AUTORIZACIONSRI = AUTORIZACIONSRI;
    }


    /**
     * Gets the AUTORIZACIONSRIID value for this DOCUMENTO.
     * 
     * @return AUTORIZACIONSRIID
     */
    public java.lang.String getAUTORIZACIONSRIID() {
        return AUTORIZACIONSRIID;
    }


    /**
     * Sets the AUTORIZACIONSRIID value for this DOCUMENTO.
     * 
     * @param AUTORIZACIONSRIID
     */
    public void setAUTORIZACIONSRIID(java.lang.String AUTORIZACIONSRIID) {
        this.AUTORIZACIONSRIID = AUTORIZACIONSRIID;
    }


    /**
     * Gets the CAMBIO value for this DOCUMENTO.
     * 
     * @return CAMBIO
     */
    public java.lang.String getCAMBIO() {
        return CAMBIO;
    }


    /**
     * Sets the CAMBIO value for this DOCUMENTO.
     * 
     * @param CAMBIO
     */
    public void setCAMBIO(java.lang.String CAMBIO) {
        this.CAMBIO = CAMBIO;
    }


    /**
     * Gets the DEBITOENVIADO value for this DOCUMENTO.
     * 
     * @return DEBITOENVIADO
     */
    public java.lang.String getDEBITOENVIADO() {
        return DEBITOENVIADO;
    }


    /**
     * Sets the DEBITOENVIADO value for this DOCUMENTO.
     * 
     * @param DEBITOENVIADO
     */
    public void setDEBITOENVIADO(java.lang.String DEBITOENVIADO) {
        this.DEBITOENVIADO = DEBITOENVIADO;
    }


    /**
     * Gets the DEBITOREALIZADO value for this DOCUMENTO.
     * 
     * @return DEBITOREALIZADO
     */
    public java.lang.String getDEBITOREALIZADO() {
        return DEBITOREALIZADO;
    }


    /**
     * Sets the DEBITOREALIZADO value for this DOCUMENTO.
     * 
     * @param DEBITOREALIZADO
     */
    public void setDEBITOREALIZADO(java.lang.String DEBITOREALIZADO) {
        this.DEBITOREALIZADO = DEBITOREALIZADO;
    }


    /**
     * Gets the DESCRIPCION value for this DOCUMENTO.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this DOCUMENTO.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the DISCRIMINADOR value for this DOCUMENTO.
     * 
     * @return DISCRIMINADOR
     */
    public java.lang.String getDISCRIMINADOR() {
        return DISCRIMINADOR;
    }


    /**
     * Sets the DISCRIMINADOR value for this DOCUMENTO.
     * 
     * @param DISCRIMINADOR
     */
    public void setDISCRIMINADOR(java.lang.String DISCRIMINADOR) {
        this.DISCRIMINADOR = DISCRIMINADOR;
    }


    /**
     * Gets the ENTIDAD value for this DOCUMENTO.
     * 
     * @return ENTIDAD
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD getENTIDAD() {
        return ENTIDAD;
    }


    /**
     * Sets the ENTIDAD value for this DOCUMENTO.
     * 
     * @param ENTIDAD
     */
    public void setENTIDAD(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD ENTIDAD) {
        this.ENTIDAD = ENTIDAD;
    }


    /**
     * Gets the ENTIDADReference value for this DOCUMENTO.
     * 
     * @return ENTIDADReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 getENTIDADReference() {
        return ENTIDADReference;
    }


    /**
     * Sets the ENTIDADReference value for this DOCUMENTO.
     * 
     * @param ENTIDADReference
     */
    public void setENTIDADReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7 ENTIDADReference) {
        this.ENTIDADReference = ENTIDADReference;
    }


    /**
     * Gets the ESANTERIOR value for this DOCUMENTO.
     * 
     * @return ESANTERIOR
     */
    public java.lang.String getESANTERIOR() {
        return ESANTERIOR;
    }


    /**
     * Sets the ESANTERIOR value for this DOCUMENTO.
     * 
     * @param ESANTERIOR
     */
    public void setESANTERIOR(java.lang.String ESANTERIOR) {
        this.ESANTERIOR = ESANTERIOR;
    }


    /**
     * Gets the ESTADOID value for this DOCUMENTO.
     * 
     * @return ESTADOID
     */
    public java.lang.String getESTADOID() {
        return ESTADOID;
    }


    /**
     * Sets the ESTADOID value for this DOCUMENTO.
     * 
     * @param ESTADOID
     */
    public void setESTADOID(java.lang.String ESTADOID) {
        this.ESTADOID = ESTADOID;
    }


    /**
     * Gets the FECHA value for this DOCUMENTO.
     * 
     * @return FECHA
     */
    public java.util.Calendar getFECHA() {
        return FECHA;
    }


    /**
     * Sets the FECHA value for this DOCUMENTO.
     * 
     * @param FECHA
     */
    public void setFECHA(java.util.Calendar FECHA) {
        this.FECHA = FECHA;
    }


    /**
     * Gets the FECHAACTUALIZA value for this DOCUMENTO.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this DOCUMENTO.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FECHACARGA value for this DOCUMENTO.
     * 
     * @return FECHACARGA
     */
    public java.util.Calendar getFECHACARGA() {
        return FECHACARGA;
    }


    /**
     * Sets the FECHACARGA value for this DOCUMENTO.
     * 
     * @param FECHACARGA
     */
    public void setFECHACARGA(java.util.Calendar FECHACARGA) {
        this.FECHACARGA = FECHACARGA;
    }


    /**
     * Gets the FECHACONTABILIZACION value for this DOCUMENTO.
     * 
     * @return FECHACONTABILIZACION
     */
    public java.util.Calendar getFECHACONTABILIZACION() {
        return FECHACONTABILIZACION;
    }


    /**
     * Sets the FECHACONTABILIZACION value for this DOCUMENTO.
     * 
     * @param FECHACONTABILIZACION
     */
    public void setFECHACONTABILIZACION(java.util.Calendar FECHACONTABILIZACION) {
        this.FECHACONTABILIZACION = FECHACONTABILIZACION;
    }


    /**
     * Gets the FECHADEBITOS value for this DOCUMENTO.
     * 
     * @return FECHADEBITOS
     */
    public java.util.Calendar getFECHADEBITOS() {
        return FECHADEBITOS;
    }


    /**
     * Sets the FECHADEBITOS value for this DOCUMENTO.
     * 
     * @param FECHADEBITOS
     */
    public void setFECHADEBITOS(java.util.Calendar FECHADEBITOS) {
        this.FECHADEBITOS = FECHADEBITOS;
    }


    /**
     * Gets the FECHARECEPCION value for this DOCUMENTO.
     * 
     * @return FECHARECEPCION
     */
    public java.util.Calendar getFECHARECEPCION() {
        return FECHARECEPCION;
    }


    /**
     * Sets the FECHARECEPCION value for this DOCUMENTO.
     * 
     * @param FECHARECEPCION
     */
    public void setFECHARECEPCION(java.util.Calendar FECHARECEPCION) {
        this.FECHARECEPCION = FECHARECEPCION;
    }


    /**
     * Gets the FECHAVENCIMIENTO value for this DOCUMENTO.
     * 
     * @return FECHAVENCIMIENTO
     */
    public java.util.Calendar getFECHAVENCIMIENTO() {
        return FECHAVENCIMIENTO;
    }


    /**
     * Sets the FECHAVENCIMIENTO value for this DOCUMENTO.
     * 
     * @param FECHAVENCIMIENTO
     */
    public void setFECHAVENCIMIENTO(java.util.Calendar FECHAVENCIMIENTO) {
        this.FECHAVENCIMIENTO = FECHAVENCIMIENTO;
    }


    /**
     * Gets the ID value for this DOCUMENTO.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this DOCUMENTO.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the LIBERACOMISIONAGENTE value for this DOCUMENTO.
     * 
     * @return LIBERACOMISIONAGENTE
     */
    public java.lang.String getLIBERACOMISIONAGENTE() {
        return LIBERACOMISIONAGENTE;
    }


    /**
     * Sets the LIBERACOMISIONAGENTE value for this DOCUMENTO.
     * 
     * @param LIBERACOMISIONAGENTE
     */
    public void setLIBERACOMISIONAGENTE(java.lang.String LIBERACOMISIONAGENTE) {
        this.LIBERACOMISIONAGENTE = LIBERACOMISIONAGENTE;
    }


    /**
     * Gets the MONEDAID value for this DOCUMENTO.
     * 
     * @return MONEDAID
     */
    public java.lang.String getMONEDAID() {
        return MONEDAID;
    }


    /**
     * Sets the MONEDAID value for this DOCUMENTO.
     * 
     * @param MONEDAID
     */
    public void setMONEDAID(java.lang.String MONEDAID) {
        this.MONEDAID = MONEDAID;
    }


    /**
     * Gets the MOTIVOGENERAL value for this DOCUMENTO.
     * 
     * @return MOTIVOGENERAL
     */
    public java.lang.String getMOTIVOGENERAL() {
        return MOTIVOGENERAL;
    }


    /**
     * Sets the MOTIVOGENERAL value for this DOCUMENTO.
     * 
     * @param MOTIVOGENERAL
     */
    public void setMOTIVOGENERAL(java.lang.String MOTIVOGENERAL) {
        this.MOTIVOGENERAL = MOTIVOGENERAL;
    }


    /**
     * Gets the MOTIVOIFI value for this DOCUMENTO.
     * 
     * @return MOTIVOIFI
     */
    public java.lang.String getMOTIVOIFI() {
        return MOTIVOIFI;
    }


    /**
     * Sets the MOTIVOIFI value for this DOCUMENTO.
     * 
     * @param MOTIVOIFI
     */
    public void setMOTIVOIFI(java.lang.String MOTIVOIFI) {
        this.MOTIVOIFI = MOTIVOIFI;
    }


    /**
     * Gets the NUMERO value for this DOCUMENTO.
     * 
     * @return NUMERO
     */
    public java.lang.String getNUMERO() {
        return NUMERO;
    }


    /**
     * Sets the NUMERO value for this DOCUMENTO.
     * 
     * @param NUMERO
     */
    public void setNUMERO(java.lang.String NUMERO) {
        this.NUMERO = NUMERO;
    }


    /**
     * Gets the NUMEROAUTORIZACION value for this DOCUMENTO.
     * 
     * @return NUMEROAUTORIZACION
     */
    public java.lang.String getNUMEROAUTORIZACION() {
        return NUMEROAUTORIZACION;
    }


    /**
     * Sets the NUMEROAUTORIZACION value for this DOCUMENTO.
     * 
     * @param NUMEROAUTORIZACION
     */
    public void setNUMEROAUTORIZACION(java.lang.String NUMEROAUTORIZACION) {
        this.NUMEROAUTORIZACION = NUMEROAUTORIZACION;
    }


    /**
     * Gets the NUMTRAMITE value for this DOCUMENTO.
     * 
     * @return NUMTRAMITE
     */
    public java.math.BigDecimal getNUMTRAMITE() {
        return NUMTRAMITE;
    }


    /**
     * Sets the NUMTRAMITE value for this DOCUMENTO.
     * 
     * @param NUMTRAMITE
     */
    public void setNUMTRAMITE(java.math.BigDecimal NUMTRAMITE) {
        this.NUMTRAMITE = NUMTRAMITE;
    }


    /**
     * Gets the PUNTOVENTAID value for this DOCUMENTO.
     * 
     * @return PUNTOVENTAID
     */
    public java.lang.String getPUNTOVENTAID() {
        return PUNTOVENTAID;
    }


    /**
     * Sets the PUNTOVENTAID value for this DOCUMENTO.
     * 
     * @param PUNTOVENTAID
     */
    public void setPUNTOVENTAID(java.lang.String PUNTOVENTAID) {
        this.PUNTOVENTAID = PUNTOVENTAID;
    }


    /**
     * Gets the RECORDATORIOID value for this DOCUMENTO.
     * 
     * @return RECORDATORIOID
     */
    public java.lang.String getRECORDATORIOID() {
        return RECORDATORIOID;
    }


    /**
     * Sets the RECORDATORIOID value for this DOCUMENTO.
     * 
     * @param RECORDATORIOID
     */
    public void setRECORDATORIOID(java.lang.String RECORDATORIOID) {
        this.RECORDATORIOID = RECORDATORIOID;
    }


    /**
     * Gets the RESPUESTASRI value for this DOCUMENTO.
     * 
     * @return RESPUESTASRI
     */
    public java.lang.String getRESPUESTASRI() {
        return RESPUESTASRI;
    }


    /**
     * Sets the RESPUESTASRI value for this DOCUMENTO.
     * 
     * @param RESPUESTASRI
     */
    public void setRESPUESTASRI(java.lang.String RESPUESTASRI) {
        this.RESPUESTASRI = RESPUESTASRI;
    }


    /**
     * Gets the RUBROCONTABLE value for this DOCUMENTO.
     * 
     * @return RUBROCONTABLE
     */
    public java.lang.String getRUBROCONTABLE() {
        return RUBROCONTABLE;
    }


    /**
     * Sets the RUBROCONTABLE value for this DOCUMENTO.
     * 
     * @param RUBROCONTABLE
     */
    public void setRUBROCONTABLE(java.lang.String RUBROCONTABLE) {
        this.RUBROCONTABLE = RUBROCONTABLE;
    }


    /**
     * Gets the SALDOEXTRANJERA value for this DOCUMENTO.
     * 
     * @return SALDOEXTRANJERA
     */
    public java.math.BigDecimal getSALDOEXTRANJERA() {
        return SALDOEXTRANJERA;
    }


    /**
     * Sets the SALDOEXTRANJERA value for this DOCUMENTO.
     * 
     * @param SALDOEXTRANJERA
     */
    public void setSALDOEXTRANJERA(java.math.BigDecimal SALDOEXTRANJERA) {
        this.SALDOEXTRANJERA = SALDOEXTRANJERA;
    }


    /**
     * Gets the SALDOLOCAL value for this DOCUMENTO.
     * 
     * @return SALDOLOCAL
     */
    public java.math.BigDecimal getSALDOLOCAL() {
        return SALDOLOCAL;
    }


    /**
     * Sets the SALDOLOCAL value for this DOCUMENTO.
     * 
     * @param SALDOLOCAL
     */
    public void setSALDOLOCAL(java.math.BigDecimal SALDOLOCAL) {
        this.SALDOLOCAL = SALDOLOCAL;
    }


    /**
     * Gets the SALDOORIGEN value for this DOCUMENTO.
     * 
     * @return SALDOORIGEN
     */
    public java.math.BigDecimal getSALDOORIGEN() {
        return SALDOORIGEN;
    }


    /**
     * Sets the SALDOORIGEN value for this DOCUMENTO.
     * 
     * @param SALDOORIGEN
     */
    public void setSALDOORIGEN(java.math.BigDecimal SALDOORIGEN) {
        this.SALDOORIGEN = SALDOORIGEN;
    }


    /**
     * Gets the SUCURSALID value for this DOCUMENTO.
     * 
     * @return SUCURSALID
     */
    public java.lang.String getSUCURSALID() {
        return SUCURSALID;
    }


    /**
     * Sets the SUCURSALID value for this DOCUMENTO.
     * 
     * @param SUCURSALID
     */
    public void setSUCURSALID(java.lang.String SUCURSALID) {
        this.SUCURSALID = SUCURSALID;
    }


    /**
     * Gets the TIPODOCUMENTOID value for this DOCUMENTO.
     * 
     * @return TIPODOCUMENTOID
     */
    public java.lang.String getTIPODOCUMENTOID() {
        return TIPODOCUMENTOID;
    }


    /**
     * Sets the TIPODOCUMENTOID value for this DOCUMENTO.
     * 
     * @param TIPODOCUMENTOID
     */
    public void setTIPODOCUMENTOID(java.lang.String TIPODOCUMENTOID) {
        this.TIPODOCUMENTOID = TIPODOCUMENTOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this DOCUMENTO.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this DOCUMENTO.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the USUARIOCARGA value for this DOCUMENTO.
     * 
     * @return USUARIOCARGA
     */
    public java.lang.String getUSUARIOCARGA() {
        return USUARIOCARGA;
    }


    /**
     * Sets the USUARIOCARGA value for this DOCUMENTO.
     * 
     * @param USUARIOCARGA
     */
    public void setUSUARIOCARGA(java.lang.String USUARIOCARGA) {
        this.USUARIOCARGA = USUARIOCARGA;
    }


    /**
     * Gets the VALOREXTRANJERA value for this DOCUMENTO.
     * 
     * @return VALOREXTRANJERA
     */
    public java.math.BigDecimal getVALOREXTRANJERA() {
        return VALOREXTRANJERA;
    }


    /**
     * Sets the VALOREXTRANJERA value for this DOCUMENTO.
     * 
     * @param VALOREXTRANJERA
     */
    public void setVALOREXTRANJERA(java.math.BigDecimal VALOREXTRANJERA) {
        this.VALOREXTRANJERA = VALOREXTRANJERA;
    }


    /**
     * Gets the VALORLOCAL value for this DOCUMENTO.
     * 
     * @return VALORLOCAL
     */
    public java.math.BigDecimal getVALORLOCAL() {
        return VALORLOCAL;
    }


    /**
     * Sets the VALORLOCAL value for this DOCUMENTO.
     * 
     * @param VALORLOCAL
     */
    public void setVALORLOCAL(java.math.BigDecimal VALORLOCAL) {
        this.VALORLOCAL = VALORLOCAL;
    }


    /**
     * Gets the VALORORDENPAGO value for this DOCUMENTO.
     * 
     * @return VALORORDENPAGO
     */
    public java.math.BigDecimal getVALORORDENPAGO() {
        return VALORORDENPAGO;
    }


    /**
     * Sets the VALORORDENPAGO value for this DOCUMENTO.
     * 
     * @param VALORORDENPAGO
     */
    public void setVALORORDENPAGO(java.math.BigDecimal VALORORDENPAGO) {
        this.VALORORDENPAGO = VALORORDENPAGO;
    }


    /**
     * Gets the VALORORIGEN value for this DOCUMENTO.
     * 
     * @return VALORORIGEN
     */
    public java.math.BigDecimal getVALORORIGEN() {
        return VALORORIGEN;
    }


    /**
     * Sets the VALORORIGEN value for this DOCUMENTO.
     * 
     * @param VALORORIGEN
     */
    public void setVALORORIGEN(java.math.BigDecimal VALORORIGEN) {
        this.VALORORIGEN = VALORORIGEN;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DOCUMENTO)) return false;
        DOCUMENTO other = (DOCUMENTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ASIENTOID==null && other.getASIENTOID()==null) || 
             (this.ASIENTOID!=null &&
              this.ASIENTOID.equals(other.getASIENTOID()))) &&
            ((this.AUTORIZACIONID==null && other.getAUTORIZACIONID()==null) || 
             (this.AUTORIZACIONID!=null &&
              this.AUTORIZACIONID.equals(other.getAUTORIZACIONID()))) &&
            ((this.AUTORIZACIONSRI==null && other.getAUTORIZACIONSRI()==null) || 
             (this.AUTORIZACIONSRI!=null &&
              this.AUTORIZACIONSRI.equals(other.getAUTORIZACIONSRI()))) &&
            ((this.AUTORIZACIONSRIID==null && other.getAUTORIZACIONSRIID()==null) || 
             (this.AUTORIZACIONSRIID!=null &&
              this.AUTORIZACIONSRIID.equals(other.getAUTORIZACIONSRIID()))) &&
            ((this.CAMBIO==null && other.getCAMBIO()==null) || 
             (this.CAMBIO!=null &&
              this.CAMBIO.equals(other.getCAMBIO()))) &&
            ((this.DEBITOENVIADO==null && other.getDEBITOENVIADO()==null) || 
             (this.DEBITOENVIADO!=null &&
              this.DEBITOENVIADO.equals(other.getDEBITOENVIADO()))) &&
            ((this.DEBITOREALIZADO==null && other.getDEBITOREALIZADO()==null) || 
             (this.DEBITOREALIZADO!=null &&
              this.DEBITOREALIZADO.equals(other.getDEBITOREALIZADO()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.DISCRIMINADOR==null && other.getDISCRIMINADOR()==null) || 
             (this.DISCRIMINADOR!=null &&
              this.DISCRIMINADOR.equals(other.getDISCRIMINADOR()))) &&
            ((this.ENTIDAD==null && other.getENTIDAD()==null) || 
             (this.ENTIDAD!=null &&
              this.ENTIDAD.equals(other.getENTIDAD()))) &&
            ((this.ENTIDADReference==null && other.getENTIDADReference()==null) || 
             (this.ENTIDADReference!=null &&
              this.ENTIDADReference.equals(other.getENTIDADReference()))) &&
            ((this.ESANTERIOR==null && other.getESANTERIOR()==null) || 
             (this.ESANTERIOR!=null &&
              this.ESANTERIOR.equals(other.getESANTERIOR()))) &&
            ((this.ESTADOID==null && other.getESTADOID()==null) || 
             (this.ESTADOID!=null &&
              this.ESTADOID.equals(other.getESTADOID()))) &&
            ((this.FECHA==null && other.getFECHA()==null) || 
             (this.FECHA!=null &&
              this.FECHA.equals(other.getFECHA()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FECHACARGA==null && other.getFECHACARGA()==null) || 
             (this.FECHACARGA!=null &&
              this.FECHACARGA.equals(other.getFECHACARGA()))) &&
            ((this.FECHACONTABILIZACION==null && other.getFECHACONTABILIZACION()==null) || 
             (this.FECHACONTABILIZACION!=null &&
              this.FECHACONTABILIZACION.equals(other.getFECHACONTABILIZACION()))) &&
            ((this.FECHADEBITOS==null && other.getFECHADEBITOS()==null) || 
             (this.FECHADEBITOS!=null &&
              this.FECHADEBITOS.equals(other.getFECHADEBITOS()))) &&
            ((this.FECHARECEPCION==null && other.getFECHARECEPCION()==null) || 
             (this.FECHARECEPCION!=null &&
              this.FECHARECEPCION.equals(other.getFECHARECEPCION()))) &&
            ((this.FECHAVENCIMIENTO==null && other.getFECHAVENCIMIENTO()==null) || 
             (this.FECHAVENCIMIENTO!=null &&
              this.FECHAVENCIMIENTO.equals(other.getFECHAVENCIMIENTO()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.LIBERACOMISIONAGENTE==null && other.getLIBERACOMISIONAGENTE()==null) || 
             (this.LIBERACOMISIONAGENTE!=null &&
              this.LIBERACOMISIONAGENTE.equals(other.getLIBERACOMISIONAGENTE()))) &&
            ((this.MONEDAID==null && other.getMONEDAID()==null) || 
             (this.MONEDAID!=null &&
              this.MONEDAID.equals(other.getMONEDAID()))) &&
            ((this.MOTIVOGENERAL==null && other.getMOTIVOGENERAL()==null) || 
             (this.MOTIVOGENERAL!=null &&
              this.MOTIVOGENERAL.equals(other.getMOTIVOGENERAL()))) &&
            ((this.MOTIVOIFI==null && other.getMOTIVOIFI()==null) || 
             (this.MOTIVOIFI!=null &&
              this.MOTIVOIFI.equals(other.getMOTIVOIFI()))) &&
            ((this.NUMERO==null && other.getNUMERO()==null) || 
             (this.NUMERO!=null &&
              this.NUMERO.equals(other.getNUMERO()))) &&
            ((this.NUMEROAUTORIZACION==null && other.getNUMEROAUTORIZACION()==null) || 
             (this.NUMEROAUTORIZACION!=null &&
              this.NUMEROAUTORIZACION.equals(other.getNUMEROAUTORIZACION()))) &&
            ((this.NUMTRAMITE==null && other.getNUMTRAMITE()==null) || 
             (this.NUMTRAMITE!=null &&
              this.NUMTRAMITE.equals(other.getNUMTRAMITE()))) &&
            ((this.PUNTOVENTAID==null && other.getPUNTOVENTAID()==null) || 
             (this.PUNTOVENTAID!=null &&
              this.PUNTOVENTAID.equals(other.getPUNTOVENTAID()))) &&
            ((this.RECORDATORIOID==null && other.getRECORDATORIOID()==null) || 
             (this.RECORDATORIOID!=null &&
              this.RECORDATORIOID.equals(other.getRECORDATORIOID()))) &&
            ((this.RESPUESTASRI==null && other.getRESPUESTASRI()==null) || 
             (this.RESPUESTASRI!=null &&
              this.RESPUESTASRI.equals(other.getRESPUESTASRI()))) &&
            ((this.RUBROCONTABLE==null && other.getRUBROCONTABLE()==null) || 
             (this.RUBROCONTABLE!=null &&
              this.RUBROCONTABLE.equals(other.getRUBROCONTABLE()))) &&
            ((this.SALDOEXTRANJERA==null && other.getSALDOEXTRANJERA()==null) || 
             (this.SALDOEXTRANJERA!=null &&
              this.SALDOEXTRANJERA.equals(other.getSALDOEXTRANJERA()))) &&
            ((this.SALDOLOCAL==null && other.getSALDOLOCAL()==null) || 
             (this.SALDOLOCAL!=null &&
              this.SALDOLOCAL.equals(other.getSALDOLOCAL()))) &&
            ((this.SALDOORIGEN==null && other.getSALDOORIGEN()==null) || 
             (this.SALDOORIGEN!=null &&
              this.SALDOORIGEN.equals(other.getSALDOORIGEN()))) &&
            ((this.SUCURSALID==null && other.getSUCURSALID()==null) || 
             (this.SUCURSALID!=null &&
              this.SUCURSALID.equals(other.getSUCURSALID()))) &&
            ((this.TIPODOCUMENTOID==null && other.getTIPODOCUMENTOID()==null) || 
             (this.TIPODOCUMENTOID!=null &&
              this.TIPODOCUMENTOID.equals(other.getTIPODOCUMENTOID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.USUARIOCARGA==null && other.getUSUARIOCARGA()==null) || 
             (this.USUARIOCARGA!=null &&
              this.USUARIOCARGA.equals(other.getUSUARIOCARGA()))) &&
            ((this.VALOREXTRANJERA==null && other.getVALOREXTRANJERA()==null) || 
             (this.VALOREXTRANJERA!=null &&
              this.VALOREXTRANJERA.equals(other.getVALOREXTRANJERA()))) &&
            ((this.VALORLOCAL==null && other.getVALORLOCAL()==null) || 
             (this.VALORLOCAL!=null &&
              this.VALORLOCAL.equals(other.getVALORLOCAL()))) &&
            ((this.VALORORDENPAGO==null && other.getVALORORDENPAGO()==null) || 
             (this.VALORORDENPAGO!=null &&
              this.VALORORDENPAGO.equals(other.getVALORORDENPAGO()))) &&
            ((this.VALORORIGEN==null && other.getVALORORIGEN()==null) || 
             (this.VALORORIGEN!=null &&
              this.VALORORIGEN.equals(other.getVALORORIGEN())));
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
        if (getASIENTOID() != null) {
            _hashCode += getASIENTOID().hashCode();
        }
        if (getAUTORIZACIONID() != null) {
            _hashCode += getAUTORIZACIONID().hashCode();
        }
        if (getAUTORIZACIONSRI() != null) {
            _hashCode += getAUTORIZACIONSRI().hashCode();
        }
        if (getAUTORIZACIONSRIID() != null) {
            _hashCode += getAUTORIZACIONSRIID().hashCode();
        }
        if (getCAMBIO() != null) {
            _hashCode += getCAMBIO().hashCode();
        }
        if (getDEBITOENVIADO() != null) {
            _hashCode += getDEBITOENVIADO().hashCode();
        }
        if (getDEBITOREALIZADO() != null) {
            _hashCode += getDEBITOREALIZADO().hashCode();
        }
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getDISCRIMINADOR() != null) {
            _hashCode += getDISCRIMINADOR().hashCode();
        }
        if (getENTIDAD() != null) {
            _hashCode += getENTIDAD().hashCode();
        }
        if (getENTIDADReference() != null) {
            _hashCode += getENTIDADReference().hashCode();
        }
        if (getESANTERIOR() != null) {
            _hashCode += getESANTERIOR().hashCode();
        }
        if (getESTADOID() != null) {
            _hashCode += getESTADOID().hashCode();
        }
        if (getFECHA() != null) {
            _hashCode += getFECHA().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFECHACARGA() != null) {
            _hashCode += getFECHACARGA().hashCode();
        }
        if (getFECHACONTABILIZACION() != null) {
            _hashCode += getFECHACONTABILIZACION().hashCode();
        }
        if (getFECHADEBITOS() != null) {
            _hashCode += getFECHADEBITOS().hashCode();
        }
        if (getFECHARECEPCION() != null) {
            _hashCode += getFECHARECEPCION().hashCode();
        }
        if (getFECHAVENCIMIENTO() != null) {
            _hashCode += getFECHAVENCIMIENTO().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getLIBERACOMISIONAGENTE() != null) {
            _hashCode += getLIBERACOMISIONAGENTE().hashCode();
        }
        if (getMONEDAID() != null) {
            _hashCode += getMONEDAID().hashCode();
        }
        if (getMOTIVOGENERAL() != null) {
            _hashCode += getMOTIVOGENERAL().hashCode();
        }
        if (getMOTIVOIFI() != null) {
            _hashCode += getMOTIVOIFI().hashCode();
        }
        if (getNUMERO() != null) {
            _hashCode += getNUMERO().hashCode();
        }
        if (getNUMEROAUTORIZACION() != null) {
            _hashCode += getNUMEROAUTORIZACION().hashCode();
        }
        if (getNUMTRAMITE() != null) {
            _hashCode += getNUMTRAMITE().hashCode();
        }
        if (getPUNTOVENTAID() != null) {
            _hashCode += getPUNTOVENTAID().hashCode();
        }
        if (getRECORDATORIOID() != null) {
            _hashCode += getRECORDATORIOID().hashCode();
        }
        if (getRESPUESTASRI() != null) {
            _hashCode += getRESPUESTASRI().hashCode();
        }
        if (getRUBROCONTABLE() != null) {
            _hashCode += getRUBROCONTABLE().hashCode();
        }
        if (getSALDOEXTRANJERA() != null) {
            _hashCode += getSALDOEXTRANJERA().hashCode();
        }
        if (getSALDOLOCAL() != null) {
            _hashCode += getSALDOLOCAL().hashCode();
        }
        if (getSALDOORIGEN() != null) {
            _hashCode += getSALDOORIGEN().hashCode();
        }
        if (getSUCURSALID() != null) {
            _hashCode += getSUCURSALID().hashCode();
        }
        if (getTIPODOCUMENTOID() != null) {
            _hashCode += getTIPODOCUMENTOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getUSUARIOCARGA() != null) {
            _hashCode += getUSUARIOCARGA().hashCode();
        }
        if (getVALOREXTRANJERA() != null) {
            _hashCode += getVALOREXTRANJERA().hashCode();
        }
        if (getVALORLOCAL() != null) {
            _hashCode += getVALORLOCAL().hashCode();
        }
        if (getVALORORDENPAGO() != null) {
            _hashCode += getVALORORDENPAGO().hashCode();
        }
        if (getVALORORIGEN() != null) {
            _hashCode += getVALORORIGEN().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DOCUMENTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ASIENTOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ASIENTOID"));
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
        elemField.setFieldName("AUTORIZACIONSRI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONSRI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AUTORIZACIONSRIID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AUTORIZACIONSRIID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CAMBIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CAMBIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBITOENVIADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEBITOENVIADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEBITOREALIZADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DEBITOREALIZADO"));
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
        elemField.setFieldName("DISCRIMINADOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DISCRIMINADOR"));
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
        elemField.setFieldName("ENTIDADReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENTIDADFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESANTERIOR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESANTERIOR"));
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
        elemField.setFieldName("FECHA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAACTUALIZA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAACTUALIZA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACARGA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACARGA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHACONTABILIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHACONTABILIZACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHADEBITOS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHADEBITOS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHARECEPCION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHARECEPCION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FECHAVENCIMIENTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FECHAVENCIMIENTO"));
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
        elemField.setFieldName("LIBERACOMISIONAGENTE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "LIBERACOMISIONAGENTE"));
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
        elemField.setFieldName("MOTIVOGENERAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOGENERAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOTIVOIFI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "MOTIVOIFI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMERO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMERO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMEROAUTORIZACION");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROAUTORIZACION"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NUMTRAMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMTRAMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("RECORDATORIOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RECORDATORIOID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RESPUESTASRI");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RESPUESTASRI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RUBROCONTABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RUBROCONTABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDOEXTRANJERA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SALDOEXTRANJERA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDOLOCAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SALDOLOCAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SALDOORIGEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SALDOORIGEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("TIPODOCUMENTOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPODOCUMENTOID"));
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
        elemField.setFieldName("USUARIOCARGA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "USUARIOCARGA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALOREXTRANJERA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALOREXTRANJERA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORLOCAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORLOCAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORORDENPAGO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORORDENPAGO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORORIGEN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORORIGEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
