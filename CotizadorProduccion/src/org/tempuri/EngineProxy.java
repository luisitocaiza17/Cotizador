package org.tempuri;

public class EngineProxy implements org.tempuri.Engine_PortType {
  private String _endpoint = null;
  private org.tempuri.Engine_PortType engine_PortType = null;
  
  public EngineProxy() {
    _initEngineProxy();
  }
  
  public EngineProxy(String endpoint) {
    _endpoint = endpoint;
    _initEngineProxy();
  }
  
  private void _initEngineProxy() {
    try {
      engine_PortType = (new org.tempuri.Engine_ServiceLocator()).getBasicHttpBinding_Engine();
      if (engine_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)engine_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)engine_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (engine_PortType != null)
      ((javax.xml.rpc.Stub)engine_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.Engine_PortType getEngine_PortType() {
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType;
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[] searchData(java.lang.String parametro, java.lang.Integer tipoBusqueda, java.lang.String ramo) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchData(parametro, tipoBusqueda, ramo);
  }
  
  public java.lang.String saveData(java.lang.String rowID, org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.saveData(rowID, rq, obj);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBeneficiario(java.lang.String ramo, java.lang.String parametro) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchBeneficiario(ramo, parametro);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[] searchDress(java.lang.String entidadId) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchDress(entidadId);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDress(java.lang.String entidadId, java.lang.String direccionId) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchEntityDress(entidadId, direccionId);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] searchPoliza(java.lang.String ramo, java.lang.String entidad) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchPoliza(ramo, entidad);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedor(java.lang.String padreId) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchContenedor(padreId);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBroker(java.lang.String poliza, java.lang.String cliente) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchBroker(poliza, cliente);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO searchRamo(java.lang.String nombreRamo) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchRamo(nombreRamo);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPais() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchPais();
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvincia() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchProvincia();
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudad() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchCiudad();
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] searchCanton() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchCanton();
  }
  
  public java.lang.String lastId(java.lang.String entidad, java.lang.String tipodireccion) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.lastId(entidad, tipodireccion);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[] searchNacionalidad() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchNacionalidad();
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[] searchActivity() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchActivity();
  }
  
  public void sincronizarBrokers() throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    engine_PortType.sincronizarBrokers();
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchOrdenPago(numeroOP);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchStateOrdenPago(numeroOP);
  }
  
  public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[] searchAcuerdos(java.lang.String identificacion) throws java.rmi.RemoteException{
    if (engine_PortType == null)
      _initEngineProxy();
    return engine_PortType.searchAcuerdos(identificacion);
  }
  
  
}