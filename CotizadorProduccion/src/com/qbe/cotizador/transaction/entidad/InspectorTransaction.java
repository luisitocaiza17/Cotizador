package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.model.Inspector;

public class InspectorTransaction {
	
	public InspectorTransaction() {       
    }

	public Inspector crear(Inspector Inspector){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InspectorDAO InspectorDAO = new InspectorDAO();
		Inspector = InspectorDAO.crear(Inspector);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Inspector;	
	}
	
	public Inspector editar(Inspector Inspector){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InspectorDAO InspectorDAO = new InspectorDAO();
		Inspector InspectorBuscada = InspectorDAO.buscarPorId(Inspector.getId());
		if(InspectorBuscada!=null){
			Inspector = InspectorDAO.editar(Inspector);
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
	return Inspector;
	}
	
	public void eliminar(Inspector Inspector){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InspectorDAO InspectorDAO = new InspectorDAO();
		Inspector InspectorBuscado = new Inspector();
		InspectorBuscado = InspectorDAO.buscarPorId(Inspector.getId());
		if(InspectorBuscado !=null){
			InspectorDAO.eliminar(Inspector);
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
