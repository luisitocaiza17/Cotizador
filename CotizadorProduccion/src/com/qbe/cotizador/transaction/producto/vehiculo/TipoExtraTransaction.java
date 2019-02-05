package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.TipoExtraDAO;
import com.qbe.cotizador.model.TipoExtra;

public class TipoExtraTransaction {
	
	public TipoExtraTransaction() {       
    }

	public TipoExtra crear(TipoExtra TipoExtra){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoExtraDAO TipoExtraDAO = new TipoExtraDAO();
		TipoExtra = TipoExtraDAO.crear(TipoExtra);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoExtra;	
	}
	
	public TipoExtra editar(TipoExtra TipoExtra){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoExtraDAO TipoExtraDAO = new TipoExtraDAO();
		TipoExtra = TipoExtraDAO.editar(TipoExtra);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoExtra;
	}
	
	public void eliminar(TipoExtra TipoExtra){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoExtraDAO TipoExtraDAO = new TipoExtraDAO();
		TipoExtra TipoExtraBuscado = new TipoExtra();
		TipoExtraBuscado = TipoExtraDAO.buscarPorId(TipoExtra.getId());
		if(TipoExtraBuscado !=null){
			TipoExtraDAO.eliminar(TipoExtra);
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
