package com.qbe.cotizador.servicios.QBE.emisionAgricolaWS;

public class WSEmisionAgricolaProxy implements com.qbe.cotizador.servicios.QBE.emisionAgricolaWS.WSEmisionAgricola_PortType {
  private String _endpoint = null;
  private com.qbe.cotizador.servicios.QBE.emisionAgricolaWS.WSEmisionAgricola_PortType wSEmisionAgricola_PortType = null;
  
  public WSEmisionAgricolaProxy() {
    _initWSEmisionAgricolaProxy();
  }
  
  public WSEmisionAgricolaProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSEmisionAgricolaProxy();
  }
  
  private void _initWSEmisionAgricolaProxy() {
    try {
      wSEmisionAgricola_PortType = (new com.qbe.cotizador.servicios.QBE.emisionAgricolaWS.WSEmisionAgricola_ServiceLocator()).getWSEmisionAgricolaPort();
      if (wSEmisionAgricola_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSEmisionAgricola_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSEmisionAgricola_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSEmisionAgricola_PortType != null)
      ((javax.xml.rpc.Stub)wSEmisionAgricola_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.qbe.cotizador.servicios.QBE.emisionAgricolaWS.WSEmisionAgricola_PortType getWSEmisionAgricola_PortType() {
    if (wSEmisionAgricola_PortType == null)
      _initWSEmisionAgricolaProxy();
    return wSEmisionAgricola_PortType;
  }
  
  public java.lang.String emisionPoliza(java.lang.String xmlString, java.lang.String xmlToken) throws java.rmi.RemoteException{
    if (wSEmisionAgricola_PortType == null)
      _initWSEmisionAgricolaProxy();
    return wSEmisionAgricola_PortType.emisionPoliza(xmlString, xmlToken);
  }
  
  
}