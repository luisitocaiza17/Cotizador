package com.qbe.cotizador.transaction.inspeccion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.inspeccion.ComentarioSolicitudInspeccionDAO;
import com.qbe.cotizador.model.ComentarioSolicitudInspeccion;

public class ComentarioSolicitudInspeccionTransaction {
	
	public ComentarioSolicitudInspeccionTransaction() {       
    }

	public ComentarioSolicitudInspeccion crear(ComentarioSolicitudInspeccion ComentarioSolicitudInspeccion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ComentarioSolicitudInspeccionDAO ComentarioSolicitudInspeccionDAO = new ComentarioSolicitudInspeccionDAO();
		ComentarioSolicitudInspeccion = ComentarioSolicitudInspeccionDAO.crear(ComentarioSolicitudInspeccion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ComentarioSolicitudInspeccion;	
	}
	
	public ComentarioSolicitudInspeccion editar(ComentarioSolicitudInspeccion ComentarioSolicitudInspeccion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ComentarioSolicitudInspeccionDAO ComentarioSolicitudInspeccionDAO = new ComentarioSolicitudInspeccionDAO();
		ComentarioSolicitudInspeccion ComentarioSolicitudInspeccionBuscada = ComentarioSolicitudInspeccionDAO.buscarPorId(ComentarioSolicitudInspeccion.getId());
		if(ComentarioSolicitudInspeccionBuscada!=null){
			ComentarioSolicitudInspeccion = ComentarioSolicitudInspeccionDAO.editar(ComentarioSolicitudInspeccion);
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
	return ComentarioSolicitudInspeccion;
	}
	
	public void eliminar(ComentarioSolicitudInspeccion ComentarioSolicitudInspeccion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ComentarioSolicitudInspeccionDAO ComentarioSolicitudInspeccionDAO = new ComentarioSolicitudInspeccionDAO();
		ComentarioSolicitudInspeccion ComentarioSolicitudInspeccionBuscado = new ComentarioSolicitudInspeccion();
		ComentarioSolicitudInspeccionBuscado = ComentarioSolicitudInspeccionDAO.buscarPorId(ComentarioSolicitudInspeccion.getId());
		if(ComentarioSolicitudInspeccionBuscado !=null){
			ComentarioSolicitudInspeccionDAO.eliminar(ComentarioSolicitudInspeccion);
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
