/**
 * ENDOSOITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance;

public class ENDOSOITEM  extends org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject  implements java.io.Serializable {
    private java.lang.String ACLARATORIO;

    private java.lang.String ASISTENCIAID;

    private java.lang.String AUTORIZACIONID;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO;

    private java.lang.Integer CANTIDAD;

    private java.lang.Integer CANTIDADREBAJA;

    private java.lang.String CLASERIESGOID;

    private java.lang.String DESCRIPCION;

    private java.lang.String DESCRIPCIONITEM;

    private org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO ENDOSO;

    private org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOFG7C7FF7 ENDOSOReference;

    private java.lang.String ESAUDITABLE;

    private java.lang.String ESBENE;

    private java.lang.String ESTADOID;

    private java.util.Calendar FECHAACTUALIZA;

    private java.math.BigDecimal FONSAT;

    private java.lang.String ID;

    private java.lang.String ITEMID;

    private java.lang.String NOMBRE;

    private java.lang.Integer NUMEROITEM;

    private java.lang.String PLANTILLAID;

    private java.math.BigDecimal PORCENTAJEPRIMAINCENDIO;

    private java.math.BigDecimal PRIMANETAAM;

    private java.lang.String SISUMA;

    private java.math.BigDecimal TASAINCENDIO;

    private java.lang.String TIPOITEMID;

    private java.lang.String TIPORIESGOID;

    private java.lang.String USUARIOACTUALIZA;

    private java.math.BigDecimal VAL1;

    private java.math.BigDecimal VAL2;

    private java.math.BigDecimal VAL3;

    private java.math.BigDecimal VAL4;

    private java.math.BigDecimal VALLIMITE;

    private java.math.BigDecimal VALORASEGURADO;

    private java.math.BigDecimal VALORITEM;

    private java.math.BigDecimal VALORPRIMAINCENDIO;

    private java.math.BigDecimal VALORPRIMANETA;

    private java.math.BigDecimal VALORUNITARIO;

    public ENDOSOITEM() {
    }

    public ENDOSOITEM(
           org.apache.axis.types.Id id,
           org.apache.axis.types.IDRef ref,
           org.datacontract.schemas._2004._07.System_Data.EntityKey entityKey,
           java.lang.String ACLARATORIO,
           java.lang.String ASISTENCIAID,
           java.lang.String AUTORIZACIONID,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO,
           java.lang.Integer CANTIDAD,
           java.lang.Integer CANTIDADREBAJA,
           java.lang.String CLASERIESGOID,
           java.lang.String DESCRIPCION,
           java.lang.String DESCRIPCIONITEM,
           org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO ENDOSO,
           org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOFG7C7FF7 ENDOSOReference,
           java.lang.String ESAUDITABLE,
           java.lang.String ESBENE,
           java.lang.String ESTADOID,
           java.util.Calendar FECHAACTUALIZA,
           java.math.BigDecimal FONSAT,
           java.lang.String ID,
           java.lang.String ITEMID,
           java.lang.String NOMBRE,
           java.lang.Integer NUMEROITEM,
           java.lang.String PLANTILLAID,
           java.math.BigDecimal PORCENTAJEPRIMAINCENDIO,
           java.math.BigDecimal PRIMANETAAM,
           java.lang.String SISUMA,
           java.math.BigDecimal TASAINCENDIO,
           java.lang.String TIPOITEMID,
           java.lang.String TIPORIESGOID,
           java.lang.String USUARIOACTUALIZA,
           java.math.BigDecimal VAL1,
           java.math.BigDecimal VAL2,
           java.math.BigDecimal VAL3,
           java.math.BigDecimal VAL4,
           java.math.BigDecimal VALLIMITE,
           java.math.BigDecimal VALORASEGURADO,
           java.math.BigDecimal VALORITEM,
           java.math.BigDecimal VALORPRIMAINCENDIO,
           java.math.BigDecimal VALORPRIMANETA,
           java.math.BigDecimal VALORUNITARIO) {
        super(
            id,
            ref,
            entityKey);
        this.ACLARATORIO = ACLARATORIO;
        this.ASISTENCIAID = ASISTENCIAID;
        this.AUTORIZACIONID = AUTORIZACIONID;
        this.BENEFICIARIO = BENEFICIARIO;
        this.CANTIDAD = CANTIDAD;
        this.CANTIDADREBAJA = CANTIDADREBAJA;
        this.CLASERIESGOID = CLASERIESGOID;
        this.DESCRIPCION = DESCRIPCION;
        this.DESCRIPCIONITEM = DESCRIPCIONITEM;
        this.ENDOSO = ENDOSO;
        this.ENDOSOReference = ENDOSOReference;
        this.ESAUDITABLE = ESAUDITABLE;
        this.ESBENE = ESBENE;
        this.ESTADOID = ESTADOID;
        this.FECHAACTUALIZA = FECHAACTUALIZA;
        this.FONSAT = FONSAT;
        this.ID = ID;
        this.ITEMID = ITEMID;
        this.NOMBRE = NOMBRE;
        this.NUMEROITEM = NUMEROITEM;
        this.PLANTILLAID = PLANTILLAID;
        this.PORCENTAJEPRIMAINCENDIO = PORCENTAJEPRIMAINCENDIO;
        this.PRIMANETAAM = PRIMANETAAM;
        this.SISUMA = SISUMA;
        this.TASAINCENDIO = TASAINCENDIO;
        this.TIPOITEMID = TIPOITEMID;
        this.TIPORIESGOID = TIPORIESGOID;
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
        this.VAL1 = VAL1;
        this.VAL2 = VAL2;
        this.VAL3 = VAL3;
        this.VAL4 = VAL4;
        this.VALLIMITE = VALLIMITE;
        this.VALORASEGURADO = VALORASEGURADO;
        this.VALORITEM = VALORITEM;
        this.VALORPRIMAINCENDIO = VALORPRIMAINCENDIO;
        this.VALORPRIMANETA = VALORPRIMANETA;
        this.VALORUNITARIO = VALORUNITARIO;
    }


    /**
     * Gets the ACLARATORIO value for this ENDOSOITEM.
     * 
     * @return ACLARATORIO
     */
    public java.lang.String getACLARATORIO() {
        return ACLARATORIO;
    }


    /**
     * Sets the ACLARATORIO value for this ENDOSOITEM.
     * 
     * @param ACLARATORIO
     */
    public void setACLARATORIO(java.lang.String ACLARATORIO) {
        this.ACLARATORIO = ACLARATORIO;
    }


    /**
     * Gets the ASISTENCIAID value for this ENDOSOITEM.
     * 
     * @return ASISTENCIAID
     */
    public java.lang.String getASISTENCIAID() {
        return ASISTENCIAID;
    }


    /**
     * Sets the ASISTENCIAID value for this ENDOSOITEM.
     * 
     * @param ASISTENCIAID
     */
    public void setASISTENCIAID(java.lang.String ASISTENCIAID) {
        this.ASISTENCIAID = ASISTENCIAID;
    }


    /**
     * Gets the AUTORIZACIONID value for this ENDOSOITEM.
     * 
     * @return AUTORIZACIONID
     */
    public java.lang.String getAUTORIZACIONID() {
        return AUTORIZACIONID;
    }


    /**
     * Sets the AUTORIZACIONID value for this ENDOSOITEM.
     * 
     * @param AUTORIZACIONID
     */
    public void setAUTORIZACIONID(java.lang.String AUTORIZACIONID) {
        this.AUTORIZACIONID = AUTORIZACIONID;
    }


    /**
     * Gets the BENEFICIARIO value for this ENDOSOITEM.
     * 
     * @return BENEFICIARIO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] getBENEFICIARIO() {
        return BENEFICIARIO;
    }


    /**
     * Sets the BENEFICIARIO value for this ENDOSOITEM.
     * 
     * @param BENEFICIARIO
     */
    public void setBENEFICIARIO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[] BENEFICIARIO) {
        this.BENEFICIARIO = BENEFICIARIO;
    }


    /**
     * Gets the CANTIDAD value for this ENDOSOITEM.
     * 
     * @return CANTIDAD
     */
    public java.lang.Integer getCANTIDAD() {
        return CANTIDAD;
    }


    /**
     * Sets the CANTIDAD value for this ENDOSOITEM.
     * 
     * @param CANTIDAD
     */
    public void setCANTIDAD(java.lang.Integer CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }


    /**
     * Gets the CANTIDADREBAJA value for this ENDOSOITEM.
     * 
     * @return CANTIDADREBAJA
     */
    public java.lang.Integer getCANTIDADREBAJA() {
        return CANTIDADREBAJA;
    }


    /**
     * Sets the CANTIDADREBAJA value for this ENDOSOITEM.
     * 
     * @param CANTIDADREBAJA
     */
    public void setCANTIDADREBAJA(java.lang.Integer CANTIDADREBAJA) {
        this.CANTIDADREBAJA = CANTIDADREBAJA;
    }


    /**
     * Gets the CLASERIESGOID value for this ENDOSOITEM.
     * 
     * @return CLASERIESGOID
     */
    public java.lang.String getCLASERIESGOID() {
        return CLASERIESGOID;
    }


    /**
     * Sets the CLASERIESGOID value for this ENDOSOITEM.
     * 
     * @param CLASERIESGOID
     */
    public void setCLASERIESGOID(java.lang.String CLASERIESGOID) {
        this.CLASERIESGOID = CLASERIESGOID;
    }


    /**
     * Gets the DESCRIPCION value for this ENDOSOITEM.
     * 
     * @return DESCRIPCION
     */
    public java.lang.String getDESCRIPCION() {
        return DESCRIPCION;
    }


    /**
     * Sets the DESCRIPCION value for this ENDOSOITEM.
     * 
     * @param DESCRIPCION
     */
    public void setDESCRIPCION(java.lang.String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }


    /**
     * Gets the DESCRIPCIONITEM value for this ENDOSOITEM.
     * 
     * @return DESCRIPCIONITEM
     */
    public java.lang.String getDESCRIPCIONITEM() {
        return DESCRIPCIONITEM;
    }


    /**
     * Sets the DESCRIPCIONITEM value for this ENDOSOITEM.
     * 
     * @param DESCRIPCIONITEM
     */
    public void setDESCRIPCIONITEM(java.lang.String DESCRIPCIONITEM) {
        this.DESCRIPCIONITEM = DESCRIPCIONITEM;
    }


    /**
     * Gets the ENDOSO value for this ENDOSOITEM.
     * 
     * @return ENDOSO
     */
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO getENDOSO() {
        return ENDOSO;
    }


    /**
     * Sets the ENDOSO value for this ENDOSOITEM.
     * 
     * @param ENDOSO
     */
    public void setENDOSO(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO ENDOSO) {
        this.ENDOSO = ENDOSO;
    }


    /**
     * Gets the ENDOSOReference value for this ENDOSOITEM.
     * 
     * @return ENDOSOReference
     */
    public org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOFG7C7FF7 getENDOSOReference() {
        return ENDOSOReference;
    }


    /**
     * Sets the ENDOSOReference value for this ENDOSOITEM.
     * 
     * @param ENDOSOReference
     */
    public void setENDOSOReference(org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOFG7C7FF7 ENDOSOReference) {
        this.ENDOSOReference = ENDOSOReference;
    }


    /**
     * Gets the ESAUDITABLE value for this ENDOSOITEM.
     * 
     * @return ESAUDITABLE
     */
    public java.lang.String getESAUDITABLE() {
        return ESAUDITABLE;
    }


    /**
     * Sets the ESAUDITABLE value for this ENDOSOITEM.
     * 
     * @param ESAUDITABLE
     */
    public void setESAUDITABLE(java.lang.String ESAUDITABLE) {
        this.ESAUDITABLE = ESAUDITABLE;
    }


    /**
     * Gets the ESBENE value for this ENDOSOITEM.
     * 
     * @return ESBENE
     */
    public java.lang.String getESBENE() {
        return ESBENE;
    }


    /**
     * Sets the ESBENE value for this ENDOSOITEM.
     * 
     * @param ESBENE
     */
    public void setESBENE(java.lang.String ESBENE) {
        this.ESBENE = ESBENE;
    }


    /**
     * Gets the ESTADOID value for this ENDOSOITEM.
     * 
     * @return ESTADOID
     */
    public java.lang.String getESTADOID() {
        return ESTADOID;
    }


    /**
     * Sets the ESTADOID value for this ENDOSOITEM.
     * 
     * @param ESTADOID
     */
    public void setESTADOID(java.lang.String ESTADOID) {
        this.ESTADOID = ESTADOID;
    }


    /**
     * Gets the FECHAACTUALIZA value for this ENDOSOITEM.
     * 
     * @return FECHAACTUALIZA
     */
    public java.util.Calendar getFECHAACTUALIZA() {
        return FECHAACTUALIZA;
    }


    /**
     * Sets the FECHAACTUALIZA value for this ENDOSOITEM.
     * 
     * @param FECHAACTUALIZA
     */
    public void setFECHAACTUALIZA(java.util.Calendar FECHAACTUALIZA) {
        this.FECHAACTUALIZA = FECHAACTUALIZA;
    }


    /**
     * Gets the FONSAT value for this ENDOSOITEM.
     * 
     * @return FONSAT
     */
    public java.math.BigDecimal getFONSAT() {
        return FONSAT;
    }


    /**
     * Sets the FONSAT value for this ENDOSOITEM.
     * 
     * @param FONSAT
     */
    public void setFONSAT(java.math.BigDecimal FONSAT) {
        this.FONSAT = FONSAT;
    }


    /**
     * Gets the ID value for this ENDOSOITEM.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ENDOSOITEM.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the ITEMID value for this ENDOSOITEM.
     * 
     * @return ITEMID
     */
    public java.lang.String getITEMID() {
        return ITEMID;
    }


    /**
     * Sets the ITEMID value for this ENDOSOITEM.
     * 
     * @param ITEMID
     */
    public void setITEMID(java.lang.String ITEMID) {
        this.ITEMID = ITEMID;
    }


    /**
     * Gets the NOMBRE value for this ENDOSOITEM.
     * 
     * @return NOMBRE
     */
    public java.lang.String getNOMBRE() {
        return NOMBRE;
    }


    /**
     * Sets the NOMBRE value for this ENDOSOITEM.
     * 
     * @param NOMBRE
     */
    public void setNOMBRE(java.lang.String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }


    /**
     * Gets the NUMEROITEM value for this ENDOSOITEM.
     * 
     * @return NUMEROITEM
     */
    public java.lang.Integer getNUMEROITEM() {
        return NUMEROITEM;
    }


    /**
     * Sets the NUMEROITEM value for this ENDOSOITEM.
     * 
     * @param NUMEROITEM
     */
    public void setNUMEROITEM(java.lang.Integer NUMEROITEM) {
        this.NUMEROITEM = NUMEROITEM;
    }


    /**
     * Gets the PLANTILLAID value for this ENDOSOITEM.
     * 
     * @return PLANTILLAID
     */
    public java.lang.String getPLANTILLAID() {
        return PLANTILLAID;
    }


    /**
     * Sets the PLANTILLAID value for this ENDOSOITEM.
     * 
     * @param PLANTILLAID
     */
    public void setPLANTILLAID(java.lang.String PLANTILLAID) {
        this.PLANTILLAID = PLANTILLAID;
    }


    /**
     * Gets the PORCENTAJEPRIMAINCENDIO value for this ENDOSOITEM.
     * 
     * @return PORCENTAJEPRIMAINCENDIO
     */
    public java.math.BigDecimal getPORCENTAJEPRIMAINCENDIO() {
        return PORCENTAJEPRIMAINCENDIO;
    }


    /**
     * Sets the PORCENTAJEPRIMAINCENDIO value for this ENDOSOITEM.
     * 
     * @param PORCENTAJEPRIMAINCENDIO
     */
    public void setPORCENTAJEPRIMAINCENDIO(java.math.BigDecimal PORCENTAJEPRIMAINCENDIO) {
        this.PORCENTAJEPRIMAINCENDIO = PORCENTAJEPRIMAINCENDIO;
    }


    /**
     * Gets the PRIMANETAAM value for this ENDOSOITEM.
     * 
     * @return PRIMANETAAM
     */
    public java.math.BigDecimal getPRIMANETAAM() {
        return PRIMANETAAM;
    }


    /**
     * Sets the PRIMANETAAM value for this ENDOSOITEM.
     * 
     * @param PRIMANETAAM
     */
    public void setPRIMANETAAM(java.math.BigDecimal PRIMANETAAM) {
        this.PRIMANETAAM = PRIMANETAAM;
    }


    /**
     * Gets the SISUMA value for this ENDOSOITEM.
     * 
     * @return SISUMA
     */
    public java.lang.String getSISUMA() {
        return SISUMA;
    }


    /**
     * Sets the SISUMA value for this ENDOSOITEM.
     * 
     * @param SISUMA
     */
    public void setSISUMA(java.lang.String SISUMA) {
        this.SISUMA = SISUMA;
    }


    /**
     * Gets the TASAINCENDIO value for this ENDOSOITEM.
     * 
     * @return TASAINCENDIO
     */
    public java.math.BigDecimal getTASAINCENDIO() {
        return TASAINCENDIO;
    }


    /**
     * Sets the TASAINCENDIO value for this ENDOSOITEM.
     * 
     * @param TASAINCENDIO
     */
    public void setTASAINCENDIO(java.math.BigDecimal TASAINCENDIO) {
        this.TASAINCENDIO = TASAINCENDIO;
    }


    /**
     * Gets the TIPOITEMID value for this ENDOSOITEM.
     * 
     * @return TIPOITEMID
     */
    public java.lang.String getTIPOITEMID() {
        return TIPOITEMID;
    }


    /**
     * Sets the TIPOITEMID value for this ENDOSOITEM.
     * 
     * @param TIPOITEMID
     */
    public void setTIPOITEMID(java.lang.String TIPOITEMID) {
        this.TIPOITEMID = TIPOITEMID;
    }


    /**
     * Gets the TIPORIESGOID value for this ENDOSOITEM.
     * 
     * @return TIPORIESGOID
     */
    public java.lang.String getTIPORIESGOID() {
        return TIPORIESGOID;
    }


    /**
     * Sets the TIPORIESGOID value for this ENDOSOITEM.
     * 
     * @param TIPORIESGOID
     */
    public void setTIPORIESGOID(java.lang.String TIPORIESGOID) {
        this.TIPORIESGOID = TIPORIESGOID;
    }


    /**
     * Gets the USUARIOACTUALIZA value for this ENDOSOITEM.
     * 
     * @return USUARIOACTUALIZA
     */
    public java.lang.String getUSUARIOACTUALIZA() {
        return USUARIOACTUALIZA;
    }


    /**
     * Sets the USUARIOACTUALIZA value for this ENDOSOITEM.
     * 
     * @param USUARIOACTUALIZA
     */
    public void setUSUARIOACTUALIZA(java.lang.String USUARIOACTUALIZA) {
        this.USUARIOACTUALIZA = USUARIOACTUALIZA;
    }


    /**
     * Gets the VAL1 value for this ENDOSOITEM.
     * 
     * @return VAL1
     */
    public java.math.BigDecimal getVAL1() {
        return VAL1;
    }


    /**
     * Sets the VAL1 value for this ENDOSOITEM.
     * 
     * @param VAL1
     */
    public void setVAL1(java.math.BigDecimal VAL1) {
        this.VAL1 = VAL1;
    }


    /**
     * Gets the VAL2 value for this ENDOSOITEM.
     * 
     * @return VAL2
     */
    public java.math.BigDecimal getVAL2() {
        return VAL2;
    }


    /**
     * Sets the VAL2 value for this ENDOSOITEM.
     * 
     * @param VAL2
     */
    public void setVAL2(java.math.BigDecimal VAL2) {
        this.VAL2 = VAL2;
    }


    /**
     * Gets the VAL3 value for this ENDOSOITEM.
     * 
     * @return VAL3
     */
    public java.math.BigDecimal getVAL3() {
        return VAL3;
    }


    /**
     * Sets the VAL3 value for this ENDOSOITEM.
     * 
     * @param VAL3
     */
    public void setVAL3(java.math.BigDecimal VAL3) {
        this.VAL3 = VAL3;
    }


    /**
     * Gets the VAL4 value for this ENDOSOITEM.
     * 
     * @return VAL4
     */
    public java.math.BigDecimal getVAL4() {
        return VAL4;
    }


    /**
     * Sets the VAL4 value for this ENDOSOITEM.
     * 
     * @param VAL4
     */
    public void setVAL4(java.math.BigDecimal VAL4) {
        this.VAL4 = VAL4;
    }


    /**
     * Gets the VALLIMITE value for this ENDOSOITEM.
     * 
     * @return VALLIMITE
     */
    public java.math.BigDecimal getVALLIMITE() {
        return VALLIMITE;
    }


    /**
     * Sets the VALLIMITE value for this ENDOSOITEM.
     * 
     * @param VALLIMITE
     */
    public void setVALLIMITE(java.math.BigDecimal VALLIMITE) {
        this.VALLIMITE = VALLIMITE;
    }


    /**
     * Gets the VALORASEGURADO value for this ENDOSOITEM.
     * 
     * @return VALORASEGURADO
     */
    public java.math.BigDecimal getVALORASEGURADO() {
        return VALORASEGURADO;
    }


    /**
     * Sets the VALORASEGURADO value for this ENDOSOITEM.
     * 
     * @param VALORASEGURADO
     */
    public void setVALORASEGURADO(java.math.BigDecimal VALORASEGURADO) {
        this.VALORASEGURADO = VALORASEGURADO;
    }


    /**
     * Gets the VALORITEM value for this ENDOSOITEM.
     * 
     * @return VALORITEM
     */
    public java.math.BigDecimal getVALORITEM() {
        return VALORITEM;
    }


    /**
     * Sets the VALORITEM value for this ENDOSOITEM.
     * 
     * @param VALORITEM
     */
    public void setVALORITEM(java.math.BigDecimal VALORITEM) {
        this.VALORITEM = VALORITEM;
    }


    /**
     * Gets the VALORPRIMAINCENDIO value for this ENDOSOITEM.
     * 
     * @return VALORPRIMAINCENDIO
     */
    public java.math.BigDecimal getVALORPRIMAINCENDIO() {
        return VALORPRIMAINCENDIO;
    }


    /**
     * Sets the VALORPRIMAINCENDIO value for this ENDOSOITEM.
     * 
     * @param VALORPRIMAINCENDIO
     */
    public void setVALORPRIMAINCENDIO(java.math.BigDecimal VALORPRIMAINCENDIO) {
        this.VALORPRIMAINCENDIO = VALORPRIMAINCENDIO;
    }


    /**
     * Gets the VALORPRIMANETA value for this ENDOSOITEM.
     * 
     * @return VALORPRIMANETA
     */
    public java.math.BigDecimal getVALORPRIMANETA() {
        return VALORPRIMANETA;
    }


    /**
     * Sets the VALORPRIMANETA value for this ENDOSOITEM.
     * 
     * @param VALORPRIMANETA
     */
    public void setVALORPRIMANETA(java.math.BigDecimal VALORPRIMANETA) {
        this.VALORPRIMANETA = VALORPRIMANETA;
    }


    /**
     * Gets the VALORUNITARIO value for this ENDOSOITEM.
     * 
     * @return VALORUNITARIO
     */
    public java.math.BigDecimal getVALORUNITARIO() {
        return VALORUNITARIO;
    }


    /**
     * Sets the VALORUNITARIO value for this ENDOSOITEM.
     * 
     * @param VALORUNITARIO
     */
    public void setVALORUNITARIO(java.math.BigDecimal VALORUNITARIO) {
        this.VALORUNITARIO = VALORUNITARIO;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ENDOSOITEM)) return false;
        ENDOSOITEM other = (ENDOSOITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ACLARATORIO==null && other.getACLARATORIO()==null) || 
             (this.ACLARATORIO!=null &&
              this.ACLARATORIO.equals(other.getACLARATORIO()))) &&
            ((this.ASISTENCIAID==null && other.getASISTENCIAID()==null) || 
             (this.ASISTENCIAID!=null &&
              this.ASISTENCIAID.equals(other.getASISTENCIAID()))) &&
            ((this.AUTORIZACIONID==null && other.getAUTORIZACIONID()==null) || 
             (this.AUTORIZACIONID!=null &&
              this.AUTORIZACIONID.equals(other.getAUTORIZACIONID()))) &&
            ((this.BENEFICIARIO==null && other.getBENEFICIARIO()==null) || 
             (this.BENEFICIARIO!=null &&
              java.util.Arrays.equals(this.BENEFICIARIO, other.getBENEFICIARIO()))) &&
            ((this.CANTIDAD==null && other.getCANTIDAD()==null) || 
             (this.CANTIDAD!=null &&
              this.CANTIDAD.equals(other.getCANTIDAD()))) &&
            ((this.CANTIDADREBAJA==null && other.getCANTIDADREBAJA()==null) || 
             (this.CANTIDADREBAJA!=null &&
              this.CANTIDADREBAJA.equals(other.getCANTIDADREBAJA()))) &&
            ((this.CLASERIESGOID==null && other.getCLASERIESGOID()==null) || 
             (this.CLASERIESGOID!=null &&
              this.CLASERIESGOID.equals(other.getCLASERIESGOID()))) &&
            ((this.DESCRIPCION==null && other.getDESCRIPCION()==null) || 
             (this.DESCRIPCION!=null &&
              this.DESCRIPCION.equals(other.getDESCRIPCION()))) &&
            ((this.DESCRIPCIONITEM==null && other.getDESCRIPCIONITEM()==null) || 
             (this.DESCRIPCIONITEM!=null &&
              this.DESCRIPCIONITEM.equals(other.getDESCRIPCIONITEM()))) &&
            ((this.ENDOSO==null && other.getENDOSO()==null) || 
             (this.ENDOSO!=null &&
              this.ENDOSO.equals(other.getENDOSO()))) &&
            ((this.ENDOSOReference==null && other.getENDOSOReference()==null) || 
             (this.ENDOSOReference!=null &&
              this.ENDOSOReference.equals(other.getENDOSOReference()))) &&
            ((this.ESAUDITABLE==null && other.getESAUDITABLE()==null) || 
             (this.ESAUDITABLE!=null &&
              this.ESAUDITABLE.equals(other.getESAUDITABLE()))) &&
            ((this.ESBENE==null && other.getESBENE()==null) || 
             (this.ESBENE!=null &&
              this.ESBENE.equals(other.getESBENE()))) &&
            ((this.ESTADOID==null && other.getESTADOID()==null) || 
             (this.ESTADOID!=null &&
              this.ESTADOID.equals(other.getESTADOID()))) &&
            ((this.FECHAACTUALIZA==null && other.getFECHAACTUALIZA()==null) || 
             (this.FECHAACTUALIZA!=null &&
              this.FECHAACTUALIZA.equals(other.getFECHAACTUALIZA()))) &&
            ((this.FONSAT==null && other.getFONSAT()==null) || 
             (this.FONSAT!=null &&
              this.FONSAT.equals(other.getFONSAT()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.ITEMID==null && other.getITEMID()==null) || 
             (this.ITEMID!=null &&
              this.ITEMID.equals(other.getITEMID()))) &&
            ((this.NOMBRE==null && other.getNOMBRE()==null) || 
             (this.NOMBRE!=null &&
              this.NOMBRE.equals(other.getNOMBRE()))) &&
            ((this.NUMEROITEM==null && other.getNUMEROITEM()==null) || 
             (this.NUMEROITEM!=null &&
              this.NUMEROITEM.equals(other.getNUMEROITEM()))) &&
            ((this.PLANTILLAID==null && other.getPLANTILLAID()==null) || 
             (this.PLANTILLAID!=null &&
              this.PLANTILLAID.equals(other.getPLANTILLAID()))) &&
            ((this.PORCENTAJEPRIMAINCENDIO==null && other.getPORCENTAJEPRIMAINCENDIO()==null) || 
             (this.PORCENTAJEPRIMAINCENDIO!=null &&
              this.PORCENTAJEPRIMAINCENDIO.equals(other.getPORCENTAJEPRIMAINCENDIO()))) &&
            ((this.PRIMANETAAM==null && other.getPRIMANETAAM()==null) || 
             (this.PRIMANETAAM!=null &&
              this.PRIMANETAAM.equals(other.getPRIMANETAAM()))) &&
            ((this.SISUMA==null && other.getSISUMA()==null) || 
             (this.SISUMA!=null &&
              this.SISUMA.equals(other.getSISUMA()))) &&
            ((this.TASAINCENDIO==null && other.getTASAINCENDIO()==null) || 
             (this.TASAINCENDIO!=null &&
              this.TASAINCENDIO.equals(other.getTASAINCENDIO()))) &&
            ((this.TIPOITEMID==null && other.getTIPOITEMID()==null) || 
             (this.TIPOITEMID!=null &&
              this.TIPOITEMID.equals(other.getTIPOITEMID()))) &&
            ((this.TIPORIESGOID==null && other.getTIPORIESGOID()==null) || 
             (this.TIPORIESGOID!=null &&
              this.TIPORIESGOID.equals(other.getTIPORIESGOID()))) &&
            ((this.USUARIOACTUALIZA==null && other.getUSUARIOACTUALIZA()==null) || 
             (this.USUARIOACTUALIZA!=null &&
              this.USUARIOACTUALIZA.equals(other.getUSUARIOACTUALIZA()))) &&
            ((this.VAL1==null && other.getVAL1()==null) || 
             (this.VAL1!=null &&
              this.VAL1.equals(other.getVAL1()))) &&
            ((this.VAL2==null && other.getVAL2()==null) || 
             (this.VAL2!=null &&
              this.VAL2.equals(other.getVAL2()))) &&
            ((this.VAL3==null && other.getVAL3()==null) || 
             (this.VAL3!=null &&
              this.VAL3.equals(other.getVAL3()))) &&
            ((this.VAL4==null && other.getVAL4()==null) || 
             (this.VAL4!=null &&
              this.VAL4.equals(other.getVAL4()))) &&
            ((this.VALLIMITE==null && other.getVALLIMITE()==null) || 
             (this.VALLIMITE!=null &&
              this.VALLIMITE.equals(other.getVALLIMITE()))) &&
            ((this.VALORASEGURADO==null && other.getVALORASEGURADO()==null) || 
             (this.VALORASEGURADO!=null &&
              this.VALORASEGURADO.equals(other.getVALORASEGURADO()))) &&
            ((this.VALORITEM==null && other.getVALORITEM()==null) || 
             (this.VALORITEM!=null &&
              this.VALORITEM.equals(other.getVALORITEM()))) &&
            ((this.VALORPRIMAINCENDIO==null && other.getVALORPRIMAINCENDIO()==null) || 
             (this.VALORPRIMAINCENDIO!=null &&
              this.VALORPRIMAINCENDIO.equals(other.getVALORPRIMAINCENDIO()))) &&
            ((this.VALORPRIMANETA==null && other.getVALORPRIMANETA()==null) || 
             (this.VALORPRIMANETA!=null &&
              this.VALORPRIMANETA.equals(other.getVALORPRIMANETA()))) &&
            ((this.VALORUNITARIO==null && other.getVALORUNITARIO()==null) || 
             (this.VALORUNITARIO!=null &&
              this.VALORUNITARIO.equals(other.getVALORUNITARIO())));
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
        if (getACLARATORIO() != null) {
            _hashCode += getACLARATORIO().hashCode();
        }
        if (getASISTENCIAID() != null) {
            _hashCode += getASISTENCIAID().hashCode();
        }
        if (getAUTORIZACIONID() != null) {
            _hashCode += getAUTORIZACIONID().hashCode();
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
        if (getCANTIDAD() != null) {
            _hashCode += getCANTIDAD().hashCode();
        }
        if (getCANTIDADREBAJA() != null) {
            _hashCode += getCANTIDADREBAJA().hashCode();
        }
        if (getCLASERIESGOID() != null) {
            _hashCode += getCLASERIESGOID().hashCode();
        }
        if (getDESCRIPCION() != null) {
            _hashCode += getDESCRIPCION().hashCode();
        }
        if (getDESCRIPCIONITEM() != null) {
            _hashCode += getDESCRIPCIONITEM().hashCode();
        }
        if (getENDOSO() != null) {
            _hashCode += getENDOSO().hashCode();
        }
        if (getENDOSOReference() != null) {
            _hashCode += getENDOSOReference().hashCode();
        }
        if (getESAUDITABLE() != null) {
            _hashCode += getESAUDITABLE().hashCode();
        }
        if (getESBENE() != null) {
            _hashCode += getESBENE().hashCode();
        }
        if (getESTADOID() != null) {
            _hashCode += getESTADOID().hashCode();
        }
        if (getFECHAACTUALIZA() != null) {
            _hashCode += getFECHAACTUALIZA().hashCode();
        }
        if (getFONSAT() != null) {
            _hashCode += getFONSAT().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getITEMID() != null) {
            _hashCode += getITEMID().hashCode();
        }
        if (getNOMBRE() != null) {
            _hashCode += getNOMBRE().hashCode();
        }
        if (getNUMEROITEM() != null) {
            _hashCode += getNUMEROITEM().hashCode();
        }
        if (getPLANTILLAID() != null) {
            _hashCode += getPLANTILLAID().hashCode();
        }
        if (getPORCENTAJEPRIMAINCENDIO() != null) {
            _hashCode += getPORCENTAJEPRIMAINCENDIO().hashCode();
        }
        if (getPRIMANETAAM() != null) {
            _hashCode += getPRIMANETAAM().hashCode();
        }
        if (getSISUMA() != null) {
            _hashCode += getSISUMA().hashCode();
        }
        if (getTASAINCENDIO() != null) {
            _hashCode += getTASAINCENDIO().hashCode();
        }
        if (getTIPOITEMID() != null) {
            _hashCode += getTIPOITEMID().hashCode();
        }
        if (getTIPORIESGOID() != null) {
            _hashCode += getTIPORIESGOID().hashCode();
        }
        if (getUSUARIOACTUALIZA() != null) {
            _hashCode += getUSUARIOACTUALIZA().hashCode();
        }
        if (getVAL1() != null) {
            _hashCode += getVAL1().hashCode();
        }
        if (getVAL2() != null) {
            _hashCode += getVAL2().hashCode();
        }
        if (getVAL3() != null) {
            _hashCode += getVAL3().hashCode();
        }
        if (getVAL4() != null) {
            _hashCode += getVAL4().hashCode();
        }
        if (getVALLIMITE() != null) {
            _hashCode += getVALLIMITE().hashCode();
        }
        if (getVALORASEGURADO() != null) {
            _hashCode += getVALORASEGURADO().hashCode();
        }
        if (getVALORITEM() != null) {
            _hashCode += getVALORITEM().hashCode();
        }
        if (getVALORPRIMAINCENDIO() != null) {
            _hashCode += getVALORPRIMAINCENDIO().hashCode();
        }
        if (getVALORPRIMANETA() != null) {
            _hashCode += getVALORPRIMANETA().hashCode();
        }
        if (getVALORUNITARIO() != null) {
            _hashCode += getVALORUNITARIO().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ENDOSOITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ACLARATORIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACLARATORIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ASISTENCIAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ASISTENCIAID"));
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
        elemField.setFieldName("BENEFICIARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANTIDAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTIDAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CANTIDADREBAJA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTIDADREBAJA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLASERIESGOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLASERIESGOID"));
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
        elemField.setFieldName("DESCRIPCIONITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DESCRIPCIONITEM"));
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
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ENDOSOReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENDOSOFG7c7FF7"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESAUDITABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESAUDITABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ESBENE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESBENE"));
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
        elemField.setFieldName("FONSAT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "FONSAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("ITEMID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ITEMID"));
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
        elemField.setFieldName("NUMEROITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NUMEROITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PLANTILLAID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PLANTILLAID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PORCENTAJEPRIMAINCENDIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PORCENTAJEPRIMAINCENDIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PRIMANETAAM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PRIMANETAAM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SISUMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "SISUMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("TASAINCENDIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TASAINCENDIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("TIPORIESGOID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPORIESGOID"));
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
        elemField.setFieldName("VAL1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VAL1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VAL2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VAL2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VAL3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VAL3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VAL4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VAL4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALLIMITE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALLIMITE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORASEGURADO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORASEGURADO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORITEM");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORITEM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VALORPRIMAINCENDIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORPRIMAINCENDIO"));
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
        elemField.setFieldName("VALORUNITARIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "VALORUNITARIO"));
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
