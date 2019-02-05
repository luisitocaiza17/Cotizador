package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.ganadero.VigenciaPolizaXPuntoVentaDAO;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVenta;

public class VigenciaPolizaXPuntoVentaTransaction {
	
	public VigenciaPolizaXPuntoVentaTransaction() {       
    }

	public VigenciaPolizaXPuntoVenta crear(VigenciaPolizaXPuntoVenta VigenciaPolizaXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VigenciaPolizaXPuntoVentaDAO VigenciaPolizaXPuntoVentaDAO = new VigenciaPolizaXPuntoVentaDAO();
		VigenciaPolizaXPuntoVenta = VigenciaPolizaXPuntoVentaDAO.crear(VigenciaPolizaXPuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return VigenciaPolizaXPuntoVenta;	
	}
	
	public VigenciaPolizaXPuntoVenta editar(VigenciaPolizaXPuntoVenta VigenciaPolizaXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VigenciaPolizaXPuntoVentaDAO VigenciaPolizaXPuntoVentaDAO = new VigenciaPolizaXPuntoVentaDAO();
		//VigenciaPolizaXPuntoVenta VigenciaPolizaXPuntoVentaBuscada = VigenciaPolizaXPuntoVentaDAO.buscarPorId(VigenciaPolizaXPuntoVenta.getId());
		//if(VigenciaPolizaXPuntoVentaBuscada!=null){
			VigenciaPolizaXPuntoVenta = VigenciaPolizaXPuntoVentaDAO.editar(VigenciaPolizaXPuntoVenta);
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
	return VigenciaPolizaXPuntoVenta;
	}
	
	public void eliminar(VigenciaPolizaXPuntoVenta VigenciaPolizaXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VigenciaPolizaXPuntoVentaDAO VigenciaPolizaXPuntoVentaDAO = new VigenciaPolizaXPuntoVentaDAO();
		VigenciaPolizaXPuntoVenta VigenciaPolizaXPuntoVentaBuscado = new VigenciaPolizaXPuntoVenta();
		//VigenciaPolizaXPuntoVentaBuscado = VigenciaPolizaXPuntoVentaDAO.buscarPorId(VigenciaPolizaXPuntoVenta.getId());
		//if(VigenciaPolizaXPuntoVentaBuscado !=null){
			VigenciaPolizaXPuntoVentaDAO.eliminar(VigenciaPolizaXPuntoVenta);
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
