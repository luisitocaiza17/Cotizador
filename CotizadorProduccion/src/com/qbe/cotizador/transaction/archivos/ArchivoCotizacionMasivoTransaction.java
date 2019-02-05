package com.qbe.cotizador.transaction.archivos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.ArchivoCotizacionMasivosDAO;
import com.qbe.cotizador.model.ArchivoCotizacionMasivo;

public class ArchivoCotizacionMasivoTransaction {
	
	public ArchivoCotizacionMasivoTransaction() {       
    }

	public ArchivoCotizacionMasivo crear(ArchivoCotizacionMasivo archivo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoCotizacionMasivosDAO archivoDAO = new ArchivoCotizacionMasivosDAO();
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
	
	public ArchivoCotizacionMasivo editar(ArchivoCotizacionMasivo archivo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoCotizacionMasivosDAO archivoDAO = new ArchivoCotizacionMasivosDAO();
		ArchivoCotizacionMasivo archivoBuscada = archivoDAO.buscarPorId(archivo.getId());
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
	
	public void eliminar(ArchivoCotizacionMasivo archivo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ArchivoCotizacionMasivosDAO archivoDAO = new ArchivoCotizacionMasivosDAO();
		ArchivoCotizacionMasivo archivoBuscado = new ArchivoCotizacionMasivo();
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
