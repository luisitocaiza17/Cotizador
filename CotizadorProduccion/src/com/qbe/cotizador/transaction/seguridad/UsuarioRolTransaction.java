package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.seguridad.UsuarioRolDAO;
import com.qbe.cotizador.model.UsuarioRol;

public class UsuarioRolTransaction {
	
	public UsuarioRolTransaction() {       
    }

	public UsuarioRol crear(UsuarioRol UsuarioRol){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioRolDAO UsuarioRolDAO = new UsuarioRolDAO();
		UsuarioRol = UsuarioRolDAO.crear(UsuarioRol);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return UsuarioRol;	
	}
	
	public UsuarioRol editar(UsuarioRol UsuarioRol){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioRolDAO UsuarioRolDAO = new UsuarioRolDAO();
		UsuarioRol UsuarioRolBuscada = UsuarioRolDAO.buscarPorId(UsuarioRol.getId());
		if(UsuarioRolBuscada!=null){
			UsuarioRol = UsuarioRolDAO.editar(UsuarioRol);
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
	return UsuarioRol;
	}
	
	public void eliminar(UsuarioRol UsuarioRol){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioRolDAO UsuarioRolDAO = new UsuarioRolDAO();
		UsuarioRol UsuarioRolBuscado = new UsuarioRol();
		UsuarioRolBuscado = UsuarioRolDAO.buscarPorId(UsuarioRol.getId());
		if(UsuarioRolBuscado !=null){
			UsuarioRolDAO.eliminar(UsuarioRol);
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
