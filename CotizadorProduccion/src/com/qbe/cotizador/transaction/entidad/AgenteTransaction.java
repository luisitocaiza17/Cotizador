package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.model.Agente;

public class AgenteTransaction {
	
	public AgenteTransaction() {       
    }

	public Agente crear(Agente Agente){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgenteDAO AgenteDAO = new AgenteDAO();
		Agente = AgenteDAO.crear(Agente);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Agente;	
	}
	
	public Agente editar(Agente Agente){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgenteDAO AgenteDAO = new AgenteDAO();
		Agente AgenteBuscada = AgenteDAO.buscarPorId(Agente.getId());
		if(AgenteBuscada!=null){
			Agente = AgenteDAO.editar(Agente);
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
	return Agente;
	}
	
	public void eliminar(Agente Agente){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgenteDAO AgenteDAO = new AgenteDAO();
		Agente AgenteBuscado = new Agente();
		AgenteBuscado = AgenteDAO.buscarPorId(Agente.getId());
		if(AgenteBuscado !=null){
			AgenteDAO.eliminar(Agente);
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
