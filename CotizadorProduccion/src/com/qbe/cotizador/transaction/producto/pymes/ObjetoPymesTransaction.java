package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.ObjetoPymesDAO;
import com.qbe.cotizador.model.ObjetoPyme;

public class ObjetoPymesTransaction {
	
	public ObjetoPymesTransaction() {       
    }

	public ObjetoPyme crear(ObjetoPyme ObjetoPymes) throws Exception{		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoPymesDAO ObjetoPymesDAO = new ObjetoPymesDAO();
		ObjetoPymes = ObjetoPymesDAO.crear(ObjetoPymes);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}					
	return ObjetoPymes;	
	}
	
	public ObjetoPyme editar(ObjetoPyme ObjetoPymes) throws Exception{
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoPymesDAO ObjetoPymesDAO = new ObjetoPymesDAO();
		ObjetoPymes = ObjetoPymesDAO.editar(ObjetoPymes);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}					
	return ObjetoPymes;
	}
	
	public void eliminar(ObjetoPyme ObjetoPymes) throws Exception{	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoPymesDAO ObjetoPymesDAO = new ObjetoPymesDAO();
		ObjetoPyme ObjetoPymesBuscado = new ObjetoPyme();
		ObjetoPymesBuscado = ObjetoPymesDAO.buscarPorId(ObjetoPymes.getId());
		if(ObjetoPymesBuscado !=null){
			ObjetoPymesDAO.eliminar(ObjetoPymes);
            ut.commit();
		}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}
	}
}
