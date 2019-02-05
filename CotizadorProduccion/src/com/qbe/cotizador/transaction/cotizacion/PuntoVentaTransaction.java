package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.model.PuntoVenta;

public class PuntoVentaTransaction {
	
	public PuntoVentaTransaction() {       
    }

	public PuntoVenta crear(PuntoVenta PuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PuntoVentaDAO PuntoVentaDAO = new PuntoVentaDAO();
		PuntoVenta = PuntoVentaDAO.crear(PuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return PuntoVenta;	
	}
	
	public PuntoVenta editar(PuntoVenta PuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PuntoVentaDAO PuntoVentaDAO = new PuntoVentaDAO();
		PuntoVenta PuntoVentaBuscada = PuntoVentaDAO.buscarPorId(PuntoVenta.getId());
		if(PuntoVentaBuscada!=null){
			PuntoVenta = PuntoVentaDAO.editar(PuntoVenta);
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
	return PuntoVenta;
	}
	
	public void eliminar(PuntoVenta PuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PuntoVentaDAO PuntoVentaDAO = new PuntoVentaDAO();
		PuntoVenta PuntoVentaBuscado = new PuntoVenta();
		PuntoVentaBuscado = PuntoVentaDAO.buscarPorId(PuntoVenta.getId());
		if(PuntoVentaBuscado !=null){
			PuntoVentaDAO.eliminar(PuntoVenta);
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
