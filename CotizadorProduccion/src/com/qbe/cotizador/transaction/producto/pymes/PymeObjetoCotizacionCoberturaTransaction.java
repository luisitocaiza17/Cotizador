package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionCoberturaDAO;
import com.qbe.cotizador.model.PymeObjetoCotizacionCobertura;

public class PymeObjetoCotizacionCoberturaTransaction {

	public PymeObjetoCotizacionCoberturaTransaction() {       
	}

	public PymeObjetoCotizacionCobertura crear(PymeObjetoCotizacionCobertura pymeObjetoCotizacionCobertura) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionCoberturaDAO pymeObjetoDAO = new PymeObjetoCotizacionCoberturaDAO();
			pymeObjetoCotizacionCobertura = pymeObjetoDAO.crear(pymeObjetoCotizacionCobertura);
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
		return pymeObjetoCotizacionCobertura;	
	}

	public PymeObjetoCotizacionCobertura editar(PymeObjetoCotizacionCobertura pymeObjetoCotizacionCobertura) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionCoberturaDAO pymeObjetoDAO = new PymeObjetoCotizacionCoberturaDAO();
			pymeObjetoCotizacionCobertura = pymeObjetoDAO.editar(pymeObjetoCotizacionCobertura);
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
		return pymeObjetoCotizacionCobertura;
	}

	public void eliminar(PymeObjetoCotizacionCobertura pymeObjetoCotizacionCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionCoberturaDAO pymeObjetoDAO = new PymeObjetoCotizacionCoberturaDAO();
			PymeObjetoCotizacionCobertura ObjetoPymesBuscado = new PymeObjetoCotizacionCobertura();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeObjetoCotizacionCobertura.getObjetoPymesId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeObjetoCotizacionCobertura);
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
