package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.ganadero.PrecioReferencialGanaderoDAO;
import com.qbe.cotizador.model.PrecioReferencialGanadero;

public class PrecioReferencialGanaderoTransaction {
	
	public PrecioReferencialGanaderoTransaction() {       
    }

	public PrecioReferencialGanadero crear(PrecioReferencialGanadero PrecioReferencialGanadero){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PrecioReferencialGanaderoDAO PrecioReferencialGanaderoDAO = new PrecioReferencialGanaderoDAO();
		PrecioReferencialGanadero = PrecioReferencialGanaderoDAO.crear(PrecioReferencialGanadero);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return PrecioReferencialGanadero;	
	}
	
	public PrecioReferencialGanadero editar(PrecioReferencialGanadero PrecioReferencialGanadero){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PrecioReferencialGanaderoDAO PrecioReferencialGanaderoDAO = new PrecioReferencialGanaderoDAO();
		//PrecioReferencialGanadero PrecioReferencialGanaderoBuscada = PrecioReferencialGanaderoDAO.buscarPrecioReferencialGanaderoPorId(String.valueOf(PrecioReferencialGanadero.getId()));
		//if(PrecioReferencialGanaderoBuscada!=null){
			PrecioReferencialGanadero = PrecioReferencialGanaderoDAO.editar(PrecioReferencialGanadero);
			ut.commit();
		//}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return PrecioReferencialGanadero;
	}
	
	public void eliminar(PrecioReferencialGanadero PrecioReferencialGanadero){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PrecioReferencialGanaderoDAO PrecioReferencialGanaderoDAO = new PrecioReferencialGanaderoDAO();
		//PrecioReferencialGanadero PrecioReferencialGanaderoBuscado = PrecioReferencialGanaderoDAO.buscarPrecioReferencialGanaderoPorId(String.valueOf(PrecioReferencialGanadero.getId()));
		//if(PrecioReferencialGanaderoBuscado !=null){
			PrecioReferencialGanaderoDAO.eliminar(PrecioReferencialGanadero);
            ut.commit();
		//}
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
