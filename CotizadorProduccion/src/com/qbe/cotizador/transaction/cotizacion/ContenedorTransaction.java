package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.ContenedorDAO;
import com.qbe.cotizador.model.Contenedor;

public class ContenedorTransaction {

	public ContenedorTransaction() {
	}
	
	public Contenedor crear(Contenedor contenedor){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			ContenedorDAO contenedorDAO = new ContenedorDAO();
			contenedor = contenedorDAO.crear(contenedor);
	        ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return contenedor;	
		}
		
		public Contenedor editar(Contenedor contenedor){
			UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			ContenedorDAO contenedorDAO = new ContenedorDAO();
			contenedor = contenedorDAO.editar(contenedor);
	        ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return contenedor;
		}
		
		public void eliminar(Contenedor contenedor){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			ContenedorDAO contenedorDAO = new ContenedorDAO();
			Contenedor contenedorBuscado = new Contenedor();
			contenedorBuscado = contenedorDAO.buscarPorId(contenedor.getId());
			if(contenedorBuscado !=null){
				contenedorDAO.eliminar(contenedor);
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
