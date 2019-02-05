package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.PlanDAO;
import com.qbe.cotizador.model.Plan;

public class PlanTransaction {
	
	public PlanTransaction() {       
    }

	public Plan crear(Plan Plan){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PlanDAO PlanDAO = new PlanDAO();
		Plan = PlanDAO.crear(Plan);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Plan;	
	}
	
	public Plan editar(Plan Plan){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PlanDAO PlanDAO = new PlanDAO();
		Plan PlanBuscada = PlanDAO.buscarPorId(Plan.getId());
		if(PlanBuscada!=null){
			Plan = PlanDAO.editar(Plan);
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
	return Plan;
	}
	
	public void eliminar(Plan Plan){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		PlanDAO PlanDAO = new PlanDAO();
		Plan PlanBuscado = new Plan();
		PlanBuscado = PlanDAO.buscarPorId(Plan.getId());
		if(PlanBuscado !=null){
			PlanDAO.eliminar(Plan);
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
