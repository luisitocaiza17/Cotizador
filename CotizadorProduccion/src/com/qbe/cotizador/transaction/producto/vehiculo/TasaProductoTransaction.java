package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculocerrado.TasaProductoDAO;
import com.qbe.cotizador.model.TasaProducto;

public class TasaProductoTransaction {
	
	public TasaProductoTransaction() {       
    }

	public TasaProducto crear(TasaProducto TasaProducto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TasaProductoDAO TasaProductoDAO = new TasaProductoDAO();
		TasaProducto = TasaProductoDAO.crear(TasaProducto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TasaProducto;	
	}
	
	public TasaProducto editar(TasaProducto TasaProducto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TasaProductoDAO TasaProductoDAO = new TasaProductoDAO();
		TasaProducto = TasaProductoDAO.editar(TasaProducto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TasaProducto;
	}
	
	public void eliminar(TasaProducto TasaProducto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TasaProductoDAO TasaProductoDAO = new TasaProductoDAO();
		TasaProducto TasaProductoBuscado = new TasaProducto();
		TasaProductoBuscado = TasaProductoDAO.buscarPorId(TasaProducto.getId());
		if(TasaProductoBuscado !=null){
			TasaProductoDAO.eliminar(TasaProducto);
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
