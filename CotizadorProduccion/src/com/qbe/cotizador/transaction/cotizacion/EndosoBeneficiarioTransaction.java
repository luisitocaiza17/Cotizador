package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.model.EndosoBeneficiario;

public class EndosoBeneficiarioTransaction {
	
	public EndosoBeneficiarioTransaction() {       
    }

	public EndosoBeneficiario crear(EndosoBeneficiario EndosoBeneficiario){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EndosoBeneficiarioDAO EndosoBeneficiarioDAO = new EndosoBeneficiarioDAO();
		EndosoBeneficiario = EndosoBeneficiarioDAO.crear(EndosoBeneficiario);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return EndosoBeneficiario;	
	}
	
	public EndosoBeneficiario editar(EndosoBeneficiario EndosoBeneficiario){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EndosoBeneficiarioDAO EndosoBeneficiarioDAO = new EndosoBeneficiarioDAO();
		EndosoBeneficiario EndosoBeneficiarioBuscada = EndosoBeneficiarioDAO.buscarPorId(EndosoBeneficiario.getId());
		if(EndosoBeneficiarioBuscada!=null){
			EndosoBeneficiario = EndosoBeneficiarioDAO.editar(EndosoBeneficiario);
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
	return EndosoBeneficiario;
	}
	
	public void eliminar(EndosoBeneficiario EndosoBeneficiario){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EndosoBeneficiarioDAO EndosoBeneficiarioDAO = new EndosoBeneficiarioDAO();
		EndosoBeneficiario EndosoBeneficiarioBuscado = new EndosoBeneficiario();
		EndosoBeneficiarioBuscado = EndosoBeneficiarioDAO.buscarPorId(EndosoBeneficiario.getId());
		if(EndosoBeneficiarioBuscado !=null){
			EndosoBeneficiarioDAO.eliminar(EndosoBeneficiario);
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
