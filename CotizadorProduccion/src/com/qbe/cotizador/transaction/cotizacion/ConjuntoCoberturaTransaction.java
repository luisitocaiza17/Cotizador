package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.ConjuntoCoberturaDAO;
import com.qbe.cotizador.model.ConjuntoCobertura;

public class ConjuntoCoberturaTransaction {
	
	public ConjuntoCoberturaTransaction() {       
    }

	public ConjuntoCobertura crear(ConjuntoCobertura ConjuntoCobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConjuntoCoberturaDAO ConjuntoCoberturaDAO = new ConjuntoCoberturaDAO();
		ConjuntoCobertura = ConjuntoCoberturaDAO.crear(ConjuntoCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ConjuntoCobertura;	
	}
	
	public ConjuntoCobertura editar(ConjuntoCobertura ConjuntoCobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConjuntoCoberturaDAO ConjuntoCoberturaDAO = new ConjuntoCoberturaDAO();
		ConjuntoCobertura ConjuntoCoberturaBuscada = ConjuntoCoberturaDAO.buscarPorId(ConjuntoCobertura.getId());
		if(ConjuntoCoberturaBuscada!=null){
			ConjuntoCobertura = ConjuntoCoberturaDAO.editar(ConjuntoCobertura);
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
	return ConjuntoCobertura;
	}
	
	public void eliminar(ConjuntoCobertura ConjuntoCobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConjuntoCoberturaDAO ConjuntoCoberturaDAO = new ConjuntoCoberturaDAO();
		ConjuntoCobertura ConjuntoCoberturaBuscado = new ConjuntoCobertura();
		ConjuntoCoberturaBuscado = ConjuntoCoberturaDAO.buscarPorId(ConjuntoCobertura.getId());
		if(ConjuntoCoberturaBuscado !=null){
			ConjuntoCoberturaDAO.eliminar(ConjuntoCobertura);
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
