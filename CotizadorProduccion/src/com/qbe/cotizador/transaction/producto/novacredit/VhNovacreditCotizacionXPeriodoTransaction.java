package com.qbe.cotizador.transaction.producto.novacredit;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditCotizacionXPeriodoDAO;
import com.qbe.cotizador.model.VhNovacreditCotizacionXPeriodo;

public class VhNovacreditCotizacionXPeriodoTransaction {
	
	public VhNovacreditCotizacionXPeriodoTransaction() {       
    }

	public VhNovacreditCotizacionXPeriodo crear(VhNovacreditCotizacionXPeriodo VhNovacreditCotizacionXPeriodo){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionXPeriodoDAO VhNovacreditCotizacionXPeriodoDAO = new VhNovacreditCotizacionXPeriodoDAO();
		VhNovacreditCotizacionXPeriodo = VhNovacreditCotizacionXPeriodoDAO.crear(VhNovacreditCotizacionXPeriodo);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return VhNovacreditCotizacionXPeriodo;	
	}
	
	public VhNovacreditCotizacionXPeriodo editar(VhNovacreditCotizacionXPeriodo VhNovacreditCotizacionXPeriodo){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionXPeriodoDAO VhNovacreditCotizacionXPeriodoDAO = new VhNovacreditCotizacionXPeriodoDAO();
		VhNovacreditCotizacionXPeriodo VhNovacreditCotizacionXPeriodoBuscada = VhNovacreditCotizacionXPeriodoDAO.buscarPorId(VhNovacreditCotizacionXPeriodo.getId());
		if(VhNovacreditCotizacionXPeriodoBuscada!=null){
			VhNovacreditCotizacionXPeriodo = VhNovacreditCotizacionXPeriodoDAO.editar(VhNovacreditCotizacionXPeriodo);
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
	return VhNovacreditCotizacionXPeriodo;
	}
	
	public void eliminar(VhNovacreditCotizacionXPeriodo VhNovacreditCotizacionXPeriodo){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionXPeriodoDAO VhNovacreditCotizacionXPeriodoDAO = new VhNovacreditCotizacionXPeriodoDAO();
		VhNovacreditCotizacionXPeriodo VhNovacreditCotizacionXPeriodoBuscado = new VhNovacreditCotizacionXPeriodo();
		VhNovacreditCotizacionXPeriodoBuscado = VhNovacreditCotizacionXPeriodoDAO.buscarPorId(VhNovacreditCotizacionXPeriodo.getId());
		if(VhNovacreditCotizacionXPeriodoBuscado !=null){
			VhNovacreditCotizacionXPeriodoDAO.eliminar(VhNovacreditCotizacionXPeriodo);
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
