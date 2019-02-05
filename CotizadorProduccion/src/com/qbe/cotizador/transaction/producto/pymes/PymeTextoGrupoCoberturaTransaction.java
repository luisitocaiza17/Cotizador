package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeTextoGrupoCoberturaDAO;
import com.qbe.cotizador.model.PymeTextoGrupoCobertura;

public class PymeTextoGrupoCoberturaTransaction {
	
	public PymeTextoGrupoCoberturaTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public PymeTextoGrupoCobertura crear(PymeTextoGrupoCobertura pymeTextoGrupoCobertura) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTextoGrupoCoberturaDAO pymeObjetoDAO = new PymeTextoGrupoCoberturaDAO();
			pymeTextoGrupoCobertura = pymeObjetoDAO.crear(pymeTextoGrupoCobertura);
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
		return pymeTextoGrupoCobertura;	
	}

	public PymeTextoGrupoCobertura editar(PymeTextoGrupoCobertura pymeTextoGrupoCobertura) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTextoGrupoCoberturaDAO pymeObjetoDAO = new PymeTextoGrupoCoberturaDAO();
			pymeTextoGrupoCobertura = pymeObjetoDAO.editar(pymeTextoGrupoCobertura);
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
		return pymeTextoGrupoCobertura;
	}

	public void eliminar(PymeTextoGrupoCobertura pymeTextoGrupoCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTextoGrupoCoberturaDAO pymeObjetoDAO = new PymeTextoGrupoCoberturaDAO();
			PymeTextoGrupoCobertura ObjetoPymesBuscado = new PymeTextoGrupoCobertura();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeTextoGrupoCobertura.getTextoGrupoCoberturaId());
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
