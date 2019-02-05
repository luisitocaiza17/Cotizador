package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.CargoDAO;
import com.qbe.cotizador.model.Cargo;

public class CargoTransaction {
	
	public CargoTransaction() {       
    }

	public Cargo crear(Cargo Cargo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CargoDAO CargoDAO = new CargoDAO();
		Cargo = CargoDAO.crear(Cargo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Cargo;	
	}
	
	public Cargo editar(Cargo Cargo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CargoDAO CargoDAO = new CargoDAO();
		Cargo CargoBuscada = CargoDAO.buscarPorId(Cargo.getId());
		if(CargoBuscada!=null){
			Cargo = CargoDAO.editar(Cargo);
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
	return Cargo;
	}
	
	public void eliminar(Cargo Cargo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CargoDAO CargoDAO = new CargoDAO();
		Cargo CargoBuscado = new Cargo();
		CargoBuscado = CargoDAO.buscarPorId(Cargo.getId());
		if(CargoBuscado !=null){
			CargoDAO.eliminar(Cargo);
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
