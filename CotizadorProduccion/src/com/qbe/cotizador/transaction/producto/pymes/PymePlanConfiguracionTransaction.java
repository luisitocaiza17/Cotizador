package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymePlanConfiguracionDAO;
import com.qbe.cotizador.model.PymePlanConfiguracion;

public class PymePlanConfiguracionTransaction {

	public PymePlanConfiguracionTransaction() {       
	}

	public PymePlanConfiguracion crear(PymePlanConfiguracion pymePlanConfiguracion) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymePlanConfiguracionDAO pymePlanConfigDAO = new PymePlanConfiguracionDAO();
			pymePlanConfiguracion = pymePlanConfigDAO.crear(pymePlanConfiguracion);
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
		return pymePlanConfiguracion;	
	}

	public PymePlanConfiguracion editar(PymePlanConfiguracion pymePlanConfiguracion) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymePlanConfiguracionDAO pymePlanConfigDAO = new PymePlanConfiguracionDAO();
			pymePlanConfiguracion = pymePlanConfigDAO.editar(pymePlanConfiguracion);
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
		return pymePlanConfiguracion;
	}
	

	public void eliminar(PymePlanConfiguracion pymePlanConfiguracion) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymePlanConfiguracionDAO pymeObjetoDAO = new PymePlanConfiguracionDAO();
			PymePlanConfiguracion ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymePlanConfiguracion.getPlanConfiguracionId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(ObjetoPymesBuscado);
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
