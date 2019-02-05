package com.qbe.cotizador.transaction.producto.agricola;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.AgriCiclo;

public class AgriCanalTransaction {
	public AgriCanal crear(AgriCanal agriCanal)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCanalDAO AgriObjetoDAO = new AgriCanalDAO();
			agriCanal = AgriObjetoDAO.crear(agriCanal);
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
		return agriCanal;
		}
	public AgriCanal editar(AgriCanal agriCanal)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCanalDAO AgriObjetoDAO = new AgriCanalDAO();
			agriCanal = AgriObjetoDAO.editar(agriCanal);
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
		return agriCanal;
		}
	public void eliminar(AgriCanal agriCanal)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCanalDAO AgriObjetoDAO = new AgriCanalDAO();
			AgriCanal ObjetoAgriBuscado = new AgriCanal();
			ObjetoAgriBuscado = AgriObjetoDAO.BuscarPorId(agriCanal.getCanalId());
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
