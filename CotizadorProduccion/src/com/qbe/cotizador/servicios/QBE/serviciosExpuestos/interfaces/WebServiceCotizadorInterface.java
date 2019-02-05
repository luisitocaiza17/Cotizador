package com.qbe.cotizador.servicios.QBE.serviciosExpuestos.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * Interface para la exposición de métodos de creación de cotizaciones 
 * @author ayanez
 *
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceCotizadorInterface {
	
	@WebMethod(operationName = "creacionCotizacionVH")
    @WebResult(name="retorno") 		
	public String creacionCotizacionVH(@WebParam(name="xmlCotizacion")String xmlCotizacion);
		
}
