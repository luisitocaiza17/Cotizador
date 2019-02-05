package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.ganadero.FormaPagoXPuntoVentaDAO;
import com.qbe.cotizador.model.FormaPagoXPuntoVenta;

public class FormaPagoXPuntoVentaTransaction {
	
	public FormaPagoXPuntoVentaTransaction() {       
    }

	public FormaPagoXPuntoVenta crear(FormaPagoXPuntoVenta FormaPagoXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoXPuntoVentaDAO FormaPagoXPuntoVentaDAO = new FormaPagoXPuntoVentaDAO();
		FormaPagoXPuntoVenta = FormaPagoXPuntoVentaDAO.crear(FormaPagoXPuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return FormaPagoXPuntoVenta;	
	}
	
	public FormaPagoXPuntoVenta editar(FormaPagoXPuntoVenta FormaPagoXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoXPuntoVentaDAO FormaPagoXPuntoVentaDAO = new FormaPagoXPuntoVentaDAO();
		FormaPagoXPuntoVenta FormaPagoXPuntoVentaBuscada = FormaPagoXPuntoVentaDAO.buscarFormaPagoXPuntoVentaPorId(String.valueOf(FormaPagoXPuntoVenta.getId()));
		if(FormaPagoXPuntoVentaBuscada!=null){
			FormaPagoXPuntoVenta = FormaPagoXPuntoVentaDAO.editar(FormaPagoXPuntoVenta);
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
	return FormaPagoXPuntoVenta;
	}
	
	public void eliminar(FormaPagoXPuntoVenta FormaPagoXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoXPuntoVentaDAO FormaPagoXPuntoVentaDAO = new FormaPagoXPuntoVentaDAO();
		FormaPagoXPuntoVenta FormaPagoXPuntoVentaBuscado = FormaPagoXPuntoVentaDAO.buscarFormaPagoXPuntoVentaPorId(String.valueOf(FormaPagoXPuntoVenta.getId()));
		if(FormaPagoXPuntoVentaBuscado !=null){
			FormaPagoXPuntoVentaDAO.eliminar(FormaPagoXPuntoVenta);
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
