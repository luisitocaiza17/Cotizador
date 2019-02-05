package com.qbe.cotizador.transaction.producto.pymes;

import java.math.BigInteger;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeEndosoBeneficiarioDAO;
import com.qbe.cotizador.model.PymeEndosoBeneficiario;

public class PymeEndosoBeneficiarioTransaction {

	public PymeEndosoBeneficiarioTransaction() {       
	}

	public PymeEndosoBeneficiario crear(PymeEndosoBeneficiario pymeEndosoBeneficiario) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO = new PymeEndosoBeneficiarioDAO();
			pymeEndosoBeneficiario = pymeEndosoBeneficiarioDAO.crear(pymeEndosoBeneficiario);
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
		return pymeEndosoBeneficiario;	
	}

	public PymeEndosoBeneficiario editar(PymeEndosoBeneficiario pymeEndosoBeneficiario) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO = new PymeEndosoBeneficiarioDAO();
			PymeEndosoBeneficiario PymeEndosoBeneficiarioBuscada = pymeEndosoBeneficiarioDAO.buscarPorId(pymeEndosoBeneficiario.getId());
			if(PymeEndosoBeneficiarioBuscada!=null){
				pymeEndosoBeneficiario = pymeEndosoBeneficiarioDAO.editar(pymeEndosoBeneficiario);
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
		return pymeEndosoBeneficiario;
	}

	public void eliminar(BigInteger id) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeEndosoBeneficiarioDAO pymeEndosoBeneficiarioDAO = new PymeEndosoBeneficiarioDAO();
			PymeEndosoBeneficiario pymeEndosoBeneficiarioBuscado = pymeEndosoBeneficiarioDAO.buscarPorId(id);
			if(pymeEndosoBeneficiarioBuscado !=null){
				pymeEndosoBeneficiarioDAO.eliminar(pymeEndosoBeneficiarioBuscado);
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
