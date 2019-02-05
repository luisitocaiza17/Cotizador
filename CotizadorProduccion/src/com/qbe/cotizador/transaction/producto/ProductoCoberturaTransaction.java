package com.qbe.cotizador.transaction.producto;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculocerrado.ProductoCoberturaDAO;
import com.qbe.cotizador.model.ProductoCobertura;

public class ProductoCoberturaTransaction {
	
	public ProductoCoberturaTransaction() {       
    }

	public ProductoCobertura crear(ProductoCobertura ProductoCobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoCoberturaDAO ProductoCoberturaDAO = new ProductoCoberturaDAO();
		ProductoCobertura = ProductoCoberturaDAO.crear(ProductoCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ProductoCobertura;	
	}
	
	public ProductoCobertura editar(ProductoCobertura ProductoCobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoCoberturaDAO ProductoCoberturaDAO = new ProductoCoberturaDAO();
		ProductoCobertura = ProductoCoberturaDAO.editar(ProductoCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ProductoCobertura;
	}
	
	public void eliminar(ProductoCobertura ProductoCobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoCoberturaDAO ProductoCoberturaDAO = new ProductoCoberturaDAO();
		ProductoCobertura ProductoCoberturaBuscado = new ProductoCobertura();
		ProductoCoberturaBuscado = ProductoCoberturaDAO.buscarPorId(ProductoCobertura.getId());
		if(ProductoCoberturaBuscado !=null){
			ProductoCoberturaDAO.eliminar(ProductoCoberturaBuscado);
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
