package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.EntidadJrDAO;
import com.qbe.cotizador.model.EntidadJr;

public class EntidadJrTransaction {
	
	public EntidadJrTransaction() {       
    }

	public EntidadJr crear(EntidadJr EntidadJr){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadJrDAO EntidadJrDAO = new EntidadJrDAO();
		EntidadJr = EntidadJrDAO.crear(EntidadJr);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return EntidadJr;	
	}
	
	public EntidadJr editar(EntidadJr EntidadJr){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadJrDAO EntidadJrDAO = new EntidadJrDAO();
		EntidadJr EntidadJrBuscada = EntidadJrDAO.buscarPorId(String.valueOf(EntidadJr.getId()));
		if(EntidadJrBuscada!=null){
			EntidadJr = EntidadJrDAO.editar(EntidadJr);
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
	return EntidadJr;
	}
	
	public void eliminar(EntidadJr EntidadJr){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadJrDAO EntidadJrDAO = new EntidadJrDAO();
		EntidadJr EntidadJrBuscado = new EntidadJr();
		EntidadJrBuscado = EntidadJrDAO.buscarPorId(String.valueOf(EntidadJr.getId()));
		if(EntidadJrBuscado !=null){
			EntidadJrDAO.eliminar(EntidadJr);
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
