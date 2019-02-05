package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.seguridad.ModuloDAO;
import com.qbe.cotizador.model.Modulo;

public class ModuloTransaction {
	
	public ModuloTransaction() {       
    }

	public Modulo crear(Modulo Modulo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModuloDAO ModuloDAO = new ModuloDAO();
		Modulo = ModuloDAO.crear(Modulo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Modulo;	
	}
	
	public Modulo editar(Modulo Modulo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModuloDAO ModuloDAO = new ModuloDAO();
		Modulo ModuloBuscada = ModuloDAO.buscarPorId(Modulo.getId());
		if(ModuloBuscada!=null){
			Modulo = ModuloDAO.editar(Modulo);
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
	return Modulo;
	}
	
	public void eliminar(Modulo Modulo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModuloDAO ModuloDAO = new ModuloDAO();
		Modulo ModuloBuscado = new Modulo();
		ModuloBuscado = ModuloDAO.buscarPorId(Modulo.getId());
		if(ModuloBuscado !=null){
			ModuloDAO.eliminar(Modulo);
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
