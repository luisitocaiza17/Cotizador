/**
 * Engine_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface Engine_PortType extends java.rmi.Remote {
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[] searchData(java.lang.String parametro, java.lang.Integer tipoBusqueda, java.lang.String ramo) throws java.rmi.RemoteException;
    public java.lang.String saveData(java.lang.String rowID, org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBeneficiario(java.lang.String ramo, java.lang.String parametro) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[] searchDress(java.lang.String entidadId) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDress(java.lang.String entidadId, java.lang.String direccionId) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] searchPoliza(java.lang.String ramo, java.lang.String entidad) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedor(java.lang.String padreId) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBroker(java.lang.String poliza, java.lang.String cliente) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO searchRamo(java.lang.String nombreRamo) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPais() throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvincia() throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudad() throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] searchCanton() throws java.rmi.RemoteException;
    public java.lang.String lastId(java.lang.String entidad, java.lang.String tipodireccion) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[] searchNacionalidad() throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[] searchActivity() throws java.rmi.RemoteException;
    public void sincronizarBrokers() throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException;
    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[] searchAcuerdos(java.lang.String identificacion) throws java.rmi.RemoteException;
}
