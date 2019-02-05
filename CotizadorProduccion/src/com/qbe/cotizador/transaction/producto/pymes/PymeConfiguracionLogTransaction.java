package com.qbe.cotizador.transaction.producto.pymes;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.pymes.PymeConfiguracionLogDAO;
import com.qbe.cotizador.model.PymeConfiguracionLog;

public class PymeConfiguracionLogTransaction {

	public PymeConfiguracionLogTransaction() {       
	}

	public PymeConfiguracionLog crear(PymeConfiguracionLog pymeConfiguracionLog) throws Exception{		
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			PymeConfiguracionLogDAO pymeObjetoDAO = new PymeConfiguracionLogDAO();
			pymeConfiguracionLog = pymeObjetoDAO.crear(pymeConfiguracionLog);
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			finally{
				e.printStackTrace();
				throw e;
			}			    
		}					
		return pymeConfiguracionLog;	
	}
}
