package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.model.Cliente;

public class ClienteTransaction {
	
	public ClienteTransaction() {       
    }

	public Cliente crear(Cliente Cliente){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClienteDAO ClienteDAO = new ClienteDAO();
		Cliente = ClienteDAO.crear(Cliente);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Cliente;	
	}
	
	public Cliente editar(Cliente Cliente){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClienteDAO ClienteDAO = new ClienteDAO();
		Cliente ClienteBuscada = ClienteDAO.buscarPorId(Cliente.getId());
		if(ClienteBuscada!=null){
			Cliente = ClienteDAO.editar(Cliente);
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
	return Cliente;
	}
	
	public void eliminar(Cliente Cliente){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ClienteDAO ClienteDAO = new ClienteDAO();
		Cliente ClienteBuscado = new Cliente();
		ClienteBuscado = ClienteDAO.buscarPorId(Cliente.getId());
		if(ClienteBuscado !=null){
			ClienteDAO.eliminar(Cliente);
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
