package com.qbe.cotizador.transaction.producto.vehiculo;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.ColorDAO;
import com.qbe.cotizador.model.Color;

public class ColorTransaction {
	
	public ColorTransaction() {       
    }

	public Color crear(Color color){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ColorDAO colorDAO = new ColorDAO();
		color = colorDAO.crear(color);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return color;	
	}
	
	public Color editar(Color color){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ColorDAO colorDAO = new ColorDAO();
		color = colorDAO.editar(color);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return color;
	}
	
	public void eliminar(Color color){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ColorDAO colorDAO = new ColorDAO();
		Color colorBuscado = new Color();
		colorBuscado = colorDAO.buscarPorId(color.getId());
		if(colorBuscado !=null){
			colorDAO.eliminar(color);
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
