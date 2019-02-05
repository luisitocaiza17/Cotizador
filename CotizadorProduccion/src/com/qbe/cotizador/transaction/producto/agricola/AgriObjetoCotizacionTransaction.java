package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacion;

public class AgriObjetoCotizacionTransaction {

	public AgriObjetoCotizacionTransaction() {       
	}

	public AgriObjetoCotizacion crear(AgriObjetoCotizacion agriObjetoCotizacion){		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoCotizacionDAO agriObjetoDAO = new AgriObjetoCotizacionDAO();
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

	public AgriObjetoCotizacion editar(AgriObjetoCotizacion agriObjetoCotizacion){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoCotizacionDAO agriObjetoDAO = new AgriObjetoCotizacionDAO();
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

	public void eliminar(PymeObjetoCotizacion agriObjetoCotizacion){	
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriObjetoCotizacionDAO pymeObjetoDAO = new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion objetoAgriBuscado = new AgriObjetoCotizacion();
			objetoAgriBuscado = pymeObjetoDAO.buscarPorId(agriObjetoCotizacion.getObjetoPymesId());
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
