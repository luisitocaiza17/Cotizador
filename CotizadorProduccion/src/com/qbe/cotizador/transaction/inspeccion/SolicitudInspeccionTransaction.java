package com.qbe.cotizador.transaction.inspeccion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.model.SolicitudInspeccion;

public class SolicitudInspeccionTransaction {
	
	public SolicitudInspeccionTransaction() {       
    }

	public SolicitudInspeccion crear(SolicitudInspeccion SolicitudInspeccion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudInspeccionDAO SolicitudInspeccionDAO = new SolicitudInspeccionDAO();
		SolicitudInspeccion = SolicitudInspeccionDAO.crear(SolicitudInspeccion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return SolicitudInspeccion;	
	}
	
	public SolicitudInspeccion editar(SolicitudInspeccion SolicitudInspeccion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudInspeccionDAO SolicitudInspeccionDAO = new SolicitudInspeccionDAO();
		SolicitudInspeccion SolicitudInspeccionBuscada = SolicitudInspeccionDAO.buscarPorId(SolicitudInspeccion.getId());
		if(SolicitudInspeccionBuscada!=null){
			SolicitudInspeccion = SolicitudInspeccionDAO.editar(SolicitudInspeccion);
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
	return SolicitudInspeccion;
	}
	
	public void eliminar(SolicitudInspeccion SolicitudInspeccion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudInspeccionDAO SolicitudInspeccionDAO = new SolicitudInspeccionDAO();
		SolicitudInspeccion SolicitudInspeccionBuscado = new SolicitudInspeccion();
		SolicitudInspeccionBuscado = SolicitudInspeccionDAO.buscarPorId(SolicitudInspeccion.getId());
		if(SolicitudInspeccionBuscado !=null){
			SolicitudInspeccionDAO.eliminar(SolicitudInspeccion);
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
