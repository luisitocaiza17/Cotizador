package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.UsuariosOffline;
import com.qbe.cotizador.servicios.QBE.archivoPlano.ImportarCotizaciones;
import com.qbe.cotizador.servicios.QBE.archivoPlano.ObjetosRespuesta;

public class AgriCorreoCoprogreso {

	public boolean envioEmail(String nombreEjecutivo,Cotizacion cotizacion,
			AgriCotizacionAprobacion detalleCotizacion){
		UsuariosOffline usuariosOffline = new UsuariosOffline();
		UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
		usuariosOffline=usuariosOfflineDAO.BuscarNombre(nombreEjecutivo);
		String Correo=usuariosOffline.getCorreoElectronico();
		
		/*Enviamos la inf para generar el reporte*/
		ObjetosRespuesta objetosRespuesta = new  ObjetosRespuesta();
		if(cotizacion.getPuntoVenta().getNombre().equals("CREDIFE"))
			objetosRespuesta.setBeneficiario("BANCO DEL PICHINCHA");
		else
			objetosRespuesta.setBeneficiario("COOPROGRESO");
		objetosRespuesta.setComentario("Correcto");
		objetosRespuesta.setCotizacionID(""+cotizacion.getId());
		objetosRespuesta.setEstado(Integer.parseInt(cotizacion.getEstado().getId()));
		
		ObjetosRespuesta[] objetoRespuesta1 = new ObjetosRespuesta[1];
		objetoRespuesta1[0]=objetosRespuesta;
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
		//tomamos y  guardamos los correos en el campo correos de agriObjetoCotizacion
		AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
		AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
		String Correos= agriObjetoCotizacion.getCorreos();
		
		ImportarCotizaciones importarCotizaciones = new ImportarCotizaciones();
		importarCotizaciones.enviarMailCambioEstado(objetoRespuesta1,Correo, "");
		
		return true;
	}
}
