package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.seguridad.SessionDAO;
import com.qbe.cotizador.model.Session;

public class SessionTransaction {
	
	public SessionTransaction() {       
    }

	public Session crear(Session Session){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SessionDAO SessionDAO = new SessionDAO();
		Session = SessionDAO.crear(Session);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Session;	
	}
	
	public Session editar(Session Session){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SessionDAO SessionDAO = new SessionDAO();
		Session SessionBuscada = SessionDAO.buscarPorId(Session.getId());
		if(SessionBuscada!=null){
			Session = SessionDAO.editar(Session);
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
	return Session;
	}
	
	public void eliminar(Session Session){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SessionDAO SessionDAO = new SessionDAO();
		Session SessionBuscado = new Session();
		SessionBuscado = SessionDAO.buscarPorId(Session.getId());
		if(SessionBuscado !=null){
			SessionDAO.eliminar(Session);
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
