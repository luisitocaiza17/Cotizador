package com.qbe.cotizador.transaction.producto;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.DetalleProductoDAO;
import com.qbe.cotizador.model.DetalleProducto;

public class DetalleProductoTransaction {
	
	public DetalleProductoTransaction() {       
    }

	public DetalleProducto crear(DetalleProducto DetalleProducto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DetalleProductoDAO DetalleProductoDAO = new DetalleProductoDAO();
		DetalleProducto = DetalleProductoDAO.crear(DetalleProducto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return DetalleProducto;	
	}
	
	public DetalleProducto editar(DetalleProducto DetalleProducto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DetalleProductoDAO DetalleProductoDAO = new DetalleProductoDAO();
		DetalleProducto DetalleProductoBuscada = DetalleProductoDAO.buscarPorId(DetalleProducto.getId());
		if(DetalleProductoBuscada!=null){
			DetalleProducto = DetalleProductoDAO.editar(DetalleProducto);
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
	return DetalleProducto;
	}
	
	public void eliminar(DetalleProducto DetalleProducto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		DetalleProductoDAO DetalleProductoDAO = new DetalleProductoDAO();
		DetalleProducto DetalleProductoBuscado = new DetalleProducto();
		DetalleProductoBuscado = DetalleProductoDAO.buscarPorId(DetalleProducto.getId());
		if(DetalleProductoBuscado !=null){
			DetalleProductoDAO.eliminar(DetalleProducto);
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
