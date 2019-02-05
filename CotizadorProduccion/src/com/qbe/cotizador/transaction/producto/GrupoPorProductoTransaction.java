package com.qbe.cotizador.transaction.producto;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.model.GrupoPorProducto;

public class GrupoPorProductoTransaction {
	
	public GrupoPorProductoTransaction() {       
    }

	public GrupoPorProducto crear(GrupoPorProducto GrupoPorProducto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoPorProductoDAO GrupoPorProductoDAO = new GrupoPorProductoDAO();
		GrupoPorProducto = GrupoPorProductoDAO.crear(GrupoPorProducto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoPorProducto;	
	}
	
	public GrupoPorProducto editar(GrupoPorProducto GrupoPorProducto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoPorProductoDAO GrupoPorProductoDAO = new GrupoPorProductoDAO();
		GrupoPorProducto GrupoPorProductoBuscada = GrupoPorProductoDAO.buscarPorId(GrupoPorProducto.getId());
		if(GrupoPorProductoBuscada!=null){
			GrupoPorProducto = GrupoPorProductoDAO.editar(GrupoPorProducto);
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
	return GrupoPorProducto;
	}
	
	public void eliminar(GrupoPorProducto GrupoPorProducto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoPorProductoDAO GrupoPorProductoDAO = new GrupoPorProductoDAO();
		GrupoPorProducto GrupoPorProductoBuscado = new GrupoPorProducto();
		GrupoPorProductoBuscado = GrupoPorProductoDAO.buscarPorId(GrupoPorProducto.getId());
		if(GrupoPorProductoBuscado !=null){
			GrupoPorProductoDAO.eliminar(GrupoPorProducto);
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
