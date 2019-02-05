package com.qbe.cotizador.transaction.producto.novacredit;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.vehiculo.novacredit.VhNovacreditCotizacionDAO;
import com.qbe.cotizador.model.VhNovacreditCotizacion;

public class VhNovacreditCotizacionTransaction {
	
	public VhNovacreditCotizacionTransaction() {       
    }

	public VhNovacreditCotizacion crear(VhNovacreditCotizacion VhNovacreditCotizacion){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionDAO VhNovacreditCotizacionDAO = new VhNovacreditCotizacionDAO();
		VhNovacreditCotizacion = VhNovacreditCotizacionDAO.crear(VhNovacreditCotizacion);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return VhNovacreditCotizacion;	
	}
	
	public VhNovacreditCotizacion editar(VhNovacreditCotizacion VhNovacreditCotizacion){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionDAO VhNovacreditCotizacionDAO = new VhNovacreditCotizacionDAO();
		VhNovacreditCotizacion VhNovacreditCotizacionBuscada = VhNovacreditCotizacionDAO.buscarPorId(VhNovacreditCotizacion.getId());
		if(VhNovacreditCotizacionBuscada!=null){
			VhNovacreditCotizacion = VhNovacreditCotizacionDAO.editar(VhNovacreditCotizacion);
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
	return VhNovacreditCotizacion;
	}
	
	public void eliminar(VhNovacreditCotizacion VhNovacreditCotizacion){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		VhNovacreditCotizacionDAO VhNovacreditCotizacionDAO = new VhNovacreditCotizacionDAO();
		VhNovacreditCotizacion VhNovacreditCotizacionBuscado = new VhNovacreditCotizacion();
		VhNovacreditCotizacionBuscado = VhNovacreditCotizacionDAO.buscarPorId(VhNovacreditCotizacion.getId());
		if(VhNovacreditCotizacionBuscado !=null){
			VhNovacreditCotizacionDAO.eliminar(VhNovacreditCotizacion);
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
