package com.qbe.cotizador.transaction.entidad;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.TipoPredioDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.TipoPredio;

public class TipoPredioTransaction {
	
	public TipoPredioTransaction() {       
    }

	public TipoPredio crear(TipoPredio tipoPredio){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoPredioDAO tipoPredioDAO = new TipoPredioDAO();
		tipoPredio = tipoPredioDAO.crear(tipoPredio);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return tipoPredio;	
	}
	
	public TipoPredio editar(TipoPredio tipoPredio){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoPredioDAO tipoPredioDAO = new TipoPredioDAO();
		TipoPredio tipoPredioBuscada = tipoPredioDAO.buscarPorId(tipoPredio.getId().toString());
		if(tipoPredioBuscada!=null){
			tipoPredio = tipoPredioDAO.editar(tipoPredio);
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
	return tipoPredio;
	}
	
	public void eliminar(TipoPredio tipoPredio){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		TipoPredioDAO tipoPredioDAO = new TipoPredioDAO();
		TipoPredio tipoPredioBuscado = new TipoPredio();
		tipoPredioBuscado = tipoPredioDAO.buscarPorId(tipoPredio.getId().toString());
		if(tipoPredioBuscado !=null){
			tipoPredioDAO.eliminar(tipoPredioBuscado);
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
