package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.GrupoAutorizacionDAO;
import com.qbe.cotizador.model.GrupoAutorizacion;

public class GrupoAutorizacionTransaction {
	
	public GrupoAutorizacionTransaction() {       
    }

	public GrupoAutorizacion crear(GrupoAutorizacion GrupoAutorizacion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoAutorizacionDAO GrupoAutorizacionDAO = new GrupoAutorizacionDAO();
		GrupoAutorizacion = GrupoAutorizacionDAO.crear(GrupoAutorizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoAutorizacion;	
	}
	
	public GrupoAutorizacion editar(GrupoAutorizacion GrupoAutorizacion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoAutorizacionDAO GrupoAutorizacionDAO = new GrupoAutorizacionDAO();
		GrupoAutorizacion = GrupoAutorizacionDAO.editar(GrupoAutorizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoAutorizacion;
	}
	
	public void eliminar(GrupoAutorizacion GrupoAutorizacion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoAutorizacionDAO GrupoAutorizacionDAO = new GrupoAutorizacionDAO();
		GrupoAutorizacion GrupoAutorizacionBuscado = new GrupoAutorizacion();
		GrupoAutorizacionBuscado = GrupoAutorizacionDAO.buscarPorId(GrupoAutorizacion.getId());
		if(GrupoAutorizacionBuscado !=null){
			GrupoAutorizacionDAO.eliminar(GrupoAutorizacion);
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
