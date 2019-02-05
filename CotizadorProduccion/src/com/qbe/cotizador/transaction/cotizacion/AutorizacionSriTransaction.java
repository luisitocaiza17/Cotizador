package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.AutorizacionSriDAO;
import com.qbe.cotizador.model.AutorizacionSri;

public class AutorizacionSriTransaction {
	
	public AutorizacionSriTransaction() {       
    }

	public AutorizacionSri crear(AutorizacionSri AutorizacionSri){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AutorizacionSriDAO AutorizacionSriDAO = new AutorizacionSriDAO();
		AutorizacionSri = AutorizacionSriDAO.crear(AutorizacionSri);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return AutorizacionSri;	
	}
	
	public AutorizacionSri editar(AutorizacionSri AutorizacionSri){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AutorizacionSriDAO AutorizacionSriDAO = new AutorizacionSriDAO();
		AutorizacionSri AutorizacionSriBuscada = AutorizacionSriDAO.buscarPorId(AutorizacionSri.getId());
		if(AutorizacionSriBuscada!=null){
			AutorizacionSri = AutorizacionSriDAO.editar(AutorizacionSri);
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
	return AutorizacionSri;
	}
	
	public void eliminar(AutorizacionSri AutorizacionSri){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AutorizacionSriDAO AutorizacionSriDAO = new AutorizacionSriDAO();
		AutorizacionSri AutorizacionSriBuscado = new AutorizacionSri();
		AutorizacionSriBuscado = AutorizacionSriDAO.buscarPorId(AutorizacionSri.getId());
		if(AutorizacionSriBuscado !=null){
			AutorizacionSriDAO.eliminar(AutorizacionSri);
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
