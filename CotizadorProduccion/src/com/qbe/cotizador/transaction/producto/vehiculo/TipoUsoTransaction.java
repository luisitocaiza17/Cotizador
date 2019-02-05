package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.TipoUsoDAO;
import com.qbe.cotizador.model.TipoUso;

public class TipoUsoTransaction {
	
	public TipoUsoTransaction() {       
    }

	public TipoUso crear(TipoUso TipoUso){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoUsoDAO TipoUsoDAO = new TipoUsoDAO();
		TipoUso = TipoUsoDAO.crear(TipoUso);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoUso;	
	}
	
	public TipoUso editar(TipoUso TipoUso){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoUsoDAO TipoUsoDAO = new TipoUsoDAO();
		TipoUso = TipoUsoDAO.editar(TipoUso);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoUso;
	}
	
	public void eliminar(TipoUso TipoUso){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoUsoDAO TipoUsoDAO = new TipoUsoDAO();
		TipoUso TipoUsoBuscado = new TipoUso();
		TipoUsoBuscado = TipoUsoDAO.buscarPorId(TipoUso.getId());
		if(TipoUsoBuscado !=null){
			TipoUsoDAO.eliminar(TipoUso);
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
