package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.TipoOcupacionDAO;
import com.qbe.cotizador.model.TipoOcupacion;

public class TipoOcupacionTransaction {
	
	public TipoOcupacionTransaction() {       
    }

	public TipoOcupacion crear(TipoOcupacion TipoOcupacion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoOcupacionDAO TipoOcupacionsDAO = new TipoOcupacionDAO();
		TipoOcupacion = TipoOcupacionsDAO.crear(TipoOcupacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoOcupacion;	
	}
	
	public TipoOcupacion editar(TipoOcupacion TipoOcupacions){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoOcupacionDAO TipoOcupacionsDAO = new TipoOcupacionDAO();
		TipoOcupacions = TipoOcupacionsDAO.editar(TipoOcupacions);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoOcupacions;
	}
	
	public void eliminar(TipoOcupacion TipoOcupacion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoOcupacionDAO TipoOcupacionsDAO = new TipoOcupacionDAO();
		TipoOcupacion TipoOcupacionsBuscado = new TipoOcupacion();
		TipoOcupacionsBuscado = TipoOcupacionsDAO.buscarPorId(TipoOcupacion.getId());
		if(TipoOcupacionsBuscado !=null){
			TipoOcupacionsDAO.eliminar(TipoOcupacion);
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
