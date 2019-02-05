package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaTasaDAO;
import com.qbe.cotizador.model.PymeCoberturaTasa;

public class PymeCoberturaTasaTransaction {

	public PymeCoberturaTasaTransaction() {       
	}

	public PymeCoberturaTasa crear(PymeCoberturaTasa pymeCoberturaTasa) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaTasaDAO pymeObjetoDAO = new PymeCoberturaTasaDAO();
			pymeCoberturaTasa = pymeObjetoDAO.crear(pymeCoberturaTasa);
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
		return pymeCoberturaTasa;	
	}

	public PymeCoberturaTasa editar(PymeCoberturaTasa pymeCoberturaTasa) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaTasaDAO pymeObjetoDAO = new PymeCoberturaTasaDAO();
			pymeCoberturaTasa = pymeObjetoDAO.editar(pymeCoberturaTasa);
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
		return pymeCoberturaTasa;
	}

	public void eliminar(PymeCoberturaTasa pymeCoberturaTasa) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCoberturaTasaDAO pymeObjetoDAO = new PymeCoberturaTasaDAO();
			PymeCoberturaTasa ObjetoPymesBuscado = new PymeCoberturaTasa();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeCoberturaTasa.getCoberturaTasaId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeCoberturaTasa);
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
