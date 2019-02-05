package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.CotizacionRespuestaDAO;
import com.qbe.cotizador.model.CotizacionRespuesta;

public class CotizacionRespuestaTransaction {

	public CotizacionRespuestaTransaction() {       
	}

	public CotizacionRespuesta crear(CotizacionRespuesta cotizacionRespuesta){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			CotizacionRespuestaDAO cotizacionRespuestaDAO = new CotizacionRespuestaDAO();
			cotizacionRespuesta = cotizacionRespuestaDAO.crear(cotizacionRespuesta);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return cotizacionRespuesta;	
	}

	public void eliminar(CotizacionRespuesta cotizacionRespuesta){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			CotizacionRespuestaDAO cotizacionRespuestaDAO = new CotizacionRespuestaDAO();
			CotizacionRespuesta cotizacionBuscado = new CotizacionRespuesta();
			cotizacionBuscado = cotizacionRespuestaDAO.buscarPorId(cotizacionRespuesta.getId());
			if(cotizacionBuscado !=null){
				cotizacionRespuestaDAO.eliminar(cotizacionRespuesta);
				ut.commit();
			}
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}
	}
}
