package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeParametroPuntoVentaDAO;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;


public class PymeParametroPuntoVentaTransaction {

	public PymeParametroPuntoVentaTransaction() {       
	}

	public PymeParametroPuntoVenta crear(PymeParametroPuntoVenta pymeParametroPuntoVenta) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroPuntoVentaDAO pymeObjetoDAO = new PymeParametroPuntoVentaDAO();
			pymeParametroPuntoVenta = pymeObjetoDAO.crear(pymeParametroPuntoVenta);
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
		return pymeParametroPuntoVenta;	
	}

	public PymeParametroPuntoVenta editar(PymeParametroPuntoVenta pymeParametroPuntoVenta) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroPuntoVentaDAO pymeObjetoDAO = new PymeParametroPuntoVentaDAO();
			pymeParametroPuntoVenta = pymeObjetoDAO.editar(pymeParametroPuntoVenta);
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
		return pymeParametroPuntoVenta;
	}

	public void eliminar(PymeParametroPuntoVenta pymeParametroPuntoVenta) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroPuntoVentaDAO pymeObjetoDAO = new PymeParametroPuntoVentaDAO();
			PymeParametroPuntoVenta ObjetoPymesBuscado = new PymeParametroPuntoVenta();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeParametroPuntoVenta.getParametroPuntoventaId());
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
