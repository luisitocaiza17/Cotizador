package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.model.AgriParametroXCanal;

public class AgriParametroXCanalTransaction {

	public AgriParametroXCanalTransaction() {
	}
	
	public AgriParametroXCanal crear(AgriParametroXCanal agriObjetoCotizacion) {
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriParametroXCanalDAO agriObjetoDAO = new AgriParametroXCanalDAO();
			agriObjetoCotizacion = agriObjetoDAO.crear(agriObjetoCotizacion);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return agriObjetoCotizacion;
	}
	
	public AgriParametroXCanal editar(AgriParametroXCanal agriObjetoCotizacion){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriParametroXCanalDAO agriObjetoDAO = new AgriParametroXCanalDAO();
			agriObjetoCotizacion = agriObjetoDAO.editar(agriObjetoCotizacion);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}					
		return agriObjetoCotizacion;
	}
	
	public void eliminar(AgriParametroXCanal agriObjetoCotizacion){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriParametroXCanalDAO pymeObjetoDAO = new AgriParametroXCanalDAO();
			AgriParametroXCanal objetoAgriBuscado = new AgriParametroXCanal();
			objetoAgriBuscado = pymeObjetoDAO.BuscarPorId(agriObjetoCotizacion.getParametroCanalId());
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
