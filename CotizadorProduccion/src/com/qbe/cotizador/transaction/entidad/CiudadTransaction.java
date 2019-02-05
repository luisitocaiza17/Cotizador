package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.model.Ciudad;

public class CiudadTransaction {
	
	public CiudadTransaction() {       
    }

	public Ciudad crear(Ciudad Ciudad){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CiudadDAO CiudadDAO = new CiudadDAO();
		Ciudad = CiudadDAO.crear(Ciudad);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Ciudad;	
	}
	
	public Ciudad editar(Ciudad Ciudad){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CiudadDAO CiudadDAO = new CiudadDAO();
		Ciudad CiudadBuscada = CiudadDAO.buscarPorId(Ciudad.getId());
		if(CiudadBuscada!=null){
			Ciudad = CiudadDAO.editar(Ciudad);
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
	return Ciudad;
	}
	
	public void eliminar(Ciudad Ciudad){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CiudadDAO CiudadDAO = new CiudadDAO();
		Ciudad CiudadBuscado = new Ciudad();
		CiudadBuscado = CiudadDAO.buscarPorId(Ciudad.getId());
		if(CiudadBuscado !=null){
			CiudadDAO.eliminar(Ciudad);
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
