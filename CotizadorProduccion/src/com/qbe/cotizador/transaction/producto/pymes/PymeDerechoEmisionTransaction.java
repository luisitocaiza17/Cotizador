package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDerechoEmisionDAO;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeDerechoEmision;

public class PymeDerechoEmisionTransaction {

	public PymeDerechoEmisionTransaction() {       
	}

	public PymeDerechoEmision crear(PymeDerechoEmision pymeDerechoEmision) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeDerechoEmisionDAO pymeDerehcoEmisionDAO = new PymeDerechoEmisionDAO();
			pymeDerechoEmision = pymeDerehcoEmisionDAO.crear(pymeDerechoEmision);
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
		return pymeDerechoEmision;	
	}

	public PymeDerechoEmision editar(PymeDerechoEmision pymeDerechoEmision) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeDerechoEmisionDAO pymeDerechoEmisionDAO = new PymeDerechoEmisionDAO();
			pymeDerechoEmision = pymeDerechoEmisionDAO.editar(pymeDerechoEmision);
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
		return pymeDerechoEmision;
	}

	public void eliminar(PymeDerechoEmision pymeDerechoEmision) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeDerechoEmisionDAO pymeDerechoEmisionDAO = new PymeDerechoEmisionDAO();
			PymeDerechoEmision ObjetoPymesBuscado = new PymeDerechoEmision();
			ObjetoPymesBuscado = pymeDerechoEmisionDAO.buscarPorId(pymeDerechoEmision.getDerechoEmisionId());
			if(ObjetoPymesBuscado !=null){
				pymeDerechoEmisionDAO.eliminar(pymeDerechoEmision);
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
