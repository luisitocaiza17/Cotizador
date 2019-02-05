package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.NotificacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAuditoriaGeneralDAO;
import com.qbe.cotizador.model.Notificacion;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;

public class PymeAuditoriaGeneralTransaction {

	public PymeAuditoriaGeneralTransaction() {       
	}

	public PymeAuditoriaGeneral crear(PymeAuditoriaGeneral pymeAuditoriaGeneral) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeAuditoriaGeneralDAO pymeAuditoriaGeneralDAO = new PymeAuditoriaGeneralDAO();
			pymeAuditoriaGeneral = pymeAuditoriaGeneralDAO.crear(pymeAuditoriaGeneral);
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
		return pymeAuditoriaGeneral;	
	}

	public PymeAuditoriaGeneral editar(PymeAuditoriaGeneral pymeAuditoriaGeneral) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeAuditoriaGeneralDAO pymeAuditoriaGeneralDAO = new PymeAuditoriaGeneralDAO();
			pymeAuditoriaGeneral = pymeAuditoriaGeneralDAO.editar(pymeAuditoriaGeneral);
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
		return pymeAuditoriaGeneral;
	}
	

	public void eliminar(PymeAuditoriaGeneral pymeAuditoriaGeneral) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeAuditoriaGeneralDAO pymeAuditoriaGeneralDAO = new PymeAuditoriaGeneralDAO();
			PymeAuditoriaGeneral pymeAuditoriaGeneral2 = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral2 = pymeAuditoriaGeneralDAO.buscarPorId(pymeAuditoriaGeneral.getId());
			if(pymeAuditoriaGeneral2 !=null){
				pymeAuditoriaGeneralDAO.eliminar(pymeAuditoriaGeneral);
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
