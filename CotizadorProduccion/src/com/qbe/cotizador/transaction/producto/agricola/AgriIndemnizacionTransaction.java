package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import com.qbe.cotizador.dao.producto.agricola.AgriIndemnizacionDAO;
import com.qbe.cotizador.model.AgriIndemnizacion;

public class AgriIndemnizacionTransaction {
public AgriIndemnizacionTransaction(){}
	
	public AgriIndemnizacion crear(AgriIndemnizacion agriIndemnizacion)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriIndemnizacionDAO AgriObjetoDAO = new AgriIndemnizacionDAO();
			agriIndemnizacion = AgriObjetoDAO.crear(agriIndemnizacion);
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
		return agriIndemnizacion;
		}
	public AgriIndemnizacion editar(AgriIndemnizacion agriIndemnizacion)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriIndemnizacionDAO agriIndemnizacionDAO = new AgriIndemnizacionDAO();
			agriIndemnizacion = agriIndemnizacionDAO.editar(agriIndemnizacion);
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
		return agriIndemnizacion;
	}
	public void eliminar(AgriIndemnizacion agriIndemnizacion)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriIndemnizacionDAO agriIndemnizacionDAO = new AgriIndemnizacionDAO();
			AgriIndemnizacion ObjetoAgriBuscado = new AgriIndemnizacion();
			ObjetoAgriBuscado = agriIndemnizacionDAO.buscarPorId(agriIndemnizacion.getId());
			if (ObjetoAgriBuscado!=null)
			{
				agriIndemnizacionDAO.eliminar(ObjetoAgriBuscado);
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
