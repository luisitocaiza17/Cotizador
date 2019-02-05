package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.ProductoGrupoDAO;
import com.qbe.cotizador.model.ProductoGrupo;

public class ProductoGrupoTransaction {
	
	public ProductoGrupoTransaction() {       
    }

	public ProductoGrupo crear(ProductoGrupo ProductoGrupo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoGrupoDAO ProductoGrupoDAO = new ProductoGrupoDAO();
		ProductoGrupo = ProductoGrupoDAO.crear(ProductoGrupo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ProductoGrupo;	
	}
	
	public ProductoGrupo editar(ProductoGrupo ProductoGrupo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoGrupoDAO ProductoGrupoDAO = new ProductoGrupoDAO();
		ProductoGrupo ProductoGrupoBuscada = ProductoGrupoDAO.buscarPorId(ProductoGrupo.getId());
		if(ProductoGrupoBuscada!=null){
			ProductoGrupo = ProductoGrupoDAO.editar(ProductoGrupo);
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
	return ProductoGrupo;
	}
	
	public void eliminar(ProductoGrupo ProductoGrupo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProductoGrupoDAO ProductoGrupoDAO = new ProductoGrupoDAO();
		ProductoGrupo ProductoGrupoBuscado = new ProductoGrupo();
		ProductoGrupoBuscado = ProductoGrupoDAO.buscarPorId(ProductoGrupo.getId());
		if(ProductoGrupoBuscado !=null){
			ProductoGrupoDAO.eliminar(ProductoGrupo);
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
