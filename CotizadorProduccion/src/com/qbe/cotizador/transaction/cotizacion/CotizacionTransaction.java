package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.model.Cotizacion;

public class CotizacionTransaction {
	
	public CotizacionTransaction() {       
    }

	public Cotizacion crear(Cotizacion cotizacion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		cotizacion = cotizacionDAO.crear(cotizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return cotizacion;	
	}
	
	public Cotizacion editar(Cotizacion cotizacion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		Cotizacion cotizacionBuscada = cotizacionDAO.buscarPorId(cotizacion.getId());
		if(cotizacionBuscada!=null){
			cotizacion = cotizacionDAO.editar(cotizacion);
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
	return cotizacion;
	}
	
	public void eliminar(Cotizacion cotizacion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		Cotizacion cotizacionBuscado = new Cotizacion();
		cotizacionBuscado = cotizacionDAO.buscarPorId(cotizacion.getId());
		if(cotizacionBuscado !=null){
			cotizacionDAO.eliminar(cotizacion);
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
