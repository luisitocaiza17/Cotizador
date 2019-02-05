package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.ConfiguracionProductoDAO;
import com.qbe.cotizador.model.ConfiguracionProducto;

public class ConfiguracionProductoTransaction {
	
	public ConfiguracionProductoTransaction() {       
    }

	public ConfiguracionProducto crear(ConfiguracionProducto ConfiguracionProducto){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConfiguracionProductoDAO ConfiguracionProductoDAO = new ConfiguracionProductoDAO();
		ConfiguracionProducto = ConfiguracionProductoDAO.crear(ConfiguracionProducto);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ConfiguracionProducto;	
	}
	
	public ConfiguracionProducto editar(ConfiguracionProducto ConfiguracionProducto){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConfiguracionProductoDAO ConfiguracionProductoDAO = new ConfiguracionProductoDAO();
		ConfiguracionProducto ConfiguracionProductoBuscada = ConfiguracionProductoDAO.buscarPorId(ConfiguracionProducto.getId());
		if(ConfiguracionProductoBuscada!=null){
			ConfiguracionProducto = ConfiguracionProductoDAO.editar(ConfiguracionProducto);
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
	return ConfiguracionProducto;
	}
	
	public void eliminar(ConfiguracionProducto ConfiguracionProducto){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ConfiguracionProductoDAO ConfiguracionProductoDAO = new ConfiguracionProductoDAO();
		ConfiguracionProducto ConfiguracionProductoBuscado = new ConfiguracionProducto();
		ConfiguracionProductoBuscado = ConfiguracionProductoDAO.buscarPorId(ConfiguracionProducto.getId());
		if(ConfiguracionProductoBuscado !=null){
			ConfiguracionProductoDAO.eliminar(ConfiguracionProducto);
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
