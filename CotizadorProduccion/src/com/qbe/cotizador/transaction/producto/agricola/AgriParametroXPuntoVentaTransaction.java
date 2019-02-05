package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.ganadero.ParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.ParametroXPuntoVenta;

public class AgriParametroXPuntoVentaTransaction {

	
	public AgriParametroXPuntoVentaTransaction() {       
    }

	public AgriParametroXPuntoVenta crear(AgriParametroXPuntoVenta ParametroXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
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
	
	public AgriParametroXPuntoVenta editar(AgriParametroXPuntoVenta ParametroXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
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
	
	public void eliminar(AgriParametroXPuntoVenta ParametroXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		AgriParametroXPuntoVentaDAO ParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
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
