package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.FirmasDigitalesDAO;
import com.qbe.cotizador.model.FirmasDigitales;

public class FirmasDigitalesTransaction {
	
	public FirmasDigitalesTransaction() {       
    }

	public FirmasDigitales crear(FirmasDigitales FirmasDigitales){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FirmasDigitalesDAO FirmasDigitalesDAO = new FirmasDigitalesDAO();
		FirmasDigitales = FirmasDigitalesDAO.crear(FirmasDigitales);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return FirmasDigitales;	
	}
	
	public FirmasDigitales editar(FirmasDigitales FirmasDigitales){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FirmasDigitalesDAO FirmasDigitalesDAO = new FirmasDigitalesDAO();
		FirmasDigitales FirmasDigitalesBuscada = FirmasDigitalesDAO.buscarPorId(FirmasDigitales.getId());
		if(FirmasDigitalesBuscada!=null){
			FirmasDigitales = FirmasDigitalesDAO.editar(FirmasDigitales);
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
	return FirmasDigitales;
	}
	
	public void eliminar(FirmasDigitales FirmasDigitales){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		FirmasDigitalesDAO FirmasDigitalesDAO = new FirmasDigitalesDAO();
		FirmasDigitales FirmasDigitalesBuscado = new FirmasDigitales();
		FirmasDigitalesBuscado = FirmasDigitalesDAO.buscarPorId(FirmasDigitales.getId());
		if(FirmasDigitalesBuscado !=null){
			FirmasDigitalesDAO.eliminar(FirmasDigitales);
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
