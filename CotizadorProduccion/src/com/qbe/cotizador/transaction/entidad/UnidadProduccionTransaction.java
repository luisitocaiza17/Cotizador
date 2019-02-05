package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.UnidadProduccionDAO;
import com.qbe.cotizador.model.UnidadProduccion;

public class UnidadProduccionTransaction {
	
	public UnidadProduccionTransaction() {       
    }

	public UnidadProduccion crear(UnidadProduccion UnidadProduccion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadProduccionDAO UnidadProduccionDAO = new UnidadProduccionDAO();
		UnidadProduccion = UnidadProduccionDAO.crear(UnidadProduccion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return UnidadProduccion;	
	}
	
	public UnidadProduccion editar(UnidadProduccion UnidadProduccion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadProduccionDAO UnidadProduccionDAO = new UnidadProduccionDAO();
		UnidadProduccion UnidadProduccionBuscada = UnidadProduccionDAO.buscarPorId(UnidadProduccion.getId());
		if(UnidadProduccionBuscada!=null){
			UnidadProduccion = UnidadProduccionDAO.editar(UnidadProduccion);
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
	return UnidadProduccion;
	}
	
	public void eliminar(UnidadProduccion UnidadProduccion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UnidadProduccionDAO UnidadProduccionDAO = new UnidadProduccionDAO();
		UnidadProduccion UnidadProduccionBuscado = new UnidadProduccion();
		UnidadProduccionBuscado = UnidadProduccionDAO.buscarPorId(UnidadProduccion.getId());
		if(UnidadProduccionBuscado !=null){
			UnidadProduccionDAO.eliminar(UnidadProduccion);
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
