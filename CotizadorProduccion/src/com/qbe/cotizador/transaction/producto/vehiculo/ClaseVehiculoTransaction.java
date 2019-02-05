package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.ClaseVehiculoDAO;
import com.qbe.cotizador.model.ClaseVehiculo;

public class ClaseVehiculoTransaction {
	
	public ClaseVehiculoTransaction() {       
    }

	public ClaseVehiculo crear(ClaseVehiculo ClaseVehiculo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClaseVehiculoDAO ClaseVehiculoDAO = new ClaseVehiculoDAO();
		ClaseVehiculo = ClaseVehiculoDAO.crear(ClaseVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ClaseVehiculo;	
	}
	
	public ClaseVehiculo editar(ClaseVehiculo ClaseVehiculo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClaseVehiculoDAO ClaseVehiculoDAO = new ClaseVehiculoDAO();
		ClaseVehiculo = ClaseVehiculoDAO.editar(ClaseVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ClaseVehiculo;
	}
	
	public void eliminar(ClaseVehiculo ClaseVehiculo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClaseVehiculoDAO ClaseVehiculoDAO = new ClaseVehiculoDAO();
		ClaseVehiculo ClaseVehiculoBuscado = new ClaseVehiculo();
		ClaseVehiculoBuscado = ClaseVehiculoDAO.buscarPorId(ClaseVehiculo.getId());
		if(ClaseVehiculoBuscado !=null){
			ClaseVehiculoDAO.eliminar(ClaseVehiculo);
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
