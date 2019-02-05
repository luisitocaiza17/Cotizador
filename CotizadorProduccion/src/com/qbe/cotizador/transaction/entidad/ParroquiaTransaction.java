package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.model.Parroquia;

public class ParroquiaTransaction {
	
	public ParroquiaTransaction() {       
    }

	public Parroquia crear(Parroquia Parroquia){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParroquiaDAO ParroquiaDAO = new ParroquiaDAO();
		Parroquia = ParroquiaDAO.crear(Parroquia);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Parroquia;	
	}
	
	public Parroquia editar(Parroquia Parroquia){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParroquiaDAO ParroquiaDAO = new ParroquiaDAO();
		Parroquia ParroquiaBuscada = ParroquiaDAO.buscarPorId(Parroquia.getId());
		if(ParroquiaBuscada!=null){
			Parroquia = ParroquiaDAO.editar(Parroquia);
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
	return Parroquia;
	}
	
	public void eliminar(Parroquia Parroquia){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParroquiaDAO ParroquiaDAO = new ParroquiaDAO();
		Parroquia ParroquiaBuscado = new Parroquia();
		ParroquiaBuscado = ParroquiaDAO.buscarPorId(Parroquia.getId());
		if(ParroquiaBuscado !=null){
			ParroquiaDAO.eliminar(Parroquia);
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
