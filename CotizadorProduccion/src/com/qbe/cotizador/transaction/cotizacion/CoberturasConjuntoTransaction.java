package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.CoberturasConjuntoDAO;
import com.qbe.cotizador.model.CoberturasConjunto;

public class CoberturasConjuntoTransaction {
	
	public CoberturasConjuntoTransaction() {       
    }

	public CoberturasConjunto crear(CoberturasConjunto CoberturasConjunto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturasConjuntoDAO CoberturasConjuntoDAO = new CoberturasConjuntoDAO();
		CoberturasConjunto = CoberturasConjuntoDAO.crear(CoberturasConjunto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return CoberturasConjunto;	
	}
	
	public CoberturasConjunto editar(CoberturasConjunto CoberturasConjunto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturasConjuntoDAO CoberturasConjuntoDAO = new CoberturasConjuntoDAO();
		CoberturasConjunto CoberturasConjuntoBuscada = CoberturasConjuntoDAO.buscarPorId(CoberturasConjunto.getId());
		if(CoberturasConjuntoBuscada!=null){
			CoberturasConjunto = CoberturasConjuntoDAO.editar(CoberturasConjunto);
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
	return CoberturasConjunto;
	}
	
	public void eliminar(CoberturasConjunto CoberturasConjunto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CoberturasConjuntoDAO CoberturasConjuntoDAO = new CoberturasConjuntoDAO();
		CoberturasConjunto CoberturasConjuntoBuscado = new CoberturasConjunto();
		CoberturasConjuntoBuscado = CoberturasConjuntoDAO.buscarPorId(CoberturasConjunto.getId());
		if(CoberturasConjuntoBuscado !=null){
			CoberturasConjuntoDAO.eliminar(CoberturasConjunto);
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
