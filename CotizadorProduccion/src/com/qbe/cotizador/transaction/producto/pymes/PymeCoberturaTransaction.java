package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaDAO;
import com.qbe.cotizador.model.PymeCobertura;

public class PymeCoberturaTransaction {

	public PymeCoberturaTransaction() {       
	}

	public PymeCobertura crear(PymeCobertura pymeCobertura) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaDAO pymeObjetoDAO = new PymeCoberturaDAO();
			pymeCobertura = pymeObjetoDAO.crear(pymeCobertura);
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
		return pymeCobertura;	
	}

	public PymeCobertura editar(PymeCobertura pymeCobertura) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaDAO pymeObjetoDAO = new PymeCoberturaDAO();
			pymeCobertura = pymeObjetoDAO.editar(pymeCobertura);
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
		return pymeCobertura;
	}

	public void eliminar(PymeCobertura pymeCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaDAO pymeObjetoDAO = new PymeCoberturaDAO();
			PymeCobertura ObjetoPymesBuscado = new PymeCobertura();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeCobertura.getCoberturaPymesId());
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
