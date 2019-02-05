package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;








import com.qbe.cotizador.dao.producto.agricola.AgriReglaAuditoriaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaAuditoriaNuevaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaAuditoriaOriginalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriReglaAuditoria;
import com.qbe.cotizador.model.AgriReglaNueva;
import com.qbe.cotizador.model.AgriReglaOriginal;

public class AgriReglaNuevaTransaction {

	public AgriReglaNuevaTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public AgriReglaNueva crear (AgriReglaNueva agriRegla){
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriReglaAuditoriaNuevaDAO AgriObjetoDAO = new AgriReglaAuditoriaNuevaDAO();
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
