package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.model.UnidadNegocio;

public class UnidadNegocioTransaction {
	
	public UnidadNegocioTransaction() {       
    }

	public UnidadNegocio crear(UnidadNegocio UnidadNegocio){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadNegocioDAO UnidadNegocioDAO = new UnidadNegocioDAO();
		UnidadNegocio = UnidadNegocioDAO.crear(UnidadNegocio);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return UnidadNegocio;	
	}
	
	public UnidadNegocio editar(UnidadNegocio UnidadNegocio){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadNegocioDAO UnidadNegocioDAO = new UnidadNegocioDAO();
		UnidadNegocio UnidadNegocioBuscada = UnidadNegocioDAO.buscarPorId(UnidadNegocio.getId());
		if(UnidadNegocioBuscada!=null){
			UnidadNegocio = UnidadNegocioDAO.editar(UnidadNegocio);
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
	return UnidadNegocio;
	}
	
	public void eliminar(UnidadNegocio UnidadNegocio){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadNegocioDAO UnidadNegocioDAO = new UnidadNegocioDAO();
		UnidadNegocio UnidadNegocioBuscado = new UnidadNegocio();
		UnidadNegocioBuscado = UnidadNegocioDAO.buscarPorId(UnidadNegocio.getId());
		if(UnidadNegocioBuscado !=null){
			UnidadNegocioDAO.eliminar(UnidadNegocio);
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
