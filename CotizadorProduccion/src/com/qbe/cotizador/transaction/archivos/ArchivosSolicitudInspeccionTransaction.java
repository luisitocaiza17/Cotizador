package com.qbe.cotizador.transaction.archivos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.inspeccion.ArchivoSolicitudInspeccionDAO;
import com.qbe.cotizador.model.ArchivoSolicitudInspeccion;

public class ArchivosSolicitudInspeccionTransaction {
	
	public ArchivosSolicitudInspeccionTransaction() {       
    }

	public ArchivoSolicitudInspeccion crear(ArchivoSolicitudInspeccion archivo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO archivoDAO = new ArchivoSolicitudInspeccionDAO();
		archivo = archivoDAO.crear(archivo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return archivo;	
	}
	
	public ArchivoSolicitudInspeccion editar(ArchivoSolicitudInspeccion archivo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO archivoDAO = new ArchivoSolicitudInspeccionDAO();
		ArchivoSolicitudInspeccion archivoBuscada = archivoDAO.buscarPorId(archivo.getId());
		if(archivoBuscada!=null){
			archivo = archivoDAO.editar(archivo);
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
	return archivo;
	}
	
	public void eliminar(ArchivoSolicitudInspeccion archivo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoSolicitudInspeccionDAO archivoDAO = new ArchivoSolicitudInspeccionDAO();
		ArchivoSolicitudInspeccion archivoBuscado = new ArchivoSolicitudInspeccion();
		archivoBuscado = archivoDAO.buscarPorId(archivo.getId());
		if(archivoBuscado !=null){
			archivoDAO.eliminar(archivo);
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
