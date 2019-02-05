package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.model.ProductoXPuntoVenta;

public class ProductoXPuntoVentaTransaction {
	
	public ProductoXPuntoVentaTransaction() {       
    }

	public ProductoXPuntoVenta crear(ProductoXPuntoVenta ProductoXPuntoVenta){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoXPuntoVentaDAO ProductoXPuntoVentaDAO = new ProductoXPuntoVentaDAO();
		ProductoXPuntoVenta = ProductoXPuntoVentaDAO.crear(ProductoXPuntoVenta);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ProductoXPuntoVenta;	
	}
	
	public ProductoXPuntoVenta editar(ProductoXPuntoVenta ProductoXPuntoVenta){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoXPuntoVentaDAO ProductoXPuntoVentaDAO = new ProductoXPuntoVentaDAO();
		ProductoXPuntoVenta ProductoXPuntoVentaBuscada = ProductoXPuntoVentaDAO.buscarPorId(ProductoXPuntoVenta.getId());
		if(ProductoXPuntoVentaBuscada!=null){
			ProductoXPuntoVenta = ProductoXPuntoVentaDAO.editar(ProductoXPuntoVenta);
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
	return ProductoXPuntoVenta;
	}
	
	public void eliminar(ProductoXPuntoVenta ProductoXPuntoVenta){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoXPuntoVentaDAO ProductoXPuntoVentaDAO = new ProductoXPuntoVentaDAO();
		ProductoXPuntoVenta ProductoXPuntoVentaBuscado = new ProductoXPuntoVenta();
		ProductoXPuntoVentaBuscado = ProductoXPuntoVentaDAO.buscarPorId(ProductoXPuntoVenta.getId());
		if(ProductoXPuntoVentaBuscado !=null){
			ProductoXPuntoVentaDAO.eliminar(ProductoXPuntoVenta);
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
