package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.seguridad.NivelMenuDAO;
import com.qbe.cotizador.model.NivelMenu;

public class NivelMenuTransaction {
	
	public NivelMenuTransaction() {       
    }

	public NivelMenu crear(NivelMenu NivelMenu){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		NivelMenuDAO NivelMenuDAO = new NivelMenuDAO();
		NivelMenu = NivelMenuDAO.crear(NivelMenu);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return NivelMenu;	
	}
	
	public NivelMenu editar(NivelMenu NivelMenu){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		NivelMenuDAO NivelMenuDAO = new NivelMenuDAO();
		NivelMenu NivelMenuBuscada = NivelMenuDAO.buscarPorId(NivelMenu.getId());
		if(NivelMenuBuscada!=null){
			NivelMenu = NivelMenuDAO.editar(NivelMenu);
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
	return NivelMenu;
	}
	
	public void eliminar(NivelMenu NivelMenu){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		NivelMenuDAO NivelMenuDAO = new NivelMenuDAO();
		NivelMenu NivelMenuBuscado = new NivelMenu();
		NivelMenuBuscado = NivelMenuDAO.buscarPorId(NivelMenu.getId());
		if(NivelMenuBuscado !=null){
			NivelMenuDAO.eliminar(NivelMenu);
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
