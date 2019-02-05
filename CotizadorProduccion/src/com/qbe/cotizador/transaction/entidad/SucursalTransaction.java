package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.model.Sucursal;

public class SucursalTransaction {
	
	public SucursalTransaction() {       
    }

	public Sucursal crear(Sucursal Sucursal){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SucursalDAO SucursalDAO = new SucursalDAO();
		Sucursal = SucursalDAO.crear(Sucursal);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Sucursal;	
	}
	
	public Sucursal editar(Sucursal Sucursal){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SucursalDAO SucursalDAO = new SucursalDAO();
		Sucursal SucursalBuscada = SucursalDAO.buscarPorId(Sucursal.getId());
		if(SucursalBuscada!=null){
			Sucursal = SucursalDAO.editar(Sucursal);
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
	return Sucursal;
	}
	
	public void eliminar(Sucursal Sucursal){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SucursalDAO SucursalDAO = new SucursalDAO();
		Sucursal SucursalBuscado = new Sucursal();
		SucursalBuscado = SucursalDAO.buscarPorId(Sucursal.getId());
		if(SucursalBuscado !=null){
			SucursalDAO.eliminar(Sucursal);
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
