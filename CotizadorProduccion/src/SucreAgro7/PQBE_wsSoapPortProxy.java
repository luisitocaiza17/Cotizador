package SucreAgro7;

public class PQBE_wsSoapPortProxy implements SucreAgro7.PQBE_wsSoapPort {
  private String _endpoint = null;
  private SucreAgro7.PQBE_wsSoapPort pQBE_wsSoapPort = null;
  
  public PQBE_wsSoapPortProxy() {
    _initPQBE_wsSoapPortProxy();
  }
  
  public PQBE_wsSoapPortProxy(String endpoint) {
    _endpoint = endpoint;
    _initPQBE_wsSoapPortProxy();
  }
  
  private void _initPQBE_wsSoapPortProxy() {
    try {
      pQBE_wsSoapPort = (new SucreAgro7.PQBE_wsLocator()).getpQBE_wsSoapPort();
      if (pQBE_wsSoapPort != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)pQBE_wsSoapPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)pQBE_wsSoapPort)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (pQBE_wsSoapPort != null)
      ((javax.xml.rpc.Stub)pQBE_wsSoapPort)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SucreAgro7.PQBE_wsSoapPort getPQBE_wsSoapPort() {
    if (pQBE_wsSoapPort == null)
      _initPQBE_wsSoapPortProxy();
    return pQBE_wsSoapPort;
  }
  
  public SucreAgro7.PQBE_wsACTUALIZAESTADOResponse ACTUALIZAESTADO(SucreAgro7.PQBE_wsACTUALIZAESTADO parameters) throws java.rmi.RemoteException{
    if (pQBE_wsSoapPort == null)
      _initPQBE_wsSoapPortProxy();
    return pQBE_wsSoapPort.ACTUALIZAESTADO(parameters);
  }
  
  
}