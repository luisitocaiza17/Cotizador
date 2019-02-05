package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.NotificacionDAO;
import com.qbe.cotizador.model.Notificacion;

public class NotificacionTransaction {

	public NotificacionTransaction() {       
	}

	public Notificacion crear(Notificacion notificacion) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			NotificacionDAO notificacionDAO = new NotificacionDAO();
			notificacion = notificacionDAO.crear(notificacion);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}		    
		}					
		return notificacion;	
	}

	public Notificacion editar(Notificacion notificacion) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			NotificacionDAO notificacionDAO = new NotificacionDAO();
			notificacion = notificacionDAO.editar(notificacion);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}				    
		}					
		return notificacion;
	}
	

	public void eliminar(Notificacion notificacion) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			NotificacionDAO notificacionDAO = new NotificacionDAO();
			Notificacion ObjetoPymesBuscado = new Notificacion();
			ObjetoPymesBuscado = notificacionDAO.buscarPorId(notificacion.getNotificacionId());
			if(ObjetoPymesBuscado !=null){
				notificacionDAO.eliminar(ObjetoPymesBuscado);
				ut.commit();
			}
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}				    
		}
	}
}
