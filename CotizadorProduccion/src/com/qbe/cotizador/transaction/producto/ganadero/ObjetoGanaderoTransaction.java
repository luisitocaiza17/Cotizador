package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDAO;
import com.qbe.cotizador.model.ObjetoGanadero;

public class ObjetoGanaderoTransaction {
	
	public ObjetoGanaderoTransaction() {       
    }

	public ObjetoGanadero crear(ObjetoGanadero ObjetoGanadero){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDAO ObjetoGanaderoDAO = new ObjetoGanaderoDAO();
		ObjetoGanadero = ObjetoGanaderoDAO.crear(ObjetoGanadero);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoGanadero;	
	}
	
	public ObjetoGanadero editar(ObjetoGanadero ObjetoGanadero){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDAO ObjetoGanaderoDAO = new ObjetoGanaderoDAO();
		ObjetoGanadero ObjetoGanaderoBuscada = ObjetoGanaderoDAO.buscarPorId(ObjetoGanadero.getId());
		if(ObjetoGanaderoBuscada!=null){
			ObjetoGanadero = ObjetoGanaderoDAO.editar(ObjetoGanadero);
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
	return ObjetoGanadero;
	}
	
	public void eliminar(ObjetoGanadero ObjetoGanadero){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDAO ObjetoGanaderoDAO = new ObjetoGanaderoDAO();
		ObjetoGanadero ObjetoGanaderoBuscado = new ObjetoGanadero();
		ObjetoGanaderoBuscado = ObjetoGanaderoDAO.buscarPorId(ObjetoGanadero.getId());
		if(ObjetoGanaderoBuscado !=null){
			ObjetoGanaderoDAO.eliminar(ObjetoGanadero);
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
