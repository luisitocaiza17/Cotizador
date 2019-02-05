package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeParametroXPuntoVentaDAO;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;

public class PymeParametroXPuntoVentaTransaction {

	public PymeParametroXPuntoVentaTransaction() {       
	}

	public PymeParametroXPuntoVenta crear(PymeParametroXPuntoVenta pymeParametroXPuntoVenta) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXPuntoVentaDAO pymeObjetoDAO = new PymeParametroXPuntoVentaDAO();
			pymeParametroXPuntoVenta = pymeObjetoDAO.crear(pymeParametroXPuntoVenta);
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
		return pymeParametroXPuntoVenta;	
	}

	public PymeParametroXPuntoVenta editar(PymeParametroXPuntoVenta pymeParametroXPuntoVenta) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXPuntoVentaDAO pymeObjetoDAO = new PymeParametroXPuntoVentaDAO();
			pymeParametroXPuntoVenta = pymeObjetoDAO.editar(pymeParametroXPuntoVenta);
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
		return pymeParametroXPuntoVenta;
	}

	public void eliminar(PymeParametroXPuntoVenta pymeParametroXPuntoVenta) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXPuntoVentaDAO pymeObjetoDAO = new PymeParametroXPuntoVentaDAO();
			PymeParametroXPuntoVenta ObjetoPymesBuscado = new PymeParametroXPuntoVenta();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeParametroXPuntoVenta.getParametroPuntoVentaId());
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
