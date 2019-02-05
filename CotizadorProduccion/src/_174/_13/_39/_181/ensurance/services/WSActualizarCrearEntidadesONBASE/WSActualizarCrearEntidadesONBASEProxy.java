package _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE;

public class WSActualizarCrearEntidadesONBASEProxy implements _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE {
  private String _endpoint = null;
  private _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE wSActualizarCrearEntidadesONBASE = null;
  
  public WSActualizarCrearEntidadesONBASEProxy() {
    _initWSActualizarCrearEntidadesONBASEProxy();
  }
  
  public WSActualizarCrearEntidadesONBASEProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSActualizarCrearEntidadesONBASEProxy();
  }
  
  private void _initWSActualizarCrearEntidadesONBASEProxy() {
    try {
      wSActualizarCrearEntidadesONBASE = (new _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASEServiceLocator()).getWSActualizarCrearEntidadesONBASE();
      if (wSActualizarCrearEntidadesONBASE != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSActualizarCrearEntidadesONBASE)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSActualizarCrearEntidadesONBASE)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSActualizarCrearEntidadesONBASE != null)
      ((javax.xml.rpc.Stub)wSActualizarCrearEntidadesONBASE)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE getWSActualizarCrearEntidadesONBASE() {
    if (wSActualizarCrearEntidadesONBASE == null)
      _initWSActualizarCrearEntidadesONBASEProxy();
    return wSActualizarCrearEntidadesONBASE;
  }
  
  public java.lang.String crearActualizarEntidad(com.tandi.entidad.dto.EntidadWSONBASE entidad) throws java.rmi.RemoteException{
    if (wSActualizarCrearEntidadesONBASE == null)
      _initWSActualizarCrearEntidadesONBASEProxy();
    return wSActualizarCrearEntidadesONBASE.crearActualizarEntidad(entidad);
  }
  
  
}