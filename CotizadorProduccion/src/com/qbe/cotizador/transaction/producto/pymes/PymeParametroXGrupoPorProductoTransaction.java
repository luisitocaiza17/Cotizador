package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeParametroXGrupoPorProductoDAO;
import com.qbe.cotizador.model.PymeParametroXGrupoPorProducto;

public class PymeParametroXGrupoPorProductoTransaction {

	public PymeParametroXGrupoPorProductoTransaction() {       
	}

	public PymeParametroXGrupoPorProducto crear(PymeParametroXGrupoPorProducto pymeParametroXGrupoPorProducto) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXGrupoPorProductoDAO pymeObjetoDAO = new PymeParametroXGrupoPorProductoDAO();
			pymeParametroXGrupoPorProducto = pymeObjetoDAO.crear(pymeParametroXGrupoPorProducto);
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
		return pymeParametroXGrupoPorProducto;	
	}

	public PymeParametroXGrupoPorProducto editar(PymeParametroXGrupoPorProducto pymeParametroXGrupoPorProducto) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXGrupoPorProductoDAO pymeObjetoDAO = new PymeParametroXGrupoPorProductoDAO();
			pymeParametroXGrupoPorProducto = pymeObjetoDAO.editar(pymeParametroXGrupoPorProducto);
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
		return pymeParametroXGrupoPorProducto;
	}

	public void eliminar(PymeParametroXGrupoPorProducto pymeParametroXGrupoPorProducto) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeParametroXGrupoPorProductoDAO pymeObjetoDAO = new PymeParametroXGrupoPorProductoDAO();
			PymeParametroXGrupoPorProducto ObjetoPymesBuscado = new PymeParametroXGrupoPorProducto();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeParametroXGrupoPorProducto.getParametroGrupoProductoId());
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
