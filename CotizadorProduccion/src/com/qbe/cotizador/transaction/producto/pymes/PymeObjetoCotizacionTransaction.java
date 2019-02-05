package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.model.PymeObjetoCotizacion;

public class PymeObjetoCotizacionTransaction {

	public PymeObjetoCotizacionTransaction() {       
	}

	public PymeObjetoCotizacion crear(PymeObjetoCotizacion pymeObjetoCotizacion) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionDAO pymeObjetoDAO = new PymeObjetoCotizacionDAO();
			pymeObjetoCotizacion = pymeObjetoDAO.crear(pymeObjetoCotizacion);
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
		return pymeObjetoCotizacion;	
	}

	public PymeObjetoCotizacion editar(PymeObjetoCotizacion pymeObjetoCotizacion) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionDAO pymeObjetoDAO = new PymeObjetoCotizacionDAO();
			pymeObjetoCotizacion = pymeObjetoDAO.editar(pymeObjetoCotizacion);
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
		return pymeObjetoCotizacion;
	}

	public void eliminar(PymeObjetoCotizacion pymeObjetoCotizacion) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeObjetoCotizacionDAO pymeObjetoDAO = new PymeObjetoCotizacionDAO();
			PymeObjetoCotizacion ObjetoPymesBuscado = new PymeObjetoCotizacion();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeObjetoCotizacion.getObjetoPymesId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeObjetoCotizacion);
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
