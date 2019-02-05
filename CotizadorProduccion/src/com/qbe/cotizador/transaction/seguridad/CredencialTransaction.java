package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.seguridad.CredencialDAO;
import com.qbe.cotizador.model.Credencial;

public class CredencialTransaction {
	
	public CredencialTransaction() {       
    }

	public Credencial crear(Credencial Credencial){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CredencialDAO CredencialDAO = new CredencialDAO();
		Credencial = CredencialDAO.crear(Credencial);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Credencial;	
	}
	
	public Credencial editar(Credencial Credencial){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CredencialDAO CredencialDAO = new CredencialDAO();
		Credencial CredencialBuscada = CredencialDAO.buscarPorId(Credencial.getId());
		if(CredencialBuscada!=null){
			Credencial = CredencialDAO.editar(Credencial);
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
	return Credencial;
	}
	
	public void eliminar(Credencial Credencial){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CredencialDAO CredencialDAO = new CredencialDAO();
		Credencial CredencialBuscado = new Credencial();
		CredencialBuscado = CredencialDAO.buscarPorId(Credencial.getId());
		if(CredencialBuscado !=null){
			CredencialDAO.eliminar(Credencial);
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
