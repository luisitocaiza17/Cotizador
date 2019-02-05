package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.model.Direccion;

public class DireccionTransaction {
	
	public DireccionTransaction() {       
    }

	public Direccion crear(Direccion Direccion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DireccionDAO DireccionDAO = new DireccionDAO();
		Direccion = DireccionDAO.crear(Direccion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Direccion;	
	}
	
	public Direccion editar(Direccion Direccion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DireccionDAO DireccionDAO = new DireccionDAO();
		Direccion DireccionBuscada = DireccionDAO.buscarPorId(Direccion.getId());
		if(DireccionBuscada!=null){
			Direccion = DireccionDAO.editar(Direccion);
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
	return Direccion;
	}
	
	public void eliminar(Direccion Direccion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DireccionDAO DireccionDAO = new DireccionDAO();
		Direccion DireccionBuscado = new Direccion();
		DireccionBuscado = DireccionDAO.buscarPorId(Direccion.getId());
		if(DireccionBuscado !=null){
			DireccionDAO.eliminar(Direccion);
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
