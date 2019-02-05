package com.qbe.cotizador.transaction.inspeccion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.inspeccion.DistanciaInspectorDAO;
import com.qbe.cotizador.model.DistanciaInspector;

public class DistanciaInspectorTransaction {
	
	public DistanciaInspectorTransaction() {       
    }

	public DistanciaInspector crear(DistanciaInspector DistanciaInspector){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DistanciaInspectorDAO DistanciaInspectorDAO = new DistanciaInspectorDAO();
		DistanciaInspector = DistanciaInspectorDAO.crear(DistanciaInspector);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return DistanciaInspector;	
	}
	
	public DistanciaInspector editar(DistanciaInspector DistanciaInspector){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DistanciaInspectorDAO DistanciaInspectorDAO = new DistanciaInspectorDAO();
		DistanciaInspector DistanciaInspectorBuscada = DistanciaInspectorDAO.buscarPorId(DistanciaInspector.getId());
		if(DistanciaInspectorBuscada!=null){
			DistanciaInspector = DistanciaInspectorDAO.editar(DistanciaInspector);
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
	return DistanciaInspector;
	}
	
	public void eliminar(DistanciaInspector DistanciaInspector){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DistanciaInspectorDAO DistanciaInspectorDAO = new DistanciaInspectorDAO();
		DistanciaInspector DistanciaInspectorBuscado = new DistanciaInspector();
		DistanciaInspectorBuscado = DistanciaInspectorDAO.buscarPorId(DistanciaInspector.getId());
		if(DistanciaInspectorBuscado !=null){
			DistanciaInspectorDAO.eliminar(DistanciaInspector);
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
