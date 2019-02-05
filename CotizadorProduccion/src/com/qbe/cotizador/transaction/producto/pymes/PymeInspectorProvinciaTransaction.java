package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaDAO;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeInspectorProvincia;

public class PymeInspectorProvinciaTransaction {
	
	public PymeInspectorProvinciaTransaction() {
		
	}
	
	public PymeInspectorProvincia crear(PymeInspectorProvincia pymeInspectorProvincia) throws Exception{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeInspectorProvinciaDAO pymeObjetoDAO = new PymeInspectorProvinciaDAO();
			pymeInspectorProvincia = pymeObjetoDAO.crear(pymeInspectorProvincia);
			ut.commit();
		} catch (Exception e) {
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
		return pymeInspectorProvincia;
	}
	
	public void eliminar(PymeInspectorProvincia pymeInspectorProvincia) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeInspectorProvinciaDAO pymeObjetoDAO = new PymeInspectorProvinciaDAO();
			PymeInspectorProvincia ObjetoPymesBuscado = new PymeInspectorProvincia();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeInspectorProvincia.getInspectorProvinciaId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeInspectorProvincia);
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
