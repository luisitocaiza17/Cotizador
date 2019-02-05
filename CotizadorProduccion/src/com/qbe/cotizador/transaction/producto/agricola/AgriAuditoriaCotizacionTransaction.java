package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;

public class AgriAuditoriaCotizacionTransaction {

	public AgriAuditoriaCotizacionTransaction() {
		// TODO Auto-generated constructor stub
	}
	
	public AgriAuditoriaCotizacion crear (AgriAuditoriaCotizacion agriAuditoriaCotizacion)
	{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriAuditoriaCotizacionDAO AgriObjetoDAO = new AgriAuditoriaCotizacionDAO();
			agriAuditoriaCotizacion = AgriObjetoDAO.crear(agriAuditoriaCotizacion);
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
		return agriAuditoriaCotizacion;
	}
	public AgriAuditoriaCotizacion editar (AgriAuditoriaCotizacion agriAuditoriaCotizacion)
	{
		UserTransaction ut = null;
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriAuditoriaCotizacionDAO AgriObjetoDAO = new AgriAuditoriaCotizacionDAO();
			agriAuditoriaCotizacion = AgriObjetoDAO.editar(agriAuditoriaCotizacion);
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
		return agriAuditoriaCotizacion;
	}
	public void eliminar(AgriAuditoriaCotizacion agriAuditoriaCotizacion)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriAuditoriaCotizacionDAO AgriObjetoDAO = new AgriAuditoriaCotizacionDAO();
			AgriAuditoriaCotizacion ObjetoAgriBuscado = new AgriAuditoriaCotizacion();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriAuditoriaCotizacion.getId());
			if (ObjetoAgriBuscado!=null)
			{
				AgriObjetoDAO.eliminar(ObjetoAgriBuscado);
				ut.commit();
			}
		}
		catch(Exception e)
		{
			try 
			{
				ut.rollback();
			}
			catch (IllegalStateException | SecurityException | SystemException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
