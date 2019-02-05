package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreAuditoriaDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriSucreAuditoria;

public class AgriSucreAuditoriaTransaction {
public AgriSucreAuditoriaTransaction(){}
	
	public AgriSucreAuditoria crear(AgriSucreAuditoria agriSucreAuditoria)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriSucreAuditoriaDAO agriSucreAuditoriaDAO = new AgriSucreAuditoriaDAO();
			agriSucreAuditoria = agriSucreAuditoriaDAO.crear(agriSucreAuditoria);
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
		return agriSucreAuditoria;
		}
	
	public AgriSucreAuditoria editar(AgriSucreAuditoria agriSucreAuditoria)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriSucreAuditoriaDAO agriSucreAuditoriaDAO = new AgriSucreAuditoriaDAO();
			agriSucreAuditoria = agriSucreAuditoriaDAO.editar(agriSucreAuditoria);
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
		return agriSucreAuditoria;
		}
		
}
