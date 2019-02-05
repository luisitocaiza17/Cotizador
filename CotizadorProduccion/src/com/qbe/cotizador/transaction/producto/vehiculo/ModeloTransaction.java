package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.ModeloDAO;
import com.qbe.cotizador.model.Modelo;

public class ModeloTransaction {
	
	public ModeloTransaction() {       
    }

	public Modelo crear(Modelo Modelo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModeloDAO ModeloDAO = new ModeloDAO();
		Modelo = ModeloDAO.crear(Modelo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Modelo;	
	}
	
	public Modelo editar(Modelo Modelo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModeloDAO ModeloDAO = new ModeloDAO();
		Modelo = ModeloDAO.editar(Modelo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Modelo;
	}
	
	public void eliminar(Modelo Modelo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ModeloDAO ModeloDAO = new ModeloDAO();
		Modelo ModeloBuscado = new Modelo();
		ModeloBuscado = ModeloDAO.buscarPorId(Modelo.getId());
		if(ModeloBuscado !=null){
			ModeloDAO.eliminar(Modelo);
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
