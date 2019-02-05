package com.qbe.cotizador.transaction.pagos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.pagos.InstitucionFinancieraDAO;
import com.qbe.cotizador.model.InstitucionFinanciera;

public class InstitucionFinancieraTransaction {
	
	public InstitucionFinancieraTransaction() {       
    }

	public InstitucionFinanciera crear(InstitucionFinanciera InstitucionFinanciera){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InstitucionFinancieraDAO InstitucionFinancieraDAO = new InstitucionFinancieraDAO();
		InstitucionFinanciera = InstitucionFinancieraDAO.crear(InstitucionFinanciera);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return InstitucionFinanciera;	
	}
	
	public InstitucionFinanciera editar(InstitucionFinanciera InstitucionFinanciera){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InstitucionFinancieraDAO InstitucionFinancieraDAO = new InstitucionFinancieraDAO();
		InstitucionFinanciera InstitucionFinancieraBuscada = InstitucionFinancieraDAO.buscarPorId(InstitucionFinanciera.getId());
		if(InstitucionFinancieraBuscada!=null){
			InstitucionFinanciera = InstitucionFinancieraDAO.editar(InstitucionFinanciera);
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
	return InstitucionFinanciera;
	}
	
	public void eliminar(InstitucionFinanciera InstitucionFinanciera){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		InstitucionFinancieraDAO InstitucionFinancieraDAO = new InstitucionFinancieraDAO();
		InstitucionFinanciera InstitucionFinancieraBuscado = new InstitucionFinanciera();
		InstitucionFinancieraBuscado = InstitucionFinancieraDAO.buscarPorId(InstitucionFinanciera.getId());
		if(InstitucionFinancieraBuscado !=null){
			InstitucionFinancieraDAO.eliminar(InstitucionFinanciera);
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
