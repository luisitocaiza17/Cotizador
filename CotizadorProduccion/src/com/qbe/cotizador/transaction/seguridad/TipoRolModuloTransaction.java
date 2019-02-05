package com.qbe.cotizador.transaction.seguridad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.seguridad.TipoRolModuloDAO;
import com.qbe.cotizador.model.TipoRolModulo;

public class TipoRolModuloTransaction {
	
	public TipoRolModuloTransaction() {       
    }

	public TipoRolModulo crear(TipoRolModulo TipoRolModulo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoRolModuloDAO TipoRolModuloDAO = new TipoRolModuloDAO();
		TipoRolModulo = TipoRolModuloDAO.crear(TipoRolModulo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoRolModulo;	
	}
	
	public TipoRolModulo editar(TipoRolModulo TipoRolModulo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoRolModuloDAO TipoRolModuloDAO = new TipoRolModuloDAO();
		TipoRolModulo TipoRolModuloBuscada = TipoRolModuloDAO.buscarPorId(TipoRolModulo.getId());
		if(TipoRolModuloBuscada!=null){
			TipoRolModulo = TipoRolModuloDAO.editar(TipoRolModulo);
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
	return TipoRolModulo;
	}
	
	public void eliminar(TipoRolModulo TipoRolModulo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoRolModuloDAO TipoRolModuloDAO = new TipoRolModuloDAO();
		TipoRolModulo TipoRolModuloBuscado = new TipoRolModulo();
		TipoRolModuloBuscado = TipoRolModuloDAO.buscarPorId(TipoRolModulo.getId());
		if(TipoRolModuloBuscado !=null){
			TipoRolModuloDAO.eliminar(TipoRolModulo);
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
