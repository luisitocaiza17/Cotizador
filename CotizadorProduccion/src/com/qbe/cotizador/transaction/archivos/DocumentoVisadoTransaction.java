package com.qbe.cotizador.transaction.archivos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.model.DocumentoVisado;

public class DocumentoVisadoTransaction {
	
	public DocumentoVisadoTransaction() {       
    }

	public DocumentoVisado crear(DocumentoVisado archivo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DocumentoVisadoDAO archivoDAO = new DocumentoVisadoDAO();
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
	
	public DocumentoVisado editar(DocumentoVisado archivo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DocumentoVisadoDAO archivoDAO = new DocumentoVisadoDAO();
		DocumentoVisado archivoBuscada = archivoDAO.buscarPorId(archivo.getId());
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
	
	public void eliminar(DocumentoVisado archivo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DocumentoVisadoDAO archivoDAO = new DocumentoVisadoDAO();
		DocumentoVisado archivoBuscado = new DocumentoVisado();
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
