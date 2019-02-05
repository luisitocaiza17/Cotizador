package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.model.Upla;

public class UplaTransaction {
	
	public UplaTransaction() {       
    }

	public Upla crear(Upla Upla){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UplaDAO UplaDAO = new UplaDAO();
		Upla = UplaDAO.crear(Upla);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Upla;	
	}
	
	public Upla editar(Upla Upla){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UplaDAO UplaDAO = new UplaDAO();
		Upla UplaBuscada = UplaDAO.buscarPorId(Upla.getId());
		if(UplaBuscada!=null){
			Upla = UplaDAO.editar(Upla);
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
	return Upla;
	}
	
	public void eliminar(Upla Upla){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UplaDAO UplaDAO = new UplaDAO();
		Upla UplaBuscado = new Upla();
		UplaBuscado = UplaDAO.buscarPorId(Upla.getId());
		if(UplaBuscado !=null){
			UplaDAO.eliminar(Upla);
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
