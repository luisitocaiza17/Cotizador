package com.qbe.cotizador.transaction.producto.agricola;

import java.math.BigInteger;

import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizadorOfflineDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.AgriCotizadorOffline;

public class AgriCotizadorOfflineTransaction {
	public AgriCotizadorOffline crear(AgriCotizadorOffline agriCotizadorOffline)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizadorOfflineDAO AgriCotizadorOfflineDAO = new AgriCotizadorOfflineDAO();
			agriCotizadorOffline = AgriCotizadorOfflineDAO.crear(agriCotizadorOffline);
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
		return agriCotizadorOffline;
		}
	public AgriCotizadorOffline editar(AgriCotizadorOffline agriCotizadorOffline)
	{
		UserTransaction ut = null; 
		try {
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizadorOfflineDAO AgriCotizadorOfflineDAO = new AgriCotizadorOfflineDAO();
			agriCotizadorOffline = AgriCotizadorOfflineDAO.editar(agriCotizadorOffline);
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
		return agriCotizadorOffline;
		}
	public void eliminar(AgriCotizadorOffline agriCotizadorOffline)
	{
		UserTransaction ut= null;
		try
		{
			ut= (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			AgriCotizadorOfflineDAO AgriCotizadorOfflineDAO = new AgriCotizadorOfflineDAO();
			AgriCotizadorOffline ObjetoAgriBuscado = new AgriCotizadorOffline();
			ObjetoAgriBuscado = AgriCotizadorOfflineDAO.BuscarPorId(agriCotizadorOffline.getID());
			if (ObjetoAgriBuscado!=null)
			{
				AgriCotizadorOfflineDAO.eliminar(ObjetoAgriBuscado);
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
