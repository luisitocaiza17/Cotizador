package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.DescuentoDAO;
import com.qbe.cotizador.model.Descuento;

public class DescuentoTransaction {
	
	public DescuentoTransaction() {       
    }

	public Descuento crear(Descuento Descuento){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DescuentoDAO DescuentoDAO = new DescuentoDAO();
		Descuento = DescuentoDAO.crear(Descuento);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Descuento;	
	}
	
	public Descuento editar(Descuento Descuento){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DescuentoDAO DescuentoDAO = new DescuentoDAO();
		Descuento DescuentoBuscada = DescuentoDAO.buscarPorId(Descuento.getId());
		if(DescuentoBuscada!=null){
			Descuento = DescuentoDAO.editar(Descuento);
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
	return Descuento;
	}
	
	public void eliminar(Descuento Descuento){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DescuentoDAO DescuentoDAO = new DescuentoDAO();
		Descuento DescuentoBuscado = new Descuento();
		DescuentoBuscado = DescuentoDAO.buscarPorId(Descuento.getId());
		if(DescuentoBuscado !=null){
			DescuentoDAO.eliminar(Descuento);
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
