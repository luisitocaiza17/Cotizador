package com.qbe.cotizador.servlets.producto.agricola;

import java.rmi.RemoteException;

import SucreAgro7.PQBE_wsACTUALIZAESTADO;
import SucreAgro7.PQBE_wsACTUALIZAESTADOResponse;
import SucreAgro7.PQBE_wsSoapPort;
import SucreAgro7.PQBE_wsSoapPortProxy;
import SucreAgro7.SdtEstado;

import com.qbe.cotizador.servicios.QBE.agricolaSucre.RespuestaUN01DTO;





public class AgriSucreRespuesta {
	
	public PQBE_wsACTUALIZAESTADOResponse RespuestaSucre(String CodigoTramite,SdtEstado ObjRespuesta) throws Exception{
		//consumo del servicio
		//PQBE_wsSoapPort envioEstado = new PQBE_wsSoapPortProxy("http://181.198.93.59:8080/sucreagrogt/servlet/apqbe_ws?wsdl");//instancia de la Envio de estado desde QBE
		PQBE_wsSoapPort envioEstado = new PQBE_wsSoapPortProxy("http://181.198.93.61:8080/sucreagro/servlet/apqbe_ws?wsdl");//instancia de la Envio de estado desde QBE

		PQBE_wsACTUALIZAESTADO estado = new PQBE_wsACTUALIZAESTADO();//llenamos el estado del objeto que se envia
		estado.setCodigotramite(CodigoTramite);
		estado.setEstado(ObjRespuesta);
		PQBE_wsACTUALIZAESTADOResponse respuesta = new PQBE_wsACTUALIZAESTADOResponse();//respuesta de ellos
		
		try {
			respuesta=envioEstado.ACTUALIZAESTADO(estado);
		} catch (RemoteException e) {
			e.printStackTrace();
			respuesta=null;
		}
		return respuesta;
	}
	
	
	
}
