package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.VariableSistema;

public class VariableSistemaTransaction {
	
	public VariableSistemaTransaction() {       
    }

	public VariableSistema crear(VariableSistema VariableSistema){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VariableSistemaDAO VariableSistemaDAO = new VariableSistemaDAO();
		VariableSistema = VariableSistemaDAO.crear(VariableSistema);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return VariableSistema;	
	}
	
	public VariableSistema editar(VariableSistema VariableSistema){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VariableSistemaDAO VariableSistemaDAO = new VariableSistemaDAO();
		VariableSistema VariableSistemaBuscada = VariableSistemaDAO.buscarPorId2(VariableSistema.getId());
		if(VariableSistemaBuscada!=null){
			VariableSistema = VariableSistemaDAO.editar(VariableSistema);
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
	return VariableSistema;
	}
	
	public void eliminar(VariableSistema VariableSistema){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VariableSistemaDAO VariableSistemaDAO = new VariableSistemaDAO();
		VariableSistema VariableSistemaBuscado = new VariableSistema();
		VariableSistemaBuscado = VariableSistemaDAO.buscarPorId(String.valueOf(VariableSistema.getId()));
		if(VariableSistemaBuscado !=null){
			VariableSistemaDAO.eliminar(VariableSistema);
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
