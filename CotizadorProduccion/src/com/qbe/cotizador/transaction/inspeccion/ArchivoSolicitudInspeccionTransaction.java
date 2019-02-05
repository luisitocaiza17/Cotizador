package com.qbe.cotizador.transaction.inspeccion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.inspeccion.ArchivoSolicitudInspeccionDAO;
import com.qbe.cotizador.model.ArchivoSolicitudInspeccion;

public class ArchivoSolicitudInspeccionTransaction {
	
	public ArchivoSolicitudInspeccionTransaction() {       
    }

	public ArchivoSolicitudInspeccion crear(ArchivoSolicitudInspeccion ArchivoSolicitudInspeccion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO ArchivoSolicitudInspeccionDAO = new ArchivoSolicitudInspeccionDAO();
		ArchivoSolicitudInspeccion = ArchivoSolicitudInspeccionDAO.crear(ArchivoSolicitudInspeccion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ArchivoSolicitudInspeccion;	
	}
	
	public ArchivoSolicitudInspeccion editar(ArchivoSolicitudInspeccion ArchivoSolicitudInspeccion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO ArchivoSolicitudInspeccionDAO = new ArchivoSolicitudInspeccionDAO();
		ArchivoSolicitudInspeccion ArchivoSolicitudInspeccionBuscada = ArchivoSolicitudInspeccionDAO.buscarPorId(ArchivoSolicitudInspeccion.getId());
		if(ArchivoSolicitudInspeccionBuscada!=null){
			ArchivoSolicitudInspeccion = ArchivoSolicitudInspeccionDAO.editar(ArchivoSolicitudInspeccion);
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
	return ArchivoSolicitudInspeccion;
	}
	
	public void eliminar(ArchivoSolicitudInspeccion ArchivoSolicitudInspeccion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO ArchivoSolicitudInspeccionDAO = new ArchivoSolicitudInspeccionDAO();
		ArchivoSolicitudInspeccion ArchivoSolicitudInspeccionBuscado = new ArchivoSolicitudInspeccion();
		ArchivoSolicitudInspeccionBuscado = ArchivoSolicitudInspeccionDAO.buscarPorId(ArchivoSolicitudInspeccion.getId());
		if(ArchivoSolicitudInspeccionBuscado !=null){
			ArchivoSolicitudInspeccionDAO.eliminar(ArchivoSolicitudInspeccion);
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
