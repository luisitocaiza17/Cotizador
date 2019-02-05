/**
 * ServicioIntegracion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.qbe.cotizador.servicios.QBE.emisionPymesWS;

public interface ServicioIntegracion extends java.rmi.Remote {
    public void dummy(com.tandi.servicios.DTOs.ClienteDTO p1, com.tandi.servicios.DTOs.ComponenteXDocDTO p2, com.tandi.servicios.DTOs.ConfiguracionPagoDTO p3, com.tandi.servicios.DTOs.ProductoDTO p4, com.tandi.servicios.DTOs.PolizaDTO p5, com.tandi.servicios.DTOs.EndosoDTO p6, com.tandi.servicios.DTOs.DeducibleDTO p7, com.tandi.servicios.DTOs.ErrorDTO p8, com.tandi.servicios.DTOs.FacturaDTO p9, com.tandi.servicios.DTOs.ItemDTO p10, com.tandi.servicios.DTOs.PagoDTO p11, com.tandi.servicios.DTOs.RequestDTO p12, com.tandi.servicios.DTOs.RespuestaDTO p13, com.tandi.servicios.DTOs.PredioDTO p14, com.tandi.servicios.DTOs.CoberturaDTO p15) throws java.rmi.RemoteException;
    public com.tandi.servicios.DTOs.RespuestaDTO integrar(com.tandi.servicios.DTOs.RequestDTO request) throws java.rmi.RemoteException;
}
