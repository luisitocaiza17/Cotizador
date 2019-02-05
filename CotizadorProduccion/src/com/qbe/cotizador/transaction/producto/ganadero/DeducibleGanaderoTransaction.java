package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.ganadero.DeducibleGanaderoDAO;
import com.qbe.cotizador.model.DeducibleGanadero;

public class DeducibleGanaderoTransaction {
	
	public DeducibleGanaderoTransaction() {       
    }

	public DeducibleGanadero crear(DeducibleGanadero DeducibleGanadero){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleGanaderoDAO DeducibleGanaderoDAO = new DeducibleGanaderoDAO();
		DeducibleGanadero = DeducibleGanaderoDAO.crear(DeducibleGanadero);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return DeducibleGanadero;	
	}
	
	public DeducibleGanadero editar(DeducibleGanadero DeducibleGanadero){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleGanaderoDAO DeducibleGanaderoDAO = new DeducibleGanaderoDAO();
		DeducibleGanadero DeducibleGanaderoBuscada = DeducibleGanaderoDAO.buscarPorId(DeducibleGanadero.getId());
		if(DeducibleGanaderoBuscada!=null){
			DeducibleGanadero = DeducibleGanaderoDAO.editar(DeducibleGanadero);
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
	return DeducibleGanadero;
	}
	
	public void eliminar(DeducibleGanadero DeducibleGanadero){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DeducibleGanaderoDAO DeducibleGanaderoDAO = new DeducibleGanaderoDAO();
		DeducibleGanadero DeducibleGanaderoBuscado = new DeducibleGanadero();
		DeducibleGanaderoBuscado = DeducibleGanaderoDAO.buscarPorId(DeducibleGanadero.getId());
		if(DeducibleGanaderoBuscado !=null){
			DeducibleGanaderoDAO.eliminar(DeducibleGanadero);
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
