package com.qbe.cotizador.transaction.producto.ganadero;

import java.math.BigInteger;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDeducibleDAO;
import com.qbe.cotizador.model.ObjetoGanaderoDeducible;

public class ObjetoGanaderoDeducibleTransaction {
	
	public ObjetoGanaderoDeducibleTransaction() {       
    }

	public ObjetoGanaderoDeducible crear(ObjetoGanaderoDeducible ObjetoGanaderoDeducible){		
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDeducibleDAO ObjetoGanaderoDeducibleDAO = new ObjetoGanaderoDeducibleDAO();
		ObjetoGanaderoDeducible = ObjetoGanaderoDeducibleDAO.crear(ObjetoGanaderoDeducible);
        ut.commit();
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoGanaderoDeducible;	
	}
	
	public ObjetoGanaderoDeducible editar(ObjetoGanaderoDeducible ObjetoGanaderoDeducible){
		UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDeducibleDAO ObjetoGanaderoDeducibleDAO = new ObjetoGanaderoDeducibleDAO();
		//ObjetoGanaderoDeducible ObjetoGanaderoDeducibleBuscada = ObjetoGanaderoDeducibleDAO.buscarPorObjetoGanadero(new BigInteger(ObjetoGanaderoDeducible.getId()));
		//if(ObjetoGanaderoDeducibleBuscada!=null){
			ObjetoGanaderoDeducible = ObjetoGanaderoDeducibleDAO.editar(ObjetoGanaderoDeducible);
			ut.commit();
		//}
	}catch(Exception e) {
		try {
			ut.rollback();
		} catch (IllegalStateException | SecurityException | SystemException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();			    
	}					
	return ObjetoGanaderoDeducible;
	}
	
	public void eliminar(ObjetoGanaderoDeducible ObjetoGanaderoDeducible){	
	UserTransaction ut = null;
	try{
		ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		ut.begin();
		ObjetoGanaderoDeducibleDAO ObjetoGanaderoDeducibleDAO = new ObjetoGanaderoDeducibleDAO();
		ObjetoGanaderoDeducible ObjetoGanaderoDeducibleBuscado = new ObjetoGanaderoDeducible();
		//ObjetoGanaderoDeducibleBuscado = ObjetoGanaderoDeducibleDAO.buscarPorId(ObjetoGanaderoDeducible.getId());
		//if(ObjetoGanaderoDeducibleBuscado !=null){
			ObjetoGanaderoDeducibleDAO.eliminar(ObjetoGanaderoDeducible);
            ut.commit();
		//}
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
