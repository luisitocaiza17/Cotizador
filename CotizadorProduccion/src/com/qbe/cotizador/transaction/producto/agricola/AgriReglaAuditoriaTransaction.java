package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;




import com.qbe.cotizador.dao.producto.agricola.AgriReglaAuditoriaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriReglaAuditoria;

public class AgriReglaAuditoriaTransaction {

	public AgriReglaAuditoriaTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public AgriReglaAuditoria crear (AgriReglaAuditoria agriRegla){
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriReglaAuditoriaDAO AgriObjetoDAO = new AgriReglaAuditoriaDAO();
			agriRegla = AgriObjetoDAO.crear(agriRegla);
			ut.commit();
		}
		catch(Exception e)
		{
			try{
				ut.rollback();
			}
			catch(IllegalStateException | SecurityException | SystemException e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return agriRegla;
		}
}
