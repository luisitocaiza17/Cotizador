package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.UsuarioXPuntoVentaDAO;
import com.qbe.cotizador.model.UsuarioXPuntoVenta;

public class UsuarioXPuntoVentaTransaction {
	
	public UsuarioXPuntoVentaTransaction() {       
    }

	public UsuarioXPuntoVenta crear(UsuarioXPuntoVenta UsuarioXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioXPuntoVentaDAO UsuarioXPuntoVentaDAO = new UsuarioXPuntoVentaDAO();
		UsuarioXPuntoVenta = UsuarioXPuntoVentaDAO.crear(UsuarioXPuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return UsuarioXPuntoVenta;	
	}
	
	public UsuarioXPuntoVenta editar(UsuarioXPuntoVenta UsuarioXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioXPuntoVentaDAO UsuarioXPuntoVentaDAO = new UsuarioXPuntoVentaDAO();
		UsuarioXPuntoVenta UsuarioXPuntoVentaBuscada = UsuarioXPuntoVentaDAO.buscarPorId(UsuarioXPuntoVenta.getId());
		if(UsuarioXPuntoVentaBuscada!=null){
			UsuarioXPuntoVenta = UsuarioXPuntoVentaDAO.editar(UsuarioXPuntoVenta);
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
	return UsuarioXPuntoVenta;
	}
	
	public void eliminar(UsuarioXPuntoVenta UsuarioXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		UsuarioXPuntoVentaDAO UsuarioXPuntoVentaDAO = new UsuarioXPuntoVentaDAO();
		UsuarioXPuntoVenta UsuarioXPuntoVentaBuscado = new UsuarioXPuntoVenta();
		UsuarioXPuntoVentaBuscado = UsuarioXPuntoVentaDAO.buscarPorId(UsuarioXPuntoVenta.getId());
		if(UsuarioXPuntoVentaBuscado !=null){
			UsuarioXPuntoVentaDAO.eliminar(UsuarioXPuntoVenta);
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
