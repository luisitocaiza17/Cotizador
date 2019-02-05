package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.model.CotizacionDetalle;

public class CotizacionDetalleTransaction {
	
	public CotizacionDetalleTransaction() {       
    }

	public CotizacionDetalle crear(CotizacionDetalle CotizacionDetalle){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDetalleDAO CotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle = CotizacionDetalleDAO.crear(CotizacionDetalle);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return CotizacionDetalle;	
	}
	
	public CotizacionDetalle editar(CotizacionDetalle CotizacionDetalle){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDetalleDAO CotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle CotizacionDetalleBuscada = CotizacionDetalleDAO.buscarPorId(CotizacionDetalle.getId());
		if(CotizacionDetalleBuscada!=null){
			CotizacionDetalle = CotizacionDetalleDAO.editar(CotizacionDetalle);
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
	return CotizacionDetalle;
	}
	
	public void eliminar(CotizacionDetalle CotizacionDetalle){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionDetalleDAO CotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle CotizacionDetalleBuscado = new CotizacionDetalle();
		CotizacionDetalleBuscado = CotizacionDetalleDAO.buscarPorId(CotizacionDetalle.getId());
		if(CotizacionDetalleBuscado !=null){
			CotizacionDetalleDAO.eliminar(CotizacionDetalle);
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
