package com.qbe.cotizador.transaction.pagos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.pagos.PagoDAO;
import com.qbe.cotizador.model.Pago;

public class PagoTransaction {
	
	public PagoTransaction() {       
    }

	public Pago crear(Pago Pago){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PagoDAO PagoDAO = new PagoDAO();
		Pago = PagoDAO.crear(Pago);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Pago;	
	}
	
	public Pago editar(Pago Pago){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PagoDAO PagoDAO = new PagoDAO();
		Pago PagoBuscada = PagoDAO.buscarPorId(Pago.getId());
		if(PagoBuscada!=null){
			Pago = PagoDAO.editar(Pago);
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
	return Pago;
	}
	
	public void eliminar(Pago Pago){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PagoDAO PagoDAO = new PagoDAO();
		Pago PagoBuscado = new Pago();
		PagoBuscado = PagoDAO.buscarPorId(Pago.getId());
		if(PagoBuscado !=null){
			PagoDAO.eliminar(Pago);
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
