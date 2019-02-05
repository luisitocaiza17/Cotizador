package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.model.ObjetoVehiculo;

public class ObjetoVehiculoTransaction {
	
	public ObjetoVehiculoTransaction() {       
    }

	public ObjetoVehiculo crear(ObjetoVehiculo ObjetoVehiculo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoVehiculoDAO ObjetoVehiculoDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo = ObjetoVehiculoDAO.crear(ObjetoVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoVehiculo;	
	}
	
	public ObjetoVehiculo editar(ObjetoVehiculo ObjetoVehiculo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoVehiculoDAO ObjetoVehiculoDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo = ObjetoVehiculoDAO.editar(ObjetoVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoVehiculo;
	}
	
	public void eliminar(ObjetoVehiculo ObjetoVehiculo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoVehiculoDAO ObjetoVehiculoDAO = new ObjetoVehiculoDAO();
		ObjetoVehiculo ObjetoVehiculoBuscado = new ObjetoVehiculo();
		ObjetoVehiculoBuscado = ObjetoVehiculoDAO.buscarPorId(ObjetoVehiculo.getId());
		if(ObjetoVehiculoBuscado !=null){
			ObjetoVehiculoDAO.eliminar(ObjetoVehiculo);
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
