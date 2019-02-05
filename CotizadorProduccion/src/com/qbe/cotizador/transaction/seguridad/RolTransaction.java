package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.model.Rol;

public class RolTransaction {
	
	public RolTransaction() {       
    }

	public Rol crear(Rol Rol){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		RolDAO RolDAO = new RolDAO();
		Rol = RolDAO.crear(Rol);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Rol;	
	}
	
	public Rol editar(Rol Rol){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		RolDAO RolDAO = new RolDAO();
		Rol RolBuscada = RolDAO.buscarPorId(Rol.getId());
		if(RolBuscada!=null){
			Rol = RolDAO.editar(Rol);
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
	return Rol;
	}
	
	public void eliminar(Rol Rol){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		RolDAO RolDAO = new RolDAO();
		Rol RolBuscado = new Rol();
		RolBuscado = RolDAO.buscarPorId(Rol.getId());
		if(RolBuscado !=null){
			RolDAO.eliminar(Rol);
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
