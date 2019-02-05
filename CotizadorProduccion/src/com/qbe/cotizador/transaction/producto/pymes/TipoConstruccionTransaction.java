package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.TipoConstruccionDAO;
import com.qbe.cotizador.model.TipoConstruccion;

public class TipoConstruccionTransaction {
	
	public TipoConstruccionTransaction() {       
    }

	public TipoConstruccion crear(TipoConstruccion TipoConstruccions) throws Exception{		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoConstruccionDAO TipoConstruccionsDAO = new TipoConstruccionDAO();
		TipoConstruccions = TipoConstruccionsDAO.crear(TipoConstruccions);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}					
	return TipoConstruccions;	
	}
	
	public TipoConstruccion editar(TipoConstruccion TipoConstruccions) throws Exception{
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoConstruccionDAO TipoConstruccionsDAO = new TipoConstruccionDAO();
		TipoConstruccions = TipoConstruccionsDAO.editar(TipoConstruccions);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}					
	return TipoConstruccions;
	}
	
	public void eliminar(TipoConstruccion TipoConstruccions) throws Exception{	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoConstruccionDAO TipoConstruccionsDAO = new TipoConstruccionDAO();
		TipoConstruccion TipoConstruccionsBuscado = new TipoConstruccion();
		TipoConstruccionsBuscado = TipoConstruccionsDAO.buscarPorId(TipoConstruccions.getId());
		if(TipoConstruccionsBuscado !=null){
			TipoConstruccionsDAO.eliminar(TipoConstruccions);
            ut.commit();
		}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		finally{
			e.printStackTrace();
			throw e;
		}				    
	}
	}
}
