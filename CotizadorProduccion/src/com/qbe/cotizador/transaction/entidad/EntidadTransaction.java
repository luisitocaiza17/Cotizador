package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.model.Entidad;

public class EntidadTransaction {
	
	public EntidadTransaction() {       
    }

	public Entidad crear(Entidad Entidad){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadDAO EntidadDAO = new EntidadDAO();
		Entidad = EntidadDAO.crear(Entidad);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Entidad;	
	}
	
	public Entidad editar(Entidad Entidad){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadDAO EntidadDAO = new EntidadDAO();
		Entidad EntidadBuscada = EntidadDAO.buscarPorId(Entidad.getId());
		if(EntidadBuscada!=null){
			Entidad = EntidadDAO.editar(Entidad);
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
	return Entidad;
	}
	
	public void eliminar(Entidad Entidad){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EntidadDAO EntidadDAO = new EntidadDAO();
		Entidad EntidadBuscado = new Entidad();
		EntidadBuscado = EntidadDAO.buscarPorId(Entidad.getId());
		if(EntidadBuscado !=null){
			EntidadDAO.eliminar(Entidad);
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
