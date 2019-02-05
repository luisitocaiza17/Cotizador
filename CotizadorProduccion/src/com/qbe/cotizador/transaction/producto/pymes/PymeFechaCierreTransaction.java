package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeFechaCierreDAO;
import com.qbe.cotizador.model.PymeFechaCierre;

public class PymeFechaCierreTransaction {

	public PymeFechaCierreTransaction() {       
	}

	public PymeFechaCierre crear(PymeFechaCierre pymeFechaCierre) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeFechaCierreDAO pymeFechaCierreDAO = new PymeFechaCierreDAO();
			pymeFechaCierre = pymeFechaCierreDAO.crear(pymeFechaCierre);
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
		return pymeFechaCierre;	
	}

	public PymeFechaCierre editar(PymeFechaCierre pymeFechaCierre) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeFechaCierreDAO pymeFechaCierreDAO = new PymeFechaCierreDAO();
			pymeFechaCierre = pymeFechaCierreDAO.editar(pymeFechaCierre);
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
		return pymeFechaCierre;
	}
	

	public void eliminar(PymeFechaCierre pymeFechaCierre) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeFechaCierreDAO pymeObjetoDAO = new PymeFechaCierreDAO();
			PymeFechaCierre ObjetoPymesBuscado = new PymeFechaCierre();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeFechaCierre.getFechaCierreId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeFechaCierre);
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
