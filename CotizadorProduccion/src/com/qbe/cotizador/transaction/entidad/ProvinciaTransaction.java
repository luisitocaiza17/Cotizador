package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.model.Provincia;

public class ProvinciaTransaction {
	
	public ProvinciaTransaction() {       
    }

	public Provincia crear(Provincia Provincia){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProvinciaDAO ProvinciaDAO = new ProvinciaDAO();
		Provincia = ProvinciaDAO.crear(Provincia);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Provincia;	
	}
	
	public Provincia editar(Provincia Provincia){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProvinciaDAO ProvinciaDAO = new ProvinciaDAO();
		Provincia ProvinciaBuscada = ProvinciaDAO.buscarPorId(Provincia.getId());
		if(ProvinciaBuscada!=null){
			Provincia = ProvinciaDAO.editar(Provincia);
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
	return Provincia;
	}
	
	public void eliminar(Provincia Provincia){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ProvinciaDAO ProvinciaDAO = new ProvinciaDAO();
		Provincia ProvinciaBuscado = new Provincia();
		ProvinciaBuscado = ProvinciaDAO.buscarPorId(Provincia.getId());
		if(ProvinciaBuscado !=null){
			ProvinciaDAO.eliminar(Provincia);
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
