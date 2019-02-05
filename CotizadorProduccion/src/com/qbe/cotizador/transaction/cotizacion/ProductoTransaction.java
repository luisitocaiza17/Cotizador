package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.model.Producto;

public class ProductoTransaction {
	
	public ProductoTransaction() {       
    }

	public Producto crear(Producto Producto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoDAO ProductoDAO = new ProductoDAO();
		Producto = ProductoDAO.crear(Producto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Producto;	
	}
	
	public Producto editar(Producto Producto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoDAO ProductoDAO = new ProductoDAO();
		Producto ProductoBuscada = ProductoDAO.buscarPorId(Producto.getId());
		if(ProductoBuscada!=null){
			Producto = ProductoDAO.editar(Producto);
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
	return Producto;
	}
	
	public void eliminar(Producto Producto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoDAO ProductoDAO = new ProductoDAO();
		Producto ProductoBuscado = new Producto();
		ProductoBuscado = ProductoDAO.buscarPorId(Producto.getId());
		if(ProductoBuscado !=null){
			ProductoDAO.eliminar(Producto);
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
