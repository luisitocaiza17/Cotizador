package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.ExtraDAO;
import com.qbe.cotizador.model.Extra;

public class ExtraTransaction {
	
	public ExtraTransaction() {       
    }

	public Extra crear(Extra Extra){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ExtraDAO ExtraDAO = new ExtraDAO();
		Extra = ExtraDAO.crear(Extra);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Extra;	
	}
	
	public Extra editar(Extra Extra){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ExtraDAO ExtraDAO = new ExtraDAO();
		Extra = ExtraDAO.editar(Extra);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Extra;
	}
	
	public void eliminar(Extra Extra){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ExtraDAO extraDAO = new ExtraDAO();
		Extra ExtraBuscado = new Extra();
		ExtraBuscado = extraDAO.buscarPorId(Extra.getId());
		if(ExtraBuscado !=null){
			extraDAO.eliminar(Extra);
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
