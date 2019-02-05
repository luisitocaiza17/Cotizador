package com.qbe.cotizador.transaction.cotizacion;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.cotizacion.MotivoDescuentoDAO;
import com.qbe.cotizador.model.MotivoDescuento;

public class MotivoDescuentoTransaction {
	
	public MotivoDescuentoTransaction() {       
    }

	public MotivoDescuento crear(MotivoDescuento MotivoDescuento){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MotivoDescuentoDAO MotivoDescuentoDAO = new MotivoDescuentoDAO();
		MotivoDescuento = MotivoDescuentoDAO.crear(MotivoDescuento);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return MotivoDescuento;	
	}
	
	public MotivoDescuento editar(MotivoDescuento MotivoDescuento){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MotivoDescuentoDAO MotivoDescuentoDAO = new MotivoDescuentoDAO();
		MotivoDescuento MotivoDescuentoBuscada = MotivoDescuentoDAO.buscarPorId(MotivoDescuento.getId());
		if(MotivoDescuentoBuscada!=null){
			MotivoDescuento = MotivoDescuentoDAO.editar(MotivoDescuento);
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
	return MotivoDescuento;
	}
	
	public void eliminar(MotivoDescuento MotivoDescuento){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		MotivoDescuentoDAO MotivoDescuentoDAO = new MotivoDescuentoDAO();
		MotivoDescuento MotivoDescuentoBuscado = new MotivoDescuento();
		MotivoDescuentoBuscado = MotivoDescuentoDAO.buscarPorId(MotivoDescuento.getId());
		if(MotivoDescuentoBuscado !=null){
			MotivoDescuentoDAO.eliminar(MotivoDescuento);
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
