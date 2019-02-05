package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.model.Empleado;

public class EmpleadoTransaction {
	
	public EmpleadoTransaction() {       
    }

	public Empleado crear(Empleado Empleado){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EmpleadoDAO EmpleadoDAO = new EmpleadoDAO();
		Empleado = EmpleadoDAO.crear(Empleado);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Empleado;	
	}
	
	public Empleado editar(Empleado Empleado){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EmpleadoDAO EmpleadoDAO = new EmpleadoDAO();
		Empleado EmpleadoBuscada = EmpleadoDAO.buscarPorId(Empleado.getId());
		if(EmpleadoBuscada!=null){
			Empleado = EmpleadoDAO.editar(Empleado);
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
	return Empleado;
	}
	
	public void eliminar(Empleado Empleado){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		EmpleadoDAO EmpleadoDAO = new EmpleadoDAO();
		Empleado EmpleadoBuscado = new Empleado();
		EmpleadoBuscado = EmpleadoDAO.buscarPorId(Empleado.getId());
		if(EmpleadoBuscado !=null){
			EmpleadoDAO.eliminar(Empleado);
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
