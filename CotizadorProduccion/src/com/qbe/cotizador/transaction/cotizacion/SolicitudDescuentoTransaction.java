package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.SolicitudDescuentoDAO;
import com.qbe.cotizador.model.SolicitudDescuento;

public class SolicitudDescuentoTransaction {
	
	public SolicitudDescuentoTransaction() {       
    }

	public SolicitudDescuento crear(SolicitudDescuento SolicitudDescuento){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudDescuentoDAO SolicitudDescuentoDAO = new SolicitudDescuentoDAO();
		SolicitudDescuento = SolicitudDescuentoDAO.crear(SolicitudDescuento);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return SolicitudDescuento;	
	}
	
	public SolicitudDescuento editar(SolicitudDescuento SolicitudDescuento){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudDescuentoDAO SolicitudDescuentoDAO = new SolicitudDescuentoDAO();
		SolicitudDescuento SolicitudDescuentoBuscada = SolicitudDescuentoDAO.buscarPorId(SolicitudDescuento.getId());
		if(SolicitudDescuentoBuscada!=null){
			SolicitudDescuento = SolicitudDescuentoDAO.editar(SolicitudDescuento);
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
	return SolicitudDescuento;
	}
	
	public void eliminar(SolicitudDescuento SolicitudDescuento){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudDescuentoDAO SolicitudDescuentoDAO = new SolicitudDescuentoDAO();
		SolicitudDescuento SolicitudDescuentoBuscado = new SolicitudDescuento();
		SolicitudDescuentoBuscado = SolicitudDescuentoDAO.buscarPorId(SolicitudDescuento.getId());
		if(SolicitudDescuentoBuscado !=null){
			SolicitudDescuentoDAO.eliminar(SolicitudDescuento);
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
