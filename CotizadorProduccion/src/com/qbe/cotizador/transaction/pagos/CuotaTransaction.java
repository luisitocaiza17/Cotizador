package com.qbe.cotizador.transaction.pagos;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.model.Cuota;

public class CuotaTransaction {
	
	public CuotaTransaction() {       
    }

	public Cuota crear(Cuota Cuota){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CuotaDAO CuotaDAO = new CuotaDAO();
		Cuota = CuotaDAO.crear(Cuota);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return Cuota;	
	}
	
	public Cuota editar(Cuota Cuota){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CuotaDAO CuotaDAO = new CuotaDAO();
		Cuota CuotaBuscada = CuotaDAO.buscarPorId(Cuota.getId());
		if(CuotaBuscada!=null){
			Cuota = CuotaDAO.editar(Cuota);
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
	return Cuota;
	}
	
	public void eliminar(Cuota Cuota){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CuotaDAO CuotaDAO = new CuotaDAO();
		Cuota CuotaBuscado = new Cuota();
		CuotaBuscado = CuotaDAO.buscarPorId(Cuota.getId());
		if(CuotaBuscado !=null){
			CuotaDAO.eliminar(Cuota);
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
