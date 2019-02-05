package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.DeducibleDAO;
import com.qbe.cotizador.model.Deducible;

public class DeducibleTransaction {
	
	public DeducibleTransaction() {       
    }

	public Deducible crear(Deducible Deducible){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleDAO DeducibleDAO = new DeducibleDAO();
		Deducible = DeducibleDAO.crear(Deducible);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Deducible;	
	}
	
	public Deducible editar(Deducible Deducible){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleDAO DeducibleDAO = new DeducibleDAO();
		Deducible DeducibleBuscada = DeducibleDAO.buscarPorId(Deducible.getId());
		if(DeducibleBuscada!=null){
			Deducible = DeducibleDAO.editar(Deducible);
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
	return Deducible;
	}
	
	public void eliminar(Deducible Deducible){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleDAO DeducibleDAO = new DeducibleDAO();
		Deducible DeducibleBuscado = new Deducible();
		DeducibleBuscado = DeducibleDAO.buscarPorId(Deducible.getId());
		if(DeducibleBuscado !=null){
			DeducibleDAO.eliminar(Deducible);
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
