package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.TipoVehiculoDAO;
import com.qbe.cotizador.model.TipoVehiculo;

public class TipoVehiculoTransaction {
	
	public TipoVehiculoTransaction() {       
    }

	public TipoVehiculo crear(TipoVehiculo TipoVehiculo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoVehiculoDAO TipoVehiculoDAO = new TipoVehiculoDAO();
		TipoVehiculo = TipoVehiculoDAO.crear(TipoVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoVehiculo;	
	}
	
	public TipoVehiculo editar(TipoVehiculo TipoVehiculo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoVehiculoDAO TipoVehiculoDAO = new TipoVehiculoDAO();
		TipoVehiculo = TipoVehiculoDAO.editar(TipoVehiculo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return TipoVehiculo;
	}
	
	public void eliminar(TipoVehiculo TipoVehiculo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoVehiculoDAO TipoVehiculoDAO = new TipoVehiculoDAO();
		TipoVehiculo TipoVehiculoBuscado = new TipoVehiculo();
		TipoVehiculoBuscado = TipoVehiculoDAO.buscarPorId(TipoVehiculo.getId());
		if(TipoVehiculoBuscado !=null){
			TipoVehiculoDAO.eliminar(TipoVehiculo);
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
