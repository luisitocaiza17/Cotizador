package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeConfiguracionCoberturaDAO;
import com.qbe.cotizador.model.PymeConfiguracionCobertura;

public class PymeConfiguracionCoberturaTransaction {

	public PymeConfiguracionCoberturaTransaction() {       
	}

	public PymeConfiguracionCobertura crear(PymeConfiguracionCobertura pymeConfiguracionCobertura) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeConfiguracionCoberturaDAO pymeObjetoDAO = new PymeConfiguracionCoberturaDAO();
			pymeConfiguracionCobertura = pymeObjetoDAO.crear(pymeConfiguracionCobertura);
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
		return pymeConfiguracionCobertura;	
	}

	public PymeConfiguracionCobertura editar(PymeConfiguracionCobertura pymeConfiguracionCobertura) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeConfiguracionCoberturaDAO pymeObjetoDAO = new PymeConfiguracionCoberturaDAO();
			pymeConfiguracionCobertura = pymeObjetoDAO.editar(pymeConfiguracionCobertura);
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
		return pymeConfiguracionCobertura;
	}

	public void eliminar(PymeConfiguracionCobertura pymeConfiguracionCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeConfiguracionCoberturaDAO pymeObjetoDAO = new PymeConfiguracionCoberturaDAO();
			PymeConfiguracionCobertura ObjetoPymesBuscado = new PymeConfiguracionCobertura();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeConfiguracionCobertura.getConfiguracionCoberturaId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeConfiguracionCobertura);
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
