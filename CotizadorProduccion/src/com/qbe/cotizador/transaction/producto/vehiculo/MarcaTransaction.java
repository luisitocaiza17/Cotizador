package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.model.Marca;

public class MarcaTransaction {
	
	public MarcaTransaction() {       
    }

	public Marca crear(Marca Marca){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MarcaDAO MarcaDAO = new MarcaDAO();
		Marca = MarcaDAO.crear(Marca);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Marca;	
	}
	
	public Marca editar(Marca Marca){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MarcaDAO MarcaDAO = new MarcaDAO();
		Marca = MarcaDAO.editar(Marca);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Marca;
	}
	
	public void eliminar(Marca Marca){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MarcaDAO MarcaDAO = new MarcaDAO();
		Marca MarcaBuscado = new Marca();
		MarcaBuscado = MarcaDAO.buscarPorId(Marca.getId());
		if(MarcaBuscado !=null){
			MarcaDAO.eliminar(Marca);
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
