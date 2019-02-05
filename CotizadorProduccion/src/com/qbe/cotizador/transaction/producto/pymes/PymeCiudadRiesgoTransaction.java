package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.PymeCiudadRiesgoDAO;
import com.qbe.cotizador.model.PymeCiudadRiesgo;

public class PymeCiudadRiesgoTransaction {

	public PymeCiudadRiesgoTransaction() {       
	}

	public PymeCiudadRiesgo crear(PymeCiudadRiesgo pymeCiudadRiesgo){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO = new PymeCiudadRiesgoDAO();
			pymeCiudadRiesgo = pymeCiudadRiesgoDAO.crear(pymeCiudadRiesgo);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return pymeCiudadRiesgo;	
	}

	public PymeCiudadRiesgo editar(PymeCiudadRiesgo pymeCiudadRiesgo){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO = new PymeCiudadRiesgoDAO();
			pymeCiudadRiesgo = pymeCiudadRiesgoDAO.editar(pymeCiudadRiesgo);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return pymeCiudadRiesgo;
	}

	public void eliminar(PymeCiudadRiesgo pymeCiudadRiesgo){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO = new PymeCiudadRiesgoDAO();
			PymeCiudadRiesgo ObjetoPymesBuscado = new PymeCiudadRiesgo();
			ObjetoPymesBuscado = pymeCiudadRiesgoDAO.BuscarPorId(pymeCiudadRiesgo.getId());
			if(ObjetoPymesBuscado !=null){
				pymeCiudadRiesgoDAO.eliminar(ObjetoPymesBuscado);
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
