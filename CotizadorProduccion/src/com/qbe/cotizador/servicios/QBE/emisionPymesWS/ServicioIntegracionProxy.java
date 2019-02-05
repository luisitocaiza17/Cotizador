package com.qbe.cotizador.servicios.QBE.emisionPymesWS;

public class ServicioIntegracionProxy implements com.qbe.cotizador.servicios.QBE.emisionPymesWS.ServicioIntegracion {
  private String _endpoint = null;
  private com.qbe.cotizador.servicios.QBE.emisionPymesWS.ServicioIntegracion servicioIntegracion = null;
  
  public ServicioIntegracionProxy() {
    _initServicioIntegracionProxy();
  }
  
  public ServicioIntegracionProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioIntegracionProxy();
  }
  
  private void _initServicioIntegracionProxy() {
    try {
      servicioIntegracion = (new com.qbe.cotizador.servicios.QBE.emisionPymesWS.ServicioIntegracionServiceLocator()).getWS_INTEGRACION();
      if (servicioIntegracion != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioIntegracion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioIntegracion)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicioIntegracion != null)
      ((javax.xml.rpc.Stub)servicioIntegracion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.qbe.cotizador.servicios.QBE.emisionPymesWS.ServicioIntegracion getServicioIntegracion() {
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    return servicioIntegracion;
  }
  
  public void dummy(com.tandi.servicios.DTOs.ClienteDTO p1, com.tandi.servicios.DTOs.ComponenteXDocDTO p2, com.tandi.servicios.DTOs.ConfiguracionPagoDTO p3, com.tandi.servicios.DTOs.ProductoDTO p4, com.tandi.servicios.DTOs.PolizaDTO p5, com.tandi.servicios.DTOs.EndosoDTO p6, com.tandi.servicios.DTOs.DeducibleDTO p7, com.tandi.servicios.DTOs.ErrorDTO p8, com.tandi.servicios.DTOs.FacturaDTO p9, com.tandi.servicios.DTOs.ItemDTO p10, com.tandi.servicios.DTOs.PagoDTO p11, com.tandi.servicios.DTOs.RequestDTO p12, com.tandi.servicios.DTOs.RespuestaDTO p13, com.tandi.servicios.DTOs.PredioDTO p14, com.tandi.servicios.DTOs.CoberturaDTO p15) throws java.rmi.RemoteException{
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    servicioIntegracion.dummy(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15);
  }
  
  public com.tandi.servicios.DTOs.RespuestaDTO integrar(com.tandi.servicios.DTOs.RequestDTO request) throws java.rmi.RemoteException{
    if (servicioIntegracion == null)
      _initServicioIntegracionProxy();
    return servicioIntegracion.integrar(request);
  }
  
  
}