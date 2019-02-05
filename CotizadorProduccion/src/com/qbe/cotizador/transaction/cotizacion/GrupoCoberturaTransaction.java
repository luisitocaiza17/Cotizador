package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.model.GrupoCobertura;

public class GrupoCoberturaTransaction {
	
	public GrupoCoberturaTransaction() {       
    }

	public GrupoCobertura crear(GrupoCobertura GrupoCobertura){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoCoberturaDAO GrupoCoberturaDAO = new GrupoCoberturaDAO();
		GrupoCobertura = GrupoCoberturaDAO.crear(GrupoCobertura);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return GrupoCobertura;	
	}
	
	public GrupoCobertura editar(GrupoCobertura GrupoCobertura){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoCoberturaDAO GrupoCoberturaDAO = new GrupoCoberturaDAO();
		GrupoCobertura GrupoCoberturaBuscada = GrupoCoberturaDAO.buscarPorId(GrupoCobertura.getId());
		if(GrupoCoberturaBuscada!=null){
			GrupoCobertura = GrupoCoberturaDAO.editar(GrupoCobertura);
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
	return GrupoCobertura;
	}
	
	public void eliminar(GrupoCobertura GrupoCobertura){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		GrupoCoberturaDAO GrupoCoberturaDAO = new GrupoCoberturaDAO();
		GrupoCobertura GrupoCoberturaBuscado = new GrupoCobertura();
		GrupoCoberturaBuscado = GrupoCoberturaDAO.buscarPorId(GrupoCobertura.getId());
		if(GrupoCoberturaBuscado !=null){
			GrupoCoberturaDAO.eliminar(GrupoCobertura);
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
