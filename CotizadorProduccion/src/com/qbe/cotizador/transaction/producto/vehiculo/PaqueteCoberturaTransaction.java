package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculocerrado.PaqueteCoberturaDAO;
import com.qbe.cotizador.model.PaqueteCobertura;

public class PaqueteCoberturaTransaction {
	
	public PaqueteCoberturaTransaction() {       
    }

	public PaqueteCobertura crear(PaqueteCobertura PaqueteCobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PaqueteCoberturaDAO PaqueteCoberturaDAO = new PaqueteCoberturaDAO();
		PaqueteCobertura = PaqueteCoberturaDAO.crear(PaqueteCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return PaqueteCobertura;	
	}
	
	public PaqueteCobertura editar(PaqueteCobertura PaqueteCobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PaqueteCoberturaDAO PaqueteCoberturaDAO = new PaqueteCoberturaDAO();
		PaqueteCobertura = PaqueteCoberturaDAO.editar(PaqueteCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return PaqueteCobertura;
	}
	
	public void eliminar(PaqueteCobertura PaqueteCobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PaqueteCoberturaDAO PaqueteCoberturaDAO = new PaqueteCoberturaDAO();
		PaqueteCobertura PaqueteCoberturaBuscado = new PaqueteCobertura();
		PaqueteCoberturaBuscado = PaqueteCoberturaDAO.buscarPorId(PaqueteCobertura.getId());
		if(PaqueteCoberturaBuscado !=null){
			PaqueteCoberturaDAO.eliminar(PaqueteCobertura);
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
