package com.qbe.servicios.interfaces.seguridad;

public class WebServiceSeguridadInterfaceProxy implements com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface {
  private String _endpoint = null;
  private com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface webServiceSeguridadInterface = null;
  
  public WebServiceSeguridadInterfaceProxy() {
    _initWebServiceSeguridadInterfaceProxy();
  }
  
  public WebServiceSeguridadInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initWebServiceSeguridadInterfaceProxy();
  }
  
  private void _initWebServiceSeguridadInterfaceProxy() {
    try {
      webServiceSeguridadInterface = (new com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplServiceLocator()).getWebServiceSeguridadImplPort();
      if (webServiceSeguridadInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)webServiceSeguridadInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)webServiceSeguridadInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (webServiceSeguridadInterface != null)
      ((javax.xml.rpc.Stub)webServiceSeguridadInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface getWebServiceSeguridadInterface() {
    if (webServiceSeguridadInterface == null)
      _initWebServiceSeguridadInterfaceProxy();
    return webServiceSeguridadInterface;
  }
  
  public java.lang.String validadorEntidad(java.lang.String cedula, java.lang.String origen) throws java.rmi.RemoteException{
    if (webServiceSeguridadInterface == null)
      _initWebServiceSeguridadInterfaceProxy();
    return webServiceSeguridadInterface.validadorEntidad(cedula, origen);
  }
  
  
}