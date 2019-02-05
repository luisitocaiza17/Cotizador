package com.qbe.cotizador.transaction.producto.pymes;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeTipoDeducibleCoberturaDAO;
import com.qbe.cotizador.model.PymeTipoDeducibleCobertura;

public class PymeTipoDeducibleCoberturaTransaction {
	public PymeTipoDeducibleCoberturaTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public PymeTipoDeducibleCobertura crear(PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleCoberturaDAO pymeObjetoDAO = new PymeTipoDeducibleCoberturaDAO();
			pymeTipoDeducibleCobertura = pymeObjetoDAO.crear(pymeTipoDeducibleCobertura);
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
		return pymeTipoDeducibleCobertura;	
	}

	public PymeTipoDeducibleCobertura editar(PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura) throws Exception{
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleCoberturaDAO pymeObjetoDAO = new PymeTipoDeducibleCoberturaDAO();
			pymeTipoDeducibleCobertura = pymeObjetoDAO.editar(pymeTipoDeducibleCobertura);
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
		return pymeTipoDeducibleCobertura;
	}

	public void eliminar(PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleCoberturaDAO pymeObjetoDAO = new PymeTipoDeducibleCoberturaDAO();
			PymeTipoDeducibleCobertura ObjetoPymesBuscado = new PymeTipoDeducibleCobertura();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeTipoDeducibleCobertura.getTipoDeducibleCoberturaId());
			if(ObjetoPymesBuscado !=null){
				pymeObjetoDAO.eliminar(pymeTipoDeducibleCobertura);
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
	
	public void eliminarPorConfiguracionCoberturaId(PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleCoberturaDAO pymeObjetoDAO = new PymeTipoDeducibleCoberturaDAO();
			List<PymeTipoDeducibleCobertura> ObjetoPymesBuscado = new ArrayList<PymeTipoDeducibleCobertura>();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorConfiguracionCoberturaId(pymeTipoDeducibleCobertura.getConfiguracionCoberturaId());
			for( PymeTipoDeducibleCobertura res :  ObjetoPymesBuscado){
				pymeObjetoDAO.eliminar(pymeTipoDeducibleCobertura);
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
