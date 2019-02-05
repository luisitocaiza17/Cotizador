package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.model.CotizacionCobertura;

public class CotizacionCoberturaTransaction {
	
	public CotizacionCoberturaTransaction() {       
    }

	public CotizacionCobertura crear(CotizacionCobertura CotizacionCobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionCoberturaDAO CotizacionCoberturaDAO = new CotizacionCoberturaDAO();
		CotizacionCobertura = CotizacionCoberturaDAO.crear(CotizacionCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return CotizacionCobertura;	
	}
	
	public CotizacionCobertura editar(CotizacionCobertura CotizacionCobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionCoberturaDAO CotizacionCoberturaDAO = new CotizacionCoberturaDAO();
		CotizacionCobertura CotizacionCoberturaBuscada = CotizacionCoberturaDAO.buscarPorId(CotizacionCobertura.getId());
		if(CotizacionCoberturaBuscada!=null){
			CotizacionCobertura = CotizacionCoberturaDAO.editar(CotizacionCobertura);
			ut.commit();
		}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return CotizacionCobertura;
	}
	
	public void eliminar(CotizacionCobertura CotizacionCobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		CotizacionCoberturaDAO CotizacionCoberturaDAO = new CotizacionCoberturaDAO();
		CotizacionCobertura CotizacionCoberturaBuscado = new CotizacionCobertura();
		CotizacionCoberturaBuscado = CotizacionCoberturaDAO.buscarPorId(CotizacionCobertura.getId());
		if(CotizacionCoberturaBuscado !=null){
			CotizacionCoberturaDAO.eliminar(CotizacionCobertura);
            ut.commit();
		}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}
	}
}
