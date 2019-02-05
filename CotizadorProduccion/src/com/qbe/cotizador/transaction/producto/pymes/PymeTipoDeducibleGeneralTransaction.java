package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeTipoDeducibleGeneralDAO;
import com.qbe.cotizador.model.PymeTipoDeducibleGeneral;

public class PymeTipoDeducibleGeneralTransaction {
	
	public PymeTipoDeducibleGeneralTransaction() {
		
	}
	
	public PymeTipoDeducibleGeneral crear(PymeTipoDeducibleGeneral pymeTipoDeducibleGeneral) throws Exception{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleGeneralDAO pymeObjetoDAO = new PymeTipoDeducibleGeneralDAO();
			pymeTipoDeducibleGeneral = pymeObjetoDAO.crear(pymeTipoDeducibleGeneral);
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
		return pymeTipoDeducibleGeneral;
	}
	
	public void eliminar(PymeTipoDeducibleGeneral pymeTipoDeducibleGeneral) throws Exception{	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTipoDeducibleGeneralDAO pymeObjetoDAO = new PymeTipoDeducibleGeneralDAO();
			PymeTipoDeducibleGeneral ObjetoPymesBuscado = new PymeTipoDeducibleGeneral();
			ObjetoPymesBuscado = pymeObjetoDAO.buscarPorId(pymeTipoDeducibleGeneral.getTipoDeducibleGeneralId());
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
