package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.SolicitudEmisionDAO;
import com.qbe.cotizador.model.SolicitudEmision;

public class SolicitudEmisionTransaction {
	
	public SolicitudEmisionTransaction() {       
    }

	public SolicitudEmision crear(SolicitudEmision SolicitudEmision){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudEmisionDAO SolicitudEmisionDAO = new SolicitudEmisionDAO();
		SolicitudEmision = SolicitudEmisionDAO.crear(SolicitudEmision);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return SolicitudEmision;	
	}
	
	public SolicitudEmision editar(SolicitudEmision SolicitudEmision){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudEmisionDAO SolicitudEmisionDAO = new SolicitudEmisionDAO();
		SolicitudEmision SolicitudEmisionBuscada = SolicitudEmisionDAO.buscarPorId(SolicitudEmision.getId());
		if(SolicitudEmisionBuscada!=null){
			SolicitudEmision = SolicitudEmisionDAO.editar(SolicitudEmision);
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
	return SolicitudEmision;
	}
	
	public void eliminar(SolicitudEmision SolicitudEmision){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		SolicitudEmisionDAO SolicitudEmisionDAO = new SolicitudEmisionDAO();
		SolicitudEmision SolicitudEmisionBuscado = new SolicitudEmision();
		SolicitudEmisionBuscado = SolicitudEmisionDAO.buscarPorId(SolicitudEmision.getId());
		if(SolicitudEmisionBuscado !=null){
			SolicitudEmisionDAO.eliminar(SolicitudEmision);
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
