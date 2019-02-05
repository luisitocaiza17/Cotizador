package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.GrupoUsuarioAutorizacionDAO;
import com.qbe.cotizador.model.GrupoUsuarioAutorizacion;

public class GrupoUsuarioAutorizacionTransaction {
	
	public GrupoUsuarioAutorizacionTransaction() {       
    }

	public GrupoUsuarioAutorizacion crear(GrupoUsuarioAutorizacion GrupoUsuarioAutorizacion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoUsuarioAutorizacionDAO GrupoUsuarioAutorizacionDAO = new GrupoUsuarioAutorizacionDAO();
		GrupoUsuarioAutorizacion = GrupoUsuarioAutorizacionDAO.crear(GrupoUsuarioAutorizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoUsuarioAutorizacion;	
	}
	
	public GrupoUsuarioAutorizacion editar(GrupoUsuarioAutorizacion GrupoUsuarioAutorizacion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoUsuarioAutorizacionDAO GrupoUsuarioAutorizacionDAO = new GrupoUsuarioAutorizacionDAO();
		GrupoUsuarioAutorizacion = GrupoUsuarioAutorizacionDAO.editar(GrupoUsuarioAutorizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoUsuarioAutorizacion;
	}
	
	public void eliminar(GrupoUsuarioAutorizacion GrupoUsuarioAutorizacion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoUsuarioAutorizacionDAO GrupoUsuarioAutorizacionDAO = new GrupoUsuarioAutorizacionDAO();
		GrupoUsuarioAutorizacion GrupoUsuarioAutorizacionBuscado = new GrupoUsuarioAutorizacion();
		GrupoUsuarioAutorizacionBuscado = GrupoUsuarioAutorizacionDAO.buscarPorId(GrupoUsuarioAutorizacion.getId());
		if(GrupoUsuarioAutorizacionBuscado !=null){
			GrupoUsuarioAutorizacionDAO.eliminar(GrupoUsuarioAutorizacion);
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
