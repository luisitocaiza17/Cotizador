package com.qbe.cotizador.servicios.QBE.emisionGanaderoWS;

public class WS_EMISIONProxy implements com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION {
  private String _endpoint = null;
  private com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION wS_EMISION = null;
  
  public WS_EMISIONProxy() {
    _initWS_EMISIONProxy();
  }
  
  public WS_EMISIONProxy(String endpoint) {
    _endpoint = endpoint;
    _initWS_EMISIONProxy();
  }
  
  private void _initWS_EMISIONProxy() {
    try {
      wS_EMISION = (new com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONServiceLocator()).getWS_EMISION();
      if (wS_EMISION != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wS_EMISION)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wS_EMISION)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wS_EMISION != null)
      ((javax.xml.rpc.Stub)wS_EMISION)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION getWS_EMISION() {
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION;
  }
  
  public void test(com.tandi.servicios.DTOs.ComponenteXDocDTO[] lista) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    wS_EMISION.test(lista);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_VH_4(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.VehiculoDTO[] vehiculosa, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles, java.lang.String identificador) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_VH_4(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, vehiculosa, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles, identificador);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_VH_5(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.VehiculoDTO[] vehiculosa, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles, java.lang.String identificador) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_VH_5(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, vehiculosa, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles, identificador);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_PYMES_LINK(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.PredioDTO[] item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles, java.lang.String identificador) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_PYMES_LINK(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles, identificador);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_SALINK(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.LoteCultivoDTO item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String productoid, java.lang.String planProducto, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String polizaid, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaSucursalId, java.math.BigDecimal porcSubsidio, java.math.BigDecimal valorMaxSubsidio, java.lang.String loteImpresion, java.lang.String identificador, java.lang.String canal) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_SALINK(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, productoid, planProducto, contenedorId, numeroPoliza, polizaid, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaSucursalId, porcSubsidio, valorMaxSubsidio, loteImpresion, identificador, canal);
  }
  
  public void authenticarUsuarioWS(java.lang.String ENSURANCE_HOST_NAME, java.lang.String ENSURANCE_PORT, java.lang.String ENSURANCE_LOGIN, java.lang.String ENSURANCE_PASSWORD) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    wS_EMISION.authenticarUsuarioWS(ENSURANCE_HOST_NAME, ENSURANCE_PORT, ENSURANCE_LOGIN, ENSURANCE_PASSWORD);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO crearCliente(java.lang.String puerto, java.lang.String login, java.lang.String pass, com.tandi.servicios.DTOs.ClienteDTO cliente) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.crearCliente(puerto, login, pass, cliente);
  }
  
  public void authenticarUsuarioWSTR(java.lang.String ENSURANCE_HOST_NAME, java.lang.String ENSURANCE_PORT, java.lang.String ENSURANCE_LOGIN, java.lang.String ENSURANCE_PASSWORD) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    wS_EMISION.authenticarUsuarioWSTR(ENSURANCE_HOST_NAME, ENSURANCE_PORT, ENSURANCE_LOGIN, ENSURANCE_PASSWORD);
  }
  
  public boolean emitir_AP(java.lang.String numeroPoliza, java.lang.String agenteId, java.lang.String usuarioActualiza, java.lang.String[] cliente, java.lang.String[] item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, java.lang.Object[] componentes, double valorAsegurado, double valorComision, double valorPrima, double valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String productoid, java.lang.String planProducto, java.lang.String contenedorId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String monedaId, java.lang.String plantillaId) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_AP(numeroPoliza, agenteId, usuarioActualiza, cliente, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorAsegurado, valorComision, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, productoid, planProducto, contenedorId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, puerto, login, pass, monedaId, plantillaId);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_VH_2(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.VehiculoDTO item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_VH_2(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_VH_3(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.VehiculoDTO item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_VH_3(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_PYMES(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO[] beneficiarios, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.PredioDTO item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal porcentajeComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaDigitalId, java.lang.String sistemaEmisorId, java.lang.String sistemaEmisor, com.tandi.servicios.DTOs.ClienteDTO propietarioCuenta, java.lang.String planProducto, java.lang.String productoId, com.tandi.servicios.DTOs.CoberturaDTO[] coberturasAdicionales, boolean esPymes, com.tandi.servicios.DTOs.DeducibleDTO[] deducibles) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_PYMES(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, beneficiarios, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, porcentajeComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, contenedorId, numeroPoliza, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaDigitalId, sistemaEmisorId, sistemaEmisor, propietarioCuenta, planProducto, productoId, coberturasAdicionales, esPymes, deducibles);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_AplicacionTransporte(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String numeroEndoso, java.lang.String polizaId, java.lang.String observaciones, java.lang.String clienteId, java.lang.String numeroAplicacion, boolean excentoIva, java.util.Calendar fechaElaboracionEndoso, java.lang.String sucursalId, java.lang.String unidadNegocioId, java.util.Calendar vigenciaDesdeEndoso, java.util.Calendar vigenciaHastaEndoso, java.lang.String convenioPagoId, java.lang.String objetoAseguradoId, java.lang.String coberturaId, java.lang.String medioTransporteId, java.lang.String tipoEmbalajeId, java.lang.String tipoTransporteId, double elValorAsegurado, java.lang.String puertoOrigenId, java.lang.String puertoDestinoId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.CoberturaDTO[] coberturas, java.lang.String tipoSeguroId, java.lang.String consignacion, java.lang.String embarcadoPor, java.lang.String texto, java.lang.String observacion, double elPorcentajeComision, double elLimiteCobertura, java.lang.String marca, java.lang.String numeros, java.lang.String pesoBruto, java.lang.String cantidad, java.lang.String bandera) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_AplicacionTransporte(puerto, login, pass, numeroEndoso, polizaId, observaciones, clienteId, numeroAplicacion, excentoIva, fechaElaboracionEndoso, sucursalId, unidadNegocioId, vigenciaDesdeEndoso, vigenciaHastaEndoso, convenioPagoId, objetoAseguradoId, coberturaId, medioTransporteId, tipoEmbalajeId, tipoTransporteId, elValorAsegurado, puertoOrigenId, puertoDestinoId, usuarioActualiza, coberturas, tipoSeguroId, consignacion, embarcadoPor, texto, observacion, elPorcentajeComision, elLimiteCobertura, marca, numeros, pesoBruto, cantidad, bandera);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitirFacturarAplicacionTransporte(java.lang.String login, java.lang.String pass, java.lang.String puerto, java.lang.String numeroAplicacion, java.lang.String polizaMadre, java.lang.String usuarioActualiza) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitirFacturarAplicacionTransporte(login, pass, puerto, numeroAplicacion, polizaMadre, usuarioActualiza);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO facturarAplicacionTransporteContado(java.lang.String puerto, java.lang.String login, java.lang.String pass, com.tandi.servicios.DTOs.AplicacionDTO[] numeroAplicacion, java.lang.String usuarioActualiza) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.facturarAplicacionTransporteContado(puerto, login, pass, numeroAplicacion, usuarioActualiza);
  }
  
  public boolean emitir_VI() throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_VI();
  }
  
  public com.tandi.servicios.DTOs.RespuestaDTO emitir_SA(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.LoteCultivoDTO item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String productoid, java.lang.String planProducto, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String polizaid, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaSucursalId, java.math.BigDecimal porcSubsidio, java.math.BigDecimal valorMaxSubsidio, java.lang.String loteImpresion, java.lang.String identificador, java.lang.String canal) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_SA(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, productoid, planProducto, contenedorId, numeroPoliza, polizaid, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaSucursalId, porcSubsidio, valorMaxSubsidio, loteImpresion, identificador, canal);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO emitir_SGQBE(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String agenteId, java.lang.String usuarioActualiza, com.tandi.servicios.DTOs.ConfiguracionPagoDTO configPago, com.tandi.servicios.DTOs.ClienteDTO cliente, com.tandi.servicios.DTOs.ClienteDTO asegurado, com.tandi.servicios.DTOs.ClienteDTO propietario, com.tandi.servicios.DTOs.GanadoDTO[] item, java.lang.String ramoid, java.lang.String ramo, java.lang.String unidadNegocioId, java.lang.String unidadProduccionId, java.lang.String sucursalId, long vigenciaDesde, long vigenciaHasta, com.tandi.servicios.DTOs.ComponenteXDocDTO[] componentes, java.math.BigDecimal valorComision, java.math.BigDecimal valorAsegurado, java.math.BigDecimal valorPrima, java.math.BigDecimal valorFactura, long fechaVencimientoFactura, java.lang.String tipoDocumentoFac, java.lang.String numeroFactura, java.lang.String autorizacionSRI, java.lang.String productoid, java.lang.String planProducto, java.lang.String contenedorId, java.lang.String numeroPoliza, java.lang.String polizaid, java.lang.String puntoVentaId, java.lang.String tipoRiesgoId, java.lang.String claseRiesgoId, java.lang.String cobradorId, java.lang.String tipoItemId, java.lang.String monedaId, java.lang.String plantillaId, java.lang.String firmaSucursalId, java.math.BigDecimal porcSubsidio, java.math.BigDecimal valorMaxSubsidio, java.lang.String loteImpresion, java.lang.String identificador, java.lang.String canal) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.emitir_SGQBE(puerto, login, pass, agenteId, usuarioActualiza, configPago, cliente, asegurado, propietario, item, ramoid, ramo, unidadNegocioId, unidadProduccionId, sucursalId, vigenciaDesde, vigenciaHasta, componentes, valorComision, valorAsegurado, valorPrima, valorFactura, fechaVencimientoFactura, tipoDocumentoFac, numeroFactura, autorizacionSRI, productoid, planProducto, contenedorId, numeroPoliza, polizaid, puntoVentaId, tipoRiesgoId, claseRiesgoId, cobradorId, tipoItemId, monedaId, plantillaId, firmaSucursalId, porcSubsidio, valorMaxSubsidio, loteImpresion, identificador, canal);
  }
  
  public com.tandi.servicios.DTOs.ErrorDTO anular_Aplicacion(java.lang.String puerto, java.lang.String login, java.lang.String pass, java.lang.String usuarioActualiza, java.lang.String ramoNemonico, java.lang.String numeroPoliza, int orden, java.lang.String numeroEndoso) throws java.rmi.RemoteException{
    if (wS_EMISION == null)
      _initWS_EMISIONProxy();
    return wS_EMISION.anular_Aplicacion(puerto, login, pass, usuarioActualiza, ramoNemonico, numeroPoliza, orden, numeroEndoso);
  }
  
  
}