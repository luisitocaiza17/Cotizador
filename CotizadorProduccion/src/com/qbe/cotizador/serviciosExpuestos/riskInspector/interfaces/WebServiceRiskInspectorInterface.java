package com.qbe.cotizador.serviciosExpuestos.riskInspector.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * @author evaldez
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceRiskInspectorInterface {
	
	@WebMethod(operationName = "request")
    @WebResult(name="result") 	
	//para ocultar todos mis metodos solo creo el servicio y recibo el metodo al que voy a entrar dentro del json
	public String servicio(@WebParam(name="parametros")String parametros);
/*
	String login(String parametros);

	String cargarInspeccionesPendientesUsuario(String parametros);
	*/
	
	//@WebMethod(operationName = "obtenerFirmasDigitalesParametros")
    //@WebResult(name="retorno")
	//public byte[] obtenerFirmasDigitalesParametros(@WebParam(name="sucursal")String sucursal, @WebParam(name="entidad")String entidad, @WebParam(name="ramo")String ramo);

}
