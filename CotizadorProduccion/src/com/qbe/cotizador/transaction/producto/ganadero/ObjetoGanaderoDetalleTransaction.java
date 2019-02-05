package com.qbe.cotizador.transaction.producto.ganadero;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDetalleDAO;
import com.qbe.cotizador.model.ObjetoGanaderoDetalle;

public class ObjetoGanaderoDetalleTransaction {
	
	public ObjetoGanaderoDetalleTransaction() {       
    }

	public ObjetoGanaderoDetalle crear(ObjetoGanaderoDetalle ObjetoGanaderoDetalle){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDetalleDAO ObjetoGanaderoDetalleDAO = new ObjetoGanaderoDetalleDAO();
		ObjetoGanaderoDetalle = ObjetoGanaderoDetalleDAO.crear(ObjetoGanaderoDetalle);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoGanaderoDetalle;	
	}
	
	public ObjetoGanaderoDetalle editar(ObjetoGanaderoDetalle ObjetoGanaderoDetalle){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDetalleDAO ObjetoGanaderoDetalleDAO = new ObjetoGanaderoDetalleDAO();
		//ObjetoGanaderoDetalle ObjetoGanaderoDetalleBuscada = ObjetoGanaderoDetalleDAO.buscarPorId(ObjetoGanaderoDetalle.getId());
		//if(ObjetoGanaderoDetalleBuscada!=null){
			ObjetoGanaderoDetalle = ObjetoGanaderoDetalleDAO.editar(ObjetoGanaderoDetalle);
			ut.commit();
		//}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoGanaderoDetalle;
	}
	
	public void eliminar(ObjetoGanaderoDetalle ObjetoGanaderoDetalle){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDetalleDAO ObjetoGanaderoDetalleDAO = new ObjetoGanaderoDetalleDAO();
		ObjetoGanaderoDetalle ObjetoGanaderoDetalleBuscado = new ObjetoGanaderoDetalle();
		//ObjetoGanaderoDetalleBuscado = ObjetoGanaderoDetalleDAO.buscarPorId(ObjetoGanaderoDetalle.getId());
		//if(ObjetoGanaderoDetalleBuscado !=null){
			ObjetoGanaderoDetalleDAO.eliminar(ObjetoGanaderoDetalle);
            ut.commit();
		//}
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
