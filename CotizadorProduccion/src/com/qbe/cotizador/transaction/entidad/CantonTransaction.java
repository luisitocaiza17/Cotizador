package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.model.Canton;

public class CantonTransaction {
	
	public CantonTransaction() {       
    }

	public Canton crear(Canton Canton){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CantonDAO CantonDAO = new CantonDAO();
		Canton = CantonDAO.crear(Canton);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Canton;	
	}
	
	public Canton editar(Canton Canton){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CantonDAO CantonDAO = new CantonDAO();
		Canton CantonBuscada = CantonDAO.buscarPorId(Canton.getId());
		if(CantonBuscada!=null){
			Canton = CantonDAO.editar(Canton);
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
	return Canton;
	}
	
	public void eliminar(Canton canton){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CantonDAO CantonDAO = new CantonDAO();
		Canton CantonBuscado = new Canton();
		CantonBuscado = CantonDAO.buscarPorId(canton.getId());
		if(CantonBuscado !=null){
			CantonDAO.eliminar(canton);
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
