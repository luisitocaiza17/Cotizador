package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.ganadero.ParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.ParametroXPuntoVenta;

public class ParametroXPuntoVentaTransaction {
	
	public ParametroXPuntoVentaTransaction() {       
    }

	public ParametroXPuntoVenta crear(ParametroXPuntoVenta ParametroXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new ParametroXPuntoVentaDAO();
		ParametroXPuntoVenta = ParametroXPuntoVentaDAO.crear(ParametroXPuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ParametroXPuntoVenta;	
	}
	
	public ParametroXPuntoVenta editar(ParametroXPuntoVenta ParametroXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new ParametroXPuntoVentaDAO();
//		ParametroXPuntoVenta ParametroXPuntoVentaBuscada = ParametroXPuntoVentaDAO.buscarParametroXPuntoVentaPorId(String.valueOf(ParametroXPuntoVenta.getId()));
//		if(ParametroXPuntoVentaBuscada!=null){
			ParametroXPuntoVenta = ParametroXPuntoVentaDAO.editar(ParametroXPuntoVenta);
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
	return ParametroXPuntoVenta;
	}
	
	public void eliminar(ParametroXPuntoVenta ParametroXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new ParametroXPuntoVentaDAO();
//		ParametroXPuntoVenta ParametroXPuntoVentaBuscado = ParametroXPuntoVentaDAO.buscarParametroXPuntoVentaPorId(String.valueOf(ParametroXPuntoVenta.getId()));
//		if(ParametroXPuntoVentaBuscado !=null){
			ParametroXPuntoVentaDAO.eliminar(ParametroXPuntoVenta);
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
