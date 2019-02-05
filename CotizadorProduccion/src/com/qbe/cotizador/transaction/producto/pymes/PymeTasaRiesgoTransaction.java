package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.PymeCiudadRiesgoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTasaRiesgoDAO;
import com.qbe.cotizador.model.PymeCiudadRiesgo;
import com.qbe.cotizador.model.PymeTasaRiesgo;

public class PymeTasaRiesgoTransaction {

	public PymeTasaRiesgoTransaction() {       
	}

	public PymeTasaRiesgo crear(PymeTasaRiesgo pymeTasaRiesgo){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
			pymeTasaRiesgo = pymeTasaRiesgoDAO.crear(pymeTasaRiesgo);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return pymeTasaRiesgo;	
	}

	public PymeTasaRiesgo editar(PymeTasaRiesgo pymeTasaRiesgo){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
			pymeTasaRiesgo = pymeTasaRiesgoDAO.editar(pymeTasaRiesgo);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return pymeTasaRiesgo;
	}

	public void eliminar(PymeTasaRiesgo pymeTasaRiesgo){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
			PymeTasaRiesgo ObjetoPymesBuscado = new PymeTasaRiesgo();
			ObjetoPymesBuscado = pymeTasaRiesgoDAO.BuscarPorId(pymeTasaRiesgo.getId());
			if(ObjetoPymesBuscado !=null){
				pymeTasaRiesgoDAO.eliminar(ObjetoPymesBuscado);
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
