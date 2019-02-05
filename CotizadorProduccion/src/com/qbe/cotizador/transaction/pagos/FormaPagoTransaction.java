package com.qbe.cotizador.transaction.pagos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.pagos.FormaPagoDAO;
import com.qbe.cotizador.model.FormaPago;

public class FormaPagoTransaction {
	
	public FormaPagoTransaction() {       
    }

	public FormaPago crear(FormaPago FormaPago){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoDAO FormaPagoDAO = new FormaPagoDAO();
		FormaPago = FormaPagoDAO.crear(FormaPago);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return FormaPago;	
	}
	
	public FormaPago editar(FormaPago FormaPago){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoDAO FormaPagoDAO = new FormaPagoDAO();
		FormaPago FormaPagoBuscada = FormaPagoDAO.buscarPorId(FormaPago.getId());
		if(FormaPagoBuscada!=null){
			FormaPago = FormaPagoDAO.editar(FormaPago);
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
	return FormaPago;
	}
	
	public void eliminar(FormaPago FormaPago){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FormaPagoDAO FormaPagoDAO = new FormaPagoDAO();
		FormaPago FormaPagoBuscado = new FormaPago();
		FormaPagoBuscado = FormaPagoDAO.buscarPorId(FormaPago.getId());
		if(FormaPagoBuscado !=null){
			FormaPagoDAO.eliminar(FormaPago);
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
