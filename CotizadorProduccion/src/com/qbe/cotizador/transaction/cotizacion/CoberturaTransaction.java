package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.model.Cobertura;

public class CoberturaTransaction {
	
	public CoberturaTransaction() {       
    }

	public Cobertura crear(Cobertura Cobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturaDAO CoberturaDAO = new CoberturaDAO();
		Cobertura = CoberturaDAO.crear(Cobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Cobertura;	
	}
	
	public Cobertura editar(Cobertura Cobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturaDAO CoberturaDAO = new CoberturaDAO();
		Cobertura CoberturaBuscada = CoberturaDAO.buscarPorId(Cobertura.getId());
		if(CoberturaBuscada!=null){
			Cobertura = CoberturaDAO.editar(Cobertura);
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
	return Cobertura;
	}
	
	public void eliminar(Cobertura Cobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturaDAO CoberturaDAO = new CoberturaDAO();
		Cobertura CoberturaBuscado = new Cobertura();
		CoberturaBuscado = CoberturaDAO.buscarPorId(Cobertura.getId());
		if(CoberturaBuscado !=null){
			CoberturaDAO.eliminar(Cobertura);
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
