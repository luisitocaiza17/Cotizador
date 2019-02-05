package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.model.Usuario;

public class UsuarioTransaction {
	
	public UsuarioTransaction() {       
    }

	public Usuario crear(Usuario Usuario){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		Usuario = UsuarioDAO.crear(Usuario);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Usuario;	
	}
	
	public Usuario editar(Usuario Usuario){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		Usuario UsuarioBuscada = UsuarioDAO.buscarPorId(Usuario.getId());
		if(UsuarioBuscada!=null){
			Usuario = UsuarioDAO.editar(Usuario);
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
	return Usuario;
	}
	
	public void eliminar(Usuario Usuario){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioDAO UsuarioDAO = new UsuarioDAO();
		Usuario UsuarioBuscado = new Usuario();
		UsuarioBuscado = UsuarioDAO.buscarPorId(Usuario.getId());
		if(UsuarioBuscado !=null){
			UsuarioDAO.eliminar(Usuario);
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
	
	public Boolean buscarPorConfirmacionMail(String confirmacionMail){	
		UserTransaction ut = null;
		Boolean retorno = false;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			retorno = usuarioDAO.buscarPorConfirmacionMail(confirmacionMail);			
	        ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return retorno;	
	}
}
