package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.model.ActividadEconomica;;

public class ActividadEconomicaTransaction {
	
	public ActividadEconomicaTransaction() {       
    }

	public ActividadEconomica crear(ActividadEconomica actividadEconomica){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ActividadEconomicaDAO actividadEconomicaDAO = new ActividadEconomicaDAO();
		actividadEconomica = actividadEconomicaDAO.crear(actividadEconomica);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return actividadEconomica;	
	}
	
	public ActividadEconomica editar(ActividadEconomica actividadEconomica){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ActividadEconomicaDAO actividadEconomicaDAO = new ActividadEconomicaDAO();
		ActividadEconomica actividadEconomicaBuscada = actividadEconomicaDAO.buscarPorId(actividadEconomica.getId());
		if(actividadEconomicaBuscada!=null){
			actividadEconomica = actividadEconomicaDAO.editar(actividadEconomica);
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
	return actividadEconomica;
	}
	
	
	public void eliminar(ActividadEconomica actividadEconomica){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ActividadEconomicaDAO actividadEconomicaDAO = new ActividadEconomicaDAO();
		ActividadEconomica actividadEconomicaBuscado = new ActividadEconomica();
		actividadEconomicaBuscado = actividadEconomicaDAO.buscarPorId(actividadEconomica.getId());
		if(actividadEconomicaBuscado !=null){
			actividadEconomicaDAO.eliminar(actividadEconomicaBuscado);
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
