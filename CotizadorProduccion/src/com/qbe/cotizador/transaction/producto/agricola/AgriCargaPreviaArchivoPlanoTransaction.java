package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaArchivoPlanoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacion;

public class AgriCargaPreviaArchivoPlanoTransaction {
	public  AgriCargaPreviaArchivoPlanoTransaction() {
		
	}
	
	public AgriCargaPreviaArchivoPlano crear(AgriCargaPreviaArchivoPlano agriObjeto){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCargaPreviaArchivoPlanoDAO agriObjetoDAO = new AgriCargaPreviaArchivoPlanoDAO();
			agriObjeto = agriObjetoDAO.crear(agriObjeto);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return agriObjeto;	
	}

	public AgriCargaPreviaArchivoPlano editar(AgriCargaPreviaArchivoPlano agriObjeto){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCargaPreviaArchivoPlanoDAO agriObjetoDAO = new AgriCargaPreviaArchivoPlanoDAO();
			agriObjeto = agriObjetoDAO.editar(agriObjeto);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return agriObjeto;
	}

	public void eliminar(AgriCargaPreviaArchivoPlano agriObjeto){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCargaPreviaArchivoPlanoDAO pymeObjetoDAO = new AgriCargaPreviaArchivoPlanoDAO();
			AgriCargaPreviaArchivoPlano objetoAgriBuscado = new AgriCargaPreviaArchivoPlano();
			objetoAgriBuscado = pymeObjetoDAO.buscarPorId(agriObjeto.getId());
			if(objetoAgriBuscado !=null){
				pymeObjetoDAO.eliminar(objetoAgriBuscado);
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
